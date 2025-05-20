import java.util.ArrayList;
import java.util.List;

public class Articulos {
    private List<Producto> listaProductos = new ArrayList<>();

    public void agregarProducto(Producto producto) {
        listaProductos.add(producto);
    }

    public void listarProductos() {
        if (listaProductos.isEmpty()) {
            System.out.println("No hay productos.");
            return;
        }
        for (Producto p : listaProductos) {
            System.out.println(p);
        }
    }

    public Producto buscarProductoPorId(int id) {
        for (Producto p : listaProductos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public boolean eliminarProducto(int id) {
        Producto producto = buscarProductoPorId(id);
        if (producto != null) {
            listaProductos.remove(producto);
            return true;
        }
        return false;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }
}
