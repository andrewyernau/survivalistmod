package net.andrewyernau.survivalist.sound;

import net.andrewyernau.survivalist.Survivalist;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent WOOD_SAW = registerSoundEvent("wood_saw");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(Survivalist.MOD_ID, name);

        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerModSounds() {
        Survivalist.LOGGER.info("Registering Mod Sounds for " + Survivalist.MOD_ID);
    }
}
