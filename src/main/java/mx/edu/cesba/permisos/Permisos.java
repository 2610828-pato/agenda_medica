package mx.edu.cesba.permisos;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
public class Permisos {
    //roles
    public static final String ADMIN = "ADMIN";
    public static final String MEDICO = "MEDICO";
    public static final String RECEPCION = "RECEPCION";

    //MODULO DEL SISTEMA

    public static final String PACIENTES = "PACIENTES";
    public static final String MEDICOS = "MEDICOS";
    public static final String CITAS = "CITAS";
    public static final String CONSULTAS = "CONSULTAS";
    public static final String EXPEDIENTES = "EXPEDIENTES";
    public static final String USUARIOS = "USUARIOS";

    private static final Map<String, Set<String>> PERMISOS = new HashMap<>();

    static {
        Set<String> todo = new HashSet<>(Arrays.asList(
                PACIENTES, MEDICOS, CITAS, CONSULTAS, EXPEDIENTES, USUARIOS
        ));

        PERMISOS.put(ADMIN, todo);
        PERMISOS.put(MEDICO, todo);
        PERMISOS.put(RECEPCION, new HashSet<>(Arrays.asList(
                PACIENTES, CONSULTAS)));
    }

    public static boolean puede(String rol, String modulo){
        if (rol == null){
            return false;
        }
        Set<String>modulos = PERMISOS.get(rol.trim().toUpperCase());
        return modulo != null && modulos.contains(modulo);
    }


}
