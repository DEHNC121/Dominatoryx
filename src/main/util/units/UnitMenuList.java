package main.util.units;

import java.util.HashMap;
import java.util.Map;

public class UnitMenuList {
    static public Map<Integer,Unit> list;
    static{
        list=new HashMap<>();
        Unit unit=new Kid(null,null);
        list.put(unit.getID(),unit);
    }
}
