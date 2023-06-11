package MoreFunQuicksandMod.main.items;

import MoreFunQuicksandMod.main.MFQM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBucket;

public class ItemQuicksandBucket extends ItemBucket {
  public ItemQuicksandBucket(Block blockID) {
    super(blockID);
    setUnlocalizedName("BucketOfQuicksand");
    setCreativeTab(MFQM.tabMFQM);
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister par1IIconRegister) {
    this.itemIcon = par1IIconRegister.registerIcon("morefunquicksandmod:BucketOfQuicksand");
  }
}
