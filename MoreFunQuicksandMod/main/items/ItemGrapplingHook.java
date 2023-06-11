package MoreFunQuicksandMod.main.items;

import MoreFunQuicksandMod.main.MFQM;
import MoreFunQuicksandMod.main.entity.EntityHook;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemGrapplingHook extends Item {
  @SideOnly(Side.CLIENT)
  private IIcon itemIIcon1;
  
  @SideOnly(Side.CLIENT)
  private IIcon itemIIcon2;
  
  public ItemGrapplingHook() {
    setMaxStackSize(1);
    setMaxDurability(64);
    setUnlocalizedName("GrapplingHook");
    setCreativeTab(MFQM.tabMFQM);
  }
  
  public EnumAction getItemUseAction(ItemStack par1ItemStack) {
    return EnumAction.bow;
  }
  
  public int getMaxItemUseDuration(ItemStack par1ItemStack) {
    return 72000;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean isFull3D() {
    return true;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean shouldRotateAroundWhenRendering() {
    return false;
  }
  
  public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
    if (((EntityPlayer)par3Entity).capabilities.disableDamage) {
      par1ItemStack.setMetadata(0);
      ((EntityPlayer)par3Entity).stopUsingItem();
      return;
    } 
    if (((EntityPlayer)par3Entity).getCurrentEquippedItem() != null) {
      ItemStack itemStack = ((EntityPlayer)par3Entity).getCurrentEquippedItem();
      if (itemStack == par1ItemStack && 
        itemStack.getMetadata() % 2 != 0) {
        if (!((EntityPlayer)par3Entity).isUsingItem()) {
          ((EntityPlayer)par3Entity).setItemInUse(par1ItemStack, getMaxItemUseDuration(par1ItemStack));
          return;
        } 
        ((EntityPlayer)par3Entity).stopUsingItem();
        return;
      } 
    } 
  }
  
  public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
    if (player.capabilities.disableDamage) {
      itemStack.setMetadata(0);
      return itemStack;
    } 
    if (itemStack.getMetadata() % 2 == 0) {
      world.playSoundAtEntity((Entity)player, "random.bow", 0.5F, itemRand.nextFloat() * 0.25F + 0.5F);
      world.playSoundAtEntity((Entity)player, "mob.irongolem.death", 0.5F, itemRand.nextFloat() * 0.15F + 1.75F);
      EntityHook hook = new EntityHook(world, player);
      if (!world.isRemote) {
        world.spawnEntityInWorld((Entity)hook);
        itemStack.setMetadata(itemStack.getMetadata() + 1);
      } 
    } else {
      if (!world.isRemote) {
        if (itemStack.getMetadata() < 24) {
          itemStack.setMetadata(0);
        } else {
          itemStack.setMetadata(itemStack.getMetadata() - 1);
        } 
        for (int i = 0; i <= 8; i++) {
          ItemStack itm = player.inventory.mainInventory[i];
          if (itm != null && 
            itm.getItem() instanceof ItemGrapplingHook && 
            itm.getMetadata() % 2 != 0)
            if (itm.getMetadata() < 24) {
              itm.setMetadata(0);
            } else {
              itm.setMetadata(itm.getMetadata() - 1);
            }  
        } 
        if (!player.onGround)
          player.fallDistance = 0.0F; 
        world.playSoundAtEntity((Entity)player, "random.click", 0.5F, -itemRand.nextFloat() * 0.3F + 1.15F);
      } else if (!player.onGround) {
        double spd = Math.pow(Math.pow(player.motionX, 2.0D) + Math.pow(player.motionZ, 2.0D), 0.5D);
        player.motionY += Math.min(0.75D, spd * 1.5D);
        player.fallDistance = 0.0F;
      } 
      player.swingItem();
    } 
    return itemStack;
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister iuconRegister) {
    this.itemIIcon1 = iuconRegister.registerIcon("morefunquicksandmod:GrapplingHook");
    this.itemIIcon2 = iuconRegister.registerIcon("morefunquicksandmod:GrapplingHookActive");
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
    list.add(EnumChatFormatting.DARK_RED + "" + EnumChatFormatting.BOLD + StatCollector.translateToLocal("itemGrapplingHook.instruction1") + ".");
    list.add(StatCollector.translateToLocal("itemGrapplingHook.instruction2"));
    list.add(StatCollector.translateToLocal("itemGrapplingHook.instruction3") + ".");
    list.add(EnumChatFormatting.BOLD + StatCollector.translateToLocal("itemGrapplingHook.instruction4") + ":");
    list.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("itemGrapplingHook.instruction5") + EnumChatFormatting.GRAY + " - " + StatCollector.translateToLocal("itemGrapplingHook.instruction6") + ".");
    list.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("itemGrapplingHook.instruction7") + EnumChatFormatting.GRAY + " - " + StatCollector.translateToLocal("itemGrapplingHook.instruction8") + ".");
    list.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("itemGrapplingHook.instruction9") + EnumChatFormatting.GRAY + " - " + StatCollector.translateToLocal("itemGrapplingHook.instruction10") + ".");
  }
}
