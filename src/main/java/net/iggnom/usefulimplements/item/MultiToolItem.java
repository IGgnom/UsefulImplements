package net.iggnom.usefulimplements.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

public class MultiToolItem extends TieredItem {
    private final Set<ToolKind> toolKinds;

    public MultiToolItem(Tier tier, Set<ToolKind> toolKinds, float attackDamage, float attackSpeed, Item.Properties properties) {
        super(tier, properties
                .attributes(DiggerItem.createAttributes(tier, attackDamage, attackSpeed))
                .component(DataComponents.TOOL, createTool(tier, toolKinds)));
        this.toolKinds = Set.copyOf(toolKinds);
    }

    private static Tool createTool(Tier tier, Set<ToolKind> toolKinds) {
        List<Tool.Rule> rules = new ArrayList<>();
        rules.add(Tool.Rule.deniesDrops(tier.getIncorrectBlocksForDrops()));
        for (ToolKind toolKind : toolKinds) {
            rules.add(Tool.Rule.minesAndDrops(toolKind.getMineableBlocks(), tier.getSpeed()));
        }
        return new Tool(rules, 1.0F, 1);
    }

    public Set<ToolKind> getToolKinds() {
        return this.toolKinds;
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return true;
    }

    @Override
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(2, attacker, EquipmentSlot.MAINHAND);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        for (ToolKind toolKind : this.toolKinds) {
            if (toolKind.getAbilities().contains(itemAbility)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (playerHasShieldUseIntent(context)) {
            return InteractionResult.PASS;
        }

        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        BlockState state = level.getBlockState(pos);

        BlockState modified = state.getToolModifiedState(context, ItemAbilities.AXE_STRIP, false);
        if (modified != null) {
            level.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
            return applyModification(context, modified);
        }

        modified = state.getToolModifiedState(context, ItemAbilities.AXE_SCRAPE, false);
        if (modified != null) {
            level.playSound(player, pos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.levelEvent(player, 3005, pos, 0);
            return applyModification(context, modified);
        }

        modified = state.getToolModifiedState(context, ItemAbilities.AXE_WAX_OFF, false);
        if (modified != null) {
            level.playSound(player, pos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.levelEvent(player, 3004, pos, 0);
            return applyModification(context, modified);
        }

        if (context.getClickedFace() != Direction.DOWN) {
            modified = state.getToolModifiedState(context, ItemAbilities.SHOVEL_FLATTEN, false);
            if (modified != null && level.getBlockState(pos.above()).isAir()) {
                level.playSound(player, pos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
                return applyModification(context, modified);
            }

            modified = state.getToolModifiedState(context, ItemAbilities.SHOVEL_DOUSE, false);
            if (modified != null) {
                if (!level.isClientSide()) {
                    level.levelEvent(null, 1009, pos, 0);
                }
                return applyModification(context, modified);
            }
        }

        return InteractionResult.PASS;
    }

    private static InteractionResult applyModification(UseOnContext context, BlockState newState) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();

        if (player instanceof ServerPlayer serverPlayer) {
            CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, stack);
        }

        if (!level.isClientSide) {
            level.setBlock(pos, newState, 11);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, newState));
            if (player != null) {
                stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    private static boolean playerHasShieldUseIntent(UseOnContext context) {
        Player player = context.getPlayer();
        return context.getHand().equals(InteractionHand.MAIN_HAND)
                && player.getOffhandItem().is(Items.SHIELD)
                && !player.isSecondaryUseActive();
    }
}
