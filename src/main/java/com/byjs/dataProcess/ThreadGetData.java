package com.byjs.dataProcess;

import com.byjs.model.PreferencesList;
import com.byjs.model.TaskUserAndConnectSBS;
import com.byjs.utils.GetAllTaskUserAndConnectSBS;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author FF
 * @date 2024/9/9
 */
public class ThreadGetData {
    public void getData(GetAllTaskUserAndConnectSBS getAllTaskUserAndConnectSBS, List<PreferencesList> preferencesList){
        try {
            // 获取所有任务用户和连接的SBS

            List<TaskUserAndConnectSBS> taskUserAndConnectSBSList = getAllTaskUserAndConnectSBS.getAll();

            // 创建一个线程安全的队列
            BlockingQueue<TaskUserAndConnectSBS> taskQueue = new LinkedBlockingQueue<>(taskUserAndConnectSBSList);

            // 创建并启动线程
            int numberOfThreads = 5; // 可以根据需要调整线程数
            for (int i = 0; i < numberOfThreads; i++) {
                new Thread(new TaskWorker(taskQueue,preferencesList)).start(); // 创建线程并启动
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
