package com.example.basic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Shop {
  @Id
  @GeneratedValue
  // int shop_id;
  // String shop_name;
  // String shop_desc;
  // String rest_date;
  // String parking_info;
  // String img_path;

  @Column(name="id") Integer Id;
  @Column(name="shop_name") String shopName;
  @Column(name="shop_desc") String shopDesc;
  @Column(name="rest_date") String restDate;
  @Column(name="parking_info") String parkingInfo;
  @Column(name="img_path") String imgPath;
  //shop_id 데이터를 카멜형식으로 json으로 나타냄

}
