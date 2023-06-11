package MoreFunQuicksandMod.main;

import MoreFunQuicksandMod.main.entity.EntityRope;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;

public class RopeMessage implements IMessage {
  private float Len;
  
  public RopeMessage() {}
  
  public RopeMessage(float len) {
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
      if (MFQM.arrayRopes[i] != null && 
        MFQM.arrayPlayers[i] == us)
        ((EntityRope)MFQM.arrayRopes[i]).curLen = len; 
    } 
  }
  
  public static class Handler implements IMessageHandler<RopeMessage, IMessage> {
    public IMessage onMessage(RopeMessage message, MessageContext ctx) {
      RopeMessage.handleCurLen(message.Len, (ctx.getServerHandler()).playerEntity);
      return null;
    }
  }
}
