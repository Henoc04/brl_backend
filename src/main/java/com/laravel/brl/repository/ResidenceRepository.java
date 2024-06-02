package com.laravel.brl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.laravel.brl.models.Residence;

@RepositoryRestResource(path = "resi")
public interface ResidenceRepository extends JpaRepository<Residence, Long>{

}
