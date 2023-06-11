package MoreFunQuicksandMod.main.items;

import MoreFunQuicksandMod.main.MFQM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemClayBucket extends ItemBucket {
  @SideOnly(Side.CLIENT)
  private IIcon itemIIcon1;
  
  @SideOnly(Side.CLIENT)
  private IIcon itemIIcon2;
  
  private static String[] items = new String[] { "BucketofBrownClay", "BucketofMineralClay", "Errorbucket" };
  
  public Block block;
  
  public ItemClayBucket(Block blockID) {
    super(blockID);
    this.block = blockID;
    setUnlocalizedName("BucketOfClay");
    setHasSubtypes(true);
    setCreativeTab(MFQM.tabMFQM);
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister iuconRegister) {
    this.itemIIcon1 = iuconRegister.registerIcon("morefunquicksandmod:BucketOfBrownClay");
    this.itemIIcon2 = iuconRegister.registerIcon("morefunquicksandmod:BucketOfMineralClay");
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIconFromDamage(int par1) {
    if (par1 == 0)
      return this.itemIIcon1; 
    return this.itemIIcon2;
  }
  
  @SideOnly(Side.CLIENT)
  public void getSubItems(Item item, CreativeTabs creativeTabs, List<ItemStack> list) {
    list.add(new ItemStack(item, 1, 0));
    list.add(new ItemStack(item, 1, 1));
  }
  
  public String getUnlocalizedName(ItemStack itemStack) {
    int meta = itemStack.getMetadata();
    if (meta < 0 || meta >= items.length)
      meta = 2; 
    return getUnlocalizedName() + "." + items[meta];
  }
  
  public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player) {
    boolean var4 = false;
    MovingObjectPosition var5 = getMovingObjectPositionFromPlayer(world, player, var4);
    if (var5 == null)
      return item; 
    if (var5.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
      int var6 = var5.blockX;
      int var7 = var5.blockY;
      int var8 = var5.blockZ;
      if (!world.canMineBlock(player, var6, var7, var8))
        return item; 
      if (var5.sideHit == 0)
        var7--; 
      if (var5.sideHit == 1)
        var7++; 
      if (var5.sideHit == 2)
        var8--; 
      if (var5.sideHit == 3)
        var8++; 
      if (var5.sideHit == 4)
        var6--; 
      if (var5.sideHit == 5)
        var6++; 
      if (!player.canPlayerEdit(var6, var7, var8, var5.sideHit, item))
        return item; 
      if (tryPlaceContainedLiquid2(world, var6, var7, var8, item) && !player.capabilities.isCreativeMode)
        return new ItemStack(Items.bucket); 
    } 
    return item;
  }
  
  public boolean tryPlaceContainedLiquid2(World world, int x, int y, int z, ItemStack item) {
    Material var5 = world.getBlock(x, y, z).getMaterial();
    boolean var6 = !var5.isSolid();
    if (!world.isAirBlock(x, y, z) && !var6)
      return false; 
    if (!world.isRemote && var6 && !var5.isLiquid())
      world.breakBlock(x, y, z, true); 
    if (!world.isRemote)
      if (item.getMetadata() == 0) {
        world.setBlock(x, y, z, this.block, 0, 3);
      } else {
        world.setBlock(x, y, z, this.block, 4, 3);
      }  
    return true;
  }
}
