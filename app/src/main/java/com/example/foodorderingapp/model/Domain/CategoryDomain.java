package com.example.foodorderingapp.model.Domain;

public class CategoryDomain {
    private int id;
    private String title;
    private  String pic;

    public CategoryDomain(){}
    public CategoryDomain(String title, String pic, int id) {
        this.id = id;
        this.title = title;
        this.pic = pic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
