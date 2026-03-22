package tienda.errores;

/**
 * GESTIÓN DE ERRORES — Excepciones personalizadas
 * -------------------------------------------------------
 * Excepciones específicas del dominio de la tienda.
 * Permiten mensajes de error claros y descriptivos.
 */

// ── Stock insuficiente ────────────────────────────────────
public class StockInsuficienteException extends Exception {
    private final String nombreProducto;
    private final int stockDisponible;
    private final int cantidadSolicitada;

    public StockInsuficienteException(String nombreProducto, int disponible, int solicitado) {
        super(String.format(
                "Stock insuficiente para '%s': disponible=%d, solicitado=%d",
                nombreProducto, disponible, solicitado));
        this.nombreProducto    = nombreProducto;
        this.stockDisponible   = disponible;
        this.cantidadSolicitada = solicitado;
    }

    public String getNombreProducto()   { return nombreProducto; }
    public int getStockDisponible()     { return stockDisponible; }
    public int getCantidadSolicitada()  { return cantidadSolicitada; }
}
