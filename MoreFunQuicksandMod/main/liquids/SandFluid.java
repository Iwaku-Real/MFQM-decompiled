package MoreFunQuicksandMod.main.liquids;

import net.minecraftforge.fluids.Fluid;

public class SandFluid extends Fluid {
  public SandFluid() {
    super("SandFluid");
    setTemperature(318);
    setDensity(500);
    setViscosity(1000);
  }
}
