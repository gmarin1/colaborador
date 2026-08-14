package nieto.genm.colaborador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nieto.genm.colaborador.model.AcColaboradores;

public interface AcColaboradoresRep extends JpaRepository<AcColaboradores, Integer>{
	
	AcColaboradores findByIdUsuario(Integer idUsuario);
}
