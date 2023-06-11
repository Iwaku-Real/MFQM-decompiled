package MoreFunQuicksandMod.main.Tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityBlossom extends TileEntity {
  public double phase = 0.0D;
  
  public double ampl = 0.25D;
  
  public void writeToNBT(NBTTagCompound par1) {
    super.writeToNBT(par1);
  }
  
  public void readFromNBT(NBTTagCompound par1) {
    super.readFromNBT(par1);
  }
  
  public void updateEntity() {
    AxisAlignedBB var4 = null;
    int meta = getWorld().getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
    int side = meta - 6;
    if (side == 0)
      var4 = AxisAlignedBB.getBoundingBox(this.xCoord, this.yCoord, (this.zCoord - 1), (this.xCoord + 1), (this.yCoord + 1), (this.zCoord + 1 - 1)); 
    if (side == 1)
      var4 = AxisAlignedBB.getBoundingBox((this.xCoord + 1), this.yCoord, this.zCoord, (this.xCoord + 1 + 1), (this.yCoord + 1), (this.zCoord + 1)); 
    if (side == 2)
      var4 = AxisAlignedBB.getBoundingBox(this.xCoord, this.yCoord, (this.zCoord + 1), (this.xCoord + 1), (this.yCoord + 1), (this.zCoord + 1 + 1)); 
    if (side == 3)
      var4 = AxisAlignedBB.getBoundingBox((this.xCoord - 1), this.yCoord, this.zCoord, (this.xCoord + 1 - 1), (this.yCoord + 1), (this.zCoord + 1)); 
    if (var4 != null && 
      getWorld() != null) {
      List var5 = getWorld().getEntitiesWithinAABB(EntityLivingBase.class, var4);
      Iterator var6 = var5.iterator();
      if (var6.hasNext())
        this.ampl += 0.002D; 
      this.ampl -= 0.001D;
    } 
    this.ampl = Math.min(Math.max(this.ampl, 0.25D), 0.3D);
  }
  
  @SideOnly(Side.CLIENT)
  public AxisAlignedBB getRenderBoundingBox() {
    AxisAlignedBB bb = INFINITE_EXTENT_AABB;
    bb = AxisAlignedBB.getBoundingBox(this.xCoord, this.yCoord, this.zCoord, (this.xCoord + 1), (this.yCoord + 1), (this.zCoord + 1));
    return bb;
  }
}
