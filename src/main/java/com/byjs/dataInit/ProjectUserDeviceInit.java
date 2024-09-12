package com.byjs.dataInit;

import com.byjs.model.TaskModel;
import com.byjs.utils.LocationUtil;
import com.byjs.utils.MysqlConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * @author FF
 * @date 2024/8/2
 * 初始化用户设备
 */
public class ProjectUserDeviceInit {
    // 初始化用户设备
    // 先把任务基本信息插入到用户设备表中
    Connection conn = MysqlConnection.CreateConnection();
    TaskModel taskModel = new TaskModel();
    Random random = new Random();

    public void init() throws Exception {
        System.out.println("开始初始化用户设备");
        long startTime = System.currentTimeMillis();
        userDeviceInit(); // 假设这个方法已经创建了用户设备

        String sql = "select * from tasks";
        Statement stmt = conn.createStatement(); // 创建一个Statement对象
        ResultSet resultSet = stmt.executeQuery(sql);
        List<String> list = new ArrayList<>(); // 可选：收集所有SQL语句，以便调试
        Map<Integer, Integer> hasTaskUserDevices = new HashMap<>();

        while (resultSet.next()) {
            int taskID = resultSet.getInt("ID");
            int taskSize = resultSet.getInt("TaskSize");
            int randomId = (int) (Math.random() * 600) + 1;
            // 后面将rangomId保存起来，这些用户都是有下载任务的,保存格式为 userId:taskId，用户设备号与任务id对应
            // 后面再将这些任务随机时间进行发出
            hasTaskUserDevices.put(randomId, taskID);

            String sql1 = "UPDATE userdevice SET TaskID = " + taskID + ", TaskSize = " + taskSize + " WHERE id = " + randomId;
            list.add(sql1); // 可选：收集SQL语句
            stmt.addBatch(sql1);
        }
        int[] result = stmt.executeBatch(); // 执行批处理
        stmt.close(); // 关闭Statement对象
        resultSet.close(); // 关闭ResultSet对象
        long endTime = System.currentTimeMillis();
        System.out.println("初始化用户设备完成，耗时：" + (endTime - startTime) + "ms");
    }

    // 初始化用户设备
    public void userDeviceInit() throws SQLException {
        for (int i = 1; i <= 600; i++) {
            // 用于存放用户设备信息，存放一个列表数据（ID,坐标x，坐标y）
            List<Integer> sbsList = new ArrayList<>();
            sbsList.add(0); // 每一个用户都能连接上MBS
            double x = 0;
            double y = 0;
            double sbsX = 0;
            double sbsY = 0;
            double distance = 0;

            // 80%的概率生成在某个SBS的范围内
            double chance = random.nextDouble();
            if (chance < 0.8) {
                // 选择一个随机的SBS
                int sbsId = random.nextInt(10) + 1; // 选择1到10的随机数作为SBS ID
                String sql = "select locationX, locationY from sbsmodel where ID = " + sbsId;
                try {
                    ResultSet resultSet = conn.createStatement().executeQuery(sql);
                    if (resultSet.next()) {
                        sbsX = resultSet.getDouble("locationX");
                        sbsY = resultSet.getDouble("locationY");
                        // 在SBS半径内生成随机坐标
                        double angle = random.nextDouble() * 2 * Math.PI;
                        double radius = random.nextDouble() * 70; // 半径范围是0到70
                        x = sbsX + radius * Math.cos(angle);
                        y = sbsY + radius * Math.sin(angle);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                // 20%的概率生成在随机位置
                x = LocationUtil.getLocation();
                y = LocationUtil.getLocation();
            }

            // 判断生成的位置是否在SBS范围内，并填充sbsList
            for (int j = 1; j <= 10; j++) {
                String sql = "select locationX, locationY from sbsmodel where ID = " + j;
                try {
                    ResultSet resultSet = conn.createStatement().executeQuery(sql);
                    while (resultSet.next()) {
                        sbsX = resultSet.getDouble("locationX");
                        sbsY = resultSet.getDouble("locationY");
                        distance = Math.sqrt(Math.pow(x - sbsX, 2) + Math.pow(y - sbsY, 2));
                        if (distance <= 70) {
                            sbsList.add(j);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            // 打印日志
            System.out.println("用户设备" + i + "的坐标为：(" + x + ", " + y + ")");
            System.out.println("用户设备" + i + "的可用SBS列表为：" + sbsList);
            System.out.println();

            // 插入用户设备数据
            String sql1 = "insert into userdevice (ID,locationX,locationY,sbsList)" +
                    " values ('" + i + "','" + x + "','" + y + "','" + sbsList + "')";
            try {
                conn.createStatement().execute(sql1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}
