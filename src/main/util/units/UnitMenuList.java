package main.util.units;

import java.util.*;

public class UnitMenuList {
    static public Map<Integer,Unit> list;
    static{
        list=new HashMap<>();
        Unit unit=new Kid(null,null);
        Man adult = new Man(null, null);
        list.put(unit.getID(),unit);
        list.put(adult.getID(), adult);
    }
}
