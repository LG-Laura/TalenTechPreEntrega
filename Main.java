import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Articulos articulos = new Articulos();
    static List<Pedido> pedidos = new ArrayList<>();

    public static void main(String[] args) {
        int opcion = 0;
        do {
            System.out.println("\n====== MENÚ PRINCIPAL ======");
            System.out.println("1) Agregar producto");
            System.out.println("2) Listar productos");
            System.out.println("3) Buscar/Actualizar producto");
            System.out.println("4) Eliminar producto");
            System.out.println("5) Crear pedido");
            System.out.println("6) Listar pedidos");
            System.out.println("7) Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1 -> agregarProducto();
                case 2 -> articulos.listarProductos();
                case 3 -> buscarActualizarProducto();
                case 4 -> eliminarProducto();
                case 5 -> crearPedido();
                case 6 -> listarPedidos();
                case 7 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 7);
    }

    private static void agregarProducto() {
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();

        double precio = leerDouble("Precio: ");
        int stock = leerInt("Stock: ");

        System.out.println("Tipo de producto:");
        System.out.println("1) Ropa");
        System.out.println("2) Tecnología");
        System.out.println("3) Comida");
        int tipo = leerInt("Seleccione tipo: ");

        Producto producto;
        switch (tipo) {
            case 1 -> producto = new Ropa(nombre, precio, stock);
            case 2 -> producto = new Tecnologia(nombre, precio, stock);
            case 3 -> producto = new Deco(nombre, precio, stock);
            default -> {
                System.out.println("Tipo inválido. Producto no agregado.");
                return;
            }
        }

        articulos.agregarProducto(producto);
        System.out.println("Producto agregado exitosamente.");
    }

    private static void buscarActualizarProducto() {
        int id = leerInt("Ingrese el ID del producto a buscar: ");
        Producto producto = articulos.buscarProductoPorId(id);

        if (producto == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        System.out.println("Producto encontrado: " + producto);
        System.out.print("Actualizar precio? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            producto.setPrecio(leerDouble("Nuevo precio: "));
        }

        System.out.print("Actualizar stock? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            producto.setStock(leerInt("Nuevo stock: "));
        }
    }

    private static void eliminarProducto() {
        int id = leerInt("Ingrese el ID del producto a eliminar: ");
        if (articulos.eliminarProducto(id)) {
            System.out.println("Producto eliminado.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private static void crearPedido() {
        Pedido pedido = new Pedido();
        while (true) {
            articulos.listarProductos();
            int id = leerInt("Ingrese el ID del producto a agregar al pedido (0 para finalizar): ");
            if (id == 0) break;

            Producto producto = articulos.buscarProductoPorId(id);
            if (producto == null) {
                System.out.println("Producto no encontrado.");
                continue;
            }

            int cantidad = leerInt("Cantidad: ");
            if (cantidad <= 0 || cantidad > producto.getStock()) {
                System.out.println("Cantidad inválida o stock insuficiente.");
                continue;
            }

            producto.setStock(producto.getStock() - cantidad);
            pedido.agregarLinea(producto, cantidad);
            System.out.println("Producto agregado al pedido.");
        }
        if (pedido.calcularTotal() != 0.0) {
            pedidos.add(pedido);
            pedido.mostrarPedido();
        } else {
            System.out.println("Pedido vacío. No se guardó.");
        }
    }

    private static void listarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
            return;
        }

        for (Pedido pedido : pedidos) {
            pedido.mostrarPedido();
        }
    }

    private static int leerInt(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido.");
            }
        }
    }

    private static double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido.");
            }
        }
    }
}
