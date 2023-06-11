package MoreFunQuicksandMod.main.liquids;

import net.minecraftforge.fluids.Fluid;

public class TarFluid extends Fluid {
  public TarFluid() {
    super("Tar");
    setDensity(5000);
    setViscosity(10000);
    setTemperature(345);
  }
}
