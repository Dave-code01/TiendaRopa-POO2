package tienda.patrones;

/**
 * PATRÓN STRATEGY — Estrategias de Descuento
 * -------------------------------------------------------
 * Define una familia de algoritmos de descuento
 * intercambiables sin modificar el código que los usa.
 *
 * ¿Por qué Strategy?
 * La tienda puede tener múltiples reglas de descuento
 * (temporada, cliente VIP, liquidación).  Con Strategy
 * se puede cambiar o agregar descuentos sin tocar el
 * resto del sistema.
 */

// ── Interfaz común ────────────────────────────────────────
public interface EstrategiaDescuento {
    /**
     * Calcula el precio final aplicando el descuento.
     * @param precioOriginal precio base del producto
     * @return precio con descuento aplicado
     */
    double aplicarDescuento(double precioOriginal);

    /** Descripción legible de la estrategia */
    String descripcion();
}
