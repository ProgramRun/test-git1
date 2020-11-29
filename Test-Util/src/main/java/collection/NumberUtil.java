package collection;

import com.google.common.base.Function;
import org.apache.commons.lang3.ArrayUtils;

/**
 * @author zad
 * @version 1.0
 * @descript
 * @date 2019/8/13 8:58
 */
public final class NumberUtil {

	private NumberUtil() {
		throw new UnsupportedOperationException("Util禁止反射实例化");
	}

	public static <T> int compareTo(Comparable<T> c, T t) {
		return c == null || t == null ? 0 : c.compareTo(t);
	}

	public static boolean isNull(Number num) {
		return num == null;
	}


	public static boolean isAllNull(Number... nums) {
		if (ArrayUtils.isEmpty(nums)) {
			return true;
		}

		for (Number nu : nums) {
			if (nu != null) {
				return false;
			}
		}
		return true;
	}


	public static <T> boolean isAllNull(Function<T, Number> function, Iterable<T> it) {
		assertNotNull(function);
		assertNotNull(it);

		for (T param : it) {
			if (function.apply(param) != null) {
				return false;
			}
		}
		return true;
	}

	public static boolean isAnyNull(Number... nums) {
		if (ArrayUtils.isEmpty(nums)) {
			return true;
		}

		for (Number nu : nums) {
			if (nu == null) {
				return true;
			}
		}
		return false;
	}

	public static <T> boolean isAnyNull(Function<T, Number> function, Iterable<T> it) {
		assertNotNull(function);
		assertNotNull(it);

		for (T param : it) {
			if (function.apply(param) == null) {
				return true;
			}
		}
		return false;
	}

	private static void assertNotNull(Object o) {
		if (o == null) {
			throw new IllegalArgumentException();
		}
	}
}
