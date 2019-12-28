package com.datastructures.streamVsDatastructure;

import java.util.Random;

public class ExampleString {

    public String methodA(String str){
        Random rnd = new Random();
        char c = (char) (rnd.nextInt(26) + 'a');
        String newString = str.replace('?',c);
        return newString;
    }
    public String methodB(String str){
        Random rnd = new Random();
        char b = Integer.toString(rnd.nextInt((9-0)+1)+0).charAt(0);
        System.out.println(b);
        String newString2 = str.replace('#',b);
        return newString2;
    }
    public String methodC(String str){

        Random rnd = new Random();
        char b = Integer.toString(rnd.nextInt((9-0)+1)+0).charAt(0);
        char c = (char) (rnd.nextInt(26) + 'a');
        String newString = str.replace('?',c);
        String newString2 = newString .replace('#',b);
        return newString2;
    }

    public static void main(String[] args) {
        ExampleString ex = new ExampleString();
        System.out.println(ex.methodA("zxczxc?as?asdsA?saS?d"));
        System.out.println(ex.methodB("asdsa#asd#asdasd#"));
        System.out.println(ex.methodC("fds?dsf?#SD?SDF#fg"));
    }
}
