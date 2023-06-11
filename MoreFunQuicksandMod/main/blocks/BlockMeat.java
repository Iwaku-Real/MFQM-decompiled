package MoreFunQuicksandMod.main.blocks;

import MoreFunQuicksandMod.main.MFQM;
import MoreFunQuicksandMod.main.Tileentity.TileEntityMeat;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockMeat extends BlockContainer {
  @SideOnly(Side.CLIENT)
  private IIcon[] IIconMArray;
  
  public BlockMeat() {
    super(Material.sponge);
    setHardness(2.0F);
    setStepSound(Block.soundTypeGravel);
    setUnlocalizedName("MeatWall");
    setTickRandomly(true);
    setCreativeTab(MFQM.tabMFQM);
  }
  
  public TileEntity createNewTileEntity(World world, int i) {
    if (i > 5 && i < 10) {
      if (world.isRemote) {
        TileEntityMeat TE = new TileEntityMeat();
        TE.phase = world.rand.nextDouble() * 6.28318D * 2.0D;
        return (TileEntity)TE;
      } 
      return null;
    } 
    return null;
  }
  
  public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public void getSubBlocks(Item block, CreativeTabs creativeTabs, List<ItemStack> list) {
    list.add(new ItemStack(block, 1, 0));
    list.add(new ItemStack(block, 1, 1));
    list.add(new ItemStack(block, 1, 2));
    list.add(new ItemStack(block, 1, 3));
    list.add(new ItemStack(block, 1, 4));
    list.add(new ItemStack(block, 1, 5));
    list.add(new ItemStack(block, 1, 6));
    list.add(new ItemStack(block, 1, 7));
    list.add(new ItemStack(block, 1, 8));
    list.add(new ItemStack(block, 1, 9));
    list.add(new ItemStack(block, 1, 10));
  }
  
  public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
    int meta = world.getBlockMetadata(x, y, z);
    if (entity instanceof EntityPlayer) {
      InventoryPlayer inventory = ((EntityPlayer)entity).inventory;
      if (inventory.armorInventory[0] != null && inventory.armorInventory[0].getItem() == Item.getItemById(MFQM.BootsID))
        return; 
    } 
    entity.motionX *= 0.9D;
    entity.motionZ *= 0.9D;
    if (meta > 1)
      switch (meta) {
        case 2:
          entity.motionZ = 0.1D;
          entity.motionX = 0.1D;
          break;
        case 3:
          entity.motionZ = 0.1D;
          entity.motionX = -0.1D;
          break;
        case 4:
          entity.motionZ = -0.1D;
          entity.motionX = -0.1D;
          break;
        case 5:
          entity.motionZ = -0.1D;
          entity.motionX = 0.1D;
          break;
        case 6:
          entity.motionZ = -0.15D;
          break;
        case 7:
          entity.motionZ = 0.15D;
          break;
        case 8:
          entity.motionX = -0.15D;
          break;
        case 9:
          entity.motionX = 0.15D;
          break;
      }  
  }
  
  public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int par2, int par3, int par4) {
    float f = 0.125F;
    return AxisAlignedBB.getBoundingBox(par2, par3, par4, (par2 + 1), ((par3 + 1) - f), (par4 + 1));
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int par1, int par2) {
    switch (par2) {
      case 0:
        return this.IIconMArray[0];
      case 1:
        if (par1 == 1)
          return this.IIconMArray[9]; 
        if (par1 == 0)
          return this.IIconMArray[0]; 
        return this.IIconMArray[6];
      case 2:
        if (par1 == 1 || par1 == 0)
          return this.IIconMArray[1]; 
        if (par1 == 4 || par1 == 2)
          return this.IIconMArray[0]; 
        if (par1 == 3)
          return this.IIconMArray[8]; 
        return this.IIconMArray[4];
      case 3:
        if (par1 == 1 || par1 == 0)
          return this.IIconMArray[3]; 
        if (par1 == 5 || par1 == 2)
          return this.IIconMArray[0]; 
        if (par1 == 3)
          return this.IIconMArray[4]; 
        return this.IIconMArray[8];
      case 4:
        if (par1 == 1 || par1 == 0)
          return this.IIconMArray[5]; 
        if (par1 == 3 || par1 == 5)
          return this.IIconMArray[0]; 
        if (par1 == 4)
          return this.IIconMArray[4]; 
        return this.IIconMArray[8];
      case 5:
        if (par1 == 1 || par1 == 0)
          return this.IIconMArray[7]; 
        if (par1 == 4 || par1 == 3)
          return this.IIconMArray[0]; 
        if (par1 == 2)
          return this.IIconMArray[4]; 
        return this.IIconMArray[8];
      case 6:
        if (par1 == 1 || par1 == 0)
          return this.IIconMArray[6]; 
        switch (par1) {
          case 4:
            return this.IIconMArray[4];
          case 5:
            return this.IIconMArray[8];
          case 2:
            return this.IIconMArray[9];
          case 3:
            return this.IIconMArray[0];
        } 
      case 7:
        if (par1 == 1 || par1 == 0)
          return this.IIconMArray[2]; 
        switch (par1) {
          case 4:
            return this.IIconMArray[8];
          case 5:
            return this.IIconMArray[4];
          case 2:
            return this.IIconMArray[0];
          case 3:
            return this.IIconMArray[9];
        } 
      case 8:
        if (par1 == 1 || par1 == 0)
          return this.IIconMArray[4]; 
        switch (par1) {
          case 4:
            return this.IIconMArray[9];
          case 5:
            return this.IIconMArray[0];
          case 2:
            return this.IIconMArray[8];
          case 3:
            return this.IIconMArray[4];
        } 
      case 9:
        if (par1 == 1 || par1 == 0)
          return this.IIconMArray[8]; 
        switch (par1) {
          case 4:
            return this.IIconMArray[0];
          case 5:
            return this.IIconMArray[9];
          case 2:
            return this.IIconMArray[4];
          case 3:
            return this.IIconMArray[8];
        } 
      case 10:
        if (par1 == 0)
          return this.IIconMArray[10]; 
        return this.IIconMArray[0];
    } 
    return this.IIconMArray[9];
  }
  
  protected boolean validForSpread(World world, int x, int y, int z) {
    if (!world.getBlock(x, y, z).getMaterial().isSolid() && (!world.getBlock(x, y, z).getMaterial().blocksMovement() || world.getBlock(x, y, z).getMaterial().isReplaceable()))
      return true; 
    return false;
  }
  
  protected void DropRandomThing(World world, int x, int y, int z) {
    ItemStack itm = null;
    if (world.rand.nextInt(10) == 0) {
      itm = new ItemStack(Items.bone, 1 + world.rand.nextInt(3), 0);
      MFQM.dropItem(world, x, y - 1, z, itm);
      return;
    } 
    if (world.rand.nextInt(35) == 0) {
      itm = new ItemStack(Items.arrow, 1 + world.rand.nextInt(3), 0);
      MFQM.dropItem(world, x, y - 1, z, itm);
      return;
    } 
    if (world.rand.nextInt(35) == 0) {
      itm = new ItemStack(Items.gold_nugget, 1, 0);
      MFQM.dropItem(world, x, y - 1, z, itm);
      return;
    } 
    if (world.rand.nextInt(45) == 0) {
      itm = new ItemStack(Items.iron_ingot, 1, 0);
      MFQM.dropItem(world, x, y - 1, z, itm);
      return;
    } 
    if (world.rand.nextInt(45) == 0) {
      itm = new ItemStack(Items.gold_ingot, 1, 0);
      MFQM.dropItem(world, x, y - 1, z, itm);
      return;
    } 
    if (world.rand.nextInt(50) == 0) {
      itm = new ItemStack(Items.golden_sword, 1, 4 + world.rand.nextInt(12));
      MFQM.dropItem(world, x, y - 1, z, itm);
      return;
    } 
    if (world.rand.nextInt(60) == 0) {
      itm = new ItemStack(Items.stone_sword, 1, 4 + world.rand.nextInt(12));
      MFQM.dropItem(world, x, y - 1, z, itm);
      return;
    } 
    if (world.rand.nextInt(100) == 0) {
      itm = new ItemStack(Items.coal, 1, 0);
      MFQM.dropItem(world, x, y - 1, z, itm);
      return;
    } 
    if (world.rand.nextInt(500) == 0) {
      itm = new ItemStack(Items.skull, 1, 1);
      MFQM.dropItem(world, x, y - 1, z, itm);
      return;
    } 
  }
  
  public boolean CanExt(World world, int x, int y, int z) {
    if (world.provider.dimensionId != -1)
      return false; 
    for (int i = 1; i <= 5; i++) {
      if (world.getBlock(x, y + i, z) != MFQM.MeatBlock)
        return false; 
      if (i < 5) {
        if (world.getBlock(x + 1, y + i, z) != MFQM.MeatBlock)
          return false; 
        if (world.getBlock(x - 1, y + i, z) != MFQM.MeatBlock)
          return false; 
        if (world.getBlock(x, y + i, z + 1) != MFQM.MeatBlock)
          return false; 
        if (world.getBlock(x, y + i, z - 1) != MFQM.MeatBlock)
          return false; 
      } 
    } 
    return true;
  }
  
  public void updateTick(World world, int x, int y, int z, Random rand) {
    int meta = world.getBlockMetadata(x, y, z);
    if (meta > 5 && meta < 10)
      checkMeatHole(world, x, y, z, meta); 
    if (meta == 10) {
      if (!world.isRemote && 
        rand.nextInt(25) == 0 && 
        validForSpread(world, x, y - 1, z) && 
        CanExt(world, x, y, z)) {
        world.setBlock(x, y - 1, z, MFQM.SlurryBlock, 0, 2);
        DropRandomThing(world, x, y, z);
        world.playSoundEffect(x + rand.nextFloat(), y - 0.5D, z + rand.nextFloat(), "game.player.swim", 0.25F, rand.nextFloat() * 0.2F + 0.2F);
        world.playSoundEffect(x + rand.nextFloat(), y - 0.5D, z + rand.nextFloat(), "mob.silverfish.step", 0.5F, rand.nextFloat() * 0.2F + 0.2F);
        world.playSoundEffect(x + rand.nextFloat(), y - 0.5D, z + rand.nextFloat(), "mob.silverfish.step", 0.5F, rand.nextFloat() * 0.2F + 0.2F);
        world.playSoundEffect(x + rand.nextFloat(), y - 0.5D, z + rand.nextFloat(), "mob.slime.attack", 0.5F, rand.nextFloat() * 0.2F + 0.2F);
        world.playSoundEffect(x + rand.nextFloat(), y - 0.5D, z + rand.nextFloat(), "mob.slime.attack", 0.5F, rand.nextFloat() * 0.2F + 0.2F);
      } 
      world.scheduleBlockUpdate(x, y, z, (Block)this, 100);
    } 
    super.updateTick(world, x, y, z, rand);
  }
  
  protected boolean canSilkHarvest() {
    return true;
  }
  
  public void onBlockAdded(World par1World, int par2, int par3, int par4) {
    int meta = par1World.getBlockMetadata(par2, par3, par4);
    if (meta > 5 && meta < 10)
      checkMeatHole(par1World, par2, par3, par4, meta); 
    if (meta == 10)
      par1World.scheduleBlockUpdate(par2, par3, par4, (Block)this, 100); 
  }
  
  public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5) {
    int meta = par1World.getBlockMetadata(par2, par3, par4);
    if (meta > 5 && meta < 10)
      checkMeatHole(par1World, par2, par3, par4, meta); 
    if (meta == 10)
      par1World.scheduleBlockUpdate(par2, par3, par4, (Block)this, 100); 
  }
  
  public void checkMeatHole(World world, int x, int y, int z, int meta) {
    int xx = x;
    int yy = y;
    int zz = z;
    switch (meta - 6) {
      case 0:
        xx = x;
        yy = y;
        zz = z - 1;
        break;
      case 1:
        xx = x;
        yy = y;
        zz = z + 1;
        break;
      case 2:
        xx = x - 1;
        yy = y;
        zz = z;
        break;
      case 3:
        xx = x + 1;
        yy = y;
        zz = z;
        break;
      default:
        return;
    } 
    if (world.getBlock(xx, yy, zz) != MFQM.MeatHoleBlock && (
      world.getBlock(xx, yy, zz).getMaterial().isLiquid() || world.getBlock(xx, yy, zz).getMaterial().isReplaceable()) && 
      world.getBlock(xx, yy, zz) != MFQM.AcidBlock)
      world.setBlock(xx, yy, zz, MFQM.MeatHoleBlock, 0, 2); 
  }
  
  public void harvestBlock(World world, EntityPlayer entityPlayer, int x, int y, int z, int par6) {
    if (EnchantmentHelper.getSilkTouchModifier((EntityLivingBase)entityPlayer)) {
      if (par6 != 10) {
        MFQM.dropItem(world, x, y, z, new ItemStack((Block)this, 1, par6));
      } else {
        MFQM.dropItem(world, x, y, z, new ItemStack((Block)this, 1, 0));
      } 
      world.markBlockForUpdate(x, y, z);
      return;
    } 
    super.harvestBlock(world, entityPlayer, x, y, z, par6);
  }
  
  public float getPlayerRelativeBlockHardness(EntityPlayer par1EntityPlayer, World par2World, int par3, int par4, int par5) {
    if (!((Entity)par1EntityPlayer).onGround)
      return -1.0F; 
    return (par2World.getBlockMetadata(par3, par4, par5) == 0) ? 0.083333336F : ForgeHooks.blockStrength((Block)this, par1EntityPlayer, par2World, par3, par4, par5);
  }
  
  public boolean canHarvestBlock(EntityPlayer player, int meta) {
    return true;
  }
  
  public Item getItemDropped(int par1, Random par2Random, int par3) {
    if (MFQM.BoP) {
      if (MFQM.FleshItemID != 0)
        return MFQM.FleshItem; 
      return Item.getItemFromBlock((Block)this);
    } 
    return Item.getItemFromBlock((Block)this);
  }
  
  public int quantityDropped(Random random) {
    if (MFQM.BoP)
      return random.nextInt(5); 
    return 1;
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister par1) {
    this.IIconMArray = new IIcon[11];
    this.IIconMArray[0] = par1.registerIcon("morefunquicksandmod:Meat0");
    this.IIconMArray[1] = par1.registerIcon("morefunquicksandmod:Meat1");
    this.IIconMArray[2] = par1.registerIcon("morefunquicksandmod:Meat2");
    this.IIconMArray[3] = par1.registerIcon("morefunquicksandmod:Meat3");
    this.IIconMArray[4] = par1.registerIcon("morefunquicksandmod:Meat4");
    this.IIconMArray[5] = par1.registerIcon("morefunquicksandmod:Meat5");
    this.IIconMArray[6] = par1.registerIcon("morefunquicksandmod:Meat6");
    this.IIconMArray[7] = par1.registerIcon("morefunquicksandmod:Meat7");
    this.IIconMArray[8] = par1.registerIcon("morefunquicksandmod:Meat8");
    this.IIconMArray[9] = par1.registerIcon("morefunquicksandmod:Meat9");
    this.IIconMArray[10] = par1.registerIcon("morefunquicksandmod:Meat11");
  }
  
  public int getDamageValue(World par1World, int par2, int par3, int par4) {
    return par1World.getBlockMetadata(par2, par3, par4);
  }
  
  public int damageDropped(int par1) {
    if (MFQM.BoP)
      return 3; 
    return 0;
  }
  
  @SideOnly(Side.CLIENT)
  public int getBlockColor() {
    if (MFQM.AOA && 
      (Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.AOADimAbyss)
      return 12664385; 
    return 16777215;
  }
  
  @SideOnly(Side.CLIENT)
  public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
    if (MFQM.AOA && 
      (Minecraft.getMinecraft()).thePlayer.worldObj.provider.dimensionId == MFQM.AOADimAbyss)
      return 12664385; 
    return 16777215;
  }
  
  @SideOnly(Side.CLIENT)
  public int getRenderColor(int par1) {
    return 16777215;
  }
  
  @SideOnly(Side.CLIENT)
  public void randomDisplayTick(World world, int x, int y, int z, Random random) {
    if (world.getBlockMetadata(x, y, z) == 0) {
      if (random.nextInt(4) == 0)
        world.spawnParticle("blockcrack_" + Block.getIdFromBlock((Block)this) + "_0", (x + random.nextFloat()), (y - 0.4F), (z + random.nextFloat()), 0.0D, 0.0D, 0.0D); 
      if (random.nextInt(12) == 0)
        world.spawnParticle("blockcrack_" + Block.getIdFromBlock((Block)this) + "_0", (x + random.nextFloat()), (y + 1.0F), (z + random.nextFloat()), 0.0D, 0.0D, 0.0D); 
    } 
  }
}
