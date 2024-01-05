package com.groupeisi.backendadmin;

import com.groupeisi.backendadmin.services.ProduitService;
import com.groupeisi.backendadmin.services.UserService;
import com.groupeisi.generated.model.ProduitDTO;
import com.groupeisi.generated.model.UserDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendAdminApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ProduitService produitService, UserService userService){
		return args -> {
			UserDTO userDTO = new UserDTO();
			userDTO.setNom("Diallo");
			userDTO.setPrenom("Boubacar Siddy");
			userDTO.setEmail("test@gmail.com");
			userDTO.setEtat(1);
			userDTO.setPassword("passer123");
			userService.save(userDTO);

			// produits
			ProduitDTO produitDTO = new ProduitDTO();
			produitDTO.setName("Ordinateur");
			produitDTO.setUser(userDTO);
			produitService.save(produitDTO);
		};
	}

}
