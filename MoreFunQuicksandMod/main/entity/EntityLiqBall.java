package MoreFunQuicksandMod.main.entity;

import MoreFunQuicksandMod.main.Fields;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.lang.reflect.Field;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;

public class EntityLiqBall extends EntityThrowable {
  public Block block;
  
  private boolean LiqStable = false;
  
  public EntityLiqBall(World world) {
    super(world);
    this.block = (Block)Blocks.flowing_water;
    setWatcherLiq();
  }
  
  public EntityLiqBall(World world, EntityLivingBase entity) {
    super(world, entity);
    this.block = (Block)Blocks.flowing_water;
    setWatcherLiq();
  }
  
  public EntityLiqBall(World world, double x, double y, double z) {
    super(world, x, y, z);
    this.block = (Block)Blocks.flowing_water;
    setWatcherLiq();
  }
  
  public EntityLiqBall(World world, EntityLivingBase entity, Block ablock) {
    super(world, entity);
    this.block = ablock;
    setWatcherLiq();
  }
  
  @SideOnly(Side.CLIENT)
  public float getShadowSize() {
    return 0.0F;
  }
  
  protected void entityInit() {
    initWatcherLiq();
  }
  
  protected void initWatcherLiq() {
    this.dataWatcher.addObject(25, Integer.valueOf(8));
  }
  
  public void setWatcherLiq() {
    if (!this.worldObj.isRemote)
      this.dataWatcher.updateObject(25, Integer.valueOf(Block.getIdFromBlock(this.block))); 
  }
  
  public void getWatcherLiq() {
    if (this.worldObj.isRemote) {
      BlockLiquid blockLiquid;
      Block aBlock = Block.getBlockById(this.dataWatcher.getWatchableObjectInt(25));
      if (aBlock == null) {
        blockLiquid = Blocks.flowing_water;
      } else if (!blockLiquid.getMaterial().isLiquid()) {
        blockLiquid = Blocks.flowing_water;
      } 
      this.block = (Block)blockLiquid;
    } 
    this.LiqStable = true;
  }
  
  public void writeSpawnData(ByteBuf data) {
    data.writeInt((this.block != null) ? Block.getIdFromBlock(this.block) : 8);
  }
  
  public void readSpawnData(ByteBuf data) {
    BlockLiquid blockLiquid;
    Block aBlock = Block.getBlockById(data.readInt());
    if (aBlock == null) {
      blockLiquid = Blocks.flowing_water;
    } else if (!blockLiquid.getMaterial().isLiquid()) {
      blockLiquid = Blocks.flowing_water;
    } 
    this.block = (Block)blockLiquid;
  }
  
  public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {}
  
  public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {}
  
  protected float getGravityVelocity() {
    return 0.1F;
  }
  
  public boolean isInvisible() {
    return true;
  }
  
  public boolean isInRangeToRenderDist(double p_70112_1_) {
    return false;
  }
  
  protected boolean validForSpread(World world, int x, int y, int z) {
    if (!world.getBlock(x, y, z).getMaterial().isSolid() && !world.getBlock(x, y, z).getMaterial().isLiquid() && (
      !world.getBlock(x, y, z).getMaterial().blocksMovement() || world.getBlock(x, y, z).getMaterial().isReplaceable()))
      return true; 
    return false;
  }
  
  protected void onImpact(MovingObjectPosition movObj) {
    int blX = movObj.blockX;
    int blY = movObj.blockY;
    int blZ = movObj.blockZ;
    int bsX = (int)Math.floor(this.posX);
    int bsY = (int)Math.floor(this.posY);
    int bsZ = (int)Math.floor(this.posZ);
    if (movObj.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
      bsX = (int)Math.floor(movObj.hitVec.xCoord);
      bsY = (int)Math.floor(movObj.hitVec.yCoord);
      bsZ = (int)Math.floor(movObj.hitVec.zCoord);
    } else if (movObj.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
      switch (movObj.sideHit) {
        case 0:
          bsX = blX;
          bsY = blY - 1;
          bsZ = blZ;
          break;
        case 1:
          bsX = blX;
          bsY = blY + 1;
          bsZ = blZ;
          break;
        case 2:
          bsX = blX;
          bsY = blY;
          bsZ = blZ - 1;
          break;
        case 3:
          bsX = blX;
          bsY = blY;
          bsZ = blZ + 1;
          break;
        case 4:
          bsX = blX - 1;
          bsY = blY;
          bsZ = blZ;
          break;
        case 5:
          bsX = blX + 1;
          bsY = blY;
          bsZ = blZ;
          break;
        default:
          bsX = (int)Math.floor(movObj.hitVec.xCoord);
          bsY = (int)Math.floor(movObj.hitVec.yCoord);
          bsZ = (int)Math.floor(movObj.hitVec.zCoord);
          break;
      } 
    } 
    for (int var3 = 0; var3 < 41; var3++) {
      double xx = bsX + 0.5D + (this.worldObj.rand.nextFloat() * 2.0F) - 1.0D;
      double zz = bsZ + 0.5D + (this.worldObj.rand.nextFloat() * 2.0F) - 1.0D;
      double yy = bsY + 0.5D + (this.worldObj.rand.nextFloat() * 2.0F) - 1.0D;
      double vx = (this.worldObj.rand.nextFloat() * 8.0F - 4.0F);
      double vz = (this.worldObj.rand.nextFloat() * 8.0F - 4.0F);
      double vy = (this.worldObj.rand.nextFloat() * 4.0F);
      this.worldObj.spawnParticle("blockcrack_" + Block.getIdFromBlock(this.block) + "_" + Character.MIN_VALUE, xx, yy, zz, vx, vy, vz);
    } 
    if (!this.worldObj.isRemote) {
      int quanta = 8;
      if (this.block instanceof BlockFluidBase)
        try {
          Field field2 = Fields.findField(BlockFluidBase.class, int.class, 0);
          field2.setAccessible(true);
          quanta = field2.getInt(this.block);
        } catch (Exception e) {
          e.printStackTrace();
        }  
      if (validForSpread(this.worldObj, bsX + 1, bsY, bsZ))
        this.worldObj.setBlock(bsX + 1, bsY, bsZ, this.block, quanta - 1, 3); 
      if (validForSpread(this.worldObj, bsX - 1, bsY, bsZ))
        this.worldObj.setBlock(bsX - 1, bsY, bsZ, this.block, quanta - 1, 3); 
      if (validForSpread(this.worldObj, bsX, bsY, bsZ + 1))
        this.worldObj.setBlock(bsX, bsY, bsZ + 1, this.block, quanta - 1, 3); 
      if (validForSpread(this.worldObj, bsX, bsY, bsZ - 1))
        this.worldObj.setBlock(bsX, bsY, bsZ - 1, this.block, quanta - 1, 3); 
      if (validForSpread(this.worldObj, bsX, bsY, bsZ))
        this.worldObj.setBlock(bsX, bsY, bsZ, this.block, quanta - 1, 3); 
      setDead();
    } 
  }
  
  public void onUpdate() {
    if (!this.LiqStable)
      getWatcherLiq(); 
    for (int i = 0; i < 4; i++) {
      double xx = this.worldObj.rand.nextFloat() * 0.2D - 0.1D;
      double zz = this.worldObj.rand.nextFloat() * 0.2D - 0.1D;
      double yy = this.worldObj.rand.nextFloat() * 0.2D - 0.1D;
      this.worldObj.spawnParticle("blockcrack_" + Block.getIdFromBlock(this.block) + "_" + Character.MIN_VALUE, this.posX + xx, this.posY + yy, this.posZ + zz, 0.0D, 0.0D, 0.0D);
    } 
    super.onUpdate();
  }
}
