package com.byjs.model;

import lombok.Data;

import java.lang.reflect.Array;
import java.util.*;

@Data
public class GlobalBSTable {
    private List<PreferencesList> preferencesList; // 初始化preferencesList为ArrayList

    // 根据加权平均对preferencesList进行排序
    public List<PreferencesList> sortPreferencesListByWeightedAverage() {
        // 设置默认权重值
        double defaultTransmitPowerWeight = 1.0;
        double defaultBandwidthWeight = 1.0;
        double defaultCpuResourceWeight = 1.0;
        double defaultMemoryResourceWeight = 1.0;
        double defaultMemorySizeWeight = 1.0;

        // 调用带有权重参数的方法
        return sortPreferencesListByWeightedAverage(defaultTransmitPowerWeight, defaultBandwidthWeight,
                defaultCpuResourceWeight, defaultMemoryResourceWeight, defaultMemorySizeWeight);
    }

    public List<PreferencesList> sortPreferencesListByWeightedAverage(double transmitPowerWeight, double bandwidthWeight,
                                                                      double cpuResourceWeight, double memoryResourceWeight,
                                                                      double memorySizeWeight) {
        // 根据权重计算并排序
        preferencesList.sort(Comparator.comparingDouble(
                plist -> plist.calculateWeightedAverage(transmitPowerWeight, bandwidthWeight,
                        cpuResourceWeight, memoryResourceWeight, memorySizeWeight)
        ));
        // 反转排序顺序
        Collections.reverse(preferencesList);
        return preferencesList;
    }

}
