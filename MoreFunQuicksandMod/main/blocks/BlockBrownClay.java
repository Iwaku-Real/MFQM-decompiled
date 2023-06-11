package MoreFunQuicksandMod.main.blocks;

import MoreFunQuicksandMod.main.CustomVarPlayer;
import MoreFunQuicksandMod.main.MFQM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockBrownClay extends Block {
  @SideOnly(Side.CLIENT)
  private IIcon[] IIconNormalArray;
  
  private int maxOpacity;
  
  private int lastOpacity;
  
  private int incOpacity;
  
  public BlockBrownClay(int mopc, int lopc, int iopc) {
    super(Material.sponge);
    setHardness(1.75F);
    setStepSound(Block.soundTypeSand);
    setResistance(1000.0F);
    setTickRandomly(true);
    if (MFQM.QSOpacity) {
      setLightOpacity(255);
    } else {
      setLightOpacity(3);
    } 
    setUnlocalizedName("BrownClay");
    setCreativeTab(MFQM.tabMFQM);
    this.maxOpacity = mopc;
    this.lastOpacity = lopc;
    this.incOpacity = iopc;
  }
  
  public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
    return false;
  }
  
  public int getDamageValue(World par1World, int par2, int par3, int par4) {
    return par1World.getBlockMetadata(par2, par3, par4);
  }
  
  @SideOnly(Side.CLIENT)
  public void getSubBlocks(Item block, CreativeTabs creativeTabs, List<ItemStack> list) {
    list.add(new ItemStack(block, 1, 0));
    list.add(new ItemStack(block, 1, 4));
  }
  
  public void updateTick(World world, int x, int y, int z, Random random) {
    int mtd = world.getBlockMetadata(x, y, z);
    Material mat = world.getBlock(x, y + 1, z).getMaterial();
    int xx = x;
    int yy = y;
    int zz = z;
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
        isLavaNear = true;
        break;
      } 
      if (world.getBlock(xx, yy, zz).getMaterial() == Material.water) {
        isWaterNear = true;
        break;
      } 
    } 
    if (isLavaNear) {
      if (mtd > 3) {
        world.setBlock(x, y, z, Blocks.clay, 0, 3);
      } else {
        world.setBlock(x, y, z, Blocks.hardened_clay, 0, 3);
      } 
      world.playSoundEffect((x + 0.5F), (y + 0.5F), (z + 0.5F), "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
      for (int l = 0; l < 8; l++)
        world.spawnParticle("largesmoke", x + Math.random(), y + 1.2D, z + Math.random(), 0.0D, 0.0D, 0.0D); 
      return;
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
      float mtdkof = 2.0F;
      double movDis = 1.0D;
      double movDisa = 1.0D;
      double movCof = 16.0D;
      double movKofDiv = 1.0D;
      int mr_kof = (int)Math.min(5.0D + Math.floor(Math.max(0.0D, Math.pow((mtdkof * 10.0F) * (1.5D - kof1m), 1.5D))), 128.0D);
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
        movKofDiv = 1.0D + movDis / 2.0D;
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
      bootsIsFloat = false;
      if (world.getTotalWorldTime() % 128L == 0L || (entMoving && world.getTotalWorldTime() % Math.max((int)Math.floor(movCof), 1) == 0L) || (entJump && world.getTotalWorldTime() % 8L == 0L) || entSplash) {
        if (entSplash) {
          if (kof2 > 1.5D)
            world.playSoundEffect(entity.posX, EposY, entity.posZ, "game.player.swim", 0.15F, world.rand.nextFloat() * 0.25F + 0.1F); 
          if (entity instanceof EntityPlayer && 
            world.rand.nextInt(2) == 0) {
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, this, -1);
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, this, -1);
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, this, -1);
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
        if (!world.isRemote && 
          world.rand.nextInt(5) == 0)
          if (mtd > 3) {
            world.setBlock(x, y, z, this, world.rand.nextInt(4) + 4, 3);
          } else {
            world.setBlock(x, y, z, this, world.rand.nextInt(4), 3);
          }  
      } 
      if (world.getTotalWorldTime() % 32L == 0L && 
        world.rand.nextInt(10) == 0)
        MFQM.spawnBodyBubble(world, entity, x, y, z, this, -1); 
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
        if (kof1 >= 1.2D) {
          if (!entSplash) {
            double a1 = 0.075D;
            a1 = 0.07485D;
            entity.motionY += a1 / Math.max((movKofDiv - 1.0D) * Math.max(kof1m - 0.5D, 0.75D) * 1.6D, 1.0D) / Math.pow(Math.max(kof1m / 1.25D, 1.0D), 2.0D);
            entity.onGround = false;
            entity.fallDistance = 0.0F;
            if (world.rand.nextInt((int)Math.floor(Math.max(kof1m * 5.0D, 1.0D))) == 0)
              entity.setInWeb(); 
            if (world.rand.nextInt(Math.max((int)Math.floor(5.0D - Math.pow(Math.max(kof1m, 0.0D), 1.5D)), 1)) == 0)
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
            double a1 = 0.075D;
            if (world.getTotalWorldTime() % 4L == 0L)
              a1 = 0.07485D; 
            entity.motionY += a1 / Math.max((movKofDiv - 1.0D) * Math.max(kof1m - 0.5D, 0.75D) * 1.6D, 1.0D) / Math.pow(Math.max(kof1m / 1.25D, 1.0D), 2.0D);
            MFQM.setStuckEffect((EntityLivingBase)entity, mr_kof);
            entity.onGround = false;
            entity.fallDistance = 0.0F;
            if (world.rand.nextInt((int)Math.floor(Math.max(kof1m * 5.0D / 2.0D, 1.0D))) == 0)
              entity.setInWeb(); 
            if (world.rand.nextInt(Math.max((int)Math.floor(10.0D - Math.pow(Math.max(kof1m, 0.0D), 1.5D)), 1)) == 0)
              entity.onGround = true; 
          } else {
            double a2 = 1.05D;
            if (world.rand.nextInt(Math.max((int)Math.floor(15.0D - Math.pow(Math.max(kof1m, 0.0D), 1.5D)), 1)) == 0)
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
                MFQM.setStuckEffect((EntityLivingBase)entity, 128); 
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
          MFQM.setStuckEffect((EntityLivingBase)entity, 128); 
      } 
      MFQM.antiHoldJumpScript(entity, kof1, true);
    } else if (kof1 < 1.45D) {
      entity.setInWeb();
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
    if (!par1World.isRemote) {
      int mtd = par1World.getBlockMetadata(par2, par3, par4);
      if (mtd > 3) {
        par1World.setBlock(par2, par3, par4, this, par1World.rand.nextInt(4) + 4, 3);
      } else {
        par1World.setBlock(par2, par3, par4, this, par1World.rand.nextInt(4), 3);
      } 
    } 
    par1World.scheduleBlockUpdate(par2, par3, par4, this, tickRate(par1World));
  }
  
  public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5) {
    par1World.scheduleBlockUpdate(par2, par3, par4, this, tickRate(par1World));
  }
  
  public void harvestBlock(World world, EntityPlayer entityPlayer, int x, int y, int z, int par6) {
    world.playSoundEffect(x, y, z, "mob.silverfish.step", 0.25F, world.rand.nextFloat() * 0.15F + 0.1F);
    if (EnchantmentHelper.getSilkTouchModifier((EntityLivingBase)entityPlayer)) {
      if (par6 > 3) {
        MFQM.dropItem(world, x, y, z, new ItemStack(this, 1, 4));
      } else {
        MFQM.dropItem(world, x, y, z, new ItemStack(this, 1, 0));
      } 
      world.markBlockForUpdate(x, y, z);
      return;
    } 
    super.harvestBlock(world, entityPlayer, x, y, z, par6);
  }
  
  public boolean canHarvestBlock(EntityPlayer player, int meta) {
    return true;
  }
  
  public float getPlayerRelativeBlockHardness(EntityPlayer par1EntityPlayer, World par2World, int par3, int par4, int par5) {
    if (!((Entity)par1EntityPlayer).onGround)
      return 7.5E-5F; 
    float f = getBlockHardness(par2World, par3, par4, par5);
    return ForgeHooks.blockStrength(this, par1EntityPlayer, par2World, par3, par4, par5);
  }
  
  @SideOnly(Side.CLIENT)
  public void randomDisplayTick(World world, int x, int y, int z, Random random) {
    if (world.rand.nextInt(1000) == 0)
      world.markBlockForUpdate(x, y, z); 
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int par1, int par2) {
    return this.IIconNormalArray[Math.min(7, par2)];
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister par1IIconRegister) {
    this.IIconNormalArray = new IIcon[8];
    this.IIconNormalArray[0] = par1IIconRegister.registerIcon("morefunquicksandmod:Brown_Clay0");
    this.IIconNormalArray[1] = par1IIconRegister.registerIcon("morefunquicksandmod:Brown_Clay1");
    this.IIconNormalArray[2] = par1IIconRegister.registerIcon("morefunquicksandmod:Brown_Clay2");
    this.IIconNormalArray[3] = par1IIconRegister.registerIcon("morefunquicksandmod:Brown_Clay3");
    this.IIconNormalArray[4] = par1IIconRegister.registerIcon("morefunquicksandmod:Mineral_Clay0");
    this.IIconNormalArray[5] = par1IIconRegister.registerIcon("morefunquicksandmod:Mineral_Clay1");
    this.IIconNormalArray[6] = par1IIconRegister.registerIcon("morefunquicksandmod:Mineral_Clay2");
    this.IIconNormalArray[7] = par1IIconRegister.registerIcon("morefunquicksandmod:Mineral_Clay3");
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
      MFQM.spawnDrowningBubble(ent.worldObj, ent, this, -1);
      if (!ent.worldObj.isRemote && 
        ent.isEntityAlive())
        ent.attackEntityFrom(DamageSource.drown, Math.max(((EntityLivingBase)ent).getMaxHealth() * 0.1F, 2.0F)); 
    } 
  }
  
  public void checkPlayerMuddy(EntityPlayer ent, int x, int y, int z, World world) {
    if (!world.isRemote) {
      int md = world.getBlockMetadata(x, y, z);
      if (md > 3) {
        int preML = MFQM.getMuddyLevel(ent, y, world);
        if (preML > 3) {
          int minNear = 0;
          if (world.getBlock(x, y + 1, z) != this) {
            for (int i = 0; i < 3; i++) {
              for (int j = 0; j < 3; j++) {
                int xxx = x - 1 + i;
                int zzz = z - 1 + j;
                if ((i != 1 || j != 1) && 
                  world.getBlock(xxx, y, zzz) == this && 
                  world.getBlockMetadata(xxx, y, zzz) > 3)
                  minNear++; 
              } 
            } 
            if (minNear > 7) {
              if (world.getTotalWorldTime() % 50L - (preML * 2) == 0L) {
                if (world.rand.nextInt(10) == 0) {
                  ent.addPotionEffect(new PotionEffect(19, 0, 2, false));
                  ent.addPotionEffect(new PotionEffect(18, 0, 2, false));
                  ent.addPotionEffect(new PotionEffect(17, 0, 2, false));
                } 
                ent.addPotionEffect(new PotionEffect(10, 100, 0, false));
              } 
              if (world.getTotalWorldTime() % 72L - (preML * 2) == 0L && 
                world.rand.nextInt(10) == 0)
                ent.addPotionEffect(new PotionEffect(11, 12000, 0, false)); 
            } 
          } 
        } 
      } 
    } 
    if (!MFQM.QSCover)
      return; 
    if (!world.isRemote) {
      int md = world.getBlockMetadata(x, y, z);
      CustomVarPlayer props = CustomVarPlayer.get(ent);
      int preML = MFQM.getMuddyLevel(ent, y, world);
      if (preML * this.maxOpacity / 1000.0F > props.getMuddyLevel() * props.getMuddyTime() / 1000.0F) {
        props.setMuddyLevel(preML);
        int mdtp = -1;
        if (md > 3) {
          mdtp = MFQM.getMuddyType(this) + 1;
        } else {
          mdtp = MFQM.getMuddyType(this);
        } 
        props.setMuddyType(mdtp);
        if (props.getMuddyTime() < this.maxOpacity)
          props.addMuddyTime(this.incOpacity); 
        props.setMuddyTime(Math.min(props.getMuddyTime(), this.maxOpacity));
      } 
    } 
  }
}
