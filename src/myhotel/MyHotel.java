/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myhotel;

import java.awt.Menu;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import static javax.swing.UIManager.put;

/**
 *
 * @author karim
 */
public class MyHotel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        LinkedHashMap<String, Integer> receptionistFoodMenu = new LinkedHashMap<>();
        {
            {

                put("Pizza", 125);
                put("Burger", 130);
                put("Fish n Chips", 110);
                put("Lasagna", 145);
                put("Sallad", 100);

            }
        }

        int numberOfRooms = 40;
        List<HotelRoom> ourRooms = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();

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
        
        List<HotelRoom> tempRoomList = new ArrayList<>();
        
        switch (Menus.choiceMethod(new String[]{"New customer", "Customer details", "Bookings", "Food menu", "Checkout"}, input, true)) {
            case 1:
                String tempName = Menus.questionAndInputString("Name", input);
                int tempPhone = Menus.questionAndInputInt("Phonenumber", input);
                switch (Menus.choiceMethod(new String[]{"Luxury Single Room", "Luxury Double Room", "Deluxe Single Room", "Deluxe Double Room"}, input, true)) {
                    case 1:
                        System.out.println(ourRooms.stream().filter(n -> (n.roomType == RoomType.Deluxe_Single_Room && n.booked == false )).findAny().get());
                        break;
                    case 2:
                        ourRooms.stream().filter(n -> n.roomType == RoomType.Deluxe_Double_Room).forEach(System.out::println);
                        break;
                    case 3:
                        ourRooms.stream().filter(n -> n.roomType == RoomType.Luxury_Single_Room).forEach(System.out::println);
                        break;
                    case 4:
                        ourRooms.stream().filter(n -> n.roomType == RoomType.Luxury_Double_Room).forEach(System.out::println);
                        break;
                }
                
                break;
            case 2:
                System.out.println("2");
                break;
            case 3:
                System.out.println("3");
                break;
        }

    }

}
