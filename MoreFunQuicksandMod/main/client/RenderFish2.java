package MoreFunQuicksandMod.main.client;

import MoreFunQuicksandMod.main.entity.EntityRope;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderFish2 extends Render {
  private static final ResourceLocation field_110792_a = new ResourceLocation("textures/particle/particles.png");
  
  private static final ResourceLocation BeamTextures = new ResourceLocation("morefunquicksandmod", "textures/items/RopeTex.png");
  
  public void doRenderFishHook(EntityRope par1EntityRope, double par2, double par4, double par6, float par8, float par9) {
    Tessellator tessellator = Tessellator.instance;
    if (par1EntityRope.curPlayer != null) {
      float f9 = par1EntityRope.curPlayer.getSwingProgress(par9);
      float f10 = MathHelper.sin(MathHelper.sqrt_float(f9) * 3.1415927F);
      float f11a = (par1EntityRope.curPlayer.prevRotationYaw + (par1EntityRope.curPlayer.rotationYaw - par1EntityRope.curPlayer.prevRotationYaw) * par9) * 3.1415927F / 180.0F;
      double d7a = MathHelper.sin(f11a);
      double d8a = MathHelper.cos(f11a);
      double d3 = par1EntityRope.curPlayer.prevPosX + (par1EntityRope.curPlayer.posX - par1EntityRope.curPlayer.prevPosX) * par9 - d8a * 0.35D - d7a * (0.2D + f10 * 0.5D);
      double d4 = par1EntityRope.curPlayer.prevPosY + (par1EntityRope.curPlayer.posY - par1EntityRope.curPlayer.prevPosY) * par9 - 0.5D + f10 * 0.7D;
      double d5 = par1EntityRope.curPlayer.prevPosZ + (par1EntityRope.curPlayer.posZ - par1EntityRope.curPlayer.prevPosZ) * par9 - d7a * 0.35D + d8a * (0.2D + f10 * 0.5D);
      double d6 = (par1EntityRope.curPlayer == (Minecraft.getMinecraft()).thePlayer) ? 0.0D : par1EntityRope.curPlayer.getEyeHeight();
      if (this.renderManager.options.thirdPersonView > 0 || par1EntityRope.curPlayer != (Minecraft.getMinecraft()).thePlayer) {
        float f11 = (par1EntityRope.curPlayer.prevRenderYawOffset + (par1EntityRope.curPlayer.renderYawOffset - par1EntityRope.curPlayer.prevRenderYawOffset) * par9) * 3.1415927F / 180.0F;
        double d7 = MathHelper.sin(f11);
        double d8 = MathHelper.cos(f11);
        d3 = par1EntityRope.curPlayer.prevPosX + (par1EntityRope.curPlayer.posX - par1EntityRope.curPlayer.prevPosX) * par9 - d8 * 0.35D - d7 * (0.2D + f10 * 0.5D);
        d4 = par1EntityRope.curPlayer.prevPosY + d6 + (par1EntityRope.curPlayer.posY - par1EntityRope.curPlayer.prevPosY) * par9 - 1.05D + f10 * 0.7D;
        d5 = par1EntityRope.curPlayer.prevPosZ + (par1EntityRope.curPlayer.posZ - par1EntityRope.curPlayer.prevPosZ) * par9 - d7 * 0.35D + d8 * (0.2D + f10 * 0.5D);
      } 
      double d9 = par1EntityRope.prevPosX + (par1EntityRope.posX - par1EntityRope.prevPosX) * par9;
      double d10 = par1EntityRope.prevPosY + (par1EntityRope.posY - par1EntityRope.prevPosY) * par9;
      double d11 = par1EntityRope.prevPosZ + (par1EntityRope.posZ - par1EntityRope.prevPosZ) * par9;
      double d12 = (float)(d3 - d9);
      double d13 = (float)(d4 - d10);
      double d14 = (float)(d5 - d11);
      GL11.glDisable(2896);
      GL11.glDisable(2884);
      RenderHelper.disableStandardItemLighting();
      bindTexture(BeamTextures);
      Vec3 nvU = Vec3.createVectorHelper(0.0D, 1.0D, 0.0D);
      tessellator.startDrawing(5);
      tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
      byte b2 = 16;
      double rad = 0.05D;
      double lpx = 0.0D;
      double lpy = 0.0D;
      double lpz = 0.0D;
      int i;
      for (i = 0; i <= b2; i++) {
        float f12 = i / b2;
        double npx = par2 + d12 * f12;
        double npy = par4 + d13 * f12 * (f12 + 1.0F) * 0.5D + 0.25D;
        double npz = par6 + d14 * f12;
        f12 = (i + 1) / b2;
        double npxn = par2 + d12 * f12;
        double npyn = par4 + d13 * f12 * (f12 + 1.0F) * 0.5D + 0.25D;
        double npzn = par6 + d14 * f12;
        Vec3 nv3 = Vec3.createVectorHelper(npxn - npx, npyn - npy, npzn - npz);
        nv3 = nv3.normalize();
        if (i == 0) {
          lpx = -nv3.xCoord;
          lpy = -nv3.yCoord;
          lpz = -nv3.zCoord;
        } 
        Vec3 nv1 = nv3.crossProduct(nvU);
        nv1 = nv1.normalize();
        double upx = nv1.xCoord * rad;
        double upy = nv1.yCoord * rad;
        double upz = nv1.zCoord * rad;
        tessellator.addVertexWithUV(npx - upx / 2.0D, npy - upy / 2.0D, npz - upz / 2.0D, 0.0D, 1.0D);
        tessellator.addVertexWithUV(npx + upx - upx / 2.0D, npy + upy - upy / 2.0D, npz + upz - upz / 2.0D, 1.5D, 1.0D);
      } 
      tessellator.draw();
      tessellator.startDrawing(5);
      tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
      for (i = 0; i <= b2; i++) {
        float f12 = i / b2;
        double npx = par2 + d12 * f12;
        double npy = par4 + d13 * f12 * (f12 + 1.0F) * 0.5D + 0.25D;
        double npz = par6 + d14 * f12;
        f12 = (i + 1) / b2;
        double npxn = par2 + d12 * f12;
        double npyn = par4 + d13 * f12 * (f12 + 1.0F) * 0.5D + 0.25D;
        double npzn = par6 + d14 * f12;
        Vec3 nv3 = Vec3.createVectorHelper(npxn - npx, npyn - npy, npzn - npz);
        nv3 = nv3.normalize();
        Vec3 nv1 = nv3.crossProduct(nvU);
        nv1 = nv1.normalize();
        Vec3 nv2 = nv1.crossProduct(nv3);
        nv2 = nv2.normalize();
        double vpx = nv2.xCoord * rad;
        double vpy = nv2.yCoord * rad;
        double vpz = nv2.zCoord * rad;
        tessellator.addVertexWithUV(npx - vpx / 2.0D, npy - vpy / 2.0D, npz - vpz / 2.0D, 0.0D, 1.0D);
        tessellator.addVertexWithUV(npx + vpx - vpx / 2.0D, npy + vpy - vpy / 2.0D, npz + vpz - vpz / 2.0D, 1.5D, 1.0D);
      } 
      tessellator.draw();
      Vec3 benor = Vec3.createVectorHelper(lpx, lpy, lpz);
      renderLoop(tessellator, (Entity)par1EntityRope, par2, par4 + 0.25D, par6, benor, nvU);
      RenderHelper.enableStandardItemLighting();
      GL11.glEnable(2896);
      GL11.glEnable(2884);
    } 
  }
  
  protected ResourceLocation func_110791_a(EntityRope par1EntityRope) {
    return field_110792_a;
  }
  
  protected ResourceLocation getEntityTexture(Entity par1Entity) {
    return func_110791_a((EntityRope)par1Entity);
  }
  
  public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
    doRenderFishHook((EntityRope)par1Entity, par2, par4, par6, par8, par9);
  }
  
  public void renderRope(Tessellator tessellator, Vec3 nvU, Entity par1EntityRope, double x1, double y1, double z1, double x2, double y2, double z2) {
    double rad = 0.05D;
    double npx = x1;
    double npxn = x2;
    double npy = y1;
    double npyn = y2;
    double npz = z1;
    double npzn = z2;
    Vec3 nv3 = Vec3.createVectorHelper(npxn - npx, npyn - npy, npzn - npz);
    Vec3 nv1 = nv3.crossProduct(nvU);
    nv1 = nv1.normalize();
    Vec3 nv2 = nv1.crossProduct(nv3);
    nv2 = nv2.normalize();
    double upx = nv1.xCoord * rad;
    double upy = nv1.yCoord * rad;
    double upz = nv1.zCoord * rad;
    double vpx = nv2.xCoord * rad;
    double vpy = nv2.yCoord * rad;
    double vpz = nv2.zCoord * rad;
    bindTexture(BeamTextures);
    tessellator.startDrawing(5);
    tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
    tessellator.addVertexWithUV(npxn - upx / 2.0D, npyn - upy / 2.0D, npzn - upz / 2.0D, 0.0D, 1.0D);
    tessellator.addVertexWithUV(npxn + upx - upx / 2.0D, npyn + upy - upy / 2.0D, npzn + upz - upz / 2.0D, 1.5D, 1.0D);
    tessellator.addVertexWithUV(npx - upx / 2.0D, npy - upy / 2.0D, npz - upz / 2.0D, 0.0D, 1.0D);
    tessellator.addVertexWithUV(npx + upx - upx / 2.0D, npy + upy - upy / 2.0D, npz + upz - upz / 2.0D, 1.5D, 1.0D);
    tessellator.draw();
    tessellator.startDrawing(5);
    tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
    tessellator.addVertexWithUV(npxn - vpx / 2.0D, npyn - vpy / 2.0D, npzn - vpz / 2.0D, 0.0D, 1.0D);
    tessellator.addVertexWithUV(npxn + vpx - vpx / 2.0D, npyn + vpy - vpy / 2.0D, npzn + vpz - vpz / 2.0D, 1.5D, 1.0D);
    tessellator.addVertexWithUV(npx - vpx / 2.0D, npy - vpy / 2.0D, npz - vpz / 2.0D, 0.0D, 1.0D);
    tessellator.addVertexWithUV(npx + vpx - vpx / 2.0D, npy + vpy - vpy / 2.0D, npz + vpz - vpz / 2.0D, 1.5D, 1.0D);
    tessellator.draw();
  }
  
  public void renderLoop(Tessellator tessellator, Entity par1EntityRope, double x1, double y1, double z1, Vec3 normal, Vec3 nvU) {
    if ((((EntityRope)par1EntityRope).BlockPosY == 0.0D && ((EntityRope)par1EntityRope).GrabType < 5) || ((EntityRope)par1EntityRope).GrabType < 2) {
      double sqrt2 = 0.705D;
      double sqrt2_2 = 0.35D;
      if (((EntityRope)par1EntityRope).inGround) {
        sqrt2 = 0.3525D;
        sqrt2_2 = 0.175D;
      } 
      normal = normal.normalize();
      Vec3 nv1 = normal.crossProduct(nvU);
      nv1 = nv1.normalize();
      double hpx = nv1.xCoord;
      double hpy = nv1.yCoord;
      double hpz = nv1.zCoord;
      double npx = normal.xCoord;
      double npy = normal.yCoord;
      double npz = normal.zCoord;
      renderRope(tessellator, nvU, par1EntityRope, x1, y1, z1, x1 + npx * sqrt2_2 + hpx * sqrt2_2, y1 + npy * sqrt2_2 + hpy * sqrt2_2, z1 + npz * sqrt2_2 + hpz * sqrt2_2);
      renderRope(tessellator, nvU, par1EntityRope, x1 + npx * sqrt2_2 + hpx * sqrt2_2, y1 + npy * sqrt2_2 + hpy * sqrt2_2, z1 + npz * sqrt2_2 + hpz * sqrt2_2, x1 + npx * sqrt2, y1 + npy * sqrt2, z1 + npz * sqrt2);
      renderRope(tessellator, nvU, par1EntityRope, x1 + npx * sqrt2, y1 + npy * sqrt2, z1 + npz * sqrt2, x1 + npx * sqrt2_2 - hpx * sqrt2_2, y1 + npy * sqrt2_2 - hpy * sqrt2_2, z1 + npz * sqrt2_2 - hpz * sqrt2_2);
      renderRope(tessellator, nvU, par1EntityRope, x1 + npx * sqrt2_2 - hpx * sqrt2_2, y1 + npy * sqrt2_2 - hpy * sqrt2_2, z1 + npz * sqrt2_2 - hpz * sqrt2_2, x1, y1, z1);
    } 
    if (((EntityRope)par1EntityRope).GrabType == 2) {
      double xc = x1;
      double yc = y1;
      double zc = z1;
      double rad = 0.55D;
      renderRope(tessellator, nvU, par1EntityRope, xc - rad, yc, zc - rad, xc + rad, yc, zc - rad);
      renderRope(tessellator, nvU, par1EntityRope, xc + rad, yc, zc - rad, xc + rad, yc, zc + rad);
      renderRope(tessellator, nvU, par1EntityRope, xc + rad, yc, zc + rad, xc - rad, yc, zc + rad);
      renderRope(tessellator, nvU, par1EntityRope, xc - rad, yc, zc + rad, xc - rad, yc, zc - rad);
    } 
    if (((EntityRope)par1EntityRope).GrabType == 3) {
      double xc = x1;
      double yc = y1;
      double zc = z1;
      double rad = 0.55D;
      double ds = 0.001D;
      renderRope(tessellator, nvU, par1EntityRope, xc - rad, yc - rad, zc, xc + rad + ds, yc - rad, zc);
      renderRope(tessellator, nvU, par1EntityRope, xc + rad + ds, yc - rad, zc, xc + rad, yc + rad, zc);
      renderRope(tessellator, nvU, par1EntityRope, xc + rad, yc + rad, zc, xc - rad - ds, yc + rad, zc);
      renderRope(tessellator, nvU, par1EntityRope, xc - rad - ds, yc + rad, zc, xc - rad, yc - rad, zc);
    } 
    if (((EntityRope)par1EntityRope).GrabType == 4) {
      double xc = x1;
      double yc = y1;
      double zc = z1;
      double rad = 0.55D;
      double ds = 0.001D;
      renderRope(tessellator, nvU, par1EntityRope, xc, yc - rad, zc - rad, xc, yc - rad, zc + rad + ds);
      renderRope(tessellator, nvU, par1EntityRope, xc, yc - rad, zc + rad + ds, xc, yc + rad, zc + rad);
      renderRope(tessellator, nvU, par1EntityRope, xc, yc + rad, zc + rad, xc, yc + rad, zc - rad - ds);
      renderRope(tessellator, nvU, par1EntityRope, xc, yc + rad, zc - rad - ds, xc, yc - rad, zc - rad);
    } 
    if (((EntityRope)par1EntityRope).GrabType == 5) {
      double sqrt2 = 0.705D;
      double sqrt2_2 = 0.35D;
      sqrt2 = 0.3525D;
      sqrt2_2 = 0.175D;
      normal = normal.normalize();
      Vec3 nv1 = normal.crossProduct(nvU);
      nv1 = nv1.normalize();
      double hpx = nv1.xCoord;
      double hpy = nv1.yCoord;
      double hpz = nv1.zCoord;
      double npx = normal.xCoord;
      double npy = normal.yCoord;
      double npz = normal.zCoord;
      renderRope(tessellator, nvU, par1EntityRope, x1, y1, z1, x1 + npx * sqrt2_2 + hpx * sqrt2_2, y1 + npy * sqrt2_2 + hpy * sqrt2_2, z1 + npz * sqrt2_2 + hpz * sqrt2_2);
      renderRope(tessellator, nvU, par1EntityRope, x1 + npx * sqrt2_2 + hpx * sqrt2_2, y1 + npy * sqrt2_2 + hpy * sqrt2_2, z1 + npz * sqrt2_2 + hpz * sqrt2_2, x1 + npx * sqrt2, y1 + npy * sqrt2, z1 + npz * sqrt2);
      renderRope(tessellator, nvU, par1EntityRope, x1 + npx * sqrt2, y1 + npy * sqrt2, z1 + npz * sqrt2, x1 + npx * sqrt2_2 - hpx * sqrt2_2, y1 + npy * sqrt2_2 - hpy * sqrt2_2, z1 + npz * sqrt2_2 - hpz * sqrt2_2);
      renderRope(tessellator, nvU, par1EntityRope, x1 + npx * sqrt2_2 - hpx * sqrt2_2, y1 + npy * sqrt2_2 - hpy * sqrt2_2, z1 + npz * sqrt2_2 - hpz * sqrt2_2, x1, y1, z1);
    } 
    if (((EntityRope)par1EntityRope).GrabType == 6) {
      double sqrt2 = 0.705D;
      double sqrt2_2 = 0.35D;
      sqrt2 = 0.3525D;
      sqrt2_2 = 0.175D;
      normal = normal.normalize();
      Vec3 nv1 = normal.crossProduct(nvU);
      nv1 = nv1.normalize();
      double hpx = nv1.xCoord;
      double hpy = nv1.yCoord;
      double hpz = nv1.zCoord;
      double npx = normal.xCoord;
      double npy = normal.yCoord;
      double npz = normal.zCoord;
      renderRope(tessellator, nvU, par1EntityRope, x1, y1, z1, x1 + npx * sqrt2_2 + hpx * sqrt2_2, y1 + npy * sqrt2_2 + hpy * sqrt2_2, z1 + npz * sqrt2_2 + hpz * sqrt2_2);
      renderRope(tessellator, nvU, par1EntityRope, x1 + npx * sqrt2_2 + hpx * sqrt2_2, y1 + npy * sqrt2_2 + hpy * sqrt2_2, z1 + npz * sqrt2_2 + hpz * sqrt2_2, x1 + npx * sqrt2, y1 + npy * sqrt2, z1 + npz * sqrt2);
      renderRope(tessellator, nvU, par1EntityRope, x1 + npx * sqrt2, y1 + npy * sqrt2, z1 + npz * sqrt2, x1 + npx * sqrt2_2 - hpx * sqrt2_2, y1 + npy * sqrt2_2 - hpy * sqrt2_2, z1 + npz * sqrt2_2 - hpz * sqrt2_2);
      renderRope(tessellator, nvU, par1EntityRope, x1 + npx * sqrt2_2 - hpx * sqrt2_2, y1 + npy * sqrt2_2 - hpy * sqrt2_2, z1 + npz * sqrt2_2 - hpz * sqrt2_2, x1, y1, z1);
    } 
  }
}
