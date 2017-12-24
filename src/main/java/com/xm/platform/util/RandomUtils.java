package com.xm.platform.util;

import java.math.BigDecimal;
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
    public static BigDecimal randomIntBigDecimal(int min,int max){
        return new BigDecimal(String.valueOf(randomInt(min,max)));
    }
    public static BigDecimal randomFloat(float min,float max){
        return randomFloat(min,max,2);
    }

    public static BigDecimal randomFloat(float min,float max,int scale){
        Random r = new Random();
        if (min==max){
            return new BigDecimal(min).setScale(scale,BigDecimal.ROUND_HALF_UP);
        }
        if (min>max){
            new BigDecimal(max).setScale(scale,BigDecimal.ROUND_HALF_UP);
        }
        return new BigDecimal(min+(max-min)*r.nextFloat()).setScale(scale,BigDecimal.ROUND_HALF_UP);
    }

    public static void main(String[] args){
        for (int i = 0;i<100;i++){
        System.out.println(RandomUtils.randomInt(1,3));}
    }
}
