package com.galio.generator.utils;

import java.util.Scanner;

public class ConsoleUtil {
    /**
     * 发送提示消息，并读取一行返回值
     *
     * @param info
     * @return
     */
    public static String readConsole(String info) {
        Scanner sc = new Scanner(System.in);
        System.out.println(info);
        String line = sc.nextLine();  //读取字符串型输入
        return line;
    }
}
