package org.example.service;

import org.example.controller.OrderItemController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.example.model.Data;
import org.example.model.MenuItem;
import org.example.model.OrderSubTotal;
import org.example.utils.Constant;
import org.example.view.PaymentView;

public class OrderItemService {


    List<MenuItem> tempOrder = new ArrayList<>();

    public List<MenuItem> orderMenu(int menuNumber) {
        MenuItemService mis = new MenuItemService();
        if (menuNumber == 1){
            String menuName = mis.getMenuItems().get(0).getMenuName();
            Integer menuPrice = mis.getMenuItems().get(0).getMenuPrice();
            tempOrder.add(new MenuItem(menuName, menuPrice));
            return tempOrder;

        } else if (menuNumber == 2) {
            String menuName = mis.getMenuItems().get(1).getMenuName();
            Integer menuPrice = mis.getMenuItems().get(1).getMenuPrice();
            tempOrder.add(new MenuItem(menuName, menuPrice));
            return tempOrder;

        } else if (menuNumber == 3) {
            String menuName = mis.getMenuItems().get(2).getMenuName();
            Integer menuPrice = mis.getMenuItems().get(2).getMenuPrice();
            tempOrder.add(new MenuItem(menuName, menuPrice));
            return tempOrder;

        } else if (menuNumber == 4) {
            String menuName = mis.getMenuItems().get(3).getMenuName();
            Integer menuPrice = mis.getMenuItems().get(3).getMenuPrice();
            tempOrder.add(new MenuItem(menuName, menuPrice));
            return tempOrder;

        } else if (menuNumber == 5) {
            String menuName = mis.getMenuItems().get(4).getMenuName();
            Integer menuPrice = mis.getMenuItems().get(4).getMenuPrice();
            tempOrder.add(new MenuItem(menuName, menuPrice));
            return tempOrder;
        }
        return tempOrder;
    }

    public boolean checkOutOrder(){
        PaymentView pv = new PaymentView();
        OrderItemController oic = new OrderItemController();
        try {
            pv.screenConfirmPayment();
            int choose = oic.inputUser();
            switch (choose) {
                case 1 -> {
                    pv.screenPembayaran();
                    printStruck();
                    Data.orderItems.clear();
                    return false;
                }
                case 2 -> {
                    System.out.println("Kembali ke menu utama");
                    return false;
                }
                case 0 -> {
                    System.out.println("Keluar Aplikasi");
                    return true;
                }
                default -> {
                    return oic.errorHandler();
                }
            }
        }catch (Exception e){
            return oic.errorHandler();
        }
    }

    public void printStruck() {
        PaymentView pv = new PaymentView();
        FileWriter myWriter = null;
        File currentDir = new File("Challenge__2/struk.txt");
        String path = currentDir.getAbsolutePath();
        try(InputStream input = Files.newInputStream(Path.of(path))){
            myWriter = new FileWriter(path);
            myWriter.write(Constant.PRINTHEADERSTRUCT);
            int sumQty = 0;
            int sumTotal = 0;
            for (OrderSubTotal order : pv.tempSubTotal()) {
                if (order.getQuantity() > 9 && order.getMenuPrice() > 10000) {
                    myWriter.write(Constant.handleMenuName(order.getMenuName()) + order.getQuantity() + "    " + order.getMenuPrice() + "  " + order.getSubTotal() + "\n");
                } else if (order.getQuantity() < 9 && order.getMenuPrice() > 10000) {
                    myWriter.write(Constant.handleMenuName(order.getMenuName()) + order.getQuantity() + "     " + order.getMenuPrice() + "  " + order.getSubTotal() + "\n");
                } else if (order.getQuantity() < 9 && order.getMenuPrice() < 10000) {
                    myWriter.write(Constant.handleMenuName(order.getMenuName()) + order.getQuantity() + "     " + order.getMenuPrice() + "   " + order.getSubTotal() + "\n");
                } else if (order.getQuantity() > 9 && order.getMenuPrice() < 10000) {
                    myWriter.write(Constant.handleMenuName(order.getMenuName()) + order.getQuantity() + "    " + order.getMenuPrice() + "   " + order.getSubTotal() + "\n");
                }
                sumQty += order.getQuantity();
                sumTotal += order.getSubTotal();
            }

            if (sumQty > 9) {
                myWriter.write(Constant.sumTotalQtyMore(sumQty, sumTotal));
            } else {
                myWriter.write(Constant.sumTotalQtyLess(sumQty, sumTotal));
            }

            myWriter.write(Constant.PRINTFOOTERSTRUCT);
            myWriter.close();
            System.out.println("Struk berhasil di cetak. Terimakasih sudah belanja di BinarFood\n");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } finally {
            try {
                myWriter.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
