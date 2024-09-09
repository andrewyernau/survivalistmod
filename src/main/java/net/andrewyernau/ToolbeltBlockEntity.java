package net.andrewyernau;

import static com.github.scillman.minecraft.toolbelt.ToolbeltMain.NUMBER_ID;

import org.jetbrains.annotations.Nullable;

import com.github.scillman.minecraft.toolbelt.ToolbeltMain;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ToolbeltBlockEntity extends BlockEntity implements ImplementedInventory
{
    private static final int DEFAULT_NUMBER = 7;
    public int number = DEFAULT_NUMBER;

    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(2, ItemStack.EMPTY);

    public ToolbeltBlockEntity(BlockPos pos, BlockState state)
    {
        super(ToolbeltMain.TOOLBELT_BLOCK_ENTITY, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems()
    {
        return this.items;
    }

    @Override
    public void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapper)
    {
        nbt.putInt(NUMBER_ID, number);
        Inventories.writeNbt(nbt, this.items, false, wrapper);
        super.writeNbt(nbt, wrapper);
    }

    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapper)
    {
        super.readNbt(nbt, wrapper);
        Inventories.readNbt(nbt, this.items, wrapper);
        this.number = nbt.getInt(NUMBER_ID);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket()
    {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public final NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup wrapper)
    {
        return new NbtCompound();
    }

    public static void tick(World world, BlockPos pos, BlockState state, ToolbeltBlockEntity blockEntity)
    {
        // Perform an action...
    }

    @Override
    protected void addComponents(ComponentMap.Builder componentMapBuilder)
    {
        componentMapBuilder.add(ToolbeltMain.NUMBER, this.number);
        componentMapBuilder.add(DataComponentTypes.CONTAINER, ContainerComponent.fromStacks(this.items));
    }

    @Override
    protected void readComponents(BlockEntity.ComponentsAccess components)
    {
        //components.getOrDefault(DataComponentTypes.CONTAINER, ContainerComponent.DEFAULT).copyTo(this.items);

        @Nullable ContainerComponent container = components.get(DataComponentTypes.CONTAINER);
        if (container != null)
        {
            container.copyTo(this.items);
        }
        else
        {
            this.items.clear();
        }

        int number = components.getOrDefault(ToolbeltMain.NUMBER, -1);
        if (number != -1)
        {
            this.number = number;
        }
    }
}
