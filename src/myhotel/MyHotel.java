/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myhotel;

import java.awt.Menu;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;

import java.util.HashMap;

import java.util.List;

import java.util.Scanner;

enum User {

    Customer, Staff;
}

public class MyHotel {
    public static int roomSelected;
    public static boolean running = true;
    public static boolean runningMenu = true;
    public static int numberOfRooms = 4;
    public static List<HotelRoom> ourRooms = new ArrayList<>();
    public static List<Customer> customers = new ArrayList<>();
    public static Scanner input = new Scanner(System.in);
    public static User user;
    public static int selectedRoom;

    public static void main(String[] args) {

        customers = FileManagement.GetCustomerData();

        Menus.LoadHotelRooms();


        while (running) { 
            
            
            runningMenu = true;
            user = Menus.chooseUserType();

            Menus.primaryMenu();
        }


    }

}
