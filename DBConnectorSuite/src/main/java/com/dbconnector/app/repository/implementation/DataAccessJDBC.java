package com.dbconnector.app.repository.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.dbconnector.app.entity.UserEntity;


@Repository
public class DataAccessJDBC {
	
	public static final String  GET_ALL_USERS= "SELECT * FROM javadb.bauser_entity";
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<UserEntity> getAllUsersUsingJDBCTemplate() {
		return  jdbcTemplate.query(GET_ALL_USERS, (ResultSetExtractor<List<UserEntity>>) rs -> {
			List<UserEntity> userList = new ArrayList<UserEntity>();
			
			while(rs.next()) {
				UserEntity userEntity = new UserEntity();
				userEntity.setUser_id(rs.getString("user_id"));
				userEntity.setFirstname(rs.getString("firstname"));
				userEntity.setEmail(rs.getString("email"));
				userEntity.setAbout(rs.getString("about"));
				userList.add(userEntity);
			}
			
			return userList;
		});
		
		
	}
	
	

}
