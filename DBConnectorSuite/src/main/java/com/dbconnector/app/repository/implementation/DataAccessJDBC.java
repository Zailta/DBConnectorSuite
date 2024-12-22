package com.dbconnector.app.repository.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.dbconnector.app.entity.UserEntity;

@Repository
public class DataAccessJDBC {

	public static final String GET_ALL_USERS = "SELECT * FROM javadb.bauser_entity";
	public static final String GET_ALL_USERS_SP = "GetAllBauserEntityRecords";

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	SimpleJdbcCall jdbcCall;

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

}
