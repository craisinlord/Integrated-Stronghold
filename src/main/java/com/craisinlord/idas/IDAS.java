package com.craisinlord.idas;

import com.craisinlord.idas.item.ModItems;
import com.craisinlord.idas.misc.maptrades.StructureMapManager;
import com.craisinlord.idas.misc.maptrades.StructureMapTradesEvents;
import com.craisinlord.idas.misc.pooladditions.PoolAdditionMerger;
import com.craisinlord.idas.misc.structurepiececounter.JSONConditionsRegistry;
import com.craisinlord.idas.misc.structurepiececounter.StructurePieceCountsManager;
import com.craisinlord.idas.modinit.*;
import com.craisinlord.idas.sound.ModSounds;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(IDAS.MODID)
public class IDAS {
    public static final String MODID = "idas";
    public static final Logger LOGGER = LogManager.getLogger();

    public static StructureMapManager structureMapManager = new StructureMapManager();
    public static StructurePieceCountsManager structurePieceCountsManager = new StructurePieceCountsManager();

    public IDAS() {
        IDASTags.initTags();
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModSounds.register(eventBus);
        eventBus.addListener(this::setup);
        // Classload and create custom registry. Other mods should add to this custom registry in FMLCommonSetupEvent.
        JSONConditionsRegistry.registerTestJSONCondition();

        // Register the setup method for modloading
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        forgeBus.addListener(this::registerDatapackListener);
        forgeBus.addListener(StructureMapTradesEvents::onVillagerTradesEvent);
        forgeBus.addListener(StructureMapTradesEvents::onWandererTradesEvent);
        forgeBus.addListener(PoolAdditionMerger::mergeAdditionPools);

        modEventBus.addListener(this::setup);
        IDASPredicates.RULE_TEST.register(modEventBus);
        IDASPredicates.POS_RULE_TEST.register(modEventBus);
        IDASStructures.STRUCTURE_TYPE.register(modEventBus);
        IDASPlacements.PLACEMENT_MODIFIER.register(modEventBus);
        IDASProcessors.STRUCTURE_PROCESSOR.register(modEventBus);
        IDASStructurePieces.STRUCTURE_PIECE.register(modEventBus);
        IDASStructurePieces.STRUCTURE_POOL_ELEMENT.register(modEventBus);
        IDASStructurePlacementType.STRUCTURE_PLACEMENT_TYPE.register(modEventBus);
    }

    public void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
        });
    }

    public void registerDatapackListener(final AddReloadListenerEvent event) {
        event.addListener(IDAS.structureMapManager);
        event.addListener(IDAS.structurePieceCountsManager);
    }
}
