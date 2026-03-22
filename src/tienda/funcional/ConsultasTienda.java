package tienda.funcional;

import tienda.modelo.Cliente;
import tienda.modelo.Pedido;
import tienda.modelo.Producto;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * PROGRAMACIÓN FUNCIONAL — ConsultasTienda
 * -------------------------------------------------------
 * Centraliza todas las operaciones sobre colecciones
 * usando Streams y expresiones lambda.
 * Demuestra: filter(), map(), sorted(), collect(),
 *            mapToDouble(), groupingBy(), forEach().
 */
public class ConsultasTienda {

    // ── Consultas sobre productos ─────────────────────────

    /**
     * Filtra productos por categoría usando filter() y lambda.
     */
    public static List<Producto> filtrarPorCategoria(List<Producto> productos, String categoria) {
        return productos.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }

    /**
     * Filtra productos que cuesten menos de un precio máximo.
     */
    public static List<Producto> filtrarPorPrecioMaximo(List<Producto> productos, double precioMax) {
        return productos.stream()
                .filter(p -> p.getPrecio() <= precioMax)
                .collect(Collectors.toList());
    }

    /**
     * Devuelve los nombres de todos los productos usando map().
     */
    public static List<String> obtenerNombresProductos(List<Producto> productos) {
        return productos.stream()
                .map(Producto::getNombre)
                .collect(Collectors.toList());
    }

    /**
     * Calcula el precio promedio del catálogo con mapToDouble() + average().
     */
    public static double calcularPrecioPromedio(List<Producto> productos) {
        return productos.stream()
                .mapToDouble(Producto::getPrecio)
                .average()
                .orElse(0.0);
    }

    /**
     * Ordena productos de menor a mayor precio con sorted().
     */
    public static List<Producto> ordenarPorPrecio(List<Producto> productos) {
        return productos.stream()
                .sorted(Comparator.comparingDouble(Producto::getPrecio))
                .collect(Collectors.toList());
    }

    /**
     * Agrupa productos por categoría con groupingBy().
     */
    public static Map<String, List<Producto>> agruparPorCategoria(List<Producto> productos) {
        return productos.stream()
                .collect(Collectors.groupingBy(Producto::getCategoria));
    }

    /**
     * Devuelve los productos con stock disponible (stock > 0).
     */
    public static List<Producto> productosDisponibles(List<Producto> productos) {
        return productos.stream()
                .filter(p -> p.getStock() > 0)
                .collect(Collectors.toList());
    }

    // ── Consultas sobre pedidos ───────────────────────────

    /**
     * Calcula la suma de ventas totales usando mapToDouble() + sum().
     */
    public static double totalVentas(List<Pedido> pedidos) {
        return pedidos.stream()
                .mapToDouble(Pedido::calcularTotal)
                .sum();
    }

    /**
     * Filtra pedidos por estado (PENDIENTE, ENVIADO, ENTREGADO).
     */
    public static List<Pedido> filtrarPorEstado(List<Pedido> pedidos, String estado) {
        return pedidos.stream()
                .filter(p -> p.getEstado().equalsIgnoreCase(estado))
                .collect(Collectors.toList());
    }

    // ── Impresión con forEach ─────────────────────────────

    /** Imprime todos los productos del catálogo con forEach() y lambda. */
    public static void imprimirCatalogo(List<Producto> productos) {
        System.out.println("\n===== CATÁLOGO DE PRODUCTOS =====");
        productos.forEach(p ->
                System.out.printf("  [%d] %-20s | %-10s | Talla: %-3s | $%.2f | Stock: %d%n",
                        p.getId(), p.getNombre(), p.getCategoria(),
                        p.getTalla(), p.getPrecio(), p.getStock())
        );
    }

    /** Imprime todos los clientes registrados. */
    public static void imprimirClientes(List<Cliente> clientes) {
        System.out.println("\n===== CLIENTES REGISTRADOS =====");
        clientes.forEach(c ->
                System.out.printf("  [%d] %-15s | %s | Puntos: %d%n",
                        c.getId(), c.getNombre(), c.getEmail(), c.getPuntosAcumulados())
        );
    }
}
