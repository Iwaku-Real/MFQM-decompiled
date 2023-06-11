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
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class BlockTent extends BlockBush implements IShearable {
  @SideOnly(Side.CLIENT)
  private IIcon[] IIconArray;
  
  public BlockTent() {
    super(Material.vine);
    float f = 0.4F;
    float height = 0.8F;
    setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, height, 0.5F + f);
    setUnlocalizedName("Tendrils");
    setHardness(0.0F);
    setStepSound(soundTypeGrass);
    setTickRandomly(true);
    setCreativeTab(MFQM.tabMFQM);
  }
  
  public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List<ItemStack> list) {
    list.add(new ItemStack((Block)this, 1, 0));
    list.add(new ItemStack((Block)this, 1, 1));
    list.add(new ItemStack((Block)this, 1, 2));
    list.add(new ItemStack((Block)this, 1, 3));
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int par1, int par2) {
    return this.IIconArray[Math.min(par2, 3)];
  }
  
  public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
    entity.setInWeb();
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
    this.IIconArray = new IIcon[4];
    this.IIconArray[0] = par1IIconRegister.registerIcon("morefunquicksandmod:Tent0");
    this.IIconArray[1] = par1IIconRegister.registerIcon("morefunquicksandmod:Tent1");
    this.IIconArray[2] = par1IIconRegister.registerIcon("morefunquicksandmod:Tent2");
    this.IIconArray[3] = par1IIconRegister.registerIcon("morefunquicksandmod:Tent3");
  }
  
  public boolean canBlockStay(World world, int x, int y, int z) {
    if (world.getBlock(x, y - 1, z).getMaterial().isSolid()) {
      if (world.getBlock(x, y - 1, z) == MFQM.MeatBlock)
        return true; 
      if (world.getBlock(x, y - 1, z) == MFQM.FleshBlock)
        return true; 
      if (world.getBlock(x, y - 1, z) == MFQM.SwFleshBlock)
        return true; 
    } 
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
  
  public void breakBlock(World world, int x, int y, int z, Block id, int meta) {
    if (this != world.getBlock(x, y, z))
      world.playSoundEffect((x + world.rand.nextFloat()), (y + world.rand.nextFloat()), (z + world.rand.nextFloat()), "mob.silverfish.kill", 0.75F, world.rand.nextFloat() * 0.25F + 1.75F); 
    super.breakBlock(world, x, y, z, id, meta);
  }
}
