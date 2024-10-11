package cl.violetaparra.iaasvioletaparra.service;

import cl.violetaparra.iaasvioletaparra.dto.EncuestaDto;
import cl.violetaparra.iaasvioletaparra.entity.CuracionesComplejas;
import cl.violetaparra.iaasvioletaparra.entity.LimpiezaUnidades;
import cl.violetaparra.iaasvioletaparra.entity.OportunidadLavadoManos;
import cl.violetaparra.iaasvioletaparra.entity.REDMedicamentos;
import cl.violetaparra.iaasvioletaparra.repository.CuracionesComplejasRepository;
import cl.violetaparra.iaasvioletaparra.repository.LimpiezaUnidadesRepository;
import cl.violetaparra.iaasvioletaparra.repository.OportunidadLavadoManosRepository;
import static cl.violetaparra.iaasvioletaparra.utils.EncuestaUtils.*;

import cl.violetaparra.iaasvioletaparra.repository.REDMedicamentoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EncuestasService {

    private final OportunidadLavadoManosRepository oportunidadLavadoManosRepository;
    private final CuracionesComplejasRepository curacionesComplejasRepository;
    private final LimpiezaUnidadesRepository limpiezaUnidadesRepository;
    private final REDMedicamentoRepository redMedicamentoRepository;

    public EncuestasService(OportunidadLavadoManosRepository oportunidadLavadoManosRepository,
                            CuracionesComplejasRepository curacionesComplejasRepository,
                            LimpiezaUnidadesRepository limpiezaUnidadesRepository,
                            REDMedicamentoRepository redMedicamentoRepository) {

        this.oportunidadLavadoManosRepository = oportunidadLavadoManosRepository;
        this.curacionesComplejasRepository = curacionesComplejasRepository;
        this.limpiezaUnidadesRepository = limpiezaUnidadesRepository;
        this.redMedicamentoRepository = redMedicamentoRepository;
    }

    public void guardarEncuesta(EncuestaDto encuesta){
        switch (encuesta.getTipoEncuesta()){
            case OPORTUNIDAD_LAVADO_MANOS: {
                OportunidadLavadoManos OLMEncuesta = new OportunidadLavadoManos(encuesta);
                oportunidadLavadoManosRepository.save(OLMEncuesta);
            }
            break;
            case CURACIONES_COMPLEJAS: {
                CuracionesComplejas CCEncuesta = new CuracionesComplejas(encuesta);
                curacionesComplejasRepository.save(CCEncuesta);
            }
            break;
            case LIMPIEZA_UNIDADES: {
                LimpiezaUnidades LUEncuesta = new LimpiezaUnidades(encuesta);
                limpiezaUnidadesRepository.save(LUEncuesta);
            }
            break;
            case RED_MEDICAMENTOS: {
                REDMedicamentos REDMEncuesta = new REDMedicamentos(encuesta);
                redMedicamentoRepository.save(REDMEncuesta);
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
