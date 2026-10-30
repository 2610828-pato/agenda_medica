package mx.edu.cesba.UsuarioDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import mx.edu.cesba.Usuario.Usuario;
import mx.edu.cesba.PruebaConexion.conexion;

public class UsuarioDAO {
    private conexion Conexion;


    public boolean insertar(Usuario u) {

        String sql =
                "INSERT INTO usuarios (" +
                        "usuario, password, nombre, rol, activo) " +
                        "VALUES (?, ?, ?, ?, ?)";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement ps =
                     conexion.prepareStatement(sql)) {

            ps.setString(1, u.getUsuario());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getNombre());
            ps.setString(4, u.getRol());
            ps.setBoolean(5, u.isActivo());

            int filas =
                    ps.executeUpdate();

            return filas > 0;

        } catch (Exception ex) {

            ex.printStackTrace();

            return false;
        }

    }

    // ----------------------------------------------------
    // BUSCAR USUARIO POR USUARIO
    // ----------------------------------------------------
    public Usuario buscarPorUsuario(String usuario) {

        String sql =
                "SELECT id, usuario, password, nombre, rol, activo " +
                        "FROM usuarios " +
                        "WHERE usuario = ?";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement ps =
                     conexion.prepareStatement(sql)) {

            ps.setString(1, usuario);

            try (ResultSet rs =
                         ps.executeQuery()) {

                if (rs.next()) {

                    return new Usuario(
                            rs.getInt("id"),
                            rs.getString("usuario"),
                            rs.getString("password"),
                            rs.getString("nombre"),
                            rs.getString("rol"),
                            rs.getBoolean("activo")
                    );

                }

            }

        } catch (Exception ex) {

            ex.printStackTrace();

        }

        return null;
    }
}