package net.iggnom.usefulimplements.datagen;

import java.util.concurrent.CompletableFuture;

import net.iggnom.usefulimplements.UsefulImplements;
import net.iggnom.usefulimplements.item.ModItem;
import net.iggnom.usefulimplements.item.MultiToolItem;
import net.iggnom.usefulimplements.item.ToolKind;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagsProvider.TagLookup<Block>> blockTags, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, UsefulImplements.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        for (DeferredHolder<Item, ? extends Item> entry : ModItem.ITEMS.getEntries()) {
            if (entry.get() instanceof MultiToolItem multiTool) {
                for (ToolKind toolKind : multiTool.getToolKinds()) {
                    tag(toolKind.getItemTag()).add(entry.get());
                }
            }
        }
    }
}
