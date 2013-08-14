package com.nekokittygames.TechWorld;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.logging.Level;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DimensionManager;

public class Configs {

    @Retention(RetentionPolicy.RUNTIME)
    private static @interface CfgBool {
    }

    @Retention(RetentionPolicy.RUNTIME)
    private static @interface CfgDouble {
    }

    @Retention(RetentionPolicy.RUNTIME)
    private static @interface CfgId {
        public boolean block() default false;
    }

    @Retention(RetentionPolicy.RUNTIME)
    private static @interface CfgInteger {
    }

    @Retention(RetentionPolicy.RUNTIME)
    private static @interface CfgString {
    }

    @CfgInteger
    public static int TechDimensionID=DimensionManager.getNextFreeDimId();
    
    @CfgInteger
    public static int MainBiomeID=27;
    
    @CfgInteger
    public static int FarmBiomeID=28;
    
    @CfgInteger
    public static int PowerBiomeID=29;
    
    @CfgId(block=true)
    public static  int TechFireID = 2024;
    
    @CfgId(block=true)
    public static  int TechPortalID = 2023;
    
    @CfgId(block=false)
    public static  int TechFlintID = 5003;
    
    @CfgId(block=false)
    public static  int TechPortalPlacerID = 5004;
    
    public static void load(Configuration config) {
        try {
            config.load();
            final Field[] fields = Configs.class.getFields();
            for (final Field field : fields) {
                final CfgId annotation = field.getAnnotation(CfgId.class);
                if (annotation != null) {
                    int id = field.getInt(null);
                    if (annotation.block()) {
                        id = config.getBlock(field.getName(), id).getInt();
                    } else {
                        id = config.getItem(field.getName(), id).getInt();
                    }
                    field.setInt(null, id);
                } else {
                    if (field.isAnnotationPresent(CfgBool.class)) {
                        boolean bool = field.getBoolean(null);
                        bool = config.get(Configuration.CATEGORY_GENERAL,
                                field.getName(), bool).getBoolean(bool);
                        field.setBoolean(null, bool);
                    } else if (field.isAnnotationPresent(CfgString.class)) {
                        String string = (String) field.get(null);
                        string = config.get(Configuration.CATEGORY_GENERAL,
                                field.getName(), string).getString();
                        field.set(null, string);
                    } else if (field.isAnnotationPresent(CfgDouble.class)) {
                        double doub = field.getDouble(null);
                        doub = config.get(Configuration.CATEGORY_GENERAL,
                                field.getName(), doub).getDouble(doub);
                        field.set(null, doub);
                    } else if (field.isAnnotationPresent(CfgInteger.class)) {
                        int integer = field.getInt(null);
                        integer = config.get(Configuration.CATEGORY_GENERAL,
                                field.getName(), integer).getInt();
                        field.set(null, integer);
                    }
                }
            }
        } catch (final Exception e) {
            TechWorld.logging.log(Level.SEVERE,
                    "Got an error in loading config!", e);
        } finally {
            config.save();
        }
    }
}
