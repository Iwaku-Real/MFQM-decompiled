package MoreFunQuicksandMod.main.blocks;

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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockCorruptedSand extends Block {
  public IIcon theCSIIcon;
  
  public BlockCorruptedSand() {
    super(Material.sand);
    setHardness(1.75F);
    setStepSound(Block.soundTypeSand);
    setUnlocalizedName("CorruptedSand");
    setTickRandomly(true);
    setResistance(1.0F);
    setLightOpacity(0);
    setCreativeTab(MFQM.tabMFQM);
  }
  
  public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
    return false;
  }
  
  public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
    int mtd = world.getBlockMetadata(x, y, z);
    double EposY = entity.posY;
    double EprevPosY = entity.prevPosY;
    double prevVel = entity.motionY;
    double oy = y - EposY + 1.0D - mtd / 16.0D;
    double oy2 = y - EprevPosY + 1.0D - mtd / 16.0D;
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
      oy = y - EposY - 0.5D - mtd / 16.0D;
      oy2 = y - EprevPosY - 0.5D - mtd / 16.0D;
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
    if (mtd < 7) {
      if (entity instanceof EntityLivingBase) {
        checkEntityUnder(entity);
        boolean entJump = false;
        boolean entMoving = false;
        boolean entSplash = false;
        boolean entRotate = false;
        float mtdkof = 5.0F;
        double movDis = 1.0D;
        double movCof = 16.0D;
        double movKofDiv = 1.0D;
        int mr_kof = (int)Math.min(5.0D + Math.floor(Math.max(0.0D, Math.pow(mtdkof * (1.5D - kof1m), 1.85D))), 132.0D);
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
        if (world.getTotalWorldTime() % 4L == 0L) {
          double kof1w = kof1;
          if (entity instanceof EntityPlayer) {
            kof1w += 1.5D;
            kof1w = Math.max(kof1w, 0.0D);
          } 
          if (world.rand.nextInt((int)Math.floor(Math.max(kof1w * 100.0D, 50.0D))) == 0 && 
            !world.isRemote)
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(20, 125, 0, false)); 
        } 
        if (world.getBlock(x, y + 1, z) != this || (world.getBlock(x, y + 1, z) == this && world.getBlock(x, y + 2, z) == this)) {
          if (world.getBlock(x, y + 1, z) == this && world.getBlock(x, y + 2, z) == this)
            kof1 = 0.001D; 
          double mys = 0.0D;
          if (!(entity instanceof EntityPlayer))
            mys = -0.0025D; 
          if (entJump && 
            MFQM.suctionWorldCheck(entity, world, prevVel)) {
            entity.motionY -= 0.025D * mtdkof * (Math.min(0.75D, kof1m) + 1.0D);
            world.playSoundEffect(entity.posX, EposY, entity.posZ, "dig.sand", 0.15F, world.rand.nextFloat() * 0.5F + 0.5F);
          } 
          if (EposY - EprevPosY > 0.001D) {
            entity.motionY -= 0.25D;
            world.playSound(entity.posX, EposY, entity.posZ, "dig.sand", 0.15F, world.rand.nextFloat() * 0.5F + 0.5F, false);
          } 
          double xx = x + 0.5D;
          double zz = z + 0.5D;
          double disq = Math.pow(Math.pow(entity.posX - xx, 2.0D) + Math.pow(entity.posZ - zz, 2.0D), 0.5D);
          if (disq < 1.0D && 
            disq >= 0.1D) {
            entity.motionX += (xx - entity.posX) / Math.max(2.5D, kof1m * 5.0D);
            entity.motionZ += (zz - entity.posZ) / Math.max(2.5D, kof1m * 5.0D);
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
            if (kof1 < 1.35D) {
              double a1 = 0.07485D;
              entity.motionY += a1 / Math.max((movKofDiv - 1.0D) * Math.max(kof1m - 0.5D, 0.75D) * 1.6D * mtdkof * 17.5D, 1.0D) / Math.pow(Math.max(kof1m / 1.25D, 1.0D), 2.0D);
              if (!entSplash)
                entity.onGround = true; 
              entity.fallDistance = 0.0F;
            } else {
              entity.motionY = 0.0D;
            } 
          } else if (kof1 >= 0.9D) {
            double a1 = 0.07485D;
            entity.motionY += a1 / Math.max((movKofDiv - 1.0D) * Math.max(kof1m - 0.5D, 0.75D) * 1.6D * mtdkof * 17.5D, 1.0D) / Math.pow(Math.max(kof1m / 1.25D, 1.0D), 2.0D);
            MFQM.setStuckEffect((EntityLivingBase)entity, mr_kof);
            if (!entSplash) {
              entity.onGround = true;
              entity.fallDistance = 0.0F;
            } 
            if (entity instanceof EntityPlayer) {
              if (world.getTotalWorldTime() % Math.max(1.0D + Math.floor(Math.max(kof1 * 5.0D, 0.0D)), 1.0D) == 0.0D) {
                entity.setInWeb();
                entity.onGround = false;
              } 
            } else {
              double mw_kof = (1 + mr_kof / 10);
              entity.setPosition(entity.prevPosX + (entity.posX - entity.prevPosX) / mw_kof, EposY, entity.prevPosZ + (entity.posZ - entity.prevPosZ) / mw_kof);
            } 
          } else {
            double a2 = 0.9D;
            if (world.rand.nextInt(Math.max((int)Math.floor(10.0D - Math.pow(Math.max(kof1m, 0.0D), 1.5D)), 1)) == 0)
              entity.onGround = true; 
            if (MFQM.isTrulySink(entity, kof1m))
              MFQM.setStuckEffect((EntityLivingBase)entity, mr_kof); 
            entity.fallDistance = 0.0F;
            if (entMoving || world.rand.nextInt(Math.max((int)Math.floor(kof1 * 150.0D), 25)) == 0)
              if (!entMoving) {
                entity.motionY -= 0.5D;
              } else if (world.rand.nextInt(Math.min(Math.max((int)Math.floor(75.0D - kof1 * 25.0D), 1), 35)) == 0) {
                entity.motionY -= 0.1D;
              } else {
                entity.motionY -= 0.05D;
              }  
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
                MFQM.setStuckEffect((EntityLivingBase)entity, 132); 
            } 
          } 
          if (world.getTotalWorldTime() % Math.max(1.0D + Math.floor(Math.max(kof1 * 10.0D, 0.0D)), 1.0D) == 0.0D)
            entity.onGround = false; 
          if (entity.isInWater() && 
            entity.motionY > 0.0D)
            entity.motionY /= 4.0D; 
        } else {
          entity.setInWeb();
          if (kof1 < 0.5D && 
            MFQM.isTrulySink(entity, kof1m))
            MFQM.setStuckEffect((EntityLivingBase)entity, 132); 
        } 
        MFQM.antiHoldJumpScript(entity, kof1, false);
      } else if (kof1 < 1.45D) {
        entity.setInWeb();
      } 
    } else if (entity instanceof EntityLivingBase) {
      if (kof1 < 1.45D) {
        entity.setInWeb();
        if (world.getTotalWorldTime() % 4L == 0L && 
          world.rand.nextInt((int)Math.floor(Math.max(kof1 * 500.0D, 50.0D))) == 0 && 
          !world.isRemote)
          ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(20, 125, 0, false)); 
      } 
    } else if (kof1 < 1.45D) {
      entity.setInWeb();
    } 
  }
  
  protected boolean validForSpreadDown(World world, int x, int y, int z) {
    int depth = -1;
    if (validForSpread(world, x, y, z)) {
      int i;
      for (i = 0; i <= 16; i++) {
        if (!validForSpread(world, x, y - i, z)) {
          depth = i;
          if (world.getBlock(x, y - i, z).getMaterial().isLiquid())
            return false; 
          break;
        } 
      } 
      if (depth >= 0)
        for (i = 0; i <= 3; i++) {
          if (world.getBlock(x, y - depth - i, z) != this)
            return true; 
        }  
    } 
    return false;
  }
  
  protected boolean validForSpread(World world, int x, int y, int z) {
    if (!world.getBlock(x, y, z).getMaterial().isSolid() && !world.getBlock(x, y, z).getMaterial().isLiquid() && (
      !world.getBlock(x, y, z).getMaterial().blocksMovement() || world.getBlock(x, y, z).getMaterial().isReplaceable()))
      return true; 
    return false;
  }
  
  protected boolean DropifNeed(World world, int x, int y, int z) {
    if ((!world.getBlock(x, y, z).getMaterial().blocksMovement() || world.getBlock(x, y, z).getMaterial().isReplaceable()) && !world.getBlock(x, y, z).getMaterial().isLiquid()) {
      world.breakBlock(x, y, z, true);
      return true;
    } 
    return false;
  }
  
  public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
    return null;
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int par1, int par2) {
    return this.theCSIIcon;
  }
  
  public void updateTick(World world, int x, int y, int z, Random rand) {
    int mtd = world.getBlockMetadata(x, y, z);
    int xx = x;
    int yy = y;
    int zz = z;
    boolean isLavaNear = false;
    int re;
    for (re = 0; re <= 6; re++) {
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
        if (mtd == 0) {
          world.breakBlock(x, y, z, true);
        } else {
          world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(this) + (mtd << 12));
          world.setBlockToAir(x, y, z);
        } 
        world.playSoundEffect((x + 0.5F), (y + 0.5F), (z + 0.5F), "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
        for (int l = 0; l < 8; l++)
          world.spawnParticle("largesmoke", x + Math.random(), y + 1.2D, z + Math.random(), 0.0D, 0.0D, 0.0D); 
        return;
      } 
    } 
    if (world.getBlock(x, y - 1, z) != this) {
      if (world.getBlock(x, y - 1, z).getMaterial().isLiquid() && 
        mtd > 7) {
        world.setBlockToAir(x, y, z);
        return;
      } 
      if (validForSpread(world, x, y - 1, z) || world.getBlock(x, y - 1, z).getMaterial().isLiquid()) {
        DropifNeed(world, x, y - 1, z);
        world.setBlock(x, y - 1, z, this, mtd, 3);
        world.setBlockToAir(x, y, z);
        return;
      } 
    } else {
      int mmtd = world.getBlockMetadata(x, y - 1, z);
      if (mmtd > 0) {
        int umtd = mmtd + mtd;
        int dmtd = umtd - 16;
        if (umtd < 16) {
          world.setBlock(x, y, z, this, umtd, 3);
        } else {
          world.setBlockToAir(x, y, z);
        } 
        if (dmtd < 0) {
          world.setBlock(x, y - 1, z, this, 0, 3);
        } else {
          world.setBlock(x, y - 1, z, this, dmtd, 3);
        } 
        return;
      } 
    } 
    if (mtd > 0) {
      if (world.rand.nextInt(50) == 0) {
        int inThisBlocks = 0;
        int inMetaBlocks = 0;
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
        if (inThisBlocks > 0 && 
          inMetaBlocks > 0)
          world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) - 1, 2); 
      } 
    } else {
      if (world.rand.nextInt(50) == 0) {
        re = world.rand.nextInt(4);
        switch (re) {
          case 0:
            if (validForSpreadDown(world, x - 1, y, z)) {
              DropifNeed(world, x - 1, y, z);
              world.setBlock(x - 1, y, z, this, 15, 2);
            } 
            break;
          case 1:
            if (validForSpreadDown(world, x + 1, y, z)) {
              DropifNeed(world, x + 1, y, z);
              world.setBlock(x + 1, y, z, this, 15, 2);
            } 
            break;
          case 2:
            if (validForSpreadDown(world, x, y, z - 1)) {
              DropifNeed(world, x, y, z - 1);
              world.setBlock(x, y, z - 1, this, 15, 2);
            } 
            break;
          case 3:
            if (validForSpreadDown(world, x, y, z + 1)) {
              DropifNeed(world, x, y, z + 1);
              world.setBlock(x, y, z + 1, this, 15, 2);
            } 
            break;
        } 
      } 
      if (world.rand.nextInt(200) == 0 && 
        world.getBlock(x, y + 1, z) != this)
        for (int i = 1; i <= 3; i++) {
          Block bbid = world.getBlock(x, y - i, z);
          if (bbid == Blocks.gravel || bbid == Blocks.grass || bbid == Blocks.dirt || bbid == Blocks.sand || bbid == Blocks.soul_sand) {
            world.setBlock(x, y - i, z, this);
            break;
          } 
          if (bbid != this)
            break; 
        }  
    } 
    world.scheduleBlockUpdate(x, y, z, this, tickRate(world) * 2);
    super.updateTick(world, x, y, z, rand);
  }
  
  protected boolean canSilkHarvest() {
    return true;
  }
  
  public boolean isOpaqueCube() {
    return false;
  }
  
  public boolean renderAsNormalBlock() {
    return false;
  }
  
  public Item getItemDropped(int par1, Random random, int par3) {
    if (par3 == 0)
      return Item.getItemFromBlock(Blocks.soul_sand); 
    return null;
  }
  
  public int quantityDropped(Random par1Random) {
    return 1;
  }
  
  public void harvestBlock(World world, EntityPlayer entityPlayer, int x, int y, int z, int par6) {
    if (EnchantmentHelper.getSilkTouchModifier((EntityLivingBase)entityPlayer)) {
      MFQM.dropItem(world, x, y, z, new ItemStack(this));
      world.markBlockForUpdate(x, y, z);
      return;
    } 
    if (par6 < 7)
      entityPlayer.addPotionEffect(new PotionEffect(20, 125, 0, false)); 
    if (par6 < 12)
      world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "mob.blaze.death", 0.25F, world.rand.nextFloat() * 0.25F + 0.15F); 
    super.harvestBlock(world, entityPlayer, x, y, z, par6);
  }
  
  public boolean canHarvestBlock(EntityPlayer player, int meta) {
    return true;
  }
  
  public void onBlockAdded(World par1World, int par2, int par3, int par4) {
    par1World.scheduleBlockUpdate(par2, par3, par4, this, tickRate(par1World));
  }
  
  public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5) {
    if (!par1World.getBlock(par2, par3 - 1, par4).getMaterial().isSolid() && par1World.getBlock(par2, par3 - 1, par4).getMaterial().isReplaceable())
      par1World.scheduleBlockUpdate(par2, par3, par4, this, tickRate(par1World)); 
  }
  
  public float getPlayerRelativeBlockHardness(EntityPlayer par1EntityPlayer, World par2World, int par3, int par4, int par5) {
    if (!((Entity)par1EntityPlayer).onGround)
      return 2.5E-5F; 
    float f = getBlockHardness(par2World, par3, par4, par5);
    return ForgeHooks.blockStrength(this, par1EntityPlayer, par2World, par3, par4, par5);
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister par1IIconRegister) {
    this.theCSIIcon = par1IIconRegister.registerIcon("morefunquicksandmod:CorruptedSand");
  }
  
  public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
    int j = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
    float f = Math.min(Math.max(1.0F - j / 16.0F, 0.1F), 1.0F);
    setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
  }
  
  public void setBlockBoundsForItemRender() {
    int j = 0;
    float f = 1.0F;
    setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
  }
  
  @SideOnly(Side.CLIENT)
  public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
    if (par1World.getBlock(par2, par3 + 1, par4) != this) {
      if (par5Random.nextInt(50) == 0) {
        double d0 = (par2 + par5Random.nextFloat());
        double d1 = (par3 + 0.95F) + 0.05D - (par1World.getBlockMetadata(par2, par3, par4) / 16.0F);
        double d2 = (par4 + par5Random.nextFloat());
        double d3 = 0.001D;
        double d4 = 0.001D;
        double d5 = 0.001D;
        par1World.spawnParticle("reddust", d0, d1, d2, d3, d4, d5);
      } 
      if (par5Random.nextInt(1000) == 0)
        par1World.playSound(par2 + 0.5D, par3, par4 + 0.5D, "mob.blaze.breathe", 0.25F, par5Random.nextFloat() * 0.25F + 0.25F, false); 
    } 
  }
  
  public boolean isNormalCube() {
    return false;
  }
  
  public boolean isSolid() {
    return true;
  }
  
  public void checkEntityUnder(Entity ent) {
    if (MFQM.isEntityInsideOfBlock(ent, this) && 
      MFQM.isDrowning(ent) && 
      !ent.worldObj.isRemote && 
      ent.isEntityAlive())
      ent.attackEntityFrom(DamageSource.inWall, Math.max(((EntityLivingBase)ent).getMaxHealth() * 0.1F, 2.0F)); 
  }
}
