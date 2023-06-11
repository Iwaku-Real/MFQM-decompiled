package MoreFunQuicksandMod.main.entity;

import MoreFunQuicksandMod.main.MFQM;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityLongStick extends Entity implements IEntityAdditionalSpawnData {
  public int xx = 0;
  
  public int yy = 0;
  
  public int zz = 0;
  
  public Entity curPlayer;
  
  public EntityLongStick(World world) {
    super(world);
    setSize(0.25F, 0.25F);
    this.ignoreFrustumCheck = true;
  }
  
  public EntityLongStick(World world, double x, double y, double z, Entity entity) {
    this(world);
    setPosition(x, y, z);
    this.ignoreFrustumCheck = true;
    this.curPlayer = entity;
  }
  
  public EntityLongStick(World world, double x, double y, double z, Entity entity, int xx, int yy, int zz) {
    this(world, x, y, z, entity);
    this.xx = xx;
    this.yy = yy;
    this.zz = zz;
  }
  
  public EntityLongStick(World par1World, EntityLivingBase liv) {
    super(par1World);
    this.ignoreFrustumCheck = true;
    setSize(0.25F, 0.25F);
    setPosition(this.posX, this.posY, this.posZ);
  }
  
  protected void entityInit() {}
  
  public void writeSpawnData(ByteBuf data) {
    data.writeInt((this.curPlayer != null) ? this.curPlayer.getEntityId() : 0);
    data.writeInt(this.xx);
    data.writeInt(this.yy);
    data.writeInt(this.zz);
  }
  
  public void readSpawnData(ByteBuf data) {
    this.curPlayer = this.worldObj.getEntityByID(data.readInt());
    this.xx = data.readInt();
    this.yy = data.readInt();
    this.zz = data.readInt();
  }
  
  @SideOnly(Side.CLIENT)
  public boolean isInRangeToRenderDist(double par1) {
    double d1 = this.boundingBox.getAverageEdgeLength() * 4.0D;
    d1 *= 64.0D;
    return (par1 < d1 * d1);
  }
  
  public void onUpdate() {
    super.onUpdate();
    if (!this.worldObj.isRemote) {
      setDead();
      return;
    } 
    Block block1 = this.worldObj.getBlock(this.xx, this.yy, this.zz);
    int meta1 = this.worldObj.getBlockMetadata(this.xx, this.yy, this.zz);
    int Indax1 = -1;
    Block block2 = this.worldObj.getBlock(this.xx, this.yy - 1, this.zz);
    int meta2 = this.worldObj.getBlockMetadata(this.xx, this.yy - 1, this.zz);
    int Indax2 = -1;
    if (block1 == MFQM.MudBlock || block1.getCollisionBoundingBoxFromPool(this.worldObj, this.xx, this.yy, this.zz) == null) {
      int i;
      for (i = 0; i < EntityRope.quicksandIDS.length; i++) {
        if (EntityRope.quicksandIDS[i] == block1)
          Indax1 = i; 
      } 
      if (block2.getCollisionBoundingBoxFromPool(this.worldObj, this.xx, this.yy, this.zz) == null)
        for (i = 0; i < EntityRope.quicksandIDS.length; i++) {
          if (EntityRope.quicksandIDS[i] == block2)
            Indax2 = i; 
        }  
      int deep = 1;
      if (Indax2 != -1)
        deep = 3; 
      if (Indax1 != -1) {
        switch (Indax1) {
          case 0:
            spawnParticle(block1, 0, 5 * deep, 0.2D * deep, 0.1D * deep, 0.05D / deep);
            playSound("mob.slime.attack", 0.25F, 0.25F, 0.25F);
            if (deep > 1) {
              playSuction(0.25F);
              playSound("mob.slime.attack", 0.25F, 0.1F, 0.25F);
            } 
            MFQM.spawnStickBubble(this.worldObj, this.posX, this.posY, this.posZ, block1, 0, (deep > 1));
            break;
          case 1:
            spawnParticle(block1, 0, 5 * deep, 0.2D * deep, 0.1D * deep, 0.05D / deep);
            playSound("dig.cloth", 0.75F, 0.5F, 0.5F);
            if (deep > 1)
              playSound("dig.cloth", 0.75F, 0.25F, 0.5F); 
            break;
          case 2:
            spawnParticle(block1, 0, 5 * deep, 0.2D * deep, 0.1D * deep, 0.05D / deep);
            playSound("dig.sand", 0.25F, 0.5F, 0.5F);
            if (deep > 1)
              playSound("dig.sand", 0.15F, 0.25F, 0.5F); 
            break;
          case 3:
            spawnParticle(MFQM.MireBlock, 10, 5 * deep, 0.2D * deep, 0.1D * deep, 0.05D / deep);
            playSound("mob.slime.attack", 0.25F, 0.25F, 0.25F);
            if (deep > 1) {
              playSuction(0.25F);
              playSound("mob.slime.attack", 0.25F, 0.1F, 0.25F);
            } 
            break;
          case 4:
            spawnParticle(block1, 0, 5 * deep, 0.2D * deep, 0.1D * deep, 0.05D / deep);
            playSound("mob.slime.attack", 0.25F, 0.25F, 0.25F);
            if (deep > 1) {
              playSuction(0.25F);
              playSound("mob.slime.attack", 0.25F, 0.1F, 0.25F);
            } 
            MFQM.spawnStickBubble(this.worldObj, this.posX, this.posY, this.posZ, block1, 0, (deep > 1));
            break;
          case 5:
            spawnParticle(block1, 0, 5 * deep, 0.2D * deep, 0.1D * deep, 0.05D / deep);
            playSound("mob.slime.attack", 0.25F, 0.25F, 0.25F);
            playSuction(0.1F);
            if (deep > 1) {
              playSuction(0.25F);
              playSound("mob.slime.attack", 0.25F, 0.1F, 0.25F);
            } 
            MFQM.spawnStickBubble(this.worldObj, this.posX, this.posY, this.posZ, block1, 0, (deep > 1));
            break;
          case 6:
            spawnParticle(block1, 0, 5 * deep, 0.2D * deep, 0.1D * deep, 0.05D / deep);
            playSound("mob.slime.attack", 0.25F, 0.25F, 0.25F);
            if (deep > 1) {
              playSuction(0.15F);
              playSound("mob.slime.attack", 0.25F, 0.1F, 0.25F);
            } 
            MFQM.spawnStickBubble(this.worldObj, this.posX, this.posY, this.posZ, block1, 0, (deep > 1));
            break;
          case 7:
            spawnParticle(block1, 0, 5 * deep, 0.2D * deep, 0.1D * deep, 0.05D / deep);
            playSound("mob.slime.attack", 0.25F, 0.25F, 0.25F);
            if (deep > 1) {
              playSuction(0.15F);
              playSound("mob.slime.attack", 0.25F, 0.1F, 0.25F);
            } 
            MFQM.spawnStickBubble(this.worldObj, this.posX, this.posY, this.posZ, block1, 0, (deep > 1));
            break;
          case 8:
            spawnParticle(block1, 0, 5 * deep, 0.2D * deep, 0.1D * deep, 0.05D / deep);
            playSound("mob.slime.attack", 0.25F, 0.25F, 0.25F);
            playSuction(0.1F);
            if (deep > 1) {
              playSuction(0.25F);
              playSound("mob.slime.attack", 0.25F, 0.1F, 0.25F);
            } 
            break;
          case 9:
            spawnParticle(block1, 10, 5 * deep, 0.2D * deep, 0.1D * deep, 0.05D / deep);
            playSound("mob.slime.attack", 0.25F, 0.25F, 0.25F);
            playSuction(0.1F);
            if (deep > 1) {
              playSuction(0.25F);
              playSound("mob.slime.attack", 0.25F, 0.1F, 0.25F);
            } 
            MFQM.spawnStickBubble(this.worldObj, this.posX, this.posY, this.posZ, block1, -1, (deep > 1));
            break;
          case 10:
            spawnParticle(block1, 10, 5 * deep, 0.2D * deep, 0.1D * deep, 0.05D / deep);
            playSound("mob.slime.attack", 0.25F, 0.25F, 0.25F);
            playSuction(0.1F);
            if (deep > 1) {
              playSuction(0.25F);
              playSound("mob.slime.attack", 0.25F, 0.1F, 0.25F);
            } 
            MFQM.spawnStickBubble(this.worldObj, this.posX, this.posY, this.posZ, block1, 10, (deep > 1));
            break;
          case 11:
            spawnParticle(block1, 10, 5 * deep, 0.2D * deep, 0.1D * deep, 0.05D / deep);
            playSound("mob.slime.attack", 0.25F, 0.25F, 0.25F);
            playSuction(0.1F);
            if (deep > 1) {
              playSuction(0.25F);
              playSound("mob.slime.attack", 0.25F, 0.1F, 0.25F);
            } 
            MFQM.spawnStickBubble(this.worldObj, this.posX, this.posY, this.posZ, block1, 10, (deep > 1));
            break;
          case 12:
            spawnParticle(block1, 0, 5 * deep, 0.2D * deep, 0.1D * deep, 0.05D / deep);
            playSound("mob.slime.attack", 0.25F, 0.25F, 0.25F);
            playSuction(0.2F);
            if (deep > 1) {
              playSuction(0.25F);
              playSound("mob.slime.attack", 0.25F, 0.1F, 0.25F);
            } 
            MFQM.spawnStickBubble(this.worldObj, this.posX, this.posY, this.posZ, block1, 0, (deep > 1));
            break;
          case 13:
            playSound("mob.silverfish.step", 0.55F, 0.85F, 0.15F);
            playSound("mob.silverfish.hit", 0.15F, 1.75F, 0.25F);
            if (deep > 1)
              playSound("mob.silverfish.step", 0.55F, 0.45F, 0.15F); 
            break;
          case 14:
            spawnParticle(block1, 0, 5 * deep, 0.2D * deep, 0.1D * deep, 0.05D / deep);
            playSound("dig.sand", 0.25F, 0.5F, 0.5F);
            if (deep > 1)
              playSound("dig.sand", 0.15F, 0.25F, 0.5F); 
            break;
          case 16:
            spawnParticle(block1, meta1, 5 * deep, 0.2D * deep, 0.1D * deep, 0.05D / deep);
            playSound("mob.slime.attack", 0.25F, 0.25F, 0.25F);
            playSound("dig.cloth", 0.55F, 0.1F, 0.15F);
            if (deep > 1) {
              playSuction(0.25F);
              playSound("mob.slime.attack", 0.25F, 0.1F, 0.25F);
              playSound("dig.cloth", 0.55F, 0.1F, 0.15F);
            } 
            break;
          case 17:
            spawnParticle(block1, 0, 5 * deep, 0.2D * deep, 0.1D * deep, 0.05D / deep);
            playSound("mob.slime.attack", 0.25F, 0.25F, 0.25F);
            playSound("dig.sand", 0.25F, 0.5F, 0.5F);
            playSuction(0.1F);
            if (deep > 1) {
              playSuction(0.25F);
              playSound("mob.slime.attack", 0.25F, 0.1F, 0.25F);
              playSound("dig.sand", 0.25F, 0.25F, 0.5F);
            } 
            break;
          case 18:
            spawnParticle(block1, 0, 5 * deep, 0.2D * deep, 0.1D * deep, 0.05D / deep);
            playSound("mob.magmacube.jump", 0.25F, 0.25F, 0.4F);
            if (deep > 1)
              playSound("mob.magmacube.jump", 0.25F, 0.1F, 0.4F); 
            break;
          case 19:
            spawnParticle(block1, 0, 5 * deep, 0.2D * deep, 0.1D * deep, 0.05D / deep);
            playSound("mob.slime.attack", 0.25F, 0.25F, 0.25F);
            playSuction(0.1F);
            if (deep > 1) {
              playSuction(0.25F);
              playSound("mob.slime.attack", 0.25F, 0.1F, 0.25F);
            } 
            break;
          case 20:
            spawnParticle(block1, 0, 5 * deep, 0.2D * deep, 0.1D * deep, 0.05D / deep);
            playSound("mob.slime.attack", 0.25F, 0.25F, 0.25F);
            playSuction(0.1F);
            if (deep > 1) {
              playSuction(0.25F);
              playSound("mob.slime.attack", 0.25F, 0.1F, 0.25F);
            } 
            MFQM.spawnStickBubble(this.worldObj, this.posX, this.posY, this.posZ, block1, 0, (deep > 1));
            break;
          case 22:
            spawnParticle(block1, 0, 5 * deep, 0.2D * deep, 0.1D * deep, 0.05D / deep);
            playSound("dig.grass", 0.15F, 0.25F, 0.4F);
            if (deep > 1) {
              playSuction(0.25F);
              playSound("dig.grass", 0.15F, 0.1F, 0.4F);
            } 
            break;
          case 23:
            spawnParticle(block1, meta1, 5 * deep, 0.2D * deep, 0.1D * deep, 0.05D / deep);
            playSound("mob.slime.attack", 0.25F, 0.25F, 0.25F);
            if (deep > 1) {
              playSuction(0.25F);
              playSound("mob.slime.attack", 0.25F, 0.1F, 0.25F);
            } 
            MFQM.spawnStickBubble(this.worldObj, this.posX, this.posY, this.posZ, block1, -1, (deep > 1));
            break;
          case 24:
            spawnParticle(block1, meta1, 5 * deep, 0.2D * deep, 0.1D * deep, 0.05D / deep);
            playSound("mob.slime.attack", 0.25F, 0.25F, 0.25F);
            if (deep > 1) {
              playSuction(0.25F);
              playSound("mob.slime.attack", 0.25F, 0.1F, 0.25F);
            } 
            MFQM.spawnStickBubble(this.worldObj, this.posX, this.posY, this.posZ, block1, -1, (deep > 1));
            break;
          case 25:
            spawnParticle(block1, 0, 5 * deep, 0.2D * deep, 0.1D * deep, 0.05D / deep);
            playSound("mob.slime.attack", 0.25F, 0.25F, 0.25F);
            if (deep > 1) {
              playSuction(0.25F);
              playSound("mob.slime.attack", 0.25F, 0.1F, 0.25F);
            } 
            break;
          case 26:
            spawnParticle(block1, 0, 5 * deep, 0.2D * deep, 0.1D * deep, 0.05D / deep);
            playSound("mob.slime.attack", 0.25F, 0.25F, 0.25F);
            playSuction(0.1F);
            if (deep > 1) {
              playSuction(0.25F);
              playSound("mob.slime.attack", 0.25F, 0.1F, 0.25F);
            } 
            MFQM.spawnStickBubble(this.worldObj, this.posX, this.posY, this.posZ, block1, -1, (deep > 1));
            break;
          case 27:
            spawnParticle(block1, 0, 5 * deep, 0.2D * deep, 0.1D * deep, 0.05D / deep);
            playSound("mob.slime.attack", 0.25F, 0.25F, 0.25F);
            playSuction(0.1F);
            if (deep > 1) {
              playSuction(0.25F);
              playSound("mob.slime.attack", 0.25F, 0.1F, 0.25F);
            } 
            MFQM.spawnStickBubble(this.worldObj, this.posX, this.posY, this.posZ, block1, 0, (deep > 1));
            break;
          case 28:
            if (this.curPlayer != null) {
              spawnParticle(block1, 0, 5 * deep, 0.2D * deep, 0.1D * deep, 0.05D / deep);
              playSound("mob.slime.attack", 0.25F, 0.25F, 0.25F);
              if (deep > 1) {
                playSuction(0.25F);
                playSound("mob.slime.attack", 0.25F, 0.1F, 0.25F);
              } 
              MFQM.spawnStickBubble(this.worldObj, this.posX, this.posY, this.posZ, block1, 0, (deep > 1));
              break;
            } 
            MFQM.spawnStickBubble(this.worldObj, this.posX, this.posY, this.posZ, block1, 0, true);
            break;
          case 29:
            if (meta1 == 0)
              playBottom(block1); 
            if (meta1 > 0) {
              spawnParticle(block1, 0, 5, 0.2D, 0.1D, 0.05D);
              playSound("mob.slime.attack", 0.25F, 0.25F, 0.25F);
              playSound("dig.sand", 0.25F, 0.5F, 0.5F);
            } 
            if (meta1 == 2)
              playSuction(0.1F); 
            if (meta1 > 2) {
              playSuction(0.25F);
              MFQM.spawnStickBubble(this.worldObj, this.posX, this.posY, this.posZ, block1, 0, (deep > 1));
            } 
            break;
          case 30:
            spawnParticle(block1, 0, 5 * deep, 0.2D * deep, 0.1D * deep, 0.05D / deep);
            playSound("mob.slime.attack", 0.25F, 0.25F, 0.25F);
            if (deep > 1) {
              playSuction(0.25F);
              playSound("mob.slime.attack", 0.25F, 0.1F, 0.25F);
            } 
            MFQM.spawnStickBubble(this.worldObj, this.posX, this.posY, this.posZ, block1, 2, (deep > 1));
            break;
          case 31:
            if (this.curPlayer != null) {
              spawnParticle(block1, 0, 5 * deep, 0.2D * deep, 0.1D * deep, 0.05D / deep);
              playSound("mob.slime.attack", 0.25F, 0.25F, 0.25F);
              if (deep > 1) {
                playSuction(0.25F);
                playSound("mob.slime.attack", 0.25F, 0.1F, 0.25F);
              } 
              MFQM.spawnStickBubble(this.worldObj, this.posX, this.posY, this.posZ, block1, 0, (deep > 1));
              break;
            } 
            MFQM.spawnStickBubble(this.worldObj, this.posX, this.posY, this.posZ, block1, 0, true);
            break;
        } 
        if (deep == 1)
          playBottom(block2); 
      } 
    } else {
      this.worldObj.playSound(this.posX, this.posY, this.posZ, block1.stepSound.getStepSound(), 0.25F, block1.stepSound.getFrequency(), false);
    } 
    setDead();
  }
  
  public void spawnParticle(Block blc, int Meta, int count, double rad, double yrad, double up) {
    for (int var3 = 0; var3 < count; var3++)
      this.worldObj.spawnParticle("blockcrack_" + Block.getIdFromBlock(blc) + "_" + Meta, this.posX + this.worldObj.rand.nextFloat() * rad - rad / 2.0D, this.posY + up + this.worldObj.rand.nextFloat() * yrad - yrad / 2.0D, this.posZ + this.worldObj.rand.nextFloat() * rad - rad / 2.0D, 0.0D, 0.0D, 0.0D); 
  }
  
  public void playSound(String str, float vol, float pitch, float rpitch) {
    this.worldObj.playSound(this.posX, this.posY, this.posZ, str, vol, this.worldObj.rand.nextFloat() * rpitch + pitch, false);
  }
  
  public void playBottom(Block blc) {
    if (blc.getMaterial().isSolid())
      this.worldObj.playSound(this.posX, this.posY - 1.0D, this.posZ, blc.stepSound.getStepSound(), 0.2F, blc.stepSound.getFrequency(), false); 
  }
  
  public void playSuction(float vol) {
    this.worldObj.playSound(this.posX, this.posY, this.posZ, "mob.magmacube.jump", vol / 2.5F, 0.25F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F, false);
    this.worldObj.playSound(this.posX, this.posY, this.posZ, "mob.magmacube.jump", vol / 2.5F, 0.25F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F, false);
    this.worldObj.playSound(this.posX, this.posY, this.posZ, "mob.magmacube.jump", vol / 2.5F, 0.25F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F, false);
    this.worldObj.playSound(this.posX, this.posY, this.posZ, "mob.magmacube.jump", vol / 2.5F, 0.25F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F, false);
  }
  
  public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {}
  
  public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {}
  
  @SideOnly(Side.CLIENT)
  public float getShadowSize() {
    return 0.0F;
  }
}
