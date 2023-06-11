package MoreFunQuicksandMod.main;

import cpw.mods.fml.common.registry.GameRegistry;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.stats.StatCrafting;
import net.minecraft.stats.StatList;
import net.minecraft.util.ObjectIntIdentityMap;
import net.minecraft.util.RegistryNamespaced;
import net.minecraft.util.RegistrySimple;

public final class CustomRegister {
  public void registerItem(String id, Item item, String modId) {
    if (!id.startsWith(modId + ":")) {
      GameRegistry.registerItem(item, id);
    } else {
      RegistryNamespaced registry = Item.itemRegistry;
      try {
        Item item0 = (Item)registry.getObject(id);
        Integer id0 = Integer.valueOf(registry.getIDForObject(item0));
        Map<Object, Object> map0 = null;
        ObjectIntIdentityMap map1 = null;
        Field field0 = Fields.findField(RegistrySimple.class, Map.class, 0);
        field0.setAccessible(true);
        Field field1 = Fields.findField(RegistryNamespaced.class, ObjectIntIdentityMap.class, 0);
        field1.setAccessible(true);
        map0 = (Map<Object, Object>)field0.get(registry);
        map1 = (ObjectIntIdentityMap)field1.get(registry);
        map0.remove(id);
        map1.func_148746_a(item, id0.intValue());
        map0.put(id, item);
        map0.put("removed:" + id.replace(modId + ":", ""), item0);
        item0.setCreativeTab(null);
        for (Field field : Items.class.getFields()) {
          int modifiers = field.getModifiers();
          if (Modifier.isStatic(modifiers))
            try {
              if (field.get((Object)null).equals(item0)) {
                Field field3 = Field.class.getDeclaredField("modifiers");
                field3.setAccessible(true);
                field3.set(field, Integer.valueOf(modifiers & 0xFFFFFFEF));
                field.setAccessible(true);
                field.set((Object)null, item);
              } 
            } catch (Exception e) {
              e.printStackTrace();
            }  
        } 
        Field field2 = Fields.findField(StatCrafting.class, Item.class, 0);
        field2.setAccessible(true);
        List<StatCrafting> list = StatList.itemStats;
        Iterator<StatCrafting> it = list.iterator();
        while (it.hasNext()) {
          StatCrafting stat = it.next();
          if (field2.get(stat) == item0) {
            Field field3 = Field.class.getDeclaredField("modifiers");
            field3.setAccessible(true);
            field3.set(field2, Integer.valueOf(field2.getModifiers() & 0xFFFFFFEF));
            field2.setAccessible(true);
            field2.set(stat, item);
          } 
        } 
      } catch (Exception e) {
        e.printStackTrace();
      } 
    } 
  }
  
  public void registerBlock(String id, Block block, String modId) {
    if (!id.startsWith(modId + ":")) {
      GameRegistry.registerBlock(block, id);
    } else {
      RegistryNamespaced registry = Block.blockRegistry;
      try {
        Block block0 = (Block)registry.getObject(id);
        Integer id0 = Integer.valueOf(registry.getIDForObject(block0));
        Map<Object, Object> map0 = null;
        ObjectIntIdentityMap map1 = null;
        Field field0 = Fields.findField(RegistrySimple.class, Map.class, 0);
        field0.setAccessible(true);
        Field field1 = Fields.findField(RegistryNamespaced.class, ObjectIntIdentityMap.class, 0);
        field1.setAccessible(true);
        map0 = (Map<Object, Object>)field0.get(registry);
        map1 = (ObjectIntIdentityMap)field1.get(registry);
        map0.remove(id);
        map1.func_148746_a(block, id0.intValue());
        map0.put(id, block);
        map0.put("removed:" + id.replace(modId + ":", ""), block0);
        block0.setCreativeTab(null);
        for (Field field : Blocks.class.getFields()) {
          int modifiers = field.getModifiers();
          if (Modifier.isStatic(modifiers))
            try {
              if (field.get((Object)null).equals(block0)) {
                Field field2 = Field.class.getDeclaredField("modifiers");
                field2.setAccessible(true);
                field2.set(field, Integer.valueOf(modifiers & 0xFFFFFFEF));
                field.setAccessible(true);
                field.set((Object)null, block);
              } 
            } catch (Exception e) {
              e.printStackTrace();
            }  
        } 
      } catch (Exception e) {
        e.printStackTrace();
      } 
    } 
  }
}
