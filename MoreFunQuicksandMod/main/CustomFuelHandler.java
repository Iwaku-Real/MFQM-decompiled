package MoreFunQuicksandMod.main;

import cpw.mods.fml.common.IFuelHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CustomFuelHandler implements IFuelHandler {
  public int getBurnTime(ItemStack fuel) {
    Item var1 = fuel.getItem();
    if (var1 == MFQM.TarBucketItem)
      return 4800; 
    if (var1 == MFQM.PeatItem)
      return 1067; 
    if (var1 == Item.getItemFromBlock(MFQM.PeatBlock))
      return 9603; 
    return 0;
  }
}
