package MoreFunQuicksandMod.main.items;

import MoreFunQuicksandMod.main.MFQM;
import MoreFunQuicksandMod.main.entity.EntityLongStick;
import MoreFunQuicksandMod.main.entity.EntityRope;
import com.google.common.collect.Multimap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemLongStick extends Item {
  @SideOnly(Side.CLIENT)
  private IIcon itemIcon;
  
  @SideOnly(Side.CLIENT)
  public IIcon itemL1Icon;
  
  @SideOnly(Side.CLIENT)
  public IIcon itemL2Icon;
  
  public static int[] quicksandHardCoof = new int[] { 
      1, 1, 1, 1, 2, 3, 10, 10, 5, 6, 
      7, 6, 9, 5, 10, 2, 2, 3, 10, 5, 
      5, 2, 10, 1, 1, 1, 6, 5, 3, 3, 
      5, 6 };
  
  public ItemLongStick() {
    setMaxStackSize(1);
    setUnlocalizedName("LongStick");
    setCreativeTab(MFQM.tabMFQM);
  }
  
  public EnumAction getItemUseAction(ItemStack p_77661_1_) {
    return EnumAction.bow;
  }
  
  public int getMaxItemUseDuration(ItemStack p_77626_1_) {
    return 72000;
  }
  
  public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer ply) {
    if (!ply.isUsingItem())
      ply.setItemInUse(item, getMaxItemUseDuration(item)); 
    FoodControl(item, world, ply);
    return item;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean isFull3D() {
    return true;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean shouldRotateAroundWhenRendering() {
    return false;
  }
  
  public boolean onItemUse(ItemStack item, EntityPlayer ply, World world, int x, int y, int z, int side, float px, float py, float pz) {
    if (!ply.isUsingItem()) {
      ply.setItemInUse(item, getMaxItemUseDuration(item));
      if (!world.isRemote) {
        world.spawnEntityInWorld((Entity)new EntityLongStick(world, (x + px), (y + 1), (z + pz), (Entity)ply, x, y, z));
      } else {
        ply.swingItem();
      } 
    } 
    return false;
  }
  
  public void onUpdate(ItemStack item, World world, Entity ply, int par4, boolean par5) {
    if (!(ply instanceof EntityPlayer))
      return; 
    EntityPlayer player = (EntityPlayer)ply;
    if (player.getCurrentEquippedItem() == item) {
      if (world.isRemote && 
        player.isUsingItem()) {
        boolean IsSucStr = false;
        int UC = player.getItemInUseCount();
        int U_MAX = item.getMaxItemUseDuration();
        int U_AL = 100;
        int U_HE = U_MAX - U_AL;
        int U_CRT = (int)Math.floor((item.getMaxItemUseDuration() / 2));
        int U_HV = U_CRT - (int)Math.floor((U_AL / 4));
        int U_VH = U_MAX - (int)Math.floor((U_AL / 4));
        int U_VE = U_CRT - U_AL;
        if (CheckQS(world, player) > 1)
          IsSucStr = true; 
        if (IsSucStr) {
          if (UC < U_CRT)
            if (UC > U_HV) {
              player.clearItemInUse();
              player.setItemInUse(item, U_HE - 1);
            } else {
              player.clearItemInUse();
              player.setItemInUse(item, U_MAX);
            }  
        } else {
          if (UC < 2) {
            player.clearItemInUse();
            player.setItemInUse(item, U_VE - 1);
          } 
          if (UC > U_VH) {
            player.clearItemInUse();
            player.setItemInUse(item, U_VE - 1);
          } else if (UC > U_CRT) {
            player.clearItemInUse();
            player.setItemInUse(item, U_CRT);
          } 
        } 
      } 
      if (rightKey(player) || player.isUsingItem())
        if (!world.isRemote) {
          FoodControl(item, world, player);
        } else {
          int Depth = 0;
          double deep = player.posY + player.getEyeHeight() - 1.5D;
          int x = MathHelper.floor_double(player.posX);
          int y = MathHelper.floor_float(MathHelper.floor_double(deep));
          int z = MathHelper.floor_double(player.posZ);
          Block blc = world.getBlock(x, y, z);
          int blc_dif = getQSDifficult(blc);
          Block blc_up = world.getBlock(x, y + 1, z);
          int blc_up_dif = getQSDifficult(blc_up);
          double surf = (y + 1);
          if (blc_up_dif > 0)
            surf = (y + 2); 
          if (blc_up_dif > blc_dif)
            blc_dif = blc_up_dif; 
          if (blc_dif > 0)
            if (surf == (y + 2)) {
              if (deep < surf - 1.5D) {
                Depth = 4;
              } else {
                Depth = 3;
              } 
            } else if (deep < surf - 0.5D) {
              Depth = 2;
            } else {
              Depth = 1;
            }  
          if (blc_dif >= 10)
            Depth = 4; 
          if (Depth > 0 && 
            player.getFoodStats().getFoodLevel() > 6) {
            if (Depth == 3 && 
              player.motionY < 0.0D)
              player.motionY /= 2.25D; 
            if (Depth < 4) {
              if (world.rand.nextInt(Math.max((int)Math.floor(Math.max(Math.min((surf - deep) * blc_dif, 1.0D) * 20.0D, 0.0D)), 1)) == 0)
                player.onGround = true; 
            } else if (world.rand.nextInt(Math.max((int)Math.floor(Math.max(Math.min((surf - deep) * blc_dif, 1.0D) * 40.0D, 0.0D)), 1)) == 0) {
              player.onGround = true;
            } 
          } 
        }  
    } 
  }
  
  public static int CheckQS(World world, EntityPlayer player) {
    double deep;
    if (world.isRemote) {
      deep = player.posY + player.getEyeHeight() - 1.5D;
    } else {
      deep = player.posY;
    } 
    int x = MathHelper.floor_double(player.posX);
    int y = MathHelper.floor_float(MathHelper.floor_double(deep));
    int z = MathHelper.floor_double(player.posZ);
    Block blc = world.getBlock(x, y, z);
    int blc_dif = getQSDifficult(blc);
    Block blc_up = world.getBlock(x, y + 1, z);
    int blc_up_dif = getQSDifficult(blc_up);
    double surf = (y + 1);
    if (blc_up_dif > 0)
      surf = (y + 2); 
    if (blc_up_dif > blc_dif)
      blc_dif = blc_up_dif; 
    if (blc_dif >= 10)
      return 4; 
    if (blc_dif > 0) {
      if (surf == (y + 2)) {
        if (deep < surf - 1.5D)
          return 4; 
        return 3;
      } 
      if (deep < surf - 0.5D)
        return 2; 
      return 1;
    } 
    return 0;
  }
  
  public void FoodControl(ItemStack itemStack, World world, EntityPlayer player) {
    if (!world.isRemote && 
      world.getTotalWorldTime() % 256L == 0L) {
      int Depth = CheckQS(world, player);
      if (Depth > 0 && 
        player.getFoodStats().getFoodLevel() > 6)
        player.getFoodStats().addStats(-1, 0.0F); 
    } 
  }
  
  public boolean rightKey(EntityPlayer curPlayer) {
    if (curPlayer.worldObj.isRemote && 
      (Minecraft.getMinecraft()).thePlayer == curPlayer)
      return GameSettings.isKeyDown((Minecraft.getMinecraft()).gameSettings.keyBindUseItem); 
    return false;
  }
  
  public static int getQSDifficult(Block blc) {
    for (int i = 0; i < EntityRope.quicksandIDS.length; i++) {
      if (EntityRope.quicksandIDS[i] == blc)
        return quicksandHardCoof[i]; 
    } 
    if (blc.getMaterial().isLiquid())
      return 10; 
    return 0;
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister iuconRegister) {
    this.itemIcon = iuconRegister.registerIcon("morefunquicksandmod:LongStick");
    this.itemL1Icon = iuconRegister.registerIcon("morefunquicksandmod:LongStick0");
    this.itemL2Icon = iuconRegister.registerIcon("morefunquicksandmod:LongStick1");
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIconFromDamage(int par1) {
    return this.itemIcon;
  }
  
  public void addInformation(ItemStack item, EntityPlayer player, List<String> list, boolean bool) {
    list.add(StatCollector.translateToLocal("itemLongStick.instruction1"));
    list.add(StatCollector.translateToLocal("itemLongStick.instruction2") + ".");
    list.add(StatCollector.translateToLocal("itemLongStick.instruction3"));
    list.add(StatCollector.translateToLocal("itemLongStick.instruction4") + ".");
    list.add(EnumChatFormatting.BOLD + StatCollector.translateToLocal("itemLongStick.instruction5") + ":");
    list.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("itemLongStick.instruction6") + EnumChatFormatting.GRAY + " - " + StatCollector.translateToLocal("itemLongStick.instruction7"));
    list.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("itemLongStick.instruction8") + EnumChatFormatting.GRAY + " - " + StatCollector.translateToLocal("itemLongStick.instruction9"));
  }
  
  public Multimap getItemAttributeModifiers() {
    Multimap var1 = super.getItemAttributeModifiers();
    var1.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(itemModifierUUID, "Weapon modifier", 4.0D, 0));
    return var1;
  }
}
