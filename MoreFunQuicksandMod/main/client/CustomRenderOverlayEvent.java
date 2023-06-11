package MoreFunQuicksandMod.main.client;

import MoreFunQuicksandMod.main.MFQM;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.opengl.GL11;

public class CustomRenderOverlayEvent {
  private static Block[] quicksandIDS = new Block[] { 
      MFQM.BogBlock, MFQM.SoftSnowBlock, MFQM.SandBlock, MFQM.MorassBlock, MFQM.QuicksandBlock, MFQM.JungleQuicksandBlock, MFQM.SlimeBlock, MFQM.MireBlock, MFQM.LiquidMireBlock, MFQM.StableLiquidMireBlock, 
      MFQM.MoorBlock, MFQM.SClayBlock, MFQM.TarBlock, MFQM.CSandBlock, MFQM.SinkingWoolBlock, MFQM.SoftQuicksandBlock, MFQM.DenseWebBlock, MFQM.SwFleshBlock, MFQM.MucusBlock, MFQM.MossBlock, 
      MFQM.BrownClayBlock, MFQM.WetPeatBlock, MFQM.WaxBlock, MFQM.LarvaeBlock, MFQM.LiquidChocolateBlock, MFQM.SlurryBlock, MFQM.MudBlock, MFQM.SoftGravelBlock, MFQM.HoneyBlock };
  
  @SubscribeEvent
  @SideOnly(Side.CLIENT)
  public void renderAirGUI(RenderGameOverlayEvent.Pre event) {
    if (event.type == RenderGameOverlayEvent.ElementType.AIR && 
      MFQM.QSAir) {
      Minecraft mc = Minecraft.getMinecraft();
      int gma = MFQM.getMuddyAir((Entity)mc.thePlayer);
      if (gma < mc.thePlayer.getAir())
        mc.thePlayer.setAir(gma); 
      if (MFQM.QSCAir) {
        event.setCanceled(true);
        if (mc.thePlayer.getAir() < 300) {
          ScaledResolution resolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
          int height = resolution.getScaledHeight();
          int width = resolution.getScaledWidth();
          mc.getTextureManager().bindTexture(GuiIngame.icons);
          GL11.glPushMatrix();
          GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.9F);
          IAttributeInstance var10 = mc.thePlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth);
          int var26 = 0;
          int var12 = width / 2 + 91;
          int var13 = height - 39;
          float var14 = (float)var10.getAttributeValue();
          float var15 = mc.thePlayer.getAbsorptionAmount();
          int var16 = MathHelper.ceiling_float_int((var14 + var15) / 2.0F / 10.0F);
          int var17 = Math.max(10 - var16 - 2, 3);
          int var18 = var13 - (var16 - 1) * var17 - 10;
          int var23 = mc.thePlayer.getAir();
          int var36 = MathHelper.ceiling_double_int((var23 - 2) * 10.0D / 300.0D);
          int var25 = MathHelper.ceiling_double_int(var23 * 10.0D / 300.0D) - var36;
          for (var26 = 0; var26 < var36 + var25; var26++) {
            if (var26 < var36) {
              mc.ingameGUI.drawTexturedModalRect(var12 - var26 * 8 - 9, var18, 16, 18, 9, 9);
            } else {
              mc.ingameGUI.drawTexturedModalRect(var12 - var26 * 8 - 9, var18, 25, 18, 9, 9);
            } 
          } 
          GL11.glPopMatrix();
        } 
      } 
    } 
  }
  
  @SubscribeEvent
  public void RenderOverlay(RenderWorldLastEvent event) {
    for (int i = 0; i < quicksandIDS.length; i++) {
      if (MFQM.isEntityInsideOfBlockS((Entity)(Minecraft.getMinecraft()).thePlayer, quicksandIDS[i])) {
        IIcon Icon;
        if (i == 19) {
          Icon = quicksandIDS[i].getBlockTextureFromSide(1);
        } else {
          Icon = quicksandIDS[i].getBlockTextureFromSide(2);
        } 
        if (MFQM.QSThirdPerson)
          (Minecraft.getMinecraft()).gameSettings.thirdPersonView = 0; 
        GL11.glMatrixMode(5889);
        GL11.glLoadIdentity();
        float var3 = 0.07F;
        GL11.glMatrixMode(5888);
        GL11.glLoadIdentity();
        if ((Minecraft.getMinecraft()).gameSettings.thirdPersonView == 0 && !(Minecraft.getMinecraft()).renderViewEntity.isPlayerSleeping())
          renderInsideOfBlockR(0.0F, Icon); 
        return;
      } 
    } 
  }
  
  public void renderInsideOfBlockR(float p_78446_1_, IIcon p_78446_2_) {
    Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
    Tessellator var3 = Tessellator.instance;
    float var4 = 0.1F;
    GL11.glColor4f(var4, var4, var4, 1.0F);
    GL11.glDepthFunc(515);
    GL11.glEnable(3008);
    GL11.glEnable(3553);
    GL11.glEnable(2977);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    GL11.glPushMatrix();
    float var5 = -1.0F;
    float var6 = 1.0F;
    float var7 = -1.0F;
    float var8 = 1.0F;
    float var9 = -0.5F;
    float var10 = p_78446_2_.getMinU();
    float var11 = p_78446_2_.getMaxU();
    float var12 = p_78446_2_.getMinV();
    float var13 = p_78446_2_.getMaxV();
    var3.startDrawingQuads();
    var3.addVertexWithUV(var5, var7, var9, var11, var13);
    var3.addVertexWithUV(var6, var7, var9, var10, var13);
    var3.addVertexWithUV(var6, var8, var9, var10, var12);
    var3.addVertexWithUV(var5, var8, var9, var11, var12);
    var3.draw();
    GL11.glPopMatrix();
    GL11.glDisable(2977);
    GL11.glDisable(3042);
    GL11.glDepthFunc(515);
    GL11.glEnable(3008);
    GL11.glEnable(3553);
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
  }
}
