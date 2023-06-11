package MoreFunQuicksandMod.main.client;

import MoreFunQuicksandMod.main.entity.EntityTarSlime;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderTarSlime extends RenderLiving {
  private static final ResourceLocation slimeTextures = new ResourceLocation("morefunquicksandmod", "textures/entity/TarSlime.png");
  
  private ModelBase scaleAmount;
  
  private int texType;
  
  public RenderTarSlime(ModelBase p_i1267_1_, ModelBase p_i1267_2_, float p_i1267_3_) {
    super(p_i1267_1_, p_i1267_3_);
    this.scaleAmount = p_i1267_2_;
  }
  
  protected int shouldRenderPass(EntityTarSlime p_77032_1_, int p_77032_2_, float p_77032_3_) {
    if (p_77032_1_.isInvisible())
      return 0; 
    if (p_77032_2_ == 0) {
      setRenderPassModel(this.scaleAmount);
      GL11.glEnable(2977);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      return 1;
    } 
    if (p_77032_2_ == 1) {
      GL11.glDisable(3042);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    } 
    return -1;
  }
  
  protected void preRenderCallback(EntityTarSlime p_77041_1_, float p_77041_2_) {
    float var3 = p_77041_1_.getSlimeSize();
    float var4 = (p_77041_1_.prevSquishFactor + (p_77041_1_.squishFactor - p_77041_1_.prevSquishFactor) * p_77041_2_) / (var3 * 0.5F + 1.0F);
    float var5 = 1.0F / (var4 + 1.0F);
    float scaleFactor = 1.25F;
    GL11.glScalef(var5 * var3 * scaleFactor, 0.75F / var5 * var3 * scaleFactor, var5 * var3 * scaleFactor);
  }
  
  protected ResourceLocation getEntityTexture(EntityTarSlime p_110775_1_) {
    return slimeTextures;
  }
  
  protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_) {
    preRenderCallback((EntityTarSlime)p_77041_1_, p_77041_2_);
  }
  
  protected int shouldRenderPass(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_) {
    return shouldRenderPass((EntityTarSlime)p_77032_1_, p_77032_2_, p_77032_3_);
  }
  
  protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
    return getEntityTexture((EntityTarSlime)p_110775_1_);
  }
}
