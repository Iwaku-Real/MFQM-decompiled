package MoreFunQuicksandMod.main;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.UUID;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.init.Items;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

public class CustomPotionEvent {
  public static final UUID stuckModifierUUID = UUID.fromString("73A49882-85A3-4896-AD2D-8C591E24AA62");
  
  @SubscribeEvent
  public void onEntityConstructing(EntityEvent.EntityConstructing event) {
    if (event.entity instanceof EntityLivingBase)
      if (PotionStuck.get((EntityLivingBase)event.entity) == null) {
        PotionStuck.register((EntityLivingBase)event.entity);
      } else {
        PotionStuck.get((EntityLivingBase)event.entity).DoInit();
      }  
  }
  
  @SubscribeEvent
  public void onLivingUpdateEvent(LivingEvent.LivingUpdateEvent event) {
    IAttributeInstance movment = event.entityLiving.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
    AttributeModifier aold = movment.getModifier(stuckModifierUUID);
    if (aold != null)
      movment.removeModifier(aold); 
    PotionStuck props = PotionStuck.get(event.entityLiving);
    if (props == null)
      return; 
    if (props.getLevel() > 0) {
      double speed, powermod = (props.getLevel() - 5);
      double corrector = 0.0D;
      if (!event.entityLiving.isCollidedVertically)
        corrector = 1.0D; 
      if (powermod < 250.0D) {
        event.entityLiving.jumpMovementFactor = (float)(event.entityLiving.jumpMovementFactor / Math.max(Math.pow(1.0D + powermod * (1.0D + corrector * 2.5D) / 1000.0D, 8.0D), 1.0D));
        speed = Math.max(1.0D / Math.pow(1.0D + powermod * (1.0D + corrector / 2.0D) / 50.0D, 2.0D), 0.02D);
      } else {
        event.entityLiving.jumpMovementFactor = 0.0F;
        speed = 0.02D;
      } 
      if (event.entityLiving.jumpMovementFactor < 0.005D)
        event.entityLiving.jumpMovementFactor = 0.0F; 
      aold = movment.getModifier(stuckModifierUUID);
      if (aold == null) {
        AttributeModifier stuckModifier = (new AttributeModifier(stuckModifierUUID, "Slowdown entity", speed - 1.0D, 2)).setSaved(false);
        movment.applyModifier(stuckModifier);
      } 
      props.setStuckLevel(0);
    } else if (props.getLevel() == 0) {
      props.setStuckLevel(-1);
    } 
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void onFOVUpdateEvent(FOVUpdateEvent event) {
    EntityPlayerSP entityPlayerSP = event.entity;
    PotionStuck props = PotionStuck.get((EntityLivingBase)entityPlayerSP);
    if (props == null) {
      MFQM.SIFOV = event.fov;
      event.newfov = event.fov;
      return;
    } 
    if (props.getLevel() >= 0) {
      event.newfov = (float)MFQM.SIFOV;
      if (entityPlayerSP.isUsingItem() && entityPlayerSP.getItemInUse().getItem() == Items.bow) {
        int var3 = entityPlayerSP.getItemInUseDuration();
        float var4 = var3 / 20.0F;
        if (var4 > 1.0F) {
          var4 = 1.0F;
        } else {
          var4 *= var4;
        } 
        event.newfov *= 1.0F - var4 * 0.15F;
      } 
    } else {
      MFQM.SIFOV = event.fov;
      event.newfov = event.fov;
    } 
  }
}
