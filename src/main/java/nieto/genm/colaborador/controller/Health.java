package nieto.genm.colaborador.controller;

import java.sql.Connection;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Health {

    private final DataSource dataSource;

    // Spring inyecta automáticamente el Bean dataSource() de tu AppConfig
    public Health(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> checkHealth() {
        try (Connection connection = dataSource.getConnection()) {
            // Verifica si la conexión con PostgreSQL responde (timeout de 2 segundos)
            boolean isAlive = connection.isValid(2);

            if (isAlive) {
                return ResponseEntity.ok(Map.of(
                        "status", "UP",
                        "database", "PostgreSQL Conectado Correctamente"
                ));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                        "status", "DOWN",
                        "database", "PostgreSQL no responde"
                ));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "DOWN",
                    "error", e.getMessage()
            ));
        }
    }
}