package org.example.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.example.model.*;
import org.example.service.MenuItemService;
import org.example.utils.Constant;

public class PaymentView {
    public void screenConfirmPayment(){
        System.out.println(Constant.PRINTCONFIRMPAYMENT);
        count();
        System.out.print("\n");
        System.out.println(Constant.PRINTINPUTPAYMENT);
        System.out.print("=> ");
    }

    public void screenPembayaran(){

        System.out.println(Constant.PRINTHEADERSTRUCT);
        count();
        System.out.println(Constant.PRINTFOOTERSTRUCT);

    }

    public void count(){
        int sumQty = 0;
        int sumTotal = 0;
        for(OrderSubTotal order:tempSubTotal()){
            if (order.getQuantity() > 9 && order.getMenuPrice() > 10000) {
                System.out.println(Constant.handleMenuName(order.getMenuName()) + order.getQuantity() + "    " + order.getMenuPrice() + "  " +order.getSubTotal());
            } else if (order.getQuantity() < 9 && order.getMenuPrice() > 10000) {
                System.out.println(Constant.handleMenuName(order.getMenuName()) + order.getQuantity() + "     " + order.getMenuPrice() + "  " +order.getSubTotal());
            } else if (order.getQuantity() < 9 && order.getMenuPrice() < 10000){
                System.out.println(Constant.handleMenuName(order.getMenuName()) + order.getQuantity() + "     " + order.getMenuPrice() + "   "+order.getSubTotal());
            } else if (order.getQuantity() > 9 && order.getMenuPrice() < 10000) {
                System.out.println(Constant.handleMenuName(order.getMenuName()) + order.getQuantity() + "    " + order.getMenuPrice() + "   "+order.getSubTotal());
            }
            sumQty += order.getQuantity();
            sumTotal += order.getSubTotal();
        }
        if (sumQty>9){
            System.out.println(Constant.sumTotalQtyMore(sumQty,sumTotal));
        }else{
            System.out.println(Constant.sumTotalQtyLess(sumQty,sumTotal));
        }

        System.out.println("Notes : ");
        for (TotalOrderNotes totalOrderNotes:handleRedundantNotes()){
            System.out.println(totalOrderNotes.getTotalNotes()+" "+totalOrderNotes.getName()+" "+totalOrderNotes.getNotes());
        }
    }

    public List<TotalOrderNotes> handleRedundantNotes(){
        Map<Integer, Long> groupQty = Data.notes.stream().collect(Collectors.groupingBy(OrderNotes::getIdNotes,
                Collectors.counting()));
        List<TotalOrderNotes> lisTotNotes = new ArrayList<>();
        for (Map.Entry<Integer,Long> entry : groupQty.entrySet()){
            String menuName = getMenuName(entry.getKey());
            String notes = getNotes(entry.getKey());
            lisTotNotes.add(new TotalOrderNotes(entry.getKey(), notes, menuName, entry.getValue()));
        }
        return lisTotNotes;
    }

    public String getMenuName(int id){
        for (OrderNotes orderNotes: Data.notes){
            if (orderNotes.getIdNotes() == id){
                return orderNotes.getName();
            }
        }
        return null;
    }

    public String getNotes(int id){
        for (OrderNotes orderNotes: Data.notes){
            if (orderNotes.getIdNotes() == id){
                return orderNotes.getNotes();
            }
        }
        return null;
    }

    public List<OrderSubTotal> tempSubTotal(){
        MenuItemService mis = new MenuItemService();
        Map<String, Integer> groupQty = Data.orderItems.stream().collect(Collectors.groupingBy(OrderItem::getMenuName,
                Collectors.summingInt(OrderItem::getQuantity)));

        List<OrderSubTotal> listOrder = new ArrayList<>();
        int sub = 0;
        for (Map.Entry<String,Integer> entry : groupQty.entrySet()){
            Integer menuId = mis.getMenuId(entry.getKey());
            Integer menuPrice = mis.getMenuPrice(entry.getKey());
            sub = entry.getValue() * menuPrice;
            listOrder.add(new OrderSubTotal(menuId, entry.getKey(), menuPrice, entry.getValue(), sub));
        }
        return listOrder;
    }
}
