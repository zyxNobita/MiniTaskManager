package com.james.tm.taskmanage;

import com.james.tm.queue.TaskBlockingQueue;
import com.james.tm.task.Task;

/**
 * 任务管理
 * 
 * @author james
 * 
 */
public class TaskManager {

	public TaskManager() {
			
	}

	public static int addTask(Task task) {
		if (MtmManager.taskBlockingQueue == null)
			throw new NullPointerException(" taskBlockingQueue is null or mtmManager not init");
		return MtmManager.taskBlockingQueue.add(task);

	}

	public static int cancelTask(Task task) {
		if (MtmManager.taskBlockingQueue == null)
			throw new NullPointerException(" taskBlockingQueue is null or mtmManager not init");
		return MtmManager.taskBlockingQueue.cancel(task);
	}

}
