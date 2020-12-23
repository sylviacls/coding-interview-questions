import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

public class RomansToInteger {
    
   public static int romansToInteger(String roman) {
      HashMap<Character, Integer> map = new HashMap<Character, Integer>();
      map.put('I', 1);
      map.put('V', 5);
      map.put('X', 10);
      map.put('L', 50);
      map.put('C', 100);
      map.put('D', 500);
      map.put('M', 1000);
      
      roman = roman.toUpperCase();
      int count = 0; //XXI //IV 21 - 4
      int i = 0;
      while (i < roman.length()) {
         char current = roman.charAt(i);
         //check if there is a next char
         if(i+1 < roman.length()) {
            char next = roman.charAt(i+1);
            //adding
            if(map.get(current).intValue() >= map.get(next).intValue()){
               count += map.get(current).intValue();
               i++;
            } else { //subtraction
               int sub = map.get(next).intValue() - map.get(current).intValue();
               count += sub;
               i = i+2;
            }
         } else {
            count += map.get(current).intValue();
            i++;
         }


      }
      return count;
   } 

   @Test
   public void validate() {
      Assert.assertEquals(34, RomansToInteger.romansToInteger("XXXIV"));
      Assert.assertEquals(4, RomansToInteger.romansToInteger("IV"));
      Assert.assertEquals(3, RomansToInteger.romansToInteger("III"));
      Assert.assertEquals(58, RomansToInteger.romansToInteger("LVIII"));
   }
}