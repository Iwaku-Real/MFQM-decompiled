package MoreFunQuicksandMod.main.items;

import MoreFunQuicksandMod.main.MFQM;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemFertilizer extends Item {
  public ItemFertilizer() {
    setUnlocalizedName("Fertilizer");
    setCreativeTab(MFQM.tabMFQM);
    setTextureName("morefunquicksandmod:Fertilizer");
  }
  
  public void addInformation(ItemStack item, EntityPlayer player, List<String> list, boolean bool) {
    list.add(StatCollector.translateToLocal("itemFertilizer.instruction1"));
  }
  
  public boolean onItemUse(ItemStack item, EntityPlayer ply, World world, int x, int y, int z, int side, float px, float py, float pz) {
    if (!ply.canPlayerEdit(x, y, z, side, item))
      return false; 
    if (GrowThings(item, world, x, y, z)) {
      if (world.isRemote) {
        world.playAuxSFX(2005, x, y, z, 0);
        world.playAuxSFX(2005, x, y, z, 0);
      } else if (!ply.capabilities.isCreativeMode) {
        item.stackSize--;
      } 
      return true;
    } 
    return false;
  }
  
  public boolean GrowThings(ItemStack item, World world, int x, int y, int z) {
    Block bl = world.getBlock(x, y, z);
    if (bl instanceof IGrowable) {
      IGrowable grow = (IGrowable)bl;
      if (grow.canFertilize(world, x, y, z, !world.isRemote)) {
        if (!world.isRemote && 
          grow.shouldFertilize(world, world.rand, x, y, z)) {
          grow.fertilize(world, world.rand, x, y, z);
          bl = world.getBlock(x, y, z);
          if (bl instanceof IGrowable && 
            grow.shouldFertilize(world, world.rand, x, y, z))
            grow.fertilize(world, world.rand, x, y, z); 
        } 
        return true;
      } 
    } 
    return false;
  }
}
