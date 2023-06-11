package MoreFunQuicksandMod.main.Tileentity;

import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityLure extends TileEntity {
  public void writeToNBT(NBTTagCompound par1) {
    super.writeToNBT(par1);
  }
  
  public void readFromNBT(NBTTagCompound par1) {
    super.readFromNBT(par1);
  }
  
  public void updateEntity() {
    if (this.worldObj.getTotalWorldTime() % 48L == 0L && 
      !this.worldObj.isRemote && 
      this.worldObj.rand.nextInt(5) == 0) {
      int x = this.xCoord;
      int y = this.yCoord;
      int z = this.zCoord;
      List var4 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox((x - 64), (y - 4), (z - 64), (x + 64), (y + 32), (z + 64)));
      Iterator<EntityLivingBase> var6 = var4.iterator();
      while (var6.hasNext()) {
        EntityLivingBase e = var6.next();
        if (e instanceof EntityCreature && 
          !(e instanceof net.minecraft.entity.player.EntityPlayer)) {
          double dis = Math.pow(Math.pow(e.posX - x, 2.0D) + Math.pow(e.posZ - z, 2.0D), 0.5D);
          if (dis > 4.0D && 
            this.worldObj.rand.nextInt((int)Math.floor(dis / 5.0D) + 20) == 0)
            ((EntityCreature)e).getNavigator().tryMoveToXYZ(x + 0.5D, y + 0.5D, z + 0.5D, 1.0D); 
        } 
      } 
    } 
  }
}
