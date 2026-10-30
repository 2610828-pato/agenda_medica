package mx.edu.cesba.servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.edu.cesba.Usuario.Usuario;
import mx.edu.cesba.UsuarioDao.UsuarioDAO;

import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Configurar codificación
        request.setCharacterEncoding("UTF-8");

        // 2. Obtener datos del formulario
        String usuario =
                request.getParameter("usuario");

        String password = request.getParameter("password");

        // 3. Validar que los campos no estén vacíos
        if (usuario == null || usuario.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {

            response.sendRedirect("login.jsp?error=0");
            return;
        }

        // 4. Buscar usuario en la base de datos
        UsuarioDAO dao =
                new UsuarioDAO();

        Usuario usuarioEncontrado =
                dao.buscarPorUsuario(usuario);

        // 5. Validar si el usuario existe
        if (usuarioEncontrado != null) {

            // Validar que esté activo
            if (!usuarioEncontrado.isActivo()) {

                response.sendRedirect("login.jsp?error=1");
                return;
            }

            // Verificar contraseña con BCrypt
            boolean passwordCorrecta = false;

            try {
                passwordCorrecta = BCrypt.checkpw(
                        password,
                        usuarioEncontrado.getPassword()
                );
            } catch (IllegalArgumentException e) {
                // El hash almacenado no tiene un formato válido.
                passwordCorrecta = false;
            }

            if (passwordCorrecta) {

                // Crear sesión
                HttpSession sesion =
                        request.getSession();

                sesion.setAttribute("usuario",
                        usuarioEncontrado.getUsuario());

                sesion.setAttribute("nombre",
                        usuarioEncontrado.getNombre());

                sesion.setAttribute("rol",
                        usuarioEncontrado.getRol());

               request.changeSessionId();

                response.sendRedirect("index.jsp");

            } else {
                // Contraseña incorrecta
                response.sendRedirect("login.jsp?error=2");

            }

        } else {
            // Usuario no encontrado
            response.sendRedirect("login.jsp?error=3");

        }

        System.out.println("--- INTENTO DE LOGIN ---");
        System.out.println("Usuario escrito en el input: " + usuario);
        System.out.println("Contraseña escrita en el input: " + password);
        System.out.println("¿Se encontró el usuario en la BD?: " + (usuarioEncontrado != null));

        if (usuarioEncontrado != null) {
            System.out.println("Contraseña hasheada en la BD: " + usuarioEncontrado.getPassword());
            boolean coincide = BCrypt.checkpw(password, usuarioEncontrado.getPassword());
            System.out.println("¿La contraseña coincide según BCrypt?: " + coincide);
        }
    }
}
