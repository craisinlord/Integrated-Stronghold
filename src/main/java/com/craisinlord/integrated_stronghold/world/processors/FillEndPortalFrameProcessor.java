package com.craisinlord.integrated_stronghold.world.processors;

import com.craisinlord.integrated_stronghold.modinit.IntegratedStrongholdProcessors;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EndPortalFrameBlock;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

/**
 * FOR RANDOMIZING THE END PORTAL FRAMES WITH EYES OF ENDER
 */
public class FillEndPortalFrameProcessor extends StructureProcessor {
    public static final FillEndPortalFrameProcessor INSTANCE = new FillEndPortalFrameProcessor();
    public static final Codec<FillEndPortalFrameProcessor> CODEC = Codec.unit(() -> INSTANCE);

    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader levelReader,
                                                             BlockPos jigsawPiecePos,
                                                             BlockPos jigsawPieceBottomCenterPos,
                                                             StructureTemplate.StructureBlockInfo blockInfoLocal,
                                                             StructureTemplate.StructureBlockInfo blockInfoGlobal,
                                                             StructurePlaceSettings structurePlacementData) {
        if (blockInfoGlobal.state.is(Blocks.END_PORTAL_FRAME)) {
            RandomSource randomSource = structurePlacementData.getRandom(blockInfoGlobal.pos);
            if (randomSource.nextFloat() < 0.1f)
                blockInfoGlobal = new StructureTemplate.StructureBlockInfo(
                        blockInfoGlobal.pos,
                        blockInfoGlobal.state.setValue(EndPortalFrameBlock.HAS_EYE, true),
                        blockInfoGlobal.nbt);
        }
        return blockInfoGlobal;
    }
    protected StructureProcessorType<?> getType() {
        return IntegratedStrongholdProcessors.FILL_END_PORTAL_FRAME_PROCESSOR.get();
    }
}
