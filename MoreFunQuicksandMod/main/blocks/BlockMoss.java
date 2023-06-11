package MoreFunQuicksandMod.main.blocks;

import MoreFunQuicksandMod.main.CustomVarPlayer;
import MoreFunQuicksandMod.main.Fields;
import MoreFunQuicksandMod.main.MFQM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockMoss extends Block implements IShearable {
  public IIcon theQIIcon;
  
  public IIcon theFIIcon;
  
  private int maxOpacity;
  
  private int lastOpacity;
  
  private int incOpacity;
  
  public BlockMoss(int mopc, int lopc, int iopc) {
    super(Material.sponge);
    setHardness(5.0F);
    setStepSound(Block.soundTypeGrass);
    setResistance(1000.0F);
    setLightOpacity(0);
    setUnlocalizedName("TangleRootMoss");
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
  
  public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
    int meta = world.getBlockMetadata(x, y, z);
    float bottom = 0.0F;
    float up = 1.0F;
    switch (meta) {
      case 0:
        bottom = -0.8F;
        up = 0.0F;
        break;
      case 1:
        bottom = 0.0F;
        up = 0.2F;
        break;
      case 2:
        bottom = -0.8F;
        up = 0.0F;
        break;
      case 3:
        bottom = -0.8F;
        up = 0.2F;
        break;
      case 4:
        bottom = 0.0F;
        up = 1.0F;
        break;
      case 5:
        bottom = -0.8F;
        up = 0.2F;
        break;
      case 6:
        bottom = -0.8F;
        up = 0.0F;
        break;
    } 
    setBlockBounds(0.0F, bottom, 0.0F, 1.0F, up, 1.0F);
  }
  
  public void setBlockBoundsForItemRender() {
    setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.8F, 1.0F);
  }
  
  public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
    int mtd = 0;
    double EposY = entity.posY;
    double EprevPosY = entity.prevPosY;
    double prevVel = entity.motionY;
    y--;
    double uppa = 0.2D;
    int meta2 = world.getBlockMetadata(x, y + 1, z);
    if (meta2 == 2 || meta2 == 6)
      uppa = 0.1D; 
    double oy = y - EposY + 1.0D + uppa;
    double oy2 = y - EprevPosY + 1.0D + uppa;
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
      oy = y - EposY - 0.5D + uppa;
      oy2 = y - EprevPosY - 0.5D + uppa;
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
    double kof3 = kof1;
    if (entity instanceof EntityPlayer && 
      !world.isRemote)
      kof3 += 1.5D; 
    if (kof3 <= 1.5D)
      if (entity instanceof EntityLivingBase) {
        boolean Affect = true;
        if (!(entity instanceof EntityPlayer) && 
          MFQM.ABC_MIC && 
          entity.worldObj.provider.dimensionId == MFQM.ABCDim2)
          Affect = false; 
        if (!(entity instanceof EntityPlayer) && 
          MFQM.AOA && (
          world.provider.dimensionId == MFQM.AOADimAbyss || world.provider.dimensionId == MFQM.AOADimLBorean))
          Affect = false; 
        if (Affect)
          checkEntityUnder(entity); 
        if (entity instanceof EntityPlayer);
        boolean entJump = false;
        boolean entMoving = false;
        boolean entSplash = false;
        boolean entRotate = false;
        float mtdkof = 50.0F;
        double movDis = 1.0D;
        double movCof = 16.0D;
        double movKofDiv = 1.0D;
        int mr_kof = (int)Math.min(5.0D + Math.floor(Math.max(0.0D, Math.pow(mtdkof * 0.5D * (1.5D - kof1m), 1.75D))), 255.0D);
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
        if (entRotate || Math.abs(entity.prevPosX - entity.posX) > 0.01D || Math.abs(entity.prevPosZ - entity.posZ) > 0.01D) {
          entMoving = true;
          movDis = Math.pow(Math.pow(entity.prevPosX - entity.posX, 2.0D) + Math.pow(entity.prevPosZ - entity.posZ, 2.0D), 0.5D);
          movCof = movDis * 10.0D;
          movCof = Math.max(Math.min(32.0D / (1.0D + movCof), 32.0D), 16.0D);
          movKofDiv = 1.0D + movDis / 2.0D;
          if (kof1m < 0.9D && kof1m != 0.0D && 
            entRotate)
            movKofDiv += mr_kof * 0.005D; 
        } 
        if (Affect) {
          entity.motionX = 0.0D;
          entity.motionZ = 0.0D;
          if (entity.motionY > -0.1D) {
            entity.motionY = 0.0D;
          } else {
            entity.motionY /= 2.0D;
          } 
        } 
        if (world.getTotalWorldTime() % 64L == 0L || (entMoving && world.getTotalWorldTime() % Math.max((int)Math.floor(movCof), 1) == 0L) || (entJump && world.getTotalWorldTime() % 8L == 0L) || entSplash) {
          if (entSplash && 
            kof2 > 1.5D)
            world.playSoundEffect(entity.posX, EposY, entity.posZ, "step.grass", 1.0F, world.rand.nextFloat() * 0.15F + 0.1F); 
          if (!entSplash && entMoving && 
            world.rand.nextInt(2) == 0) {
            world.playSoundEffect(entity.posX, EposY, entity.posZ, "step.grass", 0.75F, world.rand.nextFloat() * 0.15F + 0.1F);
            world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.magmacube.jump", 0.25F, 0.25F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F);
          } 
          if (entJump)
            world.playSoundEffect(entity.posX, EposY, entity.posZ, "dig.grass", 0.15F, 0.25F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F); 
          if (!entJump && !entMoving && !entSplash && 
            world.rand.nextInt(5) == 0)
            world.playSoundEffect(entity.posX, EposY, entity.posZ, "step.grass", 0.75F, world.rand.nextFloat() * 0.15F + 0.1F); 
        } 
        if (!(entity instanceof EntityPlayer) && 
          !Affect) {
          entity.onGround = true;
          entity.fallDistance = 0.0F;
          return;
        } 
        if (world.getBlock(x, y + 2, z) != this || (world.getBlock(x, y + 2, z) == this && world.getBlock(x, y + 3, z) == this)) {
          if (world.getBlock(x, y + 2, z) == this && world.getBlock(x, y + 3, z) == this)
            kof1 = 0.001D; 
          double mys = 0.0D;
          if (!(entity instanceof EntityPlayer))
            mys = 0.0D; 
          if (entJump && 
            kof1 > -0.075D && 
            world.rand.nextInt(1) == 0) {
            if (MFQM.suctionWorldCheck(entity, world, prevVel))
              entity.motionY -= Math.min(0.05D * (Math.min(0.5D, kof1m) + 1.0D), 1.0D); 
            world.playSound(entity.posX, EposY, entity.posZ, "mob.magmacube.jump", 0.15F, 0.25F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F, false);
            world.playSound(entity.posX, EposY, entity.posZ, "dig.grass", 0.1F, 0.25F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F, false);
          } 
          if (kof1 > -0.075D && 
            EposY - EprevPosY > 0.001D && 
            world.rand.nextInt(Math.max((int)Math.floor(kof1m * 2.0D), 1)) == 0) {
            if (MFQM.suctionWorldCheck(entity, world, prevVel))
              entity.motionY -= Math.min(0.025D * mtdkof * (Math.min(0.5D, kof1m) + 1.0D), 1.0D); 
            world.playSound(entity.posX, EposY, entity.posZ, "mob.magmacube.jump", 0.15F, 0.25F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F, false);
            world.playSound(entity.posX, EposY, entity.posZ, "dig.grass", 0.1F, 0.25F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F, false);
          } 
          double suc = 5.0D;
          if (world.getBlock(x, y - 1, z).getMaterial().isSolid())
            suc = 25.0D; 
          double xx = x + 0.5D;
          double zz = z + 0.5D;
          double disq = Math.pow(Math.pow(entity.posX - xx, 2.0D) + Math.pow(entity.posZ - zz, 2.0D), 0.5D);
          if (disq < 0.5D && 
            disq >= 0.075D) {
            entity.motionX += (xx - entity.posX) / Math.max(suc, kof1m * 25.0D);
            entity.motionZ += (zz - entity.posZ) / Math.max(suc, kof1m * 25.0D);
            if (Math.pow(Math.pow(entity.motionX, 2.0D) + Math.pow(entity.motionZ, 2.0D), 0.5D) > 0.005D) {
              movKofDiv = Math.max(movKofDiv / 10.0D, 1.0D);
              if (!entRotate) {
                entMoving = false;
              } else {
                movKofDiv += mr_kof * 0.005D;
              } 
            } 
          } 
          if (kof1 >= 1.2D) {
            if (!entSplash) {
              double a1 = 0.07485D;
              entity.motionY += a1 / Math.max((movKofDiv - 1.0D) * Math.max(kof1m - 0.5D, 0.75D) * 1.6D * mtdkof * 17.5D, 1.0D) / Math.pow(Math.max(kof1m / 1.25D, 1.0D), 2.0D);
              entity.onGround = false;
              entity.fallDistance = 0.0F;
              if (world.rand.nextInt((int)Math.floor(Math.max(kof1m * 5.0D, 1.0D))) == 0) {
                MFQM.setStuckEffect((EntityLivingBase)entity, mr_kof);
                entity.setInWeb();
              } 
              if (world.rand.nextInt(Math.max((int)Math.floor(50.0D - Math.pow(Math.max(kof1m, 0.0D), 1.5D)), 1)) == 0)
                entity.onGround = true; 
            } 
          } else {
            if (kof1 > -0.075D && 
              EposY - EprevPosY > 0.001D && 
              world.rand.nextInt(Math.max((int)Math.floor(kof1m * 3.0D), 10)) == 0 && 
              MFQM.suctionWorldCheck(entity, world, prevVel)) {
              entity.motionY -= Math.min(0.035D * mtdkof * (Math.min(0.5D, kof1m) + 1.0D), 1.0D);
              world.playSound(entity.posX, EposY, entity.posZ, "mob.magmacube.jump", 0.15F, 0.25F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F, false);
              world.playSound(entity.posX, EposY, entity.posZ, "dig.grass", 0.1F, 0.25F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F, false);
            } 
            if (kof1 >= 0.9D) {
              double a1 = 0.075D;
              a1 = 0.07485D;
              entity.motionY += a1 / Math.max((movKofDiv - 1.0D) * Math.max(kof1m - 0.5D, 0.75D) * 1.6D, 1.0D) / Math.pow(Math.max(kof1m / 1.25D, 1.0D), 2.0D);
              MFQM.setStuckEffect((EntityLivingBase)entity, mr_kof);
              entity.onGround = false;
              entity.fallDistance = 0.0F;
              if (world.rand.nextInt((int)Math.floor(Math.max(kof1m * 5.0D / 2.0D, 1.0D))) == 0)
                entity.setInWeb(); 
              if (world.rand.nextInt(Math.max((int)Math.floor(50.0D - Math.pow(Math.max(kof1m, 0.0D), 1.5D)), 1)) == 0)
                entity.onGround = true; 
            } else {
              double a1 = 0.0725D + mys;
              if (world.rand.nextInt(Math.max((int)Math.floor(50.0D - Math.pow(Math.max(kof1m, 0.0D), 1.5D)), 1)) == 0)
                entity.onGround = true; 
              if (MFQM.isTrulySink(entity, kof1m))
                MFQM.setStuckEffect((EntityLivingBase)entity, mr_kof); 
              entity.fallDistance = 0.0F;
              entity.setInWeb();
              if (kof1 >= 0.5D) {
                if (entMoving || world.rand.nextInt(Math.min(Math.max((int)Math.floor(75.0D - kof1 * 25.0D), 1), 75)) == 0) {
                  if (!entMoving) {
                    entity.motionY += a1 / Math.max(2.0D, 5.0D - kof1);
                  } else if (world.rand.nextInt(Math.min(Math.max((int)Math.floor(75.0D - kof1 * 25.0D), 1), 10)) == 0) {
                    entity.motionY += a1 / Math.max(2.0D, 5.0D - kof1);
                  } else {
                    entity.motionY += a1;
                  } 
                } else {
                  entity.motionY += a1;
                } 
              } else {
                entity.setInWeb();
                if (kof1 > -0.075D) {
                  if (entMoving || world.rand.nextInt(Math.min(Math.max((int)Math.floor(75.0D - kof1 * 25.0D), 1), 75)) == 0) {
                    if (!entMoving) {
                      entity.motionY -= 0.25D;
                    } else if (world.rand.nextInt(Math.min(Math.max((int)Math.floor(75.0D - kof1 * 25.0D), 1), 35)) == 0) {
                      entity.motionY -= 0.25D;
                    } else {
                      entity.motionY -= 0.125D;
                    } 
                  } else {
                    entity.motionY += a1;
                  } 
                } else {
                  entity.motionY += 0.079D;
                } 
                if (kof1 < 0.45D && 
                  MFQM.isTrulySink(entity, kof1m))
                  MFQM.setStuckEffect((EntityLivingBase)entity, 255); 
              } 
            } 
          } 
          if (world.getTotalWorldTime() % Math.max(1.0D + Math.floor(Math.max(kof1 * 10.0D, 0.0D)), 1.0D) == 0.0D)
            entity.onGround = false; 
          if (entity.isInWater()) {
            boolean jump = false;
            try {
              Field field2 = Fields.findField(EntityLivingBase.class, boolean.class, 2);
              field2.setAccessible(true);
              jump = field2.getBoolean(entity);
            } catch (Exception e) {
              e.printStackTrace();
            } 
            if (jump && 
              kof1 > -0.075D)
              entity.motionY -= 0.0375D; 
            entity.motionY -= 0.06D;
          } 
        } else {
          entity.setInWeb();
          if (kof1 < 0.5D && 
            MFQM.isTrulySink(entity, kof1m))
            MFQM.setStuckEffect((EntityLivingBase)entity, 255); 
        } 
        MFQM.antiHoldJumpScript(entity, kof1, false);
      } else {
        entity.setInWeb();
      }  
  }
  
  public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
    return null;
  }
  
  public void onBlockAdded(World par1World, int par2, int par3, int par4) {
    checkMeta(par1World, par2, par3, par4);
    par1World.scheduleBlockUpdate(par2, par3, par4, this, tickRate(par1World));
  }
  
  public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5) {
    checkMeta(par1World, par2, par3, par4);
    par1World.scheduleBlockUpdate(par2, par3, par4, this, tickRate(par1World));
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int par1, int par2) {
    int meta = par2;
    switch (meta) {
      case 0:
        return (par1 == 1) ? this.theQIIcon : ((par1 == 0) ? this.theQIIcon : this.theFIIcon);
      case 1:
        return this.theQIIcon;
      case 2:
        return (par1 == 1) ? this.theQIIcon : ((par1 == 0) ? this.theQIIcon : this.theFIIcon);
      case 3:
        return (par1 == 1) ? this.theQIIcon : ((par1 == 0) ? this.theQIIcon : this.theFIIcon);
      case 4:
        return this.theQIIcon;
      case 5:
        return this.theQIIcon;
      case 6:
        return this.theQIIcon;
    } 
    return this.theQIIcon;
  }
  
  public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
    int meta = world.getBlockMetadata(x, y, z);
    if ((meta == 0 || meta == 2 || meta == 3) && 
      side == 0)
      return false; 
    return true;
  }
  
  public void updateTick(World world, int x, int y, int z, Random rand) {
    checkLavaNear(world, x, y, z);
    if (!world.getBlock(x, y - 1, z).getMaterial().isSolid() && world.getBlock(x, y - 1, z).getMaterial().isReplaceable() && !world.getBlock(x, y - 1, z).getMaterial().isLiquid()) {
      world.setBlock(x, y - 1, z, this, 0, 3);
      world.setBlockToAir(x, y, z);
      checkMeta(world, x, y - 1, z);
      return;
    } 
    checkMeta(world, x, y, z);
    super.updateTick(world, x, y, z, rand);
  }
  
  public void checkMeta(World world, int x, int y, int z) {
    int meta1 = world.getBlockMetadata(x, y, z);
    int meta2 = meta1;
    if (world.getBlock(x, y - 1, z) == Blocks.air) {
      world.setBlock(x, y, z, this, 0, 3);
      return;
    } 
    if (world.getBlock(x, y - 1, z).getMaterial().isLiquid()) {
      if (world.getBlock(x, y + 1, z) != this) {
        world.setBlock(x, y, z, this, 2, 3);
        return;
      } 
      world.setBlock(x, y, z, this, 3, 3);
      return;
    } 
    if (world.getBlock(x, y - 1, z) == this) {
      meta2 = world.getBlockMetadata(x, y - 1, z);
      if (world.getBlock(x, y + 1, z) != this) {
        switch (meta2) {
          case 0:
            world.setBlock(x, y, z, this, 1, 3);
            return;
          case 1:
            world.setBlock(x, y, z, this, 1, 3);
            return;
          case 2:
            world.setBlock(x, y, z, this, 6, 3);
            return;
          case 3:
            world.setBlock(x, y, z, this, 6, 3);
            return;
          case 4:
            world.setBlock(x, y, z, this, 1, 3);
            return;
          case 5:
            world.setBlock(x, y, z, this, 6, 3);
            return;
          case 6:
            world.setBlock(x, y, z, this, 6, 3);
            return;
        } 
      } else {
        switch (meta2) {
          case 0:
            world.setBlock(x, y, z, this, 4, 3);
            return;
          case 1:
            world.setBlock(x, y, z, this, 4, 3);
            return;
          case 2:
            world.setBlock(x, y, z, this, 5, 3);
            return;
          case 3:
            world.setBlock(x, y, z, this, 5, 3);
            return;
          case 4:
            world.setBlock(x, y, z, this, 4, 3);
            return;
          case 5:
            world.setBlock(x, y, z, this, 5, 3);
            return;
          case 6:
            world.setBlock(x, y, z, this, 5, 3);
            return;
        } 
      } 
    } 
    if (world.getBlock(x, y + 1, z) != this) {
      world.setBlock(x, y, z, this, 1, 3);
      return;
    } 
    world.setBlock(x, y, z, this, 4, 3);
  }
  
  public void checkLavaNear(World world, int x, int y, int z) {
    int xx = x;
    int yy = y;
    int zz = z;
    int met = 0;
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
    } 
    if (isLavaNear) {
      if (world.rand.nextInt(5) == 0) {
        world.setBlockToAir(x, y, z);
        world.playSoundEffect((x + 0.5F), (y + 0.5F), (z + 0.5F), "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
        for (int l = 0; l < 8; l++)
          world.spawnParticle("largesmoke", x + Math.random(), y + 1.2D, z + Math.random(), 0.0D, 0.0D, 0.0D); 
        return;
      } 
      world.scheduleBlockUpdate(x, y, z, this, 5);
    } 
  }
  
  public float getPlayerRelativeBlockHardness(EntityPlayer par1EntityPlayer, World par2World, int par3, int par4, int par5) {
    if (!((Entity)par1EntityPlayer).onGround)
      return 2.5E-5F; 
    if (par1EntityPlayer.getCurrentEquippedItem() != null && par1EntityPlayer.getCurrentEquippedItem().getItem() == Items.shears)
      return 0.025F; 
    float f = getBlockHardness(par2World, par3, par4, par5);
    return ForgeHooks.blockStrength(this, par1EntityPlayer, par2World, par3, par4, par5);
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister par1IIconRegister) {
    this.theQIIcon = par1IIconRegister.registerIcon("morefunquicksandmod:Moss_up");
    this.theFIIcon = par1IIconRegister.registerIcon("morefunquicksandmod:Moss_front");
  }
  
  public boolean canDropFromExplosion(Explosion par1Explosion) {
    return false;
  }
  
  public boolean canHarvestBlock(EntityPlayer player, int meta) {
    return true;
  }
  
  public Item getItemDropped(int par1, Random random, int par3) {
    return Item.getItemFromBlock(Blocks.vine);
  }
  
  public int quantityDropped(Random par1Random) {
    return 5 + par1Random.nextInt(5);
  }
  
  public void harvestBlock(World world, EntityPlayer entityPlayer, int x, int y, int z, int par6) {
    if (entityPlayer.getCurrentEquippedItem() != null && entityPlayer.getCurrentEquippedItem().getItem() == Items.shears)
      return; 
    if (EnchantmentHelper.getSilkTouchModifier((EntityLivingBase)entityPlayer)) {
      MFQM.dropItem(world, x, y, z, new ItemStack(this));
      world.markBlockForUpdate(x, y, z);
      return;
    } 
    super.harvestBlock(world, entityPlayer, x, y, z, par6);
  }
  
  public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
    return true;
  }
  
  public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
    ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
    ret.add(new ItemStack(this));
    return ret;
  }
  
  public boolean isNormalCube() {
    return false;
  }
  
  public void checkEntityUnder(Entity ent) {
    if (MFQM.isEntityInsideOfBlockM(ent, this) && 
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
  
  @SideOnly(Side.CLIENT)
  public int getBlockColor() {
    if (MFQM.AOA) {
      if ((Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.AOADimAbyss)
        return 14745600; 
      if ((Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.AOADimLBorean)
        return 52200; 
    } 
    double d0 = 0.5D;
    double d1 = 1.0D;
    int col = ColorizerGrass.getGrassColor(d0, d1);
    return col;
  }
  
  @SideOnly(Side.CLIENT)
  public int getRenderColor(int par1) {
    if (MFQM.AOA) {
      if ((Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.AOADimAbyss)
        return 14745600; 
      if ((Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.AOADimLBorean)
        return 52200; 
    } 
    return 16777215;
  }
  
  @SideOnly(Side.CLIENT)
  public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
    if (MFQM.AOA) {
      if ((Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.AOADimAbyss)
        return 14745600; 
      if ((Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.AOADimLBorean)
        return Color.HSBtoRGB((par2 + par4) * 0.01F, 1.0F, 1.0F); 
    } 
    int col = par1IBlockAccess.getBiomeGenForCoords(par2, par4).getBiomeGrassColor(par2, 0, par4);
    return col;
  }
}
