package com.sun.test;

public class ReadBookDemo {

	public static void main(String[] args) {
		String content = read("533496");
		content = content.substring(content.indexOf("<div id=\"content\">"));
		System.out.println(content);
		
//		String[]cont = content.split("<br />");
//		for(String str:cont){
//			System.out.println(str);
//		}
	}
	
	
	public static String read(String id){
		return WebClient.sgSendGet("http://www.biquku.com/1/1065/"+id+".html", null);
	}
}
