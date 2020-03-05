/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myhotel;


public class Customer {
    
    int customerBill;
    String billingDetails;
    int phoneNumber;
    String customerName;
    int roomNumber;
    
    public String getBillingDetails() {
        return billingDetails;
    }

    public void setBillingDetails(String billingDetails) {
        this.billingDetails = billingDetails;
    }

    public int getCustomerBill() {
        return customerBill;
    }

    public void setCustomerBill(int customerBill) {
        this.customerBill = customerBill;
    }

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

    public Customer(int customerBill, int phoneNumber, String customerName, int roomNumber) {
        this.customerBill = customerBill;
        this.phoneNumber = phoneNumber;
        this.customerName = customerName;
        this.roomNumber = roomNumber;
    }
}
