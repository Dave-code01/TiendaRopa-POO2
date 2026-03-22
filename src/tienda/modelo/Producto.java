package tienda.modelo;

/**
 * Representa un producto (prenda de ropa) disponible en la tienda.
 * Incluye categoría, talla, precio y stock disponible.
 */
public class Producto {
    private int id;
    private String nombre;
    private String categoria;   // Camiseta, Pantalón, Zapatos, etc.
    private String talla;       // XS, S, M, L, XL
    private double precio;
    private int stock;

    public Producto(int id, String nombre, String categoria, String talla, double precio, int stock) {
        this.id        = id;
        this.nombre    = nombre;
        this.categoria = categoria;
        this.talla     = talla;
        this.precio    = precio;
        this.stock     = stock;
    }

    // Getters
    public int    getId()        { return id; }
    public String getNombre()    { return nombre; }
    public String getCategoria() { return categoria; }
    public String getTalla()     { return talla; }
    public double getPrecio()    { return precio; }
    public int    getStock()     { return stock; }

    /** Reduce el stock al confirmar una venta */
    public void reducirStock(int cantidad) {
        this.stock -= cantidad;
    }

    @Override
    public String toString() {
        return String.format("Producto{id=%d, nombre='%s', talla='%s', precio=$%.2f, stock=%d}",
                id, nombre, talla, precio, stock);
    }
}
