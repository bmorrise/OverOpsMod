package com.overops.overopsmod.item;

import com.overops.overopsmod.block.OverOpsBlock;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.CactusBlock;
import net.minecraft.block.DeadBushBlock;
import net.minecraft.block.SandBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OverOpsItem extends Item {

  private static final Logger LOGGER = LogManager.getLogger();

  public OverOpsItem(Properties properties) {
    super(properties);
  }

  private static Map<Class<? extends Block>, Number> possibleBlocks = new HashMap<>();

  static {
    possibleBlocks.put(Block.class, 1);
    possibleBlocks.put(DeadBushBlock.class, 1);
    possibleBlocks.put(SandBlock.class, 1);
  }

  @Override
  public ActionResultType onItemUse(ItemUseContext context) {
    World world = context.getWorld();

    if (!world.isRemote && world.getBlockState(context.getPos())
        .getBlock() instanceof OverOpsBlock) {
      world.destroyBlock(context.getPos(), false);
    } else {
      PlayerEntity playerEntity = Objects.requireNonNull(context.getPlayer());
      Block block = world.getBlockState(context.getPos()).getBlock();
      Number seconds = possibleBlocks.get(block.getClass());

      playerEntity.setFire(seconds.intValue());
    }

    return super.onItemUse(context);
  }
}
