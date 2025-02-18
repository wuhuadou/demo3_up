package com.app.shop.mylibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;


import com.app.shop.mylibrary.MyApplication;
import com.app.shop.mylibrary.beans.MainConfig;
import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Created by sj on 2015/11/15.
 */
public class SharedPreferencesUtil {

    public static final String FILE_NAME = "share_data";//保存在手机里面的文件名

    public static void removeData(Context context, String fileName, String... keys) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (String key : keys) {
            editor.remove(key);
        }
        editor.commit();
    }

    public static void removeAll(Context context, String fileName) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    public static void saveData(Context context, String fileName, String key, Object data) {
        if (null != data && null != data.getClass()) {
            String type = data.getClass().getSimpleName();
            SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if ("Integer".equals(type)) {
                editor.putInt(key, (Integer) data);
            } else if ("Boolean".equals(type)) {
                editor.putBoolean(key, (Boolean) data);
            } else if ("String".equals(type)) {
                if (null == data) {
                    editor.putString(key, "");
                } else {
                    editor.putString(key, (String) data);
                }
            } else if ("Float".equals(type)) {
                editor.putFloat(key, (Float) data);
            } else if ("Long".equals(type)) {
                editor.putLong(key, (Long) data);
            } else if ("List".equals(type)) {
                List list = (List) data;
                if (null == list || list.size() == 0) {
                    editor.putString(key, "");
                } else {
                    String value = new Gson().toJson(list);
                    editor.putString(key, value);
                }
            }
            editor.commit();
        }
    }

    /**
     * 从文件中读取数据
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static <T extends Object> T getData(Context context, String fileName, String key, Object defValue) {
        String type = defValue.getClass().getSimpleName();
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        //defValue为为默认值，如果当前获取不到数据就返回它
        if ("Integer".equals(type)) {
            return (T) (Integer) sharedPreferences.getInt(key, (Integer) defValue);
        } else if ("Boolean".equals(type)) {
            return (T) (Boolean) sharedPreferences.getBoolean(key, (Boolean) defValue);
        } else if ("String".equals(type)) {
            return (T) sharedPreferences.getString(key, (String) defValue);
        } else if ("Float".equals(type)) {
            return (T) (Float) sharedPreferences.getFloat(key, (Float) defValue);
        } else if ("Long".equals(type)) {
            return (T) (Long) sharedPreferences.getLong(key, (Long) defValue);
        }
        throw new RuntimeException("get SharedPrefrences error!!");
    }

    //从文件中读取数据json
    public static String getAll(Context context, String fileName) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        Map<String, ?> map = sharedPreferences.getAll();
        String json = new Gson().toJson(map);
        //处理json
        json = json.replace("\\", ""); //去掉'\'
        json = json.replace("\"[", "[");
        json = json.replace("]\"", "]");
        return json;
    }

    /*保存用户信息*/
    public static <T> void saveDataBean(Context context, T bean, String beanName) {
        if (null != bean) {
            SharedPreferences preferences = context.getSharedPreferences(beanName, 0);
            SharedPreferences.Editor editor = preferences.edit();
            Class c = bean.getClass();
            Field[] fields = c.getDeclaredFields();
            try {
                for (Field field : fields) {
                    field.setAccessible(true);
                    switch (field.getType().getSimpleName()) {
                        case "String":
                            if (null == field.get(bean)) {
                                editor.putString(field.getName(), "");
                            } else {
                                editor.putString(field.getName(), "" + field.get(bean));
                            }
                            break;
                        case "int":
                            editor.putInt(field.getName(), (int) field.get(bean));
                            break;
                        case "double":
                            editor.putString(field.getName(), "" + field.get(bean));
                            break;
                        case "long":
                            editor.putLong(field.getName(), (long) field.get(bean));
                            break;
                        case "float":
                            editor.putFloat(field.getName(), (float) field.get(bean));
                            break;
                        case "boolean":
                            editor.putBoolean(field.getName(), (boolean) field.get(bean));
                            break;
                        case "List":
                            List list = (List) field.get(bean);
                            if (null == list || list.size() == 0) {
                                editor.putString(field.getName(), "");
                            } else {
                                String value = new Gson().toJson(list);
                                editor.putString(field.getName(), value);
                            }
                            break;
                        default:
                            editor.putString(field.getName(), "" + field.get(bean));
                            break;
                    }
                    editor.commit();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static <T> void saveDataMainConfig(Context context, MainConfig bean, String beanName) {
        SharedPreferences preferences = context.getSharedPreferences(beanName, 0);
        SharedPreferences.Editor editor = preferences.edit();
        Class c = bean.getData().getClass();
        Field[] fields = c.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                switch (field.getType().getSimpleName()) {
                    case "String":
                        String currentUrl = field.get(bean.getData()) + "";
                        editor.putString(field.getName(), currentUrl);
                        break;
                    case "int":
                        editor.putInt(field.getName(), (int) field.get(bean.getData()));
                        break;
                    case "long":
                        editor.putLong(field.getName(), (long) field.get(bean.getData()));
                        break;
                    case "float":
                        editor.putFloat(field.getName(), (float) field.get(bean.getData()));
                        break;
                    case "boolean":
                        editor.putBoolean(field.getName(), (boolean) field.get(bean.getData()));
                        break;
                    case "List":
                        break;
                    default:
                        break;
                }
                editor.commit();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param context
     * @param key
     * @param object  类型:String,int,boolean,float,long
     */
    public static void put(Context context, String key, Object object) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }

        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param context
     * @param key
     * @param defaultObject 类型:String,int,boolean,float,long
     * @return
     */
    public static Object get(Context context, String key, Object defaultObject) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        if (defaultObject instanceof String) {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        }
        return null;
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param context
     * @param key
     */
    public static void remove(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean contains(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     *
     * @author zhy
     */
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         *
         * @return
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }

            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor
         */
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            editor.commit();
        }
    }
}
