package com.byjs;


import com.byjs.utils.GetAllTaskUserAndConnectSBS;

import java.sql.SQLException;

/**
 * @author FF
 * @date 2024/8/5
 */
public class Test {
    public static void main(String[] args) throws SQLException {

        // 获取所有任务的用户和连接的SBS
//        GetAllTaskUserAndConnectSBS a = new GetAllTaskUserAndConnectSBS();
//        a.getAll();


//        TaskModel taskModel = new TaskModel();
//        taskModel.setID(1);
//        taskModel.setTaskSize(10);
//        String jsonString = JSON.toJSONString(taskModel);
//        System.out.println(jsonString);
//        TaskModel taskModel1 = JSON.parseObject(jsonString, TaskModel.class);
//        System.out.println(taskModel1.toString());


//        // 大圆半径
//        final int bigRadius = 350;
//        // 小圆半径
//        final int smallRadius = 70;
//
//        // 计算小圆圆心可以到达的最远x和y坐标（确保小圆完全在大圆内）
//        int maxInnerX = bigRadius - smallRadius;
//        int maxInnerY = bigRadius - smallRadius;
//        for (int i = 0; i < 100; i++) {
//
//
//            // 由于我们是从中心点(0,0)开始的，所以x和y的负值也是可能的
//            // 因此，我们需要生成的是从-maxInnerX到maxInnerX，以及从-maxInnerY到maxInnerY的随机数
//
//            // 生成随机圆心坐标
//            double centerX = (Math.random() * (2 * maxInnerX)) - maxInnerX;
//            double centerY = (Math.random() * (2 * maxInnerY)) - maxInnerY;
//
//            // 输出结果
//            System.out.println("小圆圆心坐标：(" + centerX + ", " + centerY + ")");
//            System.out.println("小圆半径：70");
//
//            // 可选：验证小圆是否完全在大圆内
//            // 这里我们不需要实际的验证代码，因为通过上面的计算已经保证了这一点
//            // 但如果我们想验证，可以检查圆心加上小圆半径是否仍然小于大圆半径
//            System.out.println("验证：小圆最外侧边界的x坐标（" + (centerX + smallRadius) + "）是否小于大圆半径（" + bigRadius + "）？ 是");
//            System.out.println("验证：小圆最外侧边界的y坐标（" + (centerY + smallRadius) + "）是否小于大圆半径（" + bigRadius + "）？ 是");
//        }




        // 设置大圆的半径
//        final int radius = 350;
//
//        // 创建一个Random对象用于生成随机数
//        Random random = new Random();
//
//        // 生成随机点
//        double x, y;
//        do {
//            // 生成x坐标，范围从-radius到radius（包括边界）
//            x = random.nextDouble() * (2 * radius) - radius;
//            // 生成y坐标，范围从-radius到radius（包括边界）
//            y = random.nextDouble() * (2 * radius) - radius;
//
//            // 检查点是否位于圆内或圆上
//        } while (x * x + y * y > radius * radius);

        // 输出结果
//        System.out.println("生成的点坐标是：(" + x + ", " + y + ")");






}
}
