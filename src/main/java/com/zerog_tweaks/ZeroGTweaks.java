package com.zerog_tweaks;

import com.mojang.logging.LogUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(ZeroGTweaks.MODID)
public class ZeroGTweaks {
    public static final String MODID = "zerogtweaks";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MODID, Block.class);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MODID, Item.class);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(MODID, CreativeModeTab.class);

    public static final DeferredHolder<Block> AUSIOUM_BLOCK = BLOCKS.register("ausioum_block",
        () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE))
    );
    public static final DeferredHolder<Item> AUSIOUM_BLOCK_ITEM = ITEMS.register("ausioum_block",
        () -> new BlockItem(AUSIOUM_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredHolder<Item> AUSIOUM_ITEM = ITEMS.register("ausioum_item",
        () -> new Item(new Item.Properties())
    );

    public static final DeferredHolder<CreativeModeTab> ZEROG_TWEAKS_TAB = CREATIVE_MODE_TABS.register("zerogtweaks_tab",
        () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.zerogtweaks"))
            .icon(() -> AUSIOUM_ITEM.get().getDefaultInstance())
            .displayItems((params, output) -> {
                output.accept(AUSIOUM_BLOCK_ITEM.get());
                output.accept(AUSIOUM_ITEM.get());
            }).build()
    );

    public ZeroGTweaks() {
        var modEventBus = NeoForge.EVENT_BUS.getModEventBus();
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        LOGGER.info("ZeroG Tweaks mod initialized!");
    }
}
