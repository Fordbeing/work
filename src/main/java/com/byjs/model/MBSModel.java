package com.byjs.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author FF
 * @date 2024/7/3
 * TODO:MBS模型
 */
@Getter
@Setter
public class MBSModel {
    private Integer ID; // ID
    private String Name; // 名称
    private String Type; // 类型
    private int Num; // 数量
    private int CoverRange; // 覆盖范围
    private double TransmitPower; // 传输功率
    private double Bandwidth; // 带宽
    private double CPUResource; // CPU资源
    private double MemoryResource; // 内存资源
    private Integer ChannelNumber; // 通道数量
    private double MemorySize; // 存储能力
    private double locationX; // X坐标
    private double locationY; // Y坐标

    public MBSModel() {
        this.ID = 1; // ID1
        this.Name = "MBS"; // 名称MBS
        this.Type = "MBS"; // 类型MBS
        this.Num = 1; // 数量1
        this.CoverRange = 350; // 覆盖范围350米
        this.TransmitPower = 43; // 传输功率43dBm
        this.Bandwidth = 20; // 带宽20MHz
        this.CPUResource = 200; // CPU资源0.5
        this.MemoryResource = 0.5; // 内存资源0.5
        this.ChannelNumber = 5; // 通道数量5
        this.MemorySize = 1000; // 存储能力1000MB
        this.locationX = 0;
        this.locationY = 0;
    }

}
