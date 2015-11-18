package com.jossv.framework.dao.annotation;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

public class RefUtils {

	@SuppressWarnings({ "rawtypes" })
	public static Class[] getClasses(String packageName, Class<? extends Annotation> annotationType) throws ClassNotFoundException, IOException {
		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
		scanner.addIncludeFilter(new AnnotationTypeFilter(annotationType));
		Set<BeanDefinition> beanDefinitions =  scanner.findCandidateComponents(packageName);
		ArrayList<Class> classes = new ArrayList<Class>();
		for (BeanDefinition bean : beanDefinitions) {
			classes.add(Class.forName(bean.getBeanClassName()));
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
