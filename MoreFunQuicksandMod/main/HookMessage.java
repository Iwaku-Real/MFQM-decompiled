package MoreFunQuicksandMod.main;

import MoreFunQuicksandMod.main.entity.EntityHook;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;

public class HookMessage implements IMessage {
  private float Len;
  
  public HookMessage() {}
  
  public HookMessage(float len) {
    this.Len = len;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.Len = buf.readFloat();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeFloat(this.Len);
  }
  
  public static void handleCurLenHook(float len, EntityPlayerMP player) {
    String us = player.getCommandSenderName();
    for (int i = 0; i < 64; i++) {
      if (MFQM.arrayHooks[i] != null && 
        MFQM.arrayPlayersHooks[i] == us)
        ((EntityHook)MFQM.arrayHooks[i]).curLen = len; 
    } 
  }
  
  public static class Handler implements IMessageHandler<HookMessage, IMessage> {
    public IMessage onMessage(HookMessage message, MessageContext ctx) {
      HookMessage.handleCurLenHook(message.Len, (ctx.getServerHandler()).playerEntity);
      return null;
    }
  }
}
