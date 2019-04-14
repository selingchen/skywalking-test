package com.seling.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtils {
	
	public static final int THREAD_POOL_SIZE = 3;
 
	public interface HttpClientDownLoadProgress {
		public void onProgress(int progress);
	}
 
	private static HttpUtils httpClient;
 
	private ExecutorService downloadExcutorService;
 
	private HttpUtils() {
		downloadExcutorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
	}
 
	public static HttpUtils getInstance() {
		if (httpClient == null) {
			httpClient = new HttpUtils();
		}
		return httpClient;
	}

	public static String basePath = System.getProperty("user.dir");

	public static void getFile(String url, String charset, String fileName) {
		BufferedReader in = null;
		try {
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("contentType", charset);
			conn.setRequestProperty("content-type", "application/xmsdownload");
			conn.setConnectTimeout(6000);
			conn.setReadTimeout(6000);
			conn.connect();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			InputStream inputStream = conn.getInputStream();
			inputStream = new BufferedInputStream(inputStream);
			byte[] buffer = new byte[10 * 1024];
			int len = 0;
			while ((len = inputStream.read(buffer)) != -1) {
				if (len > 0) {
					outputStream.write(buffer, 0, len);
				}
				outputStream.flush();
			}
			Path path = Paths.get(basePath + File.separator + fileName);
			Files.write(path, outputStream.toByteArray());
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
	}

	 
		/**
		 * 下载文件
		 * 
		 * @param url
		 * @param filePath
		 */
		public void download(final String url, final String filePath) {
			downloadExcutorService.execute(new Runnable() {
				@Override
				public void run() {
					httpDownloadFile(url, filePath, null, null);
				}
			});
		}
	 
		/**
		 * 下载文件
		 * 
		 * @param url
		 * @param filePath
		 * @param progress
		 *            进度回调
		 */
		public void download(final String url, final String filePath,
				final HttpClientDownLoadProgress progress) {
			downloadExcutorService.execute(new Runnable() {
	 
				@Override
				public void run() {
					httpDownloadFile(url, filePath, progress, null);
				}
			});
		}
	 
		/**
		 * 下载文件
		 * 
		 * @param url
		 * @param filePath
		 */
		public void httpDownloadFile(String url, String filePath,
				HttpClientDownLoadProgress progress, Map<String, String> headMap) {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			try {
				HttpGet httpGet = new HttpGet(url);
				setGetHead(httpGet, headMap);
				CloseableHttpResponse response1 = httpclient.execute(httpGet);
				try {
					//System.out.println(response1.getStatusLine());
					HttpEntity httpEntity = response1.getEntity();
					long contentLength = httpEntity.getContentLength();
					InputStream is = httpEntity.getContent();
					// 根据InputStream 下载文件
					ByteArrayOutputStream output = new ByteArrayOutputStream();
					byte[] buffer = new byte[4096];
					int r = 0;
					long totalRead = 0;
					while ((r = is.read(buffer)) > 0) {
						output.write(buffer, 0, r);
						totalRead += r;
						if (progress != null) {// 回调进度
							progress.onProgress((int) (totalRead * 100 / contentLength));
						}
					}
					FileOutputStream fos = new FileOutputStream(filePath);
					output.writeTo(fos);
					output.flush();
					output.close();
					fos.close();
					EntityUtils.consume(httpEntity);
				} finally {
					response1.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					httpclient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	 
		/**
		 * get请求
		 * 
		 * @param url
		 * @return
		 */
		public String httpGet(String url) {
			return httpGet(url, null);
		}
	 
		/**
		 * http get请求
		 * 
		 * @param url
		 * @return
		 */
		public String httpGet(String url, Map<String, String> headMap) {
			String responseContent = null;
			CloseableHttpClient httpclient = HttpClients.createDefault();
			try {
				HttpGet httpGet = new HttpGet(url);
				CloseableHttpResponse response = httpclient.execute(httpGet);
				setGetHead(httpGet, headMap);
				try {
					HttpEntity entity = response.getEntity();
					responseContent = getRespString(entity);
					EntityUtils.consume(entity);
				} finally {
					response.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					httpclient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return responseContent;
		}
	 
		public String httpPost(String url, Map<String, String> paramsMap) {
			return httpPost(url, paramsMap, null);
		}
	 
		/**
		 * http的post请求
		 * 
		 * @param url
		 * @param paramsMap
		 * @return
		 */
		public String httpPost(String url, Map<String, String> paramsMap,
				Map<String, String> headMap) {
			String responseContent = null;
			CloseableHttpClient httpclient = HttpClients.createDefault();
			try {
				HttpPost httpPost = new HttpPost(url);
				setPostHead(httpPost, headMap);
				setPostParams(httpPost, paramsMap);
				CloseableHttpResponse response = httpclient.execute(httpPost);
				try {
					System.out.println(response.getStatusLine());
					HttpEntity entity = response.getEntity();
					responseContent = getRespString(entity);
					EntityUtils.consume(entity);
				} finally {
					response.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					httpclient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return responseContent;
		}
	 
		/**
		 * 设置POST的参数
		 * 
		 * @param httpPost
		 * @param paramsMap
		 * @throws Exception
		 */
		private void setPostParams(HttpPost httpPost, Map<String, String> paramsMap)
				throws Exception {
			if (paramsMap != null && paramsMap.size() > 0) {
				List<NameValuePair> nvps = new ArrayList<NameValuePair>();
				Set<String> keySet = paramsMap.keySet();
				for (String key : keySet) {
					nvps.add(new BasicNameValuePair(key, paramsMap.get(key)));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			}
		}
	 
		/**
		 * 设置http的HEAD
		 * 
		 * @param httpPost
		 * @param headMap
		 */
		private void setPostHead(HttpPost httpPost, Map<String, String> headMap) {
			if (headMap != null && headMap.size() > 0) {
				Set<String> keySet = headMap.keySet();
				for (String key : keySet) {
					httpPost.addHeader(key, headMap.get(key));
				}
			}
		}
	 
		/**
		 * 设置http的HEAD
		 * 
		 * @param httpGet
		 * @param headMap
		 */
		private void setGetHead(HttpGet httpGet, Map<String, String> headMap) {
			if (headMap != null && headMap.size() > 0) {
				Set<String> keySet = headMap.keySet();
				for (String key : keySet) {
					httpGet.addHeader(key, headMap.get(key));
				}
			}
		}
	 
		/**
		 * 上传文件
		 * 
		 * @param serverUrl
		 *            服务器地址
		 * @param localFilePath
		 *            本地文件路径
		 * @param serverFieldName
		 * @param params
		 * @return
		 * @throws Exception
		 */
		public String uploadFileImpl(String serverUrl, String localFilePath,
				String serverFieldName, Map<String, String> params)
				throws Exception {
			String respStr = null;
			CloseableHttpClient httpclient = HttpClients.createDefault();
			try {
				HttpPost httppost = new HttpPost(serverUrl);
				FileBody binFileBody = new FileBody(new File(localFilePath));
	 
				MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder
						.create();
				// add the file params
				multipartEntityBuilder.addPart(serverFieldName, binFileBody);
				// 设置上传的其他参数
				setUploadParams(multipartEntityBuilder, params);
				HttpEntity reqEntity = multipartEntityBuilder.build();
				httppost.setEntity(reqEntity);
				CloseableHttpResponse response = httpclient.execute(httppost);
				try {
					System.out.println(response.getStatusLine());
					HttpEntity resEntity = response.getEntity();
					respStr = getRespString(resEntity);
					EntityUtils.consume(resEntity);
				} finally {
					response.close();
				}
			} finally {
				httpclient.close();
			}
			System.out.println("resp=" + respStr);
			return respStr;
		}
	 
		/**
		 * 设置上传文件时所附带的其他参数
		 * 
		 * @param multipartEntityBuilder
		 * @param params
		 */
		private void setUploadParams(MultipartEntityBuilder multipartEntityBuilder,
				Map<String, String> params) {
			if (params != null && params.size() > 0) {
				Set<String> keys = params.keySet();
				for (String key : keys) {
					multipartEntityBuilder
							.addPart(key, new StringBody(params.get(key),
									ContentType.TEXT_PLAIN));
				}
			}
		}
	 
		/**
		 * 将返回结果转化为String
		 * 
		 * @param entity
		 * @return
		 * @throws Exception
		 */
		private String getRespString(HttpEntity entity) throws Exception {
			if (entity == null) {
				return null;
			}
			InputStream is = entity.getContent();
			StringBuffer strBuf = new StringBuffer();
			byte[] buffer = new byte[4096];
			int r = 0;
			while ((r = is.read(buffer)) > 0) {
				strBuf.append(new String(buffer, 0, r, "UTF-8"));
			}
			return strBuf.toString();
		}
}
