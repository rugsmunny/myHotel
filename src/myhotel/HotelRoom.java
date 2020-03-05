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

    @Override
    public String toString() {
        return "HotelRoom number: " + roomNumber + " RoomType: " + roomType;
    }

 

    public int getRoomNumber() {
        return roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public HotelRoom(int roomNumber, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
    }
}
