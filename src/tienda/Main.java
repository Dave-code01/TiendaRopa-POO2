package tienda;

import tienda.errores.ProductoNoEncontradoException;
import tienda.errores.StockInsuficienteException;
import tienda.errores.ValidadorEntradas;
import tienda.funcional.ConsultasTienda;
import tienda.modelo.Cliente;
import tienda.modelo.Pedido;
import tienda.modelo.Producto;
import tienda.patrones.EstrategiaDescuento;
import tienda.patrones.FabricaDescuento;
import tienda.patrones.GestorTienda;

import java.util.List;
import java.util.Map;

/**
 * =====================================================
 *   TIENDA DE ROPA — Sistema de E-commerce en Java
 *   Evidencia de Aprendizaje Unidad 3 — POO II
 *   IU Digital de Antioquia
 * =====================================================
 *
 * Conceptos aplicados:
 *  1. Clases, subclases y relaciones
 *  2. Patrones de diseño: Singleton + Strategy
 *  3. Programación funcional: Streams y lambdas
 *  4. Gestión de errores: try-catch-finally
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║     SISTEMA TIENDA DE ROPA — POO II     ║");
        System.out.println("╚══════════════════════════════════════════╝\n");

        // ─────────────────────────────────────────────────────
        // 1. SINGLETON — Obtenemos la única instancia del gestor
        // ─────────────────────────────────────────────────────
        GestorTienda gestor = GestorTienda.getInstancia();
        System.out.println("✔ GestorTienda instancia creada: " + gestor.hashCode());
        // Verificamos que siempre es la misma instancia:
        GestorTienda gestor2 = GestorTienda.getInstancia();
        System.out.println("✔ Segunda llamada - misma instancia: " + (gestor == gestor2));

        // ─────────────────────────────────────────────────────
        // 2. CARGA DE CATÁLOGO Y CLIENTES
        // ─────────────────────────────────────────────────────
        cargarCatalogo(gestor);
        cargarClientes(gestor);

        // ─────────────────────────────────────────────────────
        // 3. PROGRAMACIÓN FUNCIONAL
        // ─────────────────────────────────────────────────────
        demostrarFuncional(gestor);

        // ─────────────────────────────────────────────────────
        // 4. PATRÓN STRATEGY — Descuentos
        // ─────────────────────────────────────────────────────
        demostrarStrategy();

        // ─────────────────────────────────────────────────────
        // 5. CREACIÓN DE PEDIDOS CON VALIDACIONES
        // ─────────────────────────────────────────────────────
        demostrarPedidosYErrores(gestor);

        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║          FIN DE LA DEMOSTRACIÓN         ║");
        System.out.println("╚══════════════════════════════════════════╝");
    }

    // ────────────────────────────────────────────────────────
    // MÉTODOS DE APOYO
    // ────────────────────────────────────────────────────────

    private static void cargarCatalogo(GestorTienda gestor) {
        gestor.agregarProducto(new Producto(1, "Camiseta Básica",   "Camiseta",  "M",  35_000, 20));
        gestor.agregarProducto(new Producto(2, "Camiseta Estampada","Camiseta",  "L",  45_000, 15));
        gestor.agregarProducto(new Producto(3, "Jean Slim",         "Pantalón",  "32", 89_000, 10));
        gestor.agregarProducto(new Producto(4, "Jean Clásico",      "Pantalón",  "34", 79_000,  8));
        gestor.agregarProducto(new Producto(5, "Tenis Casual",      "Zapatos",   "42",120_000,  5));
        gestor.agregarProducto(new Producto(6, "Chaqueta Denim",    "Chaqueta",  "M", 150_000,  6));
        gestor.agregarProducto(new Producto(7, "Sudadera Hoodie",   "Sudadera",  "L",  95_000, 12));
        gestor.agregarProducto(new Producto(8, "Falda Plisada",     "Falda",     "S",  65_000,  0)); // sin stock
    }

    private static void cargarClientes(GestorTienda gestor) {
        gestor.registrarCliente(new Cliente(1, "Ana García",    "ana@email.com",   "300-111", "Calle 10 #5-20, Medellín"));
        gestor.registrarCliente(new Cliente(2, "Luis Martínez", "luis@email.com",  "301-222", "Carrera 8 #15-30, Bogotá"));
        gestor.registrarCliente(new Cliente(3, "María López",   "maria@email.com", "302-333", "Avenida 4 #20-10, Cali"));
    }

    private static void demostrarFuncional(GestorTienda gestor) {
        System.out.println("\n══════════ PROGRAMACIÓN FUNCIONAL ══════════");
        List<Producto> catalogo = gestor.getCatalogo();

        // filter() — productos por categoría
        List<Producto> camisetas = ConsultasTienda.filtrarPorCategoria(catalogo, "Camiseta");
        System.out.println("\n▶ filter() — Camisetas en catálogo: " + camisetas.size());
        camisetas.forEach(p -> System.out.println("   " + p));

        // filter() — productos con precio ≤ 80.000
        List<Producto> economicos = ConsultasTienda.filtrarPorPrecioMaximo(catalogo, 80_000);
        System.out.println("\n▶ filter() — Productos ≤ $80.000: " + economicos.size());

        // map() — solo nombres
        List<String> nombres = ConsultasTienda.obtenerNombresProductos(catalogo);
        System.out.println("\n▶ map() — Nombres de productos:");
        nombres.forEach(n -> System.out.println("   • " + n));

        // mapToDouble + average()
        double promedio = ConsultasTienda.calcularPrecioPromedio(catalogo);
        System.out.printf("\n▶ mapToDouble + average() — Precio promedio: $%.2f%n", promedio);

        // sorted()
        System.out.println("\n▶ sorted() — Productos ordenados por precio:");
        ConsultasTienda.ordenarPorPrecio(catalogo)
                .forEach(p -> System.out.printf("   $%,.0f — %s%n", p.getPrecio(), p.getNombre()));

        // groupingBy()
        Map<String, List<Producto>> grupos = ConsultasTienda.agruparPorCategoria(catalogo);
        System.out.println("\n▶ groupingBy() — Productos por categoría:");
        grupos.forEach((cat, prods) ->
                System.out.println("   " + cat + ": " + prods.size() + " producto(s)"));

        // filter() — solo con stock disponible
        long disponibles = ConsultasTienda.productosDisponibles(catalogo).size();
        System.out.println("\n▶ filter() — Productos con stock disponible: " + disponibles + "/" + catalogo.size());

        // Clientes
        ConsultasTienda.imprimirClientes(gestor.getClientes());
    }

    private static void demostrarStrategy() {
        System.out.println("\n══════════ PATRÓN STRATEGY — DESCUENTOS ══════════");
        double precioBase = 150_000;
        String[] tipos = {"NORMAL", "TEMPORADA", "VIP", "LIQUIDACION"};

        System.out.printf("Precio base: $%,.0f%n%n", precioBase);
        for (String tipo : tipos) {
            EstrategiaDescuento estrategia = FabricaDescuento.obtener(tipo);
            double precioFinal = estrategia.aplicarDescuento(precioBase);
            System.out.printf("  %-35s → $%,.2f%n", estrategia.descripcion(), precioFinal);
        }
    }

    private static void demostrarPedidosYErrores(GestorTienda gestor) {
        System.out.println("\n══════════ PEDIDOS Y VALIDACIONES ══════════");

        Cliente cliente = gestor.buscarClientePorId(1);

        // ── Pedido exitoso ────────────────────────────────
        System.out.println("\n--- Pedido exitoso ---");
        try {
            Pedido pedido = gestor.crearPedido(cliente);
            Producto camiseta = gestor.buscarProductoPorId(1);
            Producto jean     = gestor.buscarProductoPorId(3);

            ValidadorEntradas.validarStock(camiseta, 2);
            pedido.agregarProducto(camiseta, 2);

            ValidadorEntradas.validarStock(jean, 1);
            pedido.agregarProducto(jean, 1);

            double total = pedido.calcularTotal();
            cliente.agregarPuntos(total);

            System.out.println("\n  ✔ " + pedido);
            pedido.getLineas().forEach(l -> System.out.println(l));
            System.out.printf("  Total: $%,.2f | Puntos acumulados: %d%n",
                    total, cliente.getPuntosAcumulados());

        } catch (StockInsuficienteException e) {
            System.out.println("  ✗ No se pudo procesar el pedido: " + e.getMessage());
        }

        // ── Error: stock insuficiente ─────────────────────
        System.out.println("\n--- Intentar comprar más de lo disponible ---");
        try {
            Producto tenis = gestor.buscarProductoPorId(5); // stock = 5
            ValidadorEntradas.validarStock(tenis, 10);       // pedimos 10
            System.out.println("  ✔ Stock OK");
        } catch (StockInsuficienteException e) {
            System.out.println("  ✗ Error capturado: " + e.getMessage());
        }

        // ── Error: producto sin stock ─────────────────────
        System.out.println("\n--- Producto sin stock (stock = 0) ---");
        try {
            Producto falda = gestor.buscarProductoPorId(8); // stock = 0
            ValidadorEntradas.validarStock(falda, 1);
        } catch (StockInsuficienteException e) {
            System.out.println("  ✗ Error capturado: " + e.getMessage());
        }

        // ── Error: email inválido ─────────────────────────
        System.out.println("\n--- Validación de email inválido ---");
        try {
            ValidadorEntradas.validarEmail("correo-sin-arroba");
        } catch (IllegalArgumentException e) {
            System.out.println("  ✗ Error capturado: " + e.getMessage());
        }

        // ── Error: nombre vacío ───────────────────────────
        System.out.println("\n--- Validación de nombre vacío ---");
        try {
            ValidadorEntradas.validarNombre("");
        } catch (IllegalArgumentException e) {
            System.out.println("  ✗ Error capturado: " + e.getMessage());
        }

        // ── Error: producto no encontrado ─────────────────
        System.out.println("\n--- Buscar producto con ID inexistente ---");
        try {
            Producto p = gestor.buscarProductoPorId(99);
            if (p == null) throw new ProductoNoEncontradoException(99);
            System.out.println("  ✔ Producto: " + p);
        } catch (ProductoNoEncontradoException e) {
            System.out.println("  ✗ Error capturado: " + e.getMessage());
        }

        // ── Resumen de ventas (funcional sobre pedidos) ───
        System.out.println("\n--- Resumen final de ventas ---");
        double totalVentas = ConsultasTienda.totalVentas(gestor.getPedidos());
        System.out.printf("  Total pedidos registrados : %d%n", gestor.getPedidos().size());
        System.out.printf("  Total ventas acumuladas   : $%,.2f%n", totalVentas);

        List<Pedido> pendientes = ConsultasTienda.filtrarPorEstado(gestor.getPedidos(), "PENDIENTE");
        System.out.printf("  Pedidos en estado PENDIENTE: %d%n", pendientes.size());
    }
}
