package mx.edu.cesba.conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class conexion {

    private static final String URL =
            "jdbc:mysql://localhost:3306/agenda_medica"
            + "?useSSL=false"
            + "&serverTimezone=UTC"
                    + "&allowPublicKeyRetrieval=true";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "D4AB82FB123E";

    public static Connection conectar(){
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conexion = DriverManager.getConnection(
                    URL,
                    USUARIO,
                    PASSWORD
            );
            System.out.println("conexion a agenda_medica exitosa");

        }catch (ClassNotFoundException e){
            System.out.println("no se encontro el driver de msql");

            e.printStackTrace();
        }catch (SQLException e) {
            System.out.println("error al conectar con agenda medica");

            e.printStackTrace();
        }
        return conexion;
    }
}
