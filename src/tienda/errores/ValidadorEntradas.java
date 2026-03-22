package tienda.errores;

import tienda.modelo.Producto;

/**
 * GESTIÓN DE ERRORES — ValidadorEntradas
 * -------------------------------------------------------
 * Centraliza las validaciones del sistema con try-catch-finally.
 * Garantiza que el programa nunca se detenga abruptamente
 * ante datos erróneos.
 */
public class ValidadorEntradas {

    /**
     * Valida que un nombre no sea nulo ni vacío.
     * @throws IllegalArgumentException si el nombre es inválido
     */
    public static void validarNombre(String nombre) {
        try {
            if (nombre == null || nombre.isBlank()) {
                throw new IllegalArgumentException("El nombre no puede estar vacío.");
            }
            if (nombre.length() < 2) {
                throw new IllegalArgumentException("El nombre debe tener al menos 2 caracteres.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("  ✗ Error de validación (nombre): " + e.getMessage());
            throw e;    // re-lanza para que el llamador también la maneje
        } finally {
            // El bloque finally siempre se ejecuta, haya error o no
            System.out.println("  [validación nombre ejecutada]");
        }
    }

    /**
     * Valida que un email tenga formato básico correcto.
     */
    public static void validarEmail(String email) {
        try {
            if (email == null || !email.contains("@") || !email.contains(".")) {
                throw new IllegalArgumentException("Email inválido: '" + email + "'");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("  ✗ Error de validación (email): " + e.getMessage());
            throw e;
        } finally {
            System.out.println("  [validación email ejecutada]");
        }
    }

    /**
     * Valida que la cantidad solicitada sea positiva y no supere el stock.
     * @throws StockInsuficienteException si el stock es insuficiente
     */
    public static void validarStock(Producto producto, int cantidad)
            throws StockInsuficienteException {
        try {
            if (cantidad <= 0) {
                throw new IllegalArgumentException("La cantidad debe ser mayor a cero.");
            }
            if (producto.getStock() < cantidad) {
                throw new StockInsuficienteException(
                        producto.getNombre(), producto.getStock(), cantidad);
            }
            System.out.println("  ✓ Stock validado correctamente para: " + producto.getNombre());
        } catch (IllegalArgumentException e) {
            System.out.println("  ✗ Error de validación (cantidad): " + e.getMessage());
            throw e;
        } catch (StockInsuficienteException e) {
            System.out.println("  ✗ " + e.getMessage());
            throw e;
        } finally {
            System.out.println("  [validación stock ejecutada]");
        }
    }

    /**
     * Valida que un precio sea positivo.
     */
    public static void validarPrecio(double precio) {
        try {
            if (precio <= 0) {
                throw new IllegalArgumentException("El precio debe ser mayor a cero. Recibido: " + precio);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("  ✗ Error de validación (precio): " + e.getMessage());
            throw e;
        } finally {
            System.out.println("  [validación precio ejecutada]");
        }
    }
}
