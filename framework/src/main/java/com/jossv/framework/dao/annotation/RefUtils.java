package com.jossv.framework.dao.annotation;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class RefUtils {

	@SuppressWarnings({ "rawtypes" })
	public static Class[] getClasses(String packageName, Class<? extends Annotation> annotationType) throws ClassNotFoundException, IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		assert classLoader != null;
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		ArrayList<Class> classes = new ArrayList<Class>();
		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName, annotationType));
		}
		return classes.toArray(new Class[classes.size()]);
	}

	@SuppressWarnings("rawtypes")
	public static List<Class> findClasses(File directory, String packageName, Class<? extends Annotation> annotationType) throws ClassNotFoundException {
		List<Class> classes = new ArrayList<Class>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName(),annotationType));
			} else if (file.getName().endsWith(".class")) {
				Class<?>  clazz = Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6));
				if(clazz.getAnnotation(annotationType) != null) {
					classes.add(clazz);
				}
			}
		}
		return classes;
	}

}
