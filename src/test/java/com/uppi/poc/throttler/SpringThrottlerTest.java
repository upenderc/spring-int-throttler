package com.uppi.poc.throttler;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.channel.ExecutorChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-test.xml")
public class SpringThrottlerTest {

	@Autowired
	private ExecutorChannel inputChannel;
	private AtomicInteger value = new AtomicInteger(1);

	@Test
	public void showMe() throws Exception {
		ThreadPoolExecutor be = new ThreadPoolExecutor(100, 200, 1000, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>());
		for (int i = 1; i <= 90; i++) {
			be.submit(new TaskSubmiter(inputChannel, value));
		}
		TimeUnit.SECONDS.sleep(28);
	}

	private static class TaskSubmiter implements Runnable {
		private final ExecutorChannel inputChannel;
		private final AtomicInteger i;

		public TaskSubmiter(final ExecutorChannel testChannel, final AtomicInteger i) {
			this.inputChannel = testChannel;
			this.i = i;
		}

		@Override
		public void run() {
			inputChannel.send(
					MessageBuilder.withPayload(Thread.currentThread().getName() + ",= " + i.getAndIncrement()).build());
		}

	}
}
