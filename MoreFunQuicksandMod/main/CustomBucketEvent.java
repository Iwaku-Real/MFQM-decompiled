package MoreFunQuicksandMod.main;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.FillBucketEvent;

public class CustomBucketEvent {
  @SubscribeEvent
  public void onBucketFill(FillBucketEvent event) {
    ItemStack result = fillBucket(event.world, event.target);
    if (result == null)
      return; 
    event.result = result;
    event.setResult(Event.Result.ALLOW);
  }
  
  public ItemStack fillBucket(World world, MovingObjectPosition pos) {
    Block blockID = world.getBlock(pos.blockX, pos.blockY, pos.blockZ);
    int mtd = world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ);
    if (blockID == MFQM.BogBlock && mtd == 0) {
      world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
      return new ItemStack(MFQM.BogBucketItem, 1);
    } 
    if (blockID == MFQM.LiquidMireBlock && mtd == 0) {
      world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
      return new ItemStack(MFQM.MireBucketItem, 1);
    } 
    if (blockID == MFQM.StableLiquidMireBlock && mtd == 0) {
      world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
      return new ItemStack(MFQM.MireBucketItem, 1);
    } 
    if (blockID == MFQM.JungleQuicksandBlock && mtd == 0) {
      world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
      return new ItemStack(MFQM.QuicksandBucketItem, 1);
    } 
    if (blockID == MFQM.SlimeBlock && mtd == 0) {
      world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
      return new ItemStack(MFQM.SlimeBucketItem, 1);
    } 
    if (blockID == MFQM.SandBlock && mtd == 0) {
      world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
      return new ItemStack(MFQM.SandBucketItem, 1);
    } 
    if (blockID == MFQM.TarBlock && mtd == 0) {
      world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
      return new ItemStack(MFQM.TarBucketItem, 1);
    } 
    if (blockID == MFQM.AcidBlock && mtd == 0) {
      world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
      return new ItemStack(MFQM.AcidBucketItem, 1);
    } 
    if (blockID == MFQM.MucusBlock && mtd == 0) {
      world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
      return new ItemStack(MFQM.MucusBucketItem, 1);
    } 
    if (blockID == MFQM.BrownClayBlock && mtd < 4) {
      world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
      return new ItemStack(MFQM.ClayBucketItem, 1, 0);
    } 
    if (blockID == MFQM.BrownClayBlock && mtd > 3) {
      world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
      return new ItemStack(MFQM.ClayBucketItem, 1, 1);
    } 
    if (blockID == MFQM.LiquidChocolateBlock && mtd == 0) {
      world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
      return new ItemStack(MFQM.ChocolateBucketItem, 1);
    } 
    if (blockID == MFQM.SlurryBlock && mtd == 0) {
      world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
      return new ItemStack(MFQM.SlurryBucketItem, 1);
    } 
    if (blockID == MFQM.HoneyBlock && mtd == 0) {
      world.setBlock(pos.blockX, pos.blockY, pos.blockZ, Blocks.air);
      return new ItemStack(MFQM.HoneyBucketItem, 1);
    } 
    return null;
  }
}
