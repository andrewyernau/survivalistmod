package net.andrewyernau.survivalist.block.entities;

import net.andrewyernau.survivalist.Survivalist;
import net.andrewyernau.survivalist.block.BlockEntityTypeInit;
import net.andrewyernau.survivalist.block.custom.CutLogBlock;
import net.andrewyernau.survivalist.item.ModItems;
import net.andrewyernau.survivalist.util.TickableBlockEntity;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

import static net.andrewyernau.survivalist.block.custom.CutLogBlock.RESIN_DROP;

public class CutLogBlockEntity extends BlockEntity implements BlockEntityProvider, TickableBlockEntity {

    private final int MAX_TICK = 6000;
    private int counter = 0;

    public CutLogBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityTypeInit.CUT_LOG_BLOCK_ENTITY, pos, state);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return BlockEntityTypeInit.CUT_LOG_BLOCK_ENTITY.instantiate(pos, state);
    }

    public int getCounter() {
        return counter;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        var modidDataInt = new NbtCompound();
        var modidDataBool = new NbtCompound();
        modidDataInt.putInt("counter", this.counter);
        modidDataBool.putBoolean("drop",this.getCachedState().get(RESIN_DROP));
        nbt.put(Survivalist.MOD_ID, modidDataInt);
        nbt.put(Survivalist.MOD_ID,modidDataBool);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        if (nbt.contains(Survivalist.MOD_ID, NbtElement.COMPOUND_TYPE)) {
            this.counter = nbt.contains("counter", NbtElement.INT_TYPE) ? nbt.getInt("counter") : 0;
        }
        if (nbt.contains(Survivalist.MOD_ID, NbtElement.COMPOUND_TYPE)){
            boolean drop = nbt.getBoolean("drop");
            assert this.world != null;
            this.world.setBlockState(this.pos, this.getCachedState().with(CutLogBlock.RESIN_DROP, drop), 3);
        }
    }

    public void summonResinItem() {
        if (this.world instanceof ServerWorld server) {
            Vec3d vec3d = Vec3d.add(pos, 0.5, 0.01, 0.5).addRandom(server.random, 0.7F);
            ItemEntity itemEntity = new ItemEntity(server, vec3d.getX(), vec3d.getY(), vec3d.getZ(), new ItemStack(ModItems.RESIN));
            itemEntity.setToDefaultPickupDelay();
            server.spawnEntity(itemEntity);
        }
    }

    @Override
    public void tick() {
        if (this.world == null || this.world.isClient) return;

        if (world.getBlockState(pos).getBlock() instanceof CutLogBlock cutLogBlock) {
            if (++this.counter >= MAX_TICK) {
                this.counter = 0;
                world.setBlockState(pos, world.getBlockState(pos).with(CutLogBlock.RESIN_DROP, true), 3);
            }
        }
    }
}
