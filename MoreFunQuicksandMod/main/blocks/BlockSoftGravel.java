package MoreFunQuicksandMod.main.blocks;

import MoreFunQuicksandMod.main.CustomVarPlayer;
import MoreFunQuicksandMod.main.MFQM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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

public class BlockSoftGravel extends Block {
  public IIcon SGravelIcon0;
  
  public IIcon SGravelIcon1;
  
  public IIcon SGravelIcon2;
  
  public IIcon SGravelIcon3;
  
  private int maxOpacity;
  
  private int lastOpacity;
  
  private int incOpacity;
  
  public BlockSoftGravel(int mopc, int lopc, int iopc) {
    super(Material.sand);
    setHardness(2.5F);
    setStepSound(Block.soundTypeGravel);
    setResistance(1000.0F);
    setTickRandomly(true);
    if (MFQM.QSOpacity) {
      setLightOpacity(255);
    } else {
      setLightOpacity(3);
    } 
    setUnlocalizedName("SoftGravel");
    setCreativeTab(MFQM.tabMFQM);
    this.maxOpacity = mopc;
    this.lastOpacity = lopc;
    this.incOpacity = iopc;
  }
  
  @SideOnly(Side.CLIENT)
  public void getSubBlocks(Item block, CreativeTabs creativeTabs, List<ItemStack> list) {
    list.add(new ItemStack(block, 1, 0));
    list.add(new ItemStack(block, 1, 1));
  }
  
  public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int par2, int par3, int par4) {
    int mtd = world.getBlockMetadata(par2, par3, par4);
    if (mtd == 0) {
      float f = 0.2F;
      return AxisAlignedBB.getBoundingBox(par2, par3, par4, (par2 + 1), ((par3 + 1) - f), (par4 + 1));
    } 
    return null;
  }
  
  public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
    return false;
  }
  
  public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
    int mtd = world.getBlockMetadata(x, y, z);
    double EposY = entity.posY;
    double EprevPosY = entity.prevPosY;
    double prevVel = entity.motionY;
    boolean boots = false;
    boolean bootsIsFloat = false;
    if (entity instanceof EntityPlayer && (
      (EntityPlayer)entity).getCurrentArmor(0) != null && (
      (EntityPlayer)entity).getCurrentArmor(0).getItem() == MFQM.WadingBoots) {
      boots = true;
      bootsIsFloat = true;
    } 
    if (mtd < 1) {
      boolean slidex = false;
      boolean slidez = false;
      double velQ = 0.0D;
      if (!(entity instanceof EntityLivingBase)) {
        entity.motionX = 0.0D;
        entity.motionZ = 0.0D;
        entity.motionY = 0.0D;
      } 
      if (prevVel < 0.0D || entity.onGround || entity.isCollidedVertically || !world.isRemote) {
        double mul = Math.min(Math.max(-prevVel / 2.0D, 1.0D), 2.0D);
        if (!world.getBlock(x - 1, y + 1, z).isOpaqueCube() && 
          !world.getBlock(x - 1, y, z).isOpaqueCube()) {
          if (entity.motionX < -0.001D) {
            entity.motionX -= 0.2D * mul;
          } else if (entity.motionX > 1.0D - mul) {
            entity.motionX -= 0.05D * mul;
          } 
          slidex = true;
        } 
        if (!world.getBlock(x + 1, y + 1, z).isOpaqueCube() && 
          !world.getBlock(x + 1, y, z).isOpaqueCube()) {
          if (entity.motionX > 0.001D) {
            entity.motionX += 0.2D * mul;
          } else if (entity.motionX < mul - 1.0D) {
            entity.motionX += 0.05D * mul;
          } 
          slidex = true;
        } 
        if (!world.getBlock(x, y + 1, z - 1).isOpaqueCube() && 
          !world.getBlock(x, y, z - 1).isOpaqueCube()) {
          if (entity.motionZ < -0.001D) {
            entity.motionZ -= 0.2D * mul;
          } else if (entity.motionZ > 1.0D - mul) {
            entity.motionZ -= 0.05D * mul;
          } 
          slidez = true;
        } 
        if (!world.getBlock(x, y + 1, z + 1).isOpaqueCube() && 
          !world.getBlock(x, y, z + 1).isOpaqueCube()) {
          if (entity.motionZ > 0.001D) {
            entity.motionZ += 0.2D * mul;
          } else if (entity.motionZ < mul - 1.0D) {
            entity.motionZ += 0.05D * mul;
          } 
          slidez = true;
        } 
        velQ = Math.pow(Math.pow(entity.motionX, 2.0D) + Math.pow(entity.motionZ, 2.0D), 0.5D);
        if (!boots) {
          if (!slidex)
            entity.motionX = 0.0D; 
          if (!slidez)
            entity.motionZ = 0.0D; 
        } 
        entity.motionY = 0.0D;
        if (entity instanceof EntityLivingBase)
          entity.fallDistance = 0.0F; 
      } 
      if (entity instanceof EntityLivingBase) {
        if ((slidex || slidez) && 
          velQ > 0.01D && 
          world.rand.nextInt((int)(2.0D / (velQ + 0.001D) + 1.0D)) == 0)
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "step.gravel", 0.5F, world.rand.nextFloat() * 0.25F + 0.85F); 
        checkEntityUnder(entity, false);
        if (entity instanceof EntityPlayer)
          checkPlayerMuddy((EntityPlayer)entity, x, y, z, world); 
      } 
    } 
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
      if (mtd < 1) {
        if (!(entity instanceof EntityPlayer) && 
          kof1 < 0.9D) {
          entity.motionY = 0.0D;
          entity.motionY += 0.08D + Math.min((0.9D - kof1) / 100.0D, 0.005D);
          entity.onGround = true;
          entity.fallDistance = 0.0F;
        } 
        return;
      } 
      checkEntityUnder(entity, true);
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
      int mr_kof = (int)Math.min(5.0D + Math.floor(Math.max(0.0D, (mtdkof * 15.0F) * (1.5D - kof1m))), 125.0D);
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
        movKofDiv = 1.0D + movDis * 2.0D;
        if (kof1m < 0.9D && kof1m != 0.0D && 
          entRotate)
          movKofDiv += mr_kof * 0.003D; 
      } 
      entity.motionX = 0.0D;
      entity.motionZ = 0.0D;
      if (entity.motionY > 0.0D) {
        entity.motionY = 0.0D;
      } else {
        entity.motionY /= 1.5D;
      } 
      if (world.getTotalWorldTime() % 128L == 0L || (entMoving && world.getTotalWorldTime() % Math.max((int)Math.floor(movCof), 1) == 0L) || (entJump && world.getTotalWorldTime() % 8L == 0L) || entSplash) {
        if (entSplash) {
          if (kof2 > 1.45D)
            world.playSoundEffect(entity.posX, EposY, entity.posZ, "game.player.swim", 0.5F, world.rand.nextFloat() * 0.25F + 0.1F); 
          if (entity instanceof EntityPlayer && 
            world.rand.nextInt(2) == 0) {
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, this, 2);
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, this, 2);
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, this, 2);
          } 
        } 
        if (!entSplash && entMoving) {
          if (world.rand.nextInt(2) == 0)
            world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.step", 0.15F, world.rand.nextFloat() * 0.25F + 0.1F); 
          if (world.rand.nextInt(5) == 0)
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, this, 2); 
        } 
        if (entJump) {
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.slime.attack", 0.25F, world.rand.nextFloat() * 0.25F + 0.25F);
          if (entity instanceof EntityPlayer && 
            world.rand.nextInt(2) == 0) {
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, this, 2);
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, this, 2);
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, this, 2);
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
      if (world.getTotalWorldTime() % 32L == 0L && 
        world.rand.nextInt(10) == 0)
        MFQM.spawnBodyBubble(world, entity, x, y, z, this, 2); 
      if (world.getBlock(x, y + 1, z) != this || (world.getBlock(x, y + 1, z) == this && world.getBlock(x, y + 2, z) == this)) {
        if (world.getBlock(x, y + 1, z) == this && world.getBlock(x, y + 2, z) == this)
          kof1 = 0.001D; 
        double mys = 0.0D;
        if (!(entity instanceof EntityPlayer))
          mys = -0.0025D; 
        if (entJump && 
          world.rand.nextInt(1) == 0 && 
          MFQM.suctionWorldCheck(entity, world, prevVel)) {
          entity.motionY -= 0.05D * (Math.min(0.75D, kof1m) + 1.0D);
          world.playSound(entity.posX, EposY, entity.posZ, "mob.magmacube.jump", 0.25F, 0.25F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F, false);
        } 
        if (kof1 >= 1.2D) {
          if (!entSplash) {
            double a1 = 0.07485D;
            if (world.getTotalWorldTime() % 2L == 0L)
              a1 = 0.073D; 
            entity.motionY += a1 / Math.max((movKofDiv - 1.0D) * Math.max(kof1m - 0.5D, 0.75D) * 1.6D, 1.0D) / Math.pow(Math.max(kof1m / 1.25D, 1.0D), 2.0D);
            entity.onGround = false;
            entity.fallDistance = 0.0F;
            if (world.rand.nextInt((int)Math.floor(Math.max(kof1m * 5.0D, 1.0D))) == 0)
              entity.setInWeb(); 
            if (world.rand.nextInt(Math.max((int)Math.floor(20.0D - Math.pow(Math.max(kof1m, 0.0D), 1.5D)), 1)) == 0)
              entity.onGround = true; 
          } 
        } else {
          if (EposY - EprevPosY > 0.001D && 
            world.rand.nextInt(Math.max((int)Math.floor(kof1m * 3.0D), 10)) == 0 && 
            MFQM.suctionWorldCheck(entity, world, prevVel)) {
            entity.motionY -= 0.035D * mtdkof * (Math.min(0.75D, kof1m) + 1.0D);
            world.playSound(entity.posX, EposY, entity.posZ, "mob.magmacube.jump", 0.25F, 0.25F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F, false);
          } 
          if (kof1 >= 0.9D) {
            double a1 = 0.07485D;
            entity.motionY += a1 / Math.max((movKofDiv - 1.0D) * Math.max(kof1m - 0.5D, 0.75D) * 1.6D, 1.0D) / Math.pow(Math.max(kof1m / 1.25D, 1.0D), 2.0D);
            MFQM.setStuckEffect((EntityLivingBase)entity, mr_kof);
            entity.onGround = false;
            entity.fallDistance = 0.0F;
            if (world.rand.nextInt((int)Math.floor(Math.max(kof1m * 5.0D / 2.0D, 1.0D))) == 0)
              entity.setInWeb(); 
            if (world.rand.nextInt(Math.max((int)Math.floor(25.0D - Math.pow(Math.max(kof1m, 0.0D), 1.5D)), 1)) == 0)
              entity.onGround = true; 
          } else {
            double a2 = 1.05D;
            if (world.getTotalWorldTime() % 4L == 0L)
              a2 = 1.01D; 
            if (world.rand.nextInt(Math.max((int)Math.floor(30.0D - Math.pow(Math.max(kof1m, 0.0D), 1.5D)), 1)) == 0)
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
              a2 = 1.05D;
              if (kof1 >= 0.4D && 
                world.getTotalWorldTime() % 8L == 0L)
                a2 = 1.01D; 
              entity.setInWeb();
              if (!entMoving) {
                entity.motionY += (0.0725D + mys) * a2;
              } else {
                entity.motionY += (0.0725D + mys) * a2 / (movKofDiv + 0.025D);
              } 
              if (kof1 < 0.45D && 
                MFQM.isTrulySink(entity, kof1m))
                MFQM.setStuckEffect((EntityLivingBase)entity, 125); 
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
          MFQM.setStuckEffect((EntityLivingBase)entity, 150); 
      } 
      MFQM.antiHoldJumpScript(entity, kof1, true);
    } else if (kof1 < 1.45D) {
      entity.setInWeb();
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int par1, int par2) {
    return (par2 == 2) ? this.SGravelIcon3 : ((par2 == 1) ? this.SGravelIcon2 : ((par1 <= 1) ? this.SGravelIcon0 : this.SGravelIcon1));
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
      world.setBlock(x, y, z, Blocks.gravel, 0, 3);
      world.playSoundEffect((x + 0.5F), (y + 0.5F), (z + 0.5F), "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
      for (int l = 0; l < 8; l++)
        world.spawnParticle("largesmoke", x + Math.random(), y + 1.2D, z + Math.random(), 0.0D, 0.0D, 0.0D); 
      return;
    } 
    if (world.rand.nextInt(2) == 0) {
      if (!world.getBlock(x, y - 1, z).getMaterial().isSolid() && world.getBlock(x, y - 1, z).getMaterial().isReplaceable()) {
        world.setBlock(x, y - 1, z, this, world.getBlockMetadata(x, y, z), 3);
        world.setBlockToAir(x, y, z);
      } 
    } else {
      world.scheduleBlockUpdate(x, y, z, this, tickRate(world) * 5);
    } 
  }
  
  public void onBlockAdded(World par1World, int par2, int par3, int par4) {
    if (par1World.getBlockMetadata(par2, par3, par4) > 1)
      par1World.playSoundEffect(par2, par3, par4, "dig.sand", 0.5F, par1World.rand.nextFloat() * 0.25F + 0.85F); 
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
    this.SGravelIcon0 = par1IIconRegister.registerIcon("morefunquicksandmod:SoftGravel0");
    this.SGravelIcon1 = par1IIconRegister.registerIcon("morefunquicksandmod:SoftGravel1");
    this.SGravelIcon2 = par1IIconRegister.registerIcon("morefunquicksandmod:SoftGravel2");
    this.SGravelIcon3 = par1IIconRegister.registerIcon("morefunquicksandmod:SoftGravel3");
  }
  
  public boolean canDropFromExplosion(Explosion par1Explosion) {
    return false;
  }
  
  public boolean canHarvestBlock(EntityPlayer player, int meta) {
    return true;
  }
  
  public Item getItemDropped(int par1, Random random, int par3) {
    return null;
  }
  
  public int getDamageValue(World par1World, int par2, int par3, int par4) {
    return par1World.getBlockMetadata(par2, par3, par4);
  }
  
  public void harvestBlock(World world, EntityPlayer entityPlayer, int x, int y, int z, int par6) {
    if (EnchantmentHelper.getSilkTouchModifier((EntityLivingBase)entityPlayer)) {
      MFQM.dropItem(world, x, y, z, new ItemStack(this, 1, par6));
      world.markBlockForUpdate(x, y, z);
      return;
    } 
    if (par6 > 0) {
      if (world.rand.nextInt(5) == 0) {
        MFQM.dropItem(world, x, y, z, new ItemStack(Blocks.gravel));
      } else if (world.rand.nextInt(5) == 0) {
        MFQM.dropItem(world, x, y, z, new ItemStack(Blocks.dirt));
      } else {
        MFQM.dropItem(world, x, y, z, new ItemStack((Block)Blocks.sand));
      } 
      MFQM.dropItem(world, x, y, z, new ItemStack(Items.clay_ball, world.rand.nextInt(3) + 1, 0));
    } else {
      MFQM.dropItem(world, x, y, z, new ItemStack(Blocks.gravel));
    } 
    world.setBlockToAir(x, y, z);
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
      (Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.AOADimBarathos)
      return 16759497; 
    return 16777215;
  }
  
  @SideOnly(Side.CLIENT)
  public int getRenderColor(int par1) {
    if (MFQM.AOA && 
      (Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.AOADimBarathos)
      return 16759497; 
    return 16777215;
  }
  
  @SideOnly(Side.CLIENT)
  public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
    if (MFQM.AOA && 
      (Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.AOADimBarathos)
      return 16759497; 
    return 16777215;
  }
  
  public void checkEntityUnder(Entity ent, boolean bubbles) {
    if (MFQM.isEntityInsideOfBlock(ent, this) && 
      MFQM.isDrowning(ent)) {
      if (bubbles)
        MFQM.spawnDrowningBubble(ent.worldObj, ent, this, 2); 
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
