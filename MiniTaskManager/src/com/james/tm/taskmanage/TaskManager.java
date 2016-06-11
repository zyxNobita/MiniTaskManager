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

		return TaskBlockingQueue.getInstance().add(task);

	}

	public static int cancelTask(Task task) {

		return TaskBlockingQueue.getInstance().cancel(task);
	}
	

}
