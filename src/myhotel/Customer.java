/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myhotel;

import java.util.HashMap;

import java.io.Serializable;

public class Customer implements Serializable {
    
    
   
    private int phoneNumber;
    private String customerName;
    public HashMap<String, Integer> billingDetails = new HashMap<>();

    public HashMap<String, Integer> getBillingDetails() {
        return billingDetails;
    }

 
    @Override
    public String toString() {
        return "Name: " + customerName + ". Phone number: " + phoneNumber +"\nRoomNumber: " + roomNumber + ". Accumulated bill: " + billingDetails+"\n";
    }
    int roomNumber;
    

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Customer(String customerName,int phoneNumber,  int roomNumber) {
      
        this.phoneNumber = phoneNumber;
        this.customerName = customerName;
        this.roomNumber = roomNumber;
    }
}
