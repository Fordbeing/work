package com.byjs.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author FF
 * @date 2024/7/3
 * TODO:SBS模型
 */
@Getter
@Setter
public class SBSModel {
    private Integer ID; // ID
    private String Name; // 名称
    private String Type; // 类型
    private int CoverRange; // 覆盖范围
    private double TransmitPower; // 传输功率
    private double Bandwidth; // 带宽
    private double CPUResource; // CPU资源
    private double MemoryResource; // 内存资源
    private Integer ChannelNumber; // 通道数量
    private double MemorySize; // 存储能力
    private double locationX; // X坐标
    private double locationY; // Y坐标

//    public SBSModel() {
//        this.ID = Integer.valueOf(IdUtil.simpleUUID()); // ID1
//        this.Name = "SBS"+this.ID; // 名称MBS
//        this.Type = "SBS"; // 类型MBS
//        this.CoverRange = 70; // 覆盖范围70米
//        this.TransmitPower = 23; // 传输功率23dBm
//        this.Bandwidth = 10; // 带宽10MHz
//        this.CPUResource = 0.5; // CPU资源0.5
//        this.MemoryResource = 0.5; // 内存资源0.5
//        this.ChannelNumber = 5; // 通道数量5
//        this.MemorySize = 500; // 存储能力500MB
//        System.out.println("SBS模型初始化完成");
//    }

}
