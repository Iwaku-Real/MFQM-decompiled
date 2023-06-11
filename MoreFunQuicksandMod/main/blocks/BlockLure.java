package MoreFunQuicksandMod.main.blocks;

import MoreFunQuicksandMod.main.MFQM;
import MoreFunQuicksandMod.main.Tileentity.TileEntityLure;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockLure extends BlockContainer {
  public IIcon theCSIIcon;
  
  public BlockLure() {
    super(Material.sponge);
    setHardness(1.75F);
    setStepSound(Block.soundTypeGravel);
    setUnlocalizedName("LureBlock");
    setCreativeTab(MFQM.tabMFQM);
  }
  
  public TileEntity createNewTileEntity(World world, int i) {
    if (!world.isRemote) {
      TileEntityLure TE = new TileEntityLure();
      return (TileEntity)TE;
    } 
    return null;
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int par1, int par2) {
    return this.theCSIIcon;
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister par1IIconRegister) {
    this.theCSIIcon = par1IIconRegister.registerIcon("morefunquicksandmod:Blossom6");
  }
  
  @SideOnly(Side.CLIENT)
  public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
    if (par1World.rand.nextInt(2) == 0) {
      double d0 = par2 + par5Random.nextFloat() * 2.0D - 1.0D;
      double d1 = par3 + par5Random.nextFloat() * 2.0D - 1.0D;
      double d2 = par4 + par5Random.nextFloat() * 2.0D - 1.0D;
      double d3 = 0.913D;
      double d4 = 0.737D;
      double d5 = 0.996D;
      par1World.spawnParticle("reddust", d0 + 0.5D, d1 + 0.5D, d2 + 0.5D, d3, d4, d5);
    } 
  }
}
