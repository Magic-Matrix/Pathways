package com.matrix.pathways.entity;

import com.matrix.pathways.Pathways;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.world.poi.PointOfInterestType;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
public class ModPointOfInterestType {
    public static final PointOfInterestType BEYONDER;

    static {
        BEYONDER = PointOfInterestHelper.register(new Identifier(Pathways.MOD_ID,"beyonder"), 1,1,Blocks.ENCHANTING_TABLE);

    }
}
