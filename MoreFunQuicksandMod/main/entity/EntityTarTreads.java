package MoreFunQuicksandMod.main.entity;

import MoreFunQuicksandMod.main.MFQM;
import MoreFunQuicksandMod.main.PotionStuck;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityTarTreads extends Entity {
  public int tickWorld;
  
  public double hgrab;
  
  public double rsize;
  
  public int limbs = 0;
  
  public EntityLivingBase target;
  
  public double xs;
  
  public double zs;
  
  public int live;
  
  public EntityTarTreads(World world) {
    super(world);
    setSize(0.25F, 0.25F);
    this.tickWorld = 0;
    this.ignoreFrustumCheck = true;
    if (!world.isRemote)
      setDead(); 
    this.rsize = 0.25D + world.rand.nextFloat() * 0.25D;
    this.xs = world.rand.nextFloat() * 0.8D - 0.4D;
    this.zs = world.rand.nextFloat() * 0.8D - 0.4D;
    this.live = 1000;
  }
  
  public EntityTarTreads(World world, double x, double y, double z, double ahgrab, int tick, EntityLivingBase entity) {
    this(world);
    setPosition(x, y, z);
    this.hgrab = ahgrab;
    this.tickWorld = tick;
    this.target = entity;
    PotionStuck props = PotionStuck.get(entity);
    if (props == null) {
      setDead();
      return;
    } 
    props.setTarTreads(props.getTarTreads() + 1);
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
    if (this.target == null) {
      setDead();
      return;
    } 
    if (!(this.target instanceof EntityLivingBase)) {
      setDead();
      return;
    } 
    if (this.target.isDead || !this.target.isEntityAlive()) {
      PotionStuck props = PotionStuck.get(this.target);
      if (props != null)
        props.setTarTreads(Math.max(props.getTarTreads() - 1, 0)); 
      setDead();
      return;
    } 
    this.motionX = 0.0D;
    this.motionY = 0.0D;
    this.motionZ = 0.0D;
    super.onUpdate();
    this.motionX = 0.0D;
    this.motionY = 0.0D;
    this.motionZ = 0.0D;
    double dis = Math.sqrt(Math.pow(this.target.posX - this.posX, 2.0D) + Math.pow(this.target.boundingBox.minY + this.target.height * this.hgrab - this.posY, 2.0D) + Math.pow(this.target.posZ - this.posZ, 2.0D));
    if (dis > 4.0D * this.rsize || this.target.boundingBox.minY + this.target.height * this.hgrab < this.posY - 0.25D)
      this.live--; 
    if (dis > 6.0D * this.rsize || this.live < 1) {
      PotionStuck props = PotionStuck.get(this.target);
      if (props != null)
        props.setTarTreads(Math.max(props.getTarTreads() - 1, 0)); 
      setDead();
      return;
    } 
    if (this.worldObj.getBlock((int)Math.floor(this.posX), (int)Math.floor(this.posY - 0.5D), (int)Math.floor(this.posZ)) != MFQM.TarBlock && 
      this.worldObj.getBlock((int)Math.floor(this.posX), (int)Math.floor(this.posY + 0.5D), (int)Math.floor(this.posZ)) != MFQM.TarBlock) {
      PotionStuck props = PotionStuck.get(this.target);
      if (props != null)
        props.setTarTreads(Math.max(props.getTarTreads() - 1, 0)); 
      setDead();
      return;
    } 
  }
  
  public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {}
  
  public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {}
  
  @SideOnly(Side.CLIENT)
  public float getShadowSize() {
    return 0.0F;
  }
}
