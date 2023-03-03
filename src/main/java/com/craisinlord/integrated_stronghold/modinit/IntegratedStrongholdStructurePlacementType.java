package com.craisinlord.integrated_stronghold.modinit;

import com.craisinlord.integrated_stronghold.IntegratedStronghold;
import com.craisinlord.integrated_stronghold.world.placement.IntegratedStrongholdPlacement;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


public final class IntegratedStrongholdStructurePlacementType {
    public static final DeferredRegister<StructurePlacementType<?>> STRUCTURE_PLACEMENT_TYPE = DeferredRegister.create(Registry.STRUCTURE_PLACEMENT_TYPE_REGISTRY, IntegratedStronghold.MODID);

    public static final RegistryObject<StructurePlacementType<IntegratedStrongholdPlacement>> INTEGRATED_STRONGHOLD_PLACEMENT = STRUCTURE_PLACEMENT_TYPE.register("stronghold", () -> () -> IntegratedStrongholdPlacement.CODEC);
}
