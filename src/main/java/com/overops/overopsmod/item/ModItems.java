package com.overops.overopsmod.item;

import com.overops.overopsmod.OverOpsMod;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
      OverOpsMod.MOD_ID);

  public static final RegistryObject<Item> OVEROPS = ITEMS.register("overops",
      () -> new OverOpsItem(new Item.Properties().group(ModItemGroup.EXAMPLE_GROUP)));

  public static void register(IEventBus eventBus) {
    ITEMS.register(eventBus);
  }

}
