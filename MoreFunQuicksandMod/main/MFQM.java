package MoreFunQuicksandMod.main;

import MoreFunQuicksandMod.main.Tileentity.TileEntityLure;
import MoreFunQuicksandMod.main.blocks.BlockBlossom;
import MoreFunQuicksandMod.main.blocks.BlockBlossomSlab;
import MoreFunQuicksandMod.main.blocks.BlockBrownClay;
import MoreFunQuicksandMod.main.blocks.BlockChocolate;
import MoreFunQuicksandMod.main.blocks.BlockCorruptedSand;
import MoreFunQuicksandMod.main.blocks.BlockCustomLilyPad;
import MoreFunQuicksandMod.main.blocks.BlockDenseWeb;
import MoreFunQuicksandMod.main.blocks.BlockGas;
import MoreFunQuicksandMod.main.blocks.BlockHardenedClay;
import MoreFunQuicksandMod.main.blocks.BlockHoneycomb;
import MoreFunQuicksandMod.main.blocks.BlockLarvae;
import MoreFunQuicksandMod.main.blocks.BlockLeaves;
import MoreFunQuicksandMod.main.blocks.BlockLure;
import MoreFunQuicksandMod.main.blocks.BlockMeat;
import MoreFunQuicksandMod.main.blocks.BlockMeatHole;
import MoreFunQuicksandMod.main.blocks.BlockMire;
import MoreFunQuicksandMod.main.blocks.BlockMoor;
import MoreFunQuicksandMod.main.blocks.BlockMoorGrass;
import MoreFunQuicksandMod.main.blocks.BlockMorass;
import MoreFunQuicksandMod.main.blocks.BlockMoss;
import MoreFunQuicksandMod.main.blocks.BlockMud;
import MoreFunQuicksandMod.main.blocks.BlockPeat;
import MoreFunQuicksandMod.main.blocks.BlockQuicksand;
import MoreFunQuicksandMod.main.blocks.BlockSinkingClay;
import MoreFunQuicksandMod.main.blocks.BlockSinkingWool;
import MoreFunQuicksandMod.main.blocks.BlockSoftGravel;
import MoreFunQuicksandMod.main.blocks.BlockSoftQuicksand;
import MoreFunQuicksandMod.main.blocks.BlockSoftSnow;
import MoreFunQuicksandMod.main.blocks.BlockSolidHoney;
import MoreFunQuicksandMod.main.blocks.BlockSwallowingFlesh;
import MoreFunQuicksandMod.main.blocks.BlockTent;
import MoreFunQuicksandMod.main.blocks.BlockVoreHole;
import MoreFunQuicksandMod.main.blocks.BlockWax;
import MoreFunQuicksandMod.main.blocks.BlockWaxWood;
import MoreFunQuicksandMod.main.blocks.BlockWetPeat;
import MoreFunQuicksandMod.main.blocks.ItemMeta;
import MoreFunQuicksandMod.main.blocks.ItemMetaColor;
import MoreFunQuicksandMod.main.blocks.ItemMud;
import MoreFunQuicksandMod.main.client.CustomRenderOverlayEvent;
import MoreFunQuicksandMod.main.client.CustomRenderPlayerEvent;
import MoreFunQuicksandMod.main.entity.EntityBee;
import MoreFunQuicksandMod.main.entity.EntityBubble;
import MoreFunQuicksandMod.main.entity.EntityHook;
import MoreFunQuicksandMod.main.entity.EntityLiqBall;
import MoreFunQuicksandMod.main.entity.EntityLongStick;
import MoreFunQuicksandMod.main.entity.EntityMudTentacles;
import MoreFunQuicksandMod.main.entity.EntityMuddyBlob;
import MoreFunQuicksandMod.main.entity.EntityRescue;
import MoreFunQuicksandMod.main.entity.EntityRope;
import MoreFunQuicksandMod.main.entity.EntitySandBlob;
import MoreFunQuicksandMod.main.entity.EntitySlimeHole;
import MoreFunQuicksandMod.main.entity.EntityTarSlime;
import MoreFunQuicksandMod.main.entity.EntityTarTreads;
import MoreFunQuicksandMod.main.entity.EntityTentacles;
import MoreFunQuicksandMod.main.entity.EntityVoreSlime;
import MoreFunQuicksandMod.main.items.ItemAcidBucket;
import MoreFunQuicksandMod.main.items.ItemBogBucket;
import MoreFunQuicksandMod.main.items.ItemCable;
import MoreFunQuicksandMod.main.items.ItemChocolateBucket;
import MoreFunQuicksandMod.main.items.ItemChocolatePowderBucket;
import MoreFunQuicksandMod.main.items.ItemClayBucket;
import MoreFunQuicksandMod.main.items.ItemCranberry;
import MoreFunQuicksandMod.main.items.ItemFertilizer;
import MoreFunQuicksandMod.main.items.ItemGasMask;
import MoreFunQuicksandMod.main.items.ItemGrapplingHook;
import MoreFunQuicksandMod.main.items.ItemGrapplingHookBRK;
import MoreFunQuicksandMod.main.items.ItemHoneyBucket;
import MoreFunQuicksandMod.main.items.ItemLifeJacket;
import MoreFunQuicksandMod.main.items.ItemLiqGun;
import MoreFunQuicksandMod.main.items.ItemLongStick;
import MoreFunQuicksandMod.main.items.ItemMireBucket;
import MoreFunQuicksandMod.main.items.ItemMucusBucket;
import MoreFunQuicksandMod.main.items.ItemMyPotion;
import MoreFunQuicksandMod.main.items.ItemPreWadingBoots0;
import MoreFunQuicksandMod.main.items.ItemPreWadingBoots1;
import MoreFunQuicksandMod.main.items.ItemQuicksandBucket;
import MoreFunQuicksandMod.main.items.ItemRescue;
import MoreFunQuicksandMod.main.items.ItemRope;
import MoreFunQuicksandMod.main.items.ItemSandBucket;
import MoreFunQuicksandMod.main.items.ItemSlimeBucket;
import MoreFunQuicksandMod.main.items.ItemSlurryBucket;
import MoreFunQuicksandMod.main.items.ItemTarBucket;
import MoreFunQuicksandMod.main.items.ItemWadingBoots;
import MoreFunQuicksandMod.main.items.ItemWaxPiece;
import MoreFunQuicksandMod.main.liquids.AcidFluid;
import MoreFunQuicksandMod.main.liquids.BlockAcid;
import MoreFunQuicksandMod.main.liquids.BlockBog;
import MoreFunQuicksandMod.main.liquids.BlockHoney;
import MoreFunQuicksandMod.main.liquids.BlockJungleQuicksand;
import MoreFunQuicksandMod.main.liquids.BlockLiquidChocolate;
import MoreFunQuicksandMod.main.liquids.BlockLiquidMire;
import MoreFunQuicksandMod.main.liquids.BlockMucus;
import MoreFunQuicksandMod.main.liquids.BlockSand;
import MoreFunQuicksandMod.main.liquids.BlockSlime;
import MoreFunQuicksandMod.main.liquids.BlockSlurry;
import MoreFunQuicksandMod.main.liquids.BlockStableLiquidMire;
import MoreFunQuicksandMod.main.liquids.BlockTar;
import MoreFunQuicksandMod.main.liquids.BogFluid;
import MoreFunQuicksandMod.main.liquids.ChocolateFluid;
import MoreFunQuicksandMod.main.liquids.HoneyFluid;
import MoreFunQuicksandMod.main.liquids.MireFluid;
import MoreFunQuicksandMod.main.liquids.MucusFluid;
import MoreFunQuicksandMod.main.liquids.QuicksandFluid;
import MoreFunQuicksandMod.main.liquids.SandFluid;
import MoreFunQuicksandMod.main.liquids.SlimeFluid;
import MoreFunQuicksandMod.main.liquids.SlurryFluid;
import MoreFunQuicksandMod.main.liquids.TarFluid;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

@Mod(modid = "MFQM", name = "MoreFunQuicksandMod", version = "1.0.8.8")
public class MFQM {
  @SidedProxy(clientSide = "MoreFunQuicksandMod.main.client.ClientProxy", serverSide = "MoreFunQuicksandMod.main.CommonProxy", modId = "MFQM")
  public static CommonProxy proxy;
  
  @Instance("MFQM")
  public static MFQM instance;
  
  public static SimpleNetworkWrapper network;
  
  public CustomRegister customRegister;
  
  public static ItemArmor.ArmorMaterial armorMaterialUnprotective = EnumHelper.addArmorMaterial("UNPROTECTIVE", -1, new int[] { 0, 0, 0, 0 }, 0);
  
  public static CreativeTabs tabMFQM;
  
  public static int eggIdCounter = 300;
  
  public static final ResourceLocation[] mudTextures = new ResourceLocation[] { new ResourceLocation("morefunquicksandmod", "textures/entity/mudOverlays/MudOverlay0.png"), new ResourceLocation("morefunquicksandmod", "textures/entity/mudOverlays/MudOverlay1.png"), new ResourceLocation("morefunquicksandmod", "textures/entity/mudOverlays/MudOverlay2.png"), new ResourceLocation("morefunquicksandmod", "textures/entity/mudOverlays/MudOverlay3.png"), new ResourceLocation("morefunquicksandmod", "textures/entity/mudOverlays/MudOverlay4.png"), new ResourceLocation("morefunquicksandmod", "textures/entity/mudOverlays/MudOverlay5.png"), new ResourceLocation("morefunquicksandmod", "textures/entity/mudOverlays/MudOverlay6.png"), new ResourceLocation("morefunquicksandmod", "textures/entity/mudOverlays/MudOverlay7.png"), new ResourceLocation("morefunquicksandmod", "textures/entity/mudOverlays/MudOverlay8.png"), new ResourceLocation("morefunquicksandmod", "textures/entity/mudOverlays/MudOverlay9.png") };
  
  public static final ResourceLocation[] slimeTextures = new ResourceLocation[] { new ResourceLocation("morefunquicksandmod", "textures/entity/mudOverlays/SlimeOverlay0.png"), new ResourceLocation("morefunquicksandmod", "textures/entity/mudOverlays/SlimeOverlay1.png"), new ResourceLocation("morefunquicksandmod", "textures/entity/mudOverlays/SlimeOverlay2.png"), new ResourceLocation("morefunquicksandmod", "textures/entity/mudOverlays/SlimeOverlay3.png"), new ResourceLocation("morefunquicksandmod", "textures/entity/mudOverlays/SlimeOverlay4.png"), new ResourceLocation("morefunquicksandmod", "textures/entity/mudOverlays/SlimeOverlay5.png"), new ResourceLocation("morefunquicksandmod", "textures/entity/mudOverlays/SlimeOverlay6.png"), new ResourceLocation("morefunquicksandmod", "textures/entity/mudOverlays/SlimeOverlay7.png"), new ResourceLocation("morefunquicksandmod", "textures/entity/mudOverlays/SlimeOverlay8.png"), new ResourceLocation("morefunquicksandmod", "textures/entity/mudOverlays/SlimeOverlay9.png") };
  
  public static final int[] mudTypesColors = new int[] { 
      4538917, 14611967, 16777215, 2431764, 6444596, 3089167, 2431764, 2431764, 15007713, 2431764, 
      2431764, 10197137, 1973277, 16777215, 16777215, 16777189, 16777215, 6180923, 14803425, 16777189, 
      14013598, 16777189, 7041868, 10056782, 8554890, 2431764, 12298319, 3806471, 5720614, 2431764, 
      5389343, 16758567 };
  
  public static final int[] mudMaxOpacity = new int[] { 
      2000, 500, 0, 1000, 5000, 5000, 500, 500, 500, 5000, 
      5000, 5000, 10000, 0, 0, 250, 0, 2000, 3000, 250, 
      500, 250, 1000, 5000, 5000, 1000, 10000, 7000, 2000, 2000, 
      5000, 600 };
  
  public static final int[] mudLastOpacity = new int[] { 
      500, 0, 0, 750, 750, 750, 250, 250, 0, 750, 
      750, 750, 1000, 0, 0, 0, 0, 500, 0, 0, 
      0, 0, 0, 750, 750, 750, 1000, 1000, 750, 750, 
      750, 600 };
  
  public static final int[] mudIncOpacity = new int[] { 
      50, 25, 0, 100, 100, 100, 50, 50, 50, 100, 
      100, 100, 500, 0, 0, 25, 0, 100, 75, 25, 
      50, 25, 100, 100, 100, 100, 500, 500, 50, 100, 
      100, 50 };
  
  public static int BootsID;
  
  public static int BOPMudBlockID;
  
  public static int MudBlockID;
  
  public static int AshBlockID;
  
  public static int FleshBlockID;
  
  public static int HiveBlockID;
  
  public static int HoneyBlockID;
  
  public static int SolidHoneyBlockID;
  
  public static int TBLMudBlockID;
  
  public static int TBLTarBlockID;
  
  public static int TBLSolidTarBlockID;
  
  public static int TBLSludgeBlockID;
  
  public static int ABCSludgeBlockID;
  
  public static int WLDAshBlockID;
  
  public static int WLDSwampBlockID;
  
  public static int AOAToxicBlockID;
  
  public static int FleshItemID;
  
  public static int FoodItemID;
  
  public static int FertilizerItemID;
  
  public static int WLDDruidPouchItemID;
  
  public static int AOAFaceMaskItemID;
  
  public static int AOADoomStoneItemID;
  
  public static int AOAToxicLumpItemID;
  
  public static int MireBucketItemID;
  
  public static int BogBucketItemID;
  
  public static int QuicksandBucketItemID;
  
  public static int SandBucketItemID;
  
  public static int TarBucketItemID;
  
  public static int SlimeBucketItemID;
  
  public static int AcidBucketItemID;
  
  public static int RopeItemID;
  
  public static int CableItemID;
  
  public static int CoilItemID;
  
  public static int HookItemID;
  
  public static int GrapplingHookItemID;
  
  public static int GrapplingHookBRKItemID;
  
  public static Entity[] arrayRopes = new Entity[64];
  
  public static String[] arrayPlayers = new String[64];
  
  public static Entity[] arrayHooks = new Entity[64];
  
  public static String[] arrayPlayersHooks = new String[64];
  
  public static Entity[] arrayRescue = new Entity[64];
  
  public static String[] arrayPlayersRescue = new String[64];
  
  public static int LarvaRawItemID;
  
  public static int LarvaCookedItemID;
  
  public static int CberryItemID;
  
  public static int MudBallID;
  
  public static boolean GenMud;
  
  public static boolean GenMire;
  
  public static boolean GenDeep;
  
  public static boolean GenLiqMire;
  
  public static boolean GenMoor;
  
  public static boolean GenBog;
  
  public static boolean GenMorass;
  
  public static boolean GenQS;
  
  public static boolean GenSQS;
  
  public static boolean GenSQSF;
  
  public static boolean GenJQS;
  
  public static boolean GenSSand;
  
  public static boolean GenSSnow;
  
  public static boolean GenHClay;
  
  public static boolean GenHPClay;
  
  public static boolean GenSClay;
  
  public static boolean GenWebU;
  
  public static boolean GenWeb;
  
  public static boolean GenLarv;
  
  public static boolean GenTar;
  
  public static boolean GenSlime;
  
  public static boolean GenCSand;
  
  public static boolean GenMeat;
  
  public static boolean GenMeatS;
  
  public static boolean GenMeatAss;
  
  public static boolean GenMucBl;
  
  public static boolean GenMoss;
  
  public static boolean GenBClay;
  
  public static boolean GenMClay;
  
  public static boolean GenWax;
  
  public static boolean GenHoney;
  
  public static boolean GenHoney2;
  
  public static boolean GenGP;
  
  public static boolean GenWastePit;
  
  public static boolean GenMarshOver;
  
  public static boolean GenWaterColor;
  
  public static boolean GenDesertTombs;
  
  public static boolean InitMobs;
  
  public static boolean SpawnVS;
  
  public static boolean SpawnMB;
  
  public static boolean SpawnSB;
  
  public static boolean SpawnTS;
  
  public static boolean TurnBoots;
  
  public static boolean TurnStick;
  
  public static boolean TurnRope;
  
  public static boolean TurnHook;
  
  public static boolean TurnJacket;
  
  public static boolean TurnGasMask;
  
  public static boolean TurnPotion;
  
  public static boolean TurnLiqGun;
  
  public static boolean CFR;
  
  public static boolean BoP;
  
  public static boolean PBoP;
  
  public static boolean CWG;
  
  public static int AltitudeShift;
  
  public static boolean TF;
  
  public static int TFDim = -999999;
  
  public static boolean TBL;
  
  public static int TBLDim = -999999;
  
  public static boolean ABC;
  
  public static int ABCDim1 = -999999;
  
  public static int ABCDim2 = -999999;
  
  public static int ABCDim3 = -999999;
  
  public static int ABCDim4 = -999999;
  
  public static boolean WLD;
  
  public static int WLDDim = -999999;
  
  public static boolean AOA;
  
  public static int AOADimHeaven = -999999;
  
  public static int AOADimAbyss = -999999;
  
  public static int AOADimDeeplands = -999999;
  
  public static int AOADimPrecasia = -999999;
  
  public static int AOADimLunalus = -999999;
  
  public static int AOADimIromine = -999999;
  
  public static int AOADimGardencia = -999999;
  
  public static int AOADimGreckon = -999999;
  
  public static int AOADimLBorean = -999999;
  
  public static int AOADimBarathos = -999999;
  
  public static int AOADimDustopia = -999999;
  
  public static int AOADimVoxPonds = -999999;
  
  public static int AOADimLandsLelyetia = -999999;
  
  public static int AOADimCeleve = -999999;
  
  public static int AOADimCreeponia = -999999;
  
  public static int AOADimShyrelands = -999999;
  
  public static int AOADimCrystevia = -999999;
  
  public static int AOADimCandyland = -999999;
  
  public static boolean TBL_MIC = false;
  
  public static boolean ABC_MIC = false;
  
  public static boolean WLD_MIC = false;
  
  public static boolean AOA_MIC = false;
  
  public static ArrayList<Class> TBLMobs;
  
  public static ArrayList<Class> TBLMobsTar;
  
  public static Class TBLTarBeast;
  
  public static boolean HookAsRider;
  
  public static boolean QSOpacity;
  
  public static boolean QSThirdPerson;
  
  public static boolean QSCover;
  
  public static boolean QSTarTreads;
  
  public static boolean QSAir;
  
  public static boolean QSMAir;
  
  public static boolean QSCAir;
  
  public static boolean QSBubble;
  
  public static boolean QSGas;
  
  public static boolean QSTentacles;
  
  public static boolean QSMudTentacles;
  
  public static boolean QSHandRescue;
  
  public static boolean QSBootsCalc;
  
  public static boolean QSArmorCalc;
  
  public static boolean QSWeightCalc;
  
  public static boolean QSHotTar;
  
  public static int[] SwampBiomes = new int[30];
  
  public static int[] TarBiomes = new int[5];
  
  public static int[] QSJungleBiomes = new int[1];
  
  public static int[] SQSBiomes = new int[10];
  
  public static int[] JungleBiomes = new int[5];
  
  public static int[] DesertBiomes = new int[5];
  
  public static int[] SnowBiomes = new int[5];
  
  public static int[] LarvBiomes = new int[5];
  
  public static int[] CorruptedSands = new int[1];
  
  public static int[] BloodHeaps = new int[1];
  
  public static int[] PeatBogBiomes = new int[7];
  
  public static int[] MineralBiomes = new int[6];
  
  public static int[] WastelandBiomes = new int[1];
  
  public static int[] ABCBiomes = new int[13];
  
  public static int[] WLDBiomes = new int[7];
  
  public static int[] AOABiomes = new int[48];
  
  public static Block AshBlock;
  
  public static Block FleshBlock;
  
  public static Block MudBlock;
  
  public static Block HiveBlock;
  
  public static Block BOPMudBlock;
  
  public static Block BOPHoneyBlock;
  
  public static Block BOPSolidHoneyBlock;
  
  public static Block TBLMudBlock;
  
  public static Block TBLTarBlock;
  
  public static Block TBLSolidTarBlock;
  
  public static Block TBLSludgeBlock;
  
  public static Block ABCSludgeBlock;
  
  public static Block WLDAshBlock;
  
  public static Block WLDSwampBlock;
  
  public static Block AOAToxicBlock;
  
  public static Block MireBlock;
  
  public static Block MoorBlock;
  
  public static Block MorassBlock;
  
  public static Block MoorGrassBlock;
  
  public static Block CustomLilyPadBlock;
  
  public static Block LiquidMireBlock;
  
  public static Block StableLiquidMireBlock;
  
  public static Block BogBlock;
  
  public static Block SlurryBlock;
  
  public static Block GasBlock;
  
  public static Block JungleQuicksandBlock;
  
  public static Block SandBlock;
  
  public static Block TarBlock;
  
  public static Block SlimeBlock;
  
  public static Block QuicksandBlock;
  
  public static Block SoftSnowBlock;
  
  public static Block HClayBlock;
  
  public static Block SClayBlock;
  
  public static Block LarvaeBlock;
  
  public static Block DenseWebBlock;
  
  public static Block CSandBlock;
  
  public static Block MeatBlock;
  
  public static Block MeatHoleBlock;
  
  public static Block AcidBlock;
  
  public static Block SinkingWoolBlock;
  
  public static Block SoftQuicksandBlock;
  
  public static Block SwFleshBlock;
  
  public static Block TentBlock;
  
  public static Block MucusBlock;
  
  public static Block BlossomBlock;
  
  public static Block BlossomSlabBlock;
  
  public static Block LureBlock;
  
  public static Block VoreHoleBlock;
  
  public static Block MossBlock;
  
  public static Block BrownClayBlock;
  
  public static Block WetPeatBlock;
  
  public static Block PeatBlock;
  
  public static Block WaxBlock;
  
  public static Block SoftGravelBlock;
  
  public static Block LiquidChocolateBlock;
  
  public static Block ChocolateBlock;
  
  public static Block HoneyBlock;
  
  public static Block SolidHoneyBlock;
  
  public static Block HoneycombBlock;
  
  public static Block WaxWoodBlock;
  
  public static Block LeavesBlock;
  
  public static final Fluid MireFluid = (Fluid)new MireFluid("LiquidMire");
  
  public static final Fluid MireFluidS = (Fluid)new MireFluid("LiquidMireS");
  
  public static final Fluid BogFluid = (Fluid)new BogFluid();
  
  public static final Fluid SlurryFluid = (Fluid)new SlurryFluid();
  
  public static final Fluid QuicksandFluid = (Fluid)new QuicksandFluid();
  
  public static final Fluid SandFluid = (Fluid)new SandFluid();
  
  public static final Fluid TarFluid = (Fluid)new TarFluid();
  
  public static final Fluid SlimeFluid = (Fluid)new SlimeFluid();
  
  public static final Fluid AcidFluid = (Fluid)new AcidFluid();
  
  public static final Fluid MucusFluid = (Fluid)new MucusFluid();
  
  public static final Fluid ChocolateFluid = (Fluid)new ChocolateFluid();
  
  public static final Fluid HoneyFluid = (Fluid)new HoneyFluid();
  
  public static Item CustFishRod;
  
  public static Item MudBall;
  
  public static Item FleshItem;
  
  public static Item FoodItem;
  
  public static Item IC2FertilizerItem;
  
  public static Item WLDDruidPouchItem;
  
  public static Item AOAFaceMaskItem;
  
  public static Item AOADoomStoneItem;
  
  public static Item AOAToxicLumpItem;
  
  public static Item MireBucketItem;
  
  public static Item BogBucketItem;
  
  public static Item QuicksandBucketItem;
  
  public static Item SandBucketItem;
  
  public static Item TarBucketItem;
  
  public static Item SlimeBucketItem;
  
  public static Item AcidBucketItem;
  
  public static Item MucusBucketItem;
  
  public static Item ClayBucketItem;
  
  public static Item ChocolateBucketItem;
  
  public static Item ChocolatePowderBucketItem;
  
  public static Item SlurryBucketItem;
  
  public static Item HoneyBucketItem;
  
  public static Item RescueItem;
  
  public static Item RopeItem;
  
  public static Item CableItem;
  
  public static Item CoilItem;
  
  public static Item HookItem;
  
  public static Item FilterItem;
  
  public static Item GasMaskItem;
  
  public static Item GrapplingHookItem;
  
  public static Item GrapplingHookBRKItem;
  
  public static Item LiqGunItem;
  
  public static Item MyPotionItem;
  
  public static Item PeatItem;
  
  public static Item WaxItem;
  
  public static Item HoneycombItem;
  
  public static Item EmptycombItem;
  
  public static Item FertilizerItem;
  
  public static ItemFood LarvaRawItem;
  
  public static ItemFood LarvaCookedItem;
  
  public static Item CberryItem;
  
  public static Item DonutItem0;
  
  public static Item DonutItem1;
  
  public static Item DonutItem2;
  
  public static Item DonutItem3;
  
  public static Item DonutItem4;
  
  public static Item LongStickItem;
  
  public static Item LifeJacketItem;
  
  public static Item WadingBoots;
  
  public static Item PreWadingBoots0;
  
  public static Item PreWadingBoots1;
  
  public static int MuddyLevel;
  
  public static int MuddyAir;
  
  public static int MuddyMobsAir;
  
  public static int MuddyLevelEcp = 0;
  
  public static int MuddyAirEcp = 0;
  
  public static int MuddyMobsAirEcp = 0;
  
  public static double SIWeight = 0.0D;
  
  public static double SIFOV = 1.0D;
  
  public static double SIRenderYaw = 0.0D;
  
  public static double SIRenderYawPre = 0.0D;
  
  @EventHandler
  public void preInit(FMLPreInitializationEvent event) {
    network = NetworkRegistry.INSTANCE.newSimpleChannel("MFQM_REC");
    network.registerMessage(RopeMessage.Handler.class, RopeMessage.class, 0, Side.SERVER);
    network.registerMessage(HookMessage.Handler.class, HookMessage.class, 1, Side.SERVER);
    network.registerMessage(RescueMessage.Handler.class, RescueMessage.class, 2, Side.SERVER);
    this.customRegister = new CustomRegister();
    tabMFQM = new CreativeTabsMFQM(CreativeTabs.getNextID(), "tabMFQM");
    MinecraftForge.EVENT_BUS.register(new CustomPotionEvent());
    Configuration config = new Configuration(new File(event.getModConfigurationDirectory() + "/MFQM.cfg"));
    config.load();
    BoP = config.get("Mods", "Checking BiomesOPlenty", true).getBoolean(false);
    PBoP = config.get("Mods", "Forced using default world", true).getBoolean(false);
    TF = config.get("Mods", "Checking TwilightForest", true).getBoolean(false);
    TBL = config.get("Mods", "Checking TheBetweenLands", true).getBoolean(false);
    ABC = config.get("Mods", "Checking TheAbyssalCraft", true).getBoolean(false);
    WLD = config.get("Mods", "Checking Wildycraft", true).getBoolean(false);
    AOA = config.get("Mods", "Checking AdventOfAscension", true).getBoolean(false);
    CWG = config.get("Mods", "Forced custom world gen", false).getBoolean(false);
    GenMud = config.get("Terrain Generator", "Generate Mud in swamps", true).getBoolean(false);
    GenMire = config.get("Terrain Generator", "Generate Mire in swamps", true).getBoolean(false);
    GenDeep = config.get("Terrain Generator", "Generate Deep mud in swamps", true).getBoolean(false);
    GenLiqMire = config.get("Terrain Generator", "Generate Liquid Mire in swamps", true).getBoolean(false);
    GenMoor = config.get("Terrain Generator", "Generate Moor in marsh", true).getBoolean(false);
    GenBog = config.get("Terrain Generator", "Generate Bog in swamps", true).getBoolean(false);
    GenMorass = config.get("Terrain Generator", "Generate Peat Bogs", true).getBoolean(false);
    GenJQS = config.get("Terrain Generator", "Generate Quicksand in jungle", true).getBoolean(false);
    GenQS = config.get("Terrain Generator", "Generate Quicksand in desert", true).getBoolean(false);
    GenSQS = config.get("Terrain Generator", "Generate Soft Quicksand in jungle", true).getBoolean(false);
    GenSQSF = config.get("Terrain Generator", "Generate rare Soft Quicksand in forest", true).getBoolean(false);
    GenSSand = config.get("Terrain Generator", "Generate Dry Quicksand pits in desert", true).getBoolean(false);
    GenSSnow = config.get("Terrain Generator", "Generate Soft Snow", true).getBoolean(false);
    GenHClay = config.get("Terrain Generator", "Generate Hardened Clay", true).getBoolean(false);
    GenHPClay = config.get("Terrain Generator", "Generate Hardened Clay path in jungle", true).getBoolean(false);
    GenSClay = config.get("Terrain Generator", "Generate Sinking Clay pits", true).getBoolean(false);
    GenLarv = config.get("Terrain Generator", "Generate Larvae Pits", true).getBoolean(false);
    GenWeb = config.get("Terrain Generator", "Generate Webbing Nest", true).getBoolean(false);
    GenWebU = config.get("Terrain Generator", "Generate Spider Spawner in nests", true).getBoolean(false);
    GenTar = config.get("Terrain Generator", "Generate Tar pits", true).getBoolean(false);
    GenSlime = config.get("Terrain Generator", "Generate Sinking Slime pits", true).getBoolean(false);
    GenCSand = config.get("Terrain Generator", "Generate Corrupted Sands in Nether", true).getBoolean(false);
    GenMeat = config.get("Terrain Generator", "Generate Fleshy Pits in Nether", true).getBoolean(false);
    GenMeatS = config.get("Terrain Generator", "Generate Swallowing Flesh in Nether", true).getBoolean(false);
    GenMeatAss = config.get("Terrain Generator", "Generate Waste pits in Nether", true).getBoolean(false);
    GenMucBl = config.get("Terrain Generator", "Generate Mucus Blossom in jungle and roofed forest", true).getBoolean(false);
    GenMoss = config.get("Terrain Generator", "Generate Tangleroot Moss in swamps", true).getBoolean(false);
    GenBClay = config.get("Terrain Generator", "Generate Brown Clay pits", true).getBoolean(false);
    GenMClay = config.get("Terrain Generator", "Generate Mineral Clay pits", true).getBoolean(false);
    GenWax = config.get("Terrain Generator", "Generate Wax Trees in jungle", true).getBoolean(false);
    GenHoney = config.get("Terrain Generator", "Generate Bee Hives", true).getBoolean(false);
    GenHoney2 = config.get("Terrain Generator", "Generate new Honey in Nether", true).getBoolean(false);
    GenGP = config.get("Terrain Generator", "Generate natural gravel pits", true).getBoolean(false);
    GenWastePit = config.get("Terrain Generator", "Generate slurry pits in Wasteland", true).getBoolean(false);
    AltitudeShift = config.get("Terrain Generator", "Water level (62 Default, 144 TerraFirmaCraft)", 62).getInt(62);
    GenMarshOver = config.get("Terrain Generator", "Reforming marsh (BoP only)", true).getBoolean(false);
    GenWaterColor = config.get("Terrain Generator", "Customize water color in swamps", true).getBoolean(false);
    GenDesertTombs = config.get("Terrain Generator", "Generate Desert Tombs", true).getBoolean(false);
    InitMobs = config.get("Mobs spawn", "Add custom Slimes", true).getBoolean(true);
    SpawnVS = config.get("Mobs spawn", "Spawn Vore Slime in caves", true).getBoolean(false);
    SpawnMB = config.get("Mobs spawn", "Spawn Muddy Blob in swamps", true).getBoolean(false);
    SpawnSB = config.get("Mobs spawn", "Spawn Sandy Blob in desert", true).getBoolean(false);
    SpawnTS = config.get("Mobs spawn", "Spawn Tar Slime near tar pits", true).getBoolean(false);
    TurnBoots = config.get("Items receipts", "Add wading boots", true).getBoolean(true);
    TurnStick = config.get("Items receipts", "Add long stick", true).getBoolean(true);
    TurnRope = config.get("Items receipts", "Add rope", true).getBoolean(true);
    TurnHook = config.get("Items receipts", "Add grappling hook", true).getBoolean(true);
    TurnJacket = config.get("Items receipts", "Add life jacket", true).getBoolean(true);
    TurnGasMask = config.get("Items receipts", "Add gasmask", true).getBoolean(true);
    TurnPotion = config.get("Items receipts", "Add sinking potion", true).getBoolean(true);
    TurnLiqGun = config.get("Items receipts", "Add liquid gun", true).getBoolean(true);
    QSHotTar = config.get("Options", "Make Tar been hot", true).getBoolean(true);
    QSTentacles = config.get("Options", "Add tentacles in fleshy pits", true).getBoolean(false);
    QSMudTentacles = config.get("Options", "Add tentacles in mud and quicksand pits", true).getBoolean(false);
    QSGas = config.get("Options", "Allow gasify for Slurry", true).getBoolean(false);
    QSBubble = config.get("Options", "Spawn bubbles on surface of quicksands", true).getBoolean(false);
    QSTarTreads = config.get("Options", "Add tar threads effect", true).getBoolean(false);
    HookAsRider = config.get("Options", "Hooks used ride script", true).getBoolean(false);
    QSOpacity = config.get("Options", "Makes Quicksands is opacity", false).getBoolean(false);
    QSThirdPerson = config.get("Options", "Force camera to firstperson in deep", true).getBoolean(false);
    QSCover = config.get("Options", "Covering player with mud", true).getBoolean(false);
    QSHandRescue = config.get("Options", "Allow players rescue each other by hands", true).getBoolean(false);
    QSAir = config.get("Options", "More realistic suffocation players", true).getBoolean(false);
    QSMAir = config.get("Options", "More realistic suffocation mobs", true).getBoolean(false);
    QSBootsCalc = config.get("Options", "More realistic boots relations", true).getBoolean(false);
    QSArmorCalc = config.get("Options", "More realistic armor relations", true).getBoolean(false);
    QSWeightCalc = config.get("Options", "Calculate inventory weight", true).getBoolean(false);
    QSCAir = config.get("Options", "Use custom Air HUD", true).getBoolean(false);
    MuddyLevel = config.get("Options", "Muddy Level Datawatcher", 25).getInt(25);
    MuddyAir = config.get("Options", "Muddy Air Datawatcher", 29, "Must be different in range between 20 and 31").getInt(29);
    MuddyMobsAir = config.get("Options", "Muddy Mobs Air Datawatcher", 29).getInt(29);
    config.save();
    if (QSCover && (
      MuddyLevel < 20 || MuddyLevel > 31)) {
      MuddyLevel = 25;
      System.out.println("(MFQM)Incorrect MuddyLevel DataWatcher, changed to default");
    } 
    if (QSAir) {
      if (MuddyAir < 20 || MuddyAir > 31) {
        MuddyAir = 29;
        System.out.println("(MFQM)Incorrect MuddyAir DataWatcher, changed to default");
      } 
      if (MuddyLevel == MuddyAir) {
        MuddyLevel = 25;
        MuddyAir = 29;
        System.out.println("(MFQM)Conflict MuddyLevel and MuddyAir DataWatchers, changed to default");
      } 
    } else {
      QSCAir = false;
    } 
    if (QSMAir && (
      MuddyMobsAir < 20 || MuddyMobsAir > 31)) {
      MuddyMobsAir = 29;
      System.out.println("(MFQM)Incorrect MuddyMobsAir DataWatchers, changed to default");
    } 
    if (BoP)
      if (Loader.isModLoaded("BiomesOPlenty")) {
        Configuration config2 = new Configuration(new File(event.getModConfigurationDirectory() + "/biomesoplenty/ids.cfg"));
        SwampBiomes[0] = config2.get("biome ids", "Bayou ID", -1).getInt();
        SwampBiomes[1] = config2.get("biome ids", "Blessed Bog (Promised Land) ID", -1).getInt();
        SwampBiomes[2] = config2.get("biome ids", "Bog ID", -1).getInt();
        SwampBiomes[3] = config2.get("biome ids", "Dead Swamp ID", -1).getInt();
        SwampBiomes[4] = config2.get("biome ids", "Fen ID", -1).getInt();
        SwampBiomes[5] = config2.get("biome ids", "Lush Swamp ID", -1).getInt();
        SwampBiomes[6] = config2.get("biome ids", "Mangrove ID", -1).getInt();
        SwampBiomes[7] = config2.get("biome ids", "Marsh ID", -1).getInt();
        SwampBiomes[8] = config2.get("biome ids", "Moor ID", -1).getInt();
        SwampBiomes[9] = config2.get("biome ids", "Quagmire ID", -1).getInt();
        SwampBiomes[10] = config2.get("biome ids", "Sludgepit ID", -1).getInt();
        SwampBiomes[11] = 6;
        SwampBiomes[12] = config2.get("biome ids", "Wetland ID", -1).getInt();
        PeatBogBiomes[0] = config2.get("biome ids", "Shrubland ID", -1).getInt();
        PeatBogBiomes[1] = 1;
        PeatBogBiomes[2] = config2.get("biome ids", "Orchard ID", -1).getInt();
        PeatBogBiomes[3] = config2.get("biome ids", "Fen ID", -1).getInt();
        PeatBogBiomes[4] = 27;
        PeatBogBiomes[5] = 4;
        PeatBogBiomes[6] = config2.get("biome ids", "Woodland ID", -1).getInt();
        TarBiomes[0] = config2.get("biome ids", "Volcano ID", -1).getInt();
        TarBiomes[1] = config2.get("biome ids", "Deadlands ID", -1).getInt();
        TarBiomes[2] = config2.get("biome ids", "Ominous Woods ID", -1).getInt();
        TarBiomes[3] = config2.get("biome ids", "Phantasmagoric Inferno ID", -1).getInt();
        QSJungleBiomes[0] = config2.get("biome ids", "Tropical Rainforest ID", -1).getInt();
        DesertBiomes[0] = config2.get("biome ids", "Brushland ID", -1).getInt();
        DesertBiomes[1] = config2.get("biome ids", "Xeric Shrubland ID", -1).getInt();
        DesertBiomes[2] = config2.get("biome ids", "Oasis ID", -1).getInt();
        DesertBiomes[3] = 130;
        DesertBiomes[4] = 2;
        JungleBiomes[0] = 21;
        JungleBiomes[1] = config2.get("biome ids", "Lush Swamp ID", -1).getInt();
        JungleBiomes[2] = config2.get("biome ids", "Tropical Rainforest ID", -1).getInt();
        SnowBiomes[0] = config2.get("biome ids", "Arctic ID", -1).getInt();
        SnowBiomes[1] = 12;
        SQSBiomes[0] = config2.get("biome ids", "Bamboo Forest ID", -1).getInt();
        SQSBiomes[1] = config2.get("biome ids", "Boreal Forest ID", -1).getInt();
        SQSBiomes[2] = config2.get("biome ids", "Deciduous Forest ID", -1).getInt();
        SQSBiomes[3] = config2.get("biome ids", "Seasonal Forest ID", -1).getInt();
        SQSBiomes[4] = config2.get("biome ids", "Temperate Rainforest ID", -1).getInt();
        SQSBiomes[5] = config2.get("biome ids", "Woodland ID", -1).getInt();
        SQSBiomes[6] = config2.get("biome ids", "Spruce Woods ID", -1).getInt();
        SQSBiomes[7] = 4;
        SQSBiomes[8] = 27;
        SQSBiomes[9] = 29;
        LarvBiomes[0] = config2.get("biome ids", "Dead Forest ID", -1).getInt();
        LarvBiomes[1] = config2.get("biome ids", "Silkglades ID", -1).getInt();
        LarvBiomes[2] = 29;
        CorruptedSands[0] = config2.get("biome ids", "Corrupted Sands ID", -1).getInt();
        BloodHeaps[0] = config2.get("biome ids", "Visceral Heap ID", -1).getInt();
        MineralBiomes[0] = config2.get("biome ids", "Highland ID", -1).getInt();
        MineralBiomes[1] = config2.get("biome ids", "Jade Cliffs ID", -1).getInt();
        MineralBiomes[2] = config2.get("biome ids", "Mountain ID", -1).getInt();
        MineralBiomes[3] = 3;
        MineralBiomes[4] = 131;
        MineralBiomes[5] = 162;
        WastelandBiomes[0] = config2.get("biome ids", "Wasteland ID", -1).getInt();
      } else {
        SwampBiomes[11] = 6;
        PeatBogBiomes[1] = 1;
        PeatBogBiomes[4] = 27;
        PeatBogBiomes[5] = 4;
        DesertBiomes[3] = 130;
        DesertBiomes[4] = 2;
        JungleBiomes[0] = 21;
        SnowBiomes[1] = 12;
        SQSBiomes[7] = 4;
        SQSBiomes[8] = 27;
        SQSBiomes[9] = 29;
        LarvBiomes[2] = 29;
        MineralBiomes[3] = 3;
        MineralBiomes[4] = 131;
        MineralBiomes[5] = 162;
        System.out.println("(MFQM)BiomesOPlenty not found");
        BoP = false;
      }  
    if (TF)
      if (Loader.isModLoaded("TwilightForest")) {
        Configuration config2 = new Configuration(new File(event.getModConfigurationDirectory() + "/TwilightForest.cfg"));
        TFDim = config2.get("dimension", "dimensionID", -999999).getInt(-999999);
      } else {
        TF = false;
        TFDim = -999999;
      }  
    if (TBL)
      if (Loader.isModLoaded("thebetweenlands")) {
        Configuration config2 = new Configuration(new File(event.getModConfigurationDirectory() + "/thebetweenlands/mainConfig.cfg"));
        TBLDim = config2.get("world and dimension", "The Betweenlands Dimension ID", -999999).getInt(-999999);
      } else {
        TBL = false;
        TBLDim = -999999;
      }  
    if (ABC)
      if (Loader.isModLoaded("abyssalcraft")) {
        Configuration config2 = new Configuration(new File(event.getModConfigurationDirectory() + "/abyssalcraft.cfg"));
        ABCDim1 = config2.get("dimensions", "The Abyssal Wasteland", -999999, "The first dimension, full of undead monsters.").getInt(-999999);
        ABCDim2 = config2.get("dimensions", "The Dreadlands", -999999, "The second dimension, infested with mutated monsters.").getInt(-999999);
        ABCDim3 = config2.get("dimensions", "Omothol", -999999, "The third dimension, also known as В§oThe Realm of J'zaharВ§r.").getInt(-999999);
        ABCDim4 = config2.get("dimensions", "The Dark Realm", -999999, "Hidden fourth dimension, reached by falling down from Omothol").getInt(-999999);
        ABCBiomes[0] = config2.get("biomes", "Coralium Infested Swamp", -1).getInt();
        ABCBiomes[1] = config2.get("biomes", "Darklands Forest", -1).getInt();
        ABCBiomes[2] = config2.get("biomes", "Darklands Highland", -1).getInt();
        ABCBiomes[3] = config2.get("biomes", "Darklands Mountains", -1).getInt();
        ABCBiomes[4] = config2.get("biomes", "Darklands Plains", -1).getInt();
        ABCBiomes[5] = config2.get("biomes", "Darklands", -1).getInt();
        ABCBiomes[6] = config2.get("biomes", "Coralium Infested Swamp", -1).getInt();
        ABCBiomes[7] = config2.get("biomes", "Darklands Forest", -1).getInt();
        ABCBiomes[8] = config2.get("biomes", "Darklands Highland", -1).getInt();
        ABCBiomes[9] = config2.get("biomes", "Dreadlands", -1).getInt();
        ABCBiomes[10] = config2.get("biomes", "Dreadlands Mountains", -1).getInt();
        ABCBiomes[11] = config2.get("biomes", "Dreadlands Forest", -1).getInt();
        ABCBiomes[12] = config2.get("biomes", "Purified Dreadlands", -1).getInt();
      } else {
        ABC = false;
        ABCDim1 = -999999;
        ABCDim2 = -999999;
        ABCDim3 = -999999;
        ABCDim4 = -999999;
      }  
    if (WLD)
      if (Loader.isModLoaded("nolpfij_wildycraft")) {
        Configuration config2 = new Configuration(new File(event.getModConfigurationDirectory() + "/nolpfij_wildycraft.cfg"));
        WLDDim = config2.get("general", "Runescape Dimension ID", -999999).getInt(-999999);
        WLDBiomes[0] = config2.get("general", "Forest Biome ID", -1).getInt();
        WLDBiomes[1] = config2.get("general", "Desert Biome ID", -1).getInt();
        WLDBiomes[2] = config2.get("general", "Fremennik Biome ID", -1).getInt();
        WLDBiomes[3] = config2.get("general", "Morytania Biome ID", -1).getInt();
        WLDBiomes[4] = config2.get("general", "Wildy Biome ID", -1).getInt();
        WLDBiomes[5] = config2.get("general", "Volcanic Biome ID", -1).getInt();
      } else {
        WLD = false;
        WLDDim = -999999;
      }  
    if (AOA)
      if (Loader.isModLoaded("nevermine")) {
        Configuration config2 = new Configuration(new File(event.getModConfigurationDirectory() + "/AoA.cfg"));
        AOADimHeaven = config2.get("dimension", "Haven ID", -999999).getInt(-999999);
        AOADimAbyss = config2.get("dimension", "Abyss ID", -999999).getInt(-999999);
        AOADimDeeplands = config2.get("dimension", "Deeplands ID", -999999).getInt(-999999);
        AOADimPrecasia = config2.get("dimension", "Precasia ID", -999999).getInt(-999999);
        AOADimLunalus = config2.get("dimension", "Lunalus ID", -999999).getInt(-999999);
        AOADimIromine = config2.get("dimension", "Iromine ID", -999999).getInt(-999999);
        AOADimGardencia = config2.get("dimension", "Gardencia ID", -999999).getInt(-999999);
        AOADimGreckon = config2.get("dimension", "Greckon ID", -999999).getInt(-999999);
        AOADimLBorean = config2.get("dimension", "L'borean ID", -999999).getInt(-999999);
        AOADimBarathos = config2.get("dimension", "Barathos ID", -999999).getInt(-999999);
        AOADimDustopia = config2.get("dimension", "Dustopia ID", -999999).getInt(-999999);
        AOADimVoxPonds = config2.get("dimension", "Voxponds ID", -999999).getInt(-999999);
        AOADimLandsLelyetia = config2.get("dimension", "Lelyetia ID", -999999).getInt(-999999);
        AOADimCeleve = config2.get("dimension", "Celeve ID", -999999).getInt(-999999);
        AOADimCreeponia = config2.get("dimension", "Creeponia ID", -999999).getInt(-999999);
        AOADimShyrelands = config2.get("dimension", "Shyrelands ID", -999999).getInt(-999999);
        AOADimCrystevia = config2.get("dimension", "Crystevia ID", -999999).getInt(-999999);
        AOADimCandyland = config2.get("dimension", "Candyland ID", -999999).getInt(-999999);
        AOABiomes[0] = config2.get("biome", "Abyss Biome ID", -1).getInt();
        AOABiomes[1] = config2.get("biome", "Abyss Eye Biome ID", -1).getInt();
        AOABiomes[2] = config2.get("biome", "Abyss Shadow Biome ID", -1).getInt();
        AOABiomes[3] = config2.get("biome", "Ancient Cavern Biome ID", -1).getInt();
        AOABiomes[4] = config2.get("biome", "Barathos Biome ID", -1).getInt();
        AOABiomes[5] = config2.get("biome", "Baron Forest Biome ID", -1).getInt();
        AOABiomes[6] = config2.get("biome", "Candyland Biome ID", -1).getInt();
        AOABiomes[7] = config2.get("biome", "Celeve Biome ID", -1).getInt();
        AOABiomes[8] = config2.get("biome", "Chocolate Forest Biome ID", -1).getInt();
        AOABiomes[9] = config2.get("biome", "Creeponia Biome ID", -1).getInt();
        AOABiomes[10] = config2.get("biome", "Crystevia Biome ID", -1).getInt();
        AOABiomes[11] = config2.get("biome", "Deeplands Biome ID", -1).getInt();
        AOABiomes[12] = config2.get("biome", "Deeplands Fungal Biome ID", -1).getInt();
        AOABiomes[13] = config2.get("biome", "Deeplands Shine Biome ID", -1).getInt();
        AOABiomes[14] = config2.get("biome", "Dustopia Biome ID", -1).getInt();
        AOABiomes[15] = config2.get("biome", "Dustopian Marsh Biome ID", -1).getInt();
        AOABiomes[16] = config2.get("biome", "Dustopian Plains Biome ID", -1).getInt();
        AOABiomes[17] = config2.get("biome", "Gardencia Biome ID", -1).getInt();
        AOABiomes[18] = config2.get("biome", "Gardencia Fungal Biome ID", -1).getInt();
        AOABiomes[19] = config2.get("biome", "Gardencian Marsh Biome ID", -1).getInt();
        AOABiomes[20] = config2.get("biome", "Greckon Biome ID", -1).getInt();
        AOABiomes[21] = config2.get("biome", "Haunted Forest Biome ID", -1).getInt();
        AOABiomes[22] = config2.get("biome", "Haunted Skulls Biome ID", -1).getInt();
        AOABiomes[23] = config2.get("biome", "Haven Biome ID", -1).getInt();
        AOABiomes[24] = config2.get("biome", "Immortallis Biome ID", -1).getInt();
        AOABiomes[25] = config2.get("biome", "Iro-Tech Biome ID", -1).getInt();
        AOABiomes[26] = config2.get("biome", "Iromine Biome ID", -1).getInt();
        AOABiomes[27] = config2.get("biome", "L'Borean Bubble Forest Biome ID", -1).getInt();
        AOABiomes[28] = config2.get("biome", "L'Borean Forest Biome ID", -1).getInt();
        AOABiomes[29] = config2.get("biome", "L'Borean Reds Biome ID", -1).getInt();
        AOABiomes[30] = config2.get("biome", "L'borean Biome ID", -1).getInt();
        AOABiomes[31] = config2.get("biome", "Labricon Biome ID", -1).getInt();
        AOABiomes[32] = config2.get("biome", "Lelyetia Biome ID", -1).getInt();
        AOABiomes[33] = config2.get("biome", "Lunalus Biome ID", -1).getInt();
        AOABiomes[34] = config2.get("biome", "Marshmallow Hills Biome ID", -1).getInt();
        AOABiomes[35] = config2.get("biome", "Mysterium Biome ID", -1).getInt();
        AOABiomes[36] = config2.get("biome", "Precasia Biome ID", -1).getInt();
        AOABiomes[37] = config2.get("biome", "Precasian Desert Biome ID", -1).getInt();
        AOABiomes[38] = config2.get("biome", "Precasian Field Biome ID", -1).getInt();
        AOABiomes[39] = config2.get("biome", "Precasian Tall Forest Biome ID", -1).getInt();
        AOABiomes[40] = config2.get("biome", "Rock Candy Hills Biome ID", -1).getInt();
        AOABiomes[41] = config2.get("biome", "Rocky Maze Biome ID", -1).getInt();
        AOABiomes[42] = config2.get("biome", "Runandor Biome ID", -1).getInt();
        AOABiomes[43] = config2.get("biome", "Shyrelands Biome ID", -1).getInt();
        AOABiomes[44] = config2.get("biome", "Silvro Biome ID", -1).getInt();
        AOABiomes[45] = config2.get("biome", "Voxponds Biome ID", -1).getInt();
      } else {
        AOA = false;
        AOADimHeaven = -999999;
        AOADimAbyss = -999999;
        AOADimDeeplands = -999999;
        AOADimPrecasia = -999999;
        AOADimLunalus = -999999;
        AOADimIromine = -999999;
        AOADimGardencia = -999999;
        AOADimGreckon = -999999;
        AOADimLBorean = -999999;
        AOADimBarathos = -999999;
        AOADimDustopia = -999999;
        AOADimVoxPonds = -999999;
        AOADimLandsLelyetia = -999999;
        AOADimCeleve = -999999;
        AOADimCreeponia = -999999;
        AOADimShyrelands = -999999;
        AOADimCrystevia = -999999;
        AOADimCandyland = -999999;
      }  
    FluidRegistry.registerFluid(MireFluid);
    FluidRegistry.registerFluid(MireFluidS);
    FluidRegistry.registerFluid(BogFluid);
    FluidRegistry.registerFluid(SlurryFluid);
    FluidRegistry.registerFluid(QuicksandFluid);
    FluidRegistry.registerFluid(SandFluid);
    FluidRegistry.registerFluid(TarFluid);
    FluidRegistry.registerFluid(SlimeFluid);
    FluidRegistry.registerFluid(AcidFluid);
    FluidRegistry.registerFluid(MucusFluid);
    FluidRegistry.registerFluid(ChocolateFluid);
    FluidRegistry.registerFluid(HoneyFluid);
    MudBlock = (Block)new BlockMud();
    GameRegistry.registerBlock(MudBlock, ItemMud.class, "Mud");
    LanguageRegistry.addName(new ItemStack(MudBlock, 1, 0), "Mud");
    LanguageRegistry.addName(new ItemStack(MudBlock, 1, 1), "Mud");
    LanguageRegistry.addName(new ItemStack(MudBlock, 1, 2), "Mud");
    LanguageRegistry.addName(new ItemStack(MudBlock, 1, 3), "Mud");
    BogBlock = (Block)new BlockBog(mudMaxOpacity[0], mudLastOpacity[0], mudIncOpacity[0]);
    GameRegistry.registerBlock(BogBlock, "Bog");
    LanguageRegistry.addName(BogBlock, "Bog");
    BogFluid.setBlock(BogBlock);
    SoftSnowBlock = (Block)new BlockSoftSnow();
    GameRegistry.registerBlock(SoftSnowBlock, ItemMeta.class, "SoftSnow");
    LanguageRegistry.addName(new ItemStack(SoftSnowBlock, 1, 0), "Soft Snow");
    LanguageRegistry.addName(new ItemStack(SoftSnowBlock, 1, 1), "Soft Ash");
    SandBlock = (Block)new BlockSand();
    GameRegistry.registerBlock(SandBlock, "DryQuicksand");
    LanguageRegistry.addName(SandBlock, "Dry Quicksand");
    SandFluid.setBlock(SandBlock);
    SoftQuicksandBlock = (Block)new BlockSoftQuicksand(mudMaxOpacity[17], mudLastOpacity[17], mudIncOpacity[17]);
    GameRegistry.registerBlock(SoftQuicksandBlock, "SoftQuicksand");
    LanguageRegistry.addName(SoftQuicksandBlock, "Soft Quicksand");
    MorassBlock = (Block)new BlockMorass(mudMaxOpacity[3], mudLastOpacity[3], mudIncOpacity[3]);
    GameRegistry.registerBlock(MorassBlock, ItemMeta.class, "Morass");
    for (int ix = 0; ix < 8; ix++) {
      ItemStack multiBlockStack = new ItemStack(MorassBlock, 1, ix);
      LanguageRegistry.addName(multiBlockStack, "Morass");
    } 
    WetPeatBlock = (Block)new BlockWetPeat(mudMaxOpacity[25], mudLastOpacity[25], mudIncOpacity[25]);
    GameRegistry.registerBlock(WetPeatBlock, "WetPeat");
    LanguageRegistry.addName(WetPeatBlock, "Wet Peat");
    PeatBlock = (Block)new BlockPeat();
    GameRegistry.registerBlock(PeatBlock, "HPeat");
    LanguageRegistry.addName(PeatBlock, "Peat");
    Blocks.fire.setFireInfo(PeatBlock, 5, 5);
    BrownClayBlock = (Block)new BlockBrownClay(mudMaxOpacity[23], mudLastOpacity[23], mudIncOpacity[23]);
    String[] ClayNames = { "Brown Clay", "Brown Clay", "Brown Clay", "Brown Clay", "Mineral Clay", "Mineral Clay", "Mineral Clay", "Mineral Clay" };
    GameRegistry.registerBlock(BrownClayBlock, ItemMeta.class, "BrownClay");
    int i;
    for (i = 0; i < 8; i++) {
      ItemStack multiBlockStack = new ItemStack(BrownClayBlock, 1, i);
      LanguageRegistry.addName(multiBlockStack, ClayNames[i]);
    } 
    WaxBlock = (Block)new BlockWax(mudMaxOpacity[26], mudLastOpacity[26], mudIncOpacity[26]);
    GameRegistry.registerBlock(WaxBlock, "Wax");
    LanguageRegistry.addName(WaxBlock, "Wax");
    QuicksandBlock = (Block)new BlockQuicksand(mudMaxOpacity[4], mudLastOpacity[4], mudIncOpacity[4]);
    GameRegistry.registerBlock(QuicksandBlock, "Quicksand");
    LanguageRegistry.addName(QuicksandBlock, "Quicksand");
    JungleQuicksandBlock = (Block)new BlockJungleQuicksand(mudMaxOpacity[5], mudLastOpacity[5], mudIncOpacity[5]);
    GameRegistry.registerBlock(JungleQuicksandBlock, "JungleQuicksand");
    LanguageRegistry.addName(JungleQuicksandBlock, "Jungle Quicksand");
    QuicksandFluid.setBlock(JungleQuicksandBlock);
    LiquidMireBlock = (Block)new BlockLiquidMire(mudMaxOpacity[6], mudLastOpacity[6], mudIncOpacity[6]);
    GameRegistry.registerBlock(LiquidMireBlock, "LiquidMire0");
    LanguageRegistry.addName(LiquidMireBlock, "Liquid Mire");
    MireFluid.setBlock(LiquidMireBlock);
    StableLiquidMireBlock = (Block)new BlockStableLiquidMire(mudMaxOpacity[7], mudLastOpacity[7], mudIncOpacity[7]);
    GameRegistry.registerBlock(StableLiquidMireBlock, "LiquidMire1");
    LanguageRegistry.addName(StableLiquidMireBlock, "Liquid Mire");
    MireFluidS.setBlock(StableLiquidMireBlock);
    EntityRegistry.registerModEntity(EntitySlimeHole.class, "EntitySlimeHole", 102, instance, 120, 2, false);
    SlimeBlock = (Block)new BlockSlime(mudMaxOpacity[8], mudLastOpacity[8], mudIncOpacity[8]);
    GameRegistry.registerBlock(SlimeBlock, "SinkingSlime");
    LanguageRegistry.addName(SlimeBlock, "Sinking Slime");
    SlimeFluid.setBlock(SlimeBlock);
    MucusBlock = (Block)new BlockMucus(mudMaxOpacity[20], mudLastOpacity[20], mudIncOpacity[20]);
    GameRegistry.registerBlock(MucusBlock, "Mucus");
    LanguageRegistry.addName(MucusBlock, "Mucus");
    MucusFluid.setBlock(MucusBlock);
    MireBlock = (Block)new BlockMire(mudMaxOpacity[9], mudLastOpacity[9], mudIncOpacity[9]);
    GameRegistry.registerBlock(MireBlock, "Mire");
    LanguageRegistry.addName(MireBlock, "Mire");
    MoorBlock = (Block)new BlockMoor(mudMaxOpacity[10], mudLastOpacity[10], mudIncOpacity[10]);
    GameRegistry.registerBlock(MoorBlock, "Moor");
    LanguageRegistry.addName(MoorBlock, "Moor");
    HClayBlock = (Block)new BlockHardenedClay();
    GameRegistry.registerBlock(HClayBlock, "HardenedClay");
    LanguageRegistry.addName(HClayBlock, "Hardened Clay");
    SClayBlock = (Block)new BlockSinkingClay(mudMaxOpacity[11], mudLastOpacity[11], mudIncOpacity[11]);
    GameRegistry.registerBlock(SClayBlock, "SinkingClay");
    LanguageRegistry.addName(SClayBlock, "Sinking Clay");
    MossBlock = (Block)new BlockMoss(mudMaxOpacity[22], mudLastOpacity[22], mudIncOpacity[22]);
    GameRegistry.registerBlock(MossBlock, "TangleRootMoss");
    LanguageRegistry.addName(MossBlock, "Tangleroot Moss");
    DenseWebBlock = (Block)new BlockDenseWeb(mudMaxOpacity[18], mudLastOpacity[18], mudIncOpacity[18]);
    GameRegistry.registerBlock(DenseWebBlock, "DenseWebbing");
    LanguageRegistry.addName(DenseWebBlock, "Dense Webbing");
    Blocks.fire.setFireInfo(DenseWebBlock, 30, 60);
    TarBlock = (Block)new BlockTar(mudMaxOpacity[12], mudLastOpacity[12], mudIncOpacity[12]);
    GameRegistry.registerBlock(TarBlock, "Tar");
    LanguageRegistry.addName(TarBlock, "Tar");
    TarFluid.setBlock(TarBlock);
    LarvaeBlock = (Block)new BlockLarvae();
    GameRegistry.registerBlock(LarvaeBlock, "Larvae");
    LanguageRegistry.addName(LarvaeBlock, "Larvae");
    Blocks.fire.setFireInfo(LarvaeBlock, 30, 60);
    CSandBlock = (Block)new BlockCorruptedSand();
    GameRegistry.registerBlock(CSandBlock, "CorruptedSand");
    LanguageRegistry.addName(CSandBlock, "Corrupted Sand");
    if (QSTentacles)
      EntityRegistry.registerModEntity(EntityTentacles.class, "EntityTentacles", 109, instance, 120, 2, false); 
    if (QSMudTentacles)
      EntityRegistry.registerModEntity(EntityMudTentacles.class, "EntityMudTentacles", 98, instance, 120, 2, false); 
    SwFleshBlock = (Block)new BlockSwallowingFlesh(mudMaxOpacity[19], mudLastOpacity[19], mudIncOpacity[19]);
    GameRegistry.registerBlock(SwFleshBlock, "SwallowingFlesh");
    LanguageRegistry.addName(SwFleshBlock, "Swallowing Flesh");
    AcidBlock = (Block)new BlockAcid();
    GameRegistry.registerBlock(AcidBlock, "Acid");
    LanguageRegistry.addName(AcidBlock, "Acid");
    AcidFluid.setBlock(AcidBlock);
    SlurryBlock = (Block)new BlockSlurry(mudMaxOpacity[28], mudLastOpacity[28], mudIncOpacity[28]);
    GameRegistry.registerBlock(SlurryBlock, "Slurry");
    LanguageRegistry.addName(SlurryBlock, "Slurry");
    SlurryFluid.setBlock(SlurryBlock);
    GasBlock = (Block)new BlockGas();
    GameRegistry.registerBlock(GasBlock, "Gas");
    LanguageRegistry.addName(GasBlock, "Gas");
    SoftGravelBlock = (Block)new BlockSoftGravel(mudMaxOpacity[30], mudLastOpacity[30], mudIncOpacity[30]);
    GameRegistry.registerBlock(SoftGravelBlock, ItemMeta.class, "SoftGravel");
    LanguageRegistry.addName(new ItemStack(SoftGravelBlock, 1, 0), "SoftGravel");
    LanguageRegistry.addName(new ItemStack(SoftGravelBlock, 1, 1), "SoftGravel");
    HoneyBlock = (Block)new BlockHoney(mudMaxOpacity[31], mudLastOpacity[31], mudIncOpacity[31]);
    GameRegistry.registerBlock(HoneyBlock, "Honey");
    LanguageRegistry.addName(HoneyBlock, "Honey");
    HoneyFluid.setBlock(HoneyBlock);
    SolidHoneyBlock = (Block)new BlockSolidHoney();
    GameRegistry.registerBlock(SolidHoneyBlock, "SolidHoney");
    LanguageRegistry.addName(SolidHoneyBlock, "Solid Honey");
    HoneycombBlock = (Block)new BlockHoneycomb();
    GameRegistry.registerBlock(HoneycombBlock, ItemMeta.class, "Honeycomb");
    LanguageRegistry.addName(new ItemStack(HoneycombBlock, 1, 0), "Honeycomb");
    LanguageRegistry.addName(new ItemStack(HoneycombBlock, 1, 1), "Honeycomb Empty");
    LanguageRegistry.addName(new ItemStack(HoneycombBlock, 1, 2), "Honeycomb Filled");
    LiquidChocolateBlock = (Block)new BlockLiquidChocolate(mudMaxOpacity[27], mudLastOpacity[27], mudIncOpacity[27]);
    GameRegistry.registerBlock(LiquidChocolateBlock, "LiquidChocolate");
    LanguageRegistry.addName(LiquidChocolateBlock, "Liquid Chocolate");
    ChocolateFluid.setBlock(LiquidChocolateBlock);
    ChocolateBlock = (Block)new BlockChocolate();
    GameRegistry.registerBlock(ChocolateBlock, "ChocolateBlock");
    LanguageRegistry.addName(ChocolateBlock, "Solid Chocolate");
    SinkingWoolBlock = (Block)new BlockSinkingWool();
    GameRegistry.registerBlock(SinkingWoolBlock, ItemMetaColor.class, "SinkingRug");
    for (i = 0; i < 16; i++) {
      ItemStack multiBlockStack = new ItemStack(SinkingWoolBlock, 1, i);
      LanguageRegistry.addName(multiBlockStack, "Sinking Rug");
    } 
    LureBlock = (Block)new BlockLure();
    GameRegistry.registerBlock(LureBlock, "LureBlock");
    LanguageRegistry.addName(LureBlock, "Smelling Blossom");
    GameRegistry.registerTileEntity(TileEntityLure.class, "tileEntityLure");
    Blocks.fire.setFireInfo(LureBlock, 5, 5);
    BlossomBlock = (Block)new BlockBlossom();
    GameRegistry.registerBlock(BlossomBlock, ItemMeta.class, "BlossomBlock");
    for (i = 0; i < 12; i++) {
      ItemStack multiBlockStack = new ItemStack(BlossomBlock, 1, i);
      LanguageRegistry.addName(multiBlockStack, "Plant Wall");
    } 
    Blocks.fire.setFireInfo(BlossomBlock, 5, 5);
    BlossomSlabBlock = (Block)new BlockBlossomSlab();
    GameRegistry.registerBlock(BlossomSlabBlock, ItemMeta.class, "BlossomBlockSlab");
    for (i = 0; i < 6; i++) {
      ItemStack multiBlockStack = new ItemStack(BlossomSlabBlock, 1, i);
      LanguageRegistry.addName(multiBlockStack, "Plant Wall");
    } 
    Blocks.fire.setFireInfo(BlossomSlabBlock, 5, 5);
    VoreHoleBlock = (Block)new BlockVoreHole(mudMaxOpacity[21], mudLastOpacity[21], mudIncOpacity[21]);
    GameRegistry.registerBlock(VoreHoleBlock, "TempVore");
    LanguageRegistry.addName(VoreHoleBlock, "Temp Vore Hole");
    MeatBlock = (Block)new BlockMeat();
    String[] MeatTypes = { 
        "Empty", "Bottom", "Conner1", "Conner2", "Conner3", "Conner4", "Side1", "Side2", "Side3", "Side4", 
        "Anus" };
    String[] MeatNames = { 
        "", "Bottom", "Conner", "Conner", "Conner", "Conner", "Side", "Side", "Side", "Side", 
        "Anus" };
    GameRegistry.registerBlock(MeatBlock, ItemMeta.class, "MeatWall");
    int j;
    for (j = 0; j < 11; j++) {
      ItemStack multiBlockStack = new ItemStack(MeatBlock, 1, j);
      LanguageRegistry.addName(multiBlockStack, "Meat Wall " + MeatNames[j]);
    } 
    MeatHoleBlock = (Block)new BlockMeatHole(mudMaxOpacity[15], mudLastOpacity[15], mudIncOpacity[15]);
    GameRegistry.registerBlock(MeatHoleBlock, "TempMeat");
    LanguageRegistry.addName(MeatHoleBlock, "Temp Meat");
    WaxWoodBlock = (Block)new BlockWaxWood();
    GameRegistry.registerBlock(WaxWoodBlock, ItemMeta.class, "WaxWood");
    for (j = 0; j < 8; j++) {
      ItemStack multiBlockStack = new ItemStack(WaxWoodBlock, 1, j);
      LanguageRegistry.addName(multiBlockStack, "Wax Jungle Log");
    } 
    Blocks.fire.setFireInfo(WaxWoodBlock, 5, 5);
    CustomLilyPadBlock = (Block)new BlockCustomLilyPad();
    GameRegistry.registerBlock(CustomLilyPadBlock, "CustomLilyPad");
    LanguageRegistry.addName(CustomLilyPadBlock, "waterlily");
    MoorGrassBlock = (Block)new BlockMoorGrass();
    String[] MoorGrassNames = { "Moor Grass", "Moor Grass", "Moor Grass", "Bog Grass", "Bog Grass", "Cranberry Grass" };
    GameRegistry.registerBlock(MoorGrassBlock, ItemMetaColor.class, "MoorGrass");
    int k;
    for (k = 0; k < 6; k++) {
      ItemStack multiBlockStack = new ItemStack(MoorGrassBlock, 1, k);
      LanguageRegistry.addName(multiBlockStack, MoorGrassNames[k]);
    } 
    TentBlock = (Block)new BlockTent();
    GameRegistry.registerBlock(TentBlock, ItemMeta.class, "Tendrils");
    for (k = 0; k < 4; k++) {
      ItemStack multiBlockStack = new ItemStack(TentBlock, 1, k);
      LanguageRegistry.addName(multiBlockStack, "Tendrils");
    } 
    LeavesBlock = (Block)new BlockLeaves();
    GameRegistry.registerBlock(LeavesBlock, "LeavesPile");
    LanguageRegistry.addName(LeavesBlock, "Dead Leaves Pile");
    BogBucketItem = (Item)new ItemBogBucket(BogBlock);
    GameRegistry.registerItem(BogBucketItem, "BucketOfLiquidBog");
    LanguageRegistry.addName(BogBucketItem, "Bucket of Liquid Bog");
    ClayBucketItem = (Item)new ItemClayBucket(BrownClayBlock);
    GameRegistry.registerItem(ClayBucketItem, "Bucket of Clay");
    QuicksandBucketItem = (Item)new ItemQuicksandBucket(JungleQuicksandBlock);
    GameRegistry.registerItem(QuicksandBucketItem, "BucketOfQuicksand");
    LanguageRegistry.addName(QuicksandBucketItem, "Bucket of Quicksand");
    SandBucketItem = (Item)new ItemSandBucket((Block)Blocks.sand);
    GameRegistry.registerItem(SandBucketItem, "BucketOfSand");
    LanguageRegistry.addName(SandBucketItem, "Bucket of Sand");
    MireBucketItem = (Item)new ItemMireBucket(StableLiquidMireBlock);
    GameRegistry.registerItem(MireBucketItem, "BucketOfMire");
    LanguageRegistry.addName(MireBucketItem, "Bucket of Mire");
    SlimeBucketItem = (new ItemSlimeBucket(SlimeBlock)).setContainerItem(Items.bucket);
    GameRegistry.registerItem(SlimeBucketItem, "BucketOfSlime");
    LanguageRegistry.addName(SlimeBucketItem, "Bucket of Slime");
    MucusBucketItem = (new ItemMucusBucket(MucusBlock)).setContainerItem(Items.bucket);
    GameRegistry.registerItem(MucusBucketItem, "BucketOfMucus");
    LanguageRegistry.addName(MucusBucketItem, "Bucket of Mucus");
    TarBucketItem = (new ItemTarBucket(TarBlock)).setContainerItem(Items.bucket);
    GameRegistry.registerItem(TarBucketItem, "BucketOfTar");
    LanguageRegistry.addName(TarBucketItem, "Bucket of Tar");
    AcidBucketItem = (new ItemAcidBucket(AcidBlock)).setContainerItem(Items.bucket);
    GameRegistry.registerItem(AcidBucketItem, "BucketOfAcid");
    LanguageRegistry.addName(AcidBucketItem, "Bucket of Acid");
    ChocolateBucketItem = (new ItemChocolateBucket(LiquidChocolateBlock)).setContainerItem(Items.bucket);
    GameRegistry.registerItem(ChocolateBucketItem, "BucketOfChocolate");
    LanguageRegistry.addName(ChocolateBucketItem, "Bucket of Chocolate");
    ChocolatePowderBucketItem = (Item)new ItemChocolatePowderBucket();
    GameRegistry.registerItem(ChocolatePowderBucketItem, "BucketOfChocolatePowder");
    LanguageRegistry.addName(ChocolatePowderBucketItem, "Bucket of Chocolate Powder");
    ItemStack sugarStk = new ItemStack(Items.sugar);
    ItemStack cocaoStk = new ItemStack(Items.dye, 1, 3);
    GameRegistry.addShapelessRecipe(new ItemStack(ChocolatePowderBucketItem), new Object[] { new ItemStack(Items.bucket), sugarStk, sugarStk, cocaoStk, cocaoStk, cocaoStk, cocaoStk, cocaoStk, cocaoStk });
    GameRegistry.addShapelessRecipe(new ItemStack(ChocolatePowderBucketItem), new Object[] { new ItemStack(Items.bucket), new ItemStack(ChocolateBlock) });
    GameRegistry.addSmelting(ChocolatePowderBucketItem, new ItemStack(ChocolateBucketItem), 0.0F);
    HoneyBucketItem = (new ItemHoneyBucket(HoneyBlock)).setContainerItem(Items.bucket);
    GameRegistry.registerItem(HoneyBucketItem, "BucketOfHoney");
    LanguageRegistry.addName(HoneyBucketItem, "Bucket of Honey");
    SlurryBucketItem = (new ItemSlurryBucket(SlurryBlock)).setContainerItem(Items.bucket);
    GameRegistry.registerItem(SlurryBucketItem, "BucketOfSlurry");
    LanguageRegistry.addName(SlurryBucketItem, "Bucket of Slurry");
    FertilizerItem = (Item)new ItemFertilizer();
    GameRegistry.registerItem(FertilizerItem, "Fertilizer");
    LanguageRegistry.addName(FertilizerItem, "Fertilizer");
    GameRegistry.addShapelessRecipe(new ItemStack(FertilizerItem, 4), new Object[] { new ItemStack(SlurryBucketItem) });
    EntityRegistry.registerModEntity(EntityLongStick.class, "EntityLongStick", 106, instance, 120, 2, true);
    LongStickItem = (Item)new ItemLongStick();
    GameRegistry.registerItem(LongStickItem, "LongStick");
    LanguageRegistry.addName(LongStickItem, "Long Stick");
    RescueItem = (Item)new ItemRescue();
    GameRegistry.registerItem(RescueItem, "Rescuing");
    LanguageRegistry.addName(RescueItem, "Rescuing");
    RopeItem = (Item)new ItemRope();
    GameRegistry.registerItem(RopeItem, "Rope");
    LanguageRegistry.addName(RopeItem, "Rope");
    CableItem = (Item)new ItemCable();
    GameRegistry.registerItem(CableItem, "Cable");
    LanguageRegistry.addName(CableItem, "Cable");
    CoilItem = (new Item()).setUnlocalizedName("Coil").setCreativeTab(tabMFQM).setTextureName("morefunquicksandmod:Coil");
    GameRegistry.registerItem(CoilItem, "Coil");
    LanguageRegistry.addName(CoilItem, "Coil");
    HookItem = (new Item()).setUnlocalizedName("Hook").setCreativeTab(tabMFQM).setTextureName("morefunquicksandmod:Hook");
    GameRegistry.registerItem(HookItem, "Hook");
    LanguageRegistry.addName(HookItem, "Hook");
    FilterItem = (new Item()).setUnlocalizedName("Filter").setCreativeTab(tabMFQM).setTextureName("morefunquicksandmod:Filter");
    GameRegistry.registerItem(FilterItem, "Filter");
    LanguageRegistry.addName(FilterItem, "Filter");
    GasMaskItem = (Item)new ItemGasMask(armorMaterialUnprotective, proxy.addArmor("gasMask"), 0);
    GameRegistry.registerItem(GasMaskItem, "GasMask");
    LanguageRegistry.addName(GasMaskItem, "Gas Mask");
    if (TurnGasMask) {
      GameRegistry.addRecipe(new ItemStack(FilterItem, 2), new Object[] { " w ", "ici", " w ", Character.valueOf('i'), Items.iron_ingot, Character.valueOf('w'), Blocks.wool, Character.valueOf('c'), new ItemStack(Items.coal, 1, 1) });
      GameRegistry.addRecipe(new ItemStack(GasMaskItem, 1), new Object[] { 
            "lll", "lgl", "fif", Character.valueOf('f'), FilterItem, Character.valueOf('l'), Items.leather, Character.valueOf('g'), Blocks.glass_pane, Character.valueOf('i'), 
            Items.iron_ingot });
    } 
    GrapplingHookItem = (Item)new ItemGrapplingHook();
    GameRegistry.registerItem(GrapplingHookItem, "GrapplingHook");
    LanguageRegistry.addName(GrapplingHookItem, "Grappling Hook");
    GrapplingHookBRKItem = (Item)new ItemGrapplingHookBRK();
    GameRegistry.registerItem(GrapplingHookBRKItem, "GrapplingHookBroken");
    LanguageRegistry.addName(GrapplingHookBRKItem, "Grappling Hook Broken");
    if (TurnHook) {
      GameRegistry.addRecipe(new ItemStack(CableItem, 1), new Object[] { "x", "x", "x", Character.valueOf('x'), Items.iron_ingot });
      GameRegistry.addRecipe(new ItemStack(CoilItem, 1), new Object[] { "x", "s", "x", Character.valueOf('x'), Items.iron_ingot, Character.valueOf('s'), Blocks.obsidian });
      GameRegistry.addRecipe(new ItemStack(HookItem, 1), new Object[] { " i ", "iri", "i i", Character.valueOf('i'), Items.iron_ingot, Character.valueOf('r'), Items.redstone });
      GameRegistry.addRecipe(new ItemStack(GrapplingHookItem, 1), new Object[] { 
            "ri ", "csh", "ii ", Character.valueOf('i'), Items.iron_ingot, Character.valueOf('r'), Items.redstone, Character.valueOf('c'), CoilItem, Character.valueOf('s'), 
            CableItem, Character.valueOf('h'), HookItem });
      GameRegistry.addShapelessRecipe(new ItemStack(GrapplingHookItem), new Object[] { new ItemStack(GrapplingHookBRKItem), new ItemStack(HookItem), new ItemStack(CableItem) });
    } 
    LiqGunItem = (Item)new ItemLiqGun();
    GameRegistry.registerItem(LiqGunItem, "LiqGunItem");
    LanguageRegistry.addName(LiqGunItem, "Liquid Gun");
    if (TurnLiqGun)
      GameRegistry.addRecipe(new ItemStack(LiqGunItem, 1), new Object[] { 
            "ipl", "gge", "g  ", Character.valueOf('i'), Items.water_bucket, Character.valueOf('p'), Blocks.piston, Character.valueOf('g'), Items.gold_ingot, Character.valueOf('l'), 
            new ItemStack(Items.dye, 1, 4), Character.valueOf('e'), Items.emerald }); 
    WaxItem = (Item)new ItemWaxPiece();
    GameRegistry.registerItem(WaxItem, "WaxPiece");
    LanguageRegistry.addName(WaxItem, "Wax");
    GameRegistry.addRecipe(new ItemStack(WaxBlock, 1), new Object[] { "xxx", "xxx", "xxx", Character.valueOf('x'), WaxItem });
    if (TurnStick)
      GameRegistry.addRecipe(new ItemStack(LongStickItem), new Object[] { "xxt", "xsx", "txx", Character.valueOf('t'), Items.stick, Character.valueOf('s'), Items.slime_ball }); 
    if (TurnRope) {
      GameRegistry.addRecipe(new ItemStack(RopeItem, 1), new Object[] { "xxs", "xxs", "xx ", Character.valueOf('x'), Items.string, Character.valueOf('s'), Items.slime_ball });
      GameRegistry.addRecipe(new ItemStack(RopeItem, 1), new Object[] { "xx ", "xxs", "xxs", Character.valueOf('x'), Items.string, Character.valueOf('s'), Items.slime_ball });
      GameRegistry.addRecipe(new ItemStack(RopeItem, 1), new Object[] { "xxs", "xx ", "xxs", Character.valueOf('x'), Items.string, Character.valueOf('s'), Items.slime_ball });
    } 
    GameRegistry.addRecipe(new ItemStack(WaxItem, 3), new Object[] { "h", "w", Character.valueOf('h'), Blocks.wool, Character.valueOf('w'), Items.water_bucket });
    GameRegistry.addRecipe(new ItemStack(WaxItem, 6), new Object[] { "hh ", "w  ", Character.valueOf('h'), Blocks.wool, Character.valueOf('w'), Items.water_bucket });
    GameRegistry.addRecipe(new ItemStack(WaxItem, 6), new Object[] { "hh ", " w ", Character.valueOf('h'), Blocks.wool, Character.valueOf('w'), Items.water_bucket });
    GameRegistry.addRecipe(new ItemStack(WaxItem, 9), new Object[] { "hhh", "w  ", Character.valueOf('h'), Blocks.wool, Character.valueOf('w'), Items.water_bucket });
    GameRegistry.addRecipe(new ItemStack(WaxItem, 9), new Object[] { "hhh", " w ", Character.valueOf('h'), Blocks.wool, Character.valueOf('w'), Items.water_bucket });
    LifeJacketItem = (Item)new ItemLifeJacket(armorMaterialUnprotective, proxy.addArmor("lifeJacket"), 1);
    GameRegistry.registerItem(LifeJacketItem, "LifeJacket");
    LanguageRegistry.addName(LifeJacketItem, "Life Jacket");
    if (TurnJacket)
      GameRegistry.addRecipe(new ItemStack(LifeJacketItem, 1), new Object[] { "lxl", "wlw", "nln", Character.valueOf('l'), Items.leather, Character.valueOf('w'), WaxItem, Character.valueOf('n'), Items.string }); 
    PeatItem = (new Item()).setUnlocalizedName("PeatItem").setCreativeTab(tabMFQM).setTextureName("morefunquicksandmod:Peat");
    GameRegistry.registerItem(PeatItem, "PeatItem");
    LanguageRegistry.addName(PeatItem, "Peat");
    GameRegistry.addRecipe(new ItemStack(PeatBlock, 1), new Object[] { "xxx", "xxx", "xxx", Character.valueOf('x'), PeatItem });
    GameRegistry.addRecipe(new ItemStack(WetPeatBlock, 1), new Object[] { "ppp", "xwx", "xxx", Character.valueOf('p'), PeatItem, Character.valueOf('w'), Items.water_bucket });
    GameRegistry.addRecipe(new ItemStack(WetPeatBlock, 3), new Object[] { "xpx", "www", "xxx", Character.valueOf('p'), PeatBlock, Character.valueOf('w'), Items.water_bucket });
    LarvaRawItem = (ItemFood)(new ItemFood(2, 0.1F, false)).setPotionEffect(Potion.hunger.id, 30, 0, 0.5F).setUnlocalizedName("LarvaRaw").setTextureName("morefunquicksandmod:LarvaRaw").setCreativeTab(tabMFQM);
    GameRegistry.registerItem((Item)LarvaRawItem, "LarvaRaw");
    LanguageRegistry.addName(LarvaRawItem, "Larva Raw");
    LarvaCookedItem = (ItemFood)(new ItemFood(3, 0.15F, false)).setPotionEffect(Potion.hunger.id, 30, 0, 0.25F).setUnlocalizedName("LarvaCooked").setTextureName("morefunquicksandmod:LarvaCooked").setCreativeTab(tabMFQM);
    GameRegistry.registerItem((Item)LarvaCookedItem, "LarvaCooked");
    LanguageRegistry.addName(LarvaCookedItem, "Larva Cooked");
    GameRegistry.addSmelting((Item)LarvaRawItem, new ItemStack((Item)LarvaCookedItem), 0.1F);
    CberryItem = (new ItemCranberry(1, 0.1F, false)).setUnlocalizedName("Cranberry").setTextureName("morefunquicksandmod:cranberry").setCreativeTab(CreativeTabs.tabFood);
    GameRegistry.registerItem(CberryItem, "Cranberry");
    LanguageRegistry.addName(CberryItem, "Cranberry");
    DonutItem0 = (new ItemFood(3, 0.4F, true)).setUnlocalizedName("Donut0").setTextureName("morefunquicksandmod:Donut0").setCreativeTab(CreativeTabs.tabFood);
    GameRegistry.registerItem(DonutItem0, "Donut0");
    LanguageRegistry.addName(DonutItem0, "Donut");
    GameRegistry.addRecipe(new ItemStack(DonutItem0, 4), new Object[] { "_x_", "xsx", "_x_", Character.valueOf('x'), Items.wheat, Character.valueOf('s'), sugarStk });
    DonutItem1 = (new ItemFood(4, 0.5F, true)).setUnlocalizedName("Donut1").setTextureName("morefunquicksandmod:Donut1").setCreativeTab(CreativeTabs.tabFood);
    GameRegistry.registerItem(DonutItem1, "Donut1");
    LanguageRegistry.addName(DonutItem1, "Glazed Donut");
    GameRegistry.addShapelessRecipe(new ItemStack(DonutItem1, 1), new Object[] { DonutItem0, ChocolateBucketItem });
    GameRegistry.addShapelessRecipe(new ItemStack(DonutItem1, 2), new Object[] { DonutItem0, DonutItem0, ChocolateBucketItem });
    GameRegistry.addShapelessRecipe(new ItemStack(DonutItem1, 3), new Object[] { DonutItem0, DonutItem0, DonutItem0, ChocolateBucketItem });
    GameRegistry.addShapelessRecipe(new ItemStack(DonutItem1, 4), new Object[] { DonutItem0, DonutItem0, DonutItem0, DonutItem0, ChocolateBucketItem });
    GameRegistry.addShapelessRecipe(new ItemStack(DonutItem1, 5), new Object[] { DonutItem0, DonutItem0, DonutItem0, DonutItem0, DonutItem0, ChocolateBucketItem });
    GameRegistry.addShapelessRecipe(new ItemStack(DonutItem1, 6), new Object[] { DonutItem0, DonutItem0, DonutItem0, DonutItem0, DonutItem0, DonutItem0, ChocolateBucketItem });
    GameRegistry.addShapelessRecipe(new ItemStack(DonutItem1, 7), new Object[] { DonutItem0, DonutItem0, DonutItem0, DonutItem0, DonutItem0, DonutItem0, DonutItem0, ChocolateBucketItem });
    GameRegistry.addShapelessRecipe(new ItemStack(DonutItem1, 8), new Object[] { DonutItem0, DonutItem0, DonutItem0, DonutItem0, DonutItem0, DonutItem0, DonutItem0, DonutItem0, ChocolateBucketItem });
    DonutItem2 = (new ItemFood(5, 0.5F, true)).setUnlocalizedName("Donut2").setTextureName("morefunquicksandmod:Donut2").setCreativeTab(CreativeTabs.tabFood);
    GameRegistry.registerItem(DonutItem2, "Donut2");
    LanguageRegistry.addName(DonutItem2, "Glazed Donut With Sprinkles");
    GameRegistry.addShapelessRecipe(new ItemStack(DonutItem2, 1), new Object[] { DonutItem1, sugarStk, new ItemStack(Items.dye, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(DonutItem2, 1), new Object[] { DonutItem1, sugarStk, new ItemStack(Items.dye, 1, 11) });
    GameRegistry.addShapelessRecipe(new ItemStack(DonutItem2, 1), new Object[] { DonutItem1, sugarStk, new ItemStack(Items.dye, 1, 4) });
    DonutItem3 = (new ItemFood(8, 0.5F, true)).setUnlocalizedName("Donut3").setTextureName("morefunquicksandmod:Donut3").setCreativeTab(CreativeTabs.tabFood);
    GameRegistry.registerItem(DonutItem3, "Donut3");
    LanguageRegistry.addName(DonutItem3, "Pink Donut With Sprinkles");
    GameRegistry.addRecipe(new ItemStack(DonutItem3, 4), new Object[] { 
          "cxs", "xax", "sxv", Character.valueOf('x'), Items.wheat, Character.valueOf('a'), new ItemStack(Items.dye, 1, 9), Character.valueOf('c'), new ItemStack(Items.dye, 1, 1), Character.valueOf('v'), 
          new ItemStack(Items.dye, 1, 11), Character.valueOf('s'), sugarStk });
    GameRegistry.addRecipe(new ItemStack(DonutItem3, 4), new Object[] { 
          "cxs", "xax", "sxv", Character.valueOf('x'), Items.wheat, Character.valueOf('a'), new ItemStack(Items.dye, 1, 9), Character.valueOf('c'), new ItemStack(Items.dye, 1, 1), Character.valueOf('v'), 
          new ItemStack(Items.dye, 1, 4), Character.valueOf('s'), sugarStk });
    GameRegistry.addRecipe(new ItemStack(DonutItem3, 4), new Object[] { 
          "cxs", "xax", "sxv", Character.valueOf('x'), Items.wheat, Character.valueOf('a'), new ItemStack(Items.dye, 1, 9), Character.valueOf('c'), new ItemStack(Items.dye, 1, 11), Character.valueOf('v'), 
          new ItemStack(Items.dye, 1, 1), Character.valueOf('s'), sugarStk });
    GameRegistry.addRecipe(new ItemStack(DonutItem3, 4), new Object[] { 
          "cxs", "xax", "sxv", Character.valueOf('x'), Items.wheat, Character.valueOf('a'), new ItemStack(Items.dye, 1, 9), Character.valueOf('c'), new ItemStack(Items.dye, 1, 11), Character.valueOf('v'), 
          new ItemStack(Items.dye, 1, 4), Character.valueOf('s'), sugarStk });
    GameRegistry.addRecipe(new ItemStack(DonutItem3, 4), new Object[] { 
          "cxs", "xax", "sxv", Character.valueOf('x'), Items.wheat, Character.valueOf('a'), new ItemStack(Items.dye, 1, 9), Character.valueOf('c'), new ItemStack(Items.dye, 1, 4), Character.valueOf('v'), 
          new ItemStack(Items.dye, 1, 1), Character.valueOf('s'), sugarStk });
    GameRegistry.addRecipe(new ItemStack(DonutItem3, 4), new Object[] { 
          "cxs", "xax", "sxv", Character.valueOf('x'), Items.wheat, Character.valueOf('a'), new ItemStack(Items.dye, 1, 9), Character.valueOf('c'), new ItemStack(Items.dye, 1, 4), Character.valueOf('v'), 
          new ItemStack(Items.dye, 1, 11), Character.valueOf('s'), sugarStk });
    GameRegistry.addRecipe(new ItemStack(DonutItem3, 4), new Object[] { 
          "cxs", "xax", "sxv", Character.valueOf('x'), Items.wheat, Character.valueOf('a'), CberryItem, Character.valueOf('c'), new ItemStack(Items.dye, 1, 1), Character.valueOf('v'), 
          new ItemStack(Items.dye, 1, 11), Character.valueOf('s'), sugarStk });
    GameRegistry.addRecipe(new ItemStack(DonutItem3, 4), new Object[] { 
          "cxs", "xax", "sxv", Character.valueOf('x'), Items.wheat, Character.valueOf('a'), CberryItem, Character.valueOf('c'), new ItemStack(Items.dye, 1, 1), Character.valueOf('v'), 
          new ItemStack(Items.dye, 1, 4), Character.valueOf('s'), sugarStk });
    GameRegistry.addRecipe(new ItemStack(DonutItem3, 4), new Object[] { 
          "cxs", "xax", "sxv", Character.valueOf('x'), Items.wheat, Character.valueOf('a'), CberryItem, Character.valueOf('c'), new ItemStack(Items.dye, 1, 11), Character.valueOf('v'), 
          new ItemStack(Items.dye, 1, 1), Character.valueOf('s'), sugarStk });
    GameRegistry.addRecipe(new ItemStack(DonutItem3, 4), new Object[] { 
          "cxs", "xax", "sxv", Character.valueOf('x'), Items.wheat, Character.valueOf('a'), CberryItem, Character.valueOf('c'), new ItemStack(Items.dye, 1, 11), Character.valueOf('v'), 
          new ItemStack(Items.dye, 1, 4), Character.valueOf('s'), sugarStk });
    GameRegistry.addRecipe(new ItemStack(DonutItem3, 4), new Object[] { 
          "cxs", "xax", "sxv", Character.valueOf('x'), Items.wheat, Character.valueOf('a'), CberryItem, Character.valueOf('c'), new ItemStack(Items.dye, 1, 4), Character.valueOf('v'), 
          new ItemStack(Items.dye, 1, 1), Character.valueOf('s'), sugarStk });
    GameRegistry.addRecipe(new ItemStack(DonutItem3, 4), new Object[] { 
          "cxs", "xax", "sxv", Character.valueOf('x'), Items.wheat, Character.valueOf('a'), CberryItem, Character.valueOf('c'), new ItemStack(Items.dye, 1, 4), Character.valueOf('v'), 
          new ItemStack(Items.dye, 1, 11), Character.valueOf('s'), sugarStk });
    DonutItem4 = (new ItemFood(8, 0.6F, true)).setUnlocalizedName("Donut4").setTextureName("morefunquicksandmod:Donut4").setCreativeTab(CreativeTabs.tabFood);
    GameRegistry.registerItem(DonutItem4, "Donut4");
    LanguageRegistry.addName(DonutItem4, "Chocolate Donut");
    GameRegistry.addRecipe(new ItemStack(DonutItem4, 4), new Object[] { 
          "sxc", "xbx", "cxs", Character.valueOf('x'), Items.wheat, Character.valueOf('c'), cocaoStk, Character.valueOf('b'), ChocolateBucketItem, Character.valueOf('s'), 
          sugarStk });
    GameRegistry.addShapelessRecipe(new ItemStack(Items.slime_ball, 4), new Object[] { new ItemStack(SlimeBucketItem) });
    SoftSnowBlock.setHarvestLevel("shovel", 0);
    GameRegistry.registerFuelHandler(new CustomFuelHandler());
    MyPotionItem = (Item)new ItemMyPotion();
    GameRegistry.registerItem(MyPotionItem, "Potion");
    if (TurnPotion) {
      GameRegistry.addShapelessRecipe(new ItemStack(MyPotionItem, 1, 1), new Object[] { new ItemStack(MyPotionItem, 1, 0), new ItemStack(Blocks.soul_sand) });
      GameRegistry.addShapelessRecipe(new ItemStack(MyPotionItem, 1, 1), new Object[] { new ItemStack((Item)Items.potionitem, 1, 0), new ItemStack(CSandBlock) });
    } 
    ItemStack bottle = new ItemStack(Items.glass_bottle, 1, 0);
    GameRegistry.addShapelessRecipe(new ItemStack(MyPotionItem, 3, 2), new Object[] { new ItemStack(ChocolateBucketItem, 1, 0), bottle, bottle, bottle });
    for (int m = 0; m < 16; m++) {
      ItemStack woolStk = new ItemStack(Blocks.wool, 1, m);
      ItemStack potionStk = new ItemStack(MyPotionItem, 1, 1);
      GameRegistry.addShapelessRecipe(new ItemStack(SinkingWoolBlock, 1, m), new Object[] { woolStk, potionStk });
      GameRegistry.addShapelessRecipe(new ItemStack(SinkingWoolBlock, 2, m), new Object[] { woolStk, woolStk, potionStk });
      GameRegistry.addShapelessRecipe(new ItemStack(SinkingWoolBlock, 3, m), new Object[] { woolStk, woolStk, woolStk, potionStk });
      GameRegistry.addShapelessRecipe(new ItemStack(SinkingWoolBlock, 4, m), new Object[] { woolStk, woolStk, woolStk, woolStk, potionStk });
      GameRegistry.addShapelessRecipe(new ItemStack(SinkingWoolBlock, 5, m), new Object[] { woolStk, woolStk, woolStk, woolStk, woolStk, potionStk });
      GameRegistry.addShapelessRecipe(new ItemStack(SinkingWoolBlock, 6, m), new Object[] { woolStk, woolStk, woolStk, woolStk, woolStk, woolStk, potionStk });
      GameRegistry.addShapelessRecipe(new ItemStack(SinkingWoolBlock, 7, m), new Object[] { woolStk, woolStk, woolStk, woolStk, woolStk, woolStk, woolStk, potionStk });
      GameRegistry.addShapelessRecipe(new ItemStack(SinkingWoolBlock, 8, m), new Object[] { woolStk, woolStk, woolStk, woolStk, woolStk, woolStk, woolStk, woolStk, potionStk });
    } 
    EntityRegistry.registerModEntity(EntityRope.class, "EntityRope", 100, instance, 120, 2, true);
    EntityRegistry.registerModEntity(EntityHook.class, "EntityHook", 101, instance, 120, 2, true);
    EntityRegistry.registerModEntity(EntityRescue.class, "EntityRescue", 108, instance, 120, 2, true);
    EntityRegistry.registerModEntity(EntityLiqBall.class, "EntityLiqBall", 112, instance, 120, 2, true);
    MinecraftForge.EVENT_BUS.register(new CustomBucketEvent());
    if (QSCover)
      MinecraftForge.EVENT_BUS.register(new MuddyEvent()); 
    if (QSHandRescue)
      MinecraftForge.EVENT_BUS.register(new HandEvent()); 
    MinecraftForge.EVENT_BUS.register(new AirEvent());
    MinecraftForge.EVENT_BUS.register(new CustomGenerationEvent());
    if (FMLCommonHandler.instance().getSide().equals(Side.CLIENT)) {
      MinecraftForge.EVENT_BUS.register(new CustomRenderOverlayEvent());
      if (QSCover)
        MinecraftForge.EVENT_BUS.register(new CustomRenderPlayerEvent()); 
    } 
    ArrayList<BiomeGenBase> all_biomesList = new ArrayList<BiomeGenBase>();
    ArrayList<BiomeGenBase> swamp_biomesList = new ArrayList<BiomeGenBase>();
    ArrayList<BiomeGenBase> desert_biomesList = new ArrayList<BiomeGenBase>();
    for (BiomeGenBase biome : BiomeGenBase.getBiomeGenArray()) {
      if (biome != null) {
        all_biomesList.add(biome);
        if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.SWAMP) && 
          !BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.HILLS) && 
          !BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.MAGICAL) && 
          !BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.MUSHROOM) && 
          !BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.PLAINS) && (
          !BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.FOREST) || BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.WET)))
          swamp_biomesList.add(biome); 
        if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.DESERT))
          desert_biomesList.add(biome); 
      } 
    } 
    swamp_biomesList.add(BiomeGenBase.swampland);
    desert_biomesList.add(BiomeGenBase.desert);
    desert_biomesList.add(BiomeGenBase.desertHills);
    BiomeGenBase[] all_biomes_array = new BiomeGenBase[all_biomesList.size()];
    BiomeGenBase[] allBiomes = all_biomesList.<BiomeGenBase>toArray(all_biomes_array);
    BiomeGenBase[] swamp_biomes_array = new BiomeGenBase[swamp_biomesList.size()];
    BiomeGenBase[] swampBiomes = swamp_biomesList.<BiomeGenBase>toArray(swamp_biomes_array);
    BiomeGenBase[] desert_biomes_array = new BiomeGenBase[desert_biomesList.size()];
    BiomeGenBase[] desertBiomes = desert_biomesList.<BiomeGenBase>toArray(desert_biomes_array);
    int backgroundEggColour = 0;
    int foregroundEggColour = 0;
    if (InitMobs) {
      backgroundEggColour = 10205416;
      foregroundEggColour = 10993884;
      EntityRegistry.registerGlobalEntityID(EntityVoreSlime.class, "VoreSlime", EntityRegistry.findGlobalUniqueEntityId(), backgroundEggColour, foregroundEggColour);
      EntityRegistry.registerModEntity(EntityVoreSlime.class, "VoreSlime", 103, instance, 80, 3, true);
      if (SpawnVS)
        EntityRegistry.addSpawn(EntityVoreSlime.class, 2, 1, 1, EnumCreatureType.monster, allBiomes); 
      LanguageRegistry.instance().addStringLocalization("entity.VoreSlime.name", "en_US", "Vore Slime");
      backgroundEggColour = 7428915;
      foregroundEggColour = 5787429;
      EntityRegistry.registerGlobalEntityID(EntityMuddyBlob.class, "MuddyBlob", EntityRegistry.findGlobalUniqueEntityId(), backgroundEggColour, foregroundEggColour);
      EntityRegistry.registerModEntity(EntityMuddyBlob.class, "MuddyBlob", 104, instance, 80, 3, true);
      if (SpawnMB)
        EntityRegistry.addSpawn(EntityMuddyBlob.class, 2, 1, 1, EnumCreatureType.monster, swampBiomes); 
      LanguageRegistry.instance().addStringLocalization("entity.MuddyBlob.name", "en_US", "Muddy Blob");
      backgroundEggColour = 16049320;
      foregroundEggColour = 14858107;
      EntityRegistry.registerGlobalEntityID(EntitySandBlob.class, "SandBlob", EntityRegistry.findGlobalUniqueEntityId(), backgroundEggColour, foregroundEggColour);
      EntityRegistry.registerModEntity(EntitySandBlob.class, "SandBlob", 99, instance, 80, 3, true);
      if (SpawnSB)
        EntityRegistry.addSpawn(EntitySandBlob.class, 2, 1, 1, EnumCreatureType.monster, desertBiomes); 
      LanguageRegistry.instance().addStringLocalization("entity.SandBlob.name", "en_US", "Sandy Blob");
      backgroundEggColour = 1973277;
      foregroundEggColour = 2696228;
      EntityRegistry.registerGlobalEntityID(EntityTarSlime.class, "TarSlime", EntityRegistry.findGlobalUniqueEntityId(), backgroundEggColour, foregroundEggColour);
      EntityRegistry.registerModEntity(EntityTarSlime.class, "TarSlime", 105, instance, 80, 3, true);
      if (SpawnTS)
        EntityRegistry.addSpawn(EntityTarSlime.class, 12, 1, 1, EnumCreatureType.monster, allBiomes); 
      LanguageRegistry.instance().addStringLocalization("entity.TarSlime.name", "en_US", "Tar Slime");
    } 
    backgroundEggColour = 13603621;
    foregroundEggColour = 2566696;
    EntityRegistry.registerModEntity(EntityBee.class, "Bee", 111, instance, 80, 3, true);
    registerEntityEgg((Class)EntityBee.class, backgroundEggColour, foregroundEggColour);
    LanguageRegistry.instance().addStringLocalization("entity.Bee.name", "en_US", "Bee");
    EntityRegistry.registerModEntity(EntityBubble.class, "EntityBubble", 107, instance, 120, 2, false);
    EntityRegistry.registerModEntity(EntityTarTreads.class, "EntityTarTreads", 110, instance, 120, 2, false);
  }
  
  public static void registerEntityEgg(Class<? extends Entity> entity, int primaryColor, int secondaryColor) {
    int id = getUniqueEntityEggId();
    EntityList.idToClassMap.put(Integer.valueOf(id), entity);
    EntityList.entityEggs.put(Integer.valueOf(id), new EntityList.EntityEggInfo(id, primaryColor, secondaryColor));
  }
  
  public static int getUniqueEntityEggId() {
    do {
      eggIdCounter++;
    } while (EntityList.getStringFromID(eggIdCounter) != null);
    return eggIdCounter;
  }
  
  @EventHandler
  public void Init(FMLPostInitializationEvent event) {
    GameRegistry.registerWorldGenerator(new CustomWorldGen(), 9999999);
    System.out.println("(MFQM)Registered World Generation!");
  }
  
  @EventHandler
  public void load(FMLInitializationEvent event) {}
  
  @EventHandler
  public void postInit(FMLPostInitializationEvent event) {
    if (BoP || TBL || ABC || WLD || AOA) {
      System.out.println("(MFQM)Scan blocks/items:");
      int mudID = 0;
      int fleshID = 0;
      int ashID = 0;
      int hiveID = 0;
      int honeyID = 0;
      int solidHoneyID = 0;
      int blMudID = 0;
      int blTarID = 0;
      int blSolidTarID = 0;
      int blSludgeID = 0;
      int acSludgeID = 0;
      int wdAshID = 0;
      int wdSwmID = 0;
      int aaToxicID = 0;
      boolean DoneBoP = !BoP;
      boolean DoneBL = !TBL;
      boolean DoneABC = !ABC;
      boolean DoneWLD = !WLD;
      boolean DoneAOA = !AOA;
      for (int j = 4095; j > 0; j--) {
        Block tmp = Block.getBlockById(j);
        if (tmp != null) {
          GameRegistry.UniqueIdentifier untmp = GameRegistry.findUniqueIdentifierFor(tmp);
          String str1 = tmp.getUnlocalizedName().toLowerCase();
          int len = str1.length();
          String str2 = str1.substring(Math.max(len - 3, 0)).toLowerCase();
          String str3 = str1.substring(Math.max(len - 5, 0)).toLowerCase();
          if (untmp != null && 
            untmp.modId != null) {
            if (untmp.modId.toLowerCase().matches("(.*)biomesoplenty(.*)")) {
              if (str2.matches("(.*)mud(.*)")) {
                System.out.println("(MFQM)BoP Mud founded in " + j);
                BOPMudBlock = tmp;
                mudID = j;
              } 
              if (str2.matches("(.*)ash(.*)")) {
                System.out.println("(MFQM)BoP Ash founded in " + j);
                AshBlock = tmp;
                ashID = j;
              } 
              if (str1.matches("(.*)flesh(.*)")) {
                System.out.println("(MFQM)BoP Flesh founded in " + j);
                FleshBlock = tmp;
                fleshID = j;
              } 
              if (str1.matches("(.*)hive(.*)")) {
                System.out.println("(MFQM)BoP Hive founded in " + j);
                HiveBlock = tmp;
                hiveID = j;
              } 
              if (str3.matches("(.*)honey(.*)")) {
                System.out.println("(MFQM)BoP honey founded in " + j);
                BOPHoneyBlock = tmp;
                honeyID = j;
              } 
              if (str1.matches("(.*)honeyblock(.*)")) {
                System.out.println("(MFQM)BoP solid honey founded in " + j);
                BOPSolidHoneyBlock = tmp;
                solidHoneyID = j;
              } 
            } 
            if (untmp.modId.toLowerCase().matches("(.*)thebetweenlands(.*)")) {
              if (str2.matches("(.*)mud(.*)")) {
                System.out.println("(MFQM)TBL Mud founded in " + j);
                TBLMudBlock = tmp;
                TBL_MIC = true;
                blMudID = j;
              } 
              if (str1.matches("(.*)tarfluid(.*)")) {
                System.out.println("(MFQM)TBL Tar founded in " + j);
                TBLTarBlock = tmp;
                blTarID = j;
              } 
              if (str1.matches("(.*)solidtar")) {
                System.out.println("(MFQM)TBL Solid Tar founded in " + j);
                TBLSolidTarBlock = tmp;
                blSolidTarID = j;
              } 
              if (str1.matches("(.*)sludgydirt(.*)")) {
                System.out.println("(MFQM)TBL Sludge founded in " + j);
                TBLSludgeBlock = tmp;
                blSludgeID = j;
              } 
            } 
            if (untmp.modId.toLowerCase().matches("(.*)abyssalcraft(.*)") && 
              str1.matches("(.*)shoggothblock(.*)")) {
              System.out.println("(MFQM)ABC Shoggoth founded in " + j);
              ABCSludgeBlock = tmp;
              ABC_MIC = true;
              acSludgeID = j;
            } 
            if (untmp.modId.toLowerCase().matches("(.*)wildycraft(.*)")) {
              if (str1.matches("(.*)rsash(.*)")) {
                System.out.println("(MFQM)WLD Ash founded in " + j);
                WLDAshBlock = tmp;
                WLD_MIC = true;
                wdAshID = j;
              } 
              if (str1.matches("(.*)tain(.*)")) {
                System.out.println("(MFQM)WLD Tained land founded in " + j);
                WLDSwampBlock = tmp;
                wdSwmID = j;
              } 
            } 
            if (untmp.modId.toLowerCase().matches("(.*)nevermine(.*)") && 
              str1.matches("(.*)toxicblock(.*)")) {
              System.out.println("(MFQM)AoA ToxicBlock founded in " + j);
              AOAToxicBlock = tmp;
              AOA_MIC = true;
              aaToxicID = j;
            } 
          } 
          if (solidHoneyID != 0 && honeyID != 0 && hiveID != 0 && fleshID != 0 && ashID != 0 && mudID != 0)
            DoneBoP = true; 
          if (blMudID != 0 && blTarID != 0 && blSolidTarID != 0 && blSludgeID != 0)
            DoneBL = true; 
          if (acSludgeID != 0)
            DoneABC = true; 
          if (wdAshID != 0 && wdSwmID != 0)
            DoneWLD = true; 
          if (aaToxicID != 0)
            DoneAOA = true; 
          if (DoneBoP && DoneBL && DoneABC && DoneWLD && DoneAOA)
            break; 
        } 
      } 
      if (BoP) {
        if (honeyID == 0) {
          System.out.println("(MFQM)BoP honey not founded");
        } else {
          HoneyBlockID = honeyID;
        } 
        if (solidHoneyID == 0) {
          System.out.println("(MFQM)BoP solid honey not founded");
        } else {
          SolidHoneyBlockID = solidHoneyID;
        } 
        if (hiveID == 0) {
          System.out.println("(MFQM)BoP Hive not founded");
        } else {
          HiveBlockID = hiveID;
        } 
        if (fleshID == 0) {
          System.out.println("(MFQM)BoP Flesh not founded");
        } else {
          FleshBlockID = fleshID;
        } 
        if (ashID == 0) {
          System.out.println("(MFQM)BoP Ash not founded");
        } else {
          AshBlockID = ashID;
        } 
        if (mudID == 0) {
          System.out.println("(MFQM)BoP Mud not founded");
          System.out.println("(MFQM)BoP Wading Boots is not available");
          BootsID = 1;
        } else {
          BOPMudBlockID = mudID;
        } 
      } 
      if (TBL) {
        if (blMudID == 0) {
          System.out.println("(MFQM)TBL Mud not founded");
        } else {
          TBLMudBlockID = blMudID;
        } 
        if (blTarID == 0) {
          System.out.println("(MFQM)TBL Tar not founded");
        } else {
          TBLTarBlockID = blTarID;
        } 
        if (blSolidTarID == 0) {
          System.out.println("(MFQM)TBL Solid Tar not founded");
        } else {
          TBLSolidTarBlockID = blSolidTarID;
        } 
        if (blSludgeID == 0) {
          System.out.println("(MFQM)TBL Sludge not founded");
        } else {
          TBLSludgeBlockID = blSludgeID;
        } 
      } 
      if (ABC)
        if (acSludgeID == 0) {
          System.out.println("(MFQM)ABC Shoggoth not founded");
        } else {
          ABCSludgeBlockID = acSludgeID;
        }  
      if (WLD) {
        if (wdAshID == 0) {
          System.out.println("(MFQM)WLD Ash not founded");
        } else {
          WLDAshBlockID = wdAshID;
        } 
        if (wdSwmID == 0) {
          System.out.println("(MFQM)WLD Tained land not founded");
        } else {
          WLDSwampBlockID = wdSwmID;
        } 
      } 
      if (AOA)
        if (aaToxicID == 0) {
          System.out.println("(MFQM)AoA ToxicBlock not founded");
        } else {
          AOAToxicBlockID = aaToxicID;
        }  
    } else {
      BootsID = 1;
    } 
    BootsID = 1;
    MudBallID = 0;
    FleshItemID = 0;
    FoodItemID = 0;
    FertilizerItemID = 0;
    WLDDruidPouchItemID = 0;
    AOAFaceMaskItemID = 0;
    AOADoomStoneItemID = 0;
    AOAToxicLumpItemID = 0;
    for (int i = 31999; i > 255; i--) {
      Item tmp2 = Item.getItemById(i);
      if (tmp2 != null) {
        String str1 = tmp2.getUnlocalizedName();
        GameRegistry.UniqueIdentifier untmp = GameRegistry.findUniqueIdentifierFor(tmp2);
        if (BOPMudBlockID != 0 && 
          untmp != null && 
          untmp.modId != null && 
          untmp.modId.toLowerCase().matches("(.*)biomesoplenty(.*)")) {
          if (str1.matches("(.*)mudball(.*)")) {
            System.out.println("(MFQM)Mud ball founded in " + i);
            MudBallID = i;
            MudBall = tmp2;
          } 
          if (str1.matches("(.*)misc(.*)")) {
            System.out.println("(MFQM)Flesh part founded in " + i);
            FleshItemID = i;
            FleshItem = tmp2;
          } 
          if (str1.matches("(.*)food(.*)")) {
            System.out.println("(MFQM)BoP Food founded in " + i);
            FoodItemID = i;
            FoodItem = tmp2;
          } 
          if (str1.matches("(.*)wadingBoots(.*)")) {
            System.out.println("(MFQM)Wading Boots founded in " + i);
            BootsID = i;
            WadingBoots = tmp2;
          } 
        } 
        if (WLDSwampBlockID != 0 && 
          untmp != null && 
          untmp.modId != null && 
          untmp.modId.toLowerCase().matches("(.*)wildycraft(.*)") && 
          str1.matches("(.*)druid(.*)")) {
          System.out.println("(MFQM)WLD Druid pouch founded in " + i);
          WLDDruidPouchItemID = i;
          WLDDruidPouchItem = tmp2;
        } 
        if (AOAToxicBlockID != 0 && 
          untmp != null && 
          untmp.modId != null && 
          untmp.modId.toLowerCase().matches("(.*)nevermine(.*)")) {
          if (str1.toLowerCase().matches("(.*)facemask(.*)")) {
            System.out.println("(MFQM)AoA FaceMask founded in " + i);
            AOAFaceMaskItemID = i;
            AOAFaceMaskItem = tmp2;
          } 
          if (str1.toLowerCase().matches("(.*)doomstone(.*)")) {
            System.out.println("(MFQM)AoA DoomStone founded in " + i);
            AOADoomStoneItemID = i;
            AOADoomStoneItem = tmp2;
          } 
          if (str1.toLowerCase().matches("(.*)toxiclump(.*)")) {
            System.out.println("(MFQM)AoA ToxicLump founded in " + i);
            AOAToxicLumpItemID = i;
            AOAToxicLumpItem = tmp2;
          } 
        } 
        if (str1.matches("(.*)Fertilizer(.*)") && 
          tmp2 != FertilizerItem) {
          System.out.println("(MFQM)Fertilizer founded in " + i);
          FertilizerItemID = i;
          IC2FertilizerItem = tmp2;
        } 
        if (MudBallID != 0 && BootsID != 1 && FleshItemID != 0 && FoodItemID != 0 && FertilizerItemID != 0 && (!WLD || WLDDruidPouchItemID != 0) && (!AOA || AOAFaceMaskItemID != 0 || AOADoomStoneItemID != 0 || AOAToxicLumpItemID != 0))
          break; 
      } 
    } 
    if (BoP) {
      if (MudBallID == 0)
        System.out.println("(MFQM)Mud ball not founded"); 
      if (BootsID == 1)
        System.out.println("(MFQM)Wading Boots not founded"); 
      if (FleshItemID == 0)
        System.out.println("(MFQM)Flesh part not founded"); 
      if (FoodItemID == 0)
        System.out.println("(MFQM)BoP Food not founded"); 
    } 
    if (AOA) {
      if (AOAFaceMaskItemID == 0)
        System.out.println("(MFQM)AOA FaceMask not founded"); 
      if (AOADoomStoneItemID == 0)
        System.out.println("(MFQM)AOA DoomStone not founded"); 
      if (AOAToxicLumpItemID == 0)
        System.out.println("(MFQM)AOA ToxicLump not founded"); 
    } 
    if (WLD && 
      WLDDruidPouchItemID == 0)
      System.out.println("(MFQM)WLD Druid pouch not founded"); 
    if (FertilizerItemID == 0) {
      System.out.println("(MFQM)Fertilizer not founded");
    } else {
      GameRegistry.addShapelessRecipe(new ItemStack(IC2FertilizerItem, 1), new Object[] { new ItemStack(FertilizerItem) });
    } 
    if (TBL) {
      Map monsters = EntityList.stringToClassMapping;
      TBLMobs = new ArrayList<Class<?>>();
      TBLMobsTar = new ArrayList<Class<?>>();
      System.out.println("(MFQM)Scaning TBL Mobs...");
      for (Object key : monsters.keySet()) {
        if (isChildOf(EntityLivingBase.class, (Class)monsters.get(key))) {
          String[] parts = ((String)key).split("[.]");
          if (parts != null && 
            parts.length > 0 && 
            parts[0].matches("(.*)thebetweenlands(.*)")) {
            System.out.println("(MFQM)Entity " + monsters.get(key) + " added to Mobs");
            TBLMobs.add((Class)monsters.get(key));
            String[] parts2 = ((Class)monsters.get(key)).getName().split("[.]");
            if (parts2 != null && 
              parts2.length > 0) {
              if (parts2[parts2.length - 1].matches("(.*)Tar(.*)")) {
                System.out.println("(MFQM)Entity " + monsters.get(key) + " added to TarMobs");
                TBLMobsTar.add((Class)monsters.get(key));
              } 
              if (parts2[parts2.length - 1].matches("(.*)TarBeast(.*)"))
                TBLTarBeast = (Class)monsters.get(key); 
            } 
          } 
        } 
      } 
    } 
    if (BoP) {
      System.out.println("(MFQM)BiomesOPlenty corrector init");
      if (HiveBlockID != 0)
        GameRegistry.addSmelting(HiveBlock, new ItemStack(WaxItem, 9), 0.1F); 
      if (FleshItemID != 0)
        GameRegistry.addSmelting(new ItemStack(FleshItem, 1, 2), new ItemStack(WaxItem, 3), 0.1F); 
      if (FoodItemID != 0)
        GameRegistry.addSmelting(new ItemStack(FoodItem, 1, 9), new ItemStack(WaxItem, 3), 0.1F); 
    } else {
      EmptycombItem = (new Item()).setUnlocalizedName("Emptycomb").setCreativeTab(tabMFQM).setTextureName("morefunquicksandmod:emptyhoneycomb");
      GameRegistry.registerItem(EmptycombItem, "Emptycomb");
      LanguageRegistry.addName(EmptycombItem, "Empty Honeycomb");
      GameRegistry.addSmelting(new ItemStack(EmptycombItem, 1, 0), new ItemStack(WaxItem, 3), 0.1F);
      HoneycombItem = (new ItemFood(3, 0.4F, false)).setUnlocalizedName("HoneycombItem").setTextureName("morefunquicksandmod:filledhoneycomb").setCreativeTab(CreativeTabs.tabFood);
      GameRegistry.registerItem(HoneycombItem, "HoneycombItem");
      LanguageRegistry.addName(HoneycombItem, "Filled Honeycomb");
      GameRegistry.addSmelting(new ItemStack(HoneycombItem, 1, 0), new ItemStack(WaxItem, 3), 0.1F);
    } 
    if (BootsID == 1) {
      WadingBoots = (Item)new ItemWadingBoots(armorMaterialUnprotective, proxy.addArmor("wadingBoots"), 3);
      GameRegistry.registerItem(WadingBoots, "wadingBoots");
      LanguageRegistry.addName(WadingBoots, "Wading Boots");
      BootsID = Item.getIdFromItem(WadingBoots);
    } 
    PreWadingBoots0 = (Item)new ItemPreWadingBoots0(armorMaterialUnprotective, proxy.addArmor("preWadingBoots0"), 3);
    GameRegistry.registerItem(PreWadingBoots0, "preWadingBoots0");
    LanguageRegistry.addName(PreWadingBoots0, "Tall Leather Boots");
    PreWadingBoots1 = (Item)new ItemPreWadingBoots1(armorMaterialUnprotective, proxy.addArmor("preWadingBoots1"), 3);
    GameRegistry.registerItem(PreWadingBoots1, "preWadingBoots1");
    LanguageRegistry.addName(PreWadingBoots1, "Slimy Tall Leather Boots");
    GameRegistry.addRecipe(new ItemStack(QuicksandBlock, 4), new Object[] { "wsw", "sws", "wsw", Character.valueOf('s'), Blocks.sand, Character.valueOf('w'), Items.water_bucket });
    GameRegistry.addRecipe(new ItemStack(QuicksandBucketItem, 1), new Object[] { 
          "wdw", "sbd", "wsw", Character.valueOf('s'), Blocks.sand, Character.valueOf('w'), Items.water_bucket, Character.valueOf('b'), Items.bucket, Character.valueOf('d'), 
          Blocks.dirt });
    if (BoP) {
      GameRegistry.addRecipe(new ItemStack(MudBlock, 4, 1), new Object[] { " m ", "mwm", " m ", Character.valueOf('m'), new ItemStack(BOPMudBlock, 1, 0), Character.valueOf('w'), Items.water_bucket });
      GameRegistry.addRecipe(new ItemStack(MireBucketItem, 1), new Object[] { "wmw", "mbm", "wmw", Character.valueOf('m'), new ItemStack(BOPMudBlock, 1, 0), Character.valueOf('w'), Items.water_bucket, Character.valueOf('b'), Items.bucket });
      GameRegistry.addRecipe(new ItemStack(MireBlock, 4), new Object[] { "mwm", "wmw", "mwm", Character.valueOf('m'), new ItemStack(BOPMudBlock, 1, 0), Character.valueOf('w'), Items.water_bucket, Character.valueOf('d'), Blocks.dirt });
    } 
    GameRegistry.addRecipe(new ItemStack(MudBlock, 4), new Object[] { "dwd", "wdw", "dwd", Character.valueOf('d'), Blocks.dirt, Character.valueOf('w'), Items.water_bucket });
    GameRegistry.addRecipe(new ItemStack(MudBlock, 4, 1), new Object[] { " m ", "mwm", " m ", Character.valueOf('m'), new ItemStack(MudBlock, 1, 0), Character.valueOf('w'), Items.water_bucket });
    GameRegistry.addRecipe(new ItemStack(MudBlock, 4, 2), new Object[] { " m ", "mwm", " m ", Character.valueOf('m'), new ItemStack(MudBlock, 1, 1), Character.valueOf('w'), Items.water_bucket });
    GameRegistry.addRecipe(new ItemStack(MudBlock, 4, 3), new Object[] { " m ", "mwm", " m ", Character.valueOf('m'), new ItemStack(MudBlock, 1, 2), Character.valueOf('w'), Items.water_bucket });
    GameRegistry.addRecipe(new ItemStack(MireBucketItem, 1), new Object[] { "wmw", "mbm", "wmw", Character.valueOf('m'), MudBlock, Character.valueOf('w'), Items.water_bucket, Character.valueOf('b'), Items.bucket });
    GameRegistry.addRecipe(new ItemStack(MireBlock, 4), new Object[] { "mwm", "wdw", "mwm", Character.valueOf('m'), MudBlock, Character.valueOf('w'), Items.water_bucket, Character.valueOf('d'), Blocks.dirt });
    GameRegistry.addRecipe(new ItemStack(BrownClayBlock, 4, 0), new Object[] { "cwc", "wdw", "cwc", Character.valueOf('c'), Blocks.clay, Character.valueOf('d'), Blocks.dirt, Character.valueOf('w'), Items.water_bucket });
    GameRegistry.addRecipe(new ItemStack(SoftQuicksandBlock, 4), new Object[] { "wdw", "gwd", "wgw", Character.valueOf('d'), Blocks.dirt, Character.valueOf('w'), Items.water_bucket, Character.valueOf('g'), Blocks.gravel });
    GameRegistry.addRecipe(new ItemStack(SoftGravelBlock, 4, 0), new Object[] { "wdw", "gwd", "wgw", Character.valueOf('d'), Blocks.sand, Character.valueOf('w'), Items.water_bucket, Character.valueOf('g'), Blocks.gravel });
    GameRegistry.addRecipe(new ItemStack(SoftGravelBlock, 4, 1), new Object[] { "cmc", "mwm", "cmc", Character.valueOf('c'), Items.clay_ball, Character.valueOf('m'), new ItemStack(SoftGravelBlock, 1, 0), Character.valueOf('w'), Items.water_bucket });
    GameRegistry.addRecipe(new ItemStack(CSandBlock, 4), new Object[] { "cdc", "dpd", "cdc", Character.valueOf('d'), Blocks.dirt, Character.valueOf('c'), Blocks.soul_sand, Character.valueOf('p'), new ItemStack(MyPotionItem, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(SandBucketItem, 1), new Object[] { new ItemStack(Items.bucket), new ItemStack((Block)Blocks.sand) });
    GameRegistry.addShapelessRecipe(new ItemStack((Block)Blocks.sand, 1), new Object[] { new ItemStack(SandBucketItem) });
    if (TurnBoots) {
      GameRegistry.addRecipe(new ItemStack(PreWadingBoots0, 1), new Object[] { "i i", "i i", "i i", Character.valueOf('i'), Items.leather });
      GameRegistry.addRecipe(new ItemStack(PreWadingBoots1, 1), new Object[] { "sss", "sis", "sss", Character.valueOf('i'), PreWadingBoots0, Character.valueOf('s'), Items.slime_ball });
      GameRegistry.addSmelting(PreWadingBoots1, new ItemStack(WadingBoots), 0.1F);
    } 
    if (AOA && 
      AOAFaceMaskItemID != 0)
      GameRegistry.addRecipe(new ItemStack(AOAFaceMaskItem), new Object[] { " t ", "sgs", "   ", Character.valueOf('g'), GasMaskItem, Character.valueOf('s'), AOADoomStoneItem, Character.valueOf('t'), AOAToxicLumpItem }); 
    if (BoP) {
      if (MudBallID != 0) {
        CraftingManager cfm = CraftingManager.getInstance();
        try {
          Field field1 = Fields.findField(CraftingManager.class, List.class, 0);
          field1.setAccessible(true);
          List my_rec = cfm.getRecipeList();
          boolean rcp_found = false;
          Iterator it_rec = my_rec.iterator();
          if (it_rec != null)
            while (it_rec.hasNext()) {
              Object obj = it_rec.next();
              if (obj != null && 
                obj instanceof ShapedRecipes && (
                (ShapedRecipes)obj).getRecipeOutput().getItem() == Item.getItemFromBlock(BOPMudBlock)) {
                it_rec.remove();
                rcp_found = true;
                break;
              } 
            }  
          if (rcp_found) {
            System.out.println("(MFQM)BiomesOPlenty's mud receipt found");
            field1.set(cfm, my_rec);
            System.out.println("(MFQM)BiomesOPlenty's mud receipt successfully overrided");
          } else {
            System.out.println("(MFQM)BiomesOPlenty's mud receipt not found");
          } 
        } catch (Exception e) {
          e.printStackTrace();
        } 
        GameRegistry.addRecipe(new ItemStack(MudBlock, 1), new Object[] { "MM", "MM", Character.valueOf('M'), MudBall });
      } 
      if (FleshItemID != 0) {
        CraftingManager cfm = CraftingManager.getInstance();
        try {
          Field field1 = Fields.findField(CraftingManager.class, List.class, 0);
          field1.setAccessible(true);
          List my_rec = cfm.getRecipeList();
          boolean rcp_found = false;
          Iterator it_rec = my_rec.iterator();
          if (it_rec != null)
            while (it_rec.hasNext()) {
              Object obj = it_rec.next();
              if (obj != null && 
                obj instanceof ShapedRecipes && (
                (ShapedRecipes)obj).getRecipeOutput().getItem() == Item.getItemFromBlock(HiveBlock)) {
                it_rec.remove();
                rcp_found = true;
                break;
              } 
            }  
          if (rcp_found) {
            System.out.println("(MFQM)BiomesOPlenty's honeycomb1 receipt found");
            field1.set(cfm, my_rec);
            System.out.println("(MFQM)BiomesOPlenty's honeycomb1 receipt successfully removed");
          } else {
            System.out.println("(MFQM)BiomesOPlenty's honeycomb1 receipt not found");
          } 
        } catch (Exception e) {
          e.printStackTrace();
        } 
      } 
      if (FoodItemID != 0) {
        CraftingManager cfm = CraftingManager.getInstance();
        try {
          Field field1 = Fields.findField(CraftingManager.class, List.class, 0);
          field1.setAccessible(true);
          List my_rec = cfm.getRecipeList();
          boolean rcp_found = false;
          Iterator it_rec = my_rec.iterator();
          if (it_rec != null)
            while (it_rec.hasNext()) {
              Object obj = it_rec.next();
              if (obj != null && 
                obj instanceof ShapedRecipes && (
                (ShapedRecipes)obj).getRecipeOutput().getItem() == Item.getItemFromBlock(HiveBlock)) {
                it_rec.remove();
                rcp_found = true;
                break;
              } 
            }  
          if (rcp_found) {
            System.out.println("(MFQM)BiomesOPlenty's honeycomb2 receipt found");
            field1.set(cfm, my_rec);
            System.out.println("(MFQM)BiomesOPlenty's honeycomb2 receipt successfully removed");
          } else {
            System.out.println("(MFQM)BiomesOPlenty's honeycomb2 receipt not found");
          } 
        } catch (Exception e) {
          e.printStackTrace();
        } 
      } 
      if (GenMarshOver) {
        System.out.println("(MFQM)Customizning Marsh Biome...");
        BiomeGenBase biomegenbase = BiomeGenBase.getBiomeGenArray()[SwampBiomes[7]];
        try {
          Field field1 = Fields.findField(BiomeGenBase.class, float.class, 0);
          Field field2 = Fields.findField(BiomeGenBase.class, float.class, 1);
          field1.setAccessible(true);
          field2.setAccessible(true);
          field1.setFloat(biomegenbase, -0.2F);
          field2.setFloat(biomegenbase, -0.1F);
        } catch (Exception e) {
          e.printStackTrace();
        } 
        BiomeGenBase.getBiomeGenArray()[SwampBiomes[7]] = biomegenbase;
        System.out.println("(MFQM)Customizning Complete");
      } 
    } 
    if (GenWaterColor) {
      System.out.println("(MFQM)Customizning water color for swamps...");
      ArrayList<BiomeGenBase> all_biomesList = new ArrayList<BiomeGenBase>();
      ArrayList<BiomeGenBase> swamp_biomesList = new ArrayList<BiomeGenBase>();
      for (BiomeGenBase biome : BiomeGenBase.getBiomeGenArray()) {
        if (biome != null) {
          String bioName = "" + biome;
          boolean needAdd = true;
          String[] parts = bioName.split("[.]");
          if (parts != null && 
            parts.length > 0 && 
            parts[0].matches("(.*)thebetweenlands(.*)"))
            needAdd = false; 
          if (needAdd) {
            all_biomesList.add(biome);
            if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.SWAMP) && 
              !BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.HILLS) && 
              !BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.MAGICAL) && 
              !BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.MUSHROOM) && 
              !BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.PLAINS) && (
              !BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.FOREST) || BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.WET)))
              swamp_biomesList.add(biome); 
          } 
        } 
      } 
      swamp_biomesList.add(BiomeGenBase.swampland);
      BiomeGenBase[] swamp_biomes_array = new BiomeGenBase[swamp_biomesList.size()];
      BiomeGenBase[] swampBiomes = swamp_biomesList.<BiomeGenBase>toArray(swamp_biomes_array);
      for (BiomeGenBase biome : swampBiomes) {
        try {
          Field field1 = Fields.findField(BiomeGenBase.class, int.class, 4);
          field1.setAccessible(true);
          int color = field1.getInt(biome);
          float ColCof = 1.1F;
          int tarColor = 13390080;
          int tcB = tarColor & 0xFF;
          int tcG = (tarColor & 0xFF00) / 256;
          int tcR = (tarColor & 0xFF0000) / 65536;
          int ccB = color & 0xFF;
          int ccG = (color & 0xFF00) / 256;
          int ccR = (color & 0xFF0000) / 65536;
          if (ccR >= 254)
            ccR -= (int)Math.floor((Math.abs(tcG - ccG) + Math.abs(tcB - ccB)) / 2.0D); 
          int rescolor = color;
          rescolor = Math.max(Math.min((int)Math.floor(((tcB - ccB) / ColCof + (ccB / 2))), 255), 0);
          rescolor += Math.max(Math.min((int)Math.floor((((tcG - ccG) / 2) / ColCof + ccG)), 255), 0) * 256;
          rescolor += Math.max(Math.min((int)Math.floor(((tcR - ccR) / ColCof + ccR)), 255), 0) * 65536;
          field1.setInt(biome, rescolor);
        } catch (Exception e) {
          e.printStackTrace();
        } 
      } 
    } 
    proxy.registerRenderInformation();
    System.out.println("(MFQM)Init complete");
  }
  
  @SideOnly(Side.CLIENT)
  void registerClientEntity() {}
  
  public static void dropItem(World world, int x, int y, int z, ItemStack itm) {
    if (!world.isRemote) {
      float var6 = 0.7F;
      double d0 = (world.rand.nextFloat() * var6) + (1.0F - var6) * 0.5D;
      double d1 = (world.rand.nextFloat() * var6) + (1.0F - var6) * 0.5D;
      double d2 = (world.rand.nextFloat() * var6) + (1.0F - var6) * 0.5D;
      EntityItem entityitem = new EntityItem(world, x + d0, y + d1, z + d2, itm);
      entityitem.delayBeforeCanPickup = 10;
      world.spawnEntityInWorld((Entity)entityitem);
    } 
  }
  
  public static void dropItem(World world, double x, double y, double z, ItemStack itm) {
    if (!world.isRemote) {
      EntityItem entityitem = new EntityItem(world, x, y, z, itm);
      entityitem.delayBeforeCanPickup = 10;
      world.spawnEntityInWorld((Entity)entityitem);
    } 
  }
  
  public static boolean isEntityInsideOfBlock(Entity ent, Block bl) {
    Block var7, var8;
    double overCor = 0.0D;
    if (bl.getMaterial().isLiquid() && 
      bl instanceof BlockFluidClassic)
      overCor = (((BlockFluidClassic)bl).getMaxRenderHeightMeta() == -1) ? 0.0D : 0.15D; 
    double var2 = ent.posY + ent.getEyeHeight() - 0.075D + overCor;
    int var4 = MathHelper.floor_double(ent.posX);
    int var5 = MathHelper.floor_float(MathHelper.floor_double(var2));
    int var6 = MathHelper.floor_double(ent.posZ);
    if (bl != MossBlock) {
      var7 = ent.worldObj.getBlock(var4, var5, var6);
      var8 = ent.worldObj.getBlock(var4, var5 + 1, var6);
    } else {
      var7 = ent.worldObj.getBlock(var4, var5 + 1, var6);
      var8 = ent.worldObj.getBlock(var4, var5 + 2, var6);
    } 
    if (var7 == bl) {
      if (bl != MossBlock) {
        if (var8.getMaterial().isSolid() && (
          var5 + 1) > var2)
          return true; 
        if (var5 + var7.getBlockBoundsMaxY() > var2)
          return true; 
        return false;
      } 
      return true;
    } 
    return false;
  }
  
  public static boolean isEntityInsideOfBlockS(Entity ent, Block bl) {
    Block var7, var8;
    double overCor = 0.0D;
    if (bl.getMaterial().isLiquid() && 
      bl instanceof BlockFluidClassic)
      overCor = (((BlockFluidClassic)bl).getMaxRenderHeightMeta() == -1) ? 0.0D : 0.15D; 
    double var2 = ent.posY + ent.getEyeHeight() - 0.2D + overCor;
    int var4 = MathHelper.floor_double(ent.posX);
    int var5 = MathHelper.floor_float(MathHelper.floor_double(var2));
    int var6 = MathHelper.floor_double(ent.posZ);
    if (bl != MossBlock) {
      var7 = ent.worldObj.getBlock(var4, var5, var6);
      var8 = ent.worldObj.getBlock(var4, var5 + 1, var6);
    } else {
      var7 = ent.worldObj.getBlock(var4, var5 + 1, var6);
      var8 = ent.worldObj.getBlock(var4, var5 + 2, var6);
    } 
    if (var7 == bl) {
      if (bl != MossBlock) {
        if (var8.getMaterial().isSolid() && (
          var5 + 1) > var2)
          return true; 
        if (var5 + var7.getBlockBoundsMaxY() > var2)
          return true; 
        return false;
      } 
      return true;
    } 
    return false;
  }
  
  public static boolean isEntityInsideOfBlockM(Entity ent, Block bl) {
    double var2 = ent.posY + ent.getEyeHeight() - 0.2D;
    int var4 = MathHelper.floor_double(ent.posX);
    int var5 = MathHelper.floor_float(MathHelper.floor_double(var2));
    int var6 = MathHelper.floor_double(ent.posZ);
    Block var7 = ent.worldObj.getBlock(var4, var5 + 1, var6);
    if (var7 == bl)
      return true; 
    return false;
  }
  
  public static boolean isEntityInsideOfBlockL(Entity ent, Block bl) {
    double var2 = ent.posY;
    int var4 = MathHelper.floor_double(ent.posX);
    int var5 = MathHelper.floor_float(MathHelper.floor_double(var2));
    int var6 = MathHelper.floor_double(ent.posZ);
    Block var7 = ent.worldObj.getBlock(var4, var5, var6);
    if (var7 == bl) {
      if (var5 + var7.getBlockBoundsMaxY() > var2)
        return true; 
      return isEntityInsideOfBlockS(ent, bl);
    } 
    return isEntityInsideOfBlockS(ent, bl);
  }
  
  public static int getMuddyLevel(EntityPlayer ent, double y, World world) {
    double var2 = ent.posY + ent.getEyeHeight();
    double delta = var2 - y;
    if (delta < 0.7D)
      return 10; 
    if (delta < 1.04D)
      return 9; 
    if (delta < 1.1D)
      return 8; 
    if (delta < 1.21D)
      return 7; 
    if (delta < 1.32D)
      return 6; 
    if (delta < 1.53D)
      return 5; 
    if (delta < 1.67D)
      return 4; 
    if (delta < 1.98D)
      return 3; 
    if (delta < 2.14D)
      return 2; 
    if (delta < 2.4D)
      return 1; 
    return 0;
  }
  
  public static int getMuddyType(Block blc) {
    for (int i = 0; i < EntityRope.quicksandIDS.length; i++) {
      if (EntityRope.quicksandIDS[i] == blc)
        return i; 
    } 
    return -1;
  }
  
  public static int getLastMuddyType(int tp) {
    if (tp < 0)
      return 0; 
    if (tp > EntityRope.quicksandIDS.length)
      return 0; 
    return mudLastOpacity[tp];
  }
  
  public static boolean isTrulySink(Entity ent, double kofm) {
    if (ent instanceof EntityPlayer)
      return ent.worldObj.isRemote; 
    return true;
  }
  
  public static boolean suctionWorldCheck(Entity ent, World world, double Vel) {
    double VelY = Math.max(Vel * 10.0D, 0.25D);
    if (ent instanceof EntityPlayer && 
      QSBootsCalc && 
      world.isRemote) {
      boolean boots = false;
      if (((EntityPlayer)ent).getCurrentArmor(0) != null)
        boots = true; 
      if (boots)
        VelY = Math.max(VelY / 2.0D, 0.0D); 
    } 
    if (world.getTotalWorldTime() % Math.floor(Math.max(10.0D * Math.pow(VelY, 2.0D), 1.0D)) == 0.0D && 
      world.rand.nextInt(5) == 0)
      return true; 
    return false;
  }
  
  public static void antiHoldJumpScript(Entity ent, double Kof, boolean Stuck) {
    if (!(ent instanceof EntityPlayer))
      return; 
    if (ent.worldObj.isRemote) {
      double weight = 0.0D;
      double AWeight = 0.0D;
      double IWeight = 0.0D;
      if ((EntityPlayer)ent == (Minecraft.getMinecraft()).thePlayer) {
        if (QSArmorCalc) {
          double rdmg = 0.0D;
          if (((EntityPlayer)ent).getCurrentArmor(0) != null && (
            (EntityPlayer)ent).getCurrentArmor(0).getItem() instanceof ItemArmor) {
            rdmg = ((ItemArmor)((EntityPlayer)ent).getCurrentArmor(0).getItem()).getArmorMaterial().getDamageReductionAmount(3);
            AWeight += Math.signum(Math.max(1.3D - Kof, 0.0D)) * (rdmg + 10.0D / Math.max(rdmg, 1.0D)) / 2.0D + Math.pow(rdmg, 2.0D);
          } 
          if (((EntityPlayer)ent).getCurrentArmor(1) != null && (
            (EntityPlayer)ent).getCurrentArmor(1).getItem() instanceof ItemArmor) {
            rdmg = ((ItemArmor)((EntityPlayer)ent).getCurrentArmor(1).getItem()).getArmorMaterial().getDamageReductionAmount(2);
            AWeight += Math.signum(Math.max(0.9D - Kof, 0.0D)) * (rdmg + 10.0D / Math.max(rdmg, 1.0D)) / 2.0D + Math.pow(rdmg, 2.0D);
          } 
          if (((EntityPlayer)ent).getCurrentArmor(2) != null && (
            (EntityPlayer)ent).getCurrentArmor(2).getItem() instanceof ItemArmor) {
            rdmg = ((ItemArmor)((EntityPlayer)ent).getCurrentArmor(2).getItem()).getArmorMaterial().getDamageReductionAmount(1);
            AWeight += Math.signum(Math.max(0.5D - Kof, 0.0D)) * (rdmg + 10.0D / Math.max(rdmg, 1.0D)) / 2.0D + Math.pow(rdmg, 2.0D);
          } 
          if (((EntityPlayer)ent).getCurrentArmor(3) != null && (
            (EntityPlayer)ent).getCurrentArmor(3).getItem() instanceof ItemArmor) {
            rdmg = ((ItemArmor)((EntityPlayer)ent).getCurrentArmor(3).getItem()).getArmorMaterial().getDamageReductionAmount(0);
            AWeight += Math.signum(Math.max(0.1D - Kof, 0.0D)) * (rdmg + 10.0D / Math.max(rdmg, 1.0D)) / 2.0D + Math.pow(rdmg, 2.0D);
          } 
        } 
        if (QSWeightCalc) {
          IWeight = SIWeight;
          if (ent.worldObj.getTotalWorldTime() % 32L == 0L) {
            IWeight = 0.0D;
            for (int i = 0; i <= 35; i++) {
              if (((EntityPlayer)ent).inventory.mainInventory[i] != null) {
                ItemStack tpmItem = ((EntityPlayer)ent).inventory.mainInventory[i];
                if (tpmItem.getItem() instanceof net.minecraft.item.ItemBlock) {
                  Block bl = Block.getBlockFromItem(tpmItem.getItem());
                  if (bl != null) {
                    if (bl.hasTileEntity()) {
                      IWeight += (10 * tpmItem.stackSize);
                    } else {
                      Material mat = bl.getMaterial();
                      IWeight += (2.5D + (mat.getCanBurn() ? false : true) * 2.5D + ((bl.canDropFromExplosion(null) ? true : false) * bl.getExplosionResistance(null)) / 2.0D) / ((bl.isOpaqueCube() ? false : true) + 1.0D) * (((mat.getMaterialMobility() == 2) ? true : false) + 1.0D) / 2.0D * tpmItem.stackSize;
                    } 
                  } else {
                    IWeight += 0.5D * tpmItem.stackSize;
                  } 
                } else if (tpmItem.getItem() instanceof ItemArmor) {
                  IWeight += Math.pow(((ItemArmor)tpmItem.getItem()).getArmorMaterial().getDamageReductionAmount(((ItemArmor)tpmItem.getItem()).armorType), 2.0D) * tpmItem.stackSize;
                } else if (tpmItem.getItem().isDamageable()) {
                  IWeight += (5 * tpmItem.stackSize);
                } else if (tpmItem.getItem().getItemStackLimit() == 1) {
                  IWeight += 2.5D * tpmItem.stackSize;
                } else {
                  IWeight += 0.5D * tpmItem.stackSize;
                } 
              } 
            } 
            SIWeight = IWeight;
          } 
        } 
        weight = Math.max(-250.0D + IWeight / 2.5D * (1.0D + Math.signum(Math.max(0.75D - Kof, 0.0D)) * 1.5D), 0.0D) + AWeight;
        ent.motionY -= Math.min(weight, 2000.0D * Math.max((Kof - 0.25D) * 5.0D, 1.0D)) / 300000.0D;
      } 
    } 
    if (Stuck && QSBootsCalc) {
      boolean boots = false;
      boolean bootsIsFloat = false;
      if (((EntityPlayer)ent).getCurrentArmor(0) != null) {
        if (((EntityPlayer)ent).getCurrentArmor(0).getItem() == WadingBoots)
          bootsIsFloat = true; 
        boots = true;
      } 
      if (Kof < 1.3D)
        bootsIsFloat = false; 
      if (boots && Kof < 1.475D && !bootsIsFloat) {
        boolean wasAtGround = ent.onGround;
        ent.onGround = false | ent.isCollidedVertically;
        if (ent.worldObj.getTotalWorldTime() % Math.max(1.0D + Math.floor(20.0D / Math.max(Kof * 5.0D, 0.01D)), 1.0D) == 0.0D)
          ent.onGround = wasAtGround; 
        if (ent.worldObj.isRemote) {
          PotionStuck props = PotionStuck.get((EntityLivingBase)ent);
          int StLevel = -1;
          if (props != null)
            StLevel = props.getLevel(); 
          setStuckEffect((EntityLivingBase)ent, Math.max(Math.max(Math.min((int)Math.floor(Math.pow(10.0D * Math.max(1.5D - Kof, 0.0D), 2.0D)) + StLevel, 255), 5), StLevel));
        } 
      } 
    } 
    if (jumpKey((EntityPlayer)ent)) {
      ent.onGround = false;
      return;
    } 
  }
  
  public static boolean jumpKey(EntityPlayer curPlayer) {
    if (curPlayer.worldObj.isRemote && 
      (Minecraft.getMinecraft()).thePlayer == curPlayer)
      return GameSettings.isKeyDown((Minecraft.getMinecraft()).gameSettings.keyBindJump); 
    return false;
  }
  
  public static boolean crouchKey(EntityPlayer curPlayer) {
    if (curPlayer.worldObj.isRemote && 
      (Minecraft.getMinecraft()).thePlayer == curPlayer)
      return GameSettings.isKeyDown((Minecraft.getMinecraft()).gameSettings.keyBindSneak); 
    return false;
  }
  
  public static double surfaceY(Block bl) {
    if (bl instanceof BlockFluidClassic) {
      if (((BlockFluidClassic)bl).getMaxRenderHeightMeta() != -1)
        return 0.85D; 
      return 1.0D;
    } 
    return bl.getBlockBoundsMaxY();
  }
  
  public static Entity getEntityByUUID(World world, Entity ent, UUID uid, double radius) {
    AxisAlignedBB bbox = AxisAlignedBB.getBoundingBox(ent.posX - radius, ent.posY - radius, ent.posZ - radius, ent.posX + radius, ent.posY + radius, ent.posZ + radius);
    for (Object o : world.getEntitiesWithinAABBExcludingEntity(ent, bbox)) {
      if (uid.equals(((Entity)o).getUniqueID()))
        return (Entity)o; 
    } 
    return null;
  }
  
  public static void SpawnMudTentacles(World world, Entity ent, int x, int y, int z, Block bl, int mt, int rate, int chance) {
    if (QSMudTentacles && 
      !world.isRemote && 
      ent != null && 
      ent instanceof EntityLivingBase && 
      ent.width < 1.75F && !ent.isCollidedVertically && 
      world.getTotalWorldTime() % rate == 0L && 
      world.rand.nextInt(chance) == 0 && 
      CanBeMudTentacles(world, x, y, z, bl, mt)) {
      PotionEffect PEWeak = ((EntityLivingBase)ent).getActivePotionEffect(Potion.weakness);
      PotionEffect PESlow = ((EntityLivingBase)ent).getActivePotionEffect(Potion.digSlowdown);
      int weakLevel = -1;
      int slowLevel = -1;
      if (PEWeak != null)
        weakLevel = PEWeak.getAmplifier(); 
      if (PESlow != null)
        slowLevel = PESlow.getAmplifier(); 
      if (weakLevel <= -1 || slowLevel <= 2)
        world.spawnEntityInWorld((Entity)new EntityMudTentacles(world, ent.posX, Math.min(ent.posY + 0.5D, (y + 1)), ent.posZ, ent, y + 1, 0)); 
    } 
  }
  
  public static void HandleMudTentacles(World world, Entity ent, int x, int y, int z, Block bl, int mt) {
    if (QSMudTentacles && 
      !world.isRemote && 
      ent != null && 
      ent instanceof EntityItem && (
      (EntityItem)ent).getEntityItem().getItem() instanceof ItemFood && !ent.isCollidedVertically && (
      (ItemFood)((EntityItem)ent).getEntityItem().getItem()).isWolfsFavoriteMeat() && 
      world.getTotalWorldTime() % 64L == 0L && 
      world.rand.nextInt(5) == 0 && 
      CanBeMudTentacles(world, x, y, z, bl, mt)) {
      ent.attackEntityFrom(DamageSource.generic, 1000.0F);
      world.spawnEntityInWorld((Entity)new EntityLongStick(world, ent.posX, (y + 1), ent.posZ, null, x, y, z));
    } 
  }
  
  public static boolean CanBeMudTentacles(World world, int x, int y, int z, Block bl, int mt) {
    if (QSMudTentacles && 
      !world.isRemote) {
      if (AOA) {
        if (world.provider.dimensionId != 0 && world.provider.dimensionId != AOADimGardencia)
          return false; 
      } else if (world.provider.dimensionId != 0) {
        return false;
      } 
      if (world.getBlock(x, y + 1, z) != bl && 
        world.getBlock(x, y - 1, z) == bl && 
        world.getBlock(x - 1, y, z) == bl && 
        world.getBlock(x + 1, y, z) == bl && 
        world.getBlock(x, y, z - 1) == bl && 
        world.getBlock(x, y, z + 1) == bl) {
        Chunk chn = world.getChunkFromBlockCoords(x, z);
        int chance = 10;
        if (bl == JungleQuicksandBlock) {
          chance = 10;
        } else if (bl == MudBlock) {
          chance = 40;
        } else {
          chance = 20;
        } 
        if (chn.getRandomWithSeed(987654321L).nextInt(chance) == 0 && 
          CustomWorldGen.isAboveSky(x, y, z, world))
          return true; 
      } 
    } 
    return false;
  }
  
  public static void spawnBubble(World world, double x, double y, double z, Block bl, int mt, float sz, int tim) {
    world.spawnEntityInWorld((Entity)new EntityBubble(world, x, y, z, bl, mt, sz, tim));
  }
  
  public static void spawnBubbleDelay(World world, double x, double y, double z, Block bl, int mt, float sz, int tim, int dly) {
    world.spawnEntityInWorld((Entity)new EntityBubble(world, x, y, z, bl, mt, sz, tim, dly));
  }
  
  public static void spawnQSBubble(World world, double x, double y, double z, Block bl, int mt, float vol) {
    if (!world.isRemote)
      return; 
    if (!QSBubble) {
      if (vol > 0.4F)
        for (int i = 0; i < 3; i++)
          world.spawnParticle("blockcrack_" + Block.getIdFromBlock(bl) + "_" + mt, x, y, z, 0.0D, 0.0D, 0.0D);  
    } else if (vol > 0.4F) {
      float size = 2.5F - world.rand.nextFloat() * 1.75F;
      int time = (int)Math.floor(((1000 + world.rand.nextInt(500)) * size));
      spawnBubble(world, x, y, z, bl, mt, size, time);
      return;
    } 
    world.playSound(x, y, z, "liquid.lavapop", vol + world.rand.nextFloat() * 0.25F, 0.25F + world.rand.nextFloat() * 0.5F, false);
  }
  
  public static void spawnBodyBubble(World world, Entity ent, int x, int y, int z, Block bl, int mt) {
    if (!world.isRemote)
      return; 
    double xx = ent.posX + (world.rand.nextFloat() * 2.0F) - 1.0D;
    double zz = ent.posZ + (world.rand.nextFloat() * 2.0F) - 1.0D;
    if (world.getBlock((int)Math.floor(xx), y, (int)Math.floor(zz)) != bl)
      return; 
    if (mt == -1)
      mt = world.getBlockMetadata((int)Math.floor(xx), y, (int)Math.floor(zz)); 
    float size = 1.25F - world.rand.nextFloat() * 0.5F;
    int time = (int)Math.floor(((1000 + world.rand.nextInt(500)) * size));
    spawnBubble(world, xx, y + surfaceY(bl), zz, bl, mt, size, time);
  }
  
  public static void spawnBodyBubbleRandom(World world, Entity ent, int x, int y, int z, Block bl, int mt) {
    if (!world.isRemote)
      return; 
    double xx = ent.posX + (world.rand.nextFloat() * 2.0F) - 1.0D;
    double zz = ent.posZ + (world.rand.nextFloat() * 2.0F) - 1.0D;
    if (world.getBlock((int)Math.floor(xx), y, (int)Math.floor(zz)) != bl)
      return; 
    if (mt == -1)
      mt = world.getBlockMetadata((int)Math.floor(xx), y, (int)Math.floor(zz)); 
    float size = 1.25F - world.rand.nextFloat() * 0.5F;
    int time = (int)Math.floor(((1000 + world.rand.nextInt(500)) * size));
    spawnBubbleDelay(world, xx, y + surfaceY(bl), zz, bl, mt, size, time, world.rand.nextInt(20) * 100);
  }
  
  public static void spawnDrowningBubble(World world, Entity ent, Block bl, int mt) {
    if (!world.isRemote)
      return; 
    if (!ent.isEntityAlive())
      return; 
    if (ent instanceof EntityPlayer && 
      ((EntityPlayer)ent).capabilities.disableDamage)
      return; 
    if (world.getTotalWorldTime() % 48L == 0L && 
      world.rand.nextInt(2) == 0)
      for (int i = 0; i < 4 + world.rand.nextInt(6); i++) {
        double xx = ent.posX + (world.rand.nextFloat() * 1.0F) - 0.5D;
        double zz = ent.posZ + (world.rand.nextFloat() * 1.0F) - 0.5D;
        double yy = ent.posY + ent.getEyeHeight();
        if (ent instanceof EntityPlayer)
          yy += -0.15D; 
        int xxx = (int)Math.floor(xx);
        int yyy = (int)Math.floor(yy);
        int zzz = (int)Math.floor(zz);
        if (world.getBlock(xxx, yyy, zzz) != bl)
          return; 
        if (mt == -1)
          mt = world.getBlockMetadata(xxx, yyy, zzz); 
        float size = 1.25F - world.rand.nextFloat() * 1.0F;
        int time = (int)Math.floor(((1000 + world.rand.nextInt(500)) * size));
        spawnBubbleDelay(world, xx, yyy + surfaceY(bl), zz, bl, mt, size, time, i * 100 + world.rand.nextInt(40) * 100);
      }  
  }
  
  public static void spawnStickBubble(World world, double x, double y, double z, Block bl, int mt, boolean deep) {
    if (!world.isRemote)
      return; 
    int rnd = 4 + world.rand.nextInt(6);
    if (!deep)
      rnd = 2 + world.rand.nextInt(3); 
    for (int i = 0; i < rnd; i++) {
      double xx = x + (world.rand.nextFloat() * 1.0F) - 0.5D;
      double zz = z + (world.rand.nextFloat() * 1.0F) - 0.5D;
      double yy = y - 1.0D;
      int xxx = (int)Math.floor(xx);
      int yyy = (int)Math.floor(yy);
      int zzz = (int)Math.floor(zz);
      if (world.getBlock(xxx, yyy, zzz) != bl)
        return; 
      if (mt == -1)
        mt = world.getBlockMetadata(xxx, yyy, zzz); 
      float size = 1.25F - world.rand.nextFloat() * 1.0F;
      int time = (int)Math.floor(((1000 + world.rand.nextInt(500)) * size));
      spawnBubbleDelay(world, xx, yyy + surfaceY(bl), zz, bl, mt, size, time, i * 200 + world.rand.nextInt(10) * 100);
    } 
  }
  
  public static int getMuddyAir(Entity ent) {
    try {
      if (ent instanceof EntityPlayer)
        return ent.getDataWatcher().getWatchableObjectInt(MuddyAir); 
      return ent.getDataWatcher().getWatchableObjectInt(MuddyMobsAir);
    } catch (Exception e) {
      return 0;
    } 
  }
  
  public static void setMuddyAir(Entity ent, int lvl) {
    try {
      if (ent instanceof EntityPlayer) {
        ent.getDataWatcher().updateObject(MuddyAir, Integer.valueOf(lvl));
      } else {
        ent.getDataWatcher().updateObject(MuddyMobsAir, Integer.valueOf(lvl));
      } 
    } catch (Exception e) {
      return;
    } 
  }
  
  public static void addMuddyAir(Entity ent, int tm) {
    try {
      if (ent instanceof EntityPlayer) {
        ent.getDataWatcher().updateObject(MuddyAir, Integer.valueOf(ent.getDataWatcher().getWatchableObjectInt(MuddyAir) + tm));
      } else {
        ent.getDataWatcher().updateObject(MuddyMobsAir, Integer.valueOf(ent.getDataWatcher().getWatchableObjectInt(MuddyMobsAir) + tm));
      } 
    } catch (Exception e) {
      return;
    } 
  }
  
  public static boolean isDrowning(Entity ent) {
    if (ent instanceof EntityPlayer) {
      if (!QSAir)
        return true; 
    } else if (!QSMAir) {
      return true;
    } 
    if (ent instanceof EntityPlayer && 
      ((EntityPlayer)ent).capabilities.disableDamage)
      return false; 
    int gma = getMuddyAir(ent);
    if (!ent.worldObj.isRemote) {
      if (gma < 0 && 
        ent.worldObj.getTotalWorldTime() % 16L == 0L) {
        setMuddyAir(ent, 0);
        return true;
      } 
    } else if (gma < 1) {
      return true;
    } 
    return false;
  }
  
  public static boolean isChildOf(Class<? super T> parent, Class<T> child) {
    if (child.getSuperclass() == parent)
      return true; 
    if (child.getSuperclass() != Entity.class)
      return isChildOf(parent, child.getSuperclass()); 
    return false;
  }
  
  public static void setStuckEffect(EntityLivingBase elb, int level) {
    if (PotionStuck.get(elb) != null)
      PotionStuck.get(elb).setStuckLevel(level); 
  }
}
