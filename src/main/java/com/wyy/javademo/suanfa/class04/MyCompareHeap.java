package com.wyy.javademo.suanfa.class04;

import java.util.*;

/**
 * 带比较器的堆，当堆中的参数发生修改时，仍然保持堆排序
 *
 * 小根堆
 */
public class MyCompareHeap<T> {

    //动态数组，可无限扩容
    private List<T> heap;
    private int heapSize;

    //用来记录类似于每个元素原来在数组中的位置，数组下标的作用
    private Map<T, Integer> indexMap;
    private Comparator< ? super  T> comparator;

    MyCompareHeap(Comparator< ? super T> myCom){
        heap = new ArrayList<>();
        heapSize = 0 ;
        comparator = myCom;
        indexMap = new HashMap<>();
    }

    //向堆中添加一个对象
    public void push(T data){
        heap.add(data);
        indexMap.put(data,heapSize);
        heapInsert(heap, heapSize++);
    }

    public void heapInsert(List<T> heap, int index){

        //当前节点值比父节点值大
        while (comparator.compare(heap.get(index),heap.get((index - 1) / 2)) < 0){
            swap(index , (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }


    /**
     * 移除一个元素
     * @return 返回移除的第一个元素
     */
    public T pop(){
        T data = heap.get(0);
        int end = heapSize - 1;
        swap(0, end);
        indexMap.remove(data);
        heap.remove(end);
        heapify(heap , 0 , --heapSize);

        return data;
    }


    public void heapify(List<T> heap , int index , int heapSize){

        int left = index * 2 + 1;

        while (left < heapSize){
            int largest = (left + 1) < heapSize && (comparator.compare(heap.get(left),heap.get(left + 1) ) > 0) ?  left + 1 : left ;
            largest = comparator.compare(heap.get(index) , heap.get(largest)) > 0 ? largest : index;
            if(largest == index){
                break;
            }
            swap(index , largest);
            index = largest;
            left = index * 2 + 1;
        }

    }

    // 修改某个对象的值
    public void resign(T data){
        int index = indexMap.get(data);
        heapInsert(heap, index);
        heapify(heap , index , heapSize);
    }



    public void swap(int i, int j){
        T dataI = heap.get(i) ;
        T dataJ = heap.get(j);
        heap.set(j,dataI);
        heap.set(i,dataJ);
        indexMap.put(dataI,j);
        indexMap.put(dataJ,i);
    }
}
