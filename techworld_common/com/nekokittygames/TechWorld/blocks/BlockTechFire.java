package com.nekokittygames.TechWorld.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import com.nekokittygames.TechWorld.TechWorld;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTechFire extends BlockFire {
    @SideOnly(Side.CLIENT)
    private Icon[] iconArray;

    public BlockTechFire(int par1) {
        super(par1);
        setTickRandomly(true);
        setCreativeTab(CreativeTabs.tabMaterials);
    }

    public boolean canNeighborBurn(World par1World, int par2, int par3, int par4) {
        return canBlockCatchFire(par1World, par2 + 1, par3, par4,
                ForgeDirection.WEST)
                || canBlockCatchFire(par1World, par2 - 1, par3, par4,
                        ForgeDirection.EAST)
                || canBlockCatchFire(par1World, par2, par3 - 1, par4,
                        ForgeDirection.UP)
                || canBlockCatchFire(par1World, par2, par3 + 1, par4,
                        ForgeDirection.DOWN)
                || canBlockCatchFire(par1World, par2, par3, par4 - 1,
                        ForgeDirection.SOUTH)
                || canBlockCatchFire(par1World, par2, par3, par4 + 1,
                        ForgeDirection.NORTH);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon func_94438_c(int par1) {
        return iconArray[par1];
    }

    @SideOnly(Side.CLIENT)
    public Icon getBlockTextureFromSideAndMetadata(int par1, int par2) {
        return iconArray[0];
    }

    @Override
    public Icon getIcon(int par1, int par2) {
        return blockIcon;
    }

    @Override
    public boolean isBlockBurning(World world, int x, int y, int z) {
        return true;
    }

    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4) {
        /** Change these to your portal frame and Portal block **/
        if (par1World.getBlockId(par2, par3 - 1, par4) != Block.blockRedstone.blockID
                || !TechWorld.TechPortal.tryToCreatePortal(par1World, par2,
                        par3, par4)) {
            if (!par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4)
                    && !canNeighborBurn(par1World, par2, par3, par4)) {
                par1World.setBlockToAir(par2, par3, par4);
            } else {
                par1World.scheduleBlockUpdate(par2, par3, par4, blockID,
                        tickRate(par1World) + par1World.rand.nextInt(10));
            }
        }
    }

    /** registers Icons, set textures here **/
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
        blockIcon = par1IconRegister.registerIcon(TechWorld
                .GetTextureLoc("Techfire_0"));
        iconArray = new Icon[] {
                par1IconRegister.registerIcon(TechWorld
                        .GetTextureLoc("Techfire_0")),
                par1IconRegister.registerIcon(TechWorld
                        .GetTextureLoc("Techfire_1")) };
    }
}
