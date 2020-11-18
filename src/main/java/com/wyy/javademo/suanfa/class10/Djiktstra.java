package com.wyy.javademo.suanfa.class10;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 从某个给定的原点出发，分别求这个点到图中每个点的最小距离
 *
 * 两种方案
 *
 * 1、利用map记录从某个点到这个点的距离，利用set来标记是否已经遍历过的节点
 *
 * 2、利用堆的方式，需要自定义堆，因为会修改堆中的值
 */
public class Djiktstra {

    //利用第一种方式实现dijkstra
    public Map<Node,Integer> dijkstra1(Node from){
        Map<Node,Integer> distanceMap = new HashMap<>();
        Set<Node> selectedSet = new HashSet<>();

        distanceMap.put(from,0);

        Node minNode = getMinDistanceNodeFromSelected(distanceMap,selectedSet);

        while (minNode != null){
            int distance = distanceMap.get(minNode);
            for(Edge e : minNode.edges){

                Node to  = e.to;
                if(!distanceMap.containsKey(to)){
                    distanceMap.put(to,distance + e.weight);
                }else{
                    distanceMap.put(to,Math.min(distanceMap.get(to),distance + e.weight));
                }

            }
            minNode = getMinDistanceNodeFromSelected(distanceMap,selectedSet);
        }
        selectedSet.add(minNode);
        return distanceMap;

    }



    public Node getMinDistanceNodeFromSelected(Map<Node,Integer> distanceMap,
                                               Set<Node> selectedSet){
        Integer minDistance = Integer.MAX_VALUE;
        Node minNode = null;

        for(Map.Entry<Node,Integer> entry : distanceMap.entrySet()){
            Node node = entry.getKey();
            Integer distance = entry.getValue();

            if(!selectedSet.contains(node) && distance < minDistance){
                minDistance = distance;
                minNode = node;
            }
        }

        return minNode;
    }


    //堆的节点信息，按照distance进行排序
    public class NodeRecord{
        public Node node;
        public Integer distance;

        NodeRecord(Node node, int distance ){
            this.node = node;
            this.distance = distance;
        }
    }


    //节点堆
    public class NodeHeap{
        public Node[] nodeHeap;
        public int size; //堆中的元素数量
        public Map<Node,Integer> indexMap; //索引的位置信息
        public Map<Node,Integer> distanceMap;

        public NodeHeap(int limit){
            nodeHeap = new Node[limit];
            size = 0;
            indexMap = new HashMap<>();
            distanceMap = new HashMap<>();
        }


        //判断节点是否加入过堆中，加入后弹出也算加入
        public boolean isEntered(Node node){
            return indexMap.containsKey(node);
        }

        /**
         * 判断节点是否在堆中，弹出的不算
         */
        public boolean inHeap(Node node){
            return isEntered(node) && indexMap.get(node) != -1;
        }


        public void addAndUpdateAndIgnore(Node node,int distance){
            //如果当前节点现在在堆中，则进行更新操作
            if(inHeap(node)){
                distanceMap.put(node, Math.min(distanceMap.get(node),distance));
                insertHeapify(node,indexMap.get(node));
            }

            //如果当前节点没有加入过堆中，则新增节点
            if(!isEntered(node)){
                nodeHeap[size] = node;
                indexMap.put(node,size);
                distanceMap.put(node,distance);
                insertHeapify(node,size++);
            }
        }

        //在djikstra算法中，只有比当前值小才会加入小根堆中，所以加入的值只会更小，所以修改时只需要heapyInsert就好了
        public void insertHeapify(Node node,int index){
            while (distanceMap.get(nodeHeap[index])
                    < distanceMap.get(nodeHeap[(index - 1) / 2])){
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }


        //弹出一个节点，保证其余节点仍为小根堆
        public NodeRecord pop(){
            NodeRecord nodeRecord = new NodeRecord(nodeHeap[0],distanceMap.get(nodeHeap[0]));

            swap(0,size - 1);
            indexMap.put(nodeHeap[size - 1] , -1);
            distanceMap.remove(nodeHeap[size - 1]);
            nodeHeap[size - 1] = null;
            heapify(0, --size);

            return nodeRecord;
        }


        public void heapify(int index, int size){
            int left = index * 2 + 1;

            while (left < size){
                int smallest = left + 1 < size && distanceMap.get(nodeHeap[left + 1]) < distanceMap.get(nodeHeap[left]) ? left + 1 : left;

                smallest = distanceMap.get(nodeHeap[smallest]) < distanceMap.get(nodeHeap[index]) ? smallest : index;

                if(index == smallest){
                    break;
                }

                swap(index,smallest);
                index = smallest;
                left = index * 2  + 1;

            }
        }



        public void swap(int i , int j){
            Node temp = nodeHeap[i];
            nodeHeap[i] = nodeHeap[j];
            nodeHeap[j] = temp;

            indexMap.put(nodeHeap[i],j);
            indexMap.put(nodeHeap[j],i);
        }


        public boolean isEmpty(){
            return size == 0;
        }

    }




    //第二种方式，利用堆来实现
    public Map<Node,Integer> dijkstra2(Node from , int size){
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addAndUpdateAndIgnore(from,0);

        Map<Node,Integer> result = new HashMap<>();
        while (!nodeHeap.isEmpty()){
            NodeRecord nodeRecord = nodeHeap.pop();
            Node cur = nodeRecord.node;
            for (Edge edge : cur.edges){
                Node to = edge.to;
                nodeHeap.addAndUpdateAndIgnore(to,nodeRecord.distance + edge.weight);
            }

            result.put(cur,nodeRecord.distance);
        }

        return result;
    }




}


