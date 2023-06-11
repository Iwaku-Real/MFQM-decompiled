package MoreFunQuicksandMod.main.items;

import MoreFunQuicksandMod.main.MFQM;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemWaxPiece extends Item {
  public ItemWaxPiece() {
    setUnlocalizedName("WaxPiece");
    setCreativeTab(MFQM.tabMFQM);
    setTextureName("morefunquicksandmod:Wax");
  }
  
  public void addInformation(ItemStack item, EntityPlayer player, List<String> list, boolean bool) {
    list.add(StatCollector.translateToLocal("itemWaxPiece.instruction1"));
  }
}
