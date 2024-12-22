package com.dbconnector.app.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.dbconnector.app.repository.DataAccessJPAInterface;
import com.dbconnector.app.repository.implementation.DataAccessJDBC;

@Component
public class DBConnectorRunner implements CommandLineRunner{
	@Autowired
	DataAccessJDBC accessJDBC;
	@Autowired
	DataAccessJPAInterface accessJPAInterface;

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Executing runner: DBConnectorRunner.run()");
		
		accessJDBC.getAllUsersUsingJDBCTemplate().forEach(System.out::println);
		
		accessJPAInterface.findAll().forEach(System.out::println);
		
		
		
		
		
	}

}