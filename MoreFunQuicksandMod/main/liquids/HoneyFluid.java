package MoreFunQuicksandMod.main.liquids;

import net.minecraftforge.fluids.Fluid;

public class HoneyFluid extends Fluid {
  public HoneyFluid() {
    super("Honey");
    setDensity(3500);
    setViscosity(9000);
    setTemperature(303);
  }
}
