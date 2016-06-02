package com.company;

import java.util.ListIterator;

public class Main {

    public static void main(String[] args) {
	    MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        for (Integer i : list){
            System.out.print(i + " ");
        }
        list.remove(2);
        System.out.println();
        for (Integer i : list){
            System.out.print(i + " ");
        }
        list.remove(new Integer(2));
        System.out.println();
        for (Integer i : list){
            System.out.print(i + " ");
        }
        ListIterator<Integer> it = list.listIterator();
        System.out.println();
        Integer tmp = it.next();
        it.add(299);
        for (Integer i : list){
            System.out.print(i + " ");
        }
    }
}
