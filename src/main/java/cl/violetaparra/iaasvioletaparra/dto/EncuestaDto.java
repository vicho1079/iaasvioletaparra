package cl.violetaparra.iaasvioletaparra.dto;

import cl.violetaparra.iaasvioletaparra.entity.OportunidadLavadoManos;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class EncuestaDto {
    private Long evaluadorId;
    private Long funcionarioId;
    private int tipoEncuesta;

    private LocalDateTime fechaEvaluacion;
    private int[] campos;

    public EncuestaDto(OportunidadLavadoManos encuesta){
        this.evaluadorId = encuesta.getEvaluadorId();
        this.funcionarioId = encuesta.getFuncionarioId();
        this.fechaEvaluacion = encuesta.getFechaEvaluacion();
        this.campos = encuesta.getCampos();
    }
}
