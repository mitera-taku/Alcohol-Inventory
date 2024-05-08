package com.example.demo.Domain;

public class products {
    private int id;
    private String name;
    private String price;

    public products() {

    }

    public products(int id, String name, String price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "products [id=" + id + ", name=" + name + ", price=" + price + ", getId()=" + getId() + ", getName()="
                + getName() + ", getPrice()=" + getPrice() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
                + ", toString()=" + super.toString() + "]";
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
}
