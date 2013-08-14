package com.nekokittygames.TechWorld.schematics;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
    private byte[] blocks;
    private byte[] blockData;
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
     * @return the blocks
     */
    public byte[] getBlocks() {
        return blocks;
    }
    /**
     * @param blocks the blocks to set
     */
    public void setBlocks(byte[] blocks) {
        this.blocks = blocks;
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
        schematic.setBlocks(nbtSchematic.getByteArray("Blocks"));
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
}