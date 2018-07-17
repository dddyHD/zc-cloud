package cn.suancloud.springBoot.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static cn.suancloud.springBoot.util.Constant.OPENSHIFT_URL;
import static cn.suancloud.springBoot.util.IgnoreCertificates.ignoreCertificates;

public class HttpUtil {
  protected static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

  /**
   * oapi get请求转发器
   *
   * @return 原平台请求格式
   */
  public static String sendGet(HttpServletRequest request, HttpServletResponse response) {
    String url = getUrl(request);
    String result = "";
    BufferedReader in = null;
    try {
      ignoreCertificates();
      URL realUrl = new URL(url);

      // 打开和URL之间的连接
      HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
      // 配置原有的请求头
      setHeader(request, connection);
      connection.connect();
      //设置返回状态码
      response.setStatus(connection.getResponseCode());
      // 定义 BufferedReader输入流来读取URL的响应
      if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
        in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      else
        in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
      String line;
      while ((line = in.readLine()) != null) {
        result += line;
      }
    } catch (Exception e) {
      logger.error("发送GET请求出现异常！" + e);
      e.printStackTrace();
    }
    // 使用finally块来关闭输入流
    finally {
      closeStream(null, in);
    }
    return result;
  }

  /**
   * 向指定URL发送POST方法的请求
   *
   * @paramurl 发送请求的URL
   * @returnURL所代表远程资源的响应
   */
  public static String sendPost(HttpServletRequest request, HttpServletResponse response) {
    String url = getUrl(request);
    // TODO: 2018/7/17  如果为form data格式暂未解决
    // 获取json格式
    String param = getRequestPayload(request);
    OutputStreamWriter out = null;
    BufferedReader in = null;
    String result = "";
    try {
      ignoreCertificates();
      URL realUrl = new URL(url);
      // 打开和URL之间的连接
      HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
      // 配置原有的请求头
      setHeader(request, connection);
      connection.setRequestMethod("POST");
      // Post 请求不能使用缓存
      connection.setUseCaches(false);
      // 发送POST请求必须设置如下两行
      connection.setDoOutput(true);
      connection.setDoInput(true);
      // 获取URLConnection对象对应的输出流
      out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
      // 发送请求参数
      out.write(param);
      // flush输出流的缓冲
      out.flush();

      response.setStatus(connection.getResponseCode());
      // 定义BufferedReader输入流来读取URL的响应
      if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
        in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      else
        in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
      String line;
      while ((line = in.readLine()) != null) {
        result += line;
      }
    } catch (Exception e) {
      logger.error("发送 POST 请求出现异常！" + e);
      e.printStackTrace();
    }
    //使用finally块来关闭输出流、输入流
    finally {
      closeStream(out, in);
    }
    return result;
  }

  /**
   * 向指定的URL发送PUT方法请求
   */
  public static String sendPut(HttpServletRequest request, HttpServletResponse response) {
    String url = getUrl(request);
    // TODO: 2018/7/17  如果为form data格式暂未解决
    // 获取json格式
    String param = getRequestPayload(request);
    OutputStreamWriter out = null;
    BufferedReader in = null;
    String result = "";
    try {
      ignoreCertificates();
      URL realUrl = new URL(url);
      // 打开和URL之间的连接
      HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
      // 配置原有的请求头
      setHeader(request, connection);
      connection.setRequestMethod("PUT");
      // Post 请求不能使用缓存
      connection.setUseCaches(false);
      // 发送POST请求必须设置如下两行
      connection.setDoOutput(true);
      connection.setDoInput(true);
      // 获取URLConnection对象对应的输出流
      out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
      // 发送请求参数
      out.write(param);
      // flush输出流的缓冲
      out.flush();

      response.setStatus(connection.getResponseCode());
      // 定义BufferedReader输入流来读取URL的响应
      if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
        in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      else
        in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
      String line;
      while ((line = in.readLine()) != null) {
        result += line;
      }
    } catch (Exception e) {
      System.out.println();
      logger.error("发送 PUT 请求出现异常！" + e);
      e.printStackTrace();
    }
    //使用finally块来关闭输出流、输入流
    finally {
      closeStream(out, in);
    }
    return result;
  }

  /**
   * 向指定URL发送DELETE方法的请求
   *
   * @paramurl 发送请求的URL
   * @returnURL所代表远程资源的响应
   */
  public static String sendDelete(HttpServletRequest request, HttpServletResponse response) {
    String url = getUrl(request);
    String result = "";
    BufferedReader in = null;
    try {
      ignoreCertificates();
      URL realUrl = new URL(url);

      // 打开和URL之间的连接
      HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
      // 配置原有的请求头
      setHeader(request, connection);
      connection.setRequestMethod("DELETE");
      connection.connect();
      //设置返回状态码
      response.setStatus(connection.getResponseCode());
      // 定义 BufferedReader输入流来读取URL的响应
      if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
        in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      else
        in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
      String line;
      while ((line = in.readLine()) != null) {
        result += line;
      }
    } catch (Exception e) {
      logger.error("发送DELETE请求出现异常！" + e);
      e.printStackTrace();
    }
    // 使用finally块来关闭输入流
    finally {
      closeStream(null, in);
    }
    return result;
  }

  /**
   * 向指定URL发送PATCH方法的请求
   *
   * @paramurl 发送请求的URL
   * @returnURL所代表远程资源的响应
   */
  public static String sendPatch(HttpServletRequest request, HttpServletResponse response) {
    String url = getUrl(request);
    // TODO: 2018/7/17  如果为form data格式暂未解决
    // 获取json格式
    String param = getRequestPayload(request);
    OutputStreamWriter out = null;
    BufferedReader in = null;
    String result = "";
    try {
      ignoreCertificates();
      URL realUrl = new URL(url);
      // 打开和URL之间的连接
      HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
      // 配置原有的请求头
      setHeader(request, connection);
      connection.setRequestMethod("PATCH");
      // Post 请求不能使用缓存
      connection.setUseCaches(false);
      // 发送POST请求必须设置如下两行
      connection.setDoOutput(true);
      connection.setDoInput(true);
      // 获取URLConnection对象对应的输出流
      out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
      // 发送请求参数
      out.write(param);
      // flush输出流的缓冲
      out.flush();

      response.setStatus(connection.getResponseCode());
      // 定义BufferedReader输入流来读取URL的响应
      if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
        in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      else
        in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
      String line;
      while ((line = in.readLine()) != null) {
        result += line;
      }
    } catch (Exception e) {
      System.out.println();
      logger.error("发送 PATCH 请求出现异常！" + e);
      e.printStackTrace();
    }
    //使用finally块来关闭输出流、输入流
    finally {
      closeStream(out, in);
    }
    return result;
  }



  /**
   * 请求格式为Request Payload 获取参数的方法
   *
   * @return 请求参数
   */
  private static String getRequestPayload(HttpServletRequest request) {
    StringBuilder sb = new StringBuilder();
    try (BufferedReader reader = request.getReader()) {
      char[] buff = new char[1024];
      int len;
      while ((len = reader.read(buff)) != -1) {
        sb.append(buff, 0, len);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return sb.toString();
  }

  /**
   * 设置连接的请求头
   *
   * @param request    前端请求
   * @param connection java与平台连接
   */
  private static void setHeader(HttpServletRequest request, HttpURLConnection connection) {
    Enumeration<String> headerNames = request.getHeaderNames();
    for (Enumeration e = headerNames; e.hasMoreElements(); ) {
      String thisName = e.nextElement().toString();
      String thisValue = request.getHeader(thisName);
      //java 的请求头Authorization会干扰平台的认证
      if (!(thisName.equals("Authorization") || thisName.equals("authorization")))
        connection.setRequestProperty(thisName, thisValue);
      else
        connection.setRequestProperty("authorization", "Bearer MzCTCllMsPpuS_c2E9ksKxPiW-twGdp183XfJ3eTFaY");
      logger.debug(thisName + "----------->" + thisValue);
    }
  }

  /**
   * 关闭输入输出流
   */
  private static void closeStream(OutputStreamWriter out, BufferedReader in) {
    if (out != null) {
      try {
        out.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    if (in != null) {
      try {
        in.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }

  /**
   * 从HttpServletRequest 中构造出请求路径
   * @param request
   * @return openshift请求路径
   */
  private static String getUrl(HttpServletRequest request){
    return new StringBuilder(OPENSHIFT_URL)
            .append(request.getRequestURI())
            .append("?")
            .append(request.getQueryString())
            .toString();
  }
}