package com.batbot.configurer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.batbot.configurer.conn.notifier.NotifierThread;

@SpringBootApplication
public class BatBotApplicationConnectorClientApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(BatBotApplicationConnectorClientApplication.class, args);
		NotifierThread notifier = (NotifierThread) applicationContext.getBean("notifierThread");
		Thread t1 = new Thread(notifier);
		t1.start();
	}

	@Bean
	public NotifierThread getNotifierThread() {
		return new NotifierThread();
	}

}
