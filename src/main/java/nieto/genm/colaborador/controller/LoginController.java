package nieto.genm.colaborador.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import nieto.genm.colaborador.model.AcUsuarios;
import nieto.genm.colaborador.service.LoginService;
import nieto.genm.colaborador.service.PortalService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private PortalService portalService;
	
	@GetMapping("/login")
    public String loginview(HttpSession session) {
		
		if (session.getAttribute("usuario") != null) {
	        return "redirect:/";
	    }
		
        return "login";
    }
		
	@PostMapping("/login")
	public String login (@RequestParam(name = "ssoId") String ssoId, @RequestParam(name = "password") String password, HttpSession session, Model model) {
		
		String resultado = loginService.login(ssoId, password);
		
		if(resultado.equals("ok")) {
			AcUsuarios usuario = loginService.buscarXssoId(ssoId);
			session.setAttribute("usuario", usuario);
			return "redirect:/";
		}
		
		model.addAttribute("error", resultado);
        return "login";
	}
	
	@GetMapping("/")
    public String portalview(HttpSession session, Model model) {
		
		AcUsuarios usuario = (AcUsuarios) session.getAttribute("usuario");
		
		if (usuario == null) {
            return "redirect:/login"; 
        }
		
		Integer idUsuario = usuario.getId();
		
		model.addAttribute("colaborador", portalService.obtenermodeloColaborador(idUsuario));
		model.addAttribute("periodos", portalService.obtenermodeloPeriodoVac(idUsuario));
		model.addAttribute("solicitudes", portalService.obtenermodeloAcSolicitudes(idUsuario));
		model.addAttribute("diasDisponibles", portalService.diasDisponiblesTotales(idUsuario));
		
        return "portal";
    }
	
	@PostMapping("/")
	public String portalGenerarSolicitud(HttpSession session, RedirectAttributes redirectAttributes,
											@RequestParam(value="fechaInicio", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
											@RequestParam(value="fechaFin", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
											@RequestParam("motivo") String motivo,
											@RequestParam(value = "ultimoDiaTrabajado", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ultimoDiaTrabajado) {
		
		AcUsuarios usuario = (AcUsuarios) session.getAttribute("usuario");
		
		if (usuario == null)
            return "redirect:/login"; 
		
		Integer idUsuario = usuario.getId();
		String resultado = portalService.crearSolicitud(idUsuario, fechaInicio, fechaFin, motivo, ultimoDiaTrabajado);
		
		if ("OK".equalsIgnoreCase(resultado)) {
	        redirectAttributes.addFlashAttribute("mensajeOk", "Solicitud de vacaciones creada, puedes visualizar el seguimiento en el apartado de *Mis solicitudes*.");
	    } else {
	        redirectAttributes.addFlashAttribute("mensajeError", resultado);
	    }
		
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:/login";
	}

}
