package entity;

public class SizeColorProduct {
    private int id;
    private int idProduct;
    private String size;
    private String color;
    private int  inventory;

    public SizeColorProduct() {
    }

    public SizeColorProduct(int id, int idProduct, String size, String color, int inventory) {
        this.id = id;
        this.idProduct = idProduct;
        this.size = size;
        this.color = color;
        this.inventory = inventory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "SizeColorProduct{" +
                "id=" + id +
                ", idProduct=" + idProduct +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", inventory=" + inventory +
                '}';
    }
}
