package com.james.tm.log;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class Log extends Logger{

	
	
	protected Log(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public  void debug(String tag, String logInfo) {
		super.log(tag, Level.DEBUG, logInfo, null);
	}
	
	public static void d(String tag, String logInfo){
		Log.getLogger(tag).log(tag, Level.DEBUG, logInfo, null);
	}
	
	
	public static void e(String tag, Throwable t){
		Log.getLogger(tag).error(tag, t);
	}
}
