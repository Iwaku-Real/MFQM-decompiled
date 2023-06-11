package MoreFunQuicksandMod.main.blocks;

import MoreFunQuicksandMod.main.MFQM;
import MoreFunQuicksandMod.main.Tileentity.TileEntityLarvae;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockLarvae extends BlockContainer {
  public IIcon theLIIcon;
  
  public BlockLarvae() {
    super(Material.coral);
    setHardness(2.0F);
    setResistance(1.0F);
    setStepSound(Block.soundTypeGrass);
    setUnlocalizedName("Larvae");
    setCreativeTab(MFQM.tabMFQM);
  }
  
  public TileEntity createNewTileEntity(World world, int i) {
    if (world.isRemote) {
      TileEntityLarvae TE = new TileEntityLarvae();
      TE.phase = world.rand.nextDouble() * 6.28318D * 2.0D;
      return (TileEntity)TE;
    } 
    return null;
  }
  
  public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
    return false;
  }
  
  public boolean renderAsNormalBlock() {
    return false;
  }
  
  public boolean isOpaqueCube() {
    return false;
  }
  
  public boolean isSolid() {
    return true;
  }
  
  @SideOnly(Side.CLIENT)
  public void setBlockBoundsForItemRender() {
    int j = 0;
    float f = 0.75F;
    setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
  }
  
  @SideOnly(Side.CLIENT)
  public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
    int j = 0;
    float f = 0.75F;
    setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
  }
  
  @SideOnly(Side.CLIENT)
  public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
    return (par5 == 1);
  }
  
  public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
    double EposY = entity.posY;
    double EprevPosY = entity.prevPosY;
    double prevVel = entity.motionY;
    int mtd = 0;
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
      boolean entJump = false;
      boolean entMoving = false;
      boolean entSplash = false;
      boolean entRotate = false;
      float mtdkof = 1.0F;
      double movDis = 1.0D;
      double movCof = 16.0D;
      double movKofDiv = 1.0D;
      int mr_kof = (int)Math.min(5.0D + Math.floor(Math.max(0.0D, (mtdkof * 100.0F) * (1.5D - kof1m))), 128.0D);
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
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.step", 0.5F, world.rand.nextFloat() * 0.25F + 0.25F); 
        if (!entSplash && entMoving && 
          world.rand.nextInt(2) == 0)
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.step", 0.25F, world.rand.nextFloat() * 0.15F + 0.1F); 
        if (entJump)
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.step", 0.75F, world.rand.nextFloat() * 0.15F + 0.85F); 
      } 
      if (world.getBlock(x, y + 1, z) != this || (world.getBlock(x, y + 1, z) == this && world.getBlock(x, y + 2, z) == this)) {
        if (world.getBlock(x, y + 1, z) == this && world.getBlock(x, y + 2, z) == this)
          kof1 = 0.001D; 
        double mys = 0.0D;
        if (!(entity instanceof EntityPlayer))
          mys = -0.0D; 
        if (kof1 >= 1.2D) {
          double a1 = 0.06D + mys;
          entity.motionY += a1 / Math.max((movKofDiv - 1.0D) * Math.max(kof1m - 0.5D, 0.75D) * 1.6D * mtdkof * 17.5D, 1.0D) / Math.pow(Math.max(kof1m / 1.25D, 1.0D), 2.0D);
          entity.onGround = false;
          entity.fallDistance = 0.0F;
          if (world.rand.nextInt(Math.max((int)Math.floor(15.0D - Math.pow(Math.max(kof1, 0.0D), 1.5D)), 1)) == 0)
            entity.onGround = true; 
        } else {
          if (kof1 >= 0.9D) {
            MFQM.setStuckEffect((EntityLivingBase)entity, mr_kof);
            double a1 = 0.06D + mys;
            if (!entSplash) {
              if (entMoving || world.rand.nextInt(Math.max((int)Math.floor(75.0D - kof1 * 5.0D), 1)) == 0) {
                entity.motionY += a1 / Math.max(1.0D, 5.0D - kof1);
              } else {
                entity.motionY += a1;
              } 
              entity.onGround = false;
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
            if (world.rand.nextInt(Math.max((int)Math.floor(15.0D - Math.pow(Math.max(kof1, 0.0D), 1.5D)), 1)) == 0)
              entity.onGround = true; 
          } else {
            double a1 = 0.06D + mys;
            if (world.rand.nextInt(Math.max((int)Math.floor(15.0D - Math.pow(Math.max(kof1, 0.0D), 1.5D)), 1)) == 0)
              entity.onGround = true; 
            if (MFQM.isTrulySink(entity, kof1m))
              MFQM.setStuckEffect((EntityLivingBase)entity, mr_kof); 
            entity.fallDistance = 0.0F;
            if (kof1 >= 0.6D) {
              if (entMoving || world.rand.nextInt(Math.max((int)Math.floor(kof1 * 150.0D), 25)) == 0) {
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
              if (entMoving || world.rand.nextInt(Math.max((int)Math.floor(kof1 * 150.0D), 25)) == 0) {
                if (!entMoving) {
                  entity.motionY--;
                } else if (world.rand.nextInt(Math.min(Math.max((int)Math.floor(75.0D - kof1 * 25.0D), 1), 35)) == 0) {
                  entity.motionY -= 0.25D;
                } else {
                  entity.motionY -= 0.1D;
                } 
              } else {
                entity.motionY += a1;
              } 
              if (kof1 < 0.45D && 
                MFQM.isTrulySink(entity, kof1m))
                MFQM.setStuckEffect((EntityLivingBase)entity, 128); 
            } 
          } 
          if (!world.isRemote) {
            if (entity instanceof EntityPlayer) {
              kof1 += 1.5D;
              kof1 = Math.max(kof1, 0.0D);
            } 
            if (kof1 < 0.75D && EposY - EprevPosY < -0.005D) {
              entity.attackEntityFrom(DamageSource.generic, Math.max(((EntityLivingBase)entity).getMaxHealth() * 0.05F, 1.0F));
              world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.step", 0.25F, world.rand.nextFloat() * 0.15F + 0.1F);
              world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.hit", 0.25F, world.rand.nextFloat() * 0.25F + 1.75F);
            } else if (kof1 < 0.75D) {
              if (kof1 < 0.01D) {
                if (world.rand.nextInt(10) == 0) {
                  entity.attackEntityFrom(DamageSource.generic, Math.max(((EntityLivingBase)entity).getMaxHealth() * 0.05F, 1.0F));
                  world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.step", 0.25F, world.rand.nextFloat() * 0.15F + 0.1F);
                  world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.hit", 0.25F, world.rand.nextFloat() * 0.25F + 1.75F);
                } 
              } else if (world.rand.nextInt(Math.max((int)Math.floor(kof1 * 100.0D), 10)) == 0) {
                entity.attackEntityFrom(DamageSource.generic, Math.max(((EntityLivingBase)entity).getMaxHealth() * 0.05F, 1.0F));
                world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.step", 0.25F, world.rand.nextFloat() * 0.15F + 0.1F);
                world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.hit", 0.25F, world.rand.nextFloat() * 0.25F + 1.75F);
              } 
            } 
            if (world.rand.nextInt(Math.max((int)Math.floor(kof1 * 50.0D), 10)) == 0)
              world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.say", 0.25F, world.rand.nextFloat() * 0.25F + 1.75F); 
          } 
        } 
        if (entity.isInWater() && 
          entity.motionY > 0.0D)
          entity.motionY /= 4.0D; 
      } else {
        entity.setInWeb();
        if (kof1 < 0.5D && 
          MFQM.isTrulySink(entity, kof1m))
          MFQM.setStuckEffect((EntityLivingBase)entity, 128); 
        if (!world.isRemote) {
          if (entity instanceof EntityPlayer) {
            kof1 += 1.5D;
            kof1 = Math.max(kof1, 0.0D);
          } 
          if (world.getBlock(x, y + 2, z) == this) {
            if (world.rand.nextInt(Math.max((int)Math.floor(kof1 * 50.0D), 10)) == 0)
              world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.say", 0.25F, world.rand.nextFloat() * 0.25F + 1.75F); 
            if (world.rand.nextInt(10) == 0) {
              entity.attackEntityFrom(DamageSource.generic, ((EntityLivingBase)entity).getMaxHealth() * 0.05F);
              world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.step", 0.25F, world.rand.nextFloat() * 0.15F + 0.1F);
              world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.hit", 0.25F, world.rand.nextFloat() * 0.25F + 1.75F);
            } 
          } 
        } 
      } 
      MFQM.antiHoldJumpScript(entity, kof1, false);
    } else if (kof1 < 1.25D) {
      entity.setInWeb();
      if (entity instanceof EntityItem && (
        (EntityItem)entity).getEntityItem().getItem() instanceof net.minecraft.item.ItemFood && 
        world.rand.nextInt(Math.max((int)Math.floor(kof1 * 50.0D), 10)) == 0) {
        world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.say", 0.25F, world.rand.nextFloat() * 0.25F + 1.75F);
        entity.attackEntityFrom(DamageSource.generic, 0.1F);
      } 
    } 
  }
  
  public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
    return null;
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int par1, int par2) {
    return this.theLIIcon;
  }
  
  public void updateTick(World world, int x, int y, int z, Random rand) {
    checkLavaNear(world, x, y, z);
    if (!world.getBlock(x, y - 1, z).getMaterial().isSolid() && world.getBlock(x, y - 1, z).getMaterial().isReplaceable()) {
      world.setBlock(x, y - 1, z, (Block)this, 0, 3);
      world.setBlockToAir(x, y, z);
      return;
    } 
    super.updateTick(world, x, y, z, rand);
  }
  
  public void checkLavaNear(World world, int x, int y, int z) {
    int xx = x;
    int yy = y;
    int zz = z;
    int met = 0;
    boolean isWaterNear = false;
    boolean isLavaNear = false;
    boolean isFireNear = false;
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
      if (world.getBlock(xx, yy, zz).getMaterial() == Material.fire) {
        isFireNear = true;
        break;
      } 
    } 
    if (isLavaNear || isFireNear) {
      if (world.rand.nextInt(5) == 0)
        world.playSoundEffect((x + world.rand.nextFloat()), (y + world.rand.nextFloat()), (z + world.rand.nextFloat()), "mob.silverfish.hit", 0.5F, world.rand.nextFloat() * 0.25F + 1.75F); 
      if (!isFireNear && 
        world.rand.nextInt(25) == 0) {
        world.setBlockToAir(x, y, z);
        world.playSoundEffect((x + 0.5F), (y + 0.5F), (z + 0.5F), "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
        for (int l = 0; l < 8; l++)
          world.spawnParticle("largesmoke", x + Math.random(), y + 1.2D, z + Math.random(), 0.0D, 0.0D, 0.0D); 
        return;
      } 
      world.scheduleBlockUpdate(x, y, z, (Block)this, 5);
    } 
  }
  
  protected boolean canSilkHarvest() {
    return true;
  }
  
  public boolean canDropFromExplosion(Explosion par1Explosion) {
    return false;
  }
  
  public void harvestBlock(World world, EntityPlayer entityPlayer, int x, int y, int z, int par6) {
    if (EnchantmentHelper.getSilkTouchModifier((EntityLivingBase)entityPlayer)) {
      MFQM.dropItem(world, x, y, z, new ItemStack((Block)this));
      world.markBlockForUpdate(x, y, z);
      return;
    } 
    super.harvestBlock(world, entityPlayer, x, y, z, par6);
  }
  
  public boolean canHarvestBlock(EntityPlayer player, int meta) {
    return true;
  }
  
  public void breakBlock(World world, int x, int y, int z, Block id, int meta) {
    if (this != world.getBlock(x, y, z))
      world.playSoundEffect((x + world.rand.nextFloat()), (y + world.rand.nextFloat()), (z + world.rand.nextFloat()), "mob.spider.death", 0.75F, world.rand.nextFloat() * 0.25F + 1.75F); 
    super.breakBlock(world, x, y, z, id, meta);
  }
  
  public void onBlockAdded(World par1World, int par2, int par3, int par4) {
    par1World.scheduleBlockUpdate(par2, par3, par4, (Block)this, tickRate(par1World));
  }
  
  public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5) {
    par1World.scheduleBlockUpdate(par2, par3, par4, (Block)this, tickRate(par1World));
  }
  
  public Item getItemDropped(int par1, Random par2Random, int par3) {
    return (Item)MFQM.LarvaRawItem;
  }
  
  public int quantityDropped(Random par1Random) {
    return par1Random.nextInt(2) + 2;
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister par1IIconRegister) {
    this.theLIIcon = par1IIconRegister.registerIcon("morefunquicksandmod:Larvae");
  }
  
  public float getPlayerRelativeBlockHardness(EntityPlayer par1EntityPlayer, World par2World, int par3, int par4, int par5) {
    if (!((Entity)par1EntityPlayer).onGround)
      return 6.0E-5F; 
    float f = getBlockHardness(par2World, par3, par4, par5);
    return ForgeHooks.blockStrength((Block)this, par1EntityPlayer, par2World, par3, par4, par5);
  }
  
  @SideOnly(Side.CLIENT)
  public void randomDisplayTick(World world, int x, int y, int z, Random random) {
    if (world.rand.nextInt(50) == 0) {
      double xx = x + random.nextFloat();
      double zz = z + random.nextFloat();
      world.playSound(xx, y, zz, "mob.spider.step", 0.125F + random.nextFloat() * 0.125F, 1.75F + random.nextFloat() * 0.15F, false);
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public boolean shouldSideBeRendered2(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
    switch (par5) {
      case 0:
        return ((!par1IBlockAccess.getBlock(par2, par3, par4).isOpaqueCube() && par1IBlockAccess.getBlock(par2, par3, par4) != MFQM.LarvaeBlock) || (par1IBlockAccess.getBlock(par2, par3, par4) == MFQM.LarvaeBlock && par1IBlockAccess.getBlock(par2, par3 + 1, par4) != MFQM.LarvaeBlock));
      case 1:
        return (par1IBlockAccess.getBlock(par2, par3 + 1, par4) != MFQM.LarvaeBlock);
    } 
    return (!par1IBlockAccess.getBlock(par2, par3 - 1, par4).isOpaqueCube() && par1IBlockAccess.getBlock(par2, par3 - 1, par4) != MFQM.LarvaeBlock);
  }
}
