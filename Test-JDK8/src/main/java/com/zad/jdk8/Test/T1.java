package com.zad.jdk8.Test;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * 描述:
 *
 * @author zad
 * @create 2019-04-12 9:51
 */
public class T1 {
    public static void main(String[] args) throws IOException {
        /*scannerTest();
        readerTest();*/
        System.out.println(NumberUtils.isDigits("1.23"));
        System.out.println(NumberUtils.isDigits("+1.23"));
        System.out.println(NumberUtils.isDigits("-1.23"));


        System.out.println(NumberUtils.isCreatable("1.23"));
        System.out.println(NumberUtils.isCreatable("+1.23"));
        System.out.println(NumberUtils.isCreatable("-1.23"));


        System.out.println(StringUtils.isNumeric("1.23"));
        System.out.println(StringUtils.isNumeric("+1.23"));
        System.out.println(StringUtils.isNumeric("-1.23"));
    }

    static void scannerTest() {
        Scanner scn = new Scanner(System.in);
       /* System.out.println("Enter an integer");
        int a = scn.nextInt();*/
        System.out.println("Enter a String");
        String b = scn.nextLine();
        System.out.printf("You have entered:- "
                + 13 + " " + "and name as " + b);
    }

    static void readerTest() throws IOException {
        BufferedReader br = new BufferedReader(new
                InputStreamReader(System.in));
        System.out.println("Enter an integer");
        int a = Integer.parseInt(br.readLine());
        System.out.println("Enter a String");
        String b = br.readLine();
        System.out.printf("You have entered:- " + a +
                " and name as " + b);
    }

}
