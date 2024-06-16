package com.laravel.brl.dto;

import com.laravel.brl.models.TypeResidence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResidenceDTO {
	
	private Long IdResidence;
	private String nameResidence;
	private String localisationResidence;
	private float prixResidence;
	private TypeResidence typeResidence;
	private String etatResidence;

}
