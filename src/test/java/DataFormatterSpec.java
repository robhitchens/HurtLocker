import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by roberthitchens3 on 2/22/16.
 */
public class DataFormatterSpec {
    ArrayList<String> data = new ArrayList(Arrays.asList(new String[] {"naMe:Milk;price:3.23;type:Food;expiration:1/25/2016","naME:BreaD;price:1.23;type:Food;expiration:1/02/2016"}));
    DataFormatter dataFormatter = new DataFormatter(data, 0);
    @Test
    public void findItemsTest(){
        String expected = "Milk";
        String actual = dataFormatter.findItems(data.get(0));
        assertEquals("", expected, actual);
    }
    @Test
    public void findPriceTest(){
        String expected = "3.23";
        String actual= dataFormatter.findPrice(data.get(0));
        assertEquals("",expected, actual);
    }
    @Test
    public void modifyStringTest(){
        String expected  ="Cookies";
        String actual = dataFormatter.modifyString("CO0kies");
        assertEquals(expected, actual);
    }
}
