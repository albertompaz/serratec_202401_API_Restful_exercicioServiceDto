package org.serratec.backend.servicedto.controller;

import java.io.IOException;
import java.util.List;

import org.serratec.backend.servicedto.dto.FuncionarioDTO;
import org.serratec.backend.servicedto.dto.FuncionarioSalarioDTO;
import org.serratec.backend.servicedto.model.Foto;
import org.serratec.backend.servicedto.model.Funcionario;
import org.serratec.backend.servicedto.repository.FuncionarioRepository;
import org.serratec.backend.servicedto.service.FotoService;
import org.serratec.backend.servicedto.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private FotoService fotoService;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@GetMapping
	public ResponseEntity<List<FuncionarioDTO>> listar() {
		List<FuncionarioDTO> funcionarios = funcionarioService.listar();
		return ResponseEntity.ok(funcionarios);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FuncionarioDTO> buscar(@PathVariable Long id) {
		FuncionarioDTO funcionario = funcionarioService.buscar(id);
		return ResponseEntity.ok(funcionario);
	}
	
	@GetMapping("/{id}/foto")
	public ResponseEntity<byte[]> buscarFoto(@PathVariable Long id) {
		Foto foto = fotoService.buscarPorIdFuncionario(id);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", foto.getTipo());
		headers.add("Content-length", String.valueOf(foto.getDados().length));
		
		return new ResponseEntity<>(foto.getDados(), headers, HttpStatus.OK);
	}
	
	@PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public FuncionarioDTO inserir(@RequestPart MultipartFile file, @RequestPart Funcionario funcionario) 
			throws IOException  {
		return funcionarioService.inserir(funcionario, file);
	}
	
	//////////////
	
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
