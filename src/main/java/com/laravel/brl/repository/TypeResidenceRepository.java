package com.laravel.brl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.laravel.brl.models.TypeResidence;

@RepositoryRestResource(path = "typeresei")
public interface TypeResidenceRepository extends JpaRepository<TypeResidence, Long>{

}
