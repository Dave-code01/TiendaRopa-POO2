package tienda.patrones;

/**
 * Implementaciones concretas de EstrategiaDescuento.
 * Cada clase encapsula un algoritmo de descuento diferente.
 */

// ── 1. Sin descuento (precio normal) ─────────────────────
class SinDescuento implements EstrategiaDescuento {
    @Override
    public double aplicarDescuento(double precio) { return precio; }

    @Override
    public String descripcion() { return "Precio regular (sin descuento)"; }
}

// ── 2. Descuento de temporada (15 %) ─────────────────────
class DescuentoTemporada implements EstrategiaDescuento {
    private static final double PORCENTAJE = 0.15;

    @Override
    public double aplicarDescuento(double precio) {
        return precio * (1 - PORCENTAJE);
    }

    @Override
    public String descripcion() { return "Descuento de temporada (15%)"; }
}

// ── 3. Descuento cliente VIP (25 %) ──────────────────────
class DescuentoVIP implements EstrategiaDescuento {
    private static final double PORCENTAJE = 0.25;

    @Override
    public double aplicarDescuento(double precio) {
        return precio * (1 - PORCENTAJE);
    }

    @Override
    public String descripcion() { return "Descuento cliente VIP (25%)"; }
}

// ── 4. Liquidación (50 %) ────────────────────────────────
class DescuentoLiquidacion implements EstrategiaDescuento {
    private static final double PORCENTAJE = 0.50;

    @Override
    public double aplicarDescuento(double precio) {
        return precio * (1 - PORCENTAJE);
    }

    @Override
    public String descripcion() { return "Liquidación (50%)"; }
}

// ── Fábrica de estrategias ────────────────────────────────
/**
 * Clase utilitaria que actúa como fábrica de estrategias.
 * Centraliza la creación de los objetos de descuento.
 */
public class FabricaDescuento {
    public static EstrategiaDescuento obtener(String tipo) {
        return switch (tipo.toUpperCase()) {
            case "TEMPORADA"   -> new DescuentoTemporada();
            case "VIP"         -> new DescuentoVIP();
            case "LIQUIDACION" -> new DescuentoLiquidacion();
            default            -> new SinDescuento();
        };
    }
}
