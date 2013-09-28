package com.osomac.threading;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

public class CTThreadPool {

	private final BlockingQueue<Runnable> queue;
	private final Thread[] workers;
	private static Logger logger = Logger.getLogger(CTThreadPool.class.getName());
	
	/* My worker class */
	private class CTWorker extends Thread {
		public CTWorker(String workerName) {
			super(workerName);
			logger.finest("Creating CTWorker named '"+workerName+"'");
		}
		
		public void run() {
			while (true) {
				try {
					Runnable runnable = queue.take();
					logger.info("CTWorker '"+this.getName()+"' starting new job.");
					runnable.run();
				} catch (InterruptedException exc) {
					logger.warning("Job Interrupted - " + exc.getMessage());
				} catch (Exception exc) {
					logger.severe("Unhandled Exception! - " + exc.getMessage());
				}
			}
		}
	}
	
	public CTThreadPool(int numThreads) {
		queue = new LinkedBlockingQueue<Runnable>();
		workers = new Thread[numThreads];
		
		for (int i = 0; i < numThreads; ++i) {
			workers[i] = new CTWorker("CTWorker "+i);
			workers[i].start();
		}
	}
	
	public void addJob (Runnable job) {
		logger.finest("Adding new job to the queue");
		queue.add(job);
	}
}
