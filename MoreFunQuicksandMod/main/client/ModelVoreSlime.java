package MoreFunQuicksandMod.main.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelVoreSlime extends ModelBase {
  ModelRenderer slimeBodies;
  
  public ModelVoreSlime(int p_i1157_1_) {
    this.slimeBodies = new ModelRenderer(this, 0, p_i1157_1_);
    if (p_i1157_1_ == 0) {
      this.slimeBodies.addBox(-4.0F, 16.0F, -4.0F, 8, 8, 8);
      this.slimeBodies.addBox(-3.0F, 17.0F, -5.0F, 6, 6, 10);
      this.slimeBodies.addBox(-5.0F, 17.0F, -3.0F, 10, 6, 6);
      this.slimeBodies.addBox(-3.0F, 15.0F, -3.0F, 6, 10, 6);
    } 
  }
  
  public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
    setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
    this.slimeBodies.render(p_78088_7_);
  }
}
