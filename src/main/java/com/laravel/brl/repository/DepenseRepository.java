package com.laravel.brl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laravel.brl.models.Depense;

@Repository
public interface DepenseRepository extends JpaRepository<Depense, Long>{

}
