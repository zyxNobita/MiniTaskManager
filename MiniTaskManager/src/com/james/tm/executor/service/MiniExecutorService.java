package com.james.tm.executor.service;

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

	/**
	 * 获取线程池 newCachedThreadPool：
	 * 创建一个可缓存的线程池。如果线程池的大小超过了处理任务所需要的线程，那么就会回收部分空闲（60
	 * 秒不执行任务）的线程，当任务数增加时，此线程池又可以智能的添加新线程来处理任务
	 * 。此线程池不会对线程池大小做限制，线程池大小完全依赖于操作系统（或者说JVM）能够创建的最大线程大小。
	 * 
	 * @return
	 */
	public static ExecutorService getCachedThreadPool() {
		synchronized (MiniExecutorService.class) {
			if (cachedExecutorService == null) {
				cachedExecutorService = Executors.newCachedThreadPool();
			}
		}
		return cachedExecutorService;
	}

	/**
	 * 获取线程池 newSingleThreadExecutor：
	 * 创建一个单线程的线程池。这个线程池只有一个线程在工作，也就是相当于单线程串行执行所有任务。如果这个唯一的线程因为异常结束，
	 * 那么会有一个新的线程来替代它。此线程池保证所有任务的执行顺序按照任务的提交顺序执行。
	 * 
	 * @return
	 */
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
