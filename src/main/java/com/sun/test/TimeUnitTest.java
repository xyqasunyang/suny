package com.sun.test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;



public class TimeUnitTest {

	public static void main(String[] args) throws InterruptedException, ParseException {
//		TimeUnit.SECONDS.sleep(10);
		List<Object> list = new ArrayList<Object>();
		List<Object> linkList = new LinkedList<Object>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(null, null);
		Map<String,Object> hashTable = new Hashtable<String,Object>();
		hashTable.put(null, null);
		int[] array = new int[3];
		System.out.println(ArrayUtils.isEmpty(array));
		System.out.println();
	}
}
