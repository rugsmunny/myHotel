/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myhotel;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static myhotel.MyHotel.customers;
import static myhotel.MyHotel.input;
import static myhotel.MyHotel.numberOfRooms;
import static myhotel.MyHotel.ourRooms;
import static myhotel.MyHotel.running;
import static myhotel.MyHotel.runningMenu;
import static myhotel.MyHotel.user;

/**
 *
 * @author admusr
 */
public abstract class Menus {
    private static int roomSelected;

    public static String ListChoices(String[] choices) {
        String question = "";
        for (int i = 0; i < choices.length; i++) {
            question += "\n" + (i + 1) + ". " + choices[i];

        }
        return question;
    }

    public static int choiceMethod(String[] choices, Scanner input, boolean back) {
        int choice;
        while (true) {

            String question = "";

            question = ListChoices(choices);

            if (back == true) {
                if (choices[0].equals("Staff")) {
                    question += "\n0. Exit";
                } else {
                    question += "\n0. Back";
                }

            }

            question += "\n\nChoice: ";

            System.out.print(question);

            try {
                choice = Integer.parseInt(input.nextLine());
                if (choice <= choices.length && choice >= 0) {
                    if (choice == 0 && back == true) {
                        return choice;
                    }
                    if (choice == 0 && back == false) {
                        continue;
                    }
                    return choice;
                }

            } catch (NumberFormatException e) {
                System.out.println("\nError. Please try again.");

            }
        }
    }

    public static String questionAndInputString(String question, Scanner input) {
        String inputString;
        while (true) {
            System.out.print("\n" + question + ": ");
            try {
                inputString = input.nextLine();
                if (!inputString.equals("")) {
                    return inputString;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static int questionAndInputInt(String question, Scanner input) {
        while (true) {
            System.out.print("\n" + question + ": ");
            try {
                return Integer.parseInt(input.nextLine());

            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    public static void foodMenu() {

        Scanner input = MyHotel.input;

        printFoodMenu(input);

    }

    private static void printFoodMenu(Scanner input) {
        boolean running = true;
        Order order;
        Date date = new Date();
        List<Order> kvitto = new ArrayList<>();
        while (running) {

            switch (choiceMethod(new String[]{"Pizza             - 115:-", "Lasagna           - 135:-", "Burger            -  95:-", "Water / Soda      -  25:-", "Chips             -  35:-", "Cookies           -  35:-"}, input, true)) {
                case 1:
                    kvitto.add(new Order(date, 115, "Pizza"));
                    break;
                case 2:
                    kvitto.add(new Order(date, 135, "Lasagne"));
                    break;
                case 3:
                    kvitto.add(new Order(date, 95, "Burger"));
                    break;
                case 4:
                    kvitto.add(new Order(date, 25, "Soda/Water"));
                    break;
                case 5:
                    kvitto.add(new Order(date, 35, "Chips"));
                    break;
                case 6:
                    kvitto.add(new Order(date, 35, "Cookies"));
                    break;

                case 0:
                    break;

            }

            System.out.println("Anything else? :");
            switch (Menus.choiceMethod(new String[]{"Yes", "No"}, input, false)) {
                case 1:

                    break;
                case 2:
                    running = false;
                    break;
            }

        }


        if (kvitto.isEmpty() != true){
            customers.stream().filter(c -> (c.roomNumber == MyHotel.selectedRoom)).findAny().get().billingDetails.addAll(kvitto);

        }       


    }

    public static int chooseRoomType() {

        int temp = 0;
        while (true) {
            switch (Menus.choiceMethod(new String[]{"Luxury Single Room", "Luxury Double Room", "Deluxe Single Room", "Deluxe Double Room"}, input, true)) {

                case 1:
                    temp = BookRoom(RoomType.Deluxe_Single_Room);
                    break;

                case 2:
                    temp = BookRoom(RoomType.Deluxe_Double_Room);
                    break;

                case 3:
                    temp = BookRoom(RoomType.Luxury_Single_Room);
                    break;

                case 4:
                    temp = BookRoom(RoomType.Luxury_Double_Room);
                    break;
                    
                case 0:
                    return 0;
            }


            if (temp != 0) {
                return temp;
            }

        }

    }

    public static User chooseUserType() {
        switch (Menus.choiceMethod(new String[]{"Staff", "Customer"}, input, true)) {
            
            case 1:
                user = User.Staff;
                break;

            case 2:
                user = User.Customer;
                break;

            case 0:

                running = false;
                runningMenu = false;

        }
        return user;
    }

    private static String changeCustomerName() {
        return Menus.questionAndInputString("Name", input);
    }

    private static int changeCustomerNumber() {
        return Menus.questionAndInputInt("Phonenumber", input);
    }

    public static void inputCustomerDetails() {

        int roomNum = Menus.chooseRoomType();
        
        if(roomNum != 0){
            customers.add(new Customer(changeCustomerName(), changeCustomerNumber(),roomNum));
        }
        
    }

    public static void changeCustomerDetailsAndRoomType() {
        customers.stream().forEach(System.out::println);


        switch (Menus.choiceMethod(new String[]{"Check details", "Change room"}, input, true)) {

            case 1:
                boolean running = true;
                while (running) {

                    switch (Menus.choiceMethod(new String[]{"Change name", "Change phone number", "Show billing details"}, input, true)) {
                        case 1:
                            changeCustomerName();
                            break;
                        case 2:
                            changeCustomerNumber();
                            break;
                        case 3:
                            System.out.println(customers.stream().filter(c -> (c.roomNumber == MyHotel.selectedRoom)).findAny().get().billingDetails);
                        case 0:
                            running = false;
                            break;
                    }
                }

                //updateCustomerDetails(MyHotel.selectedRoom);
                break;
            case 2:
                updateRoomType(MyHotel.selectedRoom);


                break;

        }
    }

    private static void updateCustomerDetails(int tempRoomNum) {

        customers.stream().filter(c -> (c.roomNumber == tempRoomNum)).findAny().get().setCustomerName(Menus.questionAndInputString("Name", input));
        customers.stream().filter(c -> (c.roomNumber == tempRoomNum)).findAny().get().setPhoneNumber(Menus.questionAndInputInt("Phone number", input));
    }

    private static void updateRoomType(int tempRoomNum) {

        System.out.print("Your current room type is " + ourRooms.get(tempRoomNum - 1) + ".\nWhat room type would you like to choose: ");

        customers.stream().filter(c -> c.roomNumber == tempRoomNum).findAny().get().setRoomNumber(Menus.chooseRoomType());
    }

    public static int selectRoom() {
        while (true) {
            displayRooms();
            int tempRoom = Menus.questionAndInputInt("Room number", input);
            if (customers.isEmpty()){
                return 0;
            }
            if (customers.stream().anyMatch(c -> c.roomNumber == tempRoom)) {

                return tempRoom;

            }
        }

    }

    public static void LoadHotelRooms() {
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
        for (Customer c : customers) {
            
            ourRooms.stream().filter(hr -> (hr.roomNumber == c.roomNumber)).forEach((hr -> hr.booked = true));
            

        }

    }


    private static void checkOutCustomer() {
        int roomPrice = 0;
        Integer total;

        Customer customer = customers.stream().filter(c -> (c.roomNumber == MyHotel.selectedRoom)).findAny().get();
        RoomType tempRoomType = ourRooms.stream().filter(r -> (r.roomNumber == MyHotel.selectedRoom)).findAny().get().roomType;

        if (tempRoomType == RoomType.Luxury_Single_Room) {
            roomPrice = 2000;
        }
        if (tempRoomType == RoomType.Luxury_Double_Room) {
            roomPrice = 5000;
        }
        if (tempRoomType == RoomType.Deluxe_Double_Room) {
            roomPrice = 1700;
        }
        if (tempRoomType == RoomType.Deluxe_Single_Room) {
            roomPrice = 1300;
        }
        System.out.println("Room cost for " + tempRoomType + " : " + roomPrice);
        System.out.println("\nOther expenses: ");
        customer.billingDetails.stream().forEach(System.out::println);
        total = customer.billingDetails.stream().collect(Collectors.summingInt((integer) -> integer.cost));
        System.out.println("\nTotal amount: " + (total + roomPrice) + "\n\nThank you for your stay!");
        ourRooms.stream().filter(r -> r.roomNumber == MyHotel.selectedRoom).findAny().get().booked = false;
        customers.remove(customer);

    }

    public static void displayRooms() {
        customers.stream().forEach(c -> System.out.println("-" + " " + c.toString()));
    }
    public static int BookRoom(RoomType r) {

        try {

            HotelRoom tempRoom = ourRooms.stream().filter(n -> (n.roomType == r && n.booked == false)).findAny().get();

            tempRoom.booked = true;
            return tempRoom.roomNumber;
        } catch (Exception e) {
            System.out.println("No rooms of this type avaliable.\nPlease choose another room: ");
        }
        return 0;

    }

    public static void LoadBookedRooms() {

        for (Customer c : customers) {

            ourRooms.stream().filter(hr -> (hr.roomNumber == c.roomNumber)).map(r -> r.booked = true);

        }
    }

    static void primaryMenu() {

        if (customers.isEmpty()) {
            inputCustomerDetails();
        }
        while (runningMenu == true) {

            switch (Menus.choiceMethod(user == User.Staff ? new String[]{"New customer", "Staying Customer"} : new String[]{"New customer", "Staying customer"}, input, true)) {
                case 1:
                    if (customers.size() == MyHotel.numberOfRooms) {
                        System.out.println("Sorry! We are fully booked. Please try later.");

                    } else {
                        Menus.inputCustomerDetails();
                    }
                    break;
                case 2:
                    Menus.stayingCustomers();

                case 0:
                    System.out.println("Shutting down, have a nice day!");

                    FileManagement.SaveCustomerData();
                    runningMenu = false;

            }
        }
    }


    private static void stayingCustomers() {


        boolean running = true;
        MyHotel.selectedRoom = selectRoom();
        while (running) {
            System.out.println("Selected room is: " + MyHotel.selectedRoom);
            switch (Menus.choiceMethod(user == User.Staff ? new String[]{"Food menu", "Checkout", "Customer details"} : new String[]{"Food menu"}, input, true)) {

                case 1:
                    Menus.foodMenu();

                    break;

                case 2:
                    checkOutCustomer();
                    running = false;
                    break;

                case 3:
                    Menus.changeCustomerDetailsAndRoomType();
                    break;

                case 0:
                    running = false;
            }
        }

    }

}
