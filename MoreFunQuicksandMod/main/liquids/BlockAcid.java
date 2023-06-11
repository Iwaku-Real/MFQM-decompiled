package MoreFunQuicksandMod.main.liquids;

import MoreFunQuicksandMod.main.Fields;
import MoreFunQuicksandMod.main.MFQM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.lang.reflect.Field;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.FluidStack;

public class BlockAcid extends BlockFluidClassic {
  public IIcon[] theAcidIIcon;
  
  public BlockAcid() {
    super(MFQM.AcidFluid, Material.water);
    setQuantaPerBlock(4);
    if (MFQM.QSOpacity) {
      setLightOpacity(6);
    } else {
      setLightOpacity(3);
    } 
    setTickRandomly(true);
    setUnlocalizedName("Acid");
    setCreativeTab(MFQM.tabMFQM);
  }
  
  public int getRenderBlockPass() {
    return 1;
  }
  
  public void updateTick(World world, int x, int y, int z, Random rand) {
    if (world.getBlockMetadata(x, y, z) == 0) {
      Block block = world.getBlock(x, y + this.densityDir, z);
      int bMeta = world.getBlockMetadata(x, y + this.densityDir, z);
      if (block == this && bMeta != 0) {
        world.setBlock(x, y + this.densityDir, z, (Block)this);
        world.setBlockMetadataWithNotify(x, y + this.densityDir, z, 0, 3);
        world.setBlockToAir(x, y, z);
        return;
      } 
    } 
    super.updateTick(world, x, y, z, rand);
  }
  
  public void modifyEntityVelocity(World par1World, int par2, int par3, int par4, Entity par5Entity, Vec3 par6Vec3) {}
  
  public FluidStack drain(World world, int x, int y, int z, boolean doDrain) {
    return null;
  }
  
  public boolean canDrain(World world, int x, int y, int z) {
    return false;
  }
  
  public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
    int mtd = world.getBlockMetadata(x, y, z);
    double EposY = entity.posY;
    double EprevPosY = entity.prevPosY;
    double prevVel = entity.motionY;
    double height = (mtd / 5);
    double oy = y - EposY + 1.0D - height;
    double oy2 = y - EprevPosY + 1.0D - height;
    oy *= -1.0D;
    oy2 *= -1.0D;
    if (oy2 < 0.0D)
      oy2 = 0.0D; 
    double kof1 = oy;
    double kof1m = oy;
    if (kof1m < 0.0D)
      kof1m = 0.0D; 
    double kof2 = oy2;
    if (entity instanceof EntityLivingBase) {
      boolean entJump = false;
      boolean entMoving = false;
      boolean entSplash = false;
      boolean entRotate = false;
      float mtdkof = (1 + mtd / 5);
      double movDis = 1.0D;
      double movCof = 16.0D;
      double movKofDiv = 1.0D;
      double entup = entity.motionY;
      if (!(entity instanceof net.minecraft.entity.player.EntityPlayer)) {
        oy = y - EposY - 0.5D + height;
        oy2 = y - EprevPosY - 0.5D + height;
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
      if (entity.motionY < -0.05D && 
        kof2 > 1.25D) {
        entSplash = true;
        entity.motionY = Math.min(entity.motionY / 1.25D, -0.05D);
      } 
      if (!(entity instanceof net.minecraft.entity.player.EntityPlayer) && 
        ((EntityLivingBase)entity).prevRotationYaw != ((EntityLivingBase)entity).rotationYaw)
        entRotate = true; 
      if (entity instanceof net.minecraft.entity.player.EntityPlayer && 
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
          movKofDiv += 0.05D; 
      } 
      if (entMoving && world.getTotalWorldTime() % Math.max((int)Math.floor(movCof), 1) == 0L && 
        !entSplash && entMoving) {
        if (world.rand.nextInt(2) == 0)
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "game.player.swim", 0.15F, world.rand.nextFloat() * 0.25F + 0.1F); 
        if (world.rand.nextInt(5) == 0)
          MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, (Block)this, 0); 
      } 
      if (!entSplash)
        entity.motionY = 0.0D; 
      entity.motionZ = 0.0D;
      entity.motionX = 0.0D;
      entup += (movKofDiv - 1.0D) * 5.0D;
      if (world.getBlock(x, y + 1, z) != this) {
        double WSIN = Math.sin(world.getTotalWorldTime() / (4.0D - world.rand.nextFloat() * 0.05D)) * 0.75D;
        double OSIN = Math.sin(world.getTotalWorldTime() / (5.0D - world.rand.nextFloat() * 0.01D));
        double SIN = Math.max(OSIN * 1.3D, 0.0D) + 0.25D;
        boolean jump = false;
        if (!entSplash) {
          if (entity.isInWater()) {
            try {
              Field field2 = Fields.findField(EntityLivingBase.class, boolean.class, 2);
              field2.setAccessible(true);
              jump = field2.getBoolean(entity);
            } catch (Exception e) {
              e.printStackTrace();
            } 
            entity.motionY += 0.025D / (1.0D + (movKofDiv - 1.0D) * 50.0D);
            if (jump)
              entity.motionY -= 0.05D * SIN; 
          } 
          if (jump) {
            entity.motionY -= 0.0065D;
          } else {
            if (kof1 >= 1.3D) {
              entity.motionY -= 0.025D;
            } else if (world.getTotalWorldTime() % Math.max(Math.floor(8.0D * (kof1 - 0.35D) / 0.65D), 1.0D) != 0.0D) {
              entity.motionY -= 0.0065D;
            } 
            entity.motionY -= 0.0075D * WSIN;
          } 
        } 
        if (entup > 0.075D && 
          world.rand.nextInt((int)Math.floor(Math.max(1.0D, 10.0D - kof1 * 10.0D - entup * 10.0D))) == 0 && 
          world.rand.nextInt(1) == 0) {
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.magmacube.jump", 0.25F, 0.25F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F);
          entity.motionY -= 0.035D;
          if (world.rand.nextInt(2) == 0) {
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, (Block)this, 0);
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, (Block)this, 0);
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, (Block)this, 0);
          } 
        } 
      } 
      MFQM.antiHoldJumpScript(entity, kof1, false);
      if (kof1 < 1.45D) {
        if (!MFQM.AOA || world.provider.dimensionId != MFQM.AOADimIromine) {
          if (y > EposY + 1.0D) {
            entity.attackEntityFrom(DamageSource.generic, Math.max(((EntityLivingBase)entity).getMaxHealth() * 0.1F, 2.0F));
          } else if (world.getTotalWorldTime() % Math.max(8 + mtd, 1) == 0L && 
            y > EposY - 0.25D && 
            world.rand.nextInt(2 + mtd) == 0) {
            entity.attackEntityFrom(DamageSource.generic, 1.0F);
          } 
          if (world.getTotalWorldTime() % Math.max(4 + mtd, 1) == 0L) {
            if (world.rand.nextInt(5 + mtd) == 0)
              world.playSoundEffect(entity.posX, EposY, entity.posZ, "random.fizz", 0.25F, 1.75F - world.rand.nextFloat() * 0.25F); 
            if (world.rand.nextInt(5) == 0)
              MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, (Block)this, 0); 
          } 
        } 
        if (world.getTotalWorldTime() % 16L == 0L && 
          world.rand.nextInt(10) == 0)
          MFQM.spawnBodyBubble(world, entity, x, y, z, (Block)this, 0); 
      } 
    } else if (kof1 < 1.25D) {
      entity.motionY = 0.0D;
      entity.motionZ *= 1.0E-4D;
      entity.motionX *= 1.0E-4D;
      if (MFQM.AOA && 
        world.provider.dimensionId == MFQM.AOADimIromine)
        return; 
      if (entity instanceof EntityItem && (
        (EntityItem)entity).getEntityItem().getItem() instanceof net.minecraft.item.ItemFood) {
        if (world.getTotalWorldTime() % Math.max(4 + mtd, 1) == 0L && 
          world.rand.nextInt(5 + mtd) == 0)
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "random.fizz", 0.12F, 1.75F - world.rand.nextFloat() * 0.25F); 
        if (world.getTotalWorldTime() % Math.max(8 + mtd, 1) == 0L && 
          y > EposY - 0.25D && 
          world.rand.nextInt(2 + mtd) == 0)
          entity.attackEntityFrom(DamageSource.generic, 0.1F); 
      } 
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int par1, int par2) {
    return (par1 != 0 && par1 != 1) ? this.theAcidIIcon[1] : this.theAcidIIcon[0];
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
  public int getBlockColor() {
    if (MFQM.AOA && 
      (Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.AOADimIromine)
      return 10248760; 
    return 16777215;
  }
  
  @SideOnly(Side.CLIENT)
  public int getRenderColor(int par1) {
    if (MFQM.AOA && 
      (Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.AOADimIromine)
      return 10248760; 
    return 16777215;
  }
  
  @SideOnly(Side.CLIENT)
  public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
    if (MFQM.AOA && 
      (Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.AOADimIromine)
      return 10248760; 
    return 16777215;
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister par1IIconRegister) {
    if (getMaterial() == Material.water)
      this.theAcidIIcon = new IIcon[] { par1IIconRegister.registerIcon("morefunquicksandmod:Acid_Still"), par1IIconRegister.registerIcon("morefunquicksandmod:Acid_Flowing") }; 
  }
  
  @SideOnly(Side.CLIENT)
  public void randomDisplayTick(World world, int x, int y, int z, Random random) {
    if ((!MFQM.AOA || world.provider.dimensionId != MFQM.AOADimIromine) && 
      random.nextInt(1000) == 0)
      world.playSound(x + random.nextFloat(), y + random.nextFloat(), z + random.nextFloat(), "random.fizz", 0.125F + random.nextFloat() * 0.125F, 0.5F - world.rand.nextFloat() * 0.25F, false); 
    int md = world.getBlockMetadata(x, y, z);
    if (!MFQM.QSBubble && 
      world.rand.nextInt(100) == 0) {
      double xx = x + random.nextFloat();
      double zz = z + random.nextFloat();
      MFQM.spawnQSBubble(world, xx, (y + 1 / (1 + md)) - 0.15D, zz, (Block)this, 0, 0.5F);
    } 
  }
}
