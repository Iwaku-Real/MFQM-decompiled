package MoreFunQuicksandMod.main.blocks;

import MoreFunQuicksandMod.main.GenerateMucusBlossom;
import MoreFunQuicksandMod.main.MFQM;
import MoreFunQuicksandMod.main.Tileentity.TileEntityBlossom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockBlossom extends BlockContainer {
  @SideOnly(Side.CLIENT)
  private IIcon[] IIconMArray;
  
  public BlockBlossom() {
    super(Material.sponge);
    setHardness(1.8F);
    setStepSound(Block.soundTypeGrass);
    setUnlocalizedName("BlossomBlock");
    setTickRandomly(true);
    setCreativeTab(MFQM.tabMFQM);
  }
  
  public TileEntity createNewTileEntity(World world, int i) {
    if (i > 5 && i < 10) {
      if (world.isRemote) {
        TileEntityBlossom TE = new TileEntityBlossom();
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
  
  public boolean isOpaqueCube() {
    return true;
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
    list.add(new ItemStack(block, 1, 11));
  }
  
  public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
    int meta = world.getBlockMetadata(x, y, z);
    entity.motionX *= 0.9D;
    entity.motionZ *= 0.9D;
  }
  
  public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int par2, int par3, int par4) {
    float f = 0.125F;
    return AxisAlignedBB.getBoundingBox(par2, par3, par4, (par2 + 1), ((par3 + 1) - f), (par4 + 1));
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int par1, int par2) {
    switch (par2) {
      case 0:
      case 11:
        if (par1 == 1 || par1 == 0)
          return this.IIconMArray[0]; 
        return this.IIconMArray[1];
      case 1:
        if (par1 == 1 || par1 == 0)
          return this.IIconMArray[2]; 
        return this.IIconMArray[3];
      case 2:
      case 6:
        if (par1 == 1 || par1 == 0)
          return this.IIconMArray[2]; 
        switch (par1) {
          case 4:
            return this.IIconMArray[3];
          case 5:
            return this.IIconMArray[3];
          case 2:
            return this.IIconMArray[1];
          case 3:
            return this.IIconMArray[3];
        } 
      case 3:
      case 7:
        if (par1 == 1 || par1 == 0)
          return this.IIconMArray[2]; 
        switch (par1) {
          case 4:
            return this.IIconMArray[3];
          case 5:
            return this.IIconMArray[1];
          case 2:
            return this.IIconMArray[3];
          case 3:
            return this.IIconMArray[3];
        } 
      case 4:
      case 8:
        if (par1 == 1 || par1 == 0)
          return this.IIconMArray[2]; 
        switch (par1) {
          case 4:
            return this.IIconMArray[3];
          case 5:
            return this.IIconMArray[3];
          case 2:
            return this.IIconMArray[3];
          case 3:
            return this.IIconMArray[1];
        } 
      case 5:
      case 9:
        if (par1 == 1 || par1 == 0)
          return this.IIconMArray[2]; 
        switch (par1) {
          case 4:
            return this.IIconMArray[1];
          case 5:
            return this.IIconMArray[3];
          case 2:
            return this.IIconMArray[3];
          case 3:
            return this.IIconMArray[3];
        } 
        break;
    } 
    return this.IIconMArray[4];
  }
  
  public void updateTick(World world, int x, int y, int z, Random rand) {
    int meta = world.getBlockMetadata(x, y, z);
    if (meta > 5 && meta < 10)
      checkVoreHole(world, x, y, z, meta); 
    if (meta == 11) {
      if (world.rand.nextInt(32) == 0 && 
        GenerateMucusBlossom.checkCanGrow(world, x, y + 1, z, false)) {
        (new GenerateMucusBlossom()).generate(world, world.rand, x - 3, y + 2 - 8, z - 3);
        world.playSoundEffect(x, y, z, "mob.zombie.woodbreak", 0.5F, world.rand.nextFloat() * 0.1F + 0.1F);
        world.playSoundEffect(x, y, z, "mob.zombie.woodbreak", 0.5F, world.rand.nextFloat() * 0.1F + 0.1F);
      } 
      world.scheduleBlockUpdate(x, y, z, (Block)this, tickRate(world) * 32);
    } 
    super.updateTick(world, x, y, z, rand);
  }
  
  protected boolean canSilkHarvest() {
    return true;
  }
  
  public void onBlockAdded(World par1World, int par2, int par3, int par4) {
    int meta = par1World.getBlockMetadata(par2, par3, par4);
    if (meta > 5 && meta < 10)
      checkVoreHole(par1World, par2, par3, par4, meta); 
    if (meta == 11)
      par1World.scheduleBlockUpdate(par2, par3, par4, (Block)this, tickRate(par1World)); 
  }
  
  public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5) {
    int meta = par1World.getBlockMetadata(par2, par3, par4);
    if (meta > 5 && meta < 10)
      checkVoreHole(par1World, par2, par3, par4, meta); 
    if (meta == 11)
      par1World.scheduleBlockUpdate(par2, par3, par4, (Block)this, tickRate(par1World)); 
  }
  
  public void checkVoreHole(World world, int x, int y, int z, int meta) {
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
        xx = x + 1;
        yy = y;
        zz = z;
        break;
      case 2:
        xx = x;
        yy = y;
        zz = z + 1;
        break;
      case 3:
        xx = x - 1;
        yy = y;
        zz = z;
        break;
      default:
        return;
    } 
    if (world.getBlock(xx, yy, zz) != MFQM.VoreHoleBlock && (
      world.getBlock(xx, yy, zz).getMaterial().isLiquid() || world.getBlock(xx, yy, zz).getMaterial().isReplaceable()))
      world.setBlock(xx, yy, zz, MFQM.VoreHoleBlock, 0, 2); 
  }
  
  public void harvestBlock(World world, EntityPlayer entityPlayer, int x, int y, int z, int meta) {
    if (meta != 11 && 
      EnchantmentHelper.getSilkTouchModifier((EntityLivingBase)entityPlayer)) {
      MFQM.dropItem(world, x, y, z, new ItemStack((Block)this, 1, meta));
      world.markBlockForUpdate(x, y, z);
      return;
    } 
    super.harvestBlock(world, entityPlayer, x, y, z, meta);
  }
  
  public float getPlayerRelativeBlockHardness(EntityPlayer par1EntityPlayer, World par2World, int par3, int par4, int par5) {
    if (!((Entity)par1EntityPlayer).onGround)
      return -1.0F; 
    float f = getBlockHardness(par2World, par3, par4, par5);
    return ForgeHooks.blockStrength((Block)this, par1EntityPlayer, par2World, par3, par4, par5);
  }
  
  public boolean canHarvestBlock(EntityPlayer player, int meta) {
    return true;
  }
  
  public Item getItemDropped(int par1, Random par2Random, int par3) {
    return null;
  }
  
  public int quantityDropped(Random random) {
    return 1;
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister par1) {
    this.IIconMArray = new IIcon[5];
    this.IIconMArray[0] = par1.registerIcon("morefunquicksandmod:Blossom3");
    this.IIconMArray[1] = par1.registerIcon("morefunquicksandmod:Blossom2");
    this.IIconMArray[2] = par1.registerIcon("morefunquicksandmod:Blossom5");
    this.IIconMArray[3] = par1.registerIcon("morefunquicksandmod:Blossom4");
    this.IIconMArray[4] = par1.registerIcon("morefunquicksandmod:Blossom0");
  }
  
  public int getDamageValue(World par1World, int par2, int par3, int par4) {
    return par1World.getBlockMetadata(par2, par3, par4);
  }
}
