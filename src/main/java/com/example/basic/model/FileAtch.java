package com.example.basic.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = "board")
public class FileAtch {
  @Id
  @GeneratedValue
  int id;
  String originalName;
  String saveName;
 
  @ManyToOne
  Board board;

  
}
