package com.xm.platform.util;

import java.util.Random;

/**
 * Created by fanshuai on 17/12/19.
 */
public class RandomUtils {
    public static int randomInt(int min,int max){
        Random r = new Random();
        if (min==max){
            return min;
        }
        if (min>max){
            return max;
        }
        int randomInt = r.nextInt(max-min);
        if (randomInt<0){
            randomInt = -randomInt;
        }
        return min+randomInt;
    }

    public static void main(String[] args){
        for (int i = 0;i<100;i++){
        System.out.println(RandomUtils.randomInt(1,3));}
    }
}
