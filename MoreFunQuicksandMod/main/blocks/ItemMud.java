package MoreFunQuicksandMod.main.blocks;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemMud extends ItemMeta {
  public ItemMud(Block blk) {
    super(blk);
  }
  
  public void addInformation(ItemStack item, EntityPlayer player, List<String> list, boolean bool) {
    int meta = item.getMetadata();
    if (meta == 1)
      list.add(StatCollector.translateToLocal("blockMud.Text1")); 
    if (meta == 2)
      list.add(StatCollector.translateToLocal("blockMud.Text2")); 
    if (meta == 3)
      list.add(StatCollector.translateToLocal("blockMud.Text3")); 
  }
}
