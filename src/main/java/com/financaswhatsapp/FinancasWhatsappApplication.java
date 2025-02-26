package com.financaswhatsapp;

import com.financaswhatsapp.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.financaswhatsapp.repository.UsuarioRepository;

@SpringBootApplication
public class FinancasWhatsappApplication implements CommandLineRunner {
	private final UsuarioRepository usuarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(FinancasWhatsappApplication.class, args);
	}

	public FinancasWhatsappApplication(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public void run(String...args) {
		System.out.println("Iniciando o cadastro no Banco de Dados");

		System.out.println(usuarioRepository.findAll());
	}
}
