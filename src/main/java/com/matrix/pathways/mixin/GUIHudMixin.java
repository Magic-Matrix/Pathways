package com.matrix.pathways.mixin;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import com.matrix.pathways.gui.handler.HUDHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public abstract class GUIHudMixin{
    @Shadow private int ticks;
    @Shadow @Final private MinecraftClient client;
    @Shadow private LivingEntity getRiddenEntity() {
        return null;
    }

    @Redirect(method = "render(Lnet/minecraft/client/util/math/MatrixStack;F)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderStatusBars(Lnet/minecraft/client/util/math/MatrixStack;)V"))
    private void renderStatusBarsMixin(InGameHud hud, MatrixStack matrices) {
        HUDHandler.INSTANCE.hudBarRender(matrices, client, ticks);

    }

    @Redirect(method = "render(Lnet/minecraft/client/util/math/MatrixStack;F)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderMountHealth(Lnet/minecraft/client/util/math/MatrixStack;)V"))
    private void renderMountHealthMixin(InGameHud hud, MatrixStack matrices) {
        LivingEntity livingEntity = this.getRiddenEntity();

        HUDHandler.INSTANCE.hudMountHealthRender(matrices, client, livingEntity);
    }

    // @Redirect(method = "renderHotbar(F;Lnet/minecraft/client/util/math/MatrixStack)V",
    //         at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderMountHealth(Lnet/minecraft/client/util/math/MatrixStack;)V"))
    // private void renderHotbarMixin(InGameHud hud, MatrixStack matrices) {
    //     LivingEntity livingEntity = this.getRiddenEntity();

    //     HUDHandler.INSTANCE.hudMountHealthRender(matrices, client, livingEntity);
    // }
}
