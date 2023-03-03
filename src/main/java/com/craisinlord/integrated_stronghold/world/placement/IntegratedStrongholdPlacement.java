package com.craisinlord.integrated_stronghold.world.placement;

import com.craisinlord.integrated_api.modinit.IntegratedAPIStructurePlacementType;
import com.craisinlord.integrated_stronghold.modinit.IntegratedStrongholdStructurePlacementType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Vec3i;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;

import java.util.Optional;

public class IntegratedStrongholdPlacement extends RandomSpreadStructurePlacement {
    public static final Codec<IntegratedStrongholdPlacement> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Vec3i.offsetCodec(16).optionalFieldOf("locate_offset", Vec3i.ZERO).forGetter(IntegratedStrongholdPlacement::locateOffset),
            FrequencyReductionMethod.CODEC.optionalFieldOf("frequency_reduction_method", FrequencyReductionMethod.DEFAULT).forGetter(IntegratedStrongholdPlacement::frequencyReductionMethod),
            Codec.floatRange(0.0F, 1.0F).optionalFieldOf("frequency", 1.0F).forGetter(IntegratedStrongholdPlacement::frequency),
            ExtraCodecs.NON_NEGATIVE_INT.fieldOf("salt").forGetter(IntegratedStrongholdPlacement::salt),
            ExclusionZone.CODEC.optionalFieldOf("exclusion_zone").forGetter(IntegratedStrongholdPlacement::exclusionZone),
            ExtraCodecs.NON_NEGATIVE_INT.fieldOf("spacing").forGetter(IntegratedStrongholdPlacement::spacing),
            ExtraCodecs.NON_NEGATIVE_INT.fieldOf("separation").forGetter(IntegratedStrongholdPlacement::separation),
            RandomSpreadType.CODEC.optionalFieldOf("spread_type", RandomSpreadType.LINEAR).forGetter(IntegratedStrongholdPlacement::spreadType),
            ExtraCodecs.NON_NEGATIVE_INT.fieldOf("chunk_distance_to_first_ring").forGetter(IntegratedStrongholdPlacement::chunkDistanceToFirstRing),
            ExtraCodecs.NON_NEGATIVE_INT.fieldOf("ring_chunk_thickness").forGetter(IntegratedStrongholdPlacement::ringChunkThickness),
            ExtraCodecs.NON_NEGATIVE_INT.optionalFieldOf("max_ring_section").forGetter(IntegratedStrongholdPlacement::maxRingSection)
    ).apply(instance, instance.stable(IntegratedStrongholdPlacement::new)));

    private final int chunkDistanceToFirstRing;
    private final int ringChunkThickness;
    private final Optional<Integer> maxRingSection;

    public IntegratedStrongholdPlacement(Vec3i locateOffset,
                                         FrequencyReductionMethod frequencyReductionMethod,
                                         Float frequency,
                                         Integer salt,
                                         Optional<ExclusionZone> exclusionZone,
                                         Integer spacing,
                                         Integer separation,
                                         RandomSpreadType randomSpreadType,
                                         Integer chunkDistanceToFirstRing,
                                         Integer ringChunkThickness,
                                         Optional<Integer> maxRingSection) {
        super(locateOffset, frequencyReductionMethod, frequency, salt, exclusionZone, spacing, separation, randomSpreadType);
        this.chunkDistanceToFirstRing = chunkDistanceToFirstRing;
        this.ringChunkThickness = ringChunkThickness;
        this.maxRingSection = maxRingSection;
    }

    @Override
    protected boolean isPlacementChunk(ChunkGenerator chunkGenerator, RandomState randomState, long seed, int chunkX, int chunkZ) {
        ChunkPos chunkPos = this.getPotentialStructureChunk(seed, chunkX, chunkZ);
        if (chunkPos.x == chunkX && chunkPos.z == chunkZ) {
            int chunkDistance = (int) Math.sqrt((chunkX * chunkX) + (chunkZ * chunkZ));

            // Offset the distance so that the first ring is closer to spawn
            int shiftedChunkDistance = chunkDistance + (ringChunkThickness - chunkDistanceToFirstRing);

            // Determine which ring we are in.
            // Non-stronghold rings are even number ringSection.
            // Stronghold rings are odd number ringSection.
            int ringSection = shiftedChunkDistance / ringChunkThickness;

            // Limit number of rings, if max setting is present.
            if (maxRingSection.isPresent()) {
                if (ringSection > maxRingSection.get()) {
                    return false;
                }
            }

            // Only spawn strongholds on odd number sections
            return ringSection % 2 == 1;
        }
        return false;
    }

    @Override
    public StructurePlacementType<?> type() {
        return IntegratedStrongholdStructurePlacementType.INTEGRATED_STRONGHOLD_PLACEMENT.get();
    }

    public int chunkDistanceToFirstRing() {
        return chunkDistanceToFirstRing;
    }

    public int ringChunkThickness() {
        return ringChunkThickness;
    }

    public Optional<Integer> maxRingSection() {
        return maxRingSection;
    }
}
