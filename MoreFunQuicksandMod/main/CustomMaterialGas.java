package MoreFunQuicksandMod.main;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class CustomMaterialGas extends Material {
  public CustomMaterialGas(MapColor par1MapColor) {
    super(par1MapColor);
    setNoPushMobility();
    setReplaceable();
  }
  
  public boolean isSolid() {
    return false;
  }
  
  public boolean blocksLight() {
    return false;
  }
  
  public boolean blocksMovement() {
    return false;
  }
}
