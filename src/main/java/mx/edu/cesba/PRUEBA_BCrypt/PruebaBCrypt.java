package mx.edu.cesba.PRUEBA_BCrypt;
import org.mindrot.jbcrypt.BCrypt;
public class PruebaBCrypt {
    public static void main(String[] args) {
        String password = "D4AB82FB123E";
        String hash = BCrypt.hashpw(
                password,
                BCrypt.gensalt(12)
        );

        System.out.println("HASH GENERADO: ");
        System.out.println(hash);

        boolean correcta = BCrypt.checkpw(
                password,
                hash
        );

        System.out.println("CONTRASEÑA CORRECTA: " + correcta);
    }
}
