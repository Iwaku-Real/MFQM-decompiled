package MoreFunQuicksandMod.main.blocks;

import MoreFunQuicksandMod.main.CustomMaterialGas;
import MoreFunQuicksandMod.main.MFQM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGas extends Block {
  @SideOnly(Side.CLIENT)
  private IIcon[] IIconNormalArray;
  
  private int[] arrayDirection = new int[6];
  
  public static final Block.SoundType soundTypeGas = new Block.SoundType("stone", 0.0F, 0.0F);
  
  public static final Material materialGas = (Material)new CustomMaterialGas(MapColor.airColor);
  
  public BlockGas() {
    super(materialGas);
    setHardness(0.0F);
    setStepSound(soundTypeGas);
    setResistance(0.0F);
    setUnlocalizedName("Gas");
    setTickRandomly(true);
    setCreativeTab(MFQM.tabMFQM);
  }
  
  public void breakBlock(World world, int x, int y, int z, Block bl, int meta) {
    if (world.getBlock(x, y, z) != MFQM.SlurryBlock)
      return; 
    int met = meta;
    int tick = 0;
    while (met > 0 && tick < 3) {
      met = RandomSpread(world, x, y, z, met);
      tick++;
    } 
    RandomSpread(world, x, y, z, 1);
  }
  
  public int RandomSpread(World world, int x, int y, int z, int Meta) {
    int xx = 0;
    int yy = 0;
    int zz = 0;
    this.arrayDirection[0] = 0;
    this.arrayDirection[1] = 1;
    this.arrayDirection[2] = 2;
    this.arrayDirection[3] = 3;
    this.arrayDirection[4] = 4;
    this.arrayDirection[5] = 5;
    int dir = 0;
    int tick = 0;
    int nMeta = Meta;
    while (tick < 6 && nMeta > 0) {
      dir = world.rand.nextInt(6);
      int tick2 = 0;
      while ((this.arrayDirection[dir] == -1 || (this.arrayDirection[dir] == 4 && tick2 < 5 && world.rand.nextInt(2) != 0)) && tick2 < 6) {
        dir++;
        if (dir > 5)
          dir = 0; 
        tick2++;
      } 
      if (tick2 > 5)
        break; 
      switch (this.arrayDirection[dir]) {
        case 0:
          xx = 1;
          yy = 0;
          zz = 0;
          break;
        case 1:
          xx = -1;
          yy = 0;
          zz = 0;
          break;
        case 2:
          xx = 0;
          yy = 0;
          zz = 1;
          break;
        case 3:
          xx = 0;
          yy = 0;
          zz = -1;
          break;
        case 4:
          xx = 0;
          yy = 1;
          zz = 0;
          break;
        case 5:
          xx = 0;
          yy = -1;
          zz = 0;
          break;
      } 
      if (world.getBlock(x + xx, y + yy, z + zz) == Blocks.air) {
        world.setBlock(x + xx, y + yy, z + zz, this, 0, 2);
        nMeta--;
      } else if (world.getBlock(x + xx, y + yy, z + zz) == this && 
        world.getBlockMetadata(x + xx, y + yy, z + zz) < nMeta) {
        world.setBlockMetadataWithNotify(x + xx, y + yy, z + zz, world.getBlockMetadata(x + xx, y + yy, z + zz) + 1, 2);
        nMeta--;
      } 
      this.arrayDirection[dir] = -1;
      tick++;
    } 
    return nMeta;
  }
  
  public void updateTick(World world, int x, int y, int z, Random rand) {
    int meta = world.getBlockMetadata(x, y, z);
    int xx = x;
    int yy = y;
    int zz = z;
    int tickRate = 10;
    if (MFQM.ABC && 
      world.provider.dimensionId == MFQM.ABCDim2)
      tickRate = 20; 
    if (world.getBlock(x, y - 1, z) == Blocks.air) {
      world.setBlock(x, y - 1, z, this, meta, 3);
      world.setBlockToAir(x, y, z);
      yy = y - 1;
    } 
    if (meta > 0) {
      if (rand.nextInt(Math.max(48 - meta * 5, 1)) == 0) {
        meta = RandomSpread(world, xx, yy, zz, meta);
        world.setBlockMetadataWithNotify(xx, yy, zz, meta, 2);
        world.scheduleBlockUpdate(xx, yy, zz, this, tickRate);
        return;
      } 
      meta--;
      if (rand.nextInt(1000) == 0 && meta >= 0)
        world.setBlockMetadataWithNotify(xx, yy, zz, meta, 2); 
    } else if (rand.nextInt(50 * tickRate) == 0) {
      world.setBlockToAir(xx, yy, zz);
      return;
    } 
    world.scheduleBlockUpdate(xx, yy, zz, this, tickRate);
  }
  
  public boolean CheckBreathProtect(Entity entity) {
    if (!(entity instanceof EntityPlayer))
      return false; 
    if (((EntityPlayer)entity).getCurrentArmor(3) == null)
      return false; 
    String str1 = ((EntityPlayer)entity).getCurrentArmor(3).getItem().getUnlocalizedName();
    if (str1.toLowerCase().matches("(.*)gas(.*)"))
      return true; 
    if (str1.toLowerCase().matches("(.*)oxygen(.*)"))
      return true; 
    if (str1.matches("(.*)Hazmat(.*)"))
      return true; 
    if (str1.matches("(.*)Nano(.*)"))
      return true; 
    if (str1.matches("(.*)Quantum(.*)"))
      return true; 
    if (str1.toLowerCase().matches("(.*)poison(.*)"))
      return true; 
    if (MFQM.AOAFaceMaskItemID != 0 && (
      (EntityPlayer)entity).getCurrentArmor(3).getItem() == MFQM.AOAFaceMaskItem)
      return true; 
    return false;
  }
  
  public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
    int mtd = world.getBlockMetadata(x, y, z);
    if (entity instanceof EntityLivingBase && 
      !world.isRemote) {
      boolean BreathProtect = false;
      boolean BreathProtectA = false;
      if (entity instanceof EntityPlayer) {
        BreathProtect = (BreathProtect || EnchantmentHelper.getRespiration((EntityLivingBase)entity) > 0);
        BreathProtectA = (BreathProtectA || BreathProtect);
      } 
      if (MFQM.ABC && 
        world.provider.dimensionId == MFQM.ABCDim2) {
        if (mtd > 3 && 
          world.getTotalWorldTime() % 200L - (mtd * 5) == 0L && 
          world.rand.nextInt(5) == 0) {
          if (!BreathProtectA) {
            BreathProtect = (BreathProtect || CheckBreathProtect(entity));
            BreathProtectA = true;
          } 
          if (!BreathProtect)
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(15, 200, 0, false)); 
        } 
        if (world.getTotalWorldTime() % 400L - (mtd * 5) == 0L && 
          world.rand.nextInt(5) == 0) {
          if (!BreathProtectA) {
            BreathProtect = (BreathProtect || CheckBreathProtect(entity));
            BreathProtectA = true;
          } 
          if (!BreathProtect)
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(17, 200, 0, false)); 
        } 
        return;
      } 
      if (world.getTotalWorldTime() % 80L - (mtd * 5) == 0L && 
        world.rand.nextInt(5) == 0) {
        if (!BreathProtectA) {
          BreathProtect = (BreathProtect || CheckBreathProtect(entity));
          BreathProtectA = true;
        } 
        if (!BreathProtect)
          ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(17, 200, 0, false)); 
      } 
      if (world.getTotalWorldTime() % 200L - (mtd * 5) == 0L && 
        world.rand.nextInt(5) == 0) {
        if (!BreathProtectA) {
          BreathProtect = (BreathProtect || CheckBreathProtect(entity));
          BreathProtectA = true;
        } 
        if (!BreathProtect)
          ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(9, 200, 0, false)); 
      } 
      if (world.getTotalWorldTime() % 500L - (mtd * 5) == 0L && 
        world.rand.nextInt(5) == 0) {
        if (!BreathProtectA) {
          BreathProtect = (BreathProtect || CheckBreathProtect(entity));
          BreathProtectA = true;
        } 
        if (!BreathProtect) {
          ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(2, 200, 0, false));
          ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(4, 200, 0, false));
          ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(18, 200, 0, false));
        } 
      } 
      if (mtd > 2) {
        if (world.getTotalWorldTime() % 80L - (mtd * 5) == 0L && 
          world.rand.nextInt(5) == 0) {
          if (!BreathProtectA) {
            BreathProtect = (BreathProtect || CheckBreathProtect(entity));
            BreathProtectA = true;
          } 
          if (!BreathProtect) {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(9, 200, 0, false));
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(17, 600, 1, false));
          } 
        } 
        if (world.getTotalWorldTime() % 200L - (mtd * 5) == 0L && 
          world.rand.nextInt(5) == 0) {
          if (!BreathProtectA) {
            BreathProtect = (BreathProtect || CheckBreathProtect(entity));
            BreathProtectA = true;
          } 
          if (!BreathProtect)
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(19, 200, 0, false)); 
        } 
      } 
      if (mtd > 3) {
        if (world.getTotalWorldTime() % 100L - (mtd * 5) == 0L && 
          world.rand.nextInt(5) == 0) {
          if (!BreathProtectA) {
            BreathProtect = (BreathProtect || CheckBreathProtect(entity));
            BreathProtectA = true;
          } 
          if (!BreathProtect) {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(2, 200, 1, false));
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(4, 200, 1, false));
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(18, 200, 1, false));
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(17, 1200, 2, false));
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(9, 600, 0, false));
          } 
        } 
        if (world.getTotalWorldTime() % 150L - (mtd * 5) == 0L && 
          world.rand.nextInt(5) == 0) {
          if (!BreathProtectA) {
            BreathProtect = (BreathProtect || CheckBreathProtect(entity));
            BreathProtectA = true;
          } 
          if (!BreathProtect)
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(19, 200, 0, false)); 
        } 
      } 
      if (mtd > 4) {
        if (world.getTotalWorldTime() % 150L - (mtd * 5) == 0L && 
          world.rand.nextInt(5) == 0) {
          if (!BreathProtectA) {
            BreathProtect = (BreathProtect || CheckBreathProtect(entity));
            BreathProtectA = true;
          } 
          if (!BreathProtect) {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(19, 600, 0, false));
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(2, 200, 2, false));
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(4, 200, 2, false));
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(18, 200, 2, false));
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(17, 2400, 3, false));
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(15, 100, 100, false));
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(9, 1200, 0, false));
          } 
        } 
        if (world.getTotalWorldTime() % 500L - (mtd * 5) == 0L && 
          world.rand.nextInt(5) == 0) {
          if (!BreathProtectA) {
            BreathProtect = (BreathProtect || CheckBreathProtect(entity));
            BreathProtectA = true;
          } 
          if (!BreathProtect)
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(20, 200, 0, false)); 
        } 
      } 
    } 
  }
  
  public void onBlockAdded(World par1World, int par2, int par3, int par4) {
    par1World.scheduleBlockUpdate(par2, par3, par4, this, 10);
  }
  
  public void onNeighborBlockChange(World world, int x, int y, int z, Block bl) {
    if (world.getBlock(x, y + 1, z) instanceof net.minecraft.block.BlockFalling) {
      world.setBlock(x, y, z, Blocks.air, 0, 2);
      return;
    } 
  }
  
  public int getDamageValue(World par1World, int par2, int par3, int par4) {
    return par1World.getBlockMetadata(par2, par3, par4);
  }
  
  public boolean canStopRayTrace(int p_149678_1_, boolean p_149678_2_) {
    return false;
  }
  
  public int getRenderBlockPass() {
    return 1;
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int par1, int par2) {
    return this.IIconNormalArray[par2];
  }
  
  public boolean isOpaqueCube() {
    return false;
  }
  
  public boolean isNormalCube() {
    return false;
  }
  
  public boolean isSolid() {
    return false;
  }
  
  public Item getItemDropped(int par1, Random par2Random, int par3) {
    return null;
  }
  
  public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
    return null;
  }
  
  public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
    int meta = 0;
    switch (side) {
      case 0:
        meta = world.getBlockMetadata(x, y + 1, z);
        break;
      case 1:
        meta = world.getBlockMetadata(x, y - 1, z);
        break;
      case 2:
        meta = world.getBlockMetadata(x, y, z + 1);
        break;
      case 3:
        meta = world.getBlockMetadata(x, y, z - 1);
        break;
      case 4:
        meta = world.getBlockMetadata(x + 1, y, z);
        break;
      case 5:
        meta = world.getBlockMetadata(x - 1, y, z);
        break;
    } 
    return (!world.getBlock(x, y, z).isOpaqueCube() && (world.getBlock(x, y, z) != this || world.getBlockMetadata(x, y, z) + 3 <= meta));
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister par1IIconRegister) {
    this.IIconNormalArray = new IIcon[16];
    for (int i = 0; i < this.IIconNormalArray.length; i++)
      this.IIconNormalArray[i] = par1IIconRegister.registerIcon("morefunquicksandmod:Gas" + i); 
  }
  
  @SideOnly(Side.CLIENT)
  public int getBlockColor() {
    if (MFQM.ABC && 
      (Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.ABCDim2)
      return 0; 
    return 16777215;
  }
  
  @SideOnly(Side.CLIENT)
  public int getRenderColor(int par1) {
    return 16777215;
  }
  
  @SideOnly(Side.CLIENT)
  public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
    if (MFQM.ABC && 
      (Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.ABCDim2)
      return 0; 
    return 16777215;
  }
}
