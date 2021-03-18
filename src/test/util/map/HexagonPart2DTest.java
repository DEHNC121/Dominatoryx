package test.util.map;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import main.util.map.HexagonPart2D;

public class HexagonPart2DTest {
    HexagonPart2D part=new HexagonPart2D(0,0,20,20);
    @Test
    public void testWhichPart() {

        assertEquals(HexagonPart2D.Parts.UPPER,part.whichPart(10,0));
        assertEquals(HexagonPart2D.Parts.LOWER,part.whichPart(10,20));
        assertEquals(HexagonPart2D.Parts.LEFT,part.whichPart(0,10));
        assertEquals(HexagonPart2D.Parts.RIGHT,part.whichPart(20,10));
        assertEquals(HexagonPart2D.Parts.OUT,part.whichPart(30,0));
    }


}
