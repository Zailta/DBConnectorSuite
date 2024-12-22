package com.dbconnector.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.dbconnector.app.entity.UserEntity;

import jakarta.transaction.Transactional;

@Repository
public interface DataAccessJPAInterface extends JpaRepository<UserEntity, String>{
	
	@Transactional
	@Procedure(name = "GetAllBauserEntityRecords")
	List<UserEntity>  getAllBauserEntityRecords();

}
