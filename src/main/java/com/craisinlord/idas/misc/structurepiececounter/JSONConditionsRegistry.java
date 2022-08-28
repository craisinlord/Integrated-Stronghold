package com.craisinlord.idas.misc.structurepiececounter;

import com.mojang.serialization.Lifecycle;
import com.craisinlord.idas.IDAS;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.WritableRegistry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public final class JSONConditionsRegistry {
    private JSONConditionsRegistry() {}

    public static final ResourceKey<Registry<Supplier<Boolean>>> IDAS_JSON_CONDITIONS_KEY = ResourceKey.createRegistryKey(new ResourceLocation(IDAS.MODID, "json_conditions"));
    public static final Registry<Supplier<Boolean>> IDAS_JSON_CONDITIONS_REGISTRY = createRegistry(IDAS_JSON_CONDITIONS_KEY);

    public static void registerTestJSONCondition() {
        // Registers a condition for testing purposes.
        Registry.REGISTRY.getOptional(new ResourceLocation(IDAS.MODID, "idas_json_conditions"))
            .ifPresent(registry -> Registry.register(
                (Registry<Supplier<Boolean>>)registry,
                new ResourceLocation(IDAS.MODID, "idas_test"),
                () -> false));
    }

    private static <T, R extends Registry<T>> R createRegistry(ResourceKey<R> resourceKey) {
        return ((WritableRegistry<R>)Registry.REGISTRY).register(resourceKey, (R)new MappedRegistry<T>(resourceKey, Lifecycle.stable(), null), Lifecycle.stable()).value();
    }
}
