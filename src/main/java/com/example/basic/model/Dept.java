package com.example.basic.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
// import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Entity 
@Data   
@ToString(exclude = {"emps"}) //syso출력시 emps안나오게함
public class Dept {
  @Id @GeneratedValue
  Integer deptno;
  @Column(nullable = true)
  String dname;
  String loc;  

@JsonIgnore
@OneToMany(mappedBy = "dept") 
// @OneToMany(mappedBy = "dept",fetch = FetchType.EAGER) 
List<Emp> emps = new ArrayList<>();

}
