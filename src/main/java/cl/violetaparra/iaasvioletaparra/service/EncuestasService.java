package cl.violetaparra.iaasvioletaparra.service;

import cl.violetaparra.iaasvioletaparra.dto.EncuestaDto;
import cl.violetaparra.iaasvioletaparra.entity.CuracionesComplejas;
import cl.violetaparra.iaasvioletaparra.entity.OportunidadLavadoManos;
import cl.violetaparra.iaasvioletaparra.repository.CuracionesComplejasRepository;
import cl.violetaparra.iaasvioletaparra.repository.OportunidadLavadoManosRepository;
import cl.violetaparra.iaasvioletaparra.utils.EncuestaUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EncuestasService {

    private final OportunidadLavadoManosRepository oportunidadLavadoManosRepository;
    private final CuracionesComplejasRepository curacionesComplejasRepository;

    public EncuestasService(OportunidadLavadoManosRepository oportunidadLavadoManosRepository,
                            CuracionesComplejasRepository curacionesComplejasRepository) {
        this.oportunidadLavadoManosRepository = oportunidadLavadoManosRepository;
        this.curacionesComplejasRepository = curacionesComplejasRepository;
    }

    public void guardarEncuesta(EncuestaDto encuesta){
        switch (encuesta.getTipoEncuesta()){
            case EncuestaUtils.OPORTUNIDAD_LAVADO_MANOS: {
                OportunidadLavadoManos OLMEncuesta = new OportunidadLavadoManos(encuesta);
                oportunidadLavadoManosRepository.save(OLMEncuesta);
            }
            break;
            case EncuestaUtils.CURACIONES_COMPLEJAS: {
                CuracionesComplejas CCEncuesta = new CuracionesComplejas(encuesta);
                curacionesComplejasRepository.save(CCEncuesta);
            }
            break;
            default: throw new IllegalArgumentException("El tipo de encuesta que intenta crear no existe");
        }

    }

    public Optional<EncuestaDto> buscarEncuesta(Long id){
        return oportunidadLavadoManosRepository.findById(id)
                .map(EncuestaDto::new);
    }
}
