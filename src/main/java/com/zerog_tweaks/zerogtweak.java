package com.zerog_tweaks;

import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(ZeroGTweak.MODID)
public class ZeroGTweak {
    public static final String MODID = "zerogtweaks";
    private static final Logger LOGGER = LogUtils.getLogger();

    // Register Blocks and Items
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // Block and Item Definitions
    public static final DeferredBlock<Block> AUSIOUM_BLOCK = BLOCKS.registerSimpleBlock("ausioum_block", 
        BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    public static final DeferredItem<BlockItem> AUSIOUM_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("ausioum_block", AUSIOUM_BLOCK);
    public static final DeferredItem<Item> AUSIOUM_ITEM = ITEMS.registerSimpleItem("ausioum_item", 
        new Item.Properties());

    // Custom Creative Tab
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ZEROG_TWEAKS_TAB = CREATIVE_MODE_TABS.register("zerogtweaks_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.zerogtweaks_tab"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> AUSIOUM_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(AUSIOUM_ITEM.get());
                output.accept(AUSIOUM_BLOCK_ITEM.get());
            }).build());

    public ZeroGTweak(IEventBus modEventBus, ModContainer modContainer) {
        // Register events and registries
        modEventBus.addListener(this::commonSetup);
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        NeoForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addItemsToCreativeTabs);

        // Register configuration
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Common setup initialized for {}", MODID);
    }

    private void addItemsToCreativeTabs(BuildCreativeModeTabContentsEvent event) {
        // Add items to vanilla creative tabs
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(AUSIOUM_BLOCK_ITEM.get());
        }
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(AUSIOUM_ITEM.get());
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Server starting for mod {}", MODID);
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("Client setup initialized for {}", MODID);
            LOGGER.info("Player name: {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
