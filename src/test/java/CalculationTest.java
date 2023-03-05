import org.junit.Test;
import org.example.Calculation;

import static org.junit.Assert.assertEquals;

public class CalculationTest {
    @Test
    public void testFindMax(){
        assertEquals(4,Calculation.findMax(new int[]{1,3,4,2}));
        assertEquals(0,Calculation.findMax(new int[]{-12,-1,-3,-4,-2}));
    }
}
