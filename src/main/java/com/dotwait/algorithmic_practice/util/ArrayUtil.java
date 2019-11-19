package com.dotwait.algorithmic_practice.util;

import java.util.Random;

public class ArrayUtil {
    private static final Random random = new Random();

    public static int[] randomArray(int num) {
        int[] array = new int[num];
        for (int i = 0; i < num; i++) {
            array[i] = random.nextInt();
        }
        return array;
    }

    public static boolean isAscending(final int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
