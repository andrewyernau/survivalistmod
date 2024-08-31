package net.andrewyernau.survivalist.mixin;

import net.andrewyernau.survivalist.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.GrassBlock;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(BoneMealItem.class)
public class BoneMealItemMixin {
    @Inject(method = "useOnFertilizable", at = @At("HEAD"), cancellable = true)
    private static void useOnFertilizable0(ItemStack stack, World world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        BlockState blockState = world.getBlockState(pos);

        //for bonemeal
        if (stack.getItem() == Items.BONE_MEAL && blockState.getBlock() instanceof GrassBlock && blockState.getBlock() instanceof Fertilizable fertilizable && fertilizable.isFertilizable(world, pos, blockState)) {
            if (world instanceof ServerWorld) {
                if (fertilizable.canGrow(world, world.random, pos, blockState)) {
                    fertilizable.grow((ServerWorld) world, world.random, pos, blockState);
                }

                stack.decrement(1);
            }

            cir.setReturnValue(true);
        }

        cir.setReturnValue(false);

    }
}
