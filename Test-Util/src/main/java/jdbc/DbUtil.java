package jdbc;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Objects;

import static jdbc.DBConfig.*;

/**
 * @Author waiter
 * @Date 2020/11/28 0028 22:15
 * @Version 1.0
 * @Description
 */
@Slf4j
public class DbUtil {

    private static Connection connection;

    static {
        if (Objects.isNull(connection)) {
            connection = getConnection();
        }
    }

    @SneakyThrows
    private static Connection getConnection() {
        DbUtils.loadDriver(driverName);
        return DriverManager.getConnection(url, username, password);
    }

    public void close() {
        DbUtils.closeQuietly(connection);
    }


    @SneakyThrows
    public static void queryOne(String sql, Object... params) {
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<User> resultHandler = new BeanHandler<User>(User.class);
        User res = queryRunner.query(connection, sql, resultHandler, params);
    }


    @SneakyThrows
    public static void queryList(String sql, Object... params) {
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<User>> resultHandler = new BeanListHandler<User>(User.class);
        log.info(queryRunner.query(connection, sql, resultHandler, params).toString());
    }

    public static void main(String[] args) {
        queryList("select * from user", null);
    }

    @SneakyThrows
    public static void update(String sql, Object... params) {
        QueryRunner queryRunner = new QueryRunner();
        int res = queryRunner.update(connection, sql, params);
    }


    @SneakyThrows
    public static void insert(String sql, Object... params) {
        QueryRunner queryRunner = new QueryRunner();
        int res = queryRunner.insert(connection, sql, null, params);
    }


    @SneakyThrows
    public static void batch(String sql, Object[][] params) {
        QueryRunner queryRunner = new QueryRunner();
        int[] res = queryRunner.batch(connection, sql, params);
    }

    @SneakyThrows
    public static void batchInsert(String sql, Object[][] params) {
        QueryRunner queryRunner = new QueryRunner();
        int[] res = queryRunner.insertBatch(connection, sql, null, params);
    }

}
