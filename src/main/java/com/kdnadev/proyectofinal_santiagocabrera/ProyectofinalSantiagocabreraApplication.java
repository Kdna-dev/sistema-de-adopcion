package com.kdnadev.proyectofinal_santiagocabrera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kdnadev.proyectofinal_santiagocabrera.dto.tipo_mascota.TipoMascotaCreateDTO;
import com.kdnadev.proyectofinal_santiagocabrera.service.MascotaService;

import jakarta.annotation.PostConstruct;

import java.io.File;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
	info = @Info(
		title = "Adopcion de mascotas API",
		version = "1.0",
		description = "API para gestionar la adopcion de mascotas de una veterinaria, y sus usuarios",
		contact = @Contact(
			name = "Kdna.dev | Santiago Cabrera",
			email = "kdna.dev@gmail.com",
			url = "https://github.com/Kdna-dev"
		)
	)
)
@SpringBootApplication
public class ProyectofinalSantiagocabreraApplication {

	private MascotaService mascotaService;

	public ProyectofinalSantiagocabreraApplication(MascotaService mascotaService){
		this.mascotaService = mascotaService;
	}

	@PostConstruct
	public void init() {
		this.initLogDirectory();
		this.initDatosIniciales();
	}

	public void initLogDirectory(){
		// Crear directorio de logs si no existe
		File logDir = new File("logs/archived");
		if (!logDir.exists()) {
			logDir.mkdirs();
		}
	}

	public void initDatosIniciales(){
		if(mascotaService.getAllTipoMascota().isEmpty()){
			mascotaService.createTipoMascota(new TipoMascotaCreateDTO("Perro"));
			mascotaService.createTipoMascota(new TipoMascotaCreateDTO("Gato"));
			mascotaService.createTipoMascota(new TipoMascotaCreateDTO("Loro"));
			mascotaService.createTipoMascota(new TipoMascotaCreateDTO("Hamster"));
			mascotaService.createTipoMascota(new TipoMascotaCreateDTO("Huron"));
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(ProyectofinalSantiagocabreraApplication.class, args);
	}

}
