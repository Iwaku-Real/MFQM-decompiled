package MoreFunQuicksandMod.main.liquids;

import net.minecraftforge.fluids.Fluid;

public class MireFluid extends Fluid {
  public MireFluid(String name) {
    super(name);
    setTemperature(288);
    setDensity(1000);
    setViscosity(7500);
  }
}
