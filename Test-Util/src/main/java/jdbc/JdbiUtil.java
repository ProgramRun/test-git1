package jdbc;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Query;
import org.jdbi.v3.core.statement.Update;

import java.util.List;

import static jdbc.DBConfig.*;


/**
 * @Author waiter
 * @Date 2020/11/28 0028 21:59
 * @Version 1.0
 * @Description
 */
@Slf4j
public class JdbiUtil {

	private static Jdbi jdbi = JdbiEnum.getInstance();

	enum JdbiEnum {
		INSTANCE;
		private Jdbi jdbi;

		@SneakyThrows
		JdbiEnum() {
			Class.forName(driverName);
			jdbi = Jdbi.create(url, username, password);
		}

		public static Jdbi getInstance() {
			return JdbiEnum.INSTANCE.jdbi;
		}
	}

	private static Handle getHandle() {
		return jdbi.open();
	}

	public static void query(String sql, Object... params) {
		Handle handle = getHandle();
		Query query = handle.createQuery(sql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.bind(i, params[i]);
			}
		}
		log.info(query.mapToBean(User.class).list().toString());
	}


	public static void update(String sql, Object... params) {
		Handle handle = getHandle();
		Update update = handle.createUpdate(sql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				update.bind(i, params[i]);
			}
		}
		update.execute();
	}


	public static void insert(String sql, Object... params) {
		Handle handle = getHandle();
		handle.execute(sql, params);
	}


	@SneakyThrows
	public static void main(String[] args) {

		Jdbi jdbi = Jdbi.create(url, username, password);
		List<User> users = jdbi.withHandle(handle -> {
			/*// Inline positional parameters
			handle.execute("INSERT INTO user(id, name) VALUES (?, ?)", 0, "Alice");

			// Positional parameters
			handle.createUpdate("INSERT INTO user(id, name) VALUES (?, ?)")
					.bind(0, 1) // 0-based parameter indexes
					.bind(1, "Bob")
					.execute();

			// Named parameters
			handle.createUpdate("INSERT INTO user(id, name) VALUES (:id, :name)")
					.bind("id", 2)
					.bind("name", "Clarice")
					.execute();

			// Named parameters from bean properties
			handle.createUpdate("INSERT INTO user(id, name) VALUES (:id, :name)")
					.bindBean(new User(3, "David"))
					.execute();*/

			// Easy mapping to any type
			return handle.createQuery("SELECT * FROM user ORDER BY name")
					.mapToBean(User.class)
					.list();
		});
        users.forEach(System.out::println);
	}


}
