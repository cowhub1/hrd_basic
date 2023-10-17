package com.example.basic.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.basic.model.Board;
import com.example.basic.model.Comment;
import com.example.basic.model.FileAtch;
import com.example.basic.repository.BoardRepository;
import com.example.basic.repository.CommentRepository;
import com.example.basic.repository.FileAtchRepository;



@Controller
@RequestMapping("/board")
public class BoardController {

  @Autowired
  BoardRepository boardRepository;

  @Autowired
  HttpSession session;

  // 게시글 작성
  @GetMapping("/write")
  public String write() {
    String name = (String) session.getAttribute("name");
    // trim 앞 뒤 공백제거기능
    if (name == null || name.trim().equals("")) {
      return "redirect:/auth/signin";
    }
    return "board/write";
  }

  @PostMapping("/write")
  public String writePost(@ModelAttribute Board board) {
    boardRepository.save(board);
    return "redirect:/board/write";
  }

  // 게시글 조회

  @GetMapping({ "/list", "/" })
  public String list(Model model, @RequestParam(defaultValue = "1") int p) {
    Direction dic = Direction.ASC;
    Sort sort = Sort.by(dic, "id");
    Pageable page = PageRequest.of(p - 1, 5, sort); // page값바꾸면 그 페이지 내용 나옴
    Page<Board> boardList = boardRepository.findAll(page);

    int totalpage = boardList.getTotalPages();
    int startPage = (p - 1) / 10 * 10 + 1;
    int endPage = totalpage;
    int previousPage = p > 1 ? p - 1 : 1;
    int nextPage = p < totalpage ? p + 1 : p;
    
    model.addAttribute("startPage", startPage);
    model.addAttribute("endPage", endPage);
    model.addAttribute("previousPage", previousPage);
    model.addAttribute("nextPage", nextPage);
    model.addAttribute("p", p);
    
    
    model.addAttribute("boardList", boardList);
    
    // model.addAttribute(String name, Object value) : value 객체를 name 이름으로 추가함.
    // 뷰 코드에서는 name으로 지정한 이름을 통해서 value를 사용함. => th:each="board : ${boardList}"
    // model.addAttribute("boardList", boardList.getContent()) -> 이게 정석;
    return "board/list";
  }

  // 게시글 상세보기

  @GetMapping("/detail")
  public String detail(@RequestParam int id, Model model, @RequestParam(defaultValue = "1") int p) {
    Optional<Board> opt = boardRepository.findById(id);
    model.addAttribute("board", opt.get());
    model.addAttribute("p", p);
    // 게시글 삭제하기
    return "board/detail";
  }

  // 게시글 수정하기

  @GetMapping("/update")
  public String update(@RequestParam int id, Model model, @RequestParam(defaultValue = "1") int p) {
    Optional<Board> opt = boardRepository.findById(id);
    model.addAttribute("board", opt.get());
    model.addAttribute("p", p);
    return "board/update";
  }

  @PostMapping("/update")
  public String updatePost(@ModelAttribute Board board, @RequestParam(defaultValue = "1") int p) {
    boardRepository.save(board);
    return "redirect:/board/list?p=" + p;
  }

  @GetMapping("/remove")
  public String remove(@RequestParam int id, Model model, @RequestParam(defaultValue = "1") int p) {
    // 현재 로그인한 사용자의 정보를 세션에서 가져옴.(empList-owner정보 id와 name으로 로그인했음)
    String loggedName = (String) session.getAttribute("name");
    // 현재 게시글의 작성자 이름
    Optional<Board> dbBoard = boardRepository.findById(id);
    String savedName = dbBoard.get().getWriter();
    // 현재 로그인한 사용자와 작성자가 일치하는지 확인합니다.
    if (loggedName != null && loggedName.equals(savedName)) {
      Board board = new Board();
      board.setId(id);

      boardRepository.delete(board);
    } else {
      // 작성자가 일치하지 않을 경우 권한이 없음을 알리는 메시지
      model.addAttribute("errorMessage", "작성자 권한이 없습니다. 로그인 하세요.");
      return "board/remove";
      // return "redirect:/board/detail?id="+id;
      //rediect는 model의 값을 확인 할 수 없다!!!
    }

    model.addAttribute("p", p);
    return "redirect:/board/list?p=" + p;
  }


  //댓글 달기
  
  @Autowired
  CommentRepository commentRepository;

  @PostMapping("/comment")
  public String comment(@ModelAttribute Comment comment,@RequestParam int boardId) {
      String name = (String) session.getAttribute("name");
      if(name ==null){
        name = "Anonymous";
      }
      comment.setWriter(name);
      comment.setCreDate(new Date());
      //외래키로 연괸되어있는 board에 있는 id를 @RequestParam int boardId로 받기
      Board board = new Board();
      board.setId(boardId);
      comment.setBoard(board);
      commentRepository.save(comment);
        List<Comment> data = commentRepository.findAll();
        System.out.println(data);
      return "redirect:/board/detail?id="+boardId;
      // return "redirect:/board/list";
  }
  
  
  //댓글 삭제
  @GetMapping("/comment/remove")
  public String commentRemove(@ModelAttribute Comment comment,@RequestParam int boardId) {
    //1.new Comment(), setId() - @RequerstParam
    //2.modelAtrribute Comment comment
    commentRepository.delete(comment);
      return "redirect:/board/detail?id="+boardId;
  }
  @Autowired
  FileAtchRepository fileAtchRepository;
   @GetMapping("/fileAtch/remove")
  public String fileRemove(@ModelAttribute FileAtch fileAtch,@RequestParam int boardId) {
    
    fileAtchRepository.delete(fileAtch);
      return "redirect:/board/detail?id="+boardId;
  }
  
}
