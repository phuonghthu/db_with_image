package com.k214111950.model;

public class Product {
    private int ProductID;
    private String ProductName;
    private double ProductPrice;
    private byte[] ProductImage;

    public Product(int productID, String productName, double productPrice, byte[] productImage) {
        ProductID = productID;
        ProductName = productName;
        ProductPrice = productPrice;
        ProductImage = productImage;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public double getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(double productPrice) {
        ProductPrice = productPrice;
    }

    public byte[] getProductImage() {
        return ProductImage;
    }

    public void setProductImage(byte[] productImage) {
        ProductImage = productImage;
    }
}
