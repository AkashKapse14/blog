package com.blog.blog.java8;

import java.util.Arrays;
import java.util.List;

public class FindOutEvenNumber {
    public static void main(String[] args) {

        List<Integer> list= Arrays.asList(1,2,4,8,3,8,9,4,14,6,4);
//        list.stream().filter(i->i%2==0).forEach(i->{
//            System.out.println(i);
//        });

      //  list.stream().filter(i->i%2==0).forEach(System.out::println);

        list.stream().map(i->i*2).filter(i->i>15).forEach(System.out::println);
    }
}
