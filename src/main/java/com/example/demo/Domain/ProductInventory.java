package com.example.demo.Domain;

public class ProductInventory {
    private int id;
    private String name;
    private String price;
    private int product_id;
    private int quantity;
    private Products product;
    private Inventory inventory;
    @Override
    public String toString() {
        return "ProductInventory [id=" + id + ", name=" + name + ", price=" + price + ", product_id=" + product_id
                + ", quantity=" + quantity + ", product=" + product + ", inventory=" + inventory + ", getId()="
                + getId() + ", getName()=" + getName() + ", getPrice()=" + getPrice() + ", getProduct_id()="
                + getProduct_id() + ", getQuantity()=" + getQuantity() + ", getProduct()=" + getProduct()
                + ", getInventory()=" + getInventory() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
                + ", toString()=" + super.toString() + "]";
    }
    public ProductInventory() {
    }
    public ProductInventory(int id, String name, String price, int product_id, int quantity, Products product,
            Inventory inventory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.product_id = product_id;
        this.quantity = quantity;
        this.product = product;
        this.inventory = inventory;
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
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public int getProduct_id() {
        return product_id;
    }
    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Products getProduct() {
        return product;
    }
    public void setProduct(Products product) {
        this.product = product;
    }
    public Inventory getInventory() {
        return inventory;
    }
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
