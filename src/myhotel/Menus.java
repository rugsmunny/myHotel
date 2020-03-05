/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myhotel;

import java.util.Scanner;

/**
 *
 * @author admusr
 */
public abstract class Menus {
    
    public static String ListChoices(String[] choices){
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
                if (choices[0].equals("Add employee")) {
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
                    if(choice == 0 && back == true){
                        return choice;
                    }
                    if (choice == 0 && back == false){
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
 
}
