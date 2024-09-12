package com.byjs.utils;

import java.util.Random;

/**
 * @author FF
 * @date 2024/8/7
 */
public class LocationUtil {

    // 大圆半径
    private static final int bigRadius = 350;
    // 小圆半径
    private static final int smallRadius = 70;

    // 计算小圆圆心可以到达的最远x和y坐标（确保小圆完全在大圆内）
    private static int maxInnerX = bigRadius - smallRadius;

    // 生成随机圆心坐标X
    public static double getSBSRandomLocation() {
        // 生成随机圆心坐标
        return (Math.random() * (2 * maxInnerX)) - maxInnerX;
    }

    // 生成随机坐标
    static Random random = new Random();

    // 计算用户坐标
    public static double getLocation() {
        // 生成坐标，范围从-radius到radius（包括边界）
        return random.nextDouble() * (2 * bigRadius) - bigRadius;
    }
}
