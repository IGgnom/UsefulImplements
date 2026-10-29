package net.iggnom.usefulimplements;

import net.iggnom.usefulimplements.item.ModCreativeModeTabs;
import net.iggnom.usefulimplements.item.ModItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

@Mod(UsefulImplements.MOD_ID)
public class UsefulImplements {
    public static final String MOD_ID = "usefulimplements";

    public UsefulImplements(IEventBus modEventBus, ModContainer modContainer) {
        ModItem.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
    }
}
