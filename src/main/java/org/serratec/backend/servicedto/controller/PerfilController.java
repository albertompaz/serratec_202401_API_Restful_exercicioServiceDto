package org.serratec.backend.servicedto.controller;

import org.serratec.backend.servicedto.repository.PerfilRepository;
import org.serratec.backend.servicedto.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/perfis")
public class PerfilController {
	
	@Autowired
	PerfilService serfilService;

	@GetMapping("/testarTransacao")
	public ResponseEntity<Void> testarTransacao() {
		serfilService.testarTransação();
		return ResponseEntity.ok(null);
	}
	
}
