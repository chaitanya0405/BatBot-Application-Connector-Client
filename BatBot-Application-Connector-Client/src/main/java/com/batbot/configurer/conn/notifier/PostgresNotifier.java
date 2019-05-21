/**
 * Created by CGHEVADE
 * Dated May 13, 2019
 */
package com.batbot.configurer.conn.notifier;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.postgresql.PGConnection;
import org.postgresql.PGNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author CGHEVADE
 *
 */
@Component
@Repository
public class PostgresNotifier {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private DataSource dataSource;


	/**
	 *
	 */
	public PostgresNotifier() {

	}

	public void checkNotification() throws SQLException, InterruptedException {

		Connection conn = this.dataSource.getConnection();
		PGConnection pgConnection = null;
		if(!conn.isClosed()) {
			if (conn.isWrapperFor(PGConnection.class)) {
				System.out.println("Found Connection");
				Properties prop = conn.getClientInfo();
				System.out.println("Properties:"+prop.keySet());
				pgConnection = conn.unwrap(PGConnection.class);
				System.out.println("Found PGConnection:"+pgConnection.getNotifications());
				PGNotification notifications[] = pgConnection.getNotifications();
				if (notifications != null) {
					for (int i=0; i<notifications.length; i++) {
						System.out.println("Got notification: " + notifications[i].getName());
					}
				}
				else
				{
					conn.close();
				}

			}
		}
	}

}
