package com.zc.api;

import java.io.*;

/**
 * Created by chock on 2017/5/5.
 */
public class FileMethod {

    /**
     * 传入文件名以及字符串, 将字符串信息保存到文件中
     *
     * @param strFilename
     * @param strBuffer
     */
    public static void TextToFile(String strFilename, String strBuffer) {
        try {
            File fileText = new File(strFilename);  // 创建文件对象
            FileWriter fileWriter = new FileWriter(fileText);   // 向文件写入对象写入信息
            fileWriter.write(strBuffer);    // 写文件
            fileWriter.close(); // 关闭
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从文件中读取字符串
     *
     * @param FileName
     * @return
     * @throws Exception
     */
    public String ReadTxtFile(String FileName) throws Exception {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(FileName));
        ByteArrayOutputStream memStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = bufferedInputStream.read(buffer)) != -1) {
            memStream.write(buffer, 0, len);
        }
        byte[] data = memStream.toByteArray();
        bufferedInputStream.close();
        memStream.close();
        bufferedInputStream.close();
        return new String(data);
    }

}
