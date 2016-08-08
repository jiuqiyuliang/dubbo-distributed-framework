package com.dmsd.itoo.tool.mapToObject;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 将一个 Map 对象转化为一个 JavaBean
 * @author xj
 * 2015年7月12日
 *
 */
public class MapToObject {

	/** 
     * 将一个 Map 对象转化为一个 JavaBean 
     * @param type 要转化的类型 
     * @param map 包含属性值的 map 
     * @return 转化出来的 JavaBean 对象 
     * @throws IntrospectionException 如果分析类属性失败 
     * @throws IllegalAccessException 如果实例化 JavaBean 失败 
     * @throws InstantiationException 如果实例化 JavaBean 失败 
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败 
     */  
	public static Object convertMap(Class type, Map<Serializable, Serializable> map)  
            throws IntrospectionException, IllegalAccessException,  
            InstantiationException, InvocationTargetException {  
		
			if(map != null && map.size()>0){
				//通过类 Introspector 来获取某个对象的 BeanInfo 信息
		        BeanInfo beanInfo = Introspector.getBeanInfo(type);
		        //创建 JavaBean 对象  
		        Object obj = type.newInstance(); 
		  
		        //通过 BeanInfo来获取属性的描述器PropertyDescriptor
		        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
		        
		        //利用属性描述器获取某个属性对应的 getter/setter方法
		        for (int i = 0; i< propertyDescriptors.length; i++) {  
		            PropertyDescriptor descriptor = propertyDescriptors[i];  
		            String propertyName = descriptor.getName();  
		  
		            if (map.containsKey(propertyName)) {  
		               
		                Object value = map.get(propertyName);  
		                Object[] args = new Object[1];  
		                args[0] = value;  
		  
		                //通过反射机制来getter和setter
		                descriptor.getWriteMethod().invoke(obj, args);  
		            }  
		        }  
		        return obj;  
	    } else{
			return null;	
		}
	}
}
