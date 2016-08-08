package com.dmsd.itoo.base.dao.changedb;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericUtils {

	public static Class getGenericClass(Class clazz, int index) {

		Type genType = clazz.getGenericSuperclass();
		if ((genType instanceof ParameterizedType)) {
			Type[] params = ((ParameterizedType) genType)
					.getActualTypeArguments();

			if ((params != null) && (params.length > index)) {
				return (Class) params[index];
			}
		}
		return null;
	}

	public static Class getGenericClass(Class clazz) {
		return getGenericClass(clazz, 0);
	}
}
