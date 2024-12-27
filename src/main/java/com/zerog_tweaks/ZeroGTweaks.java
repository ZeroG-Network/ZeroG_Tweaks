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
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(ZeroGTweaks.MODID)
public class ZeroGTweaks {
    public static final String MODID = "zerogtweaks";
    private static final Logger LOGGER = LogUtils.getLogger();

    // Register Blocks and Items
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(ForgeRegistries.CREATIVE_MODE_TABS, MODID);

    // Block and Item Definitions
    public static final RegistryObject<Block> AUSIOUM_BLOCK = BLOCKS.register("ausioum_block",
        () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));
    public static final RegistryObject<Item> AUSIOUM_BLOCK_ITEM = ITEMS.register("ausioum_block",
        () -> new BlockItem(AUSIOUM_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> AUSIOUM_ITEM = ITEMS.register("ausioum_item",
        () -> new Item(new Item.Properties()));

    // Custom Creative Tab
    public static final RegistryObject<CreativeModeTab> ZEROG_TWEAKS_TAB = CREATIVE_MODE_TABS.register("zerogtweaks_tab",
        () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.zerogtweaks_tab"))
            .icon(() -> AUSIOUM_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(AUSIOUM_ITEM.get());
                output.accept(AUSIOUM_BLOCK_ITEM.get());
            }).build());

    public ZeroGTweaks() {
        IEventBus modEventBus = net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Common setup initialized for {}", MODID);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("Client setup initialized for {}", MODID);
    }

    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Server starting for mod {}", MODID);
    }
}
