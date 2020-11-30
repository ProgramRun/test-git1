package reflection;


import jdbc.User;
import org.apache.commons.lang3.StringUtils;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * @Author waiter
 * @Date 2020/11/29 0029 19:22
 * @Version 1.0
 * @Description https://github.com/ronmamo/reflections
 */
public class ReflectionUtil {

	public static <T> Set<Field> getFields(Class<T> clazz) {
		return ReflectionUtils.getAllFields(clazz);
	}


	public static <T> Set<Field> getField(Class<T> clazz, String fieldName) {
		return ReflectionUtils.getAllFields(clazz, field -> StringUtils.equals(fieldName, field.getName()));
	}

	public static void main(String[] args) {
		getField(User.class,"id").forEach(System.out::print);
	}

}
