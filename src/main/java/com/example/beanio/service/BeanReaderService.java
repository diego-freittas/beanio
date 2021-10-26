package com.example.beanio.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.beanio.BeanReader;
import org.beanio.BeanWriter;
import org.beanio.StreamFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.beanio.model.Employee;
import com.example.beanio.repository.BeanReaderRepository;

@Service
public class BeanReaderService {

	List<Employee> employees = new ArrayList<>();

	@Autowired
	private BeanReaderRepository beanReaderRepository;

	public List<Employee> lerArquivo(File file) {
		// create a StreamFactory
		StreamFactory factory = StreamFactory.newInstance();
		// load the mapping file
		factory.load("mapping.xml");

		// use a StreamFactory to create a BeanReader
		BeanReader in = factory.createReader("employeeFile", file);
		Employee employee;

		while ((employee = (Employee) in.read()) != null) {
			// process the employee...
			employees.add(employee);
			beanReaderRepository.save(employee);
		}
		in.close();
		//employees.stream().forEach(System.out::println);
		return employees;
	}

	public void escreveArquivo() {
		// create a StreamFactory
		StreamFactory factory = StreamFactory.newInstance();
		// load the mapping file
		factory.load("mapping.xml");
		// use a StreamFactory to create a BeanWriter
		BeanWriter out = factory.createWriter("employeeFile", new File("employee.csv"));
		// write an Employee object directly to the BeanWriter
		
		employees = beanReaderRepository.findAll();
		employees.stream()
		.forEach(e -> out.write(e));
		out.flush();
		out.close();
	}

}
