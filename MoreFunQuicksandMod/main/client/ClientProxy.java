package MoreFunQuicksandMod.main.client;

import MoreFunQuicksandMod.main.CommonProxy;
import MoreFunQuicksandMod.main.MFQM;
import MoreFunQuicksandMod.main.Tileentity.TileEntityBlossom;
import MoreFunQuicksandMod.main.Tileentity.TileEntityBlossomRenderer;
import MoreFunQuicksandMod.main.Tileentity.TileEntityLarvae;
import MoreFunQuicksandMod.main.Tileentity.TileEntityLarvaeRenderer;
import MoreFunQuicksandMod.main.Tileentity.TileEntityMeat;
import MoreFunQuicksandMod.main.Tileentity.TileEntityMeatRenderer;
import MoreFunQuicksandMod.main.entity.EntityBee;
import MoreFunQuicksandMod.main.entity.EntityBubble;
import MoreFunQuicksandMod.main.entity.EntityHook;
import MoreFunQuicksandMod.main.entity.EntityMudTentacles;
import MoreFunQuicksandMod.main.entity.EntityMuddyBlob;
import MoreFunQuicksandMod.main.entity.EntityRope;
import MoreFunQuicksandMod.main.entity.EntitySandBlob;
import MoreFunQuicksandMod.main.entity.EntitySlimeHole;
import MoreFunQuicksandMod.main.entity.EntityTarSlime;
import MoreFunQuicksandMod.main.entity.EntityTarTreads;
import MoreFunQuicksandMod.main.entity.EntityTentacles;
import MoreFunQuicksandMod.main.entity.EntityVoreSlime;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {
  public void registerRenderInformation() {
    RenderingRegistry.registerEntityRenderingHandler(EntityRope.class, new RenderFish2());
    System.out.println("(MFQM)Init rope render");
    RenderingRegistry.registerEntityRenderingHandler(EntityHook.class, new RenderFish3());
    System.out.println("(MFQM)Init hook render");
    MinecraftForgeClient.registerItemRenderer(MFQM.LongStickItem, new RenderLongStick());
    System.out.println("(MFQM)Init long stick render");
    RenderingRegistry.registerEntityRenderingHandler(EntitySlimeHole.class, new RenderSlimeHole());
    System.out.println("(MFQM)Init slime hole render");
    RenderingRegistry.registerEntityRenderingHandler(EntityTentacles.class, new RenderTentacles(0));
    System.out.println("(MFQM)Init tentacles render");
    RenderingRegistry.registerEntityRenderingHandler(EntityMudTentacles.class, new RenderTentacles(1));
    System.out.println("(MFQM)Init mud tentacles render");
    RenderingRegistry.registerEntityRenderingHandler(EntityVoreSlime.class, (Render)new RenderVoreSlime(new ModelVoreSlime(16), new ModelVoreSlime(0), 0.25F));
    System.out.println("(MFQM)Init vore slime render");
    RenderingRegistry.registerEntityRenderingHandler(EntityMuddyBlob.class, (Render)new RenderMuddyBlob(new ModelVoreSlime(16), new ModelVoreSlime(0), 0.25F));
    System.out.println("(MFQM)Init muddy blob render");
    RenderingRegistry.registerEntityRenderingHandler(EntityTarSlime.class, (Render)new RenderTarSlime(new ModelVoreSlime(16), new ModelVoreSlime(0), 0.25F));
    System.out.println("(MFQM)Init tar slime render");
    RenderingRegistry.registerEntityRenderingHandler(EntitySandBlob.class, (Render)new RenderSandBlob(new ModelVoreSlime(16), new ModelVoreSlime(0), 0.25F));
    System.out.println("(MFQM)Init sandy blob render");
    RenderingRegistry.registerEntityRenderingHandler(EntityBee.class, (Render)new RenderBee());
    System.out.println("(MFQM)Init bee render");
    ClientRegistry.registerTileEntity(TileEntityLarvae.class, "TileEntityLarvae", (TileEntitySpecialRenderer)new TileEntityLarvaeRenderer());
    System.out.println("(MFQM)Init Larvae render");
    ClientRegistry.registerTileEntity(TileEntityMeat.class, "TileEntityMeat", (TileEntitySpecialRenderer)new TileEntityMeatRenderer());
    System.out.println("(MFQM)Init Meat wall render");
    ClientRegistry.registerTileEntity(TileEntityBlossom.class, "TileEntityBlossom", (TileEntitySpecialRenderer)new TileEntityBlossomRenderer());
    System.out.println("(MFQM)Init Plant wall render");
    RenderingRegistry.registerEntityRenderingHandler(EntityBubble.class, new RenderBubble());
    System.out.println("(MFQM)Init bubble render");
    RenderingRegistry.registerEntityRenderingHandler(EntityTarTreads.class, new RenderTarTreads());
    System.out.println("(MFQM)Init tar treads render");
  }
  
  public int addArmor(String armor) {
    return RenderingRegistry.addNewArmourRendererPrefix(armor);
  }
}
