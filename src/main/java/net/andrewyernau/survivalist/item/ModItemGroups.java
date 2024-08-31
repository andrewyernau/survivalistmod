package net.andrewyernau.survivalist.item;

import net.andrewyernau.survivalist.Survivalist;
import net.andrewyernau.survivalist.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup SURVIVALIST_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Survivalist.MOD_ID, "survivalist_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.SAC))
                    .displayName(Text.translatable("itemgroup.survivalist.survivalist_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.ASH);
                        entries.add(ModItems.MANURE);
                        entries.add(ModItems.OBSIDIAN_CHOPPER);
                        entries.add(ModItems.OBSIDIAN_PEBBLE);
                        entries.add(ModItems.POTASSIUM_NITRATE);
                        entries.add(ModItems.QUARTZ_CHOPPER);
                        entries.add(ModItems.QUARTZ_PEBBLE);
                        entries.add(ModItems.RESIN);
                        entries.add(ModItems.SAC);
                        entries.add(ModItems.STONE_CHOPPER);
                        entries.add(ModItems.STONE_PEBBLE);
                        entries.add(ModItems.SULFUR);
                    }).build());
    public static final ItemGroup SURVIVALIST_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Survivalist.MOD_ID, "survivalist_block_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.MANURE_IN_SAC))
                    .displayName(Text.translatable("itemgroup.survivalist.survivalist_blocks"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.CUT_ACACIA_LOG);
                        entries.add(ModBlocks.CUT_BIRCH_LOG);
                        entries.add(ModBlocks.CUT_CHERRY_LOG);
                        entries.add(ModBlocks.CUT_DARK_OAK_LOG);
                        entries.add(ModBlocks.CUT_JUNGLE_LOG);
                        entries.add(ModBlocks.CUT_MANGROVE_LOG);
                        entries.add(ModBlocks.CUT_OAK_LOG);
                        entries.add(ModBlocks.CUT_SPRUCE_LOG);

                        entries.add(ModBlocks.DEEPSLATE_SULFUR_ORE);
                        entries.add(ModBlocks.MANURE_IN_SAC);
                        entries.add(ModBlocks.SULFUR_BLOCK);
                        entries.add(ModBlocks.SULFUR_ORE);
                    }).build());
    public static final ItemGroup SURVIVALIST_TOOLS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Survivalist.MOD_ID, "survivalist_tools"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.STONE_KNIFE))
                    .displayName(Text.translatable("itemgroup.survivalist.survivalist_tools"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.STONE_KNIFE);
                    }).build());

    public static void registerItemGroups() {
        Survivalist.LOGGER.info("Registering Item Groups for " + Survivalist.MOD_ID);
    }
}
