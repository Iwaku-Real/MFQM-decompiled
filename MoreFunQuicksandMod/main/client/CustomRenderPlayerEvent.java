package MoreFunQuicksandMod.main.client;

import MoreFunQuicksandMod.main.Fields;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;

public class CustomRenderPlayerEvent {
  @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
  public void preRenderPlayer(RenderPlayerEvent.Pre event) {
    if ((event.entityPlayer != (Minecraft.getMinecraft()).thePlayer || (event.entityPlayer == 
      (Minecraft.getMinecraft()).thePlayer && 
      (Minecraft.getMinecraft()).gameSettings.thirdPersonView != 0)) && 
      checkPlayerMuddyModel(event.renderer, event.entityPlayer))
      registerPlayerMuddyModel(event.renderer, event.entityPlayer); 
  }
  
  @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
  public void handRenderPlayer(RenderHandEvent event) {
    if (checkPlayerMuddyModel((RenderPlayer)RenderManager.instance.getEntityRenderObject((Entity)(Minecraft.getMinecraft()).thePlayer), (EntityPlayer)(Minecraft.getMinecraft()).thePlayer))
      registerPlayerMuddyModel((RenderPlayer)RenderManager.instance.getEntityRenderObject((Entity)(Minecraft.getMinecraft()).thePlayer), (EntityPlayer)(Minecraft.getMinecraft()).thePlayer); 
  }
  
  public boolean turnOnRenderMuddyModel(RenderPlayer renderer, EntityPlayer entityPlayer) {
    List<ModelRenderer> list = new ArrayList<ModelRenderer>();
    for (Object o : renderer.modelBipedMain.boxList) {
      if (o instanceof ModelRenderer)
        list.add((ModelRenderer)o); 
    } 
    for (ModelRenderer model : list)
      turnOnChilds(model, entityPlayer); 
    return true;
  }
  
  public boolean turnOnRenderMuddyModelFirstPerson(RenderPlayer renderer, EntityPlayer entityPlayer) {
    turnOnChilds(renderer.modelBipedMain.bipedRightArm, entityPlayer);
    return true;
  }
  
  public boolean registerPlayerMuddyModel(RenderPlayer renderer, EntityPlayer entityPlayer) {
    List<ModelRenderer> list = new ArrayList<ModelRenderer>();
    for (Object o : renderer.modelBipedMain.boxList) {
      if (o instanceof ModelRenderer && 
        !(o instanceof MudModelRenderer))
        list.add((ModelRenderer)o); 
    } 
    for (ModelRenderer model : list)
      scanChilds(model, entityPlayer); 
    System.out.println("(MFQM)REGISTERED CUSTOM MODEL to PLAYER: " + entityPlayer);
    return true;
  }
  
  public boolean checkPlayerMuddyModel(RenderPlayer renderer, EntityPlayer entityPlayer) {
    int isFailed = 0;
    int result = 0;
    List<?> boxList = new ArrayList(renderer.modelBipedMain.boxList);
    for (Object o : renderer.modelBipedMain.boxList) {
      if (o instanceof ModelRenderer) {
        isFailed = checkPlayerMuddyModelChild((ModelRenderer)o, entityPlayer);
        if (isFailed > result)
          result = isFailed; 
        if (isFailed == 2) {
          if (o instanceof MudModelRenderer)
            boxList.remove(o); 
          continue;
        } 
        turnOnChilds((ModelRenderer)o, entityPlayer);
      } 
    } 
    if (result == 0)
      return true; 
    if (result == 1)
      return false; 
    renderer.modelBipedMain.boxList = new ArrayList(boxList);
    for (Object o : renderer.modelBipedMain.boxList) {
      if (o instanceof ModelRenderer)
        deleteChilds((ModelRenderer)o, entityPlayer); 
    } 
    System.out.println("(MFQM)DELETED CUSTOM MODEL from PLAYER: " + entityPlayer);
    return true;
  }
  
  public int checkPlayerMuddyModelChild(ModelRenderer model, EntityPlayer ply) {
    int isFailed = 0;
    if (model != null) {
      if (model.childModels != null)
        for (Object o : model.childModels) {
          if (o instanceof ModelRenderer) {
            isFailed = checkPlayerMuddyModelChild((ModelRenderer)o, ply);
            if (isFailed == 2)
              return 2; 
          } 
        }  
      if (model instanceof MudModelRenderer && 
        ((MudModelRenderer)model).currentPlayerNick == ply.getCommandSenderName()) {
        if (((MudModelRenderer)model).world != ply.worldObj)
          return 2; 
        if (((MudModelRenderer)model).currentPlayerUUID != ply.getUniqueID())
          return 2; 
        if (((MudModelRenderer)model).currentPlayer != ply)
          return 2; 
        return 1;
      } 
    } 
    return isFailed;
  }
  
  public void scanChilds(ModelRenderer model, EntityPlayer ply) {
    if (model != null) {
      if (model.childModels != null && 
        !(model instanceof MudModelRenderer))
        for (Object o : model.childModels) {
          if (o instanceof ModelRenderer && 
            !(o instanceof MudModelRenderer))
            scanChilds((ModelRenderer)o, ply); 
        }  
      for (Object o : model.cubeList) {
        if (!(o instanceof MudModelRenderer) && 
          o instanceof ModelBox) {
          ModelBox box = (ModelBox)o;
          try {
            Field field0 = Fields.findField(ModelRenderer.class, ModelBase.class, 0);
            field0.setAccessible(true);
            ModelBase mainMdl = (ModelBase)field0.get(model);
            Field field1 = Fields.findField(ModelRenderer.class, int.class, 0);
            field1.setAccessible(true);
            int xx = field1.getInt(model);
            Field field2 = Fields.findField(ModelRenderer.class, int.class, 1);
            field2.setAccessible(true);
            int yy = field2.getInt(model);
            MudModelRenderer mm = new MudModelRenderer(mainMdl, xx, yy, ply);
            mm.addBox(box.posX1, box.posY1, box.posZ1, (int)(box.posX2 - box.posX1), (int)(box.posY2 - box.posY1), (int)(box.posZ2 - box.posZ1), 0.1F);
            model.addChild(mm);
            mm.eventShow = false;
          } catch (Exception e) {
            System.out.println("(MFQM)Cant get muddy model!");
            e.printStackTrace();
          } 
        } 
      } 
    } 
  }
  
  public void deleteChilds(ModelRenderer model, EntityPlayer ply) {
    if (model != null && 
      model.childModels != null) {
      List<?> childBoxList = new ArrayList(model.childModels);
      for (Object o : model.childModels) {
        if (o instanceof MudModelRenderer && 
          ((MudModelRenderer)o).currentPlayerNick == ply.getCommandSenderName())
          childBoxList.remove(o); 
        if (o instanceof ModelRenderer)
          deleteChilds((ModelRenderer)o, ply); 
      } 
      model.childModels = new ArrayList(childBoxList);
    } 
  }
  
  public void turnOnChilds(ModelRenderer model, EntityPlayer ply) {
    if (model != null) {
      if (model.childModels != null)
        for (Object o : model.childModels) {
          if (o instanceof ModelRenderer)
            turnOnChilds((ModelRenderer)o, ply); 
        }  
      if (model instanceof MudModelRenderer)
        if (((MudModelRenderer)model).currentPlayer == ply) {
          ((MudModelRenderer)model).eventShow = true;
        } else {
          ((MudModelRenderer)model).eventShow = false;
        }  
    } 
  }
}
