package com.james.tm.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.james.tm.handler.BaseHandler;
import com.james.tm.handler.interfaces.IHandler;
import com.james.tm.log.Log;
import com.james.tm.queue.TaskBlockingQueue;
import com.james.tm.task.MiniCallable;
import com.james.tm.task.MiniRunnable;
import com.james.tm.task.Task;
import com.james.tm.task.Task;
import com.james.tm.task.Task.Builder;
import com.james.tm.taskmanage.MiniExecutorService;
import com.james.tm.taskmanage.MtmManager;
import com.james.tm.taskmanage.TaskManager;

/**
 * 主测试案例
 * 
 * @author james
 * 
 */
public class MainSimple {

	private static final String TAG = MainSimple.class.getSimpleName();

	public static MtmManager mtmManager;

	public static void main(String[] args) throws InterruptedException {

		mtmManager = new MtmManager.Builder().build();
		mtmManager.initialize();

		Task mytask = new Task.Builder().handler(new IHandler() {

			@Override
			public <T> void back(T t) {
				// TODO Auto-generated method stub
				Log.d(TAG, "back\n" + t);
			}
		}).build();

		TaskManager.addTask(mytask);
		Thread.sleep(5000);
		TaskManager.cancelTask(mytask);
	}

}
