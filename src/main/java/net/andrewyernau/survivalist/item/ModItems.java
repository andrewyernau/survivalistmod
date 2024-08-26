package net.andrewyernau.survivalist.item;

import net.andrewyernau.survivalist.Survivalist;
import net.andrewyernau.survivalist.item.custom.StoneKnifeItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    //EARLY GAME
    public static final Item STONE_PEBBLE = registerItem("stone_pebble", new Item(new Item.Settings()));
    public static final Item QUARTZ_PEBBLE = registerItem("quartz_pebble", new Item(new Item.Settings()));
    public static final Item OBSIDIAN_PEBBLE = registerItem("obsidian_pebble", new Item(new Item.Settings()));
    public static final Item STONE_CHOPPER = registerItem("stone_chopper", new Item(new Item.Settings()));
    public static final Item QUARTZ_CHOPPER = registerItem("quartz_chopper", new Item(new Item.Settings()));
    public static final Item OBSIDIAN_CHOPPER = registerItem("obsidian_chopper", new Item(new Item.Settings()));
    public static final Item RESIN = registerItem("resin", new Item(new Item.Settings()));
    public static final Item STONE_KNIFE = registerItem("stone_knife",new StoneKnifeItem(ToolMaterials.STONE,
            new Item.Settings().maxDamage(32).attributeModifiers(StoneKnifeItem.createAttributeModifiers(ToolMaterials.STONE, 3, -2.4F))));
    //GAME MECHANICS
    public static final Item MANURE = registerItem("manure", new BoneMealItem(new Item.Settings()));
    public static final Item SULFUR = registerItem("sulfur", new Item(new Item.Settings()));
    public static final Item POTASSIUM_NITRATE = registerItem("potassium_nitrate", new Item(new Item.Settings()));
    public static final Item ASH = registerItem("ash", new Item(new Item.Settings()));
    public static final Item SAC = registerItem("sac", new Item(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Survivalist.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Survivalist.LOGGER.info("Registering Mod Items for " + Survivalist.MOD_ID);
    }
}
