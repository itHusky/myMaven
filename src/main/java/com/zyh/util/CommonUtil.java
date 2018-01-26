package com.zyh.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.ReflectionUtils;

public class CommonUtil {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 将字符串数组转换成整型数组（用法：前台提交要删除的ID集合，需要将这些ID转换成整型数组）
	 *
	 * @param strArray
	 *            字符串数组
	 * @return 整型数组
	 */
	public static Integer[] toIntArray(String[] strArray) {
		if (strArray == null) {
			throw new NullPointerException("数组strArray为空！");
		}
		int length = strArray.length;
		Integer[] intArray = new Integer[length];
		for (int i = 0; i < length; i++) {
			intArray[i] = Integer.valueOf(strArray[i]);
		}
		return intArray;
	}

	/**
	 * 将字符串数组转换成长整型数组
	 *
	 * @param strArray
	 *            字符串数组
	 * @return 长整型数组
	 */
	public static Long[] toLongArray(String[] strArray) {
		if (strArray == null) {
			throw new NullPointerException("数组strArray为空！");
		}
		int length = strArray.length;
		Long[] longArray = new Long[length];
		for (int i = 0; i < length; i++) {
			longArray[i] = Long.valueOf(strArray[i]);
		}
		return longArray;
	}

	/**
	 * 过滤掉包含敏感字符的SQL字段值，防止SQL注入攻击
	 *
	 * @param str
	 *            SQL字段值
	 * @return 已过滤敏感字符的SQL字段值
	 */
	public static String TransactSQLInjection(String str) {
		if (null == str || "".equals(str.trim())) {
			return str;
		}
		return str.replaceAll(".*([';]+|(--)+).*", "");
	}

	/**
	 * 将SQL ResultSet对象转换成List列表
	 *
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public static List<Object> resutlSetToList(ResultSet rs) throws SQLException {
		if (rs == null) {
			return new ArrayList<Object>();
		}
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		List<Object> list = new ArrayList<Object>();
		while (rs.next()) {
			Map<String, Object> row = new HashMap<String, Object>();
			for (int i = 1; i <= count; i++) {
				String colName = rsmd.getColumnName(i);
				Object colValue = rs.getObject(colName);
				row.put(colName, colValue);
			}
			list.add(row);
		}
		return list;
	}

	/**
	 * 判断一个对象是否为空（对象本身不为空，如刚初始化的对象）
	 *
	 * @param object
	 * @return
	 */
	public static boolean isNullObject(Object object) {
		if (object == null) {
			return true;
		}
		boolean isNull = true;

		Class<?> classType = object.getClass();
		Field[] fields = classType.getDeclaredFields();
		int length = fields.length;
		for (int i = 0; i < length; i++) {
			Field field = fields[i];

			// 获取字段修饰符
			int mod = field.getModifiers();
			if (Modifier.isFinal(mod) || Modifier.isStatic(mod)) {
				continue; // 跳过修饰符为static、final的字段
			}

			// 获取字段名
			String fieldName = field.getName();

			// 排除特殊字段，如序列化ID
			if ("serialVersionUID".equals(fieldName)) {
				continue;
			}
			// 将属性名首写字母改为大写字母
			String firstLetter = fieldName.substring(0, 1).toUpperCase();

			// 获得和属性对应的getXXX()方法名称
			String getterName = "get" + firstLetter + fieldName.substring(1);

			// 获得和属性对应的getXXX()方法
			Method getterMethod;

			// 调用原对象的getXXX()方法
			Object value = null;

			try {
				getterMethod = classType.getMethod(getterName, new Class[] {});

				// 调用原对象的getXXX()方法获取属性值
				value = getterMethod.invoke(object, new Object[] {});

				String typeName = field.getType().getName();
				if ("int".equals(typeName)) {
					if (Integer.parseInt(String.valueOf(value)) != 0) {
						isNull = false;
					}
				} else if ("long".equals(typeName)) {
					if (Long.parseLong(String.valueOf(value)) != 0) {
						isNull = false;
					}
				} else if ("float".equals(typeName)) {
					if (Float.parseFloat(String.valueOf(value)) != 0) {
						isNull = false;
					}
				} else if ("double".equals(typeName)) {
					if (Double.parseDouble(String.valueOf(value)) != 0) {
						isNull = false;
					}
				} else {
					if (null != value) {
						isNull = false;
					}
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return isNull;
	}

	/**
	 * <pre>
	 * 将一个对象序列化，并返回由属性名和属性值组合成的Map
	 *
	 * 【问题】	为什么不用Apache Common BeanUtils.describe(object)代替?
	 * 【回答】	因为BeanUtils.describe()会递归拆解所有成员对象，而我们只需要拆解到第一级成员对象即可。
	 * </pre>
	 *
	 * @param object
	 * @return
	 */
	public static Map<String, Object> describe(Object object) {
		if (object == null) {
			return null;
		}
		Map<String, Object> objectMap = new HashMap<String, Object>();
		Class<?> classType = object.getClass();
		Field[] fields = classType.getDeclaredFields();
		int length = fields.length;
		for (int i = 0; i < length; i++) {
			Field field = fields[i];

			// 跳过修饰符为static、final的字段
			int mod = field.getModifiers();
			if (Modifier.isFinal(mod) || Modifier.isStatic(mod)) {
				continue;
			}

			field.setAccessible(true);// 允许访问私有属性
			String fieldName = field.getName();
			Object fieldValue = ReflectionUtils.getField(field, object);
			objectMap.put(fieldName, fieldValue);
		}
		return objectMap;
	}

	/*
	 * 将一个对象序列化，并返回由属性名和属性值组合成的字符串， 形如：field1=v1&field2=v2
	 */
	public static String objectSerialize(Object object) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		if (object == null) {
			return "";
		}

		StringBuffer sb = new StringBuffer();
		Class<?> classType = object.getClass();
		Field[] fields = classType.getDeclaredFields();
		int length = fields.length;
		for (int i = 0; i < length; i++) {
			Field field = fields[i];
			// 获取字段名
			String fieldName = field.getName();
			if ("serialVersionUID".equals(fieldName)) {
				continue;
			}
			// 将属性名首写字母改为大写字母
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			// 获得和属性对应的getXXX()方法名称
			String getterName = "get" + firstLetter + fieldName.substring(1);
			// 获得和属性对应的getXXX()方法
			Method getterMethod = classType.getMethod(getterName, new Class[] {});
			// 调用原对象的getXXX()方法
			Object value = getterMethod.invoke(object, new Object[] {});
			if (value != null) {
				sb.append("&" + fieldName + "=" + value);
			}
		}
		return sb.toString().substring(1);
	}

	// 获取下个月最后一天的日期
	public static String getLastDateOfNextMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		month++;// 下个月（从0开始算起）
		int day = getLastDayOfMonth(year, month + 1);// 获取下个月（从1开始算起）最后一天
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		return dateFormat.format(calendar.getTime());
	}

	// 计算指定月份的最后一天
	public static int getLastDayOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		return calendar.getActualMaximum(Calendar.DATE);
	}

	/**
	 * 获取指定日期经过n个月后的日期
	 *
	 * @param myDate
	 *            指定日期
	 * @param n
	 *            n个月
	 * @return
	 */
	public static String getAfterDate(Date myDate, int n) {
		Calendar c = Calendar.getInstance();
		c.clear();
		c.setTime(myDate);
		c.add(Calendar.MONTH, n);
		return dateFormat.format(c.getTime());
	}

	/**
	 * 获取指定日期n天之后（之前）的日期
	 *
	 * @param d
	 *            指定日期
	 * @param n
	 *            n个天（若n>0，则表示获取n天之后的日期，若n<0，则表示获取n天之前的日期）
	 * @return
	 */
	public static String getDateAfterDays(Date d, int n) {
		Calendar c = Calendar.getInstance();
		c.clear();
		c.setTime(d);
		c.add(Calendar.DAY_OF_MONTH, n);
		return dateFormat.format(c.getTime());
	}

	/**
	 * 取得系统当前时间后n个月的相对应的一天
	 *
	 * @param n
	 *            n个月
	 * @return String yyyy-mm-dd
	 */
	public static String getDateAfterNow(int n) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, n);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(c.getTime());
	}

	/**
	 * 将数值转换成指定长度的字符串（长度不足时，前面补零）
	 *
	 * @param num
	 *            数值
	 * @param length
	 *            转换后的字符串长度
	 * @return 转换后的字符串
	 */
	public static String formatNum(long num, int length) {
		return String.format("%0" + length + "d", num);
	}

	public static float toFloat(Object object) {
		if (object == null || "".equals(object)) {
			return 0;
			// throw new RuntimeException("对象转换成Float时不能为空");
		}
		return Float.parseFloat(String.valueOf(object));
	}

	public static long toLong(Object object) {
		if (object == null || "".equals(object)) {
			return 0;
			// throw new RuntimeException("对象转换成Long时不能为空");
		}
		return Long.valueOf(String.valueOf(object));
	}

	public static int toInt(Object object) {
		if (object == null || "".equals(object)) {
			return 0;
			// throw new RuntimeException("Object转换成Int时不能为空");
		}
		return Integer.valueOf(String.valueOf(object));
	}

	/**
	 * 处理HttpServletRequest对象getParameterMap()方法返回的Map对象<br>
	 * 1、使每个参数对应的值唯一（默认参数值是数组形式的，修改后只取第一个参数值）；<br>
	 * 2、使返回的Map对象可以修改（Servlet默认不允许修改），比如添加offset、pageSize等<br>
	 *
	 * @param paramMap
	 *            getParameterMap()方法返回的Map对象
	 * @return
	 */

	public static Map<String, Object> doRequestParameterMap(
			@SuppressWarnings("rawtypes") Map paramMap) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (Object key : paramMap.keySet()) {
			Object[] value = (Object[]) paramMap.get(key);

			if (value != null) {
				int len = value.length;
				if (len > 1) {
					String strValue = "";
					for (int i = 0; i < len; i++) {
						strValue += "," + value[i];
					}
					strValue = strValue.substring(1);
					map.put(String.valueOf(key), strValue);
				} else {
					map.put(String.valueOf(key), value[0]);// value[0]默认取请求参数值（数组）的第一个元素
				}
			}
		}
		return map;
	}

}
