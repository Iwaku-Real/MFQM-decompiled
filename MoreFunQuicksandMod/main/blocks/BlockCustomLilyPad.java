package MoreFunQuicksandMod.main.blocks;

import MoreFunQuicksandMod.main.MFQM;
import java.util.Random;
import net.minecraft.block.BlockLilyPad;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockCustomLilyPad extends BlockLilyPad {
  public BlockCustomLilyPad() {
    setHardness(0.0F);
    setStepSound(soundTypeGrass);
    setUnlocalizedName("CustomLilyPad");
    setTextureName("waterlily");
  }
  
  public boolean canBlockStay(World par1World, int par2, int par3, int par4) {
    return (par3 >= 0 && par3 < 256) ? (((par1World.getBlock(par2, par3 - 1, par4).getMaterial() == Material.water || par1World.getBlock(par2, par3 - 1, par4) == MFQM.BogBlock || par1World.getBlock(par2, par3 - 1, par4) == MFQM.StableLiquidMireBlock) && par1World.getBlockMetadata(par2, par3 - 1, par4) == 0)) : false;
  }
  
  public Item getItemDropped(int par1, Random random, int par3) {
    return null;
  }
  
  public void harvestBlock(World world, EntityPlayer entityPlayer, int x, int y, int z, int par6) {}
  
  public boolean canDropFromExplosion(Explosion par1Explosion) {
    return false;
  }
}
