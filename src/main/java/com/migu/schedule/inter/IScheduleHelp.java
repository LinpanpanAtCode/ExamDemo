package com.migu.schedule.inter;

import com.migu.schedule.info.MyTaskInfo;

import java.util.List;

public interface IScheduleHelp {
    /**
     * 根据任务的数量和服务结点的数量找到所有和搭配的任务分配数组
     * @return
     */
    public List<List<Integer>> getInitArrayNum(int serverNodeNum, List<MyTaskInfo> myTaskInfoList);

    /**
     * 根据任务分配数量数组获取所有任务分配的组合
     * @param initArrayNum
     * @return
     */
    public List<List<Integer>> getTaskDistByInitArrayNum(List<Integer> initArrayNum);

    /**
     * 根据任务分配的组合获取分配消耗量最小的值组合的下标列表
     * 若只有一个则就只有这个组合的消耗量最小
     * 若有多个则代表消耗量差距最小的组合有多个
     * @param list
     * @return
     */
    public List<Integer> getAbsConsumptionDValue(List<int[]> list);

    /**
     * 若小号差距量最小的组合有多个，则获取任务数量最小的差值下标组合
     * 若只有一个则代表在消耗量相同情况下，任务数量差值最小的组合有且仅有一个
     * 若有多个则代表在消耗量相同情况下，任务数量差值最小的组合有多个
     * @return
     */
    public List<Integer> getAbsTaskNumDValue(List<Integer> list);


}
