package com.sun.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;


public class SgSend {

	// public static void main(String[] args) throws InterruptedException {
	// int id = 13142278;
	// while (true) {
	// String str = "<title>";
	// String html = WebClient.sgSendGet("http://bbs.sgamer.com/thread-" + id +
	// "-1-1.html", null);
	// int x = html.split(str)[1].indexOf(" - DOTA2 -");
	// if(x==-1){
	// id++;
	// continue;
	// }
	// String sub = html.split(str)[1].substring(0, x);
	// HashMap<String, Object> param = new HashMap<String, Object>();
	// param.put("message", sub);
	// param.put("posttime", new Date().getTime());
	// param.put("formhash", "300e7f93");
	// param.put("usesig", "1");
	// param.put("subject", "");
	// WebClient.sgSendPost("http://bbs.sgamer.com/forum.php?mod=post&action=reply&fid=44&tid="
	// + id
	// +
	// "&extra=page%3D1&replysubmit=yes&infloat=yes&handlekey=fastpost&inajax=1",
	// param);
	// System.out.println(id + ":" + sub);
	// id++;
	// Thread.sleep(20000);
	// }
	//
	// }

	public static void main(String[] args) throws Exception {
		while (true) {
			String html = WebClient.sgSendGet("http://bbs.sgamer.com/forum-44-1.html", null);
			String[] arrs = html
					.split("forum.php\\?mod\\=forumdisplay\\&fid\\=44\\&amp\\;filter\\=typeid&amp\\;typeid\\=95");
			// 所有当前页的帖子
			for (int i = 1; i < arrs.length; i++) {
				// 地址
				String index = arrs[i].substring(31, 39);
				// 所有回复过的帖子
				InputStream is = new FileInputStream("F://html.txt");
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				int tag = 0;
				while (true) {
					String str = reader.readLine();
					if (str != null && !str.equals("")) {
						if (str.equals(index)) {
							tag++;
						}
					} else {
						if (tag == 0) {

							// <table cellspacing="0" cellpadding="0"><tr><td
							// class="t_f"
							// 获取标题
							// String title = "<title>";
							// String htmlMain =
							// WebClient.sgSendGet("http://bbs.sgamer.com/thread-"
							// +
							// index + "-1-1.html",
							// null);
							// int x = htmlMain.split(title)[1].indexOf(" -
							// DOTA2
							// -");
							// if (x == -1) {
							// continue;
							// }
							// String sub =
							// htmlMain.split(title)[1].substring(0,
							// x);

							// 获取1楼的回复
							String htmlMain = WebClient.sgSendGet("http://bbs.sgamer.com/thread-" + index + "-1-1.html",
									null);
							String[] arrss = htmlMain
									.split("<table cellspacing=\"0\" cellpadding=\"0\"><tr><td class=\"t_f\"");
							String sub = "";
							if (arrss.length > 1) {
								for (int x = 1; x < arrss.length; x++) {
									int endIndex = arrss[x].indexOf("</td>");
									sub = arrss[x].substring(27, endIndex);
									if (sub.contains("<") || sub.contains("&nbsp;")) {
										sub = "";
										continue;
									} else
										break;
								}
							}
							if (sub.equals("")) {
								reader.close();
								break;
							}
							HashMap<String, Object> param = new HashMap<String, Object>();
							param.put("message", sub);
							param.put("posttime", new Date().getTime());
							param.put("formhash", "300e7f93");
							param.put("usesig", "1");
							param.put("subject", "");
							WebClient.sgSendPost(
									"http://bbs.sgamer.com/forum.php?mod=post&action=reply&fid=44&tid=" + index
											+ "&extra=page%3D1&replysubmit=yes&infloat=yes&handlekey=fastpost&inajax=1",
									param);
							PrintWriter pw = new PrintWriter(new FileWriter("F://html.txt", true), true);
							pw.println(index);
							pw.close();
							Thread.sleep(40000);
						}
						reader.close();
						break;
					}

				}
			}

		}

	}
}
