package nieto.genm.colaborador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import nieto.genm.colaborador.model.AcSolicitudesVacaciones;

public interface AcSolicitudesVacacionesRep extends JpaRepository<AcSolicitudesVacaciones, Integer>{
	
	List<AcSolicitudesVacaciones> findByIdUsuario(Integer idUsuario);
}
