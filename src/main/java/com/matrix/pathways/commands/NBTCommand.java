package com.matrix.pathways.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.LiteralText;

public class NBTCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher){
        dispatcher.register(CommandManager.literal("nbt").requires(c -> c.hasPermissionLevel(4)).executes(c -> getNbt(c.getSource().getPlayer())));
    }

    public static int getNbt(PlayerEntity player){
        ItemStack stack = player.getMainHandStack();
        if(stack.hasNbt()){
            assert stack.getNbt() != null;
            player.sendMessage(new LiteralText(stack.getNbt().toString()), false);
            return Command.SINGLE_SUCCESS;
        }
        else{
            player.sendMessage(new LiteralText("item has no nbt"), false);
        }
        return 0;
    }
}
