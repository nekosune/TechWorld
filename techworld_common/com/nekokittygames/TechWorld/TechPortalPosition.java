package com.nekokittygames.TechWorld;

import net.minecraft.util.ChunkCoordinates;

public class TechPortalPosition extends ChunkCoordinates {

    public long          field_85087_d;

    final TechTeleporter field_85088_e;

    public TechPortalPosition(TechTeleporter techTeleporter, int par2,
            int par3, int par4, long par5) {
        super(par2, par3, par4);
        field_85088_e = techTeleporter;
        field_85087_d = par5;
    }
}
