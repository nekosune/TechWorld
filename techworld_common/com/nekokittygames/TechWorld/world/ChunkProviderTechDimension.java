package com.nekokittygames.TechWorld.world;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraft.world.gen.FlatGeneratorInfo;
import net.minecraft.world.gen.FlatLayerInfo;

public class ChunkProviderTechDimension implements IChunkProvider {

    private World worldObj;
    String gen="2;7,2x3;2;1";
    private final FlatGeneratorInfo flat=FlatGeneratorInfo.createFlatGeneratorFromString(gen);
    private final byte[] field_82700_c = new byte[256];
    private final byte[] field_82698_d = new byte[256];
    public ChunkProviderTechDimension(World par1World, long par2)
    {
        worldObj=par1World;
        new Random(par2);
        
        
        @SuppressWarnings("unchecked")
        Iterator<FlatLayerInfo> iterator = this.flat.getFlatLayers().iterator();

        while (iterator.hasNext())
        {
            FlatLayerInfo flatlayerinfo = iterator.next();

            for (int j = flatlayerinfo.getMinY(); j < flatlayerinfo.getMinY() + flatlayerinfo.getLayerCount(); ++j)
            {
                this.field_82700_c[j] = (byte)(flatlayerinfo.getFillBlock() & 255);
                this.field_82698_d[j] = (byte)flatlayerinfo.getFillBlockMeta();
            }
        }
    }
    @Override
    public boolean chunkExists(int i, int j) {
        return true;
    }

    @Override
    public Chunk provideChunk(int i, int j) {
        Chunk chunk = new Chunk(this.worldObj, i, j);
        for (int k = 0; k < this.field_82700_c.length; ++k)
        {
            int l = k >> 4;
            ExtendedBlockStorage extendedblockstorage = chunk.getBlockStorageArray()[l];

            if (extendedblockstorage == null)
            {
                extendedblockstorage = new ExtendedBlockStorage(k, !this.worldObj.provider.hasNoSky);
                chunk.getBlockStorageArray()[l] = extendedblockstorage;
            }

            for (int i1 = 0; i1 < 16; ++i1)
            {
                for (int j1 = 0; j1 < 16; ++j1)
                {
                    extendedblockstorage.setExtBlockID(i1, k & 15, j1, this.field_82700_c[k] & 255);
                    extendedblockstorage.setExtBlockMetadata(i1, k & 15, j1, this.field_82698_d[k]);
                }
            }
        }

        chunk.generateSkylightMap();
        BiomeGenBase[] abiomegenbase = this.worldObj.getWorldChunkManager().loadBlockGeneratorData((BiomeGenBase[])null, i * 16, j * 16, 16, 16);
        byte[] abyte = chunk.getBiomeArray();

        for (int k1 = 0; k1 < abyte.length; ++k1)
        {
            abyte[k1] = (byte)abiomegenbase[k1].biomeID;
        }

        chunk.generateSkylightMap();
        return chunk;
    }

    @Override
    public Chunk loadChunk(int i, int j) {
        return this.provideChunk(i, j);
    }

    @Override
    public void populate(IChunkProvider ichunkprovider, int i, int j) {


    }

    @Override
    public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate) {
        return true;
    }

    @Override
    public boolean unloadQueuedChunks() {
        return false;
    }

    @Override
    public boolean canSave() {
        return true;
    }

    @Override
    public String makeString() {
        return "TechDimensionGenerator";
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List getPossibleCreatures(EnumCreatureType enumcreaturetype, int i,
            int j, int k) {
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(i, j);
        return biomegenbase == null ? null : biomegenbase.getSpawnableList(enumcreaturetype);
    }

    @Override
    public ChunkPosition findClosestStructure(World world, String s, int i,
            int j, int k) {
        return null;
    }

    @Override
    public int getLoadedChunkCount() {
        return 0;
    }

    @Override
    public void recreateStructures(int i, int j) {

    }

    @Override
    public void func_104112_b() {

    }

}
