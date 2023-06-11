package MoreFunQuicksandMod.main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabsMFQM extends CreativeTabs {
  public CreativeTabsMFQM(int position, String tabID) {
    super(position, tabID);
  }
  
  public Item getTabIconItem() {
    return MFQM.GrapplingHookItem;
  }
}
