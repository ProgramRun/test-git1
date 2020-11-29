package reflection;

import jdbc.User;
import net.vidageek.mirror.dsl.Mirror;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @Author waiter
 * @Date 2020/11/29 0029 18:34
 * @Version 1.0
 * @Description http://projetos.vidageek.net/mirror/mirror/
 */
public class MirrorUtil {

    public static <T> void setValue(T entity, String fieldName, Object value) {
        new Mirror().on(entity).set().field(fieldName).withValue(value);
    }

    public static <T> void setStatic(Class<T> clazz, String fieldName, Object value) {
        new Mirror().on(clazz).set().field(fieldName).withValue(value);
    }


    public static <T> Object getValue(T entity, String fieldName) {
        return new Mirror().on(entity).get().field(fieldName);
    }

    public static <T> Object getStatic(Class<T> clazz, String fieldName) {
        return new Mirror().on(clazz).get().field(fieldName);
    }


    public static <T> Field getField(Class<T> clazz, String fieldName) {
        return new Mirror().on(clazz).reflect().field(fieldName);
    }


    public static <T> List<Field> getFields(Class<T> clazz) {
        return new Mirror().on(clazz).reflectAll().fields();
    }


    public static <T> List<Annotation> getAnnotations(Class<T> clazz) {
        return new Mirror().on(clazz).reflectAll().annotations().atClass();
    }

    public static <T> List<Annotation> getMethodAnnotations(Class<T> clazz, String methodName) {
        return new Mirror().on(clazz).reflectAll().annotations().atMethod(methodName).withoutArgs();
    }


    public static void main(String[] args) {
        List<Annotation> annotations = getAnnotations(User.class);
        for (Annotation a : annotations) {
            System.out.println(a.toString());
        }
    }

}
