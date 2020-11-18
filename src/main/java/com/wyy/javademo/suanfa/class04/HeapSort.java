package com.wyy.javademo.suanfa.class04;

/**
 * 堆排序
 *
 * 1、先将给定的所有数排列成大根堆
 * 2、开始循环，每次将第一个数和数组最后一个数进行交换，并且heapIfy,直到index = 0；
 */
public class HeapSort {



    /*
    堆排序，时间复杂度 O(logN * N)
     */
    public static void heapSort(int[] heap){

        if(heap == null || heap.length < 2){
            return;
        }

        //1、先调整成大根堆
        // 时间复杂度为 O(logN * N)
        for(int i = 0 ; i < heap.length ; i++){
            heapInsert(heap , i);
        }

        //再逐个交换第一个数和最后一个数并且heapify
        int heapSize = heap.length;
        swap(heap , 0 ,--heapSize);

        //时间复杂度 O(logN * N)
        while (heapSize > 0 ){
            heapify(heap , 0 ,heapSize);
            swap(heap , 0 ,--heapSize);
        }

    }



    public static  void heapInsert(int[] heap, int index){

        while( heap[((index - 1) / 2)] < heap[index]){
            swap(heap, (index -1) / 2, index);
            index = (index - 1) / 2;
        }
    }





    /**
     *从第一个数开始一个一个向下沉，跟做左孩子和右孩子 进行比较交换，
     *
     * 时间复杂度也是logN
     */
    public  static void heapify(int[] heap, int index, int heapSize){

        int leftChildIndex = index * 2 + 1;

        while (leftChildIndex < heapSize){

            int largest = leftChildIndex + 1 < heapSize && heap[leftChildIndex + 1] > heap[leftChildIndex] ? leftChildIndex + 1 : leftChildIndex;
            largest = heap[largest] > heap[index] ? largest : index;

            if(largest == index){
                break;
            }

            //交换largest和index的数
            swap(heap, index, largest);
            index = largest;
            leftChildIndex = (leftChildIndex * 2) + 1;
        }
    }


    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
