package MoreFunQuicksandMod.main.items;

import MoreFunQuicksandMod.main.MFQM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBucket;

public class ItemAcidBucket extends ItemBucket {
  public ItemAcidBucket(Block blockID) {
    super(blockID);
    setUnlocalizedName("BucketOfAcid");
    setCreativeTab(MFQM.tabMFQM);
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister par1IconRegister) {
    this.itemIcon = par1IconRegister.registerIcon("morefunquicksandmod:BucketOfAcid");
  }
}
