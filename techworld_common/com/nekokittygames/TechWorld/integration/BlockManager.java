package com.nekokittygames.TechWorld.integration;

import java.util.HashMap;
import java.util.Map;

public class BlockManager {

    
    public static Map<String, Integer> NameMappings=new HashMap<String, Integer>();
    
    
    public static void AddMapping(String name,int id)
    {
        NameMappings.put(name.toLowerCase(), id);
    }
    
    public static int GetId(String name)
    {
        return NameMappings.get(name.toLowerCase());
    }
    
    static
    {
        AddMapping("TNT", 46);
        AddMapping("BPipe",1513);
    }
}
