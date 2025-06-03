package com.languagelearning.mod;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RaycastHandler {
    
    public static class LookupResult {
        public final String translationKey;
        public final String englishName;
        public final String spanishName;
        public final TargetType type;
        
        public LookupResult(String translationKey, String englishName, String spanishName, TargetType type) {
            this.translationKey = translationKey;
            this.englishName = englishName;
            this.spanishName = spanishName;
            this.type = type;
        }
    }
    
    public enum TargetType {
        BLOCK, ITEM, ENTITY, NONE
    }
    
    public static LookupResult getCurrentLookupResult() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.player == null) {
            return null;
        }
        
        HitResult hitResult = mc.hitResult;
        if (hitResult == null) {
            return null;
        }
        
        LanguageManager langManager = LanguageManager.getInstance();
        
        switch (hitResult.getType()) {
            case BLOCK:
                BlockHitResult blockHit = (BlockHitResult) hitResult;
                BlockState blockState = mc.level.getBlockState(blockHit.getBlockPos());
                Block block = blockState.getBlock();
                String blockKey = block.getDescriptionId();
                
                return new LookupResult(
                    blockKey,
                    langManager.getEnglishTranslation(blockKey),
                    langManager.getSpanishTranslation(blockKey),
                    TargetType.BLOCK
                );
                
            case ENTITY:
                EntityHitResult entityHit = (EntityHitResult) hitResult;
                Entity entity = entityHit.getEntity();
                String entityKey = entity.getType().getDescriptionId();
                
                return new LookupResult(
                    entityKey,
                    langManager.getEnglishTranslation(entityKey),
                    langManager.getSpanishTranslation(entityKey),
                    TargetType.ENTITY
                );
                
            default:
                break;
        }
        
        ItemStack heldItem = mc.player.getMainHandItem();
        if (!heldItem.isEmpty()) {
            String itemKey = heldItem.getItem().getDescriptionId();
            return new LookupResult(
                itemKey,
                langManager.getEnglishTranslation(itemKey),
                langManager.getSpanishTranslation(itemKey),
                TargetType.ITEM
            );
        }
        
        return null;
    }
}