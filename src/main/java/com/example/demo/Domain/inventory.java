package com.example.demo.Domain;

public class Inventory {
    private int id;
    private String name;
    private int price;
    private int quantity;

    @Override
    public String toString() {
        return "Inventory [id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", getId()="
                + getId() + ", getName()=" + getName() + ", getPrice()=" + getPrice() + ", getQuantity()="
                + getQuantity() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
                + super.toString() + "]";
    }

    public Inventory() {
    }

    public Inventory(int id, String name, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
