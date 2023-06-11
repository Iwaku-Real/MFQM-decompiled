package MoreFunQuicksandMod.main.blocks;

import MoreFunQuicksandMod.main.MFQM;
import MoreFunQuicksandMod.main.entity.EntityBee;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockHoneycomb extends Block {
  @SideOnly(Side.CLIENT)
  private IIcon[] IIconMArray;
  
  public BlockHoneycomb() {
    super(Material.cloth);
    setHardness(0.5F);
    setStepSound(Block.soundTypeStone);
    setResistance(1.0F);
    setUnlocalizedName("Honeycomb");
    setTickRandomly(true);
    setCreativeTab(MFQM.tabMFQM);
  }
  
  @SideOnly(Side.CLIENT)
  public void getSubBlocks(Item block, CreativeTabs creativeTabs, List<ItemStack> list) {
    list.add(new ItemStack(block, 1, 0));
    list.add(new ItemStack(block, 1, 1));
    list.add(new ItemStack(block, 1, 2));
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int par1, int par2) {
    return this.IIconMArray[Math.min(par2, 2)];
  }
  
  public Item getItemDropped(int par1, Random par2Random, int par3) {
    if (par1 != 2) {
      if (MFQM.BoP && 
        MFQM.FleshItemID != 0)
        return MFQM.FleshItem; 
      return MFQM.EmptycombItem;
    } 
    if (MFQM.BoP && 
      MFQM.FoodItemID != 0)
      return MFQM.FoodItem; 
    return MFQM.HoneycombItem;
  }
  
  public int quantityDropped(int meta, int fortune, Random random) {
    if (meta != 2)
      return random.nextInt(3) + 1; 
    if (meta < 3)
      return random.nextInt(2); 
    return 0;
  }
  
  public int damageDropped(int meta) {
    if (meta != 2) {
      if (MFQM.BoP && 
        MFQM.FleshItemID != 0)
        return 2; 
      return 0;
    } 
    if (MFQM.BoP && 
      MFQM.FoodItemID != 0)
      return 9; 
    return 0;
  }
  
  public void updateTick(World world, int x, int y, int z, Random rand) {
    if (world.getBlockMetadata(x, y, z) == 3)
      world.setBlockToAir(x, y, z); 
  }
  
  public boolean canHarvestBlock(EntityPlayer player, int meta) {
    return true;
  }
  
  public void harvestBlock(World world, EntityPlayer entityPlayer, int x, int y, int z, int par6) {
    if (par6 == 0 && 
      world.rand.nextInt(2) == 0) {
      EntityBee bee = new EntityBee(world);
      bee.setLocationAndAngles(x + 0.6D, y, z + 0.3D, 0.0F, 0.0F);
      world.spawnEntityInWorld((Entity)bee);
    } 
    if (EnchantmentHelper.getSilkTouchModifier((EntityLivingBase)entityPlayer)) {
      MFQM.dropItem(world, x, y, z, new ItemStack(this));
      world.markBlockForUpdate(x, y, z);
      return;
    } 
    if (par6 == 2) {
      world.playSoundEffect((x + 0.5F), (y + 0.5F), (z + 0.5F), "mob.silverfish.step", 0.15F, world.rand.nextFloat() * 0.2F + 0.6F);
      if (!world.isRemote)
        world.setBlock(x, y, z, MFQM.HoneyBlock, 0, 2); 
    } 
    super.harvestBlock(world, entityPlayer, x, y, z, par6);
  }
  
  public void onBlockAdded(World par1World, int par2, int par3, int par4) {
    if (par1World.getBlockMetadata(par2, par3, par4) == 3)
      par1World.scheduleBlockUpdate(par2, par3, par4, this, tickRate(par1World)); 
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister par1) {
    this.IIconMArray = new IIcon[3];
    this.IIconMArray[0] = par1.registerIcon("morefunquicksandmod:honeycomb0");
    this.IIconMArray[1] = par1.registerIcon("morefunquicksandmod:honeycomb1");
    this.IIconMArray[2] = par1.registerIcon("morefunquicksandmod:honeycomb2");
  }
  
  public int getDamageValue(World par1World, int par2, int par3, int par4) {
    return par1World.getBlockMetadata(par2, par3, par4);
  }
}
