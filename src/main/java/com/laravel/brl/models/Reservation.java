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
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idReservation;
	private Date dateEntrer;
	private Date dateSortie;
	private Date dateReservation;
	private float total;
	private String etatReservation;
	
	@ManyToOne
	private Client client;
	
	@ManyToOne
	private Residence residence;
	
	
}
