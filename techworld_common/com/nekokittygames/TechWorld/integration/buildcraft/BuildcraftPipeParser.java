package com.nekokittygames.TechWorld.integration.buildcraft;

import java.lang.reflect.Field;

import com.nekokittygames.TechWorld.integration.BlockManager;
import com.nekokittygames.TechWorld.tileEntities.SchematicTileEntity;



import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import buildcraft.BuildCraftTransport;
import buildcraft.transport.BlockGenericPipe;
import buildcraft.transport.TileGenericPipe;

public class BuildcraftPipeParser {

    
    
    public static int GetPipeID(String name)
    {
        try
        {
            Field field=BuildCraftTransport.class.getDeclaredField(name);
            Item item=(Item)field.get(null);
            return item.itemID;
        }
        catch(Exception e)
        {
            return 0;
        }
    }
    
    public static void BuildCraftPlace(SchematicTileEntity ent,World world,int x,int y,int z)
    {
        if(!ent.getBlockName().startsWith("B-"))
        {
            return;
        }
        String[] tokens=ent.getBlockName().split("-");
        if(tokens[1].equalsIgnoreCase("pipe"))
        {
            int blockID=BlockManager.GetId("BPipe");
            TileGenericPipe tile=(TileGenericPipe) TileEntity.createAndLoadEntity(ent.getTileEntity());
            tile.pipe=BlockGenericPipe.createPipe(GetPipeID(tokens[2]));
            world.setBlock(ent.xCoord+x, ent.yCoord+y, ent.zCoord+z,blockID ,0 , 2);
            world.setBlockTileEntity(x+ent.xCoord, y+ent.yCoord, z+ent.zCoord, tile);
            
        }
    }
}
