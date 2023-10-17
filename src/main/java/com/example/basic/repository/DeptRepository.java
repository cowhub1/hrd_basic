package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.basic.model.Dept;


@Repository
public interface DeptRepository extends JpaRepository<Dept, Integer>{
 //jpa레파지토리 상속받음! , EMP 테이블을 Integer형태로 가져옴
}