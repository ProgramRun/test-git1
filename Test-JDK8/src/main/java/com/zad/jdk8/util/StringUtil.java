package com.zad.jdk8.util;

import com.google.common.base.Strings;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

/**
 * 描述:
 *
 * @author zad
 * @create 2019-07-16 19:32
 */
public final class StringUtil {

	public StringUtil() {
		throw new AssertionError("Util禁止反射实例化");
	}

	public static boolean isBlank(final CharSequence cs) {
		return StringUtils.isBlank(cs);
	}

	public static boolean isNotBlank(final CharSequence cs) {
		return !isBlank(cs);
	}

	public static boolean isEmpty(final CharSequence cs) {
		return StringUtils.isEmpty(cs);
	}

	public static boolean isNotEmpty(final CharSequence cs) {
		return !isEmpty(cs);
	}

	public static boolean isAnyBlank(final CharSequence... css) {
		return StringUtils.isAnyBlank(css);
	}

	public static boolean isAllBlank(final CharSequence... css) {
		return StringUtils.isAllBlank(css);
	}

	/**
	 * none of the css is blank
	 *
	 * @param css
	 * @return
	 */
	public static boolean noneBlank(final CharSequence... css) {
		if (ArrayUtils.isEmpty(css)) {
			return false;
		}
		for (final CharSequence cs : css) {
			if (isBlank(cs)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isAnyEmpty(final CharSequence... css) {
		return StringUtils.isAnyEmpty(css);
	}


	public static boolean isAllEmpty(final CharSequence... css) {
		return StringUtils.isAnyEmpty(css);
	}

	/**
	 * none of the css is empty
	 *
	 * @param css
	 * @return
	 */
	public static boolean noneEmpty(final CharSequence... css) {
		if (ArrayUtils.isEmpty(css)) {
			return false;
		}
		for (final CharSequence cs : css) {
			if (isEmpty(cs)) {
				return false;
			}
		}
		return true;
	}

	public static String padStart(String string, int minLength, char padChar) {
		return Strings.padStart(string, minLength, padChar);
	}

	public static String padEnd(String string, int minLength, char padChar) {
		return Strings.padEnd(string, minLength, padChar);
	}

	public static boolean contains(final CharSequence cs, final char... searchChars) {
		return StringUtils.containsAny(cs, searchChars);
	}

	public static boolean isChinese(final CharSequence cs) {
		return isAllUnicodeScript(cs, Character.UnicodeScript.HAN);
	}

	public static boolean containsChinese(final CharSequence cs) {
		return containsUnicodeScript(cs, Character.UnicodeScript.HAN);
	}

	public static boolean isJapanese(final CharSequence cs) {
		return isAllUnicodeScript(cs, Character.UnicodeScript.HAN);
	}

	public static boolean containsJapanese(final CharSequence cs) {
		return containsUnicodeScript(cs, Character.UnicodeScript.JAVANESE);
	}

	private static boolean isAllUnicodeScript(final CharSequence cs, Character.UnicodeScript unicodeScript) {
		if (isBlank(cs)) {
			return false;
		}
		int csLength = cs.length();
		boolean isChinese = true;
		for (int i = 0; i < csLength && isChinese; i++) {
			Character.UnicodeScript sc = Character.UnicodeScript.of(cs.charAt(i));
			isChinese = sc == unicodeScript;
		}
		return isChinese;
	}


	private static boolean containsUnicodeScript(final CharSequence cs, Character.UnicodeScript unicodeScript) {
		if (isBlank(cs)) {
			return false;
		}
		int length = cs.length();
		boolean isChinese = false;
		for (int i = 0; i < length && !isChinese; i++) {
			Character.UnicodeScript sc = Character.UnicodeScript.of(cs.charAt(i));
			isChinese = sc == unicodeScript;
		}
		return isChinese;
	}

	public static boolean isChinesePunctuation(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS
				|| ub == Character.UnicodeBlock.VERTICAL_FORMS) {
			return true;
		} else {
			return false;
		}
	}


	public static boolean isNumeric(final CharSequence cs) {
		return StringUtils.isNumeric(cs);
	}

	public static boolean containsNumber(final CharSequence cs) {
		return containsBetween(cs, '0', '9');
	}

	public static boolean containsBetween(final CharSequence cs, char startChar, char endChar) {
		if (isBlank(cs)) {
			return false;
		}
		char start = startChar > endChar ? endChar : startChar;
		char end = startChar > endChar ? startChar : endChar;

		int length = cs.length();
		boolean isBetween = false;
		for (int i = 0; i < length && !isBetween; i++) {
			char cn = cs.charAt(i);
			isBetween = start <= cs.charAt(i) && cn <= end;
		}
		return isBetween;
	}

	public static void main(String[] args) {
		System.out.println("zad_1212321".toUpperCase(Locale.US));
	}

}
