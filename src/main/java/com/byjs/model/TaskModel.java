package com.byjs.model;

import lombok.Data;

/**
 * @author FF
 * @date 2024/7/3
 * TODO:任务模型
 * 任务数量只有100个，随机分配给100个设备
 */
@Data
public class TaskModel {
    private Integer ID; // 任务模型ID
    private Integer TaskSize;// 任务大小
    private Integer CPUResource;// CPU资源
    private Integer MemoryResource;// 内存资源

}
