package nieto.genm.colaborador.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		return periodosVacaciones.findByIdUsuarioOrderByAnioPeriodoDesc(idUsuario);
	}
	
	public Integer diasDisponiblesTotales(Integer idUsuario) {
		
		List<AcPeriodosVacaciones> periodo = periodosVacaciones.findTop2ByIdUsuarioOrderByAnioPeriodoDesc(idUsuario);
		List<AcSolicitudesVacaciones> solicitud = obtenermodeloAcSolicitudes (idUsuario);
		Integer diasOtorgados=0;
		Integer diasTomados=0;
		Integer diasSolicitados=0;
		
		for (AcPeriodosVacaciones p : periodo) {
		    if (p.getDiasOtorgados() != null && !p.getFechaCaducidad().isBefore(LocalDate.now())) {
		        diasOtorgados += p.getDiasOtorgados();
		    }
		    if (p.getDiasTomados() != null && !p.getFechaCaducidad().isBefore(LocalDate.now())) {
		        diasTomados += p.getDiasTomados();
		    }
		}
		
		for (AcSolicitudesVacaciones s : solicitud) {
			if(!s.getEstado().equals("RECHAZADA"))
				diasSolicitados += s.getDiasSolicitados();
		}
		
		return diasOtorgados - diasTomados - diasSolicitados;
	}
	
	public List<AcSolicitudesVacaciones> obtenermodeloAcSolicitudes (Integer idUsuario) {
		return solicitudesVacaciones.findByIdUsuarioOrderByCreatedatetimeDesc(idUsuario);
	}
	
	private List<LocalDate> diasVacacionesAlternos(AcSolicitudesVacaciones solicitud){
		
		List<LocalDate> vacAlter = new ArrayList<>();
		LocalDate fecha = solicitud.getFechaInicio();
		
		while(!fecha.isAfter(solicitud.getFechaFin())) {
            if(Math.abs(ChronoUnit.DAYS.between(fecha, solicitud.getFechaFin())) % 2 == 0)
            	vacAlter.add(fecha);
            fecha = fecha.plusDays(1);
		}
		return vacAlter;
	}
	
	public String crearSolicitud (Integer idUsuario, LocalDate fechaInicio, LocalDate fechaFin, String motivo, LocalDate ultimoDiaTrabajado) {
		
		AcColaboradores colaborador = colaboradoresRep.findByIdUsuario(idUsuario);
		
		if (fechaInicio == null && fechaFin == null)
	        return "No se selecciono ninguna fecha";
		
		if (fechaInicio != null && fechaFin == null)
	        fechaFin = fechaInicio;
		
		if (fechaFin.isBefore(fechaInicio))
	        return "Rango de fecha invalido";
		
		if(ChronoUnit.DAYS.between(LocalDate.now(), fechaInicio)<5)
			return "Debes solicitar las vacaciones con 5 dias de anticipacion";
		
		if ("ALTERNO".equals(colaborador.getDiasLaborales()) && ultimoDiaTrabajado == null)
	        return "No ingreso su ultimo dia trabajado";
		
		AcSolicitudesVacaciones solicitud = new AcSolicitudesVacaciones();
		solicitud.setFechaInicio(fechaInicio);
	    solicitud.setFechaFin(fechaFin);
	    
	    fechasSolicitudVacaciones(solicitud,colaborador,ultimoDiaTrabajado);
		
		if (solicitud.getFechaInicio().isAfter(solicitud.getFechaFin())) {
	        return "El rango seleccionado no contiene días laborables";
	    }
		
		Integer diasSolicitados = calcularDiasLaborables(fechaInicio, fechaFin, colaborador.getDiasLaborales(), ultimoDiaTrabajado);
		Integer diasDisponiblesTotales = diasDisponiblesTotales(idUsuario);
		
		if (diasSolicitados <= 0) {
	        return "El rango seleccionado no contiene días laborables";
	    }
		
		if(diasSolicitados>diasDisponiblesTotales)
			return "No tienes suficientes dias disponibles";
		
		List<AcSolicitudesVacaciones> solicitudes = obtenermodeloAcSolicitudes (idUsuario);
		
		for(AcSolicitudesVacaciones s : solicitudes) {
			if(!s.getEstado().equals("RECHAZADA") && (!s.getFechaInicio().isAfter(solicitud.getFechaFin()) && !s.getFechaFin().isBefore(solicitud.getFechaInicio())))
				return "Ya tienes una solicitud de vacaciones dentro de ese rango de fechas";
		}
		
		fechasSolicitudVacaciones(solicitud,colaborador,ultimoDiaTrabajado);
		
		solicitud.setCreatedatetime(LocalDateTime.now());
		solicitud.setMotivo(motivo);
		solicitud.setIdUsuario(idUsuario);
		solicitud.setDiasSolicitados(diasSolicitados);
			
		solicitudesVacaciones.save(solicitud);
		return "ok";
	}
	
	private void fechasSolicitudVacaciones (AcSolicitudesVacaciones solicitud, AcColaboradores colaborador,LocalDate ultimoDiaTrabajado){
		
		switch (colaborador.getDiasLaborales() != null ? colaborador.getDiasLaborales() : "") {
		
        case "LUNES_VIERNES":
        	
        	while(solicitud.getFechaInicio().getDayOfWeek() == DayOfWeek.SATURDAY || solicitud.getFechaInicio().getDayOfWeek() == DayOfWeek.SUNDAY)
        		solicitud.setFechaInicio(solicitud.getFechaInicio().plusDays(1));
        	
        	while(solicitud.getFechaFin().getDayOfWeek() == DayOfWeek.SATURDAY || solicitud.getFechaFin().getDayOfWeek() == DayOfWeek.SUNDAY)
        		solicitud.setFechaFin(solicitud.getFechaFin().minusDays(1));
        	
        	break;
	   
        case "LUNES_SABADO":
        	
        	while(solicitud.getFechaInicio().getDayOfWeek() == DayOfWeek.SUNDAY)
        		solicitud.setFechaInicio(solicitud.getFechaInicio().plusDays(1));
        	
        	while(solicitud.getFechaFin().getDayOfWeek() == DayOfWeek.SUNDAY)
        		solicitud.setFechaFin(solicitud.getFechaFin().minusDays(1));
        	
        	break;

        case "LUNES_DOMINGO":
        	
        	break;

        case "ALTERNO":
        	
        	long diasDiferenciai = ChronoUnit.DAYS.between(ultimoDiaTrabajado, solicitud.getFechaInicio());
            if(Math.abs(diasDiferenciai) % 2 != 0)
            	solicitud.setFechaInicio(solicitud.getFechaInicio().plusDays(1));
            
            long diasDiferenciaf = ChronoUnit.DAYS.between(ultimoDiaTrabajado, solicitud.getFechaFin());
            if(Math.abs(diasDiferenciaf) % 2 != 0)
            	solicitud.setFechaFin(solicitud.getFechaFin().minusDays(1));
            
            break;
		}
		
	}
	
	private Integer calcularDiasLaborables(LocalDate fechaInicio, LocalDate fechaFin, String esquema, LocalDate ultimoDiaTrabajado) {
	    Integer dias = 0;
	    LocalDate fechaActual = fechaInicio;

	    while (!fechaActual.isAfter(fechaFin)) {
	        if (!esFestivoOficialMexico(fechaActual) && esDiaLaboralSegunEsquema(fechaActual, ultimoDiaTrabajado, esquema))
	            dias++;
	        fechaActual = fechaActual.plusDays(1);
	    }
	    return dias;
	}
	
	private boolean esDiaLaboralSegunEsquema(LocalDate fechaActual, LocalDate ultimoDiaTrabajado, String esquema) {
	    DayOfWeek diaSemana = fechaActual.getDayOfWeek();

	    switch (esquema != null ? esquema : "") {
	        case "LUNES_VIERNES":
	            return diaSemana != DayOfWeek.SATURDAY && diaSemana != DayOfWeek.SUNDAY;

	        case "LUNES_SABADO":
	            return diaSemana != DayOfWeek.SUNDAY;

	        case "LUNES_DOMINGO":
	            return true;

	        case "ALTERNO":
	        	if (ultimoDiaTrabajado == null)
	                return true;

	            long diasDiferencia = ChronoUnit.DAYS.between(ultimoDiaTrabajado, fechaActual);
	            return Math.abs(diasDiferencia) % 2 == 0;

	        default:
	            return true;
	    }
	}

	private boolean esFestivoOficialMexico(LocalDate fecha) {
	    int anio = fecha.getYear();
	    Set<LocalDate> festivos = new HashSet<>();

	    // Fechas fijas (LFT México)
	    festivos.add(LocalDate.of(anio, 1, 1));   // Año Nuevo
	    festivos.add(LocalDate.of(anio, 5, 1));   // Día del Trabajo
	    festivos.add(LocalDate.of(anio, 9, 16));  // Independencia
	    festivos.add(LocalDate.of(anio, 12, 25)); // Navidad

	    // Fechas movibles (LFT México)
	    // 1er lunes de febrero (Constitución)
	    festivos.add(LocalDate.of(anio, 2, 1).with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)));
	    
	    // 3er lunes de marzo (Natalicio de Benito Juárez)
	    festivos.add(LocalDate.of(anio, 3, 1).with(TemporalAdjusters.dayOfWeekInMonth(3, DayOfWeek.MONDAY)));
	    
	    // 3er lunes de noviembre (Revolución Mexicana)
	    festivos.add(LocalDate.of(anio, 11, 1).with(TemporalAdjusters.dayOfWeekInMonth(3, DayOfWeek.MONDAY)));

	    // Transmisión del Poder Ejecutivo (1 de octubre cada 6 años: 2024, 2030, etc.)
	    if (anio % 6 == 2024 % 6) {
	        festivos.add(LocalDate.of(anio, 10, 1));
	    }

	    return festivos.contains(fecha);
	}
}
