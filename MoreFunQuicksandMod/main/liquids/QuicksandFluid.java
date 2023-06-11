package MoreFunQuicksandMod.main.liquids;

import net.minecraftforge.fluids.Fluid;

public class QuicksandFluid extends Fluid {
  public QuicksandFluid() {
    super("JungleQuicksand");
    setTemperature(298);
    setDensity(3000);
    setViscosity(9000);
  }
}
