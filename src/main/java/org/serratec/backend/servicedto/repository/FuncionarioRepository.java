package org.serratec.backend.servicedto.repository;

import org.serratec.backend.servicedto.model.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	
	@Query("SELECT f FROM Funcionario f WHERE f.salario >= :valorMinimo AND f.salario <= :valorMaximo")
	Page<Funcionario> buscarSalario(Double valorMinimo, Double valorMaximo, Pageable pageable);
	
	Page<Funcionario> findBySalarioGreaterThanEqualAndSalarioLessThanEqual(Double valorMinimo, Double valorMaximo, Pageable pageable);
	
	@Query(value = "SELECT * FROM funcionario f WHERE f.salario >= :valorMinimo AND f.salario <= :valorMaximo", nativeQuery=true)
	Page<Funcionario> buscarSalarioNativo(Double valorMinimo, Double valorMaximo, Pageable pageable);

}
