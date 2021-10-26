package com.example.beanio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.beanio.model.Employee;

@Repository
public interface BeanReaderRepository extends JpaRepository<Employee, Long>{



}
