package com.zc.api;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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


}
