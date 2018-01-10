package com.xm.platform.util;

import org.joda.time.DateTime;

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
    public static BigDecimal speed(float stander,String dataDate,int pointNum){
        return speed(stander,dataDate,pointNum,0.03f);
    }
    public static BigDecimal speed(float stander,String dataDate,int pointNum,float unitRate){
        String date[] = dataDate.split(":");
        String m = date[0];
        String s = date[1];
        int mou =  Integer.parseInt(m)%3;
        int sec = Integer.parseInt(s);
        // 3 12  36
        BigDecimal rate = new BigDecimal("0");
        BigDecimal r = new BigDecimal(String.valueOf(unitRate));
        if (mou==0){
            switch (sec){
                case 0:{
                    rate = new BigDecimal("1.0").add(r.multiply(new BigDecimal("0")));break;
                }
                case 5:{
                    rate = new BigDecimal("1.0").add(r.multiply(new BigDecimal("1")));break;
                }
                case 10:{
                    rate = new BigDecimal("1.0").add(r.multiply(new BigDecimal("2")));break;
                }
                case 15:{
                    rate = new BigDecimal("1.0").add(r.multiply(new BigDecimal("3")));break;
                }
                case 20:{
                    rate = new BigDecimal("1.0").add(r.multiply(new BigDecimal("4")));break;
                }
                case 25:{
                    rate = new BigDecimal("1.0").add(r.multiply(new BigDecimal("5")));break;
                }
                case 30:{
                    rate = new BigDecimal("1.0").add(r.multiply(new BigDecimal("6")));break;
                }
                case 35:{
                    rate = new BigDecimal("1.0").add(r.multiply(new BigDecimal("7")));break;
                }
                case 40:{
                    rate = new BigDecimal("1.0").add(r.multiply(new BigDecimal("8")));break;
                }
                case 45:{
                    rate = new BigDecimal("1.0").add(r.multiply(new BigDecimal("8")));break;
                }
                case 50:{
                    rate = new BigDecimal("1.0").add(r.multiply(new BigDecimal("7")));break;
                }
                case 55:{
                    rate = new BigDecimal("1.0").add(r.multiply(new BigDecimal("6")));break;
                }
            }
        }else if (mou==1){
            switch (sec){
                case 0:{
                    rate = new BigDecimal("1.0").add(r.multiply(new BigDecimal("5")));break;
                }
                case 5:{
                    rate = new BigDecimal("1.0").add(r.multiply(new BigDecimal("4")));break;
                }
                case 10:{
                    rate = new BigDecimal("1.0").add(r.multiply(new BigDecimal("3")));break;
                }
                case 15:{
                    rate = new BigDecimal("1.0").add(r.multiply(new BigDecimal("2")));break;
                }
                case 20:{
                    rate = new BigDecimal("1.0").add(r.multiply(new BigDecimal("1")));break;
                }
                case 25:{
                    rate = new BigDecimal("1.0").add(r.multiply(new BigDecimal("0")));break;
                }
                case 30:{
                    rate = new BigDecimal("1.0").add(r.multiply(new BigDecimal("0")));break;
                }
                case 35:{
                    rate = new BigDecimal("1.0").add(r.multiply(new BigDecimal("-1")));break;
                }
                case 40:{
                    rate = new BigDecimal("1.0").add(r.multiply(new BigDecimal("-2")));break;
                }
                case 45:{
                    rate = new BigDecimal("1.0").add(r.multiply(new BigDecimal("-3")));break;
                }
                case 50:{
                    rate = new BigDecimal("1.0").add(r.multiply(new BigDecimal("-4")));break;
                }
                case 55:{
                    rate = new BigDecimal("1.0").add(r.multiply(new BigDecimal("-5")));break;
                }
            }
        }else if (mou==2){
            switch (sec){
                case 0:{
                    rate = new BigDecimal("1.0").add(r.multiply(new BigDecimal("-6")));break;
                }
                case 5:{
                    rate = new BigDecimal("1.0").add(r.multiply(new BigDecimal("-7")));break;
                }
                case 10:{
                    rate = new BigDecimal("1.0").add(r.multiply(new BigDecimal("-8")));break;
                }
                case 15:{
                    rate = new BigDecimal("1.0").add(r.multiply(new BigDecimal("-8")));break;
                }
                case 20:{
                    rate = new BigDecimal("1.0").add(r.multiply(new BigDecimal("-7")));break;
                }
                case 25:{
                    rate = new BigDecimal("1.0").add(r.multiply(new BigDecimal("-6")));break;
                }
                case 30:{
                    rate = new BigDecimal("1.0").add(r.multiply(new BigDecimal("-5")));break;
                }
                case 35:{
                    rate = new BigDecimal("1.0").add(r.multiply(new BigDecimal("-4")));break;
                }
                case 40:{
                    rate = new BigDecimal("1.0").add(r.multiply(new BigDecimal("-3")));break;
                }
                case 45:{
                    rate = new BigDecimal("1.0").add(r.multiply(new BigDecimal("-2")));break;
                }
                case 50:{
                    rate = new BigDecimal("1.0").add(r.multiply(new BigDecimal("-1")));break;
                }
                case 55:{
                    rate = new BigDecimal("1.0").add(r.multiply(new BigDecimal("0")));break;
                }
            }
        }
        return new BigDecimal(String.valueOf(stander)).multiply(rate).setScale(pointNum,BigDecimal.ROUND_HALF_UP);
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
