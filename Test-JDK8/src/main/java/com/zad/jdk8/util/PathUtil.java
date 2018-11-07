package com.zad.jdk8.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;

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

    }

    public static <T> void printFile(Class<T> cClass) {
        // 第一种：获取类加载的根路径
        File f = new File(cClass.getResource("/").getPath());
        System.out.println(f);

        // 获取当前类的所在工程路径; 如果不加“/”  获取当前类的加载目录
        File f2 = new File(cClass.getResource("").getPath());
        System.out.println(f2);

        // 第二种：获取项目路径
        File directory = new File("");
        String courseFile = null;
        try {
            courseFile = directory.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(courseFile);

        // 第三种：
        URL xmlpath = cClass.getClassLoader().getResource("");
        System.out.println(xmlpath);

        // 第四种：
        System.out.println(System.getProperty("user.dir"));

        // 第五种：  获取所有的类路径 包括jar包的路径
        System.out.println(System.getProperty("java.class.path"));
    }

    public static void main(String[] args) {
        printFile(PathUtil.class);
    }
}
