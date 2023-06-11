package MoreFunQuicksandMod.main.Tileentity;

import MoreFunQuicksandMod.main.MFQM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class TileEntityMeatRenderer extends TileEntitySpecialRenderer {
  private final ResourceLocation field_110637_a = new ResourceLocation("morefunquicksandmod", "textures/blocks/Meat10.png");
  
  public void renderMeat(TileEntityMeat par1TileEntityMeat, double par2, double par4, double par6, float par8) {
    double x = 0.0D;
    double y = 0.0D;
    double z = 0.0D;
    int meta = par1TileEntityMeat.getWorld().getBlockMetadata(par1TileEntityMeat.xCoord, par1TileEntityMeat.yCoord, par1TileEntityMeat.zCoord);
    double upper = 1.0D;
    if (meta > 5 && meta < 10) {
      if (par1TileEntityMeat.getWorld().getBlock(par1TileEntityMeat.xCoord, par1TileEntityMeat.yCoord + 1, par1TileEntityMeat.zCoord) == MFQM.MeatBlock && 
        par1TileEntityMeat.getWorld().getBlockMetadata(par1TileEntityMeat.xCoord, par1TileEntityMeat.yCoord + 1, par1TileEntityMeat.zCoord) == meta) {
        upper++;
        if (par1TileEntityMeat.getWorld().getBlock(par1TileEntityMeat.xCoord, par1TileEntityMeat.yCoord + 2, par1TileEntityMeat.zCoord) == MFQM.MeatBlock && 
          par1TileEntityMeat.getWorld().getBlockMetadata(par1TileEntityMeat.xCoord, par1TileEntityMeat.yCoord + 2, par1TileEntityMeat.zCoord) == meta) {
          upper++;
          if (par1TileEntityMeat.getWorld().getBlock(par1TileEntityMeat.xCoord, par1TileEntityMeat.yCoord + 3, par1TileEntityMeat.zCoord) == MFQM.MeatBlock && 
            par1TileEntityMeat.getWorld().getBlockMetadata(par1TileEntityMeat.xCoord, par1TileEntityMeat.yCoord + 3, par1TileEntityMeat.zCoord) == meta)
            upper++; 
        } 
      } 
      int side = meta - 6;
      Tessellator tessellator = Tessellator.instance;
      int lightValue = 0;
      if (side == 0)
        lightValue = MFQM.MeatBlock.getMixedBrightnessForBlock((IBlockAccess)par1TileEntityMeat.getWorld(), par1TileEntityMeat.xCoord, par1TileEntityMeat.yCoord, par1TileEntityMeat.zCoord - 1); 
      if (side == 1)
        lightValue = MFQM.MeatBlock.getMixedBrightnessForBlock((IBlockAccess)par1TileEntityMeat.getWorld(), par1TileEntityMeat.xCoord, par1TileEntityMeat.yCoord, par1TileEntityMeat.zCoord + 1); 
      if (side == 2)
        lightValue = MFQM.MeatBlock.getMixedBrightnessForBlock((IBlockAccess)par1TileEntityMeat.getWorld(), par1TileEntityMeat.xCoord - 1, par1TileEntityMeat.yCoord, par1TileEntityMeat.zCoord); 
      if (side == 3)
        lightValue = MFQM.MeatBlock.getMixedBrightnessForBlock((IBlockAccess)par1TileEntityMeat.getWorld(), par1TileEntityMeat.xCoord + 1, par1TileEntityMeat.yCoord, par1TileEntityMeat.zCoord); 
      tessellator.setBrightness(lightValue);
      tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);
      bindTexture(this.field_110637_a);
      GL11.glPushMatrix();
      GL11.glEnable(32826);
      GL11.glTranslatef((float)par2, (float)par4, (float)par6);
      double ph = par1TileEntityMeat.phase;
      double ampl = 0.025D + upper / 30.0D;
      double sz = 0.5D + upper / 15.0D;
      double ampld = 0.025D + upper / 30.0D;
      double szd = 0.5D + upper / 15.0D;
      double timd1 = Sys.getTime() / (200.0D + upper * 100.0D) + ph;
      float timf1 = (float)(timd1 - Math.floor(timd1 / 6.28318D) * 6.28318D);
      double timd2 = Sys.getTime() / (200.0D + upper * 100.0D) + ph + 90.0D;
      float timf2 = (float)(timd2 - Math.floor(timd2 / 6.28318D) * 6.28318D);
      double sina = MathHelper.sin(timf1);
      double sinb = MathHelper.sin(timf2);
      if (side == 0) {
        x -= 0.25D;
        y--;
        z -= 0.55D;
        renderFleshCube(tessellator, x - 0.0625D, y + 0.9375D, z, side, sz + sina * ampl);
        renderFleshCube(tessellator, x + 0.125D, y + 0.375D, z + 0.05D, side, szd + sinb * ampld);
        renderFleshCube(tessellator, x + 0.625D, y + 0.6875D, z - 0.1D, side, sz - sina * ampl);
      } 
      if (side == 1) {
        x -= 0.25D;
        y--;
        z += 0.55D;
        renderFleshCube(tessellator, x + 0.5625D, y + 0.9375D, z, side, sz + sina * ampl);
        renderFleshCube(tessellator, x + 0.375D, y + 0.375D, z - 0.05D, side, szd + sinb * ampld);
        renderFleshCube(tessellator, x - 0.125D, y + 0.6875D, z + 0.1D, side, sz - sina * ampl);
      } 
      if (side == 2) {
        z -= 0.25D;
        y--;
        x -= 0.55D;
        renderFleshCube(tessellator, x, y + 0.9375D, z + 0.5625D, side, sz + sina * ampl);
        renderFleshCube(tessellator, x + 0.05D, y + 0.375D, z + 0.375D, side, szd + sinb * ampld);
        renderFleshCube(tessellator, x - 0.1D, y + 0.6875D, z - 0.125D, side, sz - sina * ampl);
      } 
      if (side == 3) {
        z -= 0.25D;
        y--;
        x += 0.55D;
        renderFleshCube(tessellator, x, y + 0.9375D, z - 0.0625D, side, sz + sina * ampl);
        renderFleshCube(tessellator, x - 0.05D, y + 0.375D, z + 0.125D, side, szd + sinb * ampld);
        renderFleshCube(tessellator, x + 0.1D, y + 0.6875D, z + 0.625D, side, sz - sina * ampl);
      } 
      GL11.glDisable(32826);
      GL11.glPopMatrix();
    } 
  }
  
  public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
    renderMeat((TileEntityMeat)par1TileEntity, par2, par4, par6, par8);
  }
  
  public void renderFleshCube(Tessellator tessellator, double x, double y, double z, int side, double scale) {
    double minU = 0.0D;
    double minV = 0.0D;
    double maxU = 1.0D;
    double maxV = 1.0D;
    double ss = 1.0D * scale;
    double sh = (1.0D - ss) / 2.0D;
    double ssy = 1.25D * scale;
    double shy = (1.25D - ssy) / 2.0D;
    tessellator.startDrawingQuads();
    tessellator.setNormal(0.0F, 1.0F, 0.0F);
    tessellator.addVertexWithUV(x + sh, y + shy + ssy, z + sh, maxU, minV);
    tessellator.addVertexWithUV(x + sh, y + shy + ssy, z + sh + ss, maxU, maxV);
    tessellator.addVertexWithUV(x + sh + ss, y + shy + ssy, z + sh + ss, minU, maxV);
    tessellator.addVertexWithUV(x + sh + ss, y + shy + ssy, z + sh, minU, minV);
    tessellator.draw();
    tessellator.startDrawingQuads();
    tessellator.setNormal(0.0F, -1.0F, 0.0F);
    tessellator.addVertexWithUV(x + sh + ss, y + shy, z + sh, maxU, minV);
    tessellator.addVertexWithUV(x + sh + ss, y + shy, z + sh + ss, maxU, maxV);
    tessellator.addVertexWithUV(x + sh, y + shy, z + sh + ss, minU, maxV);
    tessellator.addVertexWithUV(x + sh, y + shy, z + sh, minU, minV);
    tessellator.draw();
    if (side != 1) {
      tessellator.startDrawingQuads();
      tessellator.setNormal(0.0F, 0.0F, -1.0F);
      tessellator.addVertexWithUV(x + sh, y + shy, z + sh, maxU, minV);
      tessellator.addVertexWithUV(x + sh, y + shy + ssy, z + sh, maxU, maxV);
      tessellator.addVertexWithUV(x + sh + ss, y + shy + ssy, z + sh, minU, maxV);
      tessellator.addVertexWithUV(x + sh + ss, y + shy, z + sh, minU, minV);
      tessellator.draw();
    } 
    if (side != 0) {
      tessellator.startDrawingQuads();
      tessellator.setNormal(0.0F, 0.0F, 1.0F);
      tessellator.addVertexWithUV(x + sh + ss, y + shy, z + sh + ss, maxU, minV);
      tessellator.addVertexWithUV(x + sh + ss, y + shy + ssy, z + sh + ss, maxU, maxV);
      tessellator.addVertexWithUV(x + sh, y + shy + ssy, z + sh + ss, minU, maxV);
      tessellator.addVertexWithUV(x + sh, y + shy, z + sh + ss, minU, minV);
      tessellator.draw();
    } 
    if (side != 3) {
      tessellator.startDrawingQuads();
      tessellator.setNormal(-1.0F, 0.0F, 0.0F);
      tessellator.addVertexWithUV(x + sh, y + shy, z + sh + ss, maxU, minV);
      tessellator.addVertexWithUV(x + sh, y + shy + ssy, z + sh + ss, maxU, maxV);
      tessellator.addVertexWithUV(x + sh, y + shy + ssy, z + sh, minU, maxV);
      tessellator.addVertexWithUV(x + sh, y + shy, z + sh, minU, minV);
      tessellator.draw();
    } 
    if (side != 2) {
      tessellator.startDrawingQuads();
      tessellator.setNormal(1.0F, 0.0F, 0.0F);
      tessellator.addVertexWithUV(x + sh + ss, y + shy, z + sh, minU, minV);
      tessellator.addVertexWithUV(x + sh + ss, y + shy + ssy, z + sh, minU, maxV);
      tessellator.addVertexWithUV(x + sh + ss, y + shy + ssy, z + sh + ss, maxU, maxV);
      tessellator.addVertexWithUV(x + sh + ss, y + shy, z + sh + ss, maxU, minV);
      tessellator.draw();
    } 
  }
}
