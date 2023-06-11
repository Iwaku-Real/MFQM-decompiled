package MoreFunQuicksandMod.main;

import MoreFunQuicksandMod.main.entity.EntityRescue;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

public class HandEvent {
  @SubscribeEvent
  public void onEntityInteractEvent(EntityInteractEvent event) {
    if (event.entityPlayer instanceof net.minecraft.entity.player.EntityPlayer && 
      !event.entityPlayer.worldObj.isRemote) {
      ItemStack curItm = event.entityPlayer.getCurrentEquippedItem();
      if (curItm == null && 
        event.target instanceof EntityLivingBase) {
        double dis = Math.pow(Math.pow(event.target.posX - event.entityPlayer.posX, 2.0D) + Math.pow(event.target.posY - event.entityPlayer.posY, 2.0D) + Math.pow(event.target.posZ - event.entityPlayer.posZ, 2.0D), 0.5D);
        if (dis < 2.5D) {
          int inqs = EntityRescue.isInsideOfQuicksand((EntityLivingBase)event.target);
          if (event.target instanceof net.minecraft.entity.player.EntityPlayer || event.entityPlayer.isSneaking()) {
            World world = event.entityPlayer.worldObj;
            int ropeNum = event.entityPlayer.inventory.currentItem;
            event.entityPlayer.inventory.setInventorySlotContents(ropeNum, new ItemStack(MFQM.RescueItem, 1));
            EntityRescue rescue = new EntityRescue(world, event.entityPlayer);
            world.spawnEntityInWorld((Entity)rescue);
            rescue.hitted = true;
            rescue.curGrabbed = (EntityLivingBase)event.target;
            rescue.motionX = 0.0D;
            rescue.motionY = 0.0D;
            rescue.motionZ = 0.0D;
            rescue.posX = rescue.curGrabbed.posX;
            rescue.posY = rescue.curGrabbed.boundingBox.minY + rescue.curGrabbed.height * 0.75D;
            rescue.posZ = rescue.curGrabbed.posZ;
            rescue.BlockPosX = rescue.posX;
            rescue.BlockPosY = rescue.posY;
            rescue.BlockPosZ = rescue.posZ;
            if (!(rescue.curGrabbed instanceof net.minecraft.entity.player.EntityPlayer)) {
              rescue.GrabType = 5;
              rescue.curGrabbed.setRevengeTarget((EntityLivingBase)event.entityPlayer);
            } else {
              rescue.GrabType = 6;
            } 
            if (rescue.getMoCreatureValid(rescue.curGrabbed)) {
              rescue.riddenByEntity = (Entity)rescue.curGrabbed;
              rescue.mountEntity((Entity)rescue.curGrabbed);
            } 
            rescue.curLen = dis;
            rescue.beginCurLen = rescue.curLen;
            rescue.GrabSound();
            rescue.setWatcherGrabbed();
            rescue.setWatcherLenMP();
            return;
          } 
        } 
      } 
    } 
  }
}
