package nieto.genm.colaborador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import nieto.genm.colaborador.model.AcUsuarios;
import nieto.genm.colaborador.repository.AcUsuariosRep;

@Service
public class LoginService {
	
	@Autowired
	private AcUsuariosRep usuariosRep;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public String login (String usuario, String password){
		
		AcUsuarios user = usuariosRep.findBySsoId(usuario);
		
		if(usuario == null || usuario.isBlank())
			return "Favor de ingresar el usuario";
		if(password == null || password.isBlank())
			return "Favor de ingresar un password";
		
		if(user == null)
			return "Usuario no encontrado";
		
		if(!passwordEncoder.matches(password, user.getPassword()))
			return "Usuario o contraseña incorrecta";
		
		return "ok";
	}
	
	public AcUsuarios buscarXssoId (String ssoId) {
		return usuariosRep.findBySsoId(ssoId);
	}

}
