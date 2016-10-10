package com.sun.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadXML {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Properties p = new Properties();
		p.load(new ReadXML().getClass().getResourceAsStream("/properties.properties"));
		System.out.println(p.getProperty("1"));
	}
}
