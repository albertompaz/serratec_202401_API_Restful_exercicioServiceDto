package org.serratec.backend.servicedto.service;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.backend.servicedto.dto.FuncionarioDTO;
import org.serratec.backend.servicedto.model.Funcionario;
import org.serratec.backend.servicedto.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private FotoService fotoService;
	
	public List<FuncionarioDTO> listar() {
		List<Funcionario> funcionarios = funcionarioRepository.findAll();
		
		List<FuncionarioDTO> funcionariosDTO = new ArrayList<>();
		
		funcionarios.forEach(f -> {
			funcionariosDTO.add(dicionarImagemUrl(f));
		});
		
		return funcionariosDTO;
	}
	
	public FuncionarioDTO buscar(Long id) {
		Optional<Funcionario> funcionarioOpt = funcionarioRepository.findById(id);
		return dicionarImagemUrl(funcionarioOpt.get());
	}
	
	public FuncionarioDTO inserir(Funcionario funcionario, MultipartFile file) throws IOException {
		funcionario = funcionarioRepository.save(funcionario);
		fotoService.inserir(funcionario, file);
		return dicionarImagemUrl(funcionario);
	}
	
	
	public FuncionarioDTO dicionarImagemUrl(Funcionario funcionario) {
		URI uri = ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.path("/funcionarios/{id}/foto")
				.buildAndExpand(funcionario.getId())
				.toUri();
		
		FuncionarioDTO dto = new FuncionarioDTO();
		dto.setNome(funcionario.getNome());
		dto.setDataNascimento(funcionario.getDataNascimento());
		dto.setSalario(funcionario.getSalario());
		dto.setUrl(uri.toString());
		
		return dto;
	}
	
}
