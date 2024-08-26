package net.andrewyernau.survivalist.block;


import net.andrewyernau.survivalist.Survivalist;
import net.andrewyernau.survivalist.block.custom.CutLogBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import static net.minecraft.block.Blocks.createLogBlock;

public class ModBlocks {

    public static final Block SULFUR_BLOCK = registerBlock("sulfur_block",
            new Block(
                    AbstractBlock.Settings.create()
                            .strength(6)
                        .requiresTool()
                        .sounds(BlockSoundGroup.METAL)));
    public static final Block SULFUR_ORE = registerBlock("sulfur_ore",
            new Block(
                    AbstractBlock.Settings.create()
                            .strength(4)
                            .requiresTool()
                            .sounds(BlockSoundGroup.STONE)));
    public static final Block DEEPSLATE_SULFUR_ORE = registerBlock("deepslate_sulfur_ore",
            new Block(
                    AbstractBlock.Settings.create()
                            .strength(4)
                            .requiresTool()
                            .sounds(BlockSoundGroup.STONE)));
    public static final Block MANURE_IN_SAC = registerBlock("manure_in_sac",
            new Block(
                    AbstractBlock.Settings.create()
                            .strength(2)
                            .sounds(BlockSoundGroup.ROOTED_DIRT)));
    public static final Block CUT_OAK_LOG = registerBlock("cut_oak_log", new CutLogBlock(AbstractBlock.Settings.create()
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .sounds(BlockSoundGroup.WOOD)
            .burnable()));
    public static final Block CUT_SPRUCE_LOG = registerBlock("cut_spruce_log", new CutLogBlock(AbstractBlock.Settings.create()
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .sounds(BlockSoundGroup.WOOD)
            .burnable()));
    public static final Block CUT_BIRCH_LOG = registerBlock("cut_birch_log", new CutLogBlock(AbstractBlock.Settings.create()
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .sounds(BlockSoundGroup.WOOD)
            .burnable()));
    public static final Block CUT_JUNGLE_LOG = registerBlock("cut_jungle_log", new CutLogBlock(AbstractBlock.Settings.create()
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .sounds(BlockSoundGroup.WOOD)
            .burnable()));
    public static final Block CUT_ACACIA_LOG = registerBlock("cut_acacia_log", new CutLogBlock(AbstractBlock.Settings.create()
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .sounds(BlockSoundGroup.WOOD)
            .burnable()));
    public static final Block CUT_CHERRY_LOG = registerBlock("cut_cherry_log", new CutLogBlock(AbstractBlock.Settings.create()
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .sounds( BlockSoundGroup.CHERRY_WOOD)
            .burnable()));
    public static final Block CUT_DARK_OAK_LOG = registerBlock("cut_dark_oak_log", new CutLogBlock(AbstractBlock.Settings.create()
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .sounds(BlockSoundGroup.WOOD)
            .burnable()));
    public static final Block CUT_MANGROVE_LOG = registerBlock("cut_mangrove_log", new CutLogBlock(AbstractBlock.Settings.create()
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .sounds(BlockSoundGroup.WOOD)
            .burnable()));

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Survivalist.MOD_ID,name),block);
    }

    private static void registerBlockItem(String name, Block block){
        Registry.register(Registries.ITEM, Identifier.of(Survivalist.MOD_ID,name),
                new BlockItem(block,new Item.Settings()));
    }

    public static void registerModBlocks(){
        Survivalist.LOGGER.info("Registering Mod Blocks for "+ Survivalist.MOD_ID);
    }

}
