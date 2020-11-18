package com.wyy.javademo.suanfa.class04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;


//大根堆
public class MyComHeap<T> {
     List<T> heap;

     int heapSize;

     HashMap<T,Integer> indexMap;

    private Comparator<T> comparator;

    MyComHeap(Comparator<T> mycom){
        heap = new ArrayList<>();
        heapSize = 0 ;
        indexMap = new HashMap<>();
        comparator = mycom;
    }


    private void push(T value){
        heap.add(value);
        indexMap.put(value,heapSize);
        heapInsert(heapSize++);
    }


    private T pop(){
        T data = heap.get(0);
        int end = heapSize - 1;
        swap(0, end);
        heap.remove(end);
        indexMap.remove(data);
        heapify(0, --heapSize);
        return data;
    }


    private void heapInsert(int i){
        while (comparator.compare(heap.get((i - 1) / 2), heap.get(i) ) < 0  ){
            swap(i, (i - 1) / 2);
            i = (i  - 1 )/ 2;
        }
    }


    private void heapify(int index, int heapSize){
        int left = index * 2 + 1;
        while (left < heapSize){
            int largest = left + 1 < heapSize && comparator.compare(heap.get(left),heap.get(left + 1) ) < 0 ? left + 1 : left;
            largest = comparator.compare(heap.get(largest),heap.get(index)) > 0 ? largest : index;
            if(largest == index){
                break;
            }

            swap(largest,index);
            index = largest;
            left = index * 2 + 1;
        }
    }


    private void swap(int i,int j){
        T dataI = heap.get(i);
        T dataJ = heap.get(j);
        heap.set(i,dataJ);
        heap.set(j,dataI);
        indexMap.put(dataI,j);
        indexMap.put(dataJ,i);

    }



}
