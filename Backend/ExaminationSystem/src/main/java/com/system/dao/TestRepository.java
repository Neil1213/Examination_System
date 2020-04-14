package com.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.system.model.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer>{

}
