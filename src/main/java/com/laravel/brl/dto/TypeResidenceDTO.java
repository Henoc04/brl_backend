package com.laravel.brl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TypeResidenceDTO {
	
	private Long idTypeResidence;
	private String libeleTypeResidence;

}
