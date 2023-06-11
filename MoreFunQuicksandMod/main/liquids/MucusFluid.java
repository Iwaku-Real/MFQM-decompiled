package MoreFunQuicksandMod.main.liquids;

import net.minecraftforge.fluids.Fluid;

public class MucusFluid extends Fluid {
  public MucusFluid() {
    super("MucusFluid");
    setDensity(6000);
    setViscosity(15000);
    setTemperature(301);
  }
}
