package com.craisinlord.idas.modinit;

import com.craisinlord.idas.IDAS;
import com.craisinlord.idas.world.placements.MinusEightPlacement;
import com.craisinlord.idas.world.placements.SnapToLowerNonAirPlacement;
import com.craisinlord.idas.world.placements.UnlimitedCountPlacement;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class IDASPlacements {
	public static final DeferredRegister<PlacementModifierType<?>> PLACEMENT_MODIFIER = DeferredRegister.create(Registry.PLACEMENT_MODIFIER_REGISTRY, IDAS.MODID);

	public static final RegistryObject<PlacementModifierType<MinusEightPlacement>> MINUS_EIGHT_PLACEMENT = PLACEMENT_MODIFIER.register("minus_eight_placement", () -> () -> MinusEightPlacement.CODEC);
	public static final RegistryObject<PlacementModifierType<UnlimitedCountPlacement>> UNLIMITED_COUNT = PLACEMENT_MODIFIER.register("unlimited_count", () -> () -> UnlimitedCountPlacement.CODEC);
	public static final RegistryObject<PlacementModifierType<SnapToLowerNonAirPlacement>> SNAP_TO_LOWER_NON_AIR_PLACEMENT = PLACEMENT_MODIFIER.register("snap_to_lower_non_air_placement", () -> () -> SnapToLowerNonAirPlacement.CODEC);
}
