package nieto.genm.colaborador.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import nieto.genm.colaborador.service.UsuarioDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
    private UsuarioDetailsService usuarioDetailsService;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
            		.requestMatchers(
            				new AntPathRequestMatcher("/login"),
                            new AntPathRequestMatcher("/health"),
                            new AntPathRequestMatcher("/css/**"),
                            new AntPathRequestMatcher("/js/**"),
                            new AntPathRequestMatcher("/images/**"),
                            new AntPathRequestMatcher("/static/**")
                    ).permitAll()
            		.anyRequest().authenticated()
            )
            .userDetailsService(usuarioDetailsService)
            .formLogin(form -> form
                .loginPage("/login")               // La URL de tu vista HTML del formulario
                .loginProcessingUrl("/process-login") // La URL que procesará el POST del formulario
                .usernameParameter("ssoId")        // El nombre en tu HTML para el usuario
                .passwordParameter("password")     // El nombre en tu HTML para la contraseña
                .defaultSuccessUrl("/", true)  // A dónde redirigir si el login es exitoso
                .failureUrl("/login?error=true")   // A dónde redirigir si falla la contraseña/usuario
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .permitAll()
            );

        return http.build();
    }

}
