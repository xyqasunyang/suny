package com.sun.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.client.params.CookiePolicy;


@SuppressWarnings("deprecation")
public class WebClient {
	public static String downloadString(String url) throws IOException {
		URL _url = new URL(url);
		InputStream is = _url.openStream();
		Scanner sc = new Scanner(is, "utf-8");
		String result = "";
		while (sc.hasNextLine()) {
			result += sc.nextLine();
		}
		sc.close();
		return result;
	}

	public static byte[] downloadData(String url) throws IOException {
		URL _url = new URL(url);
		URLConnection con = _url.openConnection();
		con.setReadTimeout(10000);
		con.connect();
		Map<String, List<String>> map = con.getHeaderFields();
		BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
		byte[] buffer = new byte[Integer.parseInt(map.get("Content-Length").get(0))];
		int readed = 0;
		while (readed < buffer.length) {
			readed += bis.read(buffer, readed, buffer.length - readed);
		}
		return buffer;
	}

	public static String uploadData(String url, byte[] bytes) throws IOException {
		URL _url = new URL(url);
		HttpURLConnection con = (HttpURLConnection) _url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con.setDoInput(true);
		con.setDoOutput(true);
		// 握手
		con.connect();
		// post数据
		BufferedOutputStream os = new BufferedOutputStream(con.getOutputStream());
		os.write(bytes);
		os.close();
		// 打开输入流
		InputStream is = con.getInputStream();
		Scanner sc = new Scanner(is, "utf-8");
		String result = "";
		while (sc.hasNextLine()) {
			result += sc.nextLine();
		}
		sc.close();
		return result;
	}

	public static String sendPost(String url, HashMap<String, Object> param) {
		String responseMsg = "";

		// 1.构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setContentCharset("UTF-8");

		// 2.构造PostMethod的实例
		PostMethod postMethod = new PostMethod(url);
		// postMethod.setRequestHeader("Cookie","security_SESSIONID_sub=1xhd4nuegmfpl84hgume2mjlv");
		// 3.把参数值放入到PostMethod对象中
		if (param != null) {
			for (String key : param.keySet()) {
				postMethod.addParameter(key, String.valueOf(param.get(key)));
			}
		}
		try { // 4.执行postMethod,调用http接口
			httpClient.executeMethod(postMethod);// 200

			// 5.读取内容
			responseMsg = postMethod.getResponseBodyAsString().trim();

			// 6.处理返回的内容

		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally { // 7.释放连接
			postMethod.releaseConnection();
		}
		return responseMsg;
	}

	public static String sgSendPost(String url, HashMap<String, Object> param) {
		String responseMsg = "";

		// 1.构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setContentCharset("UTF-8");

		// 2.构造PostMethod的实例
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
		postMethod.setRequestHeader("Cookie",
				"iaFarmuser=iafram; tjpctrl=1472451586724; U6IV_2132_saltkey=PCnYCCYe; U6IV_2132_lastvisit=1472447108; U6IV_2132_sendmail=1; U6IV_2132_seccode=557758.0454ed5779906f0acb; U6IV_2132_ulastactivity=1472450731%7C0; U6IV_2132_lastcheckfeed=8934281%7C1472450731; U6IV_2132_checkfollow=1; U6IV_2132_lip=115.238.62.226%2C1472450731; U6IV_2132_security_cookiereport=f3530UwCqkoBfdey8RVmiSVecLWw3OGxhKAqWI5xkGcgloU89zyF; U6IV_2132_auth=d094BYi8EDW4Rma%2Bi%2FjiknMS1Da4McC4qa0UVlkCMEymHvjnqefYdOqJPAo5N%2F3AYYitpu3Ms44vmlGPV%2FE1PjTJlLfp; sgamer_passport_auth=%2BlcEsIHoiBYzpheAF04fq5Z1hAIT%2FQdIR7MjO6yFuXCJTUGivP7w8RcgNxPiXURKkPW3EzCErrHcvMSLd9XL5lClUhYI64pA4WUKKtYB%2B4rBazx5MZ0iYVBaA5RmkaHnehPcMQc1RfXeXPWJ0ZPJg5aXXGhsqI2GVS8ku8aBP24kg%2BSlOoDV%2FBYOxCJUoqNxwG0xWBnMvpYvAnXBD7TTMS76mV6vp3eA; U6IV_2132_checkpm=1; U6IV_2132_st_p=8934281%7C1472450744%7C71ac9a72475e7a7b249bb248563f0f13; U6IV_2132_visitedfid=44; U6IV_2132_viewid=tid_13138184; U6IV_2132_smile=1D1; sg_cblzb=0; pgv_pvi=8579103772; pgv_info=ssi=s37724120; CNZZDATA30039357=cnzz_eid%3D568401346-1472435273-%26ntime%3D1472446073; U6IV_2132_lastact=1472450745%09misc.php%09patch; U6IV_2132_connect_is_bind=0");
		// postMethod.setRequestHeader("Cookie","security_SESSIONID_sub=1xhd4nuegmfpl84hgume2mjlv");
		// 3.把参数值放入到PostMethod对象中
		if (param != null) {
			for (String key : param.keySet()) {
				postMethod.addParameter(key, String.valueOf(param.get(key)));
			}
		}
		try { // 4.执行postMethod,调用http接口
			httpClient.executeMethod(postMethod);// 200

			// 5.读取内容
			responseMsg = postMethod.getResponseBodyAsString().trim();

			// 6.处理返回的内容

		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally { // 7.释放连接
			postMethod.releaseConnection();
		}
		return responseMsg;
	}

	public static String sgSendGet(String url, HashMap<String, Object> param) {
		String responseMsg = "";

		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setContentCharset("GBK");
		String str = "";
		if (param != null) {
			for (String key : param.keySet()) {
				if (str.equals("")) {
					str = "?" + str + key + "=" + param.get(key);
				} else {
					str = str + "&" + key + "=" + param.get(key);
				}
			}
		}
		GetMethod getMethod = new GetMethod(url + str);
		getMethod.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
		getMethod.setRequestHeader("Cookie",
				"iaFarmuser=iafram; tjpctrl=1472451586724; U6IV_2132_saltkey=PCnYCCYe; U6IV_2132_lastvisit=1472447108; U6IV_2132_sendmail=1; U6IV_2132_seccode=557758.0454ed5779906f0acb; U6IV_2132_ulastactivity=1472450731%7C0; U6IV_2132_lastcheckfeed=8934281%7C1472450731; U6IV_2132_checkfollow=1; U6IV_2132_lip=115.238.62.226%2C1472450731; U6IV_2132_security_cookiereport=f3530UwCqkoBfdey8RVmiSVecLWw3OGxhKAqWI5xkGcgloU89zyF; U6IV_2132_auth=d094BYi8EDW4Rma%2Bi%2FjiknMS1Da4McC4qa0UVlkCMEymHvjnqefYdOqJPAo5N%2F3AYYitpu3Ms44vmlGPV%2FE1PjTJlLfp; sgamer_passport_auth=%2BlcEsIHoiBYzpheAF04fq5Z1hAIT%2FQdIR7MjO6yFuXCJTUGivP7w8RcgNxPiXURKkPW3EzCErrHcvMSLd9XL5lClUhYI64pA4WUKKtYB%2B4rBazx5MZ0iYVBaA5RmkaHnehPcMQc1RfXeXPWJ0ZPJg5aXXGhsqI2GVS8ku8aBP24kg%2BSlOoDV%2FBYOxCJUoqNxwG0xWBnMvpYvAnXBD7TTMS76mV6vp3eA; U6IV_2132_checkpm=1; U6IV_2132_st_p=8934281%7C1472450744%7C71ac9a72475e7a7b249bb248563f0f13; U6IV_2132_visitedfid=44; U6IV_2132_viewid=tid_13138184; U6IV_2132_smile=1D1; sg_cblzb=0; pgv_pvi=8579103772; pgv_info=ssi=s37724120; CNZZDATA30039357=cnzz_eid%3D568401346-1472435273-%26ntime%3D1472446073; U6IV_2132_lastact=1472450745%09misc.php%09patch; U6IV_2132_connect_is_bind=0");
		try {
			httpClient.executeMethod(getMethod);// 200

			responseMsg = getMethod.getResponseBodyAsString().trim();

		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally { // 7.释放连接
			getMethod.releaseConnection();
		}
		return responseMsg;
	}
}
