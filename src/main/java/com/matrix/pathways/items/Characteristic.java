package com.matrix.pathways.items;

import com.mojang.serialization.DataResult;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.nbt.NbtOps;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.List;
import java.util.Objects;

public class Characteristic extends Item {

    boolean contaminate;
    int level;

    public Characteristic(ItemGroup group, boolean contaminate, int level) {
        super((new Settings()).group(group).maxCount(1).rarity(Rarity.EPIC));
        this.contaminate = contaminate;
        this.level = level;
    }


    public Characteristic(ItemGroup group) {
        this(group, false, 0);
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {

        if(!stack.hasNbt()){
            NbtCompound nbt= new NbtCompound();
            writeNbt(nbt);
            stack.setNbt(nbt);
        }
        else{
            assert stack.getNbt() != null;
            readNbt(stack.getNbt());
        }

        Formatting formatting = this.contaminate ? Formatting.RED : Formatting.GREEN;
        String contaminate_string = "item.pathways.contaminate_" + this.contaminate + ".tooltip";
        // 是否被污染
        tooltip.add( new TranslatableText(contaminate_string).formatted(formatting));
    }

    private void writeNbt(NbtCompound nbt){
        nbt.putBoolean("contaminate", contaminate);
        nbt.putInt("level",level);
    }
    private void readNbt(NbtCompound nbt){
        contaminate = nbt.getBoolean("contaminate");
        level = nbt.getInt("level");

    }

}

