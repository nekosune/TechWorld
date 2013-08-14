package com.nekokittygames.TechWorld.world;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.DimensionManager;

import com.nekokittygames.TechWorld.Configs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldProviderTechDimension extends WorldProvider {

    public static WorldProvider getProviderForDimension(int id) {
        return DimensionManager.createProviderFor(Configs.TechDimensionID);
    }

    /** Can player re-spawn here **/
    @Override
    public boolean canRespawnHere() {
        return false;
    }

    /** What chunk provider to use **/
    @Override
    public IChunkProvider createChunkGenerator() {
        return new ChunkProviderTechDimension(worldObj, worldObj.getSeed());
    }

    @Override
    public String getDimensionName() {
        return "TechDimension";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean getWorldHasVoidParticles() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public double getVoidFogYFactor() {
        return 1.0D;
    }

    /** Dimension Movement speed **/
    @Override
    public double getMovementFactor() {
        return 10.0;
    }

    /** Determines the dimension the player will be respawned in **/
    @Override
    public int getRespawnDimension(EntityPlayerMP player) {
        return 0;
    }

    /** Welcome message **/
    @Override
    public String getWelcomeMessage() {
        return "Entering the Tutorial Dimension";
    }

    @Override
    public void registerWorldChunkManager() {
        /** tells Minecraft to use our new WorldChunkManager **/
        worldChunkMgr = new WorldChunkMangerTechDimension(worldObj.getSeed(),
                terrainType);
        hasNoSky = false;
    }

    /** Set user message **/
    // not sure if this works any more ?
    protected synchronized String setUserMessage(String par1Str) {
        return "Building Tech Dimension";
    }

}
