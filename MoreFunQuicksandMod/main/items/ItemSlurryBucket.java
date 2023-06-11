package MoreFunQuicksandMod.main.items;

import MoreFunQuicksandMod.main.MFQM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSlurryBucket extends ItemBucket {
  public ItemSlurryBucket(Block blockID) {
    super(blockID);
    setUnlocalizedName("BucketOfSlurry");
    setCreativeTab(MFQM.tabMFQM);
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister par1IconRegister) {
    this.itemIcon = par1IconRegister.registerIcon("morefunquicksandmod:BucketOfSlurry");
  }
  
  public boolean onItemUse(ItemStack item, EntityPlayer ply, World world, int x, int y, int z, int side, float px, float py, float pz) {
    if (!ply.canPlayerEdit(x, y, z, side, item))
      return false; 
    if (side == 1 && 
      GrowThings(item, world, x, y, z)) {
      if (world.isRemote) {
        world.playAuxSFX(2005, x, y, z, 0);
        world.playAuxSFX(2005, x, y, z, 0);
        world.playAuxSFX(2005, x, y, z, 0);
      } else if (!ply.capabilities.isCreativeMode) {
        ply.inventory.setInventorySlotContents(ply.inventory.currentItem, new ItemStack(Items.bucket));
      } 
      world.playSoundEffect(x, y, z, "game.player.swim", 0.15F, world.rand.nextFloat() * 0.25F + 0.1F);
      world.playSoundEffect(x, y, z, "mob.silverfish.step", 0.5F, world.rand.nextFloat() * 0.25F + 0.1F);
      return true;
    } 
    return false;
  }
  
  public boolean GrowThings(ItemStack item, World world, int x, int y, int z) {
    Block bl = world.getBlock(x, y, z);
    if (bl instanceof IGrowable) {
      IGrowable grow = (IGrowable)bl;
      if (grow.canFertilize(world, x, y, z, !world.isRemote)) {
        if (!world.isRemote) {
          grow.fertilize(world, world.rand, x, y, z);
          bl = world.getBlock(x, y, z);
          if (bl instanceof IGrowable) {
            grow.fertilize(world, world.rand, x, y, z);
            bl = world.getBlock(x, y, z);
            if (bl instanceof IGrowable)
              grow.fertilize(world, world.rand, x, y, z); 
          } 
        } 
        return true;
      } 
    } 
    return false;
  }
}
