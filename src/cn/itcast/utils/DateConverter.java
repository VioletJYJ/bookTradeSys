package cn.itcast.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;

public class DateConverter implements Converter {
	public Object convert(Class type, Object value) {
		if(type != java.util.Date.class) {
			return null;
		}
		if(value == null) {
			return null;
		}
		if(!(value instanceof String)) {
			return null;
		}
		String s = (String)value;
		if(s.length() == 0) {
			return null;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(s);
			return new java.sql.Date(date.getTime()); 
		} catch(Exception e) {
			throw new RuntimeException(e);
		}	
	}
}
