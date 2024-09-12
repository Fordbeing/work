package com.byjs.dataProcess;

import com.byjs.model.PreferencesList;
import com.byjs.model.TaskUserAndConnectSBS;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 实现 Runnable 接口的任务处理类
public class TaskWorker implements Runnable {
    private final BlockingQueue<TaskUserAndConnectSBS> taskQueue;
    private final List<PreferencesList> preferencesListList;

    private final Lock taskLock = new ReentrantLock(); // 锁对象
    private final Lock preferenceListLock = new ReentrantLock(); // 偏好列表锁对象



    public TaskWorker(BlockingQueue<TaskUserAndConnectSBS> taskQueue, List<PreferencesList> preferencesListList) {
        this.taskQueue = taskQueue;
        this.preferencesListList = preferencesListList;
    }

    @Override
    public synchronized void run() {
        try {
            while (true) {
                TaskUserAndConnectSBS task = taskQueue.take(); // 从队列中获取任务
                processTask(task, preferencesListList); // 处理任务的逻辑
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // 恢复中断状态
            e.printStackTrace();
        }
    }

    private void processTask(TaskUserAndConnectSBS task, List<PreferencesList> preferencesListList) {

        // 尝试获取 taskLock，确保任务的互斥处理
        taskLock.lock();
        try {
            System.out.println("正在处理用户的任务: " + task.getTaskId()+"任务中包含的SBS列表: " + task.getSBSList());
            System.out.println();

            // 遍历任务中的SBS列表
            for (Integer sbs : task.getSBSList()) {
                System.out.println("正在处理SBS: " + sbs);

                // 尝试获取 preferenceListLock，确保偏好列表的线程安全
                preferenceListLock.lock();
                try {
                    boolean isPreferenceFound = preferencesListList.stream()
                            .anyMatch(preference -> preference.getSbsID().equals(sbs));

                    if (isPreferenceFound) {
                        System.out.println("SBS " + sbs + " 在偏好列表中找到。");
                        // 对匹配的偏好进行进一步处理
                    } else {
                        System.out.println("SBS " + sbs + " 未在偏好列表中找到。");
                        // 处理偏好列表中没有该SBS的情况
                    }
                } finally {
                    preferenceListLock.unlock(); // 释放偏好列表的锁
                }
            }

            // TODO: 添加处理任务的其他逻辑
        } finally {
            taskLock.unlock(); // 释放任务锁
        }

    }



}