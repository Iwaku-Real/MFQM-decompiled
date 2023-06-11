package MoreFunQuicksandMod.main.Tileentity;

import MoreFunQuicksandMod.main.MFQM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class TileEntityBlossomRenderer extends TileEntitySpecialRenderer {
  private final ResourceLocation field_110637_a = new ResourceLocation("morefunquicksandmod", "textures/blocks/Meat10.png");
  
  public void renderBlossom(TileEntityBlossom par1TileEntityBlossom, double par2, double par4, double par6, float par8) {
    double x = 0.0D;
    double y = 0.0D;
    double z = 0.0D;
    int meta = par1TileEntityBlossom.getWorld().getBlockMetadata(par1TileEntityBlossom.xCoord, par1TileEntityBlossom.yCoord, par1TileEntityBlossom.zCoord);
    double upper = 1.0D;
    if (meta > 5 && meta < 10) {
      int side = meta - 6;
      Tessellator tessellator = Tessellator.instance;
      int lightValue = 0;
      if (side == 0)
        lightValue = MFQM.MeatBlock.getMixedBrightnessForBlock((IBlockAccess)par1TileEntityBlossom.getWorld(), par1TileEntityBlossom.xCoord, par1TileEntityBlossom.yCoord, par1TileEntityBlossom.zCoord - 1); 
      if (side == 1)
        lightValue = MFQM.MeatBlock.getMixedBrightnessForBlock((IBlockAccess)par1TileEntityBlossom.getWorld(), par1TileEntityBlossom.xCoord + 1, par1TileEntityBlossom.yCoord, par1TileEntityBlossom.zCoord); 
      if (side == 2)
        lightValue = MFQM.MeatBlock.getMixedBrightnessForBlock((IBlockAccess)par1TileEntityBlossom.getWorld(), par1TileEntityBlossom.xCoord, par1TileEntityBlossom.yCoord, par1TileEntityBlossom.zCoord + 1); 
      if (side == 3)
        lightValue = MFQM.MeatBlock.getMixedBrightnessForBlock((IBlockAccess)par1TileEntityBlossom.getWorld(), par1TileEntityBlossom.xCoord - 1, par1TileEntityBlossom.yCoord, par1TileEntityBlossom.zCoord); 
      int bright = lightValue;
      int brightX = bright % 65536;
      int brightY = bright / 65536;
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, brightX, brightY);
      tessellator.setBrightness(lightValue);
      tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);
      bindTexture(this.field_110637_a);
      GL11.glPushMatrix();
      GL11.glEnable(32826);
      GL11.glTranslatef((float)par2, (float)par4, (float)par6);
      double ph = par1TileEntityBlossom.phase;
      double ampl2 = par1TileEntityBlossom.ampl;
      double ampl = 0.025D + upper / 30.0D;
      double sz = 0.5D + upper / 15.0D;
      double ampld = 0.025D + upper / 30.0D;
      double timd1 = Sys.getTime() / (200.0D + upper * 100.0D) + ph;
      float timf1 = (float)(timd1 - Math.floor(timd1 / 6.28318D) * 6.28318D);
      double timd2 = Sys.getTime() / (200.0D + upper * 100.0D) + ph + 1.570795D;
      float timf2 = (float)(timd2 - Math.floor(timd2 / 6.28318D) * 6.28318D);
      double timd3 = Sys.getTime() / (200.0D + upper * 100.0D) + ph + 3.14159D;
      float timf3 = (float)(timd3 - Math.floor(timd3 / 6.28318D) * 6.28318D);
      double timd4 = Sys.getTime() / (200.0D + upper * 100.0D) + ph + 1.570795D + 3.14159D;
      float timf4 = (float)(timd4 - Math.floor(timd4 / 6.28318D) * 6.28318D);
      double sina = 0.0D;
      double sinb = 0.0D;
      double sinc = 0.0D;
      double sind = 0.0D;
      double szd = ampl2;
      double cof = Math.max((ampl2 - 0.25D) / 0.05D, 0.25D);
      sina = MathHelper.sin(timf1) * cof;
      sinb = MathHelper.sin(timf2) * cof;
      sinc = MathHelper.sin(timf3) * cof;
      sind = MathHelper.sin(timf4) * cof;
      if (side == 0) {
        renderFleshCube(tessellator, x - 0.3125D, y + 0.25D, z - 0.25D - szd - szd * sina / 4.0D, side, sz + sina * ampl);
        renderFleshCube(tessellator, x, y, z - 0.25D - szd - szd * sinc / 4.0D, side, sz + sinc * ampl);
        renderFleshCube(tessellator, x - 0.3125D, y - 0.25D, z - 0.25D - szd - szd * sinb / 4.0D, side, sz + sinb * ampl);
        renderFleshCube(tessellator, x + 0.0625D, y - 0.375D, z - 0.25D - szd - szd * sina / 4.0D, side, sz + sina * ampl);
        renderFleshCube(tessellator, x + 0.1875D, y + 0.3125D, z - 0.25D - szd - szd * sinb / 4.0D, side, sz + sinb * ampl);
        renderFleshCube(tessellator, x + 0.3125D, y - 0.0625D, z - 0.25D - szd - szd * sind / 4.0D, side, sz + sind * ampl);
      } 
      if (side == 2) {
        renderFleshCube(tessellator, x - 0.3125D, y + 0.25D, z + szd + szd * sina / 4.0D, side, sz + sina * ampl);
        renderFleshCube(tessellator, x, y, z + szd + szd * sinc / 4.0D, side, sz + sinc * ampl);
        renderFleshCube(tessellator, x - 0.3125D, y - 0.25D, z + szd + szd * sinb / 4.0D, side, sz + sinb * ampl);
        renderFleshCube(tessellator, x + 0.0625D, y - 0.375D, z + szd + szd * sina / 4.0D, side, sz + sina * ampl);
        renderFleshCube(tessellator, x + 0.1875D, y + 0.3125D, z + szd + szd * sinb / 4.0D, side, sz + sinb * ampl);
        renderFleshCube(tessellator, x + 0.3125D, y - 0.0625D, z + szd + szd * sind / 4.0D, side, sz + sind * ampl);
      } 
      if (side == 3) {
        renderFleshCube(tessellator, x - 0.25D - szd - szd * sina / 4.0D, y + 0.25D, z - 0.3125D, side, sz + sina * ampl);
        renderFleshCube(tessellator, x - 0.25D - szd - szd * sinc / 4.0D, y, z, side, sz + sinc * ampl);
        renderFleshCube(tessellator, x - 0.25D - szd - szd * sinb / 4.0D, y - 0.25D, z - 0.3125D, side, sz + sinb * ampl);
        renderFleshCube(tessellator, x - 0.25D - szd - szd * sina / 4.0D, y - 0.375D, z + 0.0625D, side, sz + sina * ampl);
        renderFleshCube(tessellator, x - 0.25D - szd - szd * sinb / 4.0D, y + 0.3125D, z + 0.1875D, side, sz + sinb * ampl);
        renderFleshCube(tessellator, x - 0.25D - szd - szd * sind / 4.0D, y - 0.0625D, z + 0.3125D, side, sz + sind * ampl);
      } 
      if (side == 1) {
        renderFleshCube(tessellator, x + szd + szd * sina / 4.0D, y + 0.25D, z - 0.3125D, side, sz + sina * ampl);
        renderFleshCube(tessellator, x + szd + szd * sinc / 4.0D, y, z, side, sz + sinc * ampl);
        renderFleshCube(tessellator, x + szd + szd * sinb / 4.0D, y - 0.25D, z - 0.3125D, side, sz + sinb * ampl);
        renderFleshCube(tessellator, x + szd + szd * sina / 4.0D, y - 0.375D, z + 0.0625D, side, sz + sina * ampl);
        renderFleshCube(tessellator, x + szd + szd * sinb / 4.0D, y + 0.3125D, z + 0.1875D, side, sz + sinb * ampl);
        renderFleshCube(tessellator, x + szd + szd * sind / 4.0D, y - 0.0625D, z + 0.3125D, side, sz + sind * ampl);
      } 
      GL11.glDisable(32826);
      GL11.glPopMatrix();
    } 
  }
  
  public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
    renderBlossom((TileEntityBlossom)par1TileEntity, par2, par4, par6, par8);
  }
  
  public void renderFleshCube(Tessellator tessellator, double x, double y, double z, int side, double scale) {
    double minU = 0.0D;
    double minV = 0.0D;
    double maxU = 1.0D;
    double maxV = 1.0D;
    double ss = 0.4D * scale;
    double sh = (1.0D - ss) / 2.0D;
    double ssx = 1.25D * scale;
    double shx = (1.25D - ssx) / 2.0D;
    double ssz = 1.25D * scale;
    double shz = (1.25D - ssz) / 2.0D;
    if (side == 0 || side == 2) {
      ssx = ss;
      shx = sh;
    } 
    if (side == 1 || side == 3) {
      ssz = ss;
      shz = sh;
    } 
    tessellator.startDrawingQuads();
    tessellator.setNormal(0.0F, 1.0F, 0.0F);
    tessellator.addVertexWithUV(x + shx, y + sh + ss, z + shz, maxU, minV);
    tessellator.addVertexWithUV(x + shx, y + sh + ss, z + shz + ssz, maxU, maxV);
    tessellator.addVertexWithUV(x + shx + ssx, y + sh + ss, z + shz + ssz, minU, maxV);
    tessellator.addVertexWithUV(x + shx + ssx, y + sh + ss, z + shz, minU, minV);
    tessellator.draw();
    tessellator.startDrawingQuads();
    tessellator.setNormal(0.0F, -1.0F, 0.0F);
    tessellator.addVertexWithUV(x + shx + ssx, y + sh, z + shz, maxU, minV);
    tessellator.addVertexWithUV(x + shx + ssx, y + sh, z + shz + ssz, maxU, maxV);
    tessellator.addVertexWithUV(x + shx, y + sh, z + shz + ssz, minU, maxV);
    tessellator.addVertexWithUV(x + shx, y + sh, z + shz, minU, minV);
    tessellator.draw();
    if (side != 2) {
      tessellator.startDrawingQuads();
      tessellator.setNormal(0.0F, 0.0F, -1.0F);
      tessellator.addVertexWithUV(x + shx, y + sh, z + shz, maxU, minV);
      tessellator.addVertexWithUV(x + shx, y + sh + ss, z + shz, maxU, maxV);
      tessellator.addVertexWithUV(x + shx + ssx, y + sh + ss, z + shz, minU, maxV);
      tessellator.addVertexWithUV(x + shx + ssx, y + sh, z + shz, minU, minV);
      tessellator.draw();
    } 
    if (side != 0) {
      tessellator.startDrawingQuads();
      tessellator.setNormal(0.0F, 0.0F, 1.0F);
      tessellator.addVertexWithUV(x + shx + ssx, y + sh, z + shz + ssz, maxU, minV);
      tessellator.addVertexWithUV(x + shx + ssx, y + sh + ss, z + shz + ssz, maxU, maxV);
      tessellator.addVertexWithUV(x + shx, y + sh + ss, z + shz + ssz, minU, maxV);
      tessellator.addVertexWithUV(x + shx, y + sh, z + shz + ssz, minU, minV);
      tessellator.draw();
    } 
    if (side != 1) {
      tessellator.startDrawingQuads();
      tessellator.setNormal(-1.0F, 0.0F, 0.0F);
      tessellator.addVertexWithUV(x + shx, y + sh, z + shz + ssz, maxU, minV);
      tessellator.addVertexWithUV(x + shx, y + sh + ss, z + shz + ssz, maxU, maxV);
      tessellator.addVertexWithUV(x + shx, y + sh + ss, z + shz, minU, maxV);
      tessellator.addVertexWithUV(x + shx, y + sh, z + shz, minU, minV);
      tessellator.draw();
    } 
    if (side != 3) {
      tessellator.startDrawingQuads();
      tessellator.setNormal(1.0F, 0.0F, 0.0F);
      tessellator.addVertexWithUV(x + shx + ssx, y + sh, z + shz, minU, minV);
      tessellator.addVertexWithUV(x + shx + ssx, y + sh + ss, z + shz, minU, maxV);
      tessellator.addVertexWithUV(x + shx + ssx, y + sh + ss, z + shz + ssz, maxU, maxV);
      tessellator.addVertexWithUV(x + shx + ssx, y + sh, z + shz + ssz, maxU, minV);
      tessellator.draw();
    } 
  }
}
