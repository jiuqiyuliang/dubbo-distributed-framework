package com.dmsd.itoo.tool.listRemoveNull;

import java.util.Collection;
import java.util.List;

/**
 * 返回Null对象的工具类
 * @author xj
 *
 */
public class NullUtil {

	/**
	 * 定义一个空集合
	 */
	 public static final Collection NULL_COLLECTION = new NullCollection();  
     
	 	/**
	 	 * 泛型List 集合
	 	 * @return 将定义的空集合，强转为泛型List返回
	 	 */
	    public static final <T> Collection<T> nullCollection() {  
	        return (List<T>) NULL_COLLECTION;  
	    } 
}
