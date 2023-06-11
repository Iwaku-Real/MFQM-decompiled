package MoreFunQuicksandMod.main.entity;

import MoreFunQuicksandMod.main.CustomVarPlayer;
import MoreFunQuicksandMod.main.MFQM;
import com.google.common.collect.Multimap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.Chunk;

public class EntityMuddyBlob extends EntityLiving implements IMob {
  private ItemStack[] inv = new ItemStack[5];
  
  public float squishAmount;
  
  public float squishFactor;
  
  public float prevSquishFactor;
  
  public float deepFactor;
  
  public float TdeepFactor;
  
  public float pullTime;
  
  public float antiSit = 0.5F;
  
  private int slimeJumpDelay;
  
  public boolean handleWaterMovement() {
    return false;
  }
  
  public EntityMuddyBlob(World p_i1742_1_) {
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
  
  public int getSizeInventory() {
    return 3;
  }
  
  public void writeEntityToNBT(NBTTagCompound nbtt) {
    super.writeEntityToNBT(nbtt);
    nbtt.setInteger("Size", getSlimeSize() - 1);
    NBTTagList var2 = new NBTTagList();
    for (int var3 = 0; var3 < this.inv.length; var3++) {
      if (this.inv[var3] != null) {
        NBTTagCompound var4 = new NBTTagCompound();
        var4.setByte("Slot", (byte)var3);
        this.inv[var3].writeToNBT(var4);
        var2.appendTag((NBTBase)var4);
      } 
    } 
    nbtt.setTag("Items", (NBTBase)var2);
  }
  
  public void readEntityFromNBT(NBTTagCompound nbtt) {
    super.readEntityFromNBT(nbtt);
    int var0 = nbtt.getInteger("Size");
    if (var0 < 0)
      var0 = 0; 
    setSlimeSize(var0 + 1);
    NBTTagList var2 = nbtt.getTagList("Items", 10);
    this.inv = new ItemStack[getSizeInventory()];
    for (int var3 = 0; var3 < var2.tagCount(); var3++) {
      NBTTagCompound var4 = var2.getCompoundTagAt(var3);
      int var5 = var4.getByte("Slot") & 0xFF;
      if (var5 >= 0 && var5 < this.inv.length)
        this.inv[var5] = ItemStack.loadItemStackFromNBT(var4); 
    } 
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
    setSize(0.6F * p_70799_1_, 0.6F * p_70799_1_);
    setPosition(this.posX, this.posY, this.posZ);
    getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(getSpawnHp());
    setHealth(getMaxHealth());
    this.experienceValue = p_70799_1_ + 2;
  }
  
  public int getSlimeSize() {
    return 3;
  }
  
  protected String getJumpSound() {
    return "mob.slime.big";
  }
  
  protected EntityMuddyBlob createInstance() {
    return new EntityMuddyBlob(this.worldObj);
  }
  
  public void setDead() {
    super.setDead();
  }
  
  protected boolean canDamagePlayer() {
    return true;
  }
  
  protected float getSoundVolume() {
    return 1.2F;
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
        this.worldObj.spawnParticle("blockcrack_" + Block.getIdFromBlock(MFQM.BogBlock) + "_" + Character.MIN_VALUE, this.posX + var6, this.boundingBox.minY, this.posZ + var7, 0.0D, 0.0D, 0.0D);
      } 
      if (makesSoundOnLand())
        playSound(getJumpSound(), getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F); 
      this.squishAmount = -0.5F;
    } else if (!this.onGround && var1) {
      this.squishAmount = 1.0F;
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
        ((EntityLivingBase)this.riddenByEntity).addPotionEffect(new PotionEffect(4, 100, 10, false));
        ((EntityLivingBase)this.riddenByEntity).addPotionEffect(new PotionEffect(18, 100, 10, false));
        ((EntityLivingBase)this.riddenByEntity).addPotionEffect(new PotionEffect(4, 100, 2, false));
        ((EntityLivingBase)this.riddenByEntity).addPotionEffect(new PotionEffect(18, 100, 2, false));
        ((EntityLivingBase)this.riddenByEntity).addPotionEffect(new PotionEffect(2, 100, 0, false));
        if (this.riddenByEntity.posY > this.posY + 0.25D)
          checkPlayerMuddy((EntityPlayer)this.riddenByEntity, 0.0D, this.posY + getMountedYOffset() - 0.5D, 0.0D, this.worldObj); 
      } 
      if (this.worldObj.getTotalWorldTime() % 8L == 0L && 
        this.rand.nextInt(5) == 0)
        playSound("mob.silverfish.step", 0.4F, this.rand.nextFloat() * 0.15F + 0.1F); 
      if (this.deepFactor < 12.5F) {
        this.TdeepFactor += 0.015F;
        if (this.pullTime-- <= 0.0F) {
          this.pullTime = (35 + this.rand.nextInt(10));
          this.squishAmount = 1.5F;
          this.TdeepFactor += 1.45F;
          playSound("mob.magmacube.jump", 0.25F, 0.25F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
          playSound("mob.magmacube.jump", 0.25F, 0.25F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
          playSound("mob.magmacube.jump", 0.25F, 0.25F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
          playSound("mob.magmacube.jump", 0.25F, 0.25F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
        } 
      } else if (!this.worldObj.isRemote && 
        this.worldObj.getTotalWorldTime() % 4L == 0L && this.rand.nextInt(5) == 0) {
        this.riddenByEntity.attackEntityFrom(DamageSource.drown, ((EntityLivingBase)this.riddenByEntity).getMaxHealth() * 0.1F);
      } 
    } 
    if (Math.abs(this.deepFactor - this.TdeepFactor) > 0.1D)
      this.deepFactor += (this.TdeepFactor - this.deepFactor) / 10.0F; 
    if (this.worldObj.isRemote) {
      int var2 = getSlimeSize();
      setSize(0.6F * var2, 0.6F * var2);
    } 
  }
  
  public void checkPlayerMuddy(EntityPlayer ent, double x, double y, double z, World world) {
    if (!MFQM.QSCover)
      return; 
    if (!world.isRemote) {
      CustomVarPlayer props = CustomVarPlayer.get(ent);
      int preML = MFQM.getMuddyLevel(ent, y, world);
      if (preML > props.getMuddyLevel() * props.getMuddyTime() / 1000.0F) {
        props.setMuddyLevel(preML);
        int mdtp = MFQM.getMuddyType(MFQM.MireBlock);
        props.setMuddyType(mdtp);
        if (props.getMuddyTime() < 5000)
          props.addMuddyTime(100); 
        props.setMuddyTime(Math.min(props.getMuddyTime(), 1000));
      } 
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
        this.slimeJumpDelay /= 3; 
      if (this.riddenByEntity == null) {
        this.isJumping = true;
        if (makesSoundOnJump())
          playSound(getJumpSound(), getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 0.8F); 
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
              float hp = p_70100_1_.getHealth() / opMul / 5.0F;
              if (hp < 1.0F || this.rand.nextInt((int)Math.floor(Math.max(1.0F, hp))) == 0) {
                this.riddenByEntity = (Entity)p_70100_1_;
                p_70100_1_.mountEntity((Entity)this);
                if (this.rand.nextInt(5) == 0) {
                  playSound("mob.slime.attack", 0.55F, this.rand.nextFloat() * 0.25F + 0.25F);
                  this.TdeepFactor += 2.0F;
                } 
                syncronizeDepth();
              } 
            } 
            playSound("mob.attack", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            if (!this.worldObj.isRemote) {
              p_70100_1_.addPotionEffect(new PotionEffect(2, 100, 0, false));
              checkPlayerMuddy(p_70100_1_, 0.0D, p_70100_1_.posY - 0.25D + (this.rand.nextFloat() * 0.5F), 0.0D, this.worldObj);
            } 
          } 
          return;
        } 
      } 
    } 
  }
  
  public boolean attackEntityFrom(DamageSource dmgs, float power) {
    Entity ply = dmgs.getEntity();
    boolean Stopper = false;
    if (ply != null && 
      ply instanceof EntityPlayer) {
      Entity ent = dmgs.getSourceOfDamage();
      if (ent == ply && ply.isEntityAlive() && ply.distanceWalkedModified > 0.0F) {
        boolean Weapon = false;
        if (((EntityPlayer)ply).getCurrentEquippedItem() != null) {
          Item item = ((EntityPlayer)ply).getCurrentEquippedItem().getItem();
          Object var1 = item.getItemAttributeModifiers();
          if (var1 != null) {
            Iterator<Map.Entry> var2 = ((Multimap)var1).entries().iterator();
            if (var2 != null)
              while (var2.hasNext()) {
                Map.Entry var3 = var2.next();
                if (var3 != null) {
                  AttributeModifier atr = (AttributeModifier)var3.getValue();
                  if (atr != null) {
                    if (atr.getName() == "Weapon modifier") {
                      Weapon = true;
                      break;
                    } 
                    if (atr.getName() == "Tool modifier") {
                      Weapon = true;
                      break;
                    } 
                  } 
                } 
              }  
          } 
        } 
        if (!Weapon) {
          if (!this.worldObj.isRemote);
        } else if (!this.worldObj.isRemote) {
          boolean canStuck = true;
          float opMul = Math.max(getMaxHealth() / 25.0F, 1.0F);
          if (this.worldObj.difficultySetting != EnumDifficulty.HARD)
            canStuck = false; 
          if (EnchantmentHelper.getFireAspectModifier((EntityLivingBase)ply) > 0)
            canStuck = false; 
          if (EnchantmentHelper.getKnockbackModifier((EntityLivingBase)ply, null) > 0)
            canStuck = false; 
          if (getHealth() <= power)
            canStuck = false; 
          PotionEffect PEWeak = ((EntityLivingBase)ply).getActivePotionEffect(Potion.weakness);
          PotionEffect PESlow = ((EntityLivingBase)ply).getActivePotionEffect(Potion.digSlowdown);
          int weakLevel = -1;
          int slowLevel = -1;
          if (PEWeak != null)
            weakLevel = PEWeak.getAmplifier(); 
          if (PESlow != null)
            slowLevel = PESlow.getAmplifier(); 
          if (canStuck) {
            if (power < 3.0F * opMul)
              for (int i = 0; i < this.inv.length; i++) {
                if (this.inv[i] == null) {
                  this.inv[i] = ((EntityPlayer)ply).getCurrentEquippedItem();
                  ((EntityPlayer)ply).inventory.setInventorySlotContents(((EntityPlayer)ply).inventory.currentItem, (ItemStack)null);
                  playSound("mob.slime.attack", 0.35F, this.rand.nextFloat() * 0.5F + 0.5F);
                  enablePersistence();
                  ((EntityLivingBase)ply).addPotionEffect(new PotionEffect(2, 100, 0, false));
                  checkPlayerMuddy((EntityPlayer)ply, 0.0D, ply.posY - 0.25D + (this.rand.nextFloat() * 0.5F), 0.0D, this.worldObj);
                  Stopper = true;
                  ((EntityLivingBase)ply).setInWeb();
                  break;
                } 
              }  
            ((EntityLivingBase)ply).addPotionEffect(new PotionEffect(4, 100 + Math.max(slowLevel * 20, 0), Math.min(slowLevel + 1, 5), false));
            ((EntityLivingBase)ply).addPotionEffect(new PotionEffect(18, 100 + Math.max(weakLevel * 20, 0), Math.min(weakLevel + 1, 5), false));
          } 
        } 
      } 
    } 
    boolean Result = super.attackEntityFrom(dmgs, power);
    if (Stopper)
      setInWeb(); 
    return Result;
  }
  
  protected void dropFewItems(boolean par1, int par2) {
    int var3 = this.rand.nextInt(8) + this.rand.nextInt(1 + par2);
    int var4;
    for (var4 = 0; var4 < var3; var4++)
      entityDropItem(new ItemStack(Items.slime_ball, 1, 0), 0.0F); 
    dropItem(Items.slime_ball, 4);
    if (MFQM.MudBallID != 0) {
      var3 = this.rand.nextInt(4) + this.rand.nextInt(1 + par2);
      for (var4 = 0; var4 < var3 * 2; var4++)
        entityDropItem(new ItemStack(MFQM.MudBall, 1, 0), 0.0F); 
      dropItem(MFQM.MudBall, 8);
    } 
    if (!this.worldObj.isRemote)
      for (int i = 0; i < this.inv.length; i++) {
        if (this.inv[i] != null)
          entityDropItem(this.inv[i], 0.0F); 
      }  
    var3 = this.rand.nextInt(2) + this.rand.nextInt(1 + par2);
    for (var4 = 0; var4 < var3; var4++)
      entityDropItem(new ItemStack(MFQM.PeatItem, 1, 0), 0.0F); 
  }
  
  protected Item getDropItem() {
    return Items.slime_ball;
  }
  
  protected boolean canDespawn() {
    for (int i = 0; i < this.inv.length; i++) {
      if (this.inv[i] != null)
        return false; 
    } 
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
        return super.getCanSpawnHere();  
    return false;
  }
  
  protected int getAttackStrength() {
    return 2;
  }
  
  protected int getSpawnHp() {
    return 25;
  }
  
  protected void alterSquishAmount() {
    this.squishAmount *= 0.8F;
  }
  
  protected String getSlimeParticle() {
    return "splash";
  }
  
  protected int getJumpDelay() {
    return this.rand.nextInt(20) + 20;
  }
  
  public boolean shouldRiderSit() {
    return false;
  }
}
