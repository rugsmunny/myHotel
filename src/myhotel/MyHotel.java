/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myhotel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author karim
 */
public class MyHotel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int numberOfRooms = 40; 
       List<HotelRoom> ourRooms = new ArrayList<>();
       List<Customer> customers = new ArrayList<>();
       
        for (int i = 0; i < numberOfRooms; i++) {
            if(i<(numberOfRooms*.25)){
                ourRooms.add(new HotelRoom(i+1, RoomType.Luxury_Double_Room));
            }
            else if(i<(numberOfRooms*.5)){
                ourRooms.add(new HotelRoom(i+1, RoomType.Luxury_Single_Room));
            }
            else if(i<(numberOfRooms*.75)){
                ourRooms.add(new HotelRoom(i+1, RoomType.Deluxe_Single_Room));
            }
            else{
                ourRooms.add(new HotelRoom(i+1, RoomType.Deluxe_Double_Room));
            }
        }
        
       
        ourRooms.stream().forEach(System.out::println);
       
        
        
        
        
        
        
    }
    
}
