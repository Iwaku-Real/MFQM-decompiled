package MoreFunQuicksandMod.main;

import cpw.mods.fml.common.IWorldGenerator;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;

public class GenerateMucusBlossom extends WorldGenerator implements IWorldGenerator {
  private static boolean validMaterial(int x, int y, int z, World world, boolean dirt) {
    if (!dirt) {
      if (world.getBlock(x, y, z) == null)
        return true; 
      if (world.getBlock(x, y, z) == Blocks.air)
        return true; 
      if (world.getBlock(x, y, z).getMaterial().isLiquid() || world.getBlock(x, y, z).getMaterial().isReplaceable())
        return true; 
      if (world.getBlock(x, y, z).getMaterial() == Material.vine || world
        .getBlock(x, y, z).getMaterial() == Material.plants || world
        .getBlock(x, y, z).getMaterial() == Material.leaves)
        return true; 
    } 
    if (world.getBlock(x, y, z) == MFQM.BlossomBlock)
      return true; 
    if (world.getBlock(x, y, z) == MFQM.BlossomSlabBlock)
      return true; 
    if (world.getBlock(x, y, z).getMaterial() == Material.grass || world
      .getBlock(x, y, z).getMaterial() == Material.ground || world
      .getBlock(x, y, z).getMaterial() == Material.sand)
      return true; 
    return false;
  }
  
  private static boolean genCheckVolume(int x, int y, int z, int r, World world, boolean force, boolean dirt) {
    if (force)
      return true; 
    if (validMaterial(x, y, z, world, dirt) && 
      validMaterial(x + r, y, z + r, world, dirt) && 
      validMaterial(x - r, y, z + r, world, dirt) && 
      validMaterial(x + r, y, z - r, world, dirt) && 
      validMaterial(x - r, y, z - r, world, dirt) && 
      validMaterial(x - r, y, z, world, dirt) && 
      validMaterial(x, y, z - r, world, dirt) && 
      validMaterial(x + r, y, z, world, dirt) && 
      validMaterial(x, y, z + r, world, dirt))
      return true; 
    return false;
  }
  
  public static boolean checkCanGrow(World world, int x, int y, int z, boolean force) {
    boolean dirt = false;
    for (int i = 0; i < 9; i++) {
      if (i > 3)
        dirt = true; 
      if (!genCheckVolume(x, y - i + 1, z, 3, world, force, dirt))
        return false; 
    } 
    return true;
  }
  
  public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {}
  
  public void setBlock(World world, int x, int y, int z, Block block, int metadata) {
    world.setBlock(x, y, z, block, metadata, 2);
  }
  
  public void collumnBlocks(World world, int x, int y, int z, int col, Block block, int metadata) {
    for (int i = 0; i < col; i++)
      world.setBlock(x, y - i, z, block, metadata, 2); 
  }
  
  public boolean generate(World world, Random rand, int i, int j, int k) {
    setBlock(world, i + 1, j + 6, k + 2, MFQM.BlossomBlock, 0);
    setBlock(world, i + 1, j + 6, k + 4, MFQM.BlossomBlock, 0);
    setBlock(world, i + 2, j + 6, k + 1, MFQM.BlossomBlock, 0);
    setBlock(world, i + 2, j + 6, k + 5, MFQM.BlossomBlock, 0);
    setBlock(world, i + 4, j + 6, k + 1, MFQM.BlossomBlock, 0);
    setBlock(world, i + 4, j + 6, k + 5, MFQM.BlossomBlock, 0);
    setBlock(world, i + 5, j + 6, k + 2, MFQM.BlossomBlock, 0);
    setBlock(world, i + 5, j + 6, k + 4, MFQM.BlossomBlock, 0);
    setBlock(world, i + 5, j + 6, k + 3, MFQM.BlossomBlock, 0);
    setBlock(world, i + 3, j + 6, k + 5, MFQM.BlossomBlock, 0);
    setBlock(world, i + 3, j + 6, k + 1, MFQM.BlossomBlock, 0);
    setBlock(world, i + 1, j + 6, k + 3, MFQM.BlossomBlock, 0);
    setBlock(world, i + 4, j + 6, k + 4, MFQM.LureBlock, 0);
    setBlock(world, i + 2, j + 6, k + 2, MFQM.LureBlock, 0);
    setBlock(world, i + 2, j + 6, k + 4, MFQM.LureBlock, 0);
    setBlock(world, i + 4, j + 6, k + 2, MFQM.LureBlock, 0);
    setBlock(world, i + 2, j + 2, k + 2, MFQM.BlossomBlock, 0);
    setBlock(world, i + 2, j + 2, k + 3, MFQM.BlossomBlock, 0);
    setBlock(world, i + 2, j + 2, k + 4, MFQM.BlossomBlock, 0);
    setBlock(world, i + 3, j + 2, k + 2, MFQM.BlossomBlock, 0);
    setBlock(world, i + 3, j + 2, k + 3, MFQM.BlossomBlock, 0);
    setBlock(world, i + 3, j + 2, k + 4, MFQM.BlossomBlock, 0);
    setBlock(world, i + 4, j + 2, k + 2, MFQM.BlossomBlock, 0);
    setBlock(world, i + 4, j + 2, k + 3, MFQM.BlossomBlock, 0);
    setBlock(world, i + 4, j + 2, k + 4, MFQM.BlossomBlock, 0);
    setBlock(world, i + 3, j + 0, k + 3, MFQM.BlossomBlock, 1);
    setBlock(world, i + 2, j + 1, k + 2, MFQM.BlossomBlock, 1);
    setBlock(world, i + 2, j + 1, k + 3, MFQM.BlossomBlock, 1);
    setBlock(world, i + 2, j + 1, k + 4, MFQM.BlossomBlock, 1);
    setBlock(world, i + 3, j + 1, k + 2, MFQM.BlossomBlock, 1);
    setBlock(world, i + 3, j + 1, k + 3, MFQM.BlossomBlock, 1);
    setBlock(world, i + 3, j + 1, k + 4, MFQM.BlossomBlock, 1);
    setBlock(world, i + 4, j + 1, k + 2, MFQM.BlossomBlock, 1);
    setBlock(world, i + 4, j + 1, k + 3, MFQM.BlossomBlock, 1);
    setBlock(world, i + 4, j + 1, k + 4, MFQM.BlossomBlock, 1);
    collumnBlocks(world, i + 3, j + 5, k + 5, 4, MFQM.BlossomBlock, 2);
    collumnBlocks(world, i + 2, j + 5, k + 5, 4, MFQM.BlossomBlock, 2);
    collumnBlocks(world, i + 4, j + 5, k + 5, 4, MFQM.BlossomBlock, 2);
    collumnBlocks(world, i + 5, j + 5, k + 4, 4, MFQM.BlossomBlock, 5);
    collumnBlocks(world, i + 5, j + 5, k + 2, 4, MFQM.BlossomBlock, 5);
    collumnBlocks(world, i + 5, j + 5, k + 3, 4, MFQM.BlossomBlock, 5);
    collumnBlocks(world, i + 4, j + 5, k + 1, 4, MFQM.BlossomBlock, 4);
    collumnBlocks(world, i + 3, j + 5, k + 1, 4, MFQM.BlossomBlock, 4);
    collumnBlocks(world, i + 2, j + 5, k + 1, 4, MFQM.BlossomBlock, 4);
    collumnBlocks(world, i + 1, j + 5, k + 2, 4, MFQM.BlossomBlock, 3);
    collumnBlocks(world, i + 1, j + 5, k + 3, 4, MFQM.BlossomBlock, 3);
    collumnBlocks(world, i + 1, j + 5, k + 4, 4, MFQM.BlossomBlock, 3);
    setBlock(world, i + 2, j + 5, k + 2, Blocks.air, 0);
    setBlock(world, i + 2, j + 5, k + 3, Blocks.air, 0);
    setBlock(world, i + 2, j + 5, k + 4, Blocks.air, 0);
    setBlock(world, i + 3, j + 5, k + 2, Blocks.air, 0);
    setBlock(world, i + 3, j + 5, k + 3, Blocks.air, 0);
    setBlock(world, i + 3, j + 5, k + 4, Blocks.air, 0);
    setBlock(world, i + 3, j + 6, k + 3, Blocks.air, 0);
    setBlock(world, i + 3, j + 7, k + 3, Blocks.air, 0);
    setBlock(world, i + 3, j + 8, k + 3, Blocks.air, 0);
    setBlock(world, i + 4, j + 5, k + 2, Blocks.air, 0);
    setBlock(world, i + 4, j + 5, k + 3, Blocks.air, 0);
    setBlock(world, i + 4, j + 5, k + 4, Blocks.air, 0);
    setBlock(world, i + 2, j + 8, k + 3, MFQM.BlossomSlabBlock, 2);
    setBlock(world, i + 3, j + 8, k + 2, MFQM.BlossomSlabBlock, 3);
    setBlock(world, i + 3, j + 8, k + 4, MFQM.BlossomSlabBlock, 1);
    setBlock(world, i + 4, j + 8, k + 3, MFQM.BlossomSlabBlock, 4);
    setBlock(world, i + 2, j + 6, k + 3, MFQM.BlossomSlabBlock, 0);
    setBlock(world, i + 3, j + 6, k + 2, MFQM.BlossomSlabBlock, 0);
    setBlock(world, i + 4, j + 6, k + 3, MFQM.BlossomSlabBlock, 0);
    setBlock(world, i + 3, j + 6, k + 4, MFQM.BlossomSlabBlock, 0);
    setBlock(world, i + 2, j + 7, k + 3, MFQM.BlossomBlock, 7);
    setBlock(world, i + 3, j + 7, k + 2, MFQM.BlossomBlock, 8);
    setBlock(world, i + 4, j + 7, k + 3, MFQM.BlossomBlock, 9);
    setBlock(world, i + 3, j + 7, k + 4, MFQM.BlossomBlock, 6);
    setBlock(world, i + 0, j + 6, k + 2, MFQM.BlossomBlock, 10);
    setBlock(world, i + 0, j + 6, k + 3, MFQM.BlossomBlock, 10);
    setBlock(world, i + 0, j + 6, k + 4, MFQM.BlossomBlock, 10);
    setBlock(world, i + 0, j + 7, k + 1, MFQM.BlossomBlock, 10);
    setBlock(world, i + 0, j + 7, k + 2, MFQM.BlossomBlock, 10);
    setBlock(world, i + 0, j + 7, k + 3, MFQM.BlossomBlock, 10);
    setBlock(world, i + 0, j + 7, k + 4, MFQM.BlossomBlock, 10);
    setBlock(world, i + 0, j + 7, k + 5, MFQM.BlossomBlock, 10);
    setBlock(world, i + 1, j + 6, k + 1, MFQM.BlossomBlock, 10);
    setBlock(world, i + 1, j + 6, k + 5, MFQM.BlossomBlock, 10);
    setBlock(world, i + 1, j + 7, k + 0, MFQM.BlossomBlock, 10);
    setBlock(world, i + 1, j + 7, k + 1, MFQM.BlossomBlock, 10);
    setBlock(world, i + 1, j + 7, k + 2, MFQM.BlossomBlock, 10);
    setBlock(world, i + 1, j + 7, k + 3, MFQM.BlossomBlock, 10);
    setBlock(world, i + 1, j + 7, k + 4, MFQM.BlossomBlock, 10);
    setBlock(world, i + 1, j + 7, k + 5, MFQM.BlossomBlock, 10);
    setBlock(world, i + 1, j + 7, k + 6, MFQM.BlossomBlock, 10);
    setBlock(world, i + 1, j + 8, k + 2, MFQM.BlossomBlock, 10);
    setBlock(world, i + 1, j + 8, k + 3, MFQM.BlossomBlock, 10);
    setBlock(world, i + 1, j + 8, k + 4, MFQM.BlossomBlock, 10);
    setBlock(world, i + 2, j + 6, k + 0, MFQM.BlossomBlock, 10);
    setBlock(world, i + 2, j + 6, k + 6, MFQM.BlossomBlock, 10);
    setBlock(world, i + 2, j + 7, k + 0, MFQM.BlossomBlock, 10);
    setBlock(world, i + 2, j + 7, k + 1, MFQM.BlossomBlock, 10);
    setBlock(world, i + 2, j + 7, k + 2, MFQM.BlossomBlock, 10);
    setBlock(world, i + 2, j + 7, k + 4, MFQM.BlossomBlock, 10);
    setBlock(world, i + 2, j + 7, k + 5, MFQM.BlossomBlock, 10);
    setBlock(world, i + 2, j + 7, k + 6, MFQM.BlossomBlock, 10);
    setBlock(world, i + 2, j + 8, k + 1, MFQM.BlossomBlock, 10);
    setBlock(world, i + 2, j + 8, k + 2, MFQM.BlossomBlock, 10);
    setBlock(world, i + 2, j + 8, k + 4, MFQM.BlossomBlock, 10);
    setBlock(world, i + 2, j + 8, k + 5, MFQM.BlossomBlock, 10);
    setBlock(world, i + 3, j + 6, k + 0, MFQM.BlossomBlock, 10);
    setBlock(world, i + 3, j + 6, k + 6, MFQM.BlossomBlock, 10);
    setBlock(world, i + 3, j + 7, k + 0, MFQM.BlossomBlock, 10);
    setBlock(world, i + 3, j + 7, k + 1, MFQM.BlossomBlock, 10);
    setBlock(world, i + 3, j + 7, k + 5, MFQM.BlossomBlock, 10);
    setBlock(world, i + 3, j + 7, k + 6, MFQM.BlossomBlock, 10);
    setBlock(world, i + 3, j + 8, k + 1, MFQM.BlossomBlock, 10);
    setBlock(world, i + 3, j + 8, k + 5, MFQM.BlossomBlock, 10);
    setBlock(world, i + 4, j + 6, k + 0, MFQM.BlossomBlock, 10);
    setBlock(world, i + 4, j + 6, k + 6, MFQM.BlossomBlock, 10);
    setBlock(world, i + 4, j + 7, k + 0, MFQM.BlossomBlock, 10);
    setBlock(world, i + 4, j + 7, k + 1, MFQM.BlossomBlock, 10);
    setBlock(world, i + 4, j + 7, k + 2, MFQM.BlossomBlock, 10);
    setBlock(world, i + 4, j + 7, k + 4, MFQM.BlossomBlock, 10);
    setBlock(world, i + 4, j + 7, k + 5, MFQM.BlossomBlock, 10);
    setBlock(world, i + 4, j + 7, k + 6, MFQM.BlossomBlock, 10);
    setBlock(world, i + 4, j + 8, k + 1, MFQM.BlossomBlock, 10);
    setBlock(world, i + 4, j + 8, k + 2, MFQM.BlossomBlock, 10);
    setBlock(world, i + 4, j + 8, k + 4, MFQM.BlossomBlock, 10);
    setBlock(world, i + 4, j + 8, k + 5, MFQM.BlossomBlock, 10);
    setBlock(world, i + 5, j + 6, k + 1, MFQM.BlossomBlock, 10);
    setBlock(world, i + 5, j + 6, k + 5, MFQM.BlossomBlock, 10);
    setBlock(world, i + 5, j + 7, k + 0, MFQM.BlossomBlock, 10);
    setBlock(world, i + 5, j + 7, k + 1, MFQM.BlossomBlock, 10);
    setBlock(world, i + 5, j + 7, k + 2, MFQM.BlossomBlock, 10);
    setBlock(world, i + 5, j + 7, k + 3, MFQM.BlossomBlock, 10);
    setBlock(world, i + 5, j + 7, k + 4, MFQM.BlossomBlock, 10);
    setBlock(world, i + 5, j + 7, k + 5, MFQM.BlossomBlock, 10);
    setBlock(world, i + 5, j + 7, k + 6, MFQM.BlossomBlock, 10);
    setBlock(world, i + 5, j + 8, k + 2, MFQM.BlossomBlock, 10);
    setBlock(world, i + 5, j + 8, k + 3, MFQM.BlossomBlock, 10);
    setBlock(world, i + 5, j + 8, k + 4, MFQM.BlossomBlock, 10);
    setBlock(world, i + 6, j + 6, k + 2, MFQM.BlossomBlock, 10);
    setBlock(world, i + 6, j + 6, k + 3, MFQM.BlossomBlock, 10);
    setBlock(world, i + 6, j + 6, k + 4, MFQM.BlossomBlock, 10);
    setBlock(world, i + 6, j + 7, k + 1, MFQM.BlossomBlock, 10);
    setBlock(world, i + 6, j + 7, k + 2, MFQM.BlossomBlock, 10);
    setBlock(world, i + 6, j + 7, k + 3, MFQM.BlossomBlock, 10);
    setBlock(world, i + 6, j + 7, k + 4, MFQM.BlossomBlock, 10);
    setBlock(world, i + 6, j + 7, k + 5, MFQM.BlossomBlock, 10);
    setBlock(world, i + 2, j + 3, k + 2, MFQM.MucusBlock, 0);
    setBlock(world, i + 2, j + 3, k + 3, MFQM.MucusBlock, 0);
    setBlock(world, i + 2, j + 3, k + 4, MFQM.MucusBlock, 0);
    setBlock(world, i + 2, j + 4, k + 2, MFQM.MucusBlock, 0);
    setBlock(world, i + 2, j + 4, k + 3, MFQM.MucusBlock, 0);
    setBlock(world, i + 2, j + 4, k + 4, MFQM.MucusBlock, 0);
    setBlock(world, i + 3, j + 3, k + 2, MFQM.MucusBlock, 0);
    setBlock(world, i + 3, j + 3, k + 3, MFQM.MucusBlock, 0);
    setBlock(world, i + 3, j + 3, k + 4, MFQM.MucusBlock, 0);
    setBlock(world, i + 3, j + 4, k + 2, MFQM.MucusBlock, 0);
    setBlock(world, i + 3, j + 4, k + 3, MFQM.MucusBlock, 0);
    setBlock(world, i + 3, j + 4, k + 4, MFQM.MucusBlock, 0);
    setBlock(world, i + 4, j + 3, k + 2, MFQM.MucusBlock, 0);
    setBlock(world, i + 4, j + 3, k + 3, MFQM.MucusBlock, 0);
    setBlock(world, i + 4, j + 3, k + 4, MFQM.MucusBlock, 0);
    setBlock(world, i + 4, j + 4, k + 2, MFQM.MucusBlock, 0);
    setBlock(world, i + 4, j + 4, k + 3, MFQM.MucusBlock, 0);
    setBlock(world, i + 4, j + 4, k + 4, MFQM.MucusBlock, 0);
    return true;
  }
}
