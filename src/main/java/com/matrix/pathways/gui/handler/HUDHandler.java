package com.matrix.pathways.gui.handler;


import com.matrix.pathways.Pathways;
// import com.matrix.pathways.utils.Color;
// import com.matrix.pathways.utils.EntityStatus;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;

public class HUDHandler {
    private static final Identifier statusBarTexture = new Identifier(Pathways.MOD_ID, "textures/gui/status_bar.png");
    private int renderHealthValue;
    private int lastHealthValue;
    private long lastHealthCheckTime;
    private long heartJumpEndTick;

    public MinecraftClient client;

    public static HUDHandler INSTANCE = new HUDHandler();


    public void drawBarShell(MatrixStack matrices, int x, int y, int color){

        this.client.inGameHud.drawTexture(matrices, x, y, 0, color, 81, 9);

    }
    public void drawBar(MatrixStack matrices, int x, int y, int color, int width){
        // Color.reset();
        // (new Color(color)).color2Gl();
        this.client.inGameHud.drawTexture(matrices, x, y, 2, 11, width, 5);
    }

    public void drawIcon(MatrixStack matrices, int x, int y, int type){
//        client.getTextureManager().bindTexture(statusBarTexture);

        int v = type < 10 ? 45 : 29;
        int w = type < 9 ? 45 : 16;
        int u = (type % 10) * w;

        // Color.reset();
        this.client.inGameHud.drawTexture(matrices, x, y, u, v, w, w);

    }



    public void hudMountHealthRender(MatrixStack matrices, MinecraftClient client, LivingEntity livingEntity){

        client.getTextureManager().bindTexture(statusBarTexture);
        this.client = client;
        int scaledWidth = client.getWindow().getScaledWidth();
        int scaledHeight = client.getWindow().getScaledHeight();
        if (livingEntity != null) {
            float health = livingEntity.getHealth();
            // float max_health = (float)livingEntity.getAttributeValue(EntityAttributes.GENERIC_MAX_HEALTH);

            drawIcon(matrices, scaledWidth / 2 + 100, scaledHeight - 36 , 12);
            client.textRenderer.draw(matrices, Integer.toString((int)health), (int)(scaledWidth / 2) + 120, scaledHeight - 32, 0x00ff00);
        }


    }

    public void hudBarRender(MatrixStack matrices, MinecraftClient client, int ticks){
        this.client = client;
        PlayerEntity player = !(client.getCameraEntity() instanceof PlayerEntity) ? null : (PlayerEntity)client.getCameraEntity();
        if (player == null)
            return;

        int scaledWidth = client.getWindow().getScaledWidth();
        int scaledHeight = client.getWindow().getScaledHeight();
        client.getTextureManager().bindTexture(statusBarTexture);
        TextRenderer fontRenderer = client.textRenderer;

        // // 获取 san 值
        // int sanLevel = (int)EntityStatus.getSanLevel(player);

        // // 获取生命值
        // int health = (int)EntityStatus.getHealth(player);
        // float maxHealth = EntityStatus.getMaxHealth(player);
        // int air = (int)EntityStatus.getAir(player);
        // int foodLevel = (int)EntityStatus.getFoodLevel(player);
        // int armor = (int)EntityStatus.getArmor(player);


        float maxHealth = (float)(player.getAttributeValue(EntityAttributes.GENERIC_MAX_HEALTH));
        int health = (int)(MathHelper.ceil(player.getHealth()));
        int foodLevel = (int)(player.getHungerManager().getFoodLevel());
        int armor = (int)(player.getArmor());
        int air = (int)(Math.max(player.getAir(), 0));
        int sanLevel = 100;

        // 检查是否生命值跳动
        boolean isHealthJump = this.heartJumpEndTick > (long)ticks && (this.heartJumpEndTick - (long)ticks) / 3L % 2L == 1L;
        // 获取当前时间
        long time_now = Util.getMeasuringTimeMs();
        if (health < this.lastHealthValue && player.timeUntilRegen > 0) {
            // 1.生命值小于上一次生命（受伤）
            // 2.有再生的时间

            // 更新上一次检查的时间
            this.lastHealthCheckTime = time_now;
            // 跳动时间延长 20 tick
            this.heartJumpEndTick = (long)(ticks + 20);
        } else if (health > this.lastHealthValue && player.timeUntilRegen > 0) {
            // 1.生命值大于上一次生命（恢复）
            // 2.有再生的时间

            // 更新上一次检查的时间
            this.lastHealthCheckTime = time_now;

            // 跳动时间延长 10 tick
            this.heartJumpEndTick = (long)(ticks + 10);
        }

        // 上一次检查的时间过长会强制刷新一次
        if (time_now - this.lastHealthCheckTime > 1000L) {
            this.lastHealthValue = health;
            this.renderHealthValue = health;
            this.lastHealthCheckTime = time_now;
        }
        // 更新上一次生命值，为下一次循环做准备
        this.lastHealthValue = health;

        int x1 = scaledWidth / 2 - 91;
        int x2 = scaledWidth / 2 + 10;

        int y1 = scaledHeight - 39;
        int y2 = y1 - 10;

        // 血条
        drawIcon(matrices, x1 - 11, y1, 0);
        drawBarShell(matrices, x1, y1, isHealthJump ? 18 : 0);
        drawBloodBar(matrices, x1, y1, maxHealth, (float)health);

        // 饥饿条
        drawIcon(matrices, x2 + 82, y1, 1);
        drawBarShell(matrices, x2, y1, 0);
        drawFoodBar(matrices, x2, y1, 20.f, (float)foodLevel);

        // 在水中显示氧气条
        if(player.isSubmergedIn(FluidTags.WATER)){
            drawIcon(matrices, x2 + 82, y2, 3);
            drawBarShell(matrices, x2, y2, 0);
            int width = (int)((float)air / 300.f * 77);
            drawBar(matrices, x2 + (77 - width)+2, y2+2,0xffff, width);
        }
        // 盔甲值显示
        if(armor > 0) {
            drawIcon(matrices, scaledWidth / 2 + 100, scaledHeight - 20, 13);
        }

        // San
        drawIcon(matrices, x1 - 25, scaledHeight - 20, 10);

        // 魔药
        drawIcon(matrices, x1 - 25, scaledHeight - 36, 11);

        // San值显示
        fontRenderer.draw(matrices, sanLevel + "%", x1-50, scaledHeight - 17, 0x00ffff);
        // 魔药显示
        fontRenderer.draw(matrices, "100%", x1-50, scaledHeight - 32, 0xffff00);
        // 盔甲值显示
        if(armor > 0){
            fontRenderer.draw(matrices, Integer.toString(armor), (int)(scaledWidth / 2) + 120, scaledHeight - 17, 0xffffff);
        }
    }

    private void drawBloodBar(MatrixStack matrices, int x, int y, float max, float num){

        int width = (int)(num / max * 77);
        drawBar(matrices, x+2, y+2, num/max > 0.25 ? 0x00ff00 : 0xff0000, width);

        // 血量减少的时候会渲染减少的量
        if(this.renderHealthValue > num){
            int width2 = (int)(this.renderHealthValue / max * 77);
            drawBar(matrices, x + 2 + width, y+2,0xffffff, width2 - width);
        }

    }

    private void drawFoodBar(MatrixStack matrices, int x, int y, float max, float num){
        int width = (int)(num / max * 77);
        drawBar(matrices, x + (77 - width) + 2, y + 2,0xcc6600, width);

    }


}
