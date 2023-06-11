package MoreFunQuicksandMod.main.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemRescue extends Item {
  @SideOnly(Side.CLIENT)
  private IIcon itemIIcon1;
  
  public ItemRescue() {
    setMaxStackSize(1);
    setUnlocalizedName("Rescuing");
  }
  
  public EnumAction getItemUseAction(ItemStack par1ItemStack) {
    return EnumAction.bow;
  }
  
  public int getMaxItemUseDuration(ItemStack par1ItemStack) {
    return 72000;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean isFull3D() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean shouldRotateAroundWhenRendering() {
    return false;
  }
  
  public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
    if (par3Entity instanceof EntityPlayer) {
      if (((EntityPlayer)par3Entity).getCurrentEquippedItem() == par1ItemStack)
        if (!((EntityPlayer)par3Entity).isUsingItem()) {
          ((EntityPlayer)par3Entity).setItemInUse(par1ItemStack, 36000);
        } else {
          ((EntityPlayer)par3Entity).clearItemInUse();
          ((EntityPlayer)par3Entity).setItemInUse(par1ItemStack, 36000);
        }  
      if (!par5) {
        if (!par2World.isRemote) {
          ((EntityPlayer)par3Entity).inventory.setInventorySlotContents(par4, (ItemStack)null);
        } else {
          ((EntityPlayer)par3Entity).inventory.setInventorySlotContents(par4, (ItemStack)null);
        } 
        return;
      } 
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister iuconRegister) {
    this.itemIIcon1 = iuconRegister.registerIcon("morefunquicksandmod:Rescue");
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIconFromDamage(int par1) {
    return this.itemIIcon1;
  }
  
  public boolean onEntityItemUpdate(EntityItem entityItem) {
    entityItem.setDead();
    return false;
  }
}
