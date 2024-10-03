package cl.violetaparra.iaasvioletaparra.entity;

import cl.violetaparra.iaasvioletaparra.dto.EncuestaDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static cl.violetaparra.iaasvioletaparra.utils.EncuestaUtils.*;

@Entity
@Table(name = "oportunidad_lavado_manos")
@Getter
@Setter
@NoArgsConstructor
public class OportunidadLavadoManos{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long evaluadorId;
    private Long funcionarioId;

    private LocalDateTime fechaEvaluacion;

    private int campo1;
    private int campo2;
    private int campo3;
    private int campo4;
    private int campo5;

    private boolean aprobado;

    public OportunidadLavadoManos(EncuestaDto encuesta){

        if(encuesta.getTipoEncuesta() != OPORTUNIDAD_LAVADO_MANOS){
            throw new IllegalArgumentException("Tipo de encuesta requerido es Oportunidad lavado de manos("
                    +OPORTUNIDAD_LAVADO_MANOS+") y fue entregado "+encuesta.getTipoEncuesta());
        }

        if(encuesta.getCampos().length != 5){
            throw new IllegalArgumentException("El largo de la encuesta debe ser igual a 5");
        }
        this.evaluadorId = encuesta.getEvaluadorId();
        this.funcionarioId = encuesta.getFuncionarioId();
        this.fechaEvaluacion = encuesta.getFechaEvaluacion();

        int[] campos = encuesta.getCampos();

        this.aprobado = encuestaAprobada(campos);

        this.campo1 = campos[0];
        this.campo2 = campos[1];
        this.campo3 = campos[2];
        this.campo4 = campos[3];
        this.campo5 = campos[4];
    }

    public int[] getCampos(){
        return new int[]{campo1, campo2, campo3, campo4, campo5};
    }
}
