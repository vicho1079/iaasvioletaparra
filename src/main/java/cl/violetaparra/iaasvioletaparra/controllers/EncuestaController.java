package cl.violetaparra.iaasvioletaparra.controllers;

import cl.violetaparra.iaasvioletaparra.dto.EncuestaDto;
import cl.violetaparra.iaasvioletaparra.service.EncuestasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static cl.violetaparra.iaasvioletaparra.utils.EncuestaUtils.*;

@RestController()
@RequestMapping("/api")
public class EncuestaController {

    private final EncuestasService service;

    public EncuestaController(EncuestasService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot API is working!";
    }
    @PostMapping("/encuesta")
    public ResponseEntity<?> postEncuesta(@RequestBody EncuestaDto encuesta){
        if(!existeEncuesta(encuesta.getTipoEncuesta())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        service.guardarEncuesta(encuesta);
        Map<String, String> response = new HashMap<>();
        response.put("Mensaje", "Encuesta guardada correctamente");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/encuesta/{id}")
    public ResponseEntity<EncuestaDto> getOportunadadLavadoManos(@PathVariable Long id){
        Optional<EncuestaDto> encuesta = service.buscarEncuesta(id);
        return encuesta
                .map(encuestaDto -> new ResponseEntity<>(encuestaDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
