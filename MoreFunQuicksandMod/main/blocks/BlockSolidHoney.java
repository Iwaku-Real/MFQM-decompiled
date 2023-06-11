package MoreFunQuicksandMod.main.blocks;

import MoreFunQuicksandMod.main.MFQM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockSolidHoney extends Block {
  IIcon theQIIcon;
  
  public BlockSolidHoney() {
    super(Material.rock);
    setHardness(1.0F);
    setStepSound(Block.soundTypeStone);
    setResistance(1.0F);
    setUnlocalizedName("SolidHoney");
    setCreativeTab(MFQM.tabMFQM);
  }
  
  public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int par2, int par3, int par4) {
    float f = 0.025F;
    return AxisAlignedBB.getBoundingBox((par2 + f), (par3 + f), (par4 + f), ((par2 + 1) - f), ((par3 + 1) - f), ((par4 + 1) - f));
  }
  
  public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
    return false;
  }
  
  protected boolean validForSpread(World world, int x, int y, int z) {
    if (world.getBlock(x, y, z) == MFQM.HoneycombBlock && world.getBlockMetadata(x, y, z) == 3)
      return true; 
    return false;
  }
  
  public void hyperHoney(World world, int x, int y, int z, int meta) {
    if (meta > 1) {
      if (validForSpread(world, x + 1, y, z))
        world.setBlock(x + 1, y, z, MFQM.SolidHoneyBlock, meta - 1, 3); 
      if (validForSpread(world, x + 2, y, z))
        world.setBlock(x + 2, y, z, MFQM.SolidHoneyBlock, meta - 1, 3); 
      if (validForSpread(world, x + 3, y, z))
        world.setBlock(x + 3, y, z, MFQM.SolidHoneyBlock, meta - 1, 3); 
      if (validForSpread(world, x - 1, y, z))
        world.setBlock(x - 1, y, z, MFQM.SolidHoneyBlock, meta - 1, 3); 
      if (validForSpread(world, x - 2, y, z))
        world.setBlock(x - 2, y, z, MFQM.SolidHoneyBlock, meta - 1, 3); 
      if (validForSpread(world, x - 3, y, z))
        world.setBlock(x - 3, y, z, MFQM.SolidHoneyBlock, meta - 1, 3); 
      if (validForSpread(world, x, y, z + 1))
        world.setBlock(x, y, z + 1, MFQM.SolidHoneyBlock, meta - 1, 3); 
      if (validForSpread(world, x, y, z + 2))
        world.setBlock(x, y, z + 2, MFQM.SolidHoneyBlock, meta - 1, 3); 
      if (validForSpread(world, x, y, z + 3))
        world.setBlock(x, y, z + 3, MFQM.SolidHoneyBlock, meta - 1, 3); 
      if (validForSpread(world, x, y, z - 1))
        world.setBlock(x, y, z - 1, MFQM.SolidHoneyBlock, meta - 1, 3); 
      if (validForSpread(world, x, y, z - 2))
        world.setBlock(x, y, z - 2, MFQM.SolidHoneyBlock, meta - 1, 3); 
      if (validForSpread(world, x, y, z - 3))
        world.setBlock(x, y, z - 3, MFQM.SolidHoneyBlock, meta - 1, 3); 
      if (validForSpread(world, x, y - 1, z))
        world.setBlock(x, y - 1, z, MFQM.SolidHoneyBlock, meta - 1, 2); 
      if (validForSpread(world, x, y - 2, z))
        world.setBlock(x, y - 2, z, MFQM.SolidHoneyBlock, meta - 1, 2); 
    } 
    world.setBlock(x, y, z, MFQM.HoneyBlock, 0, 3);
  }
  
  public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
    entity.fallDistance = 0.0F;
    double oy = y - entity.posY + 1.0D;
    double oy2 = y - entity.prevPosY + 1.0D;
    oy *= -1.0D;
    oy2 *= -1.0D;
    if (oy2 < 0.0D)
      oy2 = 0.0D; 
    double kof1 = oy;
    double kof1m = oy;
    if (kof1m < 0.0D)
      kof1m = 0.0D; 
    double kof2 = oy2;
    entity.motionY = 0.0D;
    if (entity instanceof EntityLivingBase && 
      !(entity instanceof net.minecraft.entity.player.EntityPlayer)) {
      oy = y - entity.posY - 0.5D;
      oy2 = y - entity.prevPosY - 0.5D;
      oy *= -1.0D;
      oy2 *= -1.0D;
      if (oy2 < 0.0D)
        oy2 = 0.0D; 
      kof1 = oy;
      kof1m = oy;
      if (kof1m < 0.0D)
        kof1m = 0.0D; 
      kof2 = oy2;
      if (kof1 < 1.485D) {
        entity.motionY += 0.08D + Math.min((0.9D - kof1) / 100.0D, 0.005D);
        entity.onGround = true;
        entity.fallDistance = 0.0F;
      } 
    } 
    entity.motionX = 0.0D;
    entity.motionZ = 0.0D;
    if (entity instanceof EntityLivingBase)
      ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(8, 0, -2, false)); 
    if (!entity.onGround && 
      entity.motionY <= 0.0D)
      entity.setInWeb(); 
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
      world.setBlock(x, y, z, MFQM.HoneyBlock, 0, 3);
      world.playSoundEffect((x + 0.5F), (y + 0.5F), (z + 0.5F), "mob.silverfish.step", 0.25F, world.rand.nextFloat() * 0.15F + 0.1F);
      return;
    } 
    if (hot > 0 && 
      world.getBlockMetadata(x, y, z) == 0 && 
      world.rand.nextInt(Math.max(15 - hot, 1)) == 0) {
      world.setBlock(x, y, z, MFQM.HoneyBlock, 0, 3);
      return;
    } 
    super.updateTick(world, x, y, z, rand);
  }
  
  public void onBlockAdded(World par1World, int par2, int par3, int par4) {
    int meta = par1World.getBlockMetadata(par2, par3, par4);
    if (meta > 0) {
      hyperHoney(par1World, par2, par3, par4, meta);
      return;
    } 
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
    this.theQIIcon = par1IIconRegister.registerIcon("morefunquicksandmod:Honey");
  }
  
  public int getDamageValue(World par1World, int par2, int par3, int par4) {
    return par1World.getBlockMetadata(par2, par3, par4);
  }
}
