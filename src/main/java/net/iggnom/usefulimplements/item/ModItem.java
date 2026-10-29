package net.iggnom.usefulimplements.item;

import net.iggnom.usefulimplements.UsefulImplements;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItem {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(UsefulImplements.MOD_ID);

    public static final DeferredItem<DolabraItem> GOLDEN_DOLABRA = ITEMS.register("golden_dolabra",
            () -> new DolabraItem(Tiers.GOLD, 6.0f, -3.0f, new Item.Properties()));
    public static final DeferredItem<DolabraItem> IRON_DOLABRA = ITEMS.register("iron_dolabra",
            () -> new DolabraItem(Tiers.IRON, 6.0f, -3.1f, new Item.Properties()));
    public static final DeferredItem<DolabraItem> DIAMOND_DOLABRA = ITEMS.register("diamond_dolabra",
            () -> new DolabraItem(Tiers.DIAMOND, 5.0f, -3.0f, new Item.Properties()));
    public static final DeferredItem<DolabraItem> NETHERITE_DOLABRA = ITEMS.register("netherite_dolabra",
            () -> new DolabraItem(Tiers.NETHERITE, 5.0f, -3.0f, new Item.Properties().fireResistant()));

    public static final DeferredItem<MattockItem> GOLDEN_MATTOCK = ITEMS.register("golden_mattock",
            () -> new MattockItem(Tiers.GOLD, 1.0f, -2.8f, new Item.Properties()));
    public static final DeferredItem<MattockItem> IRON_MATTOCK = ITEMS.register("iron_mattock",
            () -> new MattockItem(Tiers.IRON, 1.0f, -2.8f, new Item.Properties()));
    public static final DeferredItem<MattockItem> DIAMOND_MATTOCK = ITEMS.register("diamond_mattock",
            () -> new MattockItem(Tiers.DIAMOND, 1.0f, -2.8f, new Item.Properties()));
    public static final DeferredItem<MattockItem> NETHERITE_MATTOCK = ITEMS.register("netherite_mattock",
            () -> new MattockItem(Tiers.NETHERITE, 1.0f, -2.8f, new Item.Properties().fireResistant()));

    public static final DeferredItem<PaxelItem> GOLDEN_PAXEL = ITEMS.register("golden_paxel",
            () -> new PaxelItem(Tiers.GOLD, 6.0f, -3.0f, new Item.Properties()));
    public static final DeferredItem<PaxelItem> IRON_PAXEL = ITEMS.register("iron_paxel",
            () -> new PaxelItem(Tiers.IRON, 6.0f, -3.1f, new Item.Properties()));
    public static final DeferredItem<PaxelItem> DIAMOND_PAXEL = ITEMS.register("diamond_paxel",
            () -> new PaxelItem(Tiers.DIAMOND, 5.0f, -3.0f, new Item.Properties()));
    public static final DeferredItem<PaxelItem> NETHERITE_PAXEL = ITEMS.register("netherite_paxel",
            () -> new PaxelItem(Tiers.NETHERITE, 5.0f, -3.0f, new Item.Properties().fireResistant()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
