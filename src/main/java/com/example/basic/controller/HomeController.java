package com.example.basic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.example.basic.model.Emp;
import com.example.basic.model.Food;
import com.example.basic.model.Player;
import com.example.basic.model.Shop;
import com.example.basic.repository.EmpRepository;
import com.example.basic.repository.FoodRepository;
import com.example.basic.repository.PlayerRepository;
import com.example.basic.repository.ShopRepository;

import lombok.Data;

@Data // set메소드,get메소드 안써도 자동으로 만들어줌
class Phone {
  String brand;
  int price;

  // String getBrand(){
  // return this.brand;
  // }
  // //get메소드 : get~

  // void setBrand(String brand){
  // this.brand = brand;
  // }
  // set메소드 : set~(카멜표기법) 다음에 매개변수로()씀
}

@Controller
public class HomeController {

  @Autowired
  FoodRepository foodRepository;

  @GetMapping("/foodList")
  @ResponseBody
  public List<Food> foodList() {
    List<Food> list = foodRepository.findAll();
    return list;
  }

  // geoje.db의 food테이블 데이터를 10개씩 끊어서 출력하기 p1,p2...
  @GetMapping("/foodpagination") // pagination:페이지 매기기
  @ResponseBody
  public List<Food> foodpagination(
      @RequestParam(defaultValue = "1") int p) { // data.domain으로 import하기!!
    Sort sort = Sort.by(Direction.DESC, "id");// 정렬하고자하는 변수명
    Pageable page = PageRequest.of(p - 1, 10, sort); // page값바꾸면 그 페이지 내용 나옴
    Page<Food> list = foodRepository.findAll(page);
    System.out.println(list);
    // System.out.println(list.getTotalPages()); //전체 page갯수알려줌
    return list.getContent();
  }

  @Autowired // 레파지토리 자동연결!
  EmpRepository empRepository;

  // @GetMapping("/empList")
  // @ResponseBody
  // public List<Emp> empList(){
  // List<Emp> list = empRepository.findAll();
  // return list;
  // }

  @GetMapping("/emp")
  @ResponseBody
  public Emp emp() {
    Emp emp = new Emp();

    emp.setEmpno(1);
    emp.setEname("abcd");
    emp.setJob("developer");
    emp.setMgr(100);
    empRepository.save(emp);

    return emp;

    // 모델어트리뷰트 사용 save기능 사용 :
    // localhost:8080/emp?empno=3&ename=AA&job=designer&mgr=100하면 db에저장됨
    // public Emp emp(@ModelAttribute Emp emp){
    // empRepository.save(emp);
    // return emp;
  }

  @GetMapping("/emp-all-data")
  @ResponseBody
  public List<Emp> emps() {
    List<Emp> list = empRepository.findAll();
    System.out.println(list);
    return list;
  }

  // 데이터를 끊어서 가져오는 기능을 넣어줌
  @GetMapping("/pagination") // pagination:페이지 매기기
  @ResponseBody
  public List<Emp> pagination() { // data.domain으로 import하기!!
    Sort sort = Sort.by("ename");// 정렬하고자하는 변수명
    Pageable page = PageRequest.of(1, 5, sort); // page값바꾸면 그 페이지 내용 나옴
    Page<Emp> list = empRepository.findAll(page);
    System.out.println(list);
    // System.out.println(list.getTotalPages()); //전체 page갯수알려줌
    return list.getContent();
  }

  @GetMapping("pagination1")
  public String pagination1(
      Model model, @RequestParam(defaultValue = "1") int page) {
    int startPage = (page - 1) / 10 * 10 + 1;
    int endPage = startPage + 9;
    model.addAttribute("startPage", startPage);
    model.addAttribute("endPage", endPage);
    model.addAttribute("page", page);
    return "pagination";
  }

  @Autowired // 레파지토리 자동연결!
  ShopRepository shopRepository;

  @GetMapping("/shopList")
  @ResponseBody
  public List<Shop> shopList() {
    List<Shop> list = shopRepository.findAll();
    return list;
  }

  @Autowired // 레파지토리 자동연결!
  PlayerRepository playerRepository;

  @GetMapping("/player")
  @ResponseBody
  public List<Player> player() {
    List<Player> list = playerRepository.findAll();
    System.out.println(list);
    return list;
  }

  @RequestMapping("/")
  public String home(
      @RequestParam(defaultValue = "zzz", required = false) String userId,
      @RequestParam String userPw,
      // defaultValue = "기본값" , required = "필수값"false하면 필수로 userId안써도 됨
      @ModelAttribute Phone phone) {
    System.out.println(userId + userPw);
    System.out.println(phone);
    return "home";
  }

  @RequestMapping("/json")
  @ResponseBody
  public Map<String, Object> json() {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("name", "1");
    map.put("name2", "2");
    // map은 자바에서 객체담당
    return map;
  }

  @RequestMapping("/json/{a}")
  @ResponseBody
  public Map<String, Object> path(
      @PathVariable String a) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("name", "hong");
    map.put("name2", 1234);
    map.put("sowon", a);
    System.out.println(a);
    // map은 자바에서 객체담당
    return map;
  }

  @ResponseBody
  @RequestMapping("/phone")
  public Phone phone() {
    Phone phone = new Phone();
    phone.setBrand("LG");
    phone.setPrice(10000);

    return phone;
  }

  @RequestMapping("/html/exam")
  public String exam() {
    return "html/exam";
  }

  @ResponseBody
  @RequestMapping("/json/exam")
  public Map<Object, Object> exam2() {
    Map<Object, Object> map = new HashMap<Object, Object>();
    map.put("count", 2);

    List<Map<Object, Object>> list = new ArrayList<>();
    Map<Object, Object> map2 = new HashMap<Object, Object>();
    map2.put("name", "가");
    map2.put("userId", "A");
    map2.put("userPassword", "1");
    list.add(map2);
    map2 = new HashMap<Object, Object>();
    map2.put("name", "나");
    map2.put("userId", "B");
    map2.put("userPassword", "2");
    list.add(map2);
    map.put("list", list);

    return map;
  }

  // @RequestMapping("/")
  // public String home(@RequestParam("pageNo") int pageNo) {
  // System.out.println(pageNo);
  // return "home";
}