package MoreFunQuicksandMod.main.items;

import MoreFunQuicksandMod.main.MFQM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class ItemCable extends Item {
  @SideOnly(Side.CLIENT)
  private IIcon itemIcon;
  
  public ItemCable() {
    setMaxStackSize(1);
    setUnlocalizedName("Cable");
    setCreativeTab(MFQM.tabMFQM);
  }
  
  @SideOnly(Side.CLIENT)
  public boolean isFull3D() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean shouldRotateAroundWhenRendering() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister iuconRegister) {
    this.itemIcon = iuconRegister.registerIcon("morefunquicksandmod:Cable");
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIconFromDamage(int par1) {
    return this.itemIcon;
  }
}
