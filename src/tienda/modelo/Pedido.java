package tienda.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa un pedido realizado por un cliente.
 * RELACIÓN DE COMPOSICIÓN: un Pedido contiene una lista de LineaPedido.
 * RELACIÓN DE ASOCIACIÓN: un Pedido está asociado a un Cliente.
 */
public class Pedido {
    private int id;
    private Cliente cliente;
    private List<LineaPedido> lineas;   // composición: las líneas no existen sin el pedido
    private LocalDate fecha;
    private String estado;              // PENDIENTE, ENVIADO, ENTREGADO

    public Pedido(int id, Cliente cliente) {
        this.id       = id;
        this.cliente  = cliente;
        this.lineas   = new ArrayList<>();
        this.fecha    = LocalDate.now();
        this.estado   = "PENDIENTE";
    }

    /** Agrega un producto al pedido con su cantidad */
    public void agregarProducto(Producto producto, int cantidad) {
        lineas.add(new LineaPedido(producto, cantidad));
        producto.reducirStock(cantidad);
    }

    /** Calcula el total del pedido sumando todas las líneas */
    public double calcularTotal() {
        return lineas.stream()
                .mapToDouble(LineaPedido::getSubtotal)
                .sum();
    }

    public void setEstado(String estado) { this.estado = estado; }

    // Getters
    public int           getId()      { return id; }
    public Cliente       getCliente() { return cliente; }
    public List<LineaPedido> getLineas() { return lineas; }
    public LocalDate     getFecha()   { return fecha; }
    public String        getEstado()  { return estado; }

    @Override
    public String toString() {
        return String.format("Pedido{id=%d, cliente='%s', total=$%.2f, estado='%s'}",
                id, cliente.getNombre(), calcularTotal(), estado);
    }

    // -------------------------------------------------------
    // Clase interna de composición
    // -------------------------------------------------------

    /**
     * Línea individual dentro de un pedido.
     * Representa la relación entre un Producto y su cantidad comprada.
     */
    public static class LineaPedido {
        private Producto producto;
        private int cantidad;

        public LineaPedido(Producto producto, int cantidad) {
            this.producto = producto;
            this.cantidad = cantidad;
        }

        public double getSubtotal()   { return producto.getPrecio() * cantidad; }
        public Producto getProducto() { return producto; }
        public int getCantidad()      { return cantidad; }

        @Override
        public String toString() {
            return String.format("  - %s x%d = $%.2f",
                    producto.getNombre(), cantidad, getSubtotal());
        }
    }
}
