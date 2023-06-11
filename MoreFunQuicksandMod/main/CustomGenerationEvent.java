package MoreFunQuicksandMod.main;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;

public class CustomGenerationEvent {
  boolean T_BoP;
  
  @SubscribeEvent
  public void populateChunk(PopulateChunkEvent.Post event) {
    this.T_BoP = MFQM.BoP;
    if (MFQM.PBoP && 
      MFQM.BoP && (
      event.world.getWorldInfo().getTerrainType() == WorldType.LARGE_BIOMES || event.world.getWorldInfo().getTerrainType() == WorldType.DEFAULT))
      this.T_BoP = false; 
    if (event.world.provider.dimensionId == 0)
      for (int xx = 0; xx < 3; xx++) {
        for (int zz = 0; zz < 3; zz++) {
          if (event.world.blockExists((event.chunkX + xx - 1) * 16, 64, (event.chunkZ + zz - 1) * 16)) {
            Chunk chunk = event.world.getChunkFromChunkCoords(event.chunkX + xx - 1, event.chunkZ + zz - 1);
            if (chunk != null) {
              int GenType = 0;
              BiomeGenBase biomegenbase = event.world.getBiomeGenForCoords((event.chunkX + xx - 1) * 16, (event.chunkZ + zz - 1) * 16);
              int biomeId = biomegenbase.biomeID;
              if (this.T_BoP) {
                for (int i = 0; i < MFQM.QSJungleBiomes.length; i++) {
                  if (biomeId == MFQM.QSJungleBiomes[i])
                    GenType = 1; 
                } 
              } else if (biomegenbase instanceof net.minecraft.world.biome.BiomeGenSwamp || biomegenbase instanceof net.minecraft.world.biome.BiomeGenJungle || biomegenbase instanceof net.minecraft.world.biome.BiomeGenForest) {
                GenType = 1;
              } 
              ExtendedBlockStorage[] SA = chunk.getBlockStorageArray();
              for (int yy = 0; yy < 16; yy++) {
                ExtendedBlockStorage storage = SA[yy];
                if (storage != null)
                  for (int x = 0; x < 16; x++) {
                    for (int y = 0; y < 16; y++) {
                      for (int z = 0; z < 16; z++) {
                        if (storage.getBlockByExtId(x, y, z) == MFQM.BOPMudBlock)
                          if (storage.getExtBlockMetadata(x, y, z) != 1) {
                            storage.setExtBlockID(x, y, z, MFQM.MudBlock);
                          } else {
                            int gx = x + (event.chunkX + xx - 1) * 16;
                            int gz = z + (event.chunkZ + zz - 1) * 16;
                            int gy = y + yy * 16;
                            if (GenType == 1) {
                              if (event.world.getBlock(gx, gy + 1, gz).getMaterial().isSolid()) {
                                storage.setExtBlockID(x, y, z, Blocks.dirt);
                              } else {
                                storage.setExtBlockID(x, y, z, (Block)Blocks.grass);
                              } 
                            } else {
                              storage.setExtBlockID(x, y, z, (Block)Blocks.sand);
                              storage.setExtBlockMetadata(x, y, z, 0);
                            } 
                          }  
                      } 
                    } 
                  }  
              } 
            } 
          } 
        } 
      }  
    if (event.world.provider.dimensionId == -1 && 
      MFQM.GenHoney2)
      for (int xx = 0; xx < 3; xx++) {
        for (int zz = 0; zz < 3; zz++) {
          if (event.world.blockExists((event.chunkX + xx - 1) * 16, 64, (event.chunkZ + zz - 1) * 16)) {
            Chunk chunk = event.world.getChunkFromChunkCoords(event.chunkX + xx - 1, event.chunkZ + zz - 1);
            if (chunk != null) {
              ExtendedBlockStorage[] SA = chunk.getBlockStorageArray();
              for (int yy = 0; yy < 16; yy++) {
                ExtendedBlockStorage storage = SA[yy];
                if (storage != null)
                  for (int x = 0; x < 16; x++) {
                    for (int y = 0; y < 16; y++) {
                      for (int z = 0; z < 16; z++) {
                        if (storage.getBlockByExtId(x, y, z) == MFQM.BOPHoneyBlock) {
                          int meta = storage.getExtBlockMetadata(x, y, z);
                          storage.setExtBlockID(x, y, z, MFQM.HoneyBlock);
                          storage.setExtBlockMetadata(x, y, z, 0);
                        } 
                        if (storage.getBlockByExtId(x, y, z) == MFQM.BOPSolidHoneyBlock) {
                          storage.setExtBlockID(x, y, z, MFQM.SolidHoneyBlock);
                          storage.setExtBlockMetadata(x, y, z, 0);
                        } 
                      } 
                    } 
                  }  
              } 
            } 
          } 
        } 
      }  
    if (MFQM.TBL && 
      event.world.provider.dimensionId == MFQM.TBLDim)
      for (int xx = 0; xx < 5; xx++) {
        for (int zz = 0; zz < 5; zz++) {
          if (event.world.blockExists((event.chunkX + xx - 2) * 16, 64, (event.chunkZ + zz - 2) * 16)) {
            Chunk chunk = event.world.getChunkFromChunkCoords(event.chunkX + xx - 2, event.chunkZ + zz - 2);
            if (chunk != null) {
              ExtendedBlockStorage[] SA = chunk.getBlockStorageArray();
              for (int yy = 0; yy < 16; yy++) {
                ExtendedBlockStorage storage = SA[yy];
                if (storage != null)
                  for (int x = 0; x < 16; x++) {
                    for (int y = 0; y < 16; y++) {
                      for (int z = 0; z < 16; z++) {
                        if (storage.getBlockByExtId(x, y, z) == MFQM.TBLMudBlock) {
                          storage.setExtBlockID(x, y, z, MFQM.MudBlock);
                          storage.setExtBlockMetadata(x, y, z, 3);
                        } else if (storage.getBlockByExtId(x, y, z) == MFQM.TBLTarBlock) {
                          int mtd = storage.getExtBlockMetadata(x, y, z);
                          storage.setExtBlockID(x, y, z, MFQM.TarBlock);
                          storage.setExtBlockMetadata(x, y, z, mtd);
                        } else if (storage.getBlockByExtId(x, y, z) == MFQM.TBLSludgeBlock) {
                          storage.setExtBlockID(x, y, z, MFQM.MireBlock);
                          storage.setExtBlockMetadata(x, y, z, 0);
                          if (y > 0) {
                            storage.setExtBlockID(x, y - 1, z, MFQM.MireBlock);
                            storage.setExtBlockMetadata(x, y - 1, z, 12);
                          } else {
                            ExtendedBlockStorage storage2 = SA[Math.max(yy - 1, 0)];
                            if (storage2 != null) {
                              storage2.setExtBlockID(x, 15, z, MFQM.MireBlock);
                              storage2.setExtBlockMetadata(x, 15, z, 12);
                            } 
                          } 
                          if (y > 1) {
                            if (storage.getBlockByExtId(x, y - 2, z) != MFQM.MireBlock)
                              storage.setExtBlockID(x, y - 2, z, MFQM.PeatBlock); 
                          } else {
                            ExtendedBlockStorage storage2 = SA[Math.max(yy - 1, 0)];
                            if (storage2 != null) {
                              storage2.setExtBlockID(x, 14, z, MFQM.MireBlock);
                              storage2.setExtBlockMetadata(x, 14, z, 12);
                            } 
                          } 
                        } 
                      } 
                    } 
                  }  
              } 
            } 
          } 
        } 
      }  
  }
}
