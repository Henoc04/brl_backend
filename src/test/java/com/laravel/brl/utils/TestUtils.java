package com.laravel.brl.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import com.laravel.brl.dto.ReservationDTO;
import com.laravel.brl.models.Residence;

public class TestUtils {
	
	@Test
	public void TestCalculateTotal() throws ParseException {
		Utils u = new Utils();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date dat1 = dateFormat.parse("02-04-2025");
		Date dat2 = dateFormat.parse("12-04-2025");
				
		ReservationDTO r = new ReservationDTO();
		Residence residence = new Residence();
		residence.setPrixResidence(25000);
		
		r.setDateEntrer(dat1);
		r.setDateSortie(dat2);
		r.setResidence(residence);
		u.calculateTotal(r);
		assertEquals(250000.0, r.getTotal(), 0.0);
		
	}

}
