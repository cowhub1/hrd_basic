package com.example.basic.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Entity
@Data
// @ToString(exclude = {"boards"})
public class Owner {
  @Id 
  // @GeneratedValue //자동증가
  int id;
  String name;
  String pwd;

  
  @JsonIgnore
  @OneToMany(mappedBy = "owner")
    // List<Board> boards = new ArrayList<>();
    List<Animal> animals = new ArrayList<>();



}
