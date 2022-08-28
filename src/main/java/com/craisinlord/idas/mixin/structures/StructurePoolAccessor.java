package com.craisinlord.idas.mixin.structures;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(StructureTemplatePool.class)
public interface StructurePoolAccessor {
    @Accessor("rawTemplates")
    List<Pair<StructurePoolElement, Integer>> idas_getRawTemplates();

    @Mutable
    @Accessor("rawTemplates")
    void idas_setRawTemplates(List<Pair<StructurePoolElement, Integer>> elementCounts);

    @Accessor("templates")
    ObjectArrayList<StructurePoolElement> idas_getTemplates();

    @Mutable
    @Accessor("templates")
    void idas_setTemplates(ObjectArrayList<StructurePoolElement> elements);
}
