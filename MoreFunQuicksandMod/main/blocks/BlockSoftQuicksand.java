package MoreFunQuicksandMod.main.blocks;

import MoreFunQuicksandMod.main.CustomVarPlayer;
import MoreFunQuicksandMod.main.MFQM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockSoftQuicksand extends Block {
  public IIcon[] theQIIcon;
  
  private int maxOpacity;
  
  private int lastOpacity;
  
  private int incOpacity;
  
  public BlockSoftQuicksand(int mopc, int lopc, int iopc) {
    super(Material.sand);
    setHardness(2.0F);
    setStepSound(Block.soundTypeSand);
    setResistance(1000.0F);
    setTickRandomly(true);
    setLightOpacity(3);
    setUnlocalizedName("SoftQuicksand");
    setCreativeTab(MFQM.tabMFQM);
    this.maxOpacity = mopc;
    this.lastOpacity = lopc;
    this.incOpacity = iopc;
  }
  
  public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
    return false;
  }
  
  public boolean isOpaqueCube() {
    return false;
  }
  
  public boolean isSolid() {
    return true;
  }
  
  public boolean renderAsNormalBlock() {
    return false;
  }
  
  public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int x, int y, int z) {
    if (par1IBlockAccess.getBlock(x, y + 1, z) != this) {
      setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.85F, 1.0F);
    } else {
      setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    } 
  }
  
  public void setBlockBoundsForItemRender() {
    setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.85F, 1.0F);
  }
  
  public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
    int mtd = 0;
    double EposY = entity.posY;
    double EprevPosY = entity.prevPosY;
    double prevVel = entity.motionY;
    double oy = y - EposY + 1.0D + 0.2D;
    double oy2 = y - EprevPosY + 1.0D + 0.2D;
    oy *= -1.0D;
    oy2 *= -1.0D;
    if (oy2 < 0.0D)
      oy2 = 0.0D; 
    double kof1 = oy;
    double kof1m = oy;
    if (kof1m < 0.0D)
      kof1m = 0.0D; 
    double kof2 = oy2;
    if (!(entity instanceof EntityPlayer)) {
      oy = y - EposY - 0.5D + 0.2D;
      oy2 = y - EprevPosY - 0.5D + 0.2D;
      oy *= -1.0D;
      oy2 *= -1.0D;
      if (oy2 < 0.0D)
        oy2 = 0.0D; 
      kof1 = oy;
      kof1m = oy;
      if (kof1m < 0.0D)
        kof1m = 0.0D; 
      kof2 = oy2;
    } 
    if (entity instanceof EntityLivingBase) {
      checkEntityUnder(entity);
      if (entity instanceof EntityPlayer)
        checkPlayerMuddy((EntityPlayer)entity, x, y, z, world); 
      boolean entJump = false;
      boolean entMoving = false;
      boolean entSplash = false;
      boolean entRotate = false;
      float mtdkof = 10.0F;
      double movDis = 1.0D;
      double movCof = 16.0D;
      double movKofDiv = 1.0D;
      int mr_kof = (int)Math.min(5.0D + Math.floor(Math.max(0.0D, Math.pow(mtdkof * 1.25D * (1.5D - kof1m), 1.75D))), 175.0D);
      if (EposY - EprevPosY > 0.2D)
        entJump = true; 
      if (entity.motionY < -0.1D)
        entSplash = true; 
      if (!(entity instanceof EntityPlayer) && 
        ((EntityLivingBase)entity).prevRotationYaw != ((EntityLivingBase)entity).rotationYaw)
        entRotate = true; 
      if (entity instanceof EntityPlayer && 
        world.isRemote && 
        Math.abs(MFQM.SIRenderYawPre - MFQM.SIRenderYaw) > 10.0D)
        entRotate = true; 
      if (entRotate || Math.abs(entity.prevPosX - entity.posX) > 0.001D || Math.abs(entity.prevPosZ - entity.posZ) > 0.001D) {
        entMoving = true;
        movDis = Math.pow(Math.pow(entity.prevPosX - entity.posX, 2.0D) + Math.pow(entity.prevPosZ - entity.posZ, 2.0D), 0.5D);
        movCof = movDis * 10.0D;
        movCof = Math.max(Math.min(32.0D / (1.0D + movCof), 32.0D), 16.0D);
        movKofDiv = 1.0D + movDis * 2.0D;
        if (kof1 < 0.9D && 
          entRotate)
          movKofDiv += mr_kof * 0.002D; 
      } 
      entity.motionX *= 0.71D;
      entity.motionZ *= 0.71D;
      if (entity.motionY > -0.1D) {
        entity.motionY = 0.0D;
      } else {
        entity.motionY /= 2.0D;
      } 
      if ((!entMoving && world.getTotalWorldTime() % 4L == 0L) || (entMoving && world.getTotalWorldTime() % Math.max((int)Math.floor(movCof / 2.0D), 1) == 0L) || (entJump && world.getTotalWorldTime() % 8L == 0L) || entSplash) {
        if (entSplash && 
          kof2 > 1.5D) {
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.step", 0.25F, world.rand.nextFloat() * 0.25F + 0.25F);
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "step.sand", 0.25F, world.rand.nextFloat() * 0.5F + 0.5F);
        } 
        if (!entSplash && entMoving && 
          world.rand.nextInt(2) == 0) {
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.step", 0.1F, world.rand.nextFloat() * 0.25F + 0.75F);
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "step.grass", 0.25F, world.rand.nextFloat() * 0.15F + 0.1F);
        } 
        if (entJump) {
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.slime.attack", 0.15F, world.rand.nextFloat() * 0.25F + 0.25F);
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "dig.sand", 0.25F, world.rand.nextFloat() * 0.5F + 0.5F);
        } 
        if (!entJump && !entMoving && !entSplash) {
          if (Math.abs(EprevPosY - EposY) > 0.004D)
            world.playSoundEffect(entity.posX, EposY, entity.posZ, "step.grass", 0.25F, world.rand.nextFloat() * 0.15F + 0.1F); 
          if (world.getTotalWorldTime() % 128L == 0L && 
            world.rand.nextInt(10) == 0)
            world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.step", 0.15F, world.rand.nextFloat() * 0.25F + 0.25F); 
        } 
      } 
      if (entity instanceof MoreFunQuicksandMod.main.entity.EntitySandBlob) {
        ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(10, 5, 1, false));
        if (world.getBlock(x, y + 1, z) != this) {
          if (kof1 >= 1.35D) {
            if (!entSplash) {
              entity.motionY = 0.0D;
              entity.motionY += 0.08D;
              entity.onGround = true;
              entity.fallDistance = 0.0F;
            } 
          } else {
            entity.motionY = 0.0D;
            entity.motionY += 0.1D;
            entity.onGround = true;
            entity.fallDistance = 0.0F;
          } 
        } else {
          entity.motionY = 0.0D;
          entity.motionY += 0.1D;
          entity.onGround = true;
          entity.fallDistance = 0.0F;
        } 
        return;
      } 
      MFQM.SpawnMudTentacles(world, entity, x, y, z, this, mtd, 64, 10);
      if (world.getBlock(x, y + 1, z) != this || (world.getBlock(x, y + 1, z) == this && world.getBlock(x, y + 2, z) == this)) {
        if (world.getBlock(x, y + 1, z) == this && world.getBlock(x, y + 2, z) == this)
          kof1 = 0.001D; 
        double mys = 0.0D;
        if (!(entity instanceof EntityPlayer))
          mys = 0.0D; 
        if (entJump && 
          world.rand.nextInt(1) == 0 && 
          MFQM.suctionWorldCheck(entity, world, prevVel)) {
          entity.motionY -= 0.05D * (Math.min(0.75D, kof1m) + 1.0D);
          world.playSound(entity.posX, EposY, entity.posZ, "mob.magmacube.jump", 0.1F, 0.25F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F, false);
          world.playSound(entity.posX, EposY, entity.posZ, "dig.sand", 0.3F, world.rand.nextFloat() * 0.5F + 0.5F, false);
        } 
        if (kof1 >= 1.2D) {
          if (kof1 < 1.3D)
            if (!entSplash) {
              double a1 = 0.0735D;
              entity.motionY += a1 / Math.max((movKofDiv - 1.0D) * Math.max(kof1m - 0.5D, 0.75D) * 1.01D * 17.5D, 1.0D) / Math.pow(Math.max(kof1m / 1.475D, 1.0D), 2.0D);
              entity.onGround = true;
              entity.fallDistance = 0.0F;
              double ftrop = Math.max(Math.min((kof1 - 1.25D) * 4.0D, 1.0D), 0.0D);
              entity.motionX *= ftrop;
              entity.motionZ *= ftrop;
              if (entity instanceof EntityPlayer) {
                if (world.getTotalWorldTime() % Math.max(Math.floor(Math.max(Math.pow(kof1 + 0.1D, 5.0D), 0.0D)), 1.0D) == 0.0D) {
                  a1 = 0.0725D;
                  entity.setInWeb();
                  entity.onGround = false;
                } 
                if (world.rand.nextInt(Math.max((int)Math.floor(50.0D - Math.pow(Math.max(kof1 + 0.1D, 0.0D), 1.5D)), 1)) == 0)
                  entity.onGround = true; 
              } else {
                double mw_kof = (1 + mr_kof / 10);
                entity.setPosition(entity.prevPosX + (entity.posX - entity.prevPosX) / mw_kof, EposY, entity.prevPosZ + (entity.posZ - entity.prevPosZ) / mw_kof);
              } 
            }  
        } else {
          if (EposY - EprevPosY > 0.001D && 
            world.rand.nextInt(Math.max((int)Math.floor(kof1m * 3.0D), 10)) == 0 && 
            MFQM.suctionWorldCheck(entity, world, prevVel)) {
            entity.motionY -= 0.035D * mtdkof * (Math.min(0.75D, kof1m) + 1.0D);
            world.playSound(entity.posX, EposY, entity.posZ, "mob.magmacube.jump", 0.1F, 0.25F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F, false);
            world.playSound(entity.posX, EposY, entity.posZ, "dig.sand", 0.3F, world.rand.nextFloat() * 0.5F + 0.5F, false);
          } 
          if (kof1 >= 0.9D) {
            double a1 = 0.0735D;
            MFQM.setStuckEffect((EntityLivingBase)entity, mr_kof);
            if (!entSplash) {
              entity.onGround = true;
              entity.fallDistance = 0.0F;
            } 
            if (entity instanceof EntityPlayer) {
              if (world.getTotalWorldTime() % Math.max(Math.floor(Math.max(Math.pow(kof1 + 0.1D, 5.0D), 0.0D)), 1.0D) == 0.0D) {
                a1 = 0.0715D;
                entity.setInWeb();
                entity.onGround = false;
              } 
              if (world.rand.nextInt(Math.max((int)Math.floor(50.0D - Math.pow(Math.max(kof1 + 0.1D, 0.0D), 1.5D)), 1)) == 0)
                entity.onGround = true; 
            } else {
              double mw_kof = (1 + mr_kof / 10);
              entity.setPosition(entity.prevPosX + (entity.posX - entity.prevPosX) / mw_kof, EposY, entity.prevPosZ + (entity.posZ - entity.prevPosZ) / mw_kof);
            } 
            entity.motionY += a1 / Math.max((movKofDiv * 2.0D - 1.0D) * Math.max(kof1m - 0.5D, 0.75D) * 1.6D, 1.0D) / Math.pow(Math.max(kof1m / 1.25D, 1.0D), 2.0D);
          } else {
            entity.motionX = 0.0D;
            entity.motionZ = 0.0D;
            double xx = x + 0.5D;
            double zz = z + 0.5D;
            double disq = Math.pow(Math.pow(entity.posX - xx, 2.0D) + Math.pow(entity.posZ - zz, 2.0D), 0.5D);
            if (disq < 0.5D && 
              disq >= 0.075D) {
              entity.motionX += (xx - entity.posX) / Math.max(10.0D, kof1m * 50.0D);
              entity.motionZ += (zz - entity.posZ) / Math.max(10.0D, kof1m * 50.0D);
              if (Math.pow(Math.pow(entity.motionX, 2.0D) + Math.pow(entity.motionZ, 2.0D), 0.5D) > 0.005D) {
                movKofDiv = Math.max(movKofDiv / 10.0D, 1.0D);
                if (!entRotate) {
                  entMoving = false;
                } else {
                  movKofDiv += mr_kof * 0.002D;
                } 
              } 
            } 
            double a2 = 1.05D;
            if (world.getTotalWorldTime() % (int)Math.floor(Math.min(2.0D, Math.max(32.0D - kof1 * 32.0D, 1.0D))) == 0L) {
              a2 = 1.01D;
            } else {
              a2 = 1.05D;
            } 
            if (world.rand.nextInt(Math.max((int)Math.floor(50.0D - Math.pow(Math.max(kof1m, 0.0D), 1.5D)), 1)) == 0)
              entity.onGround = true; 
            if (MFQM.isTrulySink(entity, kof1m))
              MFQM.setStuckEffect((EntityLivingBase)entity, mr_kof); 
            entity.fallDistance = 0.0F;
            if (kof1 >= 0.5D) {
              if (world.rand.nextInt((int)Math.floor(Math.max(kof1m * 5.0D / mtdkof, 1.0D))) == 0) {
                entity.setInWeb();
                if (!entMoving) {
                  entity.motionY += (0.0725D + mys) * a2;
                } else {
                  entity.motionY += (0.0725D + mys) * a2 / (movKofDiv * 2.0D * 2.0D + 0.075D);
                } 
              } else if (!entMoving) {
                entity.motionY += (0.075D + mys) * a2;
              } else {
                entity.motionY += (0.075D + mys) * a2 / (movKofDiv * 2.0D * 2.0D + 0.075D);
              } 
            } else {
              entity.setInWeb();
              if (!entMoving) {
                entity.motionY += (0.0725D + mys) * a2;
              } else {
                entity.motionY += (0.0725D + mys) * a2 / (movKofDiv * 8.0D * 2.0D + 0.025D);
              } 
              if (kof1 < 0.45D && 
                MFQM.isTrulySink(entity, kof1m))
                MFQM.setStuckEffect((EntityLivingBase)entity, 175); 
            } 
          } 
        } 
        if (entity.isInWater() && 
          entity.motionY > 0.0D)
          entity.motionY /= 4.0D; 
      } else {
        entity.setInWeb();
        if (kof1 < 0.5D && 
          MFQM.isTrulySink(entity, kof1m))
          MFQM.setStuckEffect((EntityLivingBase)entity, 175); 
      } 
      MFQM.antiHoldJumpScript(entity, kof1, true);
    } else {
      if (kof1 < 1.45D)
        entity.setInWeb(); 
      MFQM.HandleMudTentacles(world, entity, x, y, z, this, mtd);
    } 
  }
  
  public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
    return null;
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int par1, int par2) {
    return this.theQIIcon[Math.min(Math.max(par2, 0), 2)];
  }
  
  public void updateTick(World world, int x, int y, int z, Random rand) {
    int xx = x;
    int yy = y;
    int zz = z;
    int met = 0;
    boolean isWaterNear = false;
    boolean isLavaNear = false;
    for (int re = 0; re <= 6; re++) {
      switch (re) {
        case 0:
          xx = x - 1;
          yy = y;
          zz = z;
          break;
        case 1:
          xx = x + 1;
          yy = y;
          zz = z;
          break;
        case 2:
          xx = x;
          yy = y;
          zz = z - 1;
          break;
        case 3:
          xx = x;
          yy = y;
          zz = z + 1;
          break;
        case 4:
          xx = x;
          yy = y - 1;
          zz = z;
          break;
        case 5:
          xx = x;
          yy = y + 1;
          zz = z;
          break;
      } 
      if (world.getBlock(xx, yy, zz).getMaterial() == Material.lava) {
        met = world.getBlockMetadata(xx, yy, zz);
        isLavaNear = true;
        break;
      } 
      if (world.getBlock(xx, yy, zz).getMaterial() == Material.water) {
        isWaterNear = true;
        break;
      } 
    } 
    if (isLavaNear) {
      world.setBlock(x, y, z, (Block)Blocks.sand, 0, 3);
      world.playSoundEffect((x + 0.5F), (y + 0.5F), (z + 0.5F), "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
      for (int l = 0; l < 8; l++)
        world.spawnParticle("largesmoke", x + Math.random(), y + 1.2D, z + Math.random(), 0.0D, 0.0D, 0.0D); 
      return;
    } 
    if (world.rand.nextInt(2) == 0) {
      if (!world.getBlock(x, y - 1, z).getMaterial().isSolid() && world.getBlock(x, y - 1, z).getMaterial().isReplaceable()) {
        world.setBlock(x, y - 1, z, this, world.getBlockMetadata(x, y, z), 3);
        world.setBlockToAir(x, y, z);
      } 
    } else {
      world.scheduleBlockUpdate(x, y, z, this, tickRate(world) * 10);
    } 
  }
  
  public void onBlockAdded(World par1World, int par2, int par3, int par4) {
    par1World.scheduleBlockUpdate(par2, par3, par4, this, tickRate(par1World));
  }
  
  public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5) {
    par1World.scheduleBlockUpdate(par2, par3, par4, this, tickRate(par1World));
  }
  
  public float getPlayerRelativeBlockHardness(EntityPlayer par1EntityPlayer, World par2World, int par3, int par4, int par5) {
    if (!((Entity)par1EntityPlayer).onGround)
      return 4.5E-5F; 
    float f = getBlockHardness(par2World, par3, par4, par5);
    return ForgeHooks.blockStrength(this, par1EntityPlayer, par2World, par3, par4, par5);
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister par1IIconRegister) {
    this.theQIIcon = new IIcon[3];
    this.theQIIcon[0] = par1IIconRegister.registerIcon("morefunquicksandmod:SoftQuicksand1");
    this.theQIIcon[1] = par1IIconRegister.registerIcon("morefunquicksandmod:SoftQuicksand0");
    this.theQIIcon[2] = par1IIconRegister.registerIcon("morefunquicksandmod:SoftQuicksand2");
  }
  
  public boolean canDropFromExplosion(Explosion par1Explosion) {
    return false;
  }
  
  public boolean canHarvestBlock(EntityPlayer player, int meta) {
    return true;
  }
  
  public Item getItemDropped(int par1, Random random, int par3) {
    return null;
  }
  
  public void harvestBlock(World world, EntityPlayer entityPlayer, int x, int y, int z, int par6) {
    if (EnchantmentHelper.getSilkTouchModifier((EntityLivingBase)entityPlayer)) {
      MFQM.dropItem(world, x, y, z, new ItemStack(this));
      world.markBlockForUpdate(x, y, z);
      return;
    } 
    MFQM.dropItem(world, x, y, z, new ItemStack((Block)Blocks.sand));
    world.setBlockToAir(x, y, z);
  }
  
  public boolean isNormalCube() {
    return false;
  }
  
  public void checkEntityUnder(Entity ent) {
    if (MFQM.isEntityInsideOfBlock(ent, this) && 
      MFQM.isDrowning(ent) && 
      !ent.worldObj.isRemote && 
      ent.isEntityAlive())
      ent.attackEntityFrom(DamageSource.drown, Math.max(((EntityLivingBase)ent).getMaxHealth() * 0.1F, 2.0F)); 
  }
  
  public void checkPlayerMuddy(EntityPlayer ent, int x, int y, int z, World world) {
    if (!MFQM.QSCover)
      return; 
    if (!world.isRemote) {
      CustomVarPlayer props = CustomVarPlayer.get(ent);
      int preML = MFQM.getMuddyLevel(ent, y, world);
      if (preML * this.maxOpacity / 1000.0F > props.getMuddyLevel() * props.getMuddyTime() / 1000.0F) {
        props.setMuddyLevel(preML);
        int mdtp = MFQM.getMuddyType(this);
        props.setMuddyType(mdtp);
        if (props.getMuddyTime() < this.maxOpacity)
          props.addMuddyTime(this.incOpacity); 
        props.setMuddyTime(Math.min(props.getMuddyTime(), this.maxOpacity));
      } 
    } 
  }
}
