package main.util.units;

import java.util.*;

public class UnitMenuList {
    static public Map<Integer,Unit> list;
    static{
        list=new HashMap<>();
        attach(new Recruit(null,null));
        attach(new Veteran(null,null));
        attach(new Tank(null,null));
        attach(new TankMk2(null,null));
    }
    static void attach(Unit unit){list.put(unit.getID(),unit);}
}
