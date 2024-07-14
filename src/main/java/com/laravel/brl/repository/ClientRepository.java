package com.laravel.brl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.laravel.brl.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	
	List<Client> findByNameClient(String name);
	
	@Query("select c from Client c where c.nameClient like %:name") 
	List<Client> findByName (@Param("name")String name); 
	
}
