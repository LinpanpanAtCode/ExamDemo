package com.migu.schedule.inter;

public interface ServerNodeOption {
    /**
     * 添加任务到任务列表
     */
    void addTask2ServerNode();

    /**
     * 移除任务
     */
    void removeTaskFromServerNode(int taskId);

    /**
     * 获取当前服务结点的任务数量
     * @return
     */
    int getTaskTotalNum();

    /**
     * 获取当前服务结点的消耗数量
     * @return
     */
    int getConsumptionTotal();

    /**
     * 获取服务任务队列升序编号
     * @return
     */
    int getServerInt();
}
