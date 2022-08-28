package com.craisinlord.idas.world.structures.placements;

import com.craisinlord.idas.modinit.IDASStructurePlacementType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.*;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;

import java.util.Optional;

public class AdvancedRandomSpread extends RandomSpreadStructurePlacement {
    public static final Codec<AdvancedRandomSpread> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Vec3i.offsetCodec(16).optionalFieldOf("locate_offset", Vec3i.ZERO).forGetter(AdvancedRandomSpread::locateOffset),
            FrequencyReductionMethod.CODEC.optionalFieldOf("frequency_reduction_method", FrequencyReductionMethod.DEFAULT).forGetter(AdvancedRandomSpread::frequencyReductionMethod),
            Codec.floatRange(0.0F, 1.0F).optionalFieldOf("frequency", 1.0F).forGetter(AdvancedRandomSpread::frequency),
            ExtraCodecs.NON_NEGATIVE_INT.fieldOf("salt").forGetter(AdvancedRandomSpread::salt),
            ExclusionZone.CODEC.optionalFieldOf("exclusion_zone").forGetter(AdvancedRandomSpread::exclusionZone),
            SuperExclusionZone.CODEC.optionalFieldOf("super_exclusion_zone").forGetter(AdvancedRandomSpread::superExclusionZone),
            Codec.intRange(0, Integer.MAX_VALUE).fieldOf("spacing").forGetter(AdvancedRandomSpread::spacing),
            Codec.intRange(0, Integer.MAX_VALUE).fieldOf("separation").forGetter(AdvancedRandomSpread::separation),
            RandomSpreadType.CODEC.optionalFieldOf("spread_type", RandomSpreadType.LINEAR).forGetter(AdvancedRandomSpread::spreadType),
            Codec.intRange(0, Integer.MAX_VALUE).optionalFieldOf("min_distance_from_world_origin").forGetter(AdvancedRandomSpread::minDistanceFromWorldOrigin)
    ).apply(instance, instance.stable(AdvancedRandomSpread::new)));

    private final int spacing;
    private final int separation;
    private final RandomSpreadType spreadType;
    private final Optional<Integer> minDistanceFromWorldOrigin;
    private final Optional<SuperExclusionZone> superExclusionZone;

    public AdvancedRandomSpread(Vec3i locationOffset,
                                FrequencyReductionMethod frequencyReductionMethod,
                                float frequency,
                                int salt,
                                Optional<ExclusionZone> exclusionZone,
                                Optional<SuperExclusionZone> superExclusionZone,
                                int spacing,
                                int separation,
                                RandomSpreadType spreadType,
                                Optional<Integer> minDistanceFromWorldOrigin
    ) {
        super(locationOffset, frequencyReductionMethod, frequency, salt, exclusionZone, spacing, separation, spreadType);
        this.spacing = spacing;
        this.separation = separation;
        this.spreadType = spreadType;
        this.minDistanceFromWorldOrigin = minDistanceFromWorldOrigin;
        this.superExclusionZone = superExclusionZone;

        if (spacing <= separation) {
            throw new RuntimeException("""
                IDAS: Spacing cannot be less or equal to separation.
                Please correct this error as there's no way to spawn this structure properly
                    Spacing: %s
                    Separation: %s.
            """.formatted(spacing, separation));
        }
    }

    @Override
    public int spacing() {
        return this.spacing;
    }

    @Override
    public int separation() {
        return this.separation;
    }

    @Override
    public RandomSpreadType spreadType() {
        return this.spreadType;
    }

    public Optional<Integer> minDistanceFromWorldOrigin() {
        return this.minDistanceFromWorldOrigin;
    }

    public Optional<SuperExclusionZone> superExclusionZone() {
        return this.superExclusionZone;
    }

    @Override
    public boolean isStructureChunk(ChunkGenerator chunkGenerator, RandomState randomState, long l, int i, int j) {
        if (!super.isStructureChunk(chunkGenerator, randomState, l, i, j)) {
            return false;
        }
        return this.superExclusionZone.isEmpty() || !this.superExclusionZone.get().isPlacementForbidden(chunkGenerator, randomState, l, i, j);
    }

    @Override
    public ChunkPos getPotentialStructureChunk(long seed, int x, int z) {
        int regionX = Math.floorDiv(x, this.spacing);
        int regionZ = Math.floorDiv(z, this.spacing);
        WorldgenRandom worldgenrandom = new WorldgenRandom(new LegacyRandomSource(0L));
        worldgenrandom.setLargeFeatureWithSalt(seed, regionX, regionZ, this.salt());
        int diff = this.spacing - this.separation;
        int offsetX = this.spreadType.evaluate(worldgenrandom, diff);
        int offsetZ = this.spreadType.evaluate(worldgenrandom, diff);
        return new ChunkPos(regionX * this.spacing + offsetX, regionZ * this.spacing + offsetZ);
    }

    @Override
    protected boolean isPlacementChunk(ChunkGenerator chunkGenerator, RandomState randomState, long seed, int x, int z) {
        if (minDistanceFromWorldOrigin.isPresent()) {
            int xBlockPos = x * 16;
            int zBlockPos = z * 16;
            if((xBlockPos * xBlockPos) + (zBlockPos * zBlockPos) <
                    (minDistanceFromWorldOrigin.get() * minDistanceFromWorldOrigin.get()))
            {
                return false;
            }
        }

        ChunkPos chunkpos = this.getPotentialStructureChunk(seed, x, z);
        return chunkpos.x == x && chunkpos.z == z;
    }

    @Override
    public StructurePlacementType<?> type() {
        return IDASStructurePlacementType.ADVANCED_RANDOM_SPREAD.get();
    }

    public record SuperExclusionZone(HolderSet<StructureSet> otherSet, int chunkCount) {
        public static final Codec<SuperExclusionZone> CODEC = RecordCodecBuilder.create(builder -> builder.group(
                RegistryCodecs.homogeneousList(Registry.STRUCTURE_SET_REGISTRY, StructureSet.DIRECT_CODEC).fieldOf("other_set").forGetter(SuperExclusionZone::otherSet),
                Codec.intRange(1, 16).fieldOf("chunk_count").forGetter(SuperExclusionZone::chunkCount)
        ).apply(builder, SuperExclusionZone::new));

        boolean isPlacementForbidden(ChunkGenerator chunkGenerator, RandomState randomState, long l, int i, int j) {
            for (Holder<StructureSet> holder : this.otherSet) {
                if (chunkGenerator.hasStructureChunkInRange(holder, randomState, l, i, j, this.chunkCount)) {
                    return true;
                }
            }

            return false;
        }
    }
}