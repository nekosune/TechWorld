package com.nekokittygames.TechWorld.schematics;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.nekokittygames.TechWorld.TechWorld;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Schematic {
    private short width;
    private short height;
    private short length;
    public enum Materials
    {
        Classic,
        Alpha,
    };
    private Materials materials;
    private byte[] rawBlocks;
    private byte[] blockData;
    private byte[] addBlockId;
    short[] blocks;
    public byte[] getRawBlocks() {
        return rawBlocks;
    }
    public void setRawBlocks(byte[] rawBlocks) {
        this.rawBlocks = rawBlocks;
    }
    public byte[] getAddBlockId() {
        return addBlockId;
    }
    public void setAddBlockId(byte[] addBlockId) {
        this.addBlockId = addBlockId;
    }
    public void setBlocks(short[] blocks) {
        this.blocks = blocks;
    }


    private List<Entity> entities;
    private List<TileEntity> tileEntities;
    /**
     * @return the width
     */
    public short getWidth() {
        return width;
    }
    /**
     * @param width the width to set
     */
    public void setWidth(short width) {
        this.width = width;
    }
    /**
     * @return the height
     */
    public short getHeight() {
        return height;
    }
    /**
     * @param height the height to set
     */
    public void setHeight(short height) {
        this.height = height;
    }
    /**
     * @return the length
     */
    public short getLength() {
        return length;
    }
    /**
     * @param length the length to set
     */
    public void setLength(short length) {
        this.length = length;
    }
    /**
     * @return the materials
     */
    public Materials getMaterials() {
        return materials;
    }
    /**
     * @param materials the materials to set
     */
    public void setMaterials(Materials materials) {
        this.materials = materials;
    }
    /**
     * @return the blockData
     */
    public byte[] getBlockData() {
        return blockData;
    }
    /**
     * @param blockData the blockData to set
     */
    public void setBlockData(byte[] blockData) {
        this.blockData = blockData;
    }
    /**
     * @return the entities
     */
    public List<Entity> getEntities() {
        return entities;
    }
    /**
     * @param entities the entities to set
     */
    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }
    /**
     * @return the tileEntities
     */
    public List<TileEntity> getTileEntities() {
        return tileEntities;
    }
    /**
     * @param tileEntities the tileEntities to set
     */
    public void setTileEntities(List<TileEntity> tileEntities) {
        this.tileEntities = tileEntities;
    }
    
    public Schematic()
    {
        this.entities=new ArrayList<Entity>();
        tileEntities=new ArrayList<TileEntity>();
    }
    
    
    public static Schematic loadSchematic(NBTTagCompound nbtSchematic,World world)
    {
        Schematic schematic=new Schematic();
        schematic.setHeight(nbtSchematic.getShort("Height"));
        schematic.setWidth(nbtSchematic.getShort("Width"));
        schematic.setLength(nbtSchematic.getShort("Length"));
        schematic.setMaterials(Materials.valueOf(nbtSchematic.getString("Materials")));
        schematic.setRawBlocks(nbtSchematic.getByteArray("Blocks"));
        if(nbtSchematic.hasKey("AddBlocks"))
        {
            schematic.setAddBlockId(nbtSchematic.getByteArray("AddBlocks"));
        }
        schematic.blocks=new short[schematic.getRawBlocks().length];
        if(schematic.getAddBlockId()==null)
        {
            schematic.setAddBlockId(new byte[0]);
        }
//        byte[] adds=schematic.getAddBlockId();
//        for(int i=0;i<schematic.getRawBlocks().length;i++)
//        {
//            if(i==65)
//                TechWorld.logging.info("Debugger here!");
//            if((i>>1)>=adds.length)
//            {
//                schematic.blocks[i]=(short)(schematic.rawBlocks[i]&0xFF);
//            }
//            else
//            {
//                if((i &1)==0)
//                {
//                    int index=i>>1;
//                    byte step1=adds[index];
//                    int step2=step1&0x0F;
//                    int step3=step2 << 8;
//                    int step4=schematic.rawBlocks[i] & 0xFF;
//                    short id=(short) ((step3)+(step4));
//                    schematic.blocks[i]= id;
//                }
//                else
//                {
//                    int index=i>>1;
//                    byte step1=adds[index];
//                    int step2=step1&0xF0;
//                    int step3=step2 << 4;
//                    int step4=schematic.rawBlocks[i] & 0xFF;
//                    short id=(short) ((step3)+(step4));
//                    schematic.blocks[i]= id;
//                }
//            }
//        }
//        if(schematic.getAddBlockId()!=null)
//        {
//            int size=schematic.height*schematic.length*schematic.width;
//            byte[] add=new byte[size+(size & 1)];
//            for(int i=0;i<add.length;i++)
//            {
//                add[i]=0;
//            }
//            int counter=0;
//            for(int i=0;i<add.length;i+=2)
//            {
//                add[i]=schematic.getAddBlockId()[counter++];
//            }
//            counter=0;
//            for(int i=1;i<add.length;i+=2)
//            {
//                add[i]=(byte) (add[i-1]&0xf);
//            }
//            for(int i=0;i<add.length;i+=2)
//            {
//                add[i]>>=4;
//            }
//            for(int i=0;i<add.length;i++)
//            {
//                add[i]<<=8;
//            }
//            for(int i=0;i<schematic.getRawBlocks().length;i++)
//            {
//                schematic.blocks[i]=(short)(schematic.rawBlocks[i] | add[i]);
//            }
//        }
        boolean extra = nbtSchematic.hasKey("Add") || nbtSchematic.hasKey("AddBlocks");
        byte extraBlocks[] = null;
        byte extraBlocksNibble[] = null;
        if (nbtSchematic.hasKey("AddBlocks")) {
            extraBlocksNibble = nbtSchematic.getByteArray("AddBlocks");
            extraBlocks = new byte[extraBlocksNibble.length * 2];
            for (int i = 0; i < extraBlocksNibble.length; i++) {
                extraBlocks[i * 2 + 0] = (byte) ((extraBlocksNibble[i] >> 4) & 0xF);
                extraBlocks[i * 2 + 1] = (byte) (extraBlocksNibble[i] & 0xF);
            }
        } else if (nbtSchematic.hasKey("Add")) {
            extraBlocks = nbtSchematic.getByteArray("Add");
        }
        for(int i=0;i<schematic.rawBlocks.length;i++)
        {
            schematic.blocks[i]=(short) (schematic.rawBlocks[i]& 0xFF);
            if(extra)
            {
                schematic.blocks[i]|=((extraBlocks[i]) & 0xFF) << 8;
            }
        }
        schematic.setBlockData(nbtSchematic.getByteArray("Data"));
        List<Entity> ents=new LinkedList<Entity>();
        NBTTagList entities=nbtSchematic.getTagList("Entities");
        for(int i=0;i<entities.tagCount();i++)
        {
            NBTTagCompound entity=(NBTTagCompound) entities.tagAt(i);
            Entity entEntity=EntityList.createEntityFromNBT(entity, world);
            ents.add(entEntity);
        }
        schematic.setEntities(ents);
        
        List<TileEntity> tiles=new LinkedList<TileEntity>();
        NBTTagList tileEntities=nbtSchematic.getTagList("TileEntities");
        for(int i=0;i<tileEntities.tagCount();i++)
        {
            NBTTagCompound tile=(NBTTagCompound) tileEntities.tagAt(i);
            TileEntity tileEnt=TileEntity.createAndLoadEntity(tile);
            tiles.add(tileEnt);
        }
        schematic.setTileEntities(tiles);
        return schematic;
    }
    public short[] getBlocks() {
        return blocks;
    }
}
