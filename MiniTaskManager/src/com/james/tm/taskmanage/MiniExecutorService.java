package com.james.tm.taskmanage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * taskmanage
 * 
 * @author james
 * 
 */
public class MiniExecutorService {

	static ExecutorService cachedExecutorService;

	static ExecutorService singleThreadExecutorService;

	public MiniExecutorService() {

	}

	public static ExecutorService getCachedThreadPool() {
		synchronized (MiniExecutorService.class) {
			if (cachedExecutorService == null) {
				cachedExecutorService = Executors.newCachedThreadPool();
			}
		}
		return cachedExecutorService;
	}

	public static ExecutorService getSingleThreadExecutor() {
		synchronized (MiniExecutorService.class) {
			if (singleThreadExecutorService == null) {
				singleThreadExecutorService = Executors
						.newSingleThreadExecutor();
			}
		}
		return singleThreadExecutorService;
	}

}
