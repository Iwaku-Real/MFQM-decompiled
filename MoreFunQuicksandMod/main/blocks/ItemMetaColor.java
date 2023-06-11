package MoreFunQuicksandMod.main.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemColored;
import net.minecraft.item.ItemStack;

public class ItemMetaColor extends ItemColored {
  public ItemMetaColor(Block blk) {
    super(blk, true);
  }
  
  public String getUnlocalizedName(ItemStack stack) {
    return getUnlocalizedName() + stack.getMetadata();
  }
}
