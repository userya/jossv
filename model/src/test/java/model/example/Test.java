package model.example;

import java.io.InputStream;
import java.io.StringWriter;

import com.jossv.model.table.Tables;
import com.jossv.reader.ModelReader;

public class Test {
	
	public static void main(String[] args) {
		InputStream file = Test.class.getClassLoader().getResourceAsStream("model/example/table.xml");
		ModelReader<Tables> reader = new ModelReader<>(Tables.class, file);
		Tables tables = reader.read();
		System.out.println(tables);
		StringWriter out = new StringWriter();
		reader.write(out, tables);
		System.out.println(out.toString());
	}

}
