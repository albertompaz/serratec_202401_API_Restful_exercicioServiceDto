package org.serratec.backend.servicedto.repository;

import org.serratec.backend.servicedto.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	public Endereco findByCep(String cep);
	
}
