package org.example.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.example.model.Data;
import org.example.model.OrderItem;
import org.example.model.OrderSubTotal;
import org.example.utils.Constant;

public class PaymentView {
    public void screenConfirmPayment(){
        System.out.println(Constant.PRINTCONFIRMPAYMENT);
        count();
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
    }

    public List<OrderSubTotal> tempSubTotal(){
        Map<String, Integer> groupQty = Data.orderItems.stream().collect(Collectors.groupingBy(OrderItem::getMenuName,
                Collectors.summingInt(OrderItem::getQuantity)));

        List<OrderSubTotal> listOrder = new ArrayList<>();
        int sub = 0;
        for (Map.Entry<String,Integer> entry : groupQty.entrySet())
            if (entry.getKey().equals("Nasi Goreng")){
                sub = entry.getValue() * 15000;
                listOrder.add(new OrderSubTotal(entry.getKey(), 15000, entry.getValue(), sub));
            }
            else if (entry.getKey().equals("Mie Goreng")){
                sub = entry.getValue() * 13000;
                listOrder.add(new OrderSubTotal(entry.getKey(), 13000, entry.getValue(), sub));
            }
            else if (entry.getKey().equals("Nasi + Ayam")){
                sub = entry.getValue() * 18000;
                listOrder.add(new OrderSubTotal(entry.getKey(), 18000, entry.getValue(), sub));
            }else if (entry.getKey().equals("Es Teh Manis")){
                sub = entry.getValue() * 3000;
                listOrder.add(new OrderSubTotal(entry.getKey(), 3000, entry.getValue(), sub));
            }else if (entry.getKey().equals("Es Jeruk")) {
                sub = entry.getValue() * 5000;
                listOrder.add(new OrderSubTotal(entry.getKey(), 5000, entry.getValue(), sub));
            }
        return listOrder;
    }
}
