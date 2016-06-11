package com.james.tm.simple;

import com.james.tm.handler.interfaces.IHandler;
import com.james.tm.log.Log;
import com.james.tm.task.Task;
import com.james.tm.taskmanage.MtmManager;
import com.james.tm.taskmanage.TaskManager;

public class TestSimple {


	private static final String TAG = TestSimple.class.getSimpleName();
	
	void test() throws InterruptedException {

		for (int i = 0; i < 500; i++) {
			Task mytask = new Task.Builder().handler(new IHandler() {

				@Override
				public <T> void back(T t) {
					// TODO Auto-generated method stub
					Log.d(TAG, "back\n" + t.toString());
				}
			}).build();
			TaskManager.addTask(mytask);
			
		}
		
//		Thread.sleep(1000*60*2);
		//TaskManager.cancelTask(mytask);
//		test2();
		
	}
	
	void test2(){
		for (int i = 0; i < 500; i++) {
			Task mytask = new Task.Builder().handler(new IHandler() {

				@Override
				public <T> void back(T t) {
					// TODO Auto-generated method stub
					Log.d(TAG, "back\n" + t.toString());
				}
			}).build();
			TaskManager.addTask(mytask);
			//TaskManager.cancelTask(mytask);
		}
	}
}
