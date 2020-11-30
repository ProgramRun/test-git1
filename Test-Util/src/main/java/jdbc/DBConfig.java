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
    static String url = "jdbc:mysql://localhost/uc_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    static String username = "root";
    static String password = "111111";

}
