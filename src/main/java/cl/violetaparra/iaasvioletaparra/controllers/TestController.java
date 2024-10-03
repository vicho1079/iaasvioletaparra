package cl.violetaparra.iaasvioletaparra.controllers;

import cl.violetaparra.iaasvioletaparra.dto.EncuestaDto;
import cl.violetaparra.iaasvioletaparra.service.OportunidadLavadoManosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static cl.violetaparra.iaasvioletaparra.utils.EncuestaUtils.*;

@RestController()
@RequestMapping("/api")
public class TestController {

    private final OportunidadLavadoManosService service;

    // Constructor Injection
    public TestController(OportunidadLavadoManosService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot API is working!";
    }
    @PostMapping("/oplavadomanos")
    public ResponseEntity<?> postOportunidadLavadoManos(@RequestBody EncuestaDto encuesta){
        if(encuesta.getTipoEncuesta() != OPORTUNIDAD_LAVADO_MANOS){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        service.guardarEncuesta(encuesta);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/oplavadomanos/{id}")
    public ResponseEntity<EncuestaDto> getOportunadadLavadoManos(@PathVariable Long id){
        Optional<EncuestaDto> encuesta = service.buscarEncuesta(id);
        return encuesta
                .map(encuestaDto -> new ResponseEntity<>(encuestaDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
