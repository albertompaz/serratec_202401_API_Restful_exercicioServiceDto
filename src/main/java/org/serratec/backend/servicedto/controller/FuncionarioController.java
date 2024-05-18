package org.serratec.backend.servicedto.controller;

import java.util.List;

import org.serratec.backend.servicedto.dto.FuncionarioSalarioDTO;
import org.serratec.backend.servicedto.model.Funcionario;
import org.serratec.backend.servicedto.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@GetMapping
	public ResponseEntity<List<Funcionario>> listar() {
		List<Funcionario> funcionarios = funcionarioRepository.findAll();
		return ResponseEntity.ok(funcionarios);
	}
	
	@GetMapping("/pagina")
	public ResponseEntity<Page<Funcionario>> listarPaginado(
			@PageableDefault(sort="nome", direction=Sort.Direction.ASC,
			page=0, size=8) Pageable pegeable) {
		Page<Funcionario> funcionarios = funcionarioRepository.findAll(pegeable);
		return ResponseEntity.ok(funcionarios);
	}
	
	@GetMapping("/salario")
	public ResponseEntity<Page<Funcionario>> listarSalarios(@RequestParam(defaultValue = "0") Double valorMinimo,
			@RequestParam(defaultValue = "20000") Double valoMaximo, Pageable pageable) {
		Page<Funcionario> funcionarios = funcionarioRepository.buscarSalario(valorMinimo, valoMaximo, pageable);
		//Page<Funcionario> funcionarios = funcionarioRepository.findBySalarioGreaterThanEqualAndSalarioLessThanEqual(valorMinimo, valoMaximo, pageable);
		//Page<Funcionario> funcionarios = funcionarioRepository.buscarSalarioNativo(valorMinimo, valoMaximo, pageable);
		return ResponseEntity.ok(funcionarios);
	}
	
	@GetMapping("/nome")
	public ResponseEntity<Page<Funcionario>> buscarPorNome(
			@RequestParam(defaultValue = "") String paramNome, Pageable pageable) {
		Page<Funcionario> funcionarios = funcionarioRepository.buscarPorNome(paramNome, pageable);
		return ResponseEntity.ok(funcionarios);
	}
	
	@GetMapping("/salarios-por-idade")
	public ResponseEntity<List<FuncionarioSalarioDTO>> buscarSalariosPorIdade() {
		List<FuncionarioSalarioDTO> funcionariosSalarioDTO = funcionarioRepository.buscarSalarioPorIdade();
		return ResponseEntity.ok(funcionariosSalarioDTO);
	}

}
