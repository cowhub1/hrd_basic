package com.example.basic.controller;


import java.security.NoSuchAlgorithmException;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.basic.model.Owner;
import com.example.basic.repository.OwnerRepository;
import com.example.basic.util.Encrypt;

@Controller
@RequestMapping("/auth")
public class AuthController {
  @Autowired
  HttpSession session;
  @Autowired
   OwnerRepository ownerRepository;

  @GetMapping("/login")
  public String login() {
    session.setAttribute("user_id", "HRD");
    return "redirect:/empList";
    // return "auth/login";
  }


  
  
  
  // @RequestMapping("/reqlogin")
  // public String reqLogin(@RequestParam Integer id, @RequestParam String name){
  // Owner owners = ownerRepository.findByIdAndName(id, name);
  
  // if (!owners.isEmpty()) {
    // // Owner 엔터티를 찾았을 경우, 즉 로그인 성공
    // // 여기에서 세션 설정 등의 로그인 성공 시의 동작을 정의하세요.
    // return "redirect:/auth/login"; // 로그인 후 대시보드 페이지로 리다이렉트
    // // return "redirect:/dashboard";
    // } else {
      // // Owner 엔터티를 찾지 못한 경우, 즉 로그인 실패
      // // 로그인 실패 시 다시 로그인 폼으로 이동
      // return "redirect:/auth/logout"; // 로그인 페이지로 이동
      
      // }
      // }
      
      @GetMapping("/ownerLogin")
      @ResponseBody
  public Owner ownerLogin(@RequestParam Integer id, @RequestParam String name) {
    Owner owner = ownerRepository.findByIdAndName(id, name);
    System.out.println(owner);
    if (owner != null) {
      session.setAttribute("id", id);
      session.setAttribute("name", name);
      
    }
    return owner;
  }
  
  // 회원가입
  @GetMapping("signup")
  public String singup() {
    return "auth/signup";
  }

  @PostMapping("signup")
  public String singupPost(@ModelAttribute Owner owner) {
    System.out.println(owner);
    int id = owner.getId();
    // String name = owner.getName();
    // Owner result2 = ownerRepository.findByIdAndName(id, name);
    // if (result2 != null) {
      // return "/auth/error";
      // } else {
        // ownerRepository.save(owner);
        // return "redirect:/auth/signup";
        // }
        Optional<Owner> ownerid = ownerRepository.findById(id);
        // 주어진 ID에 해당하는 Owner 객체가 있다면 isPresent()를 통해 true 값을 반환, 그렇지 않다면 false 값을 반환
        
        //encode로 암호화 시킨다음에 owner에 set으로 newpwd넣기
        String newpwd = passwordEncoder.encode(owner.getPwd());
        owner.setPwd(newpwd);
        
        if (ownerid.isPresent()) {
          return "/auth/error";
        } else {
          ownerRepository.save(owner);
          return "redirect:/auth/signup";
          
          // redirect: 쓰는이유 POST방식을 끝내고 새로고침해서 GET방식으로 바꿔줌
        }
      }
      
      // id중복검사
      @GetMapping("/id-check")
      @ResponseBody
      public String idCheck(@ModelAttribute Owner owner) {
        int id = owner.getId();
        Optional<Owner> opt = ownerRepository.findById(id);
        if (opt.isPresent()) { // 값 있음(아이디가 있음, 가입불가)
          return "가입불가";
        } else {// 값 없음(아이디가 없음, 가입가능)
          return "가입가능";
        }
        
      }
      
      @Autowired
      PasswordEncoder passwordEncoder;
      
      @Autowired
      Encrypt encrypt;
      
      // 로그인
      @GetMapping("signin")
      public String singin() throws NoSuchAlgorithmException {
        String pwd = passwordEncoder.encode("1");
        System.out.println(pwd);
        String pwd2 = encrypt.encode("1");
        System.out.println(pwd2);
        
        // 비밀번호 암호화
        // String raw = "password1234";
        // String rawAndSalt = "abcd1234";
        // MessageDigest md = MessageDigest.getInstance("SHA-256");

    // md.update(raw.getBytes());
    // String hex = String.format("%064x", new BigInteger(1, md.digest()));
    // System.out.println("raw의 해시값 : " + hex);
    // md.update(rawAndSalt.getBytes());
    // hex = String.format("%064x", new BigInteger(1, md.digest()));
    // System.out.println("raw+salt의 해시값 : " + hex);
    
    return "auth/signin";
  }
  //암호화 전
  // @PostMapping("signin")
  // public String singinPost(@ModelAttribute Owner owner) {
    //   System.out.println(owner);
    //   int id = owner.getId();
    //   String name = owner.getName();
    //   Owner result = ownerRepository.findByIdAndName(id, name);
    //   if (result != null) {
      //     session.setAttribute("id", id);
      //     session.setAttribute("name", name);
      //     return "redirect:/empList";
      //   } else {
        //     return "/auth/signin";
        //   }
        // }
        //암호화
        @PostMapping("signin")
        public String singinPost(@ModelAttribute Owner owner) {
          
          int id = owner.getId();
          Optional<Owner> result = ownerRepository.findById(id); //그 id에 해당하는 모든 정보를 result에 저장
          
    //isPresent로 id가 없는 값을 쓰더라도 오류 안나게 수정
    boolean isPresent = result.isPresent();
    if(isPresent){
      String pwd = result.get().getPwd(); //암호화된 비밀번호 가져오기
      String name = result.get().getName();
      boolean isMatch= passwordEncoder.matches(owner.getPwd(), pwd);
      System.out.println(isMatch);
      
      if (isMatch) {
      session.setAttribute("id", id);
      session.setAttribute("name", name);
      
    }
    return "redirect:/empList";}
    else {
      return "redirect:/auth/signin";
    }
  }
  @GetMapping("/logout")
  public String logout() {
    session.removeAttribute("id");
    session.removeAttribute("name");
    return "auth/logout";
  }
  @GetMapping("/back")
  public String back() {
    session.removeAttribute("id");
    session.removeAttribute("name");
    return "auth/signin";
  }
  
}
