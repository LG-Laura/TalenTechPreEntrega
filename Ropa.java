class Ropa extends Producto {
    public Ropa(String nombre, double precio, int stock) {
        super(nombre, precio, stock);
    }

    @Override
    public String getTipo() {
        return "Ropa";
    }
}
