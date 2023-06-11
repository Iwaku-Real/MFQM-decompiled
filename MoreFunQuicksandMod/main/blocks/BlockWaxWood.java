package MoreFunQuicksandMod.main.blocks;

import MoreFunQuicksandMod.main.MFQM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockWaxWood extends Block {
  @SideOnly(Side.CLIENT)
  private IIcon IIconSideNormal;
  
  @SideOnly(Side.CLIENT)
  private IIcon IIconSideWax0;
  
  @SideOnly(Side.CLIENT)
  private IIcon IIconSideWax1;
  
  @SideOnly(Side.CLIENT)
  private IIcon IIconLog;
  
  public BlockWaxWood() {
    super(Material.wood);
    setHardness(2.0F);
    setStepSound(Block.soundTypeWood);
    setUnlocalizedName("WaxWood");
    setCreativeTab(MFQM.tabMFQM);
  }
  
  public int quantityDropped(Random p_149745_1_) {
    return 1;
  }
  
  public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
    return Item.getItemFromBlock(Blocks.log);
  }
  
  public int damageDropped(int par1) {
    return 3;
  }
  
  @SideOnly(Side.CLIENT)
  public void getSubBlocks(Item block, CreativeTabs creativeTabs, List<ItemStack> list) {
    list.add(new ItemStack(block, 1, 0));
    list.add(new ItemStack(block, 1, 4));
  }
  
  public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase ent, ItemStack stack) {
    if (stack.getMetadata() < 4) {
      int var7 = MathHelper.floor_double((ent.rotationYaw * 4.0F / 360.0F) + 2.5D) & 0x3;
      world.setBlockMetadataWithNotify(x, y, z, var7, 2);
    } else {
      int var7 = MathHelper.floor_double((ent.rotationYaw * 4.0F / 360.0F) + 2.5D) & 0x3;
      world.setBlockMetadataWithNotify(x, y, z, var7 + 4, 2);
    } 
  }
  
  public int getDamageValue(World par1World, int par2, int par3, int par4) {
    return (par1World.getBlockMetadata(par2, par3, par4) < 4) ? 0 : 4;
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int side, int meta) {
    if (meta < 4) {
      int i = meta;
      return (side == 1) ? this.IIconLog : ((side == 0) ? this.IIconLog : ((i == 2 && side == 2) ? this.IIconSideWax0 : ((i == 3 && side == 5) ? this.IIconSideWax0 : ((i == 0 && side == 3) ? this.IIconSideWax0 : ((i == 1 && side == 4) ? this.IIconSideWax0 : this.IIconSideNormal)))));
    } 
    int tmp = meta - 4;
    return (side == 1) ? this.IIconLog : ((side == 0) ? this.IIconLog : ((tmp == 2 && side == 2) ? this.IIconSideWax1 : ((tmp == 3 && side == 5) ? this.IIconSideWax1 : ((tmp == 0 && side == 3) ? this.IIconSideWax1 : ((tmp == 1 && side == 4) ? this.IIconSideWax1 : this.IIconSideNormal)))));
  }
  
  public void harvestBlock(World world, EntityPlayer entityPlayer, int x, int y, int z, int par6) {
    if (EnchantmentHelper.getSilkTouchModifier((EntityLivingBase)entityPlayer)) {
      if (par6 < 4) {
        MFQM.dropItem(world, x, y, z, new ItemStack(this, 1, 0));
      } else {
        MFQM.dropItem(world, x, y, z, new ItemStack(this, 1, 4));
      } 
      world.markBlockForUpdate(x, y, z);
      return;
    } 
    if (par6 < 4) {
      MFQM.dropItem(world, x, y, z, new ItemStack(MFQM.WaxItem, 2 + world.rand.nextInt(4)));
    } else {
      MFQM.dropItem(world, x, y, z, new ItemStack(MFQM.WaxItem, 1 + world.rand.nextInt(2)));
    } 
    super.harvestBlock(world, entityPlayer, x, y, z, par6);
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister par1IIconRegister) {
    this.IIconLog = par1IIconRegister.registerIcon("log_jungle_top");
    this.IIconSideNormal = par1IIconRegister.registerIcon("log_jungle");
    this.IIconSideWax0 = par1IIconRegister.registerIcon("morefunquicksandmod:WaxTree0");
    this.IIconSideWax1 = par1IIconRegister.registerIcon("morefunquicksandmod:WaxTree1");
  }
}
