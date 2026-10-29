package net.iggnom.usefulimplements.item;

import net.iggnom.usefulimplements.UsefulImplements;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, UsefulImplements.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> USEFUL_IMPLEMENTS_TAB =
            CREATIVE_MODE_TABS.register("useful_implements_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.usefulimplements"))
                    .icon(() -> ModItem.DIAMOND_DOLABRA.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(ModItem.GOLDEN_DOLABRA.get());
                        output.accept(ModItem.IRON_DOLABRA.get());
                        output.accept(ModItem.DIAMOND_DOLABRA.get());
                        output.accept(ModItem.NETHERITE_DOLABRA.get());
                        output.accept(ModItem.GOLDEN_MATTOCK.get());
                        output.accept(ModItem.IRON_MATTOCK.get());
                        output.accept(ModItem.DIAMOND_MATTOCK.get());
                        output.accept(ModItem.NETHERITE_MATTOCK.get());
                        output.accept(ModItem.GOLDEN_PAXEL.get());
                        output.accept(ModItem.IRON_PAXEL.get());
                        output.accept(ModItem.DIAMOND_PAXEL.get());
                        output.accept(ModItem.NETHERITE_PAXEL.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
