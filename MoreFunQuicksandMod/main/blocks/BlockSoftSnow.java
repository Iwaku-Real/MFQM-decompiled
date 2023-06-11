package MoreFunQuicksandMod.main.blocks;

import MoreFunQuicksandMod.main.MFQM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSnowBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockSoftSnow extends BlockSnowBlock {
  public IIcon theSnowIIcon;
  
  public BlockSoftSnow() {
    setHardness(0.75F);
    setStepSound(Block.soundTypeSnow);
    setUnlocalizedName("SoftSnow");
    setResistance(1.0F);
    if (MFQM.QSOpacity) {
      setLightOpacity(255);
    } else {
      setLightOpacity(3);
    } 
    setCreativeTab(MFQM.tabMFQM);
  }
  
  public int getDamageValue(World par1World, int par2, int par3, int par4) {
    return par1World.getBlockMetadata(par2, par3, par4);
  }
  
  @SideOnly(Side.CLIENT)
  public void getSubBlocks(Item block, CreativeTabs creativeTabs, List<ItemStack> list) {
    list.add(new ItemStack(block, 1, 0));
    list.add(new ItemStack(block, 1, 1));
  }
  
  public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
    return false;
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
      boolean entJump = false;
      boolean entMoving = false;
      boolean entSplash = false;
      boolean entRotate = false;
      float mtdkof = 1.0F;
      double movDis = 1.0D;
      double movCof = 16.0D;
      double movKofDiv = 1.0D;
      int mr_kof = (int)Math.min(5.0D + Math.floor(Math.max(0.0D, Math.pow((mtdkof * 10.0F) * (1.5D - kof1m), 1.5D))), 60.0D);
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
      if ((entMoving && world.getTotalWorldTime() % Math.max((int)Math.floor(movCof / 1.5D), 1) == 0L) || (entJump && world.getTotalWorldTime() % 8L == 0L) || entSplash) {
        if (entSplash && 
          kof2 > 1.5D)
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "step.snow", 1.0F, world.rand.nextFloat() * 0.5F + 0.5F); 
        if (!entSplash && entMoving && 
          world.rand.nextInt(2) == 0)
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "dig.snow", 1.0F, world.rand.nextFloat() * 0.5F + 0.5F); 
        if (entJump)
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "dig.cloth", 0.75F, world.rand.nextFloat() * 0.5F + 0.5F); 
      } 
      if (world.getBlock(x, y + 1, z) != this || (world.getBlock(x, y + 1, z) == this && world.getBlock(x, y + 2, z) == this)) {
        if (world.getBlock(x, y + 1, z) == this && world.getBlock(x, y + 2, z) == this)
          kof1 = 0.001D; 
        boolean undSnow = false;
        if (kof1 > 0.65D || world.getBlock(x, y - 1, z) == this)
          undSnow = true; 
        double mys = 0.0D;
        if (!(entity instanceof EntityPlayer))
          mys = 0.0D; 
        if (kof1 >= 1.2D) {
          double a1 = 0.07485D;
          entity.motionY += a1 / Math.max((movKofDiv - 1.0D) * Math.max(kof1m - 0.5D, 0.75D) * 1.6D * mtdkof * 17.5D, 1.0D) / Math.pow(Math.max(kof1m / 1.25D, 1.0D), 2.0D);
          entity.onGround = true;
          entity.fallDistance = 0.0F;
        } else if (kof1 >= 0.9D) {
          MFQM.setStuckEffect((EntityLivingBase)entity, mr_kof);
          double a1 = 0.085D + mys;
          if (!entSplash) {
            if (entMoving || world.rand.nextInt(Math.max((int)Math.floor(75.0D - kof1 * 5.0D), 1)) == 0) {
              entity.motionY += a1 / Math.max(1.0D, 5.0D - kof1);
              if (!entMoving && 
                undSnow)
                world.playSound(entity.posX, EposY, entity.posZ, "dig.snow", 1.0F, world.rand.nextFloat() * 0.5F + 0.5F, false); 
            } else {
              entity.motionY += a1;
            } 
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
          double a1 = 0.085D + mys;
          if (world.rand.nextInt(Math.max((int)Math.floor(15.0D - Math.pow(Math.max(kof1m, 0.0D), 1.5D)), 1)) == 0)
            entity.onGround = true; 
          if (MFQM.isTrulySink(entity, kof1m))
            MFQM.setStuckEffect((EntityLivingBase)entity, mr_kof); 
          entity.fallDistance = 0.0F;
          if (kof1 >= 0.6D) {
            if (entMoving || world.rand.nextInt(Math.min(Math.max((int)Math.floor(75.0D - kof1 * 25.0D), 1), 75)) == 0) {
              if (!entMoving) {
                entity.motionY += a1 / Math.max(2.0D, 5.0D - kof1);
                if (undSnow)
                  world.playSound(entity.posX, EposY, entity.posZ, "dig.snow", 1.0F, world.rand.nextFloat() * 0.5F + 0.5F, false); 
              } else if (world.rand.nextInt(Math.min(Math.max((int)Math.floor(75.0D - kof1 * 25.0D), 1), 10)) == 0) {
                entity.motionY += a1 / Math.max(2.0D, 5.0D - kof1);
                if (undSnow)
                  world.playSound(entity.posX, EposY, entity.posZ, "dig.snow", 1.0F, world.rand.nextFloat() * 0.5F + 0.5F, false); 
              } else {
                entity.motionY += a1;
              } 
            } else {
              entity.motionY += a1;
            } 
          } else {
            entity.setInWeb();
            if (entMoving || world.rand.nextInt(Math.min(Math.max((int)Math.floor(75.0D - kof1 * 25.0D), 1), 75)) == 0) {
              if (!entMoving) {
                entity.motionY--;
                if (undSnow)
                  world.playSound(entity.posX, EposY, entity.posZ, "dig.snow", 1.0F, world.rand.nextFloat() * 0.5F + 0.5F, false); 
              } else if (world.rand.nextInt(Math.min(Math.max((int)Math.floor(75.0D - kof1 * 25.0D), 1), 35)) == 0) {
                entity.motionY--;
                if (undSnow)
                  world.playSound(entity.posX, EposY, entity.posZ, "dig.snow", 1.0F, world.rand.nextFloat() * 0.5F + 0.5F, false); 
              } else {
                entity.motionY -= 0.5D;
              } 
            } else {
              entity.motionY += a1;
            } 
            if (kof1 < 0.45D && 
              MFQM.isTrulySink(entity, kof1m))
              MFQM.setStuckEffect((EntityLivingBase)entity, 60); 
          } 
        } 
        if (world.getTotalWorldTime() % Math.max(1.0D + Math.floor(Math.max(kof1 * 10.0D, 0.0D)), 1.0D) == 0.0D)
          entity.onGround = false; 
        if (entity.isInWater() && 
          entity.motionY > 0.0D)
          entity.motionY /= 4.0D; 
      } else {
        if (kof1 < 0.5D && 
          MFQM.isTrulySink(entity, kof1m))
          MFQM.setStuckEffect((EntityLivingBase)entity, 60); 
        entity.setInWeb();
      } 
      MFQM.antiHoldJumpScript(entity, kof1, true);
    } else if (kof1 > 1.25D) {
      entity.motionX *= 0.5880000114440918D;
      entity.motionZ *= 0.5880000114440918D;
      entity.motionY = 0.0D;
      if (kof1 < 1.45D) {
        entity.motionY += 0.1D;
      } else {
        entity.motionY += 0.03999999910593033D;
      } 
    } else {
      entity.motionX = 0.0D;
      entity.motionZ = 0.0D;
      entity.motionY = 0.0D;
    } 
  }
  
  public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
    return null;
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int par1, int par2) {
    if (par2 == 1 && 
      MFQM.WLD_MIC)
      return MFQM.WLDAshBlock.getIcon(0, 0); 
    return this.theSnowIIcon;
  }
  
  public void updateTick(World world, int x, int y, int z, Random rand) {
    if ((!MFQM.AOA || world.provider.dimensionId != MFQM.AOADimLandsLelyetia) && 
      !world.getBlock(x, y - 1, z).getMaterial().isSolid() && world.getBlock(x, y - 1, z).getMaterial().isReplaceable()) {
      world.setBlock(x, y - 1, z, (Block)this, 0, 3);
      world.setBlockToAir(x, y, z);
      return;
    } 
    super.updateTick(world, x, y, z, rand);
  }
  
  protected boolean canSilkHarvest() {
    return true;
  }
  
  public boolean canDropFromExplosion(Explosion par1Explosion) {
    return false;
  }
  
  public void harvestBlock(World world, EntityPlayer entityPlayer, int x, int y, int z, int par6) {
    if (EnchantmentHelper.getSilkTouchModifier((EntityLivingBase)entityPlayer)) {
      MFQM.dropItem(world, x, y, z, new ItemStack((Block)this, 1, par6));
      world.markBlockForUpdate(x, y, z);
      return;
    } 
    super.harvestBlock(world, entityPlayer, x, y, z, par6);
  }
  
  public float getPlayerRelativeBlockHardness(EntityPlayer par1EntityPlayer, World par2World, int par3, int par4, int par5) {
    if (!((Entity)par1EntityPlayer).onGround)
      return 7.5E-5F; 
    float f = getBlockHardness(par2World, par3, par4, par5);
    return ForgeHooks.blockStrength((Block)this, par1EntityPlayer, par2World, par3, par4, par5);
  }
  
  public boolean canHarvestBlock(EntityPlayer player, int meta) {
    return true;
  }
  
  public void onBlockAdded(World par1World, int par2, int par3, int par4) {
    par1World.scheduleBlockUpdate(par2, par3, par4, (Block)this, tickRate(par1World));
  }
  
  public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5) {
    if (par1World.getBlock(par2, par3 + 1, par4) == Blocks.snow_layer)
      par1World.setBlockToAir(par2, par3 + 1, par4); 
    par1World.scheduleBlockUpdate(par2, par3, par4, (Block)this, tickRate(par1World));
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister par1IIconRegister) {
    this.theSnowIIcon = par1IIconRegister.registerIcon("morefunquicksandmod:SoftSnow");
  }
  
  public Item getItemDropped(int metadata, Random random, int fortune) {
    if (metadata == 1)
      return null; 
    return super.getItemDropped(metadata, random, fortune);
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
  
  @SideOnly(Side.CLIENT)
  public int getBlockColor() {
    if (MFQM.AOA && (Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.AOADimLandsLelyetia)
      return 16755712; 
    return 16777215;
  }
  
  @SideOnly(Side.CLIENT)
  public int getRenderColor(int par1) {
    if (MFQM.WLD && 
      par1 == 1)
      return 15658734; 
    if (MFQM.AOA && (Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.AOADimLandsLelyetia)
      return 16755712; 
    return 16777215;
  }
  
  @SideOnly(Side.CLIENT)
  public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
    int l = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
    if (MFQM.WLD && 
      l == 1)
      return 15658734; 
    if (MFQM.AOA && (Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.AOADimLandsLelyetia)
      return 16755712; 
    return 16777215;
  }
  
  public void checkEntityUnder(Entity ent) {
    if (MFQM.isEntityInsideOfBlock(ent, (Block)this) && 
      MFQM.isDrowning(ent) && 
      !ent.worldObj.isRemote && 
      ent.isEntityAlive())
      ent.attackEntityFrom(DamageSource.inWall, Math.max(((EntityLivingBase)ent).getMaxHealth() * 0.1F, 2.0F)); 
  }
}
