package nieto.genm.colaborador.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import nieto.genm.colaborador.model.AcUsuarios;

public interface AcUsuariosRep extends JpaRepository<AcUsuarios, Integer>{
	
	Optional<AcUsuarios> findBySsoId (String usuario);

}
