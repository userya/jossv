package com.jossv.framework.clazz;

import java.net.URL;
import java.net.URLClassLoader;

import org.springframework.util.ClassUtils;

public class JossvClassLoader extends URLClassLoader {

	private URL url;

	public JossvClassLoader(URL url) {
		super(new URL[] { url }, ClassUtils.getDefaultClassLoader());
		this.url = url;
	}

	public Class<?> loadClass(String name) throws ClassNotFoundException {
		Class<?> c2 = super.loadClass(name);
		return c2;
	}

	public URL getUrl() {
		return url;
	}

}
