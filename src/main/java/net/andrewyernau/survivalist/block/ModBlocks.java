package net.andrewyernau.survivalist.block;


import net.andrewyernau.survivalist.Survivalist;
import net.andrewyernau.survivalist.block.custom.CutLogBlock;
import net.andrewyernau.survivalist.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;


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
    public static final CutLogBlock CUT_OAK_LOG = registerWithItem("cut_oak_log", new CutLogBlock(
            AbstractBlock.Settings.create()
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sounds(BlockSoundGroup.WOOD)
                    .burnable()));
    public static final CutLogBlock CUT_SPRUCE_LOG = registerWithItem("cut_spruce_log", new CutLogBlock(AbstractBlock.Settings.create()
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .sounds(BlockSoundGroup.WOOD)
            .burnable()));
    public static final CutLogBlock CUT_BIRCH_LOG = registerWithItem("cut_birch_log", new CutLogBlock(AbstractBlock.Settings.create()
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .sounds(BlockSoundGroup.WOOD)
            .burnable()));
    public static final CutLogBlock CUT_JUNGLE_LOG = registerWithItem("cut_jungle_log", new CutLogBlock(AbstractBlock.Settings.create()
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .sounds(BlockSoundGroup.WOOD)
            .burnable()));
    public static final CutLogBlock CUT_ACACIA_LOG = registerWithItem("cut_acacia_log", new CutLogBlock(AbstractBlock.Settings.create()
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .sounds(BlockSoundGroup.WOOD)
            .burnable()));
    public static final CutLogBlock CUT_CHERRY_LOG = registerWithItem("cut_cherry_log", new CutLogBlock(AbstractBlock.Settings.create()
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .sounds(BlockSoundGroup.CHERRY_WOOD)
            .burnable()));
    public static final CutLogBlock CUT_DARK_OAK_LOG = registerWithItem("cut_dark_oak_log", new CutLogBlock(AbstractBlock.Settings.create()
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .sounds(BlockSoundGroup.WOOD)
            .burnable()));
    public static final CutLogBlock CUT_MANGROVE_LOG = registerWithItem("cut_mangrove_log", new CutLogBlock(AbstractBlock.Settings.create()
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .sounds(BlockSoundGroup.WOOD)
            .burnable()));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Survivalist.id(name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Survivalist.id(name),
                new BlockItem(block, new Item.Settings()));
    }

    //other way
    private static <T extends Block> T registerWithItem(String name, T block, Item.Settings settings) {
        T registered = register(name, block);
        ModItems.registerModItems(name, new BlockItem(registered, settings));
        return registered;
    }

    private static <T extends Block> T registerWithItem(String name, T block) {
        T registered = register(name, block);
        ModItems.registerModItems(name, new BlockItem(registered, new Item.Settings()));
        return registered;
    }

    private static <T extends Block> T register(String name, T block) {
        return Registry.register(Registries.BLOCK, Survivalist.id(name), block);
    }

    //
    public static void registerModBlocks() {
        Survivalist.LOGGER.info("Registering Mod Blocks for " + Survivalist.MOD_ID);
    }

}
