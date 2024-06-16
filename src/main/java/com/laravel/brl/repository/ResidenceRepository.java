package com.laravel.brl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laravel.brl.models.Residence;

@Repository
public interface ResidenceRepository extends JpaRepository<Residence, Long>{

}
