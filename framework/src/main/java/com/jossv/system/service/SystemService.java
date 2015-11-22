package com.jossv.system.service;

import java.util.List;

import com.jossv.system.model.entity.App;

/**
 * Created by yangjiankang on 15/11/17.
 */
public interface SystemService {

	List<App> getAllApp();
	
	App getApp(Long id);

}
