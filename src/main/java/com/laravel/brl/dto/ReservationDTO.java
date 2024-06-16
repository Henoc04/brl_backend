package com.laravel.brl.dto;

import java.util.Date;
import com.laravel.brl.models.Client;
import com.laravel.brl.models.Residence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationDTO {

	private Long idReservation;
	private Date dateEntrer;
	private Date dateSortie;
	private Date dateReservation;
	private float total;
	private String etatReservation;
	private Residence residence;
	private Client client;
}
