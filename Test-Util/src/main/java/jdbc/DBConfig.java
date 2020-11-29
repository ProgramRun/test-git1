package jdbc;

import lombok.Getter;

/**
 * @Author waiter
 * @Date 2020/11/29 0029 8:17
 * @Version 1.0
 * @Description
 */
@Getter
public class DBConfig {
    static String driverName = "com.mysql.cj.jdbc.Driver";
    static String url = "jdbc:mysql://192.168.92.128/soul";
    static String username = "oie";
    static String password = "12345678";

}
