package com.uppi.poc.throttler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.channel.ExecutorChannel;
import org.springframework.integration.support.MessageBuilder;

public class MessageChunkProducer {
	
	@Autowired
	private ExecutorChannel receiverChannel;
	public void process(String msg) {
		receiverChannel.send(MessageBuilder.withPayload(msg).build());
	}
}
