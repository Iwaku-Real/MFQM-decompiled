package MoreFunQuicksandMod.main.items;

import MoreFunQuicksandMod.main.MFQM;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemGasMask extends ItemArmor {
  public ItemGasMask(ItemArmor.ArmorMaterial armorMaterial, int renderIndex, int armorType) {
    super(armorMaterial, renderIndex, armorType);
    setUnlocalizedName("GasMask");
    setMaxDurability(0);
    setCreativeTab(MFQM.tabMFQM);
  }
  
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
    return "morefunquicksandmod:textures/armor/gasmask.png";
  }
  
  public void registerIcons(IIconRegister iconRegister) {
    this.itemIcon = iconRegister.registerIcon("morefunquicksandmod:GasMask");
  }
  
  public void addInformation(ItemStack item, EntityPlayer player, List<String> list, boolean bool) {
    list.add(StatCollector.translateToLocal("itemGasMask.instruction"));
  }
}
