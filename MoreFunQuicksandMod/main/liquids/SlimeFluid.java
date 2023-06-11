package MoreFunQuicksandMod.main.liquids;

import net.minecraftforge.fluids.Fluid;

public class SlimeFluid extends Fluid {
  public SlimeFluid() {
    super("SlimeFluid");
    setDensity(6000);
    setViscosity(15000);
    setTemperature(288);
  }
}
