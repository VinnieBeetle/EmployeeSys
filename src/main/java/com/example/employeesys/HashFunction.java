package com.example.employeesys;

import java.util.Arrays;

public class HashFunction {

    //for HashTable
    String[] hashArray;
    int arraySize;
    int itemsInArray;

    public void hashFunction1(String[] stringsForArray, String[] hashArray){

        for(int n = 0; n < stringsForArray.length; n++){

            String newElementVal = stringsForArray[n];

            hashArray[Integer.parseInt(newElementVal)]= newElementVal;

        }
    }

    public HashFunction(int size){

        arraySize = size;
        hashArray = new String[size];
        Arrays.fill(hashArray,"-1");

    }
}
