package MoreFunQuicksandMod.main.blocks;

import MoreFunQuicksandMod.main.CustomVarPlayer;
import MoreFunQuicksandMod.main.MFQM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockVoreHole extends Block {
  public IIcon theEmptyIIcon;
  
  private int maxOpacity;
  
  private int lastOpacity;
  
  private int incOpacity;
  
  public BlockVoreHole(int mopc, int lopc, int iopc) {
    super(Material.portal);
    setBlockUnbreakable();
    setResistance(6000000.0F);
    setStepSound(Block.soundTypeGravel);
    setUnlocalizedName("TempVore");
    setLightOpacity(4);
    setBlockBounds(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    this.maxOpacity = mopc;
    this.lastOpacity = lopc;
    this.incOpacity = iopc;
  }
  
  public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
    return false;
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
      if (world.rand.nextInt(2) == 0)
        world.playSound(x + world.rand.nextFloat(), y + world.rand.nextFloat(), z + world.rand.nextFloat(), "mob.silverfish.step", 0.125F + world.rand.nextFloat() * 0.125F, 0.75F + world.rand.nextFloat() * 0.5F, false); 
      if (entity instanceof EntityPlayer)
        checkPlayerMuddy((EntityPlayer)entity, x, y, z, world); 
      boolean entJump = false;
      boolean entMoving = false;
      boolean entSplash = false;
      boolean entRotate = false;
      float mtdkof = 1.0F;
      double movDis = 1.0D;
      double movCof = 16.0D;
      double movKofDiv = 1.0D;
      int mr_kof = Math.min(5 + (int)Math.floor(Math.max(0.0D, mtdkof * (1.5D - kof1m))), 100);
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
          movKofDiv += 0.1D; 
      } 
      if ((entMoving && world.getTotalWorldTime() % Math.max((int)Math.floor(movCof / 1.5D), 1) == 0L) || (entJump && world.getTotalWorldTime() % 8L == 0L) || entSplash) {
        if (entSplash && 
          kof2 > 1.5D)
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.step", 0.5F, world.rand.nextFloat() * 0.25F + 0.95F); 
        if (!entSplash && entMoving && 
          world.rand.nextInt(2) == 0)
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.step", 0.25F, world.rand.nextFloat() * 0.25F + 0.75F); 
        if (entJump)
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.step", 0.75F, world.rand.nextFloat() * 0.25F + 0.95F); 
      } 
      if (world.getBlock(x, y + 1, z) != this) {
        if (kof1 < 1.25D) {
          if (!entity.isInWater())
            entity.motionY = 0.0D; 
          if (world.rand.nextInt(10) == 0) {
            entity.onGround = true;
            if (world.rand.nextInt(10) == 0)
              entity.setInWeb(); 
          } else {
            entity.setInWeb();
          } 
          double xx = x + 0.5D;
          double zz = z + 0.5D;
          double disq = Math.pow(Math.pow(entity.posX - xx, 2.0D) + Math.pow(entity.posZ - zz, 2.0D), 0.5D);
          if (disq < 0.5D && 
            disq >= 0.075D) {
            entity.motionX += (xx - entity.posX) / Math.max(5.0D, kof1m * 25.0D);
            entity.motionZ += (zz - entity.posZ) / Math.max(5.0D, kof1m * 25.0D);
          } 
        } else if (!entSplash && 
          !entity.isInWater()) {
          entity.motionY /= 4.0D;
        } 
        entity.fallDistance = 0.0F;
        if (world.rand.nextInt(20) == 0)
          entity.motionY -= 0.1D; 
        if (world.rand.nextInt(25) == 0 && 
          EposY - EprevPosY > 0.001D) {
          if (MFQM.suctionWorldCheck(entity, world, prevVel))
            entity.motionY -= 0.25D; 
          world.playSound(entity.posX, EposY, entity.posZ, "mob.magmacube.jump", 0.25F, 0.25F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F, false);
        } 
      } else {
        entity.setInWeb();
        double xx = x + 0.5D;
        double zz = z + 0.5D;
        double disq = Math.pow(Math.pow(entity.posX - xx, 2.0D) + Math.pow(entity.posZ - zz, 2.0D), 0.5D);
        if (disq < 0.5D && 
          disq >= 0.075D) {
          entity.motionX += (xx - entity.posX) / Math.max(5.0D, kof1m * 25.0D);
          entity.motionZ += (zz - entity.posZ) / Math.max(5.0D, kof1m * 25.0D);
        } 
        entity.fallDistance = 0.0F;
        if (world.rand.nextInt(25) == 0 && 
          EposY - EprevPosY > 0.001D) {
          if (MFQM.suctionWorldCheck(entity, world, prevVel))
            entity.motionY -= 0.25D; 
          world.playSound(entity.posX, EposY, entity.posZ, "mob.magmacube.jump", 0.25F, 0.25F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F, false);
        } 
      } 
      MFQM.antiHoldJumpScript(entity, kof1, false);
    } 
  }
  
  public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
    return null;
  }
  
  public boolean isOpaqueCube() {
    return false;
  }
  
  public boolean renderAsNormalBlock() {
    return false;
  }
  
  public int getRenderType() {
    return -1;
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int par1, int par2) {
    return this.theEmptyIIcon;
  }
  
  public void updateTick(World world, int x, int y, int z, Random rand) {
    checkBlossomNear(world, x, y, z);
  }
  
  public void checkBlossomNear(World world, int x, int y, int z) {
    int xx = x;
    int yy = y;
    int zz = z;
    int met = 0;
    boolean isBlossomNear = false;
    for (int re = 0; re <= 4; re++) {
      switch (re) {
        case 0:
          xx = x;
          yy = y;
          zz = z + 1;
          break;
        case 1:
          xx = x - 1;
          yy = y;
          zz = z;
          break;
        case 2:
          xx = x;
          yy = y;
          zz = z - 1;
          break;
        case 3:
          xx = x + 1;
          yy = y;
          zz = z;
          break;
      } 
      if (world.getBlock(xx, yy, zz) == MFQM.BlossomBlock && 
        world.getBlockMetadata(xx, yy, zz) == re + 6) {
        met = world.getBlockMetadata(xx, yy, zz);
        isBlossomNear = true;
        break;
      } 
    } 
    if (!isBlossomNear) {
      world.setBlockToAir(x, y, z);
      return;
    } 
  }
  
  protected boolean canSilkHarvest() {
    return false;
  }
  
  public boolean canDropFromExplosion(Explosion par1Explosion) {
    return false;
  }
  
  public void harvestBlock(World world, EntityPlayer entityPlayer, int x, int y, int z, int par6) {}
  
  public boolean canHarvestBlock(EntityPlayer player, int meta) {
    return false;
  }
  
  public void onBlockAdded(World par1World, int par2, int par3, int par4) {
    checkBlossomNear(par1World, par2, par3, par4);
  }
  
  public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5) {
    checkBlossomNear(par1World, par2, par3, par4);
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister par1IIconRegister) {
    this.theEmptyIIcon = par1IIconRegister.registerIcon("morefunquicksandmod:Acid_Still");
  }
  
  public Item getItemDropped(int par1, Random par2Random, int par3) {
    return null;
  }
  
  public int quantityDropped(Random par1Random) {
    return 0;
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
