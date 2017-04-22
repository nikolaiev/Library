package com;

import java.util.HashMap;

/**
 * Created by vlad on 21.04.17.
 */
public class Test {
    @org.junit.Test
    public void method(){
        Integer a=1;
        Integer b=new Integer(1);
        System.out.println(a==b);
        a+=1;
        b+=1;
        System.out.println(a==b);


    }

}
