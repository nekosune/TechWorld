package com.nekokittygames.TechWorld.tileEntities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class SchematicTileEntity extends TileEntity {

    private String blockName;
    private String blockData;
    private NBTTagCompound tileEntity;
    @Override
    public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
        super.readFromNBT(par1nbtTagCompound);
        blockName=par1nbtTagCompound.getString("blockName");
        blockData=par1nbtTagCompound.getString("blockData");
        tileEntity=par1nbtTagCompound.getCompoundTag("tileEntity");
    }

    @Override
    public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
        super.writeToNBT(par1nbtTagCompound);
        par1nbtTagCompound.setString("blockName", blockName);
        par1nbtTagCompound.setString("blockData", blockData);
        par1nbtTagCompound.setCompoundTag("tileEntity", tileEntity);
    }

    /**
     * @return the blockName
     */
    public String getBlockName() {
        return blockName;
    }

    /**
     * @param blockName the blockName to set
     */
    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    /**
     * @return the blockData
     */
    public String getBlockData() {
        return blockData;
    }

    /**
     * @param blockData the blockData to set
     */
    public void setBlockData(String blockData) {
        this.blockData = blockData;
    }

    /**
     * @return the tileEntity
     */
    public NBTTagCompound getTileEntity() {
        return tileEntity;
    }

    /**
     * @param tileEntity the tileEntity to set
     */
    public void setTileEntity(NBTTagCompound tileEntity) {
        this.tileEntity = tileEntity;
    }

}
