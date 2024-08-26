package net.andrewyernau.survivalist.mixin;

import net.minecraft.item.FireworkRocketItem;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FireworkRocketItem.class)
public abstract class FireworkStackSizeMixin {
    @ModifyVariable (method = "<init>", at = @At("HEAD"), argsOnly = true)
    private static Item.Settings modified(Item.Settings settings) {
        settings.maxCount(4);
        return settings;
    }
}