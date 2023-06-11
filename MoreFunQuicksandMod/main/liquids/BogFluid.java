package MoreFunQuicksandMod.main.liquids;

import net.minecraftforge.fluids.Fluid;

public class BogFluid extends Fluid {
  public BogFluid() {
    super("Bog");
    setDensity(2500);
    setViscosity(8500);
    setTemperature(288);
  }
}
