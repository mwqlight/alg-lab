package com.alglab.init;

import com.alglab.algorithm.Algorithm;
import com.alglab.algorithm.AlgorithmRepository;
import com.alglab.algorithm.AnimationStep;
import com.alglab.algorithm.AnimationStepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private AlgorithmRepository algorithmRepository;
    
    @Autowired
    private AnimationStepRepository animationStepRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // 检查是否已有数据
        if (algorithmRepository.count() == 0) {
            initializeAlgorithms();
            initializeAnimationSteps();
        }
    }
    
    private void initializeAlgorithms() {
        // 创建冒泡排序算法
        Algorithm bubbleSort = new Algorithm();
        bubbleSort.setName("冒泡排序");
        bubbleSort.setCategory("排序算法");
        bubbleSort.setDescription("冒泡排序是一种简单的排序算法。它重复地走访过要排序的数列，一次比较两个元素，如果它们的顺序错误就把它们交换过来。走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。");
        bubbleSort.setTimeComplexity("O(n²)");
        bubbleSort.setSpaceComplexity("O(1)");
        bubbleSort.setAnalysis("优点：1. 实现简单 2. 稳定排序 3. 原地排序\n缺点：1. 时间复杂度高 2. 效率低\n适用场景：1. 数据量较小 2. 对稳定性有要求");
        bubbleSort.setCodeExample("public class BubbleSort {\n    public static void bubbleSort(int[] arr) {\n        int n = arr.length;\n        for (int i = 0; i < n - 1; i++) {\n            for (int j = 0; j < n - i - 1; j++) {\n                if (arr[j] > arr[j + 1]) {\n                    // 交换 arr[j] 和 arr[j+1]\n                    int temp = arr[j];\n                    arr[j] = arr[j + 1];\n                    arr[j + 1] = temp;\n                }\n            }\n        }\n    }\n}");
        
        // 创建快速排序算法
        Algorithm quickSort = new Algorithm();
        quickSort.setName("快速排序");
        quickSort.setCategory("排序算法");
        quickSort.setDescription("快速排序是一种高效的排序算法，采用分治法策略来把一个序列分为较小和较大的两个子序列，然后递归地排序两个子序列。");
        quickSort.setTimeComplexity("O(n log n)");
        quickSort.setSpaceComplexity("O(log n)");
        quickSort.setAnalysis("优点：1. 平均性能优秀 2. 原地排序\n缺点：1. 不稳定排序 2. 最坏情况性能差\n适用场景：1. 大数据量排序 2. 对平均性能有要求");
        quickSort.setCodeExample("public class QuickSort {\n    public static void quickSort(int[] arr, int low, int high) {\n        if (low < high) {\n            int pi = partition(arr, low, high);\n            quickSort(arr, low, pi - 1);\n            quickSort(arr, pi + 1, high);\n        }\n    }\n    \n    private static int partition(int[] arr, int low, int high) {\n        int pivot = arr[high];\n        int i = (low - 1);\n        \n        for (int j = low; j < high; j++) {\n            if (arr[j] <= pivot) {\n                i++;\n                int temp = arr[i];\n                arr[i] = arr[j];\n                arr[j] = temp;\n            }\n        }\n        \n        int temp = arr[i + 1];\n        arr[i + 1] = arr[high];\n        arr[high] = temp;\n        \n        return i + 1;\n    }\n}");
        
        // 创建选择排序算法
        Algorithm selectionSort = new Algorithm();
        selectionSort.setName("选择排序");
        selectionSort.setCategory("排序算法");
        selectionSort.setDescription("选择排序是一种简单直观的排序算法。它的工作原理是每一次从待排序的数据元素中选出最小（或最大）的一个元素，存放在序列的起始位置，直到全部待排序的数据元素排完。");
        selectionSort.setTimeComplexity("O(n²)");
        selectionSort.setSpaceComplexity("O(1)");
        selectionSort.setAnalysis("优点：1. 实现简单 2. 原地排序 3. 交换次数少\n缺点：1. 时间复杂度高 2. 不稳定排序\n适用场景：1. 数据量较小 2. 对交换次数有要求");
        selectionSort.setCodeExample("public class SelectionSort {\n    public static void selectionSort(int[] arr) {\n        int n = arr.length;\n        for (int i = 0; i < n - 1; i++) {\n            int minIdx = i;\n            for (int j = i + 1; j < n; j++) {\n                if (arr[j] < arr[minIdx]) {\n                    minIdx = j;\n                }\n            }\n            int temp = arr[minIdx];\n            arr[minIdx] = arr[i];\n            arr[i] = temp;\n        }\n    }\n}");
        
        // 创建插入排序算法
        Algorithm insertionSort = new Algorithm();
        insertionSort.setName("插入排序");
        insertionSort.setCategory("排序算法");
        insertionSort.setDescription("插入排序是一种简单直观的排序算法。它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。");
        insertionSort.setTimeComplexity("O(n²)");
        insertionSort.setSpaceComplexity("O(1)");
        insertionSort.setAnalysis("优点：1. 实现简单 2. 稳定排序 3. 在线算法 4. 对小规模数据效率较高\n缺点：1. 时间复杂度高\n适用场景：1. 小规模数据排序 2. 数据基本有序的情况");
        insertionSort.setCodeExample("public class InsertionSort {\n    public static void insertionSort(int[] arr) {\n        int n = arr.length;\n        for (int i = 1; i < n; ++i) {\n            int key = arr[i];\n            int j = i - 1;\n            while (j >= 0 && arr[j] > key) {\n                arr[j + 1] = arr[j];\n                j = j - 1;\n            }\n            arr[j + 1] = key;\n        }\n    }\n}");
        
        // 创建归并排序算法
        Algorithm mergeSort = new Algorithm();
        mergeSort.setName("归并排序");
        mergeSort.setCategory("排序算法");
        mergeSort.setDescription("归并排序是建立在归并操作上的一种有效的排序算法。该算法是采用分治法的一个非常典型的应用。将已有序的子序列合并，得到完全有序的序列。");
        mergeSort.setTimeComplexity("O(n log n)");
        mergeSort.setSpaceComplexity("O(n)");
        mergeSort.setAnalysis("优点：1. 稳定排序 2. 时间复杂度稳定 3. 适合大数据量排序\n缺点：1. 空间复杂度较高 2. 不是原地排序\n适用场景：1. 大数据量排序 2. 对稳定性有要求");
        mergeSort.setCodeExample("public class MergeSort {\n    public static void mergeSort(int[] arr, int l, int r) {\n        if (l < r) {\n            int m = (l + r) / 2;\n            mergeSort(arr, l, m);\n            mergeSort(arr, m + 1, r);\n            merge(arr, l, m, r);\n        }\n    }\n    \n    public static void merge(int[] arr, int l, int m, int r) {\n        int n1 = m - l + 1;\n        int n2 = r - m;\n        \n        int[] L = new int[n1];\n        int[] R = new int[n2];\n        \n        for (int i = 0; i < n1; ++i)\n            L[i] = arr[l + i];\n        for (int j = 0; j < n2; ++j)\n            R[j] = arr[m + 1 + j];\n            \n        int i = 0, j = 0;\n        int k = l;\n        \n        while (i < n1 && j < n2) {\n            if (L[i] <= R[j]) {\n                arr[k] = L[i];\n                i++;\n            } else {\n                arr[k] = R[j];\n                j++;\n            }\n            k++;\n        }\n        \n        while (i < n1) {\n            arr[k] = L[i];\n            i++;\n            k++;\n        }\n        \n        while (j < n2) {\n            arr[k] = R[j];\n            j++;\n            k++;\n        }\n    }\n}");
        
        // 创建堆排序算法
        Algorithm heapSort = new Algorithm();
        heapSort.setName("堆排序");
        heapSort.setCategory("排序算法");
        heapSort.setDescription("堆排序是指利用堆这种数据结构所设计的一种排序算法。堆积是一个近似完全二叉树的结构，并同时满足堆积的性质：即子结点的键值或索引总是小于（或者大于）它的父节点。");
        heapSort.setTimeComplexity("O(n log n)");
        heapSort.setSpaceComplexity("O(1)");
        heapSort.setAnalysis("优点：1. 时间复杂度稳定 2. 原地排序 3. 适合大数据量排序\n缺点：1. 不稳定排序 2. 常数因子较大\n适用场景：1. 大数据量排序 2. 对内存使用有限制");
        heapSort.setCodeExample("public class HeapSort {\n    public static void heapSort(int[] arr) {\n        int n = arr.length;\n        \n        // 构建最大堆\n        for (int i = n / 2 - 1; i >= 0; i--)\n            heapify(arr, n, i);\n            \n        // 逐个从堆顶取出元素\n        for (int i = n - 1; i > 0; i--) {\n            int temp = arr[0];\n            arr[0] = arr[i];\n            arr[i] = temp;\n            \n            heapify(arr, i, 0);\n        }\n    }\n    \n    public static void heapify(int[] arr, int n, int i) {\n        int largest = i;\n        int l = 2 * i + 1;\n        int r = 2 * i + 2;\n        \n        if (l < n && arr[l] > arr[largest])\n            largest = l;\n            \n        if (r < n && arr[r] > arr[largest])\n            largest = r;\n            \n        if (largest != i) {\n            int swap = arr[i];\n            arr[i] = arr[largest];\n            arr[largest] = swap;\n            \n            heapify(arr, n, largest);\n        }\n    }\n}");
        
        // 创建希尔排序算法
        Algorithm shellSort = new Algorithm();
        shellSort.setName("希尔排序");
        shellSort.setCategory("排序算法");
        shellSort.setDescription("希尔排序是插入排序的一种更高效的改进版本。希尔排序是非稳定排序算法。该方法因D.L.Shell于1959年提出而得名。");
        shellSort.setTimeComplexity("O(n^1.3)");
        shellSort.setSpaceComplexity("O(1)");
        shellSort.setAnalysis("优点：1. 改进了插入排序 2. 原地排序 3. 对中等规模数据效率较高\n缺点：1. 不稳定排序 2. 增量序列的选择影响性能\n适用场景：1. 中等规模数据排序 2. 对插入排序的改进需求");
        shellSort.setCodeExample("public class ShellSort {\n    public static void shellSort(int[] arr) {\n        int n = arr.length;\n        \n        for (int gap = n / 2; gap > 0; gap /= 2) {\n            for (int i = gap; i < n; i += 1) {\n                int temp = arr[i];\n                int j;\n                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap)\n                    arr[j] = arr[j - gap];\n                \n                arr[j] = temp;\n            }\n        }\n    }\n}");
        
        // 创建二分查找算法
        Algorithm binarySearch = new Algorithm();
        binarySearch.setName("二分查找");
        binarySearch.setCategory("搜索算法");
        binarySearch.setDescription("二分查找也称折半查找，它是一种效率较高的查找方法。但是，二分查找要求线性表必须采用顺序存储结构，而且表中元素按关键字有序排列。");
        binarySearch.setTimeComplexity("O(log n)");
        binarySearch.setSpaceComplexity("O(1)");
        binarySearch.setAnalysis("优点：1. 查找效率高 2. 实现简单\n缺点：1. 要求数据有序 2. 只适用于静态查找\n适用场景：1. 有序数组查找 2. 大数据量查找");
        binarySearch.setCodeExample("public class BinarySearch {\n    public static int binarySearch(int[] arr, int target) {\n        int left = 0, right = arr.length - 1;\n        \n        while (left <= right) {\n            int mid = left + (right - left) / 2;\n            \n            if (arr[mid] == target) {\n                return mid;\n            } else if (arr[mid] < target) {\n                left = mid + 1;\n            } else {\n                right = mid - 1;\n            }\n        }\n        \n        return -1; // 未找到\n    }\n}");
        
        // 创建深度优先搜索算法
        Algorithm dfs = new Algorithm();
        dfs.setName("深度优先搜索");
        dfs.setCategory("搜索算法");
        dfs.setDescription("深度优先搜索属于图算法的一种，英文缩写为DFS。其过程简要来说是对每一个可能的分支路径深入到不能再深入为止，而且每个节点只能访问一次。");
        dfs.setTimeComplexity("O(V+E)");
        dfs.setSpaceComplexity("O(V)");
        dfs.setAnalysis("优点：1. 能够遍历所有节点 2. 内存需求较少 3. 能够找到路径\n缺点：1. 可能陷入无限深的路径 2. 不一定找到最优解\n适用场景：1. 图或树的遍历 2. 路径搜索问题");
        dfs.setCodeExample("import java.util.*;\n\npublic class DFSGraph {\n    private int V;\n    private LinkedList<Integer> adj[];\n    \n    DFSGraph(int v) {\n        V = v;\n        adj = new LinkedList[v];\n        for (int i = 0; i < v; ++i)\n            adj[i] = new LinkedList();\n    }\n    \n    void addEdge(int v, int w) {\n        adj[v].add(w);\n    }\n    \n    void DFSUtil(int v, boolean visited[]) {\n        visited[v] = true;\n        System.out.print(v + \" \");\n        \n        Iterator<Integer> i = adj[v].listIterator();\n        while (i.hasNext()) {\n            int n = i.next();\n            if (!visited[n])\n                DFSUtil(n, visited);\n        }\n    }\n    \n    void DFS(int v) {\n        boolean visited[] = new boolean[V];\n        DFSUtil(v, visited);\n    }\n}");
        
        // 创建广度优先搜索算法
        Algorithm bfs = new Algorithm();
        bfs.setName("广度优先搜索");
        bfs.setCategory("搜索算法");
        bfs.setDescription("广度优先搜索算法是一种图形搜索算法。简单的说，BFS是从根节点开始，沿着树的宽度遍历树的节点。如果所有节点均被访问，则算法中止。");
        bfs.setTimeComplexity("O(V+E)");
        bfs.setSpaceComplexity("O(V)");
        bfs.setAnalysis("优点：1. 能够找到最短路径 2. 层次遍历特性 3. 完全性好\n缺点：1. 空间复杂度较高 2. 速度较慢\n适用场景：1. 最短路径问题 2. 层次遍历");
        bfs.setCodeExample("import java.util.*;\n\npublic class BFSGraph {\n    private int V;\n    private LinkedList<Integer> adj[];\n    \n    BFSGraph(int v) {\n        V = v;\n        adj = new LinkedList[v];\n        for (int i = 0; i < v; ++i)\n            adj[i] = new LinkedList();\n    }\n    \n    void addEdge(int v, int w) {\n        adj[v].add(w);\n    }\n    \n    void BFS(int s) {\n        boolean visited[] = new boolean[V];\n        LinkedList<Integer> queue = new LinkedList<Integer>();\n        \n        visited[s] = true;\n        queue.add(s);\n        \n        while (queue.size() != 0) {\n            s = queue.poll();\n            System.out.print(s + \" \");\n            \n            Iterator<Integer> i = adj[s].listIterator();\n            while (i.hasNext()) {\n                int n = i.next();\n                if (!visited[n]) {\n                    visited[n] = true;\n                    queue.add(n);\n                }\n            }\n        }\n    }\n}");
        
        // 创建斐波那契数列算法
        Algorithm fibonacci = new Algorithm();
        fibonacci.setName("斐波那契数列");
        fibonacci.setCategory("动态规划");
        fibonacci.setDescription("斐波那契数列是数学中一个非常著名的数列，其特点是每一项都是前两项的和。动态规划可以高效计算斐波那契数列。");
        fibonacci.setTimeComplexity("O(n)");
        fibonacci.setSpaceComplexity("O(n)");
        fibonacci.setAnalysis("优点：1. 避免重复计算 2. 时间复杂度低\n缺点：1. 需要额外空间存储中间结果\n适用场景：1. 斐波那契数列计算 2. 具有重叠子问题的优化问题");
        fibonacci.setCodeExample("public class Fibonacci {\n    public static int fibonacciDP(int n) {\n        if (n <= 1) return n;\n        \n        int[] dp = new int[n + 1];\n        dp[0] = 0;\n        dp[1] = 1;\n        \n        for (int i = 2; i <= n; i++) {\n            dp[i] = dp[i - 1] + dp[i - 2];\n        }\n        \n        return dp[n];\n    }\n}");
        
        // 创建最长公共子序列算法
        Algorithm lcs = new Algorithm();
        lcs.setName("最长公共子序列");
        lcs.setCategory("动态规划");
        lcs.setDescription("最长公共子序列问题是寻找两个序列最长的公共子序列。该问题是动态规划的经典应用之一。");
        lcs.setTimeComplexity("O(m*n)");
        lcs.setSpaceComplexity("O(m*n)");
        lcs.setAnalysis("优点：1. 能够找到最优解 2. 适用于字符串比较\n缺点：1. 空间复杂度较高 2. 时间复杂度相对较高\n适用场景：1. 文本差异比较 2. 生物信息学中的序列比对");
        lcs.setCodeExample("public class LCS {\n    public static int longestCommonSubsequence(String text1, String text2) {\n        int m = text1.length(), n = text2.length();\n        int[][] dp = new int[m + 1][n + 1];\n        \n        for (int i = 1; i <= m; i++) {\n            for (int j = 1; j <= n; j++) {\n                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {\n                    dp[i][j] = dp[i - 1][j - 1] + 1;\n                } else {\n                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);\n                }\n            }\n        }\n        \n        return dp[m][n];\n    }\n}");
        
        // 创建背包问题算法
        Algorithm knapsack = new Algorithm();
        knapsack.setName("背包问题");
        knapsack.setCategory("动态规划");
        knapsack.setDescription("背包问题是一种组合优化的NP完全问题。0-1背包问题是最基本的背包问题，每种物品仅有一件，可以选择放或不放。");
        knapsack.setTimeComplexity("O(n*W)");
        knapsack.setSpaceComplexity("O(n*W)");
        knapsack.setAnalysis("优点：1. 能够找到最优解 2. 适用于资源分配问题\n缺点：1. 空间复杂度较高 2. 对于大容量背包计算时间较长\n适用场景：1. 资源分配 2. 投资决策 3. 装载问题");
        knapsack.setCodeExample("public class Knapsack {\n    public static int knapsack(int W, int wt[], int val[], int n) {\n        int i, w;\n        int K[][] = new int[n + 1][W + 1];\n        \n        for (i = 0; i <= n; i++) {\n            for (w = 0; w <= W; w++) {\n                if (i == 0 || w == 0)\n                    K[i][w] = 0;\n                else if (wt[i - 1] <= w)\n                    K[i][w] = Math.max(val[i - 1] + K[i - 1][w - wt[i - 1]], K[i - 1][w]);\n                else\n                    K[i][w] = K[i - 1][w];\n            }\n        }\n        \n        return K[n][W];\n    }\n}");
        
        // 创建Dijkstra最短路径算法
        Algorithm dijkstra = new Algorithm();
        dijkstra.setName("Dijkstra最短路径");
        dijkstra.setCategory("图论算法");
        dijkstra.setDescription("Dijkstra算法是由荷兰计算机科学家狄克斯特拉于1959年提出的，用来解决有权图中最短路径问题。该算法采用贪心策略，每次选择距离源点最近的顶点进行扩展。");
        dijkstra.setTimeComplexity("O(V^2)");
        dijkstra.setSpaceComplexity("O(V)");
        dijkstra.setAnalysis("优点：1. 能够找到单源最短路径 2. 适用于非负权重图\n缺点：1. 不能处理负权边 2. 时间复杂度较高\n适用场景：1. 网络路由 2. 地图导航 3. 社交网络分析");
        dijkstra.setCodeExample("import java.util.*;\n\npublic class Dijkstra {\n    static final int V = 9;\n    \n    int minDistance(int dist[], Boolean sptSet[]) {\n        int min = Integer.MAX_VALUE, min_index = -1;\n        \n        for (int v = 0; v < V; v++)\n            if (sptSet[v] == false && dist[v] <= min) {\n                min = dist[v];\n                min_index = v;\n            }\n        \n        return min_index;\n    }\n    \n    void dijkstra(int graph[][], int src) {\n        int dist[] = new int[V];\n        Boolean sptSet[] = new Boolean[V];\n        \n        for (int i = 0; i < V; i++) {\n            dist[i] = Integer.MAX_VALUE;\n            sptSet[i] = false;\n        }\n        \n        dist[src] = 0;\n        \n        for (int count = 0; count < V - 1; count++) {\n            int u = minDistance(dist, sptSet);\n            sptSet[u] = true;\n            \n            for (int v = 0; v < V; v++)\n                if (!sptSet[v] && graph[u][v] != 0 &&\n                    dist[u] != Integer.MAX_VALUE &&\n                    dist[u] + graph[u][v] < dist[v])\n                    dist[v] = dist[u] + graph[u][v];\n        }\n    }\n}");
        
        // 创建Prim最小生成树算法
        Algorithm prim = new Algorithm();
        prim.setName("Prim最小生成树");
        prim.setCategory("图论算法");
        prim.setDescription("Prim算法是一种用于在加权连通图中搜索最小生成树的算法。该算法从一个顶点开始，逐步扩展生成树，每次都选择连接生成树和非生成树顶点的最小权重边。");
        prim.setTimeComplexity("O(V^2)");
        prim.setSpaceComplexity("O(V)");
        prim.setAnalysis("优点：1. 能够找到最小生成树 2. 适用于稠密图\n缺点：1. 时间复杂度较高 2. 需要邻接矩阵表示\n适用场景：1. 网络设计 2. 电路设计 3. 聚类分析");
        prim.setCodeExample("import java.util.*;\n\npublic class PrimMST {\n    private static final int V = 5;\n    \n    int minKey(int key[], Boolean mstSet[]) {\n        int min = Integer.MAX_VALUE, min_index = -1;\n        \n        for (int v = 0; v < V; v++)\n            if (mstSet[v] == false && key[v] < min) {\n                min = key[v];\n                min_index = v;\n            }\n        \n        return min_index;\n    }\n    \n    void primMST(int graph[][]) {\n        int parent[] = new int[V];\n        int key[] = new int[V];\n        Boolean mstSet[] = new Boolean[V];\n        \n        for (int i = 0; i < V; i++) {\n            key[i] = Integer.MAX_VALUE;\n            mstSet[i] = false;\n        }\n        \n        key[0] = 0;\n        parent[0] = -1;\n        \n        for (int count = 0; count < V - 1; count++) {\n            int u = minKey(key, mstSet);\n            mstSet[u] = true;\n            \n            for (int v = 0; v < V; v++)\n                if (graph[u][v] != 0 && mstSet[v] == false &&\n                    graph[u][v] < key[v]) {\n                    parent[v] = u;\n                    key[v] = graph[u][v];\n                }\n        }\n    }\n}");
        
        // 创建KMP字符串匹配算法
        Algorithm kmp = new Algorithm();
        kmp.setName("KMP算法");
        kmp.setCategory("字符串算法");
        kmp.setDescription("KMP算法是一种改进的字符串匹配算法，由Donald Knuth、Vaughan Pratt、James H. Morris三人于1977年联合发表，故取这3人的姓氏命名此算法。该算法可以在线性时间内完成字符串匹配。");
        kmp.setTimeComplexity("O(n+m)");
        kmp.setSpaceComplexity("O(m)");
        kmp.setAnalysis("优点：1. 时间复杂度低 2. 避免不必要的字符比较\n缺点：1. 需要额外空间存储next数组 2. 实现相对复杂\n适用场景：1. 文本搜索 2. 模式匹配 3. 数据压缩");
        kmp.setCodeExample("public class KMPStringMatching {\n    void KMPSearch(String pat, String txt) {\n        int M = pat.length();\n        int N = txt.length();\n        \n        int lps[] = new int[M];\n        int j = 0;\n        \n        computeLPSArray(pat, M, lps);\n        \n        int i = 0;\n        while (i < N) {\n            if (pat.charAt(j) == txt.charAt(i)) {\n                j++;\n                i++;\n            }\n            if (j == M) {\n                System.out.println(\"Found pattern at index \" + (i - j));\n                j = lps[j - 1];\n            } else if (i < N && pat.charAt(j) != txt.charAt(i)) {\n                if (j != 0)\n                    j = lps[j - 1];\n                else\n                    i = i + 1;\n            }\n        }\n    }\n    \n    void computeLPSArray(String pat, int M, int lps[]) {\n        int len = 0;\n        int i = 1;\n        lps[0] = 0;\n        \n        while (i < M) {\n            if (pat.charAt(i) == pat.charAt(len)) {\n                len++;\n                lps[i] = len;\n                i++;\n            } else {\n                if (len != 0) {\n                    len = lps[len - 1];\n                } else {\n                    lps[i] = len;\n                    i++;\n                }\n            }\n        }\n    }\n}");
        
        // 创建编辑距离算法
        Algorithm editDistance = new Algorithm();
        editDistance.setName("编辑距离");
        editDistance.setCategory("字符串算法");
        editDistance.setDescription("编辑距离是指两个字符串之间，由一个转换成另一个所需的最少编辑操作次数。许可的编辑操作包括将一个字符替换成另一个字符，插入一个字符，删除一个字符。");
        editDistance.setTimeComplexity("O(m*n)");
        editDistance.setSpaceComplexity("O(m*n)");
        editDistance.setAnalysis("优点：1. 能够量化字符串相似度 2. 应用广泛\n缺点：1. 时间复杂度较高 2. 空间复杂度较高\n适用场景：1. 拼写检查 2. DNA序列分析 3. 机器翻译");
        editDistance.setCodeExample("public class EditDistance {\n    public static int minDistance(String word1, String word2) {\n        int m = word1.length();\n        int n = word2.length();\n        \n        int[][] dp = new int[m + 1][n + 1];\n        \n        for (int i = 0; i <= m; i++) {\n            for (int j = 0; j <= n; j++) {\n                if (i == 0) {\n                    dp[i][j] = j;\n                } else if (j == 0) {\n                    dp[i][j] = i;\n                } else if (word1.charAt(i - 1) == word2.charAt(j - 1)) {\n                    dp[i][j] = dp[i - 1][j - 1];\n                } else {\n                    dp[i][j] = 1 + Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]);\n                }\n            }\n        }\n        \n        return dp[m][n];\n    }\n}");
        
        // 保存算法
        algorithmRepository.saveAll(Arrays.asList(bubbleSort, quickSort, selectionSort, insertionSort, mergeSort, heapSort, shellSort, binarySearch, dfs, bfs, fibonacci, lcs, knapsack, dijkstra, prim, kmp, editDistance));
    }
    
    private void initializeAnimationSteps() {
        // 为冒泡排序创建动画步骤 (算法ID: 76)
        List<AnimationStep> bubbleSortSteps = Arrays.asList(
            new AnimationStep(null, 76L, 1, "[5,3,8,4,2]", "[0,1]", true),
            new AnimationStep(null, 76L, 2, "[3,5,8,4,2]", "[1,2]", false),
            new AnimationStep(null, 76L, 3, "[3,5,8,4,2]", "[2,3]", true),
            new AnimationStep(null, 76L, 4, "[3,5,4,8,2]", "[3,4]", true),
            new AnimationStep(null, 76L, 5, "[3,5,4,2,8]", "[0,1]", false)
        );
        
        // 为快速排序创建动画步骤 (算法ID: 77)
        List<AnimationStep> quickSortSteps = Arrays.asList(
            new AnimationStep(null, 77L, 1, "[5,3,8,4,2]", "[0,4]", true),
            new AnimationStep(null, 77L, 2, "[2,3,8,4,5]", "[2,3]", true),
            new AnimationStep(null, 77L, 3, "[2,3,4,8,5]", "[0,1]", false),
            new AnimationStep(null, 77L, 4, "[2,3,4,5,8]", "[3,4]", true),
            new AnimationStep(null, 77L, 5, "[2,3,4,5,8]", "[2,3]", false)
        );
        
        // 为选择排序创建动画步骤 (算法ID: 78)
        List<AnimationStep> selectionSortSteps = Arrays.asList(
            new AnimationStep(null, 78L, 1, "[5,3,8,4,2]", "[0,4]", true),
            new AnimationStep(null, 78L, 2, "[2,3,8,4,5]", "[1,2]", false),
            new AnimationStep(null, 78L, 3, "[2,3,8,4,5]", "[2,3]", true),
            new AnimationStep(null, 78L, 4, "[2,3,4,8,5]", "[3,4]", true),
            new AnimationStep(null, 78L, 5, "[2,3,4,5,8]", "[0,1]", false)
        );
        
        // 为插入排序创建动画步骤 (算法ID: 79)
        List<AnimationStep> insertionSortSteps = Arrays.asList(
            new AnimationStep(null, 79L, 1, "[5,3,8,4,2]", "[0,1]", true),
            new AnimationStep(null, 79L, 2, "[3,5,8,4,2]", "[2,3]", true),
            new AnimationStep(null, 79L, 3, "[3,5,4,8,2]", "[3,4]", true),
            new AnimationStep(null, 79L, 4, "[3,4,5,8,2]", "[0,1]", false),
            new AnimationStep(null, 79L, 5, "[2,3,4,5,8]", "[0,1]", false)
        );
        
        // 为归并排序创建动画步骤 (算法ID: 80)
        List<AnimationStep> mergeSortSteps = Arrays.asList(
            new AnimationStep(null, 80L, 1, "[5,3,8,4,2]", "[0,1]", true),
            new AnimationStep(null, 80L, 2, "[3,5,8,4,2]", "[2,3]", true),
            new AnimationStep(null, 80L, 3, "[3,5,4,8,2]", "[0,1]", false),
            new AnimationStep(null, 80L, 4, "[3,5,4,8,2]", "[3,4]", true),
            new AnimationStep(null, 80L, 5, "[3,5,4,2,8]", "[0,1]", false)
        );
        
        // 为堆排序创建动画步骤 (算法ID: 81)
        List<AnimationStep> heapSortSteps = Arrays.asList(
            new AnimationStep(null, 81L, 1, "[5,3,8,4,2]", "[0,1]", true),
            new AnimationStep(null, 81L, 2, "[8,3,5,4,2]", "[0,2]", true),
            new AnimationStep(null, 81L, 3, "[8,4,5,3,2]", "[1,3]", true),
            new AnimationStep(null, 81L, 4, "[2,4,5,3,8]", "[0,4]", true),
            new AnimationStep(null, 81L, 5, "[2,3,4,5,8]", "[0,1]", false)
        );
        
        // 为希尔排序创建动画步骤 (算法ID: 82)
        List<AnimationStep> shellSortSteps = Arrays.asList(
            new AnimationStep(null, 82L, 1, "[5,3,8,4,2]", "[0,2]", true),
            new AnimationStep(null, 82L, 2, "[2,3,8,4,5]", "[1,3]", true),
            new AnimationStep(null, 82L, 3, "[2,3,5,4,8]", "[2,4]", true),
            new AnimationStep(null, 82L, 4, "[2,3,4,5,8]", "[3,4]", true),
            new AnimationStep(null, 82L, 5, "[2,3,4,5,8]", "[0,1]", false)
        );
        
        // 为二分查找创建动画步骤 (算法ID: 83)
        List<AnimationStep> binarySearchSteps = Arrays.asList(
            new AnimationStep(null, 83L, 1, "[2,3,4,5,8]", "[2,4]", true),
            new AnimationStep(null, 83L, 2, "[2,3,4,5,8]", "[3,4]", true),
            new AnimationStep(null, 83L, 3, "[2,3,4,5,8]", "[3,3]", false),
            new AnimationStep(null, 83L, 4, "[2,3,4,5,8]", "[2,2]", false),
            new AnimationStep(null, 83L, 5, "[2,3,4,5,8]", "[3,3]", true)
        );
        
        // 为深度优先搜索创建动画步骤 (算法ID: 84)
        List<AnimationStep> dfsSteps = Arrays.asList(
            new AnimationStep(null, 84L, 1, "[A,B,C,D,E]", "[0,1]", false),
            new AnimationStep(null, 84L, 2, "[A,B,D,E,C]", "[1,3]", false),
            new AnimationStep(null, 84L, 3, "[A,B,D,C,E]", "[1,2]", false),
            new AnimationStep(null, 84L, 4, "[A,B,C,E,D]", "[0,2]", false),
            new AnimationStep(null, 84L, 5, "[A,C,E,D,B]", "[2,4]", false)
        );
        
        // 为广度优先搜索创建动画步骤 (算法ID: 85)
        List<AnimationStep> bfsSteps = Arrays.asList(
            new AnimationStep(null, 85L, 1, "[A,B,C,D,E]", "[0,1]", false),
            new AnimationStep(null, 85L, 2, "[A,B,C,E,D]", "[0,2]", false),
            new AnimationStep(null, 85L, 3, "[A,B,C,D,E]", "[1,3]", false),
            new AnimationStep(null, 85L, 4, "[A,B,D,C,E]", "[1,2]", false),
            new AnimationStep(null, 85L, 5, "[A,B,C,E,D]", "[2,4]", false)
        );
        
        // 为斐波那契数列创建动画步骤 (算法ID: 86)
        List<AnimationStep> fibonacciSteps = Arrays.asList(
            new AnimationStep(null, 86L, 1, "F(0)=0, F(1)=1", "[0,1]", false),
            new AnimationStep(null, 86L, 2, "F(2)=F(1)+F(0)=1", "[1,2]", false),
            new AnimationStep(null, 86L, 3, "F(3)=F(2)+F(1)=2", "[2,3]", false),
            new AnimationStep(null, 86L, 4, "F(4)=F(3)+F(2)=3", "[3,4]", false),
            new AnimationStep(null, 86L, 5, "F(5)=F(4)+F(3)=5", "[4,5]", false)
        );
        
        // 为最长公共子序列创建动画步骤 (算法ID: 87)
        List<AnimationStep> lcsSteps = Arrays.asList(
            new AnimationStep(null, 87L, 1, "ABCBDAB & BDCABA", "[0,0]", false),
            new AnimationStep(null, 87L, 2, "ABCBDAB & BDCABA", "[1,1]", true),
            new AnimationStep(null, 87L, 3, "ABCBDAB & BDCABA", "[2,2]", false),
            new AnimationStep(null, 87L, 4, "ABCBDAB & BDCABA", "[3,3]", true),
            new AnimationStep(null, 87L, 5, "ABCBDAB & BDCABA", "[4,4]", false)
        );
        
        // 为背包问题创建动画步骤 (算法ID: 88)
        List<AnimationStep> knapsackSteps = Arrays.asList(
            new AnimationStep(null, 88L, 1, "W=10, Items: (5,3), (3,4), (4,5)", "[0,1]", false),
            new AnimationStep(null, 88L, 2, "W=10, Items: (5,3), (3,4), (4,5)", "[1,2]", true),
            new AnimationStep(null, 88L, 3, "W=10, Items: (5,3), (3,4), (4,5)", "[2,3]", true),
            new AnimationStep(null, 88L, 4, "W=10, Items: (5,3), (3,4), (4,5)", "[3,4]", false),
            new AnimationStep(null, 88L, 5, "W=10, Items: (5,3), (3,4), (4,5)", "[4,5]", false)
        );
        
        // 为Dijkstra最短路径创建动画步骤 (算法ID: 89)
        List<AnimationStep> dijkstraSteps = Arrays.asList(
            new AnimationStep(null, 89L, 1, "Graph with vertices A,B,C,D,E", "[0,1]", false),
            new AnimationStep(null, 89L, 2, "Graph with vertices A,B,C,D,E", "[1,2]", true),
            new AnimationStep(null, 89L, 3, "Graph with vertices A,B,C,D,E", "[2,3]", true),
            new AnimationStep(null, 89L, 4, "Graph with vertices A,B,C,D,E", "[3,4]", true),
            new AnimationStep(null, 89L, 5, "Graph with vertices A,B,C,D,E", "[4,0]", false)
        );
        
        // 为Prim最小生成树创建动画步骤 (算法ID: 90)
        List<AnimationStep> primSteps = Arrays.asList(
            new AnimationStep(null, 90L, 1, "Graph with vertices A,B,C,D,E", "[0,1]", false),
            new AnimationStep(null, 90L, 2, "Graph with vertices A,B,C,D,E", "[1,2]", true),
            new AnimationStep(null, 90L, 3, "Graph with vertices A,B,C,D,E", "[2,3]", true),
            new AnimationStep(null, 90L, 4, "Graph with vertices A,B,C,D,E", "[3,4]", true),
            new AnimationStep(null, 90L, 5, "Graph with vertices A,B,C,D,E", "[4,0]", false)
        );
        
        // 为KMP算法创建动画步骤 (算法ID: 91)
        List<AnimationStep> kmpSteps = Arrays.asList(
            new AnimationStep(null, 91L, 1, "Text: ABCDABD, Pattern: ABD", "[0,2]", false),
            new AnimationStep(null, 91L, 2, "Text: ABCDABD, Pattern: ABD", "[1,3]", true),
            new AnimationStep(null, 91L, 3, "Text: ABCDABD, Pattern: ABD", "[2,4]", false),
            new AnimationStep(null, 91L, 4, "Text: ABCDABD, Pattern: ABD", "[3,5]", true),
            new AnimationStep(null, 91L, 5, "Text: ABCDABD, Pattern: ABD", "[4,6]", false)
        );
        
        // 为编辑距离创建动画步骤 (算法ID: 92)
        List<AnimationStep> editDistanceSteps = Arrays.asList(
            new AnimationStep(null, 92L, 1, "Word1: kitten, Word2: sitting", "[0,0]", false),
            new AnimationStep(null, 92L, 2, "Word1: kitten, Word2: sitting", "[1,1]", true),
            new AnimationStep(null, 92L, 3, "Word1: kitten, Word2: sitting", "[2,2]", false),
            new AnimationStep(null, 92L, 4, "Word1: kitten, Word2: sitting", "[3,3]", true),
            new AnimationStep(null, 92L, 5, "Word1: kitten, Word2: sitting", "[4,4]", false)
        );
        
        // 保存所有动画步骤
        animationStepRepository.saveAll(bubbleSortSteps);
        animationStepRepository.saveAll(quickSortSteps);
        animationStepRepository.saveAll(selectionSortSteps);
        animationStepRepository.saveAll(insertionSortSteps);
        animationStepRepository.saveAll(mergeSortSteps);
        animationStepRepository.saveAll(heapSortSteps);
        animationStepRepository.saveAll(shellSortSteps);
        animationStepRepository.saveAll(binarySearchSteps);
        animationStepRepository.saveAll(dfsSteps);
        animationStepRepository.saveAll(bfsSteps);
        animationStepRepository.saveAll(fibonacciSteps);
        animationStepRepository.saveAll(lcsSteps);
        animationStepRepository.saveAll(knapsackSteps);
        animationStepRepository.saveAll(dijkstraSteps);
        animationStepRepository.saveAll(primSteps);
        animationStepRepository.saveAll(kmpSteps);
        animationStepRepository.saveAll(editDistanceSteps);
    }
}