package com.airtel.merchant.scheduler.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class will provide utility methods to handle data security over inbuild
 * data structures.
 * 
 */
public final class CollectionUtil {

	/**
	 * Instantiates a new collection util.
	 */
	private CollectionUtil() {
	}

	/**
	 * This method will return an unmodifiable list of the instance passed to it..
	 *
	 * @param <T>
	 *            the generic type
	 * @param list
	 *            the list
	 * @return the list
	 */
	public static <T> List<T> unmodifiableList(final List<? extends T> list) {
		List<T> unmodifiableList = null;
		if (list != null) {
			unmodifiableList = Collections.unmodifiableList(list);
		}
		return unmodifiableList;
	}

	/**
	 * This method will copy the elements to an new list instance and returns it.
	 *
	 * @param <T>
	 *            the generic type
	 * @param list
	 *            the list
	 * @return the list
	 */
	public static <T> List<T> copy(List<T> list) {
		List<T> copy = null;
		if (list != null) {
			copy = new ArrayList<>(list);
		}
		return copy;
	}
}
