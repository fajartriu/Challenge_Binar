package org.example.service;

import java.util.List;
import org.example.model.Data;
import org.example.model.MenuItem;

public class MenuItemService {
    public void initiateData(){
        Data.menuItems.add(new MenuItem("Nasi Goreng", 15000));
        Data.menuItems.add(new MenuItem("Mie Goreng", 13000));
        Data.menuItems.add(new MenuItem("Nasi + Ayam", 18000));
        Data.menuItems.add(new MenuItem("Es Teh Manis", 3000));
        Data.menuItems.add(new MenuItem("Es Jeruk", 5000));
    }

    public List<MenuItem> getMenuItems(){
        return Data.menuItems;
    }
}
