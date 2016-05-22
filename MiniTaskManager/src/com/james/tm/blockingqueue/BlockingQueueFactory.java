package com.james.tm.blockingqueue;

/**
 * 
 * @author james
 * 
 */
public class BlockingQueueFactory extends AbstractBlockingQueueFactory {

	public BlockingQueueFactory() {

	}

	@Override
	public void taskBlockingQueueInit() {
		// TODO Auto-generated method stub
		TaskBlockingQueue.getInstance().init();
	}

	@Override
	public void failedBlcokingQueueInit() {
		// TODO Auto-generated method stub
		FailedBlockIngQueue.getInstance().init();
	}

	@Override
	public void messageBlckingQueueInit() {
		// TODO Auto-generated method stub
		MessageBlockingQueue.getInstace().init();
	}

}
