package com.languagelearning.mod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = 
        DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, LanguageLearningMod.MODID);
    
    // Example sound registration - you can add more as needed
    public static final RegistryObject<SoundEvent> BLOCK_MINECRAFT_STONE = registerSoundEvent("block_minecraft_stone");
    public static final RegistryObject<SoundEvent> ITEM_MINECRAFT_DIAMOND_SWORD = registerSoundEvent("item_minecraft_diamond_sword");
    
    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(
            new ResourceLocation(LanguageLearningMod.MODID, name)));
    }
    
    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}