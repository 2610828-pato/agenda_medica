package mx.edu.cesba.pruebaUsuario;

import mx.edu.cesba.UsuarioDao.UsuarioDAO;
import mx.edu.cesba.Usuario.Usuario;
import org.mindrot.jbcrypt.BCrypt; // <-- 1. Importa BCrypt

public class PruebaUsuario {

    public static void main(String[] args) {

        String passwordSeguro = BCrypt.hashpw(
                "1234", BCrypt.gensalt(12));

        Usuario usuario = new Usuario(
                "pato",
                passwordSeguro,
                "Administrador",
                "ADMIN"
        );

        UsuarioDAO dao = new UsuarioDAO();

        boolean resultado = dao.insertar(usuario);

        if (resultado) {
            System.out.println("--------------------------------------------------");
            System.out.println("USUARIO CREADO CORRECTAMENTE CON HASH");
            System.out.println("Usuario: pato");
            System.out.println("Rol: ADMIN");
            System.out.println("--------------------------------------------------");
        } else {
            System.out.println("--------------------------------------------------");
            System.out.println("ERROR AL CREAR EL USUARIO");
            System.out.println("--------------------------------------------------");
        }
    }
}
