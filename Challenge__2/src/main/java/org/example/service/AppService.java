package org.example.service;

import org.example.controller.OrderItemController;

public class AppService {

    private static boolean exit;

    public static boolean isExit() {
        return exit;
    }

    public static void setExit(boolean exit) {
        AppService.exit = exit;
    }

    public void initiateData() {
        MenuItemService mis = new MenuItemService();
        mis.initiateData();

    }

    public void run(){
        //Home
        while (!exit){
            OrderItemController orderItemController = new OrderItemController();
            orderItemController.home();
        }

    }

}

