package com.byjs.dataInit;

import com.byjs.model.TaskModel;
import com.byjs.utils.MysqlConnection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author FF
 * @date 2024/8/2
 */
public class ProjectTaskInit {

    public void init() throws SQLException {
        long startTime = System.currentTimeMillis();

        System.out.println("任务模型初始化");
        // 创建任务模型，总共有100个任务模型，每个10MB
        System.out.println("任务生成中...");
        Connection connection = MysqlConnection.CreateConnection(); // 后面改成连接池
        for (int i = 1; i <= 100; i++) {
            TaskModel taskModel = new TaskModel();
            taskModel.setTaskSize(10); // 任务大小,先设定为10MB，后面再进行随机
            String sql = "insert into tasks(TaskSize) values" + "(" + taskModel.getTaskSize() + ")";
            assert connection != null;
            connection.createStatement().execute(sql);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("任务初始化完成，耗时：" + (endTime - startTime) + "ms");

    }
}
