package MoreFunQuicksandMod.main.entity;

import MoreFunQuicksandMod.main.HookMessage;
import MoreFunQuicksandMod.main.MFQM;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

public class EntityHook extends Entity implements IProjectile, IEntityAdditionalSpawnData {
  public EntityPlayer curPlayer;
  
  public EntityLivingBase curGrabbed;
  
  public int grabTick;
  
  public int inSand;
  
  public double BlockPosX;
  
  public double BlockPosY;
  
  public double BlockPosZ;
  
  public boolean isRegistered;
  
  public boolean hitted;
  
  public int GrabType;
  
  private int xTile = -1;
  
  private int yTile = -1;
  
  private int zTile = -1;
  
  public boolean inGround;
  
  public int throwableShake;
  
  public double curLen;
  
  public double beginCurLen;
  
  private double maxLen;
  
  private double brkLen;
  
  private double mathLen;
  
  private EntityLivingBase thrower;
  
  private String throwerName;
  
  private int ticksInGround;
  
  private int ticksInAir;
  
  public EntityHook(World world) {
    super(world);
    setSize(0.25F, 0.25F);
    this.maxLen = 48.0D;
    this.ignoreFrustumCheck = true;
    setWatcherGrabbed();
  }
  
  public EntityHook(World world, EntityPlayer par8EntityPlayer) {
    super(world);
    this.thrower = (EntityLivingBase)par8EntityPlayer;
    setSize(0.25F, 0.25F);
    setLocationAndAngles(par8EntityPlayer.posX, par8EntityPlayer.posY + par8EntityPlayer.getEyeHeight(), par8EntityPlayer.posZ, par8EntityPlayer.rotationYaw, par8EntityPlayer.rotationPitch);
    this.posX -= (MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * 0.16F);
    this.posY -= 0.10000000149011612D;
    this.posZ -= (MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * 0.16F);
    setPosition(this.posX, this.posY, this.posZ);
    this.yOffset = 0.0F;
    float f = 0.4F;
    this.motionX = (-MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F) * f);
    this.motionZ = (MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F) * f);
    this.motionY = (-MathHelper.sin((this.rotationPitch + func_70183_g()) / 180.0F * 3.1415927F) * f);
    setThrowableHeading(this.motionX, this.motionY, this.motionZ, func_70182_d(), 1.0F);
    this.curPlayer = par8EntityPlayer;
    this.maxLen = 48.0D;
    this.ignoreFrustumCheck = true;
    setWatcherGrabbed();
  }
  
  public EntityHook(World world, double par2, double par4, double par6) {
    super(world);
    this.ticksInGround = 0;
    setSize(0.25F, 0.25F);
    setPosition(par2, par4, par6);
    this.yOffset = 0.0F;
    this.maxLen = 48.0D;
    this.ignoreFrustumCheck = true;
    setWatcherGrabbed();
  }
  
  @SideOnly(Side.CLIENT)
  public EntityHook(World world, double par2, double par4, double par6, EntityPlayer par8EntityPlayer) {
    this(world, par2, par4, par6);
    this.curPlayer = par8EntityPlayer;
    this.maxLen = 48.0D;
    this.ignoreFrustumCheck = true;
  }
  
  protected void entityInit() {
    initWatcherGrabbed();
  }
  
  protected void initWatcherGrabbed() {
    this.dataWatcher.addObject(25, Float.valueOf(0.0F));
    this.dataWatcher.addObject(26, Integer.valueOf(0));
    this.dataWatcher.addObject(27, Integer.valueOf(0));
    this.dataWatcher.addObject(28, Integer.valueOf(0));
    this.dataWatcher.addObject(29, Integer.valueOf(0));
    this.dataWatcher.addObject(30, Integer.valueOf(0));
    this.dataWatcher.addObject(31, Integer.valueOf(0));
  }
  
  public void setWatcherGrabbed() {
    if (!this.worldObj.isRemote) {
      this.dataWatcher.updateObject(26, Integer.valueOf(this.inGround ? 1 : 0));
      this.dataWatcher.updateObject(27, Integer.valueOf(this.hitted ? 1 : 0));
      this.dataWatcher.updateObject(28, Integer.valueOf(this.GrabType));
      this.dataWatcher.updateObject(29, Integer.valueOf(this.xTile));
      this.dataWatcher.updateObject(30, Integer.valueOf(this.yTile));
      this.dataWatcher.updateObject(31, Integer.valueOf(this.zTile));
    } 
  }
  
  public void getWatcherLenMP() {
    this.curLen = this.dataWatcher.getWatchableObjectFloat(25);
  }
  
  public void setWatcherLenMP() {
    this.dataWatcher.updateObject(25, Float.valueOf((float)this.curLen));
  }
  
  public void sendPacketCurLenToServer(double cul) {
    if (this.worldObj.isRemote && 
      (Minecraft.getMinecraft()).thePlayer == this.curPlayer)
      MFQM.network.sendToServer((IMessage)new HookMessage((float)cul)); 
  }
  
  public void getWatcherGrabbed() {
    if (this.worldObj.isRemote) {
      boolean prevInGround = this.inGround;
      boolean prevHitted = this.hitted;
      int prevGrabType = this.GrabType;
      int prevXTile = this.xTile;
      int prevYTile = this.yTile;
      int prevZTile = this.zTile;
      this.inGround = (this.dataWatcher.getWatchableObjectInt(26) == 1);
      this.hitted = (this.dataWatcher.getWatchableObjectInt(27) == 1);
      this.GrabType = this.dataWatcher.getWatchableObjectInt(28);
      this.xTile = this.dataWatcher.getWatchableObjectInt(29);
      this.yTile = this.dataWatcher.getWatchableObjectInt(30);
      this.zTile = this.dataWatcher.getWatchableObjectInt(31);
      if (this.inGround) {
        if (this.yTile > 0) {
          this.BlockPosX = this.xTile + 0.5D;
          this.BlockPosY = this.yTile + 0.5D - 0.25D;
          this.BlockPosZ = this.zTile + 0.5D;
        } 
        if (prevInGround != this.inGround)
          this.curLen = Math.pow(Math.pow(this.BlockPosX - this.curPlayer.posX, 2.0D) + Math.pow(this.BlockPosY - this.curPlayer.posY, 2.0D) + Math.pow(this.BlockPosZ - this.curPlayer.posZ, 2.0D), 0.5D); 
      } else if (this.GrabType != 0 && 
        prevGrabType != this.GrabType) {
        this.curLen = Math.pow(Math.pow(this.BlockPosX - this.curPlayer.posX, 2.0D) + Math.pow(this.BlockPosY - this.curPlayer.posY, 2.0D) + Math.pow(this.BlockPosZ - this.curPlayer.posZ, 2.0D), 0.5D);
      } 
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public boolean isInRangeToRenderDist(double par1) {
    double d1 = this.boundingBox.getAverageEdgeLength() * 4.0D;
    d1 *= 64.0D;
    return (par1 < d1 * d1);
  }
  
  protected float func_70182_d() {
    return 2.5F;
  }
  
  protected float func_70183_g() {
    return 0.0F;
  }
  
  public void setThrowableHeading(double par1, double par3, double par5, float par7, float par8) {
    float f2 = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
    par1 /= f2;
    par3 /= f2;
    par5 /= f2;
    par1 += this.rand.nextGaussian() * 0.007499999832361937D * par8;
    par3 += this.rand.nextGaussian() * 0.007499999832361937D * par8;
    par5 += this.rand.nextGaussian() * 0.007499999832361937D * par8;
    par1 *= par7;
    par3 *= par7;
    par5 *= par7;
    this.motionX = par1;
    this.motionY = par3;
    this.motionZ = par5;
    float f3 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
    this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(par1, par5) * 180.0D / Math.PI);
    this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(par3, f3) * 180.0D / Math.PI);
    this.ticksInGround = 0;
  }
  
  @SideOnly(Side.CLIENT)
  public void setVelocity(double par1, double par3, double par5) {
    this.motionX = par1;
    this.motionY = par3;
    this.motionZ = par5;
    if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
      float f = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
      this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(par1, par5) * 180.0D / Math.PI);
      this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(par3, f) * 180.0D / Math.PI);
    } 
  }
  
  public void onUpdate() {
    this.rotationPitch = 0.0F;
    this.rotationYaw = 0.0F;
    this.prevRotationYaw = 0.0F;
    this.prevRotationPitch = 0.0F;
    super.onUpdate();
    this.rotationPitch = 0.0F;
    this.rotationYaw = 0.0F;
    this.prevRotationYaw = 0.0F;
    this.prevRotationPitch = 0.0F;
    this.grabTick++;
    boolean breakIt = false;
    if (this.curPlayer == null) {
      setDead();
      return;
    } 
    if (this.curPlayer.isDead || !this.curPlayer.isEntityAlive())
      breakIt = true; 
    if (this.curPlayer != null && 
      checkingInHotBar() == -1)
      breakIt = true; 
    if (breakIt) {
      setDead();
      return;
    } 
    if (this.worldObj.getTotalWorldTime() % 16L == 0L)
      getWatcherGrabbed(); 
    if (this.worldObj.getTotalWorldTime() % 8L == 0L)
      saveMeInPacketHandler(); 
    if (!this.inGround) {
      if (this.GrabType < 7) {
        if (!this.hitted) {
          throwPhysics();
          this.mathLen = Math.pow(Math.pow(this.posX - this.curPlayer.posX, 2.0D) + Math.pow(this.posY - this.curPlayer.posY, 2.0D) + Math.pow(this.posZ - this.curPlayer.posZ, 2.0D), 0.5D);
          if (this.mathLen > this.maxLen) {
            this.hitted = true;
            this.motionX = 0.0D;
            this.motionY = 0.0D;
            this.motionZ = 0.0D;
          } 
        } else {
          updatePhysics();
          hookPhysic();
        } 
      } else {
        if (this.worldObj.isRemote && 
          (Minecraft.getMinecraft()).thePlayer != this.curPlayer)
          getWatcherLenMP(); 
        if (!this.worldObj.isRemote)
          setWatcherLenMP(); 
        this.BlockPosX = this.posX;
        this.BlockPosY = this.posY;
        this.BlockPosZ = this.posZ;
        this.noClip = true;
        livingGrab();
        return;
      } 
    } else {
      double dis = Math.pow(Math.pow(this.posX - this.BlockPosX, 2.0D) + Math.pow(this.posY - this.BlockPosY, 2.0D) + Math.pow(this.posZ - this.BlockPosZ, 2.0D), 0.5D);
      if (!this.worldObj.getBlock(this.xTile, this.yTile, this.zTile).getMaterial().isSolid())
        dis = 100.0D; 
      if (dis > 5.0D) {
        unGrabServer();
        return;
      } 
      this.BlockPosX = this.xTile + 0.5D;
      this.BlockPosY = this.yTile + 0.5D;
      this.BlockPosZ = this.zTile + 0.5D;
      this.posX = this.BlockPosX;
      this.posY = this.BlockPosY;
      this.posZ = this.BlockPosZ;
      this.prevPosX = this.BlockPosX;
      this.prevPosY = this.BlockPosY;
      this.prevPosZ = this.BlockPosZ;
      this.lastTickPosX = this.BlockPosX;
      this.lastTickPosY = this.BlockPosY;
      this.lastTickPosZ = this.BlockPosZ;
      this.onGround = true;
      this.fallDistance = 0.0F;
      this.motionX = 0.0D;
      this.motionY = 0.0D;
      this.motionZ = 0.0D;
      this.throwableShake = 0;
      this.noClip = true;
      setInWeb();
      playerControl();
      return;
    } 
  }
  
  protected float getGravityVelocity() {
    return 0.01F;
  }
  
  protected void onImpact(MovingObjectPosition movingObjectPosition) {
    if (movingObjectPosition.entityHit != null) {
      if (movingObjectPosition.entityHit instanceof EntityLivingBase && 
        movingObjectPosition.entityHit != this.curPlayer && 
        !this.worldObj.isRemote) {
        this.hitted = true;
        this.curGrabbed = (EntityLivingBase)movingObjectPosition.entityHit;
        movingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, (Entity)getThrower()), 1.5F);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.posX = this.curGrabbed.posX;
        this.posY = this.curGrabbed.boundingBox.minY + this.curGrabbed.height * 0.75D;
        this.posZ = this.curGrabbed.posZ;
        this.BlockPosX = this.posX;
        this.BlockPosY = this.posY;
        this.BlockPosZ = this.posZ;
        if (!(this.curGrabbed instanceof EntityPlayer)) {
          this.GrabType = 7;
        } else {
          this.GrabType = 8;
        } 
        if (getMoCreatureValid(this.curGrabbed))
          this.ridingEntity = (Entity)this.curGrabbed; 
        this.curLen = Math.pow(Math.pow(this.curGrabbed.posX - this.curPlayer.posX, 2.0D) + Math.pow(this.curGrabbed.posY - this.curPlayer.posY, 2.0D) + Math.pow(this.curGrabbed.posZ - this.curPlayer.posZ, 2.0D), 0.5D);
        this.beginCurLen = this.curLen;
        GrabSound();
        setWatcherGrabbed();
        return;
      } 
    } else {
      int blX = movingObjectPosition.blockX;
      int blY = movingObjectPosition.blockY;
      int blZ = movingObjectPosition.blockZ;
      if (this.worldObj.getBlock(blX, blY, blZ).getMaterial().isSolid())
        if (getValidStopMat(blX, blY, blZ)) {
          this.hitted = true;
          this.posX -= this.motionX;
          this.posY -= this.motionY;
          this.posZ -= this.motionZ;
          this.mathLen = Math.pow(Math.pow(movingObjectPosition.blockX + 0.5D - this.curPlayer.posX, 2.0D) + Math.pow(movingObjectPosition.blockY + 0.5D - this.curPlayer.posY, 2.0D) + Math.pow(movingObjectPosition.blockZ + 0.5D - this.curPlayer.posZ, 2.0D), 0.5D);
          if (this.mathLen > this.maxLen) {
            this.hitted = true;
            this.motionX = 0.0D;
            this.motionY = 0.0D;
            this.motionZ = 0.0D;
            setWatcherGrabbed();
            return;
          } 
          if (setValidToGrab(movingObjectPosition)) {
            setWatcherGrabbed();
            return;
          } 
        } else {
          sandDigging();
        }  
      setWatcherGrabbed();
    } 
  }
  
  protected void throwPhysics() {
    this.lastTickPosX = this.posX;
    this.lastTickPosY = this.posY;
    this.lastTickPosZ = this.posZ;
    super.onUpdate();
    if (this.throwableShake > 0)
      this.throwableShake--; 
    if (this.inGround) {
      Block i = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
      this.inGround = false;
      this.motionX *= (this.rand.nextFloat() * 0.2F);
      this.motionY *= (this.rand.nextFloat() * 0.2F);
      this.motionZ *= (this.rand.nextFloat() * 0.2F);
      this.ticksInGround = 0;
      this.ticksInAir = 0;
    } else {
      this.ticksInAir++;
    } 
    Vec3 vec3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
    Vec3 vec31 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
    MovingObjectPosition movingobjectposition = this.worldObj.rayTraceBlocks(vec3, vec31);
    vec3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
    vec31 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
    if (movingobjectposition != null)
      vec31 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord); 
    if (!this.worldObj.isRemote) {
      Entity entity = null;
      List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
      double d0 = 0.0D;
      EntityLivingBase entitylivingbase = getThrower();
      for (int j = 0; j < list.size(); j++) {
        Entity entity1 = list.get(j);
        if (entity1.canBeCollidedWith() && (entity1 != entitylivingbase || this.ticksInAir >= 5)) {
          float f = 0.3F;
          AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(f, f, f);
          MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(vec3, vec31);
          if (movingobjectposition1 != null) {
            double d1 = vec3.distanceTo(movingobjectposition1.hitVec);
            if (d1 < d0 || d0 == 0.0D) {
              entity = entity1;
              d0 = d1;
            } 
          } 
        } 
      } 
      if (entity != null)
        movingobjectposition = new MovingObjectPosition(entity); 
    } 
    if (movingobjectposition != null)
      if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && this.worldObj.getBlock(movingobjectposition.blockX, movingobjectposition.blockY, movingobjectposition.blockZ) == Blocks.portal) {
        setInPortal();
      } else if (this.worldObj.getBlock(movingobjectposition.blockX, movingobjectposition.blockY, movingobjectposition.blockZ).getCollisionBoundingBoxFromPool(this.worldObj, movingobjectposition.blockX, movingobjectposition.blockY, movingobjectposition.blockZ) != null) {
        onImpact(movingobjectposition);
      }  
    this.posX += this.motionX;
    this.posY += this.motionY;
    this.posZ += this.motionZ;
    float f1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
    this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
    for (this.rotationPitch = (float)(Math.atan2(this.motionY, f1) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F);
    while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
      this.prevRotationPitch += 360.0F; 
    while (this.rotationYaw - this.prevRotationYaw < -180.0F)
      this.prevRotationYaw -= 360.0F; 
    while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
      this.prevRotationYaw += 360.0F; 
    this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
    this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
    float f2 = 0.99F;
    float f3 = getGravityVelocity();
    if (isInWater()) {
      for (int k = 0; k < 4; k++) {
        float f4 = 0.25F;
        this.worldObj.spawnParticle("bubble", this.posX - this.motionX * f4, this.posY - this.motionY * f4, this.posZ - this.motionZ * f4, this.motionX, this.motionY, this.motionZ);
      } 
      f2 = 0.8F;
    } 
    this.motionX *= f2;
    this.motionY *= f2;
    this.motionZ *= f2;
    this.motionY -= f3;
    setPosition(this.posX, this.posY, this.posZ);
  }
  
  protected void updatePhysics() {
    this.prevPosX = this.posX;
    this.prevPosY = this.posY;
    this.prevPosZ = this.posZ;
    this.motionY -= 0.03999999910593033D;
    this.noClip = pushOutOfBlocks(this.posX, (this.boundingBox.minY + this.boundingBox.maxY) / 2.0D, this.posZ);
    moveEntity(this.motionX, this.motionY, this.motionZ);
    boolean flag = ((int)this.prevPosX != (int)this.posX || (int)this.prevPosY != (int)this.posY || (int)this.prevPosZ != (int)this.posZ);
    if (flag || this.ticksExisted % 25 == 0)
      if (this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)).getMaterial() == Material.lava) {
        this.motionY = 0.20000000298023224D;
        this.motionX = ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
        this.motionZ = ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
        playSound("random.fizz", 0.4F, 2.0F + this.rand.nextFloat() * 0.4F);
      }  
    float f = 0.98F;
    if (this.onGround)
      f = (this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ))).slipperiness * 0.98F; 
    this.motionX *= f;
    this.motionY *= 0.9800000190734863D;
    this.motionZ *= f;
    if (this.onGround)
      this.motionY *= -0.5D; 
  }
  
  public boolean setValidToGrab(MovingObjectPosition mop) {
    int blX = mop.blockX;
    int blY = mop.blockY;
    int blZ = mop.blockZ;
    double bcX = blX + 0.5D;
    double bcY = blY + 0.5D;
    double bcZ = blZ + 0.5D;
    this.GrabType = 0;
    switch (mop.sideHit) {
      case 0:
        if (!this.worldObj.getBlock(blX, blY - 1, blZ).getMaterial().isSolid() && 
          !getValidStopMat(blX, blY - 1, blZ))
          this.GrabType = 2; 
        this.inGround = true;
        this.BlockPosX = bcX;
        this.BlockPosY = bcY;
        this.BlockPosZ = bcZ;
        this.xTile = blX;
        this.yTile = blY;
        this.zTile = blZ;
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.curLen = Math.pow(Math.pow(this.BlockPosX - this.curPlayer.posX, 2.0D) + Math.pow(this.BlockPosY - this.curPlayer.posY, 2.0D) + Math.pow(this.BlockPosZ - this.curPlayer.posZ, 2.0D), 0.5D);
        this.beginCurLen = this.curLen;
        GrabSound();
        return true;
      case 1:
        if (!this.worldObj.getBlock(blX, blY + 1, blZ).getMaterial().isSolid() && !getValidStopMat(blX, blY + 1, blZ))
          this.GrabType = 1; 
        this.inGround = true;
        this.BlockPosX = bcX;
        this.BlockPosY = bcY;
        this.BlockPosZ = bcZ;
        this.xTile = blX;
        this.yTile = blY;
        this.zTile = blZ;
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.curLen = Math.pow(Math.pow(this.BlockPosX - this.curPlayer.posX, 2.0D) + Math.pow(this.BlockPosY - this.curPlayer.posY, 2.0D) + Math.pow(this.BlockPosZ - this.curPlayer.posZ, 2.0D), 0.5D);
        this.beginCurLen = this.curLen;
        GrabSound();
        return true;
      case 2:
        if (!this.worldObj.getBlock(blX - 1, blY, blZ).getMaterial().isSolid() && !getValidStopMat(blX - 1, blY, blZ))
          this.GrabType = 6; 
        this.inGround = true;
        this.BlockPosX = bcX;
        this.BlockPosY = bcY;
        this.BlockPosZ = bcZ;
        this.xTile = blX;
        this.yTile = blY;
        this.zTile = blZ;
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.curLen = Math.pow(Math.pow(this.BlockPosX - this.curPlayer.posX, 2.0D) + Math.pow(this.BlockPosY - this.curPlayer.posY, 2.0D) + Math.pow(this.BlockPosZ - this.curPlayer.posZ, 2.0D), 0.5D);
        this.beginCurLen = this.curLen;
        GrabSound();
        return true;
      case 3:
        if (!this.worldObj.getBlock(blX + 1, blY, blZ).getMaterial().isSolid() && !getValidStopMat(blX + 1, blY, blZ))
          this.GrabType = 5; 
        this.inGround = true;
        this.BlockPosX = bcX;
        this.BlockPosY = bcY;
        this.BlockPosZ = bcZ;
        this.xTile = blX;
        this.yTile = blY;
        this.zTile = blZ;
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.curLen = Math.pow(Math.pow(this.BlockPosX - this.curPlayer.posX, 2.0D) + Math.pow(this.BlockPosY - this.curPlayer.posY, 2.0D) + Math.pow(this.BlockPosZ - this.curPlayer.posZ, 2.0D), 0.5D);
        this.beginCurLen = this.curLen;
        GrabSound();
        return true;
      case 4:
        if (!this.worldObj.getBlock(blX, blY, blZ - 1).getMaterial().isSolid() && !getValidStopMat(blX, blY, blZ - 1))
          this.GrabType = 4; 
        this.inGround = true;
        this.BlockPosX = bcX;
        this.BlockPosY = bcY;
        this.BlockPosZ = bcZ;
        this.xTile = blX;
        this.yTile = blY;
        this.zTile = blZ;
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.curLen = Math.pow(Math.pow(this.BlockPosX - this.curPlayer.posX, 2.0D) + Math.pow(this.BlockPosY - this.curPlayer.posY, 2.0D) + Math.pow(this.BlockPosZ - this.curPlayer.posZ, 2.0D), 0.5D);
        this.beginCurLen = this.curLen;
        GrabSound();
        return true;
      case 5:
        if (!this.worldObj.getBlock(blX, blY, blZ + 1).getMaterial().isSolid() && !getValidStopMat(blX, blY, blZ + 1))
          this.GrabType = 3; 
        this.inGround = true;
        this.BlockPosX = bcX;
        this.BlockPosY = bcY;
        this.BlockPosZ = bcZ;
        this.xTile = blX;
        this.yTile = blY;
        this.zTile = blZ;
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.curLen = Math.pow(Math.pow(this.BlockPosX - this.curPlayer.posX, 2.0D) + Math.pow(this.BlockPosY - this.curPlayer.posY, 2.0D) + Math.pow(this.BlockPosZ - this.curPlayer.posZ, 2.0D), 0.5D);
        this.beginCurLen = this.curLen;
        GrabSound();
        return true;
    } 
    return false;
  }
  
  public boolean getValidStopMat(int x, int y, int z) {
    if (!this.worldObj.getBlock(x, y, z).getMaterial().isSolid())
      return false; 
    if (this.worldObj.getBlock(x, y, z).getMaterial().isLiquid())
      return false; 
    if (this.worldObj.getBlock(x, y, z).getMaterial() == Material.sand)
      return false; 
    if (this.worldObj.getBlock(x, y, z).getMaterial() == Material.leaves)
      return false; 
    if (this.worldObj.getBlock(x, y, z).getMaterial() == Material.snow)
      return false; 
    return true;
  }
  
  public void writeSpawnData(ByteBuf data) {
    data.writeInt((this.curPlayer != null) ? this.curPlayer.getEntityId() : 0);
  }
  
  public void readSpawnData(ByteBuf data) {
    this.curPlayer = (EntityPlayer)this.worldObj.getEntityByID(data.readInt());
  }
  
  public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {}
  
  public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {}
  
  @SideOnly(Side.CLIENT)
  public float getShadowSize() {
    return 0.0F;
  }
  
  public EntityLivingBase getThrower() {
    if (this.thrower == null && this.throwerName != null && this.throwerName.length() > 0)
      this.thrower = (EntityLivingBase)this.worldObj.getPlayerEntityByName(this.throwerName); 
    return this.thrower;
  }
  
  public void playerControl() {
    if (this.curPlayer == null)
      return; 
    if (this.curPlayer.isDead || !this.curPlayer.isEntityAlive())
      return; 
    int ropeNum = checkingInHotBar();
    if (ropeNum == -1)
      return; 
    ItemStack ropeItem = this.curPlayer.inventory.mainInventory[ropeNum];
    if (this.curPlayer.motionY >= -1.0D && this.curPlayer.posX - this.curPlayer.prevPosX >= -0.5D)
      this.curPlayer.fallDistance = 0.0F; 
    if (this.curPlayer.motionY >= -1.0D && this.curPlayer.posZ - this.curPlayer.prevPosZ >= -0.5D)
      this.curPlayer.fallDistance = 0.0F; 
    this.mathLen = Math.pow(Math.pow(this.BlockPosX - this.curPlayer.posX, 2.0D) + Math.pow(this.BlockPosY - this.curPlayer.posY, 2.0D) + Math.pow(this.BlockPosZ - this.curPlayer.posZ, 2.0D), 0.5D);
    this.brkLen = this.curLen + this.maxLen / 20.0D + 1.0D;
    double preCurLen = this.curLen;
    if (this.mathLen > this.brkLen) {
      if (!this.worldObj.isRemote) {
        if (ropeItem.getMetadata() < 63) {
          ropeItem.damageItem(2, (EntityLivingBase)this.curPlayer);
          if (this.worldObj.rand.nextInt(5) == 0)
            stretchSound(); 
        } else {
          if (!this.worldObj.isRemote) {
            Vec3 dir = getBreakPos();
            MFQM.dropItem(this.worldObj, dir.xCoord, dir.yCoord, dir.zCoord, new ItemStack(MFQM.HookItem));
          } 
          this.curPlayer.inventory.setInventorySlotContents(ropeNum, new ItemStack(MFQM.GrapplingHookBRKItem));
          breakSound();
        } 
        if (this.worldObj.rand.nextInt(5) == 0)
          tryingBreakBlock(); 
        if (this.worldObj.rand.nextInt(100) == 0) {
          this.motionX = Math.min((this.curPlayer.posX - this.posX) / this.mathLen * (this.mathLen + 5.0D), 0.1D) + this.motionX / (this.mathLen + 5.0D + 1.0D);
          this.motionY = Math.min((this.curPlayer.posY - this.posY) / this.mathLen * (this.mathLen + 5.0D), 0.1D) + this.motionY / (this.mathLen + 5.0D + 1.0D);
          this.motionZ = Math.min((this.curPlayer.posZ - this.posZ) / this.mathLen * (this.mathLen + 5.0D), 0.1D) + this.motionZ / (this.mathLen + 5.0D + 1.0D);
          unGrabSound();
          unGrabServer();
          return;
        } 
      } 
      if (this.curLen < this.maxLen)
        this.curLen += (this.maxLen - this.curLen) / 32.0D; 
    } 
    if (jumpKey() && 
      this.curLen > 1.0D)
      this.curLen = this.mathLen - Math.max(0.25D, 1.0D - Math.max(this.curPlayer.motionY * 4.0D, 0.0D)); 
    if (crouchKey() && 
      this.curLen < this.maxLen)
      this.curLen += 0.15D; 
    ItemStack curItm = this.curPlayer.getCurrentEquippedItem();
    if (curItm != null && 
      curItm.getItem() instanceof MoreFunQuicksandMod.main.items.ItemGrapplingHook && 
      curItm.getMetadata() % 2 != 0 && 
      leftKey() && 
      this.curLen > 1.0D)
      this.curLen -= 0.25D; 
    if (midKey() && 
      this.curLen < this.maxLen)
      this.curLen += 0.15D; 
    this.curLen = Math.min(this.maxLen, this.curLen);
    if (preCurLen < this.curLen) {
      if (this.worldObj.getTotalWorldTime() % 8L == 0L)
        sendPacketCurLenToServer(this.maxLen); 
    } else if (this.curLen != this.maxLen && 
      this.worldObj.getTotalWorldTime() % 8L == 0L) {
      sendPacketCurLenToServer(this.curLen);
    } 
    if (this.mathLen > this.curLen) {
      int qs = isInsideOfQuicksand((EntityLivingBase)this.curPlayer);
      double movFac = getMovFactor((EntityLivingBase)this.curPlayer, qs);
      double stretch = Math.max((this.mathLen - this.curLen) / Math.min(movFac * 4.0D, 1.0D), 0.0D);
      if (!this.worldObj.isRemote) {
        if (stretch > 3.5D) {
          if (this.worldObj.getTotalWorldTime() % Math.max((int)Math.floor(32.0D - stretch), 1) == 0L) {
            if (this.worldObj.rand.nextInt((int)Math.floor(stretch / 10.0D) + 10) == 0)
              if (ropeItem.getMetadata() < 63) {
                ropeItem.damageItem(2, (EntityLivingBase)this.curPlayer);
                if (this.worldObj.rand.nextInt(5) == 0)
                  stretchSound(); 
              } else {
                if (!this.worldObj.isRemote) {
                  Vec3 dir = getBreakPos();
                  MFQM.dropItem(this.worldObj, dir.xCoord, dir.yCoord, dir.zCoord, new ItemStack(MFQM.HookItem));
                } 
                this.curPlayer.inventory.setInventorySlotContents(ropeNum, new ItemStack(MFQM.GrapplingHookBRKItem));
                breakSound();
              }  
            if (this.worldObj.rand.nextInt(Math.max(200 - (int)Math.floor(stretch), 150)) == 0) {
              this.motionX = Math.min((this.curPlayer.posX - this.posX) / this.mathLen * (this.mathLen + 2.0D), 0.1D) + this.motionX / (this.mathLen + 2.0D + 1.0D);
              this.motionY = Math.min((this.curPlayer.posY - this.posY) / this.mathLen * (this.mathLen + 2.0D), 0.1D) + this.motionY / (this.mathLen + 2.0D + 1.0D);
              this.motionZ = Math.min((this.curPlayer.posZ - this.posZ) / this.mathLen * (this.mathLen + 2.0D), 0.1D) + this.motionZ / (this.mathLen + 2.0D + 1.0D);
              unGrabSound();
              unGrabServer();
              return;
            } 
            if (this.worldObj.getTotalWorldTime() % Math.max((int)Math.floor(16.0D - stretch), 1) == 0L && 
              !this.worldObj.isRemote && 
              this.worldObj.rand.nextInt(Math.max(50 - (int)Math.floor(stretch * 10.0D), 10)) == 0)
              tryingBreakBlock(); 
          } 
        } else if (stretch > 1.5D && 
          this.worldObj.getTotalWorldTime() % Math.max((int)Math.floor(16.0D - stretch), 1) == 0L && 
          !this.worldObj.isRemote && 
          this.worldObj.rand.nextInt(Math.max(50 - (int)Math.floor(stretch * 10.0D), 10)) == 0) {
          tryingBreakBlock();
        } 
      } else {
        double disa = Math.max((this.mathLen - this.curLen) / 7.5D, 0.0D);
        this.curPlayer.motionX = Math.min((this.BlockPosX - this.curPlayer.posX) / this.mathLen * disa, 0.025D) * movFac + this.curPlayer.motionX / (disa / 100.0D + 1.0D);
        this.curPlayer.motionY = Math.min((this.BlockPosY - this.curPlayer.posY) / this.mathLen * disa, 0.1D) * movFac + this.curPlayer.motionY / (disa / 2.0D + 1.0D);
        this.curPlayer.motionZ = Math.min((this.BlockPosZ - this.curPlayer.posZ) / this.mathLen * disa, 0.025D) * movFac + this.curPlayer.motionZ / (disa / 100.0D + 1.0D);
        this.curPlayer.motionY = getMaxYSpeed(this.curPlayer.motionY, qs);
      } 
    } 
  }
  
  public void hookPhysic() {
    double disa = Math.max((this.mathLen - this.maxLen) / 20.0D, 0.0D);
    this.mathLen = Math.pow(Math.pow(this.posX - this.curPlayer.posX, 2.0D) + Math.pow(this.posY - this.curPlayer.posY, 2.0D) + Math.pow(this.posZ - this.curPlayer.posZ, 2.0D), 0.5D);
    this.motionX = Math.min((this.curPlayer.posX - this.posX) / this.mathLen * disa, 0.1D) + this.motionX / (disa + 1.0D);
    this.motionY = Math.min((this.curPlayer.posY - this.posY) / this.mathLen * disa, 0.1D) + this.motionY / (disa + 1.0D);
    this.motionZ = Math.min((this.curPlayer.posZ - this.posZ) / this.mathLen * disa, 0.1D) + this.motionZ / (disa + 1.0D);
  }
  
  @SideOnly(Side.CLIENT)
  public EntityPlayer getPlayerMC() {
    return (EntityPlayer)(Minecraft.getMinecraft()).thePlayer;
  }
  
  public void livingGrab() {
    if (this.curPlayer == null)
      return; 
    if (this.curPlayer.isDead || !this.curPlayer.isEntityAlive())
      return; 
    int ropeNum = checkingInHotBar();
    if (ropeNum == -1)
      return; 
    EntityPlayer ply = this.curPlayer;
    if (this.worldObj.isRemote)
      ply = getPlayerMC(); 
    ItemStack ropeItem = this.curPlayer.inventory.mainInventory[ropeNum];
    if (!this.worldObj.isRemote) {
      if (this.curGrabbed == null) {
        unGrabServer();
        return;
      } 
      if (this.curGrabbed.isDead || !this.curGrabbed.isEntityAlive()) {
        unGrabServer();
        return;
      } 
      this.motionX = 0.0D;
      this.motionY = 0.0D;
      this.motionZ = 0.0D;
      this.posX = this.curGrabbed.posX;
      this.posY = this.curGrabbed.boundingBox.minY + this.curGrabbed.height * 0.75D;
      this.posZ = this.curGrabbed.posZ;
      if (this.curPlayer.motionY >= -1.0D && this.curPlayer.posX - this.curPlayer.prevPosX >= -0.5D)
        this.curPlayer.fallDistance = 0.0F; 
      if (this.curPlayer.motionY >= -1.0D && this.curPlayer.posZ - this.curPlayer.prevPosZ >= -0.5D)
        this.curGrabbed.fallDistance = 0.0F; 
    } else if (this.GrabType == 8 && 
      ply != this.curPlayer && 
      this.curGrabbed == null && 
      !ply.isDead && ply.isEntityAlive() && 
      Math.pow(Math.pow(this.posX - ply.posX, 2.0D) + Math.pow(this.posY - ply.posY, 2.0D) + Math.pow(this.posZ - ply.posZ, 2.0D), 0.5D) < 1.5D) {
      this.curGrabbed = (EntityLivingBase)ply;
      GrabSound();
      return;
    } 
    this.mathLen = Math.pow(Math.pow(this.posX - this.curPlayer.posX, 2.0D) + Math.pow(this.posY - this.curPlayer.posY, 2.0D) + Math.pow(this.posZ - this.curPlayer.posZ, 2.0D), 0.5D);
    this.brkLen = this.curLen + this.maxLen / 10.0D + 1.0D;
    double preCurLen = this.curLen;
    if (this.mathLen > this.brkLen) {
      if (!this.worldObj.isRemote) {
        if (ropeItem.getMetadata() < 63) {
          ropeItem.damageItem(2, (EntityLivingBase)this.curPlayer);
          if (this.worldObj.rand.nextInt(5) == 0)
            stretchSound(); 
        } else {
          if (!this.worldObj.isRemote)
            MFQM.dropItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(MFQM.HookItem)); 
          this.curPlayer.inventory.setInventorySlotContents(ropeNum, new ItemStack(MFQM.GrapplingHookBRKItem));
          breakSound();
        } 
        if (this.worldObj.rand.nextInt(50) == 0) {
          this.motionX = Math.min((this.curPlayer.posX - this.posX) / this.mathLen * (this.mathLen + 5.0D), 0.1D) + this.motionX / (this.mathLen + 5.0D + 1.0D);
          this.motionY = Math.min((this.curPlayer.posY - this.posY) / this.mathLen * (this.mathLen + 5.0D), 0.1D) + this.motionY / (this.mathLen + 5.0D + 1.0D);
          this.motionZ = Math.min((this.curPlayer.posZ - this.posZ) / this.mathLen * (this.mathLen + 5.0D), 0.1D) + this.motionZ / (this.mathLen + 5.0D + 1.0D);
          unGrabSound();
          unGrabServer();
          return;
        } 
      } 
      if (this.curLen < this.maxLen)
        this.curLen += (this.maxLen - this.curLen) / 32.0D; 
    } 
    if (jumpKey() && 
      this.curLen > 1.0D)
      this.curLen = this.mathLen - Math.max(0.25D, 1.0D - Math.max(this.curPlayer.motionY * 4.0D, 0.0D)); 
    ItemStack curItm = this.curPlayer.getCurrentEquippedItem();
    if (curItm != null && 
      curItm.getItem() instanceof MoreFunQuicksandMod.main.items.ItemGrapplingHook && 
      curItm.getMetadata() % 2 != 0 && 
      leftKey() && 
      this.curLen > 1.0D)
      this.curLen -= 0.25D; 
    if (midKey() && 
      this.curLen < this.maxLen)
      this.curLen += 0.15D; 
    this.curLen = Math.min(this.maxLen, this.curLen);
    if (this.curLen != this.maxLen && 
      this.worldObj.getTotalWorldTime() % 8L == 0L)
      sendPacketCurLenToServer(this.curLen); 
    if (this.mathLen > this.curLen) {
      int qs1 = isInsideOfQuicksand((EntityLivingBase)this.curPlayer);
      double movFac1 = getMovFactor((EntityLivingBase)this.curPlayer, qs1) / 4.0D;
      int qs2 = -1;
      double movFac2 = 1.0D;
      double movFac2XZ = 1.0D;
      if (!this.worldObj.isRemote) {
        qs2 = isInsideOfQuicksand(this.curGrabbed);
        movFac2 = getMovFactor(this.curGrabbed, qs2) / getWeight(this.curGrabbed);
        movFac2XZ = movFac2 * 0.1D;
      } else {
        qs2 = -1;
        movFac2 = 1.0D;
        movFac2XZ = 1.0D;
      } 
      double stretch = Math.max((this.mathLen - this.curLen) / Math.min(movFac1 * 4.0D, 1.0D) / Math.min(movFac2 * 8.0D, 1.0D), 0.0D);
      if (!this.worldObj.isRemote) {
        if (stretch > 4.5D && 
          this.worldObj.getTotalWorldTime() % Math.max((int)Math.floor(32.0D - stretch), 1) == 0L) {
          if (this.worldObj.rand.nextInt((int)Math.floor(stretch / 10.0D) + 10) == 0)
            if (ropeItem.getMetadata() < 63) {
              ropeItem.damageItem(2, (EntityLivingBase)this.curPlayer);
              if (this.worldObj.rand.nextInt(5) == 0)
                stretchSound(); 
            } else {
              if (!this.worldObj.isRemote)
                MFQM.dropItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(MFQM.HookItem)); 
              this.curPlayer.inventory.setInventorySlotContents(ropeNum, new ItemStack(MFQM.GrapplingHookBRKItem));
              breakSound();
            }  
          if (this.worldObj.rand.nextInt(Math.max(200 - (int)Math.floor(stretch), 150)) == 0) {
            this.motionX = Math.min((this.curPlayer.posX - this.posX) / this.mathLen * (this.mathLen + 2.0D), 0.1D) + this.motionX / (this.mathLen + 2.0D + 1.0D);
            this.motionY = Math.min((this.curPlayer.posY - this.posY) / this.mathLen * (this.mathLen + 2.0D), 0.1D) + this.motionY / (this.mathLen + 2.0D + 1.0D);
            this.motionZ = Math.min((this.curPlayer.posZ - this.posZ) / this.mathLen * (this.mathLen + 2.0D), 0.1D) + this.motionZ / (this.mathLen + 2.0D + 1.0D);
            unGrabSound();
            unGrabServer();
            return;
          } 
        } 
        double disa = Math.max(this.mathLen - this.curLen, 0.0D);
        this.curGrabbed.motionX = Math.min((this.curPlayer.posX - this.curGrabbed.posX) / this.mathLen * disa, 0.05D) * movFac2XZ + this.curGrabbed.motionX / (disa / 50.0D + 1.0D);
        this.curGrabbed.motionY = Math.min((this.curPlayer.posY + 0.5D - this.curGrabbed.posY) / this.mathLen * disa, 0.2D) * movFac2 + this.curGrabbed.motionY / (disa / 2.0D + 1.0D);
        this.curGrabbed.motionZ = Math.min((this.curPlayer.posZ - this.curGrabbed.posZ) / this.mathLen * disa, 0.05D) * movFac2XZ + this.curGrabbed.motionZ / (disa / 50.0D + 1.0D);
        this.curGrabbed.onGround = false;
        this.curGrabbed.motionY = getMaxYSpeed(this.curGrabbed.motionY, qs2);
      } else {
        if (ply != this.curPlayer && 
          this.curGrabbed != null && 
          this.curGrabbed instanceof EntityPlayer && 
          !this.curGrabbed.isDead && this.curGrabbed.isEntityAlive()) {
          double disa1 = Math.max((this.mathLen - this.curLen) / 5.0D, 0.0D);
          this.curGrabbed.motionX = Math.min((this.curPlayer.posX - this.curGrabbed.posX) / this.mathLen * disa1, 0.025D) * movFac2XZ + this.curGrabbed.motionX / (disa1 / 100.0D + 1.0D);
          this.curGrabbed.motionY = Math.min((this.curPlayer.posY - this.curGrabbed.posY) / this.mathLen * disa1, 0.1D) * movFac2 + this.curGrabbed.motionY / (disa1 / 2.0D + 1.0D);
          this.curGrabbed.motionZ = Math.min((this.curPlayer.posZ - this.curGrabbed.posZ) / this.mathLen * disa1, 0.025D) * movFac2XZ + this.curGrabbed.motionZ / (disa1 / 100.0D + 1.0D);
          this.curGrabbed.motionY = getMaxYSpeed(this.curGrabbed.motionY, qs2);
        } 
        double disa = Math.max((this.mathLen - this.curLen) / 7.5D, 0.0D);
        this.curPlayer.motionX = Math.min((this.posX - this.curPlayer.posX) / this.mathLen * disa, 0.025D) * movFac1 + this.curPlayer.motionX / (disa / 100.0D + 1.0D);
        this.curPlayer.motionY = Math.min((this.posY - this.curPlayer.posY) / this.mathLen * disa, 0.1D) * movFac1 + this.curPlayer.motionY / (disa / 2.0D + 1.0D);
        this.curPlayer.motionZ = Math.min((this.posZ - this.curPlayer.posZ) / this.mathLen * disa, 0.025D) * movFac1 + this.curPlayer.motionZ / (disa / 100.0D + 1.0D);
        this.curPlayer.motionY = getMaxYSpeed(this.curPlayer.motionY, qs1);
      } 
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public int getBrightnessForRender(float par1) {
    int i = MathHelper.floor_double(this.curPlayer.posX);
    int j = MathHelper.floor_double(this.curPlayer.posZ);
    if (this.worldObj.blockExists(i, 0, j)) {
      int upper = 0;
      if (!this.worldObj.isRemote)
        upper = 1; 
      int k = MathHelper.floor_double(this.curPlayer.posY + upper);
      return this.worldObj.getLightBrightnessForSkyBlocks(i, k, j, 0);
    } 
    return 0;
  }
  
  public float getBrightness(float par1) {
    int i = MathHelper.floor_double(this.curPlayer.posX);
    int j = MathHelper.floor_double(this.curPlayer.posZ);
    if (this.worldObj.blockExists(i, 0, j)) {
      int upper = 0;
      if (!this.worldObj.isRemote)
        upper = 1; 
      int k = MathHelper.floor_double(this.curPlayer.posY + upper);
      return this.worldObj.getLightBrightness(i, k, j);
    } 
    return 0.0F;
  }
  
  public int checkingInHotBar() {
    int i = this.curPlayer.inventory.currentItem;
    if (this.curPlayer.inventory.mainInventory[i] != null && 
      this.curPlayer.inventory.mainInventory[i].getItem() instanceof MoreFunQuicksandMod.main.items.ItemGrapplingHook && (
      this.grabTick <= 5 || this.curPlayer.inventory.mainInventory[i].getMetadata() % 2 != 0))
      return i; 
    return -1;
  }
  
  public int isInsideOfQuicksand(EntityLivingBase ent) {
    int ppx = (int)Math.floor(ent.posX);
    int ppy = (int)Math.floor(ent.posY);
    int ppz = (int)Math.floor(ent.posZ);
    if (!this.worldObj.isRemote || !(ent instanceof EntityPlayer))
      ppy = (int)Math.floor(ent.posY + 1.25D); 
    for (int i = 0; i < EntityRope.quicksandIDS.length; i++) {
      if (this.worldObj.getBlock(ppx, ppy, ppz) == EntityRope.quicksandIDS[i])
        return (i + 1) * 100; 
      if (this.worldObj.getBlock(ppx, ppy - 1, ppz) == EntityRope.quicksandIDS[i])
        return i + 1; 
    } 
    return -1;
  }
  
  public double getMovFactor(EntityLivingBase ent, int qs) {
    double moveKof = 1.0D;
    if (qs != -1)
      if (qs >= 100) {
        int trueQS = qs / 100 - 1;
        int qsHC = EntityRope.quicksandHardCoof[trueQS];
        moveKof /= qsHC * 0.625D;
      } else {
        int trueQS = qs - 1;
        int qsHC = EntityRope.quicksandHardCoof[trueQS];
        int ppy = (int)Math.floor(ent.posY);
        double depthCof = 0.0D;
        if (this.worldObj.isRemote && ent instanceof EntityPlayer) {
          depthCof = Math.max(1.0D / Math.max(ent.posY - ppy, 0.25D) * 8.0D, 1.0D);
        } else {
          depthCof = Math.max(1.0D / Math.max(ent.posY + 1.25D - ppy, 0.25D) * 8.0D, 1.0D);
        } 
        moveKof /= qsHC * depthCof;
      }  
    return Math.min(Math.max(moveKof, 0.01D) * 10.0D, 1.0D);
  }
  
  public double getWeight(EntityLivingBase ent) {
    return Math.max(MathHelper.sqrt_double((ent.height / 2.0F * ent.height / 2.0F + ent.width * ent.width)) / 0.75D, 1.0D);
  }
  
  public Vec3 getBreakPos() {
    Vec3 dir = Vec3.createVectorHelper(0.0D, 0.0D, 0.0D);
    switch (this.GrabType) {
      case 1:
        dir.xCoord = 0.0D;
        dir.yCoord = -1.0D;
        dir.zCoord = 0.0D;
        break;
      case 2:
        dir.xCoord = 0.0D;
        dir.yCoord = 1.0D;
        dir.zCoord = 0.0D;
        break;
      case 3:
        dir.xCoord = -1.0D;
        dir.yCoord = 0.0D;
        dir.zCoord = 0.0D;
        break;
      case 4:
        dir.xCoord = 1.0D;
        dir.yCoord = 0.0D;
        dir.zCoord = 0.0D;
        break;
      case 5:
        dir.xCoord = 0.0D;
        dir.yCoord = 0.0D;
        dir.zCoord = -1.0D;
        break;
      case 6:
        dir.xCoord = 0.0D;
        dir.yCoord = 0.0D;
        dir.zCoord = 1.0D;
        break;
    } 
    dir.xCoord *= 0.625D;
    dir.yCoord *= 0.625D;
    dir.zCoord *= 0.625D;
    return dir;
  }
  
  public void tryingBreakBlock() {
    if (this.yTile > 0 && (
      this.worldObj.getBlock(this.xTile, this.yTile, this.zTile).getMaterial() == Material.glass || this.worldObj
      .getBlock(this.xTile, this.yTile, this.zTile).getMaterial().getMaterialMobility() == 1))
      this.worldObj.breakBlock(this.xTile, this.yTile, this.zTile, true); 
  }
  
  public void sandDigging() {
    int xx = (int)Math.floor(this.posX);
    int yy = (int)Math.floor(this.posY - 0.5D);
    int zz = (int)Math.floor(this.posZ);
    if (this.worldObj.getBlock(xx, yy, zz).getMaterial().isSolid()) {
      Block block = this.worldObj.getBlock(xx, yy, zz);
      this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, block.stepSound.getPlaceSound(), 0.25F, block.stepSound.getFrequency());
      this.worldObj.spawnParticle("blockcrack_" + Block.getIdFromBlock(block) + "_" + this.worldObj.getBlockMetadata(xx, yy, zz), this.posX, this.posY, this.posZ, (this.worldObj.rand.nextFloat() - 0.5D) / 100.0D, (this.worldObj.rand.nextFloat() / 50.0F), (this.worldObj.rand.nextFloat() - 0.5D) / 100.0D);
    } 
    this.motionX *= 0.85D;
    this.motionY *= 0.85D;
    this.motionZ *= 0.85D;
    this.inSand++;
    if (this.inSand > 10)
      this.hitted = true; 
  }
  
  public boolean getMoCreatureValid(EntityLivingBase ent) {
    if (!MFQM.HookAsRider)
      return false; 
    if (ent.getCommandSenderName().equalsIgnoreCase("HorseMob"))
      return false; 
    if (ent.getCommandSenderName().equalsIgnoreCase("Elephant"))
      return false; 
    if (ent.getCommandSenderName().equalsIgnoreCase("Crocodile"))
      return false; 
    if (ent.getCommandSenderName().equalsIgnoreCase("WildHorse"))
      return false; 
    if (ent.getMountedYOffset() != ent.height * 0.75D)
      return false; 
    return true;
  }
  
  public void unGrabServer() {
    this.inGround = false;
    this.GrabType = 0;
    this.BlockPosX = 0.0D;
    this.BlockPosY = 0.0D;
    this.BlockPosZ = 0.0D;
    this.xTile = -1;
    this.yTile = -1;
    this.zTile = -1;
    this.curGrabbed = null;
    this.ridingEntity = null;
    setWatcherGrabbed();
  }
  
  public void breakSound() {
    this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "random.break", 0.35F, this.worldObj.rand.nextFloat() * 0.25F + 0.8F);
    this.worldObj.playSoundEffect(this.curPlayer.posX, this.curPlayer.posY, this.curPlayer.posZ, "random.break", 0.25F, this.worldObj.rand.nextFloat() * 0.25F + 0.8F);
  }
  
  public void GrabSound() {
    if (this.yTile > 0)
      this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "mob.irongolem.hit", 0.5F, this.worldObj.rand.nextFloat() * 0.15F + 1.75F); 
  }
  
  public void unGrabSound() {
    if (this.yTile > 0 && 
      this.worldObj.getBlock(this.xTile, this.yTile, this.zTile).getMaterial().isSolid()) {
      Block block = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
      this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, block.stepSound.getDigResourcePath(), 0.5F, block.stepSound.getFrequency());
      this.worldObj.playSoundEffect(this.curPlayer.posX, this.curPlayer.posY, this.curPlayer.posZ, block.stepSound.getDigResourcePath(), 0.5F, block.stepSound.getFrequency());
    } 
  }
  
  public void stretchSound() {
    this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "step.ladder", 0.5F, this.worldObj.rand.nextFloat() * 0.5F + 1.5F);
    this.worldObj.playSoundEffect(this.curPlayer.posX, this.curPlayer.posY, this.curPlayer.posZ, "step.ladder", 0.25F, this.worldObj.rand.nextFloat() * 0.5F + 1.5F);
  }
  
  public boolean midKey() {
    if (this.worldObj.isRemote && 
      (Minecraft.getMinecraft()).thePlayer == this.curPlayer)
      return GameSettings.isKeyDown((Minecraft.getMinecraft()).gameSettings.keyBindPickBlock); 
    return false;
  }
  
  public boolean leftKey() {
    if (this.worldObj.isRemote && 
      (Minecraft.getMinecraft()).thePlayer == this.curPlayer)
      return GameSettings.isKeyDown((Minecraft.getMinecraft()).gameSettings.keyBindAttack); 
    return false;
  }
  
  public boolean jumpKey() {
    if (this.worldObj.isRemote && 
      (Minecraft.getMinecraft()).thePlayer == this.curPlayer)
      return Keyboard.isKeyDown((Minecraft.getMinecraft()).gameSettings.keyBindJump.getKeyCode()); 
    return false;
  }
  
  public boolean crouchKey() {
    if (this.worldObj.isRemote && 
      (Minecraft.getMinecraft()).thePlayer == this.curPlayer)
      return Keyboard.isKeyDown((Minecraft.getMinecraft()).gameSettings.keyBindSneak.getKeyCode()); 
    return false;
  }
  
  public double getMaxYSpeed(double vel, int qs) {
    if (qs != -1) {
      if (qs >= 100) {
        qs = qs / 100 - 1;
      } else {
        qs--;
      } 
      if (((qs >= 3 && qs <= 5) || (qs >= 8 && qs <= 12) || (qs >= 14 && qs <= 22) || qs == 26) && 
        this.worldObj.rand.nextInt(Math.max(1000 / EntityRope.quicksandHardCoof[qs], 1)) != 0)
        vel = Math.min(vel, 0.01625D); 
    } 
    return vel;
  }
  
  public void saveMeInPacketHandler() {
    if (!this.isRegistered && 
      this.curPlayer != null && 
      !this.worldObj.isRemote) {
      String un = this.curPlayer.getCommandSenderName();
      for (int i = 0; i < 64; i++) {
        boolean EN = false;
        if (MFQM.arrayHooks[i] == null) {
          EN = true;
        } else if (!MFQM.arrayHooks[i].isEntityAlive()) {
          EN = true;
        } 
        if (EN) {
          MFQM.arrayHooks[i] = this;
          MFQM.arrayPlayersHooks[i] = un;
          this.isRegistered = true;
          return;
        } 
      } 
    } 
  }
}
