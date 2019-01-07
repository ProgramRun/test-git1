package com.zad.jdk8.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import org.apache.commons.lang3.StringUtils;

/**
 * 描述:
 * 汉字转汉语拼音util
 *
 * @author zad
 * @create 2018-11-01 12:25
 */
public final class PinYinUtil {
    /**
     * 汉语拼音format
     */
    private static final HanyuPinyinOutputFormat PINYIN_OUTPUT_FORMAT = new HanyuPinyinOutputFormat();

    static {
        PINYIN_OUTPUT_FORMAT.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        PINYIN_OUTPUT_FORMAT.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        PINYIN_OUTPUT_FORMAT.setVCharType(HanyuPinyinVCharType.WITH_V);
    }

    private PinYinUtil() {
        throw new AssertionError("Util禁止反射实例化");
    }

    /**
     * 将汉字转为拼音,字母直接拼接,其余字符直接忽略
     *
     * @param chineseCharacter
     * @return
     */
    public static String getPinYin(String chineseCharacter) {
        if (StringUtils.isBlank(chineseCharacter)) {
            return StringUtils.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (char cc : chineseCharacter.toCharArray()) {
            // 无法识别的汉字跳过
            String[] res = getPinYinStringArray(cc);
            boolean flag = (res != null) && isChineseCharacter(StringUtils.EMPTY + cc);
            if (flag) {
                sb.append(res[0]);
                continue;
            }
            if (isCharacterOrDigits(StringUtils.EMPTY + cc)) {
                sb.append(cc);
            }
        }
        return sb.toString();
    }

    /**
     * 得到中文首字母（包括字符串中字母）,字母直接拼接,其余字符直接忽略
     *
     * @param chineseCharacter
     * @return
     */
    public static String getPinYinHeadChar(String chineseCharacter) {
        if (StringUtils.isBlank(chineseCharacter)) {
            return StringUtils.EMPTY;
        }
        StringBuilder sb = new StringBuilder(chineseCharacter.length());
        for (char cc : chineseCharacter.toCharArray()) {
            // 无法识别的汉字跳过
            String[] res = getPinYinStringArray(cc);
            boolean flag = (res != null) && isChineseCharacter(StringUtils.EMPTY + cc);
            if (flag) {
                sb.append(res[0].charAt(0));
                continue;
            }
            if (isCharacterOrDigits(StringUtils.EMPTY + cc)) {
                sb.append(cc);
            }
        }
        return sb.toString();
    }

    /**
     * 将字符串转移为ASCII码
     *
     * @param cnStr
     * @return
     */
    public static String getCnASCII(String cnStr) {
        byte[] bGBK = cnStr.getBytes();
        StringBuilder strBuf = new StringBuilder(bGBK.length);
        for (int i = 0; i < bGBK.length; i++) {
            strBuf.append(String.format("%02x", bGBK[i]));
        }
        return strBuf.toString();
    }

    /**
     * 将汉字转换为拼音
     *
     * @param cc
     * @return
     */
    private static String[] getPinYinStringArray(char cc) {
        return PinyinHelper.toHanyuPinyinStringArray(cc);
    }

    /**
     * 是否为汉字
     *
     * @param input
     * @return
     */
    private static boolean isChineseCharacter(CharSequence input) {
        return RegexEnum.CHINESE_CHARACTER_PATTERN.getPattern().matcher(input).matches();
    }

    /**
     * 是否为字母或数字
     *
     * @param input
     * @return
     */
    private static boolean isCharacterOrDigits(CharSequence input) {
        return RegexEnum.CHARACTER_DIGITS_PATTERN.getPattern().matcher(input).matches();
    }
}
