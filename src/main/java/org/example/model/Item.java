package org.example.model;

// class items for objects in cart
public class Item {
    //class fields

    // string code unique for all
    private String code;
    // string code
    private String name;
    // prices
    private double price;
    // gst
    private double gstPercent;

    //constructor
    public Item(String code, String name, double price, double gstPercent) {
        // empty code check
        // null code check
        if (code == null || code.isEmpty()) throw new IllegalArgumentException("code required");
        this.code = code;
        this.name = name;
        this.price = price;
        this.gstPercent = gstPercent;
    }

    //Getter and Setter
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
        // empty code check
        // null code check
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


    //Overriding function to display object
    @Override
    public String toString() {

        return "Item{" + code + ", " + name + ", price=" + price + ", gst=" + gstPercent + "}";
    }
}

