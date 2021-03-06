/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myhotel;

import java.util.HashMap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Customer implements Serializable {
    
    
   
    private int phoneNumber;
    private String customerName;
    public List<Order> billingDetails = new ArrayList<>();

    public List<Order> getBillingDetails() {
        return billingDetails;
    }

 
    @Override
    public String toString() {
        return "Name: " + customerName + ". Phone number: " + phoneNumber +"\nRoomNumber: " + roomNumber;
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
