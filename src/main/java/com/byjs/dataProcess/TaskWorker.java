package com.byjs.dataProcess;

import com.byjs.model.GlobalBSTable;
import com.byjs.model.PreferencesList;
import com.byjs.model.TaskUserAndConnectSBS;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

// 实现 Runnable 接口的任务处理类
public class TaskWorker implements Runnable {
    private final BlockingQueue<TaskUserAndConnectSBS> taskQueue; // 任务队列
    private final List<PreferencesList> preferencesListList; // 偏好列表

    private final Lock taskLock = new ReentrantLock(); // 任务锁对象
    private final Lock preferenceListLock = new ReentrantLock(); // 偏好列表锁对象

    // 构造函数，初始化任务队列和偏好列表
    public TaskWorker(BlockingQueue<TaskUserAndConnectSBS> taskQueue, List<PreferencesList> preferencesListList) {
        this.taskQueue = taskQueue;
        this.preferencesListList = preferencesListList;
    }

    // 运行方法，处理任务队列中的任务
    @Override
    public void run() {
        try {
            while (true) {
                TaskUserAndConnectSBS task = taskQueue.take(); // 从队列中获取任务
                processTask(task); // 处理任务
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // 恢复中断状态
            e.printStackTrace();
        }
    }

    // 处理任务的方法
    private void processTask(TaskUserAndConnectSBS task) {
        taskLock.lock(); // 获取任务锁，确保互斥处理
        try {
            // 遍历任务中的SBS列表
            for (Integer sbs : task.getSBSList()) {
                preferenceListLock.lock(); // 获取偏好列表锁，确保线程安全
                try {
                    // 查找与任务SBS匹配的偏好列表项
                    List<PreferencesList> matchedPreferences = preferencesListList.stream()
                            .filter(preference -> task.getSBSList().contains(preference.getSbsID()))
                            .collect(Collectors.toList());

                    if (!matchedPreferences.isEmpty()) {
                        GlobalBSTable globalBSTable = new GlobalBSTable();
                        globalBSTable.setPreferencesList(matchedPreferences);
                        List<PreferencesList> sortedPreferences = globalBSTable.sortPreferencesListByWeightedAverage(); // 排序并采用默认值

                        boolean isUpdated = false;
                        for (PreferencesList preference : sortedPreferences) {
                            // 检查并更新偏好列表中的资源
                            if (preference.getMemorySize() > 0 &&
                                    preference.getMemorySize() > task.getRAMResource() &&
                                    preference.getCPUResource() > 0 &&
                                    preference.getCPUResource() > task.getCPUResource() &&
                                    preference.getChannelNumber() > 0) {

                                // 更新内存、CPU和通道数
                                preference.setMemorySize(preference.getMemorySize() - task.getRAMResource());
                                preference.setCPUResource(preference.getCPUResource() - task.getCPUResource());
                                preference.setChannelNumber(preference.getChannelNumber() - 1);
                                System.out.println(preference); // 打印更新后的偏好项,以验证更新是否成功，资源确实是减少了

                                // 更新偏好列表
                                int index = preferencesListList.indexOf(preference); // 获取匹配项的原始索引
                                if (index >= 0) {
                                    preferencesListList.set(index, preference); // 更新偏好列表
                                }
                                isUpdated = true;
                                break; // 找到符合条件的项后退出循环
                            }
                        }

                        if (!isUpdated) {
                            // TODO: 处理没有符合条件的偏好项的逻辑， 比如将任务添加到等待队列中
                            taskQueue.add(task); // 将任务添加到等待队列中，等待下次处理
                            System.out.println("没有符合条件的偏好项。");
                        }
                    }
                } finally {
                    preferenceListLock.unlock(); // 释放偏好列表锁
                }
            }
        } finally {
            taskLock.unlock(); // 释放任务锁
        }
    }
}
