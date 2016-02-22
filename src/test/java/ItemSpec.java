/**
 * Created by roberthitchens3 on 2/22/16.
 */
import org.junit.Test;
import static org.junit.Assert.*;

public class ItemSpec {

    Item item = new Item("Milk", "Food", "1/28/2016", "1.25");
    @Test
    public void testGetName(){
        String expected = "Milk";
        String actual = item.getName();
        assertEquals("", expected, actual);
    }
    @Test
    public void testGetType(){
        String expected = "Food";
        String actual = item.getType();
        assertEquals("", expected, actual);
    }
    @Test
    public void testGetExpiration(){
        String expected = "1/28/2016";
        String actual = item.getExpiration();
        assertEquals("", expected, actual);
    }
    @Test
    public void testGetPrice(){
        String expected = "1.25";
        String actual = item.getPrice();
        assertEquals("",expected, actual);
    }
    @Test
    public void testGetTimesSeen(){
        int expected = 1;
        int actual = item.getTimesSeen();
        assertEquals("", expected, actual);
    }
}
