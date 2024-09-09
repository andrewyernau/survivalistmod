package net.andrewyernau;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import com.github.scillman.minecraft.toolbelt.ToolbeltMain;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.CopyComponentsLootFunction;
import net.minecraft.loot.function.CopyComponentsLootFunction.Source;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;

public class ToolbeltLootTableGenerator extends FabricBlockLootTableProvider
{
    public ToolbeltLootTableGenerator(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup)
    {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate()
    {
        // addDrop(ToolbeltMain.TOOLBELT_BLOCK,
        //     drops(ToolbeltMain.TOOLBELT_BLOCK_ITEM)
        //         .apply(CopyComponentsLootFunction.builder(Source.BLOCK_ENTITY).include(ToolbeltMain.NUMBER))
        // );
    }

    @Override
    public void accept(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> biConsumer)
    {
        final Block BLOCK = ToolbeltMain.TOOLBELT_BLOCK;

        biConsumer.accept(BLOCK.getLootTableKey(), LootTable.builder()
            .pool(addSurvivesExplosionCondition(BLOCK, LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1.0f))
                .with(ItemEntry.builder(BLOCK)
                    .apply(CopyComponentsLootFunction.builder(Source.BLOCK_ENTITY)
                        .include(ToolbeltMain.NUMBER)
                        .include(DataComponentTypes.CONTAINER)
                    )
                )
            ))
        );
    }
}
