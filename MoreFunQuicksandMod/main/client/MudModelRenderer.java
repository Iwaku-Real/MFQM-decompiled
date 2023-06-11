package MoreFunQuicksandMod.main.client;

import MoreFunQuicksandMod.main.CustomVarPlayer;
import MoreFunQuicksandMod.main.MFQM;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class MudModelRenderer extends ModelRenderer {
  public EntityPlayer currentPlayer;
  
  public UUID currentPlayerUUID;
  
  public String currentPlayerNick;
  
  public World world;
  
  public boolean eventShow = false;
  
  public MudModelRenderer(ModelBase model, String s) {
    super(model, s);
  }
  
  public MudModelRenderer(ModelBase model) {
    super(model);
  }
  
  public MudModelRenderer(ModelBase model, EntityPlayer plr) {
    this(model);
    this.currentPlayer = plr;
    this.currentPlayerUUID = plr.getUniqueID();
    this.currentPlayerNick = this.currentPlayer.getCommandSenderName();
    this.world = this.currentPlayer.worldObj;
  }
  
  public MudModelRenderer(ModelBase model, int x, int y, EntityPlayer plr) {
    this(model, x, y);
    this.currentPlayer = plr;
    this.currentPlayerUUID = plr.getUniqueID();
    this.currentPlayerNick = this.currentPlayer.getCommandSenderName();
    this.world = this.currentPlayer.worldObj;
  }
  
  public MudModelRenderer(ModelBase model, int x, int y) {
    super(model, x, y);
  }
  
  public void render(float f) {
    if (!this.showModel)
      return; 
    if (!this.eventShow)
      return; 
    CustomVarPlayer props = CustomVarPlayer.get(this.currentPlayer);
    int ml = props.getMuddyLevel();
    int mtp = props.getMuddyType();
    int mt = props.getMuddyTime();
    if (mt > 50 && 
      mtp >= 0 && mtp <= MFQM.mudTypesColors.length && 
      ml > 0) {
      int color = MFQM.mudTypesColors[mtp];
      float red = (color >> 16 & 0xFF) / 255.0F;
      float green = (color >> 8 & 0xFF) / 255.0F;
      float blue = (color & 0xFF) / 255.0F;
      GL11.glColor4f(red, green, blue, Math.min(1.0F, mt / 1000.0F));
      GL11.glDepthFunc(515);
      GL11.glEnable(3008);
      GL11.glEnable(3553);
      GL11.glEnable(2977);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      if (mtp == 0 || mtp == 1 || mtp == 3 || mtp == 6 || mtp == 7 || mtp == 8 || mtp == 15 || mtp == 19 || mtp == 20 || mtp == 21 || mtp == 25 || mtp == 28 || mtp == 31) {
        (Minecraft.getMinecraft()).renderEngine.bindTexture(MFQM.slimeTextures[ml - 1]);
      } else {
        (Minecraft.getMinecraft()).renderEngine.bindTexture(MFQM.mudTextures[ml - 1]);
      } 
      super.render(f);
      if (this.currentPlayer instanceof EntityClientPlayerMP) {
        (Minecraft.getMinecraft()).renderEngine.bindTexture(((EntityClientPlayerMP)this.currentPlayer).getLocationSkin());
      } else {
        (Minecraft.getMinecraft()).renderEngine.bindTexture(((EntityOtherPlayerMP)this.currentPlayer).getLocationSkin());
      } 
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDisable(2977);
      GL11.glDisable(3042);
      GL11.glDepthFunc(515);
      GL11.glEnable(3008);
      GL11.glEnable(3553);
    } 
    this.eventShow = false;
  }
}
