/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myhotel;

import java.util.HashMap;
import java.util.Scanner;
import static myhotel.MyHotel.customers;

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
                if (choices[0].equals("New customer")) {
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
        HashMap<String, Integer> tempOrder = new HashMap<>();
        Scanner input = MyHotel.input;
        int tempRoomNum = 0;
        
        
        
        do {
            tempRoomNum = Menus.questionAndInputInt("Room number", input);
            
        } while (tempRoomNum <= 0 || tempRoomNum > MyHotel.numberOfRooms);
        
        int tempRoomNum2 = tempRoomNum;
        Customer cust = customers.stream().filter(c-> c.roomNumber == tempRoomNum2).findAny().get();
        
        
        
        

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
            }
            if (choiceMethod(new String[] {"Add more", "Done"}, input, false) == 2){
                break;
            }
                

        }
        

        customers.stream().filter(c -> (c.roomNumber == cust.roomNumber)).findAny().get().billingDetails.putAll(tempOrder); //

    }
}
