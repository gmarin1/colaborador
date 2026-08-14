package nieto.genm.colaborador.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nieto.genm.colaborador.model.AcColaboradores;
import nieto.genm.colaborador.model.AcPeriodosVacaciones;
import nieto.genm.colaborador.model.AcSolicitudesVacaciones;
import nieto.genm.colaborador.repository.AcColaboradoresRep;
import nieto.genm.colaborador.repository.AcPeriodosVacacionesRep;
import nieto.genm.colaborador.repository.AcSolicitudesVacacionesRep;

@Service
public class PortalService {
	
	@Autowired
	private AcColaboradoresRep colaboradoresRep;
	
	@Autowired
	private AcPeriodosVacacionesRep periodosVacaciones;
	
	@Autowired
	private AcSolicitudesVacacionesRep solicitudesVacaciones;
	
	public AcColaboradores obtenermodeloColaborador(Integer idUsuario) {
		return colaboradoresRep.findByIdUsuario(idUsuario);
	}
	
	public List<AcPeriodosVacaciones> obtenermodeloPeriodoVac (Integer idUsuario) {
		return periodosVacaciones.findByIdUsuario(idUsuario);
	}
	
	public List<AcSolicitudesVacaciones> obtenermodeloAcSolicitudes (Integer idUsuario) {
		return solicitudesVacaciones.findByIdUsuario(idUsuario);
	}
}
