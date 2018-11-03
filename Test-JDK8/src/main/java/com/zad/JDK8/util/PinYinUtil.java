package com.zad.JDK8.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.ArrayUtils;

import java.util.regex.Pattern;

/**
 * 描述:
 * 汉字转韩语拼音util
 *
 * @author zad
 * @create 2018-11-01 12:25
 */
public final class PinYinUtil {
    /**
     * 汉语拼音format
     */
    private static final HanyuPinyinOutputFormat hanyuPinyinOutputFormat = new HanyuPinyinOutputFormat();

    /**
     * 汉字对应正则表达式 pattern
     */
    private static final Pattern chineseCharacterPattern = Pattern.compile("[\\u4E00-\\u9FA5]");

    //字母对应正则
    //private static final Pattern characterPattern = Pattern.compile("[a-zA-Z]");

    static {
        hanyuPinyinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        hanyuPinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        hanyuPinyinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
    }

    private PinYinUtil() {
        throw new AssertionError("Util禁止反射实例化");
    }

    /**
     * 将chineseCharacter中汉字转为拼音,其余所有字符直接略过,
     * 若需要额外处理,则需要额外判断
     *
     * @param chineseCharacter
     * @return
     */
    public static String getPingYin(String chineseCharacter) {
        StringBuilder sb = new StringBuilder();
        try {
            for (char cc : chineseCharacter.toCharArray()) {
                if (chineseCharacterPattern.matcher(String.valueOf(cc)).matches()) {
                    sb.append(PinyinHelper.toHanyuPinyinStringArray(cc, hanyuPinyinOutputFormat)[0]);
                }
               /* // 如果是字母,直接添加
                if(characterPattern.matcher(String.valueOf(cc)).matches())){
                    sb.append(cc);
                }*/
            }
        } catch (BadHanyuPinyinOutputFormatCombination e1) {
            e1.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 将chineseCharacter中汉字转为拼音并获取首字母,其余所有字符直接略过,
     *
     * @param chineseCharacter
     * @return
     */
    public static String getPinYinInitialLetter(String chineseCharacter) {
        StringBuilder res = new StringBuilder(chineseCharacter.length());
        for (char cc : chineseCharacter.toCharArray()) {
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(cc);
            if (ArrayUtils.isNotEmpty(pinyinArray)) {
                res.append(pinyinArray[0].charAt(0));
            }
        }
        return res.toString();
    }
}
