package com.james.tm.taskmanage;

import java.util.concurrent.ExecutorService;

import com.james.tm.queue.BlockingQueueFactory;

/**
 * 用于初始化 管理［程序启动时运行］
 * 
 * @author james
 * 
 */
public class MtmManager extends AbstractMtmManager {

	private ExecutorService singleExecutors;

	private ExecutorService cacheExecutors;

	private BlockingQueueFactory blockingQueueFactory;

	public MtmManager() {

	}

	/**
	 * 初始化
	 */
	public void initialize() {

		// 线程池初始化
		execcutorsInitialize();
		// 任务、消息、任务失败队列 初始化
		blockQueueInitialize();

	}

	/**
	 * 线程池初始化
	 */
	private void execcutorsInitialize() {
		singleExecutors = MiniExecutorService.getSingleThreadExecutor();
		cacheExecutors = MiniExecutorService.getCachedThreadPool();
	}

	/**
	 * 队列初始化
	 */
	private void blockQueueInitialize() {
		blockingQueueFactory = new BlockingQueueFactory.Builder()
				.singleExecutors(singleExecutors)
				.cacheExeccutors(cacheExecutors).build();
		blockingQueueFactory.init();
	}

	/**
	 * 让正在进行的任务完成之后，在进行关闭整个任务管理器
	 * 
	 */
	public void shutdown() {
		singleExecutors.shutdown();
		cacheExecutors.shutdown();
	}

	/**
	 * 如果有进行的任务，也强制关闭
	 */
	public void shutDownNow() {
		singleExecutors.shutdownNow();
		cacheExecutors.shutdownNow();
	}

	public static class Builder {

		public MtmManager build() {
			return new MtmManager();
		}
	}

}
