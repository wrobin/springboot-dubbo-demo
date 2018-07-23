package com.wrobin.common.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

/**
 * created by robin.wu on 2018/5/18
 **/
public class FileUtil {
    public static final String SAVE_PATH = System.getProperty("user.dir") + File.separator + "tmp";

    public static File downloadFile(String url, String savePath) throws UnsupportedEncodingException {
        if(savePath == null || savePath.length() <= 0) {
            savePath = SAVE_PATH;
        }

        File file = null;
        File fileDir=new File(savePath);
        if (!fileDir.exists()){
            fileDir.mkdirs();
        }
        url = URLDecoder.decode(url,"utf-8");

        FileOutputStream fileOut;
        HttpURLConnection conn;
        InputStream inputStream;
        try{
            URL httpUrl=new URL(url);
            conn=(HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.connect();
            inputStream=conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            //判断文件的保存路径后面是否以/结尾
            if (!savePath.endsWith("/")) {
                savePath += "/";
            }
            String path = httpUrl.getPath();
            String filename = new String(path.substring(path.lastIndexOf("/") + 1).getBytes(),"utf-8");
            fileOut = new FileOutputStream(savePath + File.separator + filename);
            BufferedOutputStream bos = new BufferedOutputStream(fileOut);

            byte[] buf = new byte[4096];
            int length = bis.read(buf);
            while(length != -1){
                bos.write(buf, 0, length);
                length = bis.read(buf);
            }
            file = new File(savePath + File.separator + filename);
            bos.close();
            bis.close();
            conn.disconnect();
        } catch (Exception e){
            e.printStackTrace();
        }

        return file;
    }

    public static void newDir(String dirPath) {
        File file = new File(dirPath);
        if(!file.exists()) {
            file.mkdirs();
        }
    }

    public static boolean delDir(File dir) {
        if (!dir.exists()) return false;
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (String child : children) {
                boolean success = delDir(new File(dir, child));
                if (!success) return false;
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

}
