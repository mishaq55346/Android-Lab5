package com.example.lab5.activities;

import java.util.List;
public class Singleton {
    private static Singleton singleObject;
    private List<LikedItem> items;
    public static Singleton getInstance() {
        if(singleObject == null)
        {
            singleObject = new Singleton();
        }
        return singleObject;
    }
    public void setItems(List<LikedItem> items) {
        this.items = items;
    }
    public List<LikedItem> getItems() {
        return items;
    }
    public LikedItem getItem(String name) {
        for(int i = 0; i < items.size(); i++)
        {
            if(items.get(i).getImageUrl().equals(name)) {
                return items.get(i);
            }
        }
        return null;
    }
}