package com.jossv.reader;

import java.util.Map;
import java.util.Observer;

import com.jossv.reader.impl.App;

/**
 * 
 * 
 * @author yangjiankang
 *
 */
public interface AppsReader {

	Map<String, App> getApps();
	
	void addObserver(Observer o);

}
