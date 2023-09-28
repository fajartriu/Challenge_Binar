package org.example.model;

public class MenuItem {
    private String menuName;
    private Integer menuPrice;

    public String getMenuName() {
        return this.menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getMenuPrice() {
        return this.menuPrice;
    }

    public void setMenuPrice(Integer menuPrice) {
        this.menuPrice = menuPrice;
    }

    public MenuItem() {
    }

    public MenuItem(String menuName, Integer menuPrice) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
    }
}

