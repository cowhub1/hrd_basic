package com.example.basic.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.ToString;


@Entity
@Data
@ToString(exclude = "board")
public class Comment {
@Id @GeneratedValue
int id;
String content;
String writer;
//작성일자  ,DB에서 언더스코어로 표현됨
Date creDate;

@ManyToOne
Board board;
}
