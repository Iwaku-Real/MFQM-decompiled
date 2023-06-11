package MoreFunQuicksandMod.main.blocks;

import MoreFunQuicksandMod.main.CustomVarPlayer;
import MoreFunQuicksandMod.main.MFQM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockWax extends Block {
  @SideOnly(Side.CLIENT)
  private IIcon[] theQIIcon;
  
  @SideOnly(Side.CLIENT)
  private IIcon[] theSQIIcon;
  
  private int maxOpacity;
  
  private int lastOpacity;
  
  private int incOpacity;
  
  public BlockWax(int mopc, int lopc, int iopc) {
    super(Material.sponge);
    setHardness(1.5F);
    setStepSound(Block.soundTypeStone);
    setResistance(1000.0F);
    setTickRandomly(true);
    setLightOpacity(0);
    setUnlocalizedName("Wax");
    setCreativeTab(MFQM.tabMFQM);
    this.maxOpacity = mopc;
    this.lastOpacity = lopc;
    this.incOpacity = iopc;
  }
  
  public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
    int mtd = world.getBlockMetadata(x, y, z);
    if (mtd > 0)
      return false; 
    return true;
  }
  
  public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
    int mtd = world.getBlockMetadata(x, y, z);
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
      checkEntityUnder(entity);
      if (entity instanceof EntityPlayer)
        checkPlayerMuddy((EntityPlayer)entity, x, y, z, world); 
      boolean entJump = false;
      boolean entMoving = false;
      boolean entSplash = false;
      boolean entRotate = false;
      float mtdkof = 1.0F + mtd / 2.0F;
      double movDis = 1.0D;
      double movCof = 16.0D;
      double movKofDiv = 1.0D;
      int mr_kof = (int)Math.min(5.0D + Math.floor(Math.max(0.0D, Math.pow(mtdkof * (1.5D - kof1m), 2.0D))), 175.0D);
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
      if (entRotate || Math.abs(entity.prevPosX - entity.posX) > 0.0015D || Math.abs(entity.prevPosZ - entity.posZ) > 0.0015D) {
        entMoving = true;
        movDis = Math.pow(Math.pow(entity.prevPosX - entity.posX, 2.0D) + Math.pow(entity.prevPosZ - entity.posZ, 2.0D), 0.5D);
        movCof = movDis * 10.0D;
        movCof = Math.max(Math.min(32.0D / (1.0D + movCof), 32.0D), 16.0D);
        movKofDiv = 1.0D + movDis / 2.0D;
        if (kof1m < 0.9D && kof1m != 0.0D && 
          entRotate)
          movKofDiv += mr_kof * 0.005D; 
      } 
      if (mtd > 0) {
        if (mtd < 3) {
          entity.motionX /= (mtd * 100 + 1);
          entity.motionZ /= (mtd * 100 + 1);
        } else {
          entity.motionX = 0.0D;
          entity.motionZ = 0.0D;
        } 
        if (entity.motionY > -0.1D) {
          entity.motionY = 0.0D;
        } else {
          entity.motionY /= 2.0D;
        } 
        if (world.getTotalWorldTime() % 128L == 0L || (entMoving && world.getTotalWorldTime() % Math.max((int)Math.floor(movCof), 1) == 0L) || (entJump && world.getTotalWorldTime() % 8L == 0L) || entSplash) {
          if (entSplash) {
            if (kof2 > 1.5D)
              world.playSoundEffect(entity.posX, EposY, entity.posZ, "game.player.swim", 0.25F, world.rand.nextFloat() * 0.25F + 0.1F); 
            if (world.rand.nextInt(Math.max(10 - mtd, 2)) == 0) {
              MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, this, -1);
              MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, this, -1);
              MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, this, -1);
            } 
          } 
          if (!entSplash && entMoving) {
            if (world.rand.nextInt(2) == 0)
              world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.step", 0.25F, world.rand.nextFloat() * 0.15F + 0.1F); 
            if (world.rand.nextInt(Math.max(10 - mtd, 5)) == 0)
              MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, this, -1); 
          } 
          if (entJump) {
            world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.slime.attack", 0.25F, world.rand.nextFloat() * 0.25F + 0.25F);
            if (entity instanceof EntityPlayer && 
              world.rand.nextInt(Math.max(10 - mtd, 2)) == 0) {
              MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, this, -1);
              MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, this, -1);
              MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, this, -1);
            } 
          } 
          if (!entJump && !entMoving && !entSplash && 
            world.rand.nextInt(5) == 0)
            if (world.rand.nextInt(5) == 0) {
              world.playSoundEffect(entity.posX, EposY, entity.posZ, "liquid.water", 0.5F, world.rand.nextFloat() * 0.15F + 0.1F);
            } else {
              world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.step", 0.25F, world.rand.nextFloat() * 0.15F + 0.1F);
            }  
        } 
      } 
      double kof1n = kof1;
      if (!world.isRemote) {
        if (entity instanceof EntityPlayer)
          kof1n += 1.5D; 
        kof1n = Math.max(kof1n, 0.0D);
        int koftemp = getTemp(world, x, y, z) + 1;
        if (world.getTotalWorldTime() % Math.max(1.0D, Math.floor((kof1n * 100.0D + 300.0D) / koftemp)) == 0.0D) {
          if (mtd < 15) {
            mtd++;
            mtd = Math.min(15, mtd);
            world.setBlockMetadataWithNotify(x, y, z, mtd, 2);
          } 
          int mtd2 = world.getBlockMetadata(x, y - 1, z);
          if (mtd2 < Math.floor(mtd / 1.5D)) {
            mtd2++;
            mtd2 = Math.min(15, mtd2);
            world.setBlockMetadataWithNotify(x, y - 1, z, mtd2, 2);
          } 
          int xx = x - 1;
          int zz = z - 1;
          xx = x - 1;
          if (world.rand.nextInt(3) == 0 && 
            world.getBlock(xx, y, zz) == this) {
            int smtd = world.getBlockMetadata(xx, y, zz);
            world.setBlockMetadataWithNotify(xx, y, zz, Math.min(15, Math.max((int)Math.floor((mtd / 2)), smtd)), 2);
          } 
          xx = x;
          if (world.rand.nextInt(2) == 0 && 
            world.getBlock(xx, y, zz) == this) {
            int smtd = world.getBlockMetadata(xx, y, zz);
            world.setBlockMetadataWithNotify(xx, y, zz, Math.min(15, Math.max((int)Math.floor((mtd / 1)), smtd)), 2);
          } 
          xx = x + 1;
          if (world.rand.nextInt(3) == 0 && 
            world.getBlock(xx, y, zz) == this) {
            int smtd = world.getBlockMetadata(xx, y, zz);
            world.setBlockMetadataWithNotify(xx, y, zz, Math.min(15, Math.max((int)Math.floor((mtd / 2)), smtd)), 2);
          } 
          zz = z;
          if (world.rand.nextInt(2) == 0 && 
            world.getBlock(xx, y, zz) == this) {
            int smtd = world.getBlockMetadata(xx, y, zz);
            world.setBlockMetadataWithNotify(xx, y, zz, Math.min(15, Math.max((int)Math.floor((mtd / 1)), smtd)), 2);
          } 
          xx = x;
          if (world.rand.nextInt(2) == 0 && 
            world.getBlock(xx, y, zz) == this) {
            int smtd = world.getBlockMetadata(xx, y, zz);
            world.setBlockMetadataWithNotify(xx, y, zz, Math.min(15, Math.max((int)Math.floor((mtd / 1)), smtd)), 2);
          } 
          xx = x - 1;
          if (world.rand.nextInt(2) == 0 && 
            world.getBlock(xx, y, zz) == this) {
            int smtd = world.getBlockMetadata(xx, y, zz);
            world.setBlockMetadataWithNotify(xx, y, zz, Math.min(15, Math.max((int)Math.floor((mtd / 1)), smtd)), 2);
          } 
          zz = z + 1;
          if (world.rand.nextInt(3) == 0 && 
            world.getBlock(xx, y, zz) == this) {
            int smtd = world.getBlockMetadata(xx, y, zz);
            world.setBlockMetadataWithNotify(xx, y, zz, Math.min(15, Math.max((int)Math.floor((mtd / 2)), smtd)), 2);
          } 
          xx = x;
          if (world.rand.nextInt(2) == 0 && 
            world.getBlock(xx, y, zz) == this) {
            int smtd = world.getBlockMetadata(xx, y, zz);
            world.setBlockMetadataWithNotify(xx, y, zz, Math.min(15, Math.max((int)Math.floor((mtd / 1)), smtd)), 2);
          } 
          xx = x + 1;
          if (world.rand.nextInt(3) == 0 && 
            world.getBlock(xx, y, zz) == this) {
            int smtd = world.getBlockMetadata(xx, y, zz);
            world.setBlockMetadataWithNotify(xx, y, zz, Math.min(15, Math.max((int)Math.floor((mtd / 2)), smtd)), 2);
          } 
        } 
      } 
      if (mtd < 1)
        return; 
      if (world.getTotalWorldTime() % Math.max(32 - mtd, 1) == 0L && 
        world.rand.nextInt(10) == 0)
        MFQM.spawnBodyBubble(world, entity, x, y, z, this, -1); 
      if (world.getBlock(x, y + 1, z) != this || (world.getBlock(x, y + 1, z) == this && world.getBlock(x, y + 2, z) == this)) {
        if (world.getBlock(x, y + 1, z) == this && world.getBlock(x, y + 2, z) == this)
          kof1 = 0.001D; 
        double mys = 0.0D;
        if (!(entity instanceof EntityPlayer))
          mys = 0.0D; 
        if (entJump && 
          world.rand.nextInt(2) == 0 && 
          MFQM.suctionWorldCheck(entity, world, prevVel)) {
          entity.motionY -= 0.05D * (Math.min(0.75D, kof1m) + 1.0D);
          world.playSound(entity.posX, EposY, entity.posZ, "mob.magmacube.jump", 0.25F, 0.25F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F, false);
        } 
        entity.fallDistance = 0.0F;
        if (kof1 >= 1.2D) {
          double a1 = 0.075D;
          if (world.getTotalWorldTime() % (4 - Math.min(mtd / 4, 3)) == 0L)
            a1 = 0.07485D; 
          entity.motionY += a1 / Math.max((movKofDiv - 1.0D) * Math.max(kof1m - 0.5D, 0.75D) * 1.01D * 17.5D, 1.0D) / Math.pow(Math.max(kof1m / 1.475D, 1.0D), 2.0D);
          MFQM.setStuckEffect((EntityLivingBase)entity, mr_kof);
          entity.onGround = true;
          if (entity instanceof EntityPlayer) {
            entity.onGround = false;
            if (world.rand.nextInt(Math.max((int)Math.floor(20.0D - Math.pow(Math.max(kof1m, 0.0D), 1.5D)), 1)) == 0)
              entity.onGround = true; 
          } else {
            double mw_kof = (1 + mr_kof / 10);
            entity.setPosition(entity.prevPosX + (entity.posX - entity.prevPosX) / mw_kof, EposY, entity.prevPosZ + (entity.posZ - entity.prevPosZ) / mw_kof);
          } 
        } else {
          double xx = x + 0.5D;
          double zz = z + 0.5D;
          double disq = Math.pow(Math.pow(entity.posX - xx, 2.0D) + Math.pow(entity.posZ - zz, 2.0D), 0.5D);
          if (disq < 0.5D - (mtd / 5) && 
            disq >= 0.075D) {
            entity.motionX += (xx - entity.posX) / Math.max(10.0D, kof1m * 50.0D);
            entity.motionZ += (zz - entity.posZ) / Math.max(10.0D, kof1m * 50.0D);
            if (Math.pow(Math.pow(entity.motionX, 2.0D) + Math.pow(entity.motionZ, 2.0D), 0.5D) > 0.005D) {
              movKofDiv = Math.max(movKofDiv / 10.0D, 1.0D);
              if (!entRotate) {
                entMoving = false;
              } else {
                movKofDiv += mr_kof * 0.005D;
              } 
            } 
          } 
          if (EposY - EprevPosY > 0.001D && 
            world.rand.nextInt(Math.max((int)Math.floor(kof1m * 3.0D), 10)) == 0 && 
            MFQM.suctionWorldCheck(entity, world, prevVel)) {
            entity.motionY -= 0.035D * mtdkof * (Math.min(0.75D, kof1m) + 1.0D);
            world.playSound(entity.posX, EposY, entity.posZ, "mob.magmacube.jump", 0.25F, 0.25F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F, false);
          } 
          if (kof1 >= 0.9D) {
            double a1 = 0.0725D;
            entity.motionY += a1 / Math.max(1.0D, Math.max(movKofDiv - 1.0D, 1.0D) * Math.max((mtdkof - 1.0F) / 20.0F + 1.0F, 1.0F) * Math.max(kof1m - 0.5D, 0.75D) * 1.5D);
            MFQM.setStuckEffect((EntityLivingBase)entity, mr_kof);
            entity.setInWeb();
            entity.onGround = true;
            if (entity instanceof EntityPlayer) {
              entity.onGround = false;
              if (world.rand.nextInt(Math.max((int)Math.floor(25.0D - Math.pow(Math.max(kof1m, 0.0D), 1.5D)), 1)) == 0)
                entity.onGround = true; 
            } else {
              double mw_kof = (1 + mr_kof / 10);
              entity.setPosition(entity.prevPosX + (entity.posX - entity.prevPosX) / mw_kof, EposY, entity.prevPosZ + (entity.posZ - entity.prevPosZ) / mw_kof);
            } 
          } else {
            double a2 = 0.98D;
            if (world.getTotalWorldTime() % (int)Math.floor(Math.max(1.0D, Math.min(5.0D, Math.max(32.0D - kof1 * 32.0D, 1.0D) / mtdkof))) == 0L) {
              a2 = 0.98D;
            } else {
              a2 = 1.01D;
            } 
            if (world.rand.nextInt(Math.max((int)Math.floor((25.0F * mtdkof) - Math.pow(Math.max(kof1m, 0.0D), 1.5D)), 1)) == 0)
              entity.onGround = true; 
            if (MFQM.isTrulySink(entity, kof1m))
              MFQM.setStuckEffect((EntityLivingBase)entity, mr_kof); 
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
      if (mtd < 1)
        return; 
      if (kof1 < 1.45D)
        entity.setInWeb(); 
    } 
  }
  
  public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
    int mtd = world.getBlockMetadata(x, y, z);
    double f = 0.0D;
    if (mtd < 3) {
      switch (mtd) {
        case 0:
          f = 0.01D;
          break;
        case 1:
          f = 0.1D;
          break;
        case 2:
          f = 0.25D;
          break;
      } 
      return AxisAlignedBB.getBoundingBox(x, y, z, (x + 1), (y + 1) - f, (z + 1));
    } 
    return null;
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int par1, int par2) {
    int wax = (int)Math.min(Math.floor((par2 / 3)), 4.0D);
    if (par1 == 0 || par1 == 1)
      return this.theQIIcon[wax]; 
    return this.theSQIIcon[wax];
  }
  
  public void updateTick(World world, int x, int y, int z, Random rand) {
    int met = world.getBlockMetadata(x, y, z);
    if (world.rand.nextInt(20 - met) == 0) {
      if (!world.getBlock(x, y - 1, z).getMaterial().isSolid() && world.getBlock(x, y - 1, z).getMaterial().isReplaceable()) {
        world.setBlock(x, y - 1, z, this, met, 3);
        world.setBlockToAir(x, y, z);
        return;
      } 
    } else {
      world.scheduleBlockUpdate(x, y, z, this, tickRate(world) * 20);
    } 
    if (world.provider.isHellWorld) {
      if (met < 15) {
        met++;
        world.setBlockMetadataWithNotify(x, y, z, met, 2);
      } 
      return;
    } 
    if (MFQM.AOA && 
      world.provider.dimensionId == MFQM.AOADimIromine) {
      if (met < 15) {
        met++;
        world.setBlockMetadataWithNotify(x, y, z, met, 2);
      } 
      return;
    } 
    int koftemp = getTemp(world, x, y, z);
    int xx = x;
    int yy = y;
    int zz = z;
    boolean isWaterNear = false;
    boolean isLavaNear = false;
    boolean isFireNear = false;
    boolean isIceNear = false;
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
        if (met < 15) {
          met += 2;
          world.setBlockMetadataWithNotify(x, y, z, Math.min(met, 15), 2);
        } 
        isLavaNear = true;
      } 
      if (world.getBlock(xx, yy, zz).getMaterial() == Material.fire) {
        if (met < 15) {
          met++;
          world.setBlockMetadataWithNotify(x, y, z, met, 2);
        } 
        isFireNear = true;
      } 
      if (world.getBlock(xx, yy, zz).getMaterial() == Material.water)
        koftemp--; 
      if (world.getBlock(xx, yy, zz).getMaterial() == Material.ice || world.getBlock(xx, yy, zz).getMaterial() == Material.snow)
        koftemp -= 2; 
    } 
    if (isLavaNear || isFireNear)
      return; 
    koftemp = Math.max(koftemp, 0);
    int normalMeta = 1;
    int incMeta = 1;
    int decMeta = 1;
    switch (koftemp) {
      case 0:
        normalMeta = 0;
        incMeta = 1;
        decMeta = 1;
        break;
      case 1:
        normalMeta = 0;
        incMeta = 1;
        decMeta = 2;
        break;
      case 2:
        normalMeta = 1;
        incMeta = 3;
        decMeta = 3;
        break;
      case 3:
        normalMeta = 5;
        incMeta = 2;
        decMeta = 4;
        break;
      case 4:
        normalMeta = 10;
        incMeta = 1;
        decMeta = 5;
        break;
    } 
    if (met < normalMeta && 
      world.rand.nextInt(incMeta) == 0) {
      met++;
      world.setBlockMetadataWithNotify(x, y, z, met, 2);
      return;
    } 
    if (met > normalMeta && 
      world.rand.nextInt(decMeta) == 0) {
      met--;
      world.setBlockMetadataWithNotify(x, y, z, met, 2);
      return;
    } 
  }
  
  public int getTemp(World world, int x, int y, int z) {
    int met = world.getBlockMetadata(x, y, z);
    BiomeGenBase var5 = world.getBiomeGenForCoords(x, z);
    float temp = var5.getFloatTemperature(x, y, z);
    if (world.getSavedLightValue(EnumSkyBlock.Block, x, y, z) > 11)
      temp = Math.min(temp + 1.0F, 2.0F); 
    if (temp < 0.1F)
      return 0; 
    if (temp < 0.3F)
      return 1; 
    if (temp < 0.85F)
      return 2; 
    if (temp < 1.5F)
      return 3; 
    if (temp < 3.0F)
      return 4; 
    return 2;
  }
  
  public void onBlockAdded(World par1World, int par2, int par3, int par4) {
    par1World.scheduleBlockUpdate(par2, par3, par4, this, tickRate(par1World));
  }
  
  public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5) {
    par1World.scheduleBlockUpdate(par2, par3, par4, this, tickRate(par1World));
  }
  
  public float getPlayerRelativeBlockHardness(EntityPlayer par1EntityPlayer, World par2World, int par3, int par4, int par5) {
    if (!((Entity)par1EntityPlayer).onGround) {
      int met = par2World.getBlockMetadata(par3, par4, par5);
      return 2.0E-4F / met;
    } 
    return super.getPlayerRelativeBlockHardness(par1EntityPlayer, par2World, par3, par4, par5) / (par2World.getBlockMetadata(par3, par4, par5) / 4.0F + 1.0F);
  }
  
  @SideOnly(Side.CLIENT)
  public void randomDisplayTick(World world, int x, int y, int z, Random random) {
    if (world.getBlock(x, y + 1, z).getMaterial() == Material.air) {
      int md = world.getBlockMetadata(x, y, z);
      if (md > 2 && 
        world.rand.nextInt(1250 / md) == 0) {
        double xx = x + random.nextFloat();
        double zz = z + random.nextFloat();
        MFQM.spawnQSBubble(world, xx, y + 1.0D, zz, this, -1, 0.5F);
      } 
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister par1IIconRegister) {
    this.theQIIcon = new IIcon[5];
    this.theSQIIcon = new IIcon[5];
    this.theQIIcon[0] = par1IIconRegister.registerIcon("morefunquicksandmod:Wax0");
    this.theQIIcon[1] = par1IIconRegister.registerIcon("morefunquicksandmod:Wax1");
    this.theQIIcon[2] = par1IIconRegister.registerIcon("morefunquicksandmod:Wax2");
    this.theQIIcon[3] = par1IIconRegister.registerIcon("morefunquicksandmod:Wax3");
    this.theQIIcon[4] = par1IIconRegister.registerIcon("morefunquicksandmod:Wax4");
    this.theSQIIcon[0] = par1IIconRegister.registerIcon("morefunquicksandmod:Wax0S");
    this.theSQIIcon[1] = par1IIconRegister.registerIcon("morefunquicksandmod:Wax1S");
    this.theSQIIcon[2] = par1IIconRegister.registerIcon("morefunquicksandmod:Wax2S");
    this.theSQIIcon[3] = par1IIconRegister.registerIcon("morefunquicksandmod:Wax3S");
    this.theSQIIcon[4] = par1IIconRegister.registerIcon("morefunquicksandmod:Wax4S");
  }
  
  public boolean canDropFromExplosion(Explosion par1Explosion) {
    return false;
  }
  
  public boolean canHarvestBlock(EntityPlayer player, int meta) {
    return true;
  }
  
  public Item getItemDropped(int par1, Random random, int par3) {
    return MFQM.WaxItem;
  }
  
  public int quantityDropped(Random random) {
    return 9;
  }
  
  public void harvestBlock(World world, EntityPlayer entityPlayer, int x, int y, int z, int par6) {
    if (EnchantmentHelper.getSilkTouchModifier((EntityLivingBase)entityPlayer)) {
      MFQM.dropItem(world, x, y, z, new ItemStack(this));
      world.markBlockForUpdate(x, y, z);
      return;
    } 
    super.harvestBlock(world, entityPlayer, x, y, z, par6);
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
  
  @SideOnly(Side.CLIENT)
  public int getBlockColor() {
    if (MFQM.AOA && 
      (Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.AOADimIromine)
      return 12292676; 
    return 16777215;
  }
  
  @SideOnly(Side.CLIENT)
  public int getRenderColor(int par1) {
    if (MFQM.AOA && 
      (Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.AOADimIromine)
      return 12292676; 
    return 16777215;
  }
  
  @SideOnly(Side.CLIENT)
  public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
    if (MFQM.AOA && 
      (Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.AOADimIromine)
      return 12292676; 
    return 16777215;
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
