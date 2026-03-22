package tienda.patrones;

import tienda.modelo.Cliente;
import tienda.modelo.Pedido;
import tienda.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

/**
 * PATRÓN SINGLETON — GestorTienda
 * -------------------------------------------------------
 * Garantiza que exista una única instancia del gestor
 * central de la tienda durante toda la ejecución.
 * Administra el catálogo de productos, los clientes
 * registrados y los pedidos realizados.
 *
 * ¿Por qué Singleton?
 * El inventario y los pedidos deben ser consistentes
 * en todo el sistema; dos instancias distintas generarían
 * inconsistencias de stock.
 */
public class GestorTienda {

    // La única instancia estática de la clase
    private static GestorTienda instancia;

    private List<Producto> catalogo;
    private List<Cliente>  clientes;
    private List<Pedido>   pedidos;
    private int            contadorPedidos;

    /** Constructor privado: nadie puede instanciar desde fuera */
    private GestorTienda() {
        catalogo         = new ArrayList<>();
        clientes         = new ArrayList<>();
        pedidos          = new ArrayList<>();
        contadorPedidos  = 1;
    }

    /**
     * Punto de acceso global a la instancia única.
     * Si no existe, la crea; si ya existe, devuelve la misma.
     */
    public static GestorTienda getInstancia() {
        if (instancia == null) {
            instancia = new GestorTienda();
        }
        return instancia;
    }

    // ── Gestión del catálogo ──────────────────────────────

    public void agregarProducto(Producto p) { catalogo.add(p); }

    public List<Producto> getCatalogo() { return catalogo; }

    public Producto buscarProductoPorId(int id) {
        return catalogo.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // ── Gestión de clientes ───────────────────────────────

    public void registrarCliente(Cliente c) { clientes.add(c); }

    public List<Cliente> getClientes() { return clientes; }

    public Cliente buscarClientePorId(int id) {
        return clientes.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // ── Gestión de pedidos ────────────────────────────────

    public Pedido crearPedido(Cliente cliente) {
        Pedido p = new Pedido(contadorPedidos++, cliente);
        pedidos.add(p);
        return p;
    }

    public List<Pedido> getPedidos() { return pedidos; }
}
