package com.matrix.pathways.world.dimension;

import com.matrix.pathways.Pathways;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionOptions;

public class ForsakenLandDimension {
    public static RegistryKey<World> FORSAKEN_LAND_DIMENSION_KEY;
    public static RegistryKey<DimensionType> FORSAKEN_LAND_DIMENSION_TYPE;

    public static void register(){
        FORSAKEN_LAND_DIMENSION_KEY = RegistryKey.of(Registry.WORLD_KEY, new Identifier(Pathways.MOD_ID, "forsaken_land_dimension"));
        FORSAKEN_LAND_DIMENSION_TYPE = RegistryKey.of(Registry.DIMENSION_TYPE_KEY, FORSAKEN_LAND_DIMENSION_KEY.getValue());
    }



}
