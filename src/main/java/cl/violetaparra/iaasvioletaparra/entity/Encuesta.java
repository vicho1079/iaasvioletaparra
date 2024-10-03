package cl.violetaparra.iaasvioletaparra.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Encuesta {

    public abstract int[] getCampos();
}
