package com.matrix.pathways.entity;

import com.google.common.collect.ImmutableSet;
import com.matrix.pathways.Pathways;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

import java.util.Random;

public class ModProfession {
    public static VillagerProfession BEYONDER;
    static{
        VillagerProfessionBuilder builder = VillagerProfessionBuilder.create().id(new Identifier(Pathways.MOD_ID,"beyonder")).secondaryJobSites(Blocks.ENCHANTING_TABLE).workstation(ModPointOfInterestType.BEYONDER);
        BEYONDER = Registry.register(Registry.VILLAGER_PROFESSION, new Identifier(Pathways.MOD_ID,"beyonder"), builder.build());

    }

}

