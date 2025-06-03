package com.languagelearning.mod;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = LanguageLearningMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class TranslationOverlay {
    
    private static RaycastHandler.LookupResult currentTranslation = null;
    private static long displayStartTime = 0;
    private static final long DISPLAY_DURATION = 3000; // 3 seconds
    
    public static void showTranslation(RaycastHandler.LookupResult translation) {
        currentTranslation = translation;
        displayStartTime = System.currentTimeMillis();
    }
    
    @SubscribeEvent
    public static void onRenderGuiOverlay(RenderGuiOverlayEvent.Post event) {
        if (currentTranslation == null) {
            return;
        }
        
        long currentTime = System.currentTimeMillis();
        if (currentTime - displayStartTime > DISPLAY_DURATION) {
            currentTranslation = null;
            return;
        }
        
        Minecraft mc = Minecraft.getInstance();
        GuiGraphics guiGraphics = event.getGuiGraphics();
        Font font = mc.font;
        
        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();
        
        String englishText = "🇺🇸 " + currentTranslation.englishName;
        String spanishText = "🇪🇸 " + currentTranslation.spanishName;
        String typeText = "(" + currentTranslation.type.toString().toLowerCase() + ")";
        
        int englishWidth = font.width(englishText);
        int spanishWidth = font.width(spanishText);
        int typeWidth = font.width(typeText);
        
        int maxWidth = Math.max(Math.max(englishWidth, spanishWidth), typeWidth);
        int boxWidth = maxWidth + 20;
        int boxHeight = 60;
        
        int x = (screenWidth - boxWidth) / 2;
        int y = screenHeight / 2 + 20;
        
        // Calculate fade effect
        float alpha = 1.0f;
        long elapsed = currentTime - displayStartTime;
        if (elapsed > DISPLAY_DURATION - 500) {
            alpha = (DISPLAY_DURATION - elapsed) / 500.0f;
        }
        
        int backgroundColor = (int)(alpha * 160) << 24; // Semi-transparent black
        int borderColor = (int)(alpha * 255) << 24 | 0xFFFFFF; // White border
        
        // Draw background
        guiGraphics.fill(x, y, x + boxWidth, y + boxHeight, backgroundColor);
        
        // Draw border
        guiGraphics.fill(x, y, x + boxWidth, y + 1, borderColor); // Top
        guiGraphics.fill(x, y + boxHeight - 1, x + boxWidth, y + boxHeight, borderColor); // Bottom
        guiGraphics.fill(x, y, x + 1, y + boxHeight, borderColor); // Left
        guiGraphics.fill(x + boxWidth - 1, y, x + boxWidth, y + boxHeight, borderColor); // Right
        
        // Draw text
        int textColor = (int)(alpha * 255) << 24 | 0xFFFFFF;
        
        guiGraphics.drawString(font, typeText, x + (boxWidth - typeWidth) / 2, y + 5, textColor);
        guiGraphics.drawString(font, englishText, x + (boxWidth - englishWidth) / 2, y + 20, textColor);
        guiGraphics.drawString(font, spanishText, x + (boxWidth - spanishWidth) / 2, y + 35, textColor);
    }
}