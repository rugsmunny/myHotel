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

    public static boolean running = true;
    public static boolean runningMenu = true;
    public static int numberOfRooms = 40;
    public static List<HotelRoom> ourRooms = new ArrayList<>();
    public static List<Customer> customers = new ArrayList<>();
    public static Scanner input = new Scanner(System.in);
    public static User user;

    public static void main(String[] args) {

        customers = FileManagement.GetCustomerData(); 

        
        

        Menus.LoadHotelRooms();

        LoadBookedRooms();
        while (true) {

            user = Menus.chooseUserType();

            Menus.primaryMenu();
        }

    }

    public static int BookRoom(RoomType r) {

        HotelRoom tempRoom = ourRooms.stream().filter(n -> (n.roomType == r && n.booked == false)).findAny().get();
        tempRoom.booked = true;
        return tempRoom.roomNumber;

    }

    public static void LoadBookedRooms() {

        for (Customer c : customers) {
           ourRooms.stream().filter(hr -> (hr.roomNumber == c.roomNumber)).map(r->r.booked = true);

        }
    }

}
