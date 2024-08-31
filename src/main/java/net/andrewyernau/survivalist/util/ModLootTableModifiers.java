package net.andrewyernau.survivalist.util;


import net.andrewyernau.survivalist.item.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.block.Blocks;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class ModLootTableModifiers {
    public static void modifyLootTables() {
//        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
//            if(Blocks.STONE.getLootTableKey() == key){
//                LootPool.Builder poolBuilder = LootPool.builder()
//                        .rolls(ConstantLootNumberProvider.create(1))
//                        .conditionally(RandomChanceLootCondition.builder(1f))
//                        .with(ItemEntry.builder(ModItems.STONE_PEBBLE))
//                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,1.0f)).build());
//               tableBuilder.pool(poolBuilder);
//               poolBuilder.build();
//            }
//        });
    }
}
