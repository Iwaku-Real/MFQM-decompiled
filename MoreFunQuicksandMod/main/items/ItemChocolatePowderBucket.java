package MoreFunQuicksandMod.main.items;

import MoreFunQuicksandMod.main.MFQM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class ItemChocolatePowderBucket extends Item {
  public ItemChocolatePowderBucket() {
    setMaxStackSize(1);
    setUnlocalizedName("BucketOfChocolatePowder");
    setCreativeTab(MFQM.tabMFQM);
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister par1IIconRegister) {
    this.itemIcon = par1IIconRegister.registerIcon("morefunquicksandmod:BucketOfChocolatePowder");
  }
}
