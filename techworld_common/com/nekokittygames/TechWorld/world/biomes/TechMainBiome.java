package com.nekokittygames.TechWorld.world.biomes;


import net.minecraft.world.biome.BiomeGenBase;

public class TechMainBiome extends BiomeGenBase {

    public TechMainBiome(int par1) {
        super(par1);
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.setBiomeName("TechMain");
        
        this.waterColorMultiplier = 0xE42D17;
    }

}
