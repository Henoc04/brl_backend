package com.laravel.brl.utils;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.web.multipart.MultipartFile;

import com.laravel.brl.dto.ReservationDTO;
import com.laravel.brl.dto.ResidenceDTO;

public class Utils {
	
	private final Path root = Paths.get("uploads");

	public ReservationDTO calculateTotal(ReservationDTO r) {
		
			Logger logger = Logger.getLogger(getClass().getName());
			
			float prix = r.getResidence().getPrixResidence();
			
			if (r.getDateEntrer().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isBefore(r.getDateSortie().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
			long durer = ChronoUnit.DAYS.between(r.getDateEntrer().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() , r.getDateSortie().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			r.setTotal(prix * durer); 
			} else {
				logger.info("la date d'entrer est superieur a la date de sortie");
			}
			
			
			return r;
		}
	
	public ResidenceDTO storageImg(ResidenceDTO residence, MultipartFile image) {
		
		try {
			 
			 if (!Files.exists(root)) {
	                Files.createDirectory(root);
	            }
			 
	            String filename = UUID.randomUUID().toString() + image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf("."));
	            Files.copy(image.getInputStream(), root.resolve(filename));
	           
	            residence.setImageUrl("/uploads/" + filename);
	            if (residence.getEtatResidence() == null) {
	            	residence.setEtatResidence("Disponible");
	            }
	            
	            
	            return residence;
	        } catch (Exception e) {
	            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
	        }
	}
		
}
