package MoreFunQuicksandMod.main.items;

import MoreFunQuicksandMod.main.MFQM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBucket;

public class ItemSandBucket extends ItemBucket {
  public ItemSandBucket(Block blockID) {
    super(blockID);
    setUnlocalizedName("BucketOfSand");
    setCreativeTab(MFQM.tabMFQM);
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister par1IIconRegister) {
    this.itemIcon = par1IIconRegister.registerIcon("morefunquicksandmod:BucketOfSand");
  }
}
