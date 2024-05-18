package org.serratec.backend.servicedto.repository;

import java.util.List;

import org.serratec.backend.servicedto.dto.FuncionarioSalarioDTO;
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
	
	Page<Funcionario> findBySalarioBetween(Double valorMinimo, Double valorMaximo, Pageable pageable);
	
	@Query(value = "SELECT * FROM funcionario f WHERE f.salario >= :valorMinimo AND f.salario <= :valorMaximo", nativeQuery=true)
	Page<Funcionario> buscarSalarioNativo(Double valorMinimo, Double valorMaximo, Pageable pageable);
	
	
	
	
	@Query("SELECT f FROM Funcionario f WHERE UPPER(f.nome) like UPPER(CONCAT('%', :paramNome, '%'))")
	Page<Funcionario> buscarPorNome(String paramNome, Pageable pageable);
	
	Page<Funcionario> findByNomeContainingIgnoreCase(String paramNome, Pageable pageable);
	
	
	
	
	@Query(value = "select date_part('year', age(now(), data_nascimento)) as idade, "
			+ "				avg(salario) as mediaSalario, "
			+ "				min(salario) as menorSalario, "
			+ "				max(salario) as maiorSalario, "
			+ "				count(*) as totalFuncionarios "
			+ "from funcionario "
			+ "group by idade "
			+ "having count(*) > 1 "
			+ "order by idade desc"
			, nativeQuery = true)
	List<FuncionarioSalarioDTO> buscarSalarioPorIdade();
	
}
