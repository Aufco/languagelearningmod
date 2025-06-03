package com.languagelearning.mod;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(LanguageLearningMod.MODID)
public class LanguageLearningMod {
    public static final String MODID = "languagelearningmod";
    public static final Logger LOGGER = LogManager.getLogger();

    public LanguageLearningMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        MinecraftForge.EVENT_BUS.register(this);
        
        ModSounds.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("Language Learning Mod setup complete!");
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        LOGGER.info("Language Learning Mod client setup complete!");
        MinecraftForge.EVENT_BUS.register(new KeyInputHandler());
        MinecraftForge.EVENT_BUS.register(new RaycastHandler());
    }
}