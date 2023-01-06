package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("test");

		// rdao.setConnection(DbConnection.getConnection());
        // ddao.setConnection(DbConnection.getConnection());
		
		// Region region = new Region();
		// region.setRegion_id(2);
		// region.setRegion_name("Jakarta");

		// rdao.insert(region);
	}

}
