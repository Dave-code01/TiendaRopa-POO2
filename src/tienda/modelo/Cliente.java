package tienda.modelo;

/**
 * Subclase de Usuario que representa a un cliente registrado.
 * Agrega atributos propios del cliente: dirección de envío y puntos acumulados.
 * RELACIÓN: herencia de Usuario.
 */
public class Cliente extends Usuario {
    private String direccionEnvio;
    private int puntosAcumulados;

    public Cliente(int id, String nombre, String email, String telefono, String direccionEnvio) {
        super(id, nombre, email, telefono);
        this.direccionEnvio = direccionEnvio;
        this.puntosAcumulados = 0;
    }

    public String getDireccionEnvio()   { return direccionEnvio; }
    public int getPuntosAcumulados()    { return puntosAcumulados; }

    /** Agrega puntos al cliente según el valor de su compra */
    public void agregarPuntos(double totalCompra) {
        this.puntosAcumulados += (int)(totalCompra / 1000);
    }

    @Override
    public String toString() {
        return "Cliente{id=" + id + ", nombre='" + nombre + "', puntos=" + puntosAcumulados + "}";
    }
}
