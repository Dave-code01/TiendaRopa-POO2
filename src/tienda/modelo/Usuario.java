package tienda.modelo;

/**
 * Clase base que representa un usuario del sistema de tienda de ropa.
 * Contiene los atributos comunes a todo tipo de usuario.
 */
public class Usuario {
    protected int id;
    protected String nombre;
    protected String email;
    protected String telefono;

    public Usuario(int id, String nombre, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    // Getters
    public int getId()          { return id; }
    public String getNombre()   { return nombre; }
    public String getEmail()    { return email; }
    public String getTelefono() { return telefono; }

    @Override
    public String toString() {
        return "Usuario{id=" + id + ", nombre='" + nombre + "', email='" + email + "'}";
    }
}
