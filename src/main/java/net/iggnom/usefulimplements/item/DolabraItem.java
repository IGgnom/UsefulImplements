package net.iggnom.usefulimplements.item;

import java.util.Set;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;

public class DolabraItem extends MultiToolItem {
    public DolabraItem(Tier tier, float attackDamage, float attackSpeed, Item.Properties properties) {
        super(tier, Set.of(ToolKind.PICKAXE, ToolKind.AXE), attackDamage, attackSpeed, properties);
    }
}
