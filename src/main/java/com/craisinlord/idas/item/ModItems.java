package com.craisinlord.idas.item;

import com.craisinlord.idas.IDAS;
import com.craisinlord.idas.sound.ModSounds;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, IDAS.MODID);

    public static final RegistryObject<Item> MUSIC_DISC_SLITHER = ITEMS.register("music_disc_slither",
            () -> new RecordItem(4, ModSounds.SLITHER,
                    new Item.Properties().tab(ModCreativeModeTab.IDAS_TAB).stacksTo(1).rarity(Rarity.RARE), 2400));

    public static final RegistryObject<Item> MUSIC_DISC_CALIDUM = ITEMS.register("music_disc_calidum",
            () -> new RecordItem(4, ModSounds.CALIDUM,
                    new Item.Properties().tab(ModCreativeModeTab.IDAS_TAB).stacksTo(1).rarity(Rarity.RARE), 3880));

    public static final RegistryObject<Item> DISC_FRAGMENT_SLITHER = ITEMS.register("disc_fragment_slither",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.IDAS_TAB).rarity(Rarity.RARE)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}