/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myhotel;

enum RoomType {

    Luxury_Double_Room, Deluxe_Double_Room, Luxury_Single_Room, Deluxe_Single_Room;
}

public class HotelRoom {

    int roomNumber;
    RoomType roomType;
    boolean booked;
    Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public String toString() {
        return "HotelRoom number: " + roomNumber + " RoomType: " + roomType;
    }

    public HotelRoom(int roomNumber, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
    }
}
