package com.example.basic;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.basic.model.Dept;
import com.example.basic.model.Emp;
import com.example.basic.model.Owner;
import com.example.basic.model.Product;
import com.example.basic.repository.DeptRepository;
import com.example.basic.repository.EmpRepository;
import com.example.basic.repository.OwnerRepository;
import com.example.basic.repository.ProductRepository;

@SpringBootTest
class BasicApplicationTests {
	
	@Autowired
	DeptRepository deptRepository;
	@Autowired
	EmpRepository empRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	OwnerRepository ownerRepository;

	@Test @Transactional
	void Dept테이블조회하기() {
	 List<Dept> list = deptRepository.findAll();
	System.out.println(list);
	}

	@Test 
	void Emp테이블조회하기() {
	 List<Emp> list = empRepository.findAll();
	System.out.println(list);
	}
	
	@Test 
	void Emp테이블의Job조회하기() {
	 List<Emp> list = empRepository.findByJob("MANAGER");
	System.out.println(list);
	}
	@Test 
	void Emp급여조회하기() {
	 List<Emp> list = empRepository.findBySalGreaterThanEqual(2000);
	System.out.println(list);
	}
 
	@Test 
	void prouduct조회하기() {
	 Optional<Product> opt = productRepository.findById(1);
	//  Product product = opt.get();
	System.out.println(opt);
	}
 
	@Test @Transactional
	void owner조회하기() {
	 Optional <Owner> opt = ownerRepository.findById(1);
	
	System.out.println(opt);
	}


}
