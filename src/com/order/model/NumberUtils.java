package com.order.model;

import java.math.BigDecimal;
import java.util.Random;

/**
 * 项目名称：RenRenERP <br />
 * 类名称：NumberUtils <br />
 * 创建人：Administrator <br />
 * 备注： <br />
 * 
 * @version <br />
 */
public class NumberUtils extends org.apache.commons.lang.math.NumberUtils
{
	/**
	 * 设置默认的Int值
	 */
	public static int defaultInt(String value, int defaultValue)
	{
		if (StringUtils.isEmpty(value) || !isNumber(value))
			return defaultValue;

		return Double.valueOf(value).intValue();
	}

	/**
	 * 设置默认的Int值
	 */
	public static int defaultInt(Integer value, int defaultValue)
	{
		return (value == null) ? defaultValue : value.intValue();
	}

	/**
	 * 设置默认的Long值
	 */
	public static long defaultLong(String value, int defaultValue)
	{
		if (StringUtils.isEmpty(value) || !isNumber(value))
			return defaultValue;

		return Double.valueOf(value).longValue();
	}

	/**
	 * 设置默认的Double值
	 */
	public static double defaultDouble(String value, double defaultValue)
	{
		if (StringUtils.isEmpty(value) || !isNumber(value))
			return defaultValue;

		return Double.valueOf(value);
	}

	/**
	 * 设置默认的Double值
	 */
	public static double defaultDouble(Double value, double defaultValue)
	{
		return (value == null) ? defaultValue : value.doubleValue();
	}

	/**
	 * 获取不为null的数据个数
	 */
	public static int notNullCount(Number... number)
	{
		int count = 0;
		for (Number n : number)
		{
			if (n != null)
				count++;
		}

		return count;
	}

	/**
	 * 获取是否有为null的数字
	 */
	public static boolean hasNull(Number... number)
	{
		for (Number n : number)
		{
			if (n == null)
				return true;
		}

		return false;
	}

	/**
	 * 获取是否有不为null的数字
	 */
	public static boolean hasNotNull(Number... number)
	{
		for (Number n : number)
		{
			if (n == null)
				return false;
		}

		return true;
	}

	/**
	 * 保留几位小数
	 */
	public static double toFixed(double value, int length)
	{
		BigDecimal bigDecimal = new BigDecimal(value);
		return bigDecimal.setScale(length, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 生成一个指定长度大小的随机数字字符串
	 */
	public static String fixedLengthString(int length)
	{
		if (length <= 0)
			length = 1;

		int maxLength = String.format("%d", Long.MAX_VALUE).length();
		if (length < maxLength)
		{
			double value = new Random().nextDouble();
			String string = String.format("%f", (1 + value) * Math.pow(10, length));
			return string.substring(1, length + 1);
		}

		String prefix = fixedLengthString(maxLength - 1);
		String suffix = fixedLengthString(length - maxLength + 1);
		return prefix + suffix;
	}
}
