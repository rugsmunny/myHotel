/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myhotel;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author karim
 */
public class Order implements Serializable{
    Date date;
    int cost;
    String productName;

    @Override
    public String toString() {
        return "Order{" + "date=" + date + ", cost=" + cost + ", productName=" + productName + '}';
    }
    
    public Date getDate() {
        return date;
    }

   

    public int getCost() {
        return cost;
    }

    

    public String getProductName() {
        return productName;
    }

    

    public Order(Date date, int cost, String productName) {
        this.date = date;
        this.cost = cost;
        this.productName = productName;
    }
    
}
