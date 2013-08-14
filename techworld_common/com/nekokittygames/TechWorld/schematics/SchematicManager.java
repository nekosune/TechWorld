package com.nekokittygames.TechWorld.schematics;

import java.io.IOException;
import java.io.InputStream;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.nekokittygames.TechWorld.TechWorld;

public class SchematicManager {
    
    
    public static Schematic loadSchematic(String file,World world)
    {
        Schematic schematic=null;
        InputStream is=SchematicManager.class.getResourceAsStream("/assets/"+TechWorld.ID.toLowerCase()+"/schematics/"+file+".schematic");
        try {
            NBTTagCompound comp=CompressedStreamTools.readCompressed(is);
            schematic=Schematic.loadSchematic(comp, world);
        } catch (IOException e) {
            TechWorld.logging.severe(e.toString());
        }
        return schematic;
    }
    
    public static void PlaceSchematic(Schematic schematic,World world,int x,int y,int z)
    {
        
        for(int i=0;i<schematic.getHeight();i++)
        {
            for(int j=0;j<schematic.getLength();j++)
            {
                for(int k=0;k<schematic.getWidth();k++)
                {
                    try
                    {
                        int index = i * schematic.getWidth() * schematic.getLength() + j * schematic.getWidth() + k;
                        short blockId=schematic.getBlocks()[index];
                        short blockMeta=schematic.getBlockData()[index];
                        if(blockId<0)
                            TechWorld.logging.severe("Got an error!");
                        world.setBlock(k+x, i+y, j+z,blockId , blockMeta, 2);
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                        TechWorld.logging.severe(String.format("Failed placing block:  rx=%d ry=%d rz=%d x=%d y=%d z=%d with error: %s",k,i,j,k+x, i+y, j+z,e.toString()));
                    }
                }
            }
        }
        TechWorld.logging.info(String.format("This array has %d indices", schematic.getBlocks().length));
        for(TileEntity entity:schematic.getTileEntities())
        {
            world.setBlockTileEntity(entity.xCoord+x, entity.yCoord+y, entity.zCoord+z, entity);
        }
    }

}
