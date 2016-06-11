package com.james.tm.taskmanage;

/**
 * abstractmtmManager
 * 
 * @author james
 * 
 */
public abstract class AbstractMtmManager {

	/**
	 * 任务管理器初始化
	 */
	abstract void initialize();

	/**
	 * 让正在进行的任务完成之后，在进行关闭整个任务管理器
	 * 
	 */
	abstract void shutdown();

	/**
	 * 如果有进行的任务，也强制关闭
	 */
	abstract void shutDownNow();

}
