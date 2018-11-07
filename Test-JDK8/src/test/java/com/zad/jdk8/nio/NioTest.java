package com.zad.jdk8.nio;

import com.zad.jdk8.util.PathUtil;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * 描述:
 *
 * @author zad
 * @create 2018-11-06 16:06
 */
class NioTest {
    private static void fileData(File f) {
        System.out.println(
                " 绝对路径：" + f.getAbsolutePath() +
                        "\n 可读：" + f.canRead() +
                        "\n 可写：" + f.canWrite() +
                        "\n 文件名：" + f.getName() +
                        "\n 上级目录：" + f.getParent() +
                        "\n 相对地址：" + f.getPath() +
                        "\n 长度：" + f.length() +
                        "\n 最近修改时间：" + f.lastModified()
        );
        if (f.isFile())
            System.out.println(" 是一个文件");
        else if (f.isDirectory())
            System.out.println(" 是一个目录");
    }

    @Test
    void fileTest1() {
        // 获取当前目录
        File path = new File(".");// .表示当前目录
        // 文件路径名数组
        String list[] = path.list();

        // 对String文件名进行排序
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);

        // 打印
        for (String dirItem : list)
            System.out.println(dirItem);
    }


    @Test
    void fileTest2() {
        // 获取src目录
        File file = new File("src");
        // file详细操作
        fileData(file);
    }

    @Test
    void wrapTest() {
        byte[] array = new byte[1024];
        ByteBuffer bf = ByteBuffer.wrap(array);
        for (int i = 0; i < 128; i++) {
            bf.putInt(i);
        }
        // 数据已经存入array中
        for (byte b : array) {
            System.out.println(b);
        }
    }

    @Test
    void sliceTest() {
        PathUtil.printFile();
    }
}
