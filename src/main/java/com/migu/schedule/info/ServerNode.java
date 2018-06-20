package com.migu.schedule.info;

import com.migu.schedule.inter.ServerNodeOption;

import java.util.List;

public class ServerNode implements ServerNodeOption {
    // 服务器结点id
    private int nodeId;
    // 当前服务器任务队列
    private List<MyTaskInfo> taskInfoList;

    public ServerNode(int nodeId){
        this.nodeId = nodeId;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public List<MyTaskInfo> getTaskInfoList() {
        return taskInfoList;
    }

    public void setTaskInfoList(List<MyTaskInfo> taskInfoList) {
        this.taskInfoList = taskInfoList;
    }

    public void addTask2ServerNode() {

    }

    public void removeTaskFromServerNode(int taskId) {

    }

    public int getTaskTotalNum() {
        return 0;
    }

    public int getConsumptionTotal() {
        return 0;
    }

    public int getServerInt() {
        return 0;
    }
}
