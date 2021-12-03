package com.wuzx.Server;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author wuzhixuan
 * @version 1.0.0
 * @ClassName Request.java
 * @Description  把请求信息封装为Request对象（根据InputSteam输⼊流封装）
 * @createTime 2021年12月03日 11:25:00
 */
public class Request {

    private String method; // 请求⽅式，⽐如GET/POST
    private String url; // 例如 /,/index.html
    private InputStream inputStream; // 输⼊流，其他属性从输⼊流中解析出来


    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Request() {
    }

    /**
     * 构造器，输⼊流传⼊
     * @param inputStream
     * @throws IOException
     */
    public Request(InputStream inputStream) throws IOException {
        this.inputStream = inputStream;
        // 从输⼊流中获取请求信息
        int count = 0;
        while (count == 0) {
            count = inputStream.available();
        }
        byte[] bytes = new byte[count];
        inputStream.read(bytes);
        String inputStr = new String(bytes);
        // 获取第⼀⾏请求头信息
        String firstLineStr = inputStr.split("\\n")[0]; // GET / HTTP/1.1
        String[] strings = firstLineStr.split(" ");
        this.method = strings[0];
        this.url = strings[1];
        System.out.println("=====>>method:" + method);
        System.out.println("=====>>url:" + url);
    }
 }
