package com.craisinlord.idas.mixin.resources;

import com.craisinlord.idas.misc.structurepiececounter.JSONConditionsRegistry;
import net.minecraft.data.BuiltinRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BuiltinRegistries.class)
public class BuiltInRegistriesMixin {

    /**
     * Creates and inits our custom registry at game startup
     */
    @Inject(method = "bootstrap()V",
            at = @At(value = "RETURN"))
    private static void idas_initCustomRegistries(CallbackInfo ci) {
        JSONConditionsRegistry.registerTestJSONCondition();
    }
}
