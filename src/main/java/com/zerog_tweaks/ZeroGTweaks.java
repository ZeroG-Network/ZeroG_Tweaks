package com.zerog_tweaks;

import dev.neoforged.runtime.api.Registry;
import dev.neoforged.runtime.api.NeoMod;
import dev.neoforged.runtime.api.events.lifecycle.ModLifecycleEvent;
import dev.neoforged.runtime.api.resources.ResourceKey;
import dev.neoforged.runtime.api.world.item.CreativeTab;
import dev.neoforged.runtime.api.world.item.Item;
import dev.neoforged.runtime.api.world.item.Block;
import dev.neoforged.runtime.impl.resources.ResourceLocation;

import java.util.function.Supplier;

@NeoMod(modId = ZeroGTweaks.MODID)
public class ZeroGTweaks {
    public static final String MODID = "zerogtweaks";

    // Registries
    public static final Registry<Block> BLOCKS = Registry.create(new ResourceLocation(MODID, "blocks"));
    public static final Registry<Item> ITEMS = Registry.create(new ResourceLocation(MODID, "items"));

    // Block and Item Definitions
    public static final Supplier<Block> AUSIOUM_BLOCK = BLOCKS.register("ausioum_block", () -> new Block(Block.Properties.of(Material.STONE)));
    public static final Supplier<Item> AUSIOUM_BLOCK_ITEM = ITEMS.register("ausioum_block", () -> new Item(new Item.Properties().tab(ZeroGTweaks.CREATIVE_TAB)));
    public static final Supplier<Item> AUSIOUM_ITEM = ITEMS.register("ausioum_item", () -> new Item(new Item.Properties().tab(ZeroGTweaks.CREATIVE_TAB)));

    // Creative Tab
    public static final CreativeTab CREATIVE_TAB = CreativeTab.create(new ResourceLocation(MODID, "zerogtweaks_tab"),
        () -> AUSIOUM_ITEM.get().getDefaultInstance());

    // Lifecycle Event Handling
    public ZeroGTweaks() {
        ModLifecycleEvent.INITIALIZE.register(this::onInitialize);
    }

    private void onInitialize() {
        BLOCKS.registerAll();
        ITEMS.registerAll();
        // Additional initialization logic if needed
    }
}
