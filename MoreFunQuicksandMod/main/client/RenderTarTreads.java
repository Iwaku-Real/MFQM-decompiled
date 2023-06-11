package MoreFunQuicksandMod.main.client;

import MoreFunQuicksandMod.main.MFQM;
import MoreFunQuicksandMod.main.entity.EntityTarTreads;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderTarTreads extends Render {
  private static final ResourceLocation TarTreads0 = new ResourceLocation("morefunquicksandmod", "textures/blocks/TarTread.png");
  
  public void doRenderTentacles(EntityTarTreads par1EntityTarTreads, double par2, double par4, double par6, float par8, float par9) {
    if (par1EntityTarTreads.target == null)
      return; 
    if (!(par1EntityTarTreads.target instanceof net.minecraft.entity.EntityLivingBase))
      return; 
    if (par1EntityTarTreads.target.isDead || !par1EntityTarTreads.target.isEntityAlive())
      return; 
    double x = 0.0D;
    double y = 0.0D;
    double z = 0.0D;
    Tessellator tessellator = Tessellator.instance;
    int px = (int)Math.floor(par1EntityTarTreads.posX);
    int py = (int)Math.floor(par1EntityTarTreads.posY + 1.0D);
    int pz = (int)Math.floor(par1EntityTarTreads.posZ);
    double pxq = px - par1EntityTarTreads.posX;
    double pzq = pz - par1EntityTarTreads.posZ;
    float f = (4 * MFQM.TarBlock.getMixedBrightnessForBlock((IBlockAccess)par1EntityTarTreads.worldObj, px, py, pz));
    int l = par1EntityTarTreads.worldObj.getLightBrightnessForSkyBlocks(px, py, pz, 0);
    int l1 = l % 65536;
    int l2 = l / 65536;
    bindTexture(getEntityTexture((Entity)par1EntityTarTreads));
    GL11.glPushMatrix();
    GL11.glEnable(32826);
    OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, l1, l2);
    tessellator.setColorOpaque_F(f, f, f);
    double npx = par1EntityTarTreads.posX;
    double npy = par1EntityTarTreads.posY;
    double npz = par1EntityTarTreads.posZ;
    double npxn = par1EntityTarTreads.target.posX + par1EntityTarTreads.xs * par1EntityTarTreads.target.width;
    double npyn = par1EntityTarTreads.target.boundingBox.minY + par1EntityTarTreads.target.height * par1EntityTarTreads.hgrab;
    double npzn = par1EntityTarTreads.target.posZ + par1EntityTarTreads.zs * par1EntityTarTreads.target.width;
    Vec3 nv3 = Vec3.createVectorHelper(npxn - npx, npyn - npy, npzn - npz);
    double dis = Math.sqrt(Math.pow(nv3.xCoord, 2.0D) + Math.pow(nv3.yCoord, 2.0D) + Math.pow(nv3.zCoord, 2.0D));
    nv3 = nv3.normalize();
    double size = par1EntityTarTreads.rsize / (1.0D + dis / 10.0D);
    double lpx = -nv3.xCoord;
    double lpy = -nv3.yCoord;
    double lpz = -nv3.zCoord;
    double degree = 57.29577951308232D;
    double PITCH = Math.atan2(lpy, Math.sqrt(lpx * lpx + lpz * lpz)) * degree;
    double YAW = -(Math.atan2(lpx, lpz) * degree + 180.0D);
    GL11.glTranslatef((float)par2, (float)par4, (float)par6);
    GL11.glRotatef((float)-YAW, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef((float)PITCH + 90.0F, 1.0F, 0.0F, 0.0F);
    drawCrossedSquares(tessellator, size, -size, 0.0D, -size, dis);
    GL11.glDisable(32826);
    GL11.glPopMatrix();
  }
  
  public void drawCrossedSquares(Tessellator tessellator, double size, double xx, double height, double yy, double sacal) {
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
  
  protected ResourceLocation getEntityTexture(Entity par1Entity) {
    return TarTreads0;
  }
  
  public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
    doRenderTentacles((EntityTarTreads)par1Entity, par2, par4, par6, par8, par9);
  }
}
