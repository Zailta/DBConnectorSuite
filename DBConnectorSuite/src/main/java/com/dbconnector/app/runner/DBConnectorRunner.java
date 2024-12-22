package com.dbconnector.app.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.dbconnector.app.repository.DataAccessJPAInterface;
import com.dbconnector.app.repository.implementation.DataAccessJDBC;

import jakarta.transaction.Transactional;

@Component
public class DBConnectorRunner implements CommandLineRunner{
	@Autowired
	DataAccessJDBC accessJDBC;
	@Autowired
	DataAccessJPAInterface accessJPAInterface;

	@Override
	@Transactional // Add this to ensure transaction is closing
	public void run(String... args) throws Exception {
		
		System.out.println("Executing runner: DBConnectorRunner.run()");
		
		/*
		 * accessJDBC.getAllUsersUsingJDBCTemplate().forEach(System.out::println);
		 * accessJPAInterface.findAll().forEach(System.out::println);
		 * accessJDBC.getAllUsersUsingSimpleJDBCCall().forEach(System.out::println);
		 */
		
		//accessJPAInterface.getAllBauserEntityRecords().forEach(System.out::println);
		
		//accessJDBC.getAllUsersUsingMapQuery().forEach(System.out::println);
		
		accessJDBC.getUsersUsingParameterisedSP().forEach(System.out::println);
		
	}

}
