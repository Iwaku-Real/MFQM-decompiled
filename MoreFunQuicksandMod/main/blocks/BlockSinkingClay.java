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
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockSinkingClay extends Block {
  @SideOnly(Side.CLIENT)
  private IIcon[] IIconNormalArray;
  
  @SideOnly(Side.CLIENT)
  private IIcon[] IIconBrokenArray;
  
  @SideOnly(Side.CLIENT)
  private IIcon IIconSideNormal;
  
  @SideOnly(Side.CLIENT)
  private IIcon IIconSideBroken;
  
  private int maxOpacity;
  
  private int lastOpacity;
  
  private int incOpacity;
  
  public BlockSinkingClay(int mopc, int lopc, int iopc) {
    super(Material.sponge);
    setHardness(1.2F);
    setStepSound(Block.soundTypeStone);
    setResistance(1000.0F);
    setTickRandomly(true);
    if (MFQM.QSOpacity) {
      setLightOpacity(255);
    } else {
      setLightOpacity(3);
    } 
    setUnlocalizedName("SinkingClay");
    setCreativeTab(MFQM.tabMFQM);
    this.maxOpacity = mopc;
    this.lastOpacity = lopc;
    this.incOpacity = iopc;
  }
  
  private boolean isAboveFreeSpace(int x, int y, int z, World world) {
    int re = 3;
    y++;
    for (int i = 0; i < re; i++) {
      if (world.getBlock(x, y + i, z).getMaterial().isOpaque())
        return false; 
    } 
    return true;
  }
  
  public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
    return false;
  }
  
  public void updateTick(World world, int x, int y, int z, Random random) {
    int mtd = world.getBlockMetadata(x, y, z);
    Material mat = world.getBlock(x, y + 1, z).getMaterial();
    if (world.isRaining()) {
      if (mtd > 6 && (
        mat == Material.air || mat == Material.water || mat == Material.lava) && 
        world.isRainingAt(x, y + 1, z)) {
        if (mtd < 15)
          mtd++; 
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
      } 
      if (isLavaNear) {
        world.setBlock(x, y, z, MFQM.HClayBlock, world.rand.nextInt(5), 3);
        world.playSoundEffect((x + 0.5F), (y + 0.5F), (z + 0.5F), "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
        for (int l = 0; l < 8; l++)
          world.spawnParticle("largesmoke", x + Math.random(), y + 1.2D, z + Math.random(), 0.0D, 0.0D, 0.0D); 
        return;
      } 
      if (world.getBlock(x, y + 1, z).getMaterial() == Material.water && 
        mtd < 13) {
        mtd += 3;
        world.setBlockMetadataWithNotify(x, y, z, mtd, 2);
      } 
    } 
    if (world.getBlock(x, y + 1, z) != this) {
      if (world.rand.nextInt(30) == 0) {
        int inThisBlocks = 0;
        int inMetaBlocks = 0;
        if (world.getBlock(x + 1, y, z) == this) {
          inThisBlocks++;
          if (world.getBlockMetadata(x + 1, y, z) >= 5)
            inMetaBlocks++; 
        } 
        if (world.getBlock(x - 1, y, z) == this) {
          inThisBlocks++;
          if (world.getBlockMetadata(x - 1, y, z) >= 5)
            inMetaBlocks++; 
        } 
        if (world.getBlock(x, y, z - 1) == this) {
          inThisBlocks++;
          if (world.getBlockMetadata(x, y, z - 1) >= 5)
            inMetaBlocks++; 
        } 
        if (world.getBlock(x, y, z + 1) == this) {
          inThisBlocks++;
          if (world.getBlockMetadata(x, y, z + 1) >= 5)
            inMetaBlocks++; 
        } 
        if (inMetaBlocks < 4 && 
          isAboveFreeSpace(x, y, z, world) && 
          mtd >= 5) {
          if (mtd == 5) {
            if (random.nextInt(10) == 0) {
              mtd = world.rand.nextInt(5);
            } else {
              mtd = 0;
            } 
          } else {
            mtd--;
          } 
          world.setBlockMetadataWithNotify(x, y, z, mtd, 2);
        } 
      } 
    } else if (world.rand.nextInt(10) == 0) {
      if (mtd < 15)
        mtd++; 
      if (mtd < 7)
        mtd = 7; 
      world.setBlockMetadataWithNotify(x, y, z, mtd, 2);
    } 
    if (world.rand.nextInt(5) == 0) {
      if (!world.getBlock(x, y - 1, z).getMaterial().isSolid() && world.getBlock(x, y - 1, z).getMaterial().isReplaceable()) {
        world.setBlock(x, y - 1, z, this, mtd, 3);
        world.setBlockToAir(x, y, z);
        return;
      } 
    } else {
      world.scheduleBlockUpdate(x, y, z, this, tickRate(world) * 10);
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
    int mtd = world.getBlockMetadata(x, y, z);
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
      checkEntityUnder(entity);
      if (entity instanceof EntityPlayer)
        checkPlayerMuddy((EntityPlayer)entity, x, y, z, world); 
      boolean boots = false;
      boolean bootsIsFloat = false;
      boolean entJump = false;
      boolean entMoving = false;
      boolean entSplash = false;
      boolean entRotate = false;
      boolean entUnderSurface = false;
      float mtdkof = (1 + (Math.min(mtd, 12) - 4) / 2);
      double movDis = 1.0D;
      double movDisa = 1.0D;
      double movCof = 16.0D;
      double movKofDiv = 1.0D;
      int mr_kof = (int)Math.min(5.0D + Math.floor(Math.max(0.0D, Math.pow((mtdkof * 2.0F) * (1.5D - kof1m), 1.75D))), 135.0D);
      if (mtd > 4) {
        if (entity.motionY < -0.1D / Math.max(1.0F, mtdkof / 2.0F))
          entSplash = true; 
        if (EposY - EprevPosY > 0.2D)
          entJump = true; 
        if (!(entity instanceof EntityPlayer) && 
          ((EntityLivingBase)entity).prevRotationYaw != ((EntityLivingBase)entity).rotationYaw)
          entRotate = true; 
        if (entity instanceof EntityPlayer && 
          world.isRemote && 
          Math.abs(MFQM.SIRenderYawPre - MFQM.SIRenderYaw) > 10.0D)
          entRotate = true; 
        if (entRotate || Math.abs(entity.prevPosX - entity.posX) > 0.0015D || Math.abs(entity.prevPosZ - entity.posZ) > 0.0015D) {
          entMoving = true;
          movDis = Math.pow(Math.pow(entity.prevPosX - entity.posX, 2.0D) + Math.pow(entity.prevPosZ - entity.posZ, 2.0D), 0.5D);
          movCof = movDis * 10.0D;
          movCof = Math.max(Math.min(32.0D / (1.0D + movCof), 32.0D), 16.0D);
          movKofDiv = 1.0D + movDis / 1.5D;
          if (kof1m < 0.9D && kof1m != 0.0D && 
            entRotate)
            movKofDiv += mr_kof * 0.004D; 
        } 
        entity.motionX = 0.0D;
        entity.motionZ = 0.0D;
        if (entity.motionY > -0.1D) {
          entity.motionY = 0.0D;
        } else {
          entity.motionY /= 1.5D;
        } 
      } else {
        movDisa = Math.pow(Math.pow(entity.prevPosX - entity.posX, 2.0D) + Math.pow(entity.prevPosZ - entity.posZ, 2.0D), 0.5D);
        if (entity.motionY < -0.5D)
          entSplash = true; 
        if (kof1 < 1.35D && kof1m != 0.0D)
          entUnderSurface = true; 
        entity.motionX *= 0.9100000262260437D;
        entity.motionZ *= 0.9100000262260437D;
        if (entity.motionY > -0.1D) {
          entity.motionY = 0.0D;
        } else {
          entity.motionY /= 10.0D;
        } 
      } 
      bootsIsFloat = false;
      if (entUnderSurface || (!bootsIsFloat && world.getTotalWorldTime() % 128L == 0L) || (entMoving && world.getTotalWorldTime() % Math.max((int)Math.floor(movCof), 1) == 0L) || (entJump && world.getTotalWorldTime() % 8L == 0L) || entSplash) {
        if (!world.isRemote) {
          if (mtd < 15) {
            if ((entUnderSurface && world.rand.nextInt(10) == 0) || entSplash || (mtd > 4 && (world.rand.nextInt(1 + (int)Math.floor((mtdkof / 2.0F))) == 0 || mtd < 7 || (!entJump && !entMoving && !entSplash)))) {
              mtd++;
              if (mtd < 5)
                mtd = 5; 
              mtd = Math.min(15, mtd);
              world.setBlockMetadataWithNotify(x, y, z, Math.min(15, mtd), 2);
            } 
          } else {
            mtd = 13;
            world.setBlockMetadataWithNotify(x, y, z, 13, 2);
          } 
          int xx = x - 1;
          int zz = z - 1;
          xx = x - 1;
          if (world.rand.nextInt(6) == 0 && 
            world.getBlock(xx, y, zz) == this) {
            int smtd = world.getBlockMetadata(xx, y, zz);
            int mttd = (int)Math.floor((mtd / 4));
            if (mttd < 5)
              mttd = 5; 
            world.setBlockMetadataWithNotify(xx, y, zz, Math.min(15, Math.max(mttd, smtd)), 2);
          } 
          xx = x;
          if (world.rand.nextInt(4) == 0 && 
            world.getBlock(xx, y, zz) == this) {
            int smtd = world.getBlockMetadata(xx, y, zz);
            int mttd = (int)Math.floor((mtd / 2));
            if (mttd < 5)
              mttd = 5; 
            world.setBlockMetadataWithNotify(xx, y, zz, Math.min(15, Math.max(mttd, smtd)), 2);
          } 
          xx = x + 1;
          if (world.rand.nextInt(6) == 0 && 
            world.getBlock(xx, y, zz) == this) {
            int smtd = world.getBlockMetadata(xx, y, zz);
            int mttd = (int)Math.floor((mtd / 4));
            if (mttd < 5)
              mttd = 5; 
            world.setBlockMetadataWithNotify(xx, y, zz, Math.min(15, Math.max(mttd, smtd)), 2);
          } 
          zz = z;
          if (world.rand.nextInt(4) == 0 && 
            world.getBlock(xx, y, zz) == this) {
            int smtd = world.getBlockMetadata(xx, y, zz);
            int mttd = (int)Math.floor((mtd / 2));
            if (mttd < 5)
              mttd = 5; 
            world.setBlockMetadataWithNotify(xx, y, zz, Math.min(15, Math.max(mttd, smtd)), 2);
          } 
          xx = x;
          if (world.rand.nextInt(4) == 0 && 
            world.getBlock(xx, y, zz) == this) {
            int smtd = world.getBlockMetadata(xx, y, zz);
            int mttd = (int)Math.floor((mtd / 2));
            if (mttd < 5)
              mttd = 5; 
            world.setBlockMetadataWithNotify(xx, y, zz, Math.min(15, Math.max(mttd, smtd)), 2);
          } 
          xx = x - 1;
          if (world.rand.nextInt(4) == 0 && 
            world.getBlock(xx, y, zz) == this) {
            int smtd = world.getBlockMetadata(xx, y, zz);
            int mttd = (int)Math.floor((mtd / 2));
            if (mttd < 5)
              mttd = 5; 
            world.setBlockMetadataWithNotify(xx, y, zz, Math.min(15, Math.max(mttd, smtd)), 2);
          } 
          zz = z + 1;
          if (world.rand.nextInt(6) == 0 && 
            world.getBlock(xx, y, zz) == this) {
            int smtd = world.getBlockMetadata(xx, y, zz);
            int mttd = (int)Math.floor((mtd / 4));
            if (mttd < 5)
              mttd = 5; 
            world.setBlockMetadataWithNotify(xx, y, zz, Math.min(15, Math.max(mttd, smtd)), 2);
          } 
          xx = x;
          if (world.rand.nextInt(4) == 0 && 
            world.getBlock(xx, y, zz) == this) {
            int smtd = world.getBlockMetadata(xx, y, zz);
            int mttd = (int)Math.floor((mtd / 2));
            if (mttd < 5)
              mttd = 5; 
            world.setBlockMetadataWithNotify(xx, y, zz, Math.min(15, Math.max(mttd, smtd)), 2);
          } 
          xx = x + 1;
          if (world.rand.nextInt(6) == 0 && 
            world.getBlock(xx, y, zz) == this) {
            int smtd = world.getBlockMetadata(xx, y, zz);
            int mttd = (int)Math.floor((mtd / 4));
            if (mttd < 5)
              mttd = 5; 
            world.setBlockMetadataWithNotify(xx, y, zz, Math.min(15, Math.max(mttd, smtd)), 2);
          } 
        } 
        if (entSplash) {
          if (kof2 > 1.5D)
            world.playSoundEffect(entity.posX, EposY, entity.posZ, "game.player.swim", 0.15F, world.rand.nextFloat() * 0.25F + 0.1F); 
          if (entity instanceof EntityPlayer && 
            world.rand.nextInt(2) == 0) {
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, this, 10);
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, this, 10);
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, this, 10);
          } 
        } 
        if (!entSplash && entMoving) {
          if (world.rand.nextInt(2) == 0)
            world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.step", 0.25F, world.rand.nextFloat() * 0.15F + 0.1F); 
          if (world.rand.nextInt(5) == 0)
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, this, -1); 
        } 
        if (entJump) {
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.slime.attack", 0.25F, world.rand.nextFloat() * 0.25F + 0.25F);
          if (entity instanceof EntityPlayer && 
            world.rand.nextInt(2) == 0) {
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, this, 10);
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, this, 10);
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, this, 10);
          } 
        } 
        if (mtd > 4 && 
          !entJump && !entMoving && !entSplash && 
          world.rand.nextInt(5) == 0)
          if (world.rand.nextInt(5) == 0) {
            world.playSoundEffect(entity.posX, EposY, entity.posZ, "liquid.water", 0.5F, world.rand.nextFloat() * 0.15F + 0.1F);
          } else {
            world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.step", 0.25F, world.rand.nextFloat() * 0.15F + 0.1F);
          }  
      } 
      if (entUnderSurface && 
        world.getTotalWorldTime() % 16L == 0L && 
        world.rand.nextInt(10) == 0)
        MFQM.spawnBodyBubble(world, entity, x, y, z, this, 10); 
      if (world.getBlock(x, y + 1, z) != this || (world.getBlock(x, y + 1, z) == this && world.getBlock(x, y + 2, z) == this)) {
        if (world.getBlock(x, y + 1, z) == this && world.getBlock(x, y + 2, z) == this)
          kof1 = 0.001D; 
        double mys = 0.0D;
        if (!(entity instanceof EntityPlayer))
          mys = -0.0025D; 
        if (entJump && 
          world.rand.nextInt(1) == 0 && 
          MFQM.suctionWorldCheck(entity, world, prevVel)) {
          entity.motionY -= 0.025D * mtdkof * (Math.min(0.75D, kof1m) + 1.0D);
          world.playSound(entity.posX, EposY, entity.posZ, "mob.magmacube.jump", 0.25F, 0.25F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F, false);
        } 
        double aaq = 0.0D;
        if (mtd < 5)
          aaq = 0.125D; 
        if (!(entity instanceof EntityPlayer))
          aaq = 0.0D; 
        if (kof1 >= 1.35D + aaq) {
          if (mtd > 4) {
            if (!entSplash) {
              double a1 = 0.075D;
              if ((float)world.getTotalWorldTime() % Math.max(32.0F - mtdkof * 2.0F, 1.0F) == 0.0F)
                a1 = 0.07485D; 
              entity.motionY += a1 / Math.max((movKofDiv - 1.0D) * Math.max(kof1m - 0.5D, 0.75D) * 1.6D * mtdkof * 20.0D, 1.0D) / Math.pow(Math.max(kof1m / 1.25D, 1.0D), 2.0D);
              entity.onGround = true;
              entity.fallDistance = 0.0F;
            } 
          } else {
            if (!(entity instanceof EntityPlayer)) {
              if (!entSplash) {
                entity.motionY = 0.0D;
                entity.motionY += 0.08D;
                entity.onGround = true;
                entity.fallDistance = 0.0F;
              } 
            } else if (!entSplash) {
              entity.motionY += 0.085D;
              entity.onGround = true;
              entity.fallDistance = 0.0F;
            } 
            if (!world.isRemote && 
              world.rand.nextInt(Math.max(1, 100 + (int)Math.floor(movDisa) * 1000)) == 0) {
              world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.step", 0.25F, world.rand.nextFloat() * 0.25F + 0.1F);
              int smtd = world.getBlockMetadata(x, y, z);
              if (smtd < 4)
                smtd = 4; 
              world.setBlockMetadataWithNotify(x, y, z, Math.min(15, smtd + 1), 2);
              entity.motionX = 0.0D;
              entity.motionZ = 0.0D;
              entity.motionY = -0.1D;
              entity.setInWeb();
              MFQM.setStuckEffect((EntityLivingBase)entity, 255);
              entSplash = true;
              entity.onGround = false;
            } 
          } 
        } else {
          if (EposY - EprevPosY > 0.001D && 
            world.rand.nextInt(Math.max((int)Math.floor(kof1m * 2.0D), 10)) == 0 && 
            MFQM.suctionWorldCheck(entity, world, prevVel)) {
            entity.motionY -= 0.025D * mtdkof * (Math.min(0.75D, kof1m) + 1.0D);
            world.playSound(entity.posX, EposY, entity.posZ, "mob.magmacube.jump", 0.25F, 0.25F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F, false);
          } 
          double xx = x + 0.5D;
          double zz = z + 0.5D;
          double disq = Math.pow(Math.pow(entity.posX - xx, 2.0D) + Math.pow(entity.posZ - zz, 2.0D), 0.5D);
          if (disq < 0.5D && 
            disq >= 0.075D) {
            entity.motionX += (xx - entity.posX) / Math.max(10.0D, kof1m * 25.0D);
            entity.motionZ += (zz - entity.posZ) / Math.max(10.0D, kof1m * 25.0D);
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
          if (world.rand.nextInt(Math.max((int)Math.floor(100.0D - Math.pow(Math.max(kof1m, 0.0D), 1.5D) * 20.0D), 1)) == 0)
            entity.onGround = true; 
          if (MFQM.isTrulySink(entity, kof1m))
            MFQM.setStuckEffect((EntityLivingBase)entity, mr_kof); 
          entity.fallDistance = 0.0F;
          if (kof1 >= 0.9D) {
            if (world.rand.nextInt((int)Math.floor(Math.max(kof1m * 5.0D / mtdkof, 1.0D))) == 0) {
              entity.setInWeb();
              if (!entMoving) {
                entity.motionY += (0.0725D + mys) * 0.95D;
              } else {
                entity.motionY += (0.0725D + mys) * 0.95D / movKofDiv;
              } 
            } else if (!entMoving) {
              entity.motionY += (0.075D + mys) * 0.95D;
            } else {
              entity.motionY += (0.075D + mys) * 0.95D / movKofDiv;
            } 
          } else {
            double a2 = 1.0D;
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
                MFQM.setStuckEffect((EntityLivingBase)entity, 135); 
            } 
          } 
          if (mtd < 5) {
            entity.motionY = 0.0D;
            entity.motionY += 0.1D;
          } 
        } 
        if (entity.isInWater() && 
          entity.motionY > 0.0D)
          entity.motionY /= 4.0D; 
      } else {
        entity.setInWeb();
        if (kof1 < 0.5D && 
          MFQM.isTrulySink(entity, kof1m))
          MFQM.setStuckEffect((EntityLivingBase)entity, 135); 
      } 
      MFQM.antiHoldJumpScript(entity, kof1, true);
    } else if (mtd > 5) {
      if (kof1 < 1.45D)
        entity.setInWeb(); 
    } else {
      entity.motionX *= 0.5880000114440918D;
      entity.motionZ *= 0.5880000114440918D;
      entity.motionY = 0.0D;
      if (kof1 < 1.45D) {
        entity.motionY += 0.1D;
      } else {
        entity.motionY += 0.03999999910593033D;
      } 
    } 
  }
  
  public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
    return null;
  }
  
  public Item getItemDropped(int par1, Random random, int par3) {
    return Items.clay_ball;
  }
  
  public int quantityDropped(Random par1Random) {
    return 4;
  }
  
  public void onBlockAdded(World par1World, int par2, int par3, int par4) {
    par1World.scheduleBlockUpdate(par2, par3, par4, this, tickRate(par1World));
  }
  
  public void harvestBlock(World world, EntityPlayer entityPlayer, int x, int y, int z, int par6) {
    if (par6 < 7) {
      world.setBlock(x, y, z, this, 7, 3);
      world.playSoundEffect(x, y, z, "mob.silverfish.step", 0.25F, world.rand.nextFloat() * 0.15F + 0.1F);
    } else {
      if (EnchantmentHelper.getSilkTouchModifier((EntityLivingBase)entityPlayer)) {
        MFQM.dropItem(world, x, y, z, new ItemStack(this));
        world.markBlockForUpdate(x, y, z);
        return;
      } 
      super.harvestBlock(world, entityPlayer, x, y, z, par6);
    } 
  }
  
  public boolean canHarvestBlock(EntityPlayer player, int meta) {
    return true;
  }
  
  public float getPlayerRelativeBlockHardness(EntityPlayer par1EntityPlayer, World par2World, int par3, int par4, int par5) {
    if (!((Entity)par1EntityPlayer).onGround)
      return 3.0E-5F; 
    float f = getBlockHardness(par2World, par3, par4, par5);
    return ForgeHooks.blockStrength(this, par1EntityPlayer, par2World, par3, par4, par5);
  }
  
  @SideOnly(Side.CLIENT)
  public void randomDisplayTick(World world, int x, int y, int z, Random random) {
    if (world.getBlock(x, y + 1, z).getMaterial() == Material.air) {
      int md = world.getBlockMetadata(x, y, z);
      if (world.rand.nextInt(300) == 0 && md > 4) {
        double xx = x + random.nextFloat();
        double zz = z + random.nextFloat();
        float vol = 0.2F;
        if (md > 5 || (md <= 5 && world.rand.nextInt(5) == 0))
          vol = 0.5F; 
        MFQM.spawnQSBubble(world, xx, y + 1.0D, zz, this, 10, vol);
      } 
    } 
    if (world.rand.nextInt(1000) == 0)
      world.markBlockForUpdate(x, y, z); 
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int par1, int par2) {
    if (par2 < 5) {
      switch (par1) {
        case 1:
          return this.IIconNormalArray[Math.min(4, par2)];
        case 0:
          return this.IIconBrokenArray[2];
      } 
      return this.IIconSideNormal;
    } 
    switch (par1) {
      case 1:
        return this.IIconBrokenArray[Math.min(2, par2 - 5)];
      case 0:
        return this.IIconBrokenArray[2];
    } 
    if (par2 >= 7)
      return this.IIconSideBroken; 
    return this.IIconSideNormal;
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister par1IIconRegister) {
    this.IIconNormalArray = new IIcon[5];
    this.IIconBrokenArray = new IIcon[5];
    this.IIconNormalArray[0] = par1IIconRegister.registerIcon("morefunquicksandmod:clay0_0");
    this.IIconNormalArray[1] = par1IIconRegister.registerIcon("morefunquicksandmod:clay0_1");
    this.IIconNormalArray[2] = par1IIconRegister.registerIcon("morefunquicksandmod:clay0_2");
    this.IIconNormalArray[3] = par1IIconRegister.registerIcon("morefunquicksandmod:clay0_3");
    this.IIconNormalArray[4] = par1IIconRegister.registerIcon("morefunquicksandmod:clay0_4");
    this.IIconBrokenArray[0] = par1IIconRegister.registerIcon("morefunquicksandmod:clay1");
    this.IIconBrokenArray[1] = par1IIconRegister.registerIcon("morefunquicksandmod:clay2");
    this.IIconBrokenArray[2] = par1IIconRegister.registerIcon("morefunquicksandmod:clay3");
    this.IIconSideNormal = par1IIconRegister.registerIcon("morefunquicksandmod:clay4");
    this.IIconSideBroken = par1IIconRegister.registerIcon("morefunquicksandmod:clay5");
  }
  
  protected boolean canSilkHarvest() {
    return true;
  }
  
  public boolean isOpaqueCube() {
    return true;
  }
  
  public boolean isSolid() {
    return true;
  }
  
  public boolean isNormalCube() {
    return false;
  }
  
  public void checkEntityUnder(Entity ent) {
    if (MFQM.isEntityInsideOfBlock(ent, this) && 
      MFQM.isDrowning(ent)) {
      MFQM.spawnDrowningBubble(ent.worldObj, ent, this, 10);
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
