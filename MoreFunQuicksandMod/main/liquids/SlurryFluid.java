package MoreFunQuicksandMod.main.liquids;

import net.minecraftforge.fluids.Fluid;

public class SlurryFluid extends Fluid {
  public SlurryFluid() {
    super("Slurry");
    setDensity(2000);
    setViscosity(8500);
    setTemperature(308);
  }
}
