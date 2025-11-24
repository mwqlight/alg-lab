package com.alglab.init;

import com.alglab.algorithm.Algorithm;
import com.alglab.algorithm.AlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private AlgorithmService algorithmService;

    @Override
    public void run(String... args) throws Exception {
        // 检查是否已有数据
        if (algorithmService.getAllAlgorithms().isEmpty()) {
            List<Algorithm> algorithms = new ArrayList<>();

            // 冒泡排序
            Algorithm bubbleSort = new Algorithm();
            bubbleSort.setName("冒泡排序");
            bubbleSort.setCategory("排序算法");
            bubbleSort.setSubcategory("比较排序");
            bubbleSort.setDescription("冒泡排序是一种简单的排序算法。它重复地走访过要排序的数列，一次比较两个元素，如果它们的顺序错误就把它们交换过来。走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。");
            bubbleSort.setCodeExample("public class BubbleSort {\n    public static void sort(int[] arr) {\n        int n = arr.length;\n        for (int i = 0; i < n - 1; i++) {\n            for (int j = 0; j < n - i - 1; j++) {\n                if (arr[j] > arr[j + 1]) {\n                    // 交换 arr[j] 和 arr[j+1]\n                    int temp = arr[j];\n                    arr[j] = arr[j + 1];\n                    arr[j + 1] = temp;\n                }\n            }\n        }\n    }\n}");
            bubbleSort.setTimeComplexity("平均: O(n²), 最坏: O(n²), 最好: O(n)");
            bubbleSort.setSpaceComplexity("O(1)");
            bubbleSort.setAnimationData("[{\"step\": 1, \"array\": [5, 3, 8, 4, 2], \"compared\": [0, 1], \"swapped\": false},{\"step\": 2, \"array\": [3, 5, 8, 4, 2], \"compared\": [1, 2], \"swapped\": true},{\"step\": 3, \"array\": [3, 5, 8, 4, 2], \"compared\": [2, 3], \"swapped\": false},{\"step\": 4, \"array\": [3, 5, 4, 8, 2], \"compared\": [3, 4], \"swapped\": true},{\"step\": 5, \"array\": [3, 5, 4, 2, 8], \"compared\": [0, 1], \"swapped\": false},{\"step\": 6, \"array\": [3, 5, 4, 2, 8], \"compared\": [1, 2], \"swapped\": true},{\"step\": 7, \"array\": [3, 4, 5, 2, 8], \"compared\": [2, 3], \"swapped\": true},{\"step\": 8, \"array\": [3, 4, 2, 5, 8], \"compared\": [0, 1], \"swapped\": false},{\"step\": 9, \"array\": [3, 4, 2, 5, 8], \"compared\": [1, 2], \"swapped\": true},{\"step\": 10, \"array\": [3, 2, 4, 5, 8], \"compared\": [0, 1], \"swapped\": true},{\"step\": 11, \"array\": [2, 3, 4, 5, 8], \"compared\": [], \"swapped\": false}]");
            bubbleSort.setAnalysis("冒泡排序是稳定的排序算法，适用于数据量较小的情况。它的优点是简单易懂，实现容易；缺点是时间复杂度较高，对于大规模数据排序效率较低。");
            algorithms.add(bubbleSort);

            // 快速排序
            Algorithm quickSort = new Algorithm();
            quickSort.setName("快速排序");
            quickSort.setCategory("排序算法");
            quickSort.setSubcategory("比较排序");
            quickSort.setDescription("快速排序是对冒泡排序的一种改进。它的基本思想是：通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。");
            quickSort.setCodeExample("public class QuickSort {\n    public static void sort(int[] arr) {\n        quickSort(arr, 0, arr.length - 1);\n    }\n\n    private static void quickSort(int[] arr, int low, int high) {\n        if (low < high) {\n            int pi = partition(arr, low, high);\n            quickSort(arr, low, pi - 1);\n            quickSort(arr, pi + 1, high);\n        }\n    }\n\n    private static int partition(int[] arr, int low, int high) {\n        int pivot = arr[high];\n        int i = (low - 1);\n        for (int j = low; j < high; j++) {\n            if (arr[j] < pivot) {\n                i++;\n                int temp = arr[i];\n                arr[i] = arr[j];\n                arr[j] = temp;\n            }\n        }\n        int temp = arr[i + 1];\n        arr[i + 1] = arr[high];\n        arr[high] = temp;\n        return i + 1;\n    }\n}");
            quickSort.setTimeComplexity("平均: O(n log n), 最坏: O(n²), 最好: O(n log n)");
            quickSort.setSpaceComplexity("O(log n)");
            quickSort.setAnimationData("[{\"step\": 1, \"array\": [10, 7, 8, 9, 1, 5], \"pivot\": 5, \"low\": 0, \"high\": 5},{\"step\": 2, \"array\": [10, 7, 8, 9, 1, 5], \"compared\": [0, 5], \"swapped\": false},{\"step\": 3, \"array\": [10, 7, 8, 9, 1, 5], \"compared\": [1, 5], \"swapped\": false},{\"step\": 4, \"array\": [10, 7, 8, 9, 1, 5], \"compared\": [2, 5], \"swapped\": false},{\"step\": 5, \"array\": [10, 7, 8, 9, 1, 5], \"compared\": [3, 5], \"swapped\": false},{\"step\": 6, \"array\": [10, 7, 8, 9, 1, 5], \"compared\": [4, 5], \"swapped\": true},{\"step\": 7, \"array\": [1, 7, 8, 9, 10, 5], \"compared\": [5, 5], \"swapped\": true},{\"step\": 8, \"array\": [1, 5, 8, 9, 10, 7], \"pivot\": 1, \"low\": 0, \"high\": 0}]");
            quickSort.setAnalysis("快速排序是一种不稳定的排序算法，它的优点是平均时间复杂度较低，对于大规模数据排序效率较高；缺点是实现相对复杂，最坏情况下时间复杂度较高。");
            algorithms.add(quickSort);

            // 插入排序
            Algorithm insertionSort = new Algorithm();
            insertionSort.setName("插入排序");
            insertionSort.setCategory("排序算法");
            insertionSort.setSubcategory("比较排序");
            insertionSort.setDescription("插入排序是一种简单直观的排序算法。它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。");
            insertionSort.setCodeExample("public class InsertionSort {\n    public static void sort(int[] arr) {\n        int n = arr.length;\n        for (int i = 1; i < n; ++i) {\n            int key = arr[i];\n            int j = i - 1;\n            while (j >= 0 && arr[j] > key) {\n                arr[j + 1] = arr[j];\n                j = j - 1;\n            }\n            arr[j + 1] = key;\n        }\n    }\n}");
            insertionSort.setTimeComplexity("平均: O(n²), 最坏: O(n²), 最好: O(n)");
            insertionSort.setSpaceComplexity("O(1)");
            insertionSort.setAnimationData("[{\"step\": 1, \"array\": [12, 11, 13, 5, 6], \"current\": 11, \"compared\": [1, 0], \"swapped\": true},{\"step\": 2, \"array\": [11, 12, 13, 5, 6], \"current\": 13, \"compared\": [2, 1], \"swapped\": false},{\"step\": 3, \"array\": [11, 12, 13, 5, 6], \"current\": 5, \"compared\": [3, 2], \"swapped\": true},{\"step\": 4, \"array\": [11, 12, 5, 13, 6], \"current\": 5, \"compared\": [2, 1], \"swapped\": true},{\"step\": 5, \"array\": [11, 5, 12, 13, 6], \"current\": 5, \"compared\": [1, 0], \"swapped\": true},{\"step\": 6, \"array\": [5, 11, 12, 13, 6], \"current\": 6, \"compared\": [4, 3], \"swapped\": true},{\"step\": 7, \"array\": [5, 11, 12, 6, 13], \"current\": 6, \"compared\": [3, 2], \"swapped\": true},{\"step\": 8, \"array\": [5, 11, 6, 12, 13], \"current\": 6, \"compared\": [2, 1], \"swapped\": true},{\"step\": 9, \"array\": [5, 6, 11, 12, 13], \"current\": 6, \"compared\": [1, 0], \"swapped\": false}]");
            insertionSort.setAnalysis("插入排序是稳定的排序算法，适用于数据量较小或部分有序的数据。它的优点是简单易懂，实现容易；缺点是时间复杂度较高，对于大规模数据排序效率较低。");
            algorithms.add(insertionSort);

            // 保存所有算法
            algorithms.forEach(algorithmService::saveAlgorithm);
        }
    }
}