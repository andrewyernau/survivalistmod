package net.andrewyernau.survivalist.block.custom;

import com.mojang.serialization.MapCodec;
import net.andrewyernau.survivalist.Survivalist;
import net.andrewyernau.survivalist.block.BlockEntityTypeInit;
import net.andrewyernau.survivalist.block.entities.CutLogBlockEntity;
import net.andrewyernau.survivalist.properties.ModProperties;
import net.andrewyernau.survivalist.util.TickableBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import net.minecraft.world.World;

import org.jetbrains.annotations.Nullable;

public class CutLogBlock extends Block implements BlockEntityProvider {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty RESIN_DROP = ModProperties.RESIN_DROP;


    public CutLogBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(RESIN_DROP, Boolean.FALSE));
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, RESIN_DROP);

    }

    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof CutLogBlockEntity cutLogBlockEntity) {
                if (state.get(RESIN_DROP) && player.getMainHandStack().isEmpty()) {
                    cutLogBlockEntity.summonResinItem();
                    world.setBlockState(pos, state.with(RESIN_DROP, false), 3);
                    return ActionResult.success(true);
                }
            }
        }
        else{
            if(state.get(RESIN_DROP)){
                world.playSound(player, pos, SoundEvents.BLOCK_BAMBOO_WOOD_HIT, SoundCategory.BLOCKS, 1f, 1f);
            }
        }
        return ActionResult.PASS;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return BlockEntityTypeInit.CUT_LOG_BLOCK_ENTITY.instantiate(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return state.get(RESIN_DROP) ? null : TickableBlockEntity.getTicker(world);
    }

    @Override
    protected void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack, boolean dropExperience) {
        super.onStacksDropped(state, world, pos, stack, dropExperience);

    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);


    }
}
