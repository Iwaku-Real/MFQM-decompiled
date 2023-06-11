package MoreFunQuicksandMod.main.blocks;

import MoreFunQuicksandMod.main.MFQM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class BlockLeaves extends BlockBush implements IShearable {
  @SideOnly(Side.CLIENT)
  private IIcon ic1;
  
  public BlockLeaves() {
    super(Material.plants);
    float var1 = 0.5F;
    float var2 = 0.015625F;
    setBlockBounds(0.5F - var1, 0.0F, 0.5F - var1, 0.5F + var1, var2, 0.5F + var1);
    setUnlocalizedName("LeavesPile");
    setHardness(0.0F);
    setStepSound(soundTypeGrass);
    setTickRandomly(true);
    setCreativeTab(MFQM.tabMFQM);
  }
  
  public int getRenderType() {
    return 23;
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int par1, int par2) {
    return this.ic1;
  }
  
  public Item getItemDropped(int par1, Random par2Random, int par3) {
    return null;
  }
  
  public int quantityDropped(Random par1Random) {
    return 0;
  }
  
  public int quantityDropped(int meta, int fortune, Random par1Random) {
    return 0;
  }
  
  public int quantityDroppedWithBonus(int par1, Random par2Random) {
    return 0;
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister par1IIconRegister) {
    this.ic1 = par1IIconRegister.registerIcon("morefunquicksandmod:Leaves");
  }
  
  public boolean canBlockStay(World world, int x, int y, int z) {
    if (world.getBlock(x, y - 1, z).getMaterial().isSolid() || world.getBlock(x, y - 1, z).getMaterial().isLiquid())
      return true; 
    return false;
  }
  
  public void onBlockAdded(World par1World, int par2, int par3, int par4) {
    par1World.scheduleBlockUpdate(par2, par3, par4, (Block)this, tickRate(par1World));
  }
  
  public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5) {
    if (!canBlockStay(par1World, par2, par3, par4))
      par1World.setBlockToAir(par2, par3, par4); 
  }
  
  protected boolean canThisPlantGrowOnThisBlock(Block par1) {
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
    ret.add(new ItemStack((Block)this, 1, meta));
    return ret;
  }
}
