/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadbtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author admusr
 */
public class JavaDBTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/root","root","root123");
            Statement st = con.createStatement();
            
            st.executeUpdate("insert into person values('Lars',55)");
            st.executeUpdate("update person set Name='Olle' where name ='Leo'");
            st.executeUpdate("delete from person where age=35");

            ResultSet rs = st.executeQuery("select * from person");
            while(rs.next()){
                System.out.println(rs.getString("name") + " " + rs.getInt("age"));
            }
            con.close();
        }
        catch(Exception e){
            System.err.println("Could not connect because of " + e);
        }
        
    }
    
}
