/**
 * Created by CGHEVADE
 * Dated May 13, 2019
 */
package com.batbot.configurer.conn.notifier;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author CGHEVADE
 *
 */
@Component()
public class NotifierThread implements Runnable {

	@Autowired
	PostgresNotifier notifier;
	/**
	 *
	 */
	public NotifierThread() {

	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			while(true) {
				System.out.println("Inside Notification");
				notifier.checkNotification();
				Thread.sleep(1000);
			}
		} catch (SQLException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
