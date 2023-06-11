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
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
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

public class BlockMud extends Block {
  private static final String[] mudTypes = new String[] { "mud", "mud", "mud" };
  
  IIcon texture;
  
  public BlockMud() {
    super(Material.ground);
    setHardness(0.6F);
    setHarvestLevel("shovel", 0);
    setStepSound(Block.soundTypeSand);
    if (MFQM.QSOpacity) {
      setLightOpacity(255);
    } else {
      setLightOpacity(3);
    } 
    setUnlocalizedName("Mud");
    setCreativeTab(MFQM.tabMFQM);
  }
  
  public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
    float f = 0.35F;
    int meta = par1World.getBlockMetadata(par2, par3, par4);
    if (meta > 0 && (
      par1World.getBlock(par2, par3 + 1, par4) != this || par1World.getBlockMetadata(par2, par3 + 1, par4) > 2))
      if (meta == 1) {
        f = 0.5F;
      } else if (meta == 2) {
        f = 0.75F;
      } else {
        return null;
      }  
    return AxisAlignedBB.getBoundingBox(par2, par3, par4, (par2 + 1), ((par3 + 1) - f), (par4 + 1));
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
      boolean Affect = true;
      if (entity instanceof MoreFunQuicksandMod.main.entity.EntityMuddyBlob)
        Affect = false; 
      if (MFQM.TBL)
        for (Class tmpClass : MFQM.TBLMobs) {
          if (tmpClass.isAssignableFrom(entity.getClass()))
            Affect = false; 
        }  
      if (!(entity instanceof EntityPlayer) && 
        MFQM.ABC && (
        entity.worldObj.provider.dimensionId == MFQM.ABCDim1 || entity.worldObj.provider.dimensionId == MFQM.ABCDim3))
        Affect = false; 
      if (MFQM.WLD && 
        entity.worldObj.provider.dimensionId == MFQM.WLDDim && 
        entity instanceof net.minecraft.entity.monster.EntityMob)
        Affect = false; 
      if (!(entity instanceof EntityPlayer) && 
        MFQM.AOA) {
        if (world.provider.dimensionId == MFQM.AOADimAbyss && 
          entity instanceof net.minecraft.entity.monster.EntityMob)
          Affect = false; 
        if (world.provider.dimensionId == MFQM.AOADimGardencia)
          Affect = false; 
        if (world.provider.dimensionId == MFQM.AOADimGreckon)
          Affect = false; 
      } 
      if (Affect)
        checkEntityUnder(entity); 
      boolean boots = false;
      boolean bootsIsFloat = false;
      if (entity instanceof EntityPlayer) {
        if (((EntityPlayer)entity).getCurrentArmor(0) != null && (
          (EntityPlayer)entity).getCurrentArmor(0).getItem() == MFQM.WadingBoots) {
          boots = true;
          bootsIsFloat = true;
        } 
        checkPlayerMuddy((EntityPlayer)entity, x, y, z, world);
      } 
      boolean entJump = false;
      boolean entMoving = false;
      boolean entSplash = false;
      boolean entRotate = false;
      boolean entUnderSurface = false;
      float mtdkof = (1 + mtd);
      double movDis = 1.0D;
      double movDisa = 1.0D;
      double movCof = 16.0D;
      double movKofDiv = 1.0D;
      int mr_kof = (int)Math.min(5.0D + Math.floor(Math.max(0.0D, Math.pow((mtdkof * 2.0F) * (1.5D - kof1m), 2.0D))), 145.0D);
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
      if (entRotate || Math.abs(entity.prevPosX - entity.posX) > 0.001D || Math.abs(entity.prevPosZ - entity.posZ) > 0.001D) {
        entMoving = true;
        movDis = Math.pow(Math.pow(entity.prevPosX - entity.posX, 2.0D) + Math.pow(entity.prevPosZ - entity.posZ, 2.0D), 0.5D);
        movCof = movDis * 10.0D;
        movCof = Math.max(Math.min(32.0D / (1.0D + movCof), 32.0D), 16.0D);
        movKofDiv = 1.0D + movDis * 25.0D;
        if (kof1m < 0.9D && kof1m != 0.0D && 
          entRotate)
          movKofDiv += mr_kof * 0.005D; 
      } 
      if (!boots) {
        entity.motionX *= 0.1D;
        entity.motionZ *= 0.1D;
      } 
      if (mtd == 0) {
        if (!(entity instanceof EntityPlayer) && 
          kof1 < 1.25D) {
          entity.motionY = 0.0D;
          entity.motionY += 0.08D + Math.min((1.25D - kof1) / 100.0D, 0.005D);
          entity.onGround = true;
          entity.fallDistance = 0.0F;
        } 
        return;
      } 
      if (entity instanceof MoreFunQuicksandMod.main.entity.EntityMuddyBlob) {
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
        if (entSplash) {
          if (kof2 > 1.5D)
            world.playSoundEffect(entity.posX, EposY, entity.posZ, "game.player.swim", 0.15F, world.rand.nextFloat() * 0.25F + 0.1F); 
          if (entity instanceof EntityPlayer && 
            world.rand.nextInt(3) == 0) {
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, this, -1);
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, this, -1);
          } 
        } 
        if (!entSplash && entMoving) {
          if (world.rand.nextInt(2) == 0)
            world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.step", 0.25F, world.rand.nextFloat() * 0.15F + 0.1F); 
          if (world.rand.nextInt(7) == 0)
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, this, -1); 
        } 
        if (entJump) {
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.slime.attack", 0.25F, world.rand.nextFloat() * 0.25F + 0.25F);
          if (entity instanceof EntityPlayer && 
            world.rand.nextInt(5) == 0) {
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
        if (mtd > 2 && 
          world.getTotalWorldTime() % 32L == 0L && 
          world.rand.nextInt(20) == 0)
          MFQM.spawnBodyBubble(world, entity, x, y, z, this, -1); 
      } 
      entity.motionX = 0.0D;
      entity.motionZ = 0.0D;
      if (kof1 < 1.3D)
        bootsIsFloat = false; 
      if (entity.motionY > -0.1D) {
        entity.motionY = 0.0D;
      } else {
        entity.motionY /= 1.5D;
      } 
      if (MFQM.AOA_MIC && 
        world.provider.dimensionId == MFQM.AOADimAbyss) {
        ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(15, 60, 100, false));
        ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(17, 60, 4, false));
        if (world.getTotalWorldTime() % 32L == 0L && 
          world.rand.nextInt(5) == 0)
          ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(19, 100, 0, false)); 
      } 
      if (!MFQM.AOA || world.provider.dimensionId != MFQM.AOADimAbyss)
        MFQM.SpawnMudTentacles(world, entity, x, y, z, this, mtd, 128, 10); 
      if (world.getBlock(x, y + 1, z) != this || (world.getBlock(x, y + 1, z) == this && world.getBlock(x, y + 2, z) == this)) {
        if (world.getBlock(x, y + 1, z) == this && world.getBlock(x, y + 2, z) == this)
          kof1 = 0.001D; 
        if (kof1 > 0.9D) {
          if (entMoving && 
            movKofDiv > 2.75D) {
            entMoving = false;
            movKofDiv = 0.0D;
            if (!entity.isInWater() && 
              kof1 < 1.3D)
              entity.motionY += 0.025D * Math.max(Math.min((1.3D - kof1) / 0.3D, 1.0D), 0.0D); 
          } 
          movKofDiv *= 1.125D;
        } else {
          movKofDiv *= 1.5D;
        } 
        double mys = 0.0D;
        if (!(entity instanceof EntityPlayer))
          mys = -0.0D; 
        if (entJump && 
          world.rand.nextInt(1) == 0 && 
          MFQM.suctionWorldCheck(entity, world, prevVel)) {
          entity.motionY -= 0.05D * mtdkof * (Math.min(0.75D, kof1m) + 1.0D);
          world.playSound(entity.posX, EposY, entity.posZ, "mob.magmacube.jump", 0.25F, 0.25F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F, false);
        } 
        if (kof1 >= 1.2D) {
          if (!entSplash)
            if (bootsIsFloat) {
              entity.onGround = true;
              entity.fallDistance = 0.0F;
              if (kof1 > 1.25D) {
                entity.motionY += 0.085D;
              } else {
                entity.motionY += 0.1D;
              } 
            } else {
              double a1 = 0.075D;
              if ((float)world.getTotalWorldTime() % Math.max(8.0F - mtdkof, 1.0F) == 0.0F)
                a1 = 0.07485D; 
              entity.motionY += a1 / Math.max((movKofDiv - 1.0D) * Math.max(kof1m - 0.5D, 0.75D) * 1.6D, 1.0D) / Math.pow(Math.max(kof1m / 1.25D, 1.0D), 2.0D);
              entity.onGround = false;
              entity.fallDistance = 0.0F;
              if (world.rand.nextInt((int)Math.floor(Math.max(kof1m * 20.0D, 1.0D))) == 0)
                entity.setInWeb(); 
              if (world.rand.nextInt(Math.max((int)Math.floor((2.0F + mtdkof) - Math.pow(Math.max(kof1m, 0.0D), 1.5D)), 1)) == 0)
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
            double a1 = 0.075D + mys;
            if ((float)world.getTotalWorldTime() % Math.max(8.0F - mtdkof, 1.0F) == 0.0F)
              a1 = 0.07485D; 
            entity.motionY += a1 / Math.max((movKofDiv - 1.0D) * Math.max(kof1m - 0.5D, 0.75D) * 1.6D, 1.0D) / Math.pow(Math.max(kof1m / 1.25D, 1.0D), 2.0D);
            MFQM.setStuckEffect((EntityLivingBase)entity, mr_kof);
            entity.fallDistance = 0.0F;
            if (entity instanceof EntityPlayer) {
              if (world.rand.nextInt(Math.max((int)Math.floor((4.0F + mtdkof) - Math.pow(Math.max(kof1m, 0.0D), 1.5D)), 1)) == 0)
                entity.onGround = true; 
            } else {
              double mw_kof = (1 + mr_kof / 10);
              entity.setPosition(entity.prevPosX + (entity.posX - entity.prevPosX) / mw_kof, EposY, entity.prevPosZ + (entity.posZ - entity.prevPosZ) / mw_kof);
            } 
            if (world.rand.nextInt((int)Math.floor(Math.max(kof1m * 10.0D / mtdkof, 1.0D))) == 0)
              entity.setInWeb(); 
          } else {
            double a2 = 1.05D;
            if (world.getTotalWorldTime() % (int)Math.floor(Math.min(16.0D, Math.max(16.0D - kof1 * mtdkof * 3.0D, 1.0D))) == 0L)
              a2 = 1.01D; 
            if (entity instanceof EntityPlayer) {
              if (world.rand.nextInt(Math.max((int)Math.floor((7.0F + mtdkof * 5.0F) - Math.pow(Math.max(kof1m, 0.0D), 1.5D)), 1)) == 0)
                entity.onGround = true; 
            } else {
              double mw_kof = (1 + mr_kof / 10);
              entity.setPosition(entity.prevPosX + (entity.posX - entity.prevPosX) / mw_kof, EposY, entity.prevPosZ + (entity.posZ - entity.prevPosZ) / mw_kof);
              if (world.rand.nextInt(Math.max((int)Math.floor((7.0F + mtdkof * 5.0F) - Math.pow(Math.max(kof1m, 0.0D), 1.5D)), 1)) == 0)
                entity.onGround = true; 
            } 
            if (MFQM.isTrulySink(entity, kof1m))
              MFQM.setStuckEffect((EntityLivingBase)entity, mr_kof); 
            entity.fallDistance = 0.0F;
            if (kof1 >= 0.5D) {
              if (world.rand.nextInt((int)Math.floor(Math.max(kof1m * 5.0D / mtdkof, 1.0D))) == 0) {
                entity.setInWeb();
                if (!entMoving) {
                  entity.motionY += (0.0725D + mys) * a2;
                } else {
                  entity.motionY += (0.0725D + mys) * a2 / (movKofDiv + 0.025D);
                } 
              } else if (!entMoving) {
                entity.motionY += (0.075D + mys) * a2;
              } else {
                entity.motionY += (0.075D + mys) * a2 / (movKofDiv + 0.025D);
              } 
            } else {
              entity.setInWeb();
              if (entity.isInWater() || world.getBlock(x, y + 1, z).getMaterial() == Material.water) {
                a2 = 1.01D;
              } else if (kof1 < 0.475D) {
                a2 = 1.05D;
              } 
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
        } 
        if (entity.isInWater()) {
          if (entity.motionY > 0.0D)
            entity.motionY = 0.0D; 
          entity.motionY -= 0.01D;
        } 
        MFQM.antiHoldJumpScript(entity, kof1, true);
      } else {
        if (kof1 < 0.5D && 
          MFQM.isTrulySink(entity, kof1m))
          MFQM.setStuckEffect((EntityLivingBase)entity, 145); 
        entity.setInWeb();
      } 
    } else {
      if (kof1 < 1.45D)
        entity.setInWeb(); 
      MFQM.HandleMudTentacles(world, entity, x, y, z, this, mtd);
    } 
  }
  
  public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
    int meta = world.getBlockMetadata(x, y, z);
    if (meta > 1)
      return false; 
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
  
  public int getDamageValue(World par1World, int par2, int par3, int par4) {
    return par1World.getBlockMetadata(par2, par3, par4);
  }
  
  @SideOnly(Side.CLIENT)
  public void getSubBlocks(Item block, CreativeTabs creativeTabs, List<ItemStack> list) {
    list.add(new ItemStack(block, 1, 0));
    list.add(new ItemStack(block, 1, 1));
    list.add(new ItemStack(block, 1, 2));
    list.add(new ItemStack(block, 1, 3));
  }
  
  public Item getItemDropped(int metadata, Random random, int fortune) {
    if (MFQM.MudBallID != 0)
      return MFQM.MudBall; 
    return super.getItemDropped(metadata, random, fortune);
  }
  
  public int quantityDropped(int meta, int fortune, Random random) {
    if (MFQM.MudBallID != 0)
      return 4; 
    return 1;
  }
  
  public String getUnlocalizedName(String baseName, ItemStack itemStack) {
    return baseName + "." + mudTypes[itemStack.getMetadata()];
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister iconRegister) {
    this.texture = iconRegister.registerIcon("morefunquicksandmod:mud");
  }
  
  public float getPlayerRelativeBlockHardness(EntityPlayer par1EntityPlayer, World par2World, int par3, int par4, int par5) {
    if (!((Entity)par1EntityPlayer).onGround)
      return -1.0F; 
    float f = getBlockHardness(par2World, par3, par4, par5);
    return ForgeHooks.blockStrength(this, par1EntityPlayer, par2World, par3, par4, par5);
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int side, int meta) {
    if (MFQM.TBL_MIC && 
      (Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.TBLDim)
      return MFQM.TBLMudBlock.getIcon(0, 0); 
    if (MFQM.ABC_MIC && (
      (Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.ABCDim1 || 
      (Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.ABCDim3))
      return MFQM.ABCSludgeBlock.getIcon(0, 0); 
    if (MFQM.AOA_MIC && 
      (Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.AOADimAbyss)
      return MFQM.AOAToxicBlock.getIcon(0, 0); 
    return this.texture;
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
      if (preML > props.getMuddyLevel() * props.getMuddyTime() / 1000.0F) {
        props.setMuddyLevel(preML);
        int mdtp = MFQM.getMuddyType(MFQM.MireBlock);
        props.setMuddyType(mdtp);
        if (props.getMuddyTime() < 1000)
          props.addMuddyTime(50); 
        props.setMuddyTime(Math.min(props.getMuddyTime(), 1000));
      } 
    } 
  }
}
