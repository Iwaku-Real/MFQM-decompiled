package MoreFunQuicksandMod.main.Tileentity;

import MoreFunQuicksandMod.main.MFQM;
import MoreFunQuicksandMod.main.blocks.BlockLarvae;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class TileEntityLarvaeRenderer extends TileEntitySpecialRenderer {
  public void renderLarvae(TileEntityLarvae par1TileEntityLarvae, double par2, double par4, double par6, float par8) {
    double x = 0.0D;
    double y = 0.0D;
    double z = 0.0D;
    boolean x_inc = true;
    boolean x_dec = true;
    boolean y_inc = true;
    boolean y_dec = true;
    boolean z_inc = true;
    boolean z_dec = true;
    x_inc = ((BlockLarvae)MFQM.LarvaeBlock).shouldSideBeRendered2((IBlockAccess)par1TileEntityLarvae.getWorld(), par1TileEntityLarvae.xCoord + 1, par1TileEntityLarvae.yCoord, par1TileEntityLarvae.zCoord, 0);
    x_dec = ((BlockLarvae)MFQM.LarvaeBlock).shouldSideBeRendered2((IBlockAccess)par1TileEntityLarvae.getWorld(), par1TileEntityLarvae.xCoord - 1, par1TileEntityLarvae.yCoord, par1TileEntityLarvae.zCoord, 0);
    y_inc = ((BlockLarvae)MFQM.LarvaeBlock).shouldSideBeRendered2((IBlockAccess)par1TileEntityLarvae.getWorld(), par1TileEntityLarvae.xCoord, par1TileEntityLarvae.yCoord, par1TileEntityLarvae.zCoord, 1);
    y_dec = ((BlockLarvae)MFQM.LarvaeBlock).shouldSideBeRendered2((IBlockAccess)par1TileEntityLarvae.getWorld(), par1TileEntityLarvae.xCoord, par1TileEntityLarvae.yCoord, par1TileEntityLarvae.zCoord, 2);
    z_inc = ((BlockLarvae)MFQM.LarvaeBlock).shouldSideBeRendered2((IBlockAccess)par1TileEntityLarvae.getWorld(), par1TileEntityLarvae.xCoord, par1TileEntityLarvae.yCoord, par1TileEntityLarvae.zCoord + 1, 0);
    z_dec = ((BlockLarvae)MFQM.LarvaeBlock).shouldSideBeRendered2((IBlockAccess)par1TileEntityLarvae.getWorld(), par1TileEntityLarvae.xCoord, par1TileEntityLarvae.yCoord, par1TileEntityLarvae.zCoord - 1, 0);
    if (!x_inc && !x_dec && !y_inc && !y_dec && !z_inc && !z_dec)
      return; 
    Tessellator tessellator = Tessellator.instance;
    int lightValue = MFQM.LarvaeBlock.getMixedBrightnessForBlock((IBlockAccess)par1TileEntityLarvae.getWorld(), par1TileEntityLarvae.xCoord, par1TileEntityLarvae.yCoord, par1TileEntityLarvae.zCoord);
    tessellator.setBrightness(lightValue);
    tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);
    IIcon Icon = MFQM.LarvaeBlock.getIcon(0, 0);
    bindTexture(TextureMap.locationBlocksTexture);
    GL11.glPushMatrix();
    GL11.glEnable(32826);
    GL11.glTranslatef((float)par2, (float)par4, (float)par6);
    double minU = Icon.getMinU();
    double minV = Icon.getMinV();
    double maxU = Icon.getMaxU();
    double maxV = Icon.getMaxV();
    double height = 0.75D;
    if (!y_inc) {
      height = 1.0D;
    } else {
      double timd = Sys.getTime() / 175.0D + par1TileEntityLarvae.phase;
      float timf = (float)(timd - Math.floor(timd / 6.28318D) * 6.28318D);
      height = 0.8D + MathHelper.sin(timf) * 0.025D;
    } 
    if (height != 1.0D) {
      tessellator.startDrawingQuads();
      tessellator.setNormal(0.0F, 1.0F, 0.0F);
      tessellator.addVertexWithUV(x, y + height, z, maxU, minV);
      tessellator.addVertexWithUV(x, y + height, z + 1.0D, maxU, maxV);
      tessellator.addVertexWithUV(x + 1.0D, y + height, z + 1.0D, minU, maxV);
      tessellator.addVertexWithUV(x + 1.0D, y + height, z, minU, minV);
      tessellator.draw();
    } 
    if (y_dec) {
      tessellator.startDrawingQuads();
      tessellator.setNormal(0.0F, -1.0F, 0.0F);
      tessellator.addVertexWithUV(x + 1.0D, y, z, maxU, minV);
      tessellator.addVertexWithUV(x + 1.0D, y, z + 1.0D, maxU, maxV);
      tessellator.addVertexWithUV(x, y, z + 1.0D, minU, maxV);
      tessellator.addVertexWithUV(x, y, z, minU, minV);
      tessellator.draw();
    } 
    if (z_dec) {
      tessellator.startDrawingQuads();
      tessellator.setNormal(0.0F, 0.0F, -1.0F);
      tessellator.addVertexWithUV(x, y, z, maxU, minV);
      tessellator.addVertexWithUV(x, y + height, z, maxU, maxV);
      tessellator.addVertexWithUV(x + 1.0D, y + height, z, minU, maxV);
      tessellator.addVertexWithUV(x + 1.0D, y, z, minU, minV);
      tessellator.draw();
    } 
    if (z_inc) {
      tessellator.startDrawingQuads();
      tessellator.setNormal(0.0F, 0.0F, 1.0F);
      tessellator.addVertexWithUV(x + 1.0D, y, z + 1.0D, maxU, minV);
      tessellator.addVertexWithUV(x + 1.0D, y + height, z + 1.0D, maxU, maxV);
      tessellator.addVertexWithUV(x, y + height, z + 1.0D, minU, maxV);
      tessellator.addVertexWithUV(x, y, z + 1.0D, minU, minV);
      tessellator.draw();
    } 
    if (x_dec) {
      tessellator.startDrawingQuads();
      tessellator.setNormal(-1.0F, 0.0F, 0.0F);
      tessellator.addVertexWithUV(x, y, z + 1.0D, maxU, minV);
      tessellator.addVertexWithUV(x, y + height, z + 1.0D, maxU, maxV);
      tessellator.addVertexWithUV(x, y + height, z, minU, maxV);
      tessellator.addVertexWithUV(x, y, z, minU, minV);
      tessellator.draw();
    } 
    if (x_inc) {
      tessellator.startDrawingQuads();
      tessellator.setNormal(1.0F, 0.0F, 0.0F);
      tessellator.addVertexWithUV(x + 1.0D, y, z, minU, minV);
      tessellator.addVertexWithUV(x + 1.0D, y + height, z, minU, maxV);
      tessellator.addVertexWithUV(x + 1.0D, y + height, z + 1.0D, maxU, maxV);
      tessellator.addVertexWithUV(x + 1.0D, y, z + 1.0D, maxU, minV);
      tessellator.draw();
    } 
    GL11.glDisable(32826);
    GL11.glPopMatrix();
  }
  
  public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
    renderLarvae((TileEntityLarvae)par1TileEntity, par2, par4, par6, par8);
  }
}
