package MoreFunQuicksandMod.main.entity;

import MoreFunQuicksandMod.main.MFQM;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityTentacles extends Entity implements IEntityAdditionalSpawnData {
  public Entity target;
  
  public boolean targetUpdate = true;
  
  public UUID targetUUID;
  
  public int AnimLifeTime = 0;
  
  public int maxHurtResistantTime;
  
  public float health = 10.0F;
  
  public int high;
  
  public EntityTentacles(World world) {
    super(world);
    setSize(1.0F, 1.0F);
    this.yOffset = this.height / 2.0F;
    this.ignoreFrustumCheck = true;
    this.AnimLifeTime = 0;
    this.noClip = true;
  }
  
  public EntityTentacles(World world, double x, double y, double z, Entity liv, int Hgh, int tiw) {
    this(world);
    setPosition(x, y, z);
    this.high = Hgh;
    this.AnimLifeTime = tiw;
    this.target = liv;
  }
  
  protected void entityInit() {
    this.dataWatcher.addObject(25, Integer.valueOf(-1));
    this.dataWatcher.addObject(26, Integer.valueOf(0));
    this.dataWatcher.addObject(27, Float.valueOf(10.0F));
  }
  
  protected void checkTarget() {
    if (!this.worldObj.isRemote) {
      this.dataWatcher.updateObject(26, Integer.valueOf(this.AnimLifeTime));
      this.dataWatcher.updateObject(27, Float.valueOf(this.health));
      if (this.target != null) {
        this.dataWatcher.updateObject(25, Integer.valueOf(this.target.getEntityId()));
      } else {
        this.dataWatcher.updateObject(25, Integer.valueOf(-1));
        if (this.targetUUID != null)
          this.target = MFQM.getEntityByUUID(this.worldObj, this, this.targetUUID, 32.0D); 
      } 
    } else {
      this.health = this.dataWatcher.getWatchableObjectFloat(27);
      this.target = this.worldObj.getEntityByID(this.dataWatcher.getWatchableObjectInt(25));
      if (this.AnimLifeTime < 10000)
        if (this.target != null) {
          this.AnimLifeTime = this.dataWatcher.getWatchableObjectInt(26);
          setSize(this.target.width + 0.1F, 1.0F);
        } else {
          this.AnimLifeTime = 10000 + Math.max(100 - this.AnimLifeTime, 0);
        }  
    } 
  }
  
  public void CheckInQSBlock() {
    if (this.worldObj.getBlock((int)Math.floor(this.posX), (int)Math.floor(this.posY - 0.5D), (int)Math.floor(this.posZ)) != MFQM.SwFleshBlock && 
      this.worldObj.getBlock((int)Math.floor(this.posX), (int)Math.floor(this.posY + 0.5D), (int)Math.floor(this.posZ)) != MFQM.SwFleshBlock) {
      setDead();
      return;
    } 
  }
  
  public void ManipulateWithTerrain() {}
  
  public boolean canBeCollidedWith() {
    return true;
  }
  
  public void writeSpawnData(ByteBuf data) {
    data.writeInt(this.high);
    data.writeInt(this.AnimLifeTime);
    data.writeInt((this.target != null) ? this.target.getEntityId() : 0);
    data.writeFloat(this.health);
  }
  
  public void readSpawnData(ByteBuf data) {
    this.high = data.readInt();
    this.AnimLifeTime = data.readInt();
    this.target = this.worldObj.getEntityByID(data.readInt());
    this.health = data.readFloat();
  }
  
  @SideOnly(Side.CLIENT)
  public boolean isInRangeToRenderDist(double par1) {
    double d1 = this.boundingBox.getAverageEdgeLength() * 4.0D;
    d1 *= 64.0D;
    return (par1 < d1 * d1);
  }
  
  public void CheckDead() {
    if (this.AnimLifeTime < 10000 && 
      this.worldObj.getTotalWorldTime() % 32L == 0L) {
      this.target = null;
      this.AnimLifeTime = 10000 + Math.max(100 - this.AnimLifeTime, 0);
    } 
  }
  
  public void DeadEffect() {
    for (int var1 = 0; var1 < 5; var1++) {
      double var8 = this.rand.nextGaussian() * 0.02D;
      double var4 = this.rand.nextGaussian() * 0.02D;
      double var6 = this.rand.nextGaussian() * 0.02D;
      this.worldObj.spawnParticle("explode", this.posX + (this.rand.nextFloat() * this.width * 2.0F) - this.width, (this.high + 0.5F) + (this.rand.nextFloat() * this.height), this.posZ + (this.rand.nextFloat() * this.width * 2.0F) - this.width, var8, var4, var6);
    } 
    setDead();
  }
  
  public void onUpdate() {
    super.onUpdate();
    this.motionX = 0.0D;
    this.motionY = 0.0D;
    this.motionZ = 0.0D;
    int lastTick = this.AnimLifeTime;
    this.AnimLifeTime++;
    int Ticker = 32;
    if (this.target == null)
      Ticker = 16; 
    if ((!this.worldObj.isRemote && this.targetUpdate) || this.worldObj.getTotalWorldTime() % Ticker == 0L) {
      checkTarget();
      this.targetUpdate = false;
    } 
    if (this.AnimLifeTime > 10100) {
      setDead();
      return;
    } 
    if (this.posY > this.high)
      this.posY = this.high; 
    if (this.worldObj.isRemote) {
      if (this.health < 0.01F) {
        DeadEffect();
        return;
      } 
    } else {
      if (this.health < 0.01F) {
        this.health--;
        this.target = null;
        this.AnimLifeTime = 10099;
        if (this.health < -50.0F)
          setDead(); 
        return;
      } 
      if (this.AnimLifeTime < 10000 && 
        this.maxHurtResistantTime > 0)
        this.maxHurtResistantTime--; 
      CheckInQSBlock();
      boolean Brak = false;
      String reason = "";
      if (this.target != null) {
        if (this.target instanceof EntityLivingBase) {
          if (this.target.isDead || !this.target.isEntityAlive()) {
            reason = "Dead target";
            Brak = true;
          } else if (this.worldObj.getTotalWorldTime() % 32L == 0L) {
            PotionEffect PEWeak = ((EntityLivingBase)this.target).getActivePotionEffect(Potion.weakness);
            PotionEffect PESlow = ((EntityLivingBase)this.target).getActivePotionEffect(Potion.digSlowdown);
            int weakLevel = -1;
            int slowLevel = -1;
            if (PEWeak != null)
              weakLevel = PEWeak.getAmplifier(); 
            if (PESlow != null)
              slowLevel = PESlow.getAmplifier(); 
            if (weakLevel > -1 && slowLevel > 2 && 
              this.AnimLifeTime < 60) {
              Brak = true;
              reason = "Already caught";
            } 
          } 
        } else {
          Brak = true;
          reason = "Not living base";
        } 
      } else {
        Brak = true;
        reason = "No target";
      } 
      if (Brak) {
        CheckDead();
        return;
      } 
    } 
    if (this.target != null) {
      double xx = this.posX;
      double zz = this.posZ;
      double yy = Math.min(this.posY, this.high);
      double yc = 0.0D;
      double power = 1.0D + (Math.min(this.AnimLifeTime, 500) - 60) / 100.0D;
      double moveC = 100.0D;
      double Sinker = 0.0D;
      if (this.AnimLifeTime > 60) {
        moveC = 200.0D;
        if (this.worldObj.getTotalWorldTime() % 64L == 0L && 
          this.worldObj.rand.nextInt(10) == 0)
          this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "mob.magmacube.jump", 0.25F, 0.25F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.4F); 
        if (this.worldObj.getTotalWorldTime() % 64L == 0L) {
          this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "mob.silverfish.step", 0.25F, this.worldObj.rand.nextFloat() * 0.25F + 0.1F);
          this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "mob.silverfish.step", 0.25F, this.worldObj.rand.nextFloat() * 0.25F + 0.1F);
        } 
      } else if (this.worldObj.getTotalWorldTime() % 64L == 0L) {
        this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "mob.silverfish.step", 0.15F, this.worldObj.rand.nextFloat() * 0.25F + 0.5F);
      } 
      if (this.AnimLifeTime > 100 && 
        this.high - this.target.posY + yc < 1.0D)
        Sinker = 0.0025D; 
      if (this.worldObj.isRemote && 
        this.target == (Minecraft.getMinecraft()).thePlayer)
        yc = -1.6D; 
      double disq = Math.pow(Math.pow(this.target.posX - xx, 2.0D) + Math.pow(this.target.posZ - zz, 2.0D), 0.5D);
      if (disq < 2.0D) {
        if (this.target.posY + yc <= this.high + 1.0D && this.high - this.target.posY + yc < 2.0D) {
          if (disq > 0.25D) {
            xx = (this.target.posX - this.posX) / moveC + this.posX;
            zz = (this.target.posZ - this.posZ) / moveC + this.posZ;
          } 
          yy = Math.min(Math.min(0.0D, (this.target.posY + yc - this.posY) / moveC) + Math.max(0.0D, (this.target.posY + yc - this.posY) / moveC * 2.0D) + this.posY, this.high) - Sinker;
          if (disq > 0.75D) {
            if (this.AnimLifeTime == 59)
              this.AnimLifeTime = 59; 
            CheckDead();
          } else {
            if (!this.worldObj.isRemote) {
              if (this.AnimLifeTime >= 60) {
                if (this.AnimLifeTime == 60)
                  this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "mob.silverfish.step", 0.25F, this.worldObj.rand.nextFloat() * 0.25F + 0.1F); 
                int slowLevel = (int)Math.floor(power * 16.0D);
                int weakLevel = (int)Math.floor(power * 4.0D);
                if (this.worldObj.getTotalWorldTime() % 32L == 0L) {
                  ((EntityLivingBase)this.target).addPotionEffect(new PotionEffect(4, 100, Math.min(slowLevel + 1, 255), false));
                  ((EntityLivingBase)this.target).addPotionEffect(new PotionEffect(18, 100, Math.min(weakLevel + 1, 255), false));
                } 
              } 
              ManipulateWithTerrain();
            } 
            if (this.AnimLifeTime > 60) {
              double apower = Math.max(100.0D / power, 10.0D);
              if (disq <= 0.75D && 
                disq >= 0.075D)
                if (this.worldObj.isRemote) {
                  if (this.target == (Minecraft.getMinecraft()).thePlayer) {
                    this.target.motionX += (xx - this.target.posX) / apower;
                    this.target.motionZ += (zz - this.target.posZ) / apower;
                  } 
                } else {
                  this.target.motionX += (xx - this.target.posX) / Math.max(apower / 2.0D, 1.0D);
                  this.target.motionZ += (zz - this.target.posZ) / Math.max(apower / 2.0D, 1.0D);
                }  
              this.target.motionY += -0.01D;
              if (Math.abs(yy - this.target.posY + yc) > 0.075D)
                if (this.worldObj.isRemote) {
                  if (this.target == (Minecraft.getMinecraft()).thePlayer)
                    this.target.motionY += (yy - this.target.posY + yc) / Math.max(apower / 2.0D, 1.0D); 
                } else {
                  this.target.motionY += -0.05D + (yy - this.target.posY + yc) / Math.max(apower / 4.0D, 1.0D);
                }  
              if (this.AnimLifeTime > 1000)
                this.AnimLifeTime = 500; 
            } 
          } 
        } else {
          this.target = null;
          CheckDead();
        } 
      } else {
        CheckDead();
      } 
      setPosition(xx, yy, zz);
    } 
  }
  
  public float getEyeHeight() {
    return 1.5F;
  }
  
  public boolean attackEntityFrom(DamageSource dmgs, float amount) {
    if (this.worldObj.isRemote)
      return false; 
    if (this.health > 0.0F && 
      this.maxHurtResistantTime < 1) {
      this.health -= amount;
      if (this.health <= 0.01F) {
        playSound("game.neutral.die", 1.0F, (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F + 1.0F);
        playSound("mob.silverfish.kill", 1.0F, (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F + 1.75F);
        playSound("mob.silverfish.step", 0.5F, this.worldObj.rand.nextFloat() * 0.25F + 0.5F);
        this.health = 0.0F;
        checkTarget();
      } else {
        playSound("game.neutral.hurt", 1.0F, (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F + 1.0F);
      } 
      this.maxHurtResistantTime = 20;
      return true;
    } 
    return false;
  }
  
  public void moveEntity(double p_70091_1_, double p_70091_3_, double p_70091_5_) {}
  
  public void writeEntityToNBT(NBTTagCompound nbtt) {
    nbtt.setInteger("high", this.high);
    nbtt.setInteger("TickHere", this.AnimLifeTime);
    if (this.target != null) {
      nbtt.setLong("TargetMSB", this.target.getUniqueID().getMostSignificantBits());
      nbtt.setLong("TargetLSB", this.target.getUniqueID().getLeastSignificantBits());
    } else {
      nbtt.setLong("TargetMSB", 0L);
      nbtt.setLong("TargetLSB", 0L);
    } 
    nbtt.setFloat("FKHealth", this.health);
  }
  
  public void readEntityFromNBT(NBTTagCompound nbtt) {
    this.high = nbtt.getInteger("high");
    this.AnimLifeTime = nbtt.getInteger("TickHere");
    this.targetUUID = new UUID(nbtt.getLong("TargetMSB"), nbtt.getLong("TargetLSB"));
    this.health = nbtt.getFloat("FKHealth");
  }
  
  @SideOnly(Side.CLIENT)
  public float getShadowSize() {
    return 0.0F;
  }
}
