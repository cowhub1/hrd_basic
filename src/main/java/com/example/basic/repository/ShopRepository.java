package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.basic.model.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Integer> {
  
}
