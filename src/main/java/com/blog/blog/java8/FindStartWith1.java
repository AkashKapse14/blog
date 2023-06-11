package com.blog.blog.java8;

import java.util.Arrays;
import java.util.List;

public class FindStartWith1 {
    public static void main(String[] args) {

        List<Integer> list= Arrays.asList(10,20,50,1,78,14,160);

      //  list.stream().map(i->i+"").filter(i->i.startsWith("1")).forEach(System.out::println);

        list.stream().map(i->i*2).filter(i->i>50).forEach(p->{
            System.out.println(p);
        });
    }
}
