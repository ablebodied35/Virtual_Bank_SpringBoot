package com.VirtualBank.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import com.VirtualBank.demo.model.PersonalInfo;

@Component
public interface PersonalInfoDB extends JpaRepository<PersonalInfo, Integer>{

	@Query("Select firstName from PersonalInfo where accountNum=?1")
	String findByfirstName(int accNum);
	
	@Query("Select lastName from PersonalInfo where accountNum=?1")
	String findBylastName(int accNum);
	
	
	
	
}
