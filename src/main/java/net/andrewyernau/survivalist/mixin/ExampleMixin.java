package net.andrewyernau.survivalist.mixin;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.security.auth.callback.Callback;

@Mixin(MinecraftServer.class)
public class ExampleMixin {
    @Inject(at =@At("HEAD"),method = "loadWorld")
    private void init(CallbackInfo info){

    }
}
