package MoreFunQuicksandMod.main;

import MoreFunQuicksandMod.main.entity.EntityRope;
import MoreFunQuicksandMod.main.items.ItemLongStick;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ReportedException;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

public class AirEvent {
  @SubscribeEvent
  public void onEntityConstructing(EntityEvent.EntityConstructing event) {
    if (event.entity instanceof EntityPlayer) {
      if (!MFQM.QSAir)
        return; 
    } else if (!MFQM.QSMAir) {
      return;
    } 
    if (event.entity instanceof net.minecraft.entity.EntityLivingBase)
      if (event.entity instanceof EntityPlayer) {
        try {
          event.entity.getDataWatcher().addObject(MFQM.MuddyAir, Integer.valueOf(300));
        } catch (Exception e) {
          if (MFQM.MuddyAirEcp < 5) {
            MFQM.MuddyAirEcp++;
            CrashReport var6 = CrashReport.makeCrashReport(e, "Error initializing Oxygen System. Muddy Air DataWatcher ID conflict: " + MFQM.MuddyAir + " with entity player ");
            throw new ReportedException(var6);
          } 
        } 
      } else {
        try {
          event.entity.getDataWatcher().addObject(MFQM.MuddyMobsAir, Integer.valueOf(300));
        } catch (Exception e) {
          if (MFQM.MuddyMobsAirEcp < 5) {
            MFQM.MuddyMobsAirEcp++;
            CrashReport var6 = CrashReport.makeCrashReport(e, "Error initializing Oxygen System. Muddy Mobs Air DataWatcher ID conflict: " + MFQM.MuddyMobsAir + " with entity living ");
            throw new ReportedException(var6);
          } 
        } 
      }  
  }
  
  @SubscribeEvent
  public void onLivingUpdateEvent(LivingEvent.LivingUpdateEvent event) {
    if (event.entityLiving instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer)event.entityLiving;
      if (player.worldObj.isRemote)
        if (player != (Minecraft.getMinecraft()).thePlayer) {
          if (player.getCurrentEquippedItem() != null && 
            player.getCurrentEquippedItem().getItem() == MFQM.LongStickItem) {
            ItemStack item = player.getCurrentEquippedItem();
            if (player.isUsingItem()) {
              boolean IsSucStr = false;
              int UC = player.getItemInUseCount();
              int U_MAX = item.getMaxItemUseDuration();
              int U_AL = 100;
              int U_HE = U_MAX - U_AL;
              int U_CRT = (int)Math.floor((item.getMaxItemUseDuration() / 2));
              int U_HV = U_CRT - (int)Math.floor((U_AL / 4));
              int U_VH = U_MAX - (int)Math.floor((U_AL / 4));
              int U_VE = U_CRT - U_AL;
              if (ItemLongStick.CheckQS(player.worldObj, player) > 1)
                IsSucStr = true; 
              if (IsSucStr) {
                if (UC < U_CRT)
                  if (UC > U_HV) {
                    player.clearItemInUse();
                    player.setItemInUse(item, U_HE - 1);
                  } else {
                    player.clearItemInUse();
                    player.setItemInUse(item, U_MAX);
                  }  
              } else {
                if (UC < 2) {
                  player.clearItemInUse();
                  player.setItemInUse(item, U_VE - 1);
                } 
                if (UC > U_VH) {
                  player.clearItemInUse();
                  player.setItemInUse(item, U_VE - 1);
                } else if (UC > U_CRT) {
                  player.clearItemInUse();
                  player.setItemInUse(item, U_CRT);
                } 
              } 
            } 
          } 
        } else {
          MFQM.SIRenderYawPre = MFQM.SIRenderYaw;
          MFQM.SIRenderYaw = event.entityLiving.renderYawOffset;
        }  
      if (!MFQM.QSAir)
        return; 
    } else if (!MFQM.QSMAir) {
      return;
    } 
    if (event.entityLiving.worldObj.isRemote) {
      int gma = MFQM.getMuddyAir((Entity)event.entityLiving);
      if (gma < event.entityLiving.getAir())
        event.entityLiving.setAir(gma); 
    } else if (event.entityLiving instanceof net.minecraft.entity.EntityLivingBase) {
      int gma = MFQM.getMuddyAir((Entity)event.entityLiving);
      for (int i = 0; i < EntityRope.quicksandIDS.length; i++) {
        if (i != 6 && i != 7 && i != 13 && i != 15 && i != 21)
          if (i == 22) {
            if (MFQM.isEntityInsideOfBlockM((Entity)event.entityLiving, EntityRope.quicksandIDS[i])) {
              if (gma > -1)
                MFQM.addMuddyAir((Entity)event.entityLiving, -1); 
              return;
            } 
          } else if (MFQM.isEntityInsideOfBlock((Entity)event.entityLiving, EntityRope.quicksandIDS[i])) {
            if (gma > -1)
              MFQM.addMuddyAir((Entity)event.entityLiving, -1); 
            return;
          }  
      } 
      if (gma < 300)
        MFQM.addMuddyAir((Entity)event.entityLiving, Math.min(5, 300 - gma)); 
    } 
  }
  
  @SubscribeEvent
  public void onLivingHurt(LivingAttackEvent event) {
    if (MFQM.WLD && 
      event.entityLiving instanceof EntityPlayer && 
      event.source.getDamageType().equals("Taint"))
      for (int i = 0; i <= 8; i++) {
        if (((EntityPlayer)event.entityLiving).inventory.mainInventory[i] != null && 
          MFQM.WLDDruidPouchItem != null && 
          Item.getIdFromItem(((EntityPlayer)event.entityLiving).inventory.mainInventory[i].getItem()) == Item.getIdFromItem(MFQM.WLDDruidPouchItem) && 
          ((EntityPlayer)event.entityLiving).inventory.mainInventory[i].getMetadata() < ((EntityPlayer)event.entityLiving).inventory.mainInventory[i].getMaxDurability()) {
          if (((EntityPlayer)event.entityLiving).worldObj.getTotalWorldTime() % 2L == 0L)
            ((EntityPlayer)event.entityLiving).inventory.mainInventory[i].damageItem(1, event.entityLiving); 
          event.setCanceled(true);
          return;
        } 
      }  
  }
}
