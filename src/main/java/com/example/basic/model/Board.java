package com.example.basic.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Board {
  @Id
  @GeneratedValue //순차적으로 증가하는 시퀀스
  int id; //기본키
  String title;
  String content;
  String writer;
  //string은 null허용이 기본!
  //board랑 comment랑 양방향
  @OneToMany(mappedBy = "board")
 List<Comment> comments = new ArrayList<>();

 @OneToMany(mappedBy = "board")
 List<FileAtch> fileAtchs = new ArrayList<>();
 
//   @ManyToOne
// @JoinColumn(name = "name")
// //team_id 으로 컬럼 생성
// Owner owner;

}
