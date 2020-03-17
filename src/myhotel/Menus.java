/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myhotel;

import static com.oracle.jrockit.jfr.ContentType.Class;
import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import java.util.Collection;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
import static javafx.scene.input.KeyCode.T;
import static myhotel.MyHotel.BookRoom;
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

        LinkedHashMap<String, Integer> tempOrder = new LinkedHashMap<>();
        Scanner input = MyHotel.input;
        

        printFoodMenu(input, tempOrder);

        setOrderToRoomNum(input, tempOrder);

    }

    private static HashMap printFoodMenu(Scanner input, HashMap<String, Integer> tempOrder) {
        while (true) {


            switch (choiceMethod(new String[]{"Pizza  -  115:-", "Lasagna  -  135:-", "Burger   -  95:-", "Water / Soda  -  25:-", "Chips  -  35:-", "Cookies  -  35:-"}, input, true)) {
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

                case 0:
                    break;

            }

            if (Menus.questionAndInputString("Anything else?  Y/N? Choice", input).toLowerCase().contains("n")) {

                break;
            }

        }
        return tempOrder;
    }
    private static void setOrderToRoomNum(Scanner input, HashMap<String, Integer> tempOrder) {
        while (true) {
            int roomNum = checkIfRoomIsBooked();
            if (customers.stream().anyMatch(c -> (c.roomNumber == roomNum))) {
                customers.stream().filter(c -> (c.roomNumber == roomNum)).findAny().get().billingDetails.putAll(tempOrder);
                System.out.println("Order has been set to room #: " + roomNum);
                break;

            } else {
                System.out.println("Something went wrong, please try again.");

            }
        }
    }

    public static int chooseRoomType() {

        switch (Menus.choiceMethod(new String[]{"Luxury Single Room", "Luxury Double Room", "Deluxe Single Room", "Deluxe Double Room"}, input, false)) {

            case 1:
                return BookRoom(RoomType.Deluxe_Single_Room);

            case 2:
                return BookRoom(RoomType.Deluxe_Double_Room);

            case 3:
                return BookRoom(RoomType.Luxury_Single_Room);

            case 4:
                return BookRoom(RoomType.Luxury_Double_Room);
        }
        return 0;
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

    public static void inputCustomerDetails() {

        String tempName = Menus.questionAndInputString("Name", input);
        int tempPhone = Menus.questionAndInputInt("Phonenumber", input);
        int tempRoomNumber = 0;

        customers.add(new Customer(tempName, tempPhone, Menus.chooseRoomType()));
    }

    public static void changeCustomerDetailsAndRoomType() {
        customers.stream().forEach(System.out::println);

        switch (Menus.choiceMethod(new String[]{"Change customer details", "Change room"}, input, true)) {

            case 1:
                updateCustomerDetails(checkIfRoomIsBooked());
                break;
            case 2:
                updateRoomType(checkIfRoomIsBooked());

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

    public static int checkIfRoomIsBooked() {
        Customer temp;
        while (true) {
            displayRooms();
            int tempRoom = Menus.questionAndInputInt("Room number", input);
            if (customers.stream().anyMatch(c -> c.roomNumber == tempRoom)) {
                temp = customers.stream().filter(c -> c.roomNumber == tempRoom).findAny().get();

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
           ourRooms.stream().filter(hr -> (hr.roomNumber == c.roomNumber)).map(r->r.booked = true);

        }
    }

    static void primaryMenu() {
        
        while (runningMenu == true){
            switch (Menus.choiceMethod(user == User.Staff ? new String[]{"New customer", "Food menu", "Checkout", "Customer details"} : new String[]{"New customer", "Food menu", "Checkout"}, input, true)) {
                case 1:
                    Menus.inputCustomerDetails();

                    break;
                case 2:

                    Menus.foodMenu();

                    break;

                case 3:
                    checkOutCustomer();
                    break;

                case 4:
                    Menus.changeCustomerDetailsAndRoomType();
                    break;
                case 0:
                    System.out.println("Shutting down, have a nice day!");
                    
                    FileManagement.SaveCustomerData();
                    runningMenu = false;
                    
                    

            }
        }
    }
    

    private static void checkOutCustomer() {
        while (true) {

            int checkOutRoom = Menus.checkIfRoomIsBooked();
            Customer customer = null;
            if (customers.stream().anyMatch(c -> (c.roomNumber == checkOutRoom))) {
                customer = customers.stream().filter(c -> (c.roomNumber == checkOutRoom)).findAny().get();
                HotelRoom tempRoom = ourRooms.stream().filter(n -> (n.roomNumber == checkOutRoom && n.booked == true)).findAny().get();
                tempRoom.booked = false;
//räkna ihop total skuld - visa "kvitto"
                System.out.println("\nCheckout done, Bye " + customer.getCustomerName() + "!");
                customers.remove(customer);
                  FileManagement.SaveCustomerData();
                break;
            } else {
                System.out.println("Something went wrong. Please try again.");
            }
                

        }

    }
    public static void displayRooms(){
        customers.stream().forEach(c ->System.out.println("-" + " " + c.toString()));
    }

}
