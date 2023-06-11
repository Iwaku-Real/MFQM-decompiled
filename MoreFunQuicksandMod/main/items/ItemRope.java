package MoreFunQuicksandMod.main.items;

import MoreFunQuicksandMod.main.MFQM;
import MoreFunQuicksandMod.main.entity.EntityRope;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemRope extends Item {
  @SideOnly(Side.CLIENT)
  private IIcon itemIIcon1;
  
  @SideOnly(Side.CLIENT)
  private IIcon itemIIcon2;
  
  public ItemRope() {
    setMaxStackSize(1);
    setMaxDurability(64);
    setUnlocalizedName("Rope");
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
  
  public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
    if (player.capabilities.disableDamage) {
      itemStack.setMetadata(0);
      return itemStack;
    } 
    if (itemStack.getMetadata() % 2 == 0) {
      world.playSoundAtEntity((Entity)player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
      if (!world.isRemote) {
        EntityRope rope = new EntityRope(world, player);
        world.spawnEntityInWorld((Entity)rope);
        itemStack.setMetadata(itemStack.getMetadata() + 1);
      } 
    } else if (!world.isRemote) {
      if (itemStack.getMetadata() < 24) {
        itemStack.setMetadata(0);
      } else {
        itemStack.setMetadata(itemStack.getMetadata() - 1);
      } 
      for (int i = 0; i <= 8; i++) {
        ItemStack itm = player.inventory.mainInventory[i];
        if (itm != null && 
          itm.getItem() instanceof ItemRope && 
          itm.getMetadata() % 2 != 0)
          if (itm.getMetadata() < 24) {
            itm.setMetadata(0);
          } else {
            itm.setMetadata(itm.getMetadata() - 1);
          }  
      } 
      if (!player.onGround)
        player.fallDistance = 0.0F; 
    } else if (!player.onGround) {
      double spd = Math.pow(Math.pow(player.motionX, 2.0D) + Math.pow(player.motionZ, 2.0D), 0.5D);
      player.motionY += Math.min(0.75D, spd * 1.5D);
      player.fallDistance = 0.0F;
    } 
    player.swingItem();
    return itemStack;
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister iuconRegister) {
    this.itemIIcon1 = iuconRegister.registerIcon("morefunquicksandmod:Rope1");
    this.itemIIcon2 = iuconRegister.registerIcon("morefunquicksandmod:Rope2");
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIconFromDamage(int par1) {
    if (par1 % 2 == 0)
      return this.itemIIcon1; 
    return this.itemIIcon2;
  }
  
  public boolean onEntityItemUpdate(EntityItem entityItem) {
    if (!entityItem.worldObj.isRemote && 
      entityItem.getEntityItem().getMetadata() % 2 != 0)
      entityItem.getEntityItem().setMetadata(entityItem.getEntityItem().getMetadata() - 1); 
    return false;
  }
  
  public void addInformation(ItemStack item, EntityPlayer player, List<String> list, boolean bool) {
    list.add(EnumChatFormatting.DARK_RED + "" + EnumChatFormatting.BOLD + StatCollector.translateToLocal("itemRope.instruction1") + ".");
    list.add(StatCollector.translateToLocal("itemRope.instruction2"));
    list.add(StatCollector.translateToLocal("itemRope.instruction3") + ".");
    list.add(EnumChatFormatting.BOLD + StatCollector.translateToLocal("itemRope.instruction4") + ":");
    list.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("itemRope.instruction5") + EnumChatFormatting.GRAY + " - " + StatCollector.translateToLocal("itemRope.instruction6") + ".");
    list.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("itemRope.instruction7") + EnumChatFormatting.GRAY + " - " + StatCollector.translateToLocal("itemRope.instruction8") + ".");
    list.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("itemRope.instruction9") + EnumChatFormatting.GRAY + " - " + StatCollector.translateToLocal("itemRope.instruction10") + ".");
  }
}
