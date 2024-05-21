package org.serratec.backend.servicedto.service;

import java.io.IOException;
import java.util.Optional;

import org.serratec.backend.servicedto.model.Foto;
import org.serratec.backend.servicedto.model.Funcionario;
import org.serratec.backend.servicedto.repository.FotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FotoService {
	
	@Autowired
	private FotoRepository fotoRepository;
	
	public Foto inserir(Funcionario funcionario, MultipartFile file) throws IOException {
		Foto foto = new Foto();
		foto.setNome(file.getName());
		foto.setTipo(file.getContentType());
		foto.setDados(file.getBytes());
		foto.setFuncionario(funcionario);
		
		foto = fotoRepository.save(foto);
		
		return foto;
	}
	
	@Transactional
	public Foto buscarPorIdFuncionario(Long id) {
		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		
		Optional<Foto> fotoOpt = fotoRepository.findByFuncionario(funcionario);
		
		if (fotoOpt.isEmpty()) {
			return null;
		}
		return fotoOpt.get();
	}

}
