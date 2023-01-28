package com.solution.recipetalk.util;

import java.util.Random;

public class RandomNumberProvider {

    public static String getRandNum(int size) {
        Random random = new Random();

        int createNum = 0;
        int letter = size;

        String resultNum = "";

        for (int i = 0; i < letter; i++) {
            createNum = random.nextInt(9);

            resultNum += Integer.toString(createNum);
        }

        return resultNum;
    }
}
