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

/**
 *
 * @author karim
 */
public class MyHotel {

    public static boolean running = true;
    public static boolean runningMenu = true;
    public static int numberOfRooms = 40;
    public static List<HotelRoom> ourRooms = new ArrayList<>();
    public static List<Customer> customers = new ArrayList<>();
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        customers = FileManagement.GetCustomerData();

        for (int i = 0; i < numberOfRooms; i++) {
            if (i < (numberOfRooms * .25)) {
                ourRooms.add(new HotelRoom(i + 1, RoomType.Luxury_Double_Room));
            } else if (i < (numberOfRooms * .5)) {
                ourRooms.add(new HotelRoom(i + 1, RoomType.Luxury_Single_Room));
            } else if (i < (numberOfRooms * .75)) {
                ourRooms.add(new HotelRoom(i + 1, RoomType.Deluxe_Single_Room));
            } else {
                ourRooms.add(new HotelRoom(i + 1, RoomType.Deluxe_Double_Room));
            }
        }

        LoadBookedRooms();

        while (running) {
            switch (Menus.choiceMethod(new String[]{"New customer", "Customer details", "Food menu", "Checkout"}, input, true)) {
                case 1:
                    String tempName = Menus.questionAndInputString("Name", input);
                    int tempPhone = Menus.questionAndInputInt("Phonenumber", input);
                    int tempRoomNumber = 0;

                    switch (Menus.choiceMethod(new String[]{"Luxury Single Room", "Luxury Double Room", "Deluxe Single Room", "Deluxe Double Room"}, input, false)) {
                        case 1:
                            tempRoomNumber = BookRoom(RoomType.Deluxe_Single_Room);
                            break;
                        case 2:
                            tempRoomNumber = BookRoom(RoomType.Deluxe_Double_Room);
                            break;
                        case 3:
                            tempRoomNumber = BookRoom(RoomType.Luxury_Single_Room);
                            break;
                        case 4:
                            tempRoomNumber = BookRoom(RoomType.Luxury_Double_Room);
                            break;
                    }
                    customers.add(new Customer(tempName, tempPhone, tempRoomNumber));

                    break;
                case 2:
                    customers.stream().forEach(System.out::println);
                    switch (Menus.choiceMethod(new String[]{"Change customer details", "Upgrade"}, input, true)) {

                        case 1:
                            int tempRoomNum = Menus.questionAndInputInt("Room number", input);
                            customers.stream().filter(c -> (c.roomNumber == tempRoomNum)).findAny().get().setCustomerName(Menus.questionAndInputString("Name", input));
                            customers.stream().filter(c -> (c.roomNumber == tempRoomNum)).findAny().get().setPhoneNumber(Menus.questionAndInputInt("Phone number", input));
                            break;
                        case 2:
                            int newRoom = 0;
                            tempRoomNum = Menus.questionAndInputInt("Room number", input);
                            System.out.print("Your current room type is " + ourRooms.get(tempRoomNum - 1) + ".\nWhat room type would you like to choose: ");
                            switch (Menus.choiceMethod(new String[]{"Luxury Single Room", "Luxury Double Room", "Deluxe Single Room", "Deluxe Double Room"}, input, true)) {
                                case 1:
                                    newRoom = BookRoom(RoomType.Deluxe_Single_Room);
                                    break;
                                case 2:
                                    newRoom = BookRoom(RoomType.Deluxe_Double_Room);
                                    break;
                                case 3:
                                    newRoom = BookRoom(RoomType.Luxury_Single_Room);
                                    break;
                                case 4:
                                    newRoom = BookRoom(RoomType.Luxury_Double_Room);
                                    break;
                            }
                            customers.stream().filter(c -> c.roomNumber == tempRoomNum).findAny().get().setRoomNumber(newRoom);

                            break;

                    }

                    break;

                case 3:
                    Menus.foodMenu();
                    break;

                case 4:
                    checkOutCustomer(true);

                    break;
                case 0:
                    System.out.println("Shutting down, have a nice day!");
                    running = false;
                    FileManagement.SaveCustomerData();

            }
        }

    }

    public static int BookRoom(RoomType r) {

        HotelRoom tempRoom = null; 
        for (Customer c : customers){
            tempRoom = ourRooms.stream().filter(n -> (n.roomNumber == c.roomNumber && r == n.roomType)).findAny().get();
            if(tempRoom != null){
                break;
            }
        }

        tempRoom.booked = true;
        return tempRoom.roomNumber;

    }

    public static void LoadBookedRooms() {

        for (Customer c : customers) {
            HotelRoom check = ourRooms.stream().filter(hr -> (hr.roomNumber == c.roomNumber)).findAny().get();
            check.booked = true;

        }
    }

    private static void checkOutCustomer(boolean b) {
        while (b) {

            int checkOutRoom = Menus.roomNumberController(input, running);
            Customer customer = null;
            if (customers.stream().anyMatch(c -> (c.roomNumber == checkOutRoom))) {
                customer = customers.stream().filter(c -> (c.roomNumber == checkOutRoom)).findAny().get();
                HotelRoom tempRoom = ourRooms.stream().filter(n -> (n.roomNumber == checkOutRoom && n.booked == true)).findAny().get();
                tempRoom.booked = false;
                b = false;
//räkna ihop total skuld - visa "kvitto"
                System.out.println("\nCheckout done, Bye " + customer.getCustomerName() + "!");
                customers.remove(customer);
            } else {
                System.out.println("Something went wrong. Please try again.");
            }

        }

        FileManagement.SaveCustomerData();

    }

}
