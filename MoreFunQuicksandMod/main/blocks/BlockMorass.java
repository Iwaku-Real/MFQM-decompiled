package MoreFunQuicksandMod.main.blocks;

import MoreFunQuicksandMod.main.CustomVarPlayer;
import MoreFunQuicksandMod.main.MFQM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockMorass extends Block {
  @SideOnly(Side.CLIENT)
  private IIcon[] IIconArray;
  
  @SideOnly(Side.CLIENT)
  private IIcon IIconSide0;
  
  @SideOnly(Side.CLIENT)
  private IIcon IIconSide1;
  
  private int maxOpacity;
  
  private int lastOpacity;
  
  private int incOpacity;
  
  public BlockMorass(int mopc, int lopc, int iopc) {
    super(Material.sponge);
    setHardness(2.25F);
    setResistance(1000.0F);
    setStepSound(Block.soundTypeGrass);
    setUnlocalizedName("Morass");
    setTickRandomly(true);
    if (MFQM.QSOpacity) {
      setLightOpacity(255);
    } else {
      setLightOpacity(3);
    } 
    setCreativeTab(MFQM.tabMFQM);
    this.maxOpacity = mopc;
    this.lastOpacity = lopc;
    this.incOpacity = iopc;
  }
  
  public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
    return false;
  }
  
  public int Breaking(World world, int x, int y, int z, Random random) {
    if (world.getBlock(x, y, z) == this) {
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
      world.setBlock(x, y, z, Blocks.dirt, 0, 3);
      world.playSoundEffect((x + 0.5F), (y + 0.5F), (z + 0.5F), "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
      for (int l = 0; l < 8; l++)
        world.spawnParticle("largesmoke", x + Math.random(), y + 1.2D, z + Math.random(), 0.0D, 0.0D, 0.0D); 
      return;
    } 
    if (world.getBlock(x, y + 1, z) == MFQM.MireBlock || world.getBlock(x, y + 1, z) == MFQM.MorassBlock)
      world.setBlock(x, y, z, MFQM.MireBlock, 10, 3); 
    if (world.rand.nextInt(5) == 0) {
      if (!world.getBlock(x, y - 1, z).getMaterial().isSolid() && world.getBlock(x, y - 1, z).getMaterial().isReplaceable()) {
        world.setBlock(x, y - 1, z, this, world.getBlockMetadata(x, y, z), 3);
        world.setBlockToAir(x, y, z);
        return;
      } 
    } else if (world.getBlockMetadata(x, y, z) == 9 && 
      world.rand.nextInt(10) == 0 && 
      !world.getBlock(x, y + 1, z).getMaterial().isOpaque() && world.getBlockLightValue(x, y + 1, z) >= 4 && world.getBlockLightOpacity(x, y + 1, z) <= 2) {
      world.setBlock(x, y, z, MFQM.MorassBlock, world.rand.nextInt(9), 3);
      return;
    } 
  }
  
  public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
    int mtd = world.getBlockMetadata(x, y, z);
    if (mtd < 9) {
      int xx = x;
      int yy = y;
      int zz = z;
      for (int re = 0; re <= 4; re++) {
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
        } 
        if (!world.getBlock(xx, yy, zz).getMaterial().isSolid()) {
          mtd = -1;
          break;
        } 
      } 
      if (mtd == -1)
        return AxisAlignedBB.getBoundingBox(x, y, z, x + 1.0D, y + 0.51D, z + 1.0D); 
    } 
    return null;
  }
  
  public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
    int mtd = world.getBlockMetadata(x, y, z);
    double mtd_deep = 0.0D;
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
      if (!(entity instanceof EntityPlayer) && 
        MFQM.AOA && 
        world.provider.dimensionId == MFQM.AOADimGardencia)
        Affect = false; 
      if (Affect)
        checkEntityUnder(entity); 
      if (entity instanceof EntityPlayer)
        checkPlayerMuddy((EntityPlayer)entity, x, y, z, world); 
      int tmeta = mtd;
      switch (tmeta) {
        case 0:
          mtd_deep = 0.0D;
          break;
        case 1:
          mtd_deep = 0.05D;
          break;
        case 2:
          mtd_deep = 0.05D;
          break;
        case 3:
          mtd_deep = 0.05D;
          break;
        case 4:
          mtd_deep = 0.1D;
          break;
        case 5:
          mtd_deep = 0.1D;
          break;
        case 6:
          mtd_deep = 0.1D;
          break;
        case 7:
          mtd_deep = 0.15D;
          break;
        default:
          mtd_deep = 0.15D;
          break;
      } 
      boolean boots = false;
      boolean bootsIsFloat = false;
      boolean entJump = false;
      boolean entMoving = false;
      boolean entSplash = false;
      boolean entUnderSurface = false;
      boolean entRotate = false;
      float mtdkof = (1 + mtd / 3);
      double movDis = 0.0D;
      double movCof = 16.0D;
      double movKofDiv = 1.0D;
      int mr_kof = (int)Math.min(5.0D + Math.floor(Math.max(0.0D, Math.pow((mtdkof * 2.0F) * (1.5D - kof1m), 1.75D))), 145.0D);
      if (entity instanceof EntityPlayer && (
        (EntityPlayer)entity).getCurrentArmor(0) != null && (
        (EntityPlayer)entity).getCurrentArmor(0).getItem() == MFQM.WadingBoots) {
        boots = true;
        bootsIsFloat = true;
      } 
      if (EposY - EprevPosY > 0.2D)
        entJump = true; 
      if (entity.motionY < -0.1D / Math.max(1.0F, mtdkof / 2.0F))
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
          movKofDiv += mr_kof * 0.004D; 
      } 
      if (!bootsIsFloat) {
        entity.motionX *= 0.5D / mtdkof;
        entity.motionZ *= 0.5D / mtdkof;
      } else {
        entity.motionX *= 0.5D;
        entity.motionZ *= 0.5D;
      } 
      if (entity.motionY > -0.1D) {
        entity.motionY = 0.0D;
      } else {
        if (entity.motionY < -0.1D)
          entity.motionY /= 1.25D; 
        entity.motionY /= 1.01D;
      } 
      if (kof1 < 0.9D || mtd > 6)
        bootsIsFloat = false; 
      if ((kof1 < 0.9D && kof1m != 0.0D) || mtd > 6)
        entUnderSurface = true; 
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
      if (world.getTotalWorldTime() % 128L == 0L || (entMoving && world.getTotalWorldTime() % Math.max((int)Math.floor(movCof / 2.0D), 1) == 0L) || (entJump && world.getTotalWorldTime() % 8L == 0L) || entSplash) {
        if (entSplash && 
          kof2 > 1.5D)
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "game.player.swim", 0.25F, world.rand.nextFloat() * 0.25F + 0.1F); 
        if (!entSplash && entMoving && 
          world.rand.nextInt(2) == 0)
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "game.player.swim", 0.1F, world.rand.nextFloat() * 0.25F + 0.75F); 
        if (entJump)
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.slime.attack", 0.25F, world.rand.nextFloat() * 0.25F + 0.25F); 
        if (!entJump && !entMoving && !entSplash && 
          world.rand.nextInt(5) == 0)
          if (world.rand.nextInt(5) == 0) {
            world.playSoundEffect(entity.posX, EposY, entity.posZ, "liquid.water", 0.5F, world.rand.nextFloat() * 0.15F + 0.1F);
          } else {
            world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.step", 0.25F, world.rand.nextFloat() * 0.15F + 0.1F);
          }  
        if (mtd > 0 && (entUnderSurface || (mtd > 4 && world.rand.nextInt(20 - mtd) == 0))) {
          int crck = Breaking(world, x, y, z, world.rand);
          if (crck == 2)
            return; 
          if (crck == 1) {
            world.playSoundEffect(entity.posX, EposY, entity.posZ, "game.player.swim", 0.15F, world.rand.nextFloat() * 0.25F + 0.1F);
            world.playSoundEffect(entity.posX, EposY, entity.posZ, "dig.grass", 0.15F, world.rand.nextFloat() * 0.25F + 0.75F);
          } 
          int xx = x - 1;
          int zz = z - 1;
          xx = x - 1;
          if (world.rand.nextInt(6) == 0)
            Breaking(world, xx, y, zz, world.rand); 
          xx = x;
          if (world.rand.nextInt(4) == 0)
            Breaking(world, xx, y, zz, world.rand); 
          xx = x + 1;
          if (world.rand.nextInt(6) == 0)
            Breaking(world, xx, y, zz, world.rand); 
          zz = z;
          if (world.rand.nextInt(4) == 0)
            Breaking(world, xx, y, zz, world.rand); 
          xx = x;
          if (world.rand.nextInt(4) == 0)
            Breaking(world, xx, y, zz, world.rand); 
          xx = x - 1;
          if (world.rand.nextInt(4) == 0)
            Breaking(world, xx, y, zz, world.rand); 
          zz = z + 1;
          if (world.rand.nextInt(6) == 0)
            Breaking(world, xx, y, zz, world.rand); 
          xx = x;
          if (world.rand.nextInt(4) == 0)
            Breaking(world, xx, y, zz, world.rand); 
          xx = x + 1;
          if (world.rand.nextInt(6) == 0)
            Breaking(world, xx, y, zz, world.rand); 
        } 
      } 
      if (world.getBlock(x, y + 1, z) != this || (world.getBlock(x, y + 1, z) == this && world.getBlock(x, y + 2, z) == this)) {
        if (world.getBlock(x, y + 1, z) == this && world.getBlock(x, y + 2, z) == this)
          kof1 = 0.001D; 
        double mys = 0.0D;
        if (!(entity instanceof EntityPlayer))
          mys = -0.005D; 
        int KMV = (int)Math.floor(movDis * 200.0D);
        if (entJump && 
          world.rand.nextInt(1) == 0 && 
          MFQM.suctionWorldCheck(entity, world, prevVel)) {
          entity.motionY -= 0.05D * (Math.min(0.75D, kof1m) + 1.0D);
          world.playSound(entity.posX, EposY, entity.posZ, "mob.magmacube.jump", 0.25F, 0.25F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F, false);
        } 
        if (mtd > 0 && kof1m != 0.0D && 
          world.getTotalWorldTime() % (int)Math.floor(Math.max(kof1m * 5.0D / mtdkof, 1.0D)) == 0L)
          entity.setInWeb(); 
        if (kof1 >= 0.9D) {
          if (KMV != 0 && tmeta < 7) {
            if (bootsIsFloat)
              mtd_deep /= 2.0D; 
            if (world.rand.nextInt(Math.max((int)Math.floor(Math.pow(kof1, 2.0D) * KMV / (15.0D - mtd_deep * 20.0D)), 1)) == 0) {
              entity.motionY += Math.min(1.45D - mtd_deep - kof1, 0.0855D + mys);
            } else {
              entity.motionY += 0.0855D + mys - 0.02D;
            } 
          } else if (tmeta > 6) {
            double a2 = 1.025D;
            if (bootsIsFloat)
              a2 = 1.05D; 
            if (!entMoving) {
              entity.motionY += (0.0725D + mys) * a2;
            } else {
              entity.motionY += (0.0725D + mys) * a2 / (movKofDiv * 2.0D + 0.075D);
            } 
          } else {
            entity.motionY += 0.085D + mys;
          } 
          entity.fallDistance = 0.0F;
          entity.onGround = true;
        } else {
          if (EposY - EprevPosY > 0.001D && 
            world.rand.nextInt(Math.max((int)Math.floor(kof1m * 3.0D), 10)) == 0 && 
            MFQM.suctionWorldCheck(entity, world, prevVel)) {
            entity.motionY -= 0.035D * mtdkof * (Math.min(0.75D, kof1m) + 1.0D);
            world.playSound(entity.posX, EposY, entity.posZ, "mob.magmacube.jump", 0.25F, 0.25F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F, false);
          } 
          double a2 = 1.01D;
          if (world.getTotalWorldTime() % Math.max((int)Math.floor(Math.min(2.0D, Math.max(32.0D - kof1 * 32.0D, 1.0D))), 1) == 0L) {
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
              entity.motionY += (0.0725D + mys) * a2 / (movKofDiv + 0.025D);
            } 
            if (kof1 < 0.45D && 
              MFQM.isTrulySink(entity, kof1m))
              MFQM.setStuckEffect((EntityLivingBase)entity, 145); 
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
    } else if (mtd > 6) {
      if (kof1 < 1.25D)
        entity.setInWeb(); 
    } else {
      entity.motionX *= 0.5880000114440918D;
      entity.motionZ *= 0.5880000114440918D;
      entity.motionY = 0.0D;
      if (kof1 < 1.4D) {
        entity.motionY += 0.1D;
      } else {
        entity.motionY += 0.03999999910593033D;
      } 
    } 
  }
  
  public int idDropped(int par1, Random random, int par3) {
    return 0;
  }
  
  public void harvestBlock(World world, EntityPlayer entityPlayer, int x, int y, int z, int par6) {
    if (EnchantmentHelper.getSilkTouchModifier((EntityLivingBase)entityPlayer)) {
      MFQM.dropItem(world, x, y, z, new ItemStack(this, 1, Math.min(Math.max(1, par6), 8)));
      world.markBlockForUpdate(x, y, z);
      return;
    } 
    MFQM.dropItem(world, x, y, z, new ItemStack(MFQM.PeatItem, 1));
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
      return 5.0E-5F; 
    float f = getBlockHardness(par2World, par3, par4, par5);
    return ForgeHooks.blockStrength(this, par1EntityPlayer, par2World, par3, par4, par5);
  }
  
  @SideOnly(Side.CLIENT)
  public void randomDisplayTick(World world, int x, int y, int z, Random random) {
    if (world.getBlock(x, y + 1, z).getMaterial() == Material.air && 
      world.rand.nextInt(2000) == 0) {
      double xx = x + random.nextFloat();
      double zz = z + random.nextFloat();
      float vol = 0.2F;
      world.playSound(xx, y, zz, "liquid.lavapop", vol + random.nextFloat() * 0.25F, 0.25F + random.nextFloat() * 0.5F, false);
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int par1, int par2) {
    int ic = par2;
    if (par1 == 1) {
      if (ic < 0)
        ic = 0; 
      if (ic > 9)
        ic = 9; 
      return this.IIconArray[ic];
    } 
    if (par1 == 0)
      return this.IIconSide1; 
    return this.IIconSide0;
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister par1IIconRegister) {
    this.IIconArray = new IIcon[10];
    this.IIconArray[0] = par1IIconRegister.registerIcon("morefunquicksandmod:Moor0");
    this.IIconArray[1] = this.IIconArray[0];
    this.IIconArray[2] = par1IIconRegister.registerIcon("morefunquicksandmod:Moor1");
    this.IIconArray[3] = par1IIconRegister.registerIcon("morefunquicksandmod:Moor2");
    this.IIconArray[4] = par1IIconRegister.registerIcon("morefunquicksandmod:Moor3");
    this.IIconArray[5] = par1IIconRegister.registerIcon("morefunquicksandmod:Moor4");
    this.IIconArray[6] = par1IIconRegister.registerIcon("morefunquicksandmod:Moor5");
    this.IIconArray[7] = par1IIconRegister.registerIcon("morefunquicksandmod:Moor6");
    this.IIconArray[8] = par1IIconRegister.registerIcon("morefunquicksandmod:Moor7");
    this.IIconArray[9] = par1IIconRegister.registerIcon("morefunquicksandmod:Moor8");
    this.IIconSide0 = par1IIconRegister.registerIcon("morefunquicksandmod:Moor_side0");
    this.IIconSide1 = par1IIconRegister.registerIcon("morefunquicksandmod:Moor_side1");
  }
  
  public int getDamageValue(World par1World, int par2, int par3, int par4) {
    return par1World.getBlockMetadata(par2, par3, par4);
  }
  
  public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List<ItemStack> list) {
    list.add(new ItemStack(this, 1, 1));
    list.add(new ItemStack(this, 1, 2));
    list.add(new ItemStack(this, 1, 3));
    list.add(new ItemStack(this, 1, 4));
    list.add(new ItemStack(this, 1, 5));
    list.add(new ItemStack(this, 1, 6));
    list.add(new ItemStack(this, 1, 7));
    list.add(new ItemStack(this, 1, 8));
  }
  
  protected boolean canSilkHarvest() {
    return true;
  }
  
  public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plant) {
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
  
  public void PlantingThis(World world, Random rand, int x, int y, int z) {
    int var6 = 0;
    label24: while (var6 < 128) {
      int var7 = x;
      int var8 = y + 1;
      int var9 = z;
      int var10 = 0;
      while (var10 < var6 / 16) {
        var7 += rand.nextInt(3) - 1;
        var8 += (rand.nextInt(3) - 1) * rand.nextInt(3) / 2;
        var9 += rand.nextInt(3) - 1;
        if (world.getBlock(var7, var8 - 1, var9) == Blocks.grass || world.getBlock(var7, var8 - 1, var9) == this) {
          if (!world.getBlock(var7, var8, var9).isNormalCube()) {
            var10++;
            continue;
          } 
          continue label24;
        } 
        continue label24;
      } 
      if (world.getBlock(var7, var8, var9).getMaterial() == Material.air)
        if (rand.nextInt(8) == 0) {
          String var13 = world.getBiomeGenForCoords(var7, var9).func_150572_a(rand, var7, var8, var9);
          BlockFlower var11 = BlockFlower.func_149857_e(var13);
          if (var11 != null && var11.canBlockStay(world, var7, var8, var9)) {
            int var12 = BlockFlower.func_149856_f(var13);
            world.setBlock(var7, var8, var9, (Block)var11, var12, 3);
          } 
        }  
      var6++;
    } 
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
