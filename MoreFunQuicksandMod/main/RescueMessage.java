package MoreFunQuicksandMod.main;

import MoreFunQuicksandMod.main.entity.EntityRescue;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;

public class RescueMessage implements IMessage {
  private float Len;
  
  public RescueMessage() {}
  
  public RescueMessage(float len) {
    this.Len = len;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.Len = buf.readFloat();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeFloat(this.Len);
  }
  
  public static void handleCurLen(float len, EntityPlayerMP player) {
    String us = player.getCommandSenderName();
    for (int i = 0; i < 64; i++) {
      if (MFQM.arrayRescue[i] != null && 
        MFQM.arrayPlayersRescue[i] == us)
        ((EntityRescue)MFQM.arrayRescue[i]).curLen = len; 
    } 
  }
  
  public static class Handler implements IMessageHandler<RescueMessage, IMessage> {
    public IMessage onMessage(RescueMessage message, MessageContext ctx) {
      RescueMessage.handleCurLen(message.Len, (ctx.getServerHandler()).playerEntity);
      return null;
    }
  }
}
