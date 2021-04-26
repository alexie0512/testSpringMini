package com.testSpringMini.demo.util;

import io.swagger.models.auth.In;

import java.io.*;

/**
 * 文件读取工具类
 *
 * @author Alexie on 2021/4/26
 * @ClassName FileUtil
 * @Description TODO
 * @Version 1.0
 */
public class FileUtil {


    public static String getText(InputStream inputStream){
        InputStreamReader inputStreamReader=null;
        BufferedReader bufferedReader=null;

        try {
            inputStreamReader=new InputStreamReader(inputStream,"utf-8");
            bufferedReader=new BufferedReader(inputStreamReader);
            StringBuilder sb=new StringBuilder();
            String s;
            while((s=bufferedReader.readLine())!=null){
                s=s+"\n";
                sb.append(s);
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(inputStreamReader!=null){
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }




    static class MyFileNameFilter implements FilenameFilter{

        @Override
        public boolean accept(File dir, String name) {
            //File dir 代表父目录，name 代表直接子的名字是文件名or目录名
            //log.info(dir.getAbsolutePath()+"..."+name);
            return name.endsWith(".java");
        }
    }
}
