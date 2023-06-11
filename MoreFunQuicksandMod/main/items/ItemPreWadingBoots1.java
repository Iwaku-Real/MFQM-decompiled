package MoreFunQuicksandMod.main.items;

import MoreFunQuicksandMod.main.MFQM;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemPreWadingBoots1 extends ItemArmor {
  public int textureID = 0;
  
  public ItemPreWadingBoots1(ItemArmor.ArmorMaterial armorMaterial, int renderIndex, int armorType) {
    super(armorMaterial, renderIndex, armorType);
    this.textureID = armorType;
    setUnlocalizedName("preWadingBoots1");
    setMaxDurability(0);
    setCreativeTab(MFQM.tabMFQM);
  }
  
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
    if (stack.getItem() == MFQM.PreWadingBoots1)
      return "morefunquicksandmod:textures/armor/prewadingboots1.png"; 
    return null;
  }
  
  public void registerIcons(IIconRegister iconRegister) {
    if (this.textureID == 3)
      this.itemIcon = iconRegister.registerIcon("morefunquicksandmod:prewadingboots1"); 
  }
}
