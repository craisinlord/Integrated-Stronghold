package com.craisinlord.idas.misc.maptrades;

import com.craisinlord.idas.IDAS;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StructureMapManager extends SimpleJsonResourceReloadListener {
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().setLenient().disableHtmlEscaping().excludeFieldsWithoutExposeAnnotation().create();

    public Map<String, List<VillagerMapObj>> VILLAGER_MAP_TRADES = new HashMap<>();
    public Map<WanderingTraderMapObj.TRADE_TYPE, List<WanderingTraderMapObj>> WANDERING_TRADER_MAP_TRADES = new HashMap<>();

    public StructureMapManager() {
        super(GSON, "idas_structure_map_trades");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> loader, ResourceManager manager, ProfilerFiller profiler) {
        ImmutableMap.Builder<String, List<VillagerMapObj>> builderVillager = ImmutableMap.builder();
        ImmutableMap.Builder<WanderingTraderMapObj.TRADE_TYPE, List<WanderingTraderMapObj>> builderWandering = ImmutableMap.builder();
        loader.forEach((fileIdentifier, jsonElement) -> {
            try {
                StructureMapCollectionObj spawnerMobEntries = GSON.fromJson(jsonElement, StructureMapCollectionObj.class);
                builderVillager.putAll(spawnerMobEntries.villagerMaps);
                builderWandering.putAll(spawnerMobEntries.wanderingTraderMap);
            }
            catch (Exception e) {
                IDAS.LOGGER.error("IDAS Error: Couldn't parse structure map file {}", fileIdentifier, e);
            }
        });
        VILLAGER_MAP_TRADES =  builderVillager.build();
        WANDERING_TRADER_MAP_TRADES =  builderWandering.build();
    }
}
