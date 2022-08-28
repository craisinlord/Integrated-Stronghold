package com.craisinlord.idas.modinit;

import com.craisinlord.idas.IDAS;
import com.craisinlord.idas.world.structures.pieces.MirroringSingleJigsawPiece;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


public final class IDASStructurePieces {
    public static final DeferredRegister<StructurePoolElementType<?>> STRUCTURE_POOL_ELEMENT = DeferredRegister.create(Registry.STRUCTURE_POOL_ELEMENT_REGISTRY, IDAS.MODID);
    public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECE = DeferredRegister.create(Registry.STRUCTURE_PIECE_REGISTRY, IDAS.MODID);

    public static final RegistryObject<StructurePoolElementType<MirroringSingleJigsawPiece>> MIRROR_SINGLE = STRUCTURE_POOL_ELEMENT.register("mirroring_single_pool_element", () -> () -> MirroringSingleJigsawPiece.CODEC);
}

