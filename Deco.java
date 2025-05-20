class Deco extends Producto {
    public Deco(String nombre, double precio, int stock) {
        super(nombre, precio, stock);
    }

    @Override
    public String getTipo() {
        return "Deco";
    }
}
