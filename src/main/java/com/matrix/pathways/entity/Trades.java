package com.matrix.pathways.entity;

import com.matrix.pathways.items.ItemRegister;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.village.TradeOffer;

import java.util.Random;

public class Trades {
    private static void tradeCharacteristic(int num, boolean contaminate, int count, int merchantExperienc){
        TradeOfferHelper.registerVillagerOffers(ModProfession.BEYONDER, num, factories -> {
            ItemStack stack = new ItemStack(ItemRegister.CHARACTERISTIC);
            // 添加nbt
            NbtCompound nbt= new NbtCompound();
            nbt.putBoolean("contaminate", contaminate);
            nbt.putInt("level", 10 - num);
            stack.setNbt(nbt);
            factories.add(((entity, random) -> new TradeOffer(new ItemStack(Items.EMERALD, count), stack, 1, merchantExperienc, 1)));
        });
    }

    public static void init(){
        for(int i=1;i<=5;i++){
            tradeCharacteristic(i, true, (int)(64 * 0.2 * i), 5 * i);
            tradeCharacteristic(i, false, (int)(64 * 0.2 * i * 0.8), 5 * i);
        }
    }
}
