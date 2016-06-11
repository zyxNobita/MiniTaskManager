package com.james.tm.simple;

import com.james.tm.taskmanage.MtmManager;


/**
 * 主测试案例
 * 
 * @author james
 * 
 */
public class MainSimple {

	public static MtmManager mtmManager;

	public static void main(String[] args) throws InterruptedException {
		mtmManager = new MtmManager.Builder().build();
		mtmManager.initialize();
		new TestSimple().test();
		mtmManager.shutDownNow();
	}

}
