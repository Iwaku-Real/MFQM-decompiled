package MoreFunQuicksandMod.main.items;

import MoreFunQuicksandMod.main.MFQM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

public class ItemGrapplingHookBRK extends Item {
  @SideOnly(Side.CLIENT)
  private IIcon itemIcon;
  
  public ItemGrapplingHookBRK() {
    setMaxStackSize(1);
    setUnlocalizedName("GrapplingHookBRK");
    setCreativeTab(MFQM.tabMFQM);
  }
  
  @SideOnly(Side.CLIENT)
  public boolean isFull3D() {
    return true;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean shouldRotateAroundWhenRendering() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister iuconRegister) {
    this.itemIcon = iuconRegister.registerIcon("morefunquicksandmod:GrapplingHookBroken");
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIconFromDamage(int par1) {
    return this.itemIcon;
  }
  
  public void addInformation(ItemStack item, EntityPlayer player, List<String> list, boolean bool) {
    list.add(StatCollector.translateToLocal("itemGrapplingHookBRK.instruction1") + ".");
    list.add(StatCollector.translateToLocal("itemGrapplingHookBRK.instruction2") + ".");
  }
}
