package net.andrewyernau.survivalist.block;

import net.andrewyernau.survivalist.Survivalist;
import net.andrewyernau.survivalist.block.entities.CutLogBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class BlockEntityTypeInit {

    public static final BlockEntityType<CutLogBlockEntity> CUT_LOG_BLOCK_ENTITY = register("cut_log_block_entity",
            BlockEntityType.Builder.create(CutLogBlockEntity::new,
                            ModBlocks.CUT_ACACIA_LOG,
                            ModBlocks.CUT_CHERRY_LOG,
                            ModBlocks.CUT_BIRCH_LOG,
                            ModBlocks.CUT_OAK_LOG,
                            ModBlocks.CUT_JUNGLE_LOG,
                            ModBlocks.CUT_MANGROVE_LOG,
                            ModBlocks.CUT_DARK_OAK_LOG,
                            ModBlocks.CUT_SPRUCE_LOG)
                    .build());

    public static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType<T> type) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Survivalist.id(name), type);
    }

    public static void load() {

    }
}
