package com.order.model;
import java.util.List;

public class StringUtils extends org.apache.commons.lang.StringUtils
{
	public static boolean isNullOrBlank(Object object)
	{
		if (object == null)
			return true;

		return trimToEmpty(String.valueOf(object)).length() == 0;
	}

	public static boolean isAnyBlank(String... string)
	{
		for (String s : string)
		{
			if (isBlank(s))
				return true;
		}

		return false;
	}

	public static boolean isAllBlank(String... string)
	{
		for (String s : string)
		{
			if (!isBlank(s))
				return false;
		}

		return true;
	}

	public static boolean equalsAny(String value, String... string)
	{
		for (String s : string)
		{
			if (equals(value, s))
				return true;
		}

		return false;
	}

	public static boolean equalsAny(String value, List<String> list)
	{
		for (String s : list)
		{
			if (equals(value, s))
				return true;
		}

		return false;
	}

	public static int notEmptyCount(String... string)
	{
		int count = 0;
		for (String s : string)
		{
			if (isNotEmpty(s))
				count++;
		}

		return count;
	}

	public static String substringLast(String string, int length)
	{
		if (isEmpty(string))
			return EMPTY;

		return substring(string, string.length() - length);
	}

	public static String upperCaseFirst(String string)
	{
		if (isEmpty(string))
			return EMPTY;

		char character = Character.toUpperCase(string.charAt(0));
		String substring = substringLast(string, string.length() - 1);
		return String.format("%s%s", character, substring);
	}

	public static String parse(Object object)
	{
		return parse(object, true);
	}

	public static String parse(Object object, boolean trim)
	{
		if (object == null)
			return EMPTY;

		return trim ? trimToEmpty(String.valueOf(object)) : String.valueOf(object);
	}
	
	public static boolean isLetterDigitOrChinese(String str) {
		  String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";
		  return str.matches(regex);
	}
	
	public static boolean is_number(String number) {
		if(number==null) return false;
	    return number.matches("[+-]?[1-9]+[0-9]*(\\.[0-9]+)?");    
	}
	
	public static boolean is_alpha(String alpha) {
		if(alpha==null) return false;
	    return alpha.matches("[a-zA-Z]+");    
	}
	
	public static boolean is_chinese(String chineseContent) {
		if(chineseContent==null) return false;
		return chineseContent.matches("[\u4e00-\u9fa5]");
	}
}
