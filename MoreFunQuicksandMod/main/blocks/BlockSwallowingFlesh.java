package MoreFunQuicksandMod.main.blocks;

import MoreFunQuicksandMod.main.CustomVarPlayer;
import MoreFunQuicksandMod.main.MFQM;
import MoreFunQuicksandMod.main.entity.EntityTentacles;
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
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockSwallowingFlesh extends Block {
  public IIcon theQIIcon;
  
  private int maxOpacity;
  
  private int lastOpacity;
  
  private int incOpacity;
  
  public BlockSwallowingFlesh(int mopc, int lopc, int iopc) {
    super(Material.sponge);
    setHardness(2.5F);
    setStepSound(Block.soundTypeGravel);
    setResistance(1000.0F);
    setTickRandomly(true);
    if (MFQM.QSOpacity) {
      setLightOpacity(255);
    } else {
      setLightOpacity(3);
    } 
    setUnlocalizedName("SwallowingFlesh");
    setCreativeTab(MFQM.tabMFQM);
    this.maxOpacity = mopc;
    this.lastOpacity = lopc;
    this.incOpacity = iopc;
  }
  
  public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
    return false;
  }
  
  public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
    int mtd = 0;
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
        MFQM.ABC_MIC && 
        world.provider.dimensionId == MFQM.ABCDim2)
        Affect = false; 
      if (!(entity instanceof EntityPlayer) && 
        MFQM.AOA_MIC && 
        world.provider.dimensionId == MFQM.AOADimAbyss)
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
      int mr_kof = (int)Math.min(5.0D + Math.floor(Math.max(0.0D, (mtdkof * 10.0F) * (1.5D - kof1m))), 135.0D);
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
          movKofDiv += mr_kof * 0.002D; 
      } 
      entity.motionX = 0.0D;
      entity.motionZ = 0.0D;
      if (entity.motionY > -0.1D) {
        entity.motionY = 0.0D;
      } else {
        entity.motionY /= 2.0D;
      } 
      if (world.getTotalWorldTime() % 128L == 0L || (entMoving && world.getTotalWorldTime() % Math.max((int)Math.floor(movCof), 1) == 0L) || (entJump && world.getTotalWorldTime() % 8L == 0L) || entSplash) {
        if (entSplash && 
          kof2 > 1.5D)
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.step", 0.25F, world.rand.nextFloat() * 0.25F + 0.1F); 
        if (!entSplash && entMoving && 
          world.rand.nextInt(2) == 0)
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.step", 0.15F, world.rand.nextFloat() * 0.25F + 0.1F); 
        if (entJump)
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.slime.attack", 0.25F, world.rand.nextFloat() * 0.25F + 0.25F); 
        if (!entJump && !entMoving && !entSplash && 
          world.rand.nextInt(5) == 0)
          if (world.rand.nextInt(5) == 0) {
            world.playSoundEffect(entity.posX, EposY, entity.posZ, "liquid.water", 0.5F, world.rand.nextFloat() * 0.15F + 0.1F);
          } else {
            world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.step", 0.25F, world.rand.nextFloat() * 0.15F + 0.1F);
          }  
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
        if (!world.isRemote && 
          MFQM.QSTentacles && entity.width < 1.75F && !entity.isCollidedVertically && (
          !MFQM.AOA || world.provider.dimensionId != MFQM.AOADimAbyss) && 
          world.getBlock(x, y - 1, z) == this && 
          world.getTotalWorldTime() % 64L == 0L && 
          world.rand.nextInt(2) == 0) {
          PotionEffect PEWeak = ((EntityLivingBase)entity).getActivePotionEffect(Potion.weakness);
          PotionEffect PESlow = ((EntityLivingBase)entity).getActivePotionEffect(Potion.digSlowdown);
          int weakLevel = -1;
          int slowLevel = -1;
          if (PEWeak != null)
            weakLevel = PEWeak.getAmplifier(); 
          if (PESlow != null)
            slowLevel = PESlow.getAmplifier(); 
          if (weakLevel <= -1 || slowLevel <= 2)
            world.spawnEntityInWorld((Entity)new EntityTentacles(world, entity.posX, Math.min(EposY + 0.5D, (y + 1)), entity.posZ, entity, y + 1, 0)); 
        } 
        double mys = 0.0D;
        if (!(entity instanceof EntityPlayer))
          mys = 0.0D; 
        if (entJump && 
          MFQM.suctionWorldCheck(entity, world, prevVel)) {
          entity.motionY -= 0.05D * (Math.min(0.75D, kof1m) + 1.0D);
          world.playSound(entity.posX, EposY, entity.posZ, "mob.magmacube.jump", 0.25F, 0.25F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F, false);
        } 
        if (!MFQM.QSTentacles && 
          EposY - EprevPosY > 0.001D && 
          world.rand.nextInt(Math.max((int)Math.floor(kof1m * 2.0D), 1)) == 0 && 
          MFQM.suctionWorldCheck(entity, world, prevVel)) {
          entity.motionY -= 0.025D * mtdkof * (Math.min(0.75D, kof1m) + 1.0D);
          world.playSound(entity.posX, EposY, entity.posZ, "mob.magmacube.jump", 0.25F, 0.25F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F, false);
        } 
        if (kof1 >= 1.2D) {
          if (kof1 <= 1.45D && 
            !entSplash) {
            double a1 = 0.075D;
            if (world.getTotalWorldTime() % 4L == 0L)
              a1 = 0.07485D; 
            entity.motionY += a1 / Math.max((movKofDiv - 1.0D) * Math.max(kof1m - 0.5D, 0.75D) * 1.01D * 17.5D, 1.0D) / Math.pow(Math.max(kof1m / 1.475D, 1.0D), 2.0D);
            entity.onGround = false;
            entity.fallDistance = 0.0F;
            if (world.rand.nextInt((int)Math.floor(Math.max(kof1m * 10.0D, 1.0D))) == 0)
              entity.setInWeb(); 
            if (world.rand.nextInt(Math.max((int)Math.floor(10.0D - Math.pow(Math.max(kof1m, 0.0D), 1.5D)), 1)) == 0)
              entity.onGround = true; 
          } 
        } else {
          if (EposY - EprevPosY > 0.001D && 
            world.rand.nextInt(Math.max((int)Math.floor(kof1m * 2.0D), 10)) == 0 && 
            MFQM.suctionWorldCheck(entity, world, prevVel)) {
            entity.motionY -= 0.035D * mtdkof * (Math.min(0.75D, kof1m) + 1.0D);
            world.playSound(entity.posX, EposY, entity.posZ, "mob.magmacube.jump", 0.25F, 0.25F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F, false);
          } 
          if (kof1 >= 0.9D) {
            double a1 = 0.075D + mys;
            if (world.getTotalWorldTime() % 4L == 0L)
              a1 = 0.07485D; 
            entity.motionY += a1 / Math.max((movKofDiv - 1.0D) * Math.max(kof1m - 0.5D, 0.75D) * 1.6D * 17.5D, 1.0D) / Math.pow(Math.max(kof1m / 1.25D, 1.0D), 2.0D);
            MFQM.setStuckEffect((EntityLivingBase)entity, mr_kof);
            entity.onGround = false;
            entity.fallDistance = 0.0F;
            if (world.rand.nextInt((int)Math.floor(Math.max(kof1m * 5.0D / 2.0D, 1.0D))) == 0)
              entity.setInWeb(); 
            if (world.rand.nextInt(Math.max((int)Math.floor(50.0D - Math.pow(Math.max(kof1m, 0.0D), 1.5D)), 1)) == 0)
              entity.onGround = true; 
          } else {
            double a2 = 1.01D;
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
                MFQM.setStuckEffect((EntityLivingBase)entity, 135); 
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
          MFQM.setStuckEffect((EntityLivingBase)entity, 135); 
      } 
      MFQM.antiHoldJumpScript(entity, kof1, false);
    } else if (kof1 < 1.45D) {
      entity.setInWeb();
    } 
  }
  
  public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
    return null;
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int par1, int par2) {
    return this.theQIIcon;
  }
  
  public void updateTick(World world, int x, int y, int z, Random rand) {
    if (world.rand.nextInt(5) == 0) {
      if (!world.getBlock(x, y - 1, z).getMaterial().isSolid() && world.getBlock(x, y - 1, z).getMaterial().isReplaceable()) {
        world.setBlock(x, y - 1, z, this, 0, 3);
        world.setBlockToAir(x, y, z);
      } 
    } else {
      if (!world.isRemote && (
        !MFQM.AOA || world.provider.dimensionId != MFQM.AOADimAbyss) && 
        world.getTotalWorldTime() % 4L == 0L && 
        world.rand.nextInt(1000) == 0 && 
        world.getBlock(x, y + 1, z) != MFQM.TentBlock && 
        world.getBlock(x, y + 1, z) == Blocks.air) {
        boolean CanGen = true;
        for (int re = 0; re <= 12; re++) {
          int xx = x;
          int zz = z;
          switch (re) {
            case 0:
              xx = x - 2;
              zz = z;
              break;
            case 1:
              xx = x - 1;
              zz = z;
              break;
            case 2:
              xx = x - 1;
              zz = z - 1;
              break;
            case 3:
              xx = x - 1;
              zz = z + 1;
              break;
            case 4:
              xx = x;
              zz = z - 2;
              break;
            case 5:
              xx = x;
              zz = z - 1;
              break;
            case 6:
              xx = x;
              zz = z + 1;
              break;
            case 7:
              xx = x;
              zz = z + 2;
              break;
            case 8:
              xx = x + 2;
              zz = z;
              break;
            case 9:
              xx = x + 1;
              zz = z;
              break;
            case 10:
              xx = x + 1;
              zz = z - 1;
              break;
            case 11:
              xx = x + 1;
              zz = z + 1;
              break;
          } 
          if (world.getBlock(xx, y + 1, zz) == MFQM.TentBlock) {
            CanGen = false;
            break;
          } 
        } 
        if (CanGen)
          world.setBlock(x, y + 1, z, MFQM.TentBlock, world.rand.nextInt(4), 0); 
      } 
      world.scheduleBlockUpdate(x, y, z, this, tickRate(world) * 10);
    } 
  }
  
  public void onBlockAdded(World par1World, int par2, int par3, int par4) {
    par1World.scheduleBlockUpdate(par2, par3, par4, this, tickRate(par1World));
  }
  
  public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5) {
    par1World.scheduleBlockUpdate(par2, par3, par4, this, tickRate(par1World));
  }
  
  public float getPlayerRelativeBlockHardness(EntityPlayer par1EntityPlayer, World par2World, int par3, int par4, int par5) {
    if (!((Entity)par1EntityPlayer).onGround)
      return 4.0E-5F; 
    float f = getBlockHardness(par2World, par3, par4, par5);
    return ForgeHooks.blockStrength(this, par1EntityPlayer, par2World, par3, par4, par5);
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister par1IIconRegister) {
    this.theQIIcon = par1IIconRegister.registerIcon("morefunquicksandmod:SwallowingFlesh");
  }
  
  public boolean canDropFromExplosion(Explosion par1Explosion) {
    return false;
  }
  
  public void harvestBlock(World world, EntityPlayer entityPlayer, int x, int y, int z, int par6) {
    if (EnchantmentHelper.getSilkTouchModifier((EntityLivingBase)entityPlayer)) {
      MFQM.dropItem(world, x, y, z, new ItemStack(this, 1, world.getBlockMetadata(x, y, z)));
      world.markBlockForUpdate(x, y, z);
      return;
    } 
    super.harvestBlock(world, entityPlayer, x, y, z, par6);
  }
  
  public boolean canHarvestBlock(EntityPlayer player, int meta) {
    return true;
  }
  
  public Item getItemDropped(int par1, Random random, int par3) {
    if (MFQM.BoP) {
      if (MFQM.FleshItemID != 0)
        return MFQM.FleshItem; 
      return Item.getItemFromBlock(MFQM.MeatBlock);
    } 
    return Item.getItemFromBlock(MFQM.MeatBlock);
  }
  
  public int quantityDropped(Random random) {
    if (MFQM.BoP && 
      MFQM.FleshItemID != 0)
      return 4 + random.nextInt(5); 
    return 1;
  }
  
  public int damageDropped(int par1) {
    if (MFQM.BoP)
      return 3; 
    return 0;
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
      (Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.AOADimAbyss)
      return 8069166; 
    return 16777215;
  }
  
  @SideOnly(Side.CLIENT)
  public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
    if (MFQM.AOA && 
      (Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.AOADimAbyss)
      return 8069166; 
    return 16777215;
  }
  
  @SideOnly(Side.CLIENT)
  public int getRenderColor(int par1) {
    return 16777215;
  }
  
  public void checkEntityUnder(Entity ent) {
    if (MFQM.isEntityInsideOfBlock(ent, this) && 
      MFQM.isDrowning(ent) && 
      !ent.worldObj.isRemote && 
      ent.isEntityAlive())
      ent.attackEntityFrom(DamageSource.inWall, Math.max(((EntityLivingBase)ent).getMaxHealth() * 0.1F, 2.0F)); 
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
