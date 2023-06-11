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
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockBlossomSlab extends Block {
  @SideOnly(Side.CLIENT)
  private IIcon[] IIconMArray;
  
  public BlockBlossomSlab() {
    super(Material.sponge);
    setHardness(1.8F);
    setStepSound(Block.soundTypeGrass);
    setUnlocalizedName("BlossomBlockSlab");
    setCreativeTab(MFQM.tabMFQM);
    this.fullBlock = false;
    setLightOpacity(0);
  }
  
  public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
    return false;
  }
  
  public boolean isOpaqueCube() {
    return false;
  }
  
  public boolean isNormalCube() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public void getSubBlocks(Item block, CreativeTabs creativeTabs, List<ItemStack> list) {
    list.add(new ItemStack(block, 1, 0));
    list.add(new ItemStack(block, 1, 1));
    list.add(new ItemStack(block, 1, 2));
    list.add(new ItemStack(block, 1, 3));
    list.add(new ItemStack(block, 1, 4));
    list.add(new ItemStack(block, 1, 5));
  }
  
  public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
    int meta = world.getBlockMetadata(x, y, z);
    if (entity instanceof EntityPlayer) {
      InventoryPlayer inventory = ((EntityPlayer)entity).inventory;
      if (inventory.armorInventory[0] != null && inventory.armorInventory[0].getItem() == Item.getItemById(MFQM.BootsID))
        return; 
    } 
    switch (meta) {
      case 1:
        entity.motionZ = -0.15D;
        break;
      case 2:
        entity.motionX = 0.15D;
        break;
      case 3:
        entity.motionZ = 0.15D;
        break;
      case 4:
        entity.motionX = -0.15D;
        break;
    } 
  }
  
  public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int par2, int par3, int par4) {
    int mtd = world.getBlockMetadata(par2, par3, par4);
    float f1 = 0.0F;
    float f2 = 0.0F;
    if (mtd == 0) {
      f1 = 0.5F;
      f2 = 0.0F;
    } else {
      f1 = 0.0F;
      f2 = 0.5F;
    } 
    return AxisAlignedBB.getBoundingBox(par2, ((par3 + 1) - f1), par4, (par2 + 1), ((par3 + 1) - f2), (par4 + 1));
  }
  
  public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int x, int y, int z) {
    int mtd = par1IBlockAccess.getBlockMetadata(x, y, z);
    float f1 = 0.0F;
    float f2 = 0.0F;
    if (mtd == 0) {
      f1 = 0.5F;
      f2 = 0.0F;
    } else {
      f1 = 0.0F;
      f2 = 0.5F;
    } 
    setBlockBounds(0.0F, 0.0F + f1, 0.0F, 1.0F, 1.0F - f2, 1.0F);
  }
  
  public void setBlockBoundsForItemRender() {
    setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int par1, int par2) {
    switch (par2) {
      case 0:
        if (par1 == 1 || par1 == 0)
          return this.IIconMArray[0]; 
        return this.IIconMArray[1];
      case 1:
      case 2:
      case 3:
      case 4:
        if (par1 == 1)
          return this.IIconMArray[2]; 
        if (par1 == 0)
          return this.IIconMArray[0]; 
        if (par1 != 1)
          return this.IIconMArray[3]; 
      case 5:
        if (par1 == 1)
          return this.IIconMArray[2]; 
        if (par1 == 0)
          return this.IIconMArray[0]; 
        if (par1 != 1)
          return this.IIconMArray[4]; 
        break;
    } 
    return this.IIconMArray[0];
  }
  
  protected boolean canSilkHarvest() {
    return true;
  }
  
  public void harvestBlock(World world, EntityPlayer entityPlayer, int x, int y, int z, int par6) {
    if (EnchantmentHelper.getSilkTouchModifier((EntityLivingBase)entityPlayer)) {
      MFQM.dropItem(world, x, y, z, new ItemStack(this, 1, par6));
      world.markBlockForUpdate(x, y, z);
      return;
    } 
    super.harvestBlock(world, entityPlayer, x, y, z, par6);
  }
  
  public float getPlayerRelativeBlockHardness(EntityPlayer par1EntityPlayer, World par2World, int par3, int par4, int par5) {
    if (!((Entity)par1EntityPlayer).onGround)
      return -1.0F; 
    float f = getBlockHardness(par2World, par3, par4, par5);
    return ForgeHooks.blockStrength(this, par1EntityPlayer, par2World, par3, par4, par5);
  }
  
  public boolean canHarvestBlock(EntityPlayer player, int meta) {
    return true;
  }
  
  public Item getItemDropped(int par1, Random par2Random, int par3) {
    return null;
  }
  
  public int quantityDropped(Random random) {
    return 1;
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister par1) {
    this.IIconMArray = new IIcon[5];
    this.IIconMArray[0] = par1.registerIcon("morefunquicksandmod:Blossom3");
    this.IIconMArray[1] = par1.registerIcon("morefunquicksandmod:Blossom2");
    this.IIconMArray[2] = par1.registerIcon("morefunquicksandmod:Blossom0");
    this.IIconMArray[3] = par1.registerIcon("morefunquicksandmod:Blossom1");
    this.IIconMArray[4] = par1.registerIcon("morefunquicksandmod:Blossom7");
  }
  
  public int getDamageValue(World par1World, int par2, int par3, int par4) {
    return par1World.getBlockMetadata(par2, par3, par4);
  }
}
