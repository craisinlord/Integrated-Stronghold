package com.craisinlord.idas.modinit;

import com.craisinlord.idas.IDAS;
import com.craisinlord.idas.world.structures.GenericJigsawStructure;
import com.craisinlord.idas.world.structures.GenericNetherJigsawStructure;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


public final class IDASStructures {
    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPE = DeferredRegister.create(Registry.STRUCTURE_TYPE_REGISTRY, IDAS.MODID);

    public static RegistryObject<StructureType<GenericJigsawStructure>> GENERIC_JIGSAW_STRUCTURE = STRUCTURE_TYPE.register("idas_jigsaw_structure", () -> () -> GenericJigsawStructure.CODEC);
    public static RegistryObject<StructureType<GenericNetherJigsawStructure>> GENERIC_NETHER_JIGSAW_STRUCTURE = STRUCTURE_TYPE.register("idas_nether_jigsaw_structure", () -> () -> GenericNetherJigsawStructure.CODEC);
}
