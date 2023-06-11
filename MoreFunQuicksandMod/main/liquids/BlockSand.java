package MoreFunQuicksandMod.main.liquids;

import MoreFunQuicksandMod.main.CustomMaterialLiquid;
import MoreFunQuicksandMod.main.MFQM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;

public class BlockSand extends BlockFluidClassic {
  public IIcon[] theSandIIcon;
  
  public static final Material materialSand = (new CustomMaterialLiquid(MapColor.sandColor)).setReplaceable();
  
  public BlockSand() {
    super(MFQM.SandFluid, materialSand);
    setQuantaPerBlock(2);
    this.quantaPerBlockFloat = 1.75F;
    if (MFQM.QSOpacity) {
      setLightOpacity(3);
    } else {
      setLightOpacity(3);
    } 
    setTickRandomly(true);
    setUnlocalizedName("SoftSand");
    setRenderPass(0);
    setCreativeTab(MFQM.tabMFQM);
  }
  
  public void breakBlock(World world, int x, int y, int z, Block bl, int meta) {
    if (meta == 0 && 
      world.getBlock(x, y, z) != this) {
      int chckRes = checkFusion(world, x, y, z, world.rand);
      if (chckRes != 0)
        return; 
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
  
  public int getMaxRenderHeightMeta() {
    return -1;
  }
  
  public float getPlayerRelativeBlockHardness(EntityPlayer par1EntityPlayer, World par2World, int par3, int par4, int par5) {
    return -1.0F;
  }
  
  public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
    int mtd = world.getBlockMetadata(x, y, z);
    double EposY = entity.posY;
    double EprevPosY = entity.prevPosY;
    double prevVel = entity.motionY;
    double oy = y - EposY + 1.0D - (mtd / 3);
    double oy2 = y - EprevPosY + 1.0D - (mtd / 3);
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
      oy = y - EposY - 0.5D - (mtd / 3);
      oy2 = y - EprevPosY - 0.5D - (mtd / 3);
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
      if (MFQM.WLD && 
        entity.worldObj.provider.dimensionId == MFQM.WLDDim && 
        entity instanceof net.minecraft.entity.monster.EntityMob)
        Affect = false; 
      if (!Affect)
        checkEntityUnder(entity); 
      boolean entJump = false;
      boolean entMoving = false;
      boolean entSplash = false;
      boolean entRotate = false;
      float mtdkof = 1.0F;
      double movDis = 1.0D;
      double movCof = 16.0D;
      double movKofDiv = 1.0D;
      if (MFQM.ABC && (
        world.provider.dimensionId == MFQM.ABCDim3 || world.provider.dimensionId == MFQM.ABCDim4))
        mtdkof = 2.0F; 
      int mr_kof = (int)Math.min(5.0D + Math.floor(Math.max(0.0D, Math.pow((mtdkof * 20.0F) * (1.5D - kof1m), 1.5D))), 128.0D);
      if (EposY - EprevPosY > 0.1D)
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
      if (world.getTotalWorldTime() % 4L == 0L || (entMoving && world.getTotalWorldTime() % Math.max((int)Math.floor(movCof / 1.5D), 1) == 0L) || (entJump && world.getTotalWorldTime() % 8L == 0L) || entSplash) {
        if (entSplash && 
          kof2 > 1.5D)
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "step.sand", 0.15F, world.rand.nextFloat() * 0.5F + 0.5F); 
        if (!entSplash && entMoving && 
          world.rand.nextInt(2) == 0)
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "step.grass", 0.25F, world.rand.nextFloat() * 0.5F + 0.5F); 
        if (entJump)
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "dig.sand", 0.15F, world.rand.nextFloat() * 0.5F + 0.5F); 
        if (!entSplash && !entMoving && !entJump && 
          world.rand.nextInt(10) == 0)
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "step.grass", 0.15F, world.rand.nextFloat() * 0.5F + 0.5F); 
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
      if (world.getBlock(x, y + 1, z) != this || (world.getBlock(x, y + 1, z) == this && world.getBlock(x, y + 2, z) == this)) {
        if (world.getBlock(x, y + 1, z) == this && world.getBlock(x, y + 2, z) == this)
          kof1 = 0.001D; 
        double mys = 0.0D;
        if (!(entity instanceof EntityPlayer))
          mys = -0.0025D; 
        if (kof1 >= 1.2D) {
          double a1 = 0.07485D;
          entity.motionY += a1 / Math.max((movKofDiv - 1.0D) * Math.max(kof1m - 0.5D, 0.75D) * 1.6D * 17.5D, 1.0D) / Math.pow(Math.max(kof1m / 1.25D, 1.0D), 2.0D);
          entity.onGround = true;
          entity.fallDistance = 0.0F;
        } else if (kof1 >= 0.9D) {
          double a1 = 0.07485D;
          entity.motionY += a1 / Math.max((movKofDiv - 1.0D) * Math.max(kof1m - 0.5D, 0.75D) * 1.6D * 17.5D, 1.0D) / Math.pow(Math.max(kof1m / 1.25D, 1.0D), 2.0D);
          MFQM.setStuckEffect((EntityLivingBase)entity, mr_kof);
          if (!entSplash) {
            entity.onGround = true;
            entity.fallDistance = 0.0F;
          } 
          if (entity instanceof EntityPlayer) {
            if (world.getTotalWorldTime() % Math.max(1.0D + Math.floor(Math.max(kof1 * 5.0D - (mtd / 10), 0.0D)), 1.0D) == 0.0D) {
              entity.setInWeb();
              entity.onGround = false;
            } 
          } else {
            double mw_kof = (1 + mr_kof / 10);
            entity.setPosition(entity.prevPosX + (entity.posX - entity.prevPosX) / mw_kof, EposY, entity.prevPosZ + (entity.posZ - entity.prevPosZ) / mw_kof);
          } 
        } else {
          double a2 = 0.9D;
          if (world.rand.nextInt(Math.max((int)Math.floor(25.0D - Math.pow(Math.max(kof1m, 0.0D), 1.5D)), 1)) == 0)
            entity.onGround = true; 
          if (MFQM.isTrulySink(entity, kof1m))
            MFQM.setStuckEffect((EntityLivingBase)entity, mr_kof); 
          entity.fallDistance = 0.0F;
          if (kof1 >= 0.5D) {
            if (world.rand.nextInt((int)Math.floor(Math.max(kof1m * 5.0D / 2.0D, 1.0D))) == 0) {
              entity.setInWeb();
              if (!entMoving) {
                entity.motionY += (0.0725D + mys) * a2;
              } else {
                entity.motionY += (0.0725D + mys) * a2 / (movKofDiv + 0.25D);
              } 
            } else if (!entMoving) {
              entity.motionY += (0.075D + mys) * a2;
            } else {
              entity.motionY += (0.075D + mys) * a2 / (movKofDiv + 0.25D);
            } 
          } else {
            entity.setInWeb();
            if (!entMoving) {
              entity.motionY += (0.0725D + mys) * a2;
            } else {
              entity.motionY += (0.0725D + mys) * a2 / (movKofDiv + 0.25D);
            } 
            if (kof1 < 0.45D && 
              MFQM.isTrulySink(entity, kof1m))
              MFQM.setStuckEffect((EntityLivingBase)entity, 128); 
          } 
        } 
        if (world.getTotalWorldTime() % Math.max(1.0D + Math.floor(Math.max(kof1 * 10.0D, 0.0D)), 1.0D) == 0.0D)
          entity.onGround = false; 
        if (entity.isInWater() && 
          entity.motionY > 0.0D)
          entity.motionY /= 4.0D; 
        if (this.densityDir <= 0) {
          Vec3 vec_flow = getFlowVector((IBlockAccess)world, x, y, z);
          double xx = x + 0.5D;
          double zz = z + 0.5D;
          double disq = Math.pow(Math.pow(entity.posX - xx, 2.0D) + Math.pow(entity.posZ - zz, 2.0D), 0.5D);
          entity.motionX += vec_flow.xCoord * 0.25D / Math.max(10.0D * disq, 1.0D);
          entity.motionZ += vec_flow.zCoord * 0.25D / Math.max(10.0D * disq, 1.0D);
        } 
      } else {
        entity.setInWeb();
        if (kof1 < 0.5D && 
          MFQM.isTrulySink(entity, kof1m))
          MFQM.setStuckEffect((EntityLivingBase)entity, 128); 
      } 
      MFQM.antiHoldJumpScript(entity, kof1, true);
    } else if (kof1 < 1.45D) {
      entity.setInWeb();
      if (this.densityDir <= 0) {
        Vec3 vec_flow = getFlowVector((IBlockAccess)world, x, y, z);
        double xx = x + 0.5D;
        double zz = z + 0.5D;
        double disq = Math.pow(Math.pow(entity.posX - xx, 2.0D) + Math.pow(entity.posZ - zz, 2.0D), 0.5D);
        entity.motionX += vec_flow.xCoord * 0.25D / Math.max(10.0D * disq, 1.0D);
        entity.motionZ += vec_flow.zCoord * 0.25D / Math.max(10.0D * disq, 1.0D);
      } 
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int par1, int par2) {
    return (par1 != 0 && par1 != 1) ? this.theSandIIcon[1] : this.theSandIIcon[0];
  }
  
  public void modifyEntityVelocity(World par1World, int par2, int par3, int par4, Entity par5Entity, Vec3 par6Vec3) {}
  
  public int checkFusion(World world, int x, int y, int z, Random rand) {
    if (MFQM.ABC && (
      world.provider.dimensionId == MFQM.ABCDim3 || world.provider.dimensionId == MFQM.ABCDim4))
      return 0; 
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
    if (isWaterNear && 
      world.getBlockMetadata(x, y, z) == 0) {
      world.setBlock(x, y, z, MFQM.QuicksandBlock, 0, 3);
      world.playSoundEffect((x + 0.5F), (y + 0.5F), (z + 0.5F), "mob.silverfish.step", 0.25F, world.rand.nextFloat() * 0.2F + 0.2F);
      return 2;
    } 
    return 0;
  }
  
  public void updateTick(World world, int x, int y, int z, Random rand) {
    int chckRes = checkFusion(world, x, y, z, rand);
    if (chckRes == 2)
      return; 
    if (world.getBlockMetadata(x, y, z) == 0) {
      Block block = world.getBlock(x, y + this.densityDir, z);
      int bMeta = world.getBlockMetadata(x, y + this.densityDir, z);
      if (block == this && bMeta != 0) {
        world.setBlock(x, y + this.densityDir, z, (Block)this);
        world.setBlockMetadataWithNotify(x, y + this.densityDir, z, 0, 2);
        world.setBlockToAir(x, y, z);
        return;
      } 
    } else {
      int inThisBlocks = 0;
      int inMetaBlocks = 0;
      if (world.rand.nextInt(5) == 0) {
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
          inMetaBlocks > 2)
          world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) - 1, 2); 
      } 
    } 
    this.quantaPerBlockFloat = 1.75F;
    super.updateTick(world, x, y, z, rand);
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
    if (getMaterial() == materialSand)
      this.theSandIIcon = new IIcon[] { par1IIconRegister.registerIcon("morefunquicksandmod:Sand_Still"), par1IIconRegister.registerIcon("morefunquicksandmod:Sand_Flowing") }; 
  }
  
  public boolean isNormalCube() {
    return false;
  }
  
  public void checkEntityUnder(Entity ent) {
    if (MFQM.isEntityInsideOfBlock(ent, (Block)this) && 
      MFQM.isDrowning(ent) && 
      !ent.worldObj.isRemote && 
      ent.isEntityAlive())
      ent.attackEntityFrom(DamageSource.inWall, Math.max(((EntityLivingBase)ent).getMaxHealth() * 0.1F, 2.0F)); 
  }
  
  @SideOnly(Side.CLIENT)
  public int getBlockColor() {
    if (MFQM.ABC && (
      (Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.ABCDim3 || 
      (Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.ABCDim4))
      return 1048597; 
    if (MFQM.AOA && 
      (Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.AOADimPrecasia)
      return 10651803; 
    return 16777215;
  }
  
  @SideOnly(Side.CLIENT)
  public int getRenderColor(int par1) {
    if (MFQM.ABC && (
      (Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.ABCDim3 || 
      (Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.ABCDim4))
      return 1048597; 
    if (MFQM.AOA && 
      (Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.AOADimPrecasia)
      return 10651803; 
    return 16777215;
  }
  
  @SideOnly(Side.CLIENT)
  public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
    if (MFQM.ABC && (
      (Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.ABCDim3 || 
      (Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.ABCDim4))
      return 1048597; 
    if (MFQM.AOA && 
      (Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.AOADimPrecasia)
      return 10651803; 
    return 16777215;
  }
}
