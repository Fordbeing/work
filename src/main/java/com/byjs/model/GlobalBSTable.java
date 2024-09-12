package com.byjs.model;

import lombok.Data;

import java.lang.reflect.Array;
import java.util.*;

@Data
public class GlobalBSTable {
    private List<PreferencesList> preferencesList; // 初始化preferencesList为ArrayList

    // 根据加权平均对preferencesList进行排序
    public List<PreferencesList> sortPreferencesListByWeightedAverage(double transmitPowerWeight, double bandwidthWeight,
                                                                      double cpuResourceWeight, double memoryResourceWeight,
                                                                      double memorySizeWeight) {
        preferencesList.sort(Comparator.comparingDouble(
                plist -> plist.calculateWeightedAverage(transmitPowerWeight, bandwidthWeight,
                        cpuResourceWeight, memoryResourceWeight, memorySizeWeight)
        )); // 反转排序顺序
        Collections.reverse(preferencesList);
        return preferencesList;
    }
}
