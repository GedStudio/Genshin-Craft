package net.deechael.genshin.lib.open;

import org.bukkit.Bukkit;
import sun.misc.Unsafe;

import java.lang.reflect.*;

public class Ref {


    private static Unsafe UNSAFE;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = (Unsafe) field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException ignored) {
        }
    }

    public static Unsafe getUnsafe() {
        return UNSAFE;
    }

    public static boolean isExtendedFrom(Class<?> child, Class<?> parent) {
        return parent.isAssignableFrom(child);
    }

    public static boolean isProtected(Class<?> clazz) {
        return Modifier.isProtected(clazz.getModifiers());
    }

    public static boolean isPrivate(Class<?> clazz) {
        return Modifier.isPrivate(clazz.getModifiers());
    }

    public static boolean isPublic(Class<?> clazz) {
        return Modifier.isPublic(clazz.getModifiers());
    }

    public static boolean isAbstract(Class<?> clazz) {
        return Modifier.isAbstract(clazz.getModifiers());
    }

    public static boolean isFinal(Class<?> clazz) {
        return Modifier.isFinal(clazz.getModifiers());
    }

    public static boolean isAnnotation(Class<?> clazz) {
        return clazz.isAnnotation();
    }

    public static boolean isInterface(Class<?> clazz) {
        return clazz.isInterface();
    }

    public static boolean isArray(Class<?> clazz) {
        return clazz.isArray();
    }

    public static boolean isEnum(Class<?> clazz) {
        return clazz.isEnum();
    }

    public static Class<?> getClass(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static Class<?> getArrayClass(Class<?> contentClass) {
        return Array.newInstance(contentClass, 0).getClass();
    }

    public static Class<?> getContentClass(Class<?> arrayClass) {
        return arrayClass.isArray() ? arrayClass.getComponentType() : null;
    }

    public static <T extends Enum<T>> T getEnumObject(Class<T> enumClass, String name) {
        return Enum.valueOf(enumClass, name);
    }

    public static <T> T newInstance(Constructor<T> constructor, Object... arguments) {
        try {
            return constructor.newInstance(arguments);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException ignored) {
        }
        return null;
    }

    public static Constructor<?> getConstructor(Class<?> clazz, Class<?>... arguments) {
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor(arguments);
            constructor.setAccessible(true);
            return constructor;
        } catch (NoSuchMethodException ignored) {
        }
        return null;
    }

    public static Field getField(Class<?> clazz, String fieldName) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException ignored) {
        }
        return null;
    }

    public static Method getMethod(Class<?> clazz, String methodName, Class<?>... classes) {
        try {
            Method method = clazz.getDeclaredMethod(methodName, classes);
            method.setAccessible(true);
            return method;
        } catch (NoSuchMethodException ignored) {
        }
        return null;
    }

    public static long getFieldOffset(Field field) {
        return Modifier.isStatic(field.getModifiers()) ? UNSAFE.staticFieldOffset(field) : UNSAFE.objectFieldOffset(field);
    }

    public static Class<?> getInnerClass(Class<?> owner, String name) {
        return getClass(owner.getName() + "$" + name);
    }

    public static Class<?> getOwnerClass(Class<?> innerClass) {
        String className = innerClass.getName();
        if (!className.contains("$")) {
            return null;
        }
        String[] split = className.split("\\$");
        return getClass(className.substring(0, split[split.length - 1].length() - 1));
    }

    public static Class<?> getOutestClass(Class<?> innerClass) {
        String className = innerClass.getName();
        if (!className.contains("$")) {
            return null;
        }
        return getClass(className.split("\\$")[0]);
    }

    public static boolean implemented(Class<?> clazz) {
        return clazz.getInterfaces().length > 0;
    }

    public static boolean extended(Class<?> clazz) {
        return clazz.getSuperclass() != null;
    }

    public static <T> T newInstance(Class<T> clazz) {
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            if (constructor.getParameterCount() == 0) {
                try {
                    return clazz.cast(constructor.newInstance());
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException ignored) {
                    try {
                        return clazz.cast(UNSAFE.allocateInstance(clazz));
                    } catch (InstantiationException e) {
                        return null;
                    }
                }
            }
        }
        try {
            return clazz.cast(UNSAFE.allocateInstance(clazz));
        } catch (InstantiationException e) {
            return null;
        }
    }

    public static Object invoke(Object object, Method method, Object... objects) {
        try {
            return method.invoke(object, objects);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object invoke(Object object, Field field) {
        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Minecraft Server Part

    public static boolean isPaper() {
        try {
            Class.forName("com.destroystokyo.paper.ParticleBuilder");
            return true;
        } catch (ClassNotFoundException ignored) {
            return false;
        }
    }

    public static Field getFieldOrOld(Class<?> clazz, String newName, String oldName) {
        if (Ref.getVersion() >= 16) return getField(clazz, newName);
        return getField(clazz, oldName);
    }

    public static Class<?> getNmsClass(String name) {
        if (getVersion() >= 16) return null;
        return getClass("net.minecraft.server." + getServerVersion() + "." + name);
    }

    public static Class<?> getNmsOrOld(String newName, String oldName) {
        if (getVersion() >= 16) return getClass("net.minecraft." + newName);
        return getNmsClass(oldName);
    }

    public static Class<?> getObcClass(String name) {
        return getClass("org.bukkit.craftbukkit." + getServerVersion() + "." + name);
    }

    public static String getServerVersion() {
        String version = "null";
        try {
            version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        } catch (Exception e) {
            try {
                version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[1];
            } catch (Exception ignored) {
            }
        }
        return version;
    }

    public static int getProtocolVersion(String version) {
        return switch (version) {
            case "1.13", "1.13.0" -> 393;
            case "1.13.1" -> 401;
            case "1.13.2" -> 404;
            case "1.14", "1.14.0" -> 477;
            case "1.14.1" -> 480;
            case "1.14.2" -> 485;
            case "1.14.3" -> 490;
            case "1.14.4" -> 498;
            case "1.15", "1.15.0" -> 573;
            case "1.15.1" -> 575;
            case "1.15.2" -> 578;
            case "1.16", "1.16.0" -> 735;
            case "1.16.1" -> 736;
            case "1.16.2" -> 751;
            case "1.16.3" -> 753;
            case "1.16.4", "1.16.5" -> 754;
            case "1.17" -> 755;
            case "1.17.1" -> 756;
            default -> -1;
        };
    }

    public static int getVersion() {
        return switch (getServerVersion()) {
            case "v1_8_R1" -> 1;
            case "v1_8_R2" -> 2;
            case "v1_8_R3" -> 3;
            case "v1_9_R1" -> 4;
            case "v1_9_R2" -> 5;
            case "v1_10_R1" -> 6;
            case "v1_11_R1" -> 7;
            case "v1_12_R1" -> 8;
            case "v1_13_R1" -> 9;
            case "v1_13_R2" -> 10;
            case "v1_14_R1" -> 11;
            case "v1_15_R1" -> 12;
            case "v1_16_R1" -> 13;
            case "v1_16_R2" -> 14;
            case "v1_16_R3" -> 15;
            case "v1_17_R1" -> 16;
            case "v1_18_R1" -> 17;
            case "v1_18_R2" -> 18;
            case "v1_19_R1" -> 19;
            default -> -1;
        };
    }

    public static int getLargeVersion() {
        return switch (getServerVersion()) {
            case "v1_8_R1", "v1_8_R2", "v1_8_R3" -> 1;
            case "v1_9_R1", "v1_9_R2" -> 2;
            case "v1_10_R1" -> 3;
            case "v1_11_R1" -> 4;
            case "v1_12_R1" -> 5;
            case "v1_13_R1", "v1_13_R2" -> 6;
            case "v1_14_R1" -> 7;
            case "v1_15_R1" -> 8;
            case "v1_16_R1", "v1_16_R2", "v1_16_R3" -> 9;
            case "v1_17_R1" -> 10;
            case "v1_18_R1", "v1_18_R2" -> 11;
            default -> -1;
        };
    }

    public static double getCoreVersion() {
        return switch (getLargeVersion()) {
            case 1 -> 1.08;
            case 2 -> 1.09;
            case 3 -> 1.10;
            case 4 -> 1.11;
            case 5 -> 1.12;
            case 6 -> 1.13;
            case 7 -> 1.14;
            case 8 -> 1.15;
            case 9 -> 1.16;
            case 10 -> 1.17;
            case 11 -> 1.18;
            default -> -1;
        };
    }

    public static int getEasyVersion() {
        return switch (getServerVersion()) {
            case "v1_8_R1" -> 10801;
            case "v1_8_R2" -> 10802;
            case "v1_8_R3" -> 10803;
            case "v1_9_R1" -> 10901;
            case "v1_9_R2" -> 10902;
            case "v1_10_R1" -> 11001;
            case "v1_11_R1" -> 11101;
            case "v1_12_R1" -> 11201;
            case "v1_13_R1" -> 11301;
            case "v1_13_R2" -> 11302;
            case "v1_14_R1" -> 11401;
            case "v1_15_R1" -> 11501;
            case "v1_16_R1" -> 11601;
            case "v1_16_R2" -> 11602;
            case "v1_16_R3" -> 11603;
            case "v1_17_R1" -> 11701;
            case "v1_18_R1" -> 11801;
            default -> -1;
        };
    }

    private Ref() {
    }

}
