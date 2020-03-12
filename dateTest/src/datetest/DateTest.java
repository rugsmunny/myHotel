/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datetest;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
/**
 *
 * @author admusr
 */
public class DateTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //LocalDate now = LocalDate.now();
        LocalDate now = LocalDate.of(2020,12,24);
        LocalDate julafton = LocalDate.of(now.getYear(),12,24);
        if (now.get(ChronoField.DAY_OF_YEAR) > julafton.get(ChronoField.DAY_OF_YEAR)){
            julafton = julafton.plusYears(1);
        }
        
        System.out.println(ChronoUnit.DAYS.between(now, julafton));
        
    }
    
}
