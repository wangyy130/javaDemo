package com.wyy.javademo.suanfa.class04;

public class HeapSort2 {

    //堆排序，按从小到大进行排序
    public void heapSort(int[] heap){
        if(heap == null || heap.length < 2){
            return;
        }

        //先将数组调整为一个大根堆
        for(int i = 0 ; i < heap.length; i++){
            heapInsert(heap , i);
        }

        //将大根堆中第一个元素和最后一个元素进行交换
        int heapSize = heap.length;
        swap(heap, 0 , --heapSize);

        //调整之后，再将剩余数组中的元素调整为大根堆，从第0个元素开始heapify操作
        while (heapSize > 0){
            heapify(heap, 0 ,heapSize);
            swap(heap,0, --heapSize);
        }

    }

    private void heapInsert(int[] heap,int index){
        while (heap[index] > heap[(index - 1) / 2]){
            swap(heap,index,(index-1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void heapify(int[] heap, int index, int heapSize){
        int left = index * 2 + 1;
        while (left < heapSize){
            int largest = left + 1 < heapSize && heap[left + 1] > heap[left] ? left + 1 : left;
            largest = heap[index] < heap[largest] ? largest : index;

            //如果index和largest相等，那么久没有必要继续向下比较了
            if(largest == index){
                break;
            }

            swap(heap, largest,index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    private void swap(int[] arr,int i, int j){
        int temp = arr[i];
        arr[i] = arr[j] ;
        arr[j] = temp;
    }
}
