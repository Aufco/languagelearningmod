package com.languagelearning.mod;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = LanguageLearningMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class KeyInputHandler {
    
    public static final String KEY_CATEGORY_LANGUAGE_LEARNING = "key.category.languagelearningmod.language_learning";
    public static final String KEY_SHOW_TRANSLATION = "key.languagelearningmod.show_translation";
    
    public static final KeyMapping SHOW_TRANSLATION_KEY = new KeyMapping(
        KEY_SHOW_TRANSLATION,
        InputConstants.Type.KEYSYM,
        GLFW.GLFW_KEY_F,
        KEY_CATEGORY_LANGUAGE_LEARNING
    );
    
    @SubscribeEvent
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(SHOW_TRANSLATION_KEY);
    }
    
    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (SHOW_TRANSLATION_KEY.consumeClick()) {
            handleTranslationKeyPress();
        }
    }
    
    private static void handleTranslationKeyPress() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.player == null) {
            return;
        }
        
        RaycastHandler.LookupResult result = RaycastHandler.getCurrentLookupResult();
        if (result != null) {
            TranslationOverlay.showTranslation(result);
            AudioManager.playPronunciation(result.translationKey);
        }
    }
}