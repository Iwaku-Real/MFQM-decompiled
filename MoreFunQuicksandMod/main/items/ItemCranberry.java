package MoreFunQuicksandMod.main.items;

import MoreFunQuicksandMod.main.MFQM;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class ItemCranberry extends ItemFood {
  public final int itemUseDuration;
  
  public ItemCranberry(int par2, float par3, boolean par4) {
    super(par2, par3, par4);
    this.itemUseDuration = 8;
    setCreativeTab(MFQM.tabMFQM);
  }
  
  public ItemCranberry(int par2, boolean par3) {
    this(par2, 0.6F, par3);
  }
  
  public int getMaxItemUseDuration(ItemStack par1ItemStack) {
    return 8;
  }
}
