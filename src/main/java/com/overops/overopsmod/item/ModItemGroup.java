package com.overops.overopsmod.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {
    public static final ItemGroup EXAMPLE_GROUP = new ItemGroup("overOpsModTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.OVEROPS.get());
        }
    };
}
