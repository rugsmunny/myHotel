/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myhotel;


import java.util.ArrayList;

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

    public static void main(String[] args) {

      
        customers = FileManagement.GetCustomerData();  

        
        Scanner input = new Scanner(System.in);
        
        
        /*
        customers.add(new Customer("karim", 555555, 11));
        customers.add(new Customer("leo", 555555, 12));
        customers.add(new Customer("oskar", 555555, 13));
        customers.add(new Customer("fia", 555555, 14));
        customers.add(new Customer("linda", 555555, 15));
        customers.add(new Customer("asif", 555555, 16));
        customers.add(new Customer("abiat", 555555, 17));
        customers.add(new Customer("beshlick", 555555, 18));*/

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
        while (running) {
            switch (Menus.choiceMethod(new String[]{"New customer", "Customer details", "Food menu", "Checkout"}, input, true)) {
                case 1:
                    String tempName = Menus.questionAndInputString("Name", input);
                    int tempPhone = Menus.questionAndInputInt("Phonenumber", input);
                    int tempRoomNumber = 0;

                    switch (Menus.choiceMethod(new String[]{"Luxury Single Room", "Luxury Double Room", "Deluxe Single Room", "Deluxe Double Room"}, input, true)) {
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
                            System.out.print("Your current room type is " + ourRooms.get(tempRoomNum-1) + ".\nWhat room type would you like to choose: ");
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
                    HashMap<String, Integer> tempOrder = new HashMap<>();
                    while (true) {
                        switch (Menus.choiceMethod(new String[]{"Pizza  -  115:-", "Lasagna  -  135:-", "Burger   -  95:-", "Water / Soda  -  25:-", "Chips  -  35:-", "Cookies  -  35:-"}, input, true)) {
                            case 1:
                                tempOrder.compute("Pizza", (k, v) -> (v == null) ? 115 : v + 115);
                                break;
                            case 2:
                                tempOrder.compute("Lasagna", (k, v) -> (v == null) ? 135 : v + 135);
                                break;
                            case 3:
                                tempOrder.compute("Burger", (k, v) -> (v == null) ? 95 : v + 95);
                                break;
                            case 4:
                                tempOrder.compute("Soda/Water", (k, v) -> (v == null) ? 25 : v + 25);
                                break;
                            case 5:
                                tempOrder.compute("Chips", (k, v) -> (v == null) ? 35 : v + 35);
                                break;
                            case 6:
                                tempOrder.compute("Cookies", (k, v) -> (v == null) ? 35 : v + 35);
                                break;
                        }

                        if (Menus.questionAndInputString("Anything else?  Y/N? Choice", input).toLowerCase().contains("n")) {
                            int tempRoomNum = Menus.questionAndInputInt("Room number", input);
                            customers.stream().filter(c -> (c.roomNumber == tempRoomNum)).findAny().get().billingDetails.putAll(tempOrder); //Uppdatera billingDetails på specifik kund utifrån rumsnummer 
                            customers.stream().forEach(System.out::println);
                            break;

                        }
                    }

                case 4:
                    System.out.println("Checkout done, Bye Karim!");
                    //customers.stream().forEach(FileManagement::SaveCustomerData);
                    FileManagement.SaveCustomerData();
                    running = false;
                    break;
                case 9:
                    System.out.println("Shutting down, have a nice day!");
                    running = false;

            }

        }

        
       
        ourRooms.stream().forEach(System.out::println);
       


    }

    public static int BookRoom(RoomType r) {
        HotelRoom tempRoom = ourRooms.stream().filter(n -> (n.roomType == r && n.booked == false)).findAny().get();
        tempRoom.booked = true;
        return tempRoom.roomNumber;

    }

}
