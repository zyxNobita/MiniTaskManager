package com.james.tm.simple;

import com.james.tm.handler.interfaces.IHandler;
import com.james.tm.log.Log;
import com.james.tm.task.Task;
import com.james.tm.taskmanage.MtmManager;
import com.james.tm.taskmanage.TaskManager;

public class TestSimple {
	public static MtmManager mtmManager;

	private static final String TAG = TestSimple.class.getSimpleName();
	
	void test() throws InterruptedException {

		mtmManager = new MtmManager.Builder().build();
		mtmManager.initialize();

		Task mytask = new Task.Builder().handler(new IHandler() {

			@Override
			public <T> void back(T t) {
				// TODO Auto-generated method stub
				Log.d(TAG, "back\n" + t.toString());
			}
		}).build();

		TaskManager.addTask(mytask);
		Thread.sleep(5000);
		mtmManager.shutDownNow();
	}
}
