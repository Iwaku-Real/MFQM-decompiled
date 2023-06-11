package MoreFunQuicksandMod.main.items;

import MoreFunQuicksandMod.main.MFQM;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemPreWadingBoots0 extends ItemArmor {
  public int textureID = 0;
  
  public ItemPreWadingBoots0(ItemArmor.ArmorMaterial armorMaterial, int renderIndex, int armorType) {
    super(armorMaterial, renderIndex, armorType);
    this.textureID = armorType;
    setUnlocalizedName("preWadingBoots0");
    setMaxDurability(0);
    setCreativeTab(MFQM.tabMFQM);
  }
  
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
    if (stack.getItem() == MFQM.PreWadingBoots0)
      return "morefunquicksandmod:textures/armor/prewadingboots0.png"; 
    return null;
  }
  
  public void registerIcons(IIconRegister iconRegister) {
    if (this.textureID == 3)
      this.itemIcon = iconRegister.registerIcon("morefunquicksandmod:prewadingboots0"); 
  }
}
