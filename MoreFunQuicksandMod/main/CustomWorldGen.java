package MoreFunQuicksandMod.main;

import MoreFunQuicksandMod.main.blocks.BlockMorass;
import cpw.mods.fml.common.IWorldGenerator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.Vec3;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.ChestGenHooks;

public class CustomWorldGen implements IWorldGenerator {
  boolean T_BoP;
  
  public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
    if (world.provider.dimensionId == -1) {
      generateNether(world, random, chunkX * 16, chunkZ * 16);
      return;
    } 
    if (world.provider.dimensionId == 0) {
      if (!MFQM.CWG) {
        generateSurface(world, random, chunkX * 16, chunkZ * 16, MFQM.AltitudeShift);
        return;
      } 
      generateCustom(world, random, chunkX * 16, chunkZ * 16, MFQM.AltitudeShift);
      return;
    } 
    if (world.provider.dimensionId == 1) {
      generateEnd(world, random, chunkX * 16, chunkZ * 16);
      return;
    } 
    if (world.provider.dimensionId == MFQM.TFDim) {
      generateCustom(world, random, chunkX * 16, chunkZ * 16, 32);
      return;
    } 
    if (world.provider.dimensionId == MFQM.TBLDim) {
      generateTBL(world, random, chunkX * 16, chunkZ * 16);
      return;
    } 
    if (world.provider.dimensionId == MFQM.WLDDim) {
      generateWLD(world, random, chunkX * 16, chunkZ * 16);
      return;
    } 
    if (world.provider.dimensionId == MFQM.ABCDim1) {
      generateABC1(world, random, chunkX * 16, chunkZ * 16);
      return;
    } 
    if (world.provider.dimensionId == MFQM.ABCDim2) {
      generateABC2(world, random, chunkX * 16, chunkZ * 16);
      return;
    } 
    if (world.provider.dimensionId == MFQM.ABCDim3) {
      generateABC3(world, random, chunkX * 16, chunkZ * 16);
      return;
    } 
    if (world.provider.dimensionId == MFQM.ABCDim4) {
      generateABC4(world, random, chunkX * 16, chunkZ * 16);
      return;
    } 
    if (world.provider.dimensionId == MFQM.AOADimHeaven) {
      generateAOADimHeaven(world, random, chunkX * 16, chunkZ * 16);
      return;
    } 
    if (world.provider.dimensionId == MFQM.AOADimAbyss) {
      generateAOADimAbyss(world, random, chunkX * 16, chunkZ * 16);
      return;
    } 
    if (world.provider.dimensionId == MFQM.AOADimDeeplands) {
      generateAOADimDeeplands(world, random, chunkX * 16, chunkZ * 16);
      return;
    } 
    if (world.provider.dimensionId == MFQM.AOADimPrecasia) {
      generateAOADimPrecasia(world, random, chunkX * 16, chunkZ * 16);
      return;
    } 
    if (world.provider.dimensionId == MFQM.AOADimLunalus) {
      generateAOADimLunalus(world, random, chunkX * 16, chunkZ * 16);
      return;
    } 
    if (world.provider.dimensionId == MFQM.AOADimIromine) {
      generateAOADimIromine(world, random, chunkX * 16, chunkZ * 16);
      return;
    } 
    if (world.provider.dimensionId == MFQM.AOADimGardencia) {
      generateAOADimGardencia(world, random, chunkX * 16, chunkZ * 16);
      return;
    } 
    if (world.provider.dimensionId == MFQM.AOADimGreckon) {
      generateAOADimGreckon(world, random, chunkX * 16, chunkZ * 16);
      return;
    } 
    if (world.provider.dimensionId == MFQM.AOADimLBorean) {
      generateAOADimLBorean(world, random, chunkX * 16, chunkZ * 16);
      return;
    } 
    if (world.provider.dimensionId == MFQM.AOADimBarathos) {
      generateAOADimBarathos(world, random, chunkX * 16, chunkZ * 16);
      return;
    } 
    if (world.provider.dimensionId == MFQM.AOADimDustopia) {
      generateAOADimDustopia(world, random, chunkX * 16, chunkZ * 16);
      return;
    } 
    if (world.provider.dimensionId == MFQM.AOADimVoxPonds) {
      generateAOADimVoxPonds(world, random, chunkX * 16, chunkZ * 16);
      return;
    } 
    if (world.provider.dimensionId == MFQM.AOADimLandsLelyetia) {
      generateAOADimLandsLelyetia(world, random, chunkX * 16, chunkZ * 16);
      return;
    } 
    if (world.provider.dimensionId == MFQM.AOADimCeleve) {
      generateAOADimCeleve(world, random, chunkX * 16, chunkZ * 16);
      return;
    } 
    if (world.provider.dimensionId == MFQM.AOADimCreeponia) {
      generateAOADimCreeponia(world, random, chunkX * 16, chunkZ * 16);
      return;
    } 
    if (world.provider.dimensionId == MFQM.AOADimShyrelands) {
      generateAOADimShyrelands(world, random, chunkX * 16, chunkZ * 16);
      return;
    } 
    if (world.provider.dimensionId == MFQM.AOADimCrystevia) {
      generateAOADimCrystevia(world, random, chunkX * 16, chunkZ * 16);
      return;
    } 
    if (world.provider.dimensionId == MFQM.AOADimCandyland) {
      generateAOADimCandyland(world, random, chunkX * 16, chunkZ * 16);
      return;
    } 
  }
  
  private void generateEnd(World world, Random random, int i, int j) {}
  
  private Block soilBlock(int xx, int zz, World world) {
    if ((world.getBiomeGenForCoords(xx, zz)).topBlock != null)
      return (world.getBiomeGenForCoords(xx, zz)).topBlock; 
    return (Block)Blocks.grass;
  }
  
  private Block fillerBlock(int xx, int zz, World world) {
    if ((world.getBiomeGenForCoords(xx, zz)).fillerBlock != null)
      return (world.getBiomeGenForCoords(xx, zz)).fillerBlock; 
    return Blocks.dirt;
  }
  
  private boolean validGroundMaterial(int x, int y, int z, World world) {
    if (world.getBlock(x, y, z).getMaterial() == Material.grass || world.getBlock(x, y, z).getMaterial() == Material.ground || world.getBlock(x, y, z).getMaterial() == Material.sand)
      return true; 
    return false;
  }
  
  private boolean validGroundOrLMireMaterial(int x, int y, int z, World world) {
    if (world.getBlock(x, y, z) == MFQM.MireBlock || world.getBlock(x, y, z) == MFQM.StableLiquidMireBlock || world.getBlock(x, y, z).getMaterial() == Material.grass || world.getBlock(x, y, z).getMaterial() == Material.ground || world.getBlock(x, y, z).getMaterial() == Material.sand)
      return true; 
    return false;
  }
  
  private boolean validCaveMaterial(int x, int y, int z, World world) {
    if (world.getBlock(x, y, z).getMaterial() == Material.rock || world.getBlock(x, y, z).getMaterial() == Material.grass || world.getBlock(x, y, z).getMaterial() == Material.ground || world.getBlock(x, y, z).getMaterial() == Material.sand)
      return true; 
    return false;
  }
  
  private boolean validSnowMaterial(int x, int y, int z, World world) {
    if (world.getBlock(x, y, z).getMaterial() == Material.snow || world.getBlock(x, y, z).getMaterial() == Material.craftedSnow)
      return true; 
    return false;
  }
  
  private boolean validFallingMaterial(int x, int y, int z, World world) {
    if (world.getBlock(x, y, z).getMaterial() == Material.sand || world.getBlock(x, y, z).getMaterial().isLiquid())
      return true; 
    return false;
  }
  
  private boolean validSandMaterial(boolean sndO, int x, int y, int z, World world) {
    if (sndO) {
      if (world.getBlock(x, y, z).getMaterial() == Material.sand)
        return true; 
      return false;
    } 
    if (world.getBlock(x, y, z).getMaterial() == Material.grass || world.getBlock(x, y, z).getMaterial() == Material.ground || world.getBlock(x, y, z).getMaterial() == Material.sand)
      return true; 
    return false;
  }
  
  private boolean validClayMaterial(int x, int y, int z, World world) {
    if (world.getBlock(x, y, z).getMaterial() == Material.clay)
      return true; 
    return false;
  }
  
  private boolean validWaterMaterial(int x, int y, int z, World world) {
    if (world.getBlock(x, y, z).getMaterial() == Material.water)
      return true; 
    return false;
  }
  
  private boolean validLavaMaterial(int x, int y, int z, World world) {
    if (world.getBlock(x, y, z).getMaterial() == Material.lava)
      return true; 
    return false;
  }
  
  private boolean validLiqMaterial(int x, int y, int z, World world) {
    return world.getBlock(x, y, z).getMaterial().isLiquid();
  }
  
  private boolean validCSMaterial(int x, int y, int z, World world) {
    if (world.getBlock(x, y, z) != MFQM.AshBlock && world.getBlock(x, y, z).getMaterial() == Material.sand)
      return true; 
    return false;
  }
  
  private boolean validBloodMaterial(int x, int y, int z, World world) {
    if (world.getBlock(x, y, z) == Blocks.netherrack)
      return true; 
    return false;
  }
  
  private boolean validVoreMaterial(int x, int y, int z, World world) {
    if ((world.getBlock(x, y, z) == MFQM.MeatBlock && world.getBlockMetadata(x, y, z) == 0) || world.getBlock(x, y, z) == MFQM.FleshBlock)
      return true; 
    return false;
  }
  
  private boolean validTarMaterial(int x, int y, int z, World world) {
    if (world.getBlock(x, y, z) == MFQM.TarBlock || world.getBlock(x, y, z).getMaterial().isSolid())
      return true; 
    return false;
  }
  
  private boolean validLarvMaterial(int x, int y, int z, World world) {
    if (world.getBlock(x, y, z) == MFQM.LarvaeBlock || validGroundMaterial(x, y, z, world))
      return true; 
    return false;
  }
  
  private boolean validSolidMaterial(int x, int y, int z, World world) {
    if (world.getBlock(x, y, z).getMaterial().isSolid())
      return true; 
    return false;
  }
  
  private boolean validMorassMaterial(int x, int y, int z, World world) {
    if (world.getBlock(x, y, z).getMaterial().isSolid() || world.getBlock(x, y, z) == MFQM.MorassBlock)
      return true; 
    return false;
  }
  
  private boolean validSlimeMaterial(int x, int y, int z, World world) {
    if (world.getBlock(x, y, z).getMaterial().isSolid() || world.getBlock(x, y, z) == MFQM.SlimeBlock)
      return true; 
    return false;
  }
  
  private boolean validHoneycombMaterial(int x, int y, int z, World world) {
    if (world.getBlock(x, y, z) == MFQM.HoneycombBlock)
      return true; 
    return false;
  }
  
  private boolean validWebbingMaterial(int x, int y, int z, World world) {
    if (world.getBlock(x, y, z).getMaterial().isSolid() || world.getBlock(x, y, z) == MFQM.DenseWebBlock)
      return true; 
    return false;
  }
  
  private boolean validSlurryMaterial(int x, int y, int z, World world) {
    if (world.getBlock(x, y, z).getMaterial().isSolid() || world.getBlock(x, y, z) == MFQM.SlurryBlock)
      return true; 
    return false;
  }
  
  private boolean validWebMaterial(int x, int y, int z, World world) {
    if (world.getBlock(x, y, z).getMaterial().isSolid() || world.getBlock(x, y, z) == MFQM.DenseWebBlock || world.getBlock(x, y, z) == Blocks.web)
      return true; 
    return false;
  }
  
  private boolean validGroundClayMaterial(int x, int y, int z, World world) {
    if (world.getBlock(x, y, z) == MFQM.HClayBlock || world.getBlock(x, y, z).getMaterial() == Material.grass || world.getBlock(x, y, z).getMaterial() == Material.ground || world.getBlock(x, y, z).getMaterial() == Material.sand)
      return true; 
    return false;
  }
  
  private boolean validSoftQuicksandMaterial(int x, int y, int z, World world) {
    if (validGroundMaterial(x, y, z, world))
      return true; 
    if (world.getBlock(x, y, z).getMaterial().isSolid())
      return true; 
    if (world.getBlock(x, y, z) == MFQM.SoftQuicksandBlock)
      return true; 
    return false;
  }
  
  private boolean validPlantMaterial(int x, int y, int z, World world) {
    if (world.getBlock(x, y, z) == MFQM.TentBlock)
      return false; 
    if (world.getBlock(x, y, z).getMaterial() == Material.cactus)
      return true; 
    if (world.getBlock(x, y, z).getMaterial() == Material.vine)
      return true; 
    if (world.getBlock(x, y, z).getMaterial() == Material.plants)
      return true; 
    if (world.getBlock(x, y, z).getMaterial() == Material.leaves)
      return true; 
    return false;
  }
  
  private boolean genIsFlatGroundSlime(int x, int y, int z, int r, World world) {
    r /= 2;
    if ((validGroundMaterial(x + r, y - 1, z + r, world) || 
      validSlimeMaterial(x + r, y - 1, z + r, world)) && (
      validGroundMaterial(x - r, y - 1, z + r, world) || 
      validSlimeMaterial(x - r, y - 1, z + r, world)) && (
      validGroundMaterial(x + r, y - 1, z - r, world) || 
      validSlimeMaterial(x + r, y - 1, z - r, world)) && (
      validGroundMaterial(x - r, y - 1, z - r, world) || 
      validSlimeMaterial(x - r, y - 1, z - r, world)) && (
      validGroundMaterial(x - r, y - 1, z, world) || 
      validSlimeMaterial(x - r, y - 1, z, world)) && (
      validGroundMaterial(x, y - 1, z - r, world) || 
      validSlimeMaterial(x, y - 1, z - r, world)) && (
      validGroundMaterial(x + r, y - 1, z, world) || 
      validSlimeMaterial(x + r, y - 1, z, world)) && (
      validGroundMaterial(x, y - 1, z + r, world) || 
      validSlimeMaterial(x, y - 1, z + r, world)))
      return true; 
    return false;
  }
  
  private boolean genIsFlatSoftQuicksand(int x, int y, int z, int r, World world) {
    r /= 2;
    if (validSoftQuicksandMaterial(x + r, y - 1, z + r, world) && 
      validSoftQuicksandMaterial(x - r, y - 1, z + r, world) && 
      validSoftQuicksandMaterial(x + r, y - 1, z - r, world) && 
      validSoftQuicksandMaterial(x - r, y - 1, z - r, world) && 
      validSoftQuicksandMaterial(x - r, y - 1, z, world) && 
      validSoftQuicksandMaterial(x, y - 1, z - r, world) && 
      validSoftQuicksandMaterial(x + r, y - 1, z, world) && 
      validSoftQuicksandMaterial(x, y - 1, z + r, world))
      return true; 
    return false;
  }
  
  private boolean genIsFlat(int x, int y, int z, int r, World world) {
    r /= 2;
    if (validGroundMaterial(x + r, y - 1, z + r, world) && 
      validGroundMaterial(x - r, y - 1, z + r, world) && 
      validGroundMaterial(x + r, y - 1, z - r, world) && 
      validGroundMaterial(x - r, y - 1, z - r, world) && 
      validGroundMaterial(x - r, y - 1, z, world) && 
      validGroundMaterial(x, y - 1, z - r, world) && 
      validGroundMaterial(x + r, y - 1, z, world) && 
      validGroundMaterial(x, y - 1, z + r, world))
      return true; 
    return false;
  }
  
  private boolean genIsFlatHoneycomb(int x, int y, int z, int r, World world) {
    r /= 2;
    if (validHoneycombMaterial(x + r, y - 1, z + r, world) && 
      validHoneycombMaterial(x - r, y - 1, z + r, world) && 
      validHoneycombMaterial(x + r, y - 1, z - r, world) && 
      validHoneycombMaterial(x - r, y - 1, z - r, world) && 
      validHoneycombMaterial(x - r, y - 1, z, world) && 
      validHoneycombMaterial(x, y - 1, z - r, world) && 
      validHoneycombMaterial(x + r, y - 1, z, world) && 
      validHoneycombMaterial(x, y - 1, z + r, world))
      return true; 
    return false;
  }
  
  private boolean genIsHoneycombNear(int x, int y, int z, int r, World world) {
    r /= 2;
    if (validHoneycombMaterial(x + r, y - 1, z + r, world))
      return true; 
    if (validHoneycombMaterial(x - r, y - 1, z + r, world))
      return true; 
    if (validHoneycombMaterial(x + r, y - 1, z - r, world))
      return true; 
    if (validHoneycombMaterial(x - r, y - 1, z - r, world))
      return true; 
    if (validHoneycombMaterial(x - r, y - 1, z, world))
      return true; 
    if (validHoneycombMaterial(x, y - 1, z - r, world))
      return true; 
    if (validHoneycombMaterial(x + r, y - 1, z, world))
      return true; 
    if (validHoneycombMaterial(x, y - 1, z + r, world))
      return true; 
    return false;
  }
  
  private boolean genIsFlatOrLMire(int x, int y, int z, int r, World world) {
    r /= 2;
    if (validGroundOrLMireMaterial(x + r, y - 1, z + r, world) && 
      validGroundOrLMireMaterial(x - r, y - 1, z + r, world) && 
      validGroundOrLMireMaterial(x + r, y - 1, z - r, world) && 
      validGroundOrLMireMaterial(x - r, y - 1, z - r, world) && 
      validGroundOrLMireMaterial(x - r, y - 1, z, world) && 
      validGroundOrLMireMaterial(x, y - 1, z - r, world) && 
      validGroundOrLMireMaterial(x + r, y - 1, z, world) && 
      validGroundOrLMireMaterial(x, y - 1, z + r, world))
      return true; 
    return false;
  }
  
  private boolean genIsFlatClay(int x, int y, int z, int r, World world) {
    r /= 2;
    if (validGroundClayMaterial(x + r, y - 1, z + r, world) && 
      validGroundClayMaterial(x - r, y - 1, z + r, world) && 
      validGroundClayMaterial(x + r, y - 1, z - r, world) && 
      validGroundClayMaterial(x - r, y - 1, z - r, world) && 
      validGroundClayMaterial(x - r, y - 1, z, world) && 
      validGroundClayMaterial(x, y - 1, z - r, world) && 
      validGroundClayMaterial(x + r, y - 1, z, world) && 
      validGroundClayMaterial(x, y - 1, z + r, world))
      return true; 
    return false;
  }
  
  private boolean genIsFlatVore(int x, int y, int z, int r, World world) {
    r /= 2;
    if (validVoreMaterial(x + r, y - 1, z + r, world) && 
      validVoreMaterial(x - r, y - 1, z + r, world) && 
      validVoreMaterial(x + r, y - 1, z - r, world) && 
      validVoreMaterial(x - r, y - 1, z - r, world) && 
      validVoreMaterial(x - r, y - 1, z, world) && 
      validVoreMaterial(x, y - 1, z - r, world) && 
      validVoreMaterial(x + r, y - 1, z, world) && 
      validVoreMaterial(x, y - 1, z + r, world))
      return true; 
    return false;
  }
  
  private boolean genIsFlatSnow(int x, int y, int z, int r, World world) {
    r /= 2;
    if ((validSnowMaterial(x + r, y - 1, z + r, world) || validSolidMaterial(x + r, y - 1, z + r, world)) && (
      validSnowMaterial(x - r, y - 1, z + r, world) || validSolidMaterial(x - r, y - 1, z + r, world)) && (
      validSnowMaterial(x + r, y - 1, z - r, world) || validSolidMaterial(x + r, y - 1, z - r, world)) && (
      validSnowMaterial(x - r, y - 1, z - r, world) || validSolidMaterial(x - r, y - 1, z - r, world)) && (
      validSnowMaterial(x - r, y - 1, z, world) || validSolidMaterial(x - r, y - 1, z, world)) && (
      validSnowMaterial(x, y - 1, z - r, world) || validSolidMaterial(x, y - 1, z - r, world)) && (
      validSnowMaterial(x + r, y - 1, z, world) || validSolidMaterial(x + r, y - 1, z, world)) && (
      validSnowMaterial(x, y - 1, z + r, world) || validSolidMaterial(x, y - 1, z + r, world)))
      return true; 
    return false;
  }
  
  private boolean genIsFlatSand(boolean snO, int x, int y, int z, int r, World world) {
    r /= 2;
    if (validSandMaterial(snO, x + r, y - 1, z + r, world) && 
      validSandMaterial(snO, x - r, y - 1, z + r, world) && 
      validSandMaterial(snO, x + r, y - 1, z - r, world) && 
      validSandMaterial(snO, x - r, y - 1, z - r, world) && 
      validSandMaterial(snO, x - r, y - 1, z, world) && 
      validSandMaterial(snO, x, y - 1, z - r, world) && 
      validSandMaterial(snO, x + r, y - 1, z, world) && 
      validSandMaterial(snO, x, y - 1, z + r, world))
      return true; 
    return false;
  }
  
  private boolean genIsFlatBog(int x, int y, int z, int r, World world) {
    r /= 2;
    if ((validGroundMaterial(x + r, y - 1, z + r, world) || world.getBlock(x + r, y - 1, z + r) == MFQM.BogBlock) && (
      validGroundMaterial(x - r, y - 1, z + r, world) || world.getBlock(x - r, y - 1, z + r) == MFQM.BogBlock) && (
      validGroundMaterial(x + r, y - 1, z - r, world) || world.getBlock(x + r, y - 1, z - r) == MFQM.BogBlock) && (
      validGroundMaterial(x - r, y - 1, z - r, world) || world.getBlock(x - r, y - 1, z - r) == MFQM.BogBlock) && (
      validGroundMaterial(x - r, y - 1, z, world) || world.getBlock(x - r, y - 1, z) == MFQM.BogBlock) && (
      validGroundMaterial(x, y - 1, z - r, world) || world.getBlock(x, y - 1, z - r) == MFQM.BogBlock) && (
      validGroundMaterial(x + r, y - 1, z, world) || world.getBlock(x + r, y - 1, z) == MFQM.BogBlock) && (
      validGroundMaterial(x, y - 1, z + r, world) || world.getBlock(x, y - 1, z + r) == MFQM.BogBlock))
      return true; 
    return false;
  }
  
  private boolean genIsFlatTar(int x, int y, int z, int r, World world) {
    r /= 2;
    if (validTarMaterial(x + r, y - 1, z + r, world) && 
      validTarMaterial(x - r, y - 1, z + r, world) && 
      validTarMaterial(x + r, y - 1, z - r, world) && 
      validTarMaterial(x - r, y - 1, z - r, world) && 
      validTarMaterial(x - r, y - 1, z, world) && 
      validTarMaterial(x, y - 1, z - r, world) && 
      validTarMaterial(x + r, y - 1, z, world) && 
      validTarMaterial(x, y - 1, z + r, world))
      return true; 
    return false;
  }
  
  private boolean genIsFlatSlime(int x, int y, int z, int r, World world) {
    r /= 2;
    if (validSlimeMaterial(x + r, y - 1, z + r, world) && 
      validSlimeMaterial(x - r, y - 1, z + r, world) && 
      validSlimeMaterial(x + r, y - 1, z - r, world) && 
      validSlimeMaterial(x - r, y - 1, z - r, world) && 
      validSlimeMaterial(x - r, y - 1, z, world) && 
      validSlimeMaterial(x, y - 1, z - r, world) && 
      validSlimeMaterial(x + r, y - 1, z, world) && 
      validSlimeMaterial(x, y - 1, z + r, world))
      return true; 
    return false;
  }
  
  private boolean genIsFlatWebbing(int x, int y, int z, int r, World world) {
    r /= 2;
    if (validWebbingMaterial(x + r, y - 1, z + r, world) && 
      validWebbingMaterial(x - r, y - 1, z + r, world) && 
      validWebbingMaterial(x + r, y - 1, z - r, world) && 
      validWebbingMaterial(x - r, y - 1, z - r, world) && 
      validWebbingMaterial(x - r, y - 1, z, world) && 
      validWebbingMaterial(x, y - 1, z - r, world) && 
      validWebbingMaterial(x + r, y - 1, z, world) && 
      validWebbingMaterial(x, y - 1, z + r, world))
      return true; 
    return false;
  }
  
  private boolean genIsFlatLarv(int x, int y, int z, int r, World world) {
    r /= 2;
    if (validLarvMaterial(x + r, y - 1, z + r, world) && 
      validLarvMaterial(x - r, y - 1, z + r, world) && 
      validLarvMaterial(x + r, y - 1, z - r, world) && 
      validLarvMaterial(x - r, y - 1, z - r, world) && 
      validLarvMaterial(x - r, y - 1, z, world) && 
      validLarvMaterial(x, y - 1, z - r, world) && 
      validLarvMaterial(x + r, y - 1, z, world) && 
      validLarvMaterial(x, y - 1, z + r, world))
      return true; 
    return false;
  }
  
  private boolean genIsFlatLarv2(int x, int y, int z, int r, World world) {
    r /= 2;
    if ((validGroundMaterial(x + r, y - 1, z + r, world) || (validLarvMaterial(x + r, y - 1, z + r, world) && world.getBlock(x + r, y, z + r) == MFQM.LarvaeBlock)) && (
      validGroundMaterial(x - r, y - 1, z + r, world) || (validLarvMaterial(x - r, y - 1, z + r, world) && world.getBlock(x - r, y, z + r) == MFQM.LarvaeBlock)) && (
      validGroundMaterial(x + r, y - 1, z - r, world) || (validLarvMaterial(x + r, y - 1, z - r, world) && world.getBlock(x + r, y, z - r) == MFQM.LarvaeBlock)) && (
      validGroundMaterial(x - r, y - 1, z - r, world) || (validLarvMaterial(x - r, y - 1, z - r, world) && world.getBlock(x - r, y, z - r) == MFQM.LarvaeBlock)) && (
      validGroundMaterial(x - r, y - 1, z, world) || (validLarvMaterial(x - r, y - 1, z, world) && world.getBlock(x - r, y, z) == MFQM.LarvaeBlock)) && (
      validGroundMaterial(x, y - 1, z - r, world) || (validLarvMaterial(x, y - 1, z - r, world) && world.getBlock(x, y, z - r) == MFQM.LarvaeBlock)) && (
      validGroundMaterial(x + r, y - 1, z, world) || (validLarvMaterial(x + r, y - 1, z, world) && world.getBlock(x + r, y, z) == MFQM.LarvaeBlock)) && (
      validGroundMaterial(x, y - 1, z + r, world) || (validLarvMaterial(x, y - 1, z + r, world) && world.getBlock(x, y, z + r) == MFQM.LarvaeBlock)))
      return true; 
    return false;
  }
  
  private boolean genIsFlatSolid(int x, int y, int z, int r, World world) {
    r /= 2;
    if (validSolidMaterial(x + r, y - 1, z + r, world) && 
      validSolidMaterial(x - r, y - 1, z + r, world) && 
      validSolidMaterial(x + r, y - 1, z - r, world) && 
      validSolidMaterial(x - r, y - 1, z - r, world) && 
      validSolidMaterial(x - r, y - 1, z, world) && 
      validSolidMaterial(x, y - 1, z - r, world) && 
      validSolidMaterial(x + r, y - 1, z, world) && 
      validSolidMaterial(x, y - 1, z + r, world))
      return true; 
    return false;
  }
  
  private boolean genIsFlatSlurry(int x, int y, int z, int r, World world) {
    r /= 2;
    if (validSlurryMaterial(x + r, y - 1, z + r, world) && 
      validSlurryMaterial(x - r, y - 1, z + r, world) && 
      validSlurryMaterial(x + r, y - 1, z - r, world) && 
      validSlurryMaterial(x - r, y - 1, z - r, world) && 
      validSlurryMaterial(x - r, y - 1, z, world) && 
      validSlurryMaterial(x, y - 1, z - r, world) && 
      validSlurryMaterial(x + r, y - 1, z, world) && 
      validSlurryMaterial(x, y - 1, z + r, world))
      return true; 
    return false;
  }
  
  private boolean genIsFlatMorass(int x, int y, int z, int r, World world) {
    r /= 2;
    if (validMorassMaterial(x + r, y - 1, z + r, world) && 
      validMorassMaterial(x - r, y - 1, z + r, world) && 
      validMorassMaterial(x + r, y - 1, z - r, world) && 
      validMorassMaterial(x - r, y - 1, z - r, world) && 
      validMorassMaterial(x - r, y - 1, z, world) && 
      validMorassMaterial(x, y - 1, z - r, world) && 
      validMorassMaterial(x + r, y - 1, z, world) && 
      validMorassMaterial(x, y - 1, z + r, world))
      return true; 
    return false;
  }
  
  private boolean genIsBrickNear(int x, int y, int z, int r, World world) {
    r /= 2;
    if (world.getBlock(x + r, y, z + r) == Blocks.stonebrick)
      return true; 
    if (world.getBlock(x - r, y, z + r) == Blocks.stonebrick)
      return true; 
    if (world.getBlock(x + r, y, z - r) == Blocks.stonebrick)
      return true; 
    if (world.getBlock(x - r, y, z - r) == Blocks.stonebrick)
      return true; 
    if (world.getBlock(x - r, y, z) == Blocks.stonebrick)
      return true; 
    if (world.getBlock(x, y, z - r) == Blocks.stonebrick)
      return true; 
    if (world.getBlock(x + r, y, z) == Blocks.stonebrick)
      return true; 
    if (world.getBlock(x, y, z + r) == Blocks.stonebrick)
      return true; 
    return false;
  }
  
  private boolean genIsTarNear(int x, int y, int z, int r, World world) {
    r /= 2;
    if (world.getBlock(x + r, y, z + r) == MFQM.TarBlock)
      return true; 
    if (world.getBlock(x - r, y, z + r) == MFQM.TarBlock)
      return true; 
    if (world.getBlock(x + r, y, z - r) == MFQM.TarBlock)
      return true; 
    if (world.getBlock(x - r, y, z - r) == MFQM.TarBlock)
      return true; 
    if (world.getBlock(x - r, y, z) == MFQM.TarBlock)
      return true; 
    if (world.getBlock(x, y, z - r) == MFQM.TarBlock)
      return true; 
    if (world.getBlock(x + r, y, z) == MFQM.TarBlock)
      return true; 
    if (world.getBlock(x, y, z + r) == MFQM.TarBlock)
      return true; 
    return false;
  }
  
  private boolean genIsUnderWaterFlat(int x, int y, int z, int r, World world) {
    r /= 2;
    if (validWaterMaterial(x + r, y, z + r, world) && validWaterMaterial(x + r + 2, y, z + r + 2, world) && 
      validWaterMaterial(x - r, y, z + r, world) && validWaterMaterial(x - r - 2, y, z + r + 2, world) && 
      validWaterMaterial(x + r, y, z - r, world) && validWaterMaterial(x + r + 2, y, z - r - 2, world) && 
      validWaterMaterial(x - r, y, z - r, world) && validWaterMaterial(x - r - 2, y, z - r - 2, world) && 
      validWaterMaterial(x - r, y, z, world) && validWaterMaterial(x - r - 2, y, z, world) && 
      validWaterMaterial(x, y, z - r, world) && validWaterMaterial(x, y, z - r - 2, world) && 
      validWaterMaterial(x + r, y, z, world) && validWaterMaterial(x + r + 2, y, z, world) && 
      validWaterMaterial(x, y, z + r, world) && validWaterMaterial(x, y, z + r + 2, world))
      return true; 
    return false;
  }
  
  public static boolean isAboveFreeSpace(int x, int y, int z, World world) {
    int re = 2 + world.rand.nextInt(3);
    y++;
    for (int i = 0; i < re; i++) {
      if (world.getBlock(x, y + i, z).getMaterial().isOpaque())
        return false; 
    } 
    return true;
  }
  
  public static boolean isAboveSky(int x, int y, int z, World world) {
    int re = 15;
    y++;
    for (int i = 0; i < re; i++) {
      if (world.getBlock(x, y + i, z).getMaterial().isOpaque())
        return false; 
    } 
    return true;
  }
  
  private boolean isAboveEmpty(int x, int y, int z, World world) {
    int re = 4 + world.rand.nextInt(3);
    y++;
    for (int i = 0; i < re; i++) {
      if (!world.isAirBlock(x, y + i, z))
        return false; 
    } 
    return true;
  }
  
  private boolean isAbovePlant(int x, int y, int z, World world) {
    int re = 4 + world.rand.nextInt(3);
    y++;
    for (int i = 0; i < re; i++) {
      if (world.getBlock(x, y + i, z).getMaterial() == Material.leaves)
        return true; 
    } 
    return false;
  }
  
  private boolean isAboveSolid(int x, int y, int z, World world) {
    int re = 10 + world.rand.nextInt(3);
    y++;
    for (int i = 0; i < re; i++) {
      if (validCaveMaterial(x, y + i, z, world))
        return true; 
    } 
    return false;
  }
  
  private boolean isNearAboveCeil(int x, int y, int z, World world, int rad) {
    int re = 18;
    int groundCount = 0;
    if (!isAboveEmpty(x, y + 2, z, world))
      for (int i = 0; i < re; i++) {
        float rg = world.rand.nextFloat() * 360.0F;
        float imp = (rad / 6) + world.rand.nextFloat() * 2.0F;
        rg = (float)Math.toRadians(rg);
        int xx = x + (int)Math.floor(Math.sin(rg) * imp);
        int zz = z + (int)Math.floor(Math.cos(rg) * imp);
        if (!isAboveEmpty(xx, y + 2, zz, world))
          groundCount++; 
        if (groundCount > 5)
          return true; 
      }  
    return false;
  }
  
  private boolean isNearAbovePlant(int x, int y, int z, World world, int rad) {
    int re = 18;
    int groundCount = 0;
    if (!isAboveEmpty(x, y + 2, z, world))
      for (int i = 0; i < re; i++) {
        float rg = world.rand.nextFloat() * 360.0F;
        float imp = (rad / 6) + world.rand.nextFloat() * 2.0F;
        rg = (float)Math.toRadians(rg);
        int xx = x + (int)Math.floor(Math.sin(rg) * imp);
        int zz = z + (int)Math.floor(Math.cos(rg) * imp);
        if (isAbovePlant(xx, y + 2, zz, world))
          groundCount++; 
        if (groundCount > 2)
          return true; 
      }  
    return false;
  }
  
  private boolean isInCave(int x, int y, int z, World world, int rad) {
    int re = 18;
    int groundCount = 0;
    if (isAboveSolid(x, y + 2, z, world))
      for (int i = 0; i < re; i++) {
        float rg = world.rand.nextFloat() * 360.0F;
        float imp = (rad / 6) + world.rand.nextFloat() * 2.0F;
        rg = (float)Math.toRadians(rg);
        int xx = x + (int)Math.floor(Math.sin(rg) * imp);
        int zz = z + (int)Math.floor(Math.cos(rg) * imp);
        if (isAboveSolid(xx, y + 2, zz, world))
          groundCount++; 
        if (groundCount > 5)
          return true; 
      }  
    return false;
  }
  
  private boolean isWaterNear(int x, int y, int z, int rad, World world) {
    int re = 18;
    int waterCount = 0;
    for (int l = 0; l < 1; l++) {
      for (int i = 0; i < re; i++) {
        float rg = world.rand.nextFloat() * 360.0F;
        float imp = (rad / 4) + world.rand.nextFloat() * 2.0F;
        rg = (float)Math.toRadians(rg);
        int xx = x + (int)Math.floor(Math.sin(rg) * imp);
        int zz = z + (int)Math.floor(Math.cos(rg) * imp);
        if (validWaterMaterial(xx, y - l, zz, world) && 
          !world.getBlock(x, y - l + 1, z).getMaterial().isSolid())
          waterCount++; 
        if (waterCount > 9)
          return true; 
      } 
    } 
    return false;
  }
  
  private boolean isGroundNear(int x, int y, int z, int rad, World world) {
    int re = 18;
    int groundCount = 0;
    for (int i = 0; i < re; i++) {
      float rg = world.rand.nextFloat() * 360.0F;
      float imp = (rad / 4) + world.rand.nextFloat() * 2.0F;
      rg = (float)Math.toRadians(rg);
      int xx = x + (int)Math.floor(Math.sin(rg) * imp);
      int zz = z + (int)Math.floor(Math.cos(rg) * imp);
      if (validGroundMaterial(xx, y, zz, world))
        groundCount++; 
      if (groundCount > 5)
        return true; 
    } 
    return false;
  }
  
  private boolean isGroundQS(int x, int y, int z, int rad, World world, Block qsBlock) {
    int re = 18;
    int groundCount = 0;
    for (int i = 0; i < re; i++) {
      float rg = world.rand.nextFloat() * 360.0F;
      float imp = (rad / 4) + world.rand.nextFloat() * 2.0F;
      rg = (float)Math.toRadians(rg);
      int xx = x + (int)Math.floor(Math.sin(rg) * imp);
      int zz = z + (int)Math.floor(Math.cos(rg) * imp);
      if (validGroundMaterial(xx, y, zz, world) || world.getBlock(xx, y, zz) == qsBlock)
        groundCount++; 
      if (groundCount > 5)
        return true; 
    } 
    return false;
  }
  
  private boolean isGroundMossNear(int x, int y, int z, int rad, World world) {
    int re = 18;
    int groundCount = 0;
    for (int i = 0; i < re; i++) {
      float rg = world.rand.nextFloat() * 360.0F;
      float imp = (rad / 4) + world.rand.nextFloat() * 2.0F;
      rg = (float)Math.toRadians(rg);
      int xx = x + (int)Math.floor(Math.sin(rg) * imp);
      int zz = z + (int)Math.floor(Math.cos(rg) * imp);
      if (validGroundMaterial(xx, y, zz, world) || world.getBlock(xx, y + 1, zz) == MFQM.MossBlock || world.getBlock(xx, y, zz) == MFQM.BogBlock)
        groundCount++; 
      if (groundCount > 5)
        return true; 
    } 
    return false;
  }
  
  private boolean isItLake(int x, int y, int z, int rad, World world) {
    int re = 8;
    int groundCount = 0;
    for (int i = 0; i < re; i++) {
      float rg = i * 45.0F;
      float imp = (rad / 2) + world.rand.nextFloat() * 2.0F;
      rg = (float)Math.toRadians(rg);
      int xx = x + (int)Math.floor(Math.sin(rg) * imp);
      int zz = z + (int)Math.floor(Math.cos(rg) * imp);
      if (validGroundMaterial(xx, y, zz, world))
        groundCount++; 
      if (groundCount > 7)
        return true; 
    } 
    return false;
  }
  
  private boolean isItLake2(int x, int y, int z, int rad, World world, boolean solid) {
    int re = 8;
    int groundCount = 0;
    for (int i = 0; i < re; i++) {
      float rg = i * 45.0F;
      float imp = (rad / 2) + world.rand.nextFloat() * 2.0F;
      rg = (float)Math.toRadians(rg);
      int xx = x + (int)Math.floor(Math.sin(rg) * imp);
      int zz = z + (int)Math.floor(Math.cos(rg) * imp);
      if (validGroundMaterial(xx, y, zz, world) || (solid && validSolidMaterial(xx, y, zz, world)))
        groundCount++; 
      if (groundCount > 7)
        return true; 
    } 
    return false;
  }
  
  private boolean isGroundBogNear(int x, int y, int z, int rad, World world) {
    int re = 18;
    int groundCount = 0;
    for (int i = 0; i < re; i++) {
      float rg = world.rand.nextFloat() * 360.0F;
      float imp = (rad / 4) + world.rand.nextFloat() * 2.0F;
      rg = (float)Math.toRadians(rg);
      int xx = x + (int)Math.floor(Math.sin(rg) * imp);
      int zz = z + (int)Math.floor(Math.cos(rg) * imp);
      if (validGroundMaterial(xx, y, zz, world) || world.getBlock(xx, y, zz) == MFQM.BogBlock)
        groundCount++; 
      if (groundCount > 5)
        return true; 
    } 
    return false;
  }
  
  private boolean isWoodNear(int x, int y, int z, World world) {
    boolean liquid = false;
    if (world.getBlock(x, y, z).getMaterial().isLiquid())
      liquid = true; 
    if (world.getBlock(x, y + 1, z).getMaterial() == Material.wood)
      if (world.getBlock(x, y + 2, z).getMaterial() == Material.wood) {
        if (!liquid)
          return true; 
      } else {
        world.setBlockToAir(x, y + 1, z);
      }  
    if (world.getBlock(x + 1, y + 1, z).getMaterial() == Material.wood)
      if (world.getBlock(x + 1, y + 2, z).getMaterial() == Material.wood) {
        if (!liquid)
          return true; 
      } else {
        world.setBlockToAir(x + 1, y + 1, z);
      }  
    if (world.getBlock(x - 1, y + 1, z).getMaterial() == Material.wood)
      if (world.getBlock(x - 1, y + 2, z).getMaterial() == Material.wood) {
        if (!liquid)
          return true; 
      } else {
        world.setBlockToAir(x - 1, y + 1, z);
      }  
    if (world.getBlock(x, y + 1, z + 1).getMaterial() == Material.wood)
      if (world.getBlock(x, y + 2, z + 1).getMaterial() == Material.wood) {
        if (!liquid)
          return true; 
      } else {
        world.setBlockToAir(x, y + 1, z + 1);
      }  
    if (world.getBlock(x, y + 1, z - 1).getMaterial() == Material.wood)
      if (world.getBlock(x, y + 2, z - 1).getMaterial() == Material.wood) {
        if (!liquid)
          return true; 
      } else {
        world.setBlockToAir(x, y + 1, z - 1);
      }  
    return false;
  }
  
  private boolean validVoreDown(int x, int y, int z, World world, int count) {
    for (int i = 0; i < count; i++) {
      if (!validVoreMaterial(x, y - i, z, world))
        return false; 
    } 
    return true;
  }
  
  private boolean isVorePlace(int x, int y, int z, World world) {
    int deep = 3;
    if (validVoreDown(x, y, z, world, deep) && 
      validVoreDown(x - 1, y, z - 1, world, deep) && 
      validVoreDown(x + 1, y, z - 1, world, deep) && 
      validVoreDown(x + 1, y, z + 1, world, deep) && 
      validVoreDown(x - 1, y, z + 1, world, deep) && 
      validVoreDown(x + 1, y, z, world, deep) && 
      validVoreDown(x - 1, y, z, world, deep) && 
      validVoreDown(x, y, z - 1, world, deep) && 
      validVoreDown(x, y, z + 1, world, deep))
      return true; 
    return false;
  }
  
  private boolean IsDifficultSwamp(int ui) {
    if (ui == MFQM.SwampBiomes[9] || ui == MFQM.SwampBiomes[10])
      return true; 
    return false;
  }
  
  private boolean IsNormalSwamp(int ui) {
    if (ui == MFQM.SwampBiomes[2] || ui == MFQM.SwampBiomes[0] || ui == MFQM.SwampBiomes[3] || ui == MFQM.SwampBiomes[11])
      return true; 
    return false;
  }
  
  private boolean IsEasySwamp(int ui) {
    if (ui == MFQM.SwampBiomes[12])
      return true; 
    return false;
  }
  
  private int getSwampDifficult(int ui) {
    if (this.T_BoP) {
      if (IsEasySwamp(ui))
        return 3; 
      if (IsNormalSwamp(ui))
        return 2; 
      if (IsDifficultSwamp(ui))
        return 1; 
      return 0;
    } 
    return 0;
  }
  
  private void generateClearWater(int xx, int yy, int zz, int rad, World world, Random random) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = 1.0D;
    double raz = 1.0D;
    for (int j = 0; j <= rad / 2; j++) {
      for (int i = 0; i <= rad; i++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D));
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D && 
            !validWaterMaterial(posx, posy + 1, posz, world) && 
            validWaterMaterial(posx, posy, posz, world))
            world.setBlockToAir(posx, posy, posz); 
        } 
      } 
    } 
  }
  
  private void generateBrownClay(int xx, int yy, int zz, int rad, World world, Random random) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    for (int i = 0; i <= rad; i++) {
      for (int k = 0; k <= rad / 2; k++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - k;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D && 
            world.getBlock(posx, posy, posz).getMaterial() == Material.water) {
            if (validPlantMaterial(posx, posy + 1, posz, world))
              world.setBlockToAir(posx, posy + 1, posz); 
            if (validPlantMaterial(posx, posy + 2, posz, world))
              world.setBlockToAir(posx, posy + 2, posz); 
            if (validPlantMaterial(posx, posy + 3, posz, world))
              world.setBlockToAir(posx, posy + 3, posz); 
            world.setBlock(posx, posy, posz, MFQM.BrownClayBlock, random.nextInt(4), 2);
            if (world.getBlock(posx + 1, posy, posz).getMaterial() == Material.water)
              world.setBlock(posx + 1, posy, posz, MFQM.BrownClayBlock, random.nextInt(4), 2); 
            if (world.getBlock(posx - 1, posy, posz).getMaterial() == Material.water)
              world.setBlock(posx - 1, posy, posz, MFQM.BrownClayBlock, random.nextInt(4), 2); 
            if (world.getBlock(posx, posy, posz + 1).getMaterial() == Material.water)
              world.setBlock(posx, posy, posz + 1, MFQM.BrownClayBlock, random.nextInt(4), 2); 
            if (world.getBlock(posx, posy, posz - 1).getMaterial() == Material.water)
              world.setBlock(posx, posy, posz - 1, MFQM.BrownClayBlock, random.nextInt(4), 2); 
          } 
        } 
      } 
    } 
    Block sblock = soilBlock(xx, zz, world);
    Block fblock = fillerBlock(xx, zz, world);
    for (int j = 0; j <= rad; j++) {
      for (int k = 0; k <= rad / 2 + 2; k++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + j - rad / 2;
          int posy = yy - k;
          int posz = zz + e - rad / 2;
          if (world.getBlock(posx, posy, posz) != MFQM.BrownClayBlock && 
            !validGroundMaterial(posx, posy, posz, world) && 
            world.getBlock(posx, posy + 1, posz) == MFQM.BrownClayBlock)
            world.setBlock(posx, posy, posz, fblock); 
        } 
      } 
    } 
  }
  
  private void generateMineralClay(int xx, int yy, int zz, int rad, World world, Random random) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    int i;
    for (i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D && 
            world.getBlock(posx, posy, posz).getMaterial() == Material.water) {
            if (validPlantMaterial(posx, posy + 1, posz, world))
              world.setBlockToAir(posx, posy + 1, posz); 
            if (validPlantMaterial(posx, posy + 2, posz, world))
              world.setBlockToAir(posx, posy + 2, posz); 
            if (validPlantMaterial(posx, posy + 3, posz, world))
              world.setBlockToAir(posx, posy + 3, posz); 
            world.setBlock(posx, posy, posz, MFQM.BrownClayBlock, random.nextInt(4) + 4, 2);
            if (world.getBlock(posx + 1, posy, posz).getMaterial() == Material.water)
              world.setBlock(posx + 1, posy, posz, MFQM.BrownClayBlock, random.nextInt(4) + 4, 2); 
            if (world.getBlock(posx - 1, posy, posz).getMaterial() == Material.water)
              world.setBlock(posx - 1, posy, posz, MFQM.BrownClayBlock, random.nextInt(4) + 4, 2); 
            if (world.getBlock(posx, posy, posz + 1).getMaterial() == Material.water)
              world.setBlock(posx, posy, posz + 1, MFQM.BrownClayBlock, random.nextInt(4) + 4, 2); 
            if (world.getBlock(posx, posy, posz - 1).getMaterial() == Material.water)
              world.setBlock(posx, posy, posz - 1, MFQM.BrownClayBlock, random.nextInt(4) + 4, 2); 
          } 
        } 
      } 
    } 
    for (i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2 + 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          if (world.getBlock(posx, posy, posz) != MFQM.BrownClayBlock && 
            !validGroundMaterial(posx, posy, posz, world) && 
            world.getBlock(posx, posy + 1, posz) == MFQM.BrownClayBlock)
            world.setBlock(posx, posy, posz, Blocks.stone); 
        } 
      } 
    } 
  }
  
  private void generateMire(int xx, int yy, int zz, int rad, World world, Random random, Material BlockMaterialToReplace, Block MireBlockId) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    int i;
    for (i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D && 
            world.getBlock(posx, posy, posz) != MFQM.SoftQuicksandBlock && (
            BlockMaterialToReplace == Material.cake || world.getBlock(posx, posy, posz).getMaterial() == BlockMaterialToReplace)) {
            if (validPlantMaterial(posx, posy + 1, posz, world))
              world.setBlockToAir(posx, posy + 1, posz); 
            if (validPlantMaterial(posx, posy + 2, posz, world))
              world.setBlockToAir(posx, posy + 2, posz); 
            if (validPlantMaterial(posx, posy + 3, posz, world))
              world.setBlockToAir(posx, posy + 3, posz); 
            if (world.getBlock(posx, posy + 1, posz).getMaterial() == Material.wood && 
              world.getBlock(posx, posy + 2, posz).getMaterial() != Material.wood)
              world.setBlockToAir(posx, posy + 1, posz); 
            world.setBlock(posx, posy, posz, MireBlockId);
          } 
        } 
      } 
    } 
    for (i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2 + 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          if (world.getBlock(posx, posy, posz) != MireBlockId && 
            world.getBlock(posx, posy, posz) != MFQM.SoftQuicksandBlock && 
            world.getBlock(posx, posy, posz) != MFQM.SlurryBlock && 
            world.getBlock(posx, posy + 1, posz) == MireBlockId)
            if (world.provider.dimensionId == 0) {
              world.setBlock(posx, posy, posz, Blocks.stone);
            } else {
              world.setBlock(posx, posy, posz, Blocks.netherrack);
            }  
        } 
      } 
    } 
  }
  
  private void generateMireM(int xx, int yy, int zz, int rad, World world, Random random, Material BlockMaterialToReplace, Block MireBlockId, int Meta) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    int i;
    for (i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D && 
            world.getBlock(posx, posy, posz) != MFQM.SoftQuicksandBlock && (
            BlockMaterialToReplace == Material.cake || world.getBlock(posx, posy, posz).getMaterial() == BlockMaterialToReplace)) {
            if (validPlantMaterial(posx, posy + 1, posz, world) && 
              !world.getBlock(posx, posy + 1, posz).hasTileEntity())
              world.setBlockToAir(posx, posy + 1, posz); 
            if (validPlantMaterial(posx, posy + 2, posz, world) && 
              !world.getBlock(posx, posy + 2, posz).hasTileEntity())
              world.setBlockToAir(posx, posy + 2, posz); 
            if (validPlantMaterial(posx, posy + 3, posz, world) && 
              !world.getBlock(posx, posy + 3, posz).hasTileEntity())
              world.setBlockToAir(posx, posy + 3, posz); 
            if (world.getBlock(posx, posy + 1, posz).getMaterial() == Material.wood && 
              world.getBlock(posx, posy + 2, posz).getMaterial() != Material.wood && 
              !world.getBlock(posx, posy + 1, posz).hasTileEntity())
              world.setBlockToAir(posx, posy + 1, posz); 
            world.setBlock(posx, posy, posz, MireBlockId, Meta, 0);
            world.setBlock(posx, posy - 1, posz, MireBlockId, Meta, 0);
          } 
        } 
      } 
    } 
    for (i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2 + 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          if (world.getBlock(posx, posy, posz) != MireBlockId && 
            world.getBlock(posx, posy + 1, posz) == MireBlockId)
            world.setBlock(posx, posy, posz, Blocks.stone); 
        } 
      } 
    } 
  }
  
  private void generateMireO(int xx, int yy, int zz, int rad, World world, Random random, Material BlockMaterialToReplace, Block MireBlockId) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    Block aBlock = null;
    int aMeta = 0;
    boolean aDetBlock = false;
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    int i;
    for (i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D && (
            BlockMaterialToReplace == Material.cake || world.getBlock(posx, posy, posz).getMaterial() == BlockMaterialToReplace)) {
            if (validPlantMaterial(posx, posy + 1, posz, world))
              world.setBlockToAir(posx, posy + 1, posz); 
            if (validPlantMaterial(posx, posy + 2, posz, world))
              world.setBlockToAir(posx, posy + 2, posz); 
            if (validPlantMaterial(posx, posy + 3, posz, world))
              world.setBlockToAir(posx, posy + 3, posz); 
            if (!aDetBlock) {
              aBlock = world.getBlock(posx, posy, posz);
              if (aBlock != null && 
                aBlock.getMaterial().isSolid() && 
                aBlock.isOpaqueCube() && 
                !aBlock.getMaterial().isLiquid() && 
                !aBlock.hasTileEntity()) {
                aMeta = world.getBlockMetadata(posx, posy, posz);
                aDetBlock = true;
              } 
            } 
            if (j == 0 && 
              !world.getBlock(posx, posy + 2, posz).getMaterial().isLiquid() && 
              world.getBlock(posx, posy + 1, posz) != MireBlockId && 
              world.getBlock(posx, posy + 1, posz).getMaterial().isSolid())
              world.setBlockToAir(posx, posy + 1, posz); 
            world.setBlock(posx, posy, posz, MireBlockId);
          } 
        } 
      } 
    } 
    if (aDetBlock)
      for (i = -1; i <= rad + 1; i++) {
        for (int j = -1; j <= rad / 2 + 2; j++) {
          for (int e = -1; e <= rad + 1; e++) {
            int posx = xx + i - rad / 2;
            int posy = yy - j;
            int posz = zz + e - rad / 2;
            if (world.getBlock(posx, posy, posz) != MireBlockId && 
              !validGroundMaterial(posx, posy, posz, world) && (
              world.getBlock(posx - 1, posy, posz) == MireBlockId || world
              .getBlock(posx + 1, posy, posz) == MireBlockId || world
              .getBlock(posx, posy + 1, posz) == MireBlockId || world
              .getBlock(posx, posy, posz - 1) == MireBlockId || world
              .getBlock(posx, posy, posz + 1) == MireBlockId))
              world.setBlock(posx, posy, posz, aBlock, aMeta, 0); 
          } 
        } 
      }  
  }
  
  private void generateMud(int xx, int yy, int zz, int rad, World world, Random random) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    int difq = 0;
    if (random.nextInt(4) == 0)
      difq++; 
    if (random.nextInt(5) == 0)
      difq++; 
    if (random.nextInt(6) == 0)
      difq++; 
    if (random.nextInt(7) == 0)
      difq++; 
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    for (int i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy + j - rad / 2;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          double disQ = rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D;
          if (dis < disQ && (
            world.getBlock(posx, posy, posz).getMaterial() == Material.ground || world.getBlock(posx, posy, posz).getMaterial() == Material.sand || world.getBlock(posx, posy, posz).getMaterial() == Material.grass)) {
            if (validPlantMaterial(posx, posy + 1, posz, world))
              world.setBlockToAir(posx, posy + 1, posz); 
            if (validPlantMaterial(posx, posy + 2, posz, world))
              world.setBlockToAir(posx, posy + 2, posz); 
            if (validPlantMaterial(posx, posy + 3, posz, world))
              world.setBlockToAir(posx, posy + 3, posz); 
            int metm = 0;
            int metq = 0;
            if (MFQM.GenDeep) {
              if (dis > disQ * 2.0D / 3.0D)
                metq = 0; 
              if (dis > disQ / 3.0D)
                metq = Math.max((int)(Math.floor(difq / 2.0D) - Math.floor((rad / 2 - j) / 8.0D)), 0); 
              if (dis <= disQ / 3.0D)
                metq = Math.max(Math.min((int)(difq - Math.floor((rad / 2 - j) / 8.0D)), 3), 0); 
              if (world.getBlock(posx, posy, posz) == MFQM.MudBlock)
                metm = world.getBlockMetadata(posx, posy, posz); 
            } 
            world.setBlock(posx, posy, posz, MFQM.MudBlock, Math.max(metm, metq), 2);
          } 
        } 
      } 
    } 
  }
  
  private void generateForcedMud(int xx, int yy, int zz, int rad, World world, Random random, int depth) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    int difq = 0;
    if (depth < 1) {
      if (random.nextInt(4) == 0)
        difq++; 
      if (random.nextInt(5) == 0)
        difq++; 
      if (random.nextInt(6) == 0)
        difq++; 
      if (random.nextInt(7) == 0)
        difq++; 
    } else {
      difq = depth;
    } 
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    for (int i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy + j - rad / 2;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          double disQ = rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D;
          if (dis < disQ && 
            validSolidMaterial(posx, posy, posz, world) && !world.getBlock(posx, posy, posz).getMaterial().isLiquid()) {
            if (validPlantMaterial(posx, posy + 1, posz, world))
              world.setBlockToAir(posx, posy + 1, posz); 
            if (validPlantMaterial(posx, posy + 2, posz, world))
              world.setBlockToAir(posx, posy + 2, posz); 
            if (validPlantMaterial(posx, posy + 3, posz, world))
              world.setBlockToAir(posx, posy + 3, posz); 
            int metm = 0;
            int metq = 0;
            if (MFQM.GenDeep)
              if (depth == 5) {
                metq = 3;
                metm = 3;
              } else {
                if (dis > disQ * 2.0D / 3.0D)
                  metq = 0; 
                if (dis > disQ / 3.0D)
                  metq = Math.max((int)(Math.floor(difq / 2.0D) - Math.floor((rad / 2 - j) / 8.0D)), 0); 
                if (dis <= disQ / 3.0D)
                  metq = Math.max(Math.min((int)(difq - Math.floor((rad / 2 - j) / 8.0D)), 3), 0); 
                if (world.getBlock(posx, posy, posz) == MFQM.MudBlock)
                  metm = world.getBlockMetadata(posx, posy, posz); 
              }  
            world.setBlock(posx, posy, posz, MFQM.MudBlock, Math.max(metm, metq), 2);
          } 
        } 
      } 
    } 
  }
  
  private void generateFlatMud(int xx, int yy, int zz, int rad, World world, Random random) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    int difq = 0;
    if (random.nextInt(2) == 0)
      difq++; 
    if (random.nextInt(3) == 0)
      difq++; 
    if (random.nextInt(4) == 0)
      difq++; 
    if (random.nextInt(5) == 0)
      difq++; 
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    for (int i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          double disQ = rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D;
          if (dis < disQ && (
            !world.getBlock(posx, posy, posz).isOpaqueCube() || world.getBlock(posx, posy, posz) == Blocks.water || world.getBlock(posx, posy, posz).getMaterial() == Material.ground || world.getBlock(posx, posy, posz).getMaterial() == Material.sand || world.getBlock(posx, posy, posz).getMaterial() == Material.grass)) {
            int metm = 0;
            int metq = 0;
            if (MFQM.GenDeep) {
              if (dis > disQ * 2.0D / 3.0D)
                metq = 0; 
              if (dis > disQ / 3.0D)
                metq = Math.max((int)(Math.floor(difq / 2.0D) - Math.floor((rad / 2 - j) / 4.0D)), 0); 
              if (dis <= disQ / 3.0D)
                metq = Math.max(Math.min((int)(difq - Math.floor((rad / 2 - j) / 4.0D)), 3), 0); 
              if (world.getBlock(posx, posy, posz) == MFQM.MudBlock)
                metm = world.getBlockMetadata(posx, posy, posz); 
            } 
            world.setBlock(posx, posy, posz, MFQM.MudBlock, Math.max(metm, metq), 2);
          } 
        } 
      } 
    } 
  }
  
  private void generateMud2(int xx, int yy, int zz, int rad, World world, Random random) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    for (int i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D && (
            validBloodMaterial(posx, posy, posz, world) || validVoreMaterial(posx, posy, posz, world))) {
            if (validPlantMaterial(posx, posy + 1, posz, world))
              world.setBlockToAir(posx, posy + 1, posz); 
            if (validPlantMaterial(posx, posy + 2, posz, world))
              world.setBlockToAir(posx, posy + 2, posz); 
            if (validPlantMaterial(posx, posy + 3, posz, world))
              world.setBlockToAir(posx, posy + 3, posz); 
            if (random.nextInt(4) == 0)
              world.setBlock(posx, posy + 1, posz, MFQM.MudBlock); 
            world.setBlock(posx, posy, posz, MFQM.MudBlock);
            world.setBlock(posx, posy - 1, posz, MFQM.MudBlock);
            world.setBlock(posx, posy - 2, posz, MFQM.MudBlock);
            world.setBlock(posx, posy - 3, posz, MFQM.MudBlock);
            world.setBlock(posx, posy - 4, posz, MFQM.MudBlock);
            world.setBlock(posx, posy - 5, posz, MFQM.MudBlock);
          } 
        } 
      } 
    } 
  }
  
  private void generateMire2(int xx, int yy, int zz, int rad, World world, Random random, Material BlockMaterialToReplace, Block MireBlockId) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    int i;
    for (i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D && (
            world.getBlock(posx, posy, posz) != MFQM.MeatBlock || world.getBlockMetadata(posx, posy, posz) == 0) && (
            BlockMaterialToReplace == Material.cake || world.getBlock(posx, posy, posz).getMaterial() == BlockMaterialToReplace)) {
            if (validPlantMaterial(posx, posy + 1, posz, world))
              world.setBlockToAir(posx, posy + 1, posz); 
            if (validPlantMaterial(posx, posy + 2, posz, world))
              world.setBlockToAir(posx, posy + 2, posz); 
            if (validPlantMaterial(posx, posy + 3, posz, world))
              world.setBlockToAir(posx, posy + 3, posz); 
            if (world.getBlock(posx, posy + 1, posz).getMaterial() == Material.wood && 
              world.getBlock(posx, posy + 2, posz).getMaterial() != Material.wood)
              world.setBlockToAir(posx, posy + 1, posz); 
            world.setBlock(posx, posy, posz, MireBlockId);
            world.setBlock(posx, posy - 1, posz, MireBlockId);
          } 
        } 
      } 
    } 
    for (i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2 + 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          if (world.getBlock(posx, posy, posz) != MireBlockId && (
            world.getBlock(posx - 1, posy, posz) == MireBlockId || world
            .getBlock(posx + 1, posy, posz) == MireBlockId || world
            .getBlock(posx, posy + 1, posz) == MireBlockId || world
            .getBlock(posx, posy, posz - 1) == MireBlockId || world
            .getBlock(posx, posy, posz + 1) == MireBlockId))
            world.setBlock(posx, posy, posz, MFQM.MeatBlock, 0, 2); 
        } 
      } 
    } 
  }
  
  private void generateMossQS(int xx, int yy, int zz, int rad, World world, Random random, Block qsblock) {
    Block sblock = soilBlock(xx, zz, world);
    Block fblock = fillerBlock(xx, zz, world);
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    for (int j = 0; j <= rad / 2; j++) {
      for (int k = 0; k <= rad; k++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + k - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D) {
            if (validPlantMaterial(posx, posy + 1, posz, world))
              world.setBlockToAir(posx, posy + 1, posz); 
            if (validPlantMaterial(posx, posy + 2, posz, world))
              world.setBlockToAir(posx, posy + 2, posz); 
            if (validPlantMaterial(posx, posy + 3, posz, world))
              world.setBlockToAir(posx, posy + 3, posz); 
            if (world.getBlock(posx, posy + 1, posz).getMaterial() == Material.wood && 
              world.getBlock(posx, posy + 2, posz).getMaterial() != Material.wood)
              world.setBlockToAir(posx, posy + 1, posz); 
            if (world.getBlock(posx, posy + 1, posz) != qsblock && 
              !world.getBlock(posx, posy + 1, posz).isOpaqueCube())
              world.setBlock(posx, posy + 1, posz, MFQM.MossBlock); 
            world.setBlock(posx, posy, posz, qsblock);
            world.setBlock(posx, posy - 1, posz, qsblock);
          } 
        } 
      } 
    } 
    for (int i = 0; i <= rad; i++) {
      for (int k = 0; k <= rad / 2 + 2; k++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - k;
          int posz = zz + e - rad / 2;
          if (world.getBlock(posx, posy, posz) != qsblock && (
            world.getBlock(posx - 1, posy, posz) == qsblock || world
            .getBlock(posx + 1, posy, posz) == qsblock || world
            .getBlock(posx, posy + 1, posz) == qsblock || world
            .getBlock(posx, posy, posz - 1) == qsblock || world
            .getBlock(posx, posy, posz + 1) == qsblock))
            if (!world.getBlock(posx, posy + 1, posz).isOpaqueCube()) {
              world.setBlock(posx, posy, posz, sblock);
            } else {
              world.setBlock(posx, posy, posz, fblock);
            }  
        } 
      } 
    } 
  }
  
  private void generateMireOD(int xx, int yy, int zz, int rad, World world, Random random, Material BlockMaterialToReplace, Block MireBlockId, int MireBlockMeta) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    Block aBlock = null;
    int aMeta = 0;
    boolean aDetBlock = false;
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    int i;
    for (i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D && 
            world.getBlock(posx, posy, posz) != MFQM.TarBlock && (
            BlockMaterialToReplace == Material.cake || world.getBlock(posx, posy, posz).getMaterial() == BlockMaterialToReplace)) {
            if (validPlantMaterial(posx, posy + 1, posz, world))
              world.setBlockToAir(posx, posy + 1, posz); 
            if (validPlantMaterial(posx, posy + 2, posz, world))
              world.setBlockToAir(posx, posy + 2, posz); 
            if (validPlantMaterial(posx, posy + 3, posz, world))
              world.setBlockToAir(posx, posy + 3, posz); 
            if (world.getBlock(posx, posy, posz).getMaterial() == Material.wood && 
              world.getBlock(posx, posy + 1, posz).getMaterial() != Material.wood)
              world.setBlockToAir(posx, posy + 1, posz); 
            if (world.getBlock(posx, posy + 1, posz).getMaterial() == Material.wood && 
              world.getBlock(posx, posy + 2, posz).getMaterial() != Material.wood)
              world.setBlockToAir(posx, posy + 1, posz); 
            if (!aDetBlock) {
              aBlock = world.getBlock(posx, posy, posz);
              if (aBlock != null && 
                aBlock.getMaterial().isSolid() && 
                aBlock.isOpaqueCube() && 
                !aBlock.getMaterial().isLiquid() && 
                !aBlock.hasTileEntity()) {
                aMeta = world.getBlockMetadata(posx, posy, posz);
                aDetBlock = true;
              } 
            } 
            if (j == 0) {
              if (world.getBlock(posx, posy, posz).getMaterial() != Material.wood)
                if (world.getBlock(posx, posy + 1, posz).getMaterial() == Material.wood) {
                  world.setBlock(posx, posy, posz, world.getBlock(posx, posy + 1, posz), world.getBlockMetadata(posx, posy + 1, posz), 2);
                } else {
                  world.setBlockToAir(posx, posy, posz);
                }  
              int re = world.rand.nextInt(4);
              switch (re) {
                case 0:
                  if (world.getBlock(posx - 1, posy, posz).getMaterial().isSolid())
                    world.setBlockToAir(posx - 1, posy, posz); 
                  break;
                case 1:
                  if (world.getBlock(posx + 1, posy, posz).getMaterial().isSolid())
                    world.setBlockToAir(posx + 1, posy, posz); 
                  break;
                case 2:
                  if (world.getBlock(posx, posy, posz - 1).getMaterial().isSolid())
                    world.setBlockToAir(posx, posy, posz - 1); 
                  break;
                case 3:
                  if (world.getBlock(posx, posy, posz + 1).getMaterial().isSolid())
                    world.setBlockToAir(posx, posy, posz + 1); 
                  break;
              } 
            } else {
              world.setBlock(posx, posy, posz, MireBlockId, MireBlockMeta, 2);
            } 
            world.setBlock(posx, posy - 1, posz, MireBlockId, MireBlockMeta, 2);
            world.setBlock(posx, posy - 2, posz, aBlock, aMeta, 0);
          } 
        } 
      } 
    } 
    if (aDetBlock)
      for (i = -1; i <= rad + 1; i++) {
        for (int j = -1; j <= rad / 2 + 2; j++) {
          for (int e = -1; e <= rad + 1; e++) {
            int posx = xx + i - rad / 2;
            int posy = yy - j;
            int posz = zz + e - rad / 2;
            if (world.getBlock(posx, posy, posz) != MireBlockId && 
              !validGroundMaterial(posx, posy, posz, world) && (
              world.getBlock(posx - 1, posy, posz) == MireBlockId || world
              .getBlock(posx + 1, posy, posz) == MireBlockId || world
              .getBlock(posx, posy + 1, posz) == MireBlockId || world
              .getBlock(posx, posy, posz - 1) == MireBlockId || world
              .getBlock(posx, posy, posz + 1) == MireBlockId))
              world.setBlock(posx, posy, posz, aBlock, aMeta, 0); 
          } 
        } 
      }  
  }
  
  private void generateMireD(int xx, int yy, int zz, int rad, World world, Random random, Material BlockMaterialToReplace, Block MireBlockId, boolean forced) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    if (MireBlockId == MFQM.MireBlock) {
      if (random.nextInt(2) != 0) {
        generateMire(xx, yy, zz, rad, world, random, BlockMaterialToReplace, MireBlockId);
        return;
      } 
    } else if (random.nextInt(1) != 0) {
      generateMire(xx, yy, zz, rad, world, random, BlockMaterialToReplace, MireBlockId);
      return;
    } 
    if (!forced && world.getBlock(xx, yy, zz) != Blocks.sand && world
      .getBlock(xx, yy, zz) != Blocks.grass && world
      .getBlock(xx, yy, zz) != Blocks.dirt) {
      generateMire(xx, yy, zz, rad, world, random, BlockMaterialToReplace, MireBlockId);
      return;
    } 
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    for (int i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D && 
            world.getBlock(posx, posy, posz) != MFQM.SoftQuicksandBlock && (
            BlockMaterialToReplace == Material.cake || world.getBlock(posx, posy, posz).getMaterial() == BlockMaterialToReplace)) {
            if (validPlantMaterial(posx, posy + 1, posz, world))
              world.setBlockToAir(posx, posy + 1, posz); 
            if (validPlantMaterial(posx, posy + 2, posz, world))
              world.setBlockToAir(posx, posy + 2, posz); 
            if (validPlantMaterial(posx, posy + 3, posz, world))
              world.setBlockToAir(posx, posy + 3, posz); 
            if (world.getBlock(posx, posy, posz).getMaterial() == Material.wood && 
              world.getBlock(posx, posy + 1, posz).getMaterial() != Material.wood)
              world.setBlockToAir(posx, posy + 1, posz); 
            if (world.getBlock(posx, posy + 1, posz).getMaterial() == Material.wood && 
              world.getBlock(posx, posy + 2, posz).getMaterial() != Material.wood)
              world.setBlockToAir(posx, posy + 1, posz); 
            if (j == 0) {
              if (world.getBlock(posx, posy, posz).getMaterial() != Material.wood)
                if (world.getBlock(posx, posy + 1, posz).getMaterial() == Material.wood) {
                  world.setBlock(posx, posy, posz, world.getBlock(posx, posy + 1, posz), world.getBlockMetadata(posx, posy + 1, posz), 2);
                } else {
                  world.setBlockToAir(posx, posy, posz);
                }  
              int re = world.rand.nextInt(4);
              switch (re) {
                case 0:
                  if (world.getBlock(posx - 1, posy, posz).getMaterial().isSolid()) {
                    world.setBlockToAir(posx - 1, posy, posz);
                    if (world.getBlock(posx - 1, posy - 1, posz) == Blocks.dirt)
                      world.setBlock(posx - 1, posy - 1, posz, (Block)Blocks.grass); 
                  } 
                  break;
                case 1:
                  if (world.getBlock(posx + 1, posy, posz).getMaterial().isSolid()) {
                    world.setBlockToAir(posx + 1, posy, posz);
                    if (world.getBlock(posx + 1, posy - 1, posz) == Blocks.dirt)
                      world.setBlock(posx + 1, posy - 1, posz, (Block)Blocks.grass); 
                  } 
                  break;
                case 2:
                  if (world.getBlock(posx, posy, posz - 1).getMaterial().isSolid()) {
                    world.setBlockToAir(posx, posy, posz - 1);
                    if (world.getBlock(posx, posy - 1, posz - 1) == Blocks.dirt)
                      world.setBlock(posx, posy - 1, posz - 1, (Block)Blocks.grass); 
                  } 
                  break;
                case 3:
                  if (world.getBlock(posx, posy, posz + 1).getMaterial().isSolid()) {
                    world.setBlockToAir(posx, posy, posz + 1);
                    if (world.getBlock(posx, posy - 1, posz + 1) == Blocks.dirt)
                      world.setBlock(posx, posy - 1, posz + 1, (Block)Blocks.grass); 
                  } 
                  break;
              } 
            } else {
              world.setBlock(posx, posy, posz, MireBlockId);
            } 
            world.setBlock(posx, posy - 1, posz, MireBlockId);
            world.setBlock(posx, posy - 2, posz, Blocks.stone);
          } 
        } 
      } 
    } 
  }
  
  private void generateLeavesAround(int xx, int yy, int zz, int rad, World world, Random random) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = 1.0D;
    double raz = 1.0D;
    for (int i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D && (
            world.getBlock(posx, posy, posz) == Blocks.air || world.getBlock(posx, posy, posz).getMaterial() == Material.plants)) {
            Material bl = world.getBlock(posx, posy - 1, posz).getMaterial();
            if (bl.isLiquid()) {
              if (world.getBlock(posx - 1, posy, posz).getMaterial().isSolid() || world
                .getBlock(posx + 1, posy, posz).getMaterial().isSolid() || world
                .getBlock(posx, posy, posz - 1).getMaterial().isSolid() || world
                .getBlock(posx, posy, posz + 1).getMaterial().isSolid()) {
                if (random.nextInt(2) == 0)
                  world.setBlock(posx, posy, posz, MFQM.LeavesBlock); 
              } else if (random.nextInt(5) == 0) {
                world.setBlock(posx, posy, posz, MFQM.LeavesBlock);
              } 
            } else if (random.nextInt(3) == 0) {
              world.setBlock(posx, posy, posz, MFQM.LeavesBlock);
            } 
          } 
        } 
      } 
    } 
  }
  
  private void generateHoneyNest(int xx, int yy, int zz, int rad, World world, Random random) {
    int acount = rad * 2;
    int aNeedToGen = rad / 2;
    while (aNeedToGen > 0 && acount > 0) {
      int arad = 16 + random.nextInt(10);
      int posx = xx + random.nextInt(rad) - rad / 2;
      int posy = yy + random.nextInt(rad / 8) - rad / 16;
      int posz = zz + random.nextInt(rad) - rad / 2;
      int ahoney = Math.max((posy - yy) * 4, 0) + 1;
      if (!genIsFlatSolid(posx, posy + 1, posz, arad / 2, world) && 
        genIsHoneycombNear(posx, posy + 1, posz, arad, world)) {
        generateHoneycomb(posx, posy, posz, arad, world, random, ahoney);
        aNeedToGen--;
      } 
      acount--;
    } 
    acount = rad / 2;
    while (acount > 0) {
      int arad = 6 + random.nextInt(6);
      int posx = xx + random.nextInt(rad) - rad / 2;
      int posy = yy + random.nextInt(rad / 8) - rad / 16;
      int posz = zz + random.nextInt(rad) - rad / 2;
      generateSolidHoney(posx, posy, posz, arad, world, random);
      acount--;
    } 
    for (int j = 0; j <= rad * 2; j++) {
      for (int i = 0; i <= rad * 4; i++) {
        for (int e = 0; e <= rad * 4; e++) {
          int posx = xx + i - rad * 2;
          int posy = yy + j - rad;
          int posz = zz + e - rad * 2;
          boolean isHon = (world.getBlock(posx, posy, posz) == MFQM.HoneyBlock || (world.getBlock(posx, posy, posz) == MFQM.SolidHoneyBlock && world.getBlockMetadata(posx, posy, posz) > 0));
          if (isHon) {
            if (world.getBlock(posx + 1, posy, posz) == MFQM.HoneycombBlock && world
              .getBlockMetadata(posx + 1, posy, posz) != 3)
              world.setBlock(posx + 1, posy, posz, MFQM.SolidHoneyBlock, 0, 0); 
            if (world.getBlock(posx - 1, posy, posz) == MFQM.HoneycombBlock && world
              .getBlockMetadata(posx - 1, posy, posz) != 3)
              world.setBlock(posx - 1, posy, posz, MFQM.SolidHoneyBlock, 0, 0); 
            if (world.getBlock(posx, posy - 1, posz) == MFQM.HoneycombBlock && world
              .getBlockMetadata(posx, posy - 1, posz) != 3)
              world.setBlock(posx, posy - 1, posz, MFQM.SolidHoneyBlock, 0, 0); 
            if (world.getBlock(posx, posy, posz + 1) == MFQM.HoneycombBlock && world
              .getBlockMetadata(posx, posy, posz + 1) != 3)
              world.setBlock(posx, posy, posz + 1, MFQM.SolidHoneyBlock, 0, 0); 
            if (world.getBlock(posx, posy, posz - 1) == MFQM.HoneycombBlock && world
              .getBlockMetadata(posx, posy, posz - 1) != 3)
              world.setBlock(posx, posy, posz - 1, MFQM.SolidHoneyBlock, 0, 0); 
          } 
        } 
      } 
    } 
    aNeedToGen = rad / 8 + 2;
    acount = rad * 64;
    while (aNeedToGen > 0 && acount > 0) {
      int posx = xx + random.nextInt(rad) - rad / 2;
      int posy = yy + random.nextInt(rad) - rad / 2;
      int posz = zz + random.nextInt(rad) - rad / 2;
      if (world.getBlock(posx, posy, posz) == MFQM.HoneycombBlock && world.getBlockMetadata(posx, posy, posz) != 3 && 
        world.getBlock(posx - 1, posy, posz) == MFQM.HoneycombBlock && world.getBlockMetadata(posx - 1, posy, posz) != 3 && 
        world.getBlock(posx + 1, posy, posz) == MFQM.HoneycombBlock && world.getBlockMetadata(posx + 1, posy, posz) != 3 && 
        world.getBlock(posx, posy - 1, posz) == MFQM.HoneycombBlock && world.getBlockMetadata(posx, posy - 1, posz) != 3 && 
        world.getBlock(posx, posy + 1, posz) == MFQM.HoneycombBlock && world.getBlockMetadata(posx, posy + 1, posz) != 3 && 
        world.getBlock(posx, posy, posz - 1) == MFQM.HoneycombBlock && world.getBlockMetadata(posx, posy, posz - 1) != 3 && 
        world.getBlock(posx, posy, posz + 1) == MFQM.HoneycombBlock && world.getBlockMetadata(posx, posy, posz + 1) != 3) {
        world.setBlock(posx, posy, posz, Blocks.mob_spawner, 0, 2);
        TileEntityMobSpawner var18 = (TileEntityMobSpawner)world.getTileEntity(posx, posy, posz);
        if (var18 != null) {
          NBTTagCompound nbt = new NBTTagCompound();
          var18.writeToNBT(nbt);
          nbt.setString("EntityId", "MFQM.Bee");
          nbt.setShort("MaxNearbyEntities", (short)16);
          nbt.setShort("RequiredPlayerRange", (short)48);
          nbt.setShort("SpawnRange", (short)24);
          nbt.setShort("MinSpawnDelay", (short)400);
          nbt.setShort("MaxSpawnDelay", (short)1600);
          var18.readFromNBT(nbt);
          aNeedToGen--;
        } else {
          System.err.println("(MFQM)Failed to fetch mob spawner entity");
        } 
      } 
      acount--;
    } 
    aNeedToGen = rad / 24 + 1;
    acount = rad * 16;
    while (aNeedToGen > 0 && acount > 0) {
      int posx = xx + random.nextInt(rad / 2) - rad / 4;
      int posy = yy + random.nextInt(rad / 8) - rad / 16;
      int posz = zz + random.nextInt(rad / 2) - rad / 4;
      if (((world.getBlock(posx, posy, posz) == MFQM.HoneycombBlock && world.getBlockMetadata(posx, posy, posz) == 3) || world
        .getBlock(posx, posy, posz) == Blocks.air) && ((
        world.getBlock(posx, posy + 1, posz) == MFQM.HoneycombBlock && world.getBlockMetadata(posx, posy + 1, posz) == 3) || world
        .getBlock(posx, posy + 1, posz) == Blocks.air) && (
        world.getBlock(posx, posy - 1, posz) != MFQM.HoneycombBlock || world.getBlockMetadata(posx, posy - 1, posz) != 3) && 
        !world.getBlock(posx, posy - 1, posz).getMaterial().isLiquid()) {
        generateHoneyChest(world, posx, posy, posz, 0);
        aNeedToGen--;
      } 
      acount--;
    } 
  }
  
  public void generateHoneyChest(World world, int x, int y, int z, int meta) {
    world.setBlock(x, y, z, (Block)Blocks.chest, meta, 2);
    TileEntityChest var12 = (TileEntityChest)world.getTileEntity(x, y, z);
    ItemStack tmpStack = new ItemStack(Items.diamond, 1, 0);
    for (int i = 31999; i > 1; i--) {
      Item tmp = Item.getItemById(i);
      if (tmp != null) {
        Item tmp2 = Item.getItemById(i - world.rand.nextInt(i - 1));
        if (tmp2 != null) {
          int tmpMeta, tmpCount;
          if (tmp2.getItemStackLimit() != 1 || !tmp2.isDamageable()) {
            tmpMeta = world.rand.nextInt(Math.max(tmp2.getMaxDurability(), 1));
            int maxCount = Math.max(tmp2.getItemStackLimit() - 1, 1);
            int partCount = 1;
            tmpCount = 1;
            if (maxCount > 4) {
              partCount = Math.max(maxCount / 4, 1);
              tmpCount += world.rand.nextInt(partCount);
              if (world.rand.nextInt(partCount) == 0)
                tmpCount += world.rand.nextInt(partCount + 1); 
              if (world.rand.nextInt(partCount) == 0)
                tmpCount += world.rand.nextInt(partCount + 1); 
              if (world.rand.nextInt(partCount) == 0)
                tmpCount += world.rand.nextInt(partCount + 1); 
            } else {
              tmpCount = world.rand.nextInt(maxCount) + 1;
            } 
          } else {
            tmpMeta = 0;
            tmpCount = 1;
          } 
          tmpStack = new ItemStack(tmp2, tmpCount, tmpMeta);
          break;
        } 
      } 
    } 
    if (var12 != null) {
      var12.setCustomName("Honey Chest");
      WeightedRandomChestContent[] content = ChestGenHooks.getItems("dungeonChest", world.rand);
      List<WeightedRandomChestContent> listFromArray = Arrays.asList(content);
      List<WeightedRandomChestContent> tempList = new ArrayList<WeightedRandomChestContent>(listFromArray);
      tempList.add(new WeightedRandomChestContent(Items.diamond, 0, 1, 3, 16));
      tempList.add(new WeightedRandomChestContent(Items.gold_ingot, 0, 4, 16, 16));
      tempList.add(new WeightedRandomChestContent(Items.emerald, 0, 1, 3, 16));
      WeightedRandomChestContent[] tempArray = new WeightedRandomChestContent[tempList.size()];
      content = tempList.<WeightedRandomChestContent>toArray(tempArray);
      WeightedRandomChestContent.generateChestContents(world.rand, content, (IInventory)var12, 5 + world.rand.nextInt(5));
      var12.setInventorySlotContents(world.rand.nextInt(var12.getSizeInventory()), tmpStack);
    } 
  }
  
  private void generateHoneycomb(int xx, int yy, int zz, int rad, World world, Random random, int honey) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    int aType = random.nextInt(3);
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    int honeyHeight = 0;
    int min_posy = 999;
    double minRad = rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) / (1.5D + random.nextDouble());
    int j;
    for (j = 0; j <= rad; j++) {
      for (int i = 0; i <= rad; i++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy + j - rad / 2;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D)) {
            boolean isHon = (world.getBlock(posx, posy, posz) == MFQM.HoneyBlock || (world.getBlock(posx, posy, posz) == MFQM.SolidHoneyBlock && world.getBlockMetadata(posx, posy, posz) > 0));
            if (isHon)
              honeyHeight = posy; 
            if (dis < minRad) {
              if (min_posy > posy)
                min_posy = posy; 
            } else if (world.getBlock(posx, posy, posz) != MFQM.HoneycombBlock || world.getBlockMetadata(posx, posy, posz) != 3) {
              if (world.getBlock(posx, posy, posz).getMaterial().isLiquid())
                world.setBlockToAir(posx, posy, posz); 
              if (random.nextInt(3) == 0) {
                world.setBlock(posx, posy, posz, MFQM.HoneycombBlock, random.nextInt(3), 0);
              } else {
                world.setBlock(posx, posy, posz, MFQM.HoneycombBlock, aType, 0);
              } 
            } 
          } 
        } 
      } 
    } 
    if (honeyHeight < 1 && 
      honey < 10 && 
      random.nextInt(honey) == 0)
      honeyHeight = min_posy + 1 + random.nextInt(2); 
    for (j = 0; j <= rad; j++) {
      for (int i = 0; i <= rad; i++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy + j - rad / 2;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < minRad) {
            if (world.getBlock(posx, posy, posz).getMaterial().isLiquid())
              world.setBlockToAir(posx, posy, posz); 
            if (posy <= honeyHeight) {
              world.setBlock(posx, posy, posz, MFQM.SolidHoneyBlock, 15, 0);
            } else {
              world.setBlock(posx, posy, posz, MFQM.HoneycombBlock, 3, 0);
            } 
          } 
        } 
      } 
    } 
  }
  
  private void generateSolidHoney(int xx, int yy, int zz, int rad, World world, Random random) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    boolean water = true;
    if (random.nextInt(3) == 0)
      water = false; 
    for (int j = 0; j <= rad; j++) {
      for (int i = 0; i <= rad; i++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy + j - rad / 2;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D)) {
            boolean isHon = (world.getBlock(posx, posy, posz) == MFQM.HoneyBlock || (world.getBlock(posx, posy, posz) == MFQM.SolidHoneyBlock && world.getBlockMetadata(posx, posy, posz) > 0));
            if (isHon) {
              if (water && 
                world.getBlock(posx, posy + 1, posz) != MFQM.HoneyBlock && world
                .getBlock(posx, posy + 1, posz) != MFQM.SolidHoneyBlock) {
                world.setBlockToAir(posx, posy, posz);
                world.setBlock(posx, posy, posz, MFQM.SolidHoneyBlock, 0, 0);
              } 
            } else if (!water && 
              world.getBlock(posx, posy, posz) == MFQM.HoneycombBlock && world.getBlockMetadata(posx, posy, posz) != 3) {
              world.setBlock(posx, posy, posz, MFQM.SolidHoneyBlock, 0, 0);
            } 
          } 
        } 
      } 
    } 
  }
  
  private void generateBlood(int xx, int yy, int zz, int rad, World world, Random random, Material BlockMaterialToReplace) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    for (int i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D && (
            world.getBlock(posx, posy, posz).getMaterial() == BlockMaterialToReplace || (BlockMaterialToReplace == Material.cake && validBloodMaterial(posx, posy, posz, world))))
            world.setBlock(posx, posy, posz, MFQM.MeatBlock, 0, 0); 
        } 
      } 
    } 
  }
  
  private void generateBlood2(int xx, int yy, int zz, int rad, World world, Random random) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    for (int i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad * 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy + j - rad;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D && (
            validBloodMaterial(posx, posy, posz, world) || (validLiqMaterial(posx, posy, posz, world) && world.getBlock(posx, posy, posz) != MFQM.AcidBlock && world.getBlock(posx, posy, posz) != MFQM.SlurryBlock && world.getBlockMetadata(posx, posy, posz) == 0)))
            world.setBlock(posx, posy, posz, MFQM.MeatBlock, 0, 2); 
        } 
      } 
    } 
  }
  
  private void generateBlood3(int xx, int yy, int zz, int rad, World world, Random random) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    yy += rad / 4;
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    for (int i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy + j - rad / 2;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (world.getBlock(posx, posy, posz) != MFQM.MeatBlock && 
            posy > yy - rad / 4 && 
            dis < rad / 2.0D)
            world.setBlock(posx, posy, posz, MFQM.MeatBlock, 0, 2); 
        } 
      } 
    } 
  }
  
  private void generateVore(int xx, int yy, int zz, World world, Random random) {
    if (random.nextInt(2) == 0) {
      genColumnBlockMeta(xx - 1, yy, zz - 1, world, 5, MFQM.MeatBlock, 2);
      genColumnBlockMeta(xx, yy, zz - 1, world, 5, MFQM.MeatBlock, 7);
      genColumnBlockMeta(xx + 1, yy, zz - 1, world, 5, MFQM.MeatBlock, 7);
      genColumnBlockMeta(xx + 2, yy, zz - 1, world, 5, MFQM.MeatBlock, 3);
      genColumnBlockMeta(xx - 1, yy, zz, world, 5, MFQM.MeatBlock, 9);
      genColumnBlockMeta(xx + 2, yy, zz, world, 5, MFQM.MeatBlock, 8);
      genColumnBlockMeta(xx - 1, yy, zz + 1, world, 5, MFQM.MeatBlock, 9);
      genColumnBlockMeta(xx + 2, yy, zz + 1, world, 5, MFQM.MeatBlock, 8);
      genColumnBlockMeta(xx - 1, yy, zz + 2, world, 5, MFQM.MeatBlock, 5);
      genColumnBlockMeta(xx, yy, zz + 2, world, 5, MFQM.MeatBlock, 6);
      genColumnBlockMeta(xx + 1, yy, zz + 2, world, 5, MFQM.MeatBlock, 6);
      genColumnBlockMeta(xx + 2, yy, zz + 2, world, 5, MFQM.MeatBlock, 4);
      world.setBlock(xx - 1, yy - 5, zz - 1, MFQM.MeatBlock, 0, 0);
      world.setBlock(xx, yy - 5, zz - 1, MFQM.MeatBlock, 0, 0);
      world.setBlock(xx + 1, yy - 5, zz - 1, MFQM.MeatBlock, 0, 0);
      world.setBlock(xx + 2, yy - 5, zz - 1, MFQM.MeatBlock, 0, 0);
      world.setBlock(xx - 1, yy - 5, zz, MFQM.MeatBlock, 0, 0);
      world.setBlock(xx, yy - 5, zz, MFQM.MeatBlock, 0, 0);
      world.setBlock(xx + 1, yy - 5, zz, MFQM.MeatBlock, 0, 0);
      world.setBlock(xx + 2, yy - 5, zz, MFQM.MeatBlock, 0, 0);
      world.setBlock(xx - 1, yy - 5, zz + 1, MFQM.MeatBlock, 0, 0);
      world.setBlock(xx, yy - 5, zz + 1, MFQM.MeatBlock, 0, 0);
      world.setBlock(xx + 1, yy - 5, zz + 1, MFQM.MeatBlock, 0, 0);
      world.setBlock(xx + 2, yy - 5, zz + 1, MFQM.MeatBlock, 0, 0);
      world.setBlock(xx - 1, yy - 5, zz + 2, MFQM.MeatBlock, 0, 0);
      world.setBlock(xx, yy - 5, zz + 2, MFQM.MeatBlock, 0, 0);
      world.setBlock(xx + 1, yy - 5, zz + 2, MFQM.MeatBlock, 0, 0);
      world.setBlock(xx + 2, yy - 5, zz + 2, MFQM.MeatBlock, 0, 0);
      world.setBlock(xx, yy - 6, zz, MFQM.MeatBlock, 0, 0);
      world.setBlock(xx, yy - 6, zz + 1, MFQM.MeatBlock, 0, 0);
      world.setBlock(xx + 1, yy - 6, zz, MFQM.MeatBlock, 0, 0);
      world.setBlock(xx + 1, yy - 6, zz + 1, MFQM.MeatBlock, 0, 0);
      world.setBlock(xx, yy - 5, zz, MFQM.MeatBlock, 1, 0);
      world.setBlock(xx, yy - 5, zz + 1, MFQM.MeatBlock, 1, 0);
      world.setBlock(xx + 1, yy - 5, zz, MFQM.MeatBlock, 1, 0);
      world.setBlock(xx + 1, yy - 5, zz + 1, MFQM.MeatBlock, 1, 0);
      world.setBlock(xx, yy - 4, zz, MFQM.AcidBlock);
      world.setBlock(xx, yy - 4, zz + 1, MFQM.AcidBlock);
      world.setBlock(xx + 1, yy - 4, zz, MFQM.AcidBlock);
      world.setBlock(xx + 1, yy - 4, zz + 1, MFQM.AcidBlock);
      world.setBlock(xx, yy - 3, zz, MFQM.AcidBlock);
      world.setBlock(xx, yy - 3, zz + 1, MFQM.AcidBlock);
      world.setBlock(xx + 1, yy - 3, zz, MFQM.AcidBlock);
      world.setBlock(xx + 1, yy - 3, zz + 1, MFQM.AcidBlock);
      world.setBlockToAir(xx, yy - 2, zz);
      world.setBlockToAir(xx, yy - 2, zz + 1);
      world.setBlockToAir(xx + 1, yy - 2, zz);
      world.setBlockToAir(xx + 1, yy - 2, zz + 1);
      world.setBlockToAir(xx, yy - 1, zz);
      world.setBlockToAir(xx, yy - 1, zz + 1);
      world.setBlockToAir(xx + 1, yy - 1, zz);
      world.setBlockToAir(xx + 1, yy - 1, zz + 1);
      world.setBlockToAir(xx, yy, zz);
      world.setBlockToAir(xx, yy, zz + 1);
      world.setBlockToAir(xx + 1, yy, zz);
      world.setBlockToAir(xx + 1, yy, zz + 1);
    } else {
      genColumnBlockMeta(xx - 1, yy, zz, world, 5, MFQM.MeatBlock, 9);
      genColumnBlockMeta(xx + 1, yy, zz, world, 5, MFQM.MeatBlock, 8);
      genColumnBlockMeta(xx, yy, zz - 1, world, 5, MFQM.MeatBlock, 7);
      genColumnBlockMeta(xx, yy, zz + 1, world, 5, MFQM.MeatBlock, 6);
      genColumnBlockMeta(xx - 1, yy, zz - 1, world, 5, MFQM.MeatBlock, 2);
      genColumnBlockMeta(xx + 1, yy, zz - 1, world, 5, MFQM.MeatBlock, 3);
      genColumnBlockMeta(xx + 1, yy, zz + 1, world, 5, MFQM.MeatBlock, 4);
      genColumnBlockMeta(xx - 1, yy, zz + 1, world, 5, MFQM.MeatBlock, 5);
      world.setBlock(xx - 1, yy - 5, zz - 1, MFQM.MeatBlock, 0, 0);
      world.setBlock(xx - 1, yy - 5, zz, MFQM.MeatBlock, 0, 0);
      world.setBlock(xx - 1, yy - 5, zz + 1, MFQM.MeatBlock, 0, 0);
      world.setBlock(xx, yy - 5, zz - 1, MFQM.MeatBlock, 0, 0);
      world.setBlock(xx, yy - 5, zz, MFQM.MeatBlock, 0, 0);
      world.setBlock(xx, yy - 5, zz + 1, MFQM.MeatBlock, 0, 0);
      world.setBlock(xx + 1, yy - 5, zz - 1, MFQM.MeatBlock, 0, 0);
      world.setBlock(xx + 1, yy - 5, zz, MFQM.MeatBlock, 0, 0);
      world.setBlock(xx + 1, yy - 5, zz + 1, MFQM.MeatBlock, 0, 0);
      world.setBlock(xx, yy - 6, zz, MFQM.MeatBlock, 0, 0);
      world.setBlock(xx, yy - 5, zz, MFQM.MeatBlock, 1, 0);
      world.setBlock(xx, yy - 4, zz, MFQM.AcidBlock);
      world.setBlock(xx, yy - 3, zz, MFQM.AcidBlock);
      world.setBlockToAir(xx, yy - 2, zz);
      world.setBlockToAir(xx, yy - 1, zz);
      world.setBlockToAir(xx, yy, zz);
    } 
  }
  
  private void genColumnBlockMeta(int xx, int yy, int zz, World world, int count, Block blockId, int meta) {
    for (int i = 0; i < count; i++)
      world.setBlock(xx, yy - i, zz, blockId, meta, 0); 
  }
  
  private void preventLiquid(int xx, int yy, int zz, World world, Block blockId, int meta) {
    if (world.getBlock(xx + 1, yy, zz).getMaterial().isLiquid())
      world.setBlock(xx + 1, yy, zz, blockId, meta, 2); 
    if (world.getBlock(xx - 1, yy, zz).getMaterial().isLiquid())
      world.setBlock(xx - 1, yy, zz, blockId, meta, 2); 
    if (world.getBlock(xx, yy, zz - 1).getMaterial().isLiquid())
      world.setBlock(xx, yy, zz - 1, blockId, meta, 2); 
    if (world.getBlock(xx, yy, zz + 1).getMaterial().isLiquid())
      world.setBlock(xx, yy, zz + 1, blockId, meta, 2); 
  }
  
  private void generateSlurry(int xx, int yy, int zz, int rad, World world, Random random) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    int stumb = 0;
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    int i;
    for (i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D) {
            if (j == 0 && 
              world.getBlock(posx, posy + 1, posz) != MFQM.SlurryBlock && 
              !validLiqMaterial(posx, posy + 2, posz, world)) {
              if (world.getBlockMetadata(posx, posy + 1, posz) == 10)
                world.setBlock(posx, posy + 2, posz, MFQM.MeatBlock, 10, 2); 
              world.setBlockToAir(posx, posy + 1, posz);
              preventLiquid(posx, posy + 1, posz, world, MFQM.MudBlock, 0);
              if (world.getBlock(posx, posy + 2, posz) != MFQM.SlurryBlock && 
                !validLiqMaterial(posx, posy + 3, posz, world)) {
                if (world.getBlockMetadata(posx, posy + 2, posz) == 10)
                  world.setBlock(posx, posy + 3, posz, MFQM.MeatBlock, 10, 2); 
                world.setBlockToAir(posx, posy + 2, posz);
                preventLiquid(posx, posy + 2, posz, world, MFQM.MudBlock, 0);
                if (random.nextInt(2) == 0 && 
                  world.getBlock(posx, posy + 3, posz) != MFQM.SlurryBlock && 
                  !validLiqMaterial(posx, posy + 4, posz, world)) {
                  if (world.getBlockMetadata(posx, posy + 3, posz) == 10)
                    world.setBlock(posx, posy + 4, posz, MFQM.MeatBlock, 10, 2); 
                  world.setBlockToAir(posx, posy + 3, posz);
                  preventLiquid(posx, posy + 3, posz, world, MFQM.MudBlock, 0);
                  if (random.nextInt(3) == 0 && 
                    world.getBlock(posx, posy + 4, posz) != MFQM.SlurryBlock && 
                    !validLiqMaterial(posx, posy + 5, posz, world)) {
                    if (world.getBlockMetadata(posx, posy + 4, posz) == 10)
                      world.setBlock(posx, posy + 5, posz, MFQM.MeatBlock, 10, 2); 
                    world.setBlockToAir(posx, posy + 4, posz);
                    preventLiquid(posx, posy + 4, posz, world, MFQM.MudBlock, 0);
                    if (random.nextInt(4) == 0 && 
                      world.getBlock(posx, posy + 5, posz) != MFQM.SlurryBlock && 
                      !validLiqMaterial(posx, posy + 6, posz, world)) {
                      if (world.getBlockMetadata(posx, posy + 5, posz) == 10)
                        world.setBlock(posx, posy + 6, posz, MFQM.MeatBlock, 10, 2); 
                      world.setBlockToAir(posx, posy + 5, posz);
                      preventLiquid(posx, posy + 5, posz, world, MFQM.MudBlock, 0);
                    } 
                  } 
                } 
              } 
            } 
            if (world.rand.nextInt(20) == 0 && j == 0) {
              world.setBlock(posx, posy, posz, MFQM.MudBlock);
            } else {
              world.setBlock(posx, posy, posz, MFQM.SlurryBlock);
            } 
            world.setBlock(posx, posy - 1, posz, MFQM.SlurryBlock);
            world.setBlock(posx, posy - 2, posz, MFQM.MudBlock);
          } 
        } 
      } 
    } 
    for (i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2 + 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          if (world.getBlock(posx, posy, posz) != MFQM.SlurryBlock && 
            !validGroundMaterial(posx, posy, posz, world) && (
            world.getBlock(posx - 1, posy, posz) == MFQM.SlurryBlock || world
            .getBlock(posx + 1, posy, posz) == MFQM.SlurryBlock || world
            .getBlock(posx + 1, posy, posz) == MFQM.SlurryBlock || world
            .getBlock(posx, posy + 1, posz) == MFQM.SlurryBlock || world
            .getBlock(posx, posy, posz - 1) == MFQM.SlurryBlock || world
            .getBlock(posx, posy, posz + 1) == MFQM.SlurryBlock))
            world.setBlock(posx, posy, posz, MFQM.MudBlock); 
        } 
      } 
    } 
  }
  
  private void ClearArea3(int xx, int yy, int zz, int rad, World world, Random random) {
    for (int i = 0; i <= rad; i++) {
      for (int j = 0; j <= 3; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j - 1;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D));
          if (dis < rad / 2.0D)
            world.setBlockToAir(posx, posy, posz); 
        } 
      } 
    } 
  }
  
  private boolean generateAss(int xx, int yy, int zz, int rad, World world, Random random) {
    int firstBlockYCoord = 0;
    boolean canGen = false;
    while (firstBlockYCoord < 16) {
      if (validVoreMaterial(xx, yy + firstBlockYCoord, zz, world) && 
        validVoreMaterial(xx, yy + firstBlockYCoord + 1, zz, world)) {
        canGen = true;
        break;
      } 
      if (!world.getBlock(xx, yy + firstBlockYCoord, zz).getMaterial().isOpaque())
        firstBlockYCoord++; 
    } 
    if (canGen && firstBlockYCoord > 2) {
      if (random.nextInt(2) == 0)
        firstBlockYCoord++; 
      world.setBlock(xx, yy + firstBlockYCoord, zz, MFQM.MeatBlock, 10, 2);
      for (int i = 1; i <= 5; i++) {
        world.setBlock(xx, yy + firstBlockYCoord + i, zz, MFQM.MeatBlock, 0, 2);
        world.setBlock(xx + 1, yy + firstBlockYCoord + i - 1, zz, MFQM.MeatBlock, 0, 2);
        world.setBlock(xx - 1, yy + firstBlockYCoord + i - 1, zz, MFQM.MeatBlock, 0, 2);
        world.setBlock(xx, yy + firstBlockYCoord + i - 1, zz + 1, MFQM.MeatBlock, 0, 2);
        world.setBlock(xx, yy + firstBlockYCoord + i - 1, zz - 1, MFQM.MeatBlock, 0, 2);
      } 
      generateBlood3(xx, yy + firstBlockYCoord + 1, zz, 12, world, random);
      generateBlood3(xx + random.nextInt(9) - 4, yy + firstBlockYCoord + 3, zz + random.nextInt(9) - 4, 7 + random.nextInt(5), world, random);
      generateBlood3(xx + random.nextInt(9) - 4, yy + firstBlockYCoord + 3, zz + random.nextInt(9) - 4, 7 + random.nextInt(5), world, random);
      generateBlood3(xx + random.nextInt(9) - 4, yy + firstBlockYCoord + 3, zz + random.nextInt(9) - 4, 7 + random.nextInt(5), world, random);
      generateBlood3(xx + random.nextInt(11) - 5, yy + firstBlockYCoord + 5, zz + random.nextInt(11) - 5, 10 + random.nextInt(5), world, random);
      generateBlood3(xx + random.nextInt(11) - 5, yy + firstBlockYCoord + 5, zz + random.nextInt(11) - 5, 10 + random.nextInt(5), world, random);
      generateBlood3(xx + random.nextInt(11) - 5, yy + firstBlockYCoord + 5, zz + random.nextInt(11) - 5, 10 + random.nextInt(5), world, random);
      generateBlood3(xx + random.nextInt(11) - 5, yy + firstBlockYCoord + 5, zz + random.nextInt(11) - 5, 10 + random.nextInt(5), world, random);
      generateBlood3(xx + random.nextInt(11) - 5, yy + firstBlockYCoord + 5, zz + random.nextInt(11) - 5, 10 + random.nextInt(5), world, random);
      generateBlood3(xx + random.nextInt(11) - 5, yy + firstBlockYCoord + 5, zz + random.nextInt(11) - 5, 10 + random.nextInt(5), world, random);
      ClearArea3(xx, yy + firstBlockYCoord, zz, rad, world, random);
      return true;
    } 
    return false;
  }
  
  private void generateSlime(int xx, int yy, int zz, int rad, World world, Random random) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    Block aBlock = null;
    int aMeta = 0;
    boolean aDetBlock = false;
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    int i;
    for (i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D) {
            if (j == 0 && 
              validCaveMaterial(posx, posy + 1, posz, world) && 
              !validWaterMaterial(posx, posy + 2, posz, world) && !isAboveFreeSpace(posx, posy + 1, posz, world)) {
              world.setBlockToAir(posx, posy + 1, posz);
              if (validCaveMaterial(posx, posy + 2, posz, world) && 
                !validWaterMaterial(posx, posy + 3, posz, world) && !isAboveFreeSpace(posx, posy + 2, posz, world)) {
                world.setBlockToAir(posx, posy + 2, posz);
                if (random.nextInt(1) == 0 && 
                  validCaveMaterial(posx, posy + 3, posz, world) && 
                  !validWaterMaterial(posx, posy + 4, posz, world) && !isAboveFreeSpace(posx, posy + 3, posz, world)) {
                  world.setBlockToAir(posx, posy + 3, posz);
                  if (random.nextInt(2) == 0 && 
                    validCaveMaterial(posx, posy + 4, posz, world) && 
                    !validWaterMaterial(posx, posy + 5, posz, world) && !isAboveFreeSpace(posx, posy + 4, posz, world)) {
                    world.setBlockToAir(posx, posy + 4, posz);
                    if (random.nextInt(3) == 0 && 
                      validCaveMaterial(posx, posy + 5, posz, world) && 
                      !validWaterMaterial(posx, posy + 6, posz, world) && !isAboveFreeSpace(posx, posy + 5, posz, world))
                      world.setBlockToAir(posx, posy + 5, posz); 
                  } 
                } 
              } 
            } 
            if (!aDetBlock && world.provider.dimensionId != 0) {
              aBlock = world.getBlock(posx, posy, posz);
              if (aBlock != null && 
                aBlock.getMaterial().isSolid() && 
                aBlock.isOpaqueCube() && 
                !aBlock.getMaterial().isLiquid() && 
                !aBlock.hasTileEntity()) {
                aMeta = world.getBlockMetadata(posx, posy, posz);
                aDetBlock = true;
              } 
            } else {
              world.setBlock(posx, posy, posz, MFQM.SlimeBlock);
              world.setBlock(posx, posy - 1, posz, MFQM.SlimeBlock);
              if (world.provider.dimensionId == 0) {
                world.setBlock(posx, posy - 2, posz, Blocks.stone);
              } else {
                world.setBlock(posx, posy - 2, posz, aBlock, aMeta, 0);
              } 
            } 
          } 
        } 
      } 
    } 
    if (aDetBlock || world.provider.dimensionId == 0)
      for (i = -1; i <= rad + 1; i++) {
        for (int j = -1; j <= rad / 4 + 2; j++) {
          for (int e = -1; e <= rad + 1; e++) {
            int posx = xx + i - rad / 2;
            int posy = yy - j;
            int posz = zz + e - rad / 2;
            if (world.getBlock(posx, posy, posz) != MFQM.SlimeBlock && 
              !validGroundMaterial(posx, posy, posz, world) && (
              world.getBlock(posx - 1, posy, posz) == MFQM.SlimeBlock || world
              .getBlock(posx + 1, posy, posz) == MFQM.SlimeBlock || world
              .getBlock(posx, posy + 1, posz) == MFQM.SlimeBlock || world
              .getBlock(posx, posy, posz - 1) == MFQM.SlimeBlock || world
              .getBlock(posx, posy, posz + 1) == MFQM.SlimeBlock))
              if (world.provider.dimensionId == 0) {
                world.setBlock(posx, posy, posz, Blocks.stone);
              } else {
                world.setBlock(posx, posy, posz, aBlock, aMeta, 0);
              }  
          } 
        } 
      }  
  }
  
  private void generateCaveWebbing(int xx, int yy, int zz, int rad, World world, Random random) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    int i;
    for (i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D) {
            if (j == 0 && 
              validCaveMaterial(posx, posy + 1, posz, world) && 
              !validWaterMaterial(posx, posy + 2, posz, world)) {
              world.setBlockToAir(posx, posy + 1, posz);
              if (validCaveMaterial(posx, posy + 2, posz, world) && 
                !validWaterMaterial(posx, posy + 3, posz, world)) {
                world.setBlockToAir(posx, posy + 2, posz);
                if (random.nextInt(1) == 0 && 
                  validCaveMaterial(posx, posy + 3, posz, world) && 
                  !validWaterMaterial(posx, posy + 4, posz, world)) {
                  world.setBlockToAir(posx, posy + 3, posz);
                  if (random.nextInt(2) == 0 && 
                    validCaveMaterial(posx, posy + 4, posz, world) && 
                    !validWaterMaterial(posx, posy + 5, posz, world)) {
                    world.setBlockToAir(posx, posy + 4, posz);
                    if (random.nextInt(3) == 0 && 
                      validCaveMaterial(posx, posy + 5, posz, world) && 
                      !validWaterMaterial(posx, posy + 6, posz, world))
                      world.setBlockToAir(posx, posy + 5, posz); 
                  } 
                } 
              } 
            } 
            world.setBlock(posx, posy, posz, MFQM.DenseWebBlock);
            world.setBlock(posx, posy - 1, posz, MFQM.DenseWebBlock);
            world.setBlock(posx, posy - 2, posz, Blocks.stone);
          } 
        } 
      } 
    } 
    for (i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2 + 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          if (world.getBlock(posx, posy, posz) != MFQM.DenseWebBlock && 
            !validGroundMaterial(posx, posy, posz, world) && (
            world.getBlock(posx - 1, posy, posz) == MFQM.DenseWebBlock || world
            .getBlock(posx + 1, posy, posz) == MFQM.DenseWebBlock || world
            .getBlock(posx, posy + 1, posz) == MFQM.DenseWebBlock || world
            .getBlock(posx, posy, posz - 1) == MFQM.DenseWebBlock || world
            .getBlock(posx, posy, posz + 1) == MFQM.DenseWebBlock))
            world.setBlock(posx, posy, posz, Blocks.stone); 
        } 
      } 
    } 
  }
  
  private void generateLiquidMire(int xx, int yy, int zz, int rad, World world, Random random, Material BlockMaterialToReplace, int isWL) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    for (int i = 0; i <= rad; i++) {
      for (int k = 0; k <= rad / 2; k++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - k;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D && (
            world.getBlock(posx, posy, posz) == MFQM.MireBlock || BlockMaterialToReplace == Material.cake || world.getBlock(posx, posy, posz).getMaterial() == BlockMaterialToReplace)) {
            world.setBlock(posx, posy, posz, MFQM.StableLiquidMireBlock);
            if (BlockMaterialToReplace == Material.cake || world.getBlock(posx, posy - 1, posz).getMaterial() != BlockMaterialToReplace)
              world.setBlock(posx, posy - 1, posz, MFQM.MireBlock); 
            if (isWL > 0 && 
              random.nextInt(75 / isWL) == 0 && 
              world.getBlock(posx, posy + 1, posz) == Blocks.air)
              world.setBlock(posx, posy + 1, posz, MFQM.CustomLilyPadBlock, 0, 2); 
          } 
        } 
      } 
    } 
    Block sblock = soilBlock(xx, zz, world);
    Block fblock = fillerBlock(xx, zz, world);
    for (int j = 0; j <= rad; j++) {
      for (int k = 0; k <= rad / 2 + 2; k++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + j - rad / 2;
          int posy = yy - k;
          int posz = zz + e - rad / 2;
          if (world.getBlock(posx, posy, posz) != MFQM.MireBlock && 
            world.getBlock(posx, posy, posz) != MFQM.StableLiquidMireBlock && (
            world.getBlock(posx, posy + 1, posz) == MFQM.StableLiquidMireBlock || world.getBlock(posx, posy + 1, posz) == MFQM.MireBlock))
            world.setBlock(posx, posy, posz, fblock); 
        } 
      } 
    } 
  }
  
  private void spawnMorass(World world, int x, int y, int z, int ty) {
    if (ty == 1) {
      if (world.rand.nextInt(5) == 0) {
        world.setBlock(x, y, z, MFQM.MorassBlock, world.rand.nextInt(2) + 7, 3);
        return;
      } 
      if (world.rand.nextInt(3) == 0) {
        world.setBlock(x, y, z, MFQM.MorassBlock, world.rand.nextInt(4) + 3, 3);
        return;
      } 
      if (world.rand.nextInt(2) == 0) {
        world.setBlock(x, y, z, MFQM.MorassBlock, 2, 3);
        return;
      } 
      if (world.rand.nextInt(3) == 0) {
        world.setBlock(x, y, z, MFQM.MorassBlock, 0, 3);
        return;
      } 
      world.setBlock(x, y, z, MFQM.MorassBlock, 1, 3);
      return;
    } 
    if (ty == 2) {
      if (world.rand.nextInt(20) == 0) {
        world.setBlock(x, y, z, MFQM.MorassBlock, world.rand.nextInt(2) + 7, 3);
        return;
      } 
      if (world.rand.nextInt(10) == 0) {
        world.setBlock(x, y, z, MFQM.MorassBlock, world.rand.nextInt(4) + 3, 3);
        return;
      } 
      if (world.rand.nextInt(5) == 0) {
        world.setBlock(x, y, z, MFQM.MorassBlock, 2, 3);
        return;
      } 
      if (world.rand.nextInt(10) == 0) {
        world.setBlock(x, y, z, MFQM.MorassBlock, 0, 3);
        return;
      } 
      world.setBlock(x, y, z, MFQM.MorassBlock, 1, 3);
      return;
    } 
    if (world.rand.nextInt(35) == 0) {
      world.setBlock(x, y, z, MFQM.MorassBlock, world.rand.nextInt(2) + 7, 3);
      return;
    } 
    if (world.rand.nextInt(15) == 0) {
      world.setBlock(x, y, z, MFQM.MorassBlock, world.rand.nextInt(4) + 3, 3);
      return;
    } 
    if (world.rand.nextInt(7) == 0) {
      world.setBlock(x, y, z, MFQM.MorassBlock, 2, 3);
      return;
    } 
    if (world.rand.nextInt(15) == 0) {
      world.setBlock(x, y, z, MFQM.MorassBlock, 0, 3);
      return;
    } 
    world.setBlock(x, y, z, MFQM.MorassBlock, 1, 3);
  }
  
  private void generateMorass(int xx, int yy, int zz, int rad, World world, Random random, float ty, int lush) {
    Block sblock = soilBlock(xx, zz, world);
    Block fblock = fillerBlock(xx, zz, world);
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    int dif = 0;
    int dif2 = 1;
    if (ty < 0.25D) {
      if (random.nextInt(5) == 0)
        dif = 1; 
      if (random.nextInt(10) == 0)
        dif = 2; 
      if (random.nextInt(40) == 0)
        dif2 = 2; 
      if (random.nextInt(80) == 0)
        dif2 = 3; 
    } 
    if (ty >= 0.25D && ty < 0.75D) {
      if (random.nextInt(20) == 0)
        dif = 1; 
      if (random.nextInt(40) == 0)
        dif = 2; 
      if (random.nextInt(160) == 0)
        dif2 = 2; 
      if (random.nextInt(320) == 0)
        dif2 = 3; 
    } 
    if (ty >= 0.75D) {
      if (random.nextInt(40) == 0)
        dif = 1; 
      if (random.nextInt(80) == 0)
        dif = 2; 
      if (random.nextInt(320) == 0)
        dif2 = 2; 
      if (random.nextInt(640) == 0)
        dif2 = 3; 
    } 
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    int i;
    for (i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D) {
            boolean tickGen = false;
            if (world.getBlock(posx, posy, posz) != MFQM.WetPeatBlock)
              if (world.getBlock(posx, posy, posz) != MFQM.StableLiquidMireBlock)
                if (j == 0) {
                  if (lush == 1 || !validSolidMaterial(posx, posy, posz, world) || world.getBlock(posx, posy, posz) == MFQM.MorassBlock || world.getBlock(posx, posy, posz) == MFQM.MudBlock)
                    if (!validWaterMaterial(posx, posy + 1, posz, world)) {
                      if (validSolidMaterial(posx, posy + 1, posz, world)) {
                        if (isAboveFreeSpace(posx, posy + 2, posz, world) && 
                          !validWaterMaterial(posx, posy + 2, posz, world)) {
                          world.setBlockToAir(posx, posy + 1, posz);
                          spawnMorass(world, posx, posy, posz, Math.max(dif, dif2 - 1));
                          world.setBlock(posx, posy - 1, posz, MFQM.MireBlock, 10, 2);
                          world.setBlock(posx, posy - 2, posz, fblock);
                          tickGen = true;
                          if (validSolidMaterial(posx, posy + 2, posz, world) && 
                            !validWaterMaterial(posx, posy + 3, posz, world))
                            world.setBlockToAir(posx, posy + 2, posz); 
                        } 
                      } else {
                        world.setBlockToAir(posx, posy + 1, posz);
                        spawnMorass(world, posx, posy, posz, Math.max(dif, dif2 - 1));
                        world.setBlock(posx, posy - 1, posz, MFQM.MireBlock, 10, 2);
                        world.setBlock(posx, posy - 2, posz, fblock);
                        tickGen = true;
                      } 
                      if (random.nextInt(100 * dif2) == 0) {
                        ((BlockMorass)MFQM.MorassBlock).PlantingThis(world, random, posx, posy, posz);
                      } else {
                        if (random.nextInt(4 / dif2 + 1) == 0 && 
                          !validSolidMaterial(posx, posy + 1, posz, world))
                          world.setBlock(posx, posy + 1, posz, MFQM.MoorGrassBlock, 3, 2); 
                        if (random.nextInt(30 * dif2) == 0 && 
                          !validSolidMaterial(posx, posy + 1, posz, world))
                          world.setBlock(posx, posy + 1, posz, MFQM.MoorGrassBlock, 2, 2); 
                        if (random.nextInt(40 / (int)Math.floor(Math.pow(dif2, 2.0D))) == 0 && 
                          !validSolidMaterial(posx, posy + 1, posz, world))
                          if (random.nextInt(10) == 0) {
                            world.setBlock(posx, posy + 1, posz, MFQM.MoorGrassBlock, 4, 2);
                          } else {
                            world.setBlock(posx, posy + 1, posz, MFQM.MoorGrassBlock, 5, 2);
                          }  
                      } 
                    }  
                } else if (world.getBlock(posx, posy + 1, posz) == MFQM.MorassBlock || world.getBlock(posx, posy + 1, posz) == MFQM.MireBlock) {
                  if (j == 1) {
                    world.setBlock(posx, posy, posz, MFQM.MireBlock, 10, 0);
                  } else {
                    if (random.nextInt(8 / (dif + 1)) == 0) {
                      world.setBlock(posx, posy, posz, MFQM.WetPeatBlock);
                    } else {
                      world.setBlock(posx, posy, posz, MFQM.MireBlock, 10, 0);
                      world.setBlock(posx, posy - 1, posz, MFQM.WetPeatBlock);
                      world.setBlock(posx, posy - 2, posz, MFQM.PeatBlock);
                    } 
                    tickGen = true;
                    if (world.getBlock(posx, posy + 1, posz) == MFQM.WetPeatBlock && 
                      random.nextInt(8 / (dif + 1)) == 0)
                      world.setBlock(posx, posy, posz, MFQM.PeatBlock); 
                    if (world.getBlock(posx, posy + 1, posz) == MFQM.PeatBlock)
                      world.setBlock(posx, posy, posz, MFQM.PeatBlock); 
                  } 
                }   
          } 
        } 
      } 
    } 
    if (lush == 1)
      for (i = 0; i <= rad; i++) {
        for (int j = 0; j <= rad / 2 + 2; j++) {
          for (int e = 0; e <= rad; e++) {
            int posx = xx + i - rad / 2;
            int posy = yy - j;
            int posz = zz + e - rad / 2;
            if (!world.getBlock(posx, posy, posz).getMaterial().isSolid() && (
              !world.getBlock(posx, posy, posz).getMaterial().isLiquid() || world.getBlockMetadata(posx, posy, posz) != 0) && 
              world.getBlock(posx, posy, posz) != MFQM.PeatBlock && 
              world.getBlock(posx, posy, posz) != MFQM.WetPeatBlock && 
              world.getBlock(posx, posy, posz) != MFQM.MireBlock && (
              world.getBlock(posx - 1, posy, posz) == MFQM.MireBlock || world.getBlock(posx - 1, posy, posz) != MFQM.WetPeatBlock || world
              .getBlock(posx + 1, posy, posz) == MFQM.MireBlock || world.getBlock(posx + 1, posy, posz) != MFQM.WetPeatBlock || world
              .getBlock(posx, posy, posz - 1) == MFQM.MireBlock || world.getBlock(posx, posy, posz - 1) != MFQM.WetPeatBlock || world
              .getBlock(posx, posy, posz + 1) == MFQM.MireBlock || world.getBlock(posx, posy, posz + 1) != MFQM.WetPeatBlock))
              if (validSolidMaterial(posx, posy + 1, posz, world)) {
                world.setBlock(posx, posy, posz, fblock);
              } else {
                world.setBlock(posx, posy, posz, sblock);
              }  
          } 
        } 
      }  
  }
  
  private void generateWater(int xx, int yy, int zz, int rad, World world, Random random, int wty) {
    boolean peat = false;
    Block sblock = soilBlock(xx, zz, world);
    Block fblock = fillerBlock(xx, zz, world);
    if (random.nextInt(10) == 0)
      peat = true; 
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    int i;
    for (i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D)
            if (j == 0) {
              if (validSolidMaterial(posx, posy + 1, posz, world)) {
                if (isAboveFreeSpace(posx, posy + 3, posz, world) && 
                  !validWaterMaterial(posx, posy + 2, posz, world)) {
                  world.setBlockToAir(posx, posy + 1, posz);
                  if (wty == 0) {
                    world.setBlock(posx, posy, posz, Blocks.water);
                  } else if (peat) {
                    if (validPlantMaterial(posx, posy + 1, posz, world))
                      world.setBlockToAir(posx, posy + 1, posz); 
                    world.setBlock(posx, posy, posz, MFQM.WetPeatBlock, 0, 2);
                    world.setBlock(posx, posy - 1, posz, fblock);
                  } else {
                    world.setBlock(posx, posy, posz, MFQM.StableLiquidMireBlock);
                    world.setBlock(posx, posy - 1, posz, MFQM.StableLiquidMireBlock);
                    world.setBlock(posx, posy - 2, posz, MFQM.MireBlock);
                  } 
                  if (validSolidMaterial(posx, posy + 2, posz, world) && 
                    !validWaterMaterial(posx, posy + 3, posz, world)) {
                    world.setBlockToAir(posx, posy + 2, posz);
                    if (validSolidMaterial(posx, posy + 3, posz, world) && 
                      !validWaterMaterial(posx, posy + 4, posz, world))
                      world.setBlockToAir(posx, posy + 3, posz); 
                  } 
                } 
              } else {
                world.setBlockToAir(posx, posy + 1, posz);
                if (wty == 0) {
                  world.setBlock(posx, posy, posz, Blocks.water);
                } else if (peat) {
                  if (validPlantMaterial(posx, posy + 1, posz, world))
                    world.setBlockToAir(posx, posy + 1, posz); 
                  world.setBlock(posx, posy, posz, MFQM.WetPeatBlock, 0, 2);
                  world.setBlock(posx, posy - 1, posz, fblock);
                } else {
                  world.setBlock(posx, posy, posz, MFQM.StableLiquidMireBlock);
                  world.setBlock(posx, posy - 1, posz, MFQM.MireBlock);
                } 
              } 
              if (random.nextInt(50) == 0 && 
                world.getBlock(posx, posy + 1, posz) == Blocks.air)
                world.setBlock(posx, posy + 1, posz, MFQM.CustomLilyPadBlock, 0, 2); 
            } else if (wty == 0) {
              world.setBlock(posx, posy, posz, Blocks.water);
            } else if (peat) {
              if (validPlantMaterial(posx, posy + 1, posz, world))
                world.setBlockToAir(posx, posy + 1, posz); 
              world.setBlock(posx, posy, posz, MFQM.WetPeatBlock, 0, 2);
              world.setBlock(posx, posy - 1, posz, fblock);
            } else {
              world.setBlock(posx, posy, posz, MFQM.StableLiquidMireBlock);
              world.setBlock(posx, posy - 1, posz, MFQM.MireBlock);
            }  
        } 
      } 
    } 
    for (i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2 + 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          if (!world.getBlock(posx, posy, posz).getMaterial().isSolid() && (
            !world.getBlock(posx, posy, posz).getMaterial().isLiquid() || world.getBlockMetadata(posx, posy, posz) != 0) && (
            world.getBlock(posx - 1, posy, posz).getMaterial().isLiquid() || world
            .getBlock(posx + 1, posy, posz).getMaterial().isLiquid() || world
            .getBlock(posx, posy + 1, posz).getMaterial().isLiquid() || world
            .getBlock(posx, posy, posz - 1).getMaterial().isLiquid() || world
            .getBlock(posx, posy, posz + 1).getMaterial().isLiquid()))
            if (world.getBlock(posx, posy + 1, posz) == Blocks.air) {
              spawnMorass(world, posx, posy, posz, 0);
            } else {
              world.setBlock(posx, posy, posz, fblock, 0, 0);
            }  
        } 
      } 
    } 
  }
  
  private void generateTar(int xx, int yy, int zz, int rad, World world, Random random) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    int i;
    for (i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D) {
            world.setBlock(posx, posy, posz, MFQM.TarBlock);
            world.setBlock(posx, posy - 1, posz, MFQM.TarBlock);
          } 
        } 
      } 
    } 
    for (i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2 + 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          if (world.getBlock(posx, posy, posz) != MFQM.TarBlock && (
            world.getBlock(posx - 1, posy, posz) == MFQM.TarBlock || world
            .getBlock(posx + 1, posy, posz) == MFQM.TarBlock || world
            .getBlock(posx, posy + 1, posz) == MFQM.TarBlock || world
            .getBlock(posx, posy, posz - 1) == MFQM.TarBlock || world
            .getBlock(posx, posy, posz + 1) == MFQM.TarBlock))
            world.setBlock(posx, posy, posz, Blocks.stone); 
        } 
      } 
    } 
  }
  
  private void generateLarvae(int xx, int yy, int zz, int rad, World world, Random random) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    for (int i = 0; i <= rad; i++) {
      for (int k = 0; k <= rad / 2; k++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - k;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D) {
            boolean canGenW = true;
            if (world.getBlock(posx, posy + 1, posz).getMaterial() == Material.water) {
              canGenW = false;
            } else if (world.getBlock(posx + 1, posy, posz).getMaterial() == Material.water) {
              canGenW = false;
            } else if (world.getBlock(posx - 1, posy, posz).getMaterial() == Material.water) {
              canGenW = false;
            } else if (world.getBlock(posx, posy - 1, posz).getMaterial() == Material.water) {
              canGenW = false;
            } else if (world.getBlock(posx, posy, posz - 1).getMaterial() == Material.water) {
              canGenW = false;
            } else if (world.getBlock(posx, posy, posz + 1).getMaterial() == Material.water) {
              canGenW = false;
            } 
            if (canGenW && 
              validGroundMaterial(posx, posy, posz, world)) {
              world.setBlock(posx, posy, posz, MFQM.LarvaeBlock);
              world.setBlock(posx, posy - 1, posz, MFQM.LarvaeBlock);
              world.setBlock(posx, posy - 2, posz, MFQM.LarvaeBlock);
              if (validGroundMaterial(posx, posy + 1, posz, world) && 
                !isWoodNear(posx, posy + 1, posz, world))
                world.setBlockToAir(posx, posy + 1, posz); 
              if (validGroundMaterial(posx, posy + 2, posz, world) && 
                !isWoodNear(posx, posy + 2, posz, world)) {
                boolean canGenW2 = true;
                if (world.getBlock(posx, posy + 1 + 2, posz).getMaterial() == Material.water) {
                  canGenW2 = false;
                } else if (world.getBlock(posx + 1, posy + 2, posz).getMaterial() == Material.water) {
                  canGenW2 = false;
                } else if (world.getBlock(posx - 1, posy + 2, posz).getMaterial() == Material.water) {
                  canGenW2 = false;
                } else if (world.getBlock(posx, posy - 1 + 2, posz).getMaterial() == Material.water) {
                  canGenW2 = false;
                } else if (world.getBlock(posx, posy + 2, posz - 1).getMaterial() == Material.water) {
                  canGenW2 = false;
                } else if (world.getBlock(posx, posy + 2, posz + 1).getMaterial() == Material.water) {
                  canGenW2 = false;
                } 
                if (canGenW2)
                  world.setBlockToAir(posx, posy + 2, posz); 
              } 
            } 
          } 
        } 
      } 
    } 
    Block sblock = soilBlock(xx, zz, world);
    Block fblock = fillerBlock(xx, zz, world);
    for (int j = 0; j <= rad; j++) {
      for (int k = 0; k <= rad / 2 + 3; k++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + j - rad / 2;
          int posy = yy - k;
          int posz = zz + e - rad / 2;
          if (world.getBlock(posx, posy, posz) != MFQM.LarvaeBlock && (
            world.getBlock(posx - 1, posy, posz) == MFQM.LarvaeBlock || world
            .getBlock(posx + 1, posy, posz) == MFQM.LarvaeBlock || world
            .getBlock(posx, posy + 1, posz) == MFQM.LarvaeBlock || world
            .getBlock(posx, posy, posz - 1) == MFQM.LarvaeBlock || world
            .getBlock(posx, posy, posz + 1) == MFQM.LarvaeBlock))
            if (world.getBlock(posx, posy + 1, posz).getMaterial().isSolid()) {
              world.setBlock(posx, posy, posz, fblock);
            } else {
              world.setBlock(posx, posy, posz, sblock);
            }  
        } 
      } 
    } 
  }
  
  private void generateDenseWeb(int xx, int yy, int zz, int rad, World world, Random random) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    for (int i = 0; i <= rad; i++) {
      for (int k = 0; k <= rad / 2; k++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - k;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D) {
            boolean canGenW = true;
            if (world.getBlock(posx, posy + 1, posz).getMaterial() == Material.water) {
              canGenW = false;
            } else if (world.getBlock(posx + 1, posy, posz).getMaterial() == Material.water) {
              canGenW = false;
            } else if (world.getBlock(posx - 1, posy, posz).getMaterial() == Material.water) {
              canGenW = false;
            } else if (world.getBlock(posx, posy - 1, posz).getMaterial() == Material.water) {
              canGenW = false;
            } else if (world.getBlock(posx, posy, posz - 1).getMaterial() == Material.water) {
              canGenW = false;
            } else if (world.getBlock(posx, posy, posz + 1).getMaterial() == Material.water) {
              canGenW = false;
            } 
            if (canGenW && 
              validGroundMaterial(posx, posy, posz, world)) {
              if (world.getBlock(posx, posy, posz) != Blocks.mob_spawner)
                world.setBlock(posx, posy, posz, MFQM.DenseWebBlock); 
              if (k == 0 && 
                MFQM.GenWebU && 
                random.nextInt(65) == 0) {
                world.setBlock(posx, posy + 2, posz, MFQM.DenseWebBlock);
                world.setBlock(posx + 1, posy + 1, posz, MFQM.DenseWebBlock, 1, 2);
                world.setBlock(posx - 1, posy + 1, posz, MFQM.DenseWebBlock, 1, 2);
                world.setBlock(posx, posy + 1, posz, Blocks.mob_spawner, 0, 2);
                world.setBlock(posx, posy + 1, posz + 1, MFQM.DenseWebBlock, 1, 2);
                world.setBlock(posx, posy + 1, posz - 1, MFQM.DenseWebBlock, 1, 2);
                TileEntityMobSpawner var18 = (TileEntityMobSpawner)world.getTileEntity(posx, posy + 1, posz);
                if (var18 != null) {
                  NBTTagCompound nbt = new NBTTagCompound();
                  var18.writeToNBT(nbt);
                  if (random.nextInt(5) == 0) {
                    nbt.setString("EntityId", "CaveSpider");
                    nbt.setShort("MaxNearbyEntities", (short)16);
                  } else {
                    nbt.setString("EntityId", "Spider");
                    nbt.setShort("MaxNearbyEntities", (short)10);
                  } 
                  nbt.setShort("RequiredPlayerRange", (short)96);
                  nbt.setShort("SpawnRange", (short)32);
                  nbt.setShort("MinSpawnDelay", (short)400);
                  nbt.setShort("MaxSpawnDelay", (short)1600);
                  var18.readFromNBT(nbt);
                } else {
                  System.err.println("(MFQM)Failed to fetch mob spawner entity");
                } 
              } 
              world.setBlock(posx, posy - 1, posz, MFQM.DenseWebBlock);
              world.setBlock(posx, posy - 2, posz, MFQM.DenseWebBlock);
              if (validGroundMaterial(posx, posy + 1, posz, world) && 
                !isWoodNear(posx, posy + 1, posz, world))
                world.setBlockToAir(posx, posy + 1, posz); 
              if (validGroundMaterial(posx, posy + 2, posz, world) && 
                !isWoodNear(posx, posy + 2, posz, world)) {
                boolean canGenW2 = true;
                if (world.getBlock(posx, posy + 1 + 2, posz).getMaterial() == Material.water) {
                  canGenW2 = false;
                } else if (world.getBlock(posx + 1, posy + 2, posz).getMaterial() == Material.water) {
                  canGenW2 = false;
                } else if (world.getBlock(posx - 1, posy + 2, posz).getMaterial() == Material.water) {
                  canGenW2 = false;
                } else if (world.getBlock(posx, posy - 1 + 2, posz).getMaterial() == Material.water) {
                  canGenW2 = false;
                } else if (world.getBlock(posx, posy + 2, posz - 1).getMaterial() == Material.water) {
                  canGenW2 = false;
                } else if (world.getBlock(posx, posy + 2, posz + 1).getMaterial() == Material.water) {
                  canGenW2 = false;
                } 
                if (canGenW2)
                  world.setBlockToAir(posx, posy + 2, posz); 
              } 
            } 
          } 
        } 
      } 
    } 
    Block sblock = soilBlock(xx, zz, world);
    Block fblock = fillerBlock(xx, zz, world);
    for (int j = 0; j <= rad; j++) {
      for (int k = 0; k <= rad / 2 + 3; k++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + j - rad / 2;
          int posy = yy - k;
          int posz = zz + e - rad / 2;
          if (world.getBlock(posx, posy, posz) != Blocks.mob_spawner && 
            world.getBlock(posx, posy, posz) != MFQM.DenseWebBlock && (
            world.getBlock(posx - 1, posy, posz) == MFQM.DenseWebBlock || world
            .getBlock(posx + 1, posy, posz) == MFQM.DenseWebBlock || world
            .getBlock(posx, posy + 1, posz) == MFQM.DenseWebBlock || world
            .getBlock(posx, posy, posz - 1) == MFQM.DenseWebBlock || world
            .getBlock(posx, posy, posz + 1) == MFQM.DenseWebBlock))
            if (world.getBlock(posx, posy + 1, posz).getMaterial().isSolid()) {
              world.setBlock(posx, posy, posz, fblock);
            } else {
              world.setBlock(posx, posy, posz, sblock);
            }  
        } 
      } 
    } 
  }
  
  private void generateBog(int xx, int yy, int zz, int rad, World world, Random random, Material BlockMaterialToReplace) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    Block sblock = soilBlock(xx, zz, world);
    Block fblock = fillerBlock(xx, zz, world);
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    int i;
    for (i = 0; i <= rad; i++) {
      for (int j = 0; j <= 5; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D && (
            BlockMaterialToReplace == Material.cake || world.getBlock(posx, posy, posz).getMaterial() == BlockMaterialToReplace)) {
            world.setBlock(posx, posy, posz, MFQM.BogBlock);
            world.setBlock(posx, posy - 1, posz, MFQM.BogBlock);
            if (random.nextInt(75) == 0 && 
              world.getBlock(posx, posy + 1, posz) == Blocks.air)
              world.setBlock(posx, posy + 1, posz, MFQM.CustomLilyPadBlock, 0, 2); 
          } 
        } 
      } 
    } 
    for (i = 0; i <= rad; i++) {
      for (int j = 0; j <= 7; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          if (world.getBlock(posx, posy, posz) != MFQM.BogBlock && 
            world.getBlock(posx, posy + 1, posz) == MFQM.BogBlock)
            world.setBlock(posx, posy, posz, fblock); 
        } 
      } 
    } 
  }
  
  private void generateSnow(int xx, int yy, int zz, int rad, World world, Random random) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    int rady = rad;
    if (rad > 5 && 
      random.nextInt(rad) != 0)
      rady = 0; 
    for (int i = 0; i <= rad; i++) {
      for (int j = 0; j <= rady; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D && 
            world.getBlock(posx, posy, posz) != Blocks.air) {
            if (world.getBlock(posx, posy + 1, posz) == Blocks.snow_layer) {
              world.setBlockToAir(posx, posy + 1, posz);
              world.setBlock(posx, posy, posz, MFQM.SoftSnowBlock);
            } 
            if (world.getBlock(posx, posy, posz) == Blocks.snow_layer) {
              world.setBlockToAir(posx, posy, posz);
              world.setBlock(posx, posy - 1, posz, MFQM.SoftSnowBlock);
              if (rady == 0)
                rady = 1; 
            } else {
              world.setBlock(posx, posy, posz, MFQM.SoftSnowBlock);
              if (rady > 1)
                world.setBlock(posx, posy - 1, posz, MFQM.SoftSnowBlock); 
            } 
          } 
        } 
      } 
    } 
  }
  
  private void generateGravel(int xx, int yy, int zz, int rad, World world, Random random, boolean replace) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    for (int j = 0; j <= rad; j++) {
      for (int i = 0; i <= rad; i++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy + j - rad / 2;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis >= rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) || 
            replace);
        } 
      } 
    } 
  }
  
  private void generateEmptyColumn(int xx, int yy, int zz, int len, World world, Random random) {
    for (int i = 0; i <= len; i++) {
      int posx = xx;
      int posy = yy + i;
      int posz = zz;
      world.setBlockToAir(posx, posy, posz);
      if (i == len && 
        validFallingMaterial(posx, posy + 1, posz, world))
        world.setBlock(posx, posy + 1, posz, Blocks.stone); 
    } 
  }
  
  private void generateGravelColumn(int xx, int yy, int zz, int len, World world, Random random) {
    for (int i = 0; i <= len; i++) {
      int posx = xx;
      int posy = yy - i;
      int posz = zz;
      world.setBlockToAir(posx, posy, posz);
    } 
  }
  
  private void generateGravelPit(int xx, int yy, int zz, int rad, World world, Random random, int depth) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    double minRad = rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D);
    int rad2 = rad * 2;
    double minRad2 = rad2 / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D);
    double midRad2 = minRad2 / 1.5D;
    for (int k = 0; k <= rad2 / 4; k++) {
      for (int m = 0; m <= rad2; m++) {
        for (int e = 0; e <= rad2; e++) {
          int posx = xx + m - rad2 / 2;
          int posy = yy - k + rad2 / 8;
          int posz = zz + e - rad2 / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < minRad2)
            if (dis < midRad2) {
              if (world.getBlock(posx, posy, posz) != MFQM.SoftGravelBlock && (
                validCaveMaterial(posx, posy, posz, world) || world
                .getBlock(posx, posy, posz).getMaterial().isLiquid())) {
                world.setBlock(posx, posy, posz, MFQM.SoftGravelBlock, 0, 2);
                world.setBlock(posx, posy - 1, posz, Blocks.gravel);
                world.setBlock(posx, posy - 2, posz, Blocks.gravel);
                world.setBlock(posx, posy - 3, posz, Blocks.stone);
                world.setBlock(posx, posy - 4, posz, Blocks.stone);
                world.setBlock(posx, posy - 5, posz, Blocks.stone);
              } 
            } else if (world.getBlock(posx, posy, posz) != MFQM.SoftGravelBlock && (
              validCaveMaterial(posx, posy, posz, world) || world
              .getBlock(posx, posy, posz).getMaterial().isLiquid())) {
              world.setBlock(posx, posy, posz, Blocks.gravel);
              world.setBlock(posx, posy - 1, posz, Blocks.stone);
              world.setBlock(posx, posy - 2, posz, Blocks.stone);
              world.setBlock(posx, posy - 3, posz, Blocks.stone);
            }  
        } 
      } 
    } 
    for (int i = 0; i <= rad; i++) {
      for (int e = 0; e <= rad; e++) {
        int posx = xx + i - rad / 2;
        int posy = yy;
        int posz = zz + e - rad / 2;
        double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((zz - posz), 2.0D) * raz);
        if (dis < minRad) {
          int len = (int)Math.floor((1.0D - dis / minRad) * depth);
          int plen = (int)Math.floor((1.0D - Math.max(dis - 1.0D, 0.0D) / minRad) * depth) + (int)Math.floor(Math.max(depth / 8.0D, 2.0D));
          int len2 = Math.max(plen - len, 0) + 5;
          len += random.nextInt((int)Math.floor(Math.max(depth / 8.0D, 2.0D)));
          generateGravelColumn(posx, posy, posz, len, world, random);
          if (world.getBlock(posx, posy - len - 1, posz).getMaterial().isSolid()) {
            for (int m = 0; m <= len2; m++)
              world.setBlock(posx, posy - len - m, posz, MFQM.SoftGravelBlock, 0, 2); 
            world.setBlock(posx, posy - len - len2 - 1, posz, Blocks.gravel);
            world.setBlock(posx, posy - len - len2 - 2, posz, Blocks.gravel);
            world.setBlock(posx, posy - len - len2 - 3, posz, Blocks.stone);
            world.setBlock(posx, posy - len - len2 - 4, posz, Blocks.stone);
            world.setBlock(posx, posy - len - len2 - 5, posz, Blocks.stone);
          } 
          generateEmptyColumn(posx, posy + 1, posz, len * 2, world, random);
        } 
      } 
    } 
    for (int j = 0; j <= rad / 2; j++) {
      for (int m = 0; m <= rad + 8; m++) {
        for (int e = 0; e <= rad + 8; e++) {
          int siltDepth = yy - (int)Math.floor(depth * 0.667D);
          int posx = xx + m - rad / 2 - 4;
          int posy = siltDepth - j;
          int posz = zz + e - rad / 2 - 4;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((siltDepth - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < minRad && 
            posy <= siltDepth) {
            if (posy == siltDepth && 
              !world.getBlock(posx, posy + 2, posz).getMaterial().isSolid())
              world.setBlockToAir(posx, posy + 1, posz); 
            world.setBlock(posx, posy, posz, MFQM.SoftGravelBlock, 1, 2);
            world.setBlock(posx, posy - 1, posz, MFQM.SoftGravelBlock, 1, 2);
            world.setBlock(posx, posy - 2, posz, MFQM.SoftGravelBlock, 0, 2);
            world.setBlock(posx, posy - 3, posz, MFQM.SoftGravelBlock, 0, 2);
            world.setBlock(posx, posy - 4, posz, Blocks.gravel);
            world.setBlock(posx, posy - 5, posz, Blocks.gravel);
            world.setBlock(posx, posy - 6, posz, Blocks.stone);
            world.setBlock(posx, posy - 7, posz, Blocks.stone);
            world.setBlock(posx, posy - 8, posz, Blocks.stone);
          } 
        } 
      } 
    } 
  }
  
  private void generateSoftSand(int xx, int yy, int zz, int rad, World world, Random random) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    int i;
    for (i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D) {
            world.setBlock(posx, posy, posz, MFQM.SandBlock);
            world.setBlock(posx, posy - 1, posz, MFQM.SandBlock);
            world.setBlock(posx, posy - 2, posz, Blocks.sandstone);
          } 
        } 
      } 
    } 
    if (rad > 4 && (rad < 10 || random.nextInt(2 + rad / 2) == 0))
      for (i = 0; i <= rad; i++) {
        for (int j = 0; j <= rad + 1; j++) {
          for (int e = 0; e <= rad; e++) {
            int posx = xx + i - rad / 2;
            int posy = yy - j;
            int posz = zz + e - rad / 2;
            if (j < rad / 3 && 
              world.getBlock(posx, posy, posz) == MFQM.SandBlock) {
              boolean wall = false;
              if (world.getBlock(posx - 1, posy, posz) != Blocks.air && world.getBlock(posx - 1, posy, posz) != MFQM.SandBlock) {
                wall = true;
              } else if (world.getBlock(posx + 1, posy, posz) != Blocks.air && world.getBlock(posx + 1, posy, posz) != MFQM.SandBlock) {
                wall = true;
              } else if (world.getBlock(posx, posy, posz + 1) != Blocks.air && world.getBlock(posx, posy, posz + 1) != MFQM.SandBlock) {
                wall = true;
              } else if (world.getBlock(posx, posy, posz - 1) != Blocks.air && world.getBlock(posx, posy, posz - 1) != MFQM.SandBlock) {
                wall = true;
              } else if (world.getBlock(posx, posy - 1, posz) != Blocks.air && world.getBlock(posx, posy - 1, posz) != MFQM.SandBlock) {
                wall = true;
              } 
              if (!wall)
                world.setBlockToAir(posx, posy, posz); 
            } 
          } 
        } 
      }  
  }
  
  private void generateMoorGrass(int xx, int yy, int zz, int rad, World world, Random random, Block BlockMaterialToReplace) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    for (int i = 0; i <= rad; i++) {
      for (int j = 0; j <= 3; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D && 
            world.getBlock(posx, posy, posz) == Blocks.air && 
            world.getBlock(posx, posy - 1, posz) == BlockMaterialToReplace && 
            random.nextInt(5) == 0)
            world.setBlock(posx, posy, posz, MFQM.MoorGrassBlock, 1, 0); 
        } 
      } 
    } 
  }
  
  private void generateTendrils(int xx, int yy, int zz, int rad, World world, Random random) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    for (int i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D && 
            world.getBlock(posx, posy, posz) == Blocks.air && 
            world.getBlock(posx, posy - 1, posz) == MFQM.SwFleshBlock && 
            random.nextInt(50) == 0)
            world.setBlock(posx, posy, posz, MFQM.TentBlock, random.nextInt(4), 2); 
        } 
      } 
    } 
  }
  
  private void generateWeb(int xx, int yy, int zz, int rad, World world, Random random) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    for (int i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D && 
            random.nextInt(100) == 0 && 
            world.getBlock(posx, posy, posz) == Blocks.air && 
            validLarvMaterial(posx, posy - 1, posz, world))
            world.setBlock(posx, posy, posz, Blocks.web); 
        } 
      } 
    } 
  }
  
  private void generateCaveWeb(int xx, int yy, int zz, int rad, World world, Random random) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    for (int i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D && 
            random.nextInt(50) == 0 && 
            world.getBlock(posx, posy, posz) == Blocks.air && (
            validWebMaterial(posx - 1, posy, posz, world) || 
            validWebMaterial(posx + 1, posy, posz, world) || 
            validWebMaterial(posx, posy + 1, posz, world) || 
            validWebMaterial(posx, posy - 1, posz, world) || 
            validWebMaterial(posx, posy, posz - 1, world) || 
            validWebMaterial(posx, posy, posz + 1, world)))
            world.setBlock(posx, posy, posz, Blocks.web); 
        } 
      } 
    } 
  }
  
  private void generateClay(int water, int xx, int yy, int zz, int rad, World world, Random random, int over) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    boolean isSinking = false;
    if (water == 1 && 
      random.nextInt(2) == 0 && 
      MFQM.GenSClay)
      isSinking = true; 
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    if (water == 1) {
      raz = 1.0D;
      rax = 1.0D;
    } 
    int i;
    for (i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          int clayfens = (int)Math.floor((j / 3));
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D && 
            world.getBlock(posx, posy, posz).getMaterial() != Material.wood && !isWoodNear(posx, posy, posz, world)) {
            if (over == 0) {
              if (water == 0 && 
                world.getBlock(posx, posy, posz).getMaterial().isOpaque() && world.getBlock(posx, posy, posz) != MFQM.SClayBlock) {
                boolean canGenW = true;
                if (world.getBlock(posx, posy + 1, posz).getMaterial() == Material.water) {
                  canGenW = false;
                } else if (world.getBlock(posx + 1, posy, posz).getMaterial() == Material.water) {
                  canGenW = false;
                } else if (world.getBlock(posx - 1, posy, posz).getMaterial() == Material.water) {
                  canGenW = false;
                } else if (world.getBlock(posx, posy - 1, posz).getMaterial() == Material.water) {
                  canGenW = false;
                } else if (world.getBlock(posx, posy, posz - 1).getMaterial() == Material.water) {
                  canGenW = false;
                } else if (world.getBlock(posx, posy, posz + 1).getMaterial() == Material.water) {
                  canGenW = false;
                } 
                if (canGenW) {
                  if (validPlantMaterial(posx, posy + 1, posz, world))
                    world.setBlockToAir(posx, posy + 1, posz); 
                  if (validPlantMaterial(posx, posy + 2, posz, world))
                    world.setBlockToAir(posx, posy + 2, posz); 
                  if (validPlantMaterial(posx, posy + 3, posz, world))
                    world.setBlockToAir(posx, posy + 3, posz); 
                  if (!world.getBlock(posx, posy + 1, posz).getMaterial().isOpaque() || (random.nextInt(clayfens + 1) == 0 && world.getBlock(posx, posy + 1, posz) == MFQM.HClayBlock) || clayfens == 0 || random.nextInt(clayfens + 1) == 0) {
                    if (!world.getBlock(posx, posy + 1, posz).getMaterial().isSolid() && random.nextInt(2) == 0) {
                      world.setBlock(posx, posy, posz, MFQM.HClayBlock, random.nextInt(5), 0);
                    } else {
                      world.setBlock(posx, posy, posz, MFQM.HClayBlock);
                    } 
                  } else {
                    world.setBlock(posx, posy, posz, Blocks.clay);
                  } 
                } 
              } 
            } else if (world.getBlock(posx, posy, posz).getMaterial() == Material.water) {
              if (!world.getBlock(posx, posy + 1, posz).getMaterial().isOpaque() || (random.nextInt(clayfens + 1) == 0 && world.getBlock(posx, posy + 1, posz) == MFQM.HClayBlock) || clayfens == 0 || random.nextInt(clayfens + 1) == 0) {
                if (!world.getBlock(posx, posy + 1, posz).getMaterial().isSolid() && random.nextInt(2) == 0) {
                  world.setBlock(posx, posy, posz, MFQM.HClayBlock, random.nextInt(5), 0);
                } else {
                  world.setBlock(posx, posy, posz, MFQM.HClayBlock);
                } 
              } else {
                world.setBlock(posx, posy, posz, Blocks.clay);
              } 
            } 
            if (water == 1) {
              boolean canGenW = true;
              if (world.getBlock(posx, posy + 1, posz).getMaterial().isLiquid())
                canGenW = false; 
              if (canGenW)
                if ((world.getBlock(posx, posy, posz).getMaterial().isLiquid() && isSinking) || world.getBlock(posx, posy + 1, posz) == MFQM.SClayBlock) {
                  if (validPlantMaterial(posx, posy + 1, posz, world))
                    world.setBlockToAir(posx, posy + 1, posz); 
                  if (validPlantMaterial(posx, posy + 2, posz, world))
                    world.setBlockToAir(posx, posy + 2, posz); 
                  if (validPlantMaterial(posx, posy + 3, posz, world))
                    world.setBlockToAir(posx, posy + 3, posz); 
                  if (!world.getBlock(posx, posy + 1, posz).getMaterial().isOpaque()) {
                    if (random.nextInt(10) == 0) {
                      world.setBlock(posx, posy, posz, MFQM.SClayBlock, random.nextInt(5), 0);
                    } else {
                      world.setBlock(posx, posy, posz, MFQM.SClayBlock, 0, 0);
                    } 
                    world.setBlock(posx, posy - 1, posz, MFQM.SClayBlock, 7, 0);
                  } else {
                    world.setBlock(posx, posy, posz, MFQM.SClayBlock, 7, 0);
                  } 
                } else if (!isSinking) {
                  if (world.getBlock(posx, posy, posz).getMaterial().isLiquid()) {
                    if (validPlantMaterial(posx, posy + 1, posz, world))
                      world.setBlockToAir(posx, posy + 1, posz); 
                    if (validPlantMaterial(posx, posy + 2, posz, world))
                      world.setBlockToAir(posx, posy + 2, posz); 
                    if (validPlantMaterial(posx, posy + 3, posz, world))
                      world.setBlockToAir(posx, posy + 3, posz); 
                    if (!world.getBlock(posx, posy + 1, posz).getMaterial().isSolid() || (random.nextInt(clayfens + 1) == 0 && world.getBlock(posx, posy + 1, posz) == MFQM.HClayBlock) || clayfens == 0 || random.nextInt(clayfens + 1) == 0) {
                      if (!world.getBlock(posx, posy + 1, posz).getMaterial().isSolid() && random.nextInt(5) == 0) {
                        world.setBlock(posx, posy, posz, MFQM.HClayBlock, random.nextInt(5), 0);
                      } else {
                        world.setBlock(posx, posy, posz, MFQM.HClayBlock);
                      } 
                    } else {
                      world.setBlock(posx, posy, posz, Blocks.clay);
                    } 
                  } 
                }  
            } 
          } 
        } 
      } 
    } 
    for (i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2 + 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          if (world.getBlock(posx, posy, posz) == MFQM.HClayBlock || world.getBlock(posx, posy, posz) == MFQM.SClayBlock) {
            boolean canGenW = true;
            if (world.getBlock(posx, posy + 1, posz).getMaterial() == Material.water) {
              canGenW = false;
            } else if (world.getBlock(posx + 1, posy, posz).getMaterial() == Material.water) {
              canGenW = false;
            } else if (world.getBlock(posx - 1, posy, posz).getMaterial() == Material.water) {
              canGenW = false;
            } else if (world.getBlock(posx, posy - 1, posz).getMaterial() == Material.water) {
              canGenW = false;
            } else if (world.getBlock(posx, posy, posz - 1).getMaterial() == Material.water) {
              canGenW = false;
            } else if (world.getBlock(posx, posy, posz + 1).getMaterial() == Material.water) {
              canGenW = false;
            } 
            if (!canGenW) {
              int inThisBlocks = 0;
              if (world.getBlock(posx + 1, posy, posz).getMaterial() != Material.water)
                inThisBlocks++; 
              if (world.getBlock(posx - 1, posy, posz).getMaterial() != Material.water)
                inThisBlocks++; 
              if (world.getBlock(posx, posy, posz - 1).getMaterial() != Material.water)
                inThisBlocks++; 
              if (world.getBlock(posx, posy, posz + 1).getMaterial() != Material.water)
                inThisBlocks++; 
              if (inThisBlocks > 1) {
                world.setBlock(posx, posy, posz, Blocks.clay);
              } else {
                world.setBlock(posx, posy, posz, Blocks.lapis_ore);
              } 
            } 
          } 
        } 
      } 
    } 
    for (i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2 + 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          if (world.getBlock(posx, posy, posz) == Blocks.lapis_ore)
            world.setBlock(posx, posy, posz, (Block)Blocks.flowing_water); 
        } 
      } 
    } 
  }
  
  private void generateOnlyClay(boolean sink, int xx, int yy, int zz, int rad, World world, Random random) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = 1.0D;
    double raz = 1.0D;
    boolean isSinking = sink;
    int i;
    for (i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          int clayfens = (int)Math.floor((j / 3));
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D && 
            world.getBlock(posx, posy, posz).getMaterial() != Material.wood && world.getBlock(posx, posy, posz).getMaterial().isOpaque() && world.getBlock(posx, posy, posz) != MFQM.SClayBlock && !world.getBlock(posx, posy, posz).getMaterial().isLiquid() && 
            !isWoodNear(posx, posy, posz, world)) {
            boolean canGenW = true;
            if (world.getBlock(posx, posy + 1, posz).getMaterial() == Material.water) {
              canGenW = false;
            } else if (world.getBlock(posx + 1, posy, posz).getMaterial() == Material.water) {
              canGenW = false;
            } else if (world.getBlock(posx - 1, posy, posz).getMaterial() == Material.water) {
              canGenW = false;
            } else if (world.getBlock(posx, posy - 1, posz).getMaterial() == Material.water) {
              canGenW = false;
            } else if (world.getBlock(posx, posy, posz - 1).getMaterial() == Material.water) {
              canGenW = false;
            } else if (world.getBlock(posx, posy, posz + 1).getMaterial() == Material.water) {
              canGenW = false;
            } 
            if (isSinking)
              if (world.getBlock(posx, posy + 1, posz).getMaterial().isOpaque()) {
                isSinking = false;
              } else if (!world.getBlock(posx + 1, posy, posz).getMaterial().isOpaque()) {
                isSinking = false;
              } else if (!world.getBlock(posx - 1, posy, posz).getMaterial().isOpaque()) {
                isSinking = false;
              } else if (!world.getBlock(posx, posy - 1, posz).getMaterial().isOpaque()) {
                isSinking = false;
              } else if (!world.getBlock(posx, posy, posz - 1).getMaterial().isOpaque()) {
                isSinking = false;
              } else if (!world.getBlock(posx, posy, posz + 1).getMaterial().isOpaque()) {
                isSinking = false;
              }  
            if (world.getBlock(posx, posy + 1, posz) == MFQM.SClayBlock)
              isSinking = true; 
            if (canGenW) {
              if (validPlantMaterial(posx, posy + 1, posz, world))
                world.setBlockToAir(posx, posy + 1, posz); 
              if (validPlantMaterial(posx, posy + 2, posz, world))
                world.setBlockToAir(posx, posy + 2, posz); 
              if (validPlantMaterial(posx, posy + 3, posz, world))
                world.setBlockToAir(posx, posy + 3, posz); 
              if (isSinking) {
                if (!world.getBlock(posx, posy + 1, posz).getMaterial().isOpaque()) {
                  if (random.nextInt(10) == 0) {
                    world.setBlock(posx, posy, posz, MFQM.SClayBlock, random.nextInt(5), 0);
                  } else {
                    world.setBlock(posx, posy, posz, MFQM.SClayBlock, 0, 0);
                  } 
                  world.setBlock(posx, posy - 1, posz, MFQM.SClayBlock, 7, 0);
                } else {
                  world.setBlock(posx, posy, posz, MFQM.SClayBlock, 7, 0);
                } 
              } else if (!world.getBlock(posx, posy + 1, posz).getMaterial().isOpaque() || (random.nextInt(1 + clayfens) == 0 && world.getBlock(posx, posy + 1, posz) == MFQM.HClayBlock) || clayfens == 0 || random.nextInt(clayfens + 1) == 0) {
                if (!world.getBlock(posx, posy + 1, posz).getMaterial().isSolid() && random.nextInt(5) == 0) {
                  world.setBlock(posx, posy, posz, MFQM.HClayBlock, random.nextInt(5), 0);
                } else {
                  world.setBlock(posx, posy, posz, MFQM.HClayBlock);
                } 
              } else {
                world.setBlock(posx, posy, posz, Blocks.clay);
              } 
            } 
          } 
        } 
      } 
    } 
    for (i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2 + 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          if (world.getBlock(posx, posy, posz) == MFQM.HClayBlock || world.getBlock(posx, posy, posz) == MFQM.SClayBlock) {
            boolean canGenW = true;
            if (world.getBlock(posx, posy + 1, posz).getMaterial() == Material.water) {
              canGenW = false;
            } else if (world.getBlock(posx + 1, posy, posz).getMaterial() == Material.water) {
              canGenW = false;
            } else if (world.getBlock(posx - 1, posy, posz).getMaterial() == Material.water) {
              canGenW = false;
            } else if (world.getBlock(posx, posy - 1, posz).getMaterial() == Material.water) {
              canGenW = false;
            } else if (world.getBlock(posx, posy, posz - 1).getMaterial() == Material.water) {
              canGenW = false;
            } else if (world.getBlock(posx, posy, posz + 1).getMaterial() == Material.water) {
              canGenW = false;
            } 
            if (!canGenW) {
              int inThisBlocks = 0;
              if (world.getBlock(posx + 1, posy, posz).getMaterial() != Material.water)
                inThisBlocks++; 
              if (world.getBlock(posx - 1, posy, posz).getMaterial() != Material.water)
                inThisBlocks++; 
              if (world.getBlock(posx, posy, posz - 1).getMaterial() != Material.water)
                inThisBlocks++; 
              if (world.getBlock(posx, posy, posz + 1).getMaterial() != Material.water)
                inThisBlocks++; 
              if (inThisBlocks > 1) {
                world.setBlock(posx, posy, posz, Blocks.clay);
              } else {
                world.setBlock(posx, posy, posz, Blocks.lapis_ore);
              } 
            } 
          } 
        } 
      } 
    } 
    for (i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2 + 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          if (world.getBlock(posx, posy, posz) == Blocks.lapis_ore)
            world.setBlock(posx, posy, posz, (Block)Blocks.flowing_water); 
        } 
      } 
    } 
  }
  
  private void ClearArea(int xx, int yy, int zz, int rad, World world, Random random) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    for (int i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy + j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D)
            world.setBlockToAir(posx, posy, posz); 
        } 
      } 
    } 
  }
  
  private int ClearArea2(int xx, int yy, int zz, int rad, World world, Random random) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    int removedMouth = 0;
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    for (int j = 0; j <= rad / 2; j++) {
      for (int i = 0; i <= rad; i++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy + j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          double disa = rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D);
          if (dis < disa)
            if (dis > disa - 1.5D) {
              if (world.getBlock(posx, posy, posz).getMaterial().isLiquid()) {
                world.setBlock(posx, posy, posz, MFQM.MeatBlock, 0, 2);
              } else {
                if (world.getBlock(posx, posy, posz) == MFQM.MeatBlock && world.getBlockMetadata(posx, posy, posz) == 10) {
                  world.setBlock(posx, posy + 1, posz, MFQM.MeatBlock, 10, 2);
                  ClearArea3(posx, posy + 1, posz, 5 + random.nextInt(5), world, random);
                } 
                world.setBlockToAir(posx, posy, posz);
              } 
            } else {
              if (world.getBlock(posx, posy, posz) == MFQM.MeatBlock && world.getBlockMetadata(posx, posy, posz) == 10) {
                world.setBlock(posx, posy + 1, posz, MFQM.MeatBlock, 10, 2);
                ClearArea3(posx, posy + 1, posz, 5 + random.nextInt(5), world, random);
              } 
              world.setBlockToAir(posx, posy, posz);
            }  
        } 
      } 
    } 
    return removedMouth;
  }
  
  private void spawnGreenStone(int x, int y, int z, World world, Random random, int cof) {
    switch (cof) {
      case 0:
        if (world.rand.nextInt(15) == 0) {
          world.setBlock(x, y, z, Blocks.stone, 0, 0);
          return;
        } 
        if (world.rand.nextInt(15) == 0) {
          world.setBlock(x, y, z, Blocks.cobblestone, 0, 0);
          return;
        } 
        world.setBlock(x, y, z, Blocks.mossy_cobblestone, 0, 0);
        return;
      case 1:
        if (world.rand.nextInt(1) == 0) {
          world.setBlock(x, y, z, Blocks.mossy_cobblestone, 0, 0);
          return;
        } 
        if (world.rand.nextInt(2) == 0) {
          world.setBlock(x, y, z, Blocks.cobblestone, 0, 0);
          return;
        } 
        world.setBlock(x, y, z, Blocks.stone, 0, 0);
        return;
    } 
  }
  
  private void spawnSoftQuicksand(int x, int y, int z, World world, Random random, int cof) {
    switch (cof) {
      case 0:
        world.setBlock(x, y, z, MFQM.SoftQuicksandBlock, 0, 0);
        return;
      case 1:
        world.setBlock(x, y, z, MFQM.SoftQuicksandBlock, world.rand.nextInt(3), 0);
        return;
    } 
  }
  
  private void generateSoftQuicksandForest(int xx, int yy, int zz, int rad, World world, Random random, Material BlockMaterialToReplace, Block MireBlockId) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    for (int i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D && 
            world.getBlock(posx, posy, posz) != MFQM.SoftQuicksandBlock && (
            BlockMaterialToReplace == Material.cake || world.getBlock(posx, posy, posz).getMaterial() == BlockMaterialToReplace)) {
            if (validPlantMaterial(posx, posy + 1, posz, world))
              world.setBlockToAir(posx, posy + 1, posz); 
            if (validPlantMaterial(posx, posy + 2, posz, world))
              world.setBlockToAir(posx, posy + 2, posz); 
            if (validPlantMaterial(posx, posy + 3, posz, world))
              world.setBlockToAir(posx, posy + 3, posz); 
            if (world.getBlock(posx, posy + 1, posz).getMaterial() == Material.wood && 
              world.getBlock(posx, posy + 2, posz).getMaterial() != Material.wood)
              world.setBlockToAir(posx, posy + 1, posz); 
            if (!world.getBlock(posx, posy + 1, posz).getMaterial().isOpaque() || world.getBlock(posx, posy + 1, posz) == MireBlockId) {
              world.setBlock(posx, posy, posz, MireBlockId);
              world.setBlock(posx, posy - 1, posz, MireBlockId);
              world.setBlock(posx, posy - 2, posz, Blocks.stone);
            } 
          } 
        } 
      } 
    } 
  }
  
  private void generateSoftQuicksand(int xx, int yy, int zz, int rad, World world, Random random, int ssg) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    int i;
    for (i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D) {
            if (validPlantMaterial(posx, posy + 1, posz, world))
              world.setBlockToAir(posx, posy + 1, posz); 
            if (validPlantMaterial(posx, posy + 2, posz, world))
              world.setBlockToAir(posx, posy + 2, posz); 
            if (validPlantMaterial(posx, posy + 3, posz, world))
              world.setBlockToAir(posx, posy + 3, posz); 
            if (world.getBlock(posx, posy, posz).getMaterial() == Material.wood && 
              world.getBlock(posx, posy + 1, posz).getMaterial() != Material.wood)
              world.setBlockToAir(posx, posy, posz); 
            if (world.getBlock(posx, posy + 1, posz).getMaterial() == Material.wood && 
              world.getBlock(posx, posy + 2, posz).getMaterial() != Material.wood)
              world.setBlockToAir(posx, posy + 1, posz); 
            if (j == 0) {
              if (world.getBlock(posx, posy, posz).getMaterial() != Material.wood)
                if (world.getBlock(posx, posy + 1, posz).getMaterial() == Material.wood) {
                  world.setBlock(posx, posy, posz, world.getBlock(posx, posy + 1, posz), world.getBlockMetadata(posx, posy + 1, posz), 2);
                } else if (!world.getBlock(posx, posy + 3, posz).getMaterial().isOpaque()) {
                  world.setBlockToAir(posx, posy, posz);
                  world.setBlockToAir(posx, posy + 1, posz);
                  world.setBlockToAir(posx, posy + 2, posz);
                }  
              int re = world.rand.nextInt(4);
              switch (re) {
                case 0:
                  if (world.getBlock(posx - 1, posy, posz).getMaterial().isSolid()) {
                    if (!world.getBlock(posx - 1, posy + 3, posz).getMaterial().isOpaque())
                      world.setBlockToAir(posx - 1, posy, posz); 
                    spawnGreenStone(posx - 1, posy - 1, posz, world, random, 0);
                  } 
                  break;
                case 1:
                  if (world.getBlock(posx + 1, posy, posz).getMaterial().isSolid()) {
                    if (!world.getBlock(posx + 1, posy + 3, posz).getMaterial().isOpaque())
                      world.setBlockToAir(posx + 1, posy, posz); 
                    spawnGreenStone(posx + 1, posy - 1, posz, world, random, 0);
                  } 
                  break;
                case 2:
                  if (world.getBlock(posx, posy, posz - 1).getMaterial().isSolid()) {
                    if (!world.getBlock(posx, posy + 3, posz - 1).getMaterial().isOpaque())
                      world.setBlockToAir(posx, posy, posz - 1); 
                    spawnGreenStone(posx, posy - 1, posz - 1, world, random, 0);
                  } 
                  break;
                case 3:
                  if (world.getBlock(posx, posy, posz + 1).getMaterial().isSolid()) {
                    if (!world.getBlock(posx, posy + 3, posz + 1).getMaterial().isOpaque())
                      world.setBlockToAir(posx, posy, posz + 1); 
                    spawnGreenStone(posx, posy - 1, posz + 1, world, random, 0);
                  } 
                  break;
              } 
            } else if (ssg == 0) {
              spawnSoftQuicksand(posx, posy, posz, world, random, 1);
            } else if (world.getBlock(posx, posy, posz) != MFQM.SoftQuicksandBlock) {
              spawnGreenStone(posx, posy, posz, world, random, 0);
            } 
            if (ssg == 0) {
              spawnSoftQuicksand(posx, posy - 1, posz, world, random, 1);
            } else if (world.getBlock(posx, posy - 1, posz) != MFQM.SoftQuicksandBlock) {
              spawnGreenStone(posx, posy - 1, posz, world, random, 0);
            } 
            world.setBlock(posx, posy - 2, posz, Blocks.stone);
          } 
        } 
      } 
    } 
    for (i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2 + 3; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          if (world.getBlock(posx, posy, posz).getMaterial() != Material.wood && 
            world.getBlock(posx, posy, posz) != MFQM.SoftQuicksandBlock) {
            if (world.getBlock(posx - 1, posy, posz) == MFQM.SoftQuicksandBlock || world
              .getBlock(posx + 1, posy, posz) == MFQM.SoftQuicksandBlock || world
              .getBlock(posx, posy + 1, posz) == MFQM.SoftQuicksandBlock || world
              .getBlock(posx, posy, posz - 1) == MFQM.SoftQuicksandBlock || world
              .getBlock(posx, posy, posz + 1) == MFQM.SoftQuicksandBlock)
              spawnGreenStone(posx, posy, posz, world, random, 0); 
            if (world.getBlock(posx, posy - 1, posz) != MFQM.SoftQuicksandBlock && (
              world.getBlock(posx - 1, posy - 1, posz) == MFQM.SoftQuicksandBlock || world
              .getBlock(posx + 1, posy - 1, posz) == MFQM.SoftQuicksandBlock || world
              .getBlock(posx, posy - 1, posz - 1) == MFQM.SoftQuicksandBlock || world
              .getBlock(posx, posy - 1, posz + 1) == MFQM.SoftQuicksandBlock))
              spawnGreenStone(posx, posy, posz, world, random, 0); 
          } 
        } 
      } 
    } 
  }
  
  private void generateFlatTar(int xx, int yy, int zz, int rad, World world, Random random, int ssg, Block filler, int metaFiller) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    int i;
    for (i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D) {
            if (validPlantMaterial(posx, posy + 1, posz, world))
              world.setBlockToAir(posx, posy + 1, posz); 
            if (validPlantMaterial(posx, posy + 2, posz, world))
              world.setBlockToAir(posx, posy + 2, posz); 
            if (validPlantMaterial(posx, posy + 3, posz, world))
              world.setBlockToAir(posx, posy + 3, posz); 
            if (world.getBlock(posx, posy, posz).getMaterial() == Material.wood && 
              world.getBlock(posx, posy + 1, posz).getMaterial() != Material.wood)
              world.setBlockToAir(posx, posy, posz); 
            if (world.getBlock(posx, posy + 1, posz).getMaterial() == Material.wood && 
              world.getBlock(posx, posy + 2, posz).getMaterial() != Material.wood)
              world.setBlockToAir(posx, posy + 1, posz); 
            if (j == 0) {
              if (world.getBlock(posx, posy, posz).getMaterial() != Material.wood)
                if (world.getBlock(posx, posy + 1, posz).getMaterial() == Material.wood) {
                  world.setBlock(posx, posy, posz, world.getBlock(posx, posy + 1, posz), world.getBlockMetadata(posx, posy + 1, posz), 2);
                } else if (!world.getBlock(posx, posy + 3, posz).getMaterial().isOpaque()) {
                  if (!world.getBlock(posx, posy, posz).hasTileEntity())
                    world.setBlockToAir(posx, posy, posz); 
                  if (!world.getBlock(posx, posy + 1, posz).hasTileEntity())
                    world.setBlockToAir(posx, posy, posz); 
                  if (!world.getBlock(posx, posy + 2, posz).hasTileEntity())
                    world.setBlockToAir(posx, posy + 2, posz); 
                }  
              int re = world.rand.nextInt(4);
              switch (re) {
                case 0:
                  if (world.getBlock(posx - 1, posy, posz).getMaterial().isSolid() && 
                    !world.getBlock(posx - 1, posy, posz).hasTileEntity()) {
                    if (!world.getBlock(posx - 1, posy + 3, posz).getMaterial().isOpaque())
                      world.setBlockToAir(posx - 1, posy, posz); 
                    world.setBlock(posx - 1, posy - 1, posz, filler, metaFiller, 2);
                  } 
                  break;
                case 1:
                  if (world.getBlock(posx + 1, posy, posz).getMaterial().isSolid() && 
                    !world.getBlock(posx + 1, posy, posz).hasTileEntity()) {
                    if (!world.getBlock(posx + 1, posy + 3, posz).getMaterial().isOpaque())
                      world.setBlockToAir(posx + 1, posy, posz); 
                    world.setBlock(posx + 1, posy - 1, posz, filler, metaFiller, 2);
                  } 
                  break;
                case 2:
                  if (world.getBlock(posx, posy, posz - 1).getMaterial().isSolid() && 
                    !world.getBlock(posx, posy, posz - 1).hasTileEntity()) {
                    if (!world.getBlock(posx, posy + 3, posz - 1).getMaterial().isOpaque())
                      world.setBlockToAir(posx, posy, posz - 1); 
                    world.setBlock(posx, posy - 1, posz - 1, filler, metaFiller, 2);
                  } 
                  break;
                case 3:
                  if (world.getBlock(posx, posy, posz + 1).getMaterial().isSolid() && 
                    !world.getBlock(posx, posy, posz + 1).hasTileEntity()) {
                    if (!world.getBlock(posx, posy + 3, posz + 1).getMaterial().isOpaque())
                      world.setBlockToAir(posx, posy, posz + 1); 
                    world.setBlock(posx, posy - 1, posz + 1, filler, metaFiller, 2);
                  } 
                  break;
              } 
            } else if (ssg == 0) {
              if (!world.getBlock(posx, posy, posz).hasTileEntity())
                world.setBlock(posx, posy, posz, MFQM.TarBlock, 0, 2); 
            } else if (!world.getBlock(posx, posy, posz).hasTileEntity() && 
              world.getBlock(posx, posy, posz) != MFQM.TarBlock) {
              world.setBlock(posx, posy, posz, filler, metaFiller, 2);
            } 
            if (ssg == 0) {
              if (!world.getBlock(posx, posy - 1, posz).hasTileEntity())
                world.setBlock(posx, posy - 1, posz, MFQM.TarBlock, 0, 2); 
            } else if (!world.getBlock(posx, posy, posz).hasTileEntity() && 
              world.getBlock(posx, posy - 1, posz) != MFQM.TarBlock) {
              world.setBlock(posx, posy, posz, filler, metaFiller, 2);
            } 
            if (!world.getBlock(posx, posy - 2, posz).hasTileEntity())
              world.setBlock(posx, posy - 2, posz, filler, metaFiller, 2); 
          } 
        } 
      } 
    } 
    for (i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2 + 3; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          if (world.getBlock(posx, posy, posz).getMaterial() != Material.wood && 
            !world.getBlock(posx, posy, posz).hasTileEntity() && 
            world.getBlock(posx, posy, posz) != MFQM.TarBlock) {
            if (world.getBlock(posx - 1, posy, posz) == MFQM.TarBlock || world
              .getBlock(posx + 1, posy, posz) == MFQM.TarBlock || world
              .getBlock(posx, posy + 1, posz) == MFQM.TarBlock || world
              .getBlock(posx, posy, posz - 1) == MFQM.TarBlock || world
              .getBlock(posx, posy, posz + 1) == MFQM.TarBlock)
              world.setBlock(posx, posy, posz, filler, metaFiller, 2); 
            if (world.getBlock(posx, posy - 1, posz) != MFQM.TarBlock && (
              world.getBlock(posx - 1, posy - 1, posz) == MFQM.TarBlock || world
              .getBlock(posx + 1, posy - 1, posz) == MFQM.TarBlock || world
              .getBlock(posx, posy - 1, posz - 1) == MFQM.TarBlock || world
              .getBlock(posx, posy - 1, posz + 1) == MFQM.TarBlock))
              world.setBlock(posx, posy, posz, filler, metaFiller, 2); 
          } 
        } 
      } 
    } 
  }
  
  private void generateCliffsTar(int xx, int yy, int zz, int rad, World world, Random random, Block filler, int metaFiller) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    for (int i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D && (
            world.getBlock(posx, posy, posz).getMaterial().isOpaque() || world.getBlock(posx, posy, posz).getMaterial().isLiquid()) && 
            world.getBlock(posx, posy, posz) != MFQM.TarBlock && 
            !world.getBlock(posx, posy, posz).hasTileEntity() && 
            world.getBlock(posx, posy, posz).getMaterial() != Material.wood)
            world.setBlock(posx, posy, posz, filler, metaFiller, 2); 
        } 
      } 
    } 
  }
  
  private void generateRockTar(int xx, int yy, int zz, int rad, World world, Random random, Block filler, int metaFiller) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    for (int j = rad / 4; j >= 0; j--) {
      for (int i = 0; i <= rad; i++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy + j - rad / 8;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow(((yy - posy) * 4), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D && 
            world.getBlock(posx, posy + 1, posz) != MFQM.TarBlock && 
            !world.getBlock(posx, posy, posz).hasTileEntity()) {
            world.setBlockToAir(posx, posy, posz);
            world.setBlock(posx, posy, posz, filler, metaFiller, 2);
            world.setBlockToAir(posx, posy - 1, posz);
            world.setBlock(posx, posy - 1, posz, filler, metaFiller, 2);
          } 
        } 
      } 
    } 
  }
  
  private void generateCliffs(int xx, int yy, int zz, int rad, World world, Random random) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    for (int i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D && (
            world.getBlock(posx, posy, posz).getMaterial().isOpaque() || world.getBlock(posx, posy, posz).getMaterial().isLiquid()) && 
            world.getBlock(posx, posy, posz) != MFQM.SoftQuicksandBlock && 
            world.getBlock(posx, posy, posz).getMaterial() != Material.wood)
            spawnGreenStone(posx, posy, posz, world, random, 0); 
        } 
      } 
    } 
  }
  
  private void generateRock(int xx, int yy, int zz, int rad, World world, Random random) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    for (int i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 4; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy + j - rad / 8;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow(((yy - posy) * 4), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D) {
            spawnGreenStone(posx, posy, posz, world, random, 0);
            spawnGreenStone(posx, posy - 1, posz, world, random, 0);
          } 
        } 
      } 
    } 
  }
  
  private void generateMoss(int xx, int yy, int zz, int rad, World world, Random random) {
    Block sblock = soilBlock(xx, zz, world);
    Block fblock = fillerBlock(xx, zz, world);
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    boolean aTBL = (world.provider.dimensionId == MFQM.TBLDim);
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    for (int i = 0; i <= rad; i++) {
      for (int j = 0; j <= 0; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D && (
            world.getBlock(posx, posy, posz) == Blocks.air || world.getBlock(posx, posy, posz).getMaterial() == Material.plants || world.getBlock(posx, posy, posz).getMaterial() == Material.vine || world.getBlock(posx, posy, posz).getMaterial().isReplaceable() || world.getBlock(posx, posy, posz).getMaterial().isLiquid()) && 
            world.getBlock(posx, posy - 1, posz).getMaterial().isLiquid()) {
            world.setBlock(posx, posy, posz, MFQM.MossBlock);
            if (!world.getBlock(posx, posy - 2, posz).getMaterial().isLiquid()) {
              world.setBlock(posx, posy - 2, posz, world.getBlock(posx, posy - 1, posz), 0, 0);
              if (aTBL) {
                world.setBlock(posx, posy - 3, posz, MFQM.MudBlock, 3, 0);
              } else {
                world.setBlock(posx, posy - 3, posz, fblock);
              } 
              if (world.getBlock(posx + 1, posy - 1, posz).getMaterial().isLiquid() && 
                !world.getBlock(posx + 1, posy - 2, posz).getMaterial().isLiquid()) {
                world.setBlock(posx + 1, posy - 2, posz, world.getBlock(posx, posy - 1, posz), 0, 0);
                if (aTBL) {
                  world.setBlock(posx + 1, posy - 3, posz, MFQM.MudBlock, 3, 0);
                } else {
                  world.setBlock(posx + 1, posy - 3, posz, fblock);
                } 
              } 
              if (world.getBlock(posx - 1, posy - 1, posz).getMaterial().isLiquid() && 
                !world.getBlock(posx - 1, posy - 2, posz).getMaterial().isLiquid()) {
                world.setBlock(posx - 1, posy - 2, posz, world.getBlock(posx, posy - 1, posz), 0, 0);
                if (aTBL) {
                  world.setBlock(posx - 1, posy - 3, posz, MFQM.MudBlock, 3, 0);
                } else {
                  world.setBlock(posx - 1, posy - 3, posz, fblock);
                } 
              } 
              if (world.getBlock(posx, posy - 1, posz - 1).getMaterial().isLiquid() && 
                !world.getBlock(posx, posy - 2, posz - 1).getMaterial().isLiquid()) {
                world.setBlock(posx, posy - 2, posz - 1, world.getBlock(posx, posy - 1, posz), 0, 0);
                if (aTBL) {
                  world.setBlock(posx, posy - 3, posz - 1, MFQM.MudBlock, 3, 0);
                } else {
                  world.setBlock(posx, posy - 3, posz - 1, fblock);
                } 
              } 
              if (world.getBlock(posx, posy - 1, posz + 1).getMaterial().isLiquid() && 
                !world.getBlock(posx, posy - 2, posz + 1).getMaterial().isLiquid()) {
                world.setBlock(posx, posy - 2, posz + 1, world.getBlock(posx, posy - 1, posz), 0, 0);
                if (aTBL) {
                  world.setBlock(posx, posy - 3, posz + 1, MFQM.MudBlock, 3, 0);
                } else {
                  world.setBlock(posx, posy - 3, posz + 1, fblock);
                } 
              } 
            } 
          } 
        } 
      } 
    } 
  }
  
  private boolean generateWaxTree(int xx, int yy, int zz, int rad, World world, Random random) {
    int waxx = 0;
    int waxy = 0;
    int waxz = 0;
    int[] sides = new int[4];
    int count = 0;
    int i;
    for (i = 0; i <= rad && waxy == 0; i++) {
      for (int e = 0; e <= rad && waxy == 0; e++) {
        int posx = xx + i - rad / 2;
        int posy = yy;
        int posz = zz + e - rad / 2;
        if (world.getBlock(posx, posy, posz) == Blocks.log && 
          world.getBlockMetadata(posx, posy, posz) == 3) {
          int tmpCheck = 0;
          if (world.getBlock(posx - 1, posy, posz) == Blocks.air || world.getBlock(posx - 1, posy, posz).getMaterial().isReplaceable() || world.getBlock(posx - 1, posy, posz).getMaterial() == Material.vine) {
            tmpCheck++;
            sides[0] = 1;
          } 
          if (world.getBlock(posx, posy, posz + 1) == Blocks.air || world.getBlock(posx, posy, posz + 1).getMaterial().isReplaceable() || world.getBlock(posx, posy, posz + 1).getMaterial() == Material.vine) {
            tmpCheck++;
            sides[1] = 2;
          } 
          if (world.getBlock(posx + 1, posy, posz) == Blocks.air || world.getBlock(posx + 1, posy, posz).getMaterial().isReplaceable() || world.getBlock(posx + 1, posy, posz).getMaterial() == Material.vine) {
            tmpCheck++;
            sides[2] = 3;
          } 
          if (world.getBlock(posx, posy, posz - 1) == Blocks.air || world.getBlock(posx, posy, posz - 1).getMaterial().isReplaceable() || world.getBlock(posx, posy, posz - 1).getMaterial() == Material.vine) {
            tmpCheck++;
            sides[3] = 4;
          } 
          if (tmpCheck > 0) {
            waxx = posx;
            waxy = posy;
            waxz = posz;
            count = tmpCheck;
          } 
        } 
      } 
    } 
    if (waxy != 0) {
      for (i = 0; i < 25; i++) {
        if (world.getBlock(waxx, waxy + i, waxz) == MFQM.WaxWoodBlock)
          return false; 
        if (world.getBlock(waxx, waxy - i, waxz) == MFQM.WaxWoodBlock)
          return false; 
        if (world.getBlock(waxx + 1, waxy, waxz) == MFQM.WaxWoodBlock)
          return false; 
        if (world.getBlock(waxx - 1, waxy, waxz) == MFQM.WaxWoodBlock)
          return false; 
        if (world.getBlock(waxx, waxy, waxz + 1) == MFQM.WaxWoodBlock)
          return false; 
        if (world.getBlock(waxx, waxy, waxz - 1) == MFQM.WaxWoodBlock)
          return false; 
      } 
      int[] sidesTrue = new int[count];
      int j = 0;
      for (int k = 0; k < sides.length; k++) {
        if (sides[k] != 0) {
          sidesTrue[j] = sides[k];
          j++;
        } 
      } 
      int side = random.nextInt(count);
      int meta = -1;
      int waxxx = waxx;
      int waxyy = waxy;
      int waxzz = waxz;
      switch (sidesTrue[side]) {
        case 1:
          if (world.getBlock(waxx + 1, waxy, waxz) == Blocks.log) {
            meta = 1;
            waxxx = waxx - 1;
            break;
          } 
          return false;
        case 2:
          if (world.getBlock(waxx, waxy, waxz - 1) == Blocks.log) {
            meta = 0;
            waxzz = waxz + 1;
            break;
          } 
          return false;
        case 3:
          if (world.getBlock(waxx - 1, waxy, waxz) == Blocks.log) {
            meta = 3;
            waxxx = waxx + 1;
            break;
          } 
          return false;
        case 4:
          if (world.getBlock(waxx, waxy, waxz + 1) == Blocks.log) {
            meta = 2;
            waxzz = waxz - 1;
            break;
          } 
          return false;
        default:
          return false;
      } 
      world.setBlock(waxx, waxy, waxz, MFQM.WaxWoodBlock, meta, 0);
      world.setBlockToAir(waxxx, waxyy, waxzz);
      boolean genHill = false;
      while (waxyy > 60) {
        waxyy--;
        if (world.getBlock(waxx, waxyy, waxz) == Blocks.log) {
          world.setBlock(waxx, waxyy, waxz, MFQM.WaxWoodBlock, meta + 4, 0);
          if (world.getBlock(waxxx, waxyy, waxzz).getMaterial().isReplaceable() || world.getBlock(waxxx, waxyy, waxzz).getMaterial() == Material.vine || world.getBlock(waxxx, waxyy, waxzz).getMaterial() == Material.leaves || world.getBlock(waxxx, waxyy, waxzz).getMaterial() == Material.plants) {
            world.setBlockToAir(waxxx, waxyy, waxzz);
            continue;
          } 
          genHill = true;
          break;
        } 
        genHill = true;
      } 
      if (genHill && waxyy < 70) {
        int rada = random.nextInt(4) + 15;
        int xpos = waxx + (waxxx - waxx) * rada / 5;
        int zpos = waxz + (waxzz - waxz) * rada / 5;
        generateHill(xpos, waxyy + 1, zpos, rada, world, random);
        int hc = random.nextInt(2) + 5;
        int m;
        for (m = 0; m < hc; m++) {
          int rada2 = random.nextInt(4) + 4;
          generateHill(xpos + random.nextInt(rada / 2 + 1) - rada / 4, waxyy + random.nextInt(3) - 1, zpos + random.nextInt(rada / 2 + 1) - rada / 4, rada2, world, random);
        } 
        xpos = waxx + (waxxx - waxx) * 2;
        zpos = waxz + (waxzz - waxz) * 2;
        world.setBlock(waxxx, waxyy, waxzz, MFQM.WaxBlock, 4, 0);
        hc = random.nextInt(2) + 1;
        for (m = 0; m < hc; m++) {
          int rada2 = random.nextInt(2) + 4;
          generateWax(xpos, waxyy, zpos, rada2, world, random);
        } 
      } 
      return true;
    } 
    return false;
  }
  
  private void generateWax(int xx, int yy, int zz, int rad, World world, Random random) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = random.nextDouble() + 0.5D;
    double raz = 1.0D - rax + 0.5D + 0.5D;
    for (int i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad / 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) * rax + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D) * raz);
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D && 
            world.getBlock(posx, posy, posz) != MFQM.SoftQuicksandBlock) {
            if (validPlantMaterial(posx, posy + 1, posz, world))
              world.setBlockToAir(posx, posy + 1, posz); 
            if (validPlantMaterial(posx, posy + 2, posz, world))
              world.setBlockToAir(posx, posy + 2, posz); 
            if (validPlantMaterial(posx, posy + 3, posz, world))
              world.setBlockToAir(posx, posy + 3, posz); 
            if (world.getBlock(posx, posy + 1, posz).getMaterial() == Material.wood && 
              world.getBlock(posx, posy + 2, posz).getMaterial() != Material.wood)
              world.setBlockToAir(posx, posy + 1, posz); 
            world.setBlock(posx, posy, posz, MFQM.WaxBlock, 4, 0);
            if (world.getBlock(posx, posy - 1, posz) == Blocks.air || world.getBlock(posx, posy - 1, posz).getMaterial().isReplaceable() || world.getBlock(posx, posy - 1, posz).getMaterial() == Material.vine)
              world.setBlock(posx, posy - 1, posz, MFQM.WaxBlock, 5, 0); 
          } 
        } 
      } 
    } 
  }
  
  private void generateHill(int xx, int yy, int zz, int rad, World world, Random random) {
    double rad_cof = Math.max(rad / 10.0D, 1.0D);
    double rax = 1.0D;
    double raz = 1.0D;
    int i;
    for (i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad * 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j + rad;
          int posz = zz + e - rad / 2;
          double dis = Math.sqrt(Math.pow((xx - posx), 2.0D) + Math.pow((yy - posy), 2.0D) + Math.pow((zz - posz), 2.0D));
          if (dis < rad / 2.0D / (1.0D + Math.abs(rax - raz) / 3.0D) + random.nextDouble() * rad_cof - rad_cof / 2.0D && 
            j > rad - rad / 4) {
            int posyp = posy - rad / 4;
            if (world.getBlock(posx, posyp, posz).getMaterial() != Material.wood)
              if (world.getBlock(posx, posyp + 1, posz).getMaterial().isOpaque()) {
                world.setBlock(posx, posyp, posz, Blocks.dirt);
                world.setBlock(posx, posyp - 1, posz, Blocks.dirt);
                world.setBlock(posx, posyp - 2, posz, Blocks.dirt);
              } else if (!world.getBlock(posx, posyp + 1, posz).getMaterial().isLiquid()) {
                world.setBlock(posx, posyp, posz, (Block)Blocks.grass);
                if (random.nextInt(5) == 0)
                  Blocks.grass.fertilize(world, random, posx, posy, posz); 
                world.setBlock(posx, posyp - 1, posz, Blocks.dirt);
                world.setBlock(posx, posyp - 2, posz, Blocks.dirt);
              } else {
                world.setBlock(posx, posyp, posz, Blocks.dirt);
                world.setBlock(posx, posyp - 1, posz, Blocks.dirt);
                world.setBlock(posx, posyp - 2, posz, Blocks.dirt);
              }  
          } 
        } 
      } 
    } 
    for (i = 0; i <= rad; i++) {
      for (int j = 0; j <= rad * 2 + 2; j++) {
        for (int e = 0; e <= rad; e++) {
          int posx = xx + i - rad / 2;
          int posy = yy - j + rad;
          int posz = zz + e - rad / 2;
          int posyp = posy - rad / 4;
          if (world.getBlock(posx, posyp, posz) == Blocks.grass || world.getBlock(posx, posyp, posz) == Blocks.dirt) {
            int cnt = 0;
            if (world.getBlock(posx + 1, posyp, posz).getMaterial().isSolid())
              cnt++; 
            if (world.getBlock(posx - 1, posyp, posz).getMaterial().isSolid())
              cnt++; 
            if (world.getBlock(posx, posyp, posz - 1).getMaterial().isSolid())
              cnt++; 
            if (world.getBlock(posx, posyp, posz + 1).getMaterial().isSolid())
              cnt++; 
            if (cnt < 2) {
              int cnt2 = 0;
              if (world.getBlock(posx + 1, posyp, posz).getMaterial() == Material.water)
                cnt2++; 
              if (world.getBlock(posx - 1, posyp, posz).getMaterial() == Material.water)
                cnt2++; 
              if (world.getBlock(posx, posyp, posz - 1).getMaterial() == Material.water)
                cnt2++; 
              if (world.getBlock(posx, posyp, posz + 1).getMaterial() == Material.water)
                cnt2++; 
              if (world.getBlock(posx, posyp + 1, posz).getMaterial() == Material.water)
                cnt2++; 
              if (cnt2 > 0) {
                world.setBlock(posx, posyp, posz, (Block)Blocks.flowing_water);
              } else {
                world.setBlockToAir(posx, posyp, posz);
                if (world.getBlock(posx, posyp - 1, posz) == Blocks.dirt)
                  world.setBlock(posx, posyp - 1, posz, (Block)Blocks.grass); 
              } 
              if (validPlantMaterial(posx, posyp + 1, posz, world))
                world.setBlockToAir(posx, posyp + 1, posz); 
            } 
          } 
        } 
      } 
    } 
  }
  
  private int getSwampType(int ui) {
    if (this.T_BoP) {
      if (ui == MFQM.SwampBiomes[12] || ui == MFQM.SwampBiomes[11] || ui == MFQM.SwampBiomes[10] || ui == MFQM.SwampBiomes[3] || ui == MFQM.SwampBiomes[0])
        return 2; 
      if (ui == MFQM.SwampBiomes[2])
        return 1; 
      return 0;
    } 
    return 2;
  }
  
  private boolean isSwampIsNear(int xx, int yy, int zz, World world) {
    int xxx = xx - 32;
    int zzz = zz - 32;
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        xxx = xx - 32 + 16 * i;
        zzz = zz - 32 + 16 * j;
        if (xxx != xx || zzz != zz) {
          BiomeGenBase bb = world.getBiomeGenForCoords(xxx, zzz);
          if (bb instanceof net.minecraft.world.biome.BiomeGenSwamp)
            return true; 
        } 
      } 
    } 
    return false;
  }
  
  private boolean isJungleIsNear(int xx, int yy, int zz, World world) {
    int xxx = xx - 16;
    int zzz = zz - 16;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        xxx = xx - 16 + 16 * i;
        zzz = zz - 16 + 16 * j;
        if (xxx != xx || zzz != zz) {
          BiomeGenBase bb = world.getBiomeGenForCoords(xxx, zzz);
          if (bb instanceof net.minecraft.world.biome.BiomeGenJungle)
            return true; 
        } 
      } 
    } 
    return false;
  }
  
  private int isTarBiomeIsNear(int xx, int yy, int zz, World world) {
    int xxx = xx - 32;
    int zzz = zz - 32;
    if (this.T_BoP)
      for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
          xxx = xx - 32 + 16 * i;
          zzz = zz - 32 + 16 * j;
          BiomeGenBase bb = world.getBiomeGenForCoords(xxx, zzz);
          if (getTarCount(bb.biomeID) > 0)
            return getTarCount(bb.biomeID); 
        } 
      }  
    return 0;
  }
  
  private boolean isRiverIsNear(int xx, int yy, int zz, World world) {
    int xxx = xx - 32;
    int zzz = zz - 32;
    if (this.T_BoP)
      for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
          xxx = xx - 32 + 16 * i;
          zzz = zz - 32 + 16 * j;
          if (xxx != xx || zzz != zz) {
            BiomeGenBase bb = world.getBiomeGenForCoords(xxx, zzz);
            if (bb instanceof net.minecraft.world.biome.BiomeGenRiver)
              return true; 
          } 
        } 
      }  
    return false;
  }
  
  private int getTarCount(int ui) {
    if (this.T_BoP) {
      if (ui == MFQM.TarBiomes[0])
        return 1; 
      if (ui == MFQM.TarBiomes[1])
        return 2; 
      if (ui == MFQM.TarBiomes[2])
        return 3; 
      if (ui == MFQM.TarBiomes[3])
        return 4; 
    } 
    return 0;
  }
  
  private int getJungleType(int ui) {
    if (this.T_BoP) {
      if (ui == MFQM.JungleBiomes[0])
        return 3; 
      if (ui == MFQM.JungleBiomes[1])
        return 2; 
      if (ui == MFQM.JungleBiomes[2])
        return 1; 
    } 
    return 0;
  }
  
  private int getDesertType(int ui) {
    if (this.T_BoP) {
      if (ui == MFQM.DesertBiomes[0])
        return 4; 
      if (ui == MFQM.DesertBiomes[1])
        return 3; 
      if (ui == MFQM.DesertBiomes[2])
        return 2; 
      if (ui == MFQM.DesertBiomes[3])
        return 1; 
      if (ui == MFQM.DesertBiomes[4])
        return 6; 
    } 
    return 0;
  }
  
  private int getHillsType(int ui) {
    if (this.T_BoP) {
      if (ui == MFQM.MineralBiomes[0])
        return 1; 
      if (ui == MFQM.MineralBiomes[1])
        return 2; 
      if (ui == MFQM.MineralBiomes[2])
        return 3; 
      if (ui == MFQM.MineralBiomes[3])
        return 4; 
      if (ui == MFQM.MineralBiomes[4])
        return 5; 
      if (ui == MFQM.MineralBiomes[5])
        return 6; 
    } 
    return 0;
  }
  
  private int getSnowType(int ui) {
    if (this.T_BoP) {
      if (ui == MFQM.SnowBiomes[0])
        return 1; 
      if (ui == MFQM.SnowBiomes[1])
        return 2; 
    } 
    return 0;
  }
  
  private int getLarvType(int ui) {
    if (this.T_BoP) {
      if (ui == MFQM.LarvBiomes[0])
        return 1; 
      if (ui == MFQM.LarvBiomes[1])
        return 2; 
      if (ui == MFQM.LarvBiomes[2])
        return 3; 
    } 
    return 0;
  }
  
  private int getCSandsType(int ui) {
    if (this.T_BoP && 
      ui == MFQM.CorruptedSands[0])
      return 1; 
    return 0;
  }
  
  private int getBloodType(int ui) {
    if (this.T_BoP && 
      ui == MFQM.BloodHeaps[0])
      return 1; 
    return 0;
  }
  
  private int getSlurryType(int ui) {
    if (this.T_BoP && 
      ui == MFQM.WastelandBiomes[0])
      return 1; 
    return 0;
  }
  
  private Vec3 getXcor(World world, int x, int y, int z, int rad) {
    int xinc = 0;
    int xdec = 0;
    for (int i = 1; i < rad; i++) {
      if (xinc == 0 && 
        !isAboveFreeSpace(x + i, y + 3, z, world))
        xinc = i - 1; 
      if (xdec == 0 && 
        !isAboveFreeSpace(x - i, y + 3, z, world))
        xdec = i - 1; 
      if (xdec != 0 && 
        xinc == 0)
        return Vec3.createVectorHelper(((xinc + xdec) / 2 - xdec), ((xinc + xdec) / 2), 0.0D); 
    } 
    return Vec3.createVectorHelper(0.0D, rad, 0.0D);
  }
  
  private Vec3 getZcor(World world, int x, int y, int z, int rad) {
    int zinc = 0;
    int zdec = 0;
    for (int i = 1; i < rad; i++) {
      if (zinc == 0 && 
        !isAboveFreeSpace(x, y + 3, z + i, world))
        zinc = i - 1; 
      if (zdec == 0 && 
        !isAboveFreeSpace(x, y + 3, z - i, world))
        zdec = i - 1; 
      if (zdec != 0 && 
        zinc == 0)
        return Vec3.createVectorHelper(((zinc + zdec) / 2 - zdec), ((zinc + zdec) / 2), 0.0D); 
    } 
    return Vec3.createVectorHelper(0.0D, rad, 0.0D);
  }
  
  private int getPeatBogType(int ui) {
    if (this.T_BoP) {
      if (ui == MFQM.PeatBogBiomes[1] || ui == MFQM.PeatBogBiomes[4])
        return 1; 
      if (ui == MFQM.PeatBogBiomes[3])
        return 0; 
      if (ui == MFQM.PeatBogBiomes[5] || ui == MFQM.PeatBogBiomes[6])
        return 3; 
    } 
    return 0;
  }
  
  private boolean getPeatBogGenBirch(int ui) {
    if (this.T_BoP)
      for (int O = 0; O < 7; O++) {
        if (ui == MFQM.PeatBogBiomes[O]) {
          if (O < 3)
            return true; 
          return false;
        } 
      }  
    return false;
  }
  
  private int getForestTypeBOP(int ui) {
    if (this.T_BoP && (
      ui == MFQM.SQSBiomes[0] || ui == MFQM.SQSBiomes[1] || ui == MFQM.SQSBiomes[2] || ui == MFQM.SQSBiomes[3] || ui == MFQM.SQSBiomes[4] || ui == MFQM.SQSBiomes[5] || ui == MFQM.SQSBiomes[6] || ui == MFQM.SQSBiomes[7] || ui == MFQM.SQSBiomes[8] || ui == MFQM.SQSBiomes[9]))
      return 1; 
    return 0;
  }
  
  private int getForestTypeVan(int ui) {
    if (!this.T_BoP && (
      ui == MFQM.SQSBiomes[7] || ui == MFQM.SQSBiomes[8] || ui == MFQM.SQSBiomes[9]))
      return 1; 
    return 0;
  }
  
  private void generateAOADimDustopia(World world, Random random, int chunkX, int chunkZ) {}
  
  private void generateAOADimVoxPonds(World world, Random random, int chunkX, int chunkZ) {}
  
  private void generateAOADimShyrelands(World world, Random random, int chunkX, int chunkZ) {}
  
  private void generateAOADimCandyland(World world, Random random, int chunkX, int chunkZ) {}
  
  private void generateAOADimBarathos(World world, Random random, int chunkX, int chunkZ) {
    int dHeight = 0;
    int firstBlockXCoord = chunkX + random.nextInt(16);
    int firstBlockYCoord = 96;
    int firstBlockZCoord = chunkZ + random.nextInt(16);
    BiomeGenBase biomegenbase = world.getBiomeGenForCoords(firstBlockXCoord, firstBlockZCoord);
    int biomeId = biomegenbase.biomeID;
    boolean blockGen = false;
    if (!blockGen) {
      boolean canGen = false;
      int genCount = 10 + random.nextInt(10);
      int genPos = 50;
      int genRad = 8;
      int genRadP = 10;
      int rad = 0;
      if (random.nextInt(genPos) == 0) {
        boolean Flat = false;
        if (random.nextInt(10) == 0)
          Flat = true; 
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) != MFQM.TarBlock) {
              if (validSolidMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
                !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  break;
                } 
                countBlock++;
              } 
            } else {
              canGen = false;
              break;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && 
            genIsFlatSolid(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
            genIsFlatSolid(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
            generateMireOD(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.SoftGravelBlock, 1);
            blockGen = true;
          } 
        } 
      } 
    } 
    if (MFQM.GenTar && !blockGen) {
      int trType = 0;
      int tarP1 = 0;
      int tarPU = 0;
      tarP1 = 40;
      tarPU = 5;
      if (random.nextInt(tarP1) == 0) {
        int var5 = chunkX + random.nextInt(16) + 8;
        int var6 = 60 + random.nextInt(21) + dHeight;
        int var7 = chunkZ + random.nextInt(16) + 8;
        (new CustomWorldGenLakes(MFQM.TarBlock)).generate(world, random, var5, var6, var7, 1);
        blockGen = true;
      } 
      if (random.nextInt(tarPU) == 0) {
        int var5 = chunkX + random.nextInt(16) + 8;
        int var6 = random.nextInt(64) + dHeight;
        int var7 = chunkZ + random.nextInt(16) + 8;
        (new CustomWorldGenLakes(MFQM.TarBlock)).generate(world, random, var5, var6, var7, 1);
        blockGen = true;
      } 
    } 
  }
  
  private void generateAOADimCrystevia(World world, Random random, int chunkX, int chunkZ) {
    int firstBlockXCoord = chunkX + random.nextInt(16);
    int firstBlockYCoord = 96;
    int firstBlockZCoord = chunkZ + random.nextInt(16);
    BiomeGenBase biomegenbase = world.getBiomeGenForCoords(firstBlockXCoord, firstBlockZCoord);
    int biomeId = biomegenbase.biomeID;
    boolean blockGen = false;
    if (MFQM.GenLiqMire)
      for (int hc = -1; hc <= 1; hc++) {
        for (int vc = -1; vc <= 1; vc++) {
          if (world.blockExists(chunkX + 8 + hc * 16, 32, chunkZ + 8 + vc * 16))
            for (int h = 0; h < 16; h++) {
              for (int v = 0; v < 16; v++) {
                firstBlockXCoord = chunkX + hc * 16 + h;
                firstBlockYCoord = 128;
                firstBlockZCoord = chunkZ + vc * 16 + v;
                while (firstBlockYCoord > 30) {
                  if (validWaterMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world))
                    world.setBlockToAir(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord); 
                  firstBlockYCoord--;
                } 
              } 
            }  
        } 
      }  
    if (!blockGen) {
      int trType = 0;
      int tarP2 = 10;
      int realGen = 1 + random.nextInt(3);
      boolean canGen = false;
      int genCount = 150;
      int genRad = 7;
      int genRadP = 9;
      int rad = 0;
      if (random.nextInt(tarP2) == 0)
        for (int i = 0; i < genCount && realGen > 0; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 30 + random.nextInt(98);
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 10) {
            if (validSolidMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
              !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                break;
              } 
              countBlock++;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && 
            genIsFlatSolid(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad - 2, world) && 
            genIsFlatSolid(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad - 2, world)) {
            generateMireOD(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.MucusBlock, 0);
            realGen--;
            blockGen = true;
          } 
        }  
    } 
    if (MFQM.GenTar) {
      int tarP1 = 0;
      int tarP2 = 0;
      int tarPU = 0;
      tarP1 = 1;
      tarP2 = 3;
      tarPU = 3;
      if (random.nextInt(tarP1) == 0 && 
        random.nextInt(tarP2) == 0) {
        int var5 = chunkX + random.nextInt(16) + 8;
        int var6 = random.nextInt(128);
        int var7 = chunkZ + random.nextInt(16) + 8;
        (new CustomWorldGenLakes(MFQM.TarBlock)).generate(world, random, var5, var6, var7, 0);
        blockGen = true;
      } 
    } 
  }
  
  private void generateAOADimGreckon(World world, Random random, int chunkX, int chunkZ) {
    int dHeight = 5;
    int firstBlockXCoord = chunkX + random.nextInt(16);
    int firstBlockYCoord = 96 + dHeight;
    int firstBlockZCoord = chunkZ + random.nextInt(16);
    BiomeGenBase biomegenbase = world.getBiomeGenForCoords(firstBlockXCoord, firstBlockZCoord);
    int biomeId = biomegenbase.biomeID;
    boolean blockGen = false;
    if (MFQM.GenLiqMire)
      for (int hc = -1; hc <= 1; hc++) {
        for (int vc = -1; vc <= 1; vc++) {
          if (world.blockExists(chunkX + 8 + hc * 16, 32, chunkZ + 8 + vc * 16))
            for (int h = 0; h < 16; h++) {
              for (int v = 0; v < 16; v++) {
                firstBlockXCoord = chunkX + hc * 16 + h;
                firstBlockYCoord = 2;
                firstBlockZCoord = chunkZ + vc * 16 + v;
                while (firstBlockYCoord <= 128) {
                  if (validWaterMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
                    world.getBlockMetadata(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) == 0 && 
                    world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) != MFQM.StableLiquidMireBlock) {
                    world.setBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, MFQM.StableLiquidMireBlock, 0, 2);
                    if (validSolidMaterial(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord, world)) {
                      world.setBlock(firstBlockXCoord, firstBlockYCoord - 2, firstBlockZCoord, world.getBlock(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord), world.getBlockMetadata(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord), 2);
                      world.setBlock(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord, MFQM.MireBlock, 10, 2);
                    } 
                  } 
                  if (firstBlockYCoord <= 7 && 
                    !validSolidMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
                    world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) != MFQM.StableLiquidMireBlock)
                    world.setBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, MFQM.StableLiquidMireBlock, 0, 2); 
                  firstBlockYCoord++;
                } 
              } 
            }  
        } 
      }  
    if ((MFQM.GenMire || MFQM.GenMud) && !blockGen) {
      boolean canGen = false;
      int genType = 0;
      int genCount = 0;
      int genPos = 0;
      int genRad = 0;
      int genRadP = 0;
      int rad = 0;
      genCount = 20 + random.nextInt(10);
      genRad = 6;
      genRadP = 7;
      genPos = 10;
      int jur = 0;
      boolean try_gen_mud = false;
      boolean gen_mud = false;
      if (random.nextInt(genPos / 3 + 1) == 0) {
        gen_mud = true;
        if (random.nextInt(genPos + 1) == 0)
          try_gen_mud = true; 
      } 
      if (random.nextInt(genPos) == 0 && (
        MFQM.GenMire || MFQM.GenMud)) {
        int i;
        for (i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (validGroundOrLMireMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                break;
              } 
              countBlock++;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && 
            genIsFlat(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
            genIsFlat(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
            if (MFQM.GenMire)
              if (genIsFlat(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad * 2, world)) {
                generateMireOD(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.MireBlock, 0);
              } else {
                generateMireO(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.MireBlock);
              }  
            try_gen_mud = true;
            blockGen = true;
          } 
        } 
        if (try_gen_mud && gen_mud && 
          MFQM.GenMud)
          for (i = 0; i < genCount; i++) {
            firstBlockXCoord = chunkX + random.nextInt(16);
            firstBlockYCoord = 96 + dHeight;
            firstBlockZCoord = chunkZ + random.nextInt(16);
            rad = genRad * 3 + random.nextInt(genRadP);
            int countBlock = 0;
            canGen = false;
            while (firstBlockYCoord > 60 + dHeight) {
              if (validGroundOrLMireMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  break;
                } 
                countBlock++;
              } 
              if (countBlock > 10)
                break; 
              firstBlockYCoord--;
            } 
            if (canGen)
              generateMud(firstBlockXCoord, firstBlockYCoord + 3, firstBlockZCoord, rad, world, random); 
          }  
      } 
      if (MFQM.GenMud) {
        genCount = 30 + random.nextInt(20);
        genRad = 6;
        genRadP = 7;
        genPos = 1;
        if (random.nextInt(genPos) == 0 && genPos != 999)
          for (int i = 0; i < genCount; i++) {
            firstBlockXCoord = chunkX + random.nextInt(16);
            firstBlockYCoord = 96 + dHeight;
            firstBlockZCoord = chunkZ + random.nextInt(16);
            int countBlock = 0;
            canGen = false;
            while (firstBlockYCoord > 55 + dHeight) {
              if (validGroundOrLMireMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  break;
                } 
                countBlock++;
              } 
              if (countBlock > 10)
                break; 
              firstBlockYCoord--;
            } 
            if (canGen)
              generateMud(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world, random); 
          }  
      } 
    } 
    if (MFQM.GenTar) {
      int trType = 0;
      int tarP1 = 5;
      int tarP2 = 5;
      int tarPU = 5;
      if (random.nextInt(tarP1) == 0 && 
        random.nextInt(tarPU) == 0) {
        int var5 = chunkX + random.nextInt(16) + 8;
        int var6 = random.nextInt(64) + dHeight;
        int var7 = chunkZ + random.nextInt(16) + 8;
        (new CustomWorldGenLakes(MFQM.TarBlock)).generate(world, random, var5, var6, var7, 1);
      } 
    } 
  }
  
  private void generateAOADimGardencia(World world, Random random, int chunkX, int chunkZ) {
    int dHeight = 0;
    int firstBlockXCoord = chunkX + random.nextInt(16);
    int firstBlockYCoord = 96 + dHeight;
    int firstBlockZCoord = chunkZ + random.nextInt(16);
    BiomeGenBase biomegenbase = world.getBiomeGenForCoords(firstBlockXCoord, firstBlockZCoord);
    int biomeId = biomegenbase.biomeID;
    boolean blockGen = false;
    if (MFQM.GenMud)
      for (int h = 0; h < 16; h++) {
        for (int v = 0; v < 16; v++) {
          firstBlockXCoord = chunkX + h;
          firstBlockYCoord = 60;
          firstBlockZCoord = chunkZ + v;
          if (validWaterMaterial(firstBlockXCoord, 66, firstBlockZCoord, world)) {
            if (!validSolidMaterial(firstBlockXCoord, 65, firstBlockZCoord, world))
              world.setBlock(firstBlockXCoord, 65, firstBlockZCoord, Blocks.water, 0, 2); 
            while (firstBlockYCoord <= 64) {
              if (!validSolidMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world))
                world.setBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, MFQM.MudBlock, 3, 2); 
              firstBlockYCoord++;
            } 
          } 
        } 
      }  
    if (MFQM.GenJQS && !blockGen) {
      boolean canGen = false;
      int genCount = 50 + random.nextInt(10);
      int genPos = 10;
      int genRad = 6;
      int genRadP = 6;
      int rad = 0;
      if (random.nextInt(genPos) == 0) {
        boolean Flat = false;
        if (random.nextInt(10) == 0)
          Flat = true; 
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 68 + dHeight) {
            if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) != MFQM.TarBlock) {
              if (validSolidMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
                !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  break;
                } 
                countBlock++;
              } 
            } else {
              canGen = false;
              break;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && 
            genIsFlatSolid(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
            genIsFlatSolid(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
            if (Flat) {
              generateMireO(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.JungleQuicksandBlock);
            } else {
              generateMireOD(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.JungleQuicksandBlock, 0);
            } 
            generateLeavesAround(firstBlockXCoord, firstBlockYCoord + 3, firstBlockZCoord, rad + 3, world, random);
            blockGen = true;
          } 
        } 
      } 
    } 
    if (MFQM.GenMucBl && 
      !blockGen) {
      boolean canGen = false;
      int genCount = 0;
      int genPos = 0;
      int rad = 6;
      genCount = 50;
      genPos = 100;
      if (genPos > 0 && 
        random.nextInt(genPos) == 0)
        for (int i = 0; i < genCount && !blockGen; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 70 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (validGroundMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world))
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                if (!validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                  canGen = true;
                  break;
                } 
              } else {
                countBlock++;
              }  
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && 
            genIsFlat(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord, rad, world) && 
            genIsFlat(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world)) {
            (new GenerateMucusBlossom()).generate(world, random, firstBlockXCoord - 3, firstBlockYCoord - 6, firstBlockZCoord - 3);
            blockGen = true;
          } 
        }  
    } 
    dHeight = 4;
    if (MFQM.GenMoor) {
      int MoorP = 15;
      int genCount = random.nextInt(30) + 50;
      int genRad = 20;
      int genRadP = 10;
      int BGPX = 0;
      int BGPY = 0;
      int BGPZ = 0;
      if (random.nextInt(MoorP) == 0)
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX - 16 + random.nextInt(64);
          firstBlockYCoord = 62 + dHeight;
          firstBlockZCoord = chunkZ - 16 + random.nextInt(64);
          int rad = genRad + random.nextInt(genRadP);
          boolean canGen = false;
          if (!canGen && 
            validWaterMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
            isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world))
            canGen = true; 
          if (canGen && 
            isGroundQS(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, (int)Math.round(rad * 1.5D), world, MFQM.MorassBlock)) {
            if (BGPX == 0) {
              BGPX = firstBlockXCoord;
              BGPY = firstBlockYCoord;
              BGPZ = firstBlockZCoord;
            } 
            float disToC = Math.max((float)Math.sqrt(Math.pow((firstBlockXCoord - BGPX), 2.0D) + Math.pow((firstBlockZCoord - BGPZ), 2.0D)), 1.0F);
            if (random.nextInt(100) == 0) {
              generateWater(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, Math.min(rad, 7), world, random, 1);
            } else {
              generateMorass(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, 18.0F / disToC, 0);
            } 
            blockGen = true;
          } 
        }  
    } 
    dHeight = 0;
    if (MFQM.GenLiqMire && !blockGen) {
      boolean canGen = false;
      int genCount = 0;
      int genPos = 0;
      int genRad = 0;
      int genRadP = 0;
      int rad = 0;
      genCount = 20 + random.nextInt(10);
      genRad = 10;
      genRadP = 10;
      genPos = 2;
      if (random.nextInt(genPos) == 0)
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 66 + dHeight) {
            if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) == Blocks.water || world
              .getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) == Blocks.flowing_water) {
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                break;
              } 
              countBlock++;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && 
            isGroundNear(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, (int)Math.round(rad * 1.5D), world)) {
            generateLiquidMire(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.water, 1);
            blockGen = true;
          } 
        }  
    } 
  }
  
  private void generateAOADimPrecasia(World world, Random random, int chunkX, int chunkZ) {
    int dHeight = 0;
    int firstBlockXCoord = chunkX + random.nextInt(16);
    int firstBlockYCoord = 96 + dHeight;
    int firstBlockZCoord = chunkZ + random.nextInt(16);
    BiomeGenBase biomegenbase = world.getBiomeGenForCoords(firstBlockXCoord, firstBlockZCoord);
    int biomeId = biomegenbase.biomeID;
    boolean blockGen = false;
    boolean isJungle = (biomeId != MFQM.AOABiomes[37] && biomeId != MFQM.AOABiomes[38]);
    boolean isField = (biomeId == MFQM.AOABiomes[38]);
    boolean isDesert = (biomeId == MFQM.AOABiomes[37]);
    if (MFQM.GenTar && !blockGen) {
      int trType = 0;
      int tarP1 = 0;
      int tarP2 = 0;
      int tarPU = 0;
      if (isJungle) {
        tarP1 = 10;
        tarP2 = 150;
        tarPU = 5;
      } else {
        tarP1 = 10;
        tarP2 = 15;
        tarPU = 10;
      } 
      if (isJungle) {
        boolean canGen = false;
        int genCount = 100 + random.nextInt(20);
        int genRad = 6;
        int genRadP = 10;
        int rad = 0;
        if (random.nextInt(tarP2) == 0)
          for (int i = 0; i < genCount; i++) {
            firstBlockXCoord = chunkX + random.nextInt(16);
            firstBlockYCoord = 96 + dHeight;
            firstBlockZCoord = chunkZ + random.nextInt(16);
            rad = genRad + random.nextInt(genRadP);
            int countBlock = 0;
            canGen = false;
            while (firstBlockYCoord > 60 + dHeight) {
              if (validSolidMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
                !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  break;
                } 
                countBlock++;
              } 
              if (countBlock > 10)
                break; 
              firstBlockYCoord--;
            } 
            if (canGen && 
              genIsFlatSolid(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad - 2, world) && 
              genIsFlatSolid(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad - 2, world)) {
              generateMireOD(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.TarBlock, 0);
              blockGen = true;
            } 
          }  
      } 
      if (random.nextInt(tarP1) == 0 && 
        random.nextInt(tarPU) == 0) {
        int var5 = chunkX + random.nextInt(16) + 8;
        int var6 = random.nextInt(64) + dHeight;
        int var7 = chunkZ + random.nextInt(16) + 8;
        (new CustomWorldGenLakes(MFQM.TarBlock)).generate(world, random, var5, var6, var7, 1);
      } 
    } 
    if (MFQM.GenSQSF && !blockGen && 
      isJungle) {
      boolean canGen = false;
      int genCount = 0;
      int genPos = 0;
      int genRad = 0;
      int genRadP = 0;
      int rad = 0;
      genCount = 5 + random.nextInt(10);
      genRad = 8;
      genRadP = 8;
      genPos = 60;
      if (random.nextInt(genPos) == 0)
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (validSolidMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
              !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                break;
              } 
              countBlock++;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && 
            genIsFlatSolid(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
            genIsFlatSolid(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
            generateSoftQuicksandForest(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.SoftQuicksandBlock);
            blockGen = true;
          } 
        }  
    } 
    if (MFQM.GenJQS && !blockGen && 
      isJungle) {
      boolean canGen = false;
      int genCount = 10 + random.nextInt(10);
      int genPos = 25;
      int genRad = 8;
      int genRadP = 10;
      int rad = 0;
      if (random.nextInt(genPos) == 0) {
        boolean Flat = false;
        if (random.nextInt(10) == 0)
          Flat = true; 
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) != MFQM.TarBlock) {
              if (validSolidMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
                !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  break;
                } 
                countBlock++;
              } 
            } else {
              canGen = false;
              break;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && 
            genIsFlatSolid(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
            genIsFlatSolid(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
            if (Flat) {
              generateMireO(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.JungleQuicksandBlock);
            } else {
              generateMireOD(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.JungleQuicksandBlock, 0);
            } 
            generateLeavesAround(firstBlockXCoord, firstBlockYCoord + 3, firstBlockZCoord, rad + 3, world, random);
            blockGen = true;
          } 
        } 
      } 
    } 
    if (MFQM.GenSSand && !blockGen && 
      isDesert) {
      boolean canGen = false;
      int genCount = 0;
      int genPos = 0;
      int genRad = 0;
      int genRadP = 0;
      boolean sandOnly = false;
      int rad = 0;
      genCount = 10 + random.nextInt(5);
      genRad = 8;
      genRadP = 15;
      genPos = 10;
      if (genPos > 0 && 
        random.nextInt(genPos) == 0)
        for (int i = 0; i < genCount && !blockGen; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (validSandMaterial(sandOnly, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
              !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                break;
              } 
              countBlock++;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && 
            genIsFlat(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
            genIsFlat(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
            generateSoftSand(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random);
            blockGen = true;
          } 
        }  
    } 
    if (MFQM.GenQS && !blockGen && 
      isDesert) {
      boolean canGen = false;
      int genCount = 0;
      int genPos = 0;
      int genRad = 0;
      int genRadP = 0;
      boolean sandOnly = false;
      int rad = 0;
      genCount = 10 + random.nextInt(10);
      genRad = 5;
      genRadP = 10;
      genPos = 30;
      if (random.nextInt(genPos) == 0)
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (validSandMaterial(sandOnly, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                break;
              } 
              countBlock++;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && 
            genIsFlat(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
            genIsFlat(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
            generateMireO(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.QuicksandBlock);
            blockGen = true;
          } 
        }  
    } 
    if (MFQM.GenSlime) {
      int genCount = 75 + random.nextInt(10);
      int realGen = 1;
      int genPos = 50;
      int genRad = 7;
      int genRadP = 10;
      int rad = 0;
      boolean canGen = false;
      if (random.nextInt(genPos) == 0)
        for (int i = 0; i < genCount && realGen > 0; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 16) {
            if (validSlimeMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
              !validWaterMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world))
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                if (isInCave(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world, rad)) {
                  canGen = true;
                  break;
                } 
              } else {
                countBlock++;
              }  
            firstBlockYCoord--;
          } 
          if (canGen && 
            random.nextInt((int)Math.floor((firstBlockYCoord / 4))) != 0 && 
            genIsFlatSlime(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
            genIsFlatSlime(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world) && 
            genIsFlatSlime(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord, rad, world)) {
            generateSlime(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random);
            realGen--;
            blockGen = true;
          } 
        }  
    } 
  }
  
  private void generateAOADimIromine(World world, Random random, int chunkX, int chunkZ) {
    int dHeight = 0;
    int firstBlockXCoord = chunkX + random.nextInt(16);
    int firstBlockYCoord = 96 + dHeight;
    int firstBlockZCoord = chunkZ + random.nextInt(16);
    BiomeGenBase biomegenbase = world.getBiomeGenForCoords(firstBlockXCoord, firstBlockZCoord);
    int biomeId = biomegenbase.biomeID;
    boolean blockGen = false;
    boolean isSilver = (biomeId == MFQM.AOABiomes[44]);
    if (MFQM.GenTar && !blockGen) {
      int trType = 0;
      int tarP1 = 0;
      int tarP2 = 0;
      int tarPU = 0;
      tarP1 = 30;
      if (isSilver) {
        tarP2 = 15;
        tarPU = 5;
      } else {
        tarP2 = 100;
        tarPU = 10;
      } 
      boolean canGen = false;
      int genCount = 30 + random.nextInt(10);
      int genRad = 6;
      int genRadP = 10;
      int rad = 0;
      if (random.nextInt(tarP2) == 0)
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (validSolidMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
              !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                break;
              } 
              countBlock++;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && 
            genIsFlatSolid(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad - 2, world) && 
            genIsFlatSolid(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad - 2, world)) {
            generateMireOD(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.TarBlock, 0);
            blockGen = true;
          } 
        }  
      if (random.nextInt(tarP1) == 0 && 
        random.nextInt(tarPU) == 0) {
        int var5 = chunkX + random.nextInt(16) + 8;
        int var6 = random.nextInt(64) + dHeight;
        int var7 = chunkZ + random.nextInt(16) + 8;
        (new CustomWorldGenLakes(MFQM.TarBlock)).generate(world, random, var5, var6, var7, 1);
      } 
    } 
    if (MFQM.GenWax && !blockGen && 
      !isSilver) {
      boolean canGen = false;
      int genCount = 10 + random.nextInt(5);
      int genPos = 35;
      int genRad = 10;
      int genRadP = 10;
      int rad = 0;
      if (random.nextInt(genPos) == 0) {
        boolean Flat = true;
        if (random.nextInt(10) == 0)
          Flat = false; 
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) != MFQM.TarBlock) {
              if (validSolidMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
                !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  break;
                } 
                countBlock++;
              } 
            } else {
              canGen = false;
              break;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && 
            genIsFlatSolid(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
            genIsFlatSolid(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
            if (Flat) {
              generateMireO(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.WaxBlock);
            } else {
              generateMireOD(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.WaxBlock, 10);
            } 
            blockGen = true;
          } 
        } 
      } 
    } 
    if (MFQM.GenWax && !blockGen && 
      !isSilver) {
      boolean canGen = false;
      int genCount = 50 + random.nextInt(10);
      int genPos = 20;
      int genRad = 8;
      int genRadP = 8;
      int rad = 0;
      if (random.nextInt(genPos) == 0) {
        boolean Flat = false;
        if (random.nextInt(10) == 0)
          Flat = true; 
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX - 8 + random.nextInt(32);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ - 8 + random.nextInt(32);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) != MFQM.TarBlock) {
              if (validSolidMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
                !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  break;
                } 
                countBlock++;
              } 
            } else {
              canGen = false;
              break;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && 
            genIsFlatSolid(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad - 2, world) && 
            genIsFlatSolid(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad - 2, world)) {
            if (Flat) {
              generateMireO(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.AcidBlock);
            } else {
              generateMireOD(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.AcidBlock, 0);
            } 
            blockGen = true;
          } 
        } 
      } 
    } 
  }
  
  private void generateAOADimLandsLelyetia(World world, Random random, int chunkX, int chunkZ) {
    int dHeight = 0;
    boolean blockGen = false;
    if (MFQM.GenSSnow) {
      int depth = 0;
      if (random.nextInt(5) == 0)
        depth = 1 + random.nextInt(4); 
      for (int h = 0; h < 16; h++) {
        for (int v = 0; v < 16; v++) {
          int firstBlockXCoord = chunkX + h;
          int firstBlockYCoord = 57;
          int firstBlockZCoord = chunkZ + v;
          if (depth == 0) {
            if (!validSolidMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world))
              world.setBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, MFQM.SoftSnowBlock, 1, 2); 
          } else if (depth == 1) {
            if (!validSolidMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world))
              world.setBlock(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, MFQM.SoftSnowBlock, 1, 2); 
            if (!validSolidMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world))
              world.setBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, MFQM.SoftSnowBlock, 1, 2); 
            if (!validSolidMaterial(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord, world))
              world.setBlock(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord, MFQM.SoftSnowBlock, 1, 2); 
          } 
        } 
      } 
    } 
  }
  
  private void generateAOADimCeleve(World world, Random random, int chunkX, int chunkZ) {
    int dHeight = 0;
    int firstBlockXCoord = chunkX + random.nextInt(16);
    int firstBlockYCoord = 96 + dHeight;
    int firstBlockZCoord = chunkZ + random.nextInt(16);
    BiomeGenBase biomegenbase = world.getBiomeGenForCoords(firstBlockXCoord, firstBlockZCoord);
    int biomeId = biomegenbase.biomeID;
    boolean blockGen = false;
    if (!blockGen) {
      boolean canGen = false;
      int genType = 0;
      int genCount = 0;
      int genPos = 0;
      int genRad = 0;
      int genRadP = 0;
      int rad = 0;
      int aMeta = random.nextInt(16);
      genCount = 100 + random.nextInt(20);
      genRad = 6;
      genRadP = 8;
      genPos = 1;
      if (random.nextInt(genPos) == 0 && genPos != 999)
        for (int i = 0; i < genCount; i++) {
          boolean Flat = false;
          if (random.nextInt(5) == 0) {
            Flat = true;
          } else if (random.nextInt(5) == 0) {
            aMeta = random.nextInt(16);
          } 
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = random.nextInt(80) + 32 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 16 + dHeight) {
            if (validGroundMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                break;
              } 
              countBlock++;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen) {
            if (genIsFlatSolid(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
              genIsFlat(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad - 4, world)) {
              if (Flat) {
                generateMireM(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.ground, MFQM.SinkingWoolBlock, aMeta);
              } else {
                generateMireM(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.ground, Blocks.wool, aMeta);
              } 
              blockGen = true;
            } 
          } else if (firstBlockYCoord < 17 + dHeight) {
            break;
          } 
        }  
    } 
  }
  
  private void generateAOADimLunalus(World world, Random random, int chunkX, int chunkZ) {
    int dHeight = 0;
    boolean blockGen = false;
    if (MFQM.GenTar)
      for (int h = 0; h < 16; h++) {
        for (int v = 0; v < 16; v++) {
          int firstBlockXCoord = chunkX + h;
          int firstBlockYCoord = 1;
          int firstBlockZCoord = chunkZ + v;
          while (firstBlockYCoord <= 6) {
            if (!validSolidMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world))
              world.setBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, MFQM.TarBlock, 0, 2); 
            firstBlockYCoord++;
          } 
        } 
      }  
  }
  
  private void generateAOADimCreeponia(World world, Random random, int chunkX, int chunkZ) {
    int dHeight = 0;
    int firstBlockXCoord = chunkX + random.nextInt(16);
    int firstBlockYCoord = 96 + dHeight;
    int firstBlockZCoord = chunkZ + random.nextInt(16);
    BiomeGenBase biomegenbase = world.getBiomeGenForCoords(firstBlockXCoord, firstBlockZCoord);
    int biomeId = biomegenbase.biomeID;
    boolean blockGen = false;
    boolean isJungle = true;
    if (MFQM.GenTar && !blockGen) {
      int trType = 0;
      int tarP1 = 0;
      int tarP2 = 0;
      int tarPU = 0;
      tarP1 = 35;
      tarP2 = 15;
      tarPU = 5;
      if (random.nextInt(tarP1) == 0 && 
        random.nextInt(tarP2) == 0) {
        int var5 = chunkX + random.nextInt(16) + 8;
        int var6 = 60 + random.nextInt(21) + dHeight;
        int var7 = chunkZ + random.nextInt(16) + 8;
        (new CustomWorldGenLakes(MFQM.TarBlock)).generate(world, random, var5, var6, var7, 1);
        blockGen = true;
      } 
      if (random.nextInt(tarP1) == 0 && 
        random.nextInt(tarPU) == 0) {
        int var5 = chunkX + random.nextInt(16) + 8;
        int var6 = random.nextInt(64) + dHeight;
        int var7 = chunkZ + random.nextInt(16) + 8;
        (new CustomWorldGenLakes(MFQM.TarBlock)).generate(world, random, var5, var6, var7, 1);
        blockGen = true;
      } 
    } 
    if (MFQM.GenSQSF && !blockGen && 
      isJungle) {
      boolean canGen = false;
      int genCount = 0;
      int genPos = 0;
      int genRad = 0;
      int genRadP = 0;
      int rad = 0;
      genCount = 5 + random.nextInt(10);
      genRad = 8;
      genRadP = 8;
      genPos = 80;
      if (random.nextInt(genPos) == 0)
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (validGroundMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
              !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                break;
              } 
              countBlock++;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && 
            genIsFlatSolid(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
            genIsFlat(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
            generateSoftQuicksandForest(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.SoftQuicksandBlock);
            blockGen = true;
          } 
        }  
    } 
    if (MFQM.GenJQS && !blockGen && 
      isJungle) {
      boolean canGen = false;
      int genCount = 10 + random.nextInt(10);
      int genPos = 30;
      int genRad = 8;
      int genRadP = 10;
      int rad = 0;
      if (random.nextInt(genPos) == 0) {
        boolean Flat = false;
        if (random.nextInt(10) == 0)
          Flat = true; 
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) != MFQM.TarBlock) {
              if (validGroundMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
                !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  break;
                } 
                countBlock++;
              } 
            } else {
              canGen = false;
              break;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && 
            genIsFlat(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
            genIsFlat(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
            if (Flat) {
              generateMireO(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.JungleQuicksandBlock);
            } else {
              generateMireOD(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.JungleQuicksandBlock, 0);
            } 
            generateLeavesAround(firstBlockXCoord, firstBlockYCoord + 3, firstBlockZCoord, rad + 3, world, random);
            blockGen = true;
          } 
        } 
      } 
    } 
  }
  
  private void generateAOADimLBorean(World world, Random random, int chunkX, int chunkZ) {
    int dHeight = 0;
    int firstBlockXCoord = chunkX + random.nextInt(16);
    int firstBlockYCoord = 96 + dHeight;
    int firstBlockZCoord = chunkZ + random.nextInt(16);
    BiomeGenBase biomegenbase = world.getBiomeGenForCoords(firstBlockXCoord, firstBlockZCoord);
    int biomeId = biomegenbase.biomeID;
    boolean blockGen = false;
    boolean isRedForest = (biomeId == MFQM.AOABiomes[29]);
    boolean isPonds = (biomeId == MFQM.AOABiomes[30]);
    boolean isBubbles = (biomeId == MFQM.AOABiomes[27]);
    if (MFQM.GenMoss) {
      int MossP = 15;
      int genCount = 30 + random.nextInt(20);
      int genRad = 10;
      int genRadP = 10;
      if (!isRedForest && 
        random.nextInt(MossP) == 0)
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX - 8 + random.nextInt(32);
          firstBlockYCoord = 67 + dHeight;
          firstBlockZCoord = chunkZ - 8 + random.nextInt(32);
          int rad = genRad + random.nextInt(genRadP);
          boolean canGen = false;
          int countBlock = 0;
          while (firstBlockYCoord > 60 + dHeight) {
            if (validLiqMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
              !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                break;
              } 
              countBlock++;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen)
            generateMoss(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world, random); 
        }  
    } 
    if (MFQM.GenMoss && MFQM.GenBog && !blockGen) {
      boolean canGen = false;
      int genCount = 10 + random.nextInt(10);
      int genPos = 20;
      int genRad = 8;
      int genRadP = 6;
      int rad = 0;
      if (!isPonds && !isBubbles && !isRedForest && 
        random.nextInt(genPos) == 0) {
        boolean Flat = false;
        if (random.nextInt(10) == 0)
          Flat = true; 
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (validSolidMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
              !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                break;
              } 
              countBlock++;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && 
            genIsFlatSolid(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad - 3, world) && 
            genIsFlatSolid(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad - 3, world)) {
            generateMossQS(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, MFQM.BogBlock);
            blockGen = true;
          } 
        } 
      } 
    } 
  }
  
  private void generateAOADimAbyss(World world, Random random, int chunkX, int chunkZ) {
    int dHeight = 0;
    int firstBlockXCoord = chunkX + random.nextInt(16);
    int firstBlockYCoord = 96 + dHeight;
    int firstBlockZCoord = chunkZ + random.nextInt(16);
    BiomeGenBase biomegenbase = world.getBiomeGenForCoords(firstBlockXCoord, firstBlockZCoord);
    int biomeId = biomegenbase.biomeID;
    boolean blockGen = false;
    boolean isDarkForest = (biomeId == MFQM.AOABiomes[2]);
    for (int k = 0; k < 45; k++) {
      for (int h = 0; h < 16; h++) {
        for (int v = 0; v < 16; v++) {
          firstBlockXCoord = chunkX + h;
          firstBlockYCoord = 50 + dHeight - k;
          firstBlockZCoord = chunkZ + v;
          if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) == MFQM.AOAToxicBlock)
            world.setBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, MFQM.MudBlock, 3, 0); 
        } 
      } 
    } 
    if (MFQM.GenMeatS && 
      !isDarkForest) {
      boolean canGen = false;
      int genCount = 10 + random.nextInt(2);
      int genPos = 10;
      int genRad = 6;
      int genRadP = 6;
      int rad = 0;
      if (random.nextInt(genPos) == 0)
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) != MFQM.TarBlock) {
              if (validSolidMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
                !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  break;
                } 
                countBlock++;
              } 
            } else {
              canGen = false;
              break;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen) {
            generateBlood(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad * 2, world, random, Material.ground);
            generateBlood(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad * 2, world, random, Material.ground);
            generateBlood(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad * 2, world, random, Material.ground);
            blockGen = true;
            if (random.nextInt(20) == 0 && 
              genIsFlatSolid(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad + 1, world) && 
              genIsFlatSolid(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad + 1, world))
              generateMire2(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.SwFleshBlock); 
          } 
        }  
    } 
    if (MFQM.GenLiqMire && !blockGen) {
      boolean canGen = false;
      int genCount = 20 + random.nextInt(10);
      int genPos = 10;
      int genRad = 6;
      int genRadP = 6;
      int rad = 0;
      if (isDarkForest) {
        genPos = 3;
      } else {
        genPos = 60;
      } 
      if (random.nextInt(genPos) == 0) {
        boolean Flat = false;
        if (random.nextInt(10) == 0)
          Flat = true; 
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) != MFQM.MeatBlock) {
              if (validSolidMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
                !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  break;
                } 
                countBlock++;
              } 
            } else {
              canGen = false;
              break;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && 
            genIsFlatSolid(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad - 2, world) && 
            genIsFlatSolid(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad - 2, world)) {
            generateLiquidMire(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, 0);
            blockGen = true;
          } 
        } 
      } 
    } 
    if (MFQM.GenMoss && MFQM.GenJQS && !blockGen) {
      boolean canGen = false;
      int genCount = 5 + random.nextInt(2);
      int genPos = 6;
      int genRad = 6;
      int genRadP = 8;
      int rad = 0;
      if (!isDarkForest) {
        genPos = 30;
      } else {
        genPos = 60;
      } 
      if (random.nextInt(genPos) == 0) {
        boolean Flat = false;
        if (random.nextInt(10) == 0)
          Flat = true; 
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) != MFQM.MeatBlock) {
              if (validSolidMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
                !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  break;
                } 
                countBlock++;
              } 
            } else {
              canGen = false;
              break;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && 
            genIsFlatSolid(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
            genIsFlatSolid(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
            generateMossQS(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, MFQM.JungleQuicksandBlock);
            blockGen = true;
          } 
        } 
      } 
    } 
  }
  
  private void generateAOADimDeeplands(World world, Random random, int chunkX, int chunkZ) {
    int dHeight = 0;
    int firstBlockXCoord = chunkX + random.nextInt(16);
    int firstBlockYCoord = 96 + dHeight;
    int firstBlockZCoord = chunkZ + random.nextInt(16);
    BiomeGenBase biomegenbase = world.getBiomeGenForCoords(firstBlockXCoord, firstBlockZCoord);
    int biomeId = biomegenbase.biomeID;
    boolean blockGen = false;
    if (MFQM.GenTar && !blockGen) {
      int tarP1 = 0;
      int tarP2 = 0;
      int tarPU = 0;
      boolean canGen = false;
      boolean genPosFounded = false;
      int genCount = 0;
      int genRealCount = 0;
      int genPos = 0;
      int genRad = 0;
      int genRadP = 0;
      int rad = 0;
      int FX = 0;
      int FZ = 0;
      int FY = 25;
      genCount = 20 + random.nextInt(10);
      genRad = 10;
      genRadP = 5;
      int genSand = 0;
      genRealCount = 20 + random.nextInt(10);
      int radOfGen = (int)Math.floor(30.0D * Math.min(1.0D, genRealCount / 20.0D));
      genPos = 25;
      int T_genRealCount = genRealCount * 3;
      Block aBlock = null;
      int aMeta = 0;
      boolean aDetBlock = false;
      if (random.nextInt(genPos) == 0) {
        for (int i = 0; i < genCount && genRealCount > 0; i++) {
          if (!genPosFounded) {
            firstBlockXCoord = chunkX + random.nextInt(32) - 16;
            firstBlockYCoord = 25 + dHeight;
            firstBlockZCoord = chunkZ + random.nextInt(32) - 16;
            rad = genRad + random.nextInt(genRadP);
          } else {
            firstBlockXCoord = FX + random.nextInt(radOfGen) - radOfGen / 2;
            firstBlockYCoord = 25 + dHeight;
            firstBlockZCoord = FZ + random.nextInt(radOfGen) - radOfGen / 2;
            rad = genRad - 2 + random.nextInt(5);
          } 
          int countBlock = 0;
          int upper = 0;
          canGen = false;
          while (firstBlockYCoord > 18 + dHeight) {
            if (validTarMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
              !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) == MFQM.TarBlock) {
                  upper = -1;
                  break;
                } 
                upper = -1;
                break;
              } 
              countBlock++;
            } 
            firstBlockYCoord--;
          } 
          if (canGen) {
            canGen = false;
            if (genIsFlatTar(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord, rad, world) && 
              genIsFlatTar(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
              genIsFlatTar(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world))
              if (!aDetBlock) {
                aBlock = world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
                if (aBlock != null && 
                  aBlock.getMaterial().isSolid() && 
                  aBlock.isOpaqueCube() && 
                  !aBlock.getMaterial().isLiquid() && 
                  !aBlock.hasTileEntity()) {
                  aMeta = world.getBlockMetadata(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
                  aDetBlock = true;
                } 
              } else {
                canGen = true;
              }  
          } 
          if (canGen) {
            if (!genPosFounded) {
              genPosFounded = true;
              FX = firstBlockXCoord;
              FZ = firstBlockZCoord;
              FY = 19;
              genCount = 750;
            } 
            int TgenSand = genSand;
            genRealCount--;
            generateCliffsTar(firstBlockXCoord, FY, firstBlockZCoord, (int)Math.floor(rad * 1.5D), world, random, aBlock, aMeta);
            generateFlatTar(firstBlockXCoord, FY, firstBlockZCoord, (int)Math.floor(rad * 1.5D), world, random, TgenSand, aBlock, aMeta);
            blockGen = true;
          } 
        } 
        if (aDetBlock) {
          int ccount = genSand * 10 + T_genRealCount + random.nextInt(T_genRealCount / 2);
          for (int j = 0; j < ccount; j++) {
            firstBlockXCoord = FX + random.nextInt(radOfGen) - radOfGen / 2;
            firstBlockYCoord = FY;
            firstBlockZCoord = FZ + random.nextInt(radOfGen) - radOfGen / 2;
            float disToC = Math.max((float)Math.sqrt(Math.pow((firstBlockXCoord - FX), 2.0D) + Math.pow((firstBlockZCoord - FZ), 2.0D)), 1.0F);
            int cof_d = Math.max(1, (int)Math.floor(Math.pow((16.0F / disToC), 1.5D)));
            int rrad = 1;
            if (random.nextInt(1 * cof_d) == 0)
              rrad = 2 + random.nextInt(1); 
            if (random.nextInt(2 * cof_d) == 0)
              rrad = 3 + random.nextInt(2); 
            if (random.nextInt(3 * cof_d) == 0)
              rrad = 4 + random.nextInt(3); 
            int qy = 1;
            if (random.nextInt(5) == 0)
              qy = 0; 
            if (rrad > 1)
              generateRockTar(firstBlockXCoord, firstBlockYCoord - qy - random.nextInt(Math.max(1, (int)Math.floor((rrad / 2)))), firstBlockZCoord, rrad, world, random, aBlock, aMeta); 
          } 
        } 
      } 
    } 
  }
  
  private void generateAOADimHeaven(World world, Random random, int chunkX, int chunkZ) {
    int dHeight = 0;
    int firstBlockXCoord = chunkX + random.nextInt(16);
    int firstBlockYCoord = 96 + dHeight;
    int firstBlockZCoord = chunkZ + random.nextInt(16);
    BiomeGenBase biomegenbase = world.getBiomeGenForCoords(firstBlockXCoord, firstBlockZCoord);
    int biomeId = biomegenbase.biomeID;
    boolean blockGen = false;
    if (MFQM.GenHoney && !blockGen) {
      boolean canGen = false;
      int genType = 0;
      int genCount = 0;
      int genPos = 0;
      int genRad = 0;
      int genRadP = 0;
      int rad = 0;
      genCount = 40 + random.nextInt(20);
      genRad = 7;
      genRadP = 13;
      genPos = 7;
      boolean Flat = false;
      if (random.nextInt(5) == 0)
        Flat = true; 
      if (random.nextInt(genPos) == 0 && genPos != 999)
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 16 + dHeight) {
            if (validGroundMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                break;
              } 
              countBlock++;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen) {
            if (genIsFlatSolid(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
              genIsFlat(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
              if (Flat) {
                generateMireO(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.HoneyBlock);
              } else {
                generateMireOD(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.HoneyBlock, 0);
              } 
              blockGen = true;
            } 
          } else if (firstBlockYCoord < 17 + dHeight) {
            break;
          } 
        }  
    } 
  }
  
  private void generateTBL(World world, Random random, int chunkX, int chunkZ) {
    int dHeight = 18;
    int firstBlockXCoord = chunkX + random.nextInt(16);
    int firstBlockYCoord = 96 + dHeight;
    int firstBlockZCoord = chunkZ + random.nextInt(16);
    BiomeGenBase biomegenbase = world.getBiomeGenForCoords(firstBlockXCoord, firstBlockZCoord);
    int biomeId = biomegenbase.biomeID;
    boolean blockGen = false;
    boolean isSwamp = false;
    boolean isForest = false;
    boolean isDry = false;
    boolean isSnow = false;
    boolean isPlains = false;
    boolean isDark = false;
    if (BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.SWAMP))
      isSwamp = true; 
    if (BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.FOREST))
      isForest = true; 
    if (BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.FROZEN) || 
      BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.COLD))
      isSnow = true; 
    if (BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.HOT) || 
      BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.SAVANNA) || 
      BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.DRY))
      isDry = true; 
    if (BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.PLAINS))
      isPlains = true; 
    if (BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.SPOOKY))
      isDark = true; 
    if (MFQM.GenSlime) {
      int genCount = 75 + random.nextInt(10);
      int realGen = 1;
      int genPos = 55;
      int genRad = 7;
      int genRadP = 10;
      int rad = 0;
      boolean canGen = false;
      if (random.nextInt(genPos) == 0)
        for (int i = 0; i < genCount && realGen > 0; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight - 50;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 16) {
            if (validSlimeMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
              !validWaterMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                break;
              } 
              countBlock++;
            } 
            firstBlockYCoord--;
          } 
          if (canGen && 
            random.nextInt(firstBlockYCoord / 4) != 0 && 
            genIsFlatSlime(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
            genIsFlatSlime(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world) && 
            genIsFlatSlime(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord, rad, world)) {
            generateSlime(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random);
            realGen--;
            blockGen = true;
          } 
        }  
    } 
    if (MFQM.GenWastePit) {
      int genCount = 125 + random.nextInt(10);
      int realGen = 2 + random.nextInt(3);
      int genPos = 5;
      int genRad = 7;
      int genRadP = 10;
      int rad = 0;
      boolean canGen = false;
      if (random.nextInt(genPos) == 0 && !blockGen)
        for (int i = 0; i < genCount && realGen > 0; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight - 50;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 20) {
            if (validSolidMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
              !validWaterMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                break;
              } 
              countBlock++;
            } 
            firstBlockYCoord--;
          } 
          if (canGen && 
            random.nextInt(firstBlockYCoord / 10) == 0 && 
            genIsFlatSlurry(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
            generateMireO(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.SlurryBlock);
            realGen--;
          } 
        }  
    } 
    blockGen = false;
    if (MFQM.GenJQS && !blockGen) {
      boolean canGen = false;
      int genCount = 10 + random.nextInt(10);
      int genPos = 10;
      int genRad = 6;
      int genRadP = 10;
      int rad = 0;
      if (random.nextInt(genPos) == 0) {
        boolean Flat = false;
        if (random.nextInt(10) == 0)
          Flat = true; 
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) != MFQM.TarBlock) {
              if (validGroundMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
                !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  break;
                } 
                countBlock++;
              } 
            } else {
              canGen = false;
              break;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && 
            genIsFlat(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
            genIsFlat(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
            if (Flat) {
              generateMireO(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.JungleQuicksandBlock);
            } else {
              generateMireOD(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.JungleQuicksandBlock, 0);
            } 
            generateLeavesAround(firstBlockXCoord, firstBlockYCoord + 3, firstBlockZCoord, rad + 3, world, random);
            blockGen = true;
          } 
        } 
      } 
    } 
    if (MFQM.GenLiqMire) {
      boolean canGen = false;
      int genType = 0;
      int genCount = 0;
      int genPos = 0;
      int genRad = 0;
      int genRadP = 0;
      int rad = 0;
      genCount = 5 + random.nextInt(10);
      genRad = 7;
      genRadP = 5;
      genPos = 4;
      if (random.nextInt(genPos) == 0)
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight - 32;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = (int)Math.floor(genRad * 1.5D) + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 96 + dHeight - 36) {
            if (validWaterMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                break;
              } 
              countBlock++;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && 
            isGroundNear(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world)) {
            generateLiquidMire(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.water, 0);
            blockGen = true;
          } 
        }  
    } 
    if (MFQM.GenMoss && !blockGen) {
      int MossP = 65;
      int genCount = random.nextInt(5) + 10;
      int genRad = 10;
      int genRadP = 10;
      if (random.nextInt(MossP) == 0)
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight - 30;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          int rad = genRad + random.nextInt(genRadP);
          int genType = 0;
          boolean canGen = false;
          int countBlock = 0;
          while (firstBlockYCoord > 96 + dHeight - 36) {
            if (validLiqMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
              !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                break;
              } 
              countBlock++;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && 
            validLiqMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
            isGroundMossNear(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world))
            generateMoss(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world, random); 
        }  
    } 
  }
  
  private void generateWLD(World world, Random random, int chunkX, int chunkZ) {
    int dHeight = 0;
    int firstBlockXCoord = chunkX + 7;
    int firstBlockYCoord = 96 + dHeight;
    int firstBlockZCoord = chunkZ + 7;
    BiomeGenBase biomegenbase = world.getBiomeGenForCoords(firstBlockXCoord, firstBlockZCoord);
    int biomeId = biomegenbase.biomeID;
    boolean blockGen = false;
    boolean isForest = (biomeId == MFQM.WLDBiomes[0]);
    boolean isDesert = (biomeId == MFQM.WLDBiomes[1]);
    boolean isSnow = (biomeId == MFQM.WLDBiomes[2]);
    boolean isTained = (biomeId == MFQM.WLDBiomes[3]);
    boolean isAshes = (biomeId == MFQM.WLDBiomes[4]);
    boolean isVolcan = (biomeId == MFQM.WLDBiomes[5]);
    if (MFQM.GenQS && !blockGen && 
      isDesert) {
      boolean canGen = false;
      int genCount = 2 + random.nextInt(5);
      int genPos = 10;
      int genRad = 5;
      int genRadP = 7;
      int rad = 0;
      if (random.nextInt(genPos) == 0)
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (validSandMaterial(false, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                break;
              } 
              countBlock++;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && 
            genIsFlatSolid(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
            genIsFlatSolid(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
            generateMire(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.QuicksandBlock);
            blockGen = true;
          } 
        }  
      canGen = false;
      for (int hc = -3; hc <= 3 && !canGen; hc++) {
        for (int vc = -3; vc <= 3 && !canGen; vc++) {
          if (world.blockExists(chunkX + 8 + hc * 16, 32, chunkZ + 8 + vc * 16))
            for (int k = 0; k < 16 && !canGen; k++) {
              for (int h = 0; h < 16 && !canGen; h++) {
                for (int v = 0; v < 16 && !canGen; v++) {
                  firstBlockXCoord = chunkX + hc * 16 + h;
                  firstBlockYCoord = 63 + dHeight + k;
                  firstBlockZCoord = chunkZ + vc * 16 + v;
                  if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord).getMaterial() == Material.lava) {
                    canGen = true;
                    break;
                  } 
                } 
              } 
            }  
        } 
      } 
      if (canGen) {
        canGen = false;
        int bottomPyr = 0;
        int XPyr = firstBlockXCoord;
        int ZPyr = firstBlockZCoord;
        int YPyr = firstBlockYCoord;
        int count = 5;
        while (count > 0 && !canGen) {
          int xx = random.nextInt(16) - 8;
          int zz = random.nextInt(16) - 8;
          firstBlockYCoord = YPyr;
          while (firstBlockYCoord > 60 + dHeight) {
            if (world.getBlock(firstBlockXCoord + xx, firstBlockYCoord, firstBlockZCoord + zz).getMaterial() == Material.sand) {
              canGen = true;
              bottomPyr = firstBlockYCoord;
              break;
            } 
            firstBlockYCoord--;
          } 
          count--;
        } 
        if (canGen) {
          int puddleCount = -2;
          for (int k = 20; k >= 0; k--) {
            for (int h = -35 + k; h < 35 - k; h++) {
              for (int v = -35 + k; v < 35 - k; v++) {
                firstBlockXCoord = XPyr + h;
                firstBlockYCoord = bottomPyr + k;
                firstBlockZCoord = ZPyr + v;
                if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) == Blocks.mob_spawner && 
                  random.nextInt(2) == 0) {
                  world.setBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, Blocks.coal_block);
                  if (random.nextInt(4) == 0) {
                    world.setBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, Blocks.lapis_block);
                  } else if (random.nextInt(8) == 0) {
                    world.setBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, Blocks.redstone_block);
                  } else if (random.nextInt(16) == 0) {
                    world.setBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, Blocks.gold_block);
                  } else if (random.nextInt(32) == 0) {
                    world.setBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, Blocks.iron_block);
                  } else if (random.nextInt(64) == 0) {
                    world.setBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, Blocks.diamond_block);
                  } else if (random.nextInt(128) == 0) {
                    world.setBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, Blocks.emerald_block);
                  } 
                } 
                if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) == Blocks.glass) {
                  world.setBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, Blocks.netherrack);
                } else if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord).getMaterial() == Material.lava) {
                  if (world.getBlock(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord).getMaterial() != Material.lava) {
                    world.setBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, MFQM.TarBlock);
                    if (world.getBlock(firstBlockXCoord, firstBlockYCoord - 2, firstBlockZCoord).getMaterial().isSolid()) {
                      world.setBlock(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord, MFQM.TarBlock);
                      int as = 1;
                      while (as < 10 && world.getBlock(firstBlockXCoord - as, firstBlockYCoord - 1, firstBlockZCoord).getMaterial() == Material.air) {
                        world.setBlock(firstBlockXCoord - as, firstBlockYCoord - 1, firstBlockZCoord, Blocks.sandstone);
                        as++;
                      } 
                      as = 1;
                      while (as < 10 && world.getBlock(firstBlockXCoord + as, firstBlockYCoord - 1, firstBlockZCoord).getMaterial() == Material.air) {
                        world.setBlock(firstBlockXCoord + as, firstBlockYCoord - 1, firstBlockZCoord, Blocks.sandstone);
                        as++;
                      } 
                      as = 1;
                      while (as < 10 && world.getBlock(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord - as).getMaterial() == Material.air) {
                        world.setBlock(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord - as, Blocks.sandstone);
                        as++;
                      } 
                      as = 1;
                      while (as < 10 && world.getBlock(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord + as).getMaterial() == Material.air) {
                        world.setBlock(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord + as, Blocks.sandstone);
                        as++;
                      } 
                    } 
                  } 
                } else if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord).getMaterial().isSolid() && 
                  !isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord + 3, firstBlockZCoord, world) && 
                  world.getBlock(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord).getMaterial() == Material.air && 
                  !world.getBlock(firstBlockXCoord, firstBlockYCoord + 2, firstBlockZCoord).getMaterial().isSolid()) {
                  if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord).getMaterial() == Material.sand) {
                    if (random.nextInt(40 + 25 * Math.abs(puddleCount)) == 0) {
                      rad = 5 + random.nextInt(5);
                      if (!genIsBrickNear(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world) && 
                        genIsFlatSolid(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
                        genIsFlatSolid(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
                        generateMireM(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.sand, MFQM.QuicksandBlock, 0);
                        world.setBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, Blocks.sandstone);
                        puddleCount++;
                      } 
                    } 
                  } else if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) == Blocks.sandstone) {
                    int walkableAround = 0;
                    int qsAround = 0;
                    int voidAround = 0;
                    Block aBlock = world.getBlock(firstBlockXCoord + 1, firstBlockYCoord, firstBlockZCoord);
                    if (aBlock == Blocks.sandstone) {
                      if (!world.getBlock(firstBlockXCoord + 1, firstBlockYCoord + 1, firstBlockZCoord).getMaterial().isSolid())
                        walkableAround++; 
                    } else if (aBlock == MFQM.SandBlock) {
                      qsAround++;
                    } else if (!aBlock.getMaterial().isSolid()) {
                      voidAround++;
                    } 
                    aBlock = world.getBlock(firstBlockXCoord - 1, firstBlockYCoord, firstBlockZCoord);
                    if (aBlock == Blocks.sandstone) {
                      if (!world.getBlock(firstBlockXCoord - 1, firstBlockYCoord + 1, firstBlockZCoord).getMaterial().isSolid())
                        walkableAround++; 
                    } else if (aBlock == MFQM.SandBlock) {
                      qsAround++;
                    } else if (!aBlock.getMaterial().isSolid()) {
                      voidAround++;
                    } 
                    aBlock = world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord + 1);
                    if (aBlock == Blocks.sandstone) {
                      if (!world.getBlock(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord + 1).getMaterial().isSolid())
                        walkableAround++; 
                    } else if (aBlock == MFQM.SandBlock) {
                      qsAround++;
                    } else if (!aBlock.getMaterial().isSolid()) {
                      voidAround++;
                    } 
                    aBlock = world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord - 1);
                    if (aBlock == Blocks.sandstone) {
                      if (!world.getBlock(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord - 1).getMaterial().isSolid())
                        walkableAround++; 
                    } else if (aBlock == MFQM.SandBlock) {
                      qsAround++;
                    } else if (!aBlock.getMaterial().isSolid()) {
                      voidAround++;
                    } 
                    if (voidAround < 1 && ((walkableAround > 2 && random.nextInt(Math.max(85 / (1 + qsAround * 20) / (walkableAround * walkableAround + 1), 2)) == 0) || qsAround == 4)) {
                      world.setBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, MFQM.SandBlock);
                      world.setBlock(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord, MFQM.SandBlock);
                      world.setBlock(firstBlockXCoord, firstBlockYCoord - 2, firstBlockZCoord, Blocks.sandstone);
                      if (world.getBlock(firstBlockXCoord - 1, firstBlockYCoord - 1, firstBlockZCoord).getMaterial() == Material.air) {
                        world.setBlock(firstBlockXCoord - 1, firstBlockYCoord - 1, firstBlockZCoord, Blocks.sandstone);
                        world.setBlock(firstBlockXCoord - 1, firstBlockYCoord - 2, firstBlockZCoord, Blocks.sandstone);
                      } 
                      if (world.getBlock(firstBlockXCoord + 1, firstBlockYCoord - 1, firstBlockZCoord).getMaterial() == Material.air) {
                        world.setBlock(firstBlockXCoord + 1, firstBlockYCoord - 1, firstBlockZCoord, Blocks.sandstone);
                        world.setBlock(firstBlockXCoord + 1, firstBlockYCoord - 2, firstBlockZCoord, Blocks.sandstone);
                      } 
                      if (world.getBlock(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord - 1).getMaterial() == Material.air) {
                        world.setBlock(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord - 1, Blocks.sandstone);
                        world.setBlock(firstBlockXCoord, firstBlockYCoord - 2, firstBlockZCoord - 1, Blocks.sandstone);
                      } 
                      if (world.getBlock(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord + 1).getMaterial() == Material.air) {
                        world.setBlock(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord + 1, Blocks.sandstone);
                        world.setBlock(firstBlockXCoord, firstBlockYCoord - 2, firstBlockZCoord + 1, Blocks.sandstone);
                      } 
                    } else if (voidAround < 1 && walkableAround == 1 && random.nextInt(4) == 0) {
                      world.setBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, MFQM.SandBlock);
                      world.setBlock(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord, MFQM.SandBlock);
                      world.setBlock(firstBlockXCoord, firstBlockYCoord - 2, firstBlockZCoord, Blocks.sandstone);
                      if (world.getBlock(firstBlockXCoord - 1, firstBlockYCoord - 1, firstBlockZCoord).getMaterial() == Material.air) {
                        world.setBlock(firstBlockXCoord - 1, firstBlockYCoord - 1, firstBlockZCoord, Blocks.sandstone);
                        world.setBlock(firstBlockXCoord - 1, firstBlockYCoord - 2, firstBlockZCoord, Blocks.sandstone);
                      } 
                      if (world.getBlock(firstBlockXCoord + 1, firstBlockYCoord - 1, firstBlockZCoord).getMaterial() == Material.air) {
                        world.setBlock(firstBlockXCoord + 1, firstBlockYCoord - 1, firstBlockZCoord, Blocks.sandstone);
                        world.setBlock(firstBlockXCoord + 1, firstBlockYCoord - 2, firstBlockZCoord, Blocks.sandstone);
                      } 
                      if (world.getBlock(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord - 1).getMaterial() == Material.air) {
                        world.setBlock(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord - 1, Blocks.sandstone);
                        world.setBlock(firstBlockXCoord, firstBlockYCoord - 2, firstBlockZCoord - 1, Blocks.sandstone);
                      } 
                      if (world.getBlock(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord + 1).getMaterial() == Material.air) {
                        world.setBlock(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord + 1, Blocks.sandstone);
                        world.setBlock(firstBlockXCoord, firstBlockYCoord - 2, firstBlockZCoord + 1, Blocks.sandstone);
                      } 
                    } else if (!world.getBlock(firstBlockXCoord, firstBlockYCoord - 3, firstBlockZCoord).getMaterial().isSolid() && 
                      !world.getBlock(firstBlockXCoord, firstBlockYCoord - 4, firstBlockZCoord).getMaterial().isSolid()) {
                      world.setBlock(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord, Blocks.sandstone);
                      world.setBlock(firstBlockXCoord, firstBlockYCoord - 2, firstBlockZCoord, Blocks.sandstone);
                    } 
                  } 
                } 
              } 
            } 
          } 
        } 
      } 
    } 
    if (MFQM.GenQS && !blockGen) {
      boolean canGen = false;
      int genCount = 5 + random.nextInt(5);
      int genPos = 4;
      int genRad = 5;
      int genRadP = 5;
      int rad = 0;
      if (isAshes && 
        random.nextInt(genPos) == 0)
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) != MFQM.TarBlock) {
              if (validSolidMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
                !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  break;
                } 
                countBlock++;
              } 
            } else {
              canGen = false;
              break;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && 
            genIsFlatSolid(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
            genIsFlatSolid(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
            generateMireM(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.SoftSnowBlock, 1);
            blockGen = true;
          } 
        }  
    } 
    if (MFQM.GenJQS && !blockGen) {
      boolean canGen = false;
      int genCount = 10 + random.nextInt(10);
      int genPos = 20;
      int genRad = 6;
      int genRadP = 6;
      int rad = 0;
      if (isForest && 
        random.nextInt(genPos) == 0) {
        boolean Flat = false;
        if (random.nextInt(5) == 0)
          Flat = true; 
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) != MFQM.TarBlock) {
              if (validGroundMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
                !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  break;
                } 
                countBlock++;
              } 
            } else {
              canGen = false;
              break;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && 
            genIsFlatSolid(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
            genIsFlat(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
            if (Flat) {
              generateMireO(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.JungleQuicksandBlock);
            } else {
              generateMireOD(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.JungleQuicksandBlock, 0);
            } 
            blockGen = true;
          } 
        } 
      } 
    } 
    if (MFQM.GenMud && !blockGen) {
      boolean canGen = false;
      int genType = 0;
      int genCount = 0;
      int genPos = 0;
      int genRad = 0;
      int genRadP = 0;
      int rad = 0;
      genCount = 20 + random.nextInt(30);
      genRad = 6;
      genRadP = 14;
      genPos = 3;
      if (isTained && 
        random.nextInt(genPos) == 0 && genPos != 999)
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 74 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad * 2 + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 55 + dHeight) {
            if (validGroundOrLMireMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                break;
              } 
              countBlock++;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen)
            generateForcedMud(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world, random, 0); 
        }  
    } 
    if (MFQM.GenWastePit && !blockGen && 
      isTained) {
      boolean canGen = false;
      int genCount = 10 + random.nextInt(40);
      int genPos = 2;
      int genRad = 7;
      int genRadP = 10;
      int rad = 0;
      boolean dep = false;
      if (random.nextInt(10) == 0)
        dep = true; 
      if (random.nextInt(genPos) == 0)
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) != MFQM.TarBlock) {
              if (validSolidMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
                !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  break;
                } 
                countBlock++;
              } 
            } else {
              canGen = false;
              break;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && 
            genIsFlatSlurry(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
            genIsFlatSlurry(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
            if (dep) {
              generateMireD(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.SlurryBlock, true);
            } else {
              generateMire(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.SlurryBlock);
            } 
            blockGen = true;
          } 
        }  
    } 
    if (MFQM.GenLiqMire && 
      isTained) {
      boolean canGen = false;
      int rad = 0;
      for (int h = 0; h < 3; h++) {
        for (int v = 0; v < 3; v++) {
          firstBlockXCoord = chunkX + 2 + h * 4 + random.nextInt(3) - 1;
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + 2 + v * 4 + random.nextInt(3) - 1;
          rad = 20 + random.nextInt(4);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (validWaterMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) || 
              validSolidMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                break;
              } 
              countBlock++;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen)
            if (isGroundNear(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world)) {
              generateLiquidMire(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.water, 2);
            } else {
              generateLiquidMire(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.water, 0);
            }  
        } 
      } 
    } 
    if (MFQM.GenSSnow && !blockGen && 
      isSnow) {
      boolean canGen = false;
      int genCount = 20 + random.nextInt(10);
      int genPos = 20;
      int genRad = 5;
      int genRadP = 20;
      int rad = 0;
      if (random.nextInt(genPos) == 0)
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX - 16 + random.nextInt(32);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ - 16 + random.nextInt(32);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (validSnowMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
              !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                break;
              } 
              countBlock++;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && 
            genIsFlatSnow(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
            genIsFlatSnow(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
            generateSnow(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord, rad, world, random);
            blockGen = true;
          } 
        }  
      if (MFQM.GenTar) {
        canGen = false;
        boolean genPosFounded = false;
        int genRealCount = 0;
        rad = 0;
        int FX = 0;
        int FZ = 0;
        int FY = 60;
        genCount = 20;
        genRad = 10;
        genRadP = 5;
        int genSand = 0;
        genRealCount = 40 + random.nextInt(20);
        int radOfGen = (int)Math.floor(40.0D * Math.min(1.0D, genRealCount / 20.0D));
        genPos = 1;
        int T_genRealCount = genRealCount / 2;
        Block aBlock = null;
        int aMeta = 0;
        boolean aDetBlock = false;
        aBlock = Blocks.stone;
        aMeta = 0;
        aDetBlock = true;
        if (random.nextInt(genPos) == 0) {
          for (int i = 0; i < genCount && genRealCount > 0; i++) {
            if (!genPosFounded) {
              firstBlockXCoord = chunkX + random.nextInt(32) - 16;
              firstBlockYCoord = 25 + dHeight;
              firstBlockZCoord = chunkZ + random.nextInt(32) - 16;
              rad = genRad + random.nextInt(genRadP);
            } else {
              firstBlockXCoord = FX + random.nextInt(radOfGen) - radOfGen / 2;
              firstBlockYCoord = 25 + dHeight;
              firstBlockZCoord = FZ + random.nextInt(radOfGen) - radOfGen / 2;
              rad = genRad / 2 + random.nextInt(7) + radOfGen / 6;
            } 
            int countBlock = 0;
            int upper = -1;
            canGen = false;
            while (firstBlockYCoord > 20 + dHeight) {
              if (validTarMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
                !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world))
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  if (genPosFounded || isInCave(firstBlockXCoord, firstBlockYCoord + 3, firstBlockZCoord, world, 32)) {
                    canGen = true;
                    break;
                  } 
                  countBlock++;
                } else {
                  countBlock++;
                }  
              firstBlockYCoord--;
            } 
            if (canGen && 
              genIsFlatTar(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord, rad, world) && 
              genIsFlatTar(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
              genIsFlatTar(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
              if (!genPosFounded) {
                canGen = false;
                world.setBlock(firstBlockXCoord, firstBlockYCoord + 10, firstBlockZCoord + 1, MFQM.MoorBlock, 10, 0);
                for (int k = 0; k <= 2 && !canGen; k++) {
                  for (int h = -16; h <= 16 && !canGen; h++) {
                    for (int v = -16; v <= 16 && !canGen; v++) {
                      if (world.getBlock(firstBlockXCoord + h, firstBlockYCoord + 1 - k, firstBlockZCoord + v).hasTileEntity()) {
                        canGen = true;
                        genPosFounded = true;
                        FX = firstBlockXCoord;
                        FZ = firstBlockZCoord;
                        FY = firstBlockYCoord;
                        genCount = 1000;
                      } 
                    } 
                  } 
                } 
              } 
              if (canGen) {
                int TgenSand = genSand;
                genRealCount--;
                generateCliffsTar(firstBlockXCoord, FY, firstBlockZCoord, (int)Math.floor(rad * 1.5D), world, random, aBlock, aMeta);
                generateFlatTar(firstBlockXCoord, FY - upper - 1, firstBlockZCoord, rad, world, random, TgenSand, aBlock, aMeta);
                blockGen = true;
              } 
            } 
          } 
          if (aDetBlock) {
            int ccount = genSand * 10 + T_genRealCount + random.nextInt(T_genRealCount / 2);
            int rcount = 0;
            for (int j = 0; rcount < ccount; j++) {
              firstBlockXCoord = FX + random.nextInt(radOfGen) - radOfGen / 2;
              firstBlockYCoord = FY;
              firstBlockZCoord = FZ + random.nextInt(radOfGen) - radOfGen / 2;
              float disToC = Math.max((float)Math.sqrt(Math.pow((firstBlockXCoord - FX), 2.0D) + Math.pow((firstBlockZCoord - FZ), 2.0D)), 1.0F);
              int cof_d = Math.max(1, (int)Math.floor(Math.pow((16.0F / disToC), 1.5D)));
              int rrad = 1;
              if (random.nextInt(1 * cof_d) == 0)
                rrad = 2 + random.nextInt(1); 
              if (random.nextInt(2 * cof_d) == 0)
                rrad = 3 + random.nextInt(2); 
              if (random.nextInt(3 * cof_d) == 0)
                rrad = 4 + random.nextInt(3); 
              int qy = 1;
              if (random.nextInt(5) == 0)
                qy = 0; 
              if (rrad > 1)
                generateRockTar(firstBlockXCoord, firstBlockYCoord - qy - random.nextInt(Math.max(1, (int)Math.floor((rrad / 2)))), firstBlockZCoord, rrad / 2, world, random, aBlock, aMeta); 
              rcount++;
            } 
          } 
        } 
      } 
    } 
    if (MFQM.GenTar && !blockGen) {
      int trType = 0;
      int tarP1 = 0;
      int tarP2 = 0;
      int tarPU = 0;
      if (isVolcan) {
        trType = 1;
      } else if (isAshes) {
        trType = 2;
      } 
      int trrType = trType;
      switch (trType) {
        case 1:
          tarP1 = 5;
          tarP2 = 1;
          tarPU = 5;
          break;
        case 2:
          tarP1 = 10;
          tarP2 = 8;
          tarPU = 10;
          break;
        default:
          tarP1 = 35;
          tarP2 = 15;
          tarPU = 10;
          break;
      } 
      if (trrType == 1) {
        boolean canGen = false;
        boolean genPosFounded = false;
        int genCount = 0;
        int genRealCount = 0;
        int genPos = 0;
        int genRad = 0;
        int genRadP = 0;
        int rad = 0;
        int FX = 0;
        int FZ = 0;
        int FY = 60;
        genCount = 20 + random.nextInt(10);
        genRad = 10;
        genRadP = 5;
        int genSand = 0;
        genRealCount = 10 + random.nextInt(20);
        int radOfGen = (int)Math.floor(30.0D * Math.min(1.0D, genRealCount / 20.0D));
        genPos = 8;
        int T_genRealCount = genRealCount * 3;
        Block aBlock = null;
        int aMeta = 0;
        boolean aDetBlock = false;
        if (random.nextInt(genPos) == 0) {
          for (int i = 0; i < genCount && genRealCount > 0; i++) {
            if (!genPosFounded) {
              firstBlockXCoord = chunkX + random.nextInt(32) - 16;
              firstBlockYCoord = 96 + dHeight;
              firstBlockZCoord = chunkZ + random.nextInt(32) - 16;
              rad = genRad + random.nextInt(genRadP);
            } else {
              firstBlockXCoord = FX + random.nextInt(radOfGen) - radOfGen / 2;
              firstBlockYCoord = 96 + dHeight;
              firstBlockZCoord = FZ + random.nextInt(radOfGen) - radOfGen / 2;
              rad = genRad / 2 + random.nextInt(7) + radOfGen / 6;
            } 
            int countBlock = 0;
            int upper = 0;
            canGen = false;
            while (firstBlockYCoord > 60 + dHeight) {
              if (validTarMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
                !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) == MFQM.TarBlock) {
                    upper = -1;
                    break;
                  } 
                  upper = -1;
                  break;
                } 
                countBlock++;
              } 
              firstBlockYCoord--;
            } 
            if (canGen) {
              canGen = false;
              if (!aDetBlock) {
                aBlock = world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
                if (aBlock != null && 
                  aBlock.getMaterial().isSolid() && 
                  aBlock.isOpaqueCube() && 
                  !aBlock.getMaterial().isLiquid() && 
                  !aBlock.hasTileEntity()) {
                  aMeta = world.getBlockMetadata(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
                  aDetBlock = true;
                } 
              } else {
                canGen = true;
              } 
            } 
            if (canGen && 
              genIsFlatTar(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord, rad, world) && 
              genIsFlatTar(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
              genIsFlatTar(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
              if (!genPosFounded) {
                genPosFounded = true;
                FX = firstBlockXCoord;
                FZ = firstBlockZCoord;
                FY = firstBlockYCoord;
                genCount = 750;
              } 
              int TgenSand = genSand;
              genRealCount--;
              generateCliffsTar(firstBlockXCoord, FY, firstBlockZCoord, (int)Math.floor(rad * 1.5D), world, random, aBlock, aMeta);
              generateFlatTar(firstBlockXCoord, FY - upper - 1, firstBlockZCoord, rad, world, random, TgenSand, aBlock, aMeta);
              blockGen = true;
            } 
          } 
          if (aDetBlock) {
            int ccount = genSand * 10 + T_genRealCount + random.nextInt(T_genRealCount / 2);
            int rcount = 0;
            for (int j = 0; rcount < ccount; j++) {
              firstBlockXCoord = FX + random.nextInt(radOfGen) - radOfGen / 2;
              firstBlockYCoord = FY;
              firstBlockZCoord = FZ + random.nextInt(radOfGen) - radOfGen / 2;
              float disToC = Math.max((float)Math.sqrt(Math.pow((firstBlockXCoord - FX), 2.0D) + Math.pow((firstBlockZCoord - FZ), 2.0D)), 1.0F);
              int cof_d = Math.max(1, (int)Math.floor(Math.pow((16.0F / disToC), 1.5D)));
              int rrad = 1;
              if (random.nextInt(1 * cof_d) == 0)
                rrad = 2 + random.nextInt(1); 
              if (random.nextInt(2 * cof_d) == 0)
                rrad = 3 + random.nextInt(2); 
              if (random.nextInt(3 * cof_d) == 0)
                rrad = 4 + random.nextInt(3); 
              int qy = 1;
              if (random.nextInt(5) == 0)
                qy = 0; 
              if (rrad > 1)
                generateRockTar(firstBlockXCoord, firstBlockYCoord - qy - random.nextInt(Math.max(1, (int)Math.floor((rrad / 2)))), firstBlockZCoord, rrad, world, random, aBlock, aMeta); 
              rcount++;
            } 
          } 
        } 
      } else if (random.nextInt(tarP1) == 0 && 
        random.nextInt(tarP2) == 0) {
        int var5 = chunkX + random.nextInt(16) + 8;
        int var6 = 60 + random.nextInt(21);
        int var7 = chunkZ + random.nextInt(16) + 8;
        (new CustomWorldGenLakes(MFQM.TarBlock)).generate(world, random, var5, var6, var7, 1);
        blockGen = true;
      } 
      if (random.nextInt(tarP1) == 0 && 
        random.nextInt(tarPU) == 0) {
        int var5 = chunkX + random.nextInt(16) + 8;
        int var6 = random.nextInt(64);
        int var7 = chunkZ + random.nextInt(16) + 8;
        (new CustomWorldGenLakes(MFQM.TarBlock)).generate(world, random, var5, var6, var7, 1);
        blockGen = true;
      } 
    } 
    if (MFQM.GenSlime) {
      int genCount = 75 + random.nextInt(10);
      int realGen = 1;
      int genPos = 75;
      int genRad = 7;
      int genRadP = 10;
      int rad = 0;
      boolean canGen = false;
      if (random.nextInt(genPos) == 0)
        for (int i = 0; i < genCount && realGen > 0; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 16) {
            if (validSlimeMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
              !validWaterMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world))
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                if (isInCave(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world, rad)) {
                  canGen = true;
                  break;
                } 
              } else {
                countBlock++;
              }  
            firstBlockYCoord--;
          } 
          if (canGen && 
            random.nextInt((int)Math.floor((firstBlockYCoord / 4))) != 0 && 
            genIsFlatSlime(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
            genIsFlatSlime(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world) && 
            genIsFlatSlime(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord, rad, world)) {
            generateSlime(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random);
            realGen--;
            blockGen = true;
          } 
        }  
    } 
  }
  
  private void generateABC4(World world, Random random, int chunkX, int chunkZ) {
    int dHeight = 0;
    boolean blockGen = false;
    if (MFQM.GenSSand)
      for (int h = 0; h < 16; h++) {
        for (int v = 0; v < 16; v++) {
          int firstBlockXCoord = chunkX + h;
          int firstBlockYCoord = 76 + dHeight;
          int firstBlockZCoord = chunkZ + v;
          boolean canGen = false;
          if (!validSolidMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world))
            canGen = true; 
          if (canGen) {
            int depth = 20;
            if (!validSolidMaterial(firstBlockXCoord, firstBlockYCoord - depth, firstBlockZCoord, world))
              world.setBlock(firstBlockXCoord, firstBlockYCoord - depth, firstBlockZCoord, Blocks.stone); 
            depth--;
            while (depth >= 0) {
              world.setBlock(firstBlockXCoord, firstBlockYCoord - depth, firstBlockZCoord, MFQM.SandBlock);
              depth--;
            } 
          } 
        } 
      }  
  }
  
  private void generateABC3(World world, Random random, int chunkX, int chunkZ) {
    int dHeight = 0;
    int firstBlockXCoord = chunkX + random.nextInt(16);
    int firstBlockYCoord = 96 + dHeight;
    int firstBlockZCoord = chunkZ + random.nextInt(16);
    BiomeGenBase biomegenbase = world.getBiomeGenForCoords(firstBlockXCoord, firstBlockZCoord);
    int biomeId = biomegenbase.biomeID;
    boolean blockGen = false;
    if (MFQM.GenMud && !blockGen) {
      boolean canGen = false;
      int genType = 0;
      int genCount = 0;
      int genPos = 0;
      int genRad = 0;
      int genRadP = 0;
      int rad = 0;
      genCount = 40 + random.nextInt(20);
      genRad = 7;
      genRadP = 13;
      genPos = 2;
      if (random.nextInt(genPos) == 0 && genPos != 999)
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 16 + dHeight) {
            if (validSlimeMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) || 
              validGroundOrLMireMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                break;
              } 
              countBlock++;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen) {
            if (genIsFlatSolid(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad + 1, world) || 
              genIsFlat(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad + 1, world))
              generateForcedMud(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad - 1, world, random, 5); 
          } else if (firstBlockYCoord < 17 + dHeight) {
            break;
          } 
        }  
    } 
  }
  
  private void generateABC2(World world, Random random, int chunkX, int chunkZ) {
    int dHeight = 0;
    int firstBlockXCoord = chunkX + random.nextInt(16);
    int firstBlockYCoord = 96 + dHeight;
    int firstBlockZCoord = chunkZ + random.nextInt(16);
    BiomeGenBase biomegenbase = world.getBiomeGenForCoords(firstBlockXCoord, firstBlockZCoord);
    int biomeId = biomegenbase.biomeID;
    boolean blockGen = false;
    boolean isDreadlands = (biomeId == MFQM.ABCBiomes[9] || biomeId == MFQM.ABCBiomes[10]);
    boolean isDreadForest = (biomeId == MFQM.ABCBiomes[11]);
    boolean isDreadPurified = (biomeId == MFQM.ABCBiomes[12]);
    if (MFQM.GenMoss && MFQM.GenJQS && !blockGen) {
      boolean canGen = false;
      int genCount = 10 + random.nextInt(10);
      int genPos = 7;
      int genRad = 6;
      int genRadP = 6;
      int rad = 0;
      if (isDreadForest && 
        random.nextInt(genPos) == 0) {
        boolean Flat = false;
        if (random.nextInt(10) == 0)
          Flat = true; 
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) != MFQM.TarBlock) {
              if (validGroundMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
                !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  break;
                } 
                countBlock++;
              } 
            } else {
              canGen = false;
              break;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && 
            genIsFlat(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
            genIsFlat(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
            generateMossQS(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, MFQM.JungleQuicksandBlock);
            blockGen = true;
          } 
        } 
      } 
    } 
    if (MFQM.GenLiqMire && !blockGen) {
      boolean canGen = false;
      int genCount = 10 + random.nextInt(10);
      int genPos = 10;
      int genRad = 6;
      int genRadP = 6;
      int rad = 0;
      if (isDreadForest && 
        random.nextInt(genPos) == 0) {
        boolean Flat = false;
        if (random.nextInt(10) == 0)
          Flat = true; 
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) != MFQM.TarBlock) {
              if (validGroundMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
                !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  break;
                } 
                countBlock++;
              } 
            } else {
              canGen = false;
              break;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && 
            genIsFlatOrLMire(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
            genIsFlatOrLMire(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
            generateLiquidMire(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, 0);
            blockGen = true;
          } 
        } 
      } 
    } 
    if (MFQM.GenMeatS && 
      isDreadlands) {
      boolean canGen = false;
      int genCount = 5 + random.nextInt(2);
      int genPos = 3;
      int genRad = 6;
      int genRadP = 6;
      int rad = 0;
      if (random.nextInt(genPos) == 0)
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) != MFQM.TarBlock) {
              if (validSolidMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
                !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  break;
                } 
                countBlock++;
              } 
            } else {
              canGen = false;
              break;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen) {
            generateBlood(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad * 2, world, random, Material.rock);
            generateBlood(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad * 2, world, random, Material.rock);
            generateBlood(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad * 2, world, random, Material.rock);
            if (genIsFlatSolid(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad + 1, world) && 
              genIsFlatSolid(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad + 1, world)) {
              generateMire2(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.SwFleshBlock);
              generateTendrils(firstBlockXCoord, firstBlockYCoord + 5, firstBlockZCoord, rad * 2, world, random);
              blockGen = true;
            } 
          } 
        }  
    } 
    if (MFQM.GenTar && !blockGen) {
      boolean canGen = false;
      boolean genPosFounded = false;
      int genCount = 0;
      int genRealCount = 0;
      int genPos = 0;
      int genRad = 0;
      int genRadP = 0;
      int rad = 0;
      int FX = 0;
      int FZ = 0;
      int FY = 60;
      if (isDreadPurified) {
        genCount = 20 + random.nextInt(10);
        genRad = 10;
        genRadP = 5;
        int genSand = 0;
        genRealCount = 10 + random.nextInt(20);
        int radOfGen = (int)Math.floor(30.0D * Math.min(1.0D, genRealCount / 20.0D));
        genPos = 4;
        int T_genRealCount = genRealCount * 4;
        Block aBlock = null;
        int aMeta = 0;
        boolean aDetBlock = false;
        if (random.nextInt(genPos) == 0) {
          for (int i = 0; i < genCount && genRealCount > 0; i++) {
            if (!genPosFounded) {
              firstBlockXCoord = chunkX + random.nextInt(32) - 16;
              firstBlockYCoord = 96 + dHeight;
              firstBlockZCoord = chunkZ + random.nextInt(32) - 16;
              rad = genRad + random.nextInt(genRadP);
            } else {
              firstBlockXCoord = FX + random.nextInt(radOfGen) - radOfGen / 2;
              firstBlockYCoord = 96 + dHeight;
              firstBlockZCoord = FZ + random.nextInt(radOfGen) - radOfGen / 2;
              rad = genRad / 2 + random.nextInt(7) + radOfGen / 6;
            } 
            int countBlock = 0;
            int upper = 0;
            canGen = false;
            while (firstBlockYCoord > 60 + dHeight) {
              if (validTarMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
                !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) == MFQM.TarBlock) {
                    upper = -1;
                    break;
                  } 
                  upper = -1;
                  break;
                } 
                countBlock++;
              } 
              firstBlockYCoord--;
            } 
            if (canGen) {
              canGen = false;
              if (!aDetBlock) {
                aBlock = world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
                if (aBlock != null && 
                  aBlock.getMaterial().isSolid() && 
                  aBlock.isOpaqueCube() && 
                  !aBlock.getMaterial().isLiquid() && 
                  !aBlock.hasTileEntity()) {
                  aMeta = world.getBlockMetadata(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
                  aDetBlock = true;
                } 
              } else {
                canGen = true;
              } 
            } 
            if (canGen && 
              genIsFlatTar(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord, rad, world) && 
              genIsFlatTar(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
              genIsFlatTar(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
              if (!genPosFounded) {
                genPosFounded = true;
                FX = firstBlockXCoord;
                FZ = firstBlockZCoord;
                FY = firstBlockYCoord;
                genCount = 750;
              } 
              int TgenSand = genSand;
              genRealCount--;
              generateCliffsTar(firstBlockXCoord, FY, firstBlockZCoord, (int)Math.floor(rad * 1.5D), world, random, aBlock, aMeta);
              generateFlatTar(firstBlockXCoord, FY - upper - 1, firstBlockZCoord, rad, world, random, TgenSand, aBlock, aMeta);
              blockGen = true;
            } 
          } 
          if (aDetBlock) {
            int ccount = genSand * 10 + T_genRealCount + random.nextInt(T_genRealCount / 2);
            int rcount = 0;
            for (int j = 0; rcount < ccount; j++) {
              firstBlockXCoord = FX + random.nextInt(radOfGen) - radOfGen / 2;
              firstBlockYCoord = FY;
              firstBlockZCoord = FZ + random.nextInt(radOfGen) - radOfGen / 2;
              float disToC = Math.max((float)Math.sqrt(Math.pow((firstBlockXCoord - FX), 2.0D) + Math.pow((firstBlockZCoord - FZ), 2.0D)), 1.0F);
              int cof_d = Math.max(1, (int)Math.floor(Math.pow((16.0F / disToC), 1.5D)));
              int rrad = 1;
              if (random.nextInt(1 * cof_d) == 0)
                rrad = 2 + random.nextInt(1); 
              if (random.nextInt(2 * cof_d) == 0)
                rrad = 3 + random.nextInt(2); 
              if (random.nextInt(3 * cof_d) == 0)
                rrad = 4 + random.nextInt(3); 
              int qy = 1;
              if (random.nextInt(5) == 0)
                qy = 0; 
              if (rrad > 1)
                generateRockTar(firstBlockXCoord, firstBlockYCoord - qy - random.nextInt(Math.max(1, (int)Math.floor((rrad / 2)))), firstBlockZCoord, rrad, world, random, aBlock, aMeta); 
              rcount++;
            } 
          } 
        } 
      } 
    } 
  }
  
  private void generateABC1(World world, Random random, int chunkX, int chunkZ) {
    int dHeight = 0;
    int firstBlockXCoord = chunkX + random.nextInt(16);
    int firstBlockYCoord = 96 + dHeight;
    int firstBlockZCoord = chunkZ + random.nextInt(16);
    BiomeGenBase biomegenbase = world.getBiomeGenForCoords(firstBlockXCoord, firstBlockZCoord);
    int biomeId = biomegenbase.biomeID;
    boolean blockGen = false;
    if (MFQM.GenMud && !blockGen) {
      boolean canGen = false;
      int genType = 0;
      int genCount = 0;
      int genPos = 0;
      int genRad = 0;
      int genRadP = 0;
      int rad = 0;
      genCount = 10 + random.nextInt(10);
      genRad = 6;
      genRadP = 7;
      genPos = 5;
      if (random.nextInt(genPos) == 0 && genPos != 999)
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad * 3 + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 55 + dHeight) {
            if (validSlimeMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) || 
              validGroundOrLMireMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                break;
              } 
              countBlock++;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen)
            generateForcedMud(firstBlockXCoord, firstBlockYCoord + 3, firstBlockZCoord, rad, world, random, 0); 
        }  
    } 
    if (MFQM.GenSlime) {
      boolean canGen = false;
      int genCount = 5 + random.nextInt(2);
      int genPos = 10;
      int genRad = 6;
      int genRadP = 10;
      int rad = 0;
      if (random.nextInt(genPos) == 0) {
        boolean Flat = false;
        if (random.nextInt(10) == 0)
          Flat = true; 
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) != MFQM.TarBlock) {
              if ((validSlimeMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) || 
                validGroundOrLMireMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) && 
                !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  break;
                } 
                countBlock++;
              } 
            } else {
              canGen = false;
              break;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && 
            genIsFlatSolid(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
            genIsFlatSolid(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
            if (!Flat && genIsFlatSolid(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad * 2, world)) {
              generateMireOD(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.SlimeBlock, 0);
            } else {
              generateMireO(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.SlimeBlock);
            } 
            blockGen = true;
          } 
        } 
      } 
    } 
    if (MFQM.GenTar) {
      int trType = 0;
      int tarP1 = 0;
      int tarP2 = 0;
      int tarPU = 0;
      tarP1 = 2;
      tarP2 = 35;
      tarPU = 20;
      if (random.nextInt(tarP1) == 0 && 
        random.nextInt(tarP2) == 0) {
        int var5 = chunkX + random.nextInt(16) + 8;
        int var6 = 60 + random.nextInt(21) + dHeight;
        int var7 = chunkZ + random.nextInt(16) + 8;
        (new CustomWorldGenLakes(MFQM.TarBlock)).generate(world, random, var5, var6, var7, 1);
        blockGen = true;
      } 
      if (random.nextInt(tarP1) == 0 && 
        random.nextInt(tarPU) == 0) {
        int var5 = chunkX + random.nextInt(16) + 8;
        int var6 = random.nextInt(64) + dHeight;
        int var7 = chunkZ + random.nextInt(16) + 8;
        (new CustomWorldGenLakes(MFQM.TarBlock)).generate(world, random, var5, var6, var7, 1);
        blockGen = true;
      } 
    } 
  }
  
  private void generateCustom(World world, Random random, int chunkX, int chunkZ, int height) {
    float MireMul = 1.0F;
    if (world.provider.dimensionId == MFQM.TFDim)
      MireMul = 10.0F; 
    int dHeight = height - 62;
    float wordSizer = Math.max(height / 62.0F, 1.0F);
    int firstBlockXCoord = chunkX + random.nextInt(16);
    int firstBlockYCoord = 96 + dHeight;
    int firstBlockZCoord = chunkZ + random.nextInt(16);
    BiomeGenBase biomegenbase = world.getBiomeGenForCoords(firstBlockXCoord, firstBlockZCoord);
    int biomeId = biomegenbase.biomeID;
    boolean blockGen = false;
    boolean isSwamp = false;
    boolean isForest = false;
    boolean isBeach = false;
    boolean isDesert = false;
    boolean isJungle = false;
    boolean isDry = false;
    boolean isWet = false;
    boolean isSnow = false;
    boolean isPlains = false;
    boolean isDark = false;
    int swampType = 0;
    if (BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.SWAMP)) {
      isSwamp = true;
      if (BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.RIVER))
        isSwamp = false; 
    } 
    if (BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.FOREST))
      isForest = true; 
    if (BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.FROZEN) || 
      BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.COLD))
      isSnow = true; 
    if (BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.SPOOKY))
      isDark = true; 
    if (BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.HOT) || 
      BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.SAVANNA) || 
      BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.DRY))
      isDry = true; 
    if (BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.WET))
      isWet = true; 
    if (BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.PLAINS))
      isPlains = true; 
    if (world.provider.dimensionId != MFQM.TFDim) {
      if (BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.JUNGLE))
        isJungle = true; 
      if (BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.BEACH))
        isBeach = true; 
      if (BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.DESERT) || 
        BiomeDictionary.isBiomeOfType(biomegenbase, BiomeDictionary.Type.SANDY))
        isDesert = true; 
    } 
    if (isSwamp) {
      swampType = 0;
      if (MireMul < 10.0F) {
        int regionX = (int)Math.floor(chunkX / 256.0D) * 256;
        int regionZ = (int)Math.floor(chunkZ / 256.0D) * 256;
        swampType = 0;
        if ((regionX * regionX + regionZ) % 2 == 0)
          swampType = 2; 
        if ((regionX * regionX + regionZ) % 5 == 0)
          swampType = 3; 
        if ((regionX * regionX * regionX + regionZ * regionZ * regionZ) % 7 == 0)
          swampType = 0; 
      } 
    } 
    if (MFQM.GenQS && !blockGen && (
      isDesert || isBeach)) {
      boolean canGen = false;
      int genCount = 0;
      int genPos = 0;
      int genRad = 0;
      int genRadP = 0;
      boolean sandOnly = false;
      int rad = 0;
      if (isDesert) {
        genCount = 10 + random.nextInt(10);
        genRad = 5;
        sandOnly = true;
        genRadP = 10;
        genPos = 40;
      } 
      if (isBeach) {
        genCount = 10 + random.nextInt(10);
        genRad = 5;
        sandOnly = true;
        genRadP = 5;
        genPos = 15;
      } 
      if (random.nextInt(genPos) == 0)
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (validSandMaterial(sandOnly, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                break;
              } 
              countBlock++;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && 
            genIsFlat(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
            genIsFlat(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
            generateMire(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.QuicksandBlock);
            blockGen = true;
          } 
        }  
    } 
    if (MFQM.GenJQS && !blockGen && 
      isJungle) {
      boolean canGen = false;
      int genCount = 10 + random.nextInt(10);
      int genPos = 10;
      int genRad = 6;
      int genRadP = 10;
      int rad = 0;
      if (random.nextInt(genPos) == 0) {
        boolean Flat = false;
        if (random.nextInt(10) == 0)
          Flat = true; 
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) != MFQM.TarBlock) {
              if (validGroundMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
                !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  break;
                } 
                countBlock++;
              } 
            } else {
              canGen = false;
              break;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && 
            genIsFlat(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
            genIsFlat(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
            if (Flat) {
              generateMireO(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.JungleQuicksandBlock);
            } else {
              generateMireOD(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.JungleQuicksandBlock, 0);
            } 
            generateLeavesAround(firstBlockXCoord, firstBlockYCoord + 3, firstBlockZCoord, rad + 3, world, random);
            blockGen = true;
          } 
        } 
      } 
    } 
    if (MFQM.GenSSnow && !blockGen && 
      isSnow) {
      boolean canGen = false;
      int genCount = 0;
      int genPos = 0;
      int genRad = 0;
      int genRadP = 0;
      int rad = 0;
      genCount = 20 + random.nextInt(10);
      genRad = 5;
      genRadP = 10;
      genPos = 60;
      if (random.nextInt(genPos) == 0)
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX - 16 + random.nextInt(32);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ - 16 + random.nextInt(32);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (validSnowMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
              !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                break;
              } 
              countBlock++;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && 
            genIsFlatSnow(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
            genIsFlatSnow(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
            generateSnow(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord, rad, world, random);
            blockGen = true;
          } 
        }  
    } 
    if (MFQM.GenSQSF && !blockGen && ((
      !isDesert && !isSnow) || (isForest && !isDry))) {
      boolean canGen = false;
      boolean canForceGen = false;
      int genCount = 0;
      int genPos = 0;
      int genRad = 0;
      int genRadP = 0;
      int rad = 0;
      genCount = 5 + random.nextInt(10);
      genRad = 5;
      genRadP = 6;
      if (MireMul > 5.0F) {
        genPos = 175;
      } else {
        genPos = 80;
      } 
      if (random.nextInt(genPos) == 0)
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (validGroundMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
              !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                break;
              } 
              countBlock++;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen) {
            if (!canForceGen && 
              isNearAbovePlant(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world, rad))
              canForceGen = true; 
            if (((MireMul <= 5.0F && genIsFlatSolid(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world)) || genIsFlat(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world)) && 
              genIsFlat(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world))
              if (isForest && !isDry) {
                generateSoftQuicksandForest(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.SoftQuicksandBlock);
                blockGen = true;
              } else if (canForceGen) {
                generateSoftQuicksandForest(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.SoftQuicksandBlock);
                blockGen = true;
              }  
          } 
        }  
    } 
    if (MFQM.GenWastePit && !blockGen && 
      isSwamp && 
      swampType == 3) {
      boolean canGen = false;
      int genCount = 50 + random.nextInt(50);
      int genPos = 10;
      int genRad = 10;
      int genRadP = 10;
      int rad = 0;
      if (random.nextInt(genPos) == 0)
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) != MFQM.TarBlock) {
              if (validGroundMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
                !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  break;
                } 
                countBlock++;
              } 
            } else {
              canGen = false;
              break;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && 
            genIsFlatSlurry(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
            genIsFlatSlurry(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
            generateMud(firstBlockXCoord, firstBlockYCoord + 3, firstBlockZCoord, (int)Math.floor(rad * 1.75D), world, random);
            if (random.nextInt(10) == 0) {
              generateMireD(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.SlurryBlock, true);
            } else {
              generateMire(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.SlurryBlock);
            } 
            blockGen = true;
          } 
        }  
      if (MFQM.GenMud && 
        random.nextInt(genPos * 2) == 0)
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad * 3 + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (validGroundOrLMireMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                break;
              } 
              countBlock++;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen)
            generateMud(firstBlockXCoord, firstBlockYCoord + 3, firstBlockZCoord, rad, world, random); 
        }  
    } 
    if (MFQM.GenBog && !blockGen && 
      isSwamp && 
      swampType == 2) {
      int BogP = 0;
      int genCount = 0;
      int genRad = 0;
      int genRadP = 0;
      BogP = 10;
      genCount = random.nextInt(50) + 50;
      genRad = 10;
      genRadP = 20;
      if (random.nextInt(BogP) == 0)
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(64) - 32;
          firstBlockYCoord = 62 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(64) - 32;
          int rad = genRad + random.nextInt(genRadP);
          int genType = 0;
          boolean canGen = false;
          if (!canGen && 
            validWaterMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
            isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
            canGen = true;
            genType = 1;
          } 
          if (!canGen) {
            int countBlock = 0;
            firstBlockYCoord = 96 + dHeight;
            while (firstBlockYCoord > 60 + dHeight) {
              if (validGroundMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) || world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) == MFQM.BogBlock) {
                if (!validWaterMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world) && isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  genType = 0;
                  break;
                } 
                countBlock++;
              } 
              if (countBlock > 10)
                break; 
              firstBlockYCoord--;
            } 
          } 
          if (canGen)
            if (genType == 0) {
              if (isWaterNear(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world)) {
                generateBog(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.water);
                blockGen = true;
                if (random.nextInt(2) == 0)
                  generateMoorGrass(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world, random, MFQM.BogBlock); 
              } else if (genIsFlatBog(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
                genIsFlatBog(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
                generateBog(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake);
                blockGen = true;
                if (random.nextInt(2) == 0)
                  generateMoorGrass(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world, random, MFQM.BogBlock); 
              } 
            } else {
              generateBog(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.water);
              blockGen = true;
              if (random.nextInt(5) == 0)
                generateMoorGrass(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world, random, MFQM.BogBlock); 
            }  
        }  
    } 
    if (MFQM.GenMoss && 
      isSwamp && 
      swampType == 2) {
      int MossP = 0;
      int genCount = 0;
      int genRad = 0;
      int genRadP = 0;
      MossP = 40;
      genCount = random.nextInt(5) + 10;
      genRad = 7;
      genRadP = 7;
      if (random.nextInt(MossP) == 0)
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 67 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          int rad = genRad + random.nextInt(genRadP);
          int genType = 0;
          boolean canGen = false;
          int countBlock = 0;
          while (firstBlockYCoord > 60 + dHeight) {
            if (validLiqMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
              !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                break;
              } 
              countBlock++;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen)
            if (validLiqMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
              if (isGroundMossNear(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world))
                generateMoss(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world, random); 
            } else {
              generateMoss(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world, random);
            }  
        }  
    } 
    if (MFQM.GenLiqMire && !blockGen && 
      isSwamp) {
      boolean canGen = false;
      int genType = 0;
      int genCount = 0;
      int genPos = 0;
      int genRad = 0;
      int genRadP = 0;
      int rad = 0;
      int MireP1 = 25;
      switch (swampType) {
        case 1:
          MireP1 = 999;
          break;
        case 2:
          MireP1 = 32;
          break;
        case 3:
          MireP1 = 999;
          break;
        default:
          MireP1 = 25;
          break;
      } 
      genCount = 5 + random.nextInt(10);
      genRad = 6;
      genRadP = 12;
      if (random.nextInt(MireP1) == 0 && MireP1 != 999)
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX - 8 + random.nextInt(24);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ - 8 + random.nextInt(24);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (!validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world) && 
              validGroundMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                break;
              } 
              countBlock++;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && 
            genIsFlatOrLMire(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
            genIsFlatOrLMire(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
            generateLiquidMire(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, 2);
            blockGen = true;
          } 
        }  
      if (swampType == 0) {
        genCount = (int)Math.floor((2.0F * MireMul + random.nextInt((int)Math.floor((2.0F * MireMul)))));
        if (MireMul > 5.0F) {
          genRad = 10;
          genRadP = 10;
          genPos = 2;
        } else {
          genPos = (int)Math.floor((200.0F / MireMul));
        } 
        if (random.nextInt(genPos) == 0)
          for (int i = 0; i < genCount; i++) {
            firstBlockXCoord = chunkX + random.nextInt(16);
            firstBlockYCoord = 96 + dHeight;
            firstBlockZCoord = chunkZ + random.nextInt(16);
            rad = (int)Math.floor(genRad * 1.5D) + random.nextInt(genRadP);
            int countBlock = 0;
            canGen = false;
            while (firstBlockYCoord > 60 + dHeight - 1) {
              if (validWaterMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  break;
                } 
                countBlock++;
              } 
              if (countBlock > 10)
                break; 
              firstBlockYCoord--;
            } 
            if (canGen) {
              if (MireMul > 5.0F) {
                generateLiquidMire(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.water, 0);
              } else if (isGroundNear(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world)) {
                generateLiquidMire(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.water, 3);
              } 
              blockGen = true;
            } 
          }  
      } 
    } 
    if ((MFQM.GenMire || MFQM.GenMud) && !blockGen && 
      isSwamp && swampType != 3) {
      boolean canGen = false;
      int genType = 0;
      int genCount = 0;
      int genPos = 0;
      int genRad = 0;
      int genRadP = 0;
      int rad = 0;
      genCount = 20 + random.nextInt(10);
      genRad = 6;
      genRadP = 7;
      genPos = 4;
      int jur = 0;
      boolean try_gen_mud = false;
      boolean gen_mud = false;
      if ((MireMul <= 5.0F && random.nextInt(genPos * 2) == 0) || (MireMul > 5.0F && random.nextInt(genPos / 3 + 1) == 0)) {
        gen_mud = true;
        if ((MireMul <= 5.0F && random.nextInt(genPos + 1) == 0) || (MireMul > 5.0F && random.nextInt(genPos + 1) == 0))
          try_gen_mud = true; 
      } 
      if (random.nextInt(genPos) == 0 && genPos != 999 && (
        MFQM.GenMire || MFQM.GenMud)) {
        int i;
        for (i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (validGroundOrLMireMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                break;
              } 
              countBlock++;
            } 
            if (countBlock > 10)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && ((
            MireMul <= 5.0F && genIsFlatSolid(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world)) || genIsFlat(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world)) && 
            genIsFlat(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
            if (MFQM.GenMire)
              if (genIsFlat(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad * 2, world)) {
                generateMireD(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.MireBlock, true);
              } else {
                generateMireD(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.MireBlock, false);
              }  
            try_gen_mud = true;
            blockGen = true;
          } 
        } 
        if (try_gen_mud && gen_mud && 
          MFQM.GenMud)
          for (i = 0; i < genCount; i++) {
            firstBlockXCoord = chunkX + random.nextInt(16);
            firstBlockYCoord = 96 + dHeight;
            firstBlockZCoord = chunkZ + random.nextInt(16);
            rad = genRad * 3 + random.nextInt(genRadP);
            int countBlock = 0;
            canGen = false;
            while (firstBlockYCoord > 60 + dHeight) {
              if (validGroundOrLMireMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  break;
                } 
                countBlock++;
              } 
              if (countBlock > 10)
                break; 
              firstBlockYCoord--;
            } 
            if (canGen)
              generateMud(firstBlockXCoord, firstBlockYCoord + 3, firstBlockZCoord, rad, world, random); 
          }  
      } 
      if (MFQM.GenMud) {
        genCount = 30 + random.nextInt(20);
        genRad = 6;
        genRadP = 7;
        genPos = 1;
        if (random.nextInt(genPos) == 0 && genPos != 999)
          for (int i = 0; i < genCount; i++) {
            firstBlockXCoord = chunkX + random.nextInt(16);
            firstBlockYCoord = 96 + dHeight;
            firstBlockZCoord = chunkZ + random.nextInt(16);
            int countBlock = 0;
            canGen = false;
            while (firstBlockYCoord > 55 + dHeight) {
              if (validGroundOrLMireMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  break;
                } 
                countBlock++;
              } 
              if (countBlock > 10)
                break; 
              firstBlockYCoord--;
            } 
            if (canGen)
              generateMud(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world, random); 
          }  
      } 
    } 
    if (MFQM.GenBClay && !blockGen) {
      boolean canGen = false;
      int genCount = 0;
      int genPos = 0;
      int genRad = 0;
      int genRadP = 0;
      int rad = 0;
      int Ctype = 0;
      genPos = 50;
      genCount = 60 + random.nextInt(10);
      genRad = 10;
      genRadP = 7;
      if (!isSnow)
        genPos = 0; 
      if (genPos != 0 && 
        random.nextInt(genPos) == 0)
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (validSnowMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
              canGen = false;
              break;
            } 
            if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) == Blocks.ice) {
              canGen = false;
              break;
            } 
            if (validWaterMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                Ctype = 0;
                break;
              } 
              countBlock++;
            } 
            if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) == MFQM.BrownClayBlock) {
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                Ctype = 1;
                break;
              } 
              countBlock++;
            } 
            if (countBlock > 20)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen)
            if (Ctype == 0) {
              if (isItLake2(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad * 2, world, false)) {
                generateBrownClay(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad * 2, world, random);
                blockGen = true;
              } 
            } else {
              generateBrownClay(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad * 2, world, random);
              blockGen = true;
            }  
        }  
    } 
    if (MFQM.GenSlime && !blockGen) {
      int genCount = 75 + random.nextInt(10);
      int realGen = 1;
      int genPos = 85;
      int genRad = 7;
      int genRadP = 10;
      int rad = 0;
      boolean canGen = false;
      if (random.nextInt(genPos) == 0)
        for (int i = 0; i < genCount && realGen > 0; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 16) {
            if (validSlimeMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
              !validWaterMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world))
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                if (isInCave(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world, rad)) {
                  canGen = true;
                  break;
                } 
              } else {
                countBlock++;
              }  
            firstBlockYCoord--;
          } 
          if (canGen && 
            random.nextInt((int)Math.floor(((firstBlockYCoord / 4) / wordSizer))) != 0 && 
            genIsFlatSlime(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
            genIsFlatSlime(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world) && 
            genIsFlatSlime(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord, rad, world)) {
            generateSlime(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random);
            realGen--;
            blockGen = true;
          } 
        }  
    } 
    if (MFQM.GenTar && !blockGen) {
      int trType = 0;
      int tarP1 = 0;
      int tarP2 = 0;
      int tarPU = 0;
      if (isDark) {
        tarP1 = 10;
        tarP2 = 8;
        tarPU = 10;
      } else {
        tarP1 = 35;
        tarP2 = 15;
        tarPU = 10;
      } 
      if (random.nextInt(tarP1) == 0 && 
        random.nextInt(tarP2) == 0) {
        int var5 = chunkX + random.nextInt(16) + 8;
        int var6 = 60 + random.nextInt(21) + dHeight;
        int var7 = chunkZ + random.nextInt(16) + 8;
        (new CustomWorldGenLakes(MFQM.TarBlock)).generate(world, random, var5, var6, var7, 1);
        blockGen = true;
      } 
      if (random.nextInt(tarP1) == 0 && 
        random.nextInt(tarPU) == 0) {
        int var5 = chunkX + random.nextInt(16) + 8;
        int var6 = random.nextInt(64) + dHeight;
        int var7 = chunkZ + random.nextInt(16) + 8;
        (new CustomWorldGenLakes(MFQM.TarBlock)).generate(world, random, var5, var6, var7, 1);
        blockGen = true;
      } 
    } 
  }
  
  private void generateNether(World world, Random random, int chunkX, int chunkZ) {
    this.T_BoP = MFQM.BoP;
    if (MFQM.PBoP && 
      MFQM.BoP && (
      world.getWorldInfo().getTerrainType() == WorldType.LARGE_BIOMES || world.getWorldInfo().getTerrainType() == WorldType.DEFAULT))
      this.T_BoP = false; 
    int firstBlockXCoord = chunkX + random.nextInt(16);
    int firstBlockYCoord = 96;
    int firstBlockZCoord = chunkZ + random.nextInt(16);
    BiomeGenBase biomegenbase = world.getBiomeGenForCoords(firstBlockXCoord, firstBlockZCoord);
    int biomeId = biomegenbase.biomeID;
    boolean blockGen = false;
    if (MFQM.GenTar) {
      int trType = 0;
      int tarP1 = 0;
      int tarP2 = 0;
      int tarPU = 0;
      trType = getTarCount(biomeId);
      int trrType = trType;
      if (MFQM.BoP && 
        trType == 0)
        trType = isTarBiomeIsNear(chunkX + 8, 64, chunkZ + 8, world); 
      switch (trType) {
        case 4:
          tarP1 = 1;
          tarP2 = 3;
          tarPU = 3;
          break;
        default:
          tarP1 = 8;
          tarP2 = 5;
          tarPU = 5;
          break;
      } 
      if (random.nextInt(tarP1) == 0 && 
        random.nextInt(tarP2) == 0) {
        int var5 = chunkX + random.nextInt(16) + 8;
        int var6 = random.nextInt(128);
        int var7 = chunkZ + random.nextInt(16) + 8;
        (new CustomWorldGenLakes(MFQM.TarBlock)).generate(world, random, var5, var6, var7, 0);
        blockGen = true;
      } 
    } 
    if (MFQM.GenCSand && !blockGen) {
      int dT = getCSandsType(biomeId);
      boolean canGen = false;
      int genCount = 0;
      int genPos = 0;
      int genRad = 0;
      int genRadP = 0;
      int rad = 0;
      switch (dT) {
        case 1:
          genCount = 20 + random.nextInt(20);
          genRad = 7;
          genRadP = 7;
          genPos = 5;
          break;
        case 0:
          genCount = 10 + random.nextInt(10);
          genRad = 7;
          genRadP = 7;
          genPos = 5;
          break;
      } 
      if (random.nextInt(genPos) == 0)
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 30 + random.nextInt(98);
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 30) {
            if (validCSMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
              !validLavaMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                break;
              } 
              countBlock++;
            } 
            if (countBlock > 50)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && 
            genIsFlat(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
            genIsFlat(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
            generateMire(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.sand, MFQM.CSandBlock);
            blockGen = true;
          } 
        }  
    } 
    if ((MFQM.GenMeatS || MFQM.GenMeat || MFQM.GenMeatAss) && !blockGen) {
      int dT = getBloodType(biomeId);
      boolean canGen = false;
      int genCount = 0;
      int genPos = 0;
      int genRad = 0;
      int genRadP = 0;
      int rad = 0;
      switch (dT) {
        case 1:
          genCount = 40 + random.nextInt(20);
          genRad = 10;
          genRadP = 7;
          genPos = 4;
          break;
        case 0:
          genCount = 30 + random.nextInt(10);
          genRad = 10;
          genRadP = 7;
          genPos = 50;
          break;
      } 
      if (dT == 0 && this.T_BoP)
        genPos *= 100; 
      if (!this.T_BoP) {
        double POX = chunkX / 20.0D;
        double POZ = chunkZ / 20.0D;
        double srfX = 1.1D * Math.sin(POX * 0.2623D) + 3.2D * Math.sin(POX * 0.9006D);
        double srfZ = 1.1D * Math.sin(POZ * 0.7623D) - 3.0D * Math.cos(POZ * 0.2006D) + 2.0D * Math.sin(POX * 0.0106D);
        if (random.nextInt((int)Math.floor(Math.max(Math.abs(srfX * 10.0D + srfZ * 10.0D), 1.0D))) == 0)
          genPos = 1; 
      } 
      boolean genHole = false;
      if (random.nextInt(genPos) == 0) {
        if (MFQM.GenMeatS)
          for (int i = 0; i < genCount; i++) {
            firstBlockXCoord = chunkX + random.nextInt(16);
            firstBlockYCoord = 40 + random.nextInt(98);
            firstBlockZCoord = chunkZ + random.nextInt(16);
            rad = genRad + random.nextInt(genRadP);
            int countBlock = 0;
            canGen = false;
            while (firstBlockYCoord > 40) {
              if ((validBloodMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) || validVoreMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) && 
                !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  break;
                } 
                countBlock++;
              } 
              if (countBlock > 50)
                break; 
              firstBlockYCoord--;
            } 
            if (canGen) {
              generateBlood(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad * 2, world, random, Material.cake);
              generateBlood(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad * 2, world, random, Material.cake);
              generateBlood(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad * 2, world, random, Material.cake);
              if ((MFQM.GenMeat && random.nextInt(5) == 0) || !MFQM.GenMeat) {
                if (genIsFlatSolid(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad + 1, world) && 
                  genIsFlatSolid(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad + 1, world)) {
                  generateMire2(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.SwFleshBlock);
                  generateTendrils(firstBlockXCoord, firstBlockYCoord + 5, firstBlockZCoord, rad * 2, world, random);
                  genHole = false;
                  blockGen = true;
                } 
              } else {
                genHole = true;
              } 
            } 
          }  
        if (MFQM.GenMeat) {
          boolean blockGen2 = false;
          if (genHole)
            for (int i = 0; i < genCount * 2 && !blockGen2; i++) {
              firstBlockXCoord = chunkX + random.nextInt(12) + 2;
              firstBlockYCoord = 40 + random.nextInt(98);
              firstBlockZCoord = chunkZ + random.nextInt(12) + 2;
              rad = genRad + random.nextInt(genRadP);
              int countBlock = 0;
              canGen = false;
              while (firstBlockYCoord > 40) {
                if (validVoreMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
                  !validLavaMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world))
                  if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                    if (isVorePlace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                      canGen = true;
                      break;
                    } 
                  } else {
                    countBlock++;
                  }  
                if (countBlock > 50)
                  break; 
                firstBlockYCoord--;
              } 
              if (canGen && 
                genIsFlatVore(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, 6, world)) {
                generateVore(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world, random);
                blockGen2 = true;
              } 
            }  
        } 
        if (MFQM.GenMeatAss) {
          int assGen = 0;
          int BlockYCoord = 0;
          int BlockXCoord = 0;
          int BlockZCoord = 0;
          int realGen = genCount / 4;
          if (random.nextInt(genPos + 5) == 0)
            for (int i = 0; i < genCount * 40 && realGen > 0; i++) {
              if (BlockYCoord == 0) {
                firstBlockYCoord = 20 + random.nextInt(30);
                firstBlockXCoord = chunkX + random.nextInt(16);
                firstBlockZCoord = chunkZ + random.nextInt(16);
              } else {
                firstBlockYCoord = BlockYCoord + 1;
                firstBlockXCoord = BlockXCoord + random.nextInt(11) - 5;
                firstBlockZCoord = BlockZCoord + random.nextInt(11) - 5;
              } 
              rad = genRad - 2 + random.nextInt(genRadP);
              int countBlock = 0;
              canGen = false;
              while (firstBlockYCoord > 30) {
                if ((validBloodMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) || 
                  validVoreMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) || (
                  validLiqMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && world
                  .getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) != MFQM.AcidBlock && world
                  .getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord).getMaterial() != Material.lava)) && 
                  !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world) && 
                  !isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                  if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                    canGen = true;
                    break;
                  } 
                  countBlock++;
                } 
                if (countBlock > 50 || (BlockYCoord != 0 && firstBlockYCoord < BlockYCoord - 1))
                  break; 
                firstBlockYCoord--;
              } 
              if (canGen) {
                assGen -= ClearArea2(firstBlockXCoord + random.nextInt(9) - 4, firstBlockYCoord + 1, firstBlockZCoord + random.nextInt(9) - 4, rad + 1, world, random);
                if (assGen < 0)
                  assGen = 0; 
                generateBlood2(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad + 5, world, random);
                generateBlood2(firstBlockXCoord, firstBlockYCoord + 2, firstBlockZCoord, rad + 5, world, random);
                generateBlood2(firstBlockXCoord, firstBlockYCoord + 3, firstBlockZCoord, rad + 5, world, random);
                firstBlockXCoord += random.nextInt(9) - 4;
                firstBlockZCoord += random.nextInt(9) - 4;
                if (genIsFlatSlurry(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad + 1, world) && 
                  genIsFlatSlurry(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad + 1, world)) {
                  generateMud2(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, (int)Math.floor(rad * 1.75D), world, random);
                  generateSlurry(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random);
                  BlockYCoord = firstBlockYCoord;
                  BlockZCoord = firstBlockZCoord;
                  BlockXCoord = firstBlockXCoord;
                  if (random.nextInt(assGen * 2 + 1) == 0 && 
                    generateAss(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, 5 + random.nextInt(5), world, random))
                    assGen++; 
                  realGen--;
                  blockGen = true;
                } 
              } 
            }  
        } 
      } 
    } 
  }
  
  private void generateSurface(World world, Random random, int chunkX, int chunkZ, int height) {
    this.T_BoP = MFQM.BoP;
    int dHeight = height - 62;
    float wordSizer = height / 62.0F;
    if (MFQM.PBoP && 
      MFQM.BoP && (
      world.getWorldInfo().getTerrainType() == WorldType.LARGE_BIOMES || world.getWorldInfo().getTerrainType() == WorldType.DEFAULT))
      this.T_BoP = false; 
    int firstBlockXCoord = chunkX + random.nextInt(16);
    int firstBlockYCoord = 96 + dHeight;
    int firstBlockZCoord = chunkZ + random.nextInt(16);
    BiomeGenBase biomegenbase = world.getBiomeGenForCoords(firstBlockXCoord, firstBlockZCoord);
    int biomeId = biomegenbase.biomeID;
    boolean blockGen = false;
    if (MFQM.GenDesertTombs && 
      !blockGen) {
      int dT = getDesertType(biomeId);
      if (((dT == 1 || dT == 6) && this.T_BoP) || (biomegenbase instanceof net.minecraft.world.biome.BiomeGenDesert && !this.T_BoP)) {
        boolean canGen = false;
        int genCount = 0;
        int genPos = 0;
        int rad = 0;
        genCount = 1;
        genPos = 750;
        if (genPos > 0 && 
          random.nextFloat() * genPos > (genPos - 1)) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 70 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (validSolidMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
              canGen = true;
              break;
            } 
            firstBlockYCoord--;
          } 
          if (canGen) {
            (new GenerateDesertTombs()).generate(world, random, firstBlockXCoord, firstBlockYCoord - 8 - random.nextInt(9), firstBlockZCoord);
            blockGen = true;
          } 
        } 
      } 
    } 
    if (MFQM.GenWastePit && !blockGen) {
      int slrT = getSlurryType(biomeId);
      boolean dep = false;
      if (random.nextInt(10) == 0)
        dep = true; 
      if (slrT != 0 && this.T_BoP) {
        boolean canGen = false;
        int genCount = 30 + random.nextInt(30);
        int genPos = 75;
        int genRad = 10;
        int genRadP = 10;
        int rad = 0;
        if (random.nextInt(genPos) == 0)
          for (int i = 0; i < genCount; i++) {
            firstBlockXCoord = chunkX + random.nextInt(16);
            firstBlockYCoord = 96 + dHeight;
            firstBlockZCoord = chunkZ + random.nextInt(16);
            rad = genRad + random.nextInt(genRadP);
            int countBlock = 0;
            canGen = false;
            while (firstBlockYCoord > 60 + dHeight) {
              if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) != MFQM.TarBlock) {
                if (validSolidMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
                  !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                  if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                    canGen = true;
                    break;
                  } 
                  countBlock++;
                } 
              } else {
                canGen = false;
                break;
              } 
              if (countBlock > 10)
                break; 
              firstBlockYCoord--;
            } 
            if (canGen && 
              genIsFlatSlurry(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
              genIsFlatSlurry(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
              if (dep) {
                generateMireD(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.SlurryBlock, true);
              } else {
                generateMire(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.SlurryBlock);
              } 
              blockGen = true;
            } 
          }  
      } 
    } 
    if (MFQM.GenWax && 
      !blockGen) {
      int jngT = getJungleType(biomeId);
      if ((jngT != 0 && this.T_BoP) || (biomegenbase instanceof net.minecraft.world.biome.BiomeGenJungle && !this.T_BoP)) {
        boolean canGen = false;
        int genCount = 52;
        int genPos = 85;
        int genRad = 16;
        if ((jngT == 3 || jngT == 0) && 
          random.nextInt(genPos) == 0)
          for (int i = 0; i < genCount; i++) {
            firstBlockXCoord = chunkX + random.nextInt(16);
            firstBlockYCoord = 75 - random.nextInt(8) + dHeight;
            firstBlockZCoord = chunkZ + random.nextInt(16);
            if (generateWaxTree(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, genRad, world, random)) {
              blockGen = true;
              break;
            } 
          }  
      } 
    } 
    if (MFQM.GenHoney && 
      !blockGen) {
      int jngT = getJungleType(biomeId);
      if ((jngT != 0 && this.T_BoP) || (biomegenbase instanceof net.minecraft.world.biome.BiomeGenJungle && !this.T_BoP)) {
        boolean canGen = false;
        int genCount = 50;
        int realGen = 0;
        int genPos = 175;
        int genRad = 16;
        int genRadP = 16;
        int rad = genRad + random.nextInt(genRadP);
        int arad = 16 + random.nextInt(10);
        if (jngT != 2 && 
          random.nextInt(genPos) == 0)
          for (int i = 0; i < genCount; i++) {
            firstBlockXCoord = chunkX + random.nextInt(32) - 16;
            firstBlockYCoord = 80 - random.nextInt(8) + dHeight;
            firstBlockZCoord = chunkZ + random.nextInt(32) - 16;
            int countBlock = 0;
            canGen = false;
            while (firstBlockYCoord > 60 + dHeight) {
              if (validSolidMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                break;
              } 
              countBlock++;
              firstBlockYCoord--;
            } 
            if (canGen) {
              if (!genIsHoneycombNear(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, arad * 2, world)) {
                int ahoney = 20;
                generateHoneycomb(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, arad, world, random, ahoney);
                generateHoneyNest(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random);
                blockGen = true;
              } 
              break;
            } 
          }  
      } 
    } 
    if (MFQM.GenSlime) {
      int genCount = 75 + random.nextInt(10);
      int realGen = 1;
      int genPos = 75;
      int genRad = 7;
      int genRadP = 10;
      int rad = 0;
      boolean canGen = false;
      if (random.nextInt(genPos) == 0)
        for (int i = 0; i < genCount && realGen > 0; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 16) {
            if (validSlimeMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
              !validWaterMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world))
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                if (isInCave(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world, rad)) {
                  canGen = true;
                  break;
                } 
              } else {
                countBlock++;
              }  
            firstBlockYCoord--;
          } 
          if (canGen && 
            random.nextInt((int)Math.floor(((firstBlockYCoord / 4) / wordSizer))) != 0 && 
            genIsFlatSlime(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
            genIsFlatSlime(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world) && 
            genIsFlatSlime(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord, rad, world)) {
            generateSlime(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random);
            realGen--;
            blockGen = true;
          } 
        }  
    } 
    if (MFQM.GenWeb && !blockGen) {
      int sT = getLarvType(biomeId);
      if ((sT != 0 && this.T_BoP) || (biomeId == MFQM.LarvBiomes[2] && !this.T_BoP)) {
        boolean canGen = false;
        boolean genPosFounded = false;
        int genCount = 0;
        int genRealCount = 0;
        int genPos = 0;
        int genRad = 0;
        int genRadP = 0;
        int rad = 0;
        int FX = 0;
        int FZ = 0;
        switch (sT) {
          case 1:
            genCount = 20 + random.nextInt(10);
            genRad = 7;
            genRadP = 10;
            genPos = 100;
            break;
          case 2:
            genCount = 20 + random.nextInt(10);
            genRad = 7;
            genRadP = 10;
            genPos = 75;
            break;
          case 3:
            genCount = 20 + random.nextInt(10);
            genRad = 7;
            genRadP = 10;
            genPos = 125;
            break;
          case 0:
            genCount = 20 + random.nextInt(10);
            genRad = 7;
            genRadP = 10;
            genPos = 125;
            break;
        } 
        genRealCount = 15 + random.nextInt(10);
        if (random.nextInt(genPos) == 0)
          for (int i = 0; i < genCount && genRealCount > 0; i++) {
            if (!genPosFounded) {
              firstBlockXCoord = chunkX + random.nextInt(32) - 16;
              firstBlockYCoord = 96 + dHeight;
              firstBlockZCoord = chunkZ + random.nextInt(32) - 16;
              rad = genRad + random.nextInt(genRadP);
            } else {
              firstBlockXCoord = FX + random.nextInt(24) - 12;
              firstBlockYCoord = 96 + dHeight;
              firstBlockZCoord = FZ + random.nextInt(24) - 12;
              rad = genRad + random.nextInt(10);
            } 
            int countBlock = 0;
            int upper = 0;
            canGen = false;
            biomegenbase = world.getBiomeGenForCoords(firstBlockXCoord, firstBlockZCoord);
            biomeId = biomegenbase.biomeID;
            sT = getLarvType(biomeId);
            while (firstBlockYCoord > 60 + dHeight && ((
              sT != 0 && this.T_BoP) || (biomeId == MFQM.LarvBiomes[2] && !this.T_BoP))) {
              if (validGroundMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
                !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) == MFQM.DenseWebBlock) {
                    upper = 0;
                    break;
                  } 
                  upper = 1;
                  break;
                } 
                countBlock++;
              } 
              firstBlockYCoord--;
            } 
            if (canGen)
              if (genIsFlat(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
                genIsFlat(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world) && (
                genPosFounded || isNearAboveCeil(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world, rad))) {
                if (!genPosFounded) {
                  genPosFounded = true;
                  FX = firstBlockXCoord;
                  FZ = firstBlockZCoord;
                  genCount = 750;
                } 
                genRealCount--;
                generateDenseWeb(firstBlockXCoord, firstBlockYCoord - upper + 1, firstBlockZCoord, rad, world, random);
                generateWeb(firstBlockXCoord, firstBlockYCoord - upper + 5, firstBlockZCoord, rad * 8, world, random);
                generateCaveWeb(firstBlockXCoord, firstBlockYCoord - upper + 5, firstBlockZCoord, rad * 8, world, random);
                generateCaveWeb(firstBlockXCoord, firstBlockYCoord - upper + 3, firstBlockZCoord, rad * 5, world, random);
                blockGen = true;
              }  
          }  
      } 
    } 
    if (MFQM.GenLarv && !blockGen) {
      int sT = getLarvType(biomeId);
      if ((sT != 0 && this.T_BoP) || (biomeId == MFQM.LarvBiomes[2] && !this.T_BoP)) {
        boolean canGen = false;
        boolean genPosFounded = false;
        int genCount = 0;
        int genRealCount = 0;
        int genPos = 0;
        int genRad = 0;
        int genRadP = 0;
        int rad = 0;
        int FX = 0;
        int FZ = 0;
        switch (sT) {
          case 1:
            genCount = 20 + random.nextInt(10);
            genRad = 5;
            genRadP = 5;
            genPos = 75;
            break;
          case 2:
            genCount = 20 + random.nextInt(10);
            genRad = 5;
            genRadP = 5;
            genPos = 100;
            break;
          case 3:
            genCount = 20 + random.nextInt(10);
            genRad = 5;
            genRadP = 5;
            genPos = 150;
            break;
          case 0:
            genCount = 20 + random.nextInt(10);
            genRad = 5;
            genRadP = 5;
            genPos = 150;
            break;
        } 
        genRealCount = 15 + random.nextInt(10);
        if (random.nextInt(genPos) == 0)
          for (int i = 0; i < genCount && genRealCount > 0; i++) {
            if (!genPosFounded) {
              firstBlockXCoord = chunkX + random.nextInt(32) - 16;
              firstBlockYCoord = 96 + dHeight;
              firstBlockZCoord = chunkZ + random.nextInt(32) - 16;
              rad = genRad + random.nextInt(genRadP);
            } else {
              firstBlockXCoord = FX + random.nextInt(24) - 12;
              firstBlockYCoord = 96 + dHeight;
              firstBlockZCoord = FZ + random.nextInt(24) - 12;
              rad = genRad + random.nextInt(7);
            } 
            int countBlock = 0;
            int upper = 0;
            canGen = false;
            biomegenbase = world.getBiomeGenForCoords(firstBlockXCoord, firstBlockZCoord);
            biomeId = biomegenbase.biomeID;
            sT = getLarvType(biomeId);
            while (firstBlockYCoord > 60 + dHeight && ((
              sT != 0 && this.T_BoP) || (biomeId == MFQM.LarvBiomes[2] && !this.T_BoP))) {
              if (validGroundMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
                !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) == MFQM.LarvaeBlock) {
                    upper = 0;
                    break;
                  } 
                  upper = 1;
                  break;
                } 
                countBlock++;
              } 
              firstBlockYCoord--;
            } 
            if (canGen)
              if (genIsFlat(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
                genIsFlat(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world) && (
                genPosFounded || isNearAboveCeil(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world, rad))) {
                if (!genPosFounded) {
                  genPosFounded = true;
                  FX = firstBlockXCoord;
                  FZ = firstBlockZCoord;
                  genCount = 750;
                } 
                genRealCount--;
                generateLarvae(firstBlockXCoord, firstBlockYCoord - upper, firstBlockZCoord, rad, world, random);
                generateWeb(firstBlockXCoord, firstBlockYCoord - upper + 5, firstBlockZCoord, rad * 8, world, random);
                blockGen = true;
              }  
          }  
      } 
    } 
    if (MFQM.GenSSnow && !blockGen) {
      int sT = getSnowType(biomeId);
      if ((sT != 0 && this.T_BoP) || (biomegenbase instanceof net.minecraft.world.biome.BiomeGenSnow && !this.T_BoP)) {
        boolean canGen = false;
        int genCount = 0;
        int genPos = 0;
        int genRad = 0;
        int genRadP = 0;
        int rad = 0;
        switch (sT) {
          case 1:
            genCount = 20 + random.nextInt(10);
            genRad = 5;
            genRadP = 20;
            genPos = 60;
            break;
          case 2:
            genCount = 20 + random.nextInt(10);
            genRad = 5;
            genRadP = 20;
            genPos = 30;
            break;
          case 0:
            genCount = 20 + random.nextInt(10);
            genRad = 5;
            genRadP = 20;
            genPos = 60;
            break;
        } 
        if (random.nextInt(genPos) == 0)
          for (int i = 0; i < genCount; i++) {
            firstBlockXCoord = chunkX - 16 + random.nextInt(32);
            firstBlockYCoord = 96 + dHeight;
            firstBlockZCoord = chunkZ - 16 + random.nextInt(32);
            rad = genRad + random.nextInt(genRadP);
            int countBlock = 0;
            canGen = false;
            while (firstBlockYCoord > 60 + dHeight) {
              if (validSnowMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
                !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  break;
                } 
                countBlock++;
              } 
              if (countBlock > 10)
                break; 
              firstBlockYCoord--;
            } 
            if (canGen && 
              genIsFlatSnow(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
              genIsFlatSnow(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
              generateSnow(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord, rad, world, random);
              blockGen = true;
            } 
          }  
      } 
    } 
    if (MFQM.GenSSand && !blockGen) {
      int dT = getDesertType(biomeId);
      if ((dT != 0 && this.T_BoP) || (biomegenbase instanceof net.minecraft.world.biome.BiomeGenDesert && !this.T_BoP)) {
        boolean canGen = false;
        int genCount = 0;
        int genPos = 0;
        int genRad = 0;
        int genRadP = 0;
        boolean sandOnly = false;
        int rad = 0;
        switch (dT) {
          case 1:
            genCount = 1 + random.nextInt(2);
            genRad = 4;
            sandOnly = true;
            genRadP = 15;
            genPos = 30;
            break;
          case 6:
            genCount = 5 + random.nextInt(5);
            genRad = 4;
            sandOnly = true;
            genRadP = 15;
            genPos = 20;
            break;
          case 0:
            genCount = 1 + random.nextInt(2);
            genRad = 4;
            sandOnly = true;
            genRadP = 15;
            genPos = 20;
            break;
        } 
        if (genPos > 0 && 
          random.nextInt(genPos) == 0)
          for (int i = 0; i < genCount && !blockGen; i++) {
            firstBlockXCoord = chunkX + random.nextInt(16);
            firstBlockYCoord = 96 + dHeight;
            firstBlockZCoord = chunkZ + random.nextInt(16);
            rad = genRad + random.nextInt(genRadP);
            int countBlock = 0;
            canGen = false;
            while (firstBlockYCoord > 60 + dHeight) {
              if (validSandMaterial(sandOnly, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
                !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  break;
                } 
                countBlock++;
              } 
              if (countBlock > 10)
                break; 
              firstBlockYCoord--;
            } 
            if (canGen && 
              genIsFlat(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
              genIsFlat(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
              generateSoftSand(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random);
              blockGen = true;
            } 
          }  
      } 
    } 
    if (MFQM.GenMucBl && 
      !blockGen) {
      int dT = getJungleType(biomeId);
      if ((dT != 0 && this.T_BoP && biomeId != MFQM.JungleBiomes[1]) || (biomegenbase instanceof net.minecraft.world.biome.BiomeGenJungle && !this.T_BoP) || biomeId == MFQM.LarvBiomes[2]) {
        boolean canGen = false;
        int genCount = 0;
        int genPos = 0;
        int rad = 6;
        genCount = 50;
        genPos = 125;
        if (dT == 1)
          genPos = 75; 
        if (genPos > 0 && 
          random.nextInt(genPos) == 0)
          for (int i = 0; i < genCount && !blockGen; i++) {
            firstBlockXCoord = chunkX + random.nextInt(16);
            firstBlockYCoord = 70 + dHeight;
            firstBlockZCoord = chunkZ + random.nextInt(16);
            int countBlock = 0;
            canGen = false;
            while (firstBlockYCoord > 60 + dHeight) {
              if (validGroundMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world))
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  if (!validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                    canGen = true;
                    break;
                  } 
                } else {
                  countBlock++;
                }  
              if (countBlock > 10)
                break; 
              firstBlockYCoord--;
            } 
            if (canGen && 
              genIsFlat(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord, rad, world) && 
              genIsFlat(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world)) {
              (new GenerateMucusBlossom()).generate(world, random, firstBlockXCoord - 3, firstBlockYCoord - 6, firstBlockZCoord - 3);
              blockGen = true;
            } 
          }  
      } 
    } 
    if (MFQM.GenQS && !blockGen) {
      int dT = getDesertType(biomeId);
      if (biomegenbase instanceof net.minecraft.world.biome.BiomeGenBeach)
        dT = 5; 
      if ((dT != 0 && this.T_BoP) || ((dT == 5 || biomegenbase instanceof net.minecraft.world.biome.BiomeGenDesert) && !this.T_BoP)) {
        boolean canGen = false;
        int genCount = 0;
        int genPos = 0;
        int genRad = 0;
        int genRadP = 0;
        boolean sandOnly = false;
        int rad = 0;
        switch (dT) {
          case 1:
            genCount = 1 + random.nextInt(2);
            genRad = 5;
            sandOnly = true;
            genRadP = 10;
            genPos = 15;
            break;
          case 2:
            genCount = 5 + random.nextInt(5);
            genRad = 5;
            sandOnly = true;
            genRadP = 5;
            genPos = 10;
            break;
          case 3:
            genCount = 1 + random.nextInt(2);
            genRad = 5;
            sandOnly = true;
            genRadP = 5;
            genPos = 20;
            break;
          case 4:
            genCount = 5 + random.nextInt(10);
            genRad = 5;
            sandOnly = true;
            genRadP = 5;
            genPos = 4;
            break;
          case 5:
            genCount = 5 + random.nextInt(10);
            genRad = 5;
            sandOnly = true;
            genRadP = 5;
            genPos = 20;
            break;
          case 6:
            genCount = 1 + random.nextInt(2);
            genRad = 5;
            sandOnly = true;
            genRadP = 6;
            genPos = 15;
            break;
          case 0:
            genCount = 1 + random.nextInt(2);
            genRad = 5;
            sandOnly = true;
            genRadP = 10;
            genPos = 15;
            break;
        } 
        if (random.nextInt(genPos) == 0)
          for (int i = 0; i < genCount; i++) {
            firstBlockXCoord = chunkX + random.nextInt(16);
            firstBlockYCoord = 96 + dHeight;
            firstBlockZCoord = chunkZ + random.nextInt(16);
            rad = genRad + random.nextInt(genRadP);
            int countBlock = 0;
            canGen = false;
            while (firstBlockYCoord > 60 + dHeight) {
              if (validSandMaterial(sandOnly, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  break;
                } 
                countBlock++;
              } 
              if (countBlock > 10)
                break; 
              firstBlockYCoord--;
            } 
            if (canGen && 
              genIsFlat(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
              genIsFlat(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
              generateMire(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.QuicksandBlock);
              blockGen = true;
            } 
          }  
      } 
    } 
    if (MFQM.GenJQS && !blockGen) {
      int jngT = getJungleType(biomeId);
      if ((jngT != 0 && this.T_BoP) || (biomegenbase instanceof net.minecraft.world.biome.BiomeGenJungle && !this.T_BoP)) {
        boolean canGen = false;
        int genCount = 0;
        int genPos = 0;
        int genRad = 0;
        int genRadP = 0;
        int rad = 0;
        switch (jngT) {
          case 1:
            genCount = 5 + random.nextInt(10);
            genRad = 5;
            genRadP = 6;
            genPos = 7;
            break;
          case 2:
            genCount = 1 + random.nextInt(2);
            genRad = 6;
            genRadP = 6;
            genPos = 15;
            break;
          case 3:
            genCount = 5 + random.nextInt(10);
            genRad = 6;
            genRadP = 6;
            genPos = 10;
            break;
          case 0:
            genCount = 5 + random.nextInt(7);
            genRad = 6;
            genRadP = 6;
            genPos = 10;
            break;
        } 
        if (random.nextInt(genPos) == 0)
          for (int i = 0; i < genCount; i++) {
            firstBlockXCoord = chunkX + random.nextInt(16);
            firstBlockYCoord = 96 + dHeight;
            firstBlockZCoord = chunkZ + random.nextInt(16);
            rad = genRad + random.nextInt(genRadP);
            int countBlock = 0;
            canGen = false;
            while (firstBlockYCoord > 60 + dHeight) {
              if (validGroundMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
                !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  break;
                } 
                countBlock++;
              } 
              if (countBlock > 10)
                break; 
              firstBlockYCoord--;
            } 
            if (canGen && 
              genIsFlat(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
              genIsFlat(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
              generateMireD(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.JungleQuicksandBlock, true);
              generateLeavesAround(firstBlockXCoord, firstBlockYCoord + 3, firstBlockZCoord, rad + 3, world, random);
              blockGen = true;
            } 
          }  
      } 
    } 
    if (MFQM.GenGP && !blockGen) {
      int hT = getHillsType(biomeId);
      boolean exhill = false;
      if ((hT != 0 && this.T_BoP) || (biomegenbase instanceof net.minecraft.world.biome.BiomeGenHills && !this.T_BoP))
        exhill = true; 
      if (exhill) {
        boolean canGen = false;
        boolean test = false;
        int genCount = 50 + random.nextInt(50);
        int genPos = 52;
        int genRad = 10;
        int genRadP = 15;
        int realGen = 5 + random.nextInt(10);
        int rad = 0;
        int depth = 0;
        int flat = 1;
        if (random.nextInt(genPos) == 0)
          for (int i = 0; i < genCount && realGen > 0; i++) {
            firstBlockXCoord = chunkX + random.nextInt(24) - 12;
            firstBlockZCoord = chunkZ + random.nextInt(24) - 12;
            rad = genRad + random.nextInt(genRadP);
            if (depth < 1)
              depth = (int)Math.floor(rad); 
            if (!test) {
              firstBlockYCoord = 100 + dHeight;
              int countBlock = 0;
              canGen = false;
              while (firstBlockYCoord > 68 + dHeight) {
                if (validCaveMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world))
                  if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) != MFQM.SoftGravelBlock) {
                    if (!validWaterMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                      if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                        canGen = true;
                        break;
                      } 
                      countBlock++;
                    } else {
                      canGen = false;
                      realGen = 0;
                      break;
                    } 
                  } else {
                    canGen = false;
                    realGen = 0;
                    break;
                  }  
                if (countBlock > 2)
                  break; 
                firstBlockYCoord--;
              } 
            } else {
              canGen = true;
            } 
            if (canGen && (
              test || genIsFlatSolid(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad * 2, world))) {
              test = true;
              generateGravelPit(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, depth);
              realGen--;
              blockGen = true;
            } 
          }  
      } 
    } 
    if (MFQM.GenMorass && !blockGen) {
      int BogP = 0;
      int genCount = 0;
      int trueGenCount = 0;
      int genRad = 0;
      int trueGenRad = 0;
      float dynamicGenRad = 0.0F;
      int genRadP = 0;
      int swD = getPeatBogType(biomeId);
      boolean swGB = getPeatBogGenBirch(biomeId);
      int GPX = 0;
      int GPY = 0;
      int GPZ = 0;
      int BGPX = 0;
      int BGPY = 62 + dHeight;
      int BGPZ = 0;
      int BRGEN = 0;
      if ((swD != 0 && this.T_BoP) || ((biomegenbase instanceof net.minecraft.world.biome.BiomeGenPlains || biomegenbase instanceof net.minecraft.world.biome.BiomeGenForest) && !this.T_BoP)) {
        switch (swD) {
          case 3:
            trueGenCount = random.nextInt(250) + 50;
            BogP = 100 + trueGenCount / 10;
            genCount = random.nextInt(30) + 50;
            genRad = 10;
            genRadP = 10;
            trueGenRad = trueGenCount / 2;
            break;
          case 2:
            trueGenCount = random.nextInt(250) + 100;
            BogP = 10 + trueGenCount / 10;
            genCount = random.nextInt(30) + 50;
            genRad = 10;
            genRadP = 10;
            trueGenRad = trueGenCount / 2;
            break;
          case 1:
            trueGenCount = random.nextInt(250) + 50;
            BogP = 85 + trueGenCount / 10;
            genCount = random.nextInt(30) + 50;
            genRad = 10;
            genRadP = 10;
            trueGenRad = trueGenCount / 2;
            break;
          default:
            trueGenCount = random.nextInt(250) + 50;
            BogP = 50 + trueGenCount / 10;
            genCount = random.nextInt(30) + 50;
            genRad = 10;
            genRadP = 10;
            trueGenRad = trueGenCount / 2;
            break;
        } 
        if (random.nextInt(BogP) == 0)
          for (int i = 0; i < genCount && trueGenCount > 0; i++) {
            if (BRGEN == 0) {
              GPX = chunkX + random.nextInt(16);
              GPZ = chunkZ + random.nextInt(16);
              GPY = 63 + dHeight;
            } else {
              float rg = world.rand.nextFloat() * 360.0F;
              float imp = world.rand.nextFloat() * dynamicGenRad;
              rg = (float)Math.toRadians(rg);
              GPX = BGPX + (int)Math.floor(Math.sin(rg) * imp);
              GPY = BGPY;
              GPZ = BGPZ + (int)Math.floor(Math.cos(rg) * imp);
            } 
            firstBlockXCoord = GPX;
            firstBlockYCoord = GPY;
            firstBlockZCoord = GPZ;
            int rad = genRad + random.nextInt(genRadP);
            int genType = 0;
            boolean canGen = false;
            if (!canGen) {
              int countBlock = 0;
              while (firstBlockYCoord > BGPY - 1) {
                if (validWaterMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
                  validSolidMaterial(firstBlockXCoord, firstBlockYCoord - 2, firstBlockZCoord, world) && 
                  isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  genType = 1;
                  break;
                } 
                if (countBlock > 10)
                  break; 
                firstBlockYCoord--;
              } 
            } 
            if (!canGen) {
              firstBlockYCoord = GPY;
              int countBlock = 0;
              while (firstBlockYCoord > BGPY - 1) {
                if (validGroundMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) || world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) == MFQM.MorassBlock) {
                  if (!validWaterMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world) && isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                    canGen = true;
                    genType = 0;
                    break;
                  } 
                  countBlock++;
                } 
                if (countBlock > 10)
                  break; 
                firstBlockYCoord--;
              } 
            } 
            if (canGen) {
              if (genType == 0) {
                if (BRGEN == 0)
                  for (int o = 0; o < 3; o++) {
                    Vec3 XCOR = getXcor(world, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, trueGenRad);
                    Vec3 ZCOR = getZcor(world, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, trueGenRad);
                    int xCor = (int)Math.floor(XCOR.xCoord);
                    int zCor = (int)Math.floor(ZCOR.xCoord);
                    firstBlockXCoord += xCor;
                    firstBlockZCoord += zCor;
                    trueGenRad = Math.min(trueGenRad, Math.max((int)Math.floor(XCOR.yCoord * 1.75D), (int)Math.floor(ZCOR.yCoord * 1.75D)));
                  }  
                if (genIsFlatMorass(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
                  genIsFlatMorass(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord, rad, world))
                  if (BRGEN == 0) {
                    BGPX = firstBlockXCoord;
                    BGPY = firstBlockYCoord;
                    BGPZ = firstBlockZCoord;
                    BRGEN = 1;
                    dynamicGenRad = rad;
                    genCount = trueGenRad * 5;
                  } else {
                    float disToC = Math.max((float)Math.sqrt(Math.pow((firstBlockXCoord - BGPX), 2.0D) + Math.pow((firstBlockZCoord - BGPZ), 2.0D)), 1.0F);
                    if (random.nextInt(10) == 0) {
                      if (random.nextInt(25) == 0) {
                        generateWater(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, Math.min(rad, 7), world, random, 1);
                      } else {
                        generateWater(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, Math.min(rad, 7), world, random, 0);
                      } 
                    } else {
                      generateMorass(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, trueGenRad / disToC, 1);
                      if (trueGenRad > 25) {
                        biomegenbase = world.getBiomeGenForCoords(firstBlockXCoord, firstBlockZCoord);
                        if ((!(biomegenbase instanceof net.minecraft.world.biome.BiomeGenForest) || swGB) && (
                          trueGenRad / disToC) > 0.5D && 
                          random.nextInt(trueGenRad) == 0)
                          (new WorldGenTrees(true, 5 + random.nextInt(1), 2, 2, false)).generate(world, random, firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord); 
                      } 
                    } 
                    dynamicGenRad = Math.max(disToC, dynamicGenRad);
                    trueGenCount--;
                    blockGen = true;
                  }  
              } 
              if (genType == 1) {
                if (BRGEN == 0)
                  for (int o = 0; o < 3; o++) {
                    Vec3 XCOR = getXcor(world, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, trueGenRad);
                    Vec3 ZCOR = getZcor(world, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, trueGenRad);
                    int xCor = (int)Math.floor(XCOR.xCoord);
                    int zCor = (int)Math.floor(ZCOR.xCoord);
                    firstBlockXCoord += xCor;
                    firstBlockZCoord += zCor;
                    trueGenRad = Math.min(trueGenRad, Math.max((int)Math.floor(XCOR.yCoord * 1.75D), (int)Math.floor(ZCOR.yCoord * 1.75D)));
                  }  
                if (genIsFlatSolid(firstBlockXCoord, firstBlockYCoord - 2, firstBlockZCoord, rad, world))
                  if (BRGEN == 0) {
                    BGPX = firstBlockXCoord;
                    BGPY = firstBlockYCoord;
                    BGPZ = firstBlockZCoord;
                    BRGEN = 1;
                    dynamicGenRad = rad;
                    genCount = trueGenRad * 5;
                  } else {
                    float disToC = Math.max((float)Math.sqrt(Math.pow((firstBlockXCoord - BGPX), 2.0D) + Math.pow((firstBlockZCoord - BGPZ), 2.0D)), 1.0F);
                    generateMorass(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, trueGenRad / disToC, 1);
                    if (trueGenRad > 25) {
                      biomegenbase = world.getBiomeGenForCoords(firstBlockXCoord, firstBlockZCoord);
                      if ((!(biomegenbase instanceof net.minecraft.world.biome.BiomeGenForest) || swGB) && (
                        trueGenRad / disToC) > 0.5D && 
                        random.nextInt(trueGenRad) == 0)
                        (new WorldGenTrees(true, 5 + random.nextInt(1), 2, 2, false)).generate(world, random, firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord); 
                    } 
                    dynamicGenRad = Math.max(disToC, dynamicGenRad);
                    trueGenCount--;
                    blockGen = true;
                  }  
              } 
            } 
          }  
      } 
    } 
    if (MFQM.GenMoor && 
      this.T_BoP && biomeId == MFQM.SwampBiomes[7]) {
      int MoorP = 2;
      int genCount = random.nextInt(30) + 50;
      int genRad = 16;
      int genRadP = 10;
      int trueGenCount = random.nextInt(30) + 20;
      int BGPX = 0;
      int BGPY = 0;
      int BGPZ = 0;
      int rad = 32 + random.nextInt(10);
      firstBlockXCoord = chunkX + 4 + random.nextInt(4);
      firstBlockYCoord = 62 + dHeight;
      firstBlockZCoord = chunkZ + 4 + random.nextInt(4);
      generateFlatMud(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord, rad, world, random);
      if (random.nextInt(MoorP) == 0)
        for (int i = 0; i < genCount && trueGenCount > 0; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 62 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          boolean canGen = false;
          if (!canGen && (
            random.nextInt(10) == 0 || !validSolidMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) || world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) == MFQM.MorassBlock || world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) == MFQM.MudBlock) && 
            isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world))
            canGen = true; 
          if (canGen) {
            if (BGPX == 0) {
              BGPX = firstBlockXCoord;
              BGPY = firstBlockYCoord;
              BGPZ = firstBlockZCoord;
            } 
            float disToC = Math.max((float)Math.sqrt(Math.pow((firstBlockXCoord - BGPX), 2.0D) + Math.pow((firstBlockZCoord - BGPZ), 2.0D)), 1.0F);
            if (random.nextInt(100) == 0) {
              generateWater(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, Math.min(rad, 7), world, random, 1);
            } else {
              generateMorass(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, 18.0F / disToC, 0);
              if ((16.0F / disToC) > 0.5D && 
                random.nextInt(150) == 0)
                (new WorldGenTrees(true, 5 + random.nextInt(1), 2, 2, false)).generate(world, random, firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord); 
            } 
            trueGenCount--;
            blockGen = true;
          } 
        }  
    } 
    if (MFQM.GenTar && !blockGen) {
      int trType = 0;
      int tarP1 = 0;
      int tarP2 = 0;
      int tarPU = 0;
      trType = getTarCount(biomeId);
      int trrType = trType;
      if (this.T_BoP && 
        trType == 0)
        trType = isTarBiomeIsNear(chunkX + 8, 64, chunkZ + 8, world); 
      switch (trType) {
        case 1:
          tarP1 = 5;
          tarP2 = 5;
          tarPU = 5;
          break;
        case 2:
          tarP1 = 5;
          tarP2 = 5;
          tarPU = 5;
          break;
        case 3:
          tarP1 = 10;
          tarP2 = 8;
          tarPU = 10;
          break;
        default:
          tarP1 = 35;
          tarP2 = 15;
          tarPU = 10;
          break;
      } 
      if (random.nextInt(tarP1) == 0 && 
        random.nextInt(tarP2) == 0)
        if (random.nextInt(3) == 0 && trrType == 1) {
          int genCount = random.nextInt(30) + 50;
          int genRad = 5;
          int genRadP = 15;
          int rad = 5;
          boolean canGen = false;
          for (int i = 0; i < genCount; i++) {
            firstBlockXCoord = chunkX + random.nextInt(32) - 16;
            firstBlockYCoord = 96 + dHeight;
            firstBlockZCoord = chunkZ + random.nextInt(32) - 16;
            rad = genRad + random.nextInt(genRadP);
            int countBlock = 0;
            int upper = 0;
            canGen = false;
            while (firstBlockYCoord > 60 + dHeight && (
              validTarMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) || (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord).getMaterial() != Material.water && world
              .getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord).getMaterial() != Material.lava))) {
              if (validTarMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) == MFQM.TarBlock) {
                    upper = 0;
                    break;
                  } 
                  upper = 1;
                  break;
                } 
                countBlock++;
              } 
              if (countBlock > 5)
                break; 
              firstBlockYCoord--;
            } 
            if (canGen && 
              genIsFlatSolid(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord, rad, world) && 
              genIsFlatTar(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
              genIsFlatTar(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
              ClearArea(firstBlockXCoord, firstBlockYCoord + 1 - upper, firstBlockZCoord, (int)Math.floor(rad * 1.05D), world, random);
              generateTar(firstBlockXCoord, firstBlockYCoord - upper, firstBlockZCoord, rad, world, random);
              blockGen = true;
            } 
          } 
        } else {
          int var5 = chunkX + random.nextInt(16) + 8;
          int var6 = 60 + random.nextInt(21);
          int var7 = chunkZ + random.nextInt(16) + 8;
          (new CustomWorldGenLakes(MFQM.TarBlock)).generate(world, random, var5, var6, var7, 1);
          blockGen = true;
        }  
      if (random.nextInt(tarP1) == 0 && 
        random.nextInt(tarPU) == 0) {
        int var5 = chunkX + random.nextInt(16) + 8;
        int var6 = random.nextInt(64);
        int var7 = chunkZ + random.nextInt(16) + 8;
        (new CustomWorldGenLakes(MFQM.TarBlock)).generate(world, random, var5, var6, var7, 1);
        blockGen = true;
      } 
    } 
    if (MFQM.GenSQSF && !blockGen) {
      int sqtBop = getForestTypeBOP(biomeId);
      int sqtVan = getForestTypeVan(biomeId);
      if ((sqtBop != 0 && this.T_BoP) || (sqtVan != 0 && !this.T_BoP)) {
        boolean canGen = false;
        int genCount = 0;
        int genPos = 0;
        int genRad = 0;
        int genRadP = 0;
        int rad = 0;
        genCount = 5 + random.nextInt(10);
        genRad = 5;
        genRadP = 6;
        genPos = 155;
        if (random.nextInt(genPos) == 0)
          for (int i = 0; i < genCount; i++) {
            firstBlockXCoord = chunkX + random.nextInt(16);
            firstBlockYCoord = 96 + dHeight;
            firstBlockZCoord = chunkZ + random.nextInt(16);
            rad = genRad + random.nextInt(genRadP);
            int countBlock = 0;
            canGen = false;
            while (firstBlockYCoord > 60 + dHeight) {
              if (validGroundMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
                !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  break;
                } 
                countBlock++;
              } 
              if (countBlock > 10)
                break; 
              firstBlockYCoord--;
            } 
            if (canGen && 
              genIsFlat(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
              genIsFlat(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
              generateSoftQuicksandForest(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.SoftQuicksandBlock);
              blockGen = true;
            } 
          }  
      } 
    } 
    if (MFQM.GenSQS && !blockGen) {
      boolean canGen = false;
      boolean genPosFounded = false;
      int genCount = 0;
      int genRealCount = 0;
      int genPos = 0;
      int genRad = 0;
      int genRadP = 0;
      int rad = 0;
      int FX = 0;
      int FZ = 0;
      int FY = 60;
      int jngT = getJungleType(biomeId);
      if ((jngT != 0 && jngT != 2 && this.T_BoP) || (biomegenbase instanceof net.minecraft.world.biome.BiomeGenJungle && !this.T_BoP)) {
        genCount = 20 + random.nextInt(10);
        genRad = 10;
        genRadP = 5;
        int genSand = 0;
        genRealCount = 1 + random.nextInt(29);
        int radOfGen = (int)Math.floor(30.0D * Math.min(1.0D, genRealCount / 20.0D));
        genPos = 1 + genRealCount * 4;
        int T_genRealCount = genRealCount * 4;
        if (random.nextInt(genPos) == 0) {
          for (int i = 0; i < genCount && genRealCount > 0; i++) {
            if (!genPosFounded) {
              firstBlockXCoord = chunkX + random.nextInt(32) - 16;
              firstBlockYCoord = 96 + dHeight;
              firstBlockZCoord = chunkZ + random.nextInt(32) - 16;
              rad = genRad + random.nextInt(genRadP);
            } else {
              firstBlockXCoord = FX + random.nextInt(radOfGen) - radOfGen / 2;
              firstBlockYCoord = 96 + dHeight;
              firstBlockZCoord = FZ + random.nextInt(radOfGen) - radOfGen / 2;
              rad = genRad / 2 + random.nextInt(7) + radOfGen / 6;
            } 
            int countBlock = 0;
            int upper = 0;
            canGen = false;
            while (firstBlockYCoord > 60 + dHeight) {
              if (validSoftQuicksandMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
                !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) == MFQM.SoftQuicksandBlock) {
                    upper = -1;
                    break;
                  } 
                  upper = -1;
                  break;
                } 
                countBlock++;
              } 
              firstBlockYCoord--;
            } 
            if (canGen && 
              genIsFlatSoftQuicksand(firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord, rad, world) && 
              genIsFlatSoftQuicksand(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
              genIsFlatSoftQuicksand(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
              if (!genPosFounded) {
                genPosFounded = true;
                FX = firstBlockXCoord;
                FZ = firstBlockZCoord;
                FY = firstBlockYCoord;
                genCount = 750;
              } 
              int TgenSand = genSand;
              genRealCount--;
              generateCliffs(firstBlockXCoord, FY, firstBlockZCoord, (int)Math.floor(rad * 1.5D), world, random);
              generateSoftQuicksand(firstBlockXCoord, FY - upper - 1, firstBlockZCoord, rad, world, random, TgenSand);
              blockGen = true;
            } 
          } 
          int ccount = genSand * 10 + T_genRealCount + random.nextInt(T_genRealCount / 2);
          int rcount = 0;
          for (int j = 0; rcount < ccount; j++) {
            firstBlockXCoord = FX + random.nextInt(radOfGen) - radOfGen / 2;
            firstBlockYCoord = FY;
            firstBlockZCoord = FZ + random.nextInt(radOfGen) - radOfGen / 2;
            float disToC = Math.max((float)Math.sqrt(Math.pow((firstBlockXCoord - FX), 2.0D) + Math.pow((firstBlockZCoord - FZ), 2.0D)), 1.0F);
            int cof_d = Math.max(1, (int)Math.floor(Math.pow((16.0F / disToC), 1.5D)));
            int rrad = 1;
            if (random.nextInt(1 * cof_d) == 0)
              rrad = 2 + random.nextInt(1); 
            if (random.nextInt(2 * cof_d) == 0)
              rrad = 3 + random.nextInt(2); 
            if (random.nextInt(3 * cof_d) == 0)
              rrad = 4 + random.nextInt(3); 
            int qy = 1;
            if (random.nextInt(5) == 0)
              qy = 0; 
            if (rrad > 1)
              generateRock(firstBlockXCoord, firstBlockYCoord - qy - random.nextInt(Math.max(1, (int)Math.floor((rrad / 2)))), firstBlockZCoord, rrad, world, random); 
            rcount++;
          } 
        } 
      } 
    } 
    if (MFQM.GenMoss) {
      int swD = getSwampDifficult(biomeId);
      int swType = getSwampType(biomeId);
      int MossP = 0;
      int genCount = 0;
      int genRad = 0;
      int genRadP = 0;
      if ((swType == 2 && this.T_BoP) || (biomegenbase instanceof net.minecraft.world.biome.BiomeGenSwamp && !this.T_BoP)) {
        switch (swD) {
          case 1:
            MossP = 60;
            genCount = random.nextInt(5) + 10;
            genRad = 10;
            genRadP = 10;
            break;
          case 2:
            MossP = 31;
            genCount = random.nextInt(5) + 10;
            genRad = 10;
            genRadP = 10;
            break;
          default:
            MossP = 31;
            genCount = random.nextInt(5) + 10;
            genRad = 10;
            genRadP = 10;
            break;
        } 
        if (biomeId == MFQM.SwampBiomes[0])
          MossP = 32; 
        if (random.nextInt(MossP) == 0)
          for (int i = 0; i < genCount; i++) {
            firstBlockXCoord = chunkX + random.nextInt(16);
            firstBlockYCoord = 67 + dHeight;
            firstBlockZCoord = chunkZ + random.nextInt(16);
            int rad = genRad + random.nextInt(genRadP);
            int genType = 0;
            boolean canGen = false;
            int countBlock = 0;
            while (firstBlockYCoord > 60 + dHeight) {
              if (validLiqMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
                !validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  canGen = true;
                  break;
                } 
                countBlock++;
              } 
              if (countBlock > 10)
                break; 
              firstBlockYCoord--;
            } 
            if (canGen)
              if (validLiqMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                if (isGroundMossNear(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world))
                  generateMoss(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world, random); 
              } else {
                generateMoss(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world, random);
              }  
          }  
      } 
    } 
    if (MFQM.GenBog && !blockGen) {
      int swD = getSwampDifficult(biomeId);
      int swType = getSwampType(biomeId);
      int BogP = 0;
      int genCount = 0;
      int genRad = 0;
      int genRadP = 0;
      if (((((swD == 1 || biomeId == MFQM.SwampBiomes[0] || biomeId == MFQM.SwampBiomes[11]) ? 1 : 0) & ((swType != 1) ? 1 : 0)) != 0 && this.T_BoP) || (biomegenbase instanceof net.minecraft.world.biome.BiomeGenSwamp && !this.T_BoP)) {
        switch (swD) {
          case 1:
            BogP = 125;
            genCount = 0;
            genRad = 10;
            genRadP = 20;
            break;
          case 2:
            BogP = 75;
            genCount = random.nextInt(30) + 50;
            genRad = 10;
            genRadP = 20;
            break;
          default:
            BogP = 75;
            genCount = random.nextInt(30) + 50;
            genRad = 10;
            genRadP = 20;
            break;
        } 
        if (this.T_BoP && biomeId == MFQM.SwampBiomes[0])
          BogP = 12; 
        if (random.nextInt(BogP) == 0)
          for (int i = 0; i < genCount; i++) {
            firstBlockXCoord = chunkX + random.nextInt(64) - 32;
            firstBlockYCoord = 62 + dHeight;
            firstBlockZCoord = chunkZ + random.nextInt(64) - 32;
            int rad = genRad + random.nextInt(genRadP);
            int genType = 0;
            boolean canGen = false;
            if (!canGen && biomeId != MFQM.SwampBiomes[9] && 
              validWaterMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) && 
              isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
              canGen = true;
              genType = 1;
            } 
            if (!canGen) {
              int countBlock = 0;
              firstBlockYCoord = 96 + dHeight;
              while (firstBlockYCoord > 60 + dHeight) {
                if (validGroundMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world) || world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) == MFQM.BogBlock) {
                  if (!validWaterMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world) && isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                    canGen = true;
                    genType = 0;
                    break;
                  } 
                  countBlock++;
                } 
                if (countBlock > 10)
                  break; 
                firstBlockYCoord--;
              } 
            } 
            if (canGen)
              if (genType == 0) {
                if (isWaterNear(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world)) {
                  generateBog(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.water);
                  blockGen = true;
                  if (random.nextInt(2) == 0)
                    generateMoorGrass(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world, random, MFQM.BogBlock); 
                } else if (genIsFlatBog(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
                  genIsFlatBog(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
                  generateBog(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake);
                  blockGen = true;
                  if (random.nextInt(2) == 0)
                    generateMoorGrass(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world, random, MFQM.BogBlock); 
                } 
              } else if (biomeId == MFQM.SwampBiomes[0] || isGroundBogNear(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world)) {
                generateBog(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.water);
                blockGen = true;
                if (random.nextInt(5) == 0)
                  generateMoorGrass(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world, random, MFQM.BogBlock); 
              }  
          }  
      } 
    } 
    if (MFQM.GenLiqMire && !blockGen) {
      int swD = getSwampDifficult(biomeId);
      int swType = getSwampType(biomeId);
      if ((swD != 0 && this.T_BoP) || (biomegenbase instanceof net.minecraft.world.biome.BiomeGenSwamp && !this.T_BoP)) {
        boolean canGen = false;
        int genType = 0;
        int MireP1 = 0;
        int genCount = 0;
        int genPos = 0;
        int genRad = 0;
        int genRadP = 0;
        int rad = 0;
        if (swType != 1) {
          switch (swD) {
            case 1:
              MireP1 = 32;
              break;
            case 2:
              MireP1 = 32;
              break;
            case 3:
              MireP1 = 32;
              break;
            default:
              MireP1 = 75;
              break;
          } 
          genCount = 5 + random.nextInt(10);
          genRad = 6;
          genRadP = 12;
          if (random.nextInt(MireP1) == 0)
            for (int i = 0; i < genCount; i++) {
              firstBlockXCoord = chunkX - 8 + random.nextInt(24);
              firstBlockYCoord = 96 + dHeight;
              firstBlockZCoord = chunkZ - 8 + random.nextInt(24);
              rad = genRad + random.nextInt(genRadP);
              int countBlock = 0;
              canGen = false;
              while (firstBlockYCoord > 60 + dHeight) {
                if (!validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world) && 
                  validGroundMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                    canGen = true;
                    break;
                  } 
                  countBlock++;
                } 
                if (countBlock > 10)
                  break; 
                firstBlockYCoord--;
              } 
              if (canGen && 
                genIsFlatOrLMire(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
                genIsFlatOrLMire(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
                generateLiquidMire(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, 1);
                blockGen = true;
              } 
            }  
        } else if (biomeId != MFQM.SwampBiomes[7]) {
          genCount = 10 + random.nextInt(10);
          genRad = 10;
          genRadP = 2;
          genPos = 10;
          if (random.nextInt(genPos) == 0)
            for (int i = 0; i < genCount; i++) {
              firstBlockXCoord = chunkX + random.nextInt(16);
              firstBlockYCoord = 96 + dHeight;
              firstBlockZCoord = chunkZ + random.nextInt(16);
              rad = (int)Math.floor(genRad * 1.5D) + random.nextInt(genRadP);
              int countBlock = 0;
              canGen = false;
              while (firstBlockYCoord > 60 + dHeight) {
                if (validWaterMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                    canGen = true;
                    break;
                  } 
                  countBlock++;
                } 
                if (countBlock > 10)
                  break; 
                firstBlockYCoord--;
              } 
              if (canGen && 
                isGroundNear(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world)) {
                generateLiquidMire(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.water, 0);
                blockGen = true;
              } 
            }  
        } 
      } 
    } 
    if ((MFQM.GenBClay || MFQM.GenHClay) && !blockGen) {
      boolean canGen = false;
      int genCount = 0;
      int genPos = 0;
      int genRad = 0;
      int genRadP = 0;
      int rad = 0;
      int Ctype = 0;
      genPos = 20;
      genCount = 60 + random.nextInt(10);
      genRad = 10;
      genRadP = 7;
      boolean DSRT = false;
      boolean exhill = false;
      if (!MFQM.GenBClay)
        genPos = 0; 
      int hT = getHillsType(biomeId);
      if ((hT != 0 && this.T_BoP) || (biomegenbase instanceof net.minecraft.world.biome.BiomeGenHills && !this.T_BoP)) {
        exhill = true;
        genPos = 10;
        if (!MFQM.GenMClay)
          genPos = 0; 
      } 
      int dT = getDesertType(biomeId);
      if ((dT != 0 && this.T_BoP) || (biomegenbase instanceof net.minecraft.world.biome.BiomeGenDesert && !this.T_BoP)) {
        DSRT = true;
        genPos = 5;
        if (!MFQM.GenHClay)
          genPos = 0; 
      } 
      if (genPos != 0 && 
        random.nextInt(genPos) == 0)
        for (int i = 0; i < genCount; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96 + dHeight;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock = 0;
          canGen = false;
          while (firstBlockYCoord > 60 + dHeight) {
            if (validSnowMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
              canGen = false;
              break;
            } 
            if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) == Blocks.ice) {
              canGen = false;
              break;
            } 
            if (validWaterMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                Ctype = 0;
                break;
              } 
              countBlock++;
            } 
            if (world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) == MFQM.BrownClayBlock) {
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                Ctype = 1;
                break;
              } 
              countBlock++;
            } 
            if (countBlock > 20)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen) {
            if (firstBlockYCoord < 68 + dHeight)
              exhill = false; 
            if (Ctype == 0) {
              if (isItLake2(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad * 2, world, exhill)) {
                if (!DSRT) {
                  if (exhill) {
                    generateMineralClay(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad * 2, world, random);
                  } else {
                    generateBrownClay(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad * 2, world, random);
                  } 
                } else {
                  generateClay(0, firstBlockXCoord, firstBlockYCoord - 1, firstBlockZCoord, rad * 2, world, random, 1);
                  generateClearWater(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad * 2 + rad / 2, world, random);
                } 
                blockGen = true;
              } 
            } else {
              if (exhill) {
                generateMineralClay(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad * 2, world, random);
              } else {
                generateBrownClay(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad * 2, world, random);
              } 
              blockGen = true;
            } 
          } 
        }  
    } 
    if ((MFQM.GenMire || MFQM.GenMud) && !blockGen) {
      int swD = getSwampDifficult(biomeId);
      int swType = getSwampType(biomeId);
      int flag1 = 0;
      if ((swD != 0 && this.T_BoP) || ((biomegenbase instanceof net.minecraft.world.biome.BiomeGenSwamp || flag1 != 0) && !this.T_BoP)) {
        boolean canGen = false;
        int genType = 0;
        int genCount = 0;
        int genPos = 0;
        int genRad = 0;
        int genRadP = 0;
        int rad = 0;
        if (flag1 == 1) {
          swD = 2;
          swType = 1;
        } 
        switch (swD) {
          case 0:
            genCount = 1 + random.nextInt(1);
            genRad = 6;
            genRadP = 9;
            genPos = 4;
            break;
          case 1:
            genCount = 5 + random.nextInt(10);
            genRad = 6;
            genRadP = 12;
            genPos = 999;
            break;
          case 2:
            genCount = 3 + random.nextInt(5);
            genRad = 6;
            genRadP = 9;
            genPos = 4;
            break;
          case 3:
            genCount = 1 + random.nextInt(1);
            genRad = 6;
            genRadP = 7;
            genPos = 6;
            break;
        } 
        if (flag1 == 1) {
          genPos = 3;
          genCount *= 2;
        } 
        int jur = 0;
        boolean try_gen_mud = false;
        boolean gen_mud = false;
        if (random.nextInt(genPos / 3 + 1) == 0 || genPos == 999) {
          gen_mud = true;
          if (random.nextInt(genPos + 1) == 0)
            try_gen_mud = true; 
        } 
        if (random.nextInt(genPos) == 0 || genPos == 999) {
          int i;
          switch (swType) {
            case 0:
              if (MFQM.GenMire || MFQM.GenMud) {
                int j;
                for (j = 0; j < genCount; j++) {
                  firstBlockXCoord = chunkX + random.nextInt(16);
                  firstBlockYCoord = 96 + dHeight;
                  firstBlockZCoord = chunkZ + random.nextInt(16);
                  rad = genRad + random.nextInt(genRadP);
                  int countBlock = 0;
                  canGen = false;
                  while (firstBlockYCoord > 60 + dHeight) {
                    if (validGroundOrLMireMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                      if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                        canGen = true;
                        break;
                      } 
                      countBlock++;
                    } 
                    if (countBlock > 10)
                      break; 
                    firstBlockYCoord--;
                  } 
                  if (canGen && 
                    genIsFlat(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
                    genIsFlat(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
                    if (MFQM.GenMire)
                      generateMireD(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.MireBlock, false); 
                    try_gen_mud = true;
                    blockGen = true;
                  } 
                } 
                if (try_gen_mud && gen_mud && 
                  MFQM.GenMud) {
                  genCount = 30 + random.nextInt(20);
                  for (j = 0; j < genCount; j++) {
                    firstBlockXCoord = chunkX + random.nextInt(16);
                    firstBlockYCoord = 96 + dHeight;
                    firstBlockZCoord = chunkZ + random.nextInt(16);
                    rad = genRad * 3 + random.nextInt(genRadP);
                    int countBlock = 0;
                    canGen = false;
                    while (firstBlockYCoord > 55 + dHeight) {
                      if (validGroundOrLMireMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                        if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                          canGen = true;
                          break;
                        } 
                        countBlock++;
                      } 
                      if (countBlock > 10)
                        break; 
                      firstBlockYCoord--;
                    } 
                    if (canGen)
                      generateMud(firstBlockXCoord, firstBlockYCoord + 2, firstBlockZCoord, rad, world, random); 
                  } 
                } 
              } 
              break;
            case 1:
              try_gen_mud = false;
              if (biomeId == MFQM.SwampBiomes[7])
                genRad *= 2; 
              if (biomeId == MFQM.SwampBiomes[2])
                genCount *= 3; 
              for (i = 0; i < genCount; i++) {
                firstBlockXCoord = chunkX + random.nextInt(16);
                firstBlockYCoord = 96 + dHeight;
                firstBlockZCoord = chunkZ + random.nextInt(16);
                rad = (int)Math.floor(genRad * 1.5D) + random.nextInt(genRadP);
                int countBlock = 0;
                canGen = false;
                while (firstBlockYCoord > 60 + dHeight) {
                  if (validWaterMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                    if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                      canGen = true;
                      break;
                    } 
                    countBlock++;
                  } 
                  if (countBlock > 10)
                    break; 
                  firstBlockYCoord--;
                } 
                if (canGen && 
                  isGroundNear(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world)) {
                  try_gen_mud = true;
                  if (MFQM.GenMire)
                    generateMire(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.water, MFQM.MireBlock); 
                  blockGen = true;
                } 
              } 
              if (try_gen_mud && gen_mud && 
                MFQM.GenMud) {
                genCount = 30 + random.nextInt(20);
                for (i = 0; i < genCount; i++) {
                  firstBlockXCoord = chunkX + random.nextInt(16);
                  firstBlockYCoord = 96 + dHeight;
                  firstBlockZCoord = chunkZ + random.nextInt(16);
                  rad = genRad * 3 + random.nextInt(genRadP);
                  int countBlock = 0;
                  canGen = false;
                  while (firstBlockYCoord > 55 + dHeight) {
                    if (validGroundOrLMireMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                      if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                        canGen = true;
                        break;
                      } 
                      countBlock++;
                    } 
                    if (countBlock > 10)
                      break; 
                    firstBlockYCoord--;
                  } 
                  if (canGen)
                    generateMud(firstBlockXCoord, firstBlockYCoord + 2, firstBlockZCoord, rad, world, random); 
                } 
              } 
              break;
            case 2:
              if (MFQM.GenMire || MFQM.GenMud) {
                for (i = 0; i < genCount; i++) {
                  if (jur == 1) {
                    firstBlockXCoord = chunkX + random.nextInt(64) - 32;
                    firstBlockYCoord = 62 + dHeight;
                    firstBlockZCoord = chunkZ + random.nextInt(64) - 32;
                  } else {
                    firstBlockXCoord = chunkX + random.nextInt(16);
                    firstBlockYCoord = 96 + dHeight;
                    firstBlockZCoord = chunkZ + random.nextInt(16);
                  } 
                  rad = genRad + random.nextInt(genRadP);
                  int countBlock1 = 0;
                  int countBlock2 = 0;
                  genType = 0;
                  canGen = false;
                  while (firstBlockYCoord > 60 + dHeight) {
                    if (validWaterMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                      if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                        canGen = true;
                        genType = 1;
                        break;
                      } 
                      countBlock1++;
                    } 
                    if (jur == 0 && 
                      validGroundMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                      if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                        canGen = true;
                        genType = 0;
                        break;
                      } 
                      countBlock2++;
                    } 
                    if (countBlock1 > 10 || 
                      countBlock2 > 10)
                      break; 
                    firstBlockYCoord--;
                  } 
                  if (canGen)
                    if (genType == 0) {
                      if (MFQM.GenMire || MFQM.GenMud)
                        if (isWaterNear(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world)) {
                          if (MFQM.GenMire)
                            generateMire(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.water, MFQM.MireBlock); 
                          try_gen_mud = true;
                          blockGen = true;
                        } else if (genIsFlat(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world) && 
                          genIsFlat(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, rad, world)) {
                          if (MFQM.GenMire)
                            generateMireD(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.cake, MFQM.MireBlock, false); 
                          try_gen_mud = true;
                          blockGen = true;
                        }  
                    } else if ((MFQM.GenMire || jur == 1 || MFQM.GenMud) && 
                      isGroundNear(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world)) {
                      if (MFQM.GenMire)
                        generateMire(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, Material.water, MFQM.MireBlock); 
                      try_gen_mud = true;
                      blockGen = true;
                    }  
                } 
                if (try_gen_mud && gen_mud && 
                  MFQM.GenMud) {
                  genCount = 30 + random.nextInt(20);
                  for (i = 0; i < genCount; i++) {
                    firstBlockXCoord = chunkX + random.nextInt(16);
                    firstBlockYCoord = 96 + dHeight;
                    firstBlockZCoord = chunkZ + random.nextInt(16);
                    rad = genRad * 3 + random.nextInt(genRadP);
                    int countBlock = 0;
                    canGen = false;
                    while (firstBlockYCoord > 55 + dHeight) {
                      if (validGroundOrLMireMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                        if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                          canGen = true;
                          break;
                        } 
                        countBlock++;
                      } 
                      if (countBlock > 10)
                        break; 
                      firstBlockYCoord--;
                    } 
                    if (canGen)
                      generateMud(firstBlockXCoord, firstBlockYCoord + 2, firstBlockZCoord, rad, world, random); 
                  } 
                } 
              } 
              break;
          } 
        } 
      } 
    } 
    if (MFQM.GenHClay) {
      boolean canGen = false;
      int genCount = 0;
      int genPos = 0;
      int genRad = 0;
      int genRadP = 0;
      int rad = 0;
      genCount = 10 + random.nextInt(10);
      genRad = 6;
      genRadP = 14;
      genPos = 0;
      boolean canGenWater = false;
      boolean canGenGround = true;
      if (biomegenbase instanceof net.minecraft.world.biome.BiomeGenBeach)
        genPos = 0; 
      int dT = getDesertType(biomeId);
      if ((dT != 0 && this.T_BoP) || (biomegenbase instanceof net.minecraft.world.biome.BiomeGenDesert && !this.T_BoP))
        genPos = 0; 
      int jngT = getJungleType(biomeId);
      if ((jngT != 0 && this.T_BoP) || (biomegenbase instanceof net.minecraft.world.biome.BiomeGenJungle && !this.T_BoP)) {
        genPos = 2;
        if (biomegenbase instanceof net.minecraft.world.biome.BiomeGenJungle || jngT == 3) {
          genPos = 1;
          canGenGround = false;
        } 
        genCount *= 2;
        canGenWater = true;
        if (random.nextInt(1) == 0)
          canGenGround = false; 
      } 
      if (biomegenbase instanceof net.minecraft.world.biome.BiomeGenRiver) {
        genPos = 0;
        canGenGround = false;
        genCount *= 2;
        canGenWater = true;
        if (isJungleIsNear(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world))
          genPos = 999; 
      } 
      if (genPos > 0 && (
        genPos == 999 || random.nextInt(genPos) == 0))
        for (int i = 0; i < genCount && !blockGen; i++) {
          firstBlockXCoord = chunkX + random.nextInt(16);
          firstBlockYCoord = 96;
          firstBlockZCoord = chunkZ + random.nextInt(16);
          rad = genRad + random.nextInt(genRadP);
          int countBlock1 = 0;
          int countBlock2 = 0;
          int genType = 0;
          canGen = false;
          while (firstBlockYCoord > 60) {
            if (canGenWater && validWaterMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                canGen = true;
                genType = 1;
                if (biomegenbase instanceof net.minecraft.world.biome.BiomeGenRiver)
                  rad *= 3; 
                rad *= 2;
                rad = Math.min(rad, 32);
                break;
              } 
              countBlock1++;
            } 
            if (canGenGround && world.getBlock(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord) == Blocks.sand)
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                if (!validWaterMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                  canGen = true;
                  genType = 0;
                  break;
                } 
              } else {
                countBlock2++;
              }  
            if (countBlock1 > 20 || 
              countBlock2 > 20)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && (
            genType == 0 || isItLake(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world))) {
            int ccount = random.nextInt(2) + 1;
            if (genType == 1) {
              rad *= 2;
              ccount *= 2;
            } 
            generateClay(genType, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world, random, 0);
            rad = genRad + random.nextInt(genRadP);
            for (int j = 0; j < ccount; j++)
              generateClay(0, firstBlockXCoord + random.nextInt(rad * 2) - rad, firstBlockYCoord, firstBlockZCoord + random.nextInt(rad * 2) - rad, rad, world, random, 0); 
            blockGen = true;
          } 
        }  
    } 
    if (MFQM.GenHPClay) {
      boolean canGen = false;
      boolean canGenV = false;
      int genCount = 0;
      int genPos = 0;
      int genRad = 0;
      int genRadP = 0;
      int rad = 0;
      int radV = 0;
      genCount = 30 + random.nextInt(10);
      genRad = 6;
      genRadP = 6;
      genPos = 0;
      boolean canGenWater = false;
      boolean canGenGround = true;
      int jngT = getJungleType(biomeId);
      if ((jngT != 0 && this.T_BoP) || (biomegenbase instanceof net.minecraft.world.biome.BiomeGenJungle && !this.T_BoP)) {
        genPos = 20;
        if (biomegenbase instanceof net.minecraft.world.biome.BiomeGenJungle || jngT == 3)
          genPos = 20; 
      } 
      if (genPos > 0 && (
        genPos == 999 || random.nextInt(genPos) == 0)) {
        int XC = chunkX + random.nextInt(16);
        int YC = 68;
        int ZC = chunkZ + random.nextInt(16);
        int XCV = XC;
        int YCV = YC;
        int ZCV = ZC;
        int PXC = XC;
        int PYC = YC;
        int PZC = ZC;
        int PAC = 0;
        boolean TacktGen = false;
        boolean isSinking = false;
        for (int i = 0; i < genCount; i++) {
          TacktGen = false;
          firstBlockXCoord = XCV;
          firstBlockYCoord = YCV;
          firstBlockZCoord = ZCV;
          radV = genRad + random.nextInt(genRadP);
          rad = radV;
          int countBlock1 = 0;
          int countBlock2 = 0;
          canGen = false;
          while (firstBlockYCoord > 60) {
            if (validGroundClayMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world))
              if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                if (!validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                  canGen = true;
                  break;
                } 
              } else {
                countBlock2++;
              }  
            if (countBlock1 > 20 || 
              countBlock2 > 20)
              break; 
            firstBlockYCoord--;
          } 
          if (canGen && 
            genIsFlatClay(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, rad, world)) {
            isSinking = false;
            int notGen = 200;
            if (rad > 7) {
              if (isAboveFreeSpace(firstBlockXCoord - rad, firstBlockYCoord, firstBlockZCoord, world) && 
                isAboveFreeSpace(firstBlockXCoord + rad, firstBlockYCoord, firstBlockZCoord, world) && 
                isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord - rad, world) && 
                isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord + rad, world))
                notGen = 1; 
              if (random.nextInt(Math.max(1, firstBlockYCoord - 64) * 2 * notGen) == 0 && 
                MFQM.GenSClay) {
                biomegenbase = world.getBiomeGenForCoords(firstBlockXCoord, firstBlockZCoord);
                biomeId = biomegenbase.biomeID;
                jngT = getJungleType(biomeId);
                if ((jngT != 0 && this.T_BoP) || (biomegenbase instanceof net.minecraft.world.biome.BiomeGenJungle && !this.T_BoP))
                  isSinking = true; 
              } 
            } 
            if (isSinking || random.nextInt(Math.max(1, firstBlockYCoord - 64) * notGen) == 0) {
              generateOnlyClay(isSinking, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, (int)Math.floor((rad * 1.5F)), world, random);
              TacktGen = true;
              PXC = firstBlockXCoord;
              PYC = firstBlockYCoord + 2;
              PZC = firstBlockZCoord;
              if (PAC == 0) {
                genCount = 40 + random.nextInt(10);
                PAC = 1;
              } 
              blockGen = true;
            } 
          } 
          if (!TacktGen) {
            firstBlockXCoord = XC;
            firstBlockYCoord = YC;
            firstBlockZCoord = ZC;
            rad = 4 + random.nextInt(1);
            countBlock1 = 0;
            countBlock2 = 0;
            canGen = false;
            while (firstBlockYCoord > 60) {
              if (validGroundClayMaterial(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world))
                if (isAboveFreeSpace(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, world)) {
                  if (!validLiqMaterial(firstBlockXCoord, firstBlockYCoord + 1, firstBlockZCoord, world)) {
                    canGen = true;
                    break;
                  } 
                } else {
                  countBlock2++;
                }  
              if (countBlock1 > 20 || 
                countBlock2 > 20)
                break; 
              firstBlockYCoord--;
            } 
            if (canGen) {
              generateOnlyClay(false, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord, (int)Math.floor((rad * 1.5F)), world, random);
              TacktGen = true;
              PXC = firstBlockXCoord;
              PYC = firstBlockYCoord + 2;
              PZC = firstBlockZCoord;
              if (PAC == 0) {
                genCount = 40 + random.nextInt(10);
                PAC = 1;
              } 
              blockGen = true;
            } 
          } 
          float rg = world.rand.nextFloat() * 360.0F;
          float imp = 2.0F + world.rand.nextFloat() * 1.0F;
          float impV = 7.0F + world.rand.nextFloat() * 5.0F;
          rg = (float)Math.toRadians(rg);
          XCV = PXC + (int)Math.floor(Math.sin(rg) * impV);
          YCV = PYC;
          ZCV = PZC + (int)Math.floor(Math.cos(rg) * impV);
          XC = PXC + (int)Math.floor(Math.sin(rg) * imp);
          YC = PYC;
          ZC = PZC + (int)Math.floor(Math.cos(rg) * imp);
        } 
      } 
    } 
  }
}
