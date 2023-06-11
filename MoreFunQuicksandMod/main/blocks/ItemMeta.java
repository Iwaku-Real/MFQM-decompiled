package MoreFunQuicksandMod.main.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemMeta extends ItemBlockWithMetadata {
  public ItemMeta(Block blk) {
    super(blk, blk);
  }
  
  public String getUnlocalizedName(ItemStack stack) {
    return getUnlocalizedName() + stack.getMetadata();
  }
}
