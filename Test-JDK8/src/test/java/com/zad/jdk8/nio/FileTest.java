package com.zad.jdk8.nio;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 *
 * @author zad
 * @create 2018-11-06 8:05
 */
class FileTest {

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
    void test1() {
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
    void t2() {
        Map<Integer, Map<String, Integer>> map = new HashMap<>();

        Map<String, Integer> m1 = new HashMap<>();
        m1.put("age1", 1);
        map.put(1, m1);
        System.out.println(map.get(1));

        Map<String, Integer> m2 = m1;
        m2.put("age2", 2);
        System.out.println(map.get(1));
    }

    @Test
    void t3() {
        long oneDaySeconds = 3600 * 24L;
        long other = (3600 << 4) + (3600 << 3);
        System.out.println(oneDaySeconds);
        System.out.println(other);
    }
}
