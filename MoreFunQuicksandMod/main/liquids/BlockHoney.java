package MoreFunQuicksandMod.main.liquids;

import MoreFunQuicksandMod.main.CustomMaterialLiquid;
import MoreFunQuicksandMod.main.CustomVarPlayer;
import MoreFunQuicksandMod.main.MFQM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;

public class BlockHoney extends BlockFluidClassic {
  public IIcon[] theHoneyIIcon;
  
  public static final Material materialHoney = (new CustomMaterialLiquid(MapColor.sandColor)).setReplaceable();
  
  private int maxOpacity;
  
  private int lastOpacity;
  
  private int incOpacity;
  
  public BlockHoney(int mopc, int lopc, int iopc) {
    super(MFQM.HoneyFluid, materialHoney);
    setResistance(1000.0F);
    setQuantaPerBlock(2);
    this.quantaPerBlockFloat = 1.75F;
    if (MFQM.QSOpacity) {
      setLightOpacity(32);
    } else {
      setLightOpacity(3);
    } 
    setTickRandomly(true);
    setUnlocalizedName("Honey");
    setRenderPass(1);
    setCreativeTab(MFQM.tabMFQM);
    this.maxOpacity = mopc;
    this.lastOpacity = lopc;
    this.incOpacity = iopc;
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
  
  public float getPlayerRelativeBlockHardness(EntityPlayer par1EntityPlayer, World par2World, int par3, int par4, int par5) {
    return -1.0F;
  }
  
  public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
    int mtd = world.getBlockMetadata(x, y, z);
    double EposY = entity.posY;
    double EprevPosY = entity.prevPosY;
    double prevVel = entity.motionY;
    double oy = y - EposY + 1.0D - mtd;
    double oy2 = y - EprevPosY + 1.0D - mtd;
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
      oy = y - EposY - 0.5D - mtd;
      oy2 = y - EprevPosY - 0.5D - mtd;
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
      if (entity instanceof MoreFunQuicksandMod.main.entity.EntityBee)
        Affect = false; 
      if (Affect)
        checkEntityUnder(entity); 
      if (entity instanceof EntityPlayer)
        checkPlayerMuddy((EntityPlayer)entity, x, y, z, world); 
      boolean boots = false;
      boolean entJump = false;
      boolean entMoving = false;
      boolean entSplash = false;
      boolean entRotate = false;
      float mtdkof = 5.0F;
      double movDis = 1.0D;
      double movDisa = 1.0D;
      double movCof = 16.0D;
      double movKofDiv = 1.0D;
      int mr_kof = (int)Math.min(5.0D + Math.floor(Math.max(0.0D, Math.pow((mtdkof * 5.0F) * (1.5D - kof1m), 1.5D))), 140.0D);
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
          movKofDiv += mr_kof * 0.004D; 
      } 
      movDisa = movDis;
      entity.motionX = 0.0D;
      entity.motionZ = 0.0D;
      if (entity.motionY > -0.1D) {
        entity.motionY = 0.0D;
      } else {
        entity.motionY /= 1.5D;
      } 
      if (!(entity instanceof EntityPlayer) && 
        !Affect) {
        if (world.getBlock(x, y + 1, z) != this) {
          if (kof1 >= 1.35D) {
            if (!entSplash) {
              entity.motionY = 0.0D;
              entity.motionY += 0.1D;
              entity.onGround = true;
              entity.fallDistance = 0.0F;
            } 
          } else {
            entity.motionY = 0.0D;
            entity.motionY += 0.2D;
            entity.onGround = true;
            entity.fallDistance = 0.0F;
          } 
        } else {
          entity.motionY = 0.0D;
          entity.motionY += 0.2D;
          entity.onGround = true;
          entity.fallDistance = 0.0F;
        } 
        return;
      } 
      if (world.getTotalWorldTime() % 128L == 0L || (entMoving && world.getTotalWorldTime() % Math.max((int)Math.floor(movCof), 1) == 0L) || (entJump && world.getTotalWorldTime() % 8L == 0L) || entSplash) {
        if (entSplash && 
          kof2 > 1.5D) {
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "game.player.swim", 0.1F, world.rand.nextFloat() * 0.25F + 0.1F);
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.step", 0.5F, world.rand.nextFloat() * 0.25F + 0.25F);
          if (entity instanceof EntityPlayer && 
            world.rand.nextInt(2) == 0) {
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, (Block)this, 0);
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, (Block)this, 0);
          } 
        } 
        if (!entSplash && entMoving) {
          if (world.rand.nextInt(2) == 0) {
            world.playSoundEffect(entity.posX, EposY, entity.posZ, "game.player.swim", 0.1F, world.rand.nextFloat() * 0.25F + 0.1F);
            world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.silverfish.step", 0.25F, world.rand.nextFloat() * 0.15F + 0.1F);
          } 
          if (world.rand.nextInt(10) == 0)
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, (Block)this, 0); 
        } 
        if (entJump) {
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "mob.slime.attack", 0.25F, world.rand.nextFloat() * 0.25F + 0.25F);
          if (entity instanceof EntityPlayer && 
            world.rand.nextInt(2) == 0) {
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, (Block)this, 0);
            MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, (Block)this, 0);
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
      if (world.getTotalWorldTime() % 16L == 0L && 
        world.rand.nextInt(20) == 0)
        MFQM.spawnBodyBubble(world, entity, x, y, z, (Block)this, 0); 
      if (world.getBlock(x, y + 1, z) != this || (world.getBlock(x, y + 1, z) == this && world.getBlock(x, y + 2, z) == this)) {
        if (world.getBlock(x, y + 1, z) == this && world.getBlock(x, y + 2, z) == this)
          kof1 = 0.001D; 
        if (!world.isRemote && 
          world.getTotalWorldTime() % 4L == 0L) {
          double oy3 = y - EposY - 0.5D - mtd;
          oy3 *= -1.0D;
          double kof3 = oy3;
          if (kof3 < 0.75D) {
            PotionEffect PERegen = ((EntityLivingBase)entity).getActivePotionEffect(Potion.regeneration);
            int regenTime = 0;
            if (PERegen != null)
              regenTime = PERegen.getDuration(); 
            if (regenTime < 25)
              ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(10, 600, 0, false)); 
          } 
        } 
        double mys = 0.0D;
        if (!(entity instanceof EntityPlayer))
          mys = -0.0025D; 
        if (entJump && 
          world.rand.nextInt(1) == 0 && 
          MFQM.suctionWorldCheck(entity, world, prevVel)) {
          entity.motionY -= 0.05D * (Math.min(0.75D, kof1m) + 1.0D);
          world.playSound(entity.posX, EposY, entity.posZ, "mob.magmacube.jump", 0.25F, 0.25F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F, false);
        } 
        if (kof1 >= 1.3D) {
          double a1 = 0.0735D;
          entity.onGround = false;
          entity.fallDistance = 0.0F;
          if (!entSplash) {
            entity.motionY += a1 / Math.max((movKofDiv / 3.0D - 1.0D) * Math.max(kof1m - 0.5D, 0.75D) * 1.6D * mtdkof * 17.5D, 1.0D) / Math.pow(Math.max(kof1m / 1.25D, 1.0D), 2.0D);
            if (world.rand.nextInt(Math.max((int)Math.floor((1.5D - kof1m) * 10.0D), 1)) == 0)
              entity.onGround = true; 
            if (entity instanceof EntityPlayer) {
              if (world.getTotalWorldTime() % Math.max(1.0D + Math.floor(Math.max(kof1 * 20.0D - (mtd / 10), 0.0D)), 1.0D) == 0.0D)
                entity.setInWeb(); 
            } else {
              double mw_kof = (1 + mr_kof / 10);
              entity.setPosition(entity.prevPosX + (entity.posX - entity.prevPosX) / mw_kof, EposY, entity.prevPosZ + (entity.posZ - entity.prevPosZ) / mw_kof);
            } 
          } 
        } else {
          if (EposY - EprevPosY > 0.001D && 
            world.rand.nextInt(Math.max((int)Math.floor(kof1m * 7.5D), 15)) == 0 && 
            MFQM.suctionWorldCheck(entity, world, prevVel)) {
            entity.motionY -= 0.025D * mtdkof * (Math.min(0.75D, kof1m) + 1.0D);
            world.playSound(entity.posX, EposY, entity.posZ, "mob.magmacube.jump", 0.25F, 0.25F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.4F, false);
          } 
          if (kof1 >= 0.9D) {
            double a1 = 0.07375D;
            MFQM.setStuckEffect((EntityLivingBase)entity, mr_kof);
            entity.onGround = false;
            entity.fallDistance = 0.0F;
            if (!entSplash) {
              entity.motionY += a1 / Math.max((movKofDiv / 3.0D - 1.0D) * Math.max(kof1m - 0.5D, 0.75D) * 1.6D * mtdkof * 17.5D, 1.0D) / Math.pow(Math.max(kof1m / 1.25D, 1.0D), 2.0D);
              if (world.rand.nextInt(Math.max((int)Math.floor((1.5D - kof1m) * 10.0D), 1)) == 0)
                entity.onGround = true; 
              if (entity instanceof EntityPlayer) {
                if (world.getTotalWorldTime() % Math.max(1.0D + Math.floor(Math.max(kof1 * 20.0D - (mtd / 10), 0.0D)), 1.0D) == 0.0D)
                  entity.setInWeb(); 
              } else {
                double mw_kof = (1 + mr_kof / 10);
                entity.setPosition(entity.prevPosX + (entity.posX - entity.prevPosX) / mw_kof, EposY, entity.prevPosZ + (entity.posZ - entity.prevPosZ) / mw_kof);
              } 
            } 
          } else {
            double a2 = 0.85D;
            if (world.rand.nextInt(Math.max((int)Math.floor((1.5D - kof1m) * 10.0D), 1)) == 0)
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
                MFQM.setStuckEffect((EntityLivingBase)entity, 50); 
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
          MFQM.setStuckEffect((EntityLivingBase)entity, 50); 
      } 
      MFQM.antiHoldJumpScript(entity, kof1, true);
    } else if (kof1 < 1.5D) {
      entity.setInWeb();
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int par1, int par2) {
    return (par1 != 0 && par1 != 1) ? this.theHoneyIIcon[1] : this.theHoneyIIcon[0];
  }
  
  public int checkFusion(World world, int x, int y, int z, Random rand) {
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
    if (isWaterNear) {
      world.setBlock(x, y, z, MFQM.SolidHoneyBlock, 0, 3);
      world.playSoundEffect((x + 0.5F), (y + 0.5F), (z + 0.5F), "mob.silverfish.step", 0.25F, world.rand.nextFloat() * 0.15F + 0.1F);
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
      if (world.rand.nextInt(10) == 0) {
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
          inMetaBlocks > 1)
          world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) - 1, 2); 
      } 
    } 
    this.quantaPerBlockFloat = 1.75F;
    super.updateTick(world, x, y, z, rand);
  }
  
  public void modifyEntityVelocity(World par1World, int par2, int par3, int par4, Entity par5Entity, Vec3 par6Vec3) {}
  
  public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer ply, int par1, float par2, float par3, float par4) {
    if (ply.getCurrentEquippedItem() == null) {
      eatMe(world, x, y, z, ply);
      return true;
    } 
    return super.onBlockActivated(world, x, y, z, ply, par1, par2, par3, par4);
  }
  
  private void eatMe(World world, int x, int y, int z, EntityPlayer ply) {
    if (ply.canEat(false)) {
      ply.getFoodStats().addStats(1, 0.0F);
      ply.getFoodStats().addExhaustion(0.1F);
      world.playSoundEffect((x + world.rand.nextFloat()), (y + 0.5F), (z + world.rand.nextFloat()), "mob.silverfish.step", 0.25F, world.rand.nextFloat() * 0.15F + 0.1F);
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public void randomDisplayTick(World world, int x, int y, int z, Random random) {
    if (!world.getBlock(x, y + 1, z).isOpaqueCube()) {
      int md = world.getBlockMetadata(x, y, z);
      if (world.rand.nextInt(5000) == 0) {
        double xx = x + random.nextFloat();
        double zz = z + random.nextFloat();
        MFQM.spawnQSBubble(world, xx, (y + 1) - 0.15D, zz, (Block)this, 0, 0.5F);
      } 
    } 
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
    this.theHoneyIIcon = new IIcon[] { par1IIconRegister.registerIcon("morefunquicksandmod:Honey_Still"), par1IIconRegister.registerIcon("morefunquicksandmod:Honey_Flowing") };
  }
  
  public boolean isNormalCube() {
    return false;
  }
  
  public void checkEntityUnder(Entity ent) {
    if (MFQM.isEntityInsideOfBlock(ent, (Block)this) && 
      MFQM.isDrowning(ent)) {
      MFQM.spawnDrowningBubble(ent.worldObj, ent, (Block)this, 0);
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
        int mdtp = MFQM.getMuddyType((Block)this);
        props.setMuddyType(mdtp);
        if (props.getMuddyTime() < this.maxOpacity)
          props.addMuddyTime(this.incOpacity); 
        props.setMuddyTime(Math.min(props.getMuddyTime(), this.maxOpacity));
      } 
    } 
  }
}
