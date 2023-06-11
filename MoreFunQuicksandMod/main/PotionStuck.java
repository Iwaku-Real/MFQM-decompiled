package MoreFunQuicksandMod.main;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class PotionStuck implements IExtendedEntityProperties {
  public static final String EXT_PROP_NAME = "MiredStuckEffect";
  
  public static final int MUDDYLEVEL_WATCHER = MFQM.MuddyLevel;
  
  private final EntityLivingBase entLiv;
  
  private int Level;
  
  private int TarTreads;
  
  public PotionStuck(EntityLivingBase entLiv) {
    this.entLiv = entLiv;
    this.Level = -1;
    this.TarTreads = 0;
  }
  
  public static final void register(EntityLivingBase entLiv) {
    entLiv.registerExtendedProperties("MiredStuckEffect", new PotionStuck(entLiv));
  }
  
  public static final PotionStuck get(EntityLivingBase entLiv) {
    return (PotionStuck)entLiv.getExtendedProperties("MiredStuckEffect");
  }
  
  public void saveNBTData(NBTTagCompound compound) {}
  
  public void loadNBTData(NBTTagCompound compound) {}
  
  public void init(Entity entity, World world) {}
  
  public void DoInit() {
    this.Level = -1;
    this.TarTreads = 0;
  }
  
  public int getLevel() {
    return this.Level;
  }
  
  public int getTarTreads() {
    return this.TarTreads;
  }
  
  public void setTarTreads(int tt) {
    this.TarTreads = tt;
  }
  
  public void setStuckLevel(int lvl) {
    this.Level = lvl;
  }
}
