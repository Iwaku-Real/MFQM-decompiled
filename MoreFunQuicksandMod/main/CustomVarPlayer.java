package MoreFunQuicksandMod.main;

import net.minecraft.crash.CrashReport;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ReportedException;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class CustomVarPlayer implements IExtendedEntityProperties {
  public static final String EXT_PROP_NAME = "PlayerMuddyControl";
  
  public static final int MUDDYLEVEL_WATCHER = MFQM.MuddyLevel;
  
  private final EntityPlayer player;
  
  public CustomVarPlayer(EntityPlayer player) {
    this.player = player;
    try {
      this.player.getDataWatcher().addObject(MUDDYLEVEL_WATCHER, Integer.valueOf(0));
    } catch (Exception e) {
      CrashReport var6 = CrashReport.makeCrashReport(e, "Error initializing Covering System. Muddy Level DataWatcher ID conflict: " + MFQM.MuddyLevel + " with entity player ");
      throw new ReportedException(var6);
    } 
  }
  
  public static final void register(EntityPlayer player) {
    player.registerExtendedProperties("PlayerMuddyControl", new CustomVarPlayer(player));
  }
  
  public static final CustomVarPlayer get(EntityPlayer player) {
    return (CustomVarPlayer)player.getExtendedProperties("PlayerMuddyControl");
  }
  
  public void saveNBTData(NBTTagCompound compound) {
    NBTTagCompound properties = new NBTTagCompound();
    properties.setInteger("MuddyLevel", this.player.getDataWatcher().getWatchableObjectInt(MUDDYLEVEL_WATCHER));
    compound.setTag("PlayerMuddyControl", (NBTBase)properties);
  }
  
  public void loadNBTData(NBTTagCompound compound) {
    NBTTagCompound properties = (NBTTagCompound)compound.getTag("PlayerMuddyControl");
    this.player.getDataWatcher().updateObject(MUDDYLEVEL_WATCHER, Integer.valueOf(properties.getInteger("MuddyLevel")));
  }
  
  public void init(Entity entity, World world) {}
  
  public int getData() {
    return this.player.getDataWatcher().getWatchableObjectInt(MUDDYLEVEL_WATCHER);
  }
  
  public int getMuddyLevel() {
    return getData() / 256 & 0xFF;
  }
  
  public int getMuddyTime() {
    return getData() / 65536;
  }
  
  public int getMuddyType() {
    return getData() & 0xFF;
  }
  
  public void setMuddyLevel(int lvl) {
    int dt = getData();
    this.player.getDataWatcher().updateObject(MUDDYLEVEL_WATCHER, Integer.valueOf(dt / 65536 * 65536 | lvl * 256 | dt & 0xFF));
  }
  
  public void setMuddyTime(int tm) {
    this.player.getDataWatcher().updateObject(MUDDYLEVEL_WATCHER, Integer.valueOf(getData() & 0xFFFF | tm * 65536));
  }
  
  public void setMuddyType(int tp) {
    this.player.getDataWatcher().updateObject(MUDDYLEVEL_WATCHER, Integer.valueOf(getData() / 256 * 256 | tp));
  }
  
  public void addMuddyTime(int tm) {
    int dt = getData();
    this.player.getDataWatcher().updateObject(MUDDYLEVEL_WATCHER, Integer.valueOf(dt & 0xFFFF | (tm + dt / 65536) * 65536));
  }
}
