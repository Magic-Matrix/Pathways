package com.matrix.pathways.world.dimension;

import com.matrix.pathways.Pathways;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

public class ForsakenLandDimension {
    public static final RegistryKey<World> FORSAKEN_LAND_DIMENSION_KEY = RegistryKey.of(Registry.WORLD_KEY, new Identifier(Pathways.MOD_ID, "forsaken_fand_dimension"));
    public static final RegistryKey<DimensionType> FORSAKEN_LAND_DIMENSION_TYPE = RegistryKey.of(Registry.DIMENSION_TYPE_KEY, FORSAKEN_LAND_DIMENSION_KEY.getValue());
}
