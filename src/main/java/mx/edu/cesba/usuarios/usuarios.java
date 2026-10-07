package mx.edu.cesba.usuarios;

public class usuarios {
    private int id;
    private  String usuario;
    private  String password;
    private  String nombre;
    private String rol;
    private boolean activo;

    public usuarios(){

    }

    public usuarios(
            String usuario,
            String password,
            String nombre,
            String rol){
        this.usuario = usuario;
        this.password = password;
        this.nombre = nombre;
        this.rol = rol;
        this.activo = true;
    }
    public usuarios(
            int id,
            String usuario,
            String password,
            String nombre,
            String rol,
            boolean activo){
        this.id = id;
        this.usuario = usuario;
        this.password = password;
        this.nombre = nombre;
        this.rol = rol;
        this.activo = activo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
