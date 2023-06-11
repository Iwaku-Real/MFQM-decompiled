package MoreFunQuicksandMod.main.items;

import MoreFunQuicksandMod.main.MFQM;
import MoreFunQuicksandMod.main.entity.EntityRope;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

public class ItemLifeJacket extends ItemArmor {
  public static float[] quicksandHardCoof = new float[] { 
      10.25F, 0.0F, 0.0F, 0.3F, 0.5F, 0.4F, 1.5F, 1.5F, 0.0F, 0.3F, 
      0.2F, 0.4F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 
      0.3F, 0.0F, 0.0F, 10.25F, 10.25F, 10.25F, 10.25F, 0.3F, 0.75F, 0.5F, 
      0.25F, 0.3F };
  
  public ItemLifeJacket(ItemArmor.ArmorMaterial armorMaterial, int renderIndex, int armorType) {
    super(armorMaterial, renderIndex, armorType);
    setUnlocalizedName("LifeJacket");
    setMaxDurability(0);
    setCreativeTab(MFQM.tabMFQM);
  }
  
  public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
    if (!world.isRemote)
      return; 
    if (crouchKey(player))
      return; 
    double deep = player.posY + player.getEyeHeight() - 0.75D;
    int x = MathHelper.floor_double(player.posX);
    int y = MathHelper.floor_float(MathHelper.floor_double(deep));
    int z = MathHelper.floor_double(player.posZ);
    Block blc = world.getBlock(x, y, z);
    float blc_dif = getQSDifficult(blc);
    boolean blc_us = false;
    Block blc_up = world.getBlock(x, y + 1, z);
    float blc_up_dif = getQSDifficult(blc_up);
    boolean blc_up_us = false;
    double surf = (y + 1);
    if (blc_up_dif > 0.0F)
      surf = (y + 2); 
    if (blc_up_dif > blc_dif)
      blc_dif = blc_up_dif; 
    if (blc_dif > 0.0F) {
      if (blc_dif > 10.0F) {
        blc_dif -= 10.0F;
        blc_us = true;
      } 
      if (surf == (y + 2) || world.rand.nextInt(Math.max((int)Math.floor(Math.max(Math.min(surf - deep, 1.0D), 0.0D) * 10.0D), 1)) != 0)
        if (!blc_us) {
          player.motionY += Math.max(Math.min((surf - deep) / 10.0D, 0.065D), 0.0D) * blc_dif;
        } else {
          player.motionY += Math.max(Math.min((surf - deep) / 10.0D, 0.065D * blc_dif), 0.0D);
        }  
    } 
  }
  
  public float getQSDifficult(Block blc) {
    for (int i = 0; i < EntityRope.quicksandIDS.length; i++) {
      if (EntityRope.quicksandIDS[i] == blc)
        return quicksandHardCoof[i]; 
    } 
    if (blc.getMaterial().isLiquid())
      return 1.0F; 
    return 0.0F;
  }
  
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
    return "morefunquicksandmod:textures/armor/LifeJacket.png";
  }
  
  public void registerIcons(IIconRegister iconRegister) {
    this.itemIcon = iconRegister.registerIcon("morefunquicksandmod:LifeJacket");
  }
  
  public boolean crouchKey(EntityPlayer curPlayer) {
    if (curPlayer.worldObj.isRemote && 
      (Minecraft.getMinecraft()).thePlayer == curPlayer)
      return Keyboard.isKeyDown((Minecraft.getMinecraft()).gameSettings.keyBindSneak.getKeyCode()); 
    return false;
  }
  
  public void addInformation(ItemStack item, EntityPlayer player, List<String> list, boolean bool) {
    list.add(StatCollector.translateToLocal("itemLifeJacket.instruction1") + ",");
    list.add(StatCollector.translateToLocal("itemLifeJacket.instruction2") + ".");
    list.add(StatCollector.translateToLocal("itemLifeJacket.instruction3") + ".");
    list.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("itemLifeJacket.instruction4") + EnumChatFormatting.GRAY + " " + StatCollector.translateToLocal("itemLifeJacket.instruction5"));
  }
}
