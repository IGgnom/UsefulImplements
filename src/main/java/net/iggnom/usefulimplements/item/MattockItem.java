package net.iggnom.usefulimplements.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;

import java.util.Set;

public class MattockItem extends MultiToolItem {
    public MattockItem(Tier tier, float attackDamage, float attackSpeed, Item.Properties properties) {
        super(tier, Set.of(ToolKind.PICKAXE, ToolKind.SHOVEL), attackDamage, attackSpeed, properties);
    }
}
