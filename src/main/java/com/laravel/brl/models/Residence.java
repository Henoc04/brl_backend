package com.laravel.brl.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Residence {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idResidence;
	private String nameResidence;
	private String localisationResidence;
	private float prixResidence;
	private String etatResidence;
	@Lob
	private String imageUrl;
	
	@OneToMany(mappedBy = "residence")
	@JsonIgnore
	private List<Reservation> reservations;
	
	@ManyToOne
	private TypeResidence typeResidence;
	
	@OneToMany(mappedBy = "residence")
	@JsonIgnore
	private List<Depense> depense;

}
