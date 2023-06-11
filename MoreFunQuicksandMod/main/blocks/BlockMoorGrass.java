package MoreFunQuicksandMod.main.blocks;

import MoreFunQuicksandMod.main.MFQM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class BlockMoorGrass extends BlockBush implements IShearable {
  @SideOnly(Side.CLIENT)
  private IIcon[] IIconArray;
  
  public BlockMoorGrass() {
    super(Material.vine);
    float f = 0.4F;
    float height = 0.8F;
    setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, height, 0.5F + f);
    setUnlocalizedName("MoorGrass");
    setHardness(0.0F);
    setStepSound(soundTypeGrass);
    setTickRandomly(true);
  }
  
  public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
    int j = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
    float f = 0.4F;
    float height = 0.8F;
    switch (j) {
      case 3:
      case 4:
      case 5:
        height = 0.25F;
        break;
    } 
    setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, height, 0.5F + f);
  }
  
  public void updateTick(World world, int x, int y, int z, Random rand) {
    if (world.getBlockMetadata(x, y, z) == 4 && 
      world.rand.nextInt(10) == 0 && (
      world.getBlock(x, y - 1, z) == MFQM.MorassBlock || world.getBlock(x, y - 1, z) == MFQM.BogBlock))
      world.setBlock(x, y, z, (Block)this, 5, 3); 
    super.updateTick(world, x, y, z, rand);
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int par1, int par2) {
    switch (par2) {
      case 3:
        return this.IIconArray[1];
      case 4:
        return this.IIconArray[1];
      case 5:
        return this.IIconArray[2];
    } 
    return this.IIconArray[0];
  }
  
  public Item getItemDropped(int par1, Random par2Random, int par3) {
    if (par1 < 3)
      return (par2Random.nextInt(8) == 0) ? Items.wheat_seeds : null; 
    if (par1 == 5)
      return MFQM.CberryItem; 
    return null;
  }
  
  public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
    if (par1World.isRemote)
      return true; 
    if (par1World.getBlockMetadata(par2, par3, par4) == 5) {
      par1World.setBlock(par2, par3, par4, (Block)this, 4, 3);
      MFQM.dropItem(par1World, par2, par3, par4, new ItemStack(MFQM.CberryItem, quantityDropped(par1World.rand)));
    } 
    return true;
  }
  
  public int quantityDropped(Random par1Random) {
    return 2 + par1Random.nextInt(4);
  }
  
  public int quantityDropped(int meta, int fortune, Random par1Random) {
    if (meta == 5)
      return quantityDropped(par1Random); 
    return 1 + par1Random.nextInt(fortune * 2 + 1);
  }
  
  public int quantityDroppedWithBonus(int par1, Random par2Random) {
    return 1 + par2Random.nextInt(par1 * 2 + 1);
  }
  
  public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6) {
    super.harvestBlock(par1World, par2EntityPlayer, par3, par4, par5, par6);
  }
  
  @SideOnly(Side.CLIENT)
  public int getBlockColor() {
    double d0 = 0.5D;
    double d1 = 1.0D;
    return ColorizerGrass.getGrassColor(d0, d1);
  }
  
  @SideOnly(Side.CLIENT)
  public int getRenderColor(int par1) {
    switch (par1) {
      case 0:
        return 10536285;
      case 2:
        return 10536285;
      case 3:
        return 16777215;
      case 4:
        return 16777215;
      case 5:
        return 16777215;
    } 
    return ColorizerFoliage.getFoliageColorBasic();
  }
  
  @SideOnly(Side.CLIENT)
  public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
    int l = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
    switch (l) {
      case 0:
        return 10536285;
      case 2:
        return 10536285;
      case 3:
        return 16777215;
      case 4:
        return 16777215;
      case 5:
        return 16777215;
    } 
    return par1IBlockAccess.getBiomeGenForCoords(par2, par4).getBiomeGrassColor(par2, par3, par4);
  }
  
  public int getDamageValue(World par1World, int par2, int par3, int par4) {
    return par1World.getBlockMetadata(par2, par3, par4);
  }
  
  @SideOnly(Side.CLIENT)
  public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List<ItemStack> par3List) {
    par3List.add(new ItemStack((Block)this, 1, 0));
    par3List.add(new ItemStack((Block)this, 1, 1));
    par3List.add(new ItemStack((Block)this, 1, 2));
    par3List.add(new ItemStack((Block)this, 1, 3));
    par3List.add(new ItemStack((Block)this, 1, 4));
    par3List.add(new ItemStack((Block)this, 1, 5));
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister par1IIconRegister) {
    this.IIconArray = new IIcon[3];
    this.IIconArray[0] = par1IIconRegister.registerIcon("tallgrass");
    this.IIconArray[1] = par1IIconRegister.registerIcon("morefunquicksandmod:bog_grass");
    this.IIconArray[2] = par1IIconRegister.registerIcon("morefunquicksandmod:bog_grass_cberry");
  }
  
  public boolean canBlockStay(World world, int x, int y, int z) {
    if (world.getBlock(x, y - 1, z) == MFQM.WetPeatBlock || world
      .getBlock(x, y - 1, z) == MFQM.MireBlock)
      return false; 
    if (world.getBlock(x, y - 1, z).getMaterial().isSolid() || world.getBlock(x, y - 1, z).getMaterial().isLiquid())
      return true; 
    return false;
  }
  
  protected boolean canThisPlantGrowOnThisBlock(Block par1) {
    if (par1 == MFQM.WetPeatBlock || par1 == MFQM.MireBlock)
      return false; 
    if (par1 != Blocks.air)
      return true; 
    return false;
  }
  
  public boolean canDropFromExplosion(Explosion par1Explosion) {
    return false;
  }
  
  public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
    return true;
  }
  
  public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
    ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
    int meta = world.getBlockMetadata(x, y, z);
    if (meta == 5)
      meta = 4; 
    ret.add(new ItemStack((Block)this, 1, meta));
    return ret;
  }
}
