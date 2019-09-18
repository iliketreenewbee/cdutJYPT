package com.example.demo.bean;

public class Good_items {
    private Integer id;

    private String item;

    private String isval;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item == null ? null : item.trim();
    }

    public String getIsval() {
        return isval;
    }

    public void setIsval(String isval) {
        this.isval = isval == null ? null : isval.trim();
    }
}