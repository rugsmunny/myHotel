/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myhotel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import static myhotel.MyHotel.customers;

/**
 *
 * @author admusr
 */
public class FileManagement {
    public static void SaveCustomerData(){
        
        try {
            
            FileOutputStream fileOut = new FileOutputStream("customersData.txt");
            ObjectOutputStream listOut = new ObjectOutputStream(fileOut);
            listOut.writeObject(customers);
            listOut.close();
            System.out.println("\nThe Object  was succesfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    
    public static List<Customer> GetCustomerData() {
    
        try {

            FileInputStream fileIn = new FileInputStream("customersData.txt");
            ObjectInputStream listIn = new ObjectInputStream(fileIn);

            List<Customer> list = (List<Customer>)listIn.readObject();

            System.out.println("The Object has been read from the file");
            listIn.close();
            return list;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
            
    }
}

