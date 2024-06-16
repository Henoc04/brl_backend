package com.laravel.brl.utils;

import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import com.laravel.brl.dto.ReservationDTO;

public class Utils {

	public ReservationDTO calculateTotal(ReservationDTO r) {
			
			float prix = r.getResidence().getPrixResidence();
			
			if (r.getDateEntrer().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isBefore(r.getDateSortie().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
			long durer = ChronoUnit.DAYS.between(r.getDateEntrer().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() , r.getDateSortie().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			r.setTotal(prix * durer); 
			} else {
				System.out.println("la date d'entrer est superieur a la date de sortie");
			}
			
			return r;
		}
	/*
	public Reservation etatReservation(Reservation r) {
		
		if (LocalDate.now().isBefore(r.getDateEntrer().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
			r.setEtatReservation("Réserver");
		}else if ((r.getDateEntrer().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isBefore(LocalDate.now())) && (r.getDateEntrer().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isBefore(r.getDateSortie().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) ) {
			r.setEtatReservation("En cours");
		}else {
			r.setEtatReservation("Terminer");
		}
		return r;
	}
	*/
}
