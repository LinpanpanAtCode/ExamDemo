package com.migu.schedule;

import com.migu.schedule.info.MyTaskInfo;
import com.migu.schedule.inter.IScheduleHelp;

import java.util.ArrayList;
import java.util.List;

public class ScheduleHelp implements IScheduleHelp {
    public List<List<Integer>> getInitArrayNum(int serverNodeNum, List<MyTaskInfo> myTaskInfoList) {
        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
        int taskNum = myTaskInfoList.size();
        if (taskNum <= serverNodeNum) {
            List<Integer> resultArrayItem = new ArrayList<Integer>();
            for (int i = 0; i < resultArrayItem.size(); i++) {
                if (i < taskNum) {
                    resultArrayItem.set(i, 1);
                } else {
                    resultArrayItem.set(i, 0);
                }
            }
            resultList.add(resultArrayItem);
            return resultList;
        }
        return resultList;
    }

    public List<List<Integer>> getTaskDistByInitArrayNum(List<Integer> initArrayNum) {

        return null;
    }

    public List<Integer> getAbsConsumptionDValue(List<int[]> list) {
        return null;
    }

    public List<Integer> getAbsTaskNumDValue(List<Integer> list) {
        return null;
    }

    //---------------------------------------------------------------//
    private static void print(List<Integer> list,int serverNum,List<List<Integer>> allList) {
//        if (serverNum == list.size()) {
            allList.add(list);
            for (Integer k : list) {
                System.out.print(k + "+");
            }
//        }
    }

    private static void f(int n, List<Integer> list, int start,int serverNum,List<List<Integer>> allList) {
        if (n == 1) {
            print(list,serverNum,allList);
            System.out.println(1);
        } else {
            for (int i = start; i <=n / 2; i++) {
                list.add(i);
                f(n - i, list, i,serverNum,allList);
                list.remove(list.size() -1);
            }
            print(list,serverNum,allList);
            List<Integer> mList = new ArrayList<Integer>();
            for(int i : list){
                mList.add(i);
            }
            mList.add(n);
            if (mList.size() == serverNum){
                allList.add(mList);
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        f(9,list, 1,4,new ArrayList<List<Integer>>());
    }

}
