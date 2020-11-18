package com.wyy.javademo.suanfa.class04;

/**
 * 堆结构
 * 1、堆是利用数组实现的完全二叉树结构
 */
public class MyHeap {

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 大根堆
     */
    public static class BigHeap{
        //定义一个堆的数组
        public int[] heap;
        public  int limit; //堆中存放数据的最大值
        public  int heapSize; //记录当前堆中数据量

        BigHeap(int limit){
            heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }

        /**
         *向堆中插入数据
         */
        public void push(int data){

            if(heapSize > limit){
                throw new RuntimeException("堆满了。");
            }

            heap[heapSize] = data; //将data放入heapSize的位置，并且heapSize+1

            heapInsert(heap , heapSize);

            heapSize++;
        }


        /**
         *向堆数组中插入数据，时间复杂度为 logN
         * 因为树的高度为 logN
         */
        public void heapInsert(int[] heap, int index){
            while (heap[index] > heap[(index - 1) / 2]){
                swap(heap,index,(index -1) / 2);
                index = (index - 1) / 2;
            }
        }


        /**
         * 从大根堆中取出最大值返回，并且将该最大值移除之后，保证剩下的数还是大根堆
         *
         * 注：在大根堆数组中，第0个位置上的数，就是堆根，也就是最大的值
         */
        public int popMax(){

            if(heapSize == 0){
                throw new RuntimeException("堆空啦");
            }

            int res = heap[0];

            //将数组中最后一个数与0位置的数进行交换
            swap(heap, 0 , --heapSize);

            //我们现在数组中只取交换后最后一个数字之前的数据作为新的数组。
            //让交换后的第一个数一次与它的孩子节点进行比较并交换，直到遍历完成
            heapify(heap, 0 ,heapSize);
            return res;
        }


        /**
         *从第一个数开始一个一个向下沉，跟做左孩子和右孩子 进行比较交换，
         *
         * 时间复杂度也是logN
         */
        public void heapify(int[] heap, int index, int heapSize){

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

    }


    /**
     * 小根堆
     */
    public static class smallHeap{

        public int[] heap;
        public int limit;
        public int heapSize;

        public void push(int data){
            if(heapSize == limit){
                throw new RuntimeException("堆已经满了");
            }
            heap[heapSize] = data;

            heapInsert(heap ,heapSize);

            heapSize++;

        }


        public void heapInsert(int[] heap, int index ){

            while ( heap[index] < heap[((index - 1) / 2)]){
                swap(heap, index ,(index -1) / 2);
                index = (index - 1) / 2;
            }

        }


        /**
         * 弹出最小的值，并且保证剩下的数仍为小根堆
         * @return
         */
        public int popMin(){
            int min = heap[0];

            //将第一个数和数组的最后一个数进行交换
            swap(heap , 0 , --heapSize);

            //依次跟他的孩子节点进行比较
            heapify(heap , 0 ,heapSize);
            return min;
        }


        public void heapify(int[] heap, int index ,int heapSize){

            int left = index * 2 +1;
            while (left < heapSize){

                int largest = (left + 1) < heapSize && heap[left + 1] > heap[left] ? left + 1 : left;

                largest = heap[largest] > heap[index] ? largest : index;

                if(largest == index){
                    break;
                }

                swap(heap , index ,largest);
                index = largest;
                left = 2 * index + 1;
            }

        }



    }

}
