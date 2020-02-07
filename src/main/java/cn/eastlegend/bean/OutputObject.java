package cn.eastlegend.bean;

import cn.eastlegend.util.BeanUtil;
import cn.eastlegend.util.JacksonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author java_shj
 * @desc 标准输出结果对象，用于规范数据结果，以及从输出结果取值
 * @createTime 2020/2/7 14:30
 **/
public class OutputObject {

    /**
     * 返回值编码，默认为1正常，-9999异常
     */
    private String returnCode;

    /**
     * 返回结果描述
     */
    private String returnMsg;

    /**
     * 存放Map类型数据
     */
    private Map<String, Object> bean = new HashMap<>();

    /**
     * 返回一个list
     */
    private List<Map<String, Object>> beans = new ArrayList<>();

    /**
     * 无参构造器
     */
    public OutputObject(){

    }

    /**
     * 带有返回编码的构造器
     * @param returnCode
     */
    public OutputObject(String returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * 带有返回编码、返回信息的构造器
     * @param returnCode
     * @param returnMsg
     */
    public OutputObject(String returnCode, String returnMsg) {
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
    }

    /**
     * 把Java转换成Map，并赋值给bean属性
     * @param object
     * @return
     */
    public Map<String, Object> convertBean2Map(Object object) {
        this.bean = BeanUtil.convertBean2Map(object);
        return this.bean;
    }

    /**
     * 把Java列表转成整map列表
     * @param list
     * @param <T>
     * @return
     */
    public <T> List<Map<String, Object>> convertBeans2List(List<T> list) {
        for (T object : list) {
            Map<String, Object> beanMap = BeanUtil.convertBean2Map(object);
            if(beanMap != null && beanMap.size() !=0) {
                this.beans.add(beanMap);
            }
        }
        return this.beans;
    }

    /**
     * 把this.beans转换成Java对象组成的列表
     * @param clz
     * @param <T>
     * @return
     */
    public <T> List<T> convertList2JavaBeans(Class<T> clz) {
        return BeanUtil.convertList2Beans(this.beans, clz);
    }

    /**
     * 把Map转换成Java对象
     * @param clz
     * @param <T>
     * @return
     */
    public <T> T convertMap2Bean(Class<T> clz) {
        return BeanUtil.convertMap2Bean(this.bean, clz);
    }

    /**
     * 往bean中增加键值对
     * @param key
     * @param value
     */
    public void addBean(String key, String value) {
        this.bean.put(key, value);
    }

    /**
     * 往beans列表中增加键值对
     * @param key
     * @param value
     * @param index 列表中的索引
     */
    public void addBeans(String key, String value, int index) {
        if(this.beans.size() <= index){
            throw new RuntimeException("往beans中插入时发生索引越界异常");
        }
        this.beans.get(index).put(key, value);
    }

    /**
     * 把OutputObject对象转换为json字符串
     * @return
     * @throws JsonProcessingException
     */
    public String toJson() throws JsonProcessingException {
        return JacksonUtil.obj2json(this);
    }



    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public Map<String, Object> getBean() {
        return bean;
    }

    public void setBean(Map<String, Object> bean) {
        this.bean = bean;
    }

    public List<Map<String, Object>> getBeans() {
        return beans;
    }

    public void setBeans(List<Map<String, Object>> beans) {
        this.beans = beans;
    }
}
