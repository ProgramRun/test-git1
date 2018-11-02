package com.zad.JDK8.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * 描述:
 * 汉字转韩语拼音util
 *
 * @author zad
 * @create 2018-11-01 12:25
 */
public class PinYinUtil {
    /**
     * 汉语拼音format
     */
    private static final HanyuPinyinOutputFormat hanyuPinyinOutputFormat = new HanyuPinyinOutputFormat();

    /**
     * 汉字对应正则表达式 pattern
     */
    private static final Pattern chineseCharacterPattern = Pattern.compile("[\\u4E00-\\u9FA5]");

    static {
        hanyuPinyinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        hanyuPinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        hanyuPinyinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
    }

    private PinYinUtil() {
        throw new AssertionError("Util禁止外部实例化");
    }

    /**
     * 得到 全拼
     *
     * @param chineseCharacter
     * @return
     */
    public static String getPingYin(String chineseCharacter) {
        char[] req = chineseCharacter.toCharArray();
        int size = req.length;
        StringBuilder sb = new StringBuilder();
        try {
            for (int i = 0; i < size; i++) {
                // 判断是否为汉字字符
                if (chineseCharacterPattern.matcher(String.valueOf(req[i])).matches()) {
                    sb.append(PinyinHelper.toHanyuPinyinStringArray(req[i], hanyuPinyinOutputFormat)[0]);
                } else {
                    sb.append(Character.toString(req[i]));
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e1) {
            e1.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 得到中文首字母（包括字符串中字母）
     *
     * @param chineseCharacter
     * @return
     */
    public static String getPinYinHeadChar(String chineseCharacter) {
        StringBuilder res = new StringBuilder();
        int size = chineseCharacter.length();
        for (int j = 0; j < size; j++) {
            char word = chineseCharacter.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (ArrayUtils.isNotEmpty(pinyinArray)) {
                res.append(pinyinArray[0].charAt(0));
            } else {
                res.append(word);
            }
        }
        return res.toString();
    }

    /**
     * 将字符串转移为ASCII码
     *
     * @param cnStr
     * @return
     */
    public static String getCnASCII(String cnStr) {
        StringBuffer strBuf = new StringBuffer();
        byte[] bGBK = cnStr.getBytes();
        for (int i = 0; i < bGBK.length; i++) {
            // System.out.println(Integer.toHexString(bGBK[i]&0xff));
            strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
        }
        return strBuf.toString();
    }

    public static void main(String[] args) {
        String cnStr = "中华人民共和国";
        System.out.println(getPingYin(cnStr));
        System.out.println(getPinYinHeadChar(cnStr).replaceAll("[^a-zA-Z]", ""));
        String str = getPinYinHeadChar(cnStr).replaceAll("[^a-zA-Z]", "");
        //判断是否有首字母
        if (StringUtils.isNoneBlank(str)) {
            //转换为大写
            str = str.toUpperCase();
        }
        System.out.println(str);
        if (StringUtils.isNoneBlank(str)) {
            //转换为小写
            str = str.substring(0, 1).toLowerCase();
        }
        System.out.println(str);
    }

}
