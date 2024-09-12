//package com.byjs.utils;
//
//import com.byjs.model.GlobalBSTable;
//
//import java.util.Comparator;
//
///**
// * @author FF
// * @date 2024/7/9
// * TODO: 偏好列表算法工具类
// */
//public class PreferenceListUtil {
//
//    // 静态方法，不依赖于实例，用于计算单个GlobalBSTable的加权平均值
//    public static double calculateWeightedAverage(GlobalBSTable globalBSTable, double transmitPowerWeight, double bandwidthWeight,
//                                                  double cpuResourceWeight, double memoryResourceWeight,
//                                                  double memorySizeWeight) {
//
//        // 计算加权平均值
//        double weightedAverage = globalBSTable.getTransmitPower() * transmitPowerWeight +
//                globalBSTable.getBandwidth() * bandwidthWeight +
//                globalBSTable.getCPUResource() * cpuResourceWeight +
//                globalBSTable.getMemoryResource() * memoryResourceWeight +
//                globalBSTable.getMemorySize() * memorySizeWeight;
//
//        return weightedAverage;
//    }
//
//    // Comparator用于排序，根据GlobalBSTable的加权平均值进行排序
//    public static Comparator<GlobalBSTable> compareByWeightedAverage(double transmitPowerWeight, double bandwidthWeight,
//                                                                     double cpuResourceWeight, double memoryResourceWeight,
//                                                                     double memorySizeWeight) {
//        return Comparator.comparingDouble(obj -> calculateWeightedAverage(obj, transmitPowerWeight, bandwidthWeight,
//                cpuResourceWeight, memoryResourceWeight, memorySizeWeight));
//    }
//}