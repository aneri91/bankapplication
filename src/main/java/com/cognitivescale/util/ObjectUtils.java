package com.cognitivescale.util;

import java.util.Collection;
import java.util.Map;

/**
 * The Class ObjectUtils.
 */
public class ObjectUtils {

	/**
	 * This method returns true if the collection is null or is empty.
	 *
	 * @param collection the collection
	 * @return true | false
	 */
	public static boolean isEmpty(Collection<?> collection) {
		if (collection == null || collection.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * This method returns true of the map is null or is empty.
	 *
	 * @param map the map
	 * @return true | false
	 */
	public static boolean isEmpty(Map<?, ?> map) {
		if (map == null || map.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * This method returns true if the objet is null.
	 *
	 * @param object the object
	 * @return true | false
	 */
	public static boolean isEmpty(Object object) {
		if (object == null) {
			return true;
		}
		return false;
	}

	/**
	 * This method returns true if the input array is null or its length is zero.
	 *
	 * @param array the array
	 * @return true | false
	 */
	public static boolean isEmpty(Object[] array) {
		if (array == null || array.length == 0) {
			return true;
		}
		return false;
	}

	/**
	 * This method returns true if the input string is null or its length is zero.
	 *
	 * @param string the string
	 * @return true | false
	 */
	public static boolean isEmpty(String string) {
		if (string == null || string.trim().length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * This method returns true if the collection is null or is empty.
	 *
	 * @param collection the collection
	 * @return true | false
	 */
	public static boolean isNotEmpty(Collection<?> collection) {
		if (collection != null && !collection.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * This method returns true of the map is not null or is not empty.
	 *
	 * @param map the map
	 * @return true | false
	 */
	public static boolean isNotEmpty(Map<?, ?> map) {
		if (map != null && !map.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * This method returns true if the object is not null.
	 *
	 * @param object the object
	 * @return true | false
	 */
	public static boolean isNotEmpty(Object object) {
		if (object != null) {
			return true;
		}
		return false;
	}

	/**
	 * This method returns true if the input array is not null or its length is
	 * greater than zero.
	 *
	 * @param array the array
	 * @return true | false
	 */
	public static boolean isNotEmpty(Object[] array) {
		if (array != null && array.length > 0) {
			return true;
		}
		return false;
	}

	/**
	 * This method returns true if the input string is not null or its length is
	 * greater than zero.
	 *
	 * @param string the string
	 * @return true | false
	 */
	public static boolean isNotEmpty(String string) {
		if (string != null && string.trim().length() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * Instantiates a new object utils.
	 */
	private ObjectUtils() {
		throw new IllegalAccessError("Utility class");
	}
}
