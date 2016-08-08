package com.dmsd.itoo.tool.objectToMap;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ObjectToMap
 * @Description: 用来完成ObjectToMap的转换
 * @author 周超强
 * @date 2015年8月12日 上午8:36:42
 * */
public class ObjectToMap {

	/**
	 * 将查询结果转成Map
	 * @param fields:根据这些字段进行map转换
	 * @param result：要转换的对象
	 * @return
	 */
	public List<Map<Serializable, Serializable>> convertToMap(List<Serializable> fields, List result)
	{
		long beginTime=System.currentTimeMillis();
		
		try{
			if(result != null && result.size()>0){
				//空集合，盛放转换后的数据
				List<Map<Serializable, Serializable>> list = new ArrayList<Map<Serializable, Serializable>>();
				// 遍历要转换的对象
				for(int i=0;i<result.size();i++){
					//空集合，盛放转换后的MAP数据
					Map<Serializable, Serializable> map = new HashMap<Serializable, Serializable>();
					// 强转成 对象数组
					Object obj = result.get(i);
					if(obj instanceof Object[]){
						Object[] objs = (Object[])result.get(i);
						// 遍历 给的 字段值
						for(int j=0;j<fields.size();j++){
							if (objs[j]!=null) {
								map.put((String)fields.get(j), (Serializable) objs[j]);
							}else {
								map.put((String)fields.get(j), (Serializable) "");
							}
						}
					}
					else{
						map.put((String)fields.get(0), (Serializable) obj);
					}
					// 返回list
					list.add(map);
				}
				long endTime=System.currentTimeMillis();
				long timeSpan=endTime-beginTime;

				System.out.println("List转成List<map>的时间差："+timeSpan);
				return list;
			}
			else{
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	
	 /**
     * @Title: convertToMap
     * @Description: 用来把一个实体的全部属性全部转换成为map中的值
     * @param @param result
     * @param @return
     * @param @throws IntrospectionException
     * @param @throws IllegalAccessException
     * @param @throws RuntimeException
     * @param @throws Exception
     * @return Map<Serializable,Serializable>
     * @author 周超强
     * @throws
     */
    public static Map<Serializable, Serializable> convertToMap(Object result) throws IntrospectionException,
            IllegalAccessException, RuntimeException, Exception {

        // 通过java内省机制获得javaBean
        BeanInfo beanInfo = Introspector.getBeanInfo(result.getClass());
        // 空集合，盛放转换后的MAP数据
        Map<Serializable, Serializable> map = new HashMap<Serializable, Serializable>();
        // 获得相应的javaBean的实体描述
        PropertyDescriptor[] proDescrtptors = beanInfo.getPropertyDescriptors();
        if (proDescrtptors != null && proDescrtptors.length > 0) {
            for (PropertyDescriptor propDesc : proDescrtptors) {

                // 通过反射获取方法
                Method methodGetUserName = propDesc.getReadMethod();
                // 获得属性值
                Object objUserName = methodGetUserName.invoke(result);
                // 放入到map集合中
                map.put(propDesc.getName(), (Serializable) objUserName);
            }
            return map;
        } else {
            return null;
        }
    }

    /**
     * @Title: convertToMap
     * @Description: 用来将一个实体中的部分属性全部转换为map
     * @param @param fields：要转换的字段
     * @param @param result：待转换的实体
     * @param @return
     * @param @throws IntrospectionException
     * @param @throws IllegalAccessException
     * @param @throws Exception
     * @param @throws Throwable
     * @return Map<Serializable,Serializable>
     * @author 周超强
     * @throws
     */
    public static Map<Serializable, Serializable> convertToMap(String[] fields, Object result)
            throws IntrospectionException, IllegalAccessException, Exception, Throwable {

        // 通过java内省机制获得javaBean
        BeanInfo beanInfo = Introspector.getBeanInfo(result.getClass());
        // 空集合，盛放转换后的MAP数据
        Map<Serializable, Serializable> map = new HashMap<Serializable, Serializable>();
        // 获得相应的javaBean的实体描述
        PropertyDescriptor[] proDescrtptors = beanInfo.getPropertyDescriptors();
        if (proDescrtptors != null && proDescrtptors.length > 0) {

            for (PropertyDescriptor propDesc : proDescrtptors) {
                for (String field : fields) {
                    // 需要转换成为小写，防止大小写引起混乱
                    if (field.toLowerCase().equals(propDesc.getName().toLowerCase())) {
                        // 通过反射获取方法
                        Method methodGetUserName = propDesc.getReadMethod();
                        // 获得属性值
                        Object objUserName = methodGetUserName.invoke(result);
                        // 放入到map集合中
                        map.put(propDesc.getName(), (Serializable) objUserName);
                    }
                }

            }
            return map;
        } else {
            return null;
        }

    }
}
