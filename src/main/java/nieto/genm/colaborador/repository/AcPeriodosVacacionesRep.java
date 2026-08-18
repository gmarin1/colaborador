package nieto.genm.colaborador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import nieto.genm.colaborador.model.AcPeriodosVacaciones;

public interface AcPeriodosVacacionesRep extends JpaRepository<AcPeriodosVacaciones, Integer>{
	
	List<AcPeriodosVacaciones> findByIdUsuarioOrderByAnioPeriodoDesc(Integer idUsuario);
	List<AcPeriodosVacaciones> findTop2ByIdUsuarioOrderByAnioPeriodoDesc(Integer idUsuario);
	
}
