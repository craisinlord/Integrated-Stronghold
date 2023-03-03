package com.craisinlord.integrated_stronghold;

import com.craisinlord.integrated_stronghold.item.ModItems;
import com.craisinlord.integrated_stronghold.modinit.IntegratedStrongholdProcessors;
import com.craisinlord.integrated_stronghold.modinit.IntegratedStrongholdStructurePlacementType;
import com.craisinlord.integrated_stronghold.sound.ModSounds;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(IntegratedStronghold.MODID)
public class IntegratedStronghold {
    public static final String MODID = "integrated_stronghold";
    public static final Logger LOGGER = LogManager.getLogger();

    public IntegratedStronghold() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModSounds.register(eventBus);
        eventBus.addListener(this::setup);

        // Register the setup method for modloading
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::setup);
        IntegratedStrongholdProcessors.STRUCTURE_PROCESSOR.register(modEventBus);
        IntegratedStrongholdStructurePlacementType.STRUCTURE_PLACEMENT_TYPE.register(modEventBus);
    }

    public void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
        });
    }
}
