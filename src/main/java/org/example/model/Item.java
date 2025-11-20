package org.example.model;

public class Item {
    private String code;
    private String name;
    private double price;
    private double gstPercent;

    public Item(String code, String name, double price, double gstPercent) {
        if (code == null || code.isEmpty()) throw new IllegalArgumentException("code required");
        this.code = code;
        this.name = name;
        this.price = price;
        this.gstPercent = gstPercent;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getGstPercent() {
        return gstPercent;
    }

    public void setCode(String code) {
        if (code == null || code.isEmpty()) throw new IllegalArgumentException("code required");
        this.code = code;
    }

    public void setName(String name) {
        // name can be null or empty
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setGstPercent(double gstPercent) {
        this.gstPercent = gstPercent;
    }

    @Override
    public String toString() {
        return "Item{" + code + ", " + name + ", price=" + price + ", gst=" + gstPercent + "}";
    }
}

