class Tecnologia extends Producto {
    public Tecnologia(String nombre, double precio, int stock) {
        super(nombre, precio, stock);
    }

    @Override
    public String getTipo() {
        return "Tecnología";
    }
}
