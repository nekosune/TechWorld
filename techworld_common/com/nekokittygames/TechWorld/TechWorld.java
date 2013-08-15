package com.nekokittygames.TechWorld;

import java.util.logging.Logger;

import com.nekokittygames.TechWorld.blocks.BlockTechFire;
import com.nekokittygames.TechWorld.blocks.BlockTechPortal;
import com.nekokittygames.TechWorld.items.ItemLighter;
import com.nekokittygames.TechWorld.items.ItemPortalPlacer;
import com.nekokittygames.TechWorld.tileEntities.SchematicTileEntity;
import com.nekokittygames.TechWorld.world.WorldProviderTechDimension;
import com.nekokittygames.TechWorld.world.biomes.TechMainBiome;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = TechWorld.ID, modLanguage = "java")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class TechWorld {
    public static final String ID      = "TechWorld";

    public static  BlockTechPortal TechPortal;
    public static Item portalSpawner;
    public static Item portalLighter;
    public static TechMainBiome mainBiome;
    public static BlockTechFire fire;
    public static Material fireMat=new Material(MapColor.tntColor).setReplaceable();
    @Instance(ID)
    public static TechWorld    Instance;

    @SidedProxy(clientSide = "com.nekokittygames.TechWorld.client.ClientProxy", serverSide = "com.nekokittygames.TechWorld.ServerProxy")
    public static ServerProxy  proxy;

    public static Logger       logging;

    @EventHandler
    public void Init(FMLInitializationEvent event) {
        GameRegistry.registerBlock(fire, "TechFire");
        LanguageRegistry.addName(fire, "Tech Fire");
        GameRegistry.registerBlock(TechPortal,"TechPortal");
        LanguageRegistry.addName(TechPortal, "Tech Portal");
        GameRegistry.registerItem(portalSpawner, "PortalSpawner");
        LanguageRegistry.addName(portalSpawner, "Tech Portal Placer");
        GameRegistry.registerItem(portalLighter, "PortalLighter");
        LanguageRegistry.addName(portalLighter, "Tech Portal Lighter");
        GameRegistry.addShapedRecipe(new ItemStack(portalLighter),"rrr","rfr","rrr",'r',new ItemStack(Item.redstone),'f',new ItemStack(Item.flintAndSteel));
        DimensionManager.registerProviderType(Configs.TechDimensionID, WorldProviderTechDimension.class, true);
        DimensionManager.registerDimension(Configs.TechDimensionID, Configs.TechDimensionID);
        
        GameRegistry.registerTileEntity(SchematicTileEntity.class, "schematicEntity");
    }

    @EventHandler
    public void PostInit(FMLPostInitializationEvent event) {
    }

    @EventHandler
    public void PreInit(FMLPreInitializationEvent event) {
        logging = Logger.getLogger(ID);
        logging.setParent(FMLLog.getLogger());

        final Configuration config = new Configuration(
                event.getSuggestedConfigurationFile());
        Configs.load(config);
        fire=(BlockTechFire) new BlockTechFire(Configs.TechFireID).setUnlocalizedName("TechFire").setLightValue(1.0F);
        TechPortal=new BlockTechPortal(Configs.TechPortalID);
        portalSpawner=new ItemPortalPlacer(Configs.TechPortalPlacerID);
        portalLighter=new ItemLighter(Configs.TechFlintID);
        mainBiome=new TechMainBiome(Configs.MainBiomeID);
        
    }
    /**
     * Gets the location of textures for this mod
     * @param texture texture to get in usual format
     * @return full location with mod ID
     */
    public static String GetTextureLoc(String texture)
    {
        return TechWorld.ID+":"+texture;
    }
}
