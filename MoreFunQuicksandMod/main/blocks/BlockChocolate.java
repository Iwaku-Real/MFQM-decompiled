package MoreFunQuicksandMod.main.blocks;

import MoreFunQuicksandMod.main.MFQM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockChocolate extends Block {
  IIcon theQIIcon;
  
  public BlockChocolate() {
    super(Material.rock);
    setHardness(0.85F);
    setStepSound(Block.soundTypeStone);
    setResistance(1.0F);
    setUnlocalizedName("ChocolateBlock");
    setCreativeTab(MFQM.tabMFQM);
  }
  
  public void updateTick(World world, int x, int y, int z, Random rand) {
    int xx = x;
    int yy = y;
    int zz = z;
    boolean isLavaNear = false;
    boolean isFireNear = false;
    int hot = 0;
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
      if (world.getBlock(xx, yy, zz).getMaterial() == Material.fire) {
        isFireNear = true;
        hot++;
      } 
    } 
    if (isLavaNear) {
      world.playSoundEffect((x + 0.5F), (y + 0.5F), (z + 0.5F), "mob.silverfish.step", 0.25F, world.rand.nextFloat() * 0.15F + 0.1F);
      world.setBlock(x, y, z, MFQM.LiquidChocolateBlock, 0, 3);
      return;
    } 
    if (hot > 0 && 
      world.getBlockMetadata(x, y, z) == 0 && 
      world.rand.nextInt(Math.max(15 - hot, 1)) == 0) {
      world.setBlock(x, y, z, MFQM.LiquidChocolateBlock, 0, 3);
      return;
    } 
    super.updateTick(world, x, y, z, rand);
  }
  
  public void onBlockAdded(World par1World, int par2, int par3, int par4) {
    par1World.scheduleBlockUpdate(par2, par3, par4, this, tickRate(par1World));
  }
  
  public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5) {
    par1World.scheduleBlockUpdate(par2, par3, par4, this, tickRate(par1World));
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int par1, int par2) {
    return this.theQIIcon;
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister par1IIconRegister) {
    this.theQIIcon = par1IIconRegister.registerIcon("morefunquicksandmod:Chocolate");
  }
}
