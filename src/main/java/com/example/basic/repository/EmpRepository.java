package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.basic.model.Emp;
import java.util.List;



@Repository
public interface EmpRepository extends JpaRepository<Emp, Integer>{
 //jpa레파지토리 상속받음! , EMP 테이블을 Integer형태로 가져옴
  public List<Emp> findByJob(String job);
  public List<Emp> findBySalGreaterThanEqual(Integer sal);
}