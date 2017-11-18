package com.example.test;

/**
 * Created by Wout on 17-11-2017.
 */

public class ListItem {

    private String name;
    private String desc;
    private int id;
    private int price;
    private String img_url;
    private String cat;

    public ListItem(String name, String desc, String img_url, String cat, int id, int price) {
        this.name = name;
        this.desc = desc;
        this.img_url = img_url;
        this.id = id;
        this.price = price;
        this.cat = cat;
    }

    public String getCat() {
        return cat;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public String getImg_url() {
        return img_url;
    }
}
