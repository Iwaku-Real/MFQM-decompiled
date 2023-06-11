package MoreFunQuicksandMod.main.blocks;

import MoreFunQuicksandMod.main.MFQM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockHardenedClay extends Block {
  @SideOnly(Side.CLIENT)
  private IIcon[] IIconNormalArray;
  
  @SideOnly(Side.CLIENT)
  private IIcon[] IIconBrokenArray;
  
  @SideOnly(Side.CLIENT)
  private IIcon IIconSideNormal;
  
  @SideOnly(Side.CLIENT)
  private IIcon IIconSideBroken;
  
  public BlockHardenedClay() {
    super(Material.rock);
    setHardness(1.25F);
    setStepSound(Block.soundTypeStone);
    setResistance(7.0F);
    setUnlocalizedName("HardenedClay");
    setCreativeTab(MFQM.tabMFQM);
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int par1, int par2) {
    switch (par1) {
      case 1:
        return this.IIconNormalArray[Math.min(4, par2)];
    } 
    return this.IIconNormalArray[0];
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister par1IIconRegister) {
    this.IIconNormalArray = new IIcon[5];
    this.IIconBrokenArray = new IIcon[5];
    this.IIconNormalArray[0] = par1IIconRegister.registerIcon("morefunquicksandmod:clay0_0");
    this.IIconNormalArray[1] = par1IIconRegister.registerIcon("morefunquicksandmod:clay0_1");
    this.IIconNormalArray[2] = par1IIconRegister.registerIcon("morefunquicksandmod:clay0_2");
    this.IIconNormalArray[3] = par1IIconRegister.registerIcon("morefunquicksandmod:clay0_3");
    this.IIconNormalArray[4] = par1IIconRegister.registerIcon("morefunquicksandmod:clay0_4");
    this.IIconBrokenArray[0] = par1IIconRegister.registerIcon("morefunquicksandmod:clay1");
    this.IIconBrokenArray[1] = par1IIconRegister.registerIcon("morefunquicksandmod:clay2");
    this.IIconBrokenArray[2] = par1IIconRegister.registerIcon("morefunquicksandmod:clay3");
    this.IIconSideNormal = par1IIconRegister.registerIcon("morefunquicksandmod:clay4");
    this.IIconSideBroken = par1IIconRegister.registerIcon("morefunquicksandmod:clay5");
  }
}
