package com.example.basic.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data       //각각의 변수들 get,set메소드 생성해줌
public class Food {
  @Id
  int id;
  String name;
  String address;
  String desc;
  String tel;
  String latitude;
  String longitude;
  String longitude2; //geoje.db에 새로고침하면 longitude2가 생김

}
