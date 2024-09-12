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
        String sql = "SELECT * FROM userdevice";
        Statement statement = mysqlConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            if (resultSet.getString("TaskID") != null) {
                TaskUserAndConnectSBS taskUserAndConnectSBS = new TaskUserAndConnectSBS();
                taskUserAndConnectSBS.setId(resultSet.getInt("ID"));
                taskUserAndConnectSBS.setTaskId(resultSet.getString("TaskID"));
                taskUserAndConnectSBS.setSBSList(getSbsList(resultSet));

                System.out.println(taskUserAndConnectSBS);
                taskUserAndConnectSBSS.add(taskUserAndConnectSBS);
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
