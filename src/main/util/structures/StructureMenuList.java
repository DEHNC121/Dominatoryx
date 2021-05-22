package main.util.structures;

import java.util.*;

public class StructureMenuList {
    static public Map<Integer, Structure> list;
    static{
        list=new HashMap<>();
        Structure structure = new House(null);
        list.put(structure.getID(), structure);
    }
}