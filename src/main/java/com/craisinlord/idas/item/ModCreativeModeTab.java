package com.craisinlord.idas.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab IDAS_TAB = new CreativeModeTab("idastab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.MUSIC_DISC_SLITHER.get());
        }
    };
}