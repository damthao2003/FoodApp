package com.example.foodorderingapp.model.Domain;

public class FoodDomain {
    private String title;
    private String pic;
    private  String decscription;
    private  Double fee;
    private  int numberInCart;

    public FoodDomain(String title, String pic, String decscription, Double fee, int numberInCart) {
        this.title = title;
        this.pic = pic;
        this.decscription = decscription;
        this.fee = fee;
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

    public String getDecscription() {
        return decscription;
    }

    public void setDecscription(String decscription) {
        this.decscription = decscription;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}
