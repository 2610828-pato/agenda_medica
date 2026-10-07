package mx.edu.cesba.PruebaConexion;
import java.sql.Connection;

import mx.edu.cesba.conexion.conexion;
public class PruebaConexion {

    public static void main(String[] args) {
Connection conexion = mx.edu.cesba.conexion.conexion.conectar();
    if (conexion != null) {
            System.out.println(
                    "================================="
            );
            System.out.println(
                    "CONEXION EXITOSA"
            );
            System.out.println(
                    "Base de datos: agenda_medica"
            );
            System.out.println(
                    "================================="
            );
            try {
                conexion.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(
                    "================================="
            );
            System.out.println(
                    "ERROR DE CONEXIÓN"
            );
            System.out.println(
                    "================================="
            );
        }
    }
}

