package com.dmsd.itoo.tool.listRemoveNull;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.RandomAccess;


/**
 * 定义一个集合类
 * 继承于List
 * @author xj
 *
 */
public class NullCollection extends AbstractList<Object> implements RandomAccess, Serializable {

	private static final long serialVersionUID = 5206887786441397812L;  
	  
    @Override  
    public Object get(int index) {  
        return null;  
    }  
  
    @Override  
    public int size() {  
        return 1;  
    }  
      
    public boolean contains(Object obj) {  
        return null == obj;  
    }  
      
    private Object readResolve() {  
        return null;  
    } 
}
