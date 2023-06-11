package MoreFunQuicksandMod.main.client;

import MoreFunQuicksandMod.main.MFQM;
import MoreFunQuicksandMod.main.entity.EntityTentacles;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderTentacles extends Render {
  private ResourceLocation Tentacles0;
  
  private ResourceLocation Tentacles1;
  
  private ResourceLocation Tentacles2;
  
  private ResourceLocation Tentacles3;
  
  private int TentType;
  
  public RenderTentacles(int type) {
    this.TentType = type;
    if (this.TentType == 1) {
      this.Tentacles0 = new ResourceLocation("morefunquicksandmod", "textures/blocks/Tentacles0_1.png");
      this.Tentacles1 = new ResourceLocation("morefunquicksandmod", "textures/blocks/Tentacles1_1.png");
      this.Tentacles2 = new ResourceLocation("morefunquicksandmod", "textures/blocks/Tentacles2_1.png");
      this.Tentacles3 = new ResourceLocation("morefunquicksandmod", "textures/blocks/TentaclesBase.png");
    } else {
      this.Tentacles0 = new ResourceLocation("morefunquicksandmod", "textures/blocks/Tentacles0.png");
      this.Tentacles1 = new ResourceLocation("morefunquicksandmod", "textures/blocks/Tentacles1.png");
      this.Tentacles2 = new ResourceLocation("morefunquicksandmod", "textures/blocks/Tentacles2.png");
      this.Tentacles3 = new ResourceLocation("morefunquicksandmod", "textures/blocks/Meat10.png");
    } 
  }
  
  public void doRenderTentacles(EntityTentacles par1EntityTentacles, double par2, double par4, double par6, float par8, float par9) {
    double x = 0.0D;
    double y = 0.0D;
    double z = 0.0D;
    Tessellator tessellator = Tessellator.instance;
    int px = (int)Math.floor(par1EntityTentacles.posX);
    int py = (int)Math.floor(par1EntityTentacles.posY + 1.0D);
    int pz = (int)Math.floor(par1EntityTentacles.posZ);
    double pxq = px - par1EntityTentacles.posX;
    double pzq = pz - par1EntityTentacles.posZ;
    float f = (4 * MFQM.SwFleshBlock.getMixedBrightnessForBlock((IBlockAccess)par1EntityTentacles.worldObj, px, py, pz));
    int l = par1EntityTentacles.worldObj.getLightBrightnessForSkyBlocks(px, py, pz, 0);
    int l1 = l % 65536;
    int l2 = l / 65536;
    bindTexture(getEntityTexture((Entity)par1EntityTentacles));
    GL11.glPushMatrix();
    GL11.glEnable(32826);
    OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, l1, l2);
    tessellator.setColorOpaque_F(f, f, f);
    double hgh = par1EntityTentacles.AnimLifeTime;
    if (hgh > 10000.0D)
      hgh = Math.max(10100.0D - hgh, 0.0D); 
    double pulse = Math.sin(par1EntityTentacles.worldObj.getTotalWorldTime() / 10.0D) * 50.0D + Math.max(Math.pow(Math.cos(par1EntityTentacles.worldObj.getTotalWorldTime() / 10.0D), 5.0D), 0.0D) * 25.0D;
    double living = Math.sin(par1EntityTentacles.worldObj.getTotalWorldTime() / 10.0D);
    double living2 = Math.cos(par1EntityTentacles.worldObj.getTotalWorldTime() / 10.0D);
    double tar_ofst = 0.0D;
    double Scala = 0.5D;
    double Scale = 1.0D;
    if (par1EntityTentacles.target != null) {
      Scala = par1EntityTentacles.target.width * 1.5D;
      Scale = par1EntityTentacles.target.width * 1.6D;
      double yc = 0.0D;
      if (par1EntityTentacles.target == (Minecraft.getMinecraft()).thePlayer)
        yc = -1.6D; 
      double ch_up = Math.min(Math.max(par1EntityTentacles.high - par1EntityTentacles.target.posY + yc - 0.5D, 0.0D) * 0.5D, 1.0D);
      tar_ofst = Math.max(Math.min(par1EntityTentacles.target.posY + yc - par1EntityTentacles.posY + ch_up, par1EntityTentacles.high - par1EntityTentacles.posY + 1.0D), -0.5D);
    } 
    double liv_hgh = (living * 0.05D - pulse / 75.0D * 0.2D + tar_ofst) * Scale;
    if (hgh > 30.0D)
      drawCrossedSquares(tessellator, 0.5D * Scale, par2 - 0.5D * Scale, par4, par6 - 0.5D * Scale, (float)(Math.min(hgh / 60.0D, 1.0D) + liv_hgh)); 
    if (hgh > 70.0D) {
      double phgh = 0.0D;
      if (hgh < 90.0D)
        phgh = Math.sin((hgh - 70.0D) * 9.0D * Math.PI / 180.0D) * 0.35D; 
      double sc = Math.min((hgh - 70.0D) / 80.0D, 1.0D);
      double bhg = pulse / 75.0D * 0.05D;
      double heg = (0.125D + living * 0.025D + phgh) * sc;
      double hg = (0.75D - bhg * 3.0D) * Scala;
      bindTexture(this.Tentacles3);
      double count = 3.0D;
      for (int i = 0; i < count; i++) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par2, (float)par4, (float)par6);
        GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef((float)(i * 360.0D / count + par1EntityTentacles.worldObj.getTotalWorldTime() / 4.0D), 0.0F, 0.0F, 1.0F);
        GL11.glTranslatef((float)(-par2 + (-0.20000000298023224D + bhg) * Scala), (float)-par4, (float)(-par6 - 0.75D - liv_hgh));
        renderFleshCube(tessellator, par2 - heg / 2.0D, par4 - hg / 2.0D, par6 - heg / 2.0D, heg, hg);
        GL11.glPopMatrix();
        heg = (0.125D - living * 0.025D + phgh) * sc;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par2, (float)par4, (float)par6);
        GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef((float)(i * 360.0D / count + par1EntityTentacles.worldObj.getTotalWorldTime() / 3.0D), 0.0F, 0.0F, 1.0F);
        GL11.glTranslatef((float)(-par2 + (-0.20000000298023224D + bhg) * Scala), (float)-par4, (float)(-par6 - 0.9D - liv_hgh));
        renderFleshCube(tessellator, par2 - heg / 2.0D, par4 - hg / 2.0D, par6 - heg / 2.0D, heg, hg);
        GL11.glPopMatrix();
      } 
    } 
    GL11.glDisable(32826);
    GL11.glPopMatrix();
  }
  
  public void drawCrossedSquares(Tessellator tessellator, double size, double xx, double height, double yy, float sacal) {
    double var10 = 0.0D;
    double var12 = 0.0D;
    double var14 = 1.0D;
    double var16 = 1.0D;
    double var18 = size;
    double var20 = xx + size - var18;
    double var22 = xx + size + var18;
    double var24 = yy + size - var18;
    double var26 = yy + size + var18;
    tessellator.startDrawingQuads();
    tessellator.setNormal(0.0F, 1.0F, 0.0F);
    tessellator.addVertexWithUV(var20, height + sacal, var24, var10, var12);
    tessellator.addVertexWithUV(var20, height + 0.0D, var24, var10, var16);
    tessellator.addVertexWithUV(var22, height + 0.0D, var26, var14, var16);
    tessellator.addVertexWithUV(var22, height + sacal, var26, var14, var12);
    tessellator.addVertexWithUV(var22, height + sacal, var26, var10, var12);
    tessellator.addVertexWithUV(var22, height + 0.0D, var26, var10, var16);
    tessellator.addVertexWithUV(var20, height + 0.0D, var24, var14, var16);
    tessellator.addVertexWithUV(var20, height + sacal, var24, var14, var12);
    tessellator.addVertexWithUV(var20, height + sacal, var26, var10, var12);
    tessellator.addVertexWithUV(var20, height + 0.0D, var26, var10, var16);
    tessellator.addVertexWithUV(var22, height + 0.0D, var24, var14, var16);
    tessellator.addVertexWithUV(var22, height + sacal, var24, var14, var12);
    tessellator.addVertexWithUV(var22, height + sacal, var24, var10, var12);
    tessellator.addVertexWithUV(var22, height + 0.0D, var24, var10, var16);
    tessellator.addVertexWithUV(var20, height + 0.0D, var26, var14, var16);
    tessellator.addVertexWithUV(var20, height + sacal, var26, var14, var12);
    tessellator.draw();
  }
  
  public void renderFleshCube(Tessellator tessellator, double x, double y, double z, double size, double height) {
    double minU = 0.0D;
    double minV = 0.0D;
    double maxU = 1.0D;
    double maxV = 1.0D;
    tessellator.startDrawingQuads();
    tessellator.setNormal(0.0F, 1.0F, 0.0F);
    tessellator.addVertexWithUV(x, y + height, z, maxU, minV);
    tessellator.addVertexWithUV(x, y + height, z + size, maxU, maxV);
    tessellator.addVertexWithUV(x + size, y + height, z + size, minU, maxV);
    tessellator.addVertexWithUV(x + size, y + height, z, minU, minV);
    tessellator.draw();
    tessellator.startDrawingQuads();
    tessellator.setNormal(0.0F, -1.0F, 0.0F);
    tessellator.addVertexWithUV(x + size, y, z, maxU, minV);
    tessellator.addVertexWithUV(x + size, y, z + size, maxU, maxV);
    tessellator.addVertexWithUV(x, y, z + size, minU, maxV);
    tessellator.addVertexWithUV(x, y, z, minU, minV);
    tessellator.draw();
    tessellator.startDrawingQuads();
    tessellator.setNormal(0.0F, 0.0F, -1.0F);
    tessellator.addVertexWithUV(x, y, z, maxU, minV);
    tessellator.addVertexWithUV(x, y + height, z, maxU, maxV);
    tessellator.addVertexWithUV(x + size, y + height, z, minU, maxV);
    tessellator.addVertexWithUV(x + size, y, z, minU, minV);
    tessellator.draw();
    tessellator.startDrawingQuads();
    tessellator.setNormal(0.0F, 0.0F, 1.0F);
    tessellator.addVertexWithUV(x + size, y, z + size, maxU, minV);
    tessellator.addVertexWithUV(x + size, y + height, z + size, maxU, maxV);
    tessellator.addVertexWithUV(x, y + height, z + size, minU, maxV);
    tessellator.addVertexWithUV(x, y, z + size, minU, minV);
    tessellator.draw();
    tessellator.startDrawingQuads();
    tessellator.setNormal(-1.0F, 0.0F, 0.0F);
    tessellator.addVertexWithUV(x, y, z + size, maxU, minV);
    tessellator.addVertexWithUV(x, y + height, z + size, maxU, maxV);
    tessellator.addVertexWithUV(x, y + height, z, minU, maxV);
    tessellator.addVertexWithUV(x, y, z, minU, minV);
    tessellator.draw();
    tessellator.startDrawingQuads();
    tessellator.setNormal(1.0F, 0.0F, 0.0F);
    tessellator.addVertexWithUV(x + size, y, z, minU, minV);
    tessellator.addVertexWithUV(x + size, y + height, z, minU, maxV);
    tessellator.addVertexWithUV(x + size, y + height, z + size, maxU, maxV);
    tessellator.addVertexWithUV(x + size, y, z + size, maxU, minV);
    tessellator.draw();
  }
  
  protected ResourceLocation getEntityTexture(Entity par1Entity) {
    double hgh = ((EntityTentacles)par1Entity).AnimLifeTime;
    if (hgh > 10000.0D)
      hgh = Math.max(10100.0D - hgh, 0.0D); 
    if (hgh > 80.0D)
      return this.Tentacles2; 
    if (hgh > 60.0D)
      return this.Tentacles1; 
    return this.Tentacles0;
  }
  
  public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
    doRenderTentacles((EntityTentacles)par1Entity, par2, par4, par6, par8, par9);
  }
}
