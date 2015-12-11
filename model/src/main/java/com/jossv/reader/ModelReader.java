package com.jossv.reader;

import java.io.InputStream;
import java.io.Writer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class ModelReader<T> {

	private static Map<Class<?>, Unmarshaller> unmarshallerMap = new ConcurrentHashMap<Class<?>, Unmarshaller>();

	private static Map<Class<?>, Marshaller> marshallerMap = new ConcurrentHashMap<Class<?>, Marshaller>();

	private Class<T> targetClass;

	private InputStream file;

	public ModelReader(Class<T> targetClass, InputStream file) {
		super();
		this.targetClass = targetClass;
		this.file = file;
		if (!unmarshallerMap.containsKey(targetClass)) {
			JAXBContext jaxbContext;
			try {
				jaxbContext = JAXBContext.newInstance(targetClass);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				Marshaller mar = jaxbContext.createMarshaller();
				unmarshallerMap.put(targetClass, jaxbUnmarshaller);
				marshallerMap.put(targetClass, mar);
			} catch (JAXBException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public T read() {
		try {
			T obj = (T) unmarshallerMap.get(targetClass).unmarshal(file);
			return obj;
		} catch (JAXBException e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1);
		}
	}

	public void write(Writer out, T pojo) {
		try {
			marshallerMap.get(targetClass).marshal(pojo, out);
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
