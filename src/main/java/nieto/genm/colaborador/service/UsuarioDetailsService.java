package nieto.genm.colaborador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import nieto.genm.colaborador.config.CustomUserDetails;
import nieto.genm.colaborador.model.AcUsuarios;
import nieto.genm.colaborador.repository.AcUsuariosRep;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private AcUsuariosRep usuariosRep;

    @Override
    public UserDetails loadUserByUsername(String ssoId) throws UsernameNotFoundException {
        AcUsuarios usuario = usuariosRep.findBySsoId(ssoId)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con ssoId: " + ssoId));

        return new CustomUserDetails(usuario);
    }
}