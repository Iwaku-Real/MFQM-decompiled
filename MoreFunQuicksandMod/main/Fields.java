package MoreFunQuicksandMod.main;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.relauncher.ReflectionHelper;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public final class Fields {
  public static Object findFieldAndGet(Class<?> target, Class<?> fieldType, Object targetObject, int index) {
    for (Field field : target.getDeclaredFields()) {
      field.setAccessible(true);
      if (field.getType().isAssignableFrom(fieldType)) {
        if (index == 0)
          try {
            return field.get(targetObject);
          } catch (Exception e) {
            e.printStackTrace();
          }  
        index--;
      } 
    } 
    return null;
  }
  
  public static Object[] findFieldsAndGet(Class<?> target, Class<?> fieldType, Object targetObject) {
    List<Object> list = new ArrayList();
    for (Field field : target.getDeclaredFields()) {
      field.setAccessible(true);
      if (field.getType().isAssignableFrom(fieldType))
        try {
          list.add(field.get(targetObject));
        } catch (Exception e) {
          e.printStackTrace();
        }  
    } 
    return list.toArray(new Object[0]);
  }
  
  public static Field findField(Class<?> target, Class<?> fieldType, int index) {
    for (Field field : target.getDeclaredFields()) {
      field.setAccessible(true);
      if (field.getType().isAssignableFrom(fieldType)) {
        if (index == 0)
          return field; 
        index--;
      } 
    } 
    return null;
  }
  
  public static Field[] findFields(Class<?> target, Class<?> fieldType) {
    return findFields(target, fieldType, 0);
  }
  
  public static Field[] findFields(Class<?> target, Class<?> fieldType, int depth) {
    List<Field> list = new ArrayList<Field>();
    while (target != null && target != Object.class) {
      for (Field field : target.getDeclaredFields()) {
        field.setAccessible(true);
        if (field.getType().isAssignableFrom(fieldType))
          list.add(field); 
      } 
      target = target.getSuperclass();
      if (depth != -1 && depth-- == 0)
        break; 
    } 
    return list.<Field>toArray(new Field[0]);
  }
  
  public static <T> Field removeFinal(Class<? super T> classToAccess, T instance, String... fieldNames) {
    Field field = ReflectionHelper.findField(classToAccess, ObfuscationReflectionHelper.remapFieldNames(classToAccess.getName(), fieldNames));
    try {
      Field modifiersField = Field.class.getDeclaredField("modifiers");
      modifiersField.setAccessible(true);
      modifiersField.setInt(field, field.getModifiers() & 0xFFFFFFEF);
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return field;
  }
}
