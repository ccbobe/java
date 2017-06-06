package com.bobe.commons;

import org.apache.commons.beanutils.Converter;

public class MyConvert implements Converter {

	@Override
	public <T> T convert(Class<T> type, Object value) {
		if(value instanceof String){
			return (T) "hello";
		}
		return null;
	}//类型转换器---自定意


}
