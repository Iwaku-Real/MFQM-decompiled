package MoreFunQuicksandMod.main.blocks;

import MoreFunQuicksandMod.main.MFQM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockPeat extends Block {
  IIcon theQIIcon;
  
  public BlockPeat() {
    super(Material.rock);
    setHardness(0.85F);
    setStepSound(Block.soundTypeStone);
    setResistance(1.0F);
    setUnlocalizedName("HPeat");
    setCreativeTab(MFQM.tabMFQM);
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int par1, int par2) {
    return this.theQIIcon;
  }
  
  public Item getItemDropped(int par1, Random par2Random, int par3) {
    return MFQM.PeatItem;
  }
  
  public int quantityDropped(Random random) {
    return 8 + random.nextInt(2);
  }
  
  public void harvestBlock(World world, EntityPlayer entityPlayer, int x, int y, int z, int par6) {
    if (EnchantmentHelper.getSilkTouchModifier((EntityLivingBase)entityPlayer)) {
      MFQM.dropItem(world, x, y, z, new ItemStack(this));
      world.markBlockForUpdate(x, y, z);
      return;
    } 
    super.harvestBlock(world, entityPlayer, x, y, z, par6);
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister par1IIconRegister) {
    this.theQIIcon = par1IIconRegister.registerIcon("morefunquicksandmod:Peat");
  }
}
