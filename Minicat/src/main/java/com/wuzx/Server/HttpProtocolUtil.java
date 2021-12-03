package com.wuzx.Server;

/**
 * @author wuzhixuan
 * @version 1.0.0
 * @ClassName HttpProtocolUtil.java
 * @Description http协议⼯具类，主要是提供响应头信息，这⾥我们只提供200和404的情况
 * @createTime 2021年12月03日 11:11:00
 */
public class HttpProtocolUtil {


    /**
     * 为响应码200提供请求头信息
     *
     * @param content
     * @return
     */
    public static String getHttpHeader200(String content) {
        return getHttpHeader200(content.getBytes().length) + content;
    }


    public static String getHttpHeader200(long contentLength) {
        return "HTTP/1.1 200 OK \n" +
                "Content-Type: text/html \n" +
                "Content-Length: " + contentLength + " \n" + "\r\n";
    }



    /**
     * 为响应码404提供请求头信息(此处也包含了数据内容)
     *
     * @return
     */
    public static String getHttpHeader404() {
        String str404 = "<h1>404 not found</h1>";
        return "HTTP/1.1 404 NOT Found \n" +
                "Content-Type: text/html \n" +
                "Content-Length: " + str404.getBytes().length + " \n" +
                "\r\n" + str404;
    }
}
