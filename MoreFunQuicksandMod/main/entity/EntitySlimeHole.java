package MoreFunQuicksandMod.main.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntitySlimeHole extends Entity {
  public int ticksInWorld;
  
  public int tickWorld;
  
  public int size;
  
  public float rotate;
  
  public EntitySlimeHole(World world) {
    super(world);
    setSize(0.25F, 0.25F);
    this.tickWorld = 0;
    this.ignoreFrustumCheck = true;
    if (!world.isRemote)
      setDead(); 
  }
  
  public EntitySlimeHole(World world, double x, double y, double z, Entity entity) {
    this(world);
    setPosition(x, y, z);
    this.ignoreFrustumCheck = true;
  }
  
  public EntitySlimeHole(World world, double x, double y, double z, int sizer, int tick, Entity entity) {
    this(world, x, y, z, entity);
    this.size = sizer;
    this.tickWorld = tick;
  }
  
  public EntitySlimeHole(World par1World, EntityLivingBase liv) {
    super(par1World);
    this.ignoreFrustumCheck = true;
    setSize(0.25F, 0.25F);
    setPosition(this.posX, this.posY, this.posZ);
  }
  
  protected void entityInit() {}
  
  @SideOnly(Side.CLIENT)
  public boolean isInRangeToRenderDist(double par1) {
    double d1 = this.boundingBox.getAverageEdgeLength() * 4.0D;
    d1 *= 64.0D;
    return (par1 < d1 * d1);
  }
  
  public void onUpdate() {
    if (!this.worldObj.isRemote) {
      setDead();
      return;
    } 
    super.onUpdate();
    this.ticksInWorld++;
    if (this.tickWorld == 1) {
      if (this.ticksInWorld > 10 && 
        this.worldObj.getTotalWorldTime() % 100L == 0L)
        setDead(); 
    } else if (this.ticksInWorld > 100) {
      setDead();
    } 
  }
  
  public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {}
  
  public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {}
  
  @SideOnly(Side.CLIENT)
  public float getShadowSize() {
    return 0.0F;
  }
}
