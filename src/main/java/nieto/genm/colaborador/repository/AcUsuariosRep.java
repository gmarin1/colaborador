package nieto.genm.colaborador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nieto.genm.colaborador.model.AcUsuarios;

public interface AcUsuariosRep extends JpaRepository<AcUsuarios, Integer>{
	
	AcUsuarios findBySsoId (String usuario);

}
