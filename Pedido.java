import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int contadorPedidos = 1;
    private int id;
    private List<LineaPedido> lineas = new ArrayList<>();

    public Pedido() {
        this.id = contadorPedidos++;
    }

    public void agregarLinea(Producto producto, int cantidad) {
        lineas.add(new LineaPedido(producto, cantidad));
    }

    public double calcularTotal() {
        double total = 0;
        for (LineaPedido linea : lineas) {
            total += linea.getSubtotal();
        }
        return total;
    }

    public void mostrarPedido() {
        System.out.println("\nPedido N°" + id);
        for (LineaPedido linea : lineas) {
            System.out.println(linea);
        }
        System.out.println("Total: $" + calcularTotal());
    }
}
