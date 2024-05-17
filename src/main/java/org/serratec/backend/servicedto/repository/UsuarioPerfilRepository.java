package org.serratec.backend.servicedto.repository;

import org.serratec.backend.servicedto.model.UsuarioPerfil;
import org.serratec.backend.servicedto.model.UsuarioPerfilPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioPerfilRepository extends JpaRepository<UsuarioPerfil, UsuarioPerfilPK> {

}
