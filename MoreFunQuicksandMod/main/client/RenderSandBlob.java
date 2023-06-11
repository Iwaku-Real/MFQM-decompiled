package MoreFunQuicksandMod.main.client;

import MoreFunQuicksandMod.main.entity.EntitySandBlob;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderSandBlob extends RenderLiving {
  private static final ResourceLocation slimeTextures = new ResourceLocation("morefunquicksandmod", "textures/entity/SandyBlob.png");
  
  private ModelBase scaleAmount;
  
  public RenderSandBlob(ModelBase p_i1267_1_, ModelBase p_i1267_2_, float p_i1267_3_) {
    super(p_i1267_1_, p_i1267_3_);
    this.scaleAmount = p_i1267_2_;
  }
  
  protected int shouldRenderPass(EntitySandBlob p_77032_1_, int p_77032_2_, float p_77032_3_) {
    if (p_77032_1_.isInvisible())
      return 0; 
    if (p_77032_2_ == 0) {
      float var4 = p_77032_1_.ticksExisted + p_77032_3_;
      GL11.glMatrixMode(5890);
      GL11.glLoadIdentity();
      float uh = var4 * 0.0025F + MathHelper.cos(var4 * 0.02F) * 0.005F;
      float vv = -var4 * 0.005F;
      GL11.glTranslatef(uh, vv, 0.0F);
      setRenderPassModel(this.scaleAmount);
      GL11.glMatrixMode(5888);
      GL11.glEnable(2977);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      return 1;
    } 
    if (p_77032_2_ == 1) {
      GL11.glDisable(3042);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    } 
    if (p_77032_2_ == 2) {
      GL11.glMatrixMode(5890);
      GL11.glLoadIdentity();
      GL11.glMatrixMode(5888);
      GL11.glEnable(2896);
      GL11.glDisable(3042);
    } 
    return -1;
  }
  
  protected void preRenderCallback(EntitySandBlob p_77041_1_, float p_77041_2_) {
    float var3 = p_77041_1_.getSlimeSize();
    float var4 = (p_77041_1_.prevSquishFactor + (p_77041_1_.squishFactor - p_77041_1_.prevSquishFactor) * p_77041_2_) / (var3 * 0.5F + 1.0F);
    float var5 = 1.0F / (var4 + 1.0F);
    float scaleFactor = 1.1F;
    GL11.glScalef(var5 * var3 * scaleFactor, 0.75F / var5 * var3 * scaleFactor, var5 * var3 * scaleFactor);
  }
  
  protected ResourceLocation getEntityTexture(EntitySandBlob p_110775_1_) {
    return slimeTextures;
  }
  
  protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_) {
    preRenderCallback((EntitySandBlob)p_77041_1_, p_77041_2_);
  }
  
  protected int shouldRenderPass(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_) {
    return shouldRenderPass((EntitySandBlob)p_77032_1_, p_77032_2_, p_77032_3_);
  }
  
  protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
    return getEntityTexture((EntitySandBlob)p_110775_1_);
  }
}
