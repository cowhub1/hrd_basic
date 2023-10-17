package com.example.basic.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.basic.model.Owner;


@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer>{
 //jpa레파지토리 상속받음! , EMP 테이블을 Integer형태로 가져옴
  // public List<Owner> findByIdAndName(int id,String name);
  public abstract Owner findByIdAndName(int id,String name); //다른기준으로 추상메소드 만들때
  public abstract  Optional<Owner> findById(int id);


}