package com.example.basic.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;



// @Entity //개체
// @Data   //set,get메소드를 자동으로 생성해주는 애노테이션
// public class Emp {
//   @Id @GeneratedValue
//   int empno;

//   @Column(nullable = true)
//   // @Column(nullable = false, name = "name")
//   String ename;
//   String job;
//   int mgr;
// }

@Entity //개체
@Data   //set,get메소드를 자동으로 생성해주는 애노테이션
// @ToString(exclude = {"dept"})
public class Emp {
  @Id @GeneratedValue
  Integer empno;
  // @Column(nullable = false, name = "name")
  String ename;
  String job;
  Integer mgr;
  String hiredate;
  Integer sal;
  Integer comm;
 

  @ManyToOne
  @JoinColumn(name = "deptno") //외래키 연결!! (기본 : dept_deptno)
  Dept dept;   //객체(class) : 객체(class)!  Integer dept(xxx)
}
