package com.craisinlord.integrated_stronghold.sound;

import com.craisinlord.integrated_stronghold.IntegratedStronghold;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ISSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, IntegratedStronghold.MODID);

    public static RegistryObject<SoundEvent> FORLORN = registerSoundEvents("forlorn");
    public static RegistryObject<SoundEvent> SIGHT = registerSoundEvents("sight");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(IntegratedStronghold.MODID, name)));
    }
}
