package com.laravel.brl.dto;

import com.laravel.brl.models.Residence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BilanDTO {
	

	private Residence residence;
	private int year;
    private String month;
    private Double recette;
    private Double depenses;

}
