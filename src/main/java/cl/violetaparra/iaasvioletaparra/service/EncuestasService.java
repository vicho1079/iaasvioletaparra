package cl.violetaparra.iaasvioletaparra.service;

import cl.violetaparra.iaasvioletaparra.dto.EncuestaDto;
import cl.violetaparra.iaasvioletaparra.entity.*;
import cl.violetaparra.iaasvioletaparra.repository.*;

import static cl.violetaparra.iaasvioletaparra.utils.EncuestaUtils.*;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EncuestasService {

    private final OportunidadLavadoManosRepository oportunidadLavadoManosRepository;
    private final CuracionesComplejasRepository curacionesComplejasRepository;
    private final LimpiezaUnidadesRepository limpiezaUnidadesRepository;
    private final REDMedicamentoRepository redMedicamentoRepository;
    private final LavadoManosRepository lavadoManosRepository;

    public EncuestasService(OportunidadLavadoManosRepository oportunidadLavadoManosRepository,
                            CuracionesComplejasRepository curacionesComplejasRepository,
                            LimpiezaUnidadesRepository limpiezaUnidadesRepository,
                            REDMedicamentoRepository redMedicamentoRepository,
                            LavadoManosRepository lavadoManosRepository) {

        this.oportunidadLavadoManosRepository = oportunidadLavadoManosRepository;
        this.curacionesComplejasRepository = curacionesComplejasRepository;
        this.limpiezaUnidadesRepository = limpiezaUnidadesRepository;
        this.redMedicamentoRepository = redMedicamentoRepository;
        this.lavadoManosRepository = lavadoManosRepository;
    }

    public void guardarEncuesta(EncuestaDto encuesta){
        switch (encuesta.getTipoEncuesta()){
            case OPORTUNIDAD_LAVADO_MANOS: {
                OportunidadLavadoManos olmEncuesta = new OportunidadLavadoManos(encuesta);
                oportunidadLavadoManosRepository.save(olmEncuesta);
            }
            break;
            case CURACIONES_COMPLEJAS: {
                CuracionesComplejas CCEncuesta = new CuracionesComplejas(encuesta);
                curacionesComplejasRepository.save(CCEncuesta);
            }
            break;
            case LIMPIEZA_UNIDADES: {
                LimpiezaUnidades luEncuesta = new LimpiezaUnidades(encuesta);
                limpiezaUnidadesRepository.save(luEncuesta);
            }
            break;
            case RED_MEDICAMENTOS: {
                REDMedicamentos redmEncuesta = new REDMedicamentos(encuesta);
                redMedicamentoRepository.save(redmEncuesta);
            }
            break;
            case LAVADO_MANOS: {
                LavadoManos lmEncuesta = new LavadoManos(encuesta);
                lavadoManosRepository.save(lmEncuesta);
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
