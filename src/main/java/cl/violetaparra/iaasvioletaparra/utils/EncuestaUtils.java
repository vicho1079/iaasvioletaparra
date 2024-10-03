package cl.violetaparra.iaasvioletaparra.utils;

public class EncuestaUtils {
    public static final int CUMPLE = 1;
    public static final int NO_CUMPLE = 2;
    public static final int NO_APLICA = 3;


    public static final int OPORTUNIDAD_LAVADO_MANOS = 101;
    public static final int LAVADO_MANOS = 102;
    public static final int CURACIONES_COMPLEJAS = 103;
    public static boolean valorValido(int valor){
        return valor >= 1 && valor <= 3;
    }

    public static boolean encuestaAprobada(int[] campos){
        boolean aprobado = true;
        for (int campo : campos) {
            if(!valorValido(campo)){
                throw  new IllegalArgumentException("Los valores validos en una encuesta van del 1 al 3");
            }
            aprobado = (campo == CUMPLE || campo == NO_APLICA) && aprobado;
        }
        return aprobado;
    }
}
