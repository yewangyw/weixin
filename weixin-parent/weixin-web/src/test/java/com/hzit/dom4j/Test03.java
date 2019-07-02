package com.hzit.dom4j;

import org.junit.Test;

import java.util.Random;


public class Test03 {

    @Test
    public void test01(){

        String [] game ={"石头", "剪刀", "布"};
        Random random = new Random();
        //game[1],game[2],game[3]
        while (true) {
            System.out.println(game[random.nextInt(3)]);
            //System.out.println(random.nextInt(4));
        }


    }
}
