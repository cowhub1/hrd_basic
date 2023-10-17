package com.example.basic.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = "owner")
public class Animal {
  @Id @GeneratedValue
  int id;
  String name;
  int age;
  // int owner_id; 이거 쓰면 안됨

  @JsonIgnore
  @ManyToOne
  Owner owner;
  
  @OneToMany(mappedBy = "animal")
 List<Product> products = new ArrayList<>();
}
