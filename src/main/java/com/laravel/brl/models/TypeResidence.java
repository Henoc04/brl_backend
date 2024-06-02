package com.laravel.brl.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TypeResidence {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTypeResidence;
	private String libeleTypeResidence;
	
	@OneToMany(mappedBy = "typeResidence")
	@JsonIgnore
	private List<Residence> residence;

}
