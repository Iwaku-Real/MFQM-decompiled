package MoreFunQuicksandMod.main.client;

import MoreFunQuicksandMod.main.items.ItemLongStick;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class RenderLongStick implements IItemRenderer {
  public boolean handleRenderType(ItemStack itemStack, IItemRenderer.ItemRenderType type) {
    return (type == IItemRenderer.ItemRenderType.EQUIPPED || type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON);
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
    return false;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack itemStack, Object... data) {
    double inUse = 0.0D;
    boolean IsSucStr = false;
    if (data.length > 0) {
      Entity entity = (Entity)data[1];
      if (entity instanceof EntityPlayer) {
        EntityPlayer ply = (EntityPlayer)entity;
        int UC = ply.getItemInUseCount();
        int U_MAX = itemStack.getMaxItemUseDuration();
        int U_AL = 100;
        int U_CRT = (int)Math.floor((itemStack.getMaxItemUseDuration() / 2));
        if (UC > 0) {
          if (ItemLongStick.CheckQS(entity.worldObj, ply) > 1)
            IsSucStr = true; 
          if (UC < U_MAX - 5 && IsSucStr) {
            inUse = (itemStack.getMaxItemUseDuration() - UC);
          } else {
            inUse = Math.max(1.0E-4D, (U_CRT - UC));
          } 
          inUse = Math.max(1.0E-4D, inUse);
        } 
      } 
    } else {
      Entity entity = null;
    } 
    GL11.glPushMatrix();
    GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
    GL11.glTranslatef(0.3F, 0.0F, 0.0F);
    if (inUse > 0.0D) {
      double Aexp = Math.exp(-1.0D / inUse);
      if (IsSucStr) {
        GL11.glRotatef((float)(-90.0D * Aexp), 0.0F, 1.0F, 0.0F);
        if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON)
          GL11.glTranslatef((float)(-0.5D * Aexp), (float)(0.25D * Aexp), (float)(-0.10000000149011612D * Aexp)); 
      } else {
        GL11.glRotatef((float)(-90.0D * (1.0D - Aexp)), 0.0F, 1.0F, 0.0F);
        if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON)
          GL11.glTranslatef((float)(-0.5D * (1.0D - Aexp)), (float)(0.25D * (1.0D - Aexp)), (float)(-0.10000000149011612D * (1.0D - Aexp))); 
      } 
    } 
    GL11.glTranslatef(-0.3F, 0.0F, 0.0F);
    GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
    if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON)
      GL11.glTranslatef(-0.2F, 0.2F, 0.0F); 
    renderStick(type, itemStack, 0.0F, 0.0F, 0.0F);
    GL11.glPopMatrix();
  }
  
  public void renderStick(IItemRenderer.ItemRenderType type, ItemStack itemStack, float x, float y, float z) {
    GL11.glPushMatrix();
    GL11.glTranslatef(x, y, z);
    Tessellator tessellator = Tessellator.instance;
    IIcon var7 = ((ItemLongStick)itemStack.getItem()).itemL1Icon;
    float var9 = var7.getMinU();
    float var10 = var7.getMaxU();
    float var11 = var7.getMinV();
    float var12 = var7.getMaxV();
    float sh1 = 0.25F;
    float sh2 = 0.75F;
    GL11.glPushMatrix();
    GL11.glTranslatef(sh1, -sh1, 0.0F);
    ItemRenderer.renderItemIn2D(tessellator, var10, var11, var9, var12, var7.getIconWidth(), var7.getIconHeight(), 0.0625F);
    GL11.glPopMatrix();
    tessellator = Tessellator.instance;
    if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON)
      sh2 = 1.185F; 
    GL11.glPushMatrix();
    GL11.glTranslatef(sh2, -sh2, 0.0F);
    ItemRenderer.renderItemIn2D(tessellator, var10, var11, var9, var12, var7.getIconWidth(), var7.getIconHeight(), 0.0625F);
    GL11.glPopMatrix();
    GL11.glPopMatrix();
  }
}
