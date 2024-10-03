package cl.violetaparra.iaasvioletaparra.service;

import cl.violetaparra.iaasvioletaparra.dto.EncuestaDto;
import cl.violetaparra.iaasvioletaparra.entity.OportunidadLavadoManos;
import cl.violetaparra.iaasvioletaparra.repository.OportunidadLavadoManosRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OportunidadLavadoManosService {

    private final OportunidadLavadoManosRepository repository;

    // Constructor Injection
    public OportunidadLavadoManosService(OportunidadLavadoManosRepository repository) {
        this.repository = repository;
    }

    public void guardarEncuesta(EncuestaDto encuesta){
        OportunidadLavadoManos OLMEncuesta = new OportunidadLavadoManos(encuesta);
        repository.save(OLMEncuesta);
    }

    public Optional<EncuestaDto> buscarEncuesta(Long id){
        return repository.findById(id)
                .map(EncuestaDto::new);
    }
}
