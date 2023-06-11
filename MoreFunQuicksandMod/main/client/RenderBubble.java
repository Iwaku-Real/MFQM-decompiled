package MoreFunQuicksandMod.main.client;

import MoreFunQuicksandMod.main.entity.EntityBubble;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderBubble extends Render {
  private final ResourceLocation field_110637_a = new ResourceLocation("morefunquicksandmod", "textures/blocks/Larvae.png");
  
  public void doRenderBubble(EntityBubble par1, double par2, double par4, double par6, float par8, float par9) {
    if (Sys.getTime() - par1.ticksWorld < 0L)
      return; 
    Tessellator tessellator = Tessellator.instance;
    IIcon Icon = par1.block.getIcon(1, par1.meta);
    bindTexture(TextureMap.locationBlocksTexture);
    GL11.glPushMatrix();
    GL11.glEnable(3008);
    GL11.glEnable(3553);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    int xx = (int)Math.floor(par1.posX);
    int zz = (int)Math.floor(par1.posZ);
    int yy = (int)Math.floor(par1.posY);
    int intColor = par1.block.colorMultiplier((IBlockAccess)par1.worldObj, xx, yy, zz);
    int red = (0xFF0000 & intColor) / 65536;
    int green = (0xFF00 & intColor) / 256;
    int blue = 0xFF & intColor;
    GL11.glColor4f(0.75F * red / 255.0F, 0.75F * green / 255.0F, 0.75F * blue / 255.0F, 0.9F);
    GL11.glEnable(32826);
    float tc = Math.min(1.0F, Math.max(0.0F, (float)(Sys.getTime() - par1.ticksWorld) / par1.liveTime));
    float tc2 = 1.0F - (float)Math.pow(2.0D, (-tc * 4.0F));
    float scx = 0.03125F * par1.size * tc2;
    float scy = 0.025F * par1.size * tc2;
    GL11.glTranslatef((float)par2, (float)par4 - 10.0F * scy + 5.0F * scy * tc2, (float)par6);
    GL11.glRotatef(par1.rotate / 3.1415927F * 180.0F, 0.0F, 1.0F, 0.0F);
    renderModel(tessellator, 0.0D, 0.0D, 0.0D, Icon, scx, scy);
    GL11.glDisable(32826);
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    GL11.glDisable(3042);
    GL11.glPopMatrix();
  }
  
  protected ResourceLocation getEntityTexture(Entity par1Entity) {
    return this.field_110637_a;
  }
  
  public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
    doRenderBubble((EntityBubble)par1Entity, par2, par4, par6, par8, par9);
  }
  
  public void renderModel(Tessellator tessellator, double x, double y, double z, IIcon Icon, float scx, float scy) {
    GL11.glScalef(scx, scy, scx);
    renderCube(tessellator, -4.0D, 1.0D, -4.0D, 8.0D, 8.0D, 8.0D, Icon);
    renderCube(tessellator, -3.0D, 2.0D, -5.0D, 6.0D, 6.0D, 10.0D, Icon);
    renderCube(tessellator, -5.0D, 2.0D, -3.0D, 10.0D, 6.0D, 6.0D, Icon);
    renderCube(tessellator, -3.0D, 0.0D, -3.0D, 6.0D, 10.0D, 6.0D, Icon);
  }
  
  public void renderCube(Tessellator tessellator, double x, double y, double z, double xs, double ys, double zs, IIcon Icon) {
    double minU = Icon.getMinU();
    double minV = Icon.getMinV();
    double maxU = minU + (Icon.getMaxU() - minU) / 2.0D;
    double maxV = minV + (Icon.getMaxV() - minV) / 2.0D;
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
