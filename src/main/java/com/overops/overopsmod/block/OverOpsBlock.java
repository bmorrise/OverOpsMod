package com.overops.overopsmod.block;

import com.overops.overopsmod.item.OverOpsItem;
import java.util.Arrays;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class OverOpsBlock extends Block {

  public OverOpsBlock(Properties properties) {
    super(properties);
  }

  @SuppressWarnings("deprecation")
  @Override
  public void onBlockClicked(BlockState blockState, World world, BlockPos blockPos,
      PlayerEntity playerEntity) {

    if (!world.isRemote) {
      if (!hasOverOpsItem(playerEntity)) {
        explode(world, blockPos);
      }
    }

    super.onBlockClicked(blockState, world, blockPos, playerEntity);
  }

  public void explode(World world, BlockPos blockPos) {
    placeBlock(world, blockPos, Point3D.create(0, 1, 0), Point3D.create(1, 0, 0),
        Point3D.create(-1, 0, 0), Point3D.create(0, 0, 1), Point3D.create(0, 0, -1));
  }

  private void placeBlock(World world, BlockPos blockPos, Point3D... points) {
    Arrays.stream(points).forEach(point -> {
      world.setBlockState(blockPos.add(point.getX(), point.getY(), point.getZ()),
          ModBlocks.SNEAKY_ORE.get().getDefaultState());
    });
  }

  private boolean hasOverOpsItem(PlayerEntity playerEntity) {
    Iterator<ItemStack> iterator = playerEntity.getHeldEquipment().iterator();

    if (iterator.hasNext()) {
      ItemStack itemStack = iterator.next();
      return itemStack.getItem() instanceof OverOpsItem;
    }

    return false;
  }

  public static class Point3D {

    private int x;
    private int y;
    private int z;

    public Point3D(int x, int y, int z) {
      this.x = x;
      this.y = y;
      this.z = z;
    }

    public int getX() {
      return x;
    }

    public void setX(int x) {
      this.x = x;
    }

    public int getY() {
      return y;
    }

    public void setY(int y) {
      this.y = y;
    }

    public int getZ() {
      return z;
    }

    public void setZ(int z) {
      this.z = z;
    }

    public static Point3D create(int deltaX, int deltaY, int deltaZ) {
      return new Point3D(deltaX, deltaY, deltaZ);
    }
  }
}
