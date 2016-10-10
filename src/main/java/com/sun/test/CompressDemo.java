package com.sun.test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;

public class CompressDemo {
	public static void copy(File in, File out) {
		FileInputStream inputStream = null;
		FileOutputStream outStream = null;
		try {
			inputStream = new FileInputStream(in);
			outStream = new FileOutputStream(out);
			int i = 0;
			while ((i = inputStream.read()) != -1) {
				outStream.write(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(inputStream);
			IOUtils.closeQuietly(outStream);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CompressDemo book = new CompressDemo();
		try {
			book.zip("F://1.zip", new File("f://1.txt"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void zip(String zipFileName, File inputFile) throws Exception {
		System.out.println("压缩中...");
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
				zipFileName));
		zip(out, inputFile, inputFile.getName());
		out.close(); // 输出流关闭
		System.out.println("压缩完成");
	}

	private void zip(ZipOutputStream out, File f, String base) throws Exception { // 方法重载
		out.putNextEntry(new ZipEntry(base)); // 创建zip压缩进入点base
		System.out.println(base);
		FileInputStream in = new FileInputStream(f);
		BufferedInputStream bi = new BufferedInputStream(in);
		int b;
		while ((b = bi.read()) != -1) {
			out.write(b); // 将字节流写入当前zip目录
		}
		bi.close();
		in.close(); // 输入流关闭
	}

}
