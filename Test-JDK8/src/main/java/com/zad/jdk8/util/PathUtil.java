package com.zad.jdk8.util;

import sun.security.action.GetPropertyAction;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.security.AccessController;
import java.util.Arrays;

/**
 * 描述:
 * 获取文件路径
 *
 * @author zad
 * @create 2018-11-07 15:13
 */
public final class PathUtil {

    public static String getProjectPath() {

        URL url = PathUtil.class.getProtectionDomain().getCodeSource().getLocation();

        String filePath = null;

        try {
            filePath = java.net.URLDecoder.decode(url.getPath(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (filePath.endsWith(".jar")) {

            filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);
        }

        File file = new File(filePath);
        filePath = file.getAbsolutePath();

        return filePath;

    }


    public static String getRealPath() {

        String realPath = PathUtil.class.getClassLoader().getResource("").getFile();

        File file = new File(realPath);
        realPath = file.getAbsolutePath();

        try {

            realPath = java.net.URLDecoder.decode(realPath, "utf-8");

        } catch (Exception e) {

            e.printStackTrace();

        }


        return realPath;

    }


    public static String getAppPath(Class<?> cls) {

        //检查用户传入的参数是否为空

        if (cls == null) {
            throw new IllegalArgumentException("参数不能为空！");
        }


        ClassLoader loader = cls.getClassLoader();

        //获得类的全名，包括包名

        String clsName = cls.getName();

        //此处简单判定是否是Java基础类库，防止用户传入JDK内置的类库

        if (clsName.startsWith("java.") || clsName.startsWith("javax.")) {
            throw new java.lang.IllegalArgumentException("不要传送系统类！");
        }

        //将类的class文件全名改为路径形式

        String clsPath = clsName.replace(".", "/") + ".class";


        //调用ClassLoader的getResource方法，传入包含路径信息的类文件名

        URL url = loader.getResource(clsPath);

        //从URL对象中获取路径信息

        String realPath = url.getPath();

        //去掉路径信息中的协议名"file:"

        int pos = realPath.indexOf("file:");

        if (pos > -1) {
            realPath = realPath.substring(pos + 5);
        }

        //去掉路径信息最后包含类文件信息的部分，得到类所在的路径

        pos = realPath.indexOf(clsPath);
        realPath = realPath.substring(0, pos - 1);

        //如果类文件被打包到JAR等文件中时，去掉对应的JAR等打包文件名

        if (realPath.endsWith("!")) {
            realPath = realPath.substring(0, realPath.lastIndexOf("/"));
        }

        File file = new File(realPath);
        realPath = file.getAbsolutePath();


        try {
            realPath = URLDecoder.decode(realPath, "utf-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return realPath;

    }//getAppPath定义结束

    public static void printFile() {
        // 获取当前目录
        File path = new File(".");
        // 文件路径名数组
        String list[] = path.list();

        // 对String文件名进行排序
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);

        // 打印
        for (String dirItem : list) {
            System.out.println(dirItem);
        }
    }

    public static void main(String[] args) {
        /*System.out.println(getProjectPath());
        System.out.println(getRealPath());
        System.out.println(getRealPath());
        printFile();*/
        char slash;
        char semicolon;
        char altSlash;
        slash = AccessController.doPrivileged(
                new GetPropertyAction("file.separator")).charAt(0);
        semicolon = AccessController.doPrivileged(
                new GetPropertyAction("path.separator")).charAt(0);
        altSlash = (slash == '\\') ? '/' : '\\';

        System.out.println(slash);
        System.out.println(semicolon);
        System.out.println(altSlash);
    }
}
