package com.matrix.pathways.items;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import com.matrix.pathways.Pathways;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemRegister {
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier(Pathways.MOD_ID, Pathways.MOD_ID),
            () -> new ItemStack(Items.NETHER_STAR));


    public static final Item CHARACTERISTIC = new Characteristic(ITEM_GROUP);
    public static final Item CHARACTERISTIC_POTION = new CharacteristicPotion(ITEM_GROUP);

    public static void register(){
        Registry.register(Registry.ITEM, new Identifier(Pathways.MOD_ID, "characteristic"), CHARACTERISTIC);
        Registry.register(Registry.ITEM, new Identifier(Pathways.MOD_ID, "characteristic_potion"), CHARACTERISTIC_POTION);
    }
}
