package com.dmsd.itoo.tool.objectToMap;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dmsd.itoo.tool.listRemoveNull.NullUtil;


public class EntityToMap {

	/**
	 * 将List<T> 转成List<Map>
	 * 支持将实体类型的list转成map类型的list
	 * @param list
	 * @return
	 */
	public <T> List<Map<Serializable, Serializable>> entityToMap(List<T> list){
		List<Map<Serializable, Serializable>> listReturn = new ArrayList<Map<Serializable, Serializable>>();
		//去除list里的null
		list.removeAll(NullUtil.nullCollection());
		try{
			if(list != null && list.size()>0){
				Class clazz;
				for(int i=0;i<list.size();i++){
					//定义Map对象，盛放实体对象
					Map<Serializable, Serializable> map = new HashMap<Serializable, Serializable>();
					//通过类名，利用反射生成对象
					clazz = Class.forName((list.get(i)).getClass().getName());
					//获取对象对应类中的所有属性域
					Field[] fields = clazz.getFields();
					//获取对象对应类中的所有属性域
					Method[] methods = clazz.getMethods();
					//遍历所有get方法，获得值和对应的属性名，放到map中
					for(int j=0;j<methods.length;j++){
						//得到方法名
						String mName = methods[j].getName(); 
						//判断是否是get方法
						if(mName.startsWith("get")){
							//调用invoke方法，获得这个get方法对应属性的值
							Object value = methods[j].invoke(list.get(i));
							//得到属性名
							String key=mName.substring(3);  
				            key=key.substring(0,1).toLowerCase()+key.substring(1); 
				            //如果该属性有值，key为属性名，value为属性对应的序列化后的值
				            //如果该属性没有纸，key为属性名，value为空串""
				            if(value != null){
				            	  map.put(key, (Serializable) value); 
				            }
				            else{
				            	  map.put(key, ""); 
				            }
						}
					}
					listReturn.add(map);
				}
				return listReturn;
			}
			else{
				return null;
			}
		}catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
	}
}
