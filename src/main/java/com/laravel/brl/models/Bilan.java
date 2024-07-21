package com.laravel.brl.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bilan {
	
	private Residence residence;
	private int year;
    private String month;
    private Double recette;
    private Double depenses;

}
