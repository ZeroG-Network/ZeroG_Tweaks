package com.zerog_tweaks;

import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.NeoForge;
import net.neoforged.neoforge.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

public class ZeroGTweaks {

    public static final String MODID = "zerogtweaks";
    private static final Logger LOGGER = LogUtils.getLogger();

    // Register Blocks and Items
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // Block and Item Definitions
    public static final DeferredHolder<Block, ?> AUSIOUM_BLOCK = BLOCKS.register("ausioum_block", 
        () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));
    public static final DeferredHolder<Item, ?> AUSIOUM_BLOCK_ITEM = ITEMS.register("ausioum_block", 
        () -> new BlockItem(AUSIOUM_BLOCK.get(), new Item.Properties()));
    public static final DeferredHolder<Item, ?> AUSIOUM_ITEM = ITEMS.register("ausioum_item", 
        () -> new Item(new Item.Properties()));

    // Custom Creative Tab
    public static final DeferredHolder<CreativeModeTab, ?> ZEROG_TWEAKS_TAB = CREATIVE_MODE_TABS.register("zerogtweaks_tab", 
        () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.zerogtweaks_tab"))
            .icon(() -> AUSIOUM_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(AUSIOUM_ITEM.get());
                output.accept(AUSIOUM_BLOCK_ITEM.get());
            }).build());

    public ZeroGTweaks() {
        var modEventBus = NeoForge.EVENT_BUS;

        // Register DeferredRegisters
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        // Event Listeners
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Common setup initialized for {}", MODID);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("Client setup initialized for {}", MODID);
        LOGGER.info("Player name: {}", Minecraft.getInstance().getUser().getName());
    }
}
