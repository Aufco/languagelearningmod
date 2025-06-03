package com.languagelearning.mod;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LanguageManager {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new Gson();
    
    private Map<String, String> englishTranslations = new HashMap<>();
    private Map<String, String> spanishTranslations = new HashMap<>();
    
    private static LanguageManager instance;
    
    public static LanguageManager getInstance() {
        if (instance == null) {
            instance = new LanguageManager();
        }
        return instance;
    }
    
    private LanguageManager() {
        loadLanguageFiles();
    }
    
    private void loadLanguageFiles() {
        try {
            loadLanguageFile("assets/languagelearningmod/lang/en_us.json", englishTranslations);
            loadLanguageFile("assets/languagelearningmod/lang/es_mx.json", spanishTranslations);
            LOGGER.info("Loaded {} English translations and {} Spanish translations", 
                englishTranslations.size(), spanishTranslations.size());
        } catch (Exception e) {
            LOGGER.error("Failed to load language files", e);
        }
    }
    
    private void loadLanguageFile(String resourcePath, Map<String, String> targetMap) {
        try {
            ResourceLocation location = new ResourceLocation(LanguageLearningMod.MODID, resourcePath.substring(resourcePath.indexOf("lang/")));
            Optional<Resource> resource = Minecraft.getInstance().getResourceManager().getResource(location);
            
            if (resource.isPresent()) {
                InputStreamReader reader = new InputStreamReader(resource.get().open(), StandardCharsets.UTF_8);
                Type type = new TypeToken<Map<String, String>>(){}.getType();
                Map<String, String> translations = GSON.fromJson(reader, type);
                if (translations != null) {
                    targetMap.putAll(translations);
                }
                reader.close();
            } else {
                LOGGER.warn("Could not find language file: {}", resourcePath);
            }
        } catch (IOException e) {
            LOGGER.error("Error loading language file: {}", resourcePath, e);
        }
    }
    
    public String getEnglishTranslation(String key) {
        return englishTranslations.getOrDefault(key, key);
    }
    
    public String getSpanishTranslation(String key) {
        return spanishTranslations.getOrDefault(key, key);
    }
    
    public boolean hasTranslation(String key) {
        return englishTranslations.containsKey(key) || spanishTranslations.containsKey(key);
    }
}