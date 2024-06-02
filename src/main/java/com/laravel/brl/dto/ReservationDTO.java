package com.laravel.brl.dto;

import java.util.Date;
import com.laravel.brl.models.Client;
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
	private Date dateReservation;
	private Client client;
}
