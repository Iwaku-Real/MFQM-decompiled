package MoreFunQuicksandMod.main.items;

import MoreFunQuicksandMod.main.MFQM;
import MoreFunQuicksandMod.main.entity.EntityLiqBall;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemLiqGun extends Item {
  @SideOnly(Side.CLIENT)
  private IIcon itemIcon;
  
  public ItemLiqGun() {
    setMaxStackSize(1);
    setMaxDurability(32);
    setUnlocalizedName("LiqGunItem");
    setCreativeTab(MFQM.tabMFQM);
  }
  
  public EnumAction getItemUseAction(ItemStack p_77661_1_) {
    return EnumAction.bow;
  }
  
  public boolean isDamageable() {
    return false;
  }
  
  public int getMaxItemUseDuration(ItemStack p_77626_1_) {
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
  
  public void onPlayerStoppedUsing(ItemStack item, World world, EntityPlayer player, int p_77615_4_) {
    if (player.capabilities.isCreativeMode || item.getMetadata() < item.getMaxDurability()) {
      EntityLiqBall liqBall;
      Block liqBlock = null;
      if (item.hasTagCompound() && item.stackTagCompound.hasKey("liqBlockID"))
        liqBlock = Block.getBlockById(item.stackTagCompound.getInteger("liqBlockID")); 
      if (liqBlock != null) {
        liqBall = new EntityLiqBall(world, (EntityLivingBase)player, liqBlock);
      } else {
        liqBall = new EntityLiqBall(world, (EntityLivingBase)player);
      } 
      item.damageItem(1, (EntityLivingBase)player);
      world.playSoundAtEntity((Entity)player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 0.5F);
      if (!world.isRemote)
        world.spawnEntityInWorld((Entity)liqBall); 
      if (item.getMetadata() >= item.getMaxDurability()) {
        if (!item.hasTagCompound())
          item.setTagCompound(new NBTTagCompound()); 
        item.stackTagCompound.setInteger("liqBlockID", 8);
        item.stackTagCompound.setString("liqDisplayName", "");
      } 
    } 
  }
  
  public void onCreated(ItemStack item, World world, EntityPlayer player) {
    if (!item.hasTagCompound())
      item.setTagCompound(new NBTTagCompound()); 
    Block block = Block.getBlockById(8);
    String dspName = block.getLocalizedName();
    item.stackTagCompound.setInteger("liqBlockID", 8);
    item.stackTagCompound.setString("liqDisplayName", dspName);
  }
  
  public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player) {
    if (!player.isSneaking() && item.getMetadata() < item.getMaxDurability()) {
      player.setItemInUse(item, getMaxItemUseDuration(item));
    } else {
      MovingObjectPosition mop = getMovingObjectPositionFromPlayer(world, player, true);
      if (mop == null)
        return item; 
      if (mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
        int x = mop.blockX;
        int y = mop.blockY;
        int z = mop.blockZ;
        if (!world.canMineBlock(player, x, y, z))
          return item; 
        if (!player.canPlayerEdit(x, y, z, mop.sideHit, item))
          return item; 
        if (world.getBlock(x, y, z).getMaterial().isLiquid()) {
          Block block = world.getBlock(x, y, z);
          int meta = world.getBlockMetadata(x, y, z);
          if (meta == 0) {
            int blkID = Block.getIdFromBlock(block);
            String dspName = block.getLocalizedName();
            if (!item.hasTagCompound())
              item.setTagCompound(new NBTTagCompound()); 
            item.stackTagCompound.setInteger("liqBlockID", blkID);
            item.stackTagCompound.setString("liqDisplayName", dspName);
            item.setMetadata(0);
            world.setBlockToAir(x, y, z);
          } 
        } 
      } 
    } 
    return item;
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister iuconRegister) {
    this.itemIcon = iuconRegister.registerIcon("morefunquicksandmod:LiqGun");
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIconFromDamage(int par1) {
    return this.itemIcon;
  }
  
  public void addInformation(ItemStack item, EntityPlayer player, List<String> list, boolean bool) {
    String liqName = "itemLiqGun.maybewater";
    if (item.hasTagCompound() && item.stackTagCompound.hasKey("liqDisplayName") && 
      item.stackTagCompound.getString("liqDisplayName") != "")
      liqName = item.stackTagCompound.getString("liqDisplayName"); 
    if (item.getMetadata() >= item.getMaxDurability())
      liqName = "itemLiqGun.empty"; 
    list.add(EnumChatFormatting.BOLD + StatCollector.translateToLocal(liqName));
    list.add(StatCollector.translateToLocal("itemLiqGun.instruction1"));
    list.add(StatCollector.translateToLocal("itemLiqGun.instruction2") + ".");
  }
}
