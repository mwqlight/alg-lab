#!/bin/bash

echo "======================================"
echo "          算法实验室启动脚本          "
echo "======================================"

# 检查Java版本
echo "检查Java版本..."
java_version=$(java -version 2>&1 | head -n 1 | cut -d' ' -f3 | cut -d'.' -f1,2)
if [[ "$java_version" < "17.0" ]]; then
    echo "错误: 需要Java 17或更高版本，当前版本为$java_version"
    exit 1
fi
echo "Java版本: $java_version ✓"

# 检查MySQL
echo "检查MySQL连接..."
if ! command -v mysql &> /dev/null; then
    echo "警告: 未找到mysql命令，将尝试启动后端服务"
else
    if mysql -h localhost -P 3306 -u root -proot -e "exit" 2>&1; then
        echo "MySQL连接成功 ✓"
    else
        echo "警告: MySQL连接失败，将尝试启动后端服务"
    fi
fi

# 检查Redis
echo "检查Redis连接..."
if ! command -v redis-cli &> /dev/null; then
    echo "警告: 未找到redis-cli命令，将尝试启动后端服务"
else
    if redis-cli ping | grep -q "PONG"; then
        echo "Redis连接成功 ✓"
    else
        echo "警告: Redis连接失败，将尝试启动后端服务"
    fi
fi

# 启动后端服务
echo ""
echo "启动后端服务..."
cd alg-lab-backend
if mvn spring-boot:run &> ../backend.log & then
    echo "后端服务已启动 (PID: $!)"
    echo "日志文件: ../backend.log"
else
    echo "错误: 后端服务启动失败"
    exit 1
fi
cd ..

# 等待后端启动
sleep 5

# 启动前端服务
echo ""
echo "启动前端服务..."
cd alg-lab-frontend
if npm run dev &> ../frontend.log & then
    echo "前端服务已启动 (PID: $!)"
    echo "日志文件: ../frontend.log"
else
    echo "错误: 前端服务启动失败"
    exit 1
fi
cd ..

echo ""
echo "======================================"
echo "          服务启动完成！               "
echo "======================================"
echo "后端服务: http://localhost:8080/api"
echo "前端服务: http://localhost:3000"
echo ""
echo "按 Ctrl+C 停止所有服务"

# 等待用户中断
wait
