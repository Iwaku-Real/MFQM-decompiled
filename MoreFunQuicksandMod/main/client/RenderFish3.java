package MoreFunQuicksandMod.main.client;

import MoreFunQuicksandMod.main.entity.EntityHook;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderFish3 extends Render {
  private static final ResourceLocation BeamTextures = new ResourceLocation("morefunquicksandmod", "textures/items/CableTex.png");
  
  private static final ResourceLocation BaseTextures = new ResourceLocation("morefunquicksandmod", "textures/items/GrapplingHookTex.png");
  
  public void doRenderFishHook(EntityHook par1EntityHook, double par2, double par4, double par6, float par8, float par9) {
    Tessellator tessellator = Tessellator.instance;
    if (par1EntityHook.curPlayer != null) {
      double d1, d2, d3, d6;
      boolean hookInHand = false;
      if (par1EntityHook.curPlayer.getCurrentEquippedItem() != null) {
        ItemStack itemStack = par1EntityHook.curPlayer.getCurrentEquippedItem();
        if (itemStack.getMetadata() % 2 != 0)
          hookInHand = true; 
      } 
      float f9 = par1EntityHook.curPlayer.getSwingProgress(par9);
      float f10 = MathHelper.sin(MathHelper.sqrt_float(f9) * 3.1415927F);
      if (hookInHand) {
        Vec3 vec3 = Vec3.createVectorHelper(-0.5D, 0.03D, 0.8D);
        vec3.rotateAroundX(-(par1EntityHook.curPlayer.prevRotationPitch + (par1EntityHook.curPlayer.rotationPitch - par1EntityHook.curPlayer.prevRotationPitch) * par9) * 3.1415927F / 180.0F);
        vec3.rotateAroundY(-(par1EntityHook.curPlayer.prevRotationYaw + (par1EntityHook.curPlayer.rotationYaw - par1EntityHook.curPlayer.prevRotationYaw) * par9) * 3.1415927F / 180.0F);
        vec3.rotateAroundY(f10 * 0.5F);
        vec3.rotateAroundX(-f10 * 0.7F);
        d1 = par1EntityHook.curPlayer.prevPosX + (par1EntityHook.curPlayer.posX - par1EntityHook.curPlayer.prevPosX) * par9 + vec3.xCoord;
        d2 = par1EntityHook.curPlayer.prevPosY + (par1EntityHook.curPlayer.posY - par1EntityHook.curPlayer.prevPosY) * par9 + vec3.yCoord;
        d3 = par1EntityHook.curPlayer.prevPosZ + (par1EntityHook.curPlayer.posZ - par1EntityHook.curPlayer.prevPosZ) * par9 + vec3.zCoord;
        d6 = (par1EntityHook.curPlayer == (Minecraft.getMinecraft()).thePlayer) ? 0.0D : par1EntityHook.curPlayer.getEyeHeight();
      } else {
        float f11a = (par1EntityHook.curPlayer.prevRotationYaw + (par1EntityHook.curPlayer.rotationYaw - par1EntityHook.curPlayer.prevRotationYaw) * par9) * 3.1415927F / 180.0F;
        double d7a = MathHelper.sin(f11a);
        double d8a = MathHelper.cos(f11a);
        d1 = par1EntityHook.curPlayer.prevPosX + (par1EntityHook.curPlayer.posX - par1EntityHook.curPlayer.prevPosX) * par9 - d8a * 0.35D - d7a * (0.2D + f10 * 0.5D);
        d2 = par1EntityHook.curPlayer.prevPosY + (par1EntityHook.curPlayer.posY - par1EntityHook.curPlayer.prevPosY) * par9 - 0.5D + f10 * 0.7D;
        d3 = par1EntityHook.curPlayer.prevPosZ + (par1EntityHook.curPlayer.posZ - par1EntityHook.curPlayer.prevPosZ) * par9 - d7a * 0.35D + d8a * (0.2D + f10 * 0.5D);
        d6 = (par1EntityHook.curPlayer == (Minecraft.getMinecraft()).thePlayer) ? 0.0D : par1EntityHook.curPlayer.getEyeHeight();
      } 
      if (this.renderManager.options.thirdPersonView > 0 || par1EntityHook.curPlayer != (Minecraft.getMinecraft()).thePlayer)
        if (!hookInHand) {
          float f11 = (par1EntityHook.curPlayer.prevRenderYawOffset + (par1EntityHook.curPlayer.renderYawOffset - par1EntityHook.curPlayer.prevRenderYawOffset) * par9) * 3.1415927F / 180.0F;
          double d7 = MathHelper.sin(f11);
          double d8 = MathHelper.cos(f11);
          d1 = par1EntityHook.curPlayer.prevPosX + (par1EntityHook.curPlayer.posX - par1EntityHook.curPlayer.prevPosX) * par9 - d8 * 0.35D - d7 * (0.2D + f10 * 0.5D);
          d2 = par1EntityHook.curPlayer.prevPosY + d6 + (par1EntityHook.curPlayer.posY - par1EntityHook.curPlayer.prevPosY) * par9 - 1.05D + f10 * 0.7D;
          d3 = par1EntityHook.curPlayer.prevPosZ + (par1EntityHook.curPlayer.posZ - par1EntityHook.curPlayer.prevPosZ) * par9 - d7 * 0.35D + d8 * (0.2D + f10 * 0.5D);
        } else {
          float f11 = (par1EntityHook.curPlayer.prevRenderYawOffset + (par1EntityHook.curPlayer.renderYawOffset - par1EntityHook.curPlayer.prevRenderYawOffset) * par9) * 3.1415927F / 180.0F;
          double d7 = MathHelper.sin(f11);
          double d8 = MathHelper.cos(f11);
          d1 = par1EntityHook.curPlayer.prevPosX + (par1EntityHook.curPlayer.posX - par1EntityHook.curPlayer.prevPosX) * par9 - d8 * 0.35D - d7 * (0.3D + f10 * 0.5D);
          d2 = par1EntityHook.curPlayer.prevPosY + d6 + (par1EntityHook.curPlayer.posY - par1EntityHook.curPlayer.prevPosY) * par9 - 0.25D + f10 * 0.7D;
          d3 = par1EntityHook.curPlayer.prevPosZ + (par1EntityHook.curPlayer.posZ - par1EntityHook.curPlayer.prevPosZ) * par9 - d7 * 0.35D + d8 * (0.3D + f10 * 0.5D);
        }  
      double d9 = par1EntityHook.prevPosX + (par1EntityHook.posX - par1EntityHook.prevPosX) * par9;
      double d10 = par1EntityHook.prevPosY + (par1EntityHook.posY - par1EntityHook.prevPosY) * par9;
      double d11 = par1EntityHook.prevPosZ + (par1EntityHook.posZ - par1EntityHook.prevPosZ) * par9;
      double d12 = (float)(d1 - d9);
      double d13 = (float)(d2 - d10);
      double d14 = (float)(d3 - d11);
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
      boolean setDir = false;
      double truePar2 = par2;
      double truePar4 = par4;
      double truePar6 = par6;
      Vec3 dir = Vec3.createVectorHelper(0.0D, 0.0D, 0.0D);
      switch (par1EntityHook.GrabType) {
        case 1:
          dir.xCoord = 0.0D;
          dir.yCoord = -1.001D;
          dir.zCoord = 0.0D;
          setDir = true;
          break;
        case 2:
          dir.xCoord = 0.0D;
          dir.yCoord = 1.001D;
          dir.zCoord = 0.0D;
          setDir = true;
          break;
        case 3:
          dir.xCoord = -1.001D;
          dir.yCoord = 0.0D;
          dir.zCoord = 0.0D;
          setDir = true;
          break;
        case 4:
          dir.xCoord = 1.001D;
          dir.yCoord = 0.0D;
          dir.zCoord = 0.0D;
          setDir = true;
          break;
        case 5:
          dir.xCoord = 0.0D;
          dir.yCoord = 0.0D;
          dir.zCoord = -1.001D;
          setDir = true;
          break;
        case 6:
          dir.xCoord = 0.0D;
          dir.yCoord = 0.0D;
          dir.zCoord = 1.001D;
          setDir = true;
          break;
      } 
      truePar2 -= dir.xCoord * 0.625D;
      truePar4 -= dir.yCoord * 0.625D;
      truePar6 -= dir.zCoord * 0.625D;
      if (setDir)
        truePar4 -= 0.15D; 
      if (par1EntityHook.GrabType > 6)
        truePar4 = par4 - 0.25D; 
      int i;
      for (i = 0; i <= b2; i++) {
        float f12 = i / b2;
        double npx = truePar2 + d12 * f12;
        double npy = truePar4 + d13 * f12 + 0.15D;
        double npz = truePar6 + d14 * f12;
        f12 = (i + 1) / b2;
        double npxn = truePar2 + d12 * f12;
        double npyn = truePar4 + d13 * f12 + 0.15D;
        double npzn = truePar6 + d14 * f12;
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
        double npx = truePar2 + d12 * f12;
        double npy = truePar4 + d13 * f12 + 0.15D;
        double npz = truePar6 + d14 * f12;
        f12 = (i + 1) / b2;
        double npxn = truePar2 + d12 * f12;
        double npyn = truePar4 + d13 * f12 + 0.15D;
        double npzn = truePar6 + d14 * f12;
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
      RenderHelper.enableStandardItemLighting();
      GL11.glEnable(2896);
      GL11.glEnable(2884);
      Vec3 benor = Vec3.createVectorHelper(lpx, lpy, lpz);
      if (setDir)
        benor = dir; 
      bindTexture(BaseTextures);
      GL11.glPushMatrix();
      GL11.glEnable(32826);
      GL11.glTranslatef((float)truePar2, (float)(truePar4 + 0.15D), (float)truePar6);
      renderHook(tessellator, (Entity)par1EntityHook, 0.0D, 0.0D, 0.0D, benor);
      GL11.glDisable(32826);
      GL11.glPopMatrix();
    } 
  }
  
  protected ResourceLocation func_110791_a(EntityHook par1EntityHook) {
    return BeamTextures;
  }
  
  protected ResourceLocation getEntityTexture(Entity par1Entity) {
    return func_110791_a((EntityHook)par1Entity);
  }
  
  public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
    doRenderFishHook((EntityHook)par1Entity, par2, par4, par6, par8, par9);
  }
  
  public void renderRope(Tessellator tessellator, Vec3 nvU, Entity par1EntityHook, double x1, double y1, double z1, double x2, double y2, double z2) {
    double rad = 0.035D;
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
  
  public void renderHook(Tessellator tessellator, Entity par1EntityHook, double x, double y, double z, Vec3 nvN) {
    double ss = 0.75D;
    float PITCH = 0.0F;
    float YAW = 0.0F;
    nvN = nvN.normalize();
    float len = MathHelper.sqrt_float((float)(Math.pow(nvN.xCoord, 2.0D) + Math.pow(nvN.yCoord, 2.0D) + Math.pow(nvN.zCoord, 2.0D)));
    float wSlenwY = (float)(Math.pow(nvN.zCoord, 2.0D) + Math.pow(nvN.xCoord, 2.0D));
    float wSlenwZ = (float)(Math.pow(nvN.yCoord, 2.0D) + Math.pow(nvN.xCoord, 2.0D));
    float lenwY = MathHelper.sqrt_float(wSlenwY);
    float lenwZ = MathHelper.sqrt_float(wSlenwZ);
    int nega = 0;
    if (nvN.xCoord < 0.0D) {
      wSlenwY = -wSlenwY;
      wSlenwZ = -wSlenwZ;
      nega = 1;
    } 
    PITCH = (float)Math.acos((wSlenwY / len * lenwY)) / 3.1415927F * 180.0F;
    if (nvN.yCoord < 0.0D)
      PITCH = -PITCH; 
    YAW = (float)Math.acos((wSlenwZ / len * lenwZ)) / 3.1415927F * 180.0F;
    if (nvN.zCoord > 0.0D)
      YAW = -YAW; 
    GL11.glRotatef(YAW + (180 * nega), 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(PITCH, 0.0F, 0.0F, 1.0F);
    renderCube(tessellator, x - 0.1D * ss, y - 0.055D * ss, z - 0.055D * ss, 0.25D * ss, 0.11D * ss, 0.11D * ss);
    renderCube(tessellator, x + 0.05D * ss, y + 0.05499999999999999D * ss, z - 0.02D * ss, 0.2D * ss, 0.04D * ss, 0.04D * ss);
    renderCube(tessellator, x + 0.05D * ss, y + -0.095D * ss, z - 0.02D * ss, 0.2D * ss, 0.04D * ss, 0.04D * ss);
    renderCube(tessellator, x + 0.05D * ss, y - 0.02D * ss, z + 0.05499999999999999D * ss, 0.2D * ss, 0.04D * ss, 0.04D * ss);
    renderCube(tessellator, x + 0.05D * ss, y - 0.02D * ss, z + -0.095D * ss, 0.2D * ss, 0.04D * ss, 0.04D * ss);
  }
  
  public void renderCube(Tessellator tessellator, double x, double y, double z, double xs, double ys, double zs) {
    double minU = 0.0D;
    double minV = 0.0D;
    double maxU = 1.0D;
    double maxV = 1.0D;
    tessellator.startDrawingQuads();
    tessellator.setNormal(0.0F, 1.0F, 0.0F);
    tessellator.addVertexWithUV(x, y + ys, z, maxU, minV);
    tessellator.addVertexWithUV(x, y + ys, z + zs, maxU, maxV);
    tessellator.addVertexWithUV(x + xs, y + ys, z + zs, minU, maxV);
    tessellator.addVertexWithUV(x + xs, y + ys, z, minU, minV);
    tessellator.draw();
    tessellator.startDrawingQuads();
    tessellator.setNormal(0.0F, -1.0F, 0.0F);
    tessellator.addVertexWithUV(x + xs, y, z, maxU, minV);
    tessellator.addVertexWithUV(x + xs, y, z + zs, maxU, maxV);
    tessellator.addVertexWithUV(x, y, z + zs, minU, maxV);
    tessellator.addVertexWithUV(x, y, z, minU, minV);
    tessellator.draw();
    tessellator.startDrawingQuads();
    tessellator.setNormal(0.0F, 0.0F, -1.0F);
    tessellator.addVertexWithUV(x, y, z, maxU, minV);
    tessellator.addVertexWithUV(x, y + ys, z, maxU, maxV);
    tessellator.addVertexWithUV(x + xs, y + ys, z, minU, maxV);
    tessellator.addVertexWithUV(x + xs, y, z, minU, minV);
    tessellator.draw();
    tessellator.startDrawingQuads();
    tessellator.setNormal(0.0F, 0.0F, 1.0F);
    tessellator.addVertexWithUV(x + xs, y, z + zs, maxU, minV);
    tessellator.addVertexWithUV(x + xs, y + ys, z + zs, maxU, maxV);
    tessellator.addVertexWithUV(x, y + ys, z + zs, minU, maxV);
    tessellator.addVertexWithUV(x, y, z + zs, minU, minV);
    tessellator.draw();
    tessellator.startDrawingQuads();
    tessellator.setNormal(-1.0F, 0.0F, 0.0F);
    tessellator.addVertexWithUV(x, y, z + zs, maxU, minV);
    tessellator.addVertexWithUV(x, y + ys, z + zs, maxU, maxV);
    tessellator.addVertexWithUV(x, y + ys, z, minU, maxV);
    tessellator.addVertexWithUV(x, y, z, minU, minV);
    tessellator.draw();
    tessellator.startDrawingQuads();
    tessellator.setNormal(1.0F, 0.0F, 0.0F);
    tessellator.addVertexWithUV(x + xs, y, z, minU, minV);
    tessellator.addVertexWithUV(x + xs, y + ys, z, minU, maxV);
    tessellator.addVertexWithUV(x + xs, y + ys, z + zs, maxU, maxV);
    tessellator.addVertexWithUV(x + xs, y, z + zs, maxU, minV);
    tessellator.draw();
  }
}
