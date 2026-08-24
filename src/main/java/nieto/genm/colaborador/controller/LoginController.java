package nieto.genm.colaborador.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import nieto.genm.colaborador.config.CustomUserDetails;

@Controller
public class LoginController {
	
	@GetMapping("/login")
    public String loginview(@RequestParam(value = "error", required = false) String error, Authentication authentication, Model model) {
		
		if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken))
	        return "redirect:/";
		
		if(error != null)
			model.addAttribute("error","Usuario o contraseña incorrectos.");
		
		return "login";
    }
	
	@GetMapping("/")
    public String portalView(@AuthenticationPrincipal CustomUserDetails userDetails) {
        if (userDetails == null || userDetails.getUsuario() == null) {
            return "redirect:/login";
        }
        return "portal";
    }
}
