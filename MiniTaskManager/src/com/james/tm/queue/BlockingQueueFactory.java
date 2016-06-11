package com.james.tm.queue;

import java.util.concurrent.ExecutorService;

/**
 * 
 * @author james
 * 
 */
public class BlockingQueueFactory extends AbstractBlockingQueueFactory {

	public ExecutorService singleExecutors;

	public ExecutorService cacheExecutors;

	public BlockingQueueFactory() {

	}

	public BlockingQueueFactory(Builder builder) {
		this.cacheExecutors = builder.cacheExecutors;
		this.singleExecutors = builder.singleExecutors;
	}

	/**
	 * 集中任务管理初始化
	 */
	public void init() {
		taskBlockingQueueInit();
		failedBlcokingQueueInit();
		messageBlckingQueueInit();
	}

	@Override
	void taskBlockingQueueInit() {
		// TODO Auto-generated method stub
		TaskBlockingQueue.getInstance()
				.initExecutors(singleExecutors, cacheExecutors).init();
	}

	@Override
	void failedBlcokingQueueInit() {
		// TODO Auto-generated method stub
		FailedBlockIngQueue.getInstance()
				.initExecutors(singleExecutors, cacheExecutors).init();
	}

	@Override
	void messageBlckingQueueInit() {
		// TODO Auto-generated method stub
		MessageBlockingQueue.getInstace()
				.initExecutors(singleExecutors, cacheExecutors).init();
	}

	public static class Builder {
		private ExecutorService singleExecutors;

		private ExecutorService cacheExecutors;

		public Builder singleExecutors(ExecutorService singleExecutors) {
			this.singleExecutors = singleExecutors;
			return this;
		}

		public Builder cacheExeccutors(ExecutorService cacheExecutors) {
			this.cacheExecutors = cacheExecutors;
			return this;
		}

		public BlockingQueueFactory build() {

			return new BlockingQueueFactory(this);
		}
	}

}
