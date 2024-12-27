package com.zerog_tweaks;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

@Mod(ZeroGTweaks.MODID)
public class ZeroGTweaks {
    public static final String MODID = "zerogtweaks";

    // Deferred Registers for NeoForge
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // Block and Item Definitions
    public static final DeferredBlock<Block> AUSIOUM_BLOCK = BLOCKS.registerSimpleBlock("ausioum_block",
            BlockBehaviour.Properties.of().mapColor(MapColor.STONE));

    public static final DeferredItem<BlockItem> AUSIOUM_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("ausioum_block", 
            AUSIOUM_BLOCK);

    public static final DeferredItem<Item> AUSIOUM_ITEM = ITEMS.registerSimpleItem("ausioum_item", 
            new Item.Properties());

    // Creative Tab
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CREATIVE_TAB = CREATIVE_MODE_TABS.register("zerogtweaks_tab", 
        () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + MODID))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> AUSIOUM_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(AUSIOUM_ITEM.get());
                output.accept(AUSIOUM_BLOCK_ITEM.get());
            }).build());

    public ZeroGTweaks(IEventBus modEventBus) {
        // Register to the mod event bus
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
    }
}