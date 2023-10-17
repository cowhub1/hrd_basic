package com.example.basic.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public class Encrypt {
  public String encode(String raw) {
    //try-catch하고  try는 맨위로 / catch는 맨 아래로 옮겨주기  
    
    String hex=null;
    try {
    // String raw = "password1234";
    //왜 없애는지? encode메소드에 넣어줌으로서 raw값 넣으면 자동으로 암호화 하도록 하는것
    // String rawAndSalt = "abcd1234";
    MessageDigest md;
      md = MessageDigest.getInstance("SHA-256");
      
      //방법1
      md.update(raw.getBytes());
      hex = String.format("%064x", new BigInteger(1, md.digest()));
      System.out.println("raw의 해시값 : " + hex);
      
      //방법2(양념친거-보안이 더 좋아짐)
      // md.update(rawAndSalt.getBytes());
      // hex = String.format("%064x", new BigInteger(1, md.digest()));
      // System.out.println("raw+salt의 해시값 : " + hex);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return hex;
  }
}
