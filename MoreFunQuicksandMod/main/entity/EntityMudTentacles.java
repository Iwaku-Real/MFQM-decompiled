package MoreFunQuicksandMod.main.entity;

import MoreFunQuicksandMod.main.MFQM;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class EntityMudTentacles extends EntityTentacles {
  public EntityMudTentacles(World world) {
    super(world);
  }
  
  public EntityMudTentacles(World world, double x, double y, double z, Entity liv, int Hgh, int tiw) {
    super(world, x, y, z, liv, Hgh, tiw);
  }
  
  public void CheckInQSBlock() {
    Block bl1 = this.worldObj.getBlock((int)Math.floor(this.posX), (int)Math.floor(this.posY - 0.5D), (int)Math.floor(this.posZ));
    Block bl2 = this.worldObj.getBlock((int)Math.floor(this.posX), (int)Math.floor(this.posY + 0.5D), (int)Math.floor(this.posZ));
    if (bl1 != MFQM.MudBlock && bl1 != MFQM.JungleQuicksandBlock && bl1 != MFQM.SoftQuicksandBlock && 
      bl2 != MFQM.MudBlock && bl2 != MFQM.JungleQuicksandBlock && bl2 != MFQM.SoftQuicksandBlock) {
      setDead();
      return;
    } 
  }
  
  public void ManipulateWithTerrain() {
    int xx = (int)Math.floor(this.posX);
    int zz = (int)Math.floor(this.posZ);
    int yy1 = (int)Math.floor(this.posY - 0.5D);
    int yy2 = (int)Math.floor(this.posY + 0.5D);
    if (this.worldObj.getTotalWorldTime() % 32L == 0L)
      for (int h = -1; h < 2; h++) {
        for (int v = -1; v < 2; v++) {
          if (this.worldObj.getBlock(xx + h, yy1, zz + v) == MFQM.MudBlock || this.worldObj
            .getBlock(xx + h, yy2, zz + v) == MFQM.MudBlock) {
            int i = 0;
            while (i < 4) {
              yy1 = yy2 - i + 2;
              if (this.worldObj.getBlock(xx + h, yy1, zz + v) == MFQM.MudBlock && 
                this.worldObj.getBlockMetadata(xx + h, yy1, zz + v) < 3)
                this.worldObj.setBlock(xx + h, yy1, zz + v, MFQM.MudBlock, 3, 3); 
              i++;
            } 
          } 
        } 
      }  
  }
}
