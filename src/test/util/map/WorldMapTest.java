package test.util.map;

import main.GamePanel;
import main.states.GameMapSize;
import main.states.PlayState;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import main.util.map.WorldMap;

public class WorldMapTest {
    int width=10*(3*40+1);//121 parts in row
    int height=20*(2*20+1);//41 parts in column
    GamePanel gamePanel=new GamePanel(width,height);
    WorldMap map=new WorldMap(GameMapSize.SMALL);
    @Test
    public void testGetHexagonPart(){
        assertEquals(121,WorldMap.hexagonPartsInRow);
        assertEquals(41,WorldMap.hexagonPartsInColumn);
        assertEquals( WorldMap.Parts[0],WorldMap.getHexagonPart(0,0));
        assertEquals( WorldMap.Parts[1],WorldMap.getHexagonPart(11,0));
        assertEquals( WorldMap.Parts[121],WorldMap.getHexagonPart(1,21));
    }
    public void testGetHexagon(){
        assertNull(WorldMap.getHexagon(0, 0));
        assertEquals(WorldMap.hexagonMap[0],WorldMap.getHexagon(11,0));
        assertEquals(WorldMap.hexagonMap[0],WorldMap.getHexagon(9,10));
        assertEquals(WorldMap.hexagonMap[0],WorldMap.getHexagon(5,19));
    }
}
