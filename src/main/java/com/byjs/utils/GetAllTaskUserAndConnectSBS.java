package com.byjs.utils;

import com.byjs.model.TaskUserAndConnectSBS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author FF
 * @date 2024/9/9
 */
// 获取所有任务用户和可连接的SBS
public class GetAllTaskUserAndConnectSBS {

    public List<TaskUserAndConnectSBS> getAll() throws SQLException {
        List<TaskUserAndConnectSBS> taskUserAndConnectSBSS = new ArrayList<>();
        Connection mysqlConnection = MysqlConnection.CreateConnection();

        // 创建两个Statement对象，分别用于不同的查询
        Statement userDeviceStmt = mysqlConnection.createStatement();
        Statement taskStmt = mysqlConnection.createStatement();

        String userDeviceSql = "SELECT * FROM userdevice";
        ResultSet userDeviceResultSet = userDeviceStmt.executeQuery(userDeviceSql);

        try {
            while (userDeviceResultSet.next()) {
                String taskId = userDeviceResultSet.getString("TaskID");
                if (taskId != null) {
                    TaskUserAndConnectSBS taskUserAndConnectSBS = new TaskUserAndConnectSBS();
                    taskUserAndConnectSBS.setId(userDeviceResultSet.getInt("ID"));
                    taskUserAndConnectSBS.setTaskId(taskId);
                    taskUserAndConnectSBS.setSBSList(getSbsList(userDeviceResultSet));

                    // 查询对应task的CPU和RAM资源
                    String taskSql = "SELECT CPUResource, RAMResource FROM tasks WHERE ID=" + taskId;
                    ResultSet taskResultSet = taskStmt.executeQuery(taskSql);

                    try {
                        if (taskResultSet.next()) {
                            taskUserAndConnectSBS.setCPUResource(taskResultSet.getInt("CPUResource"));
                            taskUserAndConnectSBS.setRAMResource(taskResultSet.getInt("RAMResource"));
                        }
                    } finally {
                        // 关闭task查询的ResultSet
                        if (taskResultSet != null) {
                            taskResultSet.close();
                        }
                    }

                    System.out.println(taskUserAndConnectSBS);
                    taskUserAndConnectSBSS.add(taskUserAndConnectSBS);
                }
            }
        } finally {
            // 关闭userdevice查询的ResultSet
            if (userDeviceResultSet != null) {
                userDeviceResultSet.close();
            }
            // 关闭Statement
            if (userDeviceStmt != null) {
                userDeviceStmt.close();
            }
            if (taskStmt != null) {
                taskStmt.close();
            }
            // 关闭连接
            if (mysqlConnection != null) {
                mysqlConnection.close();
            }
        }

        return taskUserAndConnectSBSS;
    }



    // 这里就是把sbsList 转换为 List<Integer>
    public List<Integer> getSbsList(ResultSet resultSet) throws SQLException {
        // 从 ResultSet 中获取 VARCHAR 类型的数据
        String sbsListString = resultSet.getString("sbsList");

        // 检查 sbsListString 是否为 null 或者为空
        if (sbsListString == null || sbsListString.trim().isEmpty()) {
            return new ArrayList<>();
        }

        // 去掉括号并拆分字符串
        sbsListString = sbsListString.replaceAll("[\\[\\] ]", "");
        String[] sbsListArray = sbsListString.split(",");

        // 将字符串数组转换为整数列表
        List<Integer> sbsList = new ArrayList<>();
        for (String s : sbsListArray) {
            if (!s.isEmpty()) {
                sbsList.add(Integer.parseInt(s));
            }
        }

        return sbsList;
    }



}
