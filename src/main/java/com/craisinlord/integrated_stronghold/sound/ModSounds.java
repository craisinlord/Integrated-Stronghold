package com.craisinlord.integrated_stronghold.sound;

import com.craisinlord.integrated_stronghold.IntegratedStronghold;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, IntegratedStronghold.MODID);

    public static RegistryObject<SoundEvent> FORLORN = registerSoundEvent("forlorn");
    public static RegistryObject<SoundEvent> SIGHT = registerSoundEvent("sight");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(IntegratedStronghold.MODID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
