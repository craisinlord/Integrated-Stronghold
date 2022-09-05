package com.craisinlord.idas.modinit;

import com.craisinlord.idas.IDAS;
import com.craisinlord.idas.world.structures.JigsawStructure;
import com.craisinlord.idas.world.structures.NetherJigsawStructure;
import com.craisinlord.idas.world.structures.OverLavaNetherStructure;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


public final class IDASStructures {
    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPE = DeferredRegister.create(Registry.STRUCTURE_TYPE_REGISTRY, IDAS.MODID);

    public static RegistryObject<StructureType<JigsawStructure>> GENERIC_JIGSAW_STRUCTURE = STRUCTURE_TYPE.register("idas_structure", () -> () -> JigsawStructure.CODEC);
    public static RegistryObject<StructureType<NetherJigsawStructure>> GENERIC_NETHER_JIGSAW_STRUCTURE = STRUCTURE_TYPE.register("idas_nether_structure", () -> () -> NetherJigsawStructure.CODEC);
    public static RegistryObject<StructureType<OverLavaNetherStructure>> OVER_LAVA_NETHER_STRUCTURE = STRUCTURE_TYPE.register("idas_over_lava_nether_structure", () -> () -> OverLavaNetherStructure.CODEC);
}
