package cl.violetaparra.iaasvioletaparra.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class EncuestaUtils {
    //tipos de campo
    public static final int CUMPLE = 1;
    public static final int NO_CUMPLE = 2;
    public static final int NO_APLICA = 3;

    // tipos de encuesta
    public static final int OPORTUNIDAD_LAVADO_MANOS = 101;
    public static final int CURACIONES_COMPLEJAS = 102;
    public static final int LIMPIEZA_UNIDADES = 103;
    //no se lol
    private static final int[] TIPOS_ENCUESTA = {OPORTUNIDAD_LAVADO_MANOS, CURACIONES_COMPLEJAS, LIMPIEZA_UNIDADES};

    private static final Map<Integer,String> NOMBRE_ENCUESTAS = new HashMap<>();
    static {
        NOMBRE_ENCUESTAS.put(OPORTUNIDAD_LAVADO_MANOS, "Oportunidad lavado de manos");
        NOMBRE_ENCUESTAS.put(CURACIONES_COMPLEJAS, "Curaciones complejas");
        NOMBRE_ENCUESTAS.put(LIMPIEZA_UNIDADES, "Limpieza unidades");
    }

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

    public static boolean existeEncuesta(int tipoEncuesta){
        return Arrays.stream(TIPOS_ENCUESTA).anyMatch(tipo -> tipo == tipoEncuesta);
    }

    public static String getNombreEncuesta(int tipoEncuesta){
        return NOMBRE_ENCUESTAS.get(tipoEncuesta);
    }
}
