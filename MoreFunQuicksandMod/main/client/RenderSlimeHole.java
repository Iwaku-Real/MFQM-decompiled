package MoreFunQuicksandMod.main.client;

import MoreFunQuicksandMod.main.MFQM;
import MoreFunQuicksandMod.main.entity.EntitySlimeHole;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderSlimeHole extends Render {
  private static final ResourceLocation slimeHole0 = new ResourceLocation("morefunquicksandmod", "textures/blocks/SlimeHole0.png");
  
  private static final ResourceLocation slimeHole1 = new ResourceLocation("morefunquicksandmod", "textures/blocks/SlimeHole1.png");
  
  private static final ResourceLocation slimeHole2 = new ResourceLocation("morefunquicksandmod", "textures/blocks/SlimeHole2.png");
  
  private static final ResourceLocation slimeHole3 = new ResourceLocation("morefunquicksandmod", "textures/blocks/SlimeHole3.png");
  
  public void doRenderSlimeHole(EntitySlimeHole par1EntitySlimeHole, double par2, double par4, double par6, float par8, float par9) {
    double x = 0.0D;
    double y = 0.0D;
    double z = 0.0D;
    Tessellator tessellator = Tessellator.instance;
    float[] f = new float[9];
    int[] l = new int[9];
    int[] l1 = new int[9];
    int[] l2 = new int[9];
    int px = (int)Math.floor(par1EntitySlimeHole.posX);
    int py = (int)Math.floor(par1EntitySlimeHole.posY + 1.0D);
    int pz = (int)Math.floor(par1EntitySlimeHole.posZ);
    double pxq = px - par1EntitySlimeHole.posX;
    double pzq = pz - par1EntitySlimeHole.posZ;
    int num = 0;
    for (int i = -1; i < 2; i++) {
      for (int j = -1; j < 2; j++) {
        l1[num] = -9999;
        if (par1EntitySlimeHole.worldObj.getBlock(px + j, py - 1, pz + i) == MFQM.SlimeBlock && 
          par1EntitySlimeHole.worldObj.getBlockMetadata(px + j, py - 1, pz + i) == 0)
          l1[num] = 0; 
        if (l1[num] != -9999) {
          f[num] = (4 * MFQM.SlimeBlock.getMixedBrightnessForBlock((IBlockAccess)par1EntitySlimeHole.worldObj, px + j, py, pz + i));
          l[num] = par1EntitySlimeHole.worldObj.getLightBrightnessForSkyBlocks(px + j, py, pz + i, 0);
          l1[num] = l[num] % 65536;
          l2[num] = l[num] / 65536;
        } 
        num++;
      } 
    } 
    bindTexture(getHoleType(par1EntitySlimeHole));
    GL11.glPushMatrix();
    GL11.glEnable(32826);
    GL11.glTranslatef((float)par2, (float)par4, (float)par6);
    double xs = 0.0D;
    double zs = 0.0D;
    double xe = 0.0D;
    double ze = 0.0D;
    int K = 0;
    xs = pxq;
    zs = pzq;
    xe = pxq + 1.0D;
    ze = pzq + 1.0D;
    K = 4;
    if (l1[K] != -9999) {
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, l1[K], l2[K]);
      tessellator.setColorOpaque_F(f[K], f[K], f[K]);
      drawQuad(tessellator, 0.0D, xs, zs, xe, ze, (xs + 1.0D) / 2.0D, (zs + 1.0D) / 2.0D, (xe + 1.0D) / 2.0D, (ze + 1.0D) / 2.0D);
    } 
    xs = -1.0D;
    zs = -1.0D;
    xe = pxq;
    ze = pzq;
    K = 0;
    if (l1[K] != -9999) {
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, l1[K], l2[K]);
      tessellator.setColorOpaque_F(f[K], f[K], f[K]);
      drawQuad(tessellator, 0.0D, xs, zs, xe, ze, (xs + 1.0D) / 2.0D, (zs + 1.0D) / 2.0D, (xe + 1.0D) / 2.0D, (ze + 1.0D) / 2.0D);
    } 
    xs = pxq;
    zs = -1.0D;
    xe = pxq + 1.0D;
    ze = pzq;
    K = 1;
    if (l1[K] != -9999) {
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, l1[K], l2[K]);
      tessellator.setColorOpaque_F(f[K], f[K], f[K]);
      drawQuad(tessellator, 0.0D, xs, zs, xe, ze, (xs + 1.0D) / 2.0D, (zs + 1.0D) / 2.0D, (xe + 1.0D) / 2.0D, (ze + 1.0D) / 2.0D);
    } 
    xs = pxq + 1.0D;
    zs = -1.0D;
    xe = 1.0D;
    ze = pzq;
    K = 2;
    if (l1[K] != -9999) {
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, l1[K], l2[K]);
      tessellator.setColorOpaque_F(f[K], f[K], f[K]);
      drawQuad(tessellator, 0.0D, xs, zs, xe, ze, (xs + 1.0D) / 2.0D, (zs + 1.0D) / 2.0D, (xe + 1.0D) / 2.0D, (ze + 1.0D) / 2.0D);
    } 
    xs = -1.0D;
    zs = pzq;
    xe = pxq;
    ze = pzq + 1.0D;
    K = 3;
    if (l1[K] != -9999) {
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, l1[K], l2[K]);
      tessellator.setColorOpaque_F(f[K], f[K], f[K]);
      drawQuad(tessellator, 0.0D, xs, zs, xe, ze, (xs + 1.0D) / 2.0D, (zs + 1.0D) / 2.0D, (xe + 1.0D) / 2.0D, (ze + 1.0D) / 2.0D);
    } 
    xs = pxq + 1.0D;
    zs = pzq;
    xe = 1.0D;
    ze = pzq + 1.0D;
    K = 5;
    if (l1[K] != -9999) {
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, l1[K], l2[K]);
      tessellator.setColorOpaque_F(f[K], f[K], f[K]);
      drawQuad(tessellator, 0.0D, xs, zs, xe, ze, (xs + 1.0D) / 2.0D, (zs + 1.0D) / 2.0D, (xe + 1.0D) / 2.0D, (ze + 1.0D) / 2.0D);
    } 
    xs = -1.0D;
    zs = pzq + 1.0D;
    xe = pxq;
    ze = 1.0D;
    K = 6;
    if (l1[K] != -9999) {
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, l1[K], l2[K]);
      tessellator.setColorOpaque_F(f[K], f[K], f[K]);
      drawQuad(tessellator, 0.0D, xs, zs, xe, ze, (xs + 1.0D) / 2.0D, (zs + 1.0D) / 2.0D, (xe + 1.0D) / 2.0D, (ze + 1.0D) / 2.0D);
    } 
    xs = pxq;
    zs = pzq + 1.0D;
    xe = pxq + 1.0D;
    ze = 1.0D;
    K = 7;
    if (l1[K] != -9999) {
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, l1[K], l2[K]);
      tessellator.setColorOpaque_F(f[K], f[K], f[K]);
      drawQuad(tessellator, 0.0D, xs, zs, xe, ze, (xs + 1.0D) / 2.0D, (zs + 1.0D) / 2.0D, (xe + 1.0D) / 2.0D, (ze + 1.0D) / 2.0D);
    } 
    xs = pxq + 1.0D;
    zs = pzq + 1.0D;
    xe = 1.0D;
    ze = 1.0D;
    K = 8;
    if (l1[K] != -9999) {
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, l1[K], l2[K]);
      tessellator.setColorOpaque_F(f[K], f[K], f[K]);
      drawQuad(tessellator, 0.0D, xs, zs, xe, ze, (xs + 1.0D) / 2.0D, (zs + 1.0D) / 2.0D, (xe + 1.0D) / 2.0D, (ze + 1.0D) / 2.0D);
    } 
    GL11.glDisable(32826);
    GL11.glPopMatrix();
  }
  
  public void drawQuad(Tessellator tessellator, double y, double xs, double zs, double xe, double ze, double um, double vm, double UM, double VM) {
    tessellator.startDrawingQuads();
    tessellator.setNormal(0.0F, 1.0F, 0.0F);
    tessellator.addVertexWithUV(xs, y, zs, um, vm);
    tessellator.addVertexWithUV(xs, y, ze, um, VM);
    tessellator.addVertexWithUV(xe, y, ze, UM, VM);
    tessellator.addVertexWithUV(xe, y, zs, UM, vm);
    tessellator.draw();
  }
  
  protected ResourceLocation getEntityTexture(Entity par1Entity) {
    return getHoleType((EntitySlimeHole)par1Entity);
  }
  
  protected ResourceLocation getHoleType(EntitySlimeHole par1Entity) {
    if (par1Entity.size == 3)
      return slimeHole3; 
    if (par1Entity.size == 2)
      return slimeHole2; 
    if (par1Entity.size == 1)
      return slimeHole1; 
    return slimeHole0;
  }
  
  public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
    doRenderSlimeHole((EntitySlimeHole)par1Entity, par2, par4, par6, par8, par9);
  }
}
