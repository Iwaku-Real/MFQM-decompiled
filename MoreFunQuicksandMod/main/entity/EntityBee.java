package MoreFunQuicksandMod.main.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityBee extends EntityMob {
  public int courseChangeCooldown;
  
  public double waypointX;
  
  public double waypointY;
  
  public double waypointZ;
  
  private Entity targetedEntity;
  
  private int aggroCooldown;
  
  public EntityBee(World world) {
    super(world);
    setSize(0.95F, 0.95F);
  }
  
  protected void fall(float par1) {}
  
  protected void updateFallState(double par1, boolean par3) {}
  
  public void moveEntityWithHeading(float par1, float par2) {
    if (isInWater()) {
      moveFlying(par1, par2, 0.02F);
      moveEntity(this.motionX, this.motionY, this.motionZ);
      this.motionX *= 0.800000011920929D;
      this.motionY *= 0.800000011920929D;
      this.motionZ *= 0.800000011920929D;
    } else if (handleLavaMovement()) {
      moveFlying(par1, par2, 0.02F);
      moveEntity(this.motionX, this.motionY, this.motionZ);
      this.motionX *= 0.5D;
      this.motionY *= 0.5D;
      this.motionZ *= 0.5D;
    } else {
      float f2 = 0.91F;
      if (this.onGround) {
        f2 = 0.54600006F;
        Block block = this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ));
        f2 = block.slipperiness * 0.91F;
      } 
      float f3 = 0.16277136F / f2 * f2 * f2;
      moveFlying(par1, par2, this.onGround ? (0.1F * f3) : 0.02F);
      f2 = 0.91F;
      if (this.onGround) {
        f2 = 0.54600006F;
        Block block = this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ));
        f2 = block.slipperiness * 0.91F;
      } 
      moveEntity(this.motionX, this.motionY, this.motionZ);
      this.motionX *= f2;
      this.motionY *= f2;
      this.motionZ *= f2;
    } 
    this.prevLimbSwingAmount = this.limbSwingAmount;
    double d0 = this.posX - this.prevPosX;
    double d1 = this.posZ - this.prevPosZ;
    float f4 = MathHelper.sqrt_double(d0 * d0 + d1 * d1) * 4.0F;
    if (f4 > 1.0F)
      f4 = 1.0F; 
    this.limbSwingAmount += (f4 - this.limbSwingAmount) * 0.4F;
    this.limbSwing += this.limbSwingAmount;
  }
  
  public boolean isOnLadder() {
    return false;
  }
  
  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
    getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.25D);
  }
  
  protected void updateEntityActionState() {
    if (!this.worldObj.isRemote && this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL)
      setDead(); 
    despawnEntity();
    double d0 = this.waypointX - this.posX;
    double d1 = this.waypointY - this.posY;
    double d2 = this.waypointZ - this.posZ;
    double d3 = d0 * d0 + d1 * d1 + d2 * d2;
    if (d3 < 1.0D || d3 > 1800.0D) {
      this.waypointX = this.posX + ((this.rand.nextFloat() * 2.0F - 1.0F) * 4.0F);
      this.waypointY = this.posY + ((this.rand.nextFloat() * 2.0F - 1.0F) * 4.0F);
      this.waypointZ = this.posZ + ((this.rand.nextFloat() * 2.0F - 1.0F) * 4.0F);
    } 
    if (this.courseChangeCooldown-- <= 0) {
      this.courseChangeCooldown += this.rand.nextInt(2) + 2;
      d3 = MathHelper.sqrt_double(d3);
      if (isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, d3)) {
        this.motionX += d0 / d3 * 0.1D;
        this.motionY += d1 / d3 * 0.1D;
        this.motionZ += d2 / d3 * 0.1D;
      } else {
        this.waypointX = this.posX;
        this.waypointY = this.posY;
        this.waypointZ = this.posZ;
      } 
    } 
    if (this.targetedEntity != null && this.targetedEntity.isDead)
      this.targetedEntity = null; 
    if (this.targetedEntity == null || this.aggroCooldown-- <= 0) {
      this.targetedEntity = (Entity)this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 16.0D);
      if (this.targetedEntity != null)
        this.aggroCooldown = 20; 
    } 
    double d4 = 16.0D;
    if (this.targetedEntity != null && this.targetedEntity.getDistanceSqToEntity((Entity)this) < d4 * d4) {
      double d5 = this.targetedEntity.posX - this.posX;
      double d6 = this.targetedEntity.boundingBox.minY + this.targetedEntity.height - this.posY;
      double d7 = this.targetedEntity.posZ - this.posZ;
      this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(d5, d7)) * 180.0F / 3.1415927F;
      if (canEntityBeSeen(this.targetedEntity)) {
        this.waypointX = this.targetedEntity.posX;
        this.waypointY = this.targetedEntity.posY;
        this.waypointZ = this.targetedEntity.posZ;
        float f1 = this.targetedEntity.getDistanceToEntity((Entity)this);
        attackEntity(this.targetedEntity, f1);
      } 
    } else {
      this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / 3.1415927F;
    } 
  }
  
  private boolean isCourseTraversable(double par1, double par3, double par5, double par7) {
    double d4 = (this.waypointX - this.posX) / par7;
    double d5 = (this.waypointY - this.posY) / par7;
    double d6 = (this.waypointZ - this.posZ) / par7;
    AxisAlignedBB axisalignedbb = this.boundingBox.copy();
    for (int i = 1; i < par7; i++) {
      axisalignedbb.offset(d4, d5, d6);
      if (!this.worldObj.getCollidingBoundingBoxes((Entity)this, axisalignedbb).isEmpty())
        return false; 
    } 
    return true;
  }
  
  protected float getSoundVolume() {
    return 0.5F;
  }
  
  protected String getLivingSound() {
    return "morefunquicksandmod:mob.bee.say";
  }
  
  protected String getHurtSound() {
    return "morefunquicksandmod:mob.bee.hurt";
  }
  
  protected String getDeathSound() {
    return "morefunquicksandmod:mob.bee.hurt";
  }
}
