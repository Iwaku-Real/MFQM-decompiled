package MoreFunQuicksandMod.main.liquids;

import MoreFunQuicksandMod.main.CustomVarPlayer;
import MoreFunQuicksandMod.main.Fields;
import MoreFunQuicksandMod.main.MFQM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.lang.reflect.Field;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;

public class BlockLiquidMire extends BlockFluidClassic {
  public IIcon[] theMireIIcon;
  
  private int maxOpacity;
  
  private int lastOpacity;
  
  private int incOpacity;
  
  public BlockLiquidMire(int mopc, int lopc, int iopc) {
    super(MFQM.MireFluid, Material.water);
    setQuantaPerBlock(8);
    if (MFQM.QSOpacity) {
      setLightOpacity(16);
    } else {
      setLightOpacity(3);
    } 
    setTickRandomly(true);
    setUnlocalizedName("LiquidMire");
    setRenderPass(0);
    this.maxOpacity = mopc;
    this.lastOpacity = lopc;
    this.incOpacity = iopc;
  }
  
  public void updateTick(World world, int x, int y, int z, Random rand) {
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
      world.setBlock(x, y, z, Blocks.dirt, 0, 3);
      world.playSoundEffect((x + 0.5F), (y + 0.5F), (z + 0.5F), "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
      for (int l = 0; l < 8; l++)
        world.spawnParticle("largesmoke", x + Math.random(), y + 1.2D, z + Math.random(), 0.0D, 0.0D, 0.0D); 
      return;
    } 
    boolean inMire = false;
    int mireCount = 0;
    Block gbi = world.getBlock(x, y - 1, z);
    if (gbi == MFQM.MireBlock || gbi == MFQM.MorassBlock)
      mireCount++; 
    gbi = world.getBlock(x + 1, y, z);
    if (gbi == MFQM.MireBlock || gbi == MFQM.MorassBlock)
      mireCount++; 
    gbi = world.getBlock(x - 1, y, z);
    if (gbi == MFQM.MireBlock || gbi == MFQM.MorassBlock)
      mireCount++; 
    gbi = world.getBlock(x, y, z - 1);
    if (gbi == MFQM.MireBlock || gbi == MFQM.MorassBlock)
      mireCount++; 
    gbi = world.getBlock(x, y, z + 1);
    if (gbi == MFQM.MireBlock || gbi == MFQM.MorassBlock)
      mireCount++; 
    if (mireCount > 2) {
      if (mireCount > 4)
        mireCount = 4; 
      inMire = true;
    } 
    if (world.getBlockMetadata(x, y, z) == 0) {
      Block block = world.getBlock(x, y + this.densityDir, z);
      int bMeta = world.getBlockMetadata(x, y + this.densityDir, z);
      if (block == this && bMeta != 0) {
        world.setBlock(x, y + this.densityDir, z, (Block)this);
        world.setBlockMetadataWithNotify(x, y + this.densityDir, z, 0, 3);
        world.setBlockToAir(x, y, z);
        return;
      } 
      if (world.rand.nextInt(5 - mireCount) == 0 && 
        world.getBlock(x, y - 1, z) != Blocks.air) {
        boolean canRes = false;
        if (world.getBlock(x + 1, y, z) != Blocks.air && 
          world.getBlock(x, y, z + 1) != Blocks.air && 
          world.getBlock(x - 1, y, z) != Blocks.air && 
          world.getBlock(x, y, z - 1) != Blocks.air)
          canRes = true; 
        if (world.getBlock(x, y - 1, z) != MFQM.MorassBlock && world.getBlock(x, y - 1, z) != MFQM.MireBlock && (world.getBlock(x, y - 1, z).getMaterial() == Material.grass || world.getBlock(x, y - 1, z).getMaterial() == Material.ground || world.getBlock(x, y - 1, z).getMaterial() == Material.sand || world.getBlock(x, y - 1, z).getMaterial() == Material.snow || world.getBlock(x, y - 1, z).getMaterial() == Material.water)) {
          world.setBlock(x, y - 1, z, MFQM.MireBlock, 10, 3);
          world.setBlockToAir(x, y, z);
          return;
        } 
        if (canRes) {
          world.setBlock(x, y, z, MFQM.MireBlock, 10, 3);
          return;
        } 
      } 
    } else if (inMire && 
      world.rand.nextInt(5 - mireCount) == 0) {
      world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) - 1, 2);
    } 
    int isdelFl = world.getBlockMetadata(x, y, z);
    setQuantaPerBlock(3);
    super.updateTick(world, x, y, z, rand);
    setQuantaPerBlock(8);
    if (inMire)
      world.setBlock(x, y, z, (Block)this, isdelFl, 0); 
  }
  
  public void modifyEntityVelocity(World par1World, int par2, int par3, int par4, Entity par5Entity, Vec3 par6Vec3) {}
  
  public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
    int mtd = world.getBlockMetadata(x, y, z);
    double EposY = entity.posY;
    double EprevPosY = entity.prevPosY;
    double prevVel = entity.motionY;
    double height = 0.075D + (mtd / 2);
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
      if (entity instanceof EntityPlayer)
        checkPlayerMuddy((EntityPlayer)entity, x, y, z, world); 
      if (MFQM.isEntityInsideOfBlock(entity, (Block)this) && 
        entity.getAir() < 1)
        MFQM.spawnDrowningBubble(entity.worldObj, entity, (Block)this, 0); 
      boolean entJump = false;
      boolean entMoving = false;
      boolean entSplash = false;
      boolean entRotate = false;
      float mtdkof = (1 + mtd / 5);
      double movDis = 1.0D;
      double movCof = 16.0D;
      double movKofDiv = 1.0D;
      double entup = entity.motionY;
      if (!(entity instanceof EntityPlayer)) {
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
          movKofDiv += 0.05D; 
      } 
      if (entMoving && world.getTotalWorldTime() % Math.max((int)Math.floor(movCof), 1) == 0L && 
        !entSplash && entMoving) {
        if (world.rand.nextInt(2) == 0)
          world.playSoundEffect(entity.posX, EposY, entity.posZ, "game.player.swim", 0.15F, world.rand.nextFloat() * 0.25F + 0.1F); 
        if (world.rand.nextInt(5) == 0)
          MFQM.spawnBodyBubbleRandom(world, entity, x, y, z, (Block)this, 0); 
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
      if (world.getTotalWorldTime() % 16L == 0L && 
        world.rand.nextInt(10) == 0)
        MFQM.spawnBodyBubble(world, entity, x, y, z, (Block)this, 0); 
      if (!entSplash)
        entity.motionY = 0.0D; 
      entity.motionZ = 0.0D;
      entity.motionX = 0.0D;
      entup += (movKofDiv - 1.0D) * 5.0D;
      if (world.getBlock(x, y + 1, z) != this) {
        double WSIN = Math.sin(world.getTotalWorldTime() / (4.0D - world.rand.nextFloat() * 0.05D));
        double OSIN = Math.sin(world.getTotalWorldTime() / (5.0D - world.rand.nextFloat() * 0.01D));
        double SIN = Math.max(OSIN * 1.3D, 0.0D) + 0.5D;
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
    } else if (kof1 < 0.0D) {
      entity.setInWeb();
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public void randomDisplayTick(World world, int x, int y, int z, Random random) {
    if (world.getBlock(x, y + 1, z).getMaterial() == Material.air) {
      int md = world.getBlockMetadata(x, y, z);
      if (world.rand.nextInt(300) == 0) {
        double xx = x + random.nextFloat();
        double zz = z + random.nextFloat();
        MFQM.spawnQSBubble(world, xx, (y + 1 / (1 + md)) - 0.15D, zz, (Block)this, 0, 0.5F);
      } 
      if (world.rand.nextInt(100) == 0)
        world.playSound(x + 0.5D, y, z + 0.5D, "liquid.water", 0.75F, world.rand.nextFloat() * 0.45F + 0.1F, false); 
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int par1, int par2) {
    return (par1 != 0 && par1 != 1) ? this.theMireIIcon[1] : this.theMireIIcon[0];
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
    if (getMaterial() == Material.water)
      this.theMireIIcon = new IIcon[] { par1IIconRegister.registerIcon("morefunquicksandmod:Mire_Still"), par1IIconRegister.registerIcon("morefunquicksandmod:Mire_Flowing") }; 
  }
  
  public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    ItemStack item = player.getCurrentEquippedItem();
    if (item == null)
      return false; 
    if (item.getItem() != Items.glass_bottle)
      return false; 
    item.stackSize--;
    if (item.stackSize <= 0) {
      if (!world.isRemote)
        player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(MFQM.MyPotionItem, 1, 0)); 
      return true;
    } 
    int isA = findItem(player, (Item)Items.potionitem, 0);
    if (isA != -1) {
      player.inventory.setInventorySlotContents(isA, new ItemStack(MFQM.MyPotionItem, 1, 0));
      return true;
    } 
    if (!player.inventory.addItemStackToInventory(new ItemStack(MFQM.MyPotionItem)))
      player.dropPlayerItemWithRandomChoice(new ItemStack(MFQM.MyPotionItem, 1, 0), false); 
    return true;
  }
  
  public boolean canStopRayTrace(int p_149678_1_, boolean p_149678_2_) {
    return true;
  }
  
  public int findItem(EntityPlayer player, Item item, int meta) {
    for (int var2 = 0; var2 < player.inventory.mainInventory.length; var2++) {
      if (player.inventory.mainInventory[var2] != null && player.inventory.mainInventory[var2].getItem() == item)
        if (player.inventory.mainInventory[var2].getMetadata() == meta)
          return var2;  
    } 
    return -1;
  }
  
  public float getPlayerRelativeBlockHardness(EntityPlayer par1EntityPlayer, World par2World, int par3, int par4, int par5) {
    return -1.0F;
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
