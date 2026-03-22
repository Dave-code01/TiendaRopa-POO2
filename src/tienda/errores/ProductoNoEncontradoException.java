package tienda.errores;

/** Excepción lanzada cuando un producto no existe en el catálogo */
public class ProductoNoEncontradoException extends Exception {
    public ProductoNoEncontradoException(int idProducto) {
        super("Producto con ID " + idProducto + " no encontrado en el catálogo.");
    }
}
