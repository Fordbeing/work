package com.byjs;

import com.byjs.dataInit.DataInit;
import com.byjs.dataInit.ProjectSBSInit;
import com.byjs.dataInit.ProjectTaskInit;
import com.byjs.dataInit.ProjectUserDeviceInit;
import com.byjs.dataProcess.TaskWorker;
import com.byjs.dataProcess.ThreadGetData;
import com.byjs.model.PreferencesList;
import com.byjs.model.TaskUserAndConnectSBS;
import com.byjs.utils.GetAllTaskUserAndConnectSBS;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * @author FF
 * @date 2024/7/9
 */
public class Main {
    public static void main(String[] args) throws Exception {
        // 初始化项目
        List<PreferencesList> preferencesLists = DataInit.init(); // 得到返回的偏好列表
        System.out.println(preferencesLists); // 打印偏好列表，这就是经过排序之后的偏好列表,第一次排好的偏好列表
        // 以下是项目持续运行
        // 下面就要得到有任务的用户列表，然后还要得到有任务的用户对应可连接的边缘设备，
        GetAllTaskUserAndConnectSBS getAllTaskUserAndConnectSBS = new GetAllTaskUserAndConnectSBS();
        ThreadGetData threadGetData = new ThreadGetData();
        threadGetData.getData(getAllTaskUserAndConnectSBS, preferencesLists); // 这里面就是启动多线程获取数据，同时进行数据处理，在TaskWorker的run函数中
        // 然后还要设置一个sort函数，为每个用户可连接的列表进行sort，然后再分配资源，然后更新大表






    }
}
