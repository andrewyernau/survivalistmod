package net.andrewyernau.survivalist;

import net.andrewyernau.survivalist.block.ModBlocks;
import net.andrewyernau.survivalist.block.BlockEntityTypeInit;
import net.andrewyernau.survivalist.item.ModItemGroups;
import net.andrewyernau.survivalist.item.ModItems;
import net.andrewyernau.survivalist.sound.ModSounds;
import net.andrewyernau.survivalist.util.ModLootTableModifiers;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Survivalist implements ModInitializer {
    public static final String MOD_ID = "survivalist";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.registerModItems();
        ModLootTableModifiers.modifyLootTables();
        ModBlocks.registerModBlocks();
        ModItemGroups.registerItemGroups();
        ModSounds.registerModSounds();
        BlockEntityTypeInit.load();
    }

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}