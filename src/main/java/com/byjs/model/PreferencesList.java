package com.byjs.model;

import lombok.Data;

@Data
public class PreferencesList {
    private Integer sbsID;
    private String Name;
    private double TransmitPower;
    private double Bandwidth;
    private double CPUResource;
    private double MemoryResource;
    private Integer ChannelNumber;
    private double MemorySize;

    public double calculateWeightedAverage(double transmitPowerWeight, double bandwidthWeight,
                                           double cpuResourceWeight, double memoryResourceWeight,
                                           double memorySizeWeight) {
        return this.TransmitPower * transmitPowerWeight +
                this.Bandwidth * bandwidthWeight +
                this.CPUResource * cpuResourceWeight +
                this.MemoryResource * memoryResourceWeight +
                this.MemorySize * memorySizeWeight;
    }
}
