package com.migu.schedule;


import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.MyTaskInfo;
import com.migu.schedule.info.ServerNode;
import com.migu.schedule.info.TaskInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 *类名和方法不能修改
 */
public class Schedule {
    // 挂起队列
    private List<MyTaskInfo> waitTaskInfoList = new ArrayList<MyTaskInfo>();
    //
    private List<ServerNode> serverNodeList = new ArrayList<ServerNode>();

    public int init() {
        // TODO 方法未实现
        if (waitTaskInfoList != null && waitTaskInfoList.size() > 0){
            waitTaskInfoList.clear();
        }
        if (serverNodeList != null && serverNodeList.size() > 0){
            serverNodeList.clear();
        }
        return ReturnCodeKeys.E001;
    }


    public int registerNode(int nodeId) {
        // TODO 方法未实现
        if (nodeId <= 0) return ReturnCodeKeys.E004;
        int pos = checkNodeIdVaild(nodeId);
        if (pos >= 0) {
            return ReturnCodeKeys.E005;
        }
        ServerNode serverNode = new ServerNode(nodeId);
        serverNodeList.add(serverNode);
        return ReturnCodeKeys.E003;
    }

    public int unregisterNode(int nodeId) {
        // TODO 方法未实现
        if (nodeId < 0) return ReturnCodeKeys.E004;
        int pos = checkNodeIdVaild(nodeId);
        if (pos < 0) {
            return ReturnCodeKeys.E007;
        }
        // 获取该服务结点内的任务列表
        List<MyTaskInfo> taskInfoList = serverNodeList.get(pos).getTaskInfoList();
        // 若任务列表不为空，则将任务列表加入到挂起状态
        if (taskInfoList != null && taskInfoList.size() > 0) {
            waitTaskInfoList.addAll(taskInfoList);
        }
        // 移除服务结点
        serverNodeList.remove(pos);
        return ReturnCodeKeys.E006;
    }


    public int addTask(int taskId, int consumption) {
        // TODO 方法未实现
        if (taskId <= 0) {
            return ReturnCodeKeys.E009;
        }
        int pos = checkTaskInWaitingList(taskId);
        if (pos >= 0){
            return ReturnCodeKeys.E010;
        }
        MyTaskInfo myTaskInfo = new MyTaskInfo();
        myTaskInfo.setTaskId(taskId);
        myTaskInfo.setConsumption(consumption);
        waitTaskInfoList.add(myTaskInfo);
        return ReturnCodeKeys.E008;
    }


    public int deleteTask(int taskId) {
        // TODO 方法未实现
        if (taskId < 0) {
            return ReturnCodeKeys.E009;
        }
        int pos = checkTaskInWaitingList(taskId);
        if (pos < 0){
            return ReturnCodeKeys.E012;
        }
        waitTaskInfoList.remove(pos);
        return ReturnCodeKeys.E011;
    }


    public int scheduleTask(int threshold) {
        // TODO 方法未实现
        if (threshold <= 0){
            return ReturnCodeKeys.E002;
        }
        List<MyTaskInfo> allMyTaskInfoList = getAllMyTaskInfoList();
        if (allMyTaskInfoList == null || allMyTaskInfoList.size() < 0){
            return ReturnCodeKeys.E014;
        }

        return ReturnCodeKeys.E000;
    }


    public int queryTaskStatus(List<TaskInfo> tasks) {
        // TODO 方法未实现
        if (serverNodeList == null || serverNodeList.size() <= 0){
            return ReturnCodeKeys.E016;
        }
        for(ServerNode serverNode : serverNodeList){
            if (serverNode.getTaskInfoList() != null || serverNodeList.size() <0){
                continue;
            }
            tasks.addAll(serverNode.getTaskInfoList());
        }
        if (tasks == null || tasks.size() <= 0){
            return ReturnCodeKeys.E016;
        }
        Collections.sort(tasks, new Comparator<TaskInfo>() {
            public int compare(TaskInfo o1, TaskInfo o2) {
                if (o1 == null || o2 == null) {
                    return 0;
                }
                return o1.getTaskId() - o2.getTaskId();
            }
        });
        return ReturnCodeKeys.E015;
    }
    //----------------------------------以下为自定义方法-----------------------------------------//
    /**
     * 查询服务结点是否存在
     * 若存在则返回服务结点的在服务结点列表里的下标
     * -1 代表不存在该服务结点
     * @param nodeId
     * @return
     */
    private int checkNodeIdVaild(int nodeId) {
        if (serverNodeList == null || serverNodeList.size() <= 0) {
            return -1;
        }
        for (int i = 0; i < serverNodeList.size(); i++) {
            ServerNode serverNode = serverNodeList.get(i);
            if (serverNode.getNodeId() == nodeId) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 查询任务列表是否存在挂起队列
     * 若存在则返回任务的在挂起队列里的下标
     * -1 代表不存在该任务
     * @param taskId
     * @return
     */
    private int checkTaskInWaitingList(int taskId){
        if (waitTaskInfoList == null || waitTaskInfoList.size() < 0){
            return  -1;
        }
        for(int i = 0 ; i < waitTaskInfoList.size() ; i++){
            MyTaskInfo myTaskInfo = waitTaskInfoList.get(i);
            if (myTaskInfo.getTaskId() == taskId){
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取所有的任务列表
     * @return
     */
    private List<MyTaskInfo> getAllMyTaskInfoList(){
        List<MyTaskInfo> allMyTaskInfoList = new ArrayList<MyTaskInfo>();
        //若挂起列表里面有任务，获取所有的挂起任务列表
        if (waitTaskInfoList != null && waitTaskInfoList.size() > 0){
            allMyTaskInfoList.addAll(waitTaskInfoList);
        }
        // 获取所有已经分配正在运行的任务
        if (serverNodeList != null && serverNodeList.size() >0){
            for(ServerNode serverNode : serverNodeList){
                if (serverNode.getTaskInfoList() != null && serverNode.getTaskInfoList().size() >0) {
                    allMyTaskInfoList.addAll(serverNode.getTaskInfoList());
                }
            }
        }
        return allMyTaskInfoList;
    }
}
