package com.laravel.brl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDTO {
	
	private Long idClient;
	private String nameClient;
	private String pieceClient;
	private int contactClient;
	private String adresseClient;
}
