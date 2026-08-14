package nieto.genm.colaborador.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "nieto.genm.colaborador")
@EnableJpaRepositories(basePackages = "nieto.genm.colaborador.repository")
@EnableWebSecurity
public class AppConfig {
	
    
	// 1. Configuración del DataSource (Conexión a PostgreSQL)
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://172.16.166.8:5432/aden?currentSchema=sipacweb_desarrollo");
        dataSource.setUsername("erick");
        dataSource.setPassword("3r1ck");
        return dataSource;
    }

    // 2. "entityManagerFactory"
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("nieto.genm.colaborador.model"); // Paquete de tus entidades (@Entity)

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        em.setJpaProperties(properties);

        return em;
    }

    // 3. Gestor de Transacciones para Spring Data JPA
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
    
    public static class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
        @Override
        protected Class<?>[] getRootConfigClasses() {
            return null;
        }
        @Override
        protected Class<?>[] getServletConfigClasses() {
            return new Class<?>[] { AppConfig.class };
        }
        @Override
        protected String[] getServletMappings() {
            return new String[] { "/" }; // Intercepta todas las peticiones HTTP
        }
    }
    
    // configuraciones de spring security
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Automáticamente valida el hash BCrypt ($2a$10$...)
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
                            new AntPathRequestMatcher("/js/**")
                    ).permitAll()
            		.anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")               // La URL de tu vista HTML del formulario
                .loginProcessingUrl("/process-login") // La URL que procesará el POST del formulario
                .usernameParameter("ssoId")        // El nombre en tu HTML para el usuario
                .passwordParameter("password")     // El nombre en tu HTML para la contraseña
                .defaultSuccessUrl("/portal", true)  // A dónde redirigir si el login es exitoso
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
