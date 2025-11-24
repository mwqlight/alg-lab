#!/bin/bash

echo "======================================"
echo "          算法实验室停止脚本          "
echo "======================================"

# 停止后端服务
echo "停止后端服务..."
backend_pid=$(lsof -i :8080 | grep LISTEN | awk '{print $2}')
if [ -n "$backend_pid" ]; then
    kill $backend_pid
    echo "后端服务已停止 (PID: $backend_pid)"
else
    echo "后端服务未运行"
fi

# 停止前端服务
echo "停止前端服务..."
frontend_pid=$(lsof -i :3000 | grep LISTEN | awk '{print $2}')
if [ -n "$frontend_pid" ]; then
    kill $frontend_pid
    echo "前端服务已停止 (PID: $frontend_pid)"
else
    echo "前端服务未运行"
fi

# 清理临时文件
echo "清理临时文件..."
rm -f backend.log frontend.log

# 检查是否还有相关进程
echo "检查剩余进程..."
remaining_pids=$(ps aux | grep -E "(java|node)" | grep -v grep | grep -v "$0" | awk '{print $2}')
if [ -n "$remaining_pids" ]; then
    echo "发现剩余进程: $remaining_pids"
    echo "是否需要强制终止? (y/N)"
    read -r response
    if [[ $response =~ ^[Yy]$ ]]; then
        kill -9 $remaining_pids
        echo "剩余进程已终止"
    fi
fi

echo ""
echo "======================================"
echo "          所有服务已停止！             "
echo "======================================"
