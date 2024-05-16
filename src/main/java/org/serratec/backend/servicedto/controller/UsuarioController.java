package org.serratec.backend.servicedto.controller;

import java.net.URI;
import java.util.List;

import org.serratec.backend.servicedto.dto.UsuarioDTO;
import org.serratec.backend.servicedto.dto.UsuarioInserirDTO;
import org.serratec.backend.servicedto.model.Usuario;
import org.serratec.backend.servicedto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> listar() {
		return ResponseEntity.ok(usuarioService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(usuarioService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> inserir(@RequestBody UsuarioInserirDTO usuarioInserirDTO) {
		UsuarioDTO usuarioDTO = usuarioService.inserir(usuarioInserirDTO);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(usuarioDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(usuarioDTO);
	}

}
