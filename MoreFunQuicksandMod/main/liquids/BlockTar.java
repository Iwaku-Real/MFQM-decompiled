package MoreFunQuicksandMod.main.liquids;

import MoreFunQuicksandMod.main.CustomMaterialLiquid;
import MoreFunQuicksandMod.main.CustomVarPlayer;
import MoreFunQuicksandMod.main.MFQM;
import MoreFunQuicksandMod.main.PotionStuck;
import MoreFunQuicksandMod.main.entity.EntityTarTreads;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;

public class BlockTar extends BlockFluidClassic {
  public IIcon[] theTarIIcon;
  
  public static final Material materialTar = (new CustomMaterialLiquid(MapColor.airColor)).setReplaceable();
  
  private int maxOpacity;
  
  private int lastOpacity;
  
  private int incOpacity;
  
  public BlockTar(int mopc, int lopc, int iopc) {
    super(MFQM.TarFluid, materialTar);
    setResistance(1000.0F);
    setQuantaPerBlock(2);
    if (MFQM.QSOpacity) {
      setLightOpacity(32);
    } else {
      setLightOpacity(3);
    } 
    setTickRandomly(true);
    setUnlocalizedName("Tar");
    setRenderPass(0);
    setCreativeTab(MFQM.tabMFQM);
    this.maxOpacity = mopc;
    this.lastOpacity = lopc;
    this.incOpacity = iopc;
  }
  
  public void breakBlock(World world, int x, int y, int z, Block bl, int meta) {
    if (meta == 0 && 
      world.getBlock(x, y, z) != this) {
      if (world.getBlock(x, y, z).getMaterial().isLiquid()) {
        this;
        int denToRep = getDensity((IBlockAccess)world, x, y, z);
        if (denToRep >= this.density)
          return; 
      } 
      if (!world.isAirBlock(x, y, z))
        world.setBlock(x, y, z, (Block)this, meta, 1); 
    } 
  }
  
  public boolean canStopRayTrace(int meta, boolean fullHit) {
    return (meta == 0);
  }
  
  public float getPlayerRelativeBlockHardness(EntityPlayer par1EntityPlayer, World par2World, int par3, int par4, int par5) {
    return -1.0F;
  }
  
  public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
    int mtd = world.getBlockMetadata(x, y, z);
    double EposY = entity.posY;
    double EprevPosY = entity.prevPosY;
    double prevVel = entity.motionY;
    double oy = y - EposY + 1.0D - 0.075D - mtd;
    double oy2 = y - EprevPosY + 1.0D - 0.075D - mtd;
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
      oy = y - EposY - 0.5D + 0.075D - mtd;
      oy2 = y - EprevPosY - 0.5D + 0.075D - mtd;
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
      if (entity instanceof MoreFunQuicksandMod.main.entity.EntityTarSlime)
        Affect = false; 
      if (MFQM.TBL)
        for (Class tmpClass : MFQM.TBLMobsTar) {
          if (tmpClass.isAssignableFrom(entity.getClass()))
            Affect = false; 
        }  
      if (!(entity instanceof EntityPlayer) && 
        MFQM.ABC && (
        entity.worldObj.provider.dimensionId == MFQM.ABCDim2 || entity.worldObj.provider.dimensionId == MFQM.ABCDim3 || entity.worldObj.provider.dimensionId == MFQM.ABCDim4))
        Affect = false; 
      if (MFQM.WLD && 
        entity.worldObj.provider.dimensionId == MFQM.WLDDim && 
        entity instanceof net.minecraft.entity.monster.EntityMob)
        Affect = false; 
      if (MFQM.AOA && (
        entity.worldObj.provider.dimensionId == MFQM.AOADimIromine || entity.worldObj.provider.dimensionId == MFQM.AOADimDeeplands || entity.worldObj.provider.dimensionId == MFQM.AOADimDustopia || entity.worldObj.provider.dimensionId == MFQM.AOADimBarathos || entity.worldObj.provider.dimensionId == MFQM.AOADimGreckon))
        if (entity instanceof net.minecraft.entity.monster.EntityMob)
          Affect = false;  
      if (Affect)
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
      int mr_kof = (int)Math.min(5.0D + Math.floor(Math.max(0.0D, Math.pow((2.0F * mtdkof) * (1.5D - kof1m), 2.25D))), 255.0D);
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
      entity.motionX = 0.0D;
      entity.motionZ = 0.0D;
      if (entity.motionY > -0.1D) {
        entity.motionY = 0.0D;
      } else {
        entity.motionY /= 2.0D;
      } 
      if (entity instanceof MoreFunQuicksandMod.main.entity.EntityTarSlime) {
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
      if (world.getTotalWorldTime() % 128L == 0L || (entMoving && world.getTotalWorldTime() % Math.max((int)Math.floor(movCof), 1) == 0L) || (entJump && world.getTotalWorldTime() % 8L == 0L) || entSplash) {
        if (entSplash && 
          kof2 > 1.5D) {
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.step", 0.25F, world.rand.nextFloat() * 0.25F + 0.1F);
          if (entity instanceof EntityPlayer && 
            world.rand.nextInt(2) == 0) {
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, (Block)this, 0);
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, (Block)this, 0);
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, (Block)this, 0);
          } 
        } 
        if (!entSplash && entMoving) {
          if (world.rand.nextInt(2) == 0)
            world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.step", 0.15F, world.rand.nextFloat() * 0.25F + 0.1F); 
          if (world.rand.nextInt(5) == 0)
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, (Block)this, 0); 
        } 
        if (entJump) {
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.slime.attack", 0.25F, world.rand.nextFloat() * 0.25F + 0.25F);
          if (entity instanceof EntityPlayer && 
            world.rand.nextInt(2) == 0) {
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, (Block)this, 0);
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, (Block)this, 0);
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, (Block)this, 0);
          } 
        } 
        if (!entJump && !entMoving && !entSplash && 
          world.rand.nextInt(5) == 0)
          if (world.rand.nextInt(5) == 0) {
            world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.step", 0.5F, world.rand.nextFloat() * 0.15F + 0.1F);
          } else {
            world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.step", 0.25F, world.rand.nextFloat() * 0.15F + 0.1F);
          }  
      } 
      if (world.getTotalWorldTime() % 16L == 0L && 
        world.rand.nextInt(10) == 0)
        MFQM.spawnBodyBubble(world, entity, x, y, z, (Block)this, 0); 
      if (MFQM.QSTarTreads && 
        world.getTotalWorldTime() % 16L == 0L && 
        world.isRemote && (
        world.rand.nextInt(25) == 0 || entMoving || world.rand.nextInt(10) == 0)) {
        PotionStuck props = PotionStuck.get((EntityLivingBase)entity);
        if (props != null && 
          props.getTarTreads() < 10) {
          double xx = entity.posX + (world.rand.nextFloat() * 2.0F) - 1.0D;
          double zz = entity.posZ + (world.rand.nextFloat() * 2.0F) - 1.0D;
          if (world.getBlock((int)Math.floor(xx), y, (int)Math.floor(zz)) != this)
            return; 
          double Dept = (y + 1.0D - entity.boundingBox.minY) / entity.height;
          Dept -= world.rand.nextFloat() * Dept * 0.1D;
          world.spawnEntityInWorld((Entity)new EntityTarTreads(world, xx, y - world.rand.nextFloat() * 0.15D - 0.1D, zz, Dept, 0, (EntityLivingBase)entity));
        } 
      } 
      if (world.getBlock(x, y + 1, z) != this || (world.getBlock(x, y + 1, z) == this && world.getBlock(x, y + 2, z) == this)) {
        if (world.getBlock(x, y + 1, z) == this && world.getBlock(x, y + 2, z) == this)
          kof1 = 0.001D; 
        double mys = 0.0D;
        if (!(entity instanceof EntityPlayer))
          mys = -0.0025D; 
        if (entJump && 
          MFQM.suctionWorldCheck(entity, world, prevVel)) {
          entity.motionY -= 0.025D * mtdkof * (Math.min(1.5D, kof1m) + 1.0D);
          world.playSound(entity.posX, EposY, entity.posZ, "mob.magmacube.jump", 0.25F, 0.25F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F, false);
        } 
        if (EposY - EprevPosY > 0.001D && 
          world.rand.nextInt(Math.max((int)Math.floor(kof1m * 5.0D), 1)) == 0 && 
          MFQM.suctionWorldCheck(entity, world, prevVel)) {
          entity.motionY -= 0.025D * mtdkof * (Math.min(0.75D, kof1m) + 1.0D);
          world.playSound(entity.posX, EposY, entity.posZ, "mob.magmacube.jump", 0.25F, 0.25F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F, false);
        } 
        if (kof1 >= 1.3D) {
          if (!entSplash) {
            double a1 = 0.0745D;
            entity.onGround = false;
            entity.motionY += a1 / Math.max(1.0D, kof1 * 7.0D / 9.0D);
            if (world.getTotalWorldTime() % Math.max(1.0D + Math.floor(Math.max(kof1 * 3.0D - (mtd / 10), 0.0D)), 1.0D) == 0.0D)
              entity.setInWeb(); 
            if (world.rand.nextInt(Math.max((int)Math.floor((5.0F * mtdkof) - Math.pow(Math.max(kof1m, 0.0D), 1.5D)), 1)) == 0)
              entity.onGround = true; 
            entity.fallDistance = 0.0F;
          } 
          MFQM.setStuckEffect((EntityLivingBase)entity, mr_kof);
        } else {
          double xx = x + 0.5D;
          double zz = z + 0.5D;
          double disq = Math.pow(Math.pow(entity.posX - xx, 2.0D) + Math.pow(entity.posZ - zz, 2.0D), 0.5D);
          if (disq < 0.5D && 
            disq >= 0.075D) {
            entity.motionX += (xx - entity.posX) / Math.max(5.0D, kof1m * 25.0D);
            entity.motionZ += (zz - entity.posZ) / Math.max(5.0D, kof1m * 25.0D);
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
            world.rand.nextInt(Math.max((int)Math.floor(kof1m * 2.0D), 5)) == 0 && 
            MFQM.suctionWorldCheck(entity, world, prevVel)) {
            entity.motionY -= 0.025D * mtdkof * (Math.min(1.5D, kof1m) + 1.0D);
            world.playSound(entity.posX, EposY, entity.posZ, "mob.magmacube.jump", 0.25F, 0.25F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F, false);
          } 
          entity.onGround = false;
          entity.fallDistance = 0.0F;
          if (kof1 >= 0.9D) {
            double a1 = 0.075D + mys;
            if (world.getTotalWorldTime() % 2L == 0L) {
              a1 = 0.07485D + mys;
            } else {
              a1 = 0.075D + mys;
            } 
            entity.motionY += a1 / Math.max(1.0D, kof1 * 7.0D / 9.0D);
            MFQM.setStuckEffect((EntityLivingBase)entity, mr_kof);
            if (!entSplash && 
              world.rand.nextInt(Math.max((int)Math.floor((10.0F * mtdkof) - Math.pow(Math.max(kof1m, 0.0D), 1.5D)), 1)) == 0)
              entity.onGround = true; 
            if (entity instanceof EntityPlayer) {
              if (world.getTotalWorldTime() % Math.max(1.0D + Math.floor(Math.max(kof1 * 3.0D - (mtd / 10), 0.0D)), 1.0D) == 0.0D) {
                entity.setInWeb();
                entity.onGround = false;
              } 
            } else {
              double mw_kof = (1 + mr_kof / 5);
              entity.setPosition(entity.prevPosX + (entity.posX - entity.prevPosX) / mw_kof, EposY, entity.prevPosZ + (entity.posZ - entity.prevPosZ) / mw_kof);
            } 
          } else {
            double a2 = 1.01D;
            entity.onGround = false;
            if (world.rand.nextInt(Math.max((int)Math.floor((25.0F * mtdkof) - Math.pow(Math.max(kof1m, 0.0D), 1.5D)), 1)) == 0)
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
                  entity.motionY += (0.0725D + mys) * a2 / (movKofDiv + 0.05D);
                } 
              } else if (!entMoving) {
                entity.motionY += (0.075D + mys) * a2;
              } else {
                entity.motionY += (0.075D + mys) * a2 / (movKofDiv + 0.05D);
              } 
            } else {
              entity.setInWeb();
              if (!entMoving) {
                entity.motionY += (0.0725D + mys) * a2;
              } else {
                entity.motionY += (0.0725D + mys) * a2 / (movKofDiv + 0.05D);
              } 
              if (kof1 < 0.45D && 
                MFQM.isTrulySink(entity, kof1m))
                MFQM.setStuckEffect((EntityLivingBase)entity, 255); 
            } 
          } 
        } 
        if (MFQM.QSHotTar && 
          !world.isRemote) {
          if (entity instanceof EntityPlayer) {
            kof1 += 1.5D;
            kof1 = Math.max(kof1, 0.0D);
          } 
          if (kof1 < 0.75D && 
            world.getTotalWorldTime() % Math.max((int)Math.floor(kof1 * 50.0D) + 8, 1) == 0L && 
            world.rand.nextInt(32) == 0) {
            entity.attackEntityFrom(DamageSource.onFire, 1.0F);
            world.playSoundEffect(entity.posX, EposY, entity.posZ, "random.fizz", 0.25F, 0.25F + world.rand.nextFloat() * 0.5F);
          } 
        } 
        if (entity.isInWater() && 
          entity.motionY > 0.0D)
          entity.motionY /= 4.0D; 
      } else {
        entity.setInWeb();
        if (kof1 < 0.5D && 
          MFQM.isTrulySink(entity, kof1m))
          MFQM.setStuckEffect((EntityLivingBase)entity, 255); 
      } 
      MFQM.antiHoldJumpScript(entity, kof1, true);
    } else if (kof1 < 1.25D) {
      entity.setInWeb();
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int par1, int par2) {
    return (par1 != 0 && par1 != 1) ? this.theTarIIcon[1] : this.theTarIIcon[0];
  }
  
  public void TBLSpawnTarBeast(World world, int x, int y, int z) {
    if (MFQM.TBL && 
      world.provider.dimensionId == MFQM.TBLDim && 
      !world.isRemote && 
      world.difficultySetting != EnumDifficulty.PEACEFUL) {
      int tickRate = 128;
      int chance = 25;
      if (y < 100) {
        tickRate = 8;
        chance = 10;
      } 
      if (world.getTotalWorldTime() % tickRate == 0L)
        if (world.rand.nextInt(chance) == 0) {
          int var4 = world.getEntitiesWithinAABB(MFQM.TBLTarBeast, AxisAlignedBB.getBoundingBox(x, y, z, (x + 1), (y + 1), (z + 1)).expand(16.0D, 4.0D, 16.0D)).size();
          if (var4 >= 3)
            return; 
          boolean isInTar = (world.getBlock(x, y, z) == this && world.getBlock(x - 1, y, z) == this && world.getBlock(x + 1, y, z) == this && world.getBlock(x, y, z - 1) == this && world.getBlock(x, y, z + 1) == this);
          if (isInTar) {
            Entity tarBeast = EntityList.createEntityByName((String)EntityList.classToStringMapping.get(MFQM.TBLTarBeast), world);
            tarBeast.setLocationAndAngles(x + 0.5D, y, z + 0.5D, 0.0F, 0.0F);
            world.spawnEntityInWorld(tarBeast);
          } 
        }  
    } 
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
      if (met != 0) {
        world.setBlock(xx, yy, zz, Blocks.stone, 0, 3);
      } else {
        world.setBlock(xx, yy, zz, Blocks.obsidian, 0, 3);
      } 
      world.playSoundEffect((xx + 0.5F), (yy + 0.5F), (zz + 0.5F), "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
      for (int l = 0; l < 8; l++)
        world.spawnParticle("largesmoke", xx + Math.random(), yy + 1.2D, zz + Math.random(), 0.0D, 0.0D, 0.0D); 
      return;
    } 
    if (world.getBlockMetadata(x, y, z) == 0) {
      Block block = world.getBlock(x, y + this.densityDir, z);
      int bMeta = world.getBlockMetadata(x, y + this.densityDir, z);
      if (block == this && bMeta != 0) {
        world.setBlock(x, y + this.densityDir, z, (Block)this);
        world.setBlockMetadataWithNotify(x, y + this.densityDir, z, 0, 3);
        world.setBlockToAir(x, y, z);
        return;
      } 
    } else {
      int inThisBlocks = 0;
      int inMetaBlocks = 0;
      if (world.rand.nextInt(10) == 0) {
        if (world.getBlock(x + 1, y, z) == this) {
          inThisBlocks++;
          if (world.getBlockMetadata(x + 1, y, z) == 0)
            inMetaBlocks++; 
        } 
        if (world.getBlock(x - 1, y, z) == this) {
          inThisBlocks++;
          if (world.getBlockMetadata(x - 1, y, z) == 0)
            inMetaBlocks++; 
        } 
        if (world.getBlock(x, y, z - 1) == this) {
          inThisBlocks++;
          if (world.getBlockMetadata(x, y, z - 1) == 0)
            inMetaBlocks++; 
        } 
        if (world.getBlock(x, y, z + 1) == this) {
          inThisBlocks++;
          if (world.getBlockMetadata(x, y, z + 1) == 0)
            inMetaBlocks++; 
        } 
        if (inThisBlocks > 2 && 
          inMetaBlocks > 1)
          world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) - 1, 2); 
      } 
    } 
    if (MFQM.ABC && 
      world.provider.dimensionId == MFQM.ABCDim2 && 
      !world.isRemote && 
      MFQM.QSGas && 
      rand.nextInt(50) == 0 && 
      world.getBlockMetadata(x, y, z) == 0 && (
      world.getBlock(x, y + 1, z) == Blocks.air || world.getBlock(x, y + 1, z) == MFQM.GasBlock)) {
      world.setBlock(x, y + 1, z, MFQM.GasBlock, 15, 2);
      double xxx = x + rand.nextFloat();
      double zzz = z + rand.nextFloat();
      world.playSound(xxx, y, zzz, "random.fizz", 0.25F, 0.25F + rand.nextFloat() * 0.5F, false);
    } 
    TBLSpawnTarBeast(world, x, y, z);
    super.updateTick(world, x, y, z, rand);
  }
  
  public void modifyEntityVelocity(World par1World, int par2, int par3, int par4, Entity par5Entity, Vec3 par6Vec3) {}
  
  @SideOnly(Side.CLIENT)
  public void randomDisplayTick(World world, int x, int y, int z, Random random) {
    if (world.getBlock(x, y + 1, z).getMaterial() == Material.air) {
      int md = world.getBlockMetadata(x, y, z);
      if (world.rand.nextInt(150) == 0) {
        double xx = x + random.nextFloat();
        double zz = z + random.nextFloat();
        MFQM.spawnQSBubble(world, xx, (y + 1 / (1 + md)) - 0.15D, zz, (Block)this, 0, 0.5F);
      } 
      if ((!MFQM.ABC || world.provider.dimensionId != MFQM.ABCDim2) && 
        world.rand.nextInt(2000) == 1000) {
        double xx = x + random.nextFloat();
        double zz = z + random.nextFloat();
        world.playSound(xx, y, zz, "random.fizz", 0.25F, 0.25F + random.nextFloat() * 0.5F, false);
      } 
    } 
  }
  
  public boolean canDisplace(World world, int x, int y, int z) {
    if (world.getBlock(x, y, z).getMaterial().isLiquid())
      return false; 
    return canDisplace((IBlockAccess)world, x, y, z);
  }
  
  public boolean displaceIfPossible(World world, int x, int y, int z) {
    if (world.getBlock(x, y, z).getMaterial().isLiquid())
      return false; 
    return super.displaceIfPossible(world, x, y, z);
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister par1IIconRegister) {
    if (getMaterial() == materialTar)
      this.theTarIIcon = new IIcon[] { par1IIconRegister.registerIcon("morefunquicksandmod:Tar_Still"), par1IIconRegister.registerIcon("morefunquicksandmod:Tar_Flowing") }; 
  }
  
  public boolean isNormalCube() {
    return false;
  }
  
  public void checkEntityUnder(Entity ent) {
    if (MFQM.isEntityInsideOfBlock(ent, (Block)this) && 
      MFQM.isDrowning(ent)) {
      MFQM.spawnDrowningBubble(ent.worldObj, ent, (Block)this, 0);
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
        int mdtp = MFQM.getMuddyType((Block)this);
        props.setMuddyType(mdtp);
        if (props.getMuddyTime() < this.maxOpacity)
          props.addMuddyTime(this.incOpacity); 
        props.setMuddyTime(Math.min(props.getMuddyTime(), this.maxOpacity));
      } 
    } 
  }
}
