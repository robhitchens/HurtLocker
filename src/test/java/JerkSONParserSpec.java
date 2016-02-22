import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by roberthitchens3 on 2/22/16.
 */
public class JerkSONParserSpec {
    JerkSONParser jerk = new JerkSONParser("naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:;expiration:2/25/2016##");

    @Test
    public void dataMatcherAndWorkingDataTest(){
        String[] expected = {"naMe:Milk;price:3.23;type:Food;expiration:1/25/2016","naME:BreaD;price:1.23;type:Food;expiration:1/02/2016"};
        String[] actual = new String[expected.length];
        actual = jerk.getWorkingData().toArray(actual);
        assertTrue("expected and actual should be equal", Arrays.equals(expected, actual));
    }
    @Test
    public void errorCountTest(){
        int expected = 1;
        int actual = jerk.getErrorCount();
        assertEquals(expected, actual);
    }
}
