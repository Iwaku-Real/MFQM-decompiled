package MoreFunQuicksandMod.main.entity;

import MoreFunQuicksandMod.main.MFQM;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.Chunk;

public class EntitySandBlob extends EntityLiving implements IMob {
  private ItemStack[] inv = new ItemStack[5];
  
  public float squishAmount;
  
  public float squishFactor;
  
  public float prevSquishFactor;
  
  public float deepFactor;
  
  public float TdeepFactor;
  
  public float pullTime;
  
  public float antiSit = 0.5F;
  
  private int slimeJumpDelay;
  
  public EntitySandBlob(World p_i1742_1_) {
    super(p_i1742_1_);
    this.yOffset = 0.0F;
    this.deepFactor = 0.0F;
    this.TdeepFactor = 0.0F;
    this.pullTime = 0.0F;
    this.slimeJumpDelay = this.rand.nextInt(20) + 10;
    setSlimeSize(3);
  }
  
  protected void entityInit() {
    super.entityInit();
    this.dataWatcher.addObject(16, new Byte((byte)1));
    this.dataWatcher.addObject(15, Float.valueOf(0.0F));
  }
  
  public void writeEntityToNBT(NBTTagCompound nbtt) {
    super.writeEntityToNBT(nbtt);
    nbtt.setInteger("Size", getSlimeSize() - 1);
  }
  
  public void readEntityFromNBT(NBTTagCompound nbtt) {
    super.readEntityFromNBT(nbtt);
    int var0 = nbtt.getInteger("Size");
    if (var0 < 0)
      var0 = 0; 
    setSlimeSize(var0 + 1);
  }
  
  protected void syncronizeDepth() {
    if (!this.worldObj.isRemote) {
      this.dataWatcher.updateObject(15, Float.valueOf(this.TdeepFactor));
    } else {
      this.TdeepFactor = this.dataWatcher.getWatchableObjectFloat(15);
    } 
  }
  
  protected void setSlimeSize(int p_70799_1_) {
    this.dataWatcher.updateObject(16, new Byte((byte)p_70799_1_));
    this.dataWatcher.updateObject(15, Float.valueOf(this.TdeepFactor));
    setSize(0.5F * p_70799_1_, 0.5F * p_70799_1_);
    setPosition(this.posX, this.posY, this.posZ);
    getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(getSpawnHp());
    setHealth(getMaxHealth());
    this.experienceValue = p_70799_1_ + 1;
  }
  
  public int getSlimeSize() {
    return 3;
  }
  
  protected String getJumpSound() {
    return "mob.slime.big";
  }
  
  protected EntitySandBlob createInstance() {
    return new EntitySandBlob(this.worldObj);
  }
  
  public void setDead() {
    super.setDead();
  }
  
  protected boolean canDamagePlayer() {
    return true;
  }
  
  protected float getSoundVolume() {
    return 0.25F;
  }
  
  public int getVerticalFaceSpeed() {
    return 0;
  }
  
  protected boolean makesSoundOnJump() {
    return true;
  }
  
  protected boolean makesSoundOnLand() {
    return true;
  }
  
  protected String getHurtSound() {
    return "mob.slime.big";
  }
  
  protected String getDeathSound() {
    return "mob.slime.big";
  }
  
  public void onUpdate() {
    if (!this.worldObj.isRemote && this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL)
      this.isDead = true; 
    if (isWet()) {
      attackEntityFrom(DamageSource.drown, 10.0F);
    } else {
      for (int var3 = 0; var3 < 2; var3++) {
        float var4 = this.rand.nextFloat() * 3.1415927F * 2.0F;
        float var5 = this.rand.nextFloat() * 0.5F + 0.5F;
        float var6 = MathHelper.sin(var4) * 2.0F * 0.5F * var5;
        float var7 = MathHelper.cos(var4) * 2.0F * 0.5F * var5;
        this.worldObj.spawnParticle("blockcrack_" + Block.getIdFromBlock((Block)Blocks.sand) + "_" + Character.MIN_VALUE, this.posX + var6, this.boundingBox.minY + 0.5D, this.posZ + var7, 0.0D, 0.0D, 0.0D);
      } 
    } 
    extinguish();
    this.squishFactor += (this.squishAmount - this.squishFactor) * 0.5F;
    this.prevSquishFactor = this.squishFactor;
    boolean var1 = this.onGround;
    super.onUpdate();
    if (this.onGround && !var1) {
      int var2 = getSlimeSize();
      for (int var3 = 0; var3 < var2 * 16; var3++) {
        float var4 = this.rand.nextFloat() * 3.1415927F * 2.0F;
        float var5 = this.rand.nextFloat() * 0.5F + 0.5F;
        float var6 = MathHelper.sin(var4) * var2 * 0.5F * var5;
        float var7 = MathHelper.cos(var4) * var2 * 0.5F * var5;
        this.worldObj.spawnParticle("blockcrack_" + Block.getIdFromBlock((Block)Blocks.sand) + "_" + Character.MIN_VALUE, this.posX + var6, this.boundingBox.minY, this.posZ + var7, 0.0D, 0.0D, 0.0D);
      } 
      if (makesSoundOnLand()) {
        playSound(getJumpSound(), getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
        playSound("step.grass", 0.5F, this.rand.nextFloat() * 0.2F + 0.1F);
        playSound("step.grass", 0.5F, this.rand.nextFloat() * 0.2F + 0.1F);
      } 
      this.squishAmount = -0.5F;
    } else if (!this.onGround && var1) {
      this.squishAmount = 1.0F;
    } 
    if (this.onGround && 
      this.worldObj.getTotalWorldTime() % 16L == 0L && 
      this.worldObj.rand.nextInt(10) == 0) {
      this.squishAmount = 2.0F;
      playSound("step.grass", 0.5F, this.rand.nextFloat() * 0.2F + 0.1F);
    } 
    alterSquishAmount();
    if (this.riddenByEntity == null) {
      if (this.deepFactor > 0.0F)
        this.TdeepFactor -= 0.5F; 
    } else {
      if (!this.worldObj.isRemote) {
        if (!(this.riddenByEntity instanceof EntityPlayer)) {
          this.riddenByEntity.mountEntity(null);
          return;
        } 
        if (((EntityPlayer)this.riddenByEntity).capabilities.disableDamage) {
          ((EntityPlayer)this.riddenByEntity).mountEntity(null);
          return;
        } 
      } 
      if (this.worldObj.getTotalWorldTime() % 2L == 0L && 
        this.rand.nextInt(5) == 0)
        playSound("step.grass", 0.25F, this.rand.nextFloat() * 0.5F + 0.5F); 
      if (this.deepFactor < 12.5F) {
        this.TdeepFactor += 0.02F;
        if (this.pullTime-- <= 0.0F) {
          this.pullTime = (20 + this.rand.nextInt(10));
          this.squishAmount = 1.5F;
          this.TdeepFactor += 0.75F;
          playSound("step.sand", 0.25F, this.rand.nextFloat() * 0.2F + 0.2F);
          playSound("mob.magmacube.jump", 0.25F, 0.25F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
          playSound("mob.magmacube.jump", 0.25F, 0.25F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
          playSound("mob.magmacube.jump", 0.25F, 0.25F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
          playSound("step.grass", 0.25F, this.rand.nextFloat() * 0.5F + 0.5F);
        } 
      } else if (!this.worldObj.isRemote && 
        this.worldObj.getTotalWorldTime() % 4L == 0L && this.rand.nextInt(5) == 0) {
        this.riddenByEntity.attackEntityFrom(DamageSource.inWall, ((EntityLivingBase)this.riddenByEntity).getMaxHealth() * 0.1F);
      } 
    } 
    if (Math.abs(this.deepFactor - this.TdeepFactor) > 0.1D)
      this.deepFactor += (this.TdeepFactor - this.deepFactor) / 10.0F; 
    if (this.worldObj.isRemote) {
      int var2 = getSlimeSize();
      setSize(0.6F * var2, 0.6F * var2);
    } 
  }
  
  public void updateRiderPosition() {
    if (this.riddenByEntity != null)
      this.riddenByEntity.setPosition(this.posX, this.posY + getMountedYOffset() + this.antiSit + this.riddenByEntity.getYOffset() - Math.min(1.25D, Math.max(0.0F, this.deepFactor / 10.0F)) * 1.25D, this.posZ); 
  }
  
  protected void updateEntityActionState() {
    despawnEntity();
    EntityPlayer var1 = null;
    if (this.riddenByEntity == null)
      var1 = this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 16.0D); 
    if (var1 != null)
      faceEntity((Entity)var1, 10.0F, 20.0F); 
    if (this.onGround && this.slimeJumpDelay-- <= 0) {
      this.slimeJumpDelay = getJumpDelay();
      if (var1 != null)
        this.slimeJumpDelay /= 10; 
      if (this.riddenByEntity == null) {
        this.isJumping = true;
        if (makesSoundOnJump()) {
          playSound(getJumpSound(), getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 0.8F);
          playSound("dig.sand", 0.5F, this.rand.nextFloat() * 0.5F + 0.5F);
        } 
        this.moveStrafing = 1.0F - this.rand.nextFloat() * 2.0F;
        this.moveForward = (1 * getSlimeSize());
      } else {
        this.slimeJumpDelay = 10;
      } 
    } else {
      this.isJumping = false;
      if (this.onGround)
        this.moveStrafing = this.moveForward = 0.0F; 
    } 
  }
  
  public void onCollideWithPlayer(EntityPlayer p_70100_1_) {
    if (p_70100_1_.capabilities.disableDamage)
      return; 
    if (canDamagePlayer()) {
      int var2 = getSlimeSize();
      float opMul = Math.max(p_70100_1_.getMaxHealth() / 20.0F, 1.0F);
      if (!(p_70100_1_.ridingEntity instanceof EntityVoreSlime) && !(p_70100_1_.ridingEntity instanceof EntityMuddyBlob) && !(p_70100_1_.ridingEntity instanceof EntityTarSlime) && !(p_70100_1_.ridingEntity instanceof EntitySandBlob)) {
        float sizeCoof = 2.0F;
        if (canEntityBeSeen((Entity)p_70100_1_) && getDistanceSqToEntity((Entity)p_70100_1_) < var2 * sizeCoof) {
          boolean inv = false;
          if (this.riddenByEntity == null)
            if (p_70100_1_.getHealth() < 10.0F * opMul) {
              inv = true;
              if (this.rand.nextInt(10) == 0)
                p_70100_1_.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), getAttackStrength()); 
            } else {
              inv = p_70100_1_.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), getAttackStrength());
            }  
          if (inv) {
            if (!isInsideOfMaterial(Material.water)) {
              float hp = p_70100_1_.getHealth() / opMul / 3.0F;
              if (hp < 1.0F || this.rand.nextInt((int)Math.floor(Math.max(1.0F, hp))) == 0) {
                this.riddenByEntity = (Entity)p_70100_1_;
                p_70100_1_.mountEntity((Entity)this);
                if (this.rand.nextInt(5) == 0) {
                  playSound("mob.slime.attack", 0.25F, this.rand.nextFloat() * 0.25F + 0.25F);
                  this.TdeepFactor += 2.0F;
                } 
                syncronizeDepth();
              } 
            } 
            playSound("mob.attack", 0.25F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            playSound("step.sand", 0.25F, this.rand.nextFloat() * 0.5F + 0.5F);
          } 
          return;
        } 
      } 
    } 
  }
  
  public boolean attackEntityFrom(DamageSource dmgs, float power) {
    Entity ply = dmgs.getEntity();
    power = Math.max(power * 0.2F, 1.0F);
    if (dmgs == DamageSource.inFire || dmgs == DamageSource.onFire || dmgs == DamageSource.lava)
      power *= 0.5F; 
    if (this.riddenByEntity == null && 
      ply != null && 
      ply instanceof EntityPlayer) {
      Entity ent = dmgs.getSourceOfDamage();
      if (ent == ply && ply.isEntityAlive() && ply.distanceWalkedModified > 0.0F && 
        getHealth() > power && 
        !this.worldObj.isRemote && 
        this.rand.nextInt(5) == 0) {
        EntitySandBlob sandBlob = new EntitySandBlob(this.worldObj);
        sandBlob.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
        double xv = this.worldObj.rand.nextFloat() * 0.5D - 0.25D;
        double zv = this.worldObj.rand.nextFloat() * 0.5D - 0.25D;
        double yv = this.worldObj.rand.nextFloat() * 0.25D + 0.25D;
        this.motionX = xv;
        this.motionY = yv;
        this.motionZ = zv;
        power = 0.0F;
        setHealth(getMaxHealth());
        this.squishAmount = 2.0F;
        playSound("mob.slime.attack", 0.25F, this.rand.nextFloat() * 0.25F + 0.25F);
        playSound("step.sand", 1.0F, this.rand.nextFloat() * 0.25F + 0.25F);
        playSound("step.sand", 1.0F, this.rand.nextFloat() * 0.25F + 0.25F);
        playSound("step.grass", 0.25F, this.rand.nextFloat() * 0.5F + 0.5F);
        sandBlob.motionX = -xv;
        sandBlob.motionY = yv;
        sandBlob.motionZ = -zv;
        sandBlob.squishAmount = 2.0F;
        this.worldObj.spawnEntityInWorld((Entity)sandBlob);
        return true;
      } 
    } 
    boolean Result = super.attackEntityFrom(dmgs, power);
    if (Result)
      playSound("dig.sand", 0.5F, this.rand.nextFloat() * 0.5F + 0.5F); 
    return Result;
  }
  
  protected void dropFewItems(boolean par1, int par2) {
    int var3 = this.rand.nextInt(2) + this.rand.nextInt(1 + par2);
    int var4;
    for (var4 = 2; var4 < var3 * 2; var4++)
      entityDropItem(new ItemStack(Items.slime_ball, 1, 0), 0.0F); 
    var3 = this.rand.nextInt(2) + this.rand.nextInt(1 + par2);
    for (var4 = 1; var4 < var3; var4++)
      entityDropItem(new ItemStack(MFQM.SoftQuicksandBlock, 1, 0), 0.0F); 
    var3 = this.rand.nextInt(2) + this.rand.nextInt(1 + par2);
    for (var4 = 1; var4 < var3 * 3; var4++)
      entityDropItem(new ItemStack((Block)Blocks.sand, 1, 0), 0.0F); 
  }
  
  protected Item getDropItem() {
    return Items.slime_ball;
  }
  
  protected boolean canDespawn() {
    return true;
  }
  
  public boolean getCanSpawnHere() {
    if (this.worldObj.provider.dimensionId != 0)
      return false; 
    Chunk var1 = this.worldObj.getChunkFromBlockCoords(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posZ));
    if (this.worldObj.getWorldInfo().getTerrainType() == WorldType.FLAT)
      return false; 
    if (this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL)
      if (this.posY > 50.0D && this.posY < 70.0D)
        if (this.rand.nextInt(2) == 0)
          return super.getCanSpawnHere();   
    return false;
  }
  
  protected int getAttackStrength() {
    return 1;
  }
  
  protected int getSpawnHp() {
    return 15;
  }
  
  protected void alterSquishAmount() {
    this.squishAmount *= 0.85F;
  }
  
  protected String getSlimeParticle() {
    return "splash";
  }
  
  protected int getJumpDelay() {
    return this.rand.nextInt(40) + 100;
  }
  
  public boolean shouldRiderSit() {
    return false;
  }
}
