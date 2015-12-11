package com.jossv.reader.impl;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import com.jossv.model.config.Config;
import com.jossv.model.data.Datas;
import com.jossv.model.entity.Entities;
import com.jossv.model.entity.Entity;
import com.jossv.model.page.Page;
import com.jossv.model.service.Service;
import com.jossv.model.service.Services;
import com.jossv.model.table.Table;
import com.jossv.model.table.Tables;
import com.jossv.reader.AppsReader;
import com.jossv.reader.ModelReader;

/**
 * 目录约定说明 apps - > app -> stage - > *
 * 
 * @author yangjiankang
 *
 */
public class AppsReaderImpl extends Observable implements AppsReader {

	private String resourcePath;

	private File root;

	private Map<String, App> apps;

	protected FileAlterationMonitor monitor;

	public AppsReaderImpl() {

	}

	protected void initMoinitor(File root) {
		if (monitor == null) {
			monitor = new FileAlterationMonitor(3000);
			FileAlterationObserver fileObserver = new FileAlterationObserver(root);
			fileObserver.addListener(new FileAlterationListenerAdaptor() {
				@Override
				public void onDirectoryDelete(File directory) {
					reload();
				}

				@Override
				public void onFileChange(File file) {
					if (file.getName().indexOf(".xml") > -1) {
						reload();
					}
				}

				@Override
				public void onFileDelete(File file) {
					if (file.getName().indexOf(".xml") > -1) {
						reload();
					}
				}

			});
			monitor.addObserver(fileObserver);
		}
	}

	public void reload() {
		apps.clear();
		loadXml();
		setChanged();
	}

	public void loadXml() {
		if (!root.exists()) {
			throw new RuntimeException(resourcePath + " is not exists... please check..");
		}
		Map<String, App> appMap = new ConcurrentHashMap<>();
		FileFilter filter = new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				if(pathname.getName().startsWith(".")) {
					return false;
				}
				return true;
			}
		};
		File[] apps = root.listFiles(filter);
		for (File app : apps) {
			App a = new App();
			a.setName(app.getName());
			appMap.put(app.getName(), a);
			for (File stage : app.listFiles(filter)) {
				if (stage.isDirectory()) {
					Stage s = new Stage();
					a.getStageMap().put(stage.getName(), s);
					for (File xml : stage.listFiles(filter)) {
						if (xml.isFile()) {
							String name = xml.getName();
							FileInputStream is;
							try {
								is = new FileInputStream(xml);
								if (name.indexOf(".data.xml") > -1) {
									Datas datas = new ModelReader<>(Datas.class, is).read();
									s.getDatas().addAll(datas.getData());
								} else if (name.indexOf(".table.xml") > -1) {
									Tables tabls = new ModelReader<>(Tables.class, is).read();
									for (Table table : tabls.getTable()) {
										s.getTables().put(table.getId(), table);
									}
								} else if (name.indexOf(".entity.xml") > -1) {
									Entities entis = new ModelReader<>(Entities.class, is).read();
									for (Entity entity : entis.getEntity()) {
										s.getEntities().put(entity.getId(), entity);
									}

								} else if (name.indexOf(".service.xml") > -1) {
									Services servs = new ModelReader<>(Services.class, is).read();
									for (Service service : servs.getService()) {
										s.getServices().put(service.getId(), service);
										a.getServiceMap().put(service.getId(), s);
									}
									
								} else if (name.indexOf(".page.xml") > -1) {
									s.getPages().put(name, new ModelReader<>(Page.class, is).read());
								} else if (name.indexOf(".stage.xml") > -1) {
									s.setStage(new ModelReader<>(com.jossv.model.stage.Stage.class, is).read());
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}

					}
				} else {
					if (stage.getName().indexOf(".config.xml") > -1) {
						try {
							Config c = new ModelReader<Config>(Config.class, new FileInputStream(stage)).read();
							a.setConfig(c);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		this.apps = appMap;
	}

	public void load() {

		initMoinitor(root);

		loadXml();

		try {
			monitor.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void close(){
		try {
			this.monitor.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
		root = new File(resourcePath);
	}

	public Map<String, App> getApps() {
		return apps;
	}

	public void setApps(Map<String, App> apps) {
		this.apps = apps;
	}
	
	

}
