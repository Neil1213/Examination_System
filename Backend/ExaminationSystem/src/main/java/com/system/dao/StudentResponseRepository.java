package com.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.system.model.StudentResponse;

@Repository
public interface StudentResponseRepository extends JpaRepository<StudentResponse, Integer>{

}
