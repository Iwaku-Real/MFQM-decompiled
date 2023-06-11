package MoreFunQuicksandMod.main.blocks;

import MoreFunQuicksandMod.main.MFQM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockMoor extends Block {
  @SideOnly(Side.CLIENT)
  private IIcon IIconSide0;
  
  private int maxOpacity;
  
  private int lastOpacity;
  
  private int incOpacity;
  
  public BlockMoor(int mopc, int lopc, int iopc) {
    super(Material.sponge);
    setHardness(2.75F);
    setResistance(1000.0F);
    setStepSound(Block.soundTypeGrass);
    setTickRandomly(true);
    if (MFQM.QSOpacity) {
      setLightOpacity(255);
    } else {
      setLightOpacity(3);
    } 
    setUnlocalizedName("Moor");
    setCreativeTab(MFQM.tabMFQM);
    this.maxOpacity = mopc;
    this.lastOpacity = lopc;
    this.incOpacity = iopc;
  }
  
  public void updateTick(World world, int x, int y, int z, Random random) {
    int meta = world.getBlockMetadata(x, y, z);
    if (meta < 10) {
      if (world.getBlock(x, y + 1, z) == MFQM.MireBlock || world.getBlock(x, y + 1, z) == MFQM.MorassBlock || world.getBlock(x, y + 1, z) == this) {
        world.setBlock(x, y, z, MFQM.MireBlock, 10, 3);
        return;
      } 
      int xx = x;
      int yy = y;
      int zz = z;
      int dificult = 10;
      int dif2 = 0;
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
        } 
        if (world.getBlock(xx, yy, zz) == MFQM.MorassBlock)
          dificult = Math.max(1, dificult - world.getBlockMetadata(xx, yy, zz)); 
      } 
      if (dif2 == 0 && 
        world.rand.nextInt(dificult * 5) == 0) {
        world.setBlock(x, y, z, MFQM.MorassBlock, world.rand.nextInt(2) + 7, 3);
        dif2 = 1;
      } 
      if (dif2 == 0 && 
        world.rand.nextInt(dificult * 3) == 0) {
        world.setBlock(x, y, z, MFQM.MorassBlock, world.rand.nextInt(4) + 3, 3);
        dif2 = 2;
      } 
      if (dif2 == 0 && 
        world.rand.nextInt(dificult * 2) == 0) {
        world.setBlock(x, y, z, MFQM.MorassBlock, 2, 3);
        dif2 = 2;
      } 
      if (dif2 == 0 && 
        world.rand.nextInt(dificult * 3) == 0) {
        world.setBlock(x, y, z, MFQM.MorassBlock, 0, 3);
        dif2 = 3;
      } 
      if (dif2 == 0) {
        world.setBlock(x, y, z, MFQM.MorassBlock, 1, 3);
        dif2 = 1;
      } 
      world.setBlock(x, y - 1, z, MFQM.MireBlock, 10, 2);
      if (world.getBlock(x, y - 2, z) != MFQM.MireBlock)
        world.setBlock(x, y - 2, z, Blocks.dirt); 
      if (random.nextInt(5) == 0)
        if (random.nextInt(100 * dif2) == 0) {
          ((BlockMorass)MFQM.MorassBlock).PlantingThis(world, random, x, y, z);
        } else {
          if (random.nextInt(4 / dif2) == 0 && 
            !world.getBlock(x, y + 1, z).getMaterial().isSolid())
            world.setBlock(x, y + 1, z, MFQM.MoorGrassBlock, 3, 2); 
          if (random.nextInt(30 * dif2) == 0 && 
            !world.getBlock(x, y + 1, z).getMaterial().isSolid())
            world.setBlock(x, y + 1, z, MFQM.MoorGrassBlock, 2, 2); 
          if (random.nextInt(40 / (int)Math.floor(Math.pow(dif2, 2.0D))) == 0 && 
            !world.getBlock(x, y + 1, z).getMaterial().isSolid())
            if (random.nextInt(10) == 0) {
              world.setBlock(x, y + 1, z, MFQM.MoorGrassBlock, 4, 2);
            } else {
              world.setBlock(x, y + 1, z, MFQM.MoorGrassBlock, 5, 2);
            }  
        }  
    } else if (meta == 10) {
      world.setBlockToAir(x, y, z);
      int rad = 128;
      for (int j = 0; j <= rad / 2; j++) {
        for (int k = 0; k <= rad; k++) {
          for (int e = 0; e <= rad; e++) {
            int posx = x + k - rad / 2;
            int posy = y - j;
            int posz = z + e - rad / 2;
            double dis = Math.sqrt(Math.pow((x - posx), 2.0D) + Math.pow((y - posy), 2.0D) + Math.pow((z - posz), 2.0D));
            if (dis < rad / 2.0D && 
              world.getBlock(posx, posy, posz).getMaterial() == Material.water)
              if (world.getBlockMetadata(posx, posy, posz) == 0) {
                if (world.getBlock(posx, posy - 1, posz).getMaterial() != Material.water || world
                  .getBlockMetadata(posx, posy - 1, posz) == 0) {
                  world.setBlock(posx, posy, posz, MFQM.TarBlock);
                  world.setBlock(posx, posy - 1, posz, MFQM.TarBlock);
                } 
              } else {
                world.setBlockToAir(posx, posy, posz);
              }  
          } 
        } 
      } 
      for (int i = 0; i <= rad; i++) {
        for (int k = 0; k <= rad / 2 + 2; k++) {
          for (int e = 0; e <= rad; e++) {
            int posx = x + i - rad / 2;
            int posy = y - k;
            int posz = z + e - rad / 2;
            if (world.getBlock(posx, posy, posz) != MFQM.TarBlock && 
              world.getBlock(posx, posy + 1, posz) == MFQM.TarBlock)
              world.setBlock(posx, posy, posz, Blocks.stone); 
          } 
        } 
      } 
    } 
    world.scheduleBlockUpdate(x, y, z, this, tickRate(world));
    super.updateTick(world, x, y, z, random);
  }
  
  public void onBlockAdded(World par1World, int par2, int par3, int par4) {
    par1World.scheduleBlockUpdate(par2, par3, par4, this, tickRate(par1World));
  }
  
  public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5) {
    if (!par1World.getBlock(par2, par3 - 1, par4).getMaterial().isSolid() && par1World.getBlock(par2, par3 - 1, par4).getMaterial().isReplaceable())
      par1World.scheduleBlockUpdate(par2, par3, par4, this, tickRate(par1World)); 
  }
  
  public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
    return null;
  }
  
  public Item getItemDropped(int par1, Random random, int par3) {
    return null;
  }
  
  public boolean canDropFromExplosion(Explosion par1Explosion) {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int par1, int par2) {
    return this.IIconSide0;
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister par1IIconRegister) {
    this.IIconSide0 = par1IIconRegister.registerIcon("morefunquicksandmod:Moor0");
  }
  
  protected boolean canSilkHarvest() {
    return true;
  }
  
  public boolean isNormalCube() {
    return false;
  }
  
  public boolean isOpaqueCube() {
    return false;
  }
  
  public boolean isSolid() {
    return true;
  }
}
