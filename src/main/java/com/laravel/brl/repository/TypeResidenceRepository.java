package com.laravel.brl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laravel.brl.models.TypeResidence;

@Repository
public interface TypeResidenceRepository extends JpaRepository<TypeResidence, Long>{

}
