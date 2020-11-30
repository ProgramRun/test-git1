package jdbc;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import static jdbc.DBConfig.*;

/**
 * @Author waiter
 * @Date 2020/11/28 0028 22:15
 * @Version 1.0
 * @Description http://commons.apache.org/proper/commons-dbutils/examples.html
 */
@Slf4j
public class DbUtil {

	private static Connection connection = ConnectionEnum.getInstance();

	enum ConnectionEnum {
		INSTANCE;
		private Connection connect;

		@SneakyThrows
		ConnectionEnum() {
			DbUtils.loadDriver(driverName);
			connect = DriverManager.getConnection(url, username, password);
		}

		public static Connection getInstance() {
			return ConnectionEnum.INSTANCE.connect;
		}
	}

	public void close() {
		DbUtils.closeQuietly(connection);
	}


	@SneakyThrows
	public static <T> T queryOne(String sql, Class<T> clazz, Object... params) {
		QueryRunner queryRunner = new QueryRunner();
		return queryRunner.query(connection, sql, new BeanHandler<T>(clazz), params);
	}


	@SneakyThrows
	public static <T> List<T> queryList(String sql, Class<T> clazz, Object... params) {
		QueryRunner queryRunner = new QueryRunner();
		return queryRunner.query(connection, sql, new BeanListHandler<T>(clazz), params);
	}

	public static void main(String[] args) {
		log.info(queryList("select * from user", User.class).toString());
		log.info(insert("insert into user (id,name) values (?,?)", User.class,null,"bbb").toString());
	}

	@SneakyThrows
	public static void update(String sql, Object... params) {
		QueryRunner queryRunner = new QueryRunner();
		queryRunner.update(connection, sql, params);
	}


	@SneakyThrows
	public static <T> T insert(String sql, Class<T> clazz, Object... params) {
		QueryRunner queryRunner = new QueryRunner();
		return queryRunner.insert(connection, sql, new BeanHandler<T>(clazz), params);
	}


	@SneakyThrows
	public static void batch(String sql, Object[][] params) {
		QueryRunner queryRunner = new QueryRunner();
		queryRunner.batch(connection, sql, params);
	}

	@SneakyThrows
	public static void batchInsert(String sql, Object[][] params) {
		QueryRunner queryRunner = new QueryRunner();
		queryRunner.insertBatch(connection, sql, null, params);
	}

}
