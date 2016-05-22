package com.james.tm.queue;

import com.james.tm.queue.interfaces.IBaseBlockingQueue;
import com.james.tm.task.Task;

/**
 * 消息队列
 * 
 * @author james
 * 
 */
public class MessageBlockingQueue implements IBaseBlockingQueue {

	public static MessageBlockingQueue messageBlockingQueue;

	private MessageBlockingQueue() {

	}

	public static MessageBlockingQueue getInstace() {
		synchronized (MessageBlockingQueue.class) {
			if (messageBlockingQueue == null) {
				messageBlockingQueue = new MessageBlockingQueue();
			}
		}
		return messageBlockingQueue;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public int add(Task mytask) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int cancel(Task mytask) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Task get(Task mytask) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loop() {
		// TODO Auto-generated method stub

	}

}
