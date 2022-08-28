package com.craisinlord.idas.modinit;

import com.craisinlord.idas.IDAS;
import com.craisinlord.idas.misc.structurepiececounter.NonFreezingMapRegistry;
import com.craisinlord.idas.world.processors.CloseOffFluidSourcesProcessor;
import com.craisinlord.idas.world.processors.WaterloggingFixProcessor;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class IDASProcessors {
    public static final DeferredRegister<StructureProcessorType<?>> STRUCTURE_PROCESSOR = DeferredRegister.create(Registry.STRUCTURE_PROCESSOR_REGISTRY, IDAS.MODID);

    public static final RegistryObject<StructureProcessorType<WaterloggingFixProcessor>> WATERLOGGING_FIX_PROCESSOR = STRUCTURE_PROCESSOR.register("waterlogging_fix_processor", () -> () -> WaterloggingFixProcessor.CODEC);
    public static final RegistryObject<StructureProcessorType<CloseOffFluidSourcesProcessor>> CLOSE_OFF_FLUID_SOURCES_PROCESSOR = STRUCTURE_PROCESSOR.register("close_off_fluid_sources_processor", () -> () -> CloseOffFluidSourcesProcessor.CODEC);
}