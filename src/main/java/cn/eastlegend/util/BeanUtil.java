package cn.eastlegend.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.fasterxml.jackson.databind.util.ISO8601Utils.format;

/**
 * @author java_shj
 * @desc bean转换相关工具类
 * @createTime 2020/2/7 14:55
 **/
public class BeanUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(BeanUtil.class);

    /**
     * 把java转换成Map
     * @param object
     * @return
     */
    public static Map<String, Object> convertBean2Map(Object object) {
        if(object == null) {
            return null;
        } else {
            HashMap beanMap = new HashMap();
            Field[] fields = object.getClass().getDeclaredFields();
            fields = getSuperFields(object.getClass(), Arrays.asList(fields));
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                String key = field.getName();
                if(!"serialVersionUID".equals(key) && !Modifier.isStatic(field.getModifiers())) {
                    boolean accessible = field.isAccessible();
                    field.setAccessible(true);
                    String value = getValueFormField(object, field) + "";
                    field.setAccessible(accessible);
                    beanMap.put(key, value);
                }
            }
            return beanMap;
        }
    }

    /**
     * 把Map列表转换成Java对象组成的列表
     * @param mapList
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> List<T> convertList2Beans(List<Map<String, Object>> mapList, Class<T> clz) {
        ArrayList<T> list = new ArrayList<>();
        for (Map<String, Object> map : mapList) {
            T t = convertMap2Bean(map, clz);
            if(t != null) {
                list.add(t);
            }
        }
        return list;
    }

    /**
     * 把Map转换成Java对象
     * @param map
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> T convertMap2Bean(Map<String, Object> map, Class<T> clz) {
        if(map == null) {
            return null;
        }else {
            return JacksonUtil.map2pojo(map, clz);
        }
    }

    /**
     * 递归获取父类的字段,直到父类为Object时才返回
     * @param subClz 子类的class对象
     * @param subFields 子类包含的字段
     * @return
     */
    public static Field[] getSuperFields(Class<?> subClz, List<Field> subFields) {
        Class<?> superclass = subClz.getSuperclass();
        ArrayList arrayList = new ArrayList(subFields);
        if(superclass != null && superclass != Object.class) {
            Field[] declaredFields = superclass.getDeclaredFields();
            arrayList.addAll(Arrays.asList(declaredFields));
            if(superclass.getSuperclass() != Object.class) {
                getSuperFields(superclass, arrayList);
            }
        }
        return (Field[]) arrayList.toArray(new Field[arrayList.size()]);
    }

    /**
     * 从当前对象获取对应字段的值
     * @param object
     * @param field
     * @return
     */
    public static Object getValueFormField(Object object, Field field) {
        try {
            Object value = field.get(object);
            String type = field.getType().getSimpleName().toLowerCase();
            switch (type) {
                case "short":
                    value = field.getShort(object);
                    break;
                case "char":
                    value = field.getChar(object);
                    break;
                case "int":
                    value = field.getInt(object);
                    break;
                case "long":
                    value = field.getLong(object);
                    break;
                case "byte":
                    value = field.getByte(object);
                    break;
                case "float":
                    value = field.getFloat(object);
                    break;
                case "double":
                    value = field.getDouble(object);
                    break;
                case "boolean":
                    value = field.getBoolean(object);
                    break;
                case "date":
                    value = format((Date) value);
                    break;
                case "byte[]":
                    if(value != null) {
                        ConvertUtil.bytes2String(((byte[]) value));
                    }
                    break;
                case "string":
                    if(value == null) {
                        value = "";
                    }
                    break;
                    default:
                        value = field.get(object);
            }
            return value;
        } catch (IllegalAccessException e) {
            LOGGER.error("getValueFormField抛出异常", e);
        }
        return null;
    }

    public static String format(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return date == null ? "" : sdf.format(date);
    }
}
