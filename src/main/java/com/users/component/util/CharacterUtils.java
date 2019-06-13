package com.users.component.util;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * 字符串工具类
 */
@Component
public class CharacterUtils {

    /**
     * 随机生成num个字符串
     * 
     * @param num
     * @return
     */
    public String RandomChaeacter(int num) {
        String str = "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        // int num = random.nextInt(10);
        for (int i = 0; i < num; ++i) {
            int number = random.nextInt(str.length());
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 随机生成num个随机串
     * 
     * @param num
     * @return 随机字符串
     */
    public String getRandomChaeacter(int num) {
        String str = "1234567890";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        // int length = random.nextInt(10);
        for (int i = 0; i < num; ++i) {
            int number = random.nextInt(str.length());
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 随机生成num个随机串（小写字母+数字）
     *
     * @param num
     * @return
     */
    public String getRandomLowercaseNum(int num) {
        String str = "zxcvbnmlkjhgfdsaqwertyuiop1234567890";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        // int length = random.nextInt(10);
        for (int i = 0; i < num; ++i) {
            int number = random.nextInt(str.length());
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

}
