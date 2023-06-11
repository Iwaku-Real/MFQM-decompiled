package MoreFunQuicksandMod.main;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

public class MuddyEvent {
  @SubscribeEvent
  public void onEntityConstructing(EntityEvent.EntityConstructing event) {
    if (event.entity instanceof EntityPlayer && 
      CustomVarPlayer.get((EntityPlayer)event.entity) == null)
      CustomVarPlayer.register((EntityPlayer)event.entity); 
  }
  
  @SubscribeEvent
  public void onLivingDeathEvent(LivingDeathEvent event) {
    if (!event.entityLiving.worldObj.isRemote && 
      event.entityLiving instanceof EntityPlayer) {
      CustomVarPlayer props = CustomVarPlayer.get((EntityPlayer)event.entityLiving);
      props.setMuddyLevel(0);
      props.setMuddyType(-1);
      props.setMuddyTime(0);
    } 
  }
  
  @SubscribeEvent
  public void onLivingUpdateEvent(LivingEvent.LivingUpdateEvent event) {
    if (!event.entityLiving.worldObj.isRemote && 
      event.entityLiving instanceof EntityPlayer) {
      CustomVarPlayer props = CustomVarPlayer.get((EntityPlayer)event.entityLiving);
      int ml = props.getMuddyLevel();
      int mtp = props.getMuddyType();
      int mt = props.getMuddyTime();
      if (mtp >= 0 && 
        mt > 0)
        if (event.entityLiving.isWet()) {
          if (!MFQM.isEntityInsideOfBlockL((Entity)event.entityLiving, MFQM.LiquidMireBlock) && 
            !MFQM.isEntityInsideOfBlockL((Entity)event.entityLiving, MFQM.StableLiquidMireBlock) && 
            props.getMuddyTime() > 0) {
            props.addMuddyTime(-5 - props.getMuddyTime() / 100);
            props.setMuddyTime(Math.max(props.getMuddyTime(), 0));
            if (event.entityLiving.worldObj.getTotalWorldTime() % 16L == 0L && 
              ml > 5)
              props.setMuddyLevel(ml - 1); 
          } 
        } else {
          if (event.entityLiving.worldObj.getTotalWorldTime() % 1024L == 0L && 
            ml > 5 && 
            mt < 1000)
            props.setMuddyLevel(ml - 1); 
          if (event.entityLiving.worldObj.getTotalWorldTime() % 2L == 0L && 
            props.getMuddyTime() > MFQM.getLastMuddyType(mtp))
            props.addMuddyTime(-1); 
        }  
    } 
  }
}
