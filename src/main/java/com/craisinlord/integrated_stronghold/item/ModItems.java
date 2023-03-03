package com.craisinlord.integrated_stronghold.item;

import com.craisinlord.integrated_stronghold.IntegratedStronghold;
import com.craisinlord.integrated_stronghold.sound.ModSounds;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, IntegratedStronghold.MODID);

    public static final RegistryObject<Item> MUSIC_DISC_SIGHT = ITEMS.register("music_disc_sight" +
                    "",
            () -> new RecordItem(7, ModSounds.SIGHT,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1).rarity(Rarity.RARE), 2700));

    public static final RegistryObject<Item> MUSIC_DISC_FORLORN = ITEMS.register("music_disc_forlorn",
            () -> new RecordItem(7, ModSounds.FORLORN,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1).rarity(Rarity.RARE), 3880));

    public static final RegistryObject<Item> DISC_FRAGMENT_SIGHT = ITEMS.register("disc_fragment_sight",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).rarity(Rarity.RARE)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}