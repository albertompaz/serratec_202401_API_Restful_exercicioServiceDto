package org.serratec.backend.servicedto.service;

import java.util.Optional;

import org.serratec.backend.servicedto.model.Perfil;
import org.serratec.backend.servicedto.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PerfilService {

	@Autowired
	private PerfilRepository perfilRepository;
	
	public Perfil buscar(Long id) {
		Optional<Perfil> perfilOpt = perfilRepository.findById(id);
		return perfilOpt.get();
	}
	
	@Transactional
	public void testarTransação() {
		Perfil perfil = new Perfil();
		perfil.setId(100L);
		perfil.setNome("TESTE_1");
		
		Perfil perfil2 = new Perfil();
		perfil.setId(101L);
		perfil.setNome("TESTE_2");
		
		Perfil perfil3 = new Perfil();
		perfil.setId(102L);
		perfil.setNome("TESTE_3");
		
		perfil = perfilRepository.save(perfil);
		perfil2 = perfilRepository.save(perfil2);
		perfilRepository.deleteById(perfil.getId());
		perfil3 = perfilRepository.save(perfil3);
		
		String teste = null;
		System.out.println(teste.toLowerCase());
		perfilRepository.deleteById(perfil3.getId());
		
		
	}
	
}
