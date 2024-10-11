package cl.violetaparra.iaasvioletaparra.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static cl.violetaparra.iaasvioletaparra.utils.EncuestaUtils.encuestaAprobada;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Encuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long evaluadorId;
    private Long funcionarioId;
    private boolean aprobado;
    private LocalDateTime fechaEvaluacion;

    public Encuesta(Long evaluadorId, Long funcionarioId, LocalDateTime fechaEvaluacion,  int[] campos){
        this.evaluadorId = evaluadorId;
        this.funcionarioId = funcionarioId;
        this.fechaEvaluacion = fechaEvaluacion;
        this.aprobado = encuestaAprobada(campos);
    }

    @Transient
    public abstract int[] getCampos();
}
