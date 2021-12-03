package com.wuzx.Server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author wuzhixuan
 * @version 1.0.0
 * @ClassName Response.java
 * @Description 封装Response对象，需要依赖于OutputStream
 * @createTime 2021年12月03日 11:27:00
 */
public class Response {

    private OutputStream outputStream;


    public Response() {
    }

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    /**
     * 使⽤输出流输出指定字符串
     *
     * @param content
     * @throws IOException
     */
    public void output(String content) throws IOException {
        outputStream.write(content.getBytes());
    }

    /**
     * @param path url，随后要根据url来获取到静态资源的绝对路径，进⼀步根据绝对路径
     *             读取该静态资源⽂件，最终通过
     *             输出流输出
     *             /-----> classes
     */
    public void outputHtml(String path) throws IOException {
        // 获取静态资源⽂件的绝对路径
        String absoluteResourcePath = StaticResourceUtil.getAbsolutePath(path);
        // 输⼊静态资源⽂件
        File file = new File(absoluteResourcePath);
        if (file.exists() && file.isFile()) {
            // 读取静态资源⽂件，输出静态资源
            StaticResourceUtil.outputStaticResource(new
                    FileInputStream(file), outputStream);
        } else {
            // 输出404
            output(HttpProtocolUtil.getHttpHeader404());
        }
    }
}

