package nieto.genm.colaborador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		
		if (session.getAttribute("usuario") == null) {
            return "redirect:/login"; 
        }
		
		Integer idUsuario = usuario.getId();
		
		model.addAttribute("colaborador", portalService.obtenermodeloColaborador(idUsuario));
		model.addAttribute("periodos", portalService.obtenermodeloPeriodoVac(idUsuario));
		model.addAttribute("solicitudes", portalService.obtenermodeloAcSolicitudes(idUsuario));
		
        return "portal";
    }
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:/login";
	}

}
