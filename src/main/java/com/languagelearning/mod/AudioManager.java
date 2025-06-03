package com.languagelearning.mod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@OnlyIn(Dist.CLIENT)
public class AudioManager {
    private static final Logger LOGGER = LogManager.getLogger();
    
    public static void playPronunciation(String translationKey) {
        try {
            // Convert translation key to audio file name
            // e.g., "block.minecraft.stone" -> "block_minecraft_stone"
            String audioFileName = translationKey.replace(".", "_");
            
            // Create resource location for the audio file
            ResourceLocation audioLocation = new ResourceLocation(
                LanguageLearningMod.MODID, 
                "sounds/" + audioFileName
            );
            
            // Check if the sound event exists
            SoundEvent soundEvent = ForgeRegistries.SOUND_EVENTS.getValue(audioLocation);
            if (soundEvent != null) {
                Minecraft mc = Minecraft.getInstance();
                if (mc.player != null) {
                    mc.level.playSound(
                        mc.player,
                        mc.player.blockPosition(),
                        soundEvent,
                        SoundSource.MASTER,
                        1.0f, // Volume
                        1.0f  // Pitch
                    );
                    LOGGER.debug("Playing pronunciation audio for: {}", translationKey);
                }
            } else {
                LOGGER.debug("No audio file found for: {}", translationKey);
            }
        } catch (Exception e) {
            LOGGER.warn("Failed to play pronunciation for: {}", translationKey, e);
        }
    }
    
    public static String getAudioFileName(String translationKey) {
        return translationKey.replace(".", "_") + ".ogg";
    }
    
    public static ResourceLocation getAudioResourceLocation(String translationKey) {
        String audioFileName = translationKey.replace(".", "_");
        return new ResourceLocation(LanguageLearningMod.MODID, audioFileName);
    }
}