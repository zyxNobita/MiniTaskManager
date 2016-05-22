package com.james.tm.taskmanage;

import com.james.tm.queue.BlockingQueueFactory;

/**
 * 用于初始化 管理［程序启动时运行］
 * 
 * @author james
 * 
 */
public class MtmManager {

	public MtmManager() {

	}

	/**
	 * 初始化
	 */
	public static void initialize() {
		BlockingQueueFactory blockingQueueFactory = new BlockingQueueFactory();
		blockingQueueFactory.taskBlockingQueueInit();
		blockingQueueFactory.failedBlcokingQueueInit();
		blockingQueueFactory.messageBlckingQueueInit();

	}

}
