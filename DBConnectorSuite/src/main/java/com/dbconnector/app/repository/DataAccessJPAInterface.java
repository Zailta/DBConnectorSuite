package com.dbconnector.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbconnector.app.entity.UserEntity;

@Repository
public interface DataAccessJPAInterface extends JpaRepository<UserEntity, String>{

}
