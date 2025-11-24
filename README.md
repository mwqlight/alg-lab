# 算法实验室

这是一个由 Spring Boot + Vue 3 实现的算法实验室项目，用于展示和演示各种算法的实现、动画演示以及时间复杂度和空间复杂度分析。

## 项目结构

```
alg-lab/
├── alg-lab-backend/          # 后端项目 (Spring Boot)
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/alglab/
│   │   │   │   ├── algorithm/    # 算法相关代码
│   │   │   │   ├── init/         # 数据初始化
│   │   │   │   └── AlgLabBackendApplication.java
│   │   │   └── resources/
│   │   │       └── application.yml
│   │   └── test/
│   └── pom.xml
├── alg-lab-frontend/         # 前端项目 (Vue 3)
│   ├── src/
│   │   ├── api/              # API 接口
│   │   ├── components/       # 组件
│   │   ├── router/           # 路由
│   │   ├── utils/            # 工具类
│   │   ├── views/            # 页面
│   │   ├── App.vue
│   │   ├── main.js
│   │   └── style.css
│   ├── index.html
│   ├── package.json
│   └── vite.config.js
└── feature-main.md           # 项目需求文档
```

## 技术栈

### 后端
- **Spring Boot 3.2.0** - 后端框架
- **Spring Data JPA** - 数据持久化
- **MySQL** - 数据库
- **Redis** - 缓存
- **Lombok** - 代码简化

### 前端
- **Vue 3** - 前端框架
- **Vue Router 4** - 路由管理
- **Element Plus** - UI 组件库
- **Axios** - HTTP 客户端
- **Vite** - 构建工具

## 功能特性

1. **算法展示** - 按分类展示各种算法
2. **代码示例** - 提供算法的 Java 实现代码
3. **动画演示** - 可视化算法执行过程
4. **复杂度分析** - 展示时间复杂度和空间复杂度
5. **算法分析** - 详细分析算法的优缺点和适用场景

## 算法分类

### 排序算法
- 冒泡排序
- 快速排序
- 插入排序
- 选择排序
- 希尔排序
- 归并排序
- 堆排序
- 基数排序
- 计数排序
- 桶排序

### 搜索算法
- 线性搜索
- 二分搜索
- 深度优先搜索 (DFS)
- 广度优先搜索 (BFS)
- 哈希搜索
- 插值搜索
- 斐波那契搜索

### 数据结构算法
- 链表操作
- 栈操作
- 队列操作
- 树操作
- 图操作
- 哈希表操作

### 动态规划算法
- 斐波那契数列
- 背包问题
- 最长公共子序列
- 最短路径问题
- 矩阵链乘法

### 贪心算法
- 霍夫曼编码
- 活动选择问题
- 最小生成树 (Prim/Kruskal)
- 最短路径 (Dijkstra)

### 分治算法
- 二分查找
- 归并排序
- 快速排序
- 矩阵乘法 (Strassen)
- 大整数乘法

### 回溯算法
- 八皇后问题
- 迷宫问题
- 背包问题
- 旅行商问题
- 正则表达式匹配

### 图算法
- 图的遍历 (DFS/BFS)
- 最短路径 (Dijkstra/Bellman-Ford/Floyd-Warshall)
- 最小生成树 (Prim/Kruskal)
- 拓扑排序
- 关键路径

### 字符串算法
- 字符串匹配 (KMP/Boyer-Moore)
- 字符串编辑距离
- 最长公共子串
- 最长回文子串
- 正则表达式

### 特殊算法
- 并查集
- 红黑树
- 跳表
- 布隆过滤器
- 哈希算法

## 快速开始

### 环境要求
- **Java 17** 或更高版本
- **MySQL 8.0** 或更高版本
- **Redis 6.0** 或更高版本
- **Node.js 18** 或更高版本

### 后端启动

1. 配置数据库
   ```sql
   CREATE DATABASE alg_lab CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```

2. 启动 Redis
   ```bash
   redis-server
   ```

3. 启动后端项目
   ```bash
   cd alg-lab-backend
   mvn spring-boot:run
   ```

   后端服务将在 `http://localhost:8080/api` 启动

### 前端启动

1. 安装依赖
   ```bash
   cd alg-lab-frontend
   npm install
   ```

2. 启动前端项目
   ```bash
   npm run dev
   ```

   前端服务将在 `http://localhost:3000` 启动

## 项目使用

1. 打开浏览器访问 `http://localhost:3000`
2. 在左侧选择算法分类
3. 点击算法卡片查看详细信息
4. 在算法详情页可以查看：
   - 算法描述
   - 代码示例
   - 复杂度分析
   - 动画演示
   - 算法分析

## 分支管理

- **feature-main** - 主分支，稳定可运行的代码
- **feature-算法名** - 各个算法的实现分支，如 `feature-bubble-sort`

## 开发说明

### 添加新算法

1. 在后端 `com.alglab.algorithm` 包下创建新的算法实现
2. 在数据初始化类 `DataInitializer` 中添加算法数据
3. 前端会自动加载新添加的算法

### 动画数据格式

动画数据采用 JSON 格式，示例：
```json
[
  {"step": 1, "array": [5, 3, 8, 4, 2], "compared": [0, 1], "swapped": false},
  {"step": 2, "array": [3, 5, 8, 4, 2], "compared": [1, 2], "swapped": true}
]
```

## 许可证

MIT License

## 贡献

欢迎提交 Issue 和 Pull Request 来添加新的算法或改进现有功能。
