package com.example.basic.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.basic.model.Board;
import com.example.basic.model.FileAtch;
import com.example.basic.model.FileInfo;
import com.example.basic.repository.BoardRepository;
import com.example.basic.repository.FileAtchRepository;

@Controller
public class UploadController {
  @Autowired
  FileAtchRepository fileAtchRepository;
  @Autowired
  BoardRepository boardRepository;

  @GetMapping("/upload1")
  public String upload1() {
    return "upload1";
  }

  //MultipartHttpServletRequest 매개변수 사용하기
  @PostMapping("/upload1")
  // @ResponseBody
  public String upload1Post(@ModelAttribute Board board,@RequestParam("file") MultipartFile[] mFiles,MultipartHttpServletRequest mRequest,RedirectAttributes redirectAttributes) {
    Board saveBoard = boardRepository.save(board);
   
    String result = "";
    for(MultipartFile mFile : mFiles){
    
      String oName = mFile.getOriginalFilename();
      long size = mFile.getSize();
      result += oName + " 용량 : " + mFile.getSize()+ "/" ;
    
    
    // 파일에 자동 저장-transferTo()(새로 생성된 files폴더에 업로드파일을 filename으로 저장)
    File folder = new File("c:/files");
    folder.mkdirs(); // 폴더생성

    //1)중복파일이 존재하는지 확인하기(.isFile() 또는 .exists())
    File file = new File("c:/files/" + oName);
    boolean isFile = file.isFile();
    System.out.println(isFile);

    //2)중복파일이 있다면 파일명을 변경하기(oName은 훼손xx)
    String sName = oName;
    if(isFile){
      //            abc.jpg => abc_183848585.jpg : abc + 유니코드 + .확장자
      String name = oName.substring(0, oName.indexOf('.'));
      String ext = oName.substring(oName.indexOf('.'));
      sName = name + System.currentTimeMillis() + ext;
      System.out.println(sName);
    }

    //파일업로드 저장하는 코드
    try {
      mFile.transferTo(new File("c:/files/"+ sName));
      
    } catch (IllegalStateException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    //첨부파일의 정보를 데이터베이스에 저장(@Entity,@Repository 필요)
    FileAtch fileAtch = new FileAtch();
    fileAtch.setOriginalName(oName);
    fileAtch.setSaveName(sName);
    
    
    // board.setId(51); //데이터 무결성 제약조건(무조건 board에 있는 id값을 입력해야함)
    fileAtch.setBoard(saveBoard);
    fileAtchRepository.save(fileAtch);
    // result += "파일 이름 : " + oName + " / 용량 : " + size + "<br>";
  }
    redirectAttributes.addFlashAttribute("message", result);
      // return "파일 이름 : " + oName + " , 용량 : " + size;
      return "redirect:/board/write";
  }

  

  //@RequestParam 이용해서 업로드
  @PostMapping("/upload2")
  @ResponseBody
  public String upload2(@RequestParam("file") MultipartFile mFile) {
                        //file이름으로 받을꺼야 그런데 사용할 변수명은 mFile이야
    String result = "";
    String oName = mFile.getOriginalFilename();
    result += oName + "<br>" + mFile.getSize();
    
    //1)중복파일이 존재하는지 확인하기(.isFile() 또는 .exists())
     File file = new File("c:/files/"+ oName);
     boolean isFile = file.isFile();
     
    //2)중복파일이 있다면 파일명을 변경하기(oName은 훼손xx)
    String sName = oName;
    if(isFile){
      //            abc.jpg => abc_183848585.jpg : abc + 유니코드 + .확장자
      String name = oName.substring(0, oName.indexOf('.'));
      String ext = oName.substring(oName.indexOf('.'));
      sName = name + System.currentTimeMillis() + ext;
      System.out.println(sName);
    }
    //파일업로드 저장하는 코드
    try {
      mFile.transferTo(new File("c:/files/"+ sName));
    } catch (IllegalStateException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();  
    }
    //첨부파일의 정보를 데이터베이스에 저장(@Entity,@Repository 필요)
    FileAtch fileAtch = new FileAtch();
    fileAtch.setOriginalName(oName);
    fileAtch.setSaveName(sName);
    
    Board board = new Board();
    board.setId(7); //데이터 무결성 제약조건(무조건 board에 있는 id값을 입력해야함)
    fileAtch.setBoard(board);
    fileAtchRepository.save(fileAtch);

    return result;
  }

//@ModelAttribute사용 -> FileInfo.java 모델 만들어줘야함
  @PostMapping("/upload3")
@ResponseBody
public String upload3Post(@ModelAttribute FileInfo info) {
String result = "";
String oName = info.getFile().getOriginalFilename();
result += oName + "<br>" + info.getFile().getSize();

return result;
}

//반복문 사용해서 여러개 첨부하기
@PostMapping("/upload4")
@ResponseBody
public String upload4Post(
@RequestParam("file") MultipartFile[] mFiles) {
String result = "";
  //반복문1
for(int i = 0; i < mFiles.length; i++ ){
MultipartFile mFile = mFiles[i];
}
  //반복문2
for(MultipartFile mFile : mFiles) {
String oName = mFile.getOriginalFilename();
result += oName + " : " + mFile.getSize() + "<br>";
} 
return result;
}


//추가버튼 만들기
@GetMapping("/upload6")
public String upload6() {
return "upload6";
}
@PostMapping("/upload6")
@ResponseBody
public String upload6Post(MultipartHttpServletRequest mRequest) {
String result = "";
Iterator<String> fileNames = mRequest.getFileNames();
//mRequest변수에 파일이름들을 가져옴 string으로 문자열형태로 가져오게됨
while(fileNames.hasNext()) {
  //hasNext()는 boolean이라 다음 name이 존재하다면 반복하게 함 
  String fileName = fileNames.next();
  List<MultipartFile> mFiles = mRequest.getFiles(fileName);
  //fileName에 해당하는 파일을 mFiles으로 가져옴(리스트형태)

for(MultipartFile mFile : mFiles) {
  // mFiles라는 MultipartFile 객체의 리스트나 배열에 대해 반복
  // 각 반복마다 mFile이라는 변수에 리스트의 현재 요소(파일)가 할당
  String oName = mFile.getOriginalFilename();
  long size = mFile.getSize();
  result += oName + " : " + size + "<br>";
  }
}
return result;
}

}
