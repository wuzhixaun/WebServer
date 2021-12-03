package com.wuzx.Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wuzhixuan
 * @version 1.0.0
 * @ClassName Bootstrap.java
 * @Description 服务启动类
 * @createTime 2021年12月03日 11:06:00
 */
public class Bootstrap {

    private int port = 8080;

    /**
     * 1.0版本
     */
    public void start1() throws IOException {

        // 创建服务段serverSocket
        ServerSocket serverSocket = new ServerSocket(port);


        while (true) {
            // 接收客户端链接
            final Socket client = serverSocket.accept();

            // 获取输出流
            final OutputStream outputStream = client.getOutputStream();

            String data = "Hello Minicat!";
            String responseText = HttpProtocolUtil.getHttpHeader200(data.getBytes().length) + data;
            outputStream.write(responseText.getBytes());

            // 关闭链接
            client.close();
        }

    }


    /**
     * v2.0版本
     * @throws IOException
     */
    public void start() throws IOException {
        // 创建服务段serverSocket
        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            // 接收客户端链接
            final Socket client = serverSocket.accept();

            // 获取输出流
            final OutputStream outputStream = client.getOutputStream();

            final InputStream inputStream = client.getInputStream();

            Request request = new Request(inputStream);

            Response response = new Response(outputStream);

            response.outputHtml(request.getUrl());

            // 关闭链接
            client.close();
        }





    }

    /**
     * 主启动类
     * @param args
     */
    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
