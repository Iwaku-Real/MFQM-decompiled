package MoreFunQuicksandMod.main.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderBee extends RenderLiving {
  public RenderBee() {
    super(new ModelBee(), 0.2375F);
    this.shadowSize = 0.0F;
  }
  
  protected void preRenderCallback(EntityLivingBase entity, float partialTickTime) {
    GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
    GL11.glTranslatef(0.0F, 0.7125F, 0.0F);
    GL11.glScalef(0.95F, 0.95F, 0.95F);
  }
  
  protected ResourceLocation getEntityTexture(Entity entity) {
    return new ResourceLocation("morefunquicksandmod:textures/entity/Bee.png");
  }
}
