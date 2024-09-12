package com.byjs.model;

import lombok.Data;

/**
 * @author FF
 * @date 2024/8/2
 */

/*
首先先创建随机的任务信息，填入到任务表中，
再从任务表中获取任务信息，
再创建用户设备信息，填入到用户设备表中，
最后从用户设备表中获取用户设备信息，其中的信道选择，任务大小，任务发出时间，任务结束时间，等选择好边缘服务器之后再填入
选择好边缘服务器之后在填入的信息有：选择了的边缘服务器ID，选择的频道，时延，选择的任务发出时间，选择的任务结束时间
* */
@Data
public class UserDevice {
    private Integer ID;// 用户ID
    private Integer TaskID; // 任务ID
    private Integer TaskSize; // 任务大小
    private Integer ChannelSelected;// 频道选择
    private Integer TaskTime;    // 任务发出时间
    private Integer TaskEndTime;    // 任务结束时间
    private double locationX; // 用户位置坐标X
    private double locationY; // 用户位置坐标Y
    private String sbsList; // 可用的边缘服务器列表，直接存放边缘服务器ID就行
}
