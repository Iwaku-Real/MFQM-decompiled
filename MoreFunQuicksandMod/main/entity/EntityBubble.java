package MoreFunQuicksandMod.main.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import org.lwjgl.Sys;

public class EntityBubble extends Entity {
  public long ticksWorld;
  
  public int liveTime;
  
  public float size;
  
  public Block block;
  
  public int meta;
  
  public float rotate;
  
  public EntityBubble(World world) {
    super(world);
    setSize(1.0F, 1.0F);
    this.ticksWorld = Sys.getTime();
    if (!world.isRemote)
      setDead(); 
  }
  
  public EntityBubble(World world, double x, double y, double z, Block bl, int mt, float sz, int tim) {
    this(world);
    this.block = bl;
    this.meta = mt;
    this.size = sz;
    this.rotate = world.rand.nextFloat() * 360.0F;
    this.liveTime = tim;
    double yy = y;
    int xx = (int)Math.floor(x);
    int zz = (int)Math.floor(z);
    for (int i = 0; i <= 5; i++) {
      if (!world.getBlock(xx, (int)Math.floor(y + 0.5D + i), zz).getMaterial().isOpaque() && !world.getBlock(xx, (int)Math.floor(y + 0.5D + i), zz).getMaterial().isLiquid()) {
        yy = y + i;
        this.ticksWorld += (i * 400);
        break;
      } 
      if (world.getBlock(xx, (int)Math.floor(y + 0.5D + i), zz) != bl) {
        setDead();
        break;
      } 
    } 
    int yyy = (int)Math.floor(yy - 0.5D);
    if (world.getBlock(xx, yyy, zz).getMaterial().isLiquid() && 
      world.getBlockMetadata(xx, yyy, zz) != 0)
      setDead(); 
    setPosition(x, yy, z);
  }
  
  public EntityBubble(World world, double x, double y, double z, Block bl, int mt, float sz, int tim, int dly) {
    this(world, x, y, z, bl, mt, sz, tim);
    this.ticksWorld += dly;
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
    if (this.worldObj.getTotalWorldTime() % 32L == 0L && 
      this.worldObj.getBlock((int)Math.floor(this.posX), (int)Math.floor(this.posY - 0.5D), (int)Math.floor(this.posZ)) != this.block) {
      setDead();
      return;
    } 
    if (Sys.getTime() - this.ticksWorld > this.liveTime) {
      for (int i = 0; i < 3.0F * this.size; i++) {
        double xx = this.worldObj.rand.nextFloat() * 0.2D * this.size - 0.1D * this.size;
        double zz = this.worldObj.rand.nextFloat() * 0.2D * this.size - 0.1D * this.size;
        this.worldObj.spawnParticle("blockcrack_" + Block.getIdFromBlock(this.block) + "_" + this.meta, this.posX + xx, this.posY, this.posZ + zz, 0.0D, 0.0D, 0.0D);
      } 
      this.worldObj.playSound(this.posX, this.posY, this.posZ, "liquid.lavapop", 0.5F + this.worldObj.rand.nextFloat() * 0.25F, (0.5F + this.worldObj.rand.nextFloat() * 0.5F) / this.size, false);
      this.worldObj.playSound(this.posX, this.posY, this.posZ, "mob.slime.attack", 0.15F, (1.25F + this.worldObj.rand.nextFloat() * 0.5F) / this.size, false);
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
