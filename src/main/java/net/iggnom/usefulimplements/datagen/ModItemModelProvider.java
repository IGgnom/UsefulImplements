package net.iggnom.usefulimplements.datagen;

import net.iggnom.usefulimplements.UsefulImplements;
import net.iggnom.usefulimplements.item.ModItem;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, UsefulImplements.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        handHeldItem(ModItem.GOLDEN_DOLABRA, "dolabra");
        handHeldItem(ModItem.IRON_DOLABRA, "dolabra");
        handHeldItem(ModItem.DIAMOND_DOLABRA, "dolabra");
        handHeldItem(ModItem.NETHERITE_DOLABRA, "dolabra");
        handHeldItem(ModItem.GOLDEN_MATTOCK, "mattock");
        handHeldItem(ModItem.IRON_MATTOCK, "mattock");
        handHeldItem(ModItem.DIAMOND_MATTOCK, "mattock");
        handHeldItem(ModItem.NETHERITE_MATTOCK, "mattock");
        handHeldItem(ModItem.GOLDEN_PAXEL, "paxel");
        handHeldItem(ModItem.IRON_PAXEL, "paxel");
        handHeldItem(ModItem.DIAMOND_PAXEL, "paxel");
        handHeldItem(ModItem.NETHERITE_PAXEL, "paxel");
    }

    private ItemModelBuilder handHeldItem(DeferredItem<?> item, String textureName) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(UsefulImplements.MOD_ID, "item/" + textureName));
    }
}
