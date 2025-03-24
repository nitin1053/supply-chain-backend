package com.supplychainbackend.model;
//package com.supplychain.model;

public class PredictionRequest {
    private String category;
    private String region;
    private int price;
    private int stockLevel;

    // Getters and setters
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public int getStockLevel() { return stockLevel; }
    public void setStockLevel(int stockLevel) { this.stockLevel = stockLevel; }
}

