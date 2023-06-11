package MoreFunQuicksandMod.main.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelBee extends ModelBase {
  ModelRenderer Head;
  
  ModelRenderer Right_Antenna;
  
  ModelRenderer Left_Antenna;
  
  ModelRenderer Nose;
  
  ModelRenderer Left_Wing;
  
  ModelRenderer Right_Wing;
  
  ModelRenderer Thorax;
  
  ModelRenderer Left_Leg_Back;
  
  ModelRenderer Left_Leg_Middle;
  
  ModelRenderer Left_Leg_Front;
  
  ModelRenderer Right_Leg_Back;
  
  ModelRenderer Right_Leg_Middle;
  
  ModelRenderer Right_Leg_Front;
  
  ModelRenderer Abdomen;
  
  ModelRenderer Stinger;
  
  public ModelBee() {
    this.textureWidth = 64;
    this.textureHeight = 32;
    this.Head = new ModelRenderer(this, 46, 0);
    this.Head.addBox(0.0F, 0.0F, 0.0F, 5, 5, 4);
    this.Head.setRotationPoint(0.0F, -2.0F, 8.0F);
    this.Head.setTextureSize(64, 32);
    this.Right_Antenna = new ModelRenderer(this, 54, 27);
    this.Right_Antenna.addBox(0.0F, 2.0F, -8.0F, 1, 1, 4);
    this.Right_Antenna.setRotationPoint(3.0F, -3.0F, 10.0F);
    this.Right_Antenna.setTextureSize(64, 32);
    this.Right_Antenna.mirror = true;
    this.Head.addChild(this.Right_Antenna);
    this.Left_Antenna = new ModelRenderer(this, 54, 27);
    this.Left_Antenna.addBox(0.0F, 2.0F, -8.0F, 1, 1, 4);
    this.Left_Antenna.setRotationPoint(1.0F, -3.0F, 10.0F);
    this.Left_Antenna.setTextureSize(64, 32);
    this.Left_Antenna.mirror = true;
    this.Head.addChild(this.Left_Antenna);
    this.Nose = new ModelRenderer(this, 54, 9);
    this.Nose.addBox(0.0F, 2.0F, -8.0F, 3, 4, 2);
    this.Nose.setRotationPoint(1.0F, 0.0F, 11.0F);
    this.Nose.setTextureSize(64, 32);
    this.Nose.mirror = true;
    this.Head.addChild(this.Nose);
    this.Left_Wing = new ModelRenderer(this, 24, 26);
    this.Left_Wing.addBox(-7.0F, 0.0F, 0.0F, 8, 1, 5);
    this.Left_Wing.setRotationPoint(0.0F, -1.0F, 2.0F);
    this.Left_Wing.setTextureSize(64, 32);
    this.Right_Wing = new ModelRenderer(this, 24, 20);
    this.Right_Wing.addBox(0.0F, 0.0F, 0.0F, 8, 1, 5);
    this.Right_Wing.setRotationPoint(4.0F, -1.0F, 2.0F);
    this.Right_Wing.setTextureSize(64, 32);
    this.Thorax = new ModelRenderer(this, 0, 0);
    this.Thorax.addBox(0.0F, 0.0F, 0.0F, 5, 5, 8);
    this.Thorax.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Thorax.setTextureSize(64, 32);
    this.Left_Leg_Back = new ModelRenderer(this, 13, 23);
    this.Left_Leg_Back.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1);
    this.Left_Leg_Back.setRotationPoint(-1.0F, 4.0F, 1.0F);
    this.Left_Leg_Back.setTextureSize(64, 32);
    this.Thorax.addChild(this.Left_Leg_Back);
    this.Left_Leg_Middle = new ModelRenderer(this, 13, 23);
    this.Left_Leg_Middle.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
    this.Left_Leg_Middle.setRotationPoint(-1.0F, 4.0F, 4.0F);
    this.Left_Leg_Middle.setTextureSize(64, 32);
    this.Thorax.addChild(this.Left_Leg_Middle);
    this.Left_Leg_Front = new ModelRenderer(this, 13, 23);
    this.Left_Leg_Front.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1);
    this.Left_Leg_Front.setRotationPoint(-1.0F, 4.0F, 6.0F);
    this.Left_Leg_Front.setTextureSize(64, 32);
    this.Thorax.addChild(this.Left_Leg_Front);
    this.Right_Leg_Back = new ModelRenderer(this, 13, 23);
    this.Right_Leg_Back.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1);
    this.Right_Leg_Back.setRotationPoint(5.0F, 4.0F, 1.0F);
    this.Right_Leg_Back.setTextureSize(64, 32);
    this.Thorax.addChild(this.Right_Leg_Back);
    this.Right_Leg_Middle = new ModelRenderer(this, 13, 23);
    this.Right_Leg_Middle.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
    this.Right_Leg_Middle.setRotationPoint(5.0F, 4.0F, 4.0F);
    this.Right_Leg_Middle.setTextureSize(64, 32);
    this.Thorax.addChild(this.Right_Leg_Middle);
    this.Right_Leg_Front = new ModelRenderer(this, 13, 23);
    this.Right_Leg_Front.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1);
    this.Right_Leg_Front.setRotationPoint(5.0F, 4.0F, 6.0F);
    this.Right_Leg_Front.setTextureSize(64, 32);
    this.Thorax.addChild(this.Right_Leg_Front);
    this.Abdomen = new ModelRenderer(this, 0, 13);
    this.Abdomen.addBox(0.0F, 0.0F, 0.0F, 3, 3, 2);
    this.Abdomen.setRotationPoint(1.0F, 2.0F, -2.0F);
    this.Abdomen.setTextureSize(64, 32);
    this.Stinger = new ModelRenderer(this, 0, 18);
    this.Stinger.addBox(-1.0F, -3.0F, 2.0F, 1, 1, 3);
    this.Stinger.setRotationPoint(2.0F, 4.0F, -5.0F);
    this.Stinger.setTextureSize(64, 32);
    this.Abdomen.addChild(this.Stinger);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    this.Head.render(f5);
    this.Left_Wing.render(f5);
    this.Right_Wing.render(f5);
    this.Thorax.render(f5);
    this.Abdomen.render(f5);
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    float headspeed = 0.1F * (entity.ticksExisted % 10);
    this.Head.rotateAngleX = MathHelper.sin(f2 * headspeed) * 2.5F * 3.1415927F / 180.0F;
    this.Head.rotateAngleZ = MathHelper.cos(f2 * headspeed) * 1.5F * 3.1415927F / 180.0F;
    float thoraxspeed = 0.075F * (entity.ticksExisted % 10);
    this.Thorax.rotateAngleX = MathHelper.sin(f2 * thoraxspeed) * 2.5F * 3.1415927F / 180.0F;
    this.Thorax.rotateAngleZ = MathHelper.cos(f2 * thoraxspeed) * 1.5F * 3.1415927F / 180.0F;
    this.Right_Wing.rotateAngleY = MathHelper.cos(f2 * 1.7F) * 3.1415927F * 0.25F;
    this.Left_Wing.rotateAngleY = -this.Right_Wing.rotateAngleY;
    this.Right_Wing.rotateAngleZ = this.Right_Wing.rotateAngleY;
    this.Left_Wing.rotateAngleZ = -this.Right_Wing.rotateAngleY;
    float abdomenspeed = 0.6F * (entity.ticksExisted % 10);
    this.Abdomen.rotateAngleX = MathHelper.sin(f2 * abdomenspeed) * 2.5F * 3.1415927F / 180.0F;
    this.Abdomen.rotateAngleZ = MathHelper.cos(f2 * abdomenspeed) * 1.5F * 3.1415927F / 180.0F;
  }
}
