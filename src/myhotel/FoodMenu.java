/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myhotel;

import java.util.HashMap;

/**
 *
 * @author karim
 */
public class FoodMenu {
    public static HashMap<String, Integer> menu = new HashMap<>();

   public FoodMenu() {
        menu.put("Pizza", 115);
        menu.put("Lasagna", 135);
        menu.put("Burger", 95);
        menu.put("Sallad", 75);
        menu.put("Soda/Water", 25);
        menu.put("Chips", 25);
        menu.put("Cookies", 25);
        
    }



    public static HashMap<String, Integer> getMenu() {
        return menu;
    }

    public static void setMenu(HashMap<String, Integer> menu) {
        FoodMenu.menu = menu;
    }

   
      @Override
    public String toString() {
        return "FoodMenu{" + menu;
    }
}