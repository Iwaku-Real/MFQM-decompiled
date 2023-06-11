package MoreFunQuicksandMod.main.items;

import MoreFunQuicksandMod.main.MFQM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemMyPotion extends Item {
  private IIcon[] itemIcons = new IIcon[3];
  
  private static String[] items = new String[] { "BottleofMire", "SinkingPotion", "HotChocolate", "ErrorPotion" };
  
  public ItemMyPotion() {
    setMaxStackSize(16);
    setHasSubtypes(true);
    setUnlocalizedName("Potion");
    setCreativeTab(MFQM.tabMFQM);
  }
  
  public ItemStack onItemUseFinish(ItemStack item, World world, EntityPlayer player) {
    if (!world.isRemote) {
      if (item.getMetadata() == 0) {
        player.addPotionEffect(new PotionEffect(19, 200, 2, false));
        player.addPotionEffect(new PotionEffect(17, 1200, 2, false));
        player.addPotionEffect(new PotionEffect(9, 1200, 2, false));
      } 
      if (item.getMetadata() == 1) {
        player.addPotionEffect(new PotionEffect(20, 200, 2, false));
        player.addPotionEffect(new PotionEffect(17, 2400, 2, false));
        player.addPotionEffect(new PotionEffect(9, 2400, 2, false));
      } 
      if (item.getMetadata() == 2) {
        player.getFoodStats().addStats(8, 1.0F);
        player.addPotionEffect(new PotionEffect(10, 200, 0, false));
      } 
      item.stackSize--;
      if (!player.capabilities.isCreativeMode && !player.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle, 1, 0)))
        player.dropPlayerItemWithRandomChoice(new ItemStack(Items.glass_bottle, 1, 0), false); 
    } 
    return item;
  }
  
  public int getMaxItemUseDuration(ItemStack item) {
    return 32;
  }
  
  public EnumAction getItemUseAction(ItemStack item) {
    return EnumAction.drink;
  }
  
  public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player) {
    player.setItemInUse(item, getMaxItemUseDuration(item));
    return item;
  }
  
  public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
    if (item.getMetadata() != 1)
      return false; 
    if (world.getBlock(x, y, z) == Blocks.red_flower) {
      if (checkCanSpawnMucusBlossom(world, x, y, z)) {
        spawnMucusBlossom(world, x, y, z);
        item.stackSize--;
        return true;
      } 
      return false;
    } 
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister iuconRegister) {
    this.itemIcons[0] = iuconRegister.registerIcon("morefunquicksandmod:potion_mud");
    this.itemIcons[1] = iuconRegister.registerIcon("morefunquicksandmod:potion_sinking");
    this.itemIcons[2] = iuconRegister.registerIcon("morefunquicksandmod:HotChocolate");
  }
  
  public String getUnlocalizedName(ItemStack itemStack) {
    int meta = itemStack.getMetadata();
    if (meta < 0 || meta >= items.length)
      meta = 3; 
    return getUnlocalizedName() + "." + items[meta];
  }
  
  @SideOnly(Side.CLIENT)
  public void getSubItems(Item item, CreativeTabs creativeTabs, List<ItemStack> list) {
    list.add(new ItemStack(item, 1, 0));
    list.add(new ItemStack(item, 1, 1));
    list.add(new ItemStack(item, 1, 2));
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIconFromDamage(int par1) {
    return this.itemIcons[Math.min(par1, 2)];
  }
  
  public void addInformation(ItemStack item, EntityPlayer player, List<String> list, boolean bool) {
    int meta = item.getMetadata();
    if (meta == 1)
      list.add(StatCollector.translateToLocal("itemSinkingPotion.Text")); 
    if (meta == 2)
      list.add(StatCollector.translateToLocal("itemHotChocolate.Text")); 
  }
  
  public boolean checkCanSpawnMucusBlossom(World world, int x, int y, int z) {
    if (y < 3)
      return false; 
    if (validMaterial(x, y - 1, z, world) && 
      validMaterial(x + 1, y - 1, z, world) && 
      validMaterial(x - 1, y - 1, z, world) && 
      validMaterial(x, y - 1, z - 1, world) && 
      validMaterial(x, y - 1, z + 1, world) && 
      validMaterial(x, y - 2, z, world))
      return true; 
    return false;
  }
  
  public void spawnMucusBlossom(World world, int x, int y, int z) {
    world.setBlock(x, y, z, MFQM.BlossomSlabBlock, 5, 2);
    world.setBlock(x, y - 1, z, MFQM.BlossomBlock, 11, 2);
    world.setBlock(x + 1, y - 1, z, MFQM.BlossomBlock, 1, 2);
    world.setBlock(x - 1, y - 1, z, MFQM.BlossomBlock, 1, 2);
    world.setBlock(x, y - 1, z - 1, MFQM.BlossomBlock, 1, 2);
    world.setBlock(x, y - 1, z + 1, MFQM.BlossomBlock, 1, 2);
    world.setBlock(x, y - 2, z, MFQM.BlossomBlock, 1, 2);
    world.playSoundEffect(x, y, z, "dig.grass", 0.5F, world.rand.nextFloat() * 0.1F + 0.1F);
    world.playSoundEffect(x, y, z, "dig.grass", 0.5F, world.rand.nextFloat() * 0.1F + 0.1F);
    world.playSoundEffect(x, y, z, "step.grass", 0.25F, world.rand.nextFloat() * 0.1F + 0.1F);
    world.playSoundEffect(x, y, z, "step.grass", 0.25F, world.rand.nextFloat() * 0.1F + 0.1F);
    world.playSoundEffect(x, y, z, "step.grass", 0.25F, world.rand.nextFloat() * 0.1F + 0.1F);
  }
  
  private boolean validMaterial(int x, int y, int z, World world) {
    if (world.getBlock(x, y, z) == null)
      return true; 
    if (world.getBlock(x, y, z) == Blocks.air)
      return true; 
    if (world.getBlock(x, y, z).getMaterial().isLiquid() || world.getBlock(x, y, z).getMaterial().isReplaceable())
      return true; 
    if (world.getBlock(x, y, z).getMaterial() == Material.vine || world
      .getBlock(x, y, z).getMaterial() == Material.plants || world
      .getBlock(x, y, z).getMaterial() == Material.leaves || world
      .getBlock(x, y, z).getMaterial() == Material.grass || world
      .getBlock(x, y, z).getMaterial() == Material.ground || world
      .getBlock(x, y, z).getMaterial() == Material.sand)
      return true; 
    return false;
  }
}
