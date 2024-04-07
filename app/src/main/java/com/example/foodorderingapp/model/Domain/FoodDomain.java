package com.example.foodorderingapp.model.Domain;

public class FoodDomain {
    private String title;
    private String pic;
    private String description;
    private Double fee;
    private int numberInCart;

    public FoodDomain(String title, String pic, String description, String fee) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.fee = Double.valueOf(fee);
    }

    public FoodDomain(String title, String pic, String description, String fee, int numberInCart) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.fee = Double.valueOf(fee);
        this.numberInCart = numberInCart;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = Double.valueOf(fee);
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}
