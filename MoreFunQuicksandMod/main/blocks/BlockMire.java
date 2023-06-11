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

public class BlockMire extends Block {
  @SideOnly(Side.CLIENT)
  private IIcon[] IIconArray;
  
  @SideOnly(Side.CLIENT)
  private IIcon IIconSide0;
  
  @SideOnly(Side.CLIENT)
  private IIcon IIconSide1;
  
  private int maxOpacity;
  
  private int lastOpacity;
  
  private int incOpacity;
  
  public BlockMire(int mopc, int lopc, int iopc) {
    super(Material.sponge);
    setHardness(2.5F);
    setStepSound(Block.soundTypeSand);
    setResistance(1000.0F);
    setTickRandomly(true);
    if (MFQM.QSOpacity) {
      setLightOpacity(255);
    } else {
      setLightOpacity(3);
    } 
    setUnlocalizedName("Mire");
    setCreativeTab(MFQM.tabMFQM);
    this.maxOpacity = mopc;
    this.lastOpacity = lopc;
    this.incOpacity = iopc;
  }
  
  public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
    return false;
  }
  
  public int Breaking(World world, int x, int y, int z, int mtd) {
    if (world.getBlock(x, y, z) == this) {
      int smtd = world.getBlockMetadata(x, y, z);
      world.setBlockMetadataWithNotify(x, y, z, Math.min(15, Math.max(mtd, smtd)), 2);
      return 1;
    } 
    if (world.getBlock(x, y, z) == MFQM.MorassBlock) {
      int smtd = world.getBlockMetadata(x, y, z);
      if (smtd < 9) {
        world.setBlockMetadataWithNotify(x, y, z, 9, 2);
        return 1;
      } 
      world.setBlock(x, y, z, MFQM.MireBlock, 10, 3);
      return 2;
    } 
    return 0;
  }
  
  public void updateTick(World world, int x, int y, int z, Random random) {
    int mtd = world.getBlockMetadata(x, y, z);
    Material mat = world.getBlock(x, y + 1, z).getMaterial();
    if (world.isRaining()) {
      if ((mat == Material.air || mat == Material.water || mat == Material.lava) && 
        world.isRainingAt(x, y + 1, z)) {
        mtd = 10 + world.rand.nextInt(4);
        world.setBlockMetadataWithNotify(x, y, z, mtd, 2);
      } 
    } else {
      int xx = x;
      int yy = y;
      int zz = z;
      boolean isWaterNear = false;
      boolean isLavaNear = false;
      boolean isMoorNear = false;
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
          isLavaNear = true;
          break;
        } 
        if (world.getBlock(xx, yy, zz).getMaterial() == Material.water) {
          isWaterNear = true;
          break;
        } 
        if (world.getBlock(xx, yy, zz) == MFQM.MorassBlock) {
          isMoorNear = true;
          break;
        } 
      } 
      if (isLavaNear) {
        world.setBlock(x, y, z, Blocks.dirt, 0, 3);
        world.playSoundEffect((x + 0.5F), (y + 0.5F), (z + 0.5F), "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
        for (int l = 0; l < 8; l++)
          world.spawnParticle("largesmoke", x + Math.random(), y + 1.2D, z + Math.random(), 0.0D, 0.0D, 0.0D); 
        return;
      } 
      if (isMoorNear && 
        mtd == 0 && 
        world.rand.nextInt(10) == 0 && 
        !world.getBlock(x, y + 1, z).getMaterial().isOpaque() && world.getBlockLightValue(x, y + 1, z) >= 4 && world.getBlockLightOpacity(x, y + 1, z) <= 2) {
        world.setBlock(x, y, z, MFQM.MorassBlock, 9, 3);
        return;
      } 
      if (world.getBlock(x, y + 1, z).getMaterial() == Material.water && 
        mtd < 10) {
        mtd += 3;
        world.setBlockMetadataWithNotify(x, y, z, mtd, 2);
      } 
    } 
    if (world.getBlock(x, y - 1, z) == Blocks.air) {
      world.setBlock(x, y, z, MFQM.LiquidMireBlock, 0, 3);
      return;
    } 
    if (world.getBlock(x, y + 1, z) == MFQM.MireBlock || world.getBlock(x, y + 1, z) == MFQM.MorassBlock) {
      if (mtd == 0) {
        mtd = world.rand.nextInt(10) + 3;
        world.setBlockMetadataWithNotify(x, y, z, mtd, 2);
      } 
    } else if (mtd > 5) {
      world.setBlockMetadataWithNotify(x, y, z, mtd - 2, 2);
    } else if (mtd > 0) {
      world.setBlockMetadataWithNotify(x, y, z, mtd - 1, 2);
    } 
  }
  
  public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
    double EposY = entity.posY;
    double EprevPosY = entity.prevPosY;
    double prevVel = entity.motionY;
    double oy = y - EposY + 1.0D;
    double oy2 = y - EprevPosY + 1.0D;
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
      oy = y - EposY - 0.5D;
      oy2 = y - EprevPosY - 0.5D;
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
      boolean Affect = true;
      if (entity instanceof MoreFunQuicksandMod.main.entity.EntityMuddyBlob)
        Affect = false; 
      if (MFQM.TBL)
        for (Class tmpClass : MFQM.TBLMobs) {
          if (tmpClass.isAssignableFrom(entity.getClass()))
            Affect = false; 
        }  
      if (!(entity instanceof EntityPlayer) && 
        MFQM.AOA) {
        if (world.provider.dimensionId == MFQM.AOADimGardencia)
          Affect = false; 
        if (world.provider.dimensionId == MFQM.AOADimGreckon)
          Affect = false; 
      } 
      if (Affect)
        checkEntityUnder(entity); 
      if (entity instanceof EntityPlayer)
        checkPlayerMuddy((EntityPlayer)entity, x, y, z, world); 
      boolean boots = false;
      boolean bootsIsFloat = false;
      int bootsCof = 1;
      int mtd = world.getBlockMetadata(x, y, z);
      boolean entJump = false;
      boolean entMoving = false;
      boolean entSplash = false;
      boolean entRotate = false;
      boolean entUnderSurface = false;
      float mtdkof = (1 + Math.min(mtd * 2, 12));
      double movDis = 1.0D;
      double movCof = 16.0D;
      double movKofDiv = 1.0D;
      int mr_kof = (int)Math.min(5.0D + Math.floor(Math.max(0.0D, Math.pow((mtdkof * 3.0F) * (1.5D - kof1m), 1.6D))), 145.0D);
      if (entity instanceof EntityPlayer && (
        (EntityPlayer)entity).getCurrentArmor(0) != null && (
        (EntityPlayer)entity).getCurrentArmor(0).getItem() == MFQM.WadingBoots) {
        boots = true;
        bootsIsFloat = true;
        bootsCof = 2;
      } 
      if (EposY - EprevPosY > 0.2D)
        entJump = true; 
      if (entity.motionY < -0.1D / Math.max(1.0F, mtdkof / 2.0F))
        entSplash = true; 
      if (kof1 < 1.2D && kof1m != 0.0D)
        entUnderSurface = true; 
      if (entUnderSurface) {
        if (!(entity instanceof EntityPlayer) && 
          ((EntityLivingBase)entity).prevRotationYaw != ((EntityLivingBase)entity).rotationYaw)
          entRotate = true; 
        if (entity instanceof EntityPlayer && 
          world.isRemote && 
          Math.abs(MFQM.SIRenderYawPre - MFQM.SIRenderYaw) > 10.0D)
          entRotate = true; 
      } 
      if (entRotate || Math.abs(entity.prevPosX - entity.posX) > 0.01D || Math.abs(entity.prevPosZ - entity.posZ) > 0.01D) {
        entMoving = true;
        movDis = Math.pow(Math.pow(entity.prevPosX - entity.posX, 2.0D) + Math.pow(entity.prevPosZ - entity.posZ, 2.0D), 0.5D);
        movCof = movDis * 10.0D;
        movCof = Math.max(Math.min(32.0D / (1.0D + movCof), 32.0D), 16.0D);
        movKofDiv = 1.0D + movDis / 2.0D;
        if (kof1m < 0.9D && kof1m != 0.0D && 
          entRotate)
          movKofDiv += mr_kof * 0.004D; 
      } 
      entity.motionX = 0.0D;
      entity.motionZ = 0.0D;
      if (entity.motionY > -0.1D) {
        entity.motionY = 0.0D;
      } else {
        entity.motionY /= 2.0D;
      } 
      if (entity instanceof MoreFunQuicksandMod.main.entity.EntityMuddyBlob) {
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
      if (!(entity instanceof EntityPlayer) && 
        !Affect) {
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
      if (kof1 < 1.3D || mtd > 5) {
        bootsIsFloat = false;
        bootsCof = 1;
      } 
      if (world.getTotalWorldTime() % 128L * bootsCof == 0L || (entMoving && world.getTotalWorldTime() % (int)Math.floor(movCof) == 0L) || (entJump && world.getTotalWorldTime() % 8L == 0L) || entSplash) {
        if (!world.isRemote) {
          if (world.getTotalWorldTime() % 32L == 0L || entUnderSurface)
            if (mtd < 15) {
              if (world.rand.nextInt(1 + (int)Math.floor((mtd / 2))) == 0 || mtd < 2 || (!entJump && !entMoving && !entSplash)) {
                mtd++;
                mtd = Math.min(15, mtd);
                world.setBlockMetadataWithNotify(x, y, z, mtd, 2);
              } 
            } else {
              mtd = 13;
              world.setBlockMetadataWithNotify(x, y, z, 13, 2);
            }  
          int xx = x - 1;
          int zz = z - 1;
          xx = x - 1;
          if (world.rand.nextInt(4) == 0)
            Breaking(world, xx, y, zz, (int)Math.floor((mtd / 4))); 
          xx = x;
          if (world.rand.nextInt(2) == 0)
            Breaking(world, xx, y, zz, (int)Math.floor((mtd / 2))); 
          xx = x + 1;
          if (world.rand.nextInt(4) == 0)
            Breaking(world, xx, y, zz, (int)Math.floor((mtd / 4))); 
          zz = z;
          if (world.rand.nextInt(2) == 0)
            Breaking(world, xx, y, zz, (int)Math.floor((mtd / 2))); 
          xx = x;
          if (world.rand.nextInt(2) == 0)
            Breaking(world, xx, y, zz, (int)Math.floor((mtd / 2))); 
          xx = x - 1;
          if (world.rand.nextInt(2) == 0)
            Breaking(world, xx, y, zz, (int)Math.floor((mtd / 2))); 
          zz = z + 1;
          if (world.rand.nextInt(4) == 0)
            Breaking(world, xx, y, zz, (int)Math.floor((mtd / 4))); 
          xx = x;
          if (world.rand.nextInt(2) == 0)
            Breaking(world, xx, y, zz, (int)Math.floor((mtd / 2))); 
          xx = x + 1;
          if (world.rand.nextInt(4) == 0)
            Breaking(world, xx, y, zz, (int)Math.floor((mtd / 4))); 
        } 
        if (entSplash) {
          if (kof2 > 1.5D)
            world.playSoundEffect(entity.posX, EposY, entity.posZ, "game.player.swim", 0.25F, world.rand.nextFloat() * 0.25F + 0.1F); 
          if (entity instanceof EntityPlayer && 
            world.rand.nextInt(2) == 0) {
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, this, -1);
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, this, -1);
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, this, -1);
          } 
        } 
        if (!entSplash && entMoving) {
          if (world.rand.nextInt(2) == 0)
            world.playSoundEffect(entity.posX, EposY, entity.posZ, "game.player.swim", 0.15F, world.rand.nextFloat() * 0.25F + 0.1F); 
          if (world.rand.nextInt(5) == 0)
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, this, -1); 
        } 
        if (entJump) {
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.slime.attack", 0.25F, world.rand.nextFloat() * 0.25F + 0.25F);
          if (entity instanceof EntityPlayer && 
            world.rand.nextInt(2) == 0) {
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, this, -1);
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, this, -1);
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, this, -1);
          } 
        } 
        if (entUnderSurface && 
          !entJump && !entMoving && !entSplash && 
          world.rand.nextInt(5) == 0)
          if (world.rand.nextInt(5) == 0) {
            world.playSoundEffect(entity.posX, EposY, entity.posZ, "liquid.water", 0.5F, world.rand.nextFloat() * 0.15F + 0.1F);
          } else {
            world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.step", 0.25F, world.rand.nextFloat() * 0.15F + 0.1F);
          }  
      } 
      if (world.getTotalWorldTime() % 16L == 0L && 
        world.rand.nextInt(10) == 0)
        MFQM.spawnBodyBubble(world, entity, x, y, z, this, -1); 
      if ((world.getBlock(x, y + 1, z) != this && world.getBlock(x, y + 1, z) != MFQM.MorassBlock) || ((world.getBlock(x, y + 1, z) == this || world.getBlock(x, y + 1, z) == MFQM.MorassBlock) && (world.getBlock(x, y + 2, z) == this || world.getBlock(x, y + 2, z) == MFQM.MorassBlock))) {
        if ((world.getBlock(x, y + 1, z) == this || world.getBlock(x, y + 1, z) == MFQM.MorassBlock) && (world.getBlock(x, y + 2, z) == this || world.getBlock(x, y + 2, z) == MFQM.MorassBlock))
          kof1 = 0.001D; 
        double mys = 0.0D;
        if (!(entity instanceof EntityPlayer))
          mys = 0.0D; 
        if (entJump && 
          world.rand.nextInt(1) == 0 && 
          MFQM.suctionWorldCheck(entity, world, prevVel)) {
          entity.motionY -= 0.025D * mtdkof * (Math.min(0.75D, kof1m) + 1.0D);
          world.playSound(entity.posX, EposY, entity.posZ, "mob.magmacube.jump", 0.25F, 0.25F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F, false);
        } 
        double aaq = 0.0D;
        if (!(entity instanceof EntityPlayer))
          aaq = -0.1D; 
        if (kof1 >= 1.2D + aaq) {
          if (EposY - EprevPosY > 0.1D && 
            world.rand.nextInt(5) == 0 && 
            MFQM.suctionWorldCheck(entity, world, prevVel)) {
            entity.motionY -= 0.025D * mtdkof * (Math.min(0.75D, kof1m) + 1.0D);
            world.playSound(entity.posX, EposY, entity.posZ, "mob.magmacube.jump", 0.25F, 0.25F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F, false);
          } 
          boolean isRandomSuck = false;
          if (world.rand.nextInt((int)(Math.floor(1000.0D) / mtdkof)) == 0)
            isRandomSuck = true; 
          if (!entSplash) {
            entity.fallDistance = 0.0F;
            entity.onGround = true;
            if (kof1 < 1.45D + aaq) {
              if (!isRandomSuck && entMoving) {
                if (kof1 > 1.25D + aaq) {
                  entity.motionY += 0.085D + mys;
                } else {
                  if (!(entity instanceof EntityPlayer)) {
                    entity.motionY = 0.0D;
                    entity.motionY += 0.09D;
                  } else {
                    entity.motionY += 0.1D;
                  } 
                  if (EposY - EprevPosY < -0.001D)
                    world.playSound(entity.posX, EposY, entity.posZ, "mob.magmacube.jump", 0.25F, 0.25F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F, false); 
                } 
              } else {
                if (!isRandomSuck) {
                  double a1 = (0.0725D + mys) / mtdkof;
                  entity.motionY += a1;
                } else {
                  entity.motionY -= 0.1D;
                  MFQM.setStuckEffect((EntityLivingBase)entity, 255);
                  entity.setInWeb();
                } 
                entity.onGround = false;
                entity.setInWeb();
                if (world.rand.nextInt(Math.max((int)Math.floor(Math.pow(Math.max(kof1m, 1.0D), 5.0D)), 1)) == 0)
                  MFQM.setStuckEffect((EntityLivingBase)entity, 255); 
              } 
            } else {
              entity.motionY = 0.0D;
            } 
          } 
        } else {
          if (EposY - EprevPosY > 0.001D && 
            world.rand.nextInt(Math.max((int)Math.floor(kof1m * 5.0D), 10)) == 0 && 
            MFQM.suctionWorldCheck(entity, world, prevVel)) {
            entity.motionY -= 0.025D * mtdkof * (Math.min(0.75D, kof1m) + 1.0D);
            world.playSound(entity.posX, EposY, entity.posZ, "mob.magmacube.jump", 0.25F, 0.25F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F, false);
          } 
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
                movKofDiv += mr_kof * 0.004D;
              } 
            } 
          } 
          entity.onGround = false;
          if (world.rand.nextInt(Math.max((int)Math.floor(50.0D - Math.pow(Math.max(kof1m, 0.0D), 1.5D) * 20.0D), 1)) == 0)
            entity.onGround = true; 
          if (MFQM.isTrulySink(entity, kof1m))
            MFQM.setStuckEffect((EntityLivingBase)entity, mr_kof); 
          entity.fallDistance = 0.0F;
          if (kof1 >= 0.9D) {
            if (world.rand.nextInt((int)Math.floor(Math.max(kof1m * 5.0D / mtdkof, 1.0D))) == 0) {
              entity.setInWeb();
              if (!entMoving) {
                entity.motionY += (0.0725D + mys) * 1.01D;
              } else {
                entity.motionY += (0.0725D + mys) * 1.01D / (movKofDiv + 0.075D);
              } 
            } else if (!entMoving) {
              entity.motionY += (0.075D + mys) * 1.01D;
            } else {
              entity.motionY += (0.075D + mys) * 1.01D / (movKofDiv + 0.075D);
            } 
          } else {
            double a2 = 1.01D;
            if (world.getTotalWorldTime() % (int)Math.floor(Math.min(3.0D, Math.max(8.0D - kof1 * 8.0D, 1.0D))) == 0L) {
              a2 = 1.01D;
            } else {
              a2 = 1.05D;
            } 
            if (kof1 >= 0.5D) {
              if (world.rand.nextInt((int)Math.floor(Math.max(kof1m * 5.0D / mtdkof, 1.0D))) == 0) {
                entity.setInWeb();
                if (!entMoving) {
                  entity.motionY += (0.0725D + mys) * a2;
                } else {
                  entity.motionY += (0.0725D + mys) * a2 / (movKofDiv + 0.075D);
                } 
              } else if (!entMoving) {
                entity.motionY += (0.075D + mys) * a2;
              } else {
                entity.motionY += (0.075D + mys) * a2 / (movKofDiv + 0.075D);
              } 
            } else {
              entity.setInWeb();
              if (!entMoving) {
                entity.motionY += (0.0725D + mys) * a2;
              } else {
                entity.motionY += (0.0725D + mys) * a2 / (movKofDiv + 0.075D);
              } 
              if (kof1 < 0.45D && 
                MFQM.isTrulySink(entity, kof1m))
                MFQM.setStuckEffect((EntityLivingBase)entity, 145); 
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
          MFQM.setStuckEffect((EntityLivingBase)entity, 145); 
      } 
      MFQM.antiHoldJumpScript(entity, kof1, true);
    } else if (kof1 < 1.25D) {
      entity.setInWeb();
    } 
  }
  
  public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
    return null;
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
    MFQM.dropItem(world, x, y, z, new ItemStack(Blocks.dirt));
    world.setBlock(x, y, z, MFQM.LiquidMireBlock, 4, 3);
  }
  
  public boolean canDropFromExplosion(Explosion par1Explosion) {
    return false;
  }
  
  public boolean canHarvestBlock(EntityPlayer player, int meta) {
    return true;
  }
  
  public float getPlayerRelativeBlockHardness(EntityPlayer par1EntityPlayer, World par2World, int par3, int par4, int par5) {
    if (!((Entity)par1EntityPlayer).onGround)
      return 3.5E-5F; 
    float f = getBlockHardness(par2World, par3, par4, par5);
    return ForgeHooks.blockStrength(this, par1EntityPlayer, par2World, par3, par4, par5);
  }
  
  @SideOnly(Side.CLIENT)
  public void randomDisplayTick(World world, int x, int y, int z, Random random) {
    if (world.getBlock(x, y + 1, z).getMaterial() == Material.air) {
      int md = world.getBlockMetadata(x, y, z);
      if ((world.rand.nextInt(100) == 0 && md > 0) || (world.rand.nextInt(200) == 0 && md <= 0)) {
        double xx = x + random.nextFloat();
        double zz = z + random.nextFloat();
        float vol = 0.2F;
        if (md > 0 || (md <= 0 && world.rand.nextInt(5) == 0))
          vol = 0.5F; 
        MFQM.spawnQSBubble(world, xx, y + 1.0D, zz, this, md, vol);
      } 
    } 
    if (world.rand.nextInt(1000) == 0)
      world.markBlockForUpdate(x, y, z); 
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int par1, int par2) {
    int ic = par2;
    if (par1 == 1) {
      switch (ic) {
        case 5:
          ic = 2;
          break;
        case 6:
          ic = 3;
          break;
        case 7:
          ic = 4;
          break;
        case 8:
          ic = 2;
          break;
        case 9:
          ic = 3;
          break;
        case 10:
          ic = 4;
          break;
        case 11:
          ic = 2;
          break;
        case 12:
          ic = 3;
          break;
        case 13:
          ic = 4;
          break;
        case 14:
          ic = 2;
          break;
        case 15:
          ic = 3;
          break;
      } 
      if (ic < 0)
        ic = 0; 
      return this.IIconArray[ic];
    } 
    if (par1 == 0) {
      switch (ic) {
        case 0:
          ic = 4;
          break;
        case 1:
          ic = 2;
          break;
        case 3:
          ic = 3;
          break;
        case 4:
          ic = 4;
          break;
        case 5:
          ic = 2;
          break;
        case 6:
          ic = 3;
          break;
        case 7:
          ic = 4;
          break;
        case 8:
          ic = 2;
          break;
        case 9:
          ic = 3;
          break;
        case 10:
          ic = 4;
          break;
        case 11:
          ic = 2;
          break;
        case 12:
          ic = 3;
          break;
        case 13:
          ic = 4;
          break;
        case 14:
          ic = 2;
          break;
        case 15:
          ic = 3;
          break;
      } 
      if (ic < 0)
        ic = 0; 
      return this.IIconArray[ic];
    } 
    if (par2 < 2)
      return this.IIconSide0; 
    return this.IIconSide1;
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister par1IIconRegister) {
    this.IIconArray = new IIcon[5];
    for (int i = 0; i < this.IIconArray.length; i++)
      this.IIconArray[i] = par1IIconRegister.registerIcon("morefunquicksandmod:Mire" + i); 
    this.IIconSide0 = par1IIconRegister.registerIcon("morefunquicksandmod:Mire_side0");
    this.IIconSide1 = par1IIconRegister.registerIcon("morefunquicksandmod:Mire_side1");
  }
  
  protected boolean canSilkHarvest() {
    return true;
  }
  
  public boolean isNormalCube() {
    return false;
  }
  
  public boolean isOpaqueCube() {
    return true;
  }
  
  public boolean isSolid() {
    return true;
  }
  
  public void checkEntityUnder(Entity ent) {
    if (MFQM.isEntityInsideOfBlock(ent, this) && 
      MFQM.isDrowning(ent)) {
      MFQM.spawnDrowningBubble(ent.worldObj, ent, this, -1);
      if (!ent.worldObj.isRemote && 
        ent.isEntityAlive())
        ent.attackEntityFrom(DamageSource.drown, Math.max(((EntityLivingBase)ent).getMaxHealth() * 0.1F, 2.0F)); 
    } 
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
