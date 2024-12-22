package com.dbconnector.app.repository.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Repository;

import com.dbconnector.app.entity.UserEntity;



@Repository
public class DataAccessJDBC{

	public static final String GET_ALL_USERS = "SELECT * FROM javadb.bauser_entity";
	public static final String GET_ALL_USERS_SP = "GetAllBauserEntityRecords";
	public static final String GET__USERS__PARAMETERISED_SP = "FindUserByEmail";

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	SimpleJdbcCall jdbcCall;
	@Autowired
	DataSource dataSource;

	public List<UserEntity> getAllUsersUsingJDBCTemplate() {
		return jdbcTemplate.query(GET_ALL_USERS, (ResultSetExtractor<List<UserEntity>>) rs -> {
			List<UserEntity> userList = new ArrayList<UserEntity>();

			while (rs.next()) {
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

	public List<UserEntity> getAllUsersUsingSimpleJDBCCall() {

		jdbcCall.setProcedureName(GET_ALL_USERS_SP);

		jdbcCall.returningResultSet("result", (RowMapper<UserEntity>) (rs, row) -> {

			UserEntity userEntity = new UserEntity();
			userEntity.setUser_id(rs.getString("user_id"));
			userEntity.setFirstname(rs.getString("firstname"));
			userEntity.setEmail(rs.getString("email"));
			userEntity.setAbout(rs.getString("about"));

			return userEntity;

		});

		Map<String, Object> output = jdbcCall.execute();

		List<UserEntity> userList = (List<UserEntity>) output.get("result");
		return userList;
	}
	
	
	public List<UserEntity> getUsersUsingParameterisedSP() {

		jdbcCall.setProcedureName(GET__USERS__PARAMETERISED_SP);
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("input_email", "cba@accenture.com");

		jdbcCall.returningResultSet("result", (RowMapper<UserEntity>) (rs, row) -> {

			UserEntity userEntity = new UserEntity();
			userEntity.setUser_id(rs.getString("user_id"));
			userEntity.setFirstname(rs.getString("firstname"));
			userEntity.setEmail(rs.getString("email"));
			userEntity.setAbout(rs.getString("about"));

			return userEntity;

		});

		Map<String, Object> output = jdbcCall.execute(parameter);

		List<UserEntity> userList = (List<UserEntity>) output.get("result");
		return userList;
	}
	
	
	/**
	 * This class extends MapSqlQuery to encapsulate a reusable SQL query.
	 * It supports named parameters and simplifies result mapping to Java objects.
	 * The query is precompiled and can be executed with dynamic parameters using a Map.
	 * 
	 */
		final GetAllUsers getAllUsers;
		@Autowired
		DataAccessJDBC(DataSource dataSource){
			this.getAllUsers = new GetAllUsers(dataSource, GET_ALL_USERS);
		}
	
	public List<UserEntity>  getAllUsersUsingMapQuery(){
		return getAllUsers.execute();
	}
	
	
	 public static class GetAllUsers extends MappingSqlQuery<UserEntity> {
		 
		 GetAllUsers(DataSource dataSource, String query){
			 super(dataSource, query);
			 super.compile();
		 }

		@Override
		protected UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			UserEntity userEntity = new UserEntity();
			userEntity.setUser_id(rs.getString("user_id"));
			userEntity.setFirstname(rs.getString("firstname"));
			userEntity.setEmail(rs.getString("email"));
			userEntity.setAbout(rs.getString("about"));

			return userEntity;
		}
		 
	 }

}
