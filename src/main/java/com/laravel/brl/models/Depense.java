package com.laravel.brl.models;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Depense {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDepense;
	private String libeleDepense;
	private float montantDepense;
	private String descriptionDepense;
	private Date dateDepense;
	
	@ManyToOne
	private Residence residence;

}
