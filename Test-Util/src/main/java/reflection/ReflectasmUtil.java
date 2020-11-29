package reflection;

import com.esotericsoftware.reflectasm.MethodAccess;
import jdbc.User;

/**
 * @Author waiter
 * @Date 2020/11/29 0029 19:05
 * @Version 1.0
 * @Description https://github.com/EsotericSoftware/reflectasm
 */
public class ReflectasmUtil {

    public static <T> Object getValue(T entity, String methodName) {
        MethodAccess access = MethodAccess.get(entity.getClass());
        return access.invoke(entity, methodName);
    }

    public static <T> void setValue(T entity, String methodName, Object... params) {
        MethodAccess access = MethodAccess.get(entity.getClass());
        access.invoke(entity, methodName, params);
    }


    public static void main(String[] args) {
        User u = User.builder().build();
        setValue(u,"setId",1);
        setValue(u,"setName","oie");
        System.out.println(getValue(u,"getId"));
        System.out.println(getValue(u,"getName"));
    }
}
