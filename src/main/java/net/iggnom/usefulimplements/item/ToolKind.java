package net.iggnom.usefulimplements.item;

import java.util.Set;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

public enum ToolKind {
    PICKAXE(BlockTags.MINEABLE_WITH_PICKAXE, ItemTags.PICKAXES, ItemAbilities.DEFAULT_PICKAXE_ACTIONS),
    AXE(BlockTags.MINEABLE_WITH_AXE, ItemTags.AXES, ItemAbilities.DEFAULT_AXE_ACTIONS),
    SHOVEL(BlockTags.MINEABLE_WITH_SHOVEL, ItemTags.SHOVELS, ItemAbilities.DEFAULT_SHOVEL_ACTIONS);

    private final TagKey<Block> mineableBlocks;
    private final TagKey<Item> itemTag;
    private final Set<ItemAbility> abilities;

    ToolKind(TagKey<Block> mineableBlocks, TagKey<Item> itemTag, Set<ItemAbility> abilities) {
        this.mineableBlocks = mineableBlocks;
        this.itemTag = itemTag;
        this.abilities = abilities;
    }

    public TagKey<Block> getMineableBlocks() {
        return this.mineableBlocks;
    }

    public TagKey<Item> getItemTag() {
        return this.itemTag;
    }

    public Set<ItemAbility> getAbilities() {
        return this.abilities;
    }
}
