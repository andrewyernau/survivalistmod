package net.andrewyernau.survivalist.item.custom;

import net.andrewyernau.survivalist.block.ModBlocks;
import net.andrewyernau.survivalist.sound.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

import java.util.Map;

public class StoneKnifeItem extends ToolItem {
    public static final Map<Block,Block> KNIFE_MAP = Map.of(
            Blocks.ACACIA_LOG, ModBlocks.CUT_ACACIA_LOG,
            Blocks.BIRCH_LOG, ModBlocks.CUT_BIRCH_LOG,
            Blocks.CHERRY_LOG,ModBlocks.CUT_CHERRY_LOG,
            Blocks.DARK_OAK_LOG,ModBlocks.CUT_DARK_OAK_LOG,
            Blocks.OAK_LOG,ModBlocks.CUT_OAK_LOG,
            Blocks.SPRUCE_LOG,ModBlocks.CUT_SPRUCE_LOG,
            Blocks.JUNGLE_LOG,ModBlocks.CUT_JUNGLE_LOG,
            Blocks.MANGROVE_LOG, ModBlocks.CUT_MANGROVE_LOG
    );

    public StoneKnifeItem(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    public static AttributeModifiersComponent createAttributeModifiers(ToolMaterial material, int baseAttackDamage, float attackSpeed) {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(
                                BASE_ATTACK_DAMAGE_MODIFIER_ID, (double)((float)baseAttackDamage + material.getAttackDamage()), EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, (double)attackSpeed, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .build();
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        Block targetBlock = world.getBlockState(context.getBlockPos()).getBlock();

        if(KNIFE_MAP.containsKey(targetBlock)){
            if(!world.isClient()){
                world.setBlockState(context.getBlockPos(),KNIFE_MAP.get(targetBlock).getDefaultState());

                context.getStack().damage(1,((ServerWorld) world),((ServerPlayerEntity) context.getPlayer()),
                        item -> context.getPlayer().sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));

                world.playSound(null,context.getBlockPos(), ModSounds.WOOD_SAW, SoundCategory.BLOCKS,1f,1f);
            }
        }
        return ActionResult.SUCCESS;
    }
}
