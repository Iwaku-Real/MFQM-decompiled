package MoreFunQuicksandMod.main.Tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityLarvae extends TileEntity {
  public double phase = 0.0D;
  
  public void writeToNBT(NBTTagCompound par1) {
    super.writeToNBT(par1);
  }
  
  public void readFromNBT(NBTTagCompound par1) {
    super.readFromNBT(par1);
  }
  
  public boolean canUpdate() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public AxisAlignedBB getRenderBoundingBox() {
    AxisAlignedBB bb = INFINITE_EXTENT_AABB;
    bb = AxisAlignedBB.getBoundingBox(this.xCoord, this.yCoord, this.zCoord, (this.xCoord + 1), (this.yCoord + 1), (this.zCoord + 1));
    return bb;
  }
}
