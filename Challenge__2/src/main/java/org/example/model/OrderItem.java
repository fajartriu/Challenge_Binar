package org.example.model;

public class OrderItem extends MenuItem {
    private Integer quantity;

    public OrderItem(String menuName, Integer menuPrice, Integer quantity) {
        super(menuName, menuPrice);
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
