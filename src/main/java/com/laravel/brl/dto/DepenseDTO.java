package com.laravel.brl.dto;

import java.util.Date;

import com.laravel.brl.models.Residence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepenseDTO {
	
	private Long idDepense;
	private String libeleDepense;
	private float montantDepense;
	private String descriptionDepense;
	private Residence residence;
	private Date dateDepense;

}
