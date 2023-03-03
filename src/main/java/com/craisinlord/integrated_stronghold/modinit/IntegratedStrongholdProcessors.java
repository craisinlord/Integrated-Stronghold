package com.craisinlord.integrated_stronghold.modinit;

import com.craisinlord.integrated_api.world.processors.WaterloggingFixProcessor;
import com.craisinlord.integrated_stronghold.IntegratedStronghold;
import com.craisinlord.integrated_stronghold.world.processors.FillEndPortalFrameProcessor;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class IntegratedStrongholdProcessors {
    public static final DeferredRegister<StructureProcessorType<?>> STRUCTURE_PROCESSOR = DeferredRegister.create(Registry.STRUCTURE_PROCESSOR_REGISTRY, IntegratedStronghold.MODID);

    public static final RegistryObject<StructureProcessorType<FillEndPortalFrameProcessor>> FILL_END_PORTAL_FRAME_PROCESSOR = STRUCTURE_PROCESSOR.register("fill_end_portal_frame_processor", () -> () -> FillEndPortalFrameProcessor.CODEC);
    public static final RegistryObject<StructureProcessorType<WaterloggingFixProcessor>> WATERLOGGING_FIX_PROCESSOR = STRUCTURE_PROCESSOR.register("waterlogging_fix_processor", () -> () -> WaterloggingFixProcessor.CODEC);
}