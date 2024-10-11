package cl.violetaparra.iaasvioletaparra.entity;

import cl.violetaparra.iaasvioletaparra.dto.EncuestaDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import static cl.violetaparra.iaasvioletaparra.utils.EncuestaUtils.*;

@Entity
@Table(name = "curaciones_complejas")
@Getter
@Setter
@NoArgsConstructor
public class CuracionesComplejas extends EncuestaBase {
    private int campo1;
    private int campo2;
    private int campo3;
    private int campo4;
    private int campo5;
    private int campo6;
    private int campo7;
    private int campo8;


    public CuracionesComplejas(EncuestaDto encuesta){
        super(encuesta.getEvaluadorId(), encuesta.getFuncionarioId(), encuesta.getFechaEvaluacion(), encuesta.getCampos());
        if(encuesta.getTipoEncuesta() != CURACIONES_COMPLEJAS){
            throw new IllegalArgumentException("Tipo de encuesta requerido es Oportunidad lavado de manos("
                    +CURACIONES_COMPLEJAS+") y fue entregado "+encuesta.getTipoEncuesta());
        }

        if(encuesta.getCampos().length != 8){
            throw new IllegalArgumentException("El largo de la encuesta debe ser igual a 8");
        }
        int[] campos = encuesta.getCampos();
        this.campo1 = campos[0];
        this.campo2 = campos[1];
        this.campo3 = campos[2];
        this.campo4 = campos[3];
        this.campo5 = campos[4];
        this.campo6 = campos[5];
        this.campo7 = campos[6];
        this.campo8 = campos[7];

    }

    @Override
    @Transient
    public int[] getCampos(){
        return new int[]
                {campo1, campo2, campo3, campo4, campo5, campo6, campo7, campo8};
    }
}
