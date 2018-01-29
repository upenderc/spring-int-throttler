package com.uppi.poc.throttler;

import java.util.Date;
import java.util.logging.Logger;

public class WorkerThread {
	private static final Logger LOG=Logger.getLogger(WorkerThread.class.getName());
	public void process(String msg) {
		LOG.info("Date "+new Date()+",Thread "+Thread.currentThread().getName()+", Msg= "+msg);
	}
}
