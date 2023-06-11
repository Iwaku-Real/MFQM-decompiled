package MoreFunQuicksandMod.main;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class CustomMaterialLiquid extends Material {
  public CustomMaterialLiquid(MapColor par1MapColor) {
    super(par1MapColor);
    setNoPushMobility();
  }
  
  public boolean isLiquid() {
    return true;
  }
  
  public boolean blocksMovement() {
    return true;
  }
  
  public boolean isSolid() {
    return false;
  }
}
