package org.example.model;

public class OrderSubTotal extends OrderItem {
    private Integer subTotal;

    public Integer getSubTotal() {
        return this.subTotal;
    }

    public void setSubTotal(Integer subTotal) {
        this.subTotal = subTotal;
    }

    public OrderSubTotal(String menuName, Integer menuPrice, Integer quantity, Integer subTotal) {
        super(menuName, menuPrice, quantity);
        this.subTotal = subTotal;
    }
}
