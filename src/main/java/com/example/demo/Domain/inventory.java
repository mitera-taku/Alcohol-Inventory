package com.example.demo.Domain;

public class inventory {
    private int product_id;
    private int quantity;

    public inventory() {
    }

    public inventory(int product_id, int quantity) {
        this.product_id = product_id;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "inventory [product_id=" + product_id + ", quantity=" + quantity + ", getProduct_id()=" + getProduct_id()
                + ", getQuantity()=" + getQuantity() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
                + ", toString()=" + super.toString() + "]";
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
}
