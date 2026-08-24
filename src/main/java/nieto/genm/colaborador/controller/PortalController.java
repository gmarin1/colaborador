package nieto.genm.colaborador.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nieto.genm.colaborador.config.CustomUserDetails;
import nieto.genm.colaborador.model.AcUsuarios;
import nieto.genm.colaborador.service.PortalService;

@RestController
@RequestMapping("/api")
public class PortalController {
	
	@Autowired
	private PortalService portalService;
	
	@GetMapping("/datosUsuario")
    public ResponseEntity<Map<String, Object>> obtenerDatosUsuario(@AuthenticationPrincipal CustomUserDetails userDetails) {
        if (userDetails == null || userDetails.getUsuario() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        AcUsuarios usuario = userDetails.getUsuario();

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("nombre", usuario.getFirstName());

        return ResponseEntity.ok(respuesta);
    }
	
	@GetMapping("/datosColaborador")
    public ResponseEntity<Map<String, Object>> obtenerDatosIniciales(@AuthenticationPrincipal CustomUserDetails userDetails) {
        if (userDetails == null || userDetails.getUsuario() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        AcUsuarios usuario = userDetails.getUsuario();
        Integer idUsuario = userDetails.getUsuario().getId();

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("colaborador", portalService.obtenermodeloColaborador(idUsuario));
        respuesta.put("usuario", usuario);

        return ResponseEntity.ok(respuesta);
    }
	
	@GetMapping("/vacDisp")
	public ResponseEntity<Map<String, Object>> obtenerPeridodosVac(@AuthenticationPrincipal CustomUserDetails userDetails) {
		if (userDetails == null || userDetails.getUsuario() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
		
		AcUsuarios usuario = userDetails.getUsuario();
        Integer idUsuario = usuario.getId();
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("periodos", portalService.obtenermodeloPeriodoVac(idUsuario));
        respuesta.put("diasDisponibles", portalService.diasDisponiblesTotales(idUsuario));
        respuesta.put("fechaHoy", LocalDate.now().toString());
        respuesta.put("esquema", portalService.obtenermodeloColaborador(idUsuario).getDiasLaborales());
        
        return ResponseEntity.ok(respuesta);
	}
	
	@GetMapping("/solicitudesVac")
	public ResponseEntity<Map<String, Object>> obtenerSolicitudesVac(@AuthenticationPrincipal CustomUserDetails userDetails) {
		if (userDetails == null || userDetails.getUsuario() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
		
		AcUsuarios usuario = userDetails.getUsuario();
        Integer idUsuario = usuario.getId();
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("solicitudes", portalService.obtenermodeloAcSolicitudes(idUsuario));
        
        return ResponseEntity.ok(respuesta);
	}
	
    @PostMapping("/solicitar")
    public ResponseEntity<Map<String, Object>> generarSolicitud(@AuthenticationPrincipal CustomUserDetails userDetails,
													            @RequestParam(value = "fechaInicio", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
													            @RequestParam(value = "fechaFin", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
													            @RequestParam("motivo") String motivo,
													            @RequestParam(value = "ultimoDiaTrabajado", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ultimoDiaTrabajado) {

        Map<String, Object> respuesta = new HashMap<>();

        if (userDetails == null || userDetails.getUsuario() == null) {
        	respuesta.put("exito", false);
        	respuesta.put("mensaje", "Sesión caducada");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(respuesta);
        }

        Integer idUsuario = userDetails.getUsuario().getId();
        String resultado = portalService.crearSolicitud(idUsuario, fechaInicio, fechaFin, motivo, ultimoDiaTrabajado);

        if ("OK".equalsIgnoreCase(resultado)) {
        	respuesta.put("exito", true);
        	respuesta.put("mensaje", "Solicitud de vacaciones creada correctamente.");
            return ResponseEntity.ok(respuesta);
        } else {
        	respuesta.put("exito", false);
        	respuesta.put("mensaje", resultado);
            return ResponseEntity.badRequest().body(respuesta);
        }
    }

}
